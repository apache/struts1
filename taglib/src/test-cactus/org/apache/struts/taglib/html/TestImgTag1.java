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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.ImgTag</code>
 * class.
 */
public class TestImgTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImgTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/html/TestImgTag1.jsp");
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgPageAlign1() throws Exception {
        runMyTest("testImgPageAlign1", "");
    }

    public void testImgPageAlign2() throws Exception {
        runMyTest("testImgPageAlign2", "");
    }

    public void testImgPageAlign3() throws Exception {
        runMyTest("testImgPageAlign3", "");
    }

    public void testImgPageAlign4() throws Exception {
        runMyTest("testImgPageAlign4", "");
    }

    public void testImgPageAlign5() throws Exception {
        runMyTest("testImgPageAlign5", "");
    }

    public void testImgPageAlign6() throws Exception {
        runMyTest("testImgPageAlign6", "");
    }

    public void testImgPageAlign7() throws Exception {
        runMyTest("testImgPageAlign7", "");
    }

    public void testImgPageAlign8() throws Exception {
        runMyTest("testImgPageAlign8", "");
    }

    public void testImgPageAlign9() throws Exception {
        runMyTest("testImgPageAlign9", "");
    }

    public void testImgPageAlign10() throws Exception {
        runMyTest("testImgPageAlign10", "");
    }

    public void testImgPageAlt() throws Exception {
        runMyTest("testImgPageAlt", "");
    }

    public void testImgPageAltKeyDefaultBundle() throws Exception {
        runMyTest("testImgPageAltKeyDefaultBundle", "");
    }

    public void testImgPageAltKeyAlternateBundle() throws Exception {
        runMyTest("testImgPageAltKeyAlternateBundle", "");
    }

    public void testImgPageAltKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImgPageAltKeyDefaultBundle_fr", "fr");
    }

    public void testImgPageAltKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImgPageAltKeyAlternateBundle_fr", "fr");
    }

    public void testImgPageBorder() throws Exception {
        runMyTest("testImgPageBorder", "");
    }

    public void testImgPageHeight1() throws Exception {
        runMyTest("testImgPageHeight1", "");
    }

    public void testImgPageHeight2() throws Exception {
        runMyTest("testImgPageHeight2", "");
    }

    public void testImgPageHspace() throws Exception {
        runMyTest("testImgPageHspace", "");
    }

    public void testImgPageImageName() throws Exception {
        runMyTest("testImgPageImageName", "");
    }

    public void testImgPageImageIsmap() throws Exception {
        runMyTest("testImgPageImageIsmap", "");
    }

    public void testImgPageLocale() throws Exception {
        pageContext.setAttribute("secret locale",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testImgPageLocale", "");
    }

}
