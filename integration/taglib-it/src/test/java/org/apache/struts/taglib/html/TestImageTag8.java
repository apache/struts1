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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.ImageTag</code>
 * class.
 */
public class TestImageTag8 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImageTag8(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImageTag8.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImageTag8.class);
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
                .forward("/org/apache/struts/taglib/html/TestImageTag8.jsp");
    }

    /*
     * Testing ImageTag.
     */

//--------Testing attributes using page------

    public void testImageSrcKeyOnmousedown() throws Exception {
        runMyTest("testImageSrcKeyOnmousedown", "");
    }

    public void testImageSrcKeyOnmousemove() throws Exception {
        runMyTest("testImageSrcKeyOnmousemove", "");
    }

    public void testImageSrcKeyOnmouseout() throws Exception {
        runMyTest("testImageSrcKeyOnmouseout", "");
    }

    public void testImageSrcKeyOnmouseover() throws Exception {
        runMyTest("testImageSrcKeyOnmouseover", "");
    }

    public void testImageSrcKeyOnmouseup() throws Exception {
        runMyTest("testImageSrcKeyOnmouseup", "");
    }

    public void testImageSrcKeyProperty() throws Exception {
        runMyTest("testImageSrcKeyProperty", "");
    }

    public void testImageSrcKeyStyle() throws Exception {
        runMyTest("testImageSrcKeyStyle", "");
    }

    public void testImageSrcKeyStyleClass() throws Exception {
        runMyTest("testImageSrcKeyStyleClass", "");
    }

    public void testImageSrcKeyStyleId() throws Exception {
        runMyTest("testImageSrcKeyStyleId", "");
    }

    public void testImageSrcKeyTabindex() throws Exception {
        runMyTest("testImageSrcKeyTabindex", "");
    }

    public void testImageSrcKeyTitle() throws Exception {
        runMyTest("testImageSrcKeyTitle", "");
    }

    public void testImageSrcKeyTitleKeyDefaultBundle() throws Exception {
        runMyTest("testImageSrcKeyTitleKeyDefaultBundle", "");
    }

    public void testImageSrcKeyTitleKeyAlternateBundle() throws Exception {
        runMyTest("testImageSrcKeyTitleKeyAlternateBundle", "");
    }

    public void testImageSrcKeyTitleKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImageSrcKeyTitleKeyDefaultBundle_fr", "fr");
    }

    public void testImageSrcKeyTitleKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImageSrcKeyTitleKeyAlternateBundle_fr", "fr");
    }

    public void testImageSrcKeyIndexedArray() throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testImageSrcKeyIndexedArray", "");
    }

    public void testImageSrcKeyIndexedArrayProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testImageSrcKeyIndexedArrayProperty", "");
    }

    public void testImageSrcKeyIndexedMap() throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testImageSrcKeyIndexedMap", "");
    }

    public void testImageSrcKeyIndexedMapProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testImageSrcKeyIndexedMapProperty", "");
    }

    public void testImageSrcKeyIndexedEnumeration() throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testImageSrcKeyIndexedEnumeration", "");
    }

    public void testImageSrcKeyIndexedEnumerationProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testImageSrcKeyIndexedEnumerationProperty", "");
    }


}
