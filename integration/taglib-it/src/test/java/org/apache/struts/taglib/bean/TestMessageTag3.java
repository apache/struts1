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
public class TestMessageTag3 extends TaglibTestBase {

    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL = "Testing Message 1 2 3";

    public TestMessageTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMessageTag3.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag3.class);
    }

    private void runMyTest(String whichTest, Locale locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                locale,
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestMessageTag3.jsp");
    }

    private void formatAndTest(String compare, String output) {
        //fix for introduced carriage return / line feeds
        output = replace(output, "\r", "");
        output = replace(output, "\r", "");
        output = output.trim();
        //System.out.println("Testing [" + compare + "] == [" + output + "]");
        assertEquals(compare, output);
    }

    /*
     * ===========================================================
     * Testing MessageTag (these comments serve as a divider of
     *                     functionality being tested)
     *
     * Section: 3 Arg
     * Locale:  (default)
     * ===========================================================
     */


    public void testMessageTag3ArgKeyNoScopeDefaultBundle() throws Exception {
        runMyTest("testMessageTag3ArgKeyNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgKeyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgKeyApplicationScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgKeyApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgKeyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgKeySessionScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgKeySessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgKeySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgKeyRequestScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgKeyRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgKeyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag3ArgKeyNoScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgKeyNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgKeyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgKeyApplicationScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgKeyApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgKeyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgKeySessionScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgKeySessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgKeySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgKeyRequestScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgKeyRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgKeyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag3ArgNameNoScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgNameNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNameNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNameApplicationScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgNameApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNameApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNameSessionScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgNameSessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNameSessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNameRequestScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgNameRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNameRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag3ArgNameNoScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgNameNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNameNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNameApplicationScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgNameApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNameApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNameSessionScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgNameSessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNameSessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNameRequestScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag3ArgNameRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNameRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag3ArgNamePropertyNoScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.3"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag3ArgNamePropertyNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNamePropertyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNamePropertyApplicationScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.3"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTag3ArgNamePropertyApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNamePropertyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNamePropertySessionScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.3"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTag3ArgNamePropertySessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNamePropertySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNamePropertyRequestScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.3"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag3ArgNamePropertyRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNamePropertyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag3ArgNamePropertyNoScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.3"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag3ArgNamePropertyNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNamePropertyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNamePropertyApplicationScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.3"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTag3ArgNamePropertyApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNamePropertyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNamePropertySessionScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.3"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTag3ArgNamePropertySessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNamePropertySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag3ArgNamePropertyRequestScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.3"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag3ArgNamePropertyRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag3ArgNamePropertyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


}
