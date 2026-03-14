/*
 * $Id$ 
 *
 * Copyright 2006 The Apache Software Foundation.
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

import javax.servlet.ServletException;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.Globals;
import org.apache.struts.mock.TestMockBase;                                                                                                                       
import org.apache.struts.mock.MockFormBean;                                                                                                                       
import org.apache.struts.mock.MockMultipartRequestHandler;                                                                                                                       

/**
 * Unit tests for the RequestUtil's <code>populate</code> method.
 *
 * @version $Rev$
 */
public class TestRequestUtilsPopulate extends TestMockBase {
    
    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestRequestUtilsPopulate(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(
            new String[] { TestRequestUtilsPopulate.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestRequestUtilsPopulate.class);
    }

    public void setUp() {
        super.setUp();
    }

    public void tearDown() {
        super.tearDown();
    }

    /** 
     * Ensure that the getMultipartRequestHandler cannot be seen in 
     * a subclass of ActionForm.
     *
     * The purpose of this test is to ensure that Bug #38534 is fixed.
     *
     */
    public void testMultipartVisibility() throws Exception {

        String mockMappingName = "mockMapping";
        String stringValue     = "Test";

        MockFormBean  mockForm = new MockFormBean();
        ActionMapping mapping  = new ActionMapping();
        mapping.setName(mockMappingName);

        // Set up the mock HttpServletRequest
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.setAttribute(Globals.MULTIPART_KEY, MockMultipartRequestHandler.class.getName());
        request.setAttribute(Globals.MAPPING_KEY, mapping);

        request.addParameter("stringProperty", stringValue);
        request.addParameter("multipartRequestHandler.mapping.name", "Bad");

        // Check the Mapping/ActionForm before
        assertNull("Multipart Handler already set",    mockForm.getMultipartRequestHandler());
        assertEquals("Mapping name not set correctly", mockMappingName, mapping.getName());

        // Try to populate
        try {
            RequestUtils.populate(mockForm, request);
        } catch(ServletException se) {
            // Expected BeanUtils.populate() to throw a NestedNullException
            // which gets wrapped in RequestUtils in a ServletException
            assertEquals("Unexpected type of Exception thrown", "BeanUtils.populate", se.getMessage());
        }

        // Check the Mapping/ActionForm after
        assertNotNull("Multipart Handler Missing", mockForm.getMultipartRequestHandler());
        assertEquals("Mapping name has been modified", mockMappingName, mapping.getName());

    }
	
	/** 
     * Ensure that the parameter of HTTP request
     * which causes ClassLoader manipulation is ignored.
     *
     * The purpose of this test is to ensure that security problem
     * CVE-2014-0114 is fixed.
     *
     */
    public void testRequestParameterIgnore1() throws Exception {

        String stringValue     = "Test";

        MockFormBean  mockForm = new MockFormBean();

       // Set up the mock HttpServletRequest
        request.setMethod("GET");
        request.setContentType("");

        request.addParameter("class.xxx.case1", stringValue);

        // Try to populate
        try {
            RequestUtils.populate(mockForm, request);
        } catch(ServletException se) {
            fail("Occur exception.");
        }

        // Check that "class." prefix parameter was ignored
        assertNull("ActionForm property set", mockForm.getStringProperty());

    }

    /** 
     * Ensure that the parameter of HTTP request
     * which causes ClassLoader manipulation is ignored.
     *
     * The purpose of this test is to ensure that security problem
     * CVE-2014-0114 is fixed.
     *
     */
    public void testRequestParameterIgnore2() throws Exception {

        String stringValue     = "Test";

        MockFormBean  mockForm = new MockFormBean();

        // Set up the mock HttpServletRequest
        request.setMethod("GET");
        request.setContentType("");

        request.addParameter("xxx.class.case2", stringValue);

        // Try to populate
        try {
            RequestUtils.populate(mockForm, request);
        } catch(ServletException se) {
            fail("Occur exception.");
        }

        // Check that ".class." containing parameter was ignored
        assertNull("ActionForm property set", mockForm.getStringProperty());

    }

    /** 
     * Ensure that the parameter of HTTP request
     * which causes ClassLoader manipulation is ignored.
     *
     * The purpose of this test is to ensure that security problem
     * CVE-2014-0114 is fixed.
     *
     */
    public void testRequestParameterIgnore3() throws Exception {

        String stringValue     = "Test";

        MockFormBean  mockForm = new MockFormBean();

        // Set up the mock HttpServletRequest
        request.setMethod("GET");
        request.setContentType("");

        request.addParameter("stringProperty", stringValue);

        // Try to populate
        try {
            RequestUtils.populate(mockForm, request);
        } catch(ServletException se) {
            fail("Occur exception.");
        }

        // Check that normal parameter was NOT ignored
        assertEquals("ActionForm property not equal", stringValue, mockForm.getStringProperty());

    }

    /** 
     * Ensure that the parameter of HTTP request
     * which causes ClassLoader manipulation is ignored.
     *
     * The purpose of this test is to ensure that security problem
     * CVE-2014-0114 is fixed.
     *
     */
    public void testRequestParameterIgnore4() throws Exception {

        String stringValue     = "Test";

        MockFormBean  mockForm = new MockFormBean();

        // Set up the mock HttpServletRequest
        request.setMethod("GET");
        request.setContentType("");

        request.addParameter("class.xxx.case4", stringValue);
        request.addParameter("xxx.class.case4", stringValue);
        request.addParameter("stringProperty", stringValue);

        // Try to populate
        try {
            RequestUtils.populate(mockForm, request);
        } catch(ServletException se) {
            fail("Occur exception.");
        }

        // Check that dangerous parameters were ignored but normal one was populated
        assertEquals("ActionForm property not equal", stringValue, mockForm.getStringProperty());

    }
   
    
}

