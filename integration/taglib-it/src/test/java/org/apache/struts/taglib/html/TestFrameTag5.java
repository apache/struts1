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
public class TestFrameTag5 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFrameTag5(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestFrameTag5.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFrameTag5.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestFrameTag5.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testFrameHref() throws Exception {
        runMyTest("testFrameHref", "");
    }

    public void testFrameHrefAnchor() throws Exception {
        runMyTest("testFrameHrefAnchor", "");
    }

    public void testFrameHrefFrameborder() throws Exception {
        runMyTest("testFrameHrefFrameborder", "");
    }

    public void testFrameHrefFrameName() throws Exception {
        runMyTest("testFrameHrefFrameName", "");
    }

    public void testFrameHrefLongdesc() throws Exception {
        runMyTest("testFrameHrefLongdesc", "");
    }

    public void testFrameHrefMarginheight() throws Exception {
        runMyTest("testFrameHrefMarginheight", "");
    }

    public void testFrameHrefMarginwidth() throws Exception {
        runMyTest("testFrameHrefMarginwidth", "");
    }

    public void testFrameHrefNameNoScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
        runMyTest("testFrameHrefNameNoScope", "");
    }

    public void testFrameHrefNamePropertyNoScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameHrefNamePropertyNoScope", "");
    }

    public void testFrameHrefNameApplicationScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext
                .setAttribute("paramMap", map, PageContext.APPLICATION_SCOPE);
        runMyTest("testFrameHrefNameApplicationScope", "");
    }

    public void testFrameHrefNamePropertyApplicationScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest("testFrameHrefNamePropertyApplicationScope", "");
    }

    public void testFrameHrefNameSessionScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.SESSION_SCOPE);
        runMyTest("testFrameHrefNameSessionScope", "");
    }

    public void testFrameHrefNamePropertySessionScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testFrameHrefNamePropertySessionScope", "");
    }

    public void testFrameHrefNameRequestScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
        runMyTest("testFrameHrefNameRequestScope", "");
    }

    public void testFrameHrefNamePropertyRequestScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameHrefNamePropertyRequestScope", "");
    }


    public void testFrameHrefNoresize1() throws Exception {
        runMyTest("testFrameHrefNoresize1", "");
    }

    public void testFrameHrefNoresize2() throws Exception {
        runMyTest("testFrameHrefNoresize2", "");
    }

    public void testFrameHrefNoresize3() throws Exception {
        runMyTest("testFrameHrefNoresize3", "");
    }

    public void testFrameHrefNoresize4() throws Exception {
        runMyTest("testFrameHrefNoresize4", "");
    }

    public void testFrameHrefNoresize5() throws Exception {
        runMyTest("testFrameHrefNoresize5", "");
    }

    public void testFrameHrefNoresize6() throws Exception {
        runMyTest("testFrameHrefNoresize6", "");
    }

}
