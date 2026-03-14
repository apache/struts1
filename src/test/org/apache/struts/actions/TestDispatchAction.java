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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;

/**
 * Unit tests for {@link DispatchAction}.
 *
 * <p>Tests cover: normal method dispatch, unspecified (empty parameter),
 * missing mapping parameter, recursive guard ("execute"/"perform"),
 * unknown method name, and cancelled-request handling.</p>
 */
public class TestDispatchAction extends TestCase {

    // ------------------------------------------------------------------
    // Minimal concrete subclass used by all tests
    // ------------------------------------------------------------------

    /** Concrete subclass exposing three handler methods. */
    public static class SampleDispatchAction extends DispatchAction {

        public ActionForward save(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
            return new ActionForward("/save-result.jsp");
        }

        public ActionForward delete(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
            return new ActionForward("/delete-result.jsp");
        }

        /** Returns null to simulate a handler that has already committed. */
        public ActionForward nullReturn(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
            return null;
        }
    }

    // ------------------------------------------------------------------
    // Fixtures
    // ------------------------------------------------------------------

    private SampleDispatchAction action;
    private ActionMapping mapping;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    protected void setUp() {
        action = new SampleDispatchAction();
        mapping = new ActionMapping();
        mapping.setParameter("method"); // standard dispatch parameter name

        MockHttpSession session = new MockHttpSession();
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
    }

    // ------------------------------------------------------------------
    // Normal dispatch
    // ------------------------------------------------------------------

    public void testDispatch_ToSave() throws Exception {
        request.addParameter("method", "save");

        ActionForward forward = action.execute(mapping, null, request, response);

        assertNotNull(forward);
        assertEquals("/save-result.jsp", forward.getPath());
    }

    public void testDispatch_ToDelete() throws Exception {
        request.addParameter("method", "delete");

        ActionForward forward = action.execute(mapping, null, request, response);

        assertNotNull(forward);
        assertEquals("/delete-result.jsp", forward.getPath());
    }

    public void testDispatch_HandlerReturningNull() throws Exception {
        request.addParameter("method", "nullReturn");

        ActionForward forward = action.execute(mapping, null, request, response);

        assertNull("Handler returning null should propagate null", forward);
    }

    // ------------------------------------------------------------------
    // Unspecified (empty / absent parameter value)
    // ------------------------------------------------------------------

    public void testDispatch_EmptyMethodParam_ThrowsException() {
        // An empty string for the method parameter results in getMethodName()
        // returning "", which is a non-null value, so dispatchMethod() is
        // called.  getMethod("") finds no method with that name, so a
        // NoSuchMethodException is thrown (wrapped with a user message).
        request.addParameter("method", "");

        try {
            action.execute(mapping, null, request, response);
            fail("Should throw an exception when method parameter is empty");
        } catch (NoSuchMethodException e) {
            // expected — empty string does not match any method name
        } catch (Exception e) {
            fail("Expected NoSuchMethodException but got: " + e.getClass().getName());
        }
    }

    public void testDispatch_AbsentMethodParam_ThrowsServletException() {
        // no "method" parameter added to request

        try {
            action.execute(mapping, null, request, response);
            fail("Should throw ServletException when method parameter is absent");
        } catch (ServletException e) {
            // expected
        } catch (Exception e) {
            fail("Expected ServletException but got: " + e.getClass().getName());
        }
    }

    // ------------------------------------------------------------------
    // Missing mapping parameter attribute
    // ------------------------------------------------------------------

    public void testDispatch_NullMappingParameter_ThrowsServletException() {
        ActionMapping mappingWithoutParam = new ActionMapping();
        // parameter is null by default
        request.addParameter("method", "save");

        try {
            action.execute(mappingWithoutParam, null, request, response);
            fail("Should throw ServletException when mapping has no parameter");
        } catch (ServletException e) {
            // expected
        } catch (Exception e) {
            fail("Expected ServletException but got: " + e.getClass().getName());
        }
    }

    // ------------------------------------------------------------------
    // Recursive guard
    // ------------------------------------------------------------------

    public void testDispatch_MethodNameExecute_ThrowsServletException() {
        request.addParameter("method", "execute");

        try {
            action.execute(mapping, null, request, response);
            fail("Should throw ServletException to prevent recursive dispatch to 'execute'");
        } catch (ServletException e) {
            // expected — DispatchAction guards against this
        } catch (Exception e) {
            fail("Expected ServletException but got: " + e.getClass().getName());
        }
    }

    public void testDispatch_MethodNamePerform_ThrowsServletException() {
        request.addParameter("method", "perform");

        try {
            action.execute(mapping, null, request, response);
            fail("Should throw ServletException to prevent recursive dispatch to 'perform'");
        } catch (ServletException e) {
            // expected
        } catch (Exception e) {
            fail("Expected ServletException but got: " + e.getClass().getName());
        }
    }

    // ------------------------------------------------------------------
    // Unknown method
    // ------------------------------------------------------------------

    public void testDispatch_UnknownMethod_ThrowsNoSuchMethodException() {
        request.addParameter("method", "nonExistentMethod");

        try {
            action.execute(mapping, null, request, response);
            fail("Should throw NoSuchMethodException for an unknown method name");
        } catch (NoSuchMethodException e) {
            // expected
        } catch (Exception e) {
            fail("Expected NoSuchMethodException but got: " + e.getClass().getName());
        }
    }

    // ------------------------------------------------------------------
    // Cancelled request
    // ------------------------------------------------------------------

    /**
     * When Globals.CANCEL_KEY is set, DispatchAction calls cancelled() which
     * by default returns null.  execute() then falls through to the normal
     * dispatch path, so the regular method is still called.
     *
     * <p>This matches the actual implementation: the {@code if (af != null)}
     * guard means a null return from cancelled() does NOT short-circuit the
     * action — dispatch continues normally.</p>
     */
    public void testDispatch_CancelledRequest_FallsThroughToNormalDispatch()
            throws Exception {
        request.setAttribute(org.apache.struts.Globals.CANCEL_KEY, Boolean.TRUE);
        request.addParameter("method", "save");

        ActionForward forward = action.execute(mapping, null, request, response);

        assertNotNull("Normal dispatch continues when cancelled() returns null", forward);
        assertEquals("/save-result.jsp", forward.getPath());
    }

    // ------------------------------------------------------------------
    // Method injection guard
    //
    // DispatchAction uses reflection to invoke the method named by the
    // request parameter. An attacker who controls that parameter value
    // could attempt to invoke inherited Object methods (getClass, wait,
    // notify, hashCode, etc.) or the execute/perform guards.
    //
    // The recursive guard blocks "execute" and "perform" explicitly.
    // For all other non-action methods (wrong signature or Object methods),
    // getMethod() will throw NoSuchMethodException because it searches for
    // a method with the exact Action-handler signature — inherited Object
    // methods have a different signature, so they cannot be reached.
    //
    // These tests document and verify that protection.
    // ------------------------------------------------------------------

    /**
     * Attempting to dispatch to "getClass" (an inherited Object method with
     * a different signature) must NOT succeed — a NoSuchMethodException is
     * expected because no public method named "getClass" with the
     * Action-handler signature exists.
     */
    public void testDispatch_GetClass_ThrowsNoSuchMethodException() {
        request.addParameter("method", "getClass");

        try {
            action.execute(mapping, null, request, response);
            fail("Dispatching to 'getClass' must not succeed");
        } catch (NoSuchMethodException e) {
            // expected — Object.getClass() has a different signature
        } catch (Exception e) {
            fail("Expected NoSuchMethodException but got: " + e.getClass().getName());
        }
    }

    /**
     * Attempting to dispatch to "wait" must NOT succeed.
     */
    public void testDispatch_Wait_ThrowsNoSuchMethodException() {
        request.addParameter("method", "wait");

        try {
            action.execute(mapping, null, request, response);
            fail("Dispatching to 'wait' must not succeed");
        } catch (NoSuchMethodException e) {
            // expected
        } catch (Exception e) {
            fail("Expected NoSuchMethodException but got: " + e.getClass().getName());
        }
    }

    /**
     * Attempting to dispatch to "notify" must NOT succeed.
     */
    public void testDispatch_Notify_ThrowsNoSuchMethodException() {
        request.addParameter("method", "notify");

        try {
            action.execute(mapping, null, request, response);
            fail("Dispatching to 'notify' must not succeed");
        } catch (NoSuchMethodException e) {
            // expected
        } catch (Exception e) {
            fail("Expected NoSuchMethodException but got: " + e.getClass().getName());
        }
    }

    /**
     * A method named "dispatchMethod" exists in DispatchAction itself but has
     * a different signature from the standard Action-handler signature, so it
     * must NOT be reachable via dispatch.
     */
    public void testDispatch_DispatchMethod_ThrowsNoSuchMethodException() {
        request.addParameter("method", "dispatchMethod");

        try {
            action.execute(mapping, null, request, response);
            fail("Dispatching to internal 'dispatchMethod' must not succeed");
        } catch (NoSuchMethodException e) {
            // expected — internal method has different signature
        } catch (Exception e) {
            fail("Expected NoSuchMethodException but got: " + e.getClass().getName());
        }
    }
}
