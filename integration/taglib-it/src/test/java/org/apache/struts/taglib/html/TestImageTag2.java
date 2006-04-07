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
public class TestImageTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImageTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestImageTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImageTag2.class);
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
                .forward("/org/apache/struts/taglib/html/TestImageTag2.jsp");
    }

    /*
     * Testing ImageTag.
     */

//--------Testing attributes using page------

    public void testImagePageOnmousedown() throws Exception {
        runMyTest("testImagePageOnmousedown", "");
    }

    public void testImagePageOnmousemove() throws Exception {
        runMyTest("testImagePageOnmousemove", "");
    }

    public void testImagePageOnmouseout() throws Exception {
        runMyTest("testImagePageOnmouseout", "");
    }

    public void testImagePageOnmouseover() throws Exception {
        runMyTest("testImagePageOnmouseover", "");
    }

    public void testImagePageOnmouseup() throws Exception {
        runMyTest("testImagePageOnmouseup", "");
    }

    public void testImagePageProperty() throws Exception {
        runMyTest("testImagePageProperty", "");
    }

    public void testImagePageStyle() throws Exception {
        runMyTest("testImagePageStyle", "");
    }

    public void testImagePageStyleClass() throws Exception {
        runMyTest("testImagePageStyleClass", "");
    }

    public void testImagePageStyleId() throws Exception {
        runMyTest("testImagePageStyleId", "");
    }

    public void testImagePageTabindex() throws Exception {
        runMyTest("testImagePageTabindex", "");
    }

    public void testImagePageTitle() throws Exception {
        runMyTest("testImagePageTitle", "");
    }

    public void testImagePageTitleKeyDefaultBundle() throws Exception {
//        runMyTest("testImagePageTitleKeyDefaultBundle", "");
    }

    public void testImagePageTitleKeyAlternateBundle() throws Exception {
//        runMyTest("testImagePageTitleKeyAlternateBundle", "");
    }

    public void testImagePageTitleKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImagePageTitleKeyDefaultBundle_fr", "fr");
    }

    public void testImagePageTitleKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImagePageTitleKeyAlternateBundle_fr", "fr");
    }

    public void testImagePageIndexedArray() throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testImagePageIndexedArray", "");
    }

    public void testImagePageIndexedArrayProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testImagePageIndexedArrayProperty", "");
    }

    public void testImagePageIndexedMap() throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testImagePageIndexedMap", "");
    }

    public void testImagePageIndexedMapProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testImagePageIndexedMapProperty", "");
    }

    public void testImagePageIndexedEnumeration() throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testImagePageIndexedEnumeration", "");
    }

    public void testImagePageIndexedEnumerationProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testImagePageIndexedEnumerationProperty", "");
    }


}
