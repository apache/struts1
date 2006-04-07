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
import org.apache.struts.taglib.SimpleBeanForTesting;

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.PasswordTag</code>
 * class.
 */
public class TestPasswordTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestPasswordTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestPasswordTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestPasswordTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestPasswordTag1.jsp");
    }

    /*
     * Testing PasswordTag.
     */
    public void testPasswordProperty() throws Exception {
        runMyTest("testPasswordProperty", "");
    }

    public void testPasswordPropertyAccesskey() throws Exception {
        runMyTest("testPasswordPropertyAccesskey", "");
    }

    public void testPasswordPropertyAlt() throws Exception {
        runMyTest("testPasswordPropertyAlt", "");
    }

    public void testPasswordPropertyAltKey1() throws Exception {
        runMyTest("testPasswordPropertyAltKey1", "");
    }

    public void testPasswordPropertyAltKey2() throws Exception {
        runMyTest("testPasswordPropertyAltKey2", "");
    }

    public void testPasswordPropertyAltKey3() throws Exception {
        runMyTest("testPasswordPropertyAltKey3", "");
    }

    public void testPasswordPropertyAltKey_fr1() throws Exception {
        runMyTest("testPasswordPropertyAltKey1_fr", "fr");
    }

    public void testPasswordPropertyAltKey_fr2() throws Exception {
        runMyTest("testPasswordPropertyAltKey2_fr", "fr");
    }

    public void testPasswordPropertyDisabled() throws Exception {
        runMyTest("testPasswordPropertyDisabled", "");
    }

    public void testPasswordPropertyMaxlength() throws Exception {
        runMyTest("testPasswordPropertyMaxlength", "");
    }

    public void testPasswordPropertyOnblur() throws Exception {
        runMyTest("testPasswordPropertyOnblur", "");
    }

    public void testPasswordPropertyOnchange() throws Exception {
        runMyTest("testPasswordPropertyOnchange", "");
    }

    public void testPasswordPropertyOnclick() throws Exception {
        runMyTest("testPasswordPropertyOnclick", "");
    }

    public void testPasswordPropertyOndblclick() throws Exception {
        runMyTest("testPasswordPropertyOndblclick", "");
    }

    public void testPasswordPropertyOnfocus() throws Exception {
        runMyTest("testPasswordPropertyOnfocus", "");
    }

    public void testPasswordPropertyOnkeydown() throws Exception {
        runMyTest("testPasswordPropertyOnkeydown", "");
    }

    public void testPasswordPropertyOnkeypress() throws Exception {
        runMyTest("testPasswordPropertyOnkeypress", "");
    }

    public void testPasswordPropertyOnkeyup() throws Exception {
        runMyTest("testPasswordPropertyOnkeyup", "");
    }

    public void testPasswordPropertyOnmousedown() throws Exception {
        runMyTest("testPasswordPropertyOnmousedown", "");
    }

    public void testPasswordPropertyOnmousemove() throws Exception {
        runMyTest("testPasswordPropertyOnmousemove", "");
    }

    public void testPasswordPropertyOnmouseout() throws Exception {
        runMyTest("testPasswordPropertyOnmouseout", "");
    }

    public void testPasswordPropertyOnmouseover() throws Exception {
        runMyTest("testPasswordPropertyOnmouseover", "");
    }

    public void testPasswordPropertyOnmouseup() throws Exception {
        runMyTest("testPasswordPropertyOnmouseup", "");
    }

}
