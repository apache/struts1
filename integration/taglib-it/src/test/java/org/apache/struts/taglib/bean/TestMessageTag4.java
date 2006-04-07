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
package org.apache.struts.taglib.bean;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.cactus.WebResponse;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;
import org.apache.struts.taglib.TaglibTestBase;

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * These tests attempt to cover every single possible configuration of the
 * org.apache.struts.taglib.bean.MessageTag
 *
 * I've tried to describe what I'm testing as best as possible by the method
 * names. To see how I'm testing, refer to the jsp file that these tests
 * forward to.
 *
 * All of these tests depend on a value being correctly written on the repose,
 * then checked here in endXXX method.
 */
public class TestMessageTag4 extends TaglibTestBase {

    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL = "Testing Message 1 2 3 4";

    public TestMessageTag4(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMessageTag4.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag4.class);
    }

    private void runMyTest(String whichTest, Locale locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                locale,
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestMessageTag4.jsp");
    }

    private void formatAndTest(String compare, String output) {
        //fix for introduced carriage return / line feeds
        output = replace(output, "\r", "");
        output = replace(output, "\n", "");
        output = output.trim();
        //System.out.println("Testing [" + compare + "] == [" + output + "]");
        assertEquals(compare, output);
    }

    /*
     * ===========================================================
     * Testing MessageTag (these comments serve as a divider of
     *                     functionality being tested)
     *
     * Section: 4 Args
     * Locale:  (default)
     * ===========================================================
     */


    public void testMessageTag4ArgKeyNoScopeDefaultBundle() throws Exception {
        runMyTest("testMessageTag4ArgKeyNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgKeyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgKeyApplicationScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgKeyApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgKeyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgKeySessionScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgKeySessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgKeySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgKeyRequestScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgKeyRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgKeyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag4ArgKeyNoScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgKeyNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgKeyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgKeyApplicationScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgKeyApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgKeyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgKeySessionScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgKeySessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgKeySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgKeyRequestScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgKeyRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgKeyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag4ArgNameNoScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgNameNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNameNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNameApplicationScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgNameApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNameApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNameSessionScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgNameSessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNameSessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNameRequestScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgNameRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNameRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag4ArgNameNoScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgNameNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNameNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNameApplicationScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgNameApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNameApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNameSessionScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgNameSessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNameSessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNameRequestScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag4ArgNameRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNameRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag4ArgNamePropertyNoScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.4"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag4ArgNamePropertyNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNamePropertyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNamePropertyApplicationScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.4"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTag4ArgNamePropertyApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNamePropertyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNamePropertySessionScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.4"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTag4ArgNamePropertySessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNamePropertySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNamePropertyRequestScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.4"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag4ArgNamePropertyRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNamePropertyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag4ArgNamePropertyNoScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.4"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag4ArgNamePropertyNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNamePropertyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNamePropertyApplicationScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.4"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTag4ArgNamePropertyApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNamePropertyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNamePropertySessionScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.4"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTag4ArgNamePropertySessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNamePropertySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag4ArgNamePropertyRequestScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.4"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag4ArgNamePropertyRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag4ArgNamePropertyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


}
