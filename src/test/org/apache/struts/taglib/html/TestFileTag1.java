/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestFileTag1.java,v 1.9 2004/03/14 06:23:40 sraeburn Exp $
 * $Revision: 1.9 $
 * $Date: 2004/03/14 06:23:40 $
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

import java.util.Locale;

import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.html.FileTag</code> class.
 *
 */
public class TestFileTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFileTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestFileTag1.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFileTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestFileTag1.jsp");
    }

    /*
     * Testing FileTag.
     */
    public void testFileProperty() throws Exception {
        runMyTest("testFileProperty", "");
        }
    public void testFilePropertyAccept() throws Exception {
        runMyTest("testFilePropertyAccept", "");
        }
    public void testFilePropertyAccesskey() throws Exception {
        runMyTest("testFilePropertyAccesskey", "");
        }
    public void testFilePropertyAlt() throws Exception {
        runMyTest("testFilePropertyAlt", "");
        }
    public void testFilePropertyAltKey1() throws Exception {
        runMyTest("testFilePropertyAltKey1", "");
        }
    public void testFilePropertyAltKey2() throws Exception {
        runMyTest("testFilePropertyAltKey2", "");
        }
    public void testFilePropertyAltKey_fr1() throws Exception {
        runMyTest("testFilePropertyAltKey1_fr", "fr");
        }
    public void testFilePropertyAltKey_fr2() throws Exception {
        runMyTest("testFilePropertyAltKey2_fr", "fr");
        }
    public void testFilePropertyDisabled_True() throws Exception {
        runMyTest("testFilePropertyDisabled_True", "");
        }
    public void testFilePropertyDisabled_False1() throws Exception {
        runMyTest("testFilePropertyDisabled_False1", "");
        }
    public void testFilePropertyDisabled_False2() throws Exception {
        runMyTest("testFilePropertyDisabled_False2", "");
        }
    public void testFilePropertyOnblur() throws Exception {
        runMyTest("testFilePropertyOnblur", "");
        }

    public void testFilePropertyOnchange() throws Exception {
        runMyTest("testFilePropertyOnchange", "");
        }

    public void testFilePropertyOnclick() throws Exception {
        runMyTest("testFilePropertyOnclick", "");
        }

    public void testFilePropertyOndblclick() throws Exception {
        runMyTest("testFilePropertyOndblclick", "");
        }

    public void testFilePropertyOnfocus() throws Exception {
        runMyTest("testFilePropertyOnfocus", "");
        }

    public void testFilePropertyOnkeydown() throws Exception {
        runMyTest("testFilePropertyOnkeydown", "");
        }

    public void testFilePropertyOnkeypress() throws Exception {
        runMyTest("testFilePropertyOnkeypress", "");
        }

    public void testFilePropertyOnkeyup() throws Exception {
        runMyTest("testFilePropertyOnkeyup", "");
        }

    public void testFilePropertyOnmousedown() throws Exception {
        runMyTest("testFilePropertyOnmousedown", "");
        }

    public void testFilePropertyOnmousemove() throws Exception {
        runMyTest("testFilePropertyOnmousemove", "");
        }

    public void testFilePropertyOnmouseout() throws Exception {
        runMyTest("testFilePropertyOnmouseout", "");
        }

    public void testFilePropertyOnmouseover() throws Exception {
        runMyTest("testFilePropertyOnmouseover", "");
        }

    public void testFilePropertyOnmouseup() throws Exception {
        runMyTest("testFilePropertyOnmouseup", "");
        }

}
