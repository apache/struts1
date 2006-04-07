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
public class TestFrameTag3 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFrameTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestFrameTag3.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFrameTag3.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestFrameTag3.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testFrameAction() throws Exception {
        runMyTest("testFrameAction", "");
    }

    public void testFrameActionAnchor() throws Exception {
        runMyTest("testFrameActionAnchor", "");
    }

    public void testFrameActionFrameborder() throws Exception {
        runMyTest("testFrameActionFrameborder", "");
    }

    public void testFrameActionFrameName() throws Exception {
        runMyTest("testFrameActionFrameName", "");
    }

    public void testFrameActionLongdesc() throws Exception {
        runMyTest("testFrameActionLongdesc", "");
    }

    public void testFrameActionMarginheight() throws Exception {
        runMyTest("testFrameActionMarginheight", "");
    }

    public void testFrameActionMarginwidth() throws Exception {
        runMyTest("testFrameActionMarginwidth", "");
    }

    public void testFrameActionNameNoScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
        runMyTest("testFrameActionNameNoScope", "");
    }

    public void testFrameActionNamePropertyNoScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameActionNamePropertyNoScope", "");
    }

    public void testFrameActionNameApplicationScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext
                .setAttribute("paramMap", map, PageContext.APPLICATION_SCOPE);
        runMyTest("testFrameActionNameApplicationScope", "");
    }

    public void testFrameActionNamePropertyApplicationScope()
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
        runMyTest("testFrameActionNamePropertyApplicationScope", "");
    }

    public void testFrameActionNameSessionScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.SESSION_SCOPE);
        runMyTest("testFrameActionNameSessionScope", "");
    }

    public void testFrameActionNamePropertySessionScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.SESSION_SCOPE);
        runMyTest("testFrameActionNamePropertySessionScope", "");
    }

    public void testFrameActionNameRequestScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
        runMyTest("testFrameActionNameRequestScope", "");
    }

    public void testFrameActionNamePropertyRequestScope() throws Exception {
        HashMap map = new HashMap();
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        map.put("param4", "value4");
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
        pageContext.setAttribute("paramPropertyMap",
                sbft,
                PageContext.REQUEST_SCOPE);
        runMyTest("testFrameActionNamePropertyRequestScope", "");
    }


    public void testFrameActionNoresize1() throws Exception {
        runMyTest("testFrameActionNoresize1", "");
    }

    public void testFrameActionNoresize2() throws Exception {
        runMyTest("testFrameActionNoresize2", "");
    }

    public void testFrameActionNoresize3() throws Exception {
        runMyTest("testFrameActionNoresize3", "");
    }

    public void testFrameActionNoresize4() throws Exception {
        runMyTest("testFrameActionNoresize4", "");
    }

    public void testFrameActionNoresize5() throws Exception {
        runMyTest("testFrameActionNoresize5", "");
    }

    public void testFrameActionNoresize6() throws Exception {
        runMyTest("testFrameActionNoresize6", "");
    }

}
