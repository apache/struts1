/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.struts.taglib.logic;

import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import junit.framework.*;
import org.apache.cactus.*;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.logic.NotEqualTag</code> class.
 * @author Dominique Plante
 */
public class TestNotEqualTag extends JspTestCase {
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

    /**
     * Create cookie for testCookieStringEquals method test.
    */
    public void beginCookieStringEquals(WebRequest testRequest) {
       testRequest.addCookie(COOKIE_KEY, "abc");
    }

    public void testCookieStringEquals() throws ServletException,  javax.servlet.jsp.JspException {
        net.setCookie(COOKIE_KEY);
        net.setValue(testStringValue);

        assertEquals("Cookie string equals comparison", true, net.condition(0, 0));
    }

    /**
     * Create cookie for testCookieStringNotEquals method test.
    */
    public void beginCookieStringNotEquals(WebRequest testRequest) {
       testRequest.addCookie(COOKIE_KEY, "abc");
    }

    public void testCookieStringNotEquals() throws ServletException,  javax.servlet.jsp.JspException {
        net.setCookie(COOKIE_KEY);
        net.setValue(testStringValue1);

        assertEquals("Cookie string not equals comparison", false, net.condition(0, 0));
    }

    /**
     * Create cookie for testHeaderStringEquals method test.
    */
    public void beginHeaderStringEquals(WebRequest testRequest) {
       testRequest.addHeader(COOKIE_KEY, "abc");
    }

    public void testHeaderStringEquals() throws ServletException,  javax.servlet.jsp.JspException {
        net.setHeader(COOKIE_KEY);
        net.setValue(testStringValue);

        assertEquals("Header string equals comparison", true, net.condition(0, 0));
    }

    /**
     * Create cookie for testHeaderStringNotEquals method test.
    */
    public void beginHeaderStringNotEquals(WebRequest testRequest) {
       testRequest.addHeader(COOKIE_KEY, "abc");
    }

    public void testHeaderStringNotEquals() throws ServletException,  javax.servlet.jsp.JspException {
        net.setHeader(COOKIE_KEY);
        net.setValue(testStringValue1);

        assertEquals("Header string not equals comparison", false, net.condition(0, 0));
    }

    /**
     * Create cookie for testParameterStringEquals method test.
    */
    public void beginParameterStringEquals(WebRequest testRequest) {
       testRequest.addParameter(PARAMETER_KEY, "abc");
    }

    public void testParameterStringEquals() throws ServletException,  javax.servlet.jsp.JspException {
        net.setParameter(PARAMETER_KEY);
        net.setValue(testStringValue);

        assertEquals("Parameter string equals comparison", true, net.condition(0, 0));
    }

    /**
     * Create cookie for testParameterStringNotEquals method test.
    */
    public void beginParameterStringNotEquals(WebRequest testRequest) {
       testRequest.addParameter(PARAMETER_KEY, "abc");
    }

    public void testParameterStringNotEquals() throws ServletException,  javax.servlet.jsp.JspException {
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