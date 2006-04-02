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

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.SubmitTag</code>
 * class.
 */
public class TestSubmitTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestSubmitTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestSubmitTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestSubmitTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestSubmitTag1.jsp");
    }

    /*
     * Testing SubmitTag.
     */
    public void testSubmitProperty() throws Exception {
        runMyTest("testSubmitProperty", "");
    }

    public void testSubmitPropertyAccesskey() throws Exception {
        runMyTest("testSubmitPropertyAccesskey", "");
    }

    public void testSubmitPropertyAlt() throws Exception {
        runMyTest("testSubmitPropertyAlt", "");
    }

    public void testSubmitPropertyAltKey1() throws Exception {
        runMyTest("testSubmitPropertyAltKey1", "");
    }

    public void testSubmitPropertyAltKey2() throws Exception {
        runMyTest("testSubmitPropertyAltKey2", "");
    }

    public void testSubmitPropertyAltKey3() throws Exception {
        runMyTest("testSubmitPropertyAltKey3", "");
    }

    public void testSubmitPropertyAltKey_fr1() throws Exception {
        runMyTest("testSubmitPropertyAltKey1_fr", "fr");
    }

    public void testSubmitPropertyAltKey_fr2() throws Exception {
        runMyTest("testSubmitPropertyAltKey2_fr", "fr");
    }

    public void testSubmitPropertyDisabled_True() throws Exception {
        runMyTest("testSubmitPropertyDisabled_True", "");
    }

    public void testSubmitPropertyDisabled_False1() throws Exception {
        runMyTest("testSubmitPropertyDisabled_False1", "");
    }

    public void testSubmitPropertyDisabled_False2() throws Exception {
        runMyTest("testSubmitPropertyDisabled_False2", "");
    }

    public void testSubmitPropertyOnblur() throws Exception {
        runMyTest("testSubmitPropertyOnblur", "");
    }

    public void testSubmitPropertyOnchange() throws Exception {
        runMyTest("testSubmitPropertyOnchange", "");
    }

    public void testSubmitPropertyOnclick() throws Exception {
        runMyTest("testSubmitPropertyOnclick", "");
    }

    public void testSubmitPropertyOndblclick() throws Exception {
        runMyTest("testSubmitPropertyOndblclick", "");
    }

    public void testSubmitPropertyOnfocus() throws Exception {
        runMyTest("testSubmitPropertyOnfocus", "");
    }

    public void testSubmitPropertyOnkeydown() throws Exception {
        runMyTest("testSubmitPropertyOnkeydown", "");
    }

    public void testSubmitPropertyOnkeypress() throws Exception {
        runMyTest("testSubmitPropertyOnkeypress", "");
    }

    public void testSubmitPropertyOnkeyup() throws Exception {
        runMyTest("testSubmitPropertyOnkeyup", "");
    }

    public void testSubmitPropertyOnmousedown() throws Exception {
        runMyTest("testSubmitPropertyOnmousedown", "");
    }

    public void testSubmitPropertyOnmousemove() throws Exception {
        runMyTest("testSubmitPropertyOnmousemove", "");
    }

    public void testSubmitPropertyOnmouseout() throws Exception {
        runMyTest("testSubmitPropertyOnmouseout", "");
    }

    public void testSubmitPropertyOnmouseover() throws Exception {
        runMyTest("testSubmitPropertyOnmouseover", "");
    }

    public void testSubmitPropertyOnmouseup() throws Exception {
        runMyTest("testSubmitPropertyOnmouseup", "");
    }

}
