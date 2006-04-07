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
import javax.servlet.jsp.PageContext;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.logic.PresentTag</code>
 * class.
 */
public class TestNotPresentTag extends JspTestCase {
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
    public TestNotPresentTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestNotPresentTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestNotPresentTag.class);
    }

    //----- Test initApplication() method --------------------------------------

    /**
     * Verify that there is an application scope object in scope using the
     * <code>NotPresentTag</code>.
     */
    public void testApplicationScopeObjectPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();
        String testKey = "testApplicationScopePresent";
        String testStringValue = "abc";

        pageContext.setAttribute(testKey,
                testStringValue,
                PageContext.APPLICATION_SCOPE);
        npt.setPageContext(pageContext);
        npt.setName(testKey);
        npt.setScope("application");

        assertEquals("Value present (not null)", false, npt.condition(false));
    }

    /**
     * Verify that there is an application scope object is not in scope using
     * the <code>NotPresentTag</code>.
     */
    public void testApplicationScopeObjectNotPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();
        String testKey = "testApplicationScopeNotPresent";

        npt.setPageContext(pageContext);
        npt.setName(testKey);
        npt.setScope("application");


        assertEquals("Value not present (null)", true, npt.condition(false));
    }

    /**
     * Verify that there is an session scope object in scope using the
     * <code>NotPresentTag</code>.
     */
    public void testSessionScopeObjectPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();
        String testKey = "testSessionScopePresent";
        String testStringValue = "abc";

        pageContext.setAttribute(testKey,
                testStringValue,
                PageContext.SESSION_SCOPE);
        npt.setPageContext(pageContext);
        npt.setName(testKey);
        npt.setScope("session");

        assertEquals("Value present (not null)", false, npt.condition(false));
    }

    /**
     * Verify that there is an session scope object is not in scope using the
     * <code>NotPresentTag</code>.
     */
    public void testSessionScopeObjectNotPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();
        String testKey = "testSessionScopeNotPresent";

        npt.setPageContext(pageContext);
        npt.setName(testKey);
        npt.setScope("session");

        assertEquals("Value present (not null)", true, npt.condition(false));
    }

    /**
     * Verify that there is an request scope object in scope using the
     * <code>NotPresentTag</code>.
     */
    public void testRequestScopeObjectPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();
        String testKey = "testRequestScopePresent";
        String testStringValue = "abc";
        npt.setScope("request");

        pageContext.setAttribute(testKey,
                testStringValue,
                PageContext.REQUEST_SCOPE);
        npt.setPageContext(pageContext);
        npt.setName(testKey);

        assertEquals("Value present (not null)", false, npt.condition(false));
    }

    /**
     * Verify that there is an request scope object is not in scope using the
     * <code>NotPresentTag</code>.
     */
    public void testRequestScopeObjectNotPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();
        String testKey = "testRequestScopeNotPresent";

        npt.setPageContext(pageContext);
        npt.setName(testKey);
        npt.setScope("request");

        assertEquals("Value not present (null)", true, npt.condition(false));
    }

    /**
     * Verify that there is an page scope object in scope using the
     * <code>NotPresentTag</code>.
     */
    public void testPageScopeObjectPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();
        String testKey = "testPageScopePresent";
        String testStringValue = "abc";
        npt.setScope("page");

        pageContext.setAttribute(testKey,
                testStringValue,
                PageContext.PAGE_SCOPE);
        npt.setPageContext(pageContext);
        npt.setName(testKey);

        assertEquals("Value present (not null)", false, npt.condition(false));
    }

    /**
     * Verify that there is an page scope object is not in scope using the
     * <code>NotPresentTag</code>.
     */
    public void testPageScopeObjectNotPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();
        String testKey = "testPageScopeNotPresent";

        npt.setPageContext(pageContext);
        npt.setName(testKey);
        npt.setScope("page");

        assertEquals("Value not present (null)", true, npt.condition(false));
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
    public void testCookiePresent() throws ServletException,  javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();

        npt.setPageContext(pageContext);
	npt.setCookie(COOKIE_KEY);

        assertEquals("Cookie present", false, npt.condition(false));
    }
    */

    /**
     * Verify that there isn't an cookie using the <code>PresentTag</code>.
     */
    public void testCookieNotPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();

        npt.setPageContext(pageContext);
        npt.setCookie(COOKIE_KEY);

        assertEquals("Cookie not present", true, npt.condition(false));
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
    public void testHeaderPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();

        npt.setPageContext(pageContext);
        npt.setHeader(HEADER_KEY);

        assertEquals("Header present", false, npt.condition(false));
    }

    /**
     * Verify that there isn't an header using the <code>PresentTag</code>.
     */
    public void testHeaderNotPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();

        npt.setPageContext(pageContext);
        npt.setHeader(HEADER_KEY);

        assertEquals("Header not present", true, npt.condition(false));
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
    public void testParameterPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();

        npt.setPageContext(pageContext);
        npt.setParameter(PARAMETER_KEY);

        assertEquals("Parameter present", false, npt.condition(false));
    }

    /**
     * Verify that there isn't an parameter using the <code>PresentTag</code>.
     */
    public void testParameterNotPresent()
            throws ServletException, javax.servlet.jsp.JspException {
        NotPresentTag npt = new NotPresentTag();

        npt.setPageContext(pageContext);
        npt.setParameter(PARAMETER_KEY);

        assertEquals("Parameter not present", true, npt.condition(false));
    }

}
