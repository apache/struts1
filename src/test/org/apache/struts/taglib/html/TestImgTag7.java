/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestImgTag7.java,v 1.1 2003/03/12 00:31:01 jmitchell Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/12 00:31:01 $
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

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.ImgTag</code> class.
 *
 * @author James Mitchell
 */
public class TestImgTag7 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag7(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestImgTag7.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag7.class);
    }

    private void runMyTest(String whichTest, String locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/html/TestImgTag7.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("There is a problem that is preventing the tests to continue!");
		}
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgSrcKeyAlign1(){
        runMyTest("testImgSrcKeyAlign1", "");
    }

    public void testImgSrcKeyAlign2(){
        runMyTest("testImgSrcKeyAlign2", "");
    }

    public void testImgSrcKeyAlign3(){
        runMyTest("testImgSrcKeyAlign3", "");
    }

    public void testImgSrcKeyAlign4(){
        runMyTest("testImgSrcKeyAlign4", "");
    }

    public void testImgSrcKeyAlign5(){
        runMyTest("testImgSrcKeyAlign5", "");
    }

    public void testImgSrcKeyAlign6(){
        runMyTest("testImgSrcKeyAlign6", "");
    }

    public void testImgSrcKeyAlign7(){
        runMyTest("testImgSrcKeyAlign7", "");
    }

    public void testImgSrcKeyAlign8(){
        runMyTest("testImgSrcKeyAlign8", "");
    }

    public void testImgSrcKeyAlign9(){
        runMyTest("testImgSrcKeyAlign9", "");
    }

    public void testImgSrcKeyAlign10(){
        runMyTest("testImgSrcKeyAlign10", "");
    }

    public void testImgSrcKeyAlt(){
        runMyTest("testImgSrcKeyAlt", "");
    }

    public void testImgSrcKeyAltKeyDefaultBundle(){
        runMyTest("testImgSrcKeyAltKeyDefaultBundle", "");
    }

    public void testImgSrcKeyAltKeyAlternateBundle(){
        runMyTest("testImgSrcKeyAltKeyAlternateBundle", "");
    }

    public void testImgSrcKeyAltKeyDefaultBundle_fr(){
        runMyTest("testImgSrcKeyAltKeyDefaultBundle_fr", "fr");
    }

    public void testImgSrcKeyAltKeyAlternateBundle_fr(){
        runMyTest("testImgSrcKeyAltKeyAlternateBundle_fr", "fr");
    }

    public void testImgSrcKeyBorder(){
        runMyTest("testImgSrcKeyBorder", "");
    }

    public void testImgSrcKeyHeight1(){
        runMyTest("testImgSrcKeyHeight1", "");
    }

    public void testImgSrcKeyHeight2(){
        runMyTest("testImgSrcKeyHeight2", "");
    }

    public void testImgSrcKeyHspace(){
        runMyTest("testImgSrcKeyHspace", "");
    }

    public void testImgSrcKeyImageName(){
        runMyTest("testImgSrcKeyImageName", "");
    }

    public void testImgSrcKeyImageIsmap(){
        runMyTest("testImgSrcKeyImageIsmap", "");
    }

    public void testImgSrcKeyLocale(){
    	pageContext.setAttribute("secret locale", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
        runMyTest("testImgSrcKeyLocale", "");
    }

}
