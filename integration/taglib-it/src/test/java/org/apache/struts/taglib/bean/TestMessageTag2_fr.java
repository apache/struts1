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
public class TestMessageTag2_fr extends TaglibTestBase {

    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL_FR = "Message D'Essai 1 2";

    public TestMessageTag2_fr(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMessageTag2_fr.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag2_fr.class);
    }

    private void runMyTest(String whichTest, Locale locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                locale,
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestMessageTag2.jsp");
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
     * Section: 2 Args
     * Locale:  (default)
     * ===========================================================
     */


    public void testMessageTag2ArgKeyNoScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgKeyNoScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgKeyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgKeyApplicationScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgKeyApplicationScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgKeyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgKeySessionScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgKeySessionScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgKeySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgKeyRequestScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgKeyRequestScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgKeyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTag2ArgKeyNoScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgKeyNoScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgKeyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgKeyApplicationScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgKeyApplicationScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgKeyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgKeySessionScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgKeySessionScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgKeySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgKeyRequestScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgKeyRequestScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgKeyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTag2ArgNameNoScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgNameNoScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNameNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNameApplicationScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgNameApplicationScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNameApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNameSessionScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgNameSessionScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNameSessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNameRequestScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgNameRequestScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNameRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTag2ArgNameNoScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgNameNoScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNameNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNameApplicationScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgNameApplicationScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNameApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNameSessionScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgNameSessionScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNameSessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNameRequestScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTag2ArgNameRequestScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNameRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTag2ArgNamePropertyNoScopeDefaultBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.2"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag2ArgNamePropertyNoScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNamePropertyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNamePropertyApplicationScopeDefaultBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.2"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTag2ArgNamePropertyApplicationScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNamePropertyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNamePropertySessionScopeDefaultBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.2"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTag2ArgNamePropertySessionScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNamePropertySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNamePropertyRequestScopeDefaultBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message.2"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag2ArgNamePropertyRequestScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNamePropertyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTag2ArgNamePropertyNoScopeAlternateBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.2"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag2ArgNamePropertyNoScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNamePropertyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNamePropertyApplicationScopeAlternateBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.2"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTag2ArgNamePropertyApplicationScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNamePropertyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNamePropertySessionScopeAlternateBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.2"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTag2ArgNamePropertySessionScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNamePropertySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTag2ArgNamePropertyRequestScopeAlternateBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message.2"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTag2ArgNamePropertyRequestScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTag2ArgNamePropertyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


}
