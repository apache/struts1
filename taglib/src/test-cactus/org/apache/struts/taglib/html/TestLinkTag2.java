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
public class TestLinkTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLinkTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestLinkTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLinkTag2.class);
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
                .forward("/org/apache/struts/taglib/html/TestLinkTag2.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testLinkForwardOnblur() throws Exception {
        runMyTest("testLinkForwardOnblur", "");
    }

    public void testLinkForwardOnclick() throws Exception {
        runMyTest("testLinkForwardOnclick", "");
    }

    public void testLinkForwardOndblclick() throws Exception {
        runMyTest("testLinkForwardOndblclick", "");
    }

    public void testLinkForwardOnfocus() throws Exception {
        runMyTest("testLinkForwardOnfocus", "");
    }

    public void testLinkForwardOnkeydown() throws Exception {
        runMyTest("testLinkForwardOnkeydown", "");
    }

    public void testLinkForwardOnkeypress() throws Exception {
        runMyTest("testLinkForwardOnkeypress", "");
    }

    public void testLinkForwardOnkeyup() throws Exception {
        runMyTest("testLinkForwardOnkeyup", "");
    }

    public void testLinkForwardOnmousedown() throws Exception {
        runMyTest("testLinkForwardOnmousedown", "");
    }

    public void testLinkForwardOnmousemove() throws Exception {
        runMyTest("testLinkForwardOnmousemove", "");
    }

    public void testLinkForwardOnmouseout() throws Exception {
        runMyTest("testLinkForwardOnmouseout", "");
    }

    public void testLinkForwardOnmouseover() throws Exception {
        runMyTest("testLinkForwardOnmouseover", "");
    }

    public void testLinkForwardOnmouseup() throws Exception {
        runMyTest("testLinkForwardOnmouseup", "");
    }

    public void testLinkForwardParamIdParamNameNoScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameNoScope", "");
    }

    public void testLinkForwardParamIdParamNameParamPropertyNoScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameParamPropertyNoScope", "");
    }

    public void testLinkForwardParamIdParamNameApplicationScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameApplicationScope", "");
    }

    public void testLinkForwardParamIdParamNameParamPropertyApplicationScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testLinkForwardParamIdParamNameParamPropertyApplicationScope",
                "");
    }

    public void testLinkForwardParamIdParamNameSessionScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameSessionScope", "");
    }

    public void testLinkForwardParamIdParamNameParamPropertySessionScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameParamPropertySessionScope",
                "");
    }

    public void testLinkForwardParamIdParamNameRequestScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameRequestScope", "");
    }

    public void testLinkForwardParamIdParamNameParamPropertyRequestScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameParamPropertyRequestScope",
                "");
    }


    public void testLinkForwardStyle() throws Exception {
        runMyTest("testLinkForwardStyle", "");
    }

    public void testLinkForwardStyleClass() throws Exception {
        runMyTest("testLinkForwardStyleClass", "");
    }

    public void testLinkForwardStyleId() throws Exception {
        runMyTest("testLinkForwardStyleId", "");
    }

    public void testLinkForwardTabIndex() throws Exception {
        runMyTest("testLinkForwardTabIndex", "");
    }

    public void testLinkForwardTarget() throws Exception {
        runMyTest("testLinkForwardTarget", "");
    }


    public void testLinkForwardTitle() throws Exception {
        runMyTest("testLinkForwardTitle", "");
    }

    public void testLinkForwardTitleKey() throws Exception {
        runMyTest("testLinkForwardTitleKey", "");
    }

    public void testLinkForwardTitleKeyAlt() throws Exception {
        runMyTest("testLinkForwardTitleKeyAlt", "");
    }

    public void testLinkForwardTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY,
                "Some_Token_Here",
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkForwardTransaction", "");
    }


}
