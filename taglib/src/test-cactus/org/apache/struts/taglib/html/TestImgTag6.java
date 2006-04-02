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
public class TestImgTag6 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag6(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImgTag6.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag6.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/html/TestImgTag6.jsp");
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgSrcOnclick() throws Exception {
        runMyTest("testImgSrcOnclick", "");
    }

    public void testImgSrcOndblclick() throws Exception {
        runMyTest("testImgSrcOndblclick", "");
    }

    public void testImgSrcOnkeydown() throws Exception {
        runMyTest("testImgSrcOnkeydown", "");
    }

    public void testImgSrcOnkeypress() throws Exception {
        runMyTest("testImgSrcOnkeypress", "");
    }

    public void testImgSrcOnkeyup() throws Exception {
        runMyTest("testImgSrcOnkeyup", "");
    }

    public void testImgSrcOnmousedown() throws Exception {
        runMyTest("testImgSrcOnmousedown", "");
    }

    public void testImgSrcOnmousemove() throws Exception {
        runMyTest("testImgSrcOnmousemove", "");
    }

    public void testImgSrcOnmouseout() throws Exception {
        runMyTest("testImgSrcOnmouseout", "");
    }

    public void testImgSrcOnmouseover() throws Exception {
        runMyTest("testImgSrcOnmouseover", "");
    }

    public void testImgSrcOnmouseup() throws Exception {
        runMyTest("testImgSrcOnmouseup", "");
    }

    public void testImgSrcStyle() throws Exception {
        runMyTest("testImgSrcStyle", "");
    }

    public void testImgSrcStyleClass() throws Exception {
        runMyTest("testImgSrcStyleClass", "");
    }

    public void testImgSrcStyleId() throws Exception {
        runMyTest("testImgSrcStyleId", "");
    }

    public void testImgSrcTitle() throws Exception {
        runMyTest("testImgSrcTitle", "");
    }

    public void testImgSrcTitleKeyDefaultBundle() throws Exception {
        runMyTest("testImgSrcTitleKeyDefaultBundle", "");
    }

    public void testImgSrcTitleKeyAlternateBundle() throws Exception {
        runMyTest("testImgSrcTitleKeyAlternateBundle", "");
    }

    public void testImgSrcTitleKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImgSrcTitleKeyDefaultBundle_fr", "fr");
    }

    public void testImgSrcTitleKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImgSrcTitleKeyAlternateBundle_fr", "fr");
    }

    public void testImgSrcUsemap() throws Exception {
        runMyTest("testImgSrcUsemap", "");
    }

}
