/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestMultiboxTag3.java,v 1.3 2003/12/26 22:10:32 germuska Exp $
 * $Revision: 1.3 $
 * $Date: 2003/12/26 22:10:32 $
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
 *    any, must include the following acknowlegement:
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
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
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
 * <code>org.apache.struts.taglib.html.MultiboxTag</code> class.
 *  NOTE - These tests were separated into 4 files each because of the
 *         size of the jsp. (not playing well with Tomcat 3.3
 *
 *  These tests are numbered as such:
 *
 *  1 thru 4 test a single checkbox
 *  TestMultiboxTag1 - These test validate true (a value was in the array) on our form.
 *  TestMultiboxTag2 - Same as 1, but using BodyContent instead of value attribute
 *
 *  TestMultiboxTag3 - These test validate true (a value was in the array) on our form.
 *  TestMultiboxTag4 - Same as 3, but using BodyContent instead of value attribute
 *
 *  5 thru 8 test multiple checkboxes
 *  TestMultiboxTag5 - These test validate true (a value was in the array) on our form.
 *  TestMultiboxTag6 - Same as 5, but using BodyContent instead of value attribute
 *
 *  TestMultiboxTag7 - These test validate true (a value was in the array) on our form.
 *  TestMultiboxTag8 - Same as 7, but using BodyContent instead of value attribute
 *
 * @author James Mitchell
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
        junit.awtui.TestRunner.main(new String[] {TestMultiboxTag3.class.getName()});
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
                for(int i = 1; i < 7; i++){
                        s[i] = "value" + i;
                }
                SimpleBeanForTesting sbft = new SimpleBeanForTesting(s);

        pageContext.setAttribute(Constants.BEAN_KEY, sbft, PageContext.REQUEST_SCOPE);
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
