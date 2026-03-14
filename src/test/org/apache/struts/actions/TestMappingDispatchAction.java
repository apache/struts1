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
 * Unit tests for {@link MappingDispatchAction}.
 *
 * <p>Unlike {@link DispatchAction}, {@code MappingDispatchAction} uses the
 * {@code parameter} attribute of the ActionMapping itself as the method name
 * (not a request parameter). Each URL mapping hardcodes which method to call.</p>
 */
public class TestMappingDispatchAction extends TestCase {

    // ------------------------------------------------------------------
    // Concrete subclass
    // ------------------------------------------------------------------

    public static class SampleMappingAction extends MappingDispatchAction {

        public ActionForward list(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
            return new ActionForward("/list.jsp");
        }

        public ActionForward create(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
            return new ActionForward("/create.jsp");
        }

        public ActionForward edit(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
            return new ActionForward("/edit.jsp");
        }
    }

    // ------------------------------------------------------------------
    // Fixtures
    // ------------------------------------------------------------------

    private SampleMappingAction action;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    protected void setUp() {
        action = new SampleMappingAction();
        MockHttpSession session = new MockHttpSession();
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
    }

    private ActionMapping mappingFor(String methodName) {
        ActionMapping m = new ActionMapping();
        m.setParameter(methodName);
        return m;
    }

    // ------------------------------------------------------------------
    // Normal dispatch — method name comes from mapping, not request
    // ------------------------------------------------------------------

    public void testDispatch_ToList() throws Exception {
        ActionForward forward = action.execute(mappingFor("list"), null, request, response);

        assertNotNull(forward);
        assertEquals("/list.jsp", forward.getPath());
    }

    public void testDispatch_ToCreate() throws Exception {
        ActionForward forward = action.execute(mappingFor("create"), null, request, response);

        assertNotNull(forward);
        assertEquals("/create.jsp", forward.getPath());
    }

    public void testDispatch_ToEdit() throws Exception {
        ActionForward forward = action.execute(mappingFor("edit"), null, request, response);

        assertNotNull(forward);
        assertEquals("/edit.jsp", forward.getPath());
    }

    /**
     * Request parameters are irrelevant — MappingDispatchAction ignores them.
     * The mapping parameter alone determines the method.
     */
    public void testDispatch_RequestParamIgnored() throws Exception {
        // Even if a "method" request param points elsewhere, mapping wins
        request.addParameter("method", "create");

        ActionForward forward = action.execute(mappingFor("list"), null, request, response);

        assertNotNull(forward);
        assertEquals("Mapping parameter should win over any request parameter",
                "/list.jsp", forward.getPath());
    }

    // ------------------------------------------------------------------
    // Missing / null mapping parameter
    // ------------------------------------------------------------------

    public void testDispatch_NullParameter_ThrowsServletException() {
        ActionMapping mappingWithoutParam = new ActionMapping();
        // parameter is null

        try {
            action.execute(mappingWithoutParam, null, request, response);
            fail("Should throw ServletException when mapping parameter is null");
        } catch (ServletException e) {
            // expected — MappingDispatchAction.unspecified() throws
        } catch (Exception e) {
            fail("Expected ServletException but got: " + e.getClass().getName());
        }
    }

    // ------------------------------------------------------------------
    // Unknown method name
    // ------------------------------------------------------------------

    public void testDispatch_UnknownMethod_ThrowsNoSuchMethodException() {
        try {
            action.execute(mappingFor("nonExistent"), null, request, response);
            fail("Should throw NoSuchMethodException for unknown method");
        } catch (NoSuchMethodException e) {
            // expected
        } catch (Exception e) {
            fail("Expected NoSuchMethodException but got: " + e.getClass().getName());
        }
    }

    // ------------------------------------------------------------------
    // Recursive guard
    // ------------------------------------------------------------------

    public void testDispatch_MethodNameExecute_ThrowsServletException() {
        try {
            action.execute(mappingFor("execute"), null, request, response);
            fail("Should throw ServletException to prevent recursive dispatch");
        } catch (ServletException e) {
            // expected
        } catch (Exception e) {
            fail("Expected ServletException but got: " + e.getClass().getName());
        }
    }
}
