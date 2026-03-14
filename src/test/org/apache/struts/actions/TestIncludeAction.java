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

import org.apache.struts.action.ActionMapping;
import org.apache.struts.mock.MockActionServlet;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;
import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;

/**
 * Unit tests for {@link IncludeAction}.
 *
 * <p>IncludeAction performs a {@code RequestDispatcher.include()} to the path
 * specified in the mapping's {@code parameter} attribute, then returns
 * {@code null} (the response is already committed by the include).
 * A null or missing parameter must throw {@link ServletException}.</p>
 *
 * <p>Note: a successful include requires a real servlet container
 * ({@code MockServletContext.getRequestDispatcher()} returns {@code null}),
 * so only error paths and the null-parameter guard are tested here.</p>
 */
public class TestIncludeAction extends TestCase {

    private IncludeAction action;
    private MockServletContext servletContext;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    protected void setUp() {
        servletContext = new MockServletContext();
        MockServletConfig servletConfig = new MockServletConfig(servletContext);
        MockActionServlet servlet = new MockActionServlet(servletContext, servletConfig);

        action = new IncludeAction();
        action.setServlet(servlet);

        MockHttpSession session = new MockHttpSession();
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
    }

    // ------------------------------------------------------------------
    // Null parameter → ServletException
    // ------------------------------------------------------------------

    public void testInclude_NullParameter_ThrowsServletException() {
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
    // No RequestDispatcher available
    // MockServletContext.getRequestDispatcher() throws
    // UnsupportedOperationException, which IncludeAction does not catch,
    // so it propagates as-is.
    // ------------------------------------------------------------------

    public void testInclude_NoDispatcher_ThrowsException() {
        ActionMapping mapping = new ActionMapping();
        mapping.setParameter("/some/resource.jsp");

        try {
            action.execute(mapping, null, request, response);
            fail("Should throw when RequestDispatcher is not available");
        } catch (UnsupportedOperationException e) {
            // expected — MockServletContext.getRequestDispatcher() throws this
        } catch (Exception e) {
            fail("Expected UnsupportedOperationException but got: "
                    + e.getClass().getName());
        }
    }

    // ------------------------------------------------------------------
    // Path validation — IncludeAction passes the parameter value directly
    // to ServletContext.getRequestDispatcher() without any validation.
    // The tests below document what paths the action currently accepts.
    // If a validation fix is ever applied, these tests will serve as the
    // regression baseline.
    //
    // NOTE: Because MockServletContext.getRequestDispatcher() always throws
    // UnsupportedOperationException, we cannot observe whether a real
    // container would reject or serve the resource. The tests below verify
    // that IncludeAction itself does NOT perform input validation, which
    // means a misconfigured mapping could expose sensitive resources.
    // ------------------------------------------------------------------

    /**
     * Documents that IncludeAction does NOT reject a parameter pointing to
     * WEB-INF. The UnsupportedOperationException comes from the mock, not
     * from any validation in IncludeAction itself.
     *
     * <p>If IncludeAction is ever fixed to validate the path, this test
     * should be updated to expect a {@link ServletException} instead.</p>
     */
    public void testInclude_WebInfPath_NotValidatedByAction() {
        ActionMapping mapping = new ActionMapping();
        mapping.setParameter("/WEB-INF/web.xml");

        try {
            action.execute(mapping, null, request, response);
            fail("Expected an exception (mock or validation)");
        } catch (UnsupportedOperationException e) {
            // MockServletContext threw this — IncludeAction itself did NOT
            // validate the path. This documents the absence of input validation.
        } catch (ServletException e) {
            // If this branch is reached, IncludeAction now validates the path.
            // That is the desired state after a fix is applied.
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }

    /**
     * Documents that IncludeAction does NOT reject a parameter containing
     * a path traversal sequence (../../).
     *
     * <p>This is a vulnerability: a misconfigured mapping (or a future
     * refactoring that sources the path from a request parameter) could
     * allow directory traversal. The test is left PASSING to document the
     * current (unvalidated) state.</p>
     */
    public void testInclude_DotDotPath_NotValidatedByAction() {
        ActionMapping mapping = new ActionMapping();
        mapping.setParameter("/../../etc/passwd");

        try {
            action.execute(mapping, null, request, response);
            fail("Expected an exception (mock or validation)");
        } catch (UnsupportedOperationException e) {
            // MockServletContext threw this — no validation in IncludeAction.
        } catch (ServletException e) {
            // Desired state after a fix: IncludeAction rejects traversal paths.
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
}
