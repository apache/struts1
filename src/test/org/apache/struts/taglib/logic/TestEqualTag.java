/*
 * $Id$ 
 *
 * Copyright 1999-2004 The Apache Software Foundation.
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

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.struts.mock.*;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.logic.EqualTag</code> class.
 *
 */
public class TestEqualTag extends TestCase {

    protected final static String COOKIE_KEY =
        "org.apache.struts.taglib.logic.COOKIE_KEY";
    protected final static String HEADER_KEY =
        "org.apache.struts.taglib.logic.HEADER_KEY";
    protected final static String PARAMETER_KEY =
        "org.apache.struts.taglib.logic.PARAMETER_KEY";

    protected final static String testStringKey = "testString";
    protected final static String testStringValue = "abc";
    protected final static String testStringValue1 = "abcd";
    protected final static String testIntegerKey = "testInteger";
    protected final static Integer testIntegerValue = new Integer(21);
    protected final static Integer testIntegerValue1 =
        new Integer(testIntegerValue.intValue() + 1);

    protected EqualTag et = null;

    protected MockServletContext context;
    protected MockServletConfig config;
    protected MockHttpSession session;
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    protected MockPageContext pageContext;

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestEqualTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestEqualTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestEqualTag.class);
    }

    public void setUp() {
        context = new MockServletContext();
        config = new MockServletConfig(context);
        session = new MockHttpSession(context);
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
        pageContext = new MockPageContext(config, request, response);

        et = new EqualTag();
        et.setPageContext(pageContext);

    }

    public void tearDown() {
        et = null;
    }



    // --------------------------------------------------- Cookie String Equals


    /* FIXME: Cactus does not send cookies?
    public void beginCookieStringEquals(WebRequest testRequest) {

        testRequest.addCookie(COOKIE_KEY, "abc");

    }


    public void testCookieStringEquals()
        throws ServletException, JspException {

        et.setCookie(COOKIE_KEY);
        et.setValue(testStringValue);

        assertEquals("Cookie string equals comparison", true,
                     et.condition(0, 0));

    }
    */


    // ----------------------------------------------- Cookie String Not Equals


    public void testCookieStringNotEquals()
        throws ServletException, JspException {

        request.addCookie(new javax.servlet.http.Cookie(COOKIE_KEY, "abc"));

        et.setCookie(COOKIE_KEY);
        et.setValue(testStringValue1);

        assertEquals("Cookie string not equals comparison", false,
                     et.condition(0, 0));

    }


    // --------------------------------------------------- Header String Equals


    public void testHeaderStringEquals()
        throws ServletException, JspException {

        request.addHeader(HEADER_KEY, "abc");

        et.setHeader(HEADER_KEY);
        et.setValue(testStringValue);

        assertEquals("Header string equals comparison", true,
                     et.condition(0, 0));

    }


    // ----------------------------------------------- Header String Not Equals


    public void testHeaderStringNotEquals()
        throws ServletException, JspException {

        request.addHeader(HEADER_KEY, "abc");

        et.setHeader(HEADER_KEY);
        et.setValue(testStringValue1);

        assertEquals("Header string not equals comparison", false,
                     et.condition(0, 0));

    }


    // --------------------------------------------------------- Integer Equals


    public void testIntegerEquals()
        throws ServletException, JspException {

        request.setAttribute(testIntegerKey, testIntegerValue);
	et.setName(testIntegerKey);
	et.setValue(testIntegerValue.toString());
	
        assertEquals("Integer equals comparison", true,
                     et.condition(0, 0));

    }


    // ----------------------------------------------------- Integer Not Equals


    public void testIntegerNotEquals()
        throws ServletException, JspException {

        request.setAttribute(testIntegerKey, testIntegerValue);
	et.setName(testIntegerKey);
	et.setValue(testIntegerValue1.toString());
	
        assertEquals("Integer not equals comparison", false,
                     et.condition(0, 0));

    }


    // ------------------------------------------------ Parameter String Equals


    public void testParameterStringEquals()
        throws ServletException, JspException {

        request.addParameter(PARAMETER_KEY, "abc");

        et.setParameter(PARAMETER_KEY);
        et.setValue(testStringValue);

        assertEquals("Parameter string equals comparison", true,
                     et.condition(0, 0));

    }


    // -------------------------------------------- Parameter String Not Equals


    public void testParameterStringNotEquals()
        throws ServletException, JspException {

        request.addParameter(PARAMETER_KEY, "abc");

        et.setParameter(PARAMETER_KEY);
        et.setValue(testStringValue1);

        assertEquals("Parameter string not equals comparison", false,
                     et.condition(0, 0));

    }


    // ---------------------------------------------------------- String Equals


    public void testStringEquals()
        throws ServletException, JspException {

        request.setAttribute(testStringKey, testStringValue);
	et.setName(testStringKey);
	et.setValue(testStringValue);
	
        assertEquals("String equals comparison", true,
                     et.condition(0, 0));

    }


    // ------------------------------------------------------ String Not Equals


    public void testStringNotEquals()
        throws ServletException, JspException {

        request.setAttribute(testStringKey, testStringValue);
	et.setName(testStringKey);
	et.setValue(testStringValue1);
	
        assertEquals("String not equals comparison", false,
                     et.condition(0, 0));

    }



}
