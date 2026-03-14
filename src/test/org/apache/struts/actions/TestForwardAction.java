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
package org.apache.struts.actions;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;

/**
 * Unit tests for {@link ForwardAction}.
 *
 * <p>ForwardAction reads its destination from the {@code parameter} attribute
 * of the ActionMapping and returns an {@link ActionForward} with
 * {@code contextRelative=true}.  It is a simple delegation action — no request
 * parameters are involved.</p>
 */
public class TestForwardAction extends TestCase {

    private ForwardAction action;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    protected void setUp() {
        action = new ForwardAction();
        MockHttpSession session = new MockHttpSession();
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
    }

    // ------------------------------------------------------------------
    // Normal operation
    // ------------------------------------------------------------------


    public void testForward_ReturnsContextRelativeForward() throws Exception {
        ActionMapping mapping = new ActionMapping();
        mapping.setParameter("/target/page.jsp");

        ActionForward forward = action.execute(mapping, null, request, response);

        assertNotNull("ActionForward must not be null", forward);
        assertEquals("Path must equal the mapping parameter",
                "/target/page.jsp", forward.getPath());
        // getContextRelative() is deprecated in favour of getModule(), but
        // ForwardAction explicitly calls setContextRelative(true), so we
        // verify that flag here.
        assertTrue("Forward must be context-relative", forward.getContextRelative());
    }


    public void testForward_RootPath() throws Exception {
        ActionMapping mapping = new ActionMapping();
        mapping.setParameter("/index.jsp");

        ActionForward forward = action.execute(mapping, null, request, response);

        assertNotNull(forward);
        assertEquals("/index.jsp", forward.getPath());
        assertTrue(forward.getContextRelative());
    }

    public void testForward_PathWithQueryString() throws Exception {
        ActionMapping mapping = new ActionMapping();
        mapping.setParameter("/search.do?q=test");

        ActionForward forward = action.execute(mapping, null, request, response);

        assertNotNull(forward);
        assertEquals("/search.do?q=test", forward.getPath());
    }

    // ------------------------------------------------------------------
    // Missing parameter
    // ------------------------------------------------------------------

    public void testForward_NullParameter_ThrowsServletException() {
        ActionMapping mapping = new ActionMapping();
        // parameter is null by default

        try {
            action.execute(mapping, null, request, response);
            fail("Should throw ServletException when mapping parameter is null");
        } catch (ServletException e) {
            // expected
        } catch (Exception e) {
            fail("Expected ServletException but got: " + e.getClass().getName());
        }
    }

    // ------------------------------------------------------------------
    // Security: ForwardAction uses a static mapping parameter, not a
    // request parameter, so open-redirect via user input is not directly
    // possible through ForwardAction itself. The tests below document the
    // current behaviour for paths that would be dangerous if they ever
    // came from user-controlled input, to guard against future refactoring
    // that might introduce such a path.
    //
    // If ForwardAction is ever changed to accept a request parameter as
    // the forward destination, these tests MUST be updated to assert that
    // dangerous values are rejected with a ServletException.
    // ------------------------------------------------------------------

    /**
     * Documents that ForwardAction currently returns whatever path is set
     * in the mapping without validation. A path starting with "//" would be
     * interpreted by some containers as a protocol-relative URL (open redirect).
     *
     * <p>This test is a <strong>regression canary</strong>: it passes today
     * because the path comes from static configuration, not user input. If the
     * source of the path ever changes to a request parameter, a fix is required
     * and this test must be rewritten to assert rejection.</p>
     */

    public void testForward_DoubleSlashPath_DocumentsNoValidation() throws Exception {
        ActionMapping mapping = new ActionMapping();
        mapping.setParameter("//evil.example.com/phishing");

        ActionForward forward = action.execute(mapping, null, request, response);

        assertNotNull(forward);
        // ForwardAction currently echoes the path with no validation.
        // If validation is ever added, this should assert a ServletException.
        assertEquals("//evil.example.com/phishing", forward.getPath());
    }

    /**
     * Documents that ForwardAction currently returns a WEB-INF path without
     * validation. A static mapping pointing at WEB-INF is a configuration
     * error, not an injection; the server is responsible for blocking it.
     * This test documents current behaviour as a regression baseline.
     */

    public void testForward_WebInfPath_DocumentsNoValidation() throws Exception {
        ActionMapping mapping = new ActionMapping();
        mapping.setParameter("/WEB-INF/web.xml");

        ActionForward forward = action.execute(mapping, null, request, response);

        assertNotNull(forward);
        assertEquals("/WEB-INF/web.xml", forward.getPath());
        assertTrue(forward.getContextRelative());
    }
}
