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
public class TestImgTag3 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImgTag3.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag3.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/html/TestImgTag3.jsp");
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgPageKeyAlign1() throws Exception {
        runMyTest("testImgPageKeyAlign1", "");
    }

    public void testImgPageKeyAlign2() throws Exception {
        runMyTest("testImgPageKeyAlign2", "");
    }

    public void testImgPageKeyAlign3() throws Exception {
        runMyTest("testImgPageKeyAlign3", "");
    }

    public void testImgPageKeyAlign4() throws Exception {
        runMyTest("testImgPageKeyAlign4", "");
    }

    public void testImgPageKeyAlign5() throws Exception {
        runMyTest("testImgPageKeyAlign5", "");
    }

    public void testImgPageKeyAlign6() throws Exception {
        runMyTest("testImgPageKeyAlign6", "");
    }

    public void testImgPageKeyAlign7() throws Exception {
        runMyTest("testImgPageKeyAlign7", "");
    }

    public void testImgPageKeyAlign8() throws Exception {
        runMyTest("testImgPageKeyAlign8", "");
    }

    public void testImgPageKeyAlign9() throws Exception {
        runMyTest("testImgPageKeyAlign9", "");
    }

    public void testImgPageKeyAlign10() throws Exception {
        runMyTest("testImgPageKeyAlign10", "");
    }

    public void testImgPageKeyAlt() throws Exception {
        runMyTest("testImgPageKeyAlt", "");
    }

    public void testImgPageKeyAltKeyDefaultBundle() throws Exception {
        runMyTest("testImgPageKeyAltKeyDefaultBundle", "");
    }

    public void testImgPageKeyAltKeyAlternateBundle() throws Exception {
        runMyTest("testImgPageKeyAltKeyAlternateBundle", "");
    }

    public void testImgPageKeyAltKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImgPageKeyAltKeyDefaultBundle_fr", "fr");
    }

    public void testImgPageKeyAltKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImgPageKeyAltKeyAlternateBundle_fr", "fr");
    }

    public void testImgPageKeyBorder() throws Exception {
        runMyTest("testImgPageKeyBorder", "");
    }

    public void testImgPageKeyHeight1() throws Exception {
        runMyTest("testImgPageKeyHeight1", "");
    }

    public void testImgPageKeyHeight2() throws Exception {
        runMyTest("testImgPageKeyHeight2", "");
    }

    public void testImgPageKeyHspace() throws Exception {
        runMyTest("testImgPageKeyHspace", "");
    }

    public void testImgPageKeyImageName() throws Exception {
        runMyTest("testImgPageKeyImageName", "");
    }

    public void testImgPageKeyImageIsmap() throws Exception {
        runMyTest("testImgPageKeyImageIsmap", "");
    }

    public void testImgPageKeyLocale() throws Exception {
        pageContext.setAttribute("secret locale",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testImgPageKeyLocale", "");
    }

}
