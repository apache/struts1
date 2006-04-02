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
public class TestImgTag5 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag5(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImgTag5.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag5.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/html/TestImgTag5.jsp");
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgSrcAlign1() throws Exception {
        runMyTest("testImgSrcAlign1", "");
    }

    public void testImgSrcAlign2() throws Exception {
        runMyTest("testImgSrcAlign2", "");
    }

    public void testImgSrcAlign3() throws Exception {
        runMyTest("testImgSrcAlign3", "");
    }

    public void testImgSrcAlign4() throws Exception {
        runMyTest("testImgSrcAlign4", "");
    }

    public void testImgSrcAlign5() throws Exception {
        runMyTest("testImgSrcAlign5", "");
    }

    public void testImgSrcAlign6() throws Exception {
        runMyTest("testImgSrcAlign6", "");
    }

    public void testImgSrcAlign7() throws Exception {
        runMyTest("testImgSrcAlign7", "");
    }

    public void testImgSrcAlign8() throws Exception {
        runMyTest("testImgSrcAlign8", "");
    }

    public void testImgSrcAlign9() throws Exception {
        runMyTest("testImgSrcAlign9", "");
    }

    public void testImgSrcAlign10() throws Exception {
        runMyTest("testImgSrcAlign10", "");
    }

    public void testImgSrcAlt() throws Exception {
        runMyTest("testImgSrcAlt", "");
    }

    public void testImgSrcAltKeyDefaultBundle() throws Exception {
        runMyTest("testImgSrcAltKeyDefaultBundle", "");
    }

    public void testImgSrcAltKeyAlternateBundle() throws Exception {
        runMyTest("testImgSrcAltKeyAlternateBundle", "");
    }

    public void testImgSrcAltKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImgSrcAltKeyDefaultBundle_fr", "fr");
    }

    public void testImgSrcAltKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImgSrcAltKeyAlternateBundle_fr", "fr");
    }

    public void testImgSrcBorder() throws Exception {
        runMyTest("testImgSrcBorder", "");
    }

    public void testImgSrcHeight1() throws Exception {
        runMyTest("testImgSrcHeight1", "");
    }

    public void testImgSrcHeight2() throws Exception {
        runMyTest("testImgSrcHeight2", "");
    }

    public void testImgSrcHspace() throws Exception {
        runMyTest("testImgSrcHspace", "");
    }

    public void testImgSrcImageName() throws Exception {
        runMyTest("testImgSrcImageName", "");
    }

    public void testImgSrcImageIsmap() throws Exception {
        runMyTest("testImgSrcImageIsmap", "");
    }

    public void testImgSrcLocale() throws Exception {
        pageContext.setAttribute("secret locale",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testImgSrcLocale", "");
    }


}
