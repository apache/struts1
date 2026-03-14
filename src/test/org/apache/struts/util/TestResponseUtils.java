/*
 * Copyright 2026 The Struts1-Forever Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts.util;

import junit.framework.TestCase;

/**
 * Unit tests for {@link ResponseUtils#filter(String)}.
 *
 * <p>filter() is the primary XSS-prevention method used throughout Struts tag
 * libraries.  It must correctly escape all five HTML-sensitive characters:
 * {@code < > & " '}</p>
 */
public class TestResponseUtils extends TestCase {

    // ------------------------------------------------------------------
    // Null / empty input
    // ------------------------------------------------------------------

    public void testFilter_Null_ReturnsNull() {
        assertNull(ResponseUtils.filter(null));
    }

    public void testFilter_EmptyString_ReturnsEmptyString() {
        assertEquals("", ResponseUtils.filter(""));
    }

    // ------------------------------------------------------------------
    // No special characters — string must be returned unchanged
    // ------------------------------------------------------------------

    public void testFilter_PlainText_Unchanged() {
        String input = "Hello World 123";
        assertSame("Plain text should be returned as the same object",
                input, ResponseUtils.filter(input));
    }

    public void testFilter_DigitsOnly_Unchanged() {
        String input = "0123456789";
        assertSame(input, ResponseUtils.filter(input));
    }

    // ------------------------------------------------------------------
    // Individual special characters
    // ------------------------------------------------------------------

    public void testFilter_LessThan() {
        assertEquals("&lt;", ResponseUtils.filter("<"));
    }

    public void testFilter_GreaterThan() {
        assertEquals("&gt;", ResponseUtils.filter(">"));
    }

    public void testFilter_Ampersand() {
        assertEquals("&amp;", ResponseUtils.filter("&"));
    }

    public void testFilter_DoubleQuote() {
        assertEquals("&quot;", ResponseUtils.filter("\""));
    }

    public void testFilter_SingleQuote() {
        assertEquals("&#39;", ResponseUtils.filter("'"));
    }

    // ------------------------------------------------------------------
    // XSS payloads
    // ------------------------------------------------------------------

    public void testFilter_ScriptTag() {
        assertEquals(
                "&lt;script&gt;alert(1)&lt;/script&gt;",
                ResponseUtils.filter("<script>alert(1)</script>"));
    }

    public void testFilter_AttributeInjection() {
        // " onmouseover="alert(1)
        assertEquals(
                "&quot; onmouseover=&quot;alert(1)",
                ResponseUtils.filter("\" onmouseover=\"alert(1)"));
    }

    public void testFilter_AmpersandInUrl() {
        assertEquals(
                "a=1&amp;b=2",
                ResponseUtils.filter("a=1&b=2"));
    }

    public void testFilter_AllSpecialCharsInOrder() {
        assertEquals(
                "&lt;&gt;&amp;&quot;&#39;",
                ResponseUtils.filter("<>&\"'"));
    }

    // ------------------------------------------------------------------
    // Mixed content (special chars interspersed with plain text)
    // ------------------------------------------------------------------

    public void testFilter_MixedContent() {
        assertEquals(
                "Hello &lt;World&gt;",
                ResponseUtils.filter("Hello <World>"));
    }

    public void testFilter_LeadingSpecialChar() {
        assertEquals("&lt;start", ResponseUtils.filter("<start"));
    }

    public void testFilter_TrailingSpecialChar() {
        assertEquals("end&gt;", ResponseUtils.filter("end>"));
    }

    public void testFilter_MultipleConsecutiveSpecialChars() {
        assertEquals("&lt;&lt;&lt;", ResponseUtils.filter("<<<"));
    }

    // ------------------------------------------------------------------
    // encodeURL — smoke test (delegates to URLEncoder)
    // ------------------------------------------------------------------

    public void testEncodeURL_SpaceBecomesPlus() {
        String encoded = ResponseUtils.encodeURL("hello world");
        assertTrue("Space should be encoded",
                encoded.contains("+") || encoded.contains("%20"));
    }

    public void testEncodeURL_NullEncoding_UsesUtf8() {
        // Should not throw; falls back to UTF-8
        String encoded = ResponseUtils.encodeURL("test", null);
        assertNotNull(encoded);
    }

    public void testEncodeURL_SpecialChars() {
        String encoded = ResponseUtils.encodeURL("a=1&b=2");
        assertFalse("Raw & should be encoded", encoded.contains("&"));
    }
}
