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
public class TestImgTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImgTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag2.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting("Test Value"),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/org/apache/struts/taglib/html/TestImgTag2.jsp");
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgPageOnclick() throws Exception {
        runMyTest("testImgPageOnclick", "");
    }

    public void testImgPageOndblclick() throws Exception {
        runMyTest("testImgPageOndblclick", "");
    }

    public void testImgPageOnkeydown() throws Exception {
        runMyTest("testImgPageOnkeydown", "");
    }

    public void testImgPageOnkeypress() throws Exception {
        runMyTest("testImgPageOnkeypress", "");
    }

    public void testImgPageOnkeyup() throws Exception {
        runMyTest("testImgPageOnkeyup", "");
    }

    public void testImgPageOnmousedown() throws Exception {
        runMyTest("testImgPageOnmousedown", "");
    }

    public void testImgPageOnmousemove() throws Exception {
        runMyTest("testImgPageOnmousemove", "");
    }

    public void testImgPageOnmouseout() throws Exception {
        runMyTest("testImgPageOnmouseout", "");
    }

    public void testImgPageOnmouseover() throws Exception {
        runMyTest("testImgPageOnmouseover", "");
    }

    public void testImgPageOnmouseup() throws Exception {
        runMyTest("testImgPageOnmouseup", "");
    }

    public void testImgPageStyle() throws Exception {
        runMyTest("testImgPageStyle", "");
    }

    public void testImgPageStyleClass() throws Exception {
        runMyTest("testImgPageStyleClass", "");
    }

    public void testImgPageStyleId() throws Exception {
        runMyTest("testImgPageStyleId", "");
    }

    public void testImgPageTitle() throws Exception {
        runMyTest("testImgPageTitle", "");
    }

    public void testImgPageTitleKeyDefaultBundle() throws Exception {
        runMyTest("testImgPageTitleKeyDefaultBundle", "");
    }

    public void testImgPageTitleKeyAlternateBundle() throws Exception {
        runMyTest("testImgPageTitleKeyAlternateBundle", "");
    }

    public void testImgPageTitleKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImgPageTitleKeyDefaultBundle_fr", "fr");
    }

    public void testImgPageTitleKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImgPageTitleKeyAlternateBundle_fr", "fr");
    }

    public void testImgPageUsemap() throws Exception {
        runMyTest("testImgPageUsemap", "");
    }

}
