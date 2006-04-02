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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.MultiboxTag</code>
 * class. NOTE - These tests were separated into 4 files each because of the
 * size of the jsp. (not playing well with Tomcat 3.3
 *
 * These tests are numbered as such:
 *
 * 1 thru 4 test a single checkbox TestOptionTag1 - These test validate true
 * (a value was in the array) on our form. TestMultiboxTag2 - Same as 1, but
 * using BodyContent instead of value attribute
 *
 * TestMultiboxTag3 - These test validate true (a value was in the array) on
 * our form. TestMultiboxTag4 - Same as 3, but using BodyContent instead of
 * value attribute
 *
 * 5 thru 8 test multiple checkboxes TestMultiboxTag5 - These test validate
 * true (a value was in the array) on our form. TestMultiboxTag6 - Same as 5,
 * but using BodyContent instead of value attribute
 *
 * TestMultiboxTag7 - These test validate true (a value was in the array) on
 * our form. TestMultiboxTag8 - Same as 7, but using BodyContent instead of
 * value attribute
 */
public class TestOptionTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestOptionTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestOptionTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestOptionTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);

        SimpleBeanForTesting sbft = new SimpleBeanForTesting("SelectMe");

        pageContext.setAttribute(Constants.BEAN_KEY,
                sbft,
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestOptionTag1.jsp");
    }

    /*
     * Testing MultiboxTag.
     */
    public void testOptionBodySelected() throws Exception {
        runMyTest("testOptionBodySelected", "");
    }

    public void testOptionBodyNotSelected() throws Exception {
        runMyTest("testOptionBodyNotSelected", "");
    }

    public void testOptionBodySelectedDisabled_true() throws Exception {
        runMyTest("testOptionBodySelectedDisabled_true", "");
    }

    public void testOptionBodyNotSelectedDisabled_true() throws Exception {
        runMyTest("testOptionBodyNotSelectedDisabled_true", "");
    }

    public void testOptionBodySelectedDisabled_false() throws Exception {
        runMyTest("testOptionBodySelectedDisabled_false", "");
    }

    public void testOptionBodyNotSelectedDisabled_false() throws Exception {
        runMyTest("testOptionBodyNotSelectedDisabled_false", "");
    }

    public void testOptionBodySelectedDisabled_other() throws Exception {
        runMyTest("testOptionBodySelectedDisabled_other", "");
    }

    public void testOptionBodyNotSelectedDisabled_other() throws Exception {
        runMyTest("testOptionBodyNotSelectedDisabled_other", "");
    }

    public void testOptionKeySelected() throws Exception {
        runMyTest("testOptionKeySelected", "");
    }

    public void testOptionKeyNotSelected() throws Exception {
        runMyTest("testOptionKeyNotSelected", "");
    }

    public void testOptionKeySelectedAlternateBundle() throws Exception {
        runMyTest("testOptionKeySelectedAlternateBundle", "");
    }

    public void testOptionKeyNotSelectedAlternateBundle() throws Exception {
        runMyTest("testOptionKeyNotSelectedAlternateBundle", "");
    }

    public void testOptionKeySelectedLocale_fr() throws Exception {
        runMyTest("testOptionKeySelectedLocale_fr", "fr");
    }

    public void testOptionKeyNotSelectedLocale_fr() throws Exception {
        runMyTest("testOptionKeyNotSelectedLocale_fr", "fr");
    }

    public void testOptionKeySelectedAlternateBundleLocale_fr()
            throws Exception {
        runMyTest("testOptionKeySelectedAlternateBundleLocale_fr", "fr");
    }

    public void testOptionKeyNotSelectedAlternateBundleLocale_fr()
            throws Exception {
        runMyTest("testOptionKeyNotSelectedAlternateBundleLocale_fr", "fr");
    }

}
