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
public class TestMessageTag extends TaglibTestBase {

    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL = "Testing Message";

    public TestMessageTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMessageTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag.class);
    }

    private void runMyTest(String whichTest, Locale locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                locale,
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestMessageTag.jsp");
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
     * Section: NoArg
     * Locale:  (default)
     * ===========================================================
     */


    public void testMessageTagNoArgKeyNoScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgKeyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgKeyApplicationScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgKeyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgKeySessionScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgKeySessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgKeySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgKeyRequestScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgKeyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTagNoArgKeyNoScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgKeyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgKeyApplicationScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgKeyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgKeySessionScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgKeySessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgKeySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgKeyRequestScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgKeyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTagNoArgNameNoScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgNameNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNameNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNameApplicationScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgNameApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNameApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNameSessionScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgNameSessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNameSessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNameRequestScopeDefaultBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgNameRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNameRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTagNoArgNameNoScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgNameNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNameNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNameApplicationScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgNameApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNameApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNameSessionScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgNameSessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNameSessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNameRequestScopeAlternateBundle()
            throws Exception {
        runMyTest("testMessageTagNoArgNameRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNameRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTagNoArgNamePropertyNoScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertyNoScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNamePropertyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNamePropertyApplicationScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTagNoArgNamePropertyApplicationScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNamePropertyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNamePropertySessionScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertySessionScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNamePropertySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNamePropertyRequestScopeDefaultBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertyRequestScopeDefaultBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNamePropertyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


    public void testMessageTagNoArgNamePropertyNoScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertyNoScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNamePropertyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNamePropertyApplicationScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTagNoArgNamePropertyApplicationScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNamePropertyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNamePropertySessionScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertySessionScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNamePropertySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }

    public void testMessageTagNoArgNamePropertyRequestScopeAlternateBundle()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertyRequestScopeAlternateBundle",
                new Locale("", ""));
    }

    public void endMessageTagNoArgNamePropertyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL, response.getText());
    }


}
