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
import java.util.HashMap;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.FrameTag</code>
 * class.
 */
public class TestFrameTag7 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFrameTag7(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestFrameTag7.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFrameTag7.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestFrameTag7.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testFramePage() throws Exception {
        runMyTest("testFramePage", "");
    }

    public void testFramePageAnchor() throws Exception {
        runMyTest("testFramePageAnchor", "");
    }

    public void testFramePageFrameborder() throws Exception {
        runMyTest("testFramePageFrameborder", "");
    }

    public void testFramePageFrameName() throws Exception {
        runMyTest("testFramePageFrameName", "");
    }

    public void testFramePageLongdesc() throws Exception {
        runMyTest("testFramePageLongdesc", "");
    }

    public void testFramePageMarginheight() throws Exception {
        runMyTest("testFramePageMarginheight", "");
    }

    public void testFramePageMarginwidth() throws Exception {
        runMyTest("testFramePageMarginwidth", "");
    }

    public void testFramePageNameNoScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
        runMyTest("testFramePageNameNoScope", "");
    }

    public void testFramePageNamePropertyNoScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFramePageNamePropertyNoScope", "");
    }

    public void testFramePageNameApplicationScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext
                .setAttribute("paramMap", map, PageContext.APPLICATION_SCOPE);
        runMyTest("testFramePageNameApplicationScope", "");
    }

    public void testFramePageNamePropertyApplicationScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest("testFramePageNamePropertyApplicationScope", "");
    }

    public void testFramePageNameSessionScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.SESSION_SCOPE);
        runMyTest("testFramePageNameSessionScope", "");
    }

    public void testFramePageNamePropertySessionScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testFramePageNamePropertySessionScope", "");
    }

    public void testFramePageNameRequestScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
        runMyTest("testFramePageNameRequestScope", "");
    }

    public void testFramePageNamePropertyRequestScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFramePageNamePropertyRequestScope", "");
    }


    public void testFramePageNoresize1() throws Exception {
        runMyTest("testFramePageNoresize1", "");
    }

    public void testFramePageNoresize2() throws Exception {
        runMyTest("testFramePageNoresize2", "");
    }

    public void testFramePageNoresize3() throws Exception {
        runMyTest("testFramePageNoresize3", "");
    }

    public void testFramePageNoresize4() throws Exception {
        runMyTest("testFramePageNoresize4", "");
    }

    public void testFramePageNoresize5() throws Exception {
        runMyTest("testFramePageNoresize5", "");
    }

    public void testFramePageNoresize6() throws Exception {
        runMyTest("testFramePageNoresize6", "");
    }

}
