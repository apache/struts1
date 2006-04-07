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

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.CancelTag</code>
 * class.
 */
public class TestCancelTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestCancelTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestCancelTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestCancelTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestCancelTag1.jsp");
    }

    /*
     * Testing CancelTag.
     */
    public void testCancelAccesskey() throws Exception {
        runMyTest("testCancelAccesskey", "");
    }

    public void testCancelAlt() throws Exception {
        runMyTest("testCancelAlt", "");
    }

    public void testCancelAltKey1() throws Exception {
        runMyTest("testCancelAltKey1", "");
    }

    public void testCancelAltKey2() throws Exception {
        runMyTest("testCancelAltKey2", "");
    }

    public void testCancelAltKey3() throws Exception {
        runMyTest("testCancelAltKey3", "");
    }

    public void testCancelAltKey_fr1() throws Exception {
        runMyTest("testCancelAltKey1_fr", "fr");
    }

    public void testCancelAltKey_fr2() throws Exception {
        runMyTest("testCancelAltKey2_fr", "fr");
    }

    public void testCancelDisabled_True() throws Exception {
        runMyTest("testCancelDisabled_True", "");
    }

    public void testCancelDisabled_False1() throws Exception {
        runMyTest("testCancelDisabled_False1", "");
    }

    public void testCancelDisabled_False2() throws Exception {
        runMyTest("testCancelDisabled_False2", "");
    }

    public void testCancelOnblur() throws Exception {
        runMyTest("testCancelOnblur", "");
    }

    public void testCancelOnchange() throws Exception {
        runMyTest("testCancelOnchange", "");
    }

    public void testCancelOnclick() throws Exception {
        runMyTest("testCancelOnclick", "");
    }

    public void testCancelOndblclick() throws Exception {
        runMyTest("testCancelOndblclick", "");
    }

    public void testCancelOnfocus() throws Exception {
        runMyTest("testCancelOnfocus", "");
    }

    public void testCancelOnkeydown() throws Exception {
        runMyTest("testCancelOnkeydown", "");
    }

    public void testCancelOnkeypress() throws Exception {
        runMyTest("testCancelOnkeypress", "");
    }

    public void testCancelOnkeyup() throws Exception {
        runMyTest("testCancelOnkeyup", "");
    }

    public void testCancelOnmousedown() throws Exception {
        runMyTest("testCancelOnmousedown", "");
    }

    public void testCancelOnmousemove() throws Exception {
        runMyTest("testCancelOnmousemove", "");
    }

    public void testCancelOnmouseout() throws Exception {
        runMyTest("testCancelOnmouseout", "");
    }

    public void testCancelOnmouseover() throws Exception {
        runMyTest("testCancelOnmouseover", "");
    }

    public void testCancelOnmouseup() throws Exception {
        runMyTest("testCancelOnmouseup", "");
    }

}
