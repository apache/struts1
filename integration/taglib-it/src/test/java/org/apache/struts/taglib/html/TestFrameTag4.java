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
public class TestFrameTag4 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFrameTag4(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestFrameTag4.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFrameTag4.class);
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
                .forward("/org/apache/struts/taglib/html/TestFrameTag4.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testFrameActionParamIdParamNameNoScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameActionParamIdParamNameNoScope", "");
    }

    public void testFrameActionParamIdParamNameParamPropertyNoScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameActionParamIdParamNameParamPropertyNoScope", "");
    }

    public void testFrameActionParamIdParamNameApplicationScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.APPLICATION_SCOPE);
        runMyTest("testFrameActionParamIdParamNameApplicationScope", "");
    }

    public void testFrameActionParamIdParamNameParamPropertyApplicationScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testFrameActionParamIdParamNameParamPropertyApplicationScope",
                "");
    }

    public void testFrameActionParamIdParamNameSessionScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.SESSION_SCOPE);
        runMyTest("testFrameActionParamIdParamNameSessionScope", "");
    }

    public void testFrameActionParamIdParamNameParamPropertySessionScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testFrameActionParamIdParamNameParamPropertySessionScope",
                "");
    }

    public void testFrameActionParamIdParamNameRequestScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameActionParamIdParamNameRequestScope", "");
    }

    public void testFrameActionParamIdParamNameParamPropertyRequestScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameActionParamIdParamNameParamPropertyRequestScope",
                "");
    }

    public void testFrameActionScrolling1() throws Exception {
        runMyTest("testFrameActionScrolling1", "");
    }

    public void testFrameActionScrollin2g() throws Exception {
        runMyTest("testFrameActionScrolling2", "");
    }

    public void testFrameActionScrolling3() throws Exception {
        runMyTest("testFrameActionScrolling3", "");
    }

    public void testFrameActionScrolling4() throws Exception {
        runMyTest("testFrameActionScrolling4", "");
    }

    public void testFrameActionScrolling5() throws Exception {
        runMyTest("testFrameActionScrolling5", "");
    }

    public void testFrameActionScrolling6() throws Exception {
        runMyTest("testFrameActionScrolling6", "");
    }

    public void testFrameActionScrolling7() throws Exception {
        runMyTest("testFrameActionScrolling7", "");
    }

    public void testFrameActionScrolling8() throws Exception {
        runMyTest("testFrameActionScrolling8", "");
    }

    public void testFrameActionScrolling9() throws Exception {
        runMyTest("testFrameActionScrolling9", "");
    }

    public void testFrameActionScrolling10() throws Exception {
        runMyTest("testFrameActionScrolling10", "");
    }

    public void testFrameActionStyle() throws Exception {
        runMyTest("testFrameActionStyle", "");
    }

    public void testFrameActionTitle() throws Exception {
        runMyTest("testFrameActionTitle", "");
    }

    public void testFrameActionTitleKey() throws Exception {
        runMyTest("testFrameActionTitleKey", "");
    }

    public void testFrameActionTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY,
                "Some_Token_Here",
                PageContext.SESSION_SCOPE);
        runMyTest("testFrameActionTransaction", "");
    }


}
