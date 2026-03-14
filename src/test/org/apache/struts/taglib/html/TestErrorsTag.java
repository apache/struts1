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

import javax.servlet.jsp.tagext.Tag;

import junit.framework.TestCase;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;
import org.apache.struts.mock.MockJspWriter;
import org.apache.struts.mock.MockPageContext;
import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;
import org.apache.struts.util.MessageResources;

/**
 * XSS tests for {@link ErrorsTag} — H-2 / CVE-2012-1007.
 *
 * <h3>Vulnerability: ErrorsTag renders message content without HTML escaping</h3>
 *
 * <p>{@code <html:errors>} iterates over {@link ActionMessages} and renders
 * each message. When an {@link ActionMessage} is created with
 * {@code resource=false} (i.e. the key is a literal string, not a resource
 * bundle key), {@code ErrorsTag.doStartTag()} appends {@code report.getKey()}
 * directly to the output buffer ({@code ErrorsTag.java} line 261) with NO call
 * to {@link org.apache.struts.util.ResponseUtils#filter(String)}.
 * If the key contains user-supplied data, this is a direct XSS vector.</p>
 *
 * <p>Even for resource-based messages, the message <em>arguments</em>
 * ({@code report.getValues()}) are passed to
 * {@link org.apache.struts.taglib.TagUtils#message} which uses
 * {@link java.text.MessageFormat} to interpolate them into the pattern —
 * but that method also does not HTML-escape the arguments, so user-controlled
 * arguments in a message pattern can also introduce XSS (CVE-2012-1007).</p>
 *
 * <p>Tests that assert escaping are expected to <strong>FAIL</strong>
 * until {@code ErrorsTag} is fixed to escape output.</p>
 */
public class TestErrorsTag extends TestCase {

    private MockServletContext context;
    private MockServletConfig config;
    private MockHttpSession session;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockPageContext pageContext;
    private MockJspWriter out;

    protected void setUp() {
        context = new MockServletContext();
        // ErrorsTag calls TagUtils.present() which needs:
        //   1. ModuleConfig in context under MODULE_KEY + "" (for getModuleConfig())
        //   2. MessageResources in context under MESSAGES_KEY (for retrieveMessageResources())
        // We use a non-existent bundle so all header/prefix/suffix/footer key
        // lookups return null → isPresent() returns false → they are skipped.
        context.setAttribute(Globals.MODULE_KEY + "", new ModuleConfigImpl(""));
        MessageResources emptyResources = MessageResources.getMessageResources(
                "org.apache.struts.taglib.html.EmptyBundle");
        context.setAttribute(Globals.MESSAGES_KEY, emptyResources);
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
     * Stores the given ActionMessages in REQUEST_SCOPE under Globals.ERROR_KEY,
     * runs ErrorsTag.doStartTag(), and returns the captured output.
     * The tag is configured with no header/prefix/suffix/footer (no bundle
     * keys match, so those strings are skipped).
     */
    private String runErrorsTag(ActionMessages messages) throws Exception {
        request.setAttribute(Globals.ERROR_KEY, messages);
        out = new MockJspWriter();
        pageContext.setJspWriter(out);

        ErrorsTag tag = new ErrorsTag();
        tag.setPageContext(pageContext);

        tag.doStartTag();
        tag.doEndTag();

        return out.getContent();
    }

    // ------------------------------------------------------------------
    // No errors — tag is silent
    // ------------------------------------------------------------------

    public void testNoMessages_ProducesNoOutput() throws Exception {
        String output = runErrorsTag(new ActionMessages());
        assertEquals("Empty ActionMessages must produce no output", "", output);
    }

    public void testNullMessagesAttribute_ProducesNoOutput() throws Exception {
        out = new MockJspWriter();
        pageContext.setJspWriter(out);

        ErrorsTag tag = new ErrorsTag();
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        assertEquals("Null errors attribute must produce no output", "", out.getContent());
    }

    // ------------------------------------------------------------------
    // Non-resource message (resource=false) — key rendered literally (H-2)
    // ------------------------------------------------------------------

    /**
     * A non-resource ActionMessage key containing an XSS payload must be
     * HTML-escaped in the output (H-2 / CVE-2012-1007).
     */
    public void testNonResourceMessage_EscapesScriptTag() throws Exception {
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("<script>alert('XSS')</script>", false));

        String output = runErrorsTag(messages);

        assertFalse("Raw <script> tag must not appear in ErrorsTag output (H-2)",
            output.contains("<script>"));
        assertTrue("Output must contain &lt;script&gt; instead",
            output.contains("&lt;script&gt;"));
    }

    /**
     * A non-resource message containing a double-quote must be HTML-escaped
     * to prevent attribute injection (H-2).
     */
    public void testNonResourceMessage_EscapesDoubleQuote() throws Exception {
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("\" onmouseover=\"alert(1)", false));

        String output = runErrorsTag(messages);

        assertFalse("Raw double-quote must not appear in ErrorsTag output (H-2)",
            output.contains("\" onmouseover="));
        assertTrue("&quot; must appear in output", output.contains("&quot;"));
    }

    /**
     * A non-resource message containing only plain text must pass through
     * unchanged — escaping must not corrupt safe content.
     */
    public void testNonResourceMessage_PlainText_PassesThrough() throws Exception {
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("Field is required.", false));

        String output = runErrorsTag(messages);

        assertTrue("Plain text must appear in output unchanged",
                output.contains("Field is required."));
    }

    // ------------------------------------------------------------------
    // Return value
    // ------------------------------------------------------------------

    public void testWithMessages_ReturnsEvalBodyInclude() throws Exception {
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("some.error.key", false));
        request.setAttribute(Globals.ERROR_KEY, messages);

        ErrorsTag tag = new ErrorsTag();
        tag.setPageContext(pageContext);

        int result = tag.doStartTag();
        assertEquals(Tag.EVAL_BODY_INCLUDE, result);
    }
}
