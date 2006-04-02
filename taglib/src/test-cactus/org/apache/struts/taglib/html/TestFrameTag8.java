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
public class TestFrameTag8 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFrameTag8(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestFrameTag8.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFrameTag8.class);
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
                .forward("/org/apache/struts/taglib/html/TestFrameTag8.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testFramePageParamIdParamNameNoScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testFramePageParamIdParamNameNoScope", "");
    }

    public void testFramePageParamIdParamNameParamPropertyNoScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFramePageParamIdParamNameParamPropertyNoScope", "");
    }

    public void testFramePageParamIdParamNameApplicationScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.APPLICATION_SCOPE);
        runMyTest("testFramePageParamIdParamNameApplicationScope", "");
    }

    public void testFramePageParamIdParamNameParamPropertyApplicationScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest("testFramePageParamIdParamNameParamPropertyApplicationScope",
                "");
    }

    public void testFramePageParamIdParamNameSessionScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.SESSION_SCOPE);
        runMyTest("testFramePageParamIdParamNameSessionScope", "");
    }

    public void testFramePageParamIdParamNameParamPropertySessionScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testFramePageParamIdParamNameParamPropertySessionScope",
                "");
    }

    public void testFramePageParamIdParamNameRequestScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testFramePageParamIdParamNameRequestScope", "");
    }

    public void testFramePageParamIdParamNameParamPropertyRequestScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFramePageParamIdParamNameParamPropertyRequestScope",
                "");
    }

    public void testFramePageScrolling1() throws Exception {
        runMyTest("testFramePageScrolling1", "");
    }

    public void testFramePageScrollin2g() throws Exception {
        runMyTest("testFramePageScrolling2", "");
    }

    public void testFramePageScrolling3() throws Exception {
        runMyTest("testFramePageScrolling3", "");
    }

    public void testFramePageScrolling4() throws Exception {
        runMyTest("testFramePageScrolling4", "");
    }

    public void testFramePageScrolling5() throws Exception {
        runMyTest("testFramePageScrolling5", "");
    }

    public void testFramePageScrolling6() throws Exception {
        runMyTest("testFramePageScrolling6", "");
    }

    public void testFramePageScrolling7() throws Exception {
        runMyTest("testFramePageScrolling7", "");
    }

    public void testFramePageScrolling8() throws Exception {
        runMyTest("testFramePageScrolling8", "");
    }

    public void testFramePageScrolling9() throws Exception {
        runMyTest("testFramePageScrolling9", "");
    }

    public void testFramePageScrolling10() throws Exception {
        runMyTest("testFramePageScrolling10", "");
    }

    public void testFramePageStyle() throws Exception {
        runMyTest("testFramePageStyle", "");
    }

    public void testFramePageTitle() throws Exception {
        runMyTest("testFramePageTitle", "");
    }

    public void testFramePageTitleKey() throws Exception {
        runMyTest("testFramePageTitleKey", "");
    }

    public void testFramePageTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY,
                "Some_Token_Here",
                PageContext.SESSION_SCOPE);
        runMyTest("testFramePageTransaction", "");
    }


}
