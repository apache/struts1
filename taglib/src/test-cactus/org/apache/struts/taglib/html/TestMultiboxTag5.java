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
import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.MultiboxTag</code>
 * class. NOTE - These tests were separated into 4 files each because of the
 * size of the jsp. (not playing well with Tomcat 3.3
 */
public class TestMultiboxTag5 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestMultiboxTag5(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMultiboxTag5.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMultiboxTag5.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);

        String[] s = new String[7];
        for (int i = 1; i < 7; i++) {
            s[i] = "value" + i;
        }
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(s);

        pageContext.setAttribute(Constants.BEAN_KEY,
                sbft,
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestMultiboxTag5.jsp");
    }

    /*
     * Testing MultiboxTag.
     */
    public void testMultiboxPropertyStyle() throws Exception {
        runMyTest("testMultiboxPropertyStyle", "");
    }

    public void testMultiboxPropertyErrorStyle() throws Exception {
        runMyTest("testMultiboxPropertyErrorStyle", "");
    }

    public void testMultiboxPropertyStyleClass() throws Exception {
        runMyTest("testMultiboxPropertyStyleClass", "");
    }

    public void testMultiboxPropertyErrorStyleClass() throws Exception {
        runMyTest("testMultiboxPropertyErrorStyleClass", "");
    }

    public void testMultiboxPropertyStyleId() throws Exception {
        runMyTest("testMultiboxPropertyStyleId", "");
    }

    public void testMultiboxPropertyErrorStyleId() throws Exception {
        runMyTest("testMultiboxPropertyErrorStyleId", "");
    }

}
