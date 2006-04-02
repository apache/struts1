/*
 * $Id$ 
 *
 * Copyright 2004 The Apache Software Foundation.
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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.TextTag</code>
 * class.
 */
public class TestTextTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestTextTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestTextTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestTextTag2.class);
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
                .forward("/org/apache/struts/taglib/html/TestTextTag2.jsp");
    }

    /*
     * Testing TextTag.
     */

    public void testTextPropertyReadonly() throws Exception {
        runMyTest("testTextPropertyReadonly", "");
    }

    public void testTextPropertySize() throws Exception {
        runMyTest("testTextPropertySize", "");
    }

    public void testTextPropertyStyle() throws Exception {
        runMyTest("testTextPropertyStyle", "");
    }

    public void testTextPropertyErrorStyle() throws Exception {
        runMyTest("testTextPropertyErrorStyle", "");
    }

    public void testTextPropertyStyleClass() throws Exception {
        runMyTest("testTextPropertyStyleClass", "");
    }

    public void testTextPropertyErrorStyleClass() throws Exception {
        runMyTest("testTextPropertyErrorStyleClass", "");
    }

    public void testTextPropertyStyleId() throws Exception {
        runMyTest("testTextPropertyStyleId", "");
    }

    public void testTextPropertyErrorStyleId() throws Exception {
        runMyTest("testTextPropertyErrorStyleId", "");
    }

    public void testTextPropertyTitle() throws Exception {
        runMyTest("testTextPropertyTitle", "");
    }

    public void testTextPropertyTitleKey() throws Exception {
        runMyTest("testTextPropertyTitleKey", "");
    }

    public void testTextPropertyTitleKey_fr() throws Exception {
        runMyTest("testTextPropertyTitleKey_fr", "fr");
    }

    public void testTextPropertyValue() throws Exception {
        runMyTest("testTextPropertyValue", "");
    }

    public void testTextPropertyIndexedArray() throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testTextPropertyIndexedArray", "");
    }

    public void testTextPropertyIndexedArrayProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testTextPropertyIndexedArrayProperty", "");
    }

    public void testTextPropertyIndexedMap() throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testTextPropertyIndexedMap", "");
    }

    public void testTextPropertyIndexedMapProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testTextPropertyIndexedMapProperty", "");
    }

    public void testTextPropertyIndexedEnumeration() throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testTextPropertyIndexedEnumeration", "");
    }

    public void testTextPropertyIndexedEnumerationProperty()
            throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testTextPropertyIndexedEnumerationProperty", "");
    }

}
