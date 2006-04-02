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
public class TestImageTag7 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImageTag7(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImageTag7.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImageTag7.class);
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
                .forward("/org/apache/struts/taglib/html/TestImageTag7.jsp");
    }

    /*
     * Testing ImageTag.
     */

//--------Testing attributes using page------

    public void testImageSrcKeyAccesskey() throws Exception {
        runMyTest("testImageSrcKeyAccesskey", "");
    }

    public void testImageSrcKeyAlign() throws Exception {
        runMyTest("testImageSrcKeyAlign", "");
    }

    public void testImageSrcKeyAlt() throws Exception {
        runMyTest("testImageSrcKeyAlt", "");
    }

    public void testImageSrcKeyAltKeyDefaultBundle() throws Exception {
        runMyTest("testImageSrcKeyAltKeyDefaultBundle", "");
    }

    public void testImageSrcKeyAltKeyAlternateBundle() throws Exception {
        runMyTest("testImageSrcKeyAltKeyAlternateBundle", "");
    }

    public void testImageSrcKeyAltKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImageSrcKeyAltKeyDefaultBundle_fr", "fr");
    }

    public void testImageSrcKeyAltKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImageSrcKeyAltKeyAlternateBundle_fr", "fr");
    }

    public void testImageSrcKeyBorder() throws Exception {
        runMyTest("testImageSrcKeyBorder", "");
    }

    public void testImageSrcKeyDisabled1() throws Exception {
        runMyTest("testImageSrcKeyDisabled1", "");
    }

    public void testImageSrcKeyDisabled2() throws Exception {
        runMyTest("testImageSrcKeyDisabled2", "");
    }

    public void testImageSrcKeyDisabled3() throws Exception {
        runMyTest("testImageSrcKeyDisabled3", "");
    }

    public void testImageSrcKeyDisabled4() throws Exception {
        runMyTest("testImageSrcKeyDisabled4", "");
    }

    public void testImageSrcKeyDisabled5() throws Exception {
        runMyTest("testImageSrcKeyDisabled5", "");
    }

    public void testImageSrcKeyDisabled6() throws Exception {
        runMyTest("testImageSrcKeyDisabled6", "");
    }

    public void testImageSrcKeyLocaleDefaultBundle() throws Exception {
        runMyTest("testImageSrcKeyLocaleDefaultBundle", "");
    }

    public void testImageSrcKeyLocaleAlternateBundle() throws Exception {
        runMyTest("testImageSrcKeyLocaleAlternateBundle", "");
    }

    public void testImageSrcKeyLocaleDefaultBundle_fr() throws Exception {
        pageContext.setAttribute("secret locale",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testImageSrcKeyLocaleDefaultBundle_fr", "");
    }

    public void testImageSrcKeyLocaleAlternateBundle_fr() throws Exception {
        pageContext.setAttribute("secret locale",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testImageSrcKeyLocaleAlternateBundle_fr", "");
    }

    public void testImageSrcKeyOnblur() throws Exception {
        runMyTest("testImageSrcKeyOnblur", "");
    }

    public void testImageSrcKeyOnchange() throws Exception {
        runMyTest("testImageSrcKeyOnchange", "");
    }

    public void testImageSrcKeyOnclick() throws Exception {
        runMyTest("testImageSrcKeyOnclick", "");
    }

    public void testImageSrcKeyOndblclick() throws Exception {
        runMyTest("testImageSrcKeyOndblclick", "");
    }

    public void testImageSrcKeyOnfocus() throws Exception {
        runMyTest("testImageSrcKeyOnfocus", "");
    }

    public void testImageSrcKeyOnkeydown() throws Exception {
        runMyTest("testImageSrcKeyOnkeydown", "");
    }

    public void testImageSrcKeyOnkeypress() throws Exception {
        runMyTest("testImageSrcKeyOnkeypress", "");
    }

    public void testImageSrcKeyOnkeyup() throws Exception {
        runMyTest("testImageSrcKeyOnkeyup", "");
    }

}
