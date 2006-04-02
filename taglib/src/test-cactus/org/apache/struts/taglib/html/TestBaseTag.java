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
package org.apache.struts.taglib.html;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.struts.taglib.TaglibTestBase;


/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.BaseTag</code>
 * class.
 */
public class TestBaseTag extends TaglibTestBase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestBaseTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestBaseTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestBaseTag.class);
    }

    private void runMyTest(String whichTest) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/html/TestBaseTag.jsp");
    }

    /*
    * Testing BaseTag.
    */
    public void testBase() throws Exception {
        runMyTest("testBase");
    }

    public void testBaseTarget() throws Exception {
        runMyTest("testBaseTarget");
    }

    public void testBaseRef() throws Exception {
        runMyTest("testBaseRef");
    }

    public void testBaseServer() throws Exception {
        runMyTest("testBaseServer");
    }

    public void testBaseServerTarget() throws Exception {
        runMyTest("testBaseServerTarget");
    }

}
