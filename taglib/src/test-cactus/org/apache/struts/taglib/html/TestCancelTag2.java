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

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.CancelTag</code>
 * class.
 */
public class TestCancelTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestCancelTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestCancelTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestCancelTag2.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestCancelTag2.jsp");
    }

    /*
     * Testing CancelTag.
     */

    public void testCancelStyle() throws Exception {
        runMyTest("testCancelStyle", "");
    }

    public void testCancelStyleClass() throws Exception {
        runMyTest("testCancelStyleClass", "");
    }

    public void testCancelStyleId() throws Exception {
        runMyTest("testCancelStyleId", "");
    }

    public void testCancelTabindex() throws Exception {
        runMyTest("testCancelTabindex", "");
    }

    public void testCancelTitle() throws Exception {
        runMyTest("testCancelTitle", "");
    }

    public void testCancelTitleKey() throws Exception {
        runMyTest("testCancelTitleKey", "");
    }

    public void testCancelTitleKey_fr() throws Exception {
        runMyTest("testCancelTitleKey_fr", "fr");
    }

    public void testCancelValue() throws Exception {
        runMyTest("testCancelValue", "");
    }

    public void testCancelBodyContent() throws Exception {
        runMyTest("testCancelBodyContent", "");
    }

    public void testCancelBodyContentMessageKey() throws Exception {
        runMyTest("testCancelBodyContentMessageKey", "");
    }

    public void testCancelBodyContentMessageKey_fr() throws Exception {
        runMyTest("testCancelBodyContentMessageKey_fr", "fr");
    }

}
