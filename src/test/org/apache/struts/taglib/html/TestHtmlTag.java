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

import java.util.Locale;

import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.html.HtmlTag</code> class.
 *
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
        junit.awtui.TestRunner.main(new String[] {TestHtmlTag.class.getName()});
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
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestHtmlTag.jsp");
    }

    /*
     * Testing HtmlTag.
     */
    public void testHtml() throws Exception {
        runMyTest("testHtml", "");
        }

    public void testHtmlLocale1() throws Exception {
        runMyTest("testHtmlLocale1", "");
        }

    public void testHtmlLocale2() throws Exception {
        runMyTest("testHtmlLocale2", "");
        }

    public void testHtmlLocale3() throws Exception {
        runMyTest("testHtmlLocale3", "");
        }

    public void testHtmlLocale4() throws Exception {
        runMyTest("testHtmlLocale4", "");
        }

    public void testHtmlLocale5() throws Exception {
        runMyTest("testHtmlLocale5", "");
        }

    public void testHtmlLocale6() throws Exception {
        runMyTest("testHtmlLocale6", "");
        }

    public void testHtmlLocale_fr1() throws Exception {
        runMyTest("testHtmlLocale_fr1", "fr");
        }

    public void testHtmlLocale_fr2() throws Exception {
        runMyTest("testHtmlLocale_fr2", "fr");
        }

    public void testHtmlLocale_fr3() throws Exception {
        runMyTest("testHtmlLocale_fr3", "fr");
        }

    public void testHtmlLocale_fr4() throws Exception {
        runMyTest("testHtmlLocale_fr4", "fr");
        }

    public void testHtmlLocale_fr5() throws Exception {
        runMyTest("testHtmlLocale_fr5", "fr");
        }

    public void testHtmlLocale_fr6() throws Exception {
        runMyTest("testHtmlLocale_fr6", "fr");
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

    public void testHtmlLocaleXhtml1() throws Exception {
        runMyTest("testHtmlLocaleXhtml1", "");
        }

    public void testHtmlLocaleXhtml2() throws Exception {
        runMyTest("testHtmlLocaleXhtml2", "");
        }

    public void testHtmlLocaleXhtml3() throws Exception {
        runMyTest("testHtmlLocaleXhtml3", "");
        }

    public void testHtmlLocaleXhtml_fr1() throws Exception {
        runMyTest("testHtmlLocaleXhtml_fr1", "fr");
        }

    public void testHtmlLocaleXhtml_fr2() throws Exception {
        runMyTest("testHtmlLocaleXhtml_fr2", "fr");
        }

    public void testHtmlLocaleXhtml_fr3() throws Exception {
        runMyTest("testHtmlLocaleXhtml_fr3", "fr");
        }


}
