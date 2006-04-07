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
 * Suite of unit tests for the <code>org.apache.struts.taglib.logic.PresentTag</code>
 * class.
 */
public class TestPresentTag extends JspTestCase {
    protected final static String COOKIE_KEY =
            "org.apache.struts.taglib.logic.COOKIE_KEY";
    protected final static String HEADER_KEY =
            "org.apache.struts.taglib.logic.HEADER_KEY";
    protected final static String PARAMETER_KEY =
            "org.apache.struts.taglib.logic.PARAMETER_KEY";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestPresentTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestPresentTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestPresentTag.class);
    }

    //----- Test initApplication() method --------------------------------------

    /**
     * Verify that there is an application scope object in scope using the
     * <code>PresentTag</code>.
     */
    public void testApplicationScopeObjectPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testApplicationScopePresent";
        String testStringValue = "abc";

        pageContext.setAttribute(testKey,
                testStringValue,
                PageContext.APPLICATION_SCOPE);
        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("application");

        assertEquals("Value present (not null)", true, pt.condition(true));
    }

    /**
     * Verify that there is an application scope object is not in scope using
     * the <code>PresentTag</code>.
     */
    public void testApplicationScopeObjectNotPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testApplicationScopeNotPresent";

        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("application");

        assertEquals("Value not present (null)", false, pt.condition(true));
    }

    /**
     * Verify that there is an session scope object in scope using the
     * <code>PresentTag</code>.
     */
    public void testSessionScopeObjectPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testSessionScopePresent";
        String testStringValue = "abc";

        pageContext.setAttribute(testKey,
                testStringValue,
                PageContext.SESSION_SCOPE);
        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("session");

        assertEquals("Value present (not null)", true, pt.condition(true));
    }

    /**
     * Verify that there is an session scope object is not in scope using the
     * <code>PresentTag</code>.
     */
    public void testSessionScopeObjectNotPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testSessionScopeNotPresent";

        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("session");

        assertEquals("Value present (not null)", false, pt.condition(true));
    }

    /**
     * Verify that there is an request scope object in scope using the
     * <code>PresentTag</code>.
     */
    public void testRequestScopeObjectPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testRequestScopePresent";
        String testStringValue = "abc";
        pt.setScope("request");

        pageContext.setAttribute(testKey,
                testStringValue,
                PageContext.REQUEST_SCOPE);
        pt.setPageContext(pageContext);
        pt.setName(testKey);

        assertEquals("Value present (not null)", true, pt.condition(true));
    }

    /**
     * Verify that there is an request scope object is not in scope using the
     * <code>PresentTag</code>.
     */
    public void testRequestScopeObjectNotPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testRequestScopeNotPresent";

        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("request");

        assertEquals("Value not present (null)", false, pt.condition(true));
    }

    /**
     * Verify that there is an page scope object in scope using the
     * <code>PresentTag</code>.
     */
    public void testPageScopeObjectPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testPageScopePresent";
        String testStringValue = "abc";
        pt.setScope("page");

        pageContext.setAttribute(testKey,
                testStringValue,
                PageContext.PAGE_SCOPE);
        pt.setPageContext(pageContext);
        pt.setName(testKey);

        assertEquals("Value present (not null)", true, pt.condition(true));
    }

    /**
     * Verify that there is an page scope object is not in scope using the
     * <code>PresentTag</code>.
     */
    public void testPageScopeObjectNotPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testPageScopeNotPresent";

        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("page");

        assertEquals("Value not present (null)", false, pt.condition(true));
    }

    /**
     * Verify that there is a LabelValueBean in application scope and test to
     * see if it has a getValue() using the <code>PresentTag</code>.
     */
    public void testApplicationScopePropertyPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testApplicationScopePropertyPresent";

        String testStringValue = "The Value";
        LabelValueBean lvb = new LabelValueBean("The Key", testStringValue);

        pageContext.setAttribute(
                testKey,
                lvb,
                PageContext.APPLICATION_SCOPE);
        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("application");

        pt.setProperty("value");
        assertEquals("Property present (not null)", true, pt.condition(true));
    }

    /**
     * Verify that there is a LabelValueBean in application scope and test to
     * see if it has a getValue() that returns null using the
     * <code>PresentTag</code>.
     */
    public void testApplicationScopePropertyNotPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testApplicationScopePropertyPresent";

        String testStringValue = null;
        LabelValueBean lvb = new LabelValueBean("The Key", testStringValue);

        pageContext.setAttribute(
                testKey,
                lvb,
                PageContext.APPLICATION_SCOPE);
        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("application");

        pt.setProperty("value");
        assertEquals("Property present (not null)",
                false,
                pt.condition(true));
    }

    /**
     * Verify that there is a LabelValueBean in Request scope and test to see
     * if it has a getValue() using the <code>PresentTag</code>.
     */
    public void testRequestScopePropertyPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testRequestScopePropertyPresent";

        String testStringValue = "The Value";
        LabelValueBean lvb = new LabelValueBean("The Key", testStringValue);

        pageContext.setAttribute(
                testKey,
                lvb,
                PageContext.REQUEST_SCOPE);
        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("request");

        pt.setProperty("value");
        assertEquals("Property present (not null)", true, pt.condition(true));
    }

    /**
     * Verify that there is a LabelValueBean in Request scope and test to see
     * if it has a getValue() that returns null using the
     * <code>PresentTag</code>.
     */
    public void testRequestScopePropertyNotPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();
        String testKey = "testRequestScopePropertyNotPresent";

        String testStringValue = null;
        LabelValueBean lvb = new LabelValueBean("The Key", testStringValue);

        pageContext.setAttribute(
                testKey,
                lvb,
                PageContext.REQUEST_SCOPE);
        pt.setPageContext(pageContext);
        pt.setName(testKey);
        pt.setScope("request");

        pt.setProperty("value");
        assertEquals("Property present (not null)",
                false,
                pt.condition(true));
    }

    /**
     * Create cookie for testCookiePresent method test.
     */
    /* FIXME: Cactus does not send cookies?
    public void beginCookiePresent(WebRequest testRequest) {
       testRequest.addCookie(COOKIE_KEY, "cookie value");
    }
    */

    /**
     * Verify that there is an cookie using the <code>PresentTag</code>.
     */
    /* FIXME: Cactus does not send cookies?
    public void testCookiePresent() throws ServletException,  JspException {
        PresentTag pt = new PresentTag();

        pt.setPageContext(pageContext);
	pt.setCookie(COOKIE_KEY);

        assertEquals("Cookie present", true, pt.condition(true));
    }
    */

    /**
     * Verify that there isn't an cookie using the <code>PresentTag</code>.
     */
    public void testCookieNotPresent() throws ServletException, JspException {
        PresentTag pt = new PresentTag();

        pt.setPageContext(pageContext);
        pt.setCookie(COOKIE_KEY);

        assertEquals("Cookie not present", false, pt.condition(true));
    }

    /**
     * Create header for testHeaderPresent method test.
     */
    public void beginHeaderPresent(WebRequest testRequest) {
        testRequest.addHeader(HEADER_KEY, "header value");
    }

    /**
     * Verify that there is an header using the <code>PresentTag</code>.
     */
    public void testHeaderPresent() throws ServletException, JspException {
        PresentTag pt = new PresentTag();

        pt.setPageContext(pageContext);
        pt.setHeader(HEADER_KEY);

        assertEquals("Header present", true, pt.condition(true));
    }

    /**
     * Verify that there isn't an header using the <code>PresentTag</code>.
     */
    public void testHeaderNotPresent() throws ServletException, JspException {
        PresentTag pt = new PresentTag();

        pt.setPageContext(pageContext);
        pt.setHeader(HEADER_KEY);

        assertEquals("Header not present", false, pt.condition(true));
    }

    /**
     * Create parameter for testParameterPresent method test.
     */
    public void beginParameterPresent(WebRequest testRequest) {
        testRequest.addParameter(PARAMETER_KEY, "parameter value");
    }

    /**
     * Verify that there is an parameter using the <code>PresentTag</code>.
     */
    public void testParameterPresent() throws ServletException, JspException {
        PresentTag pt = new PresentTag();

        pt.setPageContext(pageContext);
        pt.setParameter(PARAMETER_KEY);

        assertEquals("Parameter present", true, pt.condition(true));
    }

    /**
     * Verify that there isn't an parameter using the <code>PresentTag</code>.
     */
    public void testParameterNotPresent()
            throws ServletException, JspException {
        PresentTag pt = new PresentTag();

        pt.setPageContext(pageContext);
        pt.setParameter(PARAMETER_KEY);

        assertEquals("Parameter not present", false, pt.condition(true));
    }

}
