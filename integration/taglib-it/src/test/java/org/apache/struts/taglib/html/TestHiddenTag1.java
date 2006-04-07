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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.HiddenTag</code>
 * class.
 */
public class TestHiddenTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestHiddenTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestHiddenTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestHiddenTag1.class);
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
                .forward("/org/apache/struts/taglib/html/TestHiddenTag1.jsp");
    }

    /*
     * Testing HiddenTag.
     */
    public void testHiddenProperty() throws Exception {
        runMyTest("testHiddenProperty", "");
    }

    public void testHiddenPropertyAccesskey() throws Exception {
        runMyTest("testHiddenPropertyAccesskey", "");
    }

    public void testHiddenPropertyAlt() throws Exception {
        runMyTest("testHiddenPropertyAlt", "");
    }

    public void testHiddenPropertyAltKey1() throws Exception {
        runMyTest("testHiddenPropertyAltKey1", "");
    }

    public void testHiddenPropertyAltKey2() throws Exception {
        runMyTest("testHiddenPropertyAltKey2", "");
    }

    public void testHiddenPropertyAltKey3() throws Exception {
        runMyTest("testHiddenPropertyAltKey3", "");
    }

    public void testHiddenPropertyAltKey_fr1() throws Exception {
        runMyTest("testHiddenPropertyAltKey1_fr", "fr");
    }

    public void testHiddenPropertyAltKey_fr2() throws Exception {
        runMyTest("testHiddenPropertyAltKey2_fr", "fr");
    }

    public void testHiddenPropertyOnblur() throws Exception {
        runMyTest("testHiddenPropertyOnblur", "");
    }

    public void testHiddenPropertyOnchange() throws Exception {
        runMyTest("testHiddenPropertyOnchange", "");
    }

    public void testHiddenPropertyOnclick() throws Exception {
        runMyTest("testHiddenPropertyOnclick", "");
    }

    public void testHiddenPropertyOndblclick() throws Exception {
        runMyTest("testHiddenPropertyOndblclick", "");
    }

    public void testHiddenPropertyOnfocus() throws Exception {
        runMyTest("testHiddenPropertyOnfocus", "");
    }

    public void testHiddenPropertyOnkeydown() throws Exception {
        runMyTest("testHiddenPropertyOnkeydown", "");
    }

    public void testHiddenPropertyOnkeypress() throws Exception {
        runMyTest("testHiddenPropertyOnkeypress", "");
    }

    public void testHiddenPropertyOnkeyup() throws Exception {
        runMyTest("testHiddenPropertyOnkeyup", "");
    }

    public void testHiddenPropertyOnmousedown() throws Exception {
        runMyTest("testHiddenPropertyOnmousedown", "");
    }

    public void testHiddenPropertyOnmousemove() throws Exception {
        runMyTest("testHiddenPropertyOnmousemove", "");
    }

    public void testHiddenPropertyOnmouseout() throws Exception {
        runMyTest("testHiddenPropertyOnmouseout", "");
    }

    public void testHiddenPropertyOnmouseover() throws Exception {
        runMyTest("testHiddenPropertyOnmouseover", "");
    }

    public void testHiddenPropertyOnmouseup() throws Exception {
        runMyTest("testHiddenPropertyOnmouseup", "");
    }

}
