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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.MessagesTag</code>
 * class.
 */
public class TestMessagesTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestMessagesTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMessagesTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessagesTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestMessagesTag1.jsp");
    }

    /*
     * Testing MessagesTag.
     */

    public void testMessages() throws Exception {
        runMyTest("testMessages", "");
    }

    public void testMessagesDefaultBundleEmpty() throws Exception {
        runMyTest("testMessagesDefaultBundleEmpty", "");
    }

    public void testMessagesActionMessageDefaultBundle() throws Exception {
        runMyTest("testMessagesActionMessageDefaultBundle", "");
    }

    public void testMessagesActionMessageDefaultBundleHeader()
            throws Exception {
        runMyTest("testMessagesActionMessageDefaultBundleHeader", "");
    }


    public void testMessagesActionMessageDefaultBundleHeaderFooter()
            throws Exception {
        runMyTest("testMessagesActionMessageDefaultBundleHeaderFooter", "");
    }


    public void testMessagesNameDefaultBundleEmpty() throws Exception {
        runMyTest("testMessagesNameDefaultBundleEmpty", "");
    }

    public void testMessagesNamePropertyDefaultBundleEmpty()
            throws Exception {
        runMyTest("testMessagesNamePropertyDefaultBundleEmpty", "");
    }

    public void testMessagesNameActionMessageDefaultBundle()
            throws Exception {
        runMyTest("testMessagesNameActionMessageDefaultBundle", "");
    }

    public void testMessagesNamePropertyActionMessageDefaultBundle()
            throws Exception {
        runMyTest("testMessagesNamePropertyActionMessageDefaultBundle", "");
    }

    public void testMessagesNameActionMessageDefaultBundleHeader()
            throws Exception {
        runMyTest("testMessagesNameActionMessageDefaultBundleHeader", "");
    }

    public void testMessagesNamePropertyActionMessageDefaultBundleHeader()
            throws Exception {
        runMyTest("testMessagesNamePropertyActionMessageDefaultBundleHeader",
                "");
    }

    public void testMessagesNameActionMessageDefaultBundleHeaderFooter()
            throws Exception {
        runMyTest("testMessagesNameActionMessageDefaultBundleHeaderFooter",
                "");
    }

    public void testMessagesNamePropertyActionMessageDefaultBundleHeaderFooter()
            throws Exception {
        runMyTest(
                "testMessagesNamePropertyActionMessageDefaultBundleHeaderFooter",
                "");
    }


    public void testMessagesAlternateBundleEmpty() throws Exception {
        runMyTest("testMessagesAlternateBundleEmpty", "");
    }

    public void testMessagesActionMessageAlternateBundle() throws Exception {
        runMyTest("testMessagesActionMessageAlternateBundle", "");
    }

    public void testMessagesActionMessageAlternateBundleHeader()
            throws Exception {
        runMyTest("testMessagesActionMessageAlternateBundleHeader", "");
    }


    public void testMessagesActionMessageAlternateBundleHeaderFooter()
            throws Exception {
        runMyTest("testMessagesActionMessageAlternateBundleHeaderFooter", "");
    }

    public void testMessagesNameAlternateBundleEmpty() throws Exception {
        runMyTest("testMessagesNameAlternateBundleEmpty", "");
    }

    public void testMessagesNamePropertyAlternateBundleEmpty()
            throws Exception {
        runMyTest("testMessagesNamePropertyAlternateBundleEmpty", "");
    }

    public void testMessagesNameActionMessageAlternateBundle()
            throws Exception {
        runMyTest("testMessagesNameActionMessageAlternateBundle", "");
    }

    public void testMessagesNamePropertyActionMessageAlternateBundle()
            throws Exception {
        runMyTest("testMessagesNamePropertyActionMessageAlternateBundle", "");
    }

    public void testMessagesNameActionMessageAlternateBundleHeader()
            throws Exception {
        runMyTest("testMessagesNameActionMessageAlternateBundleHeader", "");
    }

    public void testMessagesNamePropertyActionMessageAlternateBundleHeader()
            throws Exception {
        runMyTest("testMessagesNamePropertyActionMessageAlternateBundleHeader",
                "");
    }

    public void testMessagesNameActionMessageAlternateBundleHeaderFooter()
            throws Exception {
        runMyTest("testMessagesNameActionMessageAlternateBundleHeaderFooter",
                "");
    }

    public void testMessagesNamePropertyActionMessageAlternateBundleHeaderFooter()
            throws Exception {
        runMyTest(
                "testMessagesNamePropertyActionMessageAlternateBundleHeaderFooter",
                "");
    }


}
