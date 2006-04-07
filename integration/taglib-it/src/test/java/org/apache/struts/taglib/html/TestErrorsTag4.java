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
public class TestErrorsTag4 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestErrorsTag4(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestErrorsTag4.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestErrorsTag4.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        pageContext
                .forward("/org/apache/struts/taglib/html/TestErrorsTag4.jsp");
    }

    /*
    * Testing ErrorsTag.
    */

    public void testErrorsDefaultBundle0ErrorsNameProperty()
            throws Exception {
        runMyTest("testErrorsDefaultBundle0ErrorsNameProperty", "");
    }

    public void testErrorsDefaultBundle2ErrorsNameProperty()
            throws Exception {
        runMyTest("testErrorsDefaultBundle2ErrorsNameProperty", "");
    }

    public void testErrorsAlternateBundle0ErrorsNameProperty()
            throws Exception {
        runMyTest("testErrorsAlternateBundle0ErrorsNameProperty", "");
    }

    public void testErrorsAlternateBundle2ErrorsNameProperty()
            throws Exception {
        runMyTest("testErrorsAlternateBundle2ErrorsNameProperty", "");
    }

    public void testErrorsDefaultBundle0ErrorsNameProperty_fr()
            throws Exception {
        runMyTest("testErrorsDefaultBundle0ErrorsNameProperty_fr", "fr");
    }

    public void testErrorsDefaultBundle2ErrorsNameProperty_fr()
            throws Exception {
        runMyTest("testErrorsDefaultBundle2ErrorsNameProperty_fr", "fr");
    }

    public void testErrorsAlternateBundle0ErrorsNameProperty_fr()
            throws Exception {
        runMyTest("testErrorsAlternateBundle0ErrorsNameProperty_fr", "fr");
    }

    public void testErrorsAlternateBundle2ErrorsNameProperty_fr()
            throws Exception {
        runMyTest("testErrorsAlternateBundle2ErrorsNameProperty_fr", "fr");
    }


    public void testErrorsDefaultBundle0ErrorsLocaleNameProperty()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocaleNameProperty", "");
    }

    public void testErrorsDefaultBundle2ErrorsLocaleNameProperty()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocaleNameProperty", "");
    }

    public void testErrorsAlternateBundle0ErrorsLocaleNameProperty()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocaleNameProperty", "");
    }

    public void testErrorsAlternateBundle2ErrorsLocaleNameProperty()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocaleNameProperty", "");
    }

    public void testErrorsDefaultBundle0ErrorsLocaleNameProperty_fr()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocaleNameProperty_fr", "");
    }

    public void testErrorsDefaultBundle2ErrorsLocaleNameProperty_fr()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocaleNameProperty_fr", "");
    }

    public void testErrorsAlternateBundle0ErrorsLocaleNameProperty_fr()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocaleNameProperty_fr",
                "");
    }

    public void testErrorsAlternateBundle2ErrorsLocaleNameProperty_fr()
            throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY",
                new Locale("fr", "fr"),
                PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocaleNameProperty_fr",
                "");
    }


    public void testErrorsCustomResources() throws Exception {
        runMyTest("testErrorsCustomResources", "");
    }

    public void testErrorsCustomResourcesAltBundle() throws Exception {
        runMyTest("testErrorsCustomResourcesAltBundle", "");
    }

}
