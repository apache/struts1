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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.CheckboxTag</code>
 * class.
 */
public class TestCheckboxTag4 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestCheckboxTag4(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestCheckboxTag4.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestCheckboxTag4.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY,
                new SimpleBeanForTesting(false),
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestCheckboxTag4.jsp");
    }

    /*
     * Testing CheckboxTag.
     */

    public void testCheckboxPropertybooleanFalseStyle() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseStyle", "");
    }

    public void testCheckboxPropertybooleanFalseStyleClass()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseStyleClass", "");
    }

    public void testCheckboxPropertybooleanFalseStyleId() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseStyleId", "");
    }

    public void testCheckboxPropertybooleanFalseTabindex() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseTabindex", "");
    }

    public void testCheckboxPropertybooleanFalseTitle() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseTitle", "");
    }

    public void testCheckboxPropertybooleanFalseTitleKey() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseTitleKey", "");
    }

    public void testCheckboxPropertybooleanFalseTitleKey_fr()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseTitleKey_fr", "fr");
    }

    public void testCheckboxPropertybooleanFalseValue() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseValue", "");
    }

    public void testCheckboxPropertybooleanFalseBodyContent()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseBodyContent", "");
    }

    public void testCheckboxPropertybooleanFalseBodyContentMessageKey()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseBodyContentMessageKey",
                "");
    }

    public void testCheckboxPropertybooleanFalseBodyContentMessageKey_fr()
            throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseBodyContentMessageKey_fr",
                "fr");
    }

    public void testCheckboxPropertybooleanFalseIndexedArray()
            throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testCheckboxPropertybooleanFalseIndexedArray", "");
    }

    public void testCheckboxPropertybooleanFalseIndexedArrayProperty()
            throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testCheckboxPropertybooleanFalseIndexedArrayProperty", "");
    }

    public void testCheckboxPropertybooleanFalseIndexedMap()
            throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testCheckboxPropertybooleanFalseIndexedMap", "");
    }

    public void testCheckboxPropertybooleanFalseIndexedMapProperty()
            throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testCheckboxPropertybooleanFalseIndexedMapProperty", "");
    }

    public void testCheckboxPropertybooleanFalseIndexedEnumeration()
            throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testCheckboxPropertybooleanFalseIndexedEnumeration", "");
    }

    public void testCheckboxPropertybooleanFalseIndexedEnumerationProperty()
            throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testCheckboxPropertybooleanFalseIndexedEnumerationProperty",
                "");
    }


}
