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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.TextTag</code>
 * class.
 */
public class TestTextTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestTextTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestTextTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestTextTag1.class);
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
                .forward("/org/apache/struts/taglib/html/TestTextTag1.jsp");
    }

    /*
     * Testing TextTag.
     */
    public void testTextProperty() throws Exception {
        runMyTest("testTextProperty", "");
    }

    public void testTextPropertyAccesskey() throws Exception {
        runMyTest("testTextPropertyAccesskey", "");
    }

    public void testTextPropertyAlt() throws Exception {
        runMyTest("testTextPropertyAlt", "");
    }

    public void testTextPropertyAltKey1() throws Exception {
        runMyTest("testTextPropertyAltKey1", "");
    }

    public void testTextPropertyAltKey2() throws Exception {
        runMyTest("testTextPropertyAltKey2", "");
    }

    public void testTextPropertyAltKey3() throws Exception {
        runMyTest("testTextPropertyAltKey3", "");
    }

    public void testTextPropertyAltKey_fr1() throws Exception {
        runMyTest("testTextPropertyAltKey1_fr", "fr");
    }

    public void testTextPropertyAltKey_fr2() throws Exception {
        runMyTest("testTextPropertyAltKey2_fr", "fr");
    }

    public void testTextPropertyDisabled() throws Exception {
        runMyTest("testTextPropertyDisabled", "");
    }

    public void testTextPropertyMaxlength() throws Exception {
        runMyTest("testTextPropertyMaxlength", "");
    }

    public void testTextPropertyOnblur() throws Exception {
        runMyTest("testTextPropertyOnblur", "");
    }

    public void testTextPropertyOnchange() throws Exception {
        runMyTest("testTextPropertyOnchange", "");
    }

    public void testTextPropertyOnclick() throws Exception {
        runMyTest("testTextPropertyOnclick", "");
    }

    public void testTextPropertyOndblclick() throws Exception {
        runMyTest("testTextPropertyOndblclick", "");
    }

    public void testTextPropertyOnfocus() throws Exception {
        runMyTest("testTextPropertyOnfocus", "");
    }

    public void testTextPropertyOnkeydown() throws Exception {
        runMyTest("testTextPropertyOnkeydown", "");
    }

    public void testTextPropertyOnkeypress() throws Exception {
        runMyTest("testTextPropertyOnkeypress", "");
    }

    public void testTextPropertyOnkeyup() throws Exception {
        runMyTest("testTextPropertyOnkeyup", "");
    }

    public void testTextPropertyOnmousedown() throws Exception {
        runMyTest("testTextPropertyOnmousedown", "");
    }

    public void testTextPropertyOnmousemove() throws Exception {
        runMyTest("testTextPropertyOnmousemove", "");
    }

    public void testTextPropertyOnmouseout() throws Exception {
        runMyTest("testTextPropertyOnmouseout", "");
    }

    public void testTextPropertyOnmouseover() throws Exception {
        runMyTest("testTextPropertyOnmouseover", "");
    }

    public void testTextPropertyOnmouseup() throws Exception {
        runMyTest("testTextPropertyOnmouseup", "");
    }

}
