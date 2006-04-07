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
import org.apache.struts.taglib.TaglibTestBase;
import org.apache.struts.util.LabelValueBean;

import javax.servlet.jsp.PageContext;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.bean.DefineTag</code>
 * class.
 */
public class TestDefineTag extends TaglibTestBase {

    protected final static String TEST_KEY = "TestDefineTag";
    protected final static String TEST_VALUE = "Test Value";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestDefineTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestDefineTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestDefineTag.class);
    }

    private void runMyTest(String whichTest) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestDefineTag.jsp");
    }

    private void formatAndTest(String compare, String output) {
        //fix for introduced carriage return / line feeds
        output = replace(output, "\r", "");
        output = replace(output, "\n", "");
        output = output.trim();
        assertEquals(compare, output);
    }

    /*
     * Testing DefineTag using Application scope, with Application Scope specified in tags.
     */
    public void testDefineNameApplicationScope() throws Exception {
        pageContext.setAttribute(TEST_KEY,
                TEST_VALUE,
                PageContext.APPLICATION_SCOPE);
        runMyTest("testDefineNameApplicationScope");
    }

    public void endDefineNameApplicationScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineNamePropertyApplicationScope() throws Exception {
        LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE);
        pageContext
                .setAttribute(TEST_KEY, lvb, PageContext.APPLICATION_SCOPE);
        runMyTest("testDefineNamePropertyApplicationScope");
    }

    public void endDefineNamePropertyApplicationScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineValueApplicationScope() throws Exception {
        LabelValueBean lvb = new LabelValueBean("key",
                TEST_VALUE + " this will be set to the correct value");
        pageContext
                .setAttribute(TEST_KEY, lvb, PageContext.APPLICATION_SCOPE);
        runMyTest("testDefineValueApplicationScope");
    }

    public void endDefineValueApplicationScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineBodyApplicationScope() throws Exception {
        runMyTest("testDefineBodyApplicationScope");
    }

    public void endDefineBodyApplicationScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }


    /*
     * Testing DefineTag using Session scope, with Session Scope specified in tags.
     */
    public void testDefineNameSessionScope() throws Exception {
        pageContext.setAttribute(TEST_KEY,
                TEST_VALUE,
                PageContext.SESSION_SCOPE);
        runMyTest("testDefineNameSessionScope");
    }

    public void endDefineNameSessionScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineNamePropertySessionScope() throws Exception {
        LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE);
        pageContext.setAttribute(TEST_KEY, lvb, PageContext.SESSION_SCOPE);
        runMyTest("testDefineNamePropertySessionScope");
    }

    public void endDefineNamePropertySessionScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineValueSessionScope() throws Exception {
        LabelValueBean lvb = new LabelValueBean("key",
                TEST_VALUE + " this will be set to the correct value");
        pageContext.setAttribute(TEST_KEY, lvb, PageContext.SESSION_SCOPE);
        runMyTest("testDefineValueSessionScope");
    }

    public void endDefineValueSessionScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineBodySessionScope() throws Exception {
        runMyTest("testDefineBodySessionScope");
    }

    public void endDefineBodySessionScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    /*
     * Testing DefineTag using Request scope, with Request Scope specified in tags.
     */
    public void testDefineNameRequestScope() throws Exception {
        pageContext.setAttribute(TEST_KEY,
                TEST_VALUE,
                PageContext.REQUEST_SCOPE);
        runMyTest("testDefineNameRequestScope");
    }

    public void endDefineNameRequestScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineNamePropertyRequestScope() throws Exception {
        LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE);
        pageContext.setAttribute(TEST_KEY, lvb, PageContext.REQUEST_SCOPE);
        runMyTest("testDefineNamePropertyRequestScope");
    }

    public void endDefineNamePropertyRequestScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineValueRequestScope() throws Exception {
        LabelValueBean lvb = new LabelValueBean("key",
                TEST_VALUE + " this will be set to the correct value");
        pageContext.setAttribute(TEST_KEY, lvb, PageContext.REQUEST_SCOPE);
        runMyTest("testDefineValueRequestScope");
    }

    public void endDefineValueRequestScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineBodyRequestScope() throws Exception {
        runMyTest("testDefineBodyRequestScope");
    }

    public void endDefineBodyRequestScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    /*
     * Testing DefineTag using Request scope, with no Scope specified in tags.
     */
    public void testDefineNameNoScope() throws Exception {
        request.setAttribute(TEST_KEY, TEST_VALUE);
        runMyTest("testDefineNameNoScope");
    }

    public void endDefineNameNoScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineNamePropertyNoScope() throws Exception {
        LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE);
        request.setAttribute(TEST_KEY, lvb);
        runMyTest("testDefineNamePropertyNoScope");
    }

    public void endDefineNamePropertyNoScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineValueNoScope() throws Exception {
        LabelValueBean lvb = new LabelValueBean("key",
                TEST_VALUE + " this will be set to the correct value");
        request.setAttribute(TEST_KEY, lvb);
        runMyTest("testDefineValueNoScope");
    }

    public void endDefineValueNoScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }

    public void testDefineBodyNoScope() throws Exception {
        runMyTest("testDefineBodyNoScope");
    }

    public void endDefineBodyNoScope(WebResponse response) {
        formatAndTest(TEST_VALUE, response.getText());
    }


}
