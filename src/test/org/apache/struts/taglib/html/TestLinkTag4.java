/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestLinkTag4.java,v 1.4 2003/12/26 22:10:32 germuska Exp $
 * $Revision: 1.4 $
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
 * <code>org.apache.struts.taglib.html.FrameTag</code> class.
 *
 * @author James Mitchell
 */
public class TestLinkTag4 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLinkTag4(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestLinkTag4.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLinkTag4.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestLinkTag4.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------
    public void testLinkActionOnblur() throws Exception {
        runMyTest("testLinkActionOnblur", "");
    }

    public void testLinkActionOnclick() throws Exception {
        runMyTest("testLinkActionOnclick", "");
    }

    public void testLinkActionOndblclick() throws Exception {
        runMyTest("testLinkActionOndblclick", "");
    }

    public void testLinkActionOnfocus() throws Exception {
        runMyTest("testLinkActionOnfocus", "");
    }

    public void testLinkActionOnkeydown() throws Exception {
        runMyTest("testLinkActionOnkeydown", "");
    }

    public void testLinkActionOnkeypress() throws Exception {
        runMyTest("testLinkActionOnkeypress", "");
    }

    public void testLinkActionOnkeyup() throws Exception {
        runMyTest("testLinkActionOnkeyup", "");
    }

    public void testLinkActionOnmousedown() throws Exception {
        runMyTest("testLinkActionOnmousedown", "");
    }

    public void testLinkActionOnmousemove() throws Exception {
        runMyTest("testLinkActionOnmousemove", "");
    }

    public void testLinkActionOnmouseout() throws Exception {
        runMyTest("testLinkActionOnmouseout", "");
    }

    public void testLinkActionOnmouseover() throws Exception {
        runMyTest("testLinkActionOnmouseover", "");
    }

    public void testLinkActionOnmouseup() throws Exception {
        runMyTest("testLinkActionOnmouseup", "");
    }

    public void testLinkActionParamIdParamNameNoScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionParamIdParamNameNoScope", "");
    }

    public void testLinkActionParamIdParamNameParamPropertyNoScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionParamIdParamNameParamPropertyNoScope", "");
    }

    public void testLinkActionParamIdParamNameApplicationScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkActionParamIdParamNameApplicationScope", "");
    }

    public void testLinkActionParamIdParamNameParamPropertyApplicationScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkActionParamIdParamNameParamPropertyApplicationScope", "");
    }

    public void testLinkActionParamIdParamNameSessionScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.SESSION_SCOPE);
        runMyTest("testLinkActionParamIdParamNameSessionScope", "");
    }

    public void testLinkActionParamIdParamNameParamPropertySessionScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.SESSION_SCOPE);
        runMyTest("testLinkActionParamIdParamNameParamPropertySessionScope", "");
    }

    public void testLinkActionParamIdParamNameRequestScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionParamIdParamNameRequestScope", "");
    }

    public void testLinkActionParamIdParamNameParamPropertyRequestScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionParamIdParamNameParamPropertyRequestScope", "");
    }


    public void testLinkActionStyle() throws Exception {
        runMyTest("testLinkActionStyle", "");
    }

    public void testLinkActionStyleClass() throws Exception {
        runMyTest("testLinkActionStyleClass", "");
    }

    public void testLinkActionStyleId() throws Exception {
        runMyTest("testLinkActionStyleId", "");
    }

    public void testLinkActionTabIndex() throws Exception {
        runMyTest("testLinkActionTabIndex", "");
    }

    public void testLinkActionTarget() throws Exception {
        runMyTest("testLinkActionTarget", "");
    }







    public void testLinkActionTitle() throws Exception {
        runMyTest("testLinkActionTitle", "");
    }

    public void testLinkActionTitleKey() throws Exception {
        runMyTest("testLinkActionTitleKey", "");
    }

    public void testLinkActionTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY, "Some_Token_Here", PageContext.SESSION_SCOPE);
        runMyTest("testLinkActionTransaction", "");
    }





}

