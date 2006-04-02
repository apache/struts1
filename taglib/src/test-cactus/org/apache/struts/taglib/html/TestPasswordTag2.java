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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.PasswordTag</code>
 * class.
 */
public class TestPasswordTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestPasswordTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestPasswordTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestPasswordTag2.class);
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
                .forward("/org/apache/struts/taglib/html/TestPasswordTag2.jsp");
    }

    /*
     * Testing PasswordTag.
     */

    public void testPasswordPropertyReadonly() throws Exception {
        runMyTest("testPasswordPropertyReadonly", "");
    }

    public void testPasswordPropertySize() throws Exception {
        runMyTest("testPasswordPropertySize", "");
    }

    public void testPasswordPropertyStyle() throws Exception {
        runMyTest("testPasswordPropertyStyle", "");
    }

    public void testPasswordPropertyErrorStyle() throws Exception {
        runMyTest("testPasswordPropertyErrorStyle", "");
    }

    public void testPasswordPropertyStyleClass() throws Exception {
        runMyTest("testPasswordPropertyStyleClass", "");
    }

    public void testPasswordPropertyErrorStyleClass() throws Exception {
        runMyTest("testPasswordPropertyErrorStyleClass", "");
    }

    public void testPasswordPropertyStyleId() throws Exception {
        runMyTest("testPasswordPropertyStyleId", "");
    }

    public void testPasswordPropertyErrorStyleId() throws Exception {
        runMyTest("testPasswordPropertyErrorStyleId", "");
    }

    public void testPasswordPropertyTitle() throws Exception {
        runMyTest("testPasswordPropertyTitle", "");
    }

    public void testPasswordPropertyTitleKey() throws Exception {
        runMyTest("testPasswordPropertyTitleKey", "");
    }

    public void testPasswordPropertyTitleKey_fr() throws Exception {
        runMyTest("testPasswordPropertyTitleKey_fr", "fr");
    }

    public void testPasswordPropertyValue() throws Exception {
        runMyTest("testPasswordPropertyValue", "");
    }

    public void testPasswordPropertyIndexedArray() throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testPasswordPropertyIndexedArray", "");
    }

    public void testPasswordPropertyIndexedArrayProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testPasswordPropertyIndexedArrayProperty", "");
    }

    public void testPasswordPropertyIndexedMap() throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testPasswordPropertyIndexedMap", "");
    }

    public void testPasswordPropertyIndexedMapProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testPasswordPropertyIndexedMapProperty", "");
    }

    public void testPasswordPropertyIndexedEnumeration() throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testPasswordPropertyIndexedEnumeration", "");
    }

    public void testPasswordPropertyIndexedEnumerationProperty()
            throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testPasswordPropertyIndexedEnumerationProperty", "");
    }

}
