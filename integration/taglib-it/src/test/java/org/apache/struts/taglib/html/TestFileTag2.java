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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.FileTag</code>
 * class.
 */
public class TestFileTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFileTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestFileTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFileTag2.class);
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
                .forward("/org/apache/struts/taglib/html/TestFileTag2.jsp");
    }

    /*
     * Testing FileTag.
     */

    public void testFilePropertyStyle() throws Exception {
        runMyTest("testFilePropertyStyle", "");
    }

    public void testFilePropertyErrorStyle() throws Exception {
        runMyTest("testFilePropertyErrorStyle", "");
    }

    public void testFilePropertyStyleClass() throws Exception {
        runMyTest("testFilePropertyStyleClass", "");
    }

    public void testFilePropertyErrorStyleClass() throws Exception {
        runMyTest("testFilePropertyErrorStyleClass", "");
    }

    public void testFilePropertyStyleId() throws Exception {
        runMyTest("testFilePropertyStyleId", "");
    }

    public void testFilePropertyErrorStyleId() throws Exception {
        runMyTest("testFilePropertyErrorStyleId", "");
    }

    public void testFilePropertyTabindex() throws Exception {
        runMyTest("testFilePropertyTabindex", "");
    }

    public void testFilePropertyTitle() throws Exception {
        runMyTest("testFilePropertyTitle", "");
    }

    public void testFilePropertyTitleKey() throws Exception {
        runMyTest("testFilePropertyTitleKey", "");
    }

    public void testFilePropertyTitleKey_fr() throws Exception {
        runMyTest("testFilePropertyTitleKey_fr", "fr");
    }

    public void testFilePropertyValue() throws Exception {
        runMyTest("testFilePropertyValue", "");
    }

    public void testFilePropertyBodyContent() throws Exception {
        runMyTest("testFilePropertyBodyContent", "");
    }

    public void testFilePropertyBodyContentMessageKey() throws Exception {
        runMyTest("testFilePropertyBodyContentMessageKey", "");
    }

    public void testFilePropertyBodyContentMessageKey_fr() throws Exception {
        runMyTest("testFilePropertyBodyContentMessageKey_fr", "fr");
    }

    public void testFilePropertyIndexedArray() throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testFilePropertyIndexedArray", "");
    }

    public void testFilePropertyIndexedArrayProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testFilePropertyIndexedArrayProperty", "");
    }

    public void testFilePropertyIndexedMap() throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testFilePropertyIndexedMap", "");
    }

    public void testFilePropertyIndexedMapProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testFilePropertyIndexedMapProperty", "");
    }

    public void testFilePropertyIndexedEnumeration() throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testFilePropertyIndexedEnumeration", "");
    }

    public void testFilePropertyIndexedEnumerationProperty()
            throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testFilePropertyIndexedEnumerationProperty", "");
    }


}
