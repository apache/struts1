/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestErrorsTag2.java,v 1.9 2004/01/13 12:48:54 husted Exp $
 * $Revision: 1.9 $
 * $Date: 2004/01/13 12:48:54 $
 *
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.struts.taglib.html;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.html.ErrorsTag</code> class.
 *
 */
public class TestErrorsTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestErrorsTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestErrorsTag2.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestErrorsTag2.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestErrorsTag2.jsp");
    }


    /*
     * Testing ErrorsTag.
     */

    public void testErrorsDefaultBundle0ErrorsProperty() throws Exception {
        runMyTest("testErrorsDefaultBundle0ErrorsProperty", "");
        }
    public void testErrorsDefaultBundle2ErrorsProperty() throws Exception {
        runMyTest("testErrorsDefaultBundle2ErrorsProperty", "");
        }

    public void testErrorsAlternateBundle0ErrorsProperty() throws Exception {
        runMyTest("testErrorsAlternateBundle0ErrorsProperty", "");
        }
    public void testErrorsAlternateBundle2ErrorsProperty() throws Exception {
        runMyTest("testErrorsAlternateBundle2ErrorsProperty", "");
        }

    public void testErrorsDefaultBundle0ErrorsProperty_fr() throws Exception {
        runMyTest("testErrorsDefaultBundle0ErrorsProperty_fr", "fr");
        }
    public void testErrorsDefaultBundle2ErrorsProperty_fr() throws Exception {
        runMyTest("testErrorsDefaultBundle2ErrorsProperty_fr", "fr");
        }

    public void testErrorsAlternateBundle0ErrorsProperty_fr() throws Exception {
        runMyTest("testErrorsAlternateBundle0ErrorsProperty_fr", "fr");
        }
    public void testErrorsAlternateBundle2ErrorsProperty_fr() throws Exception {
        runMyTest("testErrorsAlternateBundle2ErrorsProperty_fr", "fr");
        }



    public void testErrorsDefaultBundle0ErrorsLocaleProperty() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocaleProperty", "");
        }
    public void testErrorsDefaultBundle2ErrorsLocaleProperty() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocaleProperty", "");
        }

    public void testErrorsAlternateBundle0ErrorsLocaleProperty() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocaleProperty", "");
        }
    public void testErrorsAlternateBundle2ErrorsLocaleProperty() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocaleProperty", "");
        }

    public void testErrorsDefaultBundle0ErrorsLocaleProperty_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocaleProperty_fr", "");
        }
    public void testErrorsDefaultBundle2ErrorsLocaleProperty_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocaleProperty_fr", "");
        }

    public void testErrorsAlternateBundle0ErrorsLocaleProperty_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocaleProperty_fr", "");
        }
    public void testErrorsAlternateBundle2ErrorsLocaleProperty_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocaleProperty_fr", "");
        }







    public void testErrorsDefaultBundle0ErrorsNameProperty() throws Exception {
        runMyTest("testErrorsDefaultBundle0ErrorsNameProperty", "");
        }
    public void testErrorsDefaultBundle2ErrorsNameProperty() throws Exception {
        runMyTest("testErrorsDefaultBundle2ErrorsNameProperty", "");
        }

    public void testErrorsAlternateBundle0ErrorsNameProperty() throws Exception {
        runMyTest("testErrorsAlternateBundle0ErrorsNameProperty", "");
        }
    public void testErrorsAlternateBundle2ErrorsNameProperty() throws Exception {
        runMyTest("testErrorsAlternateBundle2ErrorsNameProperty", "");
        }

    public void testErrorsDefaultBundle0ErrorsNameProperty_fr() throws Exception {
        runMyTest("testErrorsDefaultBundle0ErrorsNameProperty_fr", "fr");
        }
    public void testErrorsDefaultBundle2ErrorsNameProperty_fr() throws Exception {
        runMyTest("testErrorsDefaultBundle2ErrorsNameProperty_fr", "fr");
        }

    public void testErrorsAlternateBundle0ErrorsNameProperty_fr() throws Exception {
        runMyTest("testErrorsAlternateBundle0ErrorsNameProperty_fr", "fr");
        }
    public void testErrorsAlternateBundle2ErrorsNameProperty_fr() throws Exception {
        runMyTest("testErrorsAlternateBundle2ErrorsNameProperty_fr", "fr");
        }



    public void testErrorsDefaultBundle0ErrorsLocaleNameProperty() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocaleNameProperty", "");
        }
    public void testErrorsDefaultBundle2ErrorsLocaleNameProperty() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocaleNameProperty", "");
        }

    public void testErrorsAlternateBundle0ErrorsLocaleNameProperty() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocaleNameProperty", "");
        }
    public void testErrorsAlternateBundle2ErrorsLocaleNameProperty() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocaleNameProperty", "");
        }

    public void testErrorsDefaultBundle0ErrorsLocaleNameProperty_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle0ErrorsLocaleNameProperty_fr", "");
        }
    public void testErrorsDefaultBundle2ErrorsLocaleNameProperty_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsDefaultBundle2ErrorsLocaleNameProperty_fr", "");
        }

    public void testErrorsAlternateBundle0ErrorsLocaleNameProperty_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle0ErrorsLocaleNameProperty_fr", "");
        }
    public void testErrorsAlternateBundle2ErrorsLocaleNameProperty_fr() throws Exception {
        pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testErrorsAlternateBundle2ErrorsLocaleNameProperty_fr", "");
        }



}
