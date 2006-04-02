/*
 * $Id$
 *
 * Copyright 1999-2005 The Apache Software Foundation.
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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.HtmlTag</code>
 * class.
 */
public class TestHtmlTag extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestHtmlTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestHtmlTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestHtmlTag.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/html/TestHtmlTag.jsp");
    }

    /*
     * Testing HtmlTag.
     */
    public void testHtml() throws Exception {
        runMyTest("testHtml", "");
    }

    public void testHtmlXhtml1() throws Exception {
        runMyTest("testHtmlXhtml1", "");
    }

    public void testHtmlXhtml2() throws Exception {
        runMyTest("testHtmlXhtml2", "");
    }

    public void testHtmlXhtml3() throws Exception {
        runMyTest("testHtmlXhtml3", "");
    }

    public void testHtmlXhtml4() throws Exception {
        runMyTest("testHtmlXhtml4", "");
    }

    public void testHtmlXhtml5() throws Exception {
        runMyTest("testHtmlXhtml5", "");
    }

    public void testHtmlXhtml6() throws Exception {
        runMyTest("testHtmlXhtml6", "");
    }

}
