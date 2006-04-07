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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.ButtonTag</code>
 * class.
 */
public class TestButtonTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestButtonTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestButtonTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestButtonTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestButtonTag1.jsp");
    }

    /*
     * Testing ButtonTag.
     */
    public void testButtonProperty() throws Exception {
        runMyTest("testButtonProperty", "");
    }

    public void testButtonPropertyAccesskey() throws Exception {
        runMyTest("testButtonPropertyAccesskey", "");
    }

    public void testButtonPropertyAlt() throws Exception {
        runMyTest("testButtonPropertyAlt", "");
    }

    public void testButtonPropertyAltKey1() throws Exception {
        runMyTest("testButtonPropertyAltKey1", "");
    }

    public void testButtonPropertyAltKey2() throws Exception {
        runMyTest("testButtonPropertyAltKey2", "");
    }

    public void testButtonPropertyAltKey3() throws Exception {
        runMyTest("testButtonPropertyAltKey3", "");
    }

    public void testButtonPropertyAltKey_fr1() throws Exception {
        runMyTest("testButtonPropertyAltKey1_fr", "fr");
    }

    public void testButtonPropertyAltKey_fr2() throws Exception {
        runMyTest("testButtonPropertyAltKey2_fr", "fr");
    }

    public void testButtonPropertyDisabled_True() throws Exception {
        runMyTest("testButtonPropertyDisabled_True", "");
    }

    public void testButtonPropertyDisabled_False1() throws Exception {
        runMyTest("testButtonPropertyDisabled_False1", "");
    }

    public void testButtonPropertyDisabled_False2() throws Exception {
        runMyTest("testButtonPropertyDisabled_False2", "");
    }

    public void testButtonPropertyOnblur() throws Exception {
        runMyTest("testButtonPropertyOnblur", "");
    }

    public void testButtonPropertyOnchange() throws Exception {
        runMyTest("testButtonPropertyOnchange", "");
    }

    public void testButtonPropertyOnclick() throws Exception {
        runMyTest("testButtonPropertyOnclick", "");
    }

    public void testButtonPropertyOndblclick() throws Exception {
        runMyTest("testButtonPropertyOndblclick", "");
    }

    public void testButtonPropertyOnfocus() throws Exception {
        runMyTest("testButtonPropertyOnfocus", "");
    }

    public void testButtonPropertyOnkeydown() throws Exception {
        runMyTest("testButtonPropertyOnkeydown", "");
    }

    public void testButtonPropertyOnkeypress() throws Exception {
        runMyTest("testButtonPropertyOnkeypress", "");
    }

    public void testButtonPropertyOnkeyup() throws Exception {
        runMyTest("testButtonPropertyOnkeyup", "");
    }

    public void testButtonPropertyOnmousedown() throws Exception {
        runMyTest("testButtonPropertyOnmousedown", "");
    }

    public void testButtonPropertyOnmousemove() throws Exception {
        runMyTest("testButtonPropertyOnmousemove", "");
    }

    public void testButtonPropertyOnmouseout() throws Exception {
        runMyTest("testButtonPropertyOnmouseout", "");
    }

    public void testButtonPropertyOnmouseover() throws Exception {
        runMyTest("testButtonPropertyOnmouseover", "");
    }

    public void testButtonPropertyOnmouseup() throws Exception {
        runMyTest("testButtonPropertyOnmouseup", "");
    }

}
