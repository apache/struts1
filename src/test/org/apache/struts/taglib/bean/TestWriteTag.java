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
package org.apache.struts.taglib.bean;

import javax.servlet.jsp.PageContext;

import junit.framework.TestCase;

import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;
import org.apache.struts.mock.MockJspWriter;
import org.apache.struts.mock.MockPageContext;
import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;

/**
 * Unit tests for {@link WriteTag} focusing on XSS prevention.
 *
 * <h3>XSS Risk in bean:write</h3>
 *
 * <p>{@code <bean:write>} renders a bean property into the HTML output.
 * When {@code filter="true"} (the default), it calls
 * {@link org.apache.struts.taglib.TagUtils#filter(String)} which escapes
 * the five HTML-sensitive characters ({@code < > & " '}).
 * When {@code filter="false"}, the raw value is written directly — this is
 * intentional for trusted content but is XSS-unsafe if user-supplied data
 * reaches the tag.</p>
 *
 * <p>These tests verify that the default {@code filter=true} behaviour is
 * correct for all XSS-relevant payloads, and document that
 * {@code filter=false} bypasses escaping (so callers must ensure the value
 * is already safe).</p>
 */
public class TestWriteTag extends TestCase {

    private MockServletContext context;
    private MockServletConfig config;
    private MockHttpSession session;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockPageContext pageContext;
    private MockJspWriter out;

    protected void setUp() {
        context = new MockServletContext();
        config = new MockServletConfig(context);
        session = new MockHttpSession(context);
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
        pageContext = new MockPageContext(config, request, response);
        out = new MockJspWriter();
        pageContext.setJspWriter(out);
    }

    // ------------------------------------------------------------------
    // Helper
    // ------------------------------------------------------------------

    /**
     * Runs WriteTag.doStartTag() with the given string stored as a request
     * attribute and returns the captured output.
     */
    private String runTag(String attrValue, boolean filter) throws Exception {
        pageContext.setAttribute("testBean", attrValue, PageContext.REQUEST_SCOPE);
        out = new MockJspWriter();
        pageContext.setJspWriter(out);

        WriteTag tag = new WriteTag();
        tag.setPageContext(pageContext);
        tag.setName("testBean");
        tag.setScope("request");
        tag.setFilter(filter);

        tag.doStartTag();
        tag.doEndTag();

        return out.getContent();
    }

    // ------------------------------------------------------------------
    // filter=true (default) — XSS prevention
    // ------------------------------------------------------------------

    public void testWrite_FilterTrue_LessThan_IsEscaped() throws Exception {
        String output = runTag("<script>alert(1)</script>", true);
        assertFalse("Raw < must not appear in output", output.contains("<script>"));
        assertTrue("&lt; must appear in output", output.contains("&lt;"));
    }

    public void testWrite_FilterTrue_ScriptTag_FullyEscaped() throws Exception {
        String output = runTag("<script>alert(1)</script>", true);
        assertEquals("&lt;script&gt;alert(1)&lt;/script&gt;", output);
    }

    public void testWrite_FilterTrue_AttributeInjection_Escaped() throws Exception {
        // Attempt: " onmouseover="alert(1)
        String output = runTag("\" onmouseover=\"alert(1)", true);
        assertFalse("Raw double-quote must not appear", output.contains("\" onmouseover="));
        assertTrue("&quot; must appear", output.contains("&quot;"));
    }

    public void testWrite_FilterTrue_SingleQuoteInjection_Escaped() throws Exception {
        String output = runTag("' or '1'='1", true);
        assertFalse("Raw single-quote must not appear", output.contains("'"));
        assertTrue("&#39; must appear", output.contains("&#39;"));
    }

    public void testWrite_FilterTrue_Ampersand_Escaped() throws Exception {
        String output = runTag("a=1&b=2", true);
        assertFalse("Raw & must not appear", output.contains("a=1&b=2"));
        assertTrue("&amp; must appear", output.contains("&amp;"));
    }

    public void testWrite_FilterTrue_PlainText_Unchanged() throws Exception {
        String output = runTag("Hello World", true);
        assertEquals("Hello World", output);
    }

    public void testWrite_FilterTrue_EmptyString_ProducesNoOutput() throws Exception {
        String output = runTag("", true);
        assertEquals("", output);
    }

    public void testWrite_FilterTrue_JavascriptUrl_Escaped() throws Exception {
        // javascript: URL in a value context — < and > are still the only
        // HTML chars that need escaping here, but the value itself should
        // pass through filter() unmodified except for HTML chars.
        String output = runTag("javascript:alert(1)", true);
        // No HTML-special chars here, so output equals input
        assertEquals("javascript:alert(1)", output);
    }

    // ------------------------------------------------------------------
    // filter=false — raw output (documents XSS risk when misused)
    //
    // These tests document that filter=false passes the value through
    // WITHOUT escaping. They PASS because that is the current behaviour.
    // If caller-supplied data reaches the tag with filter=false, XSS
    // is possible. The fix is always to set filter=true (the default).
    // ------------------------------------------------------------------

    /**
     * With filter=false, a script tag is written raw to the output.
     * This documents that filter=false is XSS-unsafe for user data.
     */
    public void testWrite_FilterFalse_ScriptTag_WrittenRaw() throws Exception {
        String output = runTag("<script>alert(1)</script>", false);
        // filter=false writes the value as-is — this is the current behaviour.
        assertTrue("filter=false must write raw value (XSS-unsafe for user data)",
                output.contains("<script>alert(1)</script>"));
    }

    public void testWrite_FilterFalse_TrustedHtml_PassedThrough() throws Exception {
        // Legitimate use: embedding pre-rendered HTML from a trusted source.
        String trustedHtml = "<strong>Bold</strong>";
        String output = runTag(trustedHtml, false);
        assertEquals(trustedHtml, output);
    }

    // ------------------------------------------------------------------
    // Absent bean with ignore=true — SKIP_BODY, no output
    //
    // When ignore=true, WriteTag checks for the bean's existence via
    // TagUtils.lookup(pageContext, name, scope) before calling the full
    // lookup. If the bean is absent, it returns SKIP_BODY without output.
    // Without ignore=true, an absent bean causes a JspException.
    // ------------------------------------------------------------------

    public void testWrite_AbsentBeanWithIgnore_NoOutput() throws Exception {
        // "absentBean" is deliberately never put into scope
        out = new MockJspWriter();
        pageContext.setJspWriter(out);

        WriteTag tag = new WriteTag();
        tag.setPageContext(pageContext);
        tag.setName("absentBean");
        tag.setScope("request");
        tag.setIgnore(true); // suppress JspException for missing bean

        tag.doStartTag();
        tag.doEndTag();

        assertEquals("Absent bean with ignore=true must produce no output",
                "", out.getContent());
    }

    // ------------------------------------------------------------------
    // Default filter setting — must be true (XSS-safe by default)
    // ------------------------------------------------------------------

    public void testWrite_DefaultFilter_IsTrue() {
        WriteTag tag = new WriteTag();
        assertTrue("filter must default to true for XSS safety", tag.getFilter());
    }
}
