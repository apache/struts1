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

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.logic.EqualTag</code>
 * class.
 */
public class TestEqualTag extends JspTestCase {

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
        junit.awtui.TestRunner
                .main(new String[] { TestEqualTag.class.getName() });
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


    public void beginCookieStringNotEquals(WebRequest testRequest) {

        testRequest.addCookie(COOKIE_KEY, "abc");

    }


    public void testCookieStringNotEquals()
            throws ServletException, JspException {

        et.setCookie(COOKIE_KEY);
        et.setValue(testStringValue1);

        assertEquals("Cookie string not equals comparison", false,
                et.condition(0, 0));

    }

    // --------------------------------------------------- Header String Equals


    public void beginHeaderStringEquals(WebRequest testRequest) {

        testRequest.addHeader(HEADER_KEY, "abc");

    }


    public void testHeaderStringEquals()
            throws ServletException, JspException {

        et.setHeader(HEADER_KEY);
        et.setValue(testStringValue);

        assertEquals("Header string equals comparison", true,
                et.condition(0, 0));

    }

    // ----------------------------------------------- Header String Not Equals


    public void beginHeaderStringNotEquals(WebRequest testRequest) {

        testRequest.addHeader(HEADER_KEY, "abc");

    }


    public void testHeaderStringNotEquals()
            throws ServletException, JspException {

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


    public void beginParameterStringEquals(WebRequest testRequest) {

        testRequest.addParameter(PARAMETER_KEY, "abc");

    }


    public void testParameterStringEquals()
            throws ServletException, JspException {

        et.setParameter(PARAMETER_KEY);
        et.setValue(testStringValue);

        assertEquals("Parameter string equals comparison", true,
                et.condition(0, 0));

    }

    // -------------------------------------------- Parameter String Not Equals


    public void beginParameterStringNotEquals(WebRequest testRequest) {

        testRequest.addParameter(PARAMETER_KEY, "abc");

    }


    public void testParameterStringNotEquals()
            throws ServletException, JspException {

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
