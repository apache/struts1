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
package org.apache.struts.taglib.html;

import javax.servlet.jsp.JspException;
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
 * Tests for {@link OptionTag} HTML escaping (H-3 fix).
 *
 * <p>Verifies that {@code OptionTag} escapes the {@code value} attribute
 * and text content via {@link org.apache.struts.taglib.TagUtils#filter(String)}
 * when the {@code filter} attribute is {@code true} (the default),
 * matching the behavior of {@code <html:options>} and
 * {@code <html:optionsCollection>}.</p>
 */
public class TestOptionTag extends TestCase {

    /**
     * Test subclass that allows injecting body text directly, bypassing the
     * {@code doAfterBody()} body-content lifecycle. This is necessary because
     * {@code OptionTag.doStartTag()} resets {@code text = null} and the real
     * body-content mechanism requires a full JSP container.
     *
     * <p>{@code doStartTag()} is overridden to populate {@code this.text}
     * from the injected value, mirroring what {@code doAfterBody()} does in a
     * real container after the body is evaluated.</p>
     */
    private static class InjectableOptionTag extends OptionTag {
        private String injectedText = null;

        public void setBodyText(String t) {
            this.injectedText = t;
        }

        public int doStartTag() throws JspException {
            this.text = injectedText;
            return EVAL_BODY_TAG;
        }
    }

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
    // Helper: register a minimal SelectTag stub in page context so
    // OptionTag.selectTag() does not throw, then run OptionTag.
    // ------------------------------------------------------------------

    /**
     * Creates a SelectTag with {@code match=[]} (no pre-selected value)
     * and stores it as the SELECT_KEY page attribute, simulating the tag
     * being nested inside a {@code <html:select>}.
     */
    private SelectTag stubSelectTag() {
        SelectTag select = new SelectTag();
        select.setPageContext(pageContext);
        select.match = new String[0];
        pageContext.setAttribute(Constants.SELECT_KEY, select,
                PageContext.PAGE_SCOPE);
        return select;
    }

    /**
     * Runs OptionTag with the given value and optional body text,
     * returns captured output.
     *
     * <p>When {@code text} is non-null, an {@link InjectableOptionTag} is
     * used so that body text can be injected without a real JSP container.
     * When {@code text} is null, a plain {@link OptionTag} is used to
     * exercise the value-as-label fallback path.</p>
     */
    private String runOptionTag(String value, String text) throws Exception {
        stubSelectTag();
        out = new MockJspWriter();
        pageContext.setJspWriter(out);

        if (text != null) {
            InjectableOptionTag tag = new InjectableOptionTag();
            tag.setPageContext(pageContext);
            tag.setValue(value);
            tag.setBodyText(text);
            tag.doStartTag();
            tag.doEndTag();
        } else {
            OptionTag tag = new OptionTag();
            tag.setPageContext(pageContext);
            tag.setValue(value);
            tag.doStartTag();
            tag.doEndTag();
        }

        return out.getContent();
    }

    // ------------------------------------------------------------------
    // Normal rendering
    // ------------------------------------------------------------------

    public void testOption_PlainValueAndText_RenderedCorrectly() throws Exception {
        String output = runOptionTag("foo", "Foo Label");
        assertTrue("value attribute must appear", output.contains("value=\"foo\""));
        assertTrue("label text must appear", output.contains("Foo Label"));
        assertTrue("must be wrapped in <option>", output.contains("<option"));
        assertTrue("must close with </option>", output.contains("</option>"));
    }

    public void testOption_NoText_UsesValueAsLabel() throws Exception {
        String output = runOptionTag("myval", null);
        assertTrue("When no text is set, value is used as label",
                output.contains(">myval<"));
    }

    // ------------------------------------------------------------------
    // value attribute — must be HTML-escaped (H-3)
    // ------------------------------------------------------------------

    /**
     * A double-quote in the value attribute must be escaped as {@code &quot;}
     * to prevent breaking out of the attribute context (H-3).
     */
    public void testValueAttribute_EscapesDoubleQuote() throws Exception {
        String output = runOptionTag("a\"b", "Label");
        assertFalse("Raw double-quote in value attribute enables attribute injection (H-3)",
            output.contains("value=\"a\"b\""));
        assertTrue("value must contain &quot; instead of raw \"",
            output.contains("&quot;"));
    }

    /**
     * A less-than sign in the value attribute must be escaped as {@code &lt;}
     * to prevent HTML injection (H-3).
     */
    public void testValueAttribute_EscapesLessThan() throws Exception {
        String output = runOptionTag("<script>", "Label");
        assertFalse("Raw <script> in value attribute must be escaped (H-3)",
            output.contains("value=\"<script>\""));
        assertTrue("&lt;script&gt; must appear", output.contains("&lt;script&gt;"));
    }

    // ------------------------------------------------------------------
    // text content — must be HTML-escaped (H-3)
    // ------------------------------------------------------------------

    /**
     * A script tag in the option text content must be HTML-escaped (H-3).
     */
    public void testText_EscapesScriptTag() throws Exception {
        String output = runOptionTag("v", "<script>alert(1)</script>");
        assertFalse("Raw <script> in option text must be escaped (H-3)",
            output.contains("<script>alert(1)</script>"));
        assertTrue("&lt;script&gt; must appear in option text",
            output.contains("&lt;script&gt;"));
    }

    /**
     * A single-quote in option text must be escaped as {@code &#39;} (H-3).
     */
    public void testText_EscapesSingleQuote() throws Exception {
        String output = runOptionTag("v", "it's");
        assertFalse("Raw single-quote in option text must be escaped (H-3)",
            output.contains(">it's<"));
        assertTrue("&#39; must appear", output.contains("&#39;"));
    }

    // ------------------------------------------------------------------
    // filter=false — backward compatibility
    // ------------------------------------------------------------------

    /**
     * When filter is explicitly set to false, values and text must NOT be
     * escaped, preserving backward compatibility for users who need raw HTML.
     */
    public void testOption_FilterFalse_NoEscaping() throws Exception {
        stubSelectTag();
        out = new MockJspWriter();
        pageContext.setJspWriter(out);

        InjectableOptionTag tag = new InjectableOptionTag();
        tag.setPageContext(pageContext);
        tag.setValue("a\"b");
        tag.setBodyText("<b>bold</b>");
        tag.setFilter(false);
        tag.doStartTag();
        tag.doEndTag();

        String output = out.getContent();
        assertTrue("With filter=false, raw double-quote must appear",
                output.contains("value=\"a\"b\""));
        assertTrue("With filter=false, raw HTML must appear in text",
                output.contains("<b>bold</b>"));
    }
}
