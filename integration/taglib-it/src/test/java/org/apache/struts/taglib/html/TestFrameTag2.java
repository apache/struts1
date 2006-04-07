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
public class TestFrameTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFrameTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestFrameTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFrameTag2.class);
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
                .forward("/org/apache/struts/taglib/html/TestFrameTag2.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testFrameForwardParamIdParamNameNoScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameForwardParamIdParamNameNoScope", "");
    }

    public void testFrameForwardParamIdParamNameParamPropertyNoScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameForwardParamIdParamNameParamPropertyNoScope", "");
    }

    public void testFrameForwardParamIdParamNameApplicationScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.APPLICATION_SCOPE);
        runMyTest("testFrameForwardParamIdParamNameApplicationScope", "");
    }

    public void testFrameForwardParamIdParamNameParamPropertyApplicationScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testFrameForwardParamIdParamNameParamPropertyApplicationScope",
                "");
    }

    public void testFrameForwardParamIdParamNameSessionScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.SESSION_SCOPE);
        runMyTest("testFrameForwardParamIdParamNameSessionScope", "");
    }

    public void testFrameForwardParamIdParamNameParamPropertySessionScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testFrameForwardParamIdParamNameParamPropertySessionScope",
                "");
    }

    public void testFrameForwardParamIdParamNameRequestScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameForwardParamIdParamNameRequestScope", "");
    }

    public void testFrameForwardParamIdParamNameParamPropertyRequestScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameForwardParamIdParamNameParamPropertyRequestScope",
                "");
    }

    public void testFrameForwardScrolling1() throws Exception {
        runMyTest("testFrameForwardScrolling1", "");
    }

    public void testFrameForwardScrolling2() throws Exception {
        runMyTest("testFrameForwardScrolling2", "");
    }

    public void testFrameForwardScrolling3() throws Exception {
        runMyTest("testFrameForwardScrolling3", "");
    }

    public void testFrameForwardScrolling4() throws Exception {
        runMyTest("testFrameForwardScrolling4", "");
    }

    public void testFrameForwardScrolling5() throws Exception {
        runMyTest("testFrameForwardScrolling5", "");
    }

    public void testFrameForwardScrolling6() throws Exception {
        runMyTest("testFrameForwardScrolling6", "");
    }

    public void testFrameForwardScrolling7() throws Exception {
        runMyTest("testFrameForwardScrolling7", "");
    }

    public void testFrameForwardScrolling8() throws Exception {
        runMyTest("testFrameForwardScrolling8", "");
    }

    public void testFrameForwardScrolling9() throws Exception {
        runMyTest("testFrameForwardScrolling9", "");
    }

    public void testFrameForwardScrolling10() throws Exception {
        runMyTest("testFrameForwardScrolling10", "");
    }

    public void testFrameForwardStyle() throws Exception {
        runMyTest("testFrameForwardStyle", "");
    }

    public void testFrameForwardTitle() throws Exception {
        runMyTest("testFrameForwardTitle", "");
    }

    public void testFrameForwardTitleKey() throws Exception {
        runMyTest("testFrameForwardTitleKey", "");
    }

    public void testFrameForwardTitleKeyAlt() throws Exception {
        runMyTest("testFrameForwardTitleKeyAlt", "");
    }

    public void testFrameForwardTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY,
                "Some_Token_Here",
                PageContext.SESSION_SCOPE);
        runMyTest("testFrameForwardTransaction", "");
    }


}
