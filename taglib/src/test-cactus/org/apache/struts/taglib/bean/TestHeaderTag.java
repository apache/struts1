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
import org.apache.cactus.WebRequest;
import org.apache.cactus.WebResponse;
import org.apache.struts.taglib.TaglibTestBase;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.bean.HeaderTag</code>
 * class.
 */
public class TestHeaderTag extends TaglibTestBase {

    protected final static String HEADER_KEY = "HEADER_KEY";
    protected final static String HEADER_VAL = "HEADER_VAL";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestHeaderTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestHeaderTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestHeaderTag.class);
    }

    private void runMyTest(String whichTest) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestHeaderTag.jsp");
    }

    private void formatAndTest(String compare, String output) {
        //fix for introduced carriage return / line feeds
        output = replace(output, "\r", "");
        output = replace(output, "\n", "");
        output = output.trim();
        assertEquals(compare, output);
    }


    /*
     * Testing HeaderTag, with Name specified in tags.
     */
    public void beginHeaderTagName(WebRequest webRequest) {
        webRequest.addHeader(HEADER_KEY, HEADER_VAL);
        webRequest.addParameter("cacheId", "1");
    }

    public void testHeaderTagName() throws Exception {
        runMyTest("testHeaderTagName");
    }

    public void endHeaderTagName(WebResponse response) {
        formatAndTest(HEADER_VAL, response.getText());
    }


    /*
     * Testing HeaderTag, with Name and Multiple specified in tags.
     */
    public void beginHeaderTagNameMultiple(WebRequest webRequest) {
        for (int i = 0; i < 10; i++) {
            webRequest.addHeader(HEADER_KEY, HEADER_VAL + i);
        }
        webRequest.addParameter("cacheId", "1");
    }

    public void testHeaderTagNameMultiple() throws Exception {
        runMyTest("testHeaderTagNameMultiple");
    }

    public void endHeaderTagNameMultiple(WebResponse response) {
        String compareTo = "";
        //Multiple Headers are comma delimited
        for (int i = 0; i < 10; i++) {
            compareTo += HEADER_VAL + i + ",";
        }
        //remove the trailing comma
        compareTo = compareTo.substring(0, compareTo.length() - 1);
        formatAndTest(compareTo, response.getText());
    }

    /*
     * Testing HeaderTag, with Name and Value specified in tags.
     */
    public void beginHeaderTagNameValue(WebRequest webRequest) {
        webRequest.addParameter("cacheId", "1");
    }

    public void testHeaderTagNameValue() throws Exception {
        runMyTest("testHeaderTagNameValue");
    }

    public void endHeaderTagNameValue(WebResponse response) {
        formatAndTest(HEADER_VAL, response.getText());
    }


}
