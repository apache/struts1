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
public class TestFrameTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFrameTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestFrameTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFrameTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestFrameTag1.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testFrameForward() throws Exception {
        runMyTest("testFrameForward", "");
    }

    public void testFrameForwardAnchor() throws Exception {
        runMyTest("testFrameForwardAnchor", "");
    }

    public void testFrameForwardFrameborder() throws Exception {
        runMyTest("testFrameForwardFrameborder", "");
    }

    public void testFrameForwardFrameName() throws Exception {
        runMyTest("testFrameForwardFrameName", "");
    }

    public void testFrameForwardLongdesc() throws Exception {
        runMyTest("testFrameForwardLongdesc", "");
    }

    public void testFrameForwardMarginheight() throws Exception {
        runMyTest("testFrameForwardMarginheight", "");
    }

    public void testFrameForwardMarginwidth() throws Exception {
        runMyTest("testFrameForwardMarginwidth", "");
    }

    public void testFrameForwardNameNoScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
        runMyTest("testFrameForwardNameNoScope", "");
    }

    public void testFrameForwardNamePropertyNoScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameForwardNamePropertyNoScope", "");
    }

    public void testFrameForwardNameApplicationScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext
                .setAttribute("paramMap", map, PageContext.APPLICATION_SCOPE);
        runMyTest("testFrameForwardNameApplicationScope", "");
    }

    public void testFrameForwardNamePropertyApplicationScope()
            throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.APPLICATION_SCOPE);
        runMyTest("testFrameForwardNamePropertyApplicationScope", "");
    }

    public void testFrameForwardNameSessionScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.SESSION_SCOPE);
        runMyTest("testFrameForwardNameSessionScope", "");
    }

    public void testFrameForwardNamePropertySessionScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testFrameForwardNamePropertySessionScope", "");
    }

    public void testFrameForwardNameRequestScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
        runMyTest("testFrameForwardNameRequestScope", "");
    }

    public void testFrameForwardNamePropertyRequestScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameForwardNamePropertyRequestScope", "");
    }


    public void testFrameForwardNoresize1() throws Exception {
        runMyTest("testFrameForwardNoresize1", "");
    }

    public void testFrameForwardNoresize2() throws Exception {
        runMyTest("testFrameForwardNoresize2", "");
    }

    public void testFrameForwardNoresize3() throws Exception {
        runMyTest("testFrameForwardNoresize3", "");
    }

    public void testFrameForwardNoresize4() throws Exception {
        runMyTest("testFrameForwardNoresize4", "");
    }

    public void testFrameForwardNoresize5() throws Exception {
        runMyTest("testFrameForwardNoresize5", "");
    }

    public void testFrameForwardNoresize6() throws Exception {
        runMyTest("testFrameForwardNoresize6", "");
    }

}
