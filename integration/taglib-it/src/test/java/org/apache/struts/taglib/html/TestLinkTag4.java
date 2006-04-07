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
public class TestLinkTag4 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLinkTag4(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestLinkTag4.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLinkTag4.class);
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
                .forward("/org/apache/struts/taglib/html/TestLinkTag4.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testLinkActionOnblur() throws Exception {
        runMyTest("testLinkActionOnblur", "");
    }

    public void testLinkActionOnclick() throws Exception {
        runMyTest("testLinkActionOnclick", "");
    }

    public void testLinkActionOndblclick() throws Exception {
        runMyTest("testLinkActionOndblclick", "");
    }

    public void testLinkActionOnfocus() throws Exception {
        runMyTest("testLinkActionOnfocus", "");
    }

    public void testLinkActionOnkeydown() throws Exception {
        runMyTest("testLinkActionOnkeydown", "");
    }

    public void testLinkActionOnkeypress() throws Exception {
        runMyTest("testLinkActionOnkeypress", "");
    }

    public void testLinkActionOnkeyup() throws Exception {
        runMyTest("testLinkActionOnkeyup", "");
    }

    public void testLinkActionOnmousedown() throws Exception {
        runMyTest("testLinkActionOnmousedown", "");
    }

    public void testLinkActionOnmousemove() throws Exception {
        runMyTest("testLinkActionOnmousemove", "");
    }

    public void testLinkActionOnmouseout() throws Exception {
        runMyTest("testLinkActionOnmouseout", "");
    }

    public void testLinkActionOnmouseover() throws Exception {
        runMyTest("testLinkActionOnmouseover", "");
    }

    public void testLinkActionOnmouseup() throws Exception {
        runMyTest("testLinkActionOnmouseup", "");
    }

    public void testLinkActionParamIdParamNameNoScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionParamIdParamNameNoScope", "");
    }

    public void testLinkActionParamIdParamNameParamPropertyNoScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionParamIdParamNameParamPropertyNoScope", "");
    }

    public void testLinkActionParamIdParamNameApplicationScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkActionParamIdParamNameApplicationScope", "");
    }

    public void testLinkActionParamIdParamNameParamPropertyApplicationScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testLinkActionParamIdParamNameParamPropertyApplicationScope",
                "");
    }

    public void testLinkActionParamIdParamNameSessionScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkActionParamIdParamNameSessionScope", "");
    }

    public void testLinkActionParamIdParamNameParamPropertySessionScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkActionParamIdParamNameParamPropertySessionScope",
                "");
    }

    public void testLinkActionParamIdParamNameRequestScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionParamIdParamNameRequestScope", "");
    }

    public void testLinkActionParamIdParamNameParamPropertyRequestScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionParamIdParamNameParamPropertyRequestScope",
                "");
    }


    public void testLinkActionStyle() throws Exception {
        runMyTest("testLinkActionStyle", "");
    }

    public void testLinkActionStyleClass() throws Exception {
        runMyTest("testLinkActionStyleClass", "");
    }

    public void testLinkActionStyleId() throws Exception {
        runMyTest("testLinkActionStyleId", "");
    }

    public void testLinkActionTabIndex() throws Exception {
        runMyTest("testLinkActionTabIndex", "");
    }

    public void testLinkActionTarget() throws Exception {
        runMyTest("testLinkActionTarget", "");
    }


    public void testLinkActionTitle() throws Exception {
        runMyTest("testLinkActionTitle", "");
    }

    public void testLinkActionTitleKey() throws Exception {
        runMyTest("testLinkActionTitleKey", "");
    }

    public void testLinkActionTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY,
                "Some_Token_Here",
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkActionTransaction", "");
    }


}

