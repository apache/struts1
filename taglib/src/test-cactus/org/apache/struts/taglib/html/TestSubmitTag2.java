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
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.SubmitTag</code>
 * class.
 */
public class TestSubmitTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestSubmitTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestSubmitTag2.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestSubmitTag2.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestSubmitTag2.jsp");
    }

    /*
     * Testing SubmitTag.
     */

    public void testSubmitPropertyStyle() throws Exception {
        runMyTest("testSubmitPropertyStyle", "");
    }

    public void testSubmitPropertyStyleClass() throws Exception {
        runMyTest("testSubmitPropertyStyleClass", "");
    }

    public void testSubmitPropertyStyleId() throws Exception {
        runMyTest("testSubmitPropertyStyleId", "");
    }

    public void testSubmitPropertyTabindex() throws Exception {
        runMyTest("testSubmitPropertyTabindex", "");
    }

    public void testSubmitPropertyTitle() throws Exception {
        runMyTest("testSubmitPropertyTitle", "");
    }

    public void testSubmitPropertyTitleKey() throws Exception {
        runMyTest("testSubmitPropertyTitleKey", "");
    }

    public void testSubmitPropertyTitleKey_fr() throws Exception {
        runMyTest("testSubmitPropertyTitleKey_fr", "fr");
    }

    public void testSubmitPropertyValue() throws Exception {
        runMyTest("testSubmitPropertyValue", "");
    }

    public void testSubmitPropertyBodyContent() throws Exception {
        runMyTest("testSubmitPropertyBodyContent", "");
    }

    public void testSubmitPropertyBodyContentMessageKey() throws Exception {
        runMyTest("testSubmitPropertyBodyContentMessageKey", "");
    }

    public void testSubmitPropertyBodyContentMessageKey_fr()
            throws Exception {
        runMyTest("testSubmitPropertyBodyContentMessageKey_fr", "fr");
    }

    public void testSubmitPropertyIndexedArray() throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testSubmitPropertyIndexedArray", "");
    }

    public void testSubmitPropertyIndexedArrayProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testSubmitPropertyIndexedArrayProperty", "");
    }

    public void testSubmitPropertyIndexedMap() throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testSubmitPropertyIndexedMap", "");
    }

    public void testSubmitPropertyIndexedMapProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testSubmitPropertyIndexedMapProperty", "");
    }

    public void testSubmitPropertyIndexedEnumeration() throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testSubmitPropertyIndexedEnumeration", "");
    }

    public void testSubmitPropertyIndexedEnumerationProperty()
            throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testSubmitPropertyIndexedEnumerationProperty", "");
    }

}
