/*
 * $Id$ 
 *
 * Copyright 2004 The Apache Software Foundation.
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

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.ResetTag</code>
 * class.
 */
public class TestResetTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestResetTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestResetTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestResetTag2.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestResetTag2.jsp");
    }

    /*
     * Testing ResetTag.
     */

    public void testResetPropertyStyle() throws Exception {
        runMyTest("testResetPropertyStyle", "");
    }

    public void testResetPropertyStyleClass() throws Exception {
        runMyTest("testResetPropertyStyleClass", "");
    }

    public void testResetPropertyStyleId() throws Exception {
        runMyTest("testResetPropertyStyleId", "");
    }

    public void testResetPropertyTabindex() throws Exception {
        runMyTest("testResetPropertyTabindex", "");
    }

    public void testResetPropertyTitle() throws Exception {
        runMyTest("testResetPropertyTitle", "");
    }

    public void testResetPropertyTitleKey() throws Exception {
        runMyTest("testResetPropertyTitleKey", "");
    }

    public void testResetPropertyTitleKey_fr() throws Exception {
        runMyTest("testResetPropertyTitleKey_fr", "fr");
    }

    public void testResetPropertyValue() throws Exception {
        runMyTest("testResetPropertyValue", "");
    }

    public void testResetPropertyBodyContent() throws Exception {
        runMyTest("testResetPropertyBodyContent", "");
    }

    public void testResetPropertyBodyContentMessageKey() throws Exception {
        runMyTest("testResetPropertyBodyContentMessageKey", "");
    }

    public void testResetPropertyBodyContentMessageKey_fr() throws Exception {
        runMyTest("testResetPropertyBodyContentMessageKey_fr", "fr");
    }

}
