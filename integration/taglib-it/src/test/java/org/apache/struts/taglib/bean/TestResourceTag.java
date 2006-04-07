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

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.bean.ResourceTag</code>
 * class.
 */
public class TestResourceTag extends TaglibTestBase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestResourceTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestResourceTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestResourceTag.class);
    }


    private void formatAndTest(String compare, String output) {
        //fix for introduced carriage return / line feeds
        output = replace(output, "\r", "");
        output = replace(output, "\n", "");
        output = output.trim();
        //System.out.println("Testing [" + compare + "] == [" + output + "]");
        assertEquals(compare, output);
    }

    public void testResourceTag() throws IOException, ServletException {
        request.setAttribute("runTest", "testResourceTag");
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestResourceTag.jsp");
    }

    public void endResourceTag(WebResponse response) {
        formatAndTest("Test Value", response.getText());
    }

    public void testResourceTagInput() throws IOException, ServletException {
        request.setAttribute("runTest", "testResourceTagInput");
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestResourceTag.jsp");
    }

    public void endResourceTagInput(WebResponse response) {
        formatAndTest("Test Value", response.getText());
    }


}
