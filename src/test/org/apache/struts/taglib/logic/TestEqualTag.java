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

import javax.servlet.ServletException;import javax.servlet.jsp.JspException;import junit.framework.Test;import junit.framework.TestSuite;import org.apache.cactus.JspTestCase;import org.apache.cactus.WebRequest;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.logic.EqualTag</code> class.
 *
 * @author David Winterfeldt
 * @author Dominique Plante
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
        et = new EqualTag();
        et.setPageContext(pageContext);

    }

    public void tearDown() {
        et = null;
    }



    // --------------------------------------------------- Cookie String Equals


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
