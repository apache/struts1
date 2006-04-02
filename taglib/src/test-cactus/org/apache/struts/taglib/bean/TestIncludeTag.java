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
 * Suite of unit tests for the <code>org.apache.struts.taglib.bean.IncludeTag</code>
 * class.
 */
public class TestIncludeTag extends TaglibTestBase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestIncludeTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestIncludeTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestIncludeTag.class);
    }

    private void runMyTest(String whichTest) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext.forward(
                request.getRequestURI()
                        + "/org/apache/struts/taglib/bean/TestIncludeTag.jsp");
    }

    /*
     * Testing IncludeTag using the forward attribute.
     */
    public void testIncludeTagForward() throws Exception {
        //@TODO problem with Cactus' request wrapper causing wrong value when request.getServerPort()
//		System.out.println("[TestIncludeTag.java]========>request.getServerPort():" + request.getServerPort());
//		System.out.println("[TestIncludeTag.java]========>request.getRequestURI():" + request.getRequestURI());
//		System.out.println("[TestIncludeTag.java]========>request.getRequestURL():" + request.getRequestURL());
//		System.out.println("[TestIncludeTag.java]========>request.getQueryString():" + request.getQueryString());
//    	runMyTest("testIncludeTagForward");
    }

    /*
     * Testing IncludeTag using the href attribute.
     */
    public void testIncludeTagHref() throws Exception {
        //@TODO problem with Cactus' request wrapper causing wrong value when request.getServerPort()
//    	runMyTest("testIncludeTagHref");
    }

    /*
     * Testing IncludeTag using the page attribute
     */
    public void testIncludeTagPage() throws Exception {
        //@TODO problem with Cactus' request wrapper causing wrong value when request.getServerPort()
//    	runMyTest("testIncludeTagPage");
    }


}
