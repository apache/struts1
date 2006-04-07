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

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.html.ErrorsTag</code>
 * class.
 */
public class TestErrorsTag3 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestErrorsTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestErrorsTag3.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestErrorsTag3.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestErrorsTag3.jsp");
    }

    /*
     * Testing ErrorsTag.
     */
    public void testErrorsDefaultBundle0ErrorsName() throws Exception {
        runMyTest("testErrorsDefaultBundle0ErrorsName", "");
    }

    public void testErrorsDefaultBundle2ErrorsName() throws Exception {
        runMyTest("testErrorsDefaultBundle2ErrorsName", "");
    }

    public void testErrorsAlternateBundle0ErrorsName() throws Exception {
        runMyTest("testErrorsAlternateBundle0ErrorsName", "");
    }

    public void testErrorsAlternateBundle2ErrorsName() throws Exception {
        runMyTest("testErrorsAlternateBundle2ErrorsName", "");
    }

    public void testErrorsDefaultBundle0ErrorsName_fr() throws Exception {
        runMyTest("testErrorsDefaultBundle0ErrorsName_fr", "fr");
    }

    public void testErrorsDefaultBundle2ErrorsName_fr() throws Exception {
        runMyTest("testErrorsDefaultBundle2ErrorsName_fr", "fr");
    }

    public void testErrorsAlternateBundle0ErrorsName_fr() throws Exception {
        runMyTest("testErrorsAlternateBundle0ErrorsName_fr", "fr");
    }

    public void testErrorsAlternateBundle2ErrorsName_fr() throws Exception {
        runMyTest("testErrorsAlternateBundle2ErrorsName_fr", "fr");
    }


    public void testErrorsDefaultBundle0ErrorsLocaleName() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocaleName", "");
    }

    public void testErrorsDefaultBundle2ErrorsLocaleName() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocaleName", "");
    }

    public void testErrorsAlternateBundle0ErrorsLocaleName()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocaleName", "");
    }

    public void testErrorsAlternateBundle2ErrorsLocaleName()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocaleName", "");
    }

    public void testErrorsDefaultBundle0ErrorsLocaleName_fr()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocaleName_fr", "");
    }

    public void testErrorsDefaultBundle2ErrorsLocaleName_fr()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocaleName_fr", "");
    }

    public void testErrorsAlternateBundle0ErrorsLocaleName_fr()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocaleName_fr", "");
    }

    public void testErrorsAlternateBundle2ErrorsLocaleName_fr()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocaleName_fr", "");
    }


}
