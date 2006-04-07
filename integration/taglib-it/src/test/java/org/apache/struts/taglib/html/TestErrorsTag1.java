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
public class TestErrorsTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestErrorsTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestErrorsTag1.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestErrorsTag1.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestErrorsTag1.jsp");
    }

    /*
     * Testing ErrorsTag.
     */
    public void testErrorsDefaultBundle0Errors() throws Exception {
        runMyTest("testErrorsDefaultBundle0Errors", "");
    }

    public void testErrorsDefaultBundle2Errors() throws Exception {
        runMyTest("testErrorsDefaultBundle2Errors", "");
    }

    public void testErrorsAlternateBundle0Errors() throws Exception {
        runMyTest("testErrorsAlternateBundle0Errors", "");
    }

    public void testErrorsAlternateBundle2Errors() throws Exception {
        runMyTest("testErrorsAlternateBundle2Errors", "");
    }

    public void testErrorsDefaultBundle0Errors_fr() throws Exception {
        runMyTest("testErrorsDefaultBundle0Errors_fr", "fr");
    }

    public void testErrorsDefaultBundle2Errors_fr() throws Exception {
        runMyTest("testErrorsDefaultBundle2Errors_fr", "fr");
    }

    public void testErrorsAlternateBundle0Errors_fr() throws Exception {
        runMyTest("testErrorsAlternateBundle0Errors_fr", "fr");
    }

    public void testErrorsAlternateBundle2Errors_fr() throws Exception {
        runMyTest("testErrorsAlternateBundle2Errors_fr", "fr");
    }


    public void testErrorsDefaultBundle0ErrorsLocale() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocale", "");
    }

    public void testErrorsDefaultBundle2ErrorsLocale() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocale", "");
    }

    public void testErrorsAlternateBundle0ErrorsLocale() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocale", "");
    }

    public void testErrorsAlternateBundle2ErrorsLocale() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocale", "");
    }

    public void testErrorsDefaultBundle0ErrorsLocale_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocale_fr", "");
    }

    public void testErrorsDefaultBundle2ErrorsLocale_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocale_fr", "");
    }

    public void testErrorsAlternateBundle0ErrorsLocale_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocale_fr", "");
    }

    public void testErrorsAlternateBundle2ErrorsLocale_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocale_fr", "");
    }
}
