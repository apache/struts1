/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestLinkTag8.java,v 1.5 2004/01/10 21:03:34 dgraham Exp $
 * $Revision: 1.5 $
 * $Date: 2004/01/10 21:03:34 $
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
 * <code>org.apache.struts.taglib.bean.FrameTag</code> class.
 *
 */
public class TestLinkTag8 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLinkTag8(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestLinkTag8.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLinkTag8.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestLinkTag8.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------
    public void testLinkPageOnblur() throws Exception {
        runMyTest("testLinkPageOnblur", "");
    }

    public void testLinkPageOnclick() throws Exception {
        runMyTest("testLinkPageOnclick", "");
    }

    public void testLinkPageOndblclick() throws Exception {
        runMyTest("testLinkPageOndblclick", "");
    }

    public void testLinkPageOnfocus() throws Exception {
        runMyTest("testLinkPageOnfocus", "");
    }

    public void testLinkPageOnkeydown() throws Exception {
        runMyTest("testLinkPageOnkeydown", "");
    }

    public void testLinkPageOnkeypress() throws Exception {
        runMyTest("testLinkPageOnkeypress", "");
    }

    public void testLinkPageOnkeyup() throws Exception {
        runMyTest("testLinkPageOnkeyup", "");
    }

    public void testLinkPageOnmousedown() throws Exception {
        runMyTest("testLinkPageOnmousedown", "");
    }

    public void testLinkPageOnmousemove() throws Exception {
        runMyTest("testLinkPageOnmousemove", "");
    }

    public void testLinkPageOnmouseout() throws Exception {
        runMyTest("testLinkPageOnmouseout", "");
    }

    public void testLinkPageOnmouseover() throws Exception {
        runMyTest("testLinkPageOnmouseover", "");
    }

    public void testLinkPageOnmouseup() throws Exception {
        runMyTest("testLinkPageOnmouseup", "");
    }

    public void testLinkPageParamIdParamNameNoScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.REQUEST_SCOPE);
        runMyTest("testLinkPageParamIdParamNameNoScope", "");
    }

    public void testLinkPageParamIdParamNameParamPropertyNoScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkPageParamIdParamNameParamPropertyNoScope", "");
    }

    public void testLinkPageParamIdParamNameApplicationScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkPageParamIdParamNameApplicationScope", "");
    }

    public void testLinkPageParamIdParamNameParamPropertyApplicationScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkPageParamIdParamNameParamPropertyApplicationScope", "");
    }

    public void testLinkPageParamIdParamNameSessionScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.SESSION_SCOPE);
        runMyTest("testLinkPageParamIdParamNameSessionScope", "");
    }

    public void testLinkPageParamIdParamNameParamPropertySessionScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.SESSION_SCOPE);
        runMyTest("testLinkPageParamIdParamNameParamPropertySessionScope", "");
    }

    public void testLinkPageParamIdParamNameRequestScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.REQUEST_SCOPE);
        runMyTest("testLinkPageParamIdParamNameRequestScope", "");
    }

    public void testLinkPageParamIdParamNameParamPropertyRequestScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkPageParamIdParamNameParamPropertyRequestScope", "");
    }


    public void testLinkPageStyle() throws Exception {
        runMyTest("testLinkPageStyle", "");
    }

    public void testLinkPageStyleClass() throws Exception {
        runMyTest("testLinkPageStyleClass", "");
    }

    public void testLinkPageStyleId() throws Exception {
        runMyTest("testLinkPageStyleId", "");
    }

    public void testLinkPageTabIndex() throws Exception {
        runMyTest("testLinkPageTabIndex", "");
    }

    public void testLinkPageTarget() throws Exception {
        runMyTest("testLinkPageTarget", "");
    }







    public void testLinkPageTitle() throws Exception {
        runMyTest("testLinkPageTitle", "");
    }

    public void testLinkPageTitleKey() throws Exception {
        runMyTest("testLinkPageTitleKey", "");
    }

    public void testLinkPageTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY, "Some_Token_Here", PageContext.SESSION_SCOPE);
        runMyTest("testLinkPageTransaction", "");
    }





}
