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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.CheckboxTag</code>
 * class.
 */
public class TestCheckboxTag3 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestCheckboxTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestCheckboxTag3.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestCheckboxTag3.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting(false),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestCheckboxTag3.jsp");
    }

    /*
     * Testing CheckboxTag.
     */
    public void testCheckboxPropertybooleanFalse() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalse", "");
    }

    public void testCheckboxPropertybooleanFalseAccesskey() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAccesskey", "");
    }

    public void testCheckboxPropertybooleanFalseAlt() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAlt", "");
    }

    public void testCheckboxPropertybooleanFalseAltKey1() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAltKey1", "");
    }

    public void testCheckboxPropertybooleanFalseAltKey2() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAltKey2", "");
    }

    public void testCheckboxPropertybooleanFalseAltKey_fr1()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAltKey1_fr", "fr");
    }

    public void testCheckboxPropertybooleanFalseAltKey_fr2()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAltKey2_fr", "fr");
    }

    public void testCheckboxPropertybooleanFalseDisabled_True()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseDisabled_True", "");
    }

    public void testCheckboxPropertybooleanFalseDisabled_False1()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseDisabled_False1", "");
    }

    public void testCheckboxPropertybooleanFalseDisabled_False2()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseDisabled_False2", "");
    }

    public void testCheckboxPropertybooleanFalseOnblur() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnblur", "");
    }

    public void testCheckboxPropertybooleanFalseOnchange() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnchange", "");
    }

    public void testCheckboxPropertybooleanFalseOnclick() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnclick", "");
    }

    public void testCheckboxPropertybooleanFalseOndblclick()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOndblclick", "");
    }

    public void testCheckboxPropertybooleanFalseOnfocus() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnfocus", "");
    }

    public void testCheckboxPropertybooleanFalseOnkeydown() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnkeydown", "");
    }

    public void testCheckboxPropertybooleanFalseOnkeypress()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnkeypress", "");
    }

    public void testCheckboxPropertybooleanFalseOnkeyup() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnkeyup", "");
    }

    public void testCheckboxPropertybooleanFalseOnmousedown()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmousedown", "");
    }

    public void testCheckboxPropertybooleanFalseOnmousemove()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmousemove", "");
    }

    public void testCheckboxPropertybooleanFalseOnmouseout()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmouseout", "");
    }

    public void testCheckboxPropertybooleanFalseOnmouseover()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmouseover", "");
    }

    public void testCheckboxPropertybooleanFalseOnmouseup() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmouseup", "");
    }

}
