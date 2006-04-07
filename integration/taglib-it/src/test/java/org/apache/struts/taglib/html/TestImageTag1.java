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
public class TestImageTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImageTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImageTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImageTag1.class);
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
                .forward("/org/apache/struts/taglib/html/TestImageTag1.jsp");
    }

    /*
     * Testing ImageTag.
     */

//--------Testing attributes using page------

    public void testImagePageAccesskey() throws Exception {
        runMyTest("testImagePageAccesskey", "");
    }

    public void testImagePageAlign() throws Exception {
        runMyTest("testImagePageAlign", "");
    }

    public void testImagePageAlt() throws Exception {
        runMyTest("testImagePageAlt", "");
    }

    public void testImagePageAltKeyDefaultBundle() throws Exception {
        runMyTest("testImagePageAltKeyDefaultBundle", "");
    }

    public void testImagePageAltKeyAlternateBundle() throws Exception {
        runMyTest("testImagePageAltKeyAlternateBundle", "");
    }

    public void testImagePageAltKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImagePageAltKeyDefaultBundle_fr", "fr");
    }

    public void testImagePageAltKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImagePageAltKeyAlternateBundle_fr", "fr");
    }

    public void testImagePageBorder() throws Exception {
        runMyTest("testImagePageBorder", "");
    }

    public void testImagePageDisabled1() throws Exception {
        runMyTest("testImagePageDisabled1", "");
    }

    public void testImagePageDisabled2() throws Exception {
        runMyTest("testImagePageDisabled2", "");
    }

    public void testImagePageDisabled3() throws Exception {
        runMyTest("testImagePageDisabled3", "");
    }

    public void testImagePageDisabled4() throws Exception {
        runMyTest("testImagePageDisabled4", "");
    }

    public void testImagePageDisabled5() throws Exception {
        runMyTest("testImagePageDisabled5", "");
    }

    public void testImagePageDisabled6() throws Exception {
        runMyTest("testImagePageDisabled6", "");
    }

    public void testImagePageLocale() throws Exception {
        pageContext.setAttribute("secret locale",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testImagePageLocale", "");
    }

    public void testImagePageOnblur() throws Exception {
        runMyTest("testImagePageOnblur", "");
    }

    public void testImagePageOnchange() throws Exception {
        runMyTest("testImagePageOnchange", "");
    }

    public void testImagePageOnclick() throws Exception {
        runMyTest("testImagePageOnclick", "");
    }

    public void testImagePageOndblclick() throws Exception {
        runMyTest("testImagePageOndblclick", "");
    }

    public void testImagePageOnfocus() throws Exception {
        runMyTest("testImagePageOnfocus", "");
    }

    public void testImagePageOnkeydown() throws Exception {
        runMyTest("testImagePageOnkeydown", "");
    }

    public void testImagePageOnkeypress() throws Exception {
        runMyTest("testImagePageOnkeypress", "");
    }

    public void testImagePageOnkeyup() throws Exception {
        runMyTest("testImagePageOnkeyup", "");
    }

}
