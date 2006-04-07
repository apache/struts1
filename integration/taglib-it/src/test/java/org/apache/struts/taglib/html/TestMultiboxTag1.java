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
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.MultiboxTag</code>
 * class. NOTE - These tests were separated into 4 files each because of the
 * size of the jsp. (not playing well with Tomcat 3.3
 *
 * These tests are numbered as such:
 *
 * 1 thru 4 test a single checkbox TestMultiboxTag1 - These test validate true
 * (a value was in the array) on our form. TestMultiboxTag2 - Same as 1, but
 * using BodyContent instead of value attribute
 *
 * TestMultiboxTag3 - These test validate true (a value was in the array) on
 * our form. TestMultiboxTag4 - Same as 3, but using BodyContent instead of
 * value attribute
 *
 * 5 thru 8 test multiple checkboxes TestMultiboxTag5 - These test validate
 * true (a value was in the array) on our form. TestMultiboxTag6 - Same as 5,
 * but using BodyContent instead of value attribute
 *
 * TestMultiboxTag7 - These test validate true (a value was in the array) on
 * our form. TestMultiboxTag8 - Same as 7, but using BodyContent instead of
 * value attribute
 */
public class TestMultiboxTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestMultiboxTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMultiboxTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMultiboxTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);

        String[] s = new String[7];
        for (int i = 1; i < 7; i++) {
            s[i] = "value" + i;
        }
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(s);

        pageContext.setAttribute(Constants.BEAN_KEY,
                sbft,
                PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestMultiboxTag1.jsp");
    }

    /*
     * Testing MultiboxTag.
     */
    public void testMultiboxPropertyTrue() throws Exception {
        runMyTest("testMultiboxPropertyTrue", "");
    }

    public void testMultiboxPropertyTrueAccesskey() throws Exception {
        runMyTest("testMultiboxPropertyTrueAccesskey", "");
    }

    public void testMultiboxPropertyTrueAlt() throws Exception {
        runMyTest("testMultiboxPropertyTrueAlt", "");
    }

    public void testMultiboxPropertyTrueAltKey1() throws Exception {
        runMyTest("testMultiboxPropertyTrueAltKey1", "");
    }

    public void testMultiboxPropertyTrueAltKey2() throws Exception {
        runMyTest("testMultiboxPropertyTrueAltKey2", "");
    }

    public void testMultiboxPropertyTrueAltKey3() throws Exception {
        runMyTest("testMultiboxPropertyTrueAltKey3", "");
    }

    public void testMultiboxPropertyTrueAltKey_fr1() throws Exception {
        runMyTest("testMultiboxPropertyTrueAltKey1_fr", "fr");
    }

    public void testMultiboxPropertyTrueAltKey_fr2() throws Exception {
        runMyTest("testMultiboxPropertyTrueAltKey2_fr", "fr");
    }

    public void testMultiboxPropertyTrueDisabled_True() throws Exception {
        runMyTest("testMultiboxPropertyTrueDisabled_True", "");
    }

    public void testMultiboxPropertyTrueDisabled_False1() throws Exception {
        runMyTest("testMultiboxPropertyTrueDisabled_False1", "");
    }

    public void testMultiboxPropertyTrueDisabled_False2() throws Exception {
        runMyTest("testMultiboxPropertyTrueDisabled_False2", "");
    }

    public void testMultiboxPropertyTrueOnblur() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnblur", "");
    }

    public void testMultiboxPropertyTrueOnchange() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnchange", "");
    }

    public void testMultiboxPropertyTrueOnclick() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnclick", "");
    }

    public void testMultiboxPropertyTrueOndblclick() throws Exception {
        runMyTest("testMultiboxPropertyTrueOndblclick", "");
    }

    public void testMultiboxPropertyTrueOnfocus() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnfocus", "");
    }

    public void testMultiboxPropertyTrueOnkeydown() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnkeydown", "");
    }

    public void testMultiboxPropertyTrueOnkeypress() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnkeypress", "");
    }

    public void testMultiboxPropertyTrueOnkeyup() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnkeyup", "");
    }

    public void testMultiboxPropertyTrueOnmousedown() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnmousedown", "");
    }

    public void testMultiboxPropertyTrueOnmousemove() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnmousemove", "");
    }

    public void testMultiboxPropertyTrueOnmouseout() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnmouseout", "");
    }

    public void testMultiboxPropertyTrueOnmouseover() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnmouseover", "");
    }

    public void testMultiboxPropertyTrueOnmouseup() throws Exception {
        runMyTest("testMultiboxPropertyTrueOnmouseup", "");
    }

}
