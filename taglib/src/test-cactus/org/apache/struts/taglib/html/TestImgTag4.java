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
public class TestImgTag4 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag4(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImgTag4.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag4.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/html/TestImgTag4.jsp");
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgPageKeyOnclick() throws Exception {
        runMyTest("testImgPageKeyOnclick", "");
    }

    public void testImgPageKeyOndblclick() throws Exception {
        runMyTest("testImgPageKeyOndblclick", "");
    }

    public void testImgPageKeyOnkeydown() throws Exception {
        runMyTest("testImgPageKeyOnkeydown", "");
    }

    public void testImgPageKeyOnkeypress() throws Exception {
        runMyTest("testImgPageKeyOnkeypress", "");
    }

    public void testImgPageKeyOnkeyup() throws Exception {
        runMyTest("testImgPageKeyOnkeyup", "");
    }

    public void testImgPageKeyOnmousedown() throws Exception {
        runMyTest("testImgPageKeyOnmousedown", "");
    }

    public void testImgPageKeyOnmousemove() throws Exception {
        runMyTest("testImgPageKeyOnmousemove", "");
    }

    public void testImgPageKeyOnmouseout() throws Exception {
        runMyTest("testImgPageKeyOnmouseout", "");
    }

    public void testImgPageKeyOnmouseover() throws Exception {
        runMyTest("testImgPageKeyOnmouseover", "");
    }

    public void testImgPageKeyOnmouseup() throws Exception {
        runMyTest("testImgPageKeyOnmouseup", "");
    }

    public void testImgPageKeyStyle() throws Exception {
        runMyTest("testImgPageKeyStyle", "");
    }

    public void testImgPageKeyStyleClass() throws Exception {
        runMyTest("testImgPageKeyStyleClass", "");
    }

    public void testImgPageKeyStyleId() throws Exception {
        runMyTest("testImgPageKeyStyleId", "");
    }

    public void testImgPageKeyTitle() throws Exception {
        runMyTest("testImgPageKeyTitle", "");
    }

    public void testImgPageKeyTitleKeyDefaultBundle() throws Exception {
        runMyTest("testImgPageKeyTitleKeyDefaultBundle", "");
    }

    public void testImgPageKeyTitleKeyAlternateBundle() throws Exception {
        runMyTest("testImgPageKeyTitleKeyAlternateBundle", "");
    }

    public void testImgPageKeyTitleKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImgPageKeyTitleKeyDefaultBundle_fr", "fr");
    }

    public void testImgPageKeyTitleKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImgPageKeyTitleKeyAlternateBundle_fr", "fr");
    }

    public void testImgPageKeyUsemap() throws Exception {
        runMyTest("testImgPageKeyUsemap", "");
    }

}
