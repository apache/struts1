/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestImgTag8.java,v 1.5 2004/01/10 21:03:35 dgraham Exp $
 * $Revision: 1.5 $
 * $Date: 2004/01/10 21:03:35 $
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
 * <code>org.apache.struts.taglib.html.ImgTag</code> class.
 *
 */
public class TestImgTag8 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag8(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestImgTag8.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag8.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestImgTag8.jsp");
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgSrcKeyOnclick() throws Exception {
        runMyTest("testImgSrcKeyOnclick", "");
    }

    public void testImgSrcKeyOndblclick() throws Exception {
        runMyTest("testImgSrcKeyOndblclick", "");
    }

    public void testImgSrcKeyOnkeydown() throws Exception {
        runMyTest("testImgSrcKeyOnkeydown", "");
    }

    public void testImgSrcKeyOnkeypress() throws Exception {
        runMyTest("testImgSrcKeyOnkeypress", "");
    }

    public void testImgSrcKeyOnkeyup() throws Exception {
        runMyTest("testImgSrcKeyOnkeyup", "");
    }

    public void testImgSrcKeyOnmousedown() throws Exception {
        runMyTest("testImgSrcKeyOnmousedown", "");
    }

    public void testImgSrcKeyOnmousemove() throws Exception {
        runMyTest("testImgSrcKeyOnmousemove", "");
    }

    public void testImgSrcKeyOnmouseout() throws Exception {
        runMyTest("testImgSrcKeyOnmouseout", "");
    }

    public void testImgSrcKeyOnmouseover() throws Exception {
        runMyTest("testImgSrcKeyOnmouseover", "");
    }

    public void testImgSrcKeyOnmouseup() throws Exception {
        runMyTest("testImgSrcKeyOnmouseup", "");
    }

    public void testImgSrcKeyStyle() throws Exception {
        runMyTest("testImgSrcKeyStyle", "");
    }

    public void testImgSrcKeyStyleClass() throws Exception {
        runMyTest("testImgSrcKeyStyleClass", "");
    }

    public void testImgSrcKeyStyleId() throws Exception {
        runMyTest("testImgSrcKeyStyleId", "");
    }

    public void testImgSrcKeyTitle() throws Exception {
        runMyTest("testImgSrcKeyTitle", "");
    }

    public void testImgSrcKeyTitleKeyDefaultBundle() throws Exception {
        runMyTest("testImgSrcKeyTitleKeyDefaultBundle", "");
    }

    public void testImgSrcKeyTitleKeyAlternateBundle() throws Exception {
        runMyTest("testImgSrcKeyTitleKeyAlternateBundle", "");
    }

    public void testImgSrcKeyTitleKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImgSrcKeyTitleKeyDefaultBundle_fr", "fr");
    }

    public void testImgSrcKeyTitleKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImgSrcKeyTitleKeyAlternateBundle_fr", "fr");
    }

    public void testImgSrcKeyUsemap() throws Exception {
        runMyTest("testImgSrcKeyUsemap", "");
    }

}
