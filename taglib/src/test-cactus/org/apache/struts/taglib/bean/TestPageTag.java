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

import javax.servlet.jsp.PageContext;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.bean.PageTag</code>
 * class.
 */
public class TestPageTag extends TaglibTestBase {

    protected final static String PAGETAG_KEY = "PAGETAG_KEY";
    protected final static String PAGETAG_VAL = "PAGETAG_VAL";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestPageTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestPageTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestPageTag.class);
    }

    private void runMyTest(String whichTest) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/bean/TestPageTag.jsp");
    }

    private void formatAndTest(String compare, String output) {
        //fix for introduced carriage return / line feeds
        output = replace(output, "\r", "");
        output = replace(output, "\n", "");
        output = output.trim();
        //System.out.println("Testing [" + compare + "] == [" + output + "]");
        assertEquals(compare, output);
    }

    public void testPageTagApplication() throws Exception {
        pageContext.setAttribute(PAGETAG_KEY,
                PAGETAG_VAL,
                PageContext.APPLICATION_SCOPE);
        runMyTest("testPageTagApplication");
    }

    public void endPageTagApplication(WebResponse response) {
        formatAndTest(PAGETAG_VAL, response.getText());
    }

    public void testPageTagSession() throws Exception {
        pageContext.setAttribute(PAGETAG_KEY,
                PAGETAG_VAL,
                PageContext.SESSION_SCOPE);
        runMyTest("testPageTagSession");
    }

    public void endPageTagSession(WebResponse response) {
        formatAndTest(PAGETAG_VAL, response.getText());
    }

    public void testPageTagRequest() throws Exception {
        pageContext.setAttribute(PAGETAG_KEY,
                PAGETAG_VAL,
                PageContext.REQUEST_SCOPE);
        runMyTest("testPageTagRequest");
    }

    public void endPageTagRequest(WebResponse response) {
        formatAndTest(PAGETAG_VAL, response.getText());
    }

    public void testPageTagResponse() throws Exception {
        pageContext.setAttribute(PAGETAG_KEY,
                PAGETAG_VAL,
                PageContext.APPLICATION_SCOPE);
        runMyTest("testPageTagResponse");
    }

    public void endPageTagResponse(WebResponse response) {
        formatAndTest(PAGETAG_VAL, response.getText());
    }

    public void testPageTagConfig() throws Exception {
        config.getServletContext().setAttribute(PAGETAG_KEY, PAGETAG_VAL);
        runMyTest("testPageTagConfig");
    }

    public void endPageTagConfig(WebResponse response) {
        formatAndTest(PAGETAG_VAL, response.getText());
    }


}
