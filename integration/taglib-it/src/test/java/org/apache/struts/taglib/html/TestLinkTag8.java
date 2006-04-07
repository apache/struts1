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
 * Suite of unit tests for the <code>org.apache.struts.taglib.bean.FrameTag</code>
 * class.
 */
public class TestLinkTag8 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLinkTag8(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestLinkTag8.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLinkTag8.class);
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
                .forward("/org/apache/struts/taglib/html/TestLinkTag8.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testLinkPageOnblur() throws Exception {
        runMyTest("testLinkPageOnblur", "");
    }

    public void testLinkPageOnclick() throws Exception {
        runMyTest("testLinkPageOnclick", "");
    }

    public void testLinkPageOndblclick() throws Exception {
        runMyTest("testLinkPageOndblclick", "");
    }

    public void testLinkPageOnfocus() throws Exception {
        runMyTest("testLinkPageOnfocus", "");
    }

    public void testLinkPageOnkeydown() throws Exception {
        runMyTest("testLinkPageOnkeydown", "");
    }

    public void testLinkPageOnkeypress() throws Exception {
        runMyTest("testLinkPageOnkeypress", "");
    }

    public void testLinkPageOnkeyup() throws Exception {
        runMyTest("testLinkPageOnkeyup", "");
    }

    public void testLinkPageOnmousedown() throws Exception {
        runMyTest("testLinkPageOnmousedown", "");
    }

    public void testLinkPageOnmousemove() throws Exception {
        runMyTest("testLinkPageOnmousemove", "");
    }

    public void testLinkPageOnmouseout() throws Exception {
        runMyTest("testLinkPageOnmouseout", "");
    }

    public void testLinkPageOnmouseover() throws Exception {
        runMyTest("testLinkPageOnmouseover", "");
    }

    public void testLinkPageOnmouseup() throws Exception {
        runMyTest("testLinkPageOnmouseup", "");
    }

    public void testLinkPageParamIdParamNameNoScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkPageParamIdParamNameNoScope", "");
    }

    public void testLinkPageParamIdParamNameParamPropertyNoScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkPageParamIdParamNameParamPropertyNoScope", "");
    }

    public void testLinkPageParamIdParamNameApplicationScope()
            throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkPageParamIdParamNameApplicationScope", "");
    }

    public void testLinkPageParamIdParamNameParamPropertyApplicationScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkPageParamIdParamNameParamPropertyApplicationScope",
                "");
    }

    public void testLinkPageParamIdParamNameSessionScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkPageParamIdParamNameSessionScope", "");
    }

    public void testLinkPageParamIdParamNameParamPropertySessionScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkPageParamIdParamNameParamPropertySessionScope",
                "");
    }

    public void testLinkPageParamIdParamNameRequestScope() throws Exception {
        pageContext.setAttribute("paramName",
                "paramValue",
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkPageParamIdParamNameRequestScope", "");
    }

    public void testLinkPageParamIdParamNameParamPropertyRequestScope()
            throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting("paramPropertyValue");
        pageContext.setAttribute("testingParamProperty",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testLinkPageParamIdParamNameParamPropertyRequestScope",
                "");
    }


    public void testLinkPageStyle() throws Exception {
        runMyTest("testLinkPageStyle", "");
    }

    public void testLinkPageStyleClass() throws Exception {
        runMyTest("testLinkPageStyleClass", "");
    }

    public void testLinkPageStyleId() throws Exception {
        runMyTest("testLinkPageStyleId", "");
    }

    public void testLinkPageTabIndex() throws Exception {
        runMyTest("testLinkPageTabIndex", "");
    }

    public void testLinkPageTarget() throws Exception {
        runMyTest("testLinkPageTarget", "");
    }


    public void testLinkPageTitle() throws Exception {
        runMyTest("testLinkPageTitle", "");
    }

    public void testLinkPageTitleKey() throws Exception {
        runMyTest("testLinkPageTitleKey", "");
    }

    public void testLinkPageTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY,
                "Some_Token_Here",
                PageContext.SESSION_SCOPE);
        runMyTest("testLinkPageTransaction", "");
    }


}
