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
package org.apache.struts.taglib.html;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.FrameTag</code>
 * class.
 */
public class TestLinkTag6 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLinkTag6(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestLinkTag6.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLinkTag6.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestLinkTag6.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testLinkHrefOnblur() throws Exception {
        runMyTest("testLinkHrefOnblur", "");
    }

    public void testLinkHrefOnclick() throws Exception {
        runMyTest("testLinkHrefOnclick", "");
    }

    public void testLinkHrefOndblclick() throws Exception {
        runMyTest("testLinkHrefOndblclick", "");
    }

    public void testLinkHrefOnfocus() throws Exception {
        runMyTest("testLinkHrefOnfocus", "");
    }

    public void testLinkHrefOnkeydown() throws Exception {
        runMyTest("testLinkHrefOnkeydown", "");
    }

    public void testLinkHrefOnkeypress() throws Exception {
        runMyTest("testLinkHrefOnkeypress", "");
    }

    public void testLinkHrefOnkeyup() throws Exception {
        runMyTest("testLinkHrefOnkeyup", "");
    }

    public void testLinkHrefOnmousedown() throws Exception {
        runMyTest("testLinkHrefOnmousedown", "");
    }

    public void testLinkHrefOnmousemove() throws Exception {
        runMyTest("testLinkHrefOnmousemove", "");
    }

    public void testLinkHrefOnmouseout() throws Exception {
        runMyTest("testLinkHrefOnmouseout", "");
    }

    public void testLinkHrefOnmouseover() throws Exception {
        runMyTest("testLinkHrefOnmouseover", "");
    }

    public void testLinkHrefOnmouseup() throws Exception {
        runMyTest("testLinkHrefOnmouseup", "");
    }

    public void testLinkHrefParamIdParamNameNoScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameNoScope", "");
    }

    public void testLinkHrefParamIdParamNameParamPropertyNoScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameParamPropertyNoScope", "");
    }

    public void testLinkHrefParamIdParamNameApplicationScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameApplicationScope", "");
    }

    public void testLinkHrefParamIdParamNameParamPropertyApplicationScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameParamPropertyApplicationScope",
                "");
    }

    public void testLinkHrefParamIdParamNameSessionScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameSessionScope", "");
    }

    public void testLinkHrefParamIdParamNameParamPropertySessionScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameParamPropertySessionScope",
                "");
    }

    public void testLinkHrefParamIdParamNameRequestScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameRequestScope", "");
    }

    public void testLinkHrefParamIdParamNameParamPropertyRequestScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameParamPropertyRequestScope",
                "");
    }


    public void testLinkHrefStyle() throws Exception {
        runMyTest("testLinkHrefStyle", "");
    }

    public void testLinkHrefStyleClass() throws Exception {
        runMyTest("testLinkHrefStyleClass", "");
    }

    public void testLinkHrefStyleId() throws Exception {
        runMyTest("testLinkHrefStyleId", "");
    }

    public void testLinkHrefTabIndex() throws Exception {
        runMyTest("testLinkHrefTabIndex", "");
    }

    public void testLinkHrefTarget() throws Exception {
        runMyTest("testLinkHrefTarget", "");
    }


    public void testLinkHrefTitle() throws Exception {
        runMyTest("testLinkHrefTitle", "");
    }

    public void testLinkHrefTitleKey() throws Exception {
        runMyTest("testLinkHrefTitleKey", "");
    }

    public void testLinkHrefTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY,
                "Some_Token_Here",
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkHrefTransaction", "");
    }


}
