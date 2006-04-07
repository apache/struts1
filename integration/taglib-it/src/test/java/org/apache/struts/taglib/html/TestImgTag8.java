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
public class TestImgTag8 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag8(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImgTag8.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag8.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/html/TestImgTag8.jsp");
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgSrcKeyOnclick() throws Exception {
        runMyTest("testImgSrcKeyOnclick", "");
    }

    public void testImgSrcKeyOndblclick() throws Exception {
        runMyTest("testImgSrcKeyOndblclick", "");
    }

    public void testImgSrcKeyOnkeydown() throws Exception {
        runMyTest("testImgSrcKeyOnkeydown", "");
    }

    public void testImgSrcKeyOnkeypress() throws Exception {
        runMyTest("testImgSrcKeyOnkeypress", "");
    }

    public void testImgSrcKeyOnkeyup() throws Exception {
        runMyTest("testImgSrcKeyOnkeyup", "");
    }

    public void testImgSrcKeyOnmousedown() throws Exception {
        runMyTest("testImgSrcKeyOnmousedown", "");
    }

    public void testImgSrcKeyOnmousemove() throws Exception {
        runMyTest("testImgSrcKeyOnmousemove", "");
    }

    public void testImgSrcKeyOnmouseout() throws Exception {
        runMyTest("testImgSrcKeyOnmouseout", "");
    }

    public void testImgSrcKeyOnmouseover() throws Exception {
        runMyTest("testImgSrcKeyOnmouseover", "");
    }

    public void testImgSrcKeyOnmouseup() throws Exception {
        runMyTest("testImgSrcKeyOnmouseup", "");
    }

    public void testImgSrcKeyStyle() throws Exception {
        runMyTest("testImgSrcKeyStyle", "");
    }

    public void testImgSrcKeyStyleClass() throws Exception {
        runMyTest("testImgSrcKeyStyleClass", "");
    }

    public void testImgSrcKeyStyleId() throws Exception {
        runMyTest("testImgSrcKeyStyleId", "");
    }

    public void testImgSrcKeyTitle() throws Exception {
        runMyTest("testImgSrcKeyTitle", "");
    }

    public void testImgSrcKeyTitleKeyDefaultBundle() throws Exception {
        runMyTest("testImgSrcKeyTitleKeyDefaultBundle", "");
    }

    public void testImgSrcKeyTitleKeyAlternateBundle() throws Exception {
        runMyTest("testImgSrcKeyTitleKeyAlternateBundle", "");
    }

    public void testImgSrcKeyTitleKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImgSrcKeyTitleKeyDefaultBundle_fr", "fr");
    }

    public void testImgSrcKeyTitleKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImgSrcKeyTitleKeyAlternateBundle_fr", "fr");
    }

    public void testImgSrcKeyUsemap() throws Exception {
        runMyTest("testImgSrcKeyUsemap", "");
    }

}
