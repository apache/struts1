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
package org.apache.struts.taglib.logic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import junit.framework.TestCase;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForward;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;
import org.apache.struts.mock.MockPageContext;
import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;

/**
 * Unit tests for {@link ForwardTag}.
 *
 * <p>ForwardTag looks up a named forward from the current module's
 * {@code ModuleConfig} and either forwards or redirects to its path.
 * The full forward/redirect path requires a real container; these tests
 * verify the lookup and error-handling logic that runs before the
 * container dispatch.</p>
 */
public class TestForwardTag extends TestCase {

    protected MockServletContext context;
    protected MockServletConfig config;
    protected MockHttpSession session;
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    protected MockPageContext pageContext;
    protected ModuleConfigImpl moduleConfig;

    public void setUp() {
        context = new MockServletContext();
        config = new MockServletConfig(context);
        session = new MockHttpSession(context);
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
        pageContext = new MockPageContext(config, request, response);

        // Register a module config with a couple of named forwards
        moduleConfig = new ModuleConfigImpl("");
        moduleConfig.addForwardConfig(
                new ActionForward("success", "/home.jsp", false));
        moduleConfig.addForwardConfig(
                new ActionForward("logout", "/login.jsp", true));
        request.setAttribute(Globals.MODULE_KEY, moduleConfig);
    }

    // ------------------------------------------------------------------
    // doStartTag always returns SKIP_BODY
    // ------------------------------------------------------------------

    public void testDoStartTag_ReturnsSkipBody() throws JspException {
        ForwardTag tag = new ForwardTag();
        tag.setPageContext(pageContext);
        tag.setName("success");

        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
    }

    // ------------------------------------------------------------------
    // doEndTag — forward not found → JspException
    // ------------------------------------------------------------------

    public void testDoEndTag_UnknownForwardName_ThrowsJspException() {
        ForwardTag tag = new ForwardTag();
        tag.setPageContext(pageContext);
        tag.setName("nonExistentForward");

        try {
            tag.doEndTag();
            fail("Should throw JspException when forward name is not found");
        } catch (JspException e) {
            // expected — no such forward configured
            assertTrue(e.getMessage() != null);
        }
    }

    public void testDoEndTag_NullModuleConfig_ThrowsJspException() {
        // Remove module config from request
        request.removeAttribute(Globals.MODULE_KEY);

        ForwardTag tag = new ForwardTag();
        tag.setPageContext(pageContext);
        tag.setName("success");

        try {
            tag.doEndTag();
            fail("Should throw JspException when module config is absent");
        } catch (JspException e) {
            // expected
        }
    }

    // ------------------------------------------------------------------
    // doEndTag — forward path — MockPageContext.forward() throws
    // UnsupportedOperationException; verify it is wrapped in JspException
    // ------------------------------------------------------------------

    public void testDoEndTag_ForwardPath_WrapsContainerException() {
        // "success" is a forward (not a redirect), so ForwardTag calls
        // pageContext.forward(), which MockPageContext throws UnsupportedOperationException.
        // ForwardTag catches Exception and re-throws as JspException.
        ForwardTag tag = new ForwardTag();
        tag.setPageContext(pageContext);
        tag.setName("success");

        try {
            tag.doEndTag();
            fail("MockPageContext.forward() throws UnsupportedOperationException; "
                    + "ForwardTag should wrap it in JspException");
        } catch (JspException e) {
            // expected — container dispatch is not available in unit tests
        }
    }

    // ------------------------------------------------------------------
    // release
    // ------------------------------------------------------------------

    public void testRelease_ClearsName() throws JspException {
        ForwardTag tag = new ForwardTag();
        tag.setPageContext(pageContext);
        tag.setName("success");
        tag.release();

        assertNull("name must be null after release()", tag.getName());
    }
}
