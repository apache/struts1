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
public class TestMessageTag1 extends TaglibTestBase {

    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL = "Testing Message 1";

    public TestMessageTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMessageTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag1.class);
    }


    private void formatAndTest(String compare, String output) {
        //fix for introduced carriage return / line feeds
        output = replace(output, "\r", "");
        output = replace(output, "\n", "");
        output = output.trim();
        //System.out.println("Testing [" + compare + "] == [" + output + "]");
        assertEquals(compare, output);
    }

    private void runMyTest(String whichTest, Locale locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                locale,
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestMessageTag1.jsp");
    }
    /*
     * ===========================================================
     * Testing MessageTag (these comments serve as a divider of
     *                     functionality being tested)
     *
     * Section: 1 Arg
     * Locale:  (default)
     * ===========================================================
     */


    public void testMessageTag1ArgKeyNoScopeDefaultBundle() throws Exception {
        runMyTest("testMessageTag1ArgKeyNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgKeyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgKeyApplicationScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgKeyApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgKeyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgKeySessionScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgKeySessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgKeySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgKeyRequestScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgKeyRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgKeyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag1ArgKeyNoScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgKeyNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgKeyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgKeyApplicationScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgKeyApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgKeyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgKeySessionScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgKeySessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgKeySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgKeyRequestScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgKeyRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgKeyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag1ArgNameNoScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgNameNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNameNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNameApplicationScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgNameApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNameApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNameSessionScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgNameSessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNameSessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNameRequestScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgNameRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNameRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag1ArgNameNoScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgNameNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNameNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNameApplicationScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgNameApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNameApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNameSessionScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgNameSessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNameSessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNameRequestScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTag1ArgNameRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNameRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag1ArgNamePropertyNoScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.1"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag1ArgNamePropertyNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNamePropertyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNamePropertyApplicationScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.1"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTag1ArgNamePropertyApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNamePropertyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNamePropertySessionScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.1"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTag1ArgNamePropertySessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNamePropertySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNamePropertyRequestScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.1"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag1ArgNamePropertyRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNamePropertyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTag1ArgNamePropertyNoScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.1"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag1ArgNamePropertyNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNamePropertyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNamePropertyApplicationScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.1"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTag1ArgNamePropertyApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNamePropertyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNamePropertySessionScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.1"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTag1ArgNamePropertySessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNamePropertySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTag1ArgNamePropertyRequestScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.1"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag1ArgNamePropertyRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTag1ArgNamePropertyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


}
