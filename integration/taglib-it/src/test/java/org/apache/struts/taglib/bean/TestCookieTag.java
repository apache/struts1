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
import org.apache.struts.taglib.TaglibTestBase;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.bean.CookieTag</code>
 * class.
 */
public class TestCookieTag extends TaglibTestBase {

    protected final static String COOKIE_KEY = "COOKIE_KEY";
    protected final static String COOKIE_VAL = "COOKIE_VAL";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestCookieTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestCookieTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestCookieTag.class);
    }


    private void runMyTest(String whichTest) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestCookieTag.jsp");
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
    * Testing CookieTag, with Name specified in tags.
    */
    /* FIXME: Cactus does not send cookies?
    public void beginCookieTagName(WebRequest webRequest) {
                webRequest.addCookie(COOKIE_KEY, COOKIE_VAL);
        webRequest.addParameter("cacheId", "1");
    }
    public void testCookieTagName() throws Exception {
        runMyTest("testCookieTagName");
    }
    public void endCookieTagName(WebResponse response){
        formatAndTest(COOKIE_VAL, response.getText());
    }
    */

    /*
    * Testing CookieTag, with Name and Multiple specified in tags.
    */
    /* FIXME: Cactus does not send cookies?
    public void beginCookieTagNameMultiple(WebRequest webRequest) {
        for (int i = 0; i < 10; i++) {
            webRequest.addCookie(COOKIE_KEY, COOKIE_VAL + i);
        }
        webRequest.addParameter("cacheId", "1");
    }
    public void testCookieTagNameMultiple() throws Exception {
        runMyTest("testCookieTagNameMultiple");
    }
    public void endCookieTagNameMultiple(WebResponse response){
        String compareTo = "";
        for (int i = 0; i < 10; i++) {
            compareTo += COOKIE_VAL + i;
        }
        formatAndTest(compareTo, response.getText());
    }
    */

    /*
     * Testing CookieTag, with Name and Value specified in tags.
     */
    /* FIXME: Cactus does not send cookies?
    public void beginCookieTagNameValue(WebRequest webRequest) {
        webRequest.addParameter("cacheId", "1");
    }
    public void testCookieTagNameValue() throws Exception {
        runMyTest("testCookieTagNameValue");
    }
    public void endCookieTagNameValue(WebResponse response){
        formatAndTest(COOKIE_VAL, response.getText());
    }
    */

    /* FIXME: dummy test since all others commented out */
    public void testDummy() {
    }

}
