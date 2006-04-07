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

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.bean.ParameterTag</code>
 * class.
 */
public class TestParameterTag extends TaglibTestBase {

    protected final static String PARAMETERTAG_KEY = "PARAMETERTAG_KEY";
    protected final static String PARAMETERTAG_VAL = "PARAMETERTAG_VAL";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestParameterTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestParameterTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestParameterTag.class);
    }


    private void formatAndTest(String compare, String output) {
        //fix for introduced carriage return / line feeds
        output = replace(output, "\r", "");
        output = replace(output, "\n", "");
        output = output.trim();
        //System.out.println("Testing [" + compare + "] == [" + output + "]");
        assertEquals(compare, output);
    }

    public void beginParameterTag(WebRequest webRequest) {
        webRequest.addParameter("testParam", "Test Value");
    }

    public void testParameterTag() throws IOException, ServletException {
        request.setAttribute("runTest", "testParameterTag");
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestParameterTag.jsp");
    }

    public void endParameterTag(WebResponse response) {
        formatAndTest("Test Value", response.getText());
    }

    public void testParameterTagValue() throws IOException, ServletException {
        request.setAttribute("runTest", "testParameterTagValue");
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestParameterTag.jsp");
    }

    public void endParameterTagValue(WebResponse response) {
        formatAndTest("Test Value", response.getText());
    }


}
