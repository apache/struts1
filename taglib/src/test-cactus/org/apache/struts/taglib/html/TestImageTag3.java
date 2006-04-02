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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.ImageTag</code>
 * class.
 */
public class TestImageTag3 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImageTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImageTag3.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImageTag3.class);
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
                .forward("/org/apache/struts/taglib/html/TestImageTag3.jsp");
    }

    /*
     * Testing ImageTag.
     */

//--------Testing attributes using page------

    public void testImagePageKeyAccesskey() throws Exception {
        runMyTest("testImagePageKeyAccesskey", "");
    }

    public void testImagePageKeyAlign() throws Exception {
        runMyTest("testImagePageKeyAlign", "");
    }

    public void testImagePageKeyAlt() throws Exception {
        runMyTest("testImagePageKeyAlt", "");
    }

    public void testImagePageKeyAltKeyDefaultBundle() throws Exception {
        runMyTest("testImagePageKeyAltKeyDefaultBundle", "");
    }

    public void testImagePageKeyAltKeyAlternateBundle() throws Exception {
        runMyTest("testImagePageKeyAltKeyAlternateBundle", "");
    }

    public void testImagePageKeyAltKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImagePageKeyAltKeyDefaultBundle_fr", "fr");
    }

    public void testImagePageKeyAltKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImagePageKeyAltKeyAlternateBundle_fr", "fr");
    }

    public void testImagePageKeyBorder() throws Exception {
        runMyTest("testImagePageKeyBorder", "");
    }

    public void testImagePageKeyDisabled1() throws Exception {
        runMyTest("testImagePageKeyDisabled1", "");
    }

    public void testImagePageKeyDisabled2() throws Exception {
        runMyTest("testImagePageKeyDisabled2", "");
    }

    public void testImagePageKeyDisabled3() throws Exception {
        runMyTest("testImagePageKeyDisabled3", "");
    }

    public void testImagePageKeyDisabled4() throws Exception {
        runMyTest("testImagePageKeyDisabled4", "");
    }

    public void testImagePageKeyDisabled5() throws Exception {
        runMyTest("testImagePageKeyDisabled5", "");
    }

    public void testImagePageKeyDisabled6() throws Exception {
        runMyTest("testImagePageKeyDisabled6", "");
    }

    public void testImagePageKeyLocaleDefaultBundle() throws Exception {
        runMyTest("testImagePageKeyLocaleDefaultBundle", "");
    }

    public void testImagePageKeyLocaleAlternateBundle() throws Exception {
        runMyTest("testImagePageKeyLocaleAlternateBundle", "");
    }

    public void testImagePageKeyLocaleDefaultBundle_fr() throws Exception {
        pageContext.setAttribute("secret locale",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testImagePageKeyLocaleDefaultBundle_fr", "");
    }

    public void testImagePageKeyLocaleAlternateBundle_fr() throws Exception {
        pageContext.setAttribute("secret locale",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testImagePageKeyLocaleAlternateBundle_fr", "");
    }

    public void testImagePageKeyOnblur() throws Exception {
        runMyTest("testImagePageKeyOnblur", "");
    }

    public void testImagePageKeyOnchange() throws Exception {
        runMyTest("testImagePageKeyOnchange", "");
    }

    public void testImagePageKeyOnclick() throws Exception {
        runMyTest("testImagePageKeyOnclick", "");
    }

    public void testImagePageKeyOndblclick() throws Exception {
        runMyTest("testImagePageKeyOndblclick", "");
    }

    public void testImagePageKeyOnfocus() throws Exception {
        runMyTest("testImagePageKeyOnfocus", "");
    }

    public void testImagePageKeyOnkeydown() throws Exception {
        runMyTest("testImagePageKeyOnkeydown", "");
    }

    public void testImagePageKeyOnkeypress() throws Exception {
        runMyTest("testImagePageKeyOnkeypress", "");
    }

    public void testImagePageKeyOnkeyup() throws Exception {
        runMyTest("testImagePageKeyOnkeyup", "");
    }

}
