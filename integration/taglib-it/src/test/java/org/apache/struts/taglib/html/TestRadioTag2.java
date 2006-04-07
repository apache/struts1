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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.RadioTag</code>
 * class.
 */
public class TestRadioTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestRadioTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestRadioTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestRadioTag2.class);
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
                .forward("/org/apache/struts/taglib/html/TestRadioTag2.jsp");
    }

    /*
     * Testing RadioTag.
     */

    public void testRadioPropertyStyle() throws Exception {
        runMyTest("testRadioPropertyStyle", "");
    }

    public void testRadioPropertyErrorStyle() throws Exception {
        runMyTest("testRadioPropertyErrorStyle", "");
    }

    public void testRadioPropertyStyleClass() throws Exception {
        runMyTest("testRadioPropertyStyleClass", "");
    }

    public void testRadioPropertyErrorStyleClass() throws Exception {
        runMyTest("testRadioPropertyErrorStyleClass", "");
    }

    public void testRadioPropertyStyleId() throws Exception {
        runMyTest("testRadioPropertyStyleId", "");
    }

    public void testRadioPropertyErrorStyleId() throws Exception {
        runMyTest("testRadioPropertyErrorStyleId", "");
    }

    public void testRadioPropertyTitle() throws Exception {
        runMyTest("testRadioPropertyTitle", "");
    }

    public void testRadioPropertyTitleKey() throws Exception {
        runMyTest("testRadioPropertyTitleKey", "");
    }

    public void testRadioPropertyTitleKey_fr() throws Exception {
        runMyTest("testRadioPropertyTitleKey_fr", "fr");
    }

    public void testRadioPropertyValueMatch() throws Exception {
        runMyTest("testRadioPropertyValueMatch", "");
    }

    public void testRadioPropertyValueNotMatch() throws Exception {
        runMyTest("testRadioPropertyValueNotMatch", "");
    }

    public void testRadioPropertyIndexedArray() throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testRadioPropertyIndexedArray", "");
    }

    public void testRadioPropertyIndexedArrayProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testRadioPropertyIndexedArrayProperty", "");
    }

    public void testRadioPropertyIndexedMap() throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testRadioPropertyIndexedMap", "");
    }

    public void testRadioPropertyIndexedMapProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testRadioPropertyIndexedMapProperty", "");
    }

    public void testRadioPropertyIndexedEnumeration() throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testRadioPropertyIndexedEnumeration", "");
    }

    public void testRadioPropertyIndexedEnumerationProperty()
            throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testRadioPropertyIndexedEnumerationProperty", "");
    }

}
