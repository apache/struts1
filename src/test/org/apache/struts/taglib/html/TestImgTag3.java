/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestImgTag3.java,v 1.4 2003/12/26 22:10:32 germuska Exp $
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
 * <code>org.apache.struts.taglib.html.ImgTag</code> class.
 *
 * @author James Mitchell
 */
public class TestImgTag3 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestImgTag3.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag3.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestImgTag3.jsp");
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgPageKeyAlign1() throws Exception {
        runMyTest("testImgPageKeyAlign1", "");
    }

    public void testImgPageKeyAlign2() throws Exception {
        runMyTest("testImgPageKeyAlign2", "");
    }

    public void testImgPageKeyAlign3() throws Exception {
        runMyTest("testImgPageKeyAlign3", "");
    }

    public void testImgPageKeyAlign4() throws Exception {
        runMyTest("testImgPageKeyAlign4", "");
    }

    public void testImgPageKeyAlign5() throws Exception {
        runMyTest("testImgPageKeyAlign5", "");
    }

    public void testImgPageKeyAlign6() throws Exception {
        runMyTest("testImgPageKeyAlign6", "");
    }

    public void testImgPageKeyAlign7() throws Exception {
        runMyTest("testImgPageKeyAlign7", "");
    }

    public void testImgPageKeyAlign8() throws Exception {
        runMyTest("testImgPageKeyAlign8", "");
    }

    public void testImgPageKeyAlign9() throws Exception {
        runMyTest("testImgPageKeyAlign9", "");
    }

    public void testImgPageKeyAlign10() throws Exception {
        runMyTest("testImgPageKeyAlign10", "");
    }

    public void testImgPageKeyAlt() throws Exception {
        runMyTest("testImgPageKeyAlt", "");
    }

    public void testImgPageKeyAltKeyDefaultBundle() throws Exception {
        runMyTest("testImgPageKeyAltKeyDefaultBundle", "");
    }

    public void testImgPageKeyAltKeyAlternateBundle() throws Exception {
        runMyTest("testImgPageKeyAltKeyAlternateBundle", "");
    }

    public void testImgPageKeyAltKeyDefaultBundle_fr() throws Exception {
        runMyTest("testImgPageKeyAltKeyDefaultBundle_fr", "fr");
    }

    public void testImgPageKeyAltKeyAlternateBundle_fr() throws Exception {
        runMyTest("testImgPageKeyAltKeyAlternateBundle_fr", "fr");
    }

    public void testImgPageKeyBorder() throws Exception {
        runMyTest("testImgPageKeyBorder", "");
    }

    public void testImgPageKeyHeight1() throws Exception {
        runMyTest("testImgPageKeyHeight1", "");
    }

    public void testImgPageKeyHeight2() throws Exception {
        runMyTest("testImgPageKeyHeight2", "");
    }

    public void testImgPageKeyHspace() throws Exception {
        runMyTest("testImgPageKeyHspace", "");
    }

    public void testImgPageKeyImageName() throws Exception {
        runMyTest("testImgPageKeyImageName", "");
    }

    public void testImgPageKeyImageIsmap() throws Exception {
        runMyTest("testImgPageKeyImageIsmap", "");
    }

    public void testImgPageKeyLocale() throws Exception {
        pageContext.setAttribute("secret locale", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testImgPageKeyLocale", "");
    }

}
