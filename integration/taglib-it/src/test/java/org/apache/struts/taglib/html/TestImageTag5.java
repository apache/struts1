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
public class TestImageTag5 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImageTag5(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImageTag5.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImageTag5.class);
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
                .forward("/org/apache/struts/taglib/html/TestImageTag5.jsp");
    }

    /*
     * Testing ImageTag.
     */

//--------Testing attributes using page------

    public void testImageSrcAccesskey() throws Exception {
        runMyTest("testImageSrcAccesskey", "");
    }

    public void testImageSrcAlign() throws Exception {
        runMyTest("testImageSrcAlign", "");
    }

    public void testImageSrcAlt() throws Exception {
        runMyTest("testImageSrcAlt", "");
    }

    public void testImageSrcAltKeyDefaultBundle() throws Exception {
        runMyTest("testImageSrcAltKeyDefaultBundle", "");
    }

    public void testImageSrcAltKeyAlternateBundle() throws Exception {
        runMyTest("testImageSrcAltKeyAlternateBundle", "");
    }

    public void testImageSrcAltKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImageSrcAltKeyDefaultBundle_fr", "fr");
    }

    public void testImageSrcAltKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImageSrcAltKeyAlternateBundle_fr", "fr");
    }

    public void testImageSrcBorder() throws Exception {
        runMyTest("testImageSrcBorder", "");
    }

    public void testImageSrcDisabled1() throws Exception {
        runMyTest("testImageSrcDisabled1", "");
    }

    public void testImageSrcDisabled2() throws Exception {
        runMyTest("testImageSrcDisabled2", "");
    }

    public void testImageSrcDisabled3() throws Exception {
        runMyTest("testImageSrcDisabled3", "");
    }

    public void testImageSrcDisabled4() throws Exception {
        runMyTest("testImageSrcDisabled4", "");
    }

    public void testImageSrcDisabled5() throws Exception {
        runMyTest("testImageSrcDisabled5", "");
    }

    public void testImageSrcDisabled6() throws Exception {
        runMyTest("testImageSrcDisabled6", "");
    }

    public void testImageSrcLocale() throws Exception {
        pageContext.setAttribute("secret locale",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testImageSrcLocale", "");
    }

    public void testImageSrcOnblur() throws Exception {
        runMyTest("testImageSrcOnblur", "");
    }

    public void testImageSrcOnchange() throws Exception {
        runMyTest("testImageSrcOnchange", "");
    }

    public void testImageSrcOnclick() throws Exception {
        runMyTest("testImageSrcOnclick", "");
    }

    public void testImageSrcOndblclick() throws Exception {
        runMyTest("testImageSrcOndblclick", "");
    }

    public void testImageSrcOnfocus() throws Exception {
        runMyTest("testImageSrcOnfocus", "");
    }

    public void testImageSrcOnkeydown() throws Exception {
        runMyTest("testImageSrcOnkeydown", "");
    }

    public void testImageSrcOnkeypress() throws Exception {
        runMyTest("testImageSrcOnkeypress", "");
    }

    public void testImageSrcOnkeyup() throws Exception {
        runMyTest("testImageSrcOnkeyup", "");
    }

}
