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
 * All of these tests depend on a value being correctly written on the
 * response, then checked here in endXXX method.
 */
public class TestMessageTag_fr extends TaglibTestBase {

    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL_FR = "Message D'Essai";

    public TestMessageTag_fr(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMessageTag_fr.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag_fr.class);
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
    public void testMessageTagNoArgKeyNoScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyNoScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgKeyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgKeyApplicationScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyApplicationScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgKeyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgKeySessionScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgKeySessionScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgKeySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgKeyRequestScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyRequestScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgKeyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTagNoArgKeyNoScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyNoScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgKeyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgKeyApplicationScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyApplicationScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgKeyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgKeySessionScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgKeySessionScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgKeySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgKeyRequestScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgKeyRequestScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgKeyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTagNoArgNameNoScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgNameNoScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNameNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNameApplicationScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgNameApplicationScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNameApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNameSessionScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgNameSessionScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNameSessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNameRequestScopeDefaultBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgNameRequestScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNameRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTagNoArgNameNoScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgNameNoScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNameNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNameApplicationScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgNameApplicationScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNameApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNameSessionScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgNameSessionScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNameSessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNameRequestScopeAlternateBundle_fr()
            throws Exception {
        runMyTest("testMessageTagNoArgNameRequestScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNameRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTagNoArgNamePropertyNoScopeDefaultBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertyNoScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNamePropertyNoScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNamePropertyApplicationScopeDefaultBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTagNoArgNamePropertyApplicationScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNamePropertyApplicationScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNamePropertySessionScopeDefaultBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertySessionScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNamePropertySessionScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNamePropertyRequestScopeDefaultBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("default.bundle.message"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertyRequestScopeDefaultBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNamePropertyRequestScopeDefaultBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


    public void testMessageTagNoArgNamePropertyNoScopeAlternateBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertyNoScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNamePropertyNoScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNamePropertyApplicationScopeAlternateBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message"),
                PageContext.APPLICATION_SCOPE);
        runMyTest(
                "testMessageTagNoArgNamePropertyApplicationScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNamePropertyApplicationScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNamePropertySessionScopeAlternateBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message"),
                PageContext.SESSION_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertySessionScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNamePropertySessionScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }

    public void testMessageTagNoArgNamePropertyRequestScopeAlternateBundle_fr()
            throws Exception {
        pageContext.setAttribute("key",
                new SimpleBeanForTesting("alternate.bundle.message"),
                PageContext.REQUEST_SCOPE);
        runMyTest("testMessageTagNoArgNamePropertyRequestScopeAlternateBundle",
                new Locale("fr", "fr"));
    }

    public void endMessageTagNoArgNamePropertyRequestScopeAlternateBundle(
            WebResponse response) {
        formatAndTest(TEST_VAL_FR, response.getText());
    }


}
