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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.TextareaTag</code>
 * class.
 */
public class TestTextareaTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestTextareaTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestTextareaTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestTextareaTag1.class);
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
                .forward("/org/apache/struts/taglib/html/TestTextareaTag1.jsp");
    }

    /*
     * Testing TextareaTag.
     */
    public void testTextareaProperty() throws Exception {
        runMyTest("testTextareaProperty", "");
    }

    public void testTextareaPropertyAccesskey() throws Exception {
        runMyTest("testTextareaPropertyAccesskey", "");
    }

    public void testTextareaPropertyAlt() throws Exception {
        runMyTest("testTextareaPropertyAlt", "");
    }

    public void testTextareaPropertyAltKey1() throws Exception {
        runMyTest("testTextareaPropertyAltKey1", "");
    }

    public void testTextareaPropertyAltKey2() throws Exception {
        runMyTest("testTextareaPropertyAltKey2", "");
    }

    public void testTextareaPropertyAltKey3() throws Exception {
        runMyTest("testTextareaPropertyAltKey3", "");
    }

    public void testTextareaPropertyAltKey_fr1() throws Exception {
        runMyTest("testTextareaPropertyAltKey1_fr", "fr");
    }

    public void testTextareaPropertyAltKey_fr2() throws Exception {
        runMyTest("testTextareaPropertyAltKey2_fr", "fr");
    }

    public void testTextareaPropertyCols() throws Exception {
        runMyTest("testTextareaPropertyCols", "");
    }

    public void testTextareaPropertyDisabled() throws Exception {
        runMyTest("testTextareaPropertyDisabled", "");
    }

    public void testTextareaPropertyOnblur() throws Exception {
        runMyTest("testTextareaPropertyOnblur", "");
    }

    public void testTextareaPropertyOnchange() throws Exception {
        runMyTest("testTextareaPropertyOnchange", "");
    }

    public void testTextareaPropertyOnclick() throws Exception {
        runMyTest("testTextareaPropertyOnclick", "");
    }

    public void testTextareaPropertyOndblclick() throws Exception {
        runMyTest("testTextareaPropertyOndblclick", "");
    }

    public void testTextareaPropertyOnfocus() throws Exception {
        runMyTest("testTextareaPropertyOnfocus", "");
    }

    public void testTextareaPropertyOnkeydown() throws Exception {
        runMyTest("testTextareaPropertyOnkeydown", "");
    }

    public void testTextareaPropertyOnkeypress() throws Exception {
        runMyTest("testTextareaPropertyOnkeypress", "");
    }

    public void testTextareaPropertyOnkeyup() throws Exception {
        runMyTest("testTextareaPropertyOnkeyup", "");
    }

    public void testTextareaPropertyOnmousedown() throws Exception {
        runMyTest("testTextareaPropertyOnmousedown", "");
    }

    public void testTextareaPropertyOnmousemove() throws Exception {
        runMyTest("testTextareaPropertyOnmousemove", "");
    }

    public void testTextareaPropertyOnmouseout() throws Exception {
        runMyTest("testTextareaPropertyOnmouseout", "");
    }

    public void testTextareaPropertyOnmouseover() throws Exception {
        runMyTest("testTextareaPropertyOnmouseover", "");
    }

    public void testTextareaPropertyOnmouseup() throws Exception {
        runMyTest("testTextareaPropertyOnmouseup", "");
    }

}
