/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestCheckboxTag3.java,v 1.8 2004/01/13 12:48:54 husted Exp $
 * $Revision: 1.8 $
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
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.html.CheckboxTag</code> class.
 *
 */
public class TestCheckboxTag3 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestCheckboxTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestCheckboxTag3.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestCheckboxTag3.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting(false), PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestCheckboxTag3.jsp");
    }

    /*
     * Testing CheckboxTag.
     */
    public void testCheckboxPropertybooleanFalse() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalse", "");
        }
    public void testCheckboxPropertybooleanFalseAccesskey() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAccesskey", "");
        }
    public void testCheckboxPropertybooleanFalseAlt() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAlt", "");
        }
    public void testCheckboxPropertybooleanFalseAltKey1() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAltKey1", "");
        }
    public void testCheckboxPropertybooleanFalseAltKey2() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAltKey2", "");
        }
    public void testCheckboxPropertybooleanFalseAltKey_fr1() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAltKey1_fr", "fr");
        }
    public void testCheckboxPropertybooleanFalseAltKey_fr2() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseAltKey2_fr", "fr");
        }
    public void testCheckboxPropertybooleanFalseDisabled_True() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseDisabled_True", "");
        }
    public void testCheckboxPropertybooleanFalseDisabled_False1() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseDisabled_False1", "");
        }
    public void testCheckboxPropertybooleanFalseDisabled_False2() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseDisabled_False2", "");
        }
    public void testCheckboxPropertybooleanFalseOnblur() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnblur", "");
        }

    public void testCheckboxPropertybooleanFalseOnchange() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnchange", "");
        }

    public void testCheckboxPropertybooleanFalseOnclick() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnclick", "");
        }

    public void testCheckboxPropertybooleanFalseOndblclick() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOndblclick", "");
        }

    public void testCheckboxPropertybooleanFalseOnfocus() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnfocus", "");
        }

    public void testCheckboxPropertybooleanFalseOnkeydown() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnkeydown", "");
        }

    public void testCheckboxPropertybooleanFalseOnkeypress() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnkeypress", "");
        }

    public void testCheckboxPropertybooleanFalseOnkeyup() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnkeyup", "");
        }

    public void testCheckboxPropertybooleanFalseOnmousedown() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmousedown", "");
        }

    public void testCheckboxPropertybooleanFalseOnmousemove() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmousemove", "");
        }

    public void testCheckboxPropertybooleanFalseOnmouseout() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmouseout", "");
        }

    public void testCheckboxPropertybooleanFalseOnmouseover() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmouseover", "");
        }

    public void testCheckboxPropertybooleanFalseOnmouseup() throws Exception {
        runMyTest("testCheckboxPropertybooleanFalseOnmouseup", "");
        }

}
