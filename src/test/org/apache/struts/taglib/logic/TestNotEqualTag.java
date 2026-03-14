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
import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.struts.mock.*;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.logic.NotEqualTag</code> class.
 */
public class TestNotEqualTag extends TestCase {
    protected final static String COOKIE_KEY = "org.apache.struts.taglib.logic.COOKIE_KEY";
    protected final static String HEADER_KEY = "org.apache.struts.taglib.logic.HEADER_KEY";
    protected final static String PARAMETER_KEY = "org.apache.struts.taglib.logic.PARAMETER_KEY";
    protected NotEqualTag net = null;
    protected static String testStringKey;
    protected static String testStringValue;
    protected static String testStringValue1;
    protected static String testIntegerKey;
    protected static Integer testIntegerValue;
    protected static Integer testIntegerValue1;

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
    public TestNotEqualTag(String theName) {
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
        return new TestSuite(TestNotEqualTag.class);
    }

    public void setUp()
    {
        context = new MockServletContext();
        config = new MockServletConfig(context);
        session = new MockHttpSession(context);
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
        pageContext = new MockPageContext(config, request, response);

        testStringKey = "testString";
        testStringValue = "abc";
        testStringValue1 = "abcd";
        testIntegerKey = "testInteger";
        testIntegerValue = new Integer(21);
        testIntegerValue1 = new Integer(testIntegerValue.intValue() + 1);

        net = new NotEqualTag();
        net.setPageContext(pageContext);
    }

    public void tearDown()
    {
        net = null;
    }

    // Create cookie for testCookieStringEquals method test.
    /* FIXME: Cactus does not send cookies?
    public void beginCookieStringEquals(WebRequest testRequest) {
       testRequest.addCookie(COOKIE_KEY, "abc");
    }

    public void testCookieStringEquals() throws ServletException,  javax.servlet.jsp.JspException {
        net.setCookie(COOKIE_KEY);
        net.setValue(testStringValue);

        assertEquals("Cookie string equals comparison", true, net.condition(0, 0));
    }
    */

    /**
     * Create cookie for testCookieStringNotEquals method test.
    */
    public void testCookieStringNotEquals() throws ServletException,  javax.servlet.jsp.JspException {
        request.addCookie(new javax.servlet.http.Cookie(COOKIE_KEY, "abc"));

        net.setCookie(COOKIE_KEY);
        net.setValue(testStringValue1);

        assertEquals("Cookie string not equals comparison", false, net.condition(0, 0));
    }

    /**
     * Create cookie for testHeaderStringEquals method test.
    */
    public void testHeaderStringEquals() throws ServletException,  javax.servlet.jsp.JspException {
        request.addHeader(COOKIE_KEY, "abc");

        net.setHeader(COOKIE_KEY);
        net.setValue(testStringValue);

        assertEquals("Header string equals comparison", true, net.condition(0, 0));
    }

    /**
     * Create cookie for testHeaderStringNotEquals method test.
    */
    public void testHeaderStringNotEquals() throws ServletException,  javax.servlet.jsp.JspException {
        request.addHeader(COOKIE_KEY, "abc");

        net.setHeader(COOKIE_KEY);
        net.setValue(testStringValue1);

        assertEquals("Header string not equals comparison", false, net.condition(0, 0));
    }

    /**
     * Create cookie for testParameterStringEquals method test.
    */
    public void testParameterStringEquals() throws ServletException,  javax.servlet.jsp.JspException {
        request.addParameter(PARAMETER_KEY, "abc");

        net.setParameter(PARAMETER_KEY);
        net.setValue(testStringValue);

        assertEquals("Parameter string equals comparison", true, net.condition(0, 0));
    }

    /**
     * Create cookie for testParameterStringNotEquals method test.
    */
    public void testParameterStringNotEquals() throws ServletException,  javax.servlet.jsp.JspException {
        request.addParameter(PARAMETER_KEY, "abc");

        net.setParameter(PARAMETER_KEY);
        net.setValue(testStringValue1);

        assertEquals("Parameter string not equals comparison", false, net.condition(0, 0));
    }

    /**
     * Verify that two <code>String</code>s match using the <code>NotEqualTag</code>.
     */
    public void testStringEquals() throws ServletException,  javax.servlet.jsp.JspException {
        request.setAttribute(testStringKey, testStringValue);
        net.setName(testStringKey);
        net.setValue(testStringValue);
        System.out.println("testing");
        assertEquals("String equals comparison", true, net.condition(0, 0));
    }

    /**
     * Verify that two <code>String</code>s do not match using the <code>NotEqualTag</code>.
     */
    public void testStringNotEquals() throws ServletException,  javax.servlet.jsp.JspException {
        request.setAttribute(testStringKey, testStringValue);
        net.setName(testStringKey);
        net.setValue(testStringValue1);

        assertEquals("String not equals comparison", false, net.condition(0, 0));
    }

    /**
     * Verify that an <code>Integer</code> and a <code>String</code>
     * match using the <code>NotEqualTag</code>.
     */
    public void testIntegerEquals() throws ServletException,  javax.servlet.jsp.JspException {
        request.setAttribute(testIntegerKey, testIntegerValue);
        net.setName(testIntegerKey);
        net.setValue(testIntegerValue.toString());

        assertEquals("Integer equals comparison", true, net.condition(0, 0));
    }

    /**
     * Verify that two <code>String</code>s do not match using the <code>NotEqualTag</code>.
     */
    public void testIntegerNotEquals() throws ServletException,  javax.servlet.jsp.JspException {
        request.setAttribute(testIntegerKey, testIntegerValue);
        net.setName(testIntegerKey);
        net.setValue(testIntegerValue1.toString());

        assertEquals("Integer equals comparison", false, net.condition(0, 0));
    }

    /**
     * Verify that there is an application scope String in scope using the <code>EqualTag</code>.
    */
    public void testApplicationScopeStringEquals() throws ServletException,  javax.servlet.jsp.JspException {
        String testKey = "testApplicationScopeStringEquals";

        pageContext.setAttribute(testKey, testStringValue, PageContext.APPLICATION_SCOPE);
        net.setPageContext(pageContext);
        net.setName(testKey);
        net.setScope("application");
        net.setValue(testStringValue);

        assertEquals("String in application scope equals", true, net.condition(0, 0));
    }

    /**
     * Verify that there is an application scope String that is not equal using the <code>EqualTag</code>.
    */
    public void testApplicationScopeStringNotEquals() throws ServletException,  javax.servlet.jsp.JspException {
        String testKey = "testApplicationScopeStringNotEquals";

        pageContext.setAttribute(testKey, testStringValue, PageContext.APPLICATION_SCOPE);
        net.setPageContext(pageContext);
        net.setName(testKey);
        net.setScope("application");
        net.setValue(testStringValue1);

        assertEquals("String in application scope not equals", false, net.condition(0, 0));
    }

}
