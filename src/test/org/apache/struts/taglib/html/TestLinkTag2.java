/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestLinkTag2.java,v 1.5 2004/01/10 21:03:34 dgraham Exp $
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
 * <code>org.apache.struts.taglib.html.FrameTag</code> class.
 *
 */
public class TestLinkTag2 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLinkTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestLinkTag2.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLinkTag2.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestLinkTag2.jsp");
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------
    public void testLinkForwardOnblur() throws Exception {
        runMyTest("testLinkForwardOnblur", "");
    }

    public void testLinkForwardOnclick() throws Exception {
        runMyTest("testLinkForwardOnclick", "");
    }

    public void testLinkForwardOndblclick() throws Exception {
        runMyTest("testLinkForwardOndblclick", "");
    }

    public void testLinkForwardOnfocus() throws Exception {
        runMyTest("testLinkForwardOnfocus", "");
    }

    public void testLinkForwardOnkeydown() throws Exception {
        runMyTest("testLinkForwardOnkeydown", "");
    }

    public void testLinkForwardOnkeypress() throws Exception {
        runMyTest("testLinkForwardOnkeypress", "");
    }

    public void testLinkForwardOnkeyup() throws Exception {
        runMyTest("testLinkForwardOnkeyup", "");
    }

    public void testLinkForwardOnmousedown() throws Exception {
        runMyTest("testLinkForwardOnmousedown", "");
    }

    public void testLinkForwardOnmousemove() throws Exception {
        runMyTest("testLinkForwardOnmousemove", "");
    }

    public void testLinkForwardOnmouseout() throws Exception {
        runMyTest("testLinkForwardOnmouseout", "");
    }

    public void testLinkForwardOnmouseover() throws Exception {
        runMyTest("testLinkForwardOnmouseover", "");
    }

    public void testLinkForwardOnmouseup() throws Exception {
        runMyTest("testLinkForwardOnmouseup", "");
    }

    public void testLinkForwardParamIdParamNameNoScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.REQUEST_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameNoScope", "");
    }

    public void testLinkForwardParamIdParamNameParamPropertyNoScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameParamPropertyNoScope", "");
    }

    public void testLinkForwardParamIdParamNameApplicationScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameApplicationScope", "");
    }

    public void testLinkForwardParamIdParamNameParamPropertyApplicationScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameParamPropertyApplicationScope", "");
    }

    public void testLinkForwardParamIdParamNameSessionScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.SESSION_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameSessionScope", "");
    }

    public void testLinkForwardParamIdParamNameParamPropertySessionScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.SESSION_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameParamPropertySessionScope", "");
    }

    public void testLinkForwardParamIdParamNameRequestScope() throws Exception {
                pageContext.setAttribute("paramName", "paramValue", PageContext.REQUEST_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameRequestScope", "");
    }

    public void testLinkForwardParamIdParamNameParamPropertyRequestScope() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
                pageContext.setAttribute("testingParamProperty", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkForwardParamIdParamNameParamPropertyRequestScope", "");
    }


    public void testLinkForwardStyle() throws Exception {
        runMyTest("testLinkForwardStyle", "");
    }

    public void testLinkForwardStyleClass() throws Exception {
        runMyTest("testLinkForwardStyleClass", "");
    }

    public void testLinkForwardStyleId() throws Exception {
        runMyTest("testLinkForwardStyleId", "");
    }

    public void testLinkForwardTabIndex() throws Exception {
        runMyTest("testLinkForwardTabIndex", "");
    }

    public void testLinkForwardTarget() throws Exception {
        runMyTest("testLinkForwardTarget", "");
    }







    public void testLinkForwardTitle() throws Exception {
        runMyTest("testLinkForwardTitle", "");
    }

    public void testLinkForwardTitleKey() throws Exception {
        runMyTest("testLinkForwardTitleKey", "");
    }

    public void testLinkForwardTransaction() throws Exception {
        pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY, "Some_Token_Here", PageContext.SESSION_SCOPE);
        runMyTest("testLinkForwardTransaction", "");
    }





}
