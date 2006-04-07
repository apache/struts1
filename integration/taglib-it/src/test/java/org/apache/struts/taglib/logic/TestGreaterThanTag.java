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
import org.apache.struts.util.LabelValueBean;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.logic.GreaterThanTag</code>
 * class.
 */
public class TestGreaterThanTag extends JspTestCase {

    protected final static String COOKIE_KEY =
            "org.apache.struts.taglib.logic.COOKIE_KEY";
    protected final static String HEADER_KEY =
            "org.apache.struts.taglib.logic.HEADER_KEY";
    protected final static String PARAMETER_KEY =
            "org.apache.struts.taglib.logic.PARAMETER_KEY";
    protected final static String GREATER_VAL = "6";
    protected final static String LESSER_VAL = "4";


    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestGreaterThanTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestGreaterThanTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestGreaterThanTag.class);
    }

    //----- Test initApplication() method --------------------------------------

    /**
     * Create cookie for testCookieGreaterThan method test.
     */
    /* FIXME: Cactus does not send cookies?
    public void beginCookieGreaterThan(WebRequest testRequest) {
       testRequest.addCookie(COOKIE_KEY, GREATER_VAL);
    }
    */

    /**
     * Create header for testHeaderGreaterThan method test.
     */
    public void beginHeaderGreaterThan(WebRequest testRequest) {
        testRequest.addHeader(HEADER_KEY, GREATER_VAL);
    }

    /**
     * Create header for testParameterGreaterThan method test.
     */
    public void beginParameterGreaterThan(WebRequest testRequest) {
        testRequest.addParameter(PARAMETER_KEY, GREATER_VAL);
    }

    /**
     * Verify the value stored in a cookie using <code>GreaterThanTag</code>.
     */
    /* FIXME: Cactus does not send cookies?
    public void testCookieGreaterThan() throws ServletException,  JspException {
        GreaterThanTag gt = new GreaterThanTag();
        gt.setPageContext(pageContext);
        gt.setCookie(COOKIE_KEY);
        gt.setValue(LESSER_VAL);

        assertTrue(
                "Cookie Value (" + GREATER_VAL + ") is greater than value (" + LESSER_VAL + ")",
                gt.condition());
    }
    */

    /**
     * Verify the value stored in header using <code>GreaterThanTag</code>.
     */
    public void testHeaderGreaterThan()
            throws ServletException, JspException {
        GreaterThanTag gt = new GreaterThanTag();
        gt.setPageContext(pageContext);
        gt.setHeader(HEADER_KEY);
        gt.setValue(LESSER_VAL);

        assertTrue(
                "Header Value (" + GREATER_VAL + ") is greater than value ("
                        + LESSER_VAL + ")",
                gt.condition());
    }

    /**
     * Verify the value stored in parameter using <code>GreaterThanTag</code>.
     */
    public void testParameterGreaterThan()
            throws ServletException, JspException {
        GreaterThanTag gt = new GreaterThanTag();
        gt.setPageContext(pageContext);
        gt.setParameter(PARAMETER_KEY);
        gt.setValue(LESSER_VAL);

        assertTrue(
                "Parameter Value (" + GREATER_VAL
                        + ") is greater than value (" + LESSER_VAL + ")",
                gt.condition());
    }


    /**
     * Testing <code>GreaterThanTag</code> using name attribute in the
     * application scope.
     */
    public void testApplicationScopeNameGreaterThan()
            throws ServletException, JspException {

        GreaterThanTag gt = new GreaterThanTag();

        String testKey = "testApplicationScopeNameGreaterThan";
        Integer itgr = new Integer(GREATER_VAL);

        pageContext
                .setAttribute(testKey, itgr, PageContext.APPLICATION_SCOPE);
        gt.setPageContext(pageContext);
        gt.setName(testKey);
        gt.setScope("application");
        gt.setValue(LESSER_VAL);

        assertTrue(
                "Application scope value from name (" + GREATER_VAL
                        + ") is greater than value (" + LESSER_VAL + ")",
                gt.condition());
    }

    /**
     * Testing <code>GreaterThanTag</code> using name attribute in the session
     * scope.
     */
    public void testSessionScopeNameGreaterThan()
            throws ServletException, JspException {

        GreaterThanTag gt = new GreaterThanTag();

        String testKey = "testSessionScopeNameGreaterThan";
        Integer itgr = new Integer(GREATER_VAL);

        pageContext.setAttribute(testKey, itgr, PageContext.SESSION_SCOPE);
        gt.setPageContext(pageContext);
        gt.setName(testKey);
        gt.setScope("session");
        gt.setValue(LESSER_VAL);

        assertTrue(
                "Session scope value from name (" + GREATER_VAL
                        + ") is greater than value (" + LESSER_VAL + ")",
                gt.condition());
    }

    /**
     * Testing <code>GreaterThanTag</code> using name attribute in the request
     * scope.
     */
    public void testRequestScopeNameGreaterThan()
            throws ServletException, JspException {

        GreaterThanTag gt = new GreaterThanTag();

        String testKey = "testRequestScopeNameGreaterThan";
        Integer itgr = new Integer(GREATER_VAL);

        pageContext.setAttribute(testKey, itgr, PageContext.REQUEST_SCOPE);
        gt.setPageContext(pageContext);
        gt.setName(testKey);
        gt.setScope("request");
        gt.setValue(LESSER_VAL);

        assertTrue(
                "Request scope value from name (" + GREATER_VAL
                        + ") is greater than value (" + LESSER_VAL + ")",
                gt.condition());
    }


    /**
     * Testing <code>GreaterThanTag</code> using name and property attribute
     * in the application scope.
     */
    public void testApplicationScopePropertyGreaterThan()
            throws ServletException, JspException {

        GreaterThanTag gt = new GreaterThanTag();

        String testKey = "testApplicationScopePropertyGreaterThan";
        LabelValueBean lvb = new LabelValueBean("The Key", GREATER_VAL);

        pageContext.setAttribute(testKey, lvb, PageContext.APPLICATION_SCOPE);
        gt.setPageContext(pageContext);
        gt.setName(testKey);
        gt.setScope("application");
        gt.setProperty("value");
        gt.setValue(LESSER_VAL);

        assertTrue(
                "Value (" + LESSER_VAL + ") is greater than value ("
                        + GREATER_VAL + ")",
                gt.condition());
    }

    /**
     * Testing <code>GreaterThanTag</code> using name and property attribute
     * in the session scope.
     */
    public void testSessionScopePropertyGreaterThan()
            throws ServletException, JspException {

        GreaterThanTag gt = new GreaterThanTag();

        String testKey = "testSessionScopePropertyGreaterThan";
        LabelValueBean lvb = new LabelValueBean("The Key", GREATER_VAL);

        pageContext.setAttribute(testKey, lvb, PageContext.SESSION_SCOPE);
        gt.setPageContext(pageContext);
        gt.setName(testKey);
        gt.setScope("session");
        gt.setProperty("value");
        gt.setValue(LESSER_VAL);

        assertTrue(
                "Value (" + LESSER_VAL + ") is greater than value ("
                        + GREATER_VAL + ")",
                gt.condition());
    }

    /**
     * Testing <code>GreaterThanTag</code> using name and property attribute
     * in the request scope.
     */
    public void testRequestScopePropertyGreaterThan()
            throws ServletException, JspException {

        GreaterThanTag gt = new GreaterThanTag();

        String testKey = "testRequestScopePropertyGreaterThan";
        LabelValueBean lvb = new LabelValueBean("The Key", GREATER_VAL);

        pageContext.setAttribute(testKey, lvb, PageContext.REQUEST_SCOPE);
        gt.setPageContext(pageContext);
        gt.setName(testKey);
        gt.setScope("request");
        gt.setProperty("value");
        gt.setValue(LESSER_VAL);

        assertTrue(
                "Value (" + LESSER_VAL + ") is greater than value ("
                        + GREATER_VAL + ")",
                gt.condition());
    }


}
