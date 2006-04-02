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
public class TestResetTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestResetTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestResetTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestResetTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestResetTag1.jsp");
    }

    /*
     * Testing ResetTag.
     */
    public void testResetProperty() throws Exception {
        runMyTest("testResetProperty", "");
    }

    public void testResetPropertyAccesskey() throws Exception {
        runMyTest("testResetPropertyAccesskey", "");
    }

    public void testResetPropertyAlt() throws Exception {
        runMyTest("testResetPropertyAlt", "");
    }

    public void testResetPropertyAltKey1() throws Exception {
        runMyTest("testResetPropertyAltKey1", "");
    }

    public void testResetPropertyAltKey2() throws Exception {
        runMyTest("testResetPropertyAltKey2", "");
    }

    public void testResetPropertyAltKey3() throws Exception {
        runMyTest("testResetPropertyAltKey3", "");
    }

    public void testResetPropertyAltKey_fr1() throws Exception {
        runMyTest("testResetPropertyAltKey1_fr", "fr");
    }

    public void testResetPropertyAltKey_fr2() throws Exception {
        runMyTest("testResetPropertyAltKey2_fr", "fr");
    }

    public void testResetPropertyDisabled_True() throws Exception {
        runMyTest("testResetPropertyDisabled_True", "");
    }

    public void testResetPropertyDisabled_False1() throws Exception {
        runMyTest("testResetPropertyDisabled_False1", "");
    }

    public void testResetPropertyDisabled_False2() throws Exception {
        runMyTest("testResetPropertyDisabled_False2", "");
    }

    public void testResetPropertyOnblur() throws Exception {
        runMyTest("testResetPropertyOnblur", "");
    }

    public void testResetPropertyOnchange() throws Exception {
        runMyTest("testResetPropertyOnchange", "");
    }

    public void testResetPropertyOnclick() throws Exception {
        runMyTest("testResetPropertyOnclick", "");
    }

    public void testResetPropertyOndblclick() throws Exception {
        runMyTest("testResetPropertyOndblclick", "");
    }

    public void testResetPropertyOnfocus() throws Exception {
        runMyTest("testResetPropertyOnfocus", "");
    }

    public void testResetPropertyOnkeydown() throws Exception {
        runMyTest("testResetPropertyOnkeydown", "");
    }

    public void testResetPropertyOnkeypress() throws Exception {
        runMyTest("testResetPropertyOnkeypress", "");
    }

    public void testResetPropertyOnkeyup() throws Exception {
        runMyTest("testResetPropertyOnkeyup", "");
    }

    public void testResetPropertyOnmousedown() throws Exception {
        runMyTest("testResetPropertyOnmousedown", "");
    }

    public void testResetPropertyOnmousemove() throws Exception {
        runMyTest("testResetPropertyOnmousemove", "");
    }

    public void testResetPropertyOnmouseout() throws Exception {
        runMyTest("testResetPropertyOnmouseout", "");
    }

    public void testResetPropertyOnmouseover() throws Exception {
        runMyTest("testResetPropertyOnmouseover", "");
    }

    public void testResetPropertyOnmouseup() throws Exception {
        runMyTest("testResetPropertyOnmouseup", "");
    }

}
