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
public class TestMultiboxTag3 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestMultiboxTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestMultiboxTag3.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMultiboxTag3.class);
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
    }

    /*
     * Testing MultiboxTag.
     */
    public void testMultiboxPropertyFalse() throws Exception {
        runMyTest("testMultiboxPropertyFalse", "");
    }

    public void testMultiboxPropertyFalseAccesskey() throws Exception {
        runMyTest("testMultiboxPropertyFalseAccesskey", "");
    }

    public void testMultiboxPropertyFalseAlt() throws Exception {
        runMyTest("testMultiboxPropertyFalseAlt", "");
    }

    public void testMultiboxPropertyFalseAltKey1() throws Exception {
        runMyTest("testMultiboxPropertyFalseAltKey1", "");
    }

    public void testMultiboxPropertyFalseAltKey2() throws Exception {
        runMyTest("testMultiboxPropertyFalseAltKey2", "");
    }

    public void testMultiboxPropertyFalseAltKey_fr1() throws Exception {
        runMyTest("testMultiboxPropertyFalseAltKey1_fr", "fr");
    }

    public void testMultiboxPropertyFalseAltKey_fr2() throws Exception {
        runMyTest("testMultiboxPropertyFalseAltKey2_fr", "fr");
    }

    public void testMultiboxPropertyFalseDisabled_True() throws Exception {
        runMyTest("testMultiboxPropertyFalseDisabled_True", "");
    }

    public void testMultiboxPropertyFalseDisabled_False1() throws Exception {
        runMyTest("testMultiboxPropertyFalseDisabled_False1", "");
    }

    public void testMultiboxPropertyFalseDisabled_False2() throws Exception {
        runMyTest("testMultiboxPropertyFalseDisabled_False2", "");
    }

    public void testMultiboxPropertyFalseOnblur() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnblur", "");
    }

    public void testMultiboxPropertyFalseOnchange() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnchange", "");
    }

    public void testMultiboxPropertyFalseOnclick() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnclick", "");
    }

    public void testMultiboxPropertyFalseOndblclick() throws Exception {
        runMyTest("testMultiboxPropertyFalseOndblclick", "");
    }

    public void testMultiboxPropertyFalseOnfocus() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnfocus", "");
    }

    public void testMultiboxPropertyFalseOnkeydown() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnkeydown", "");
    }

    public void testMultiboxPropertyFalseOnkeypress() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnkeypress", "");
    }

    public void testMultiboxPropertyFalseOnkeyup() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnkeyup", "");
    }

    public void testMultiboxPropertyFalseOnmousedown() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnmousedown", "");
    }

    public void testMultiboxPropertyFalseOnmousemove() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnmousemove", "");
    }

    public void testMultiboxPropertyFalseOnmouseout() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnmouseout", "");
    }

    public void testMultiboxPropertyFalseOnmouseover() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnmouseover", "");
    }

    public void testMultiboxPropertyFalseOnmouseup() throws Exception {
        runMyTest("testMultiboxPropertyFalseOnmouseup", "");
    }

}
