/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestOptionTag1.java,v 1.1 2003/06/07 03:53:23 jmitchell Exp $
 * $Revision: 1.1 $
 * $Date: 2003/06/07 03:53:23 $
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
 *  TestOptionTag1 - These test validate true (a value was in the array) on our form.
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
public class TestOptionTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestOptionTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestOptionTag1.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestOptionTag1.class);
    }

    private void runMyTest(String whichTest, String locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY,
    			new Locale(locale, locale),
    			PageContext.SESSION_SCOPE);

		SimpleBeanForTesting sbft = new SimpleBeanForTesting("SelectMe");

    	pageContext.setAttribute(Constants.BEAN_KEY, sbft, PageContext.REQUEST_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/html/TestOptionTag1.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("There is a problem that is preventing the tests to continue!");
		}
    }

    /*
     * Testing MultiboxTag.
     */
    public void testOptionBodySelected(){
    	runMyTest("testOptionBodySelected", "");
	}

    public void testOptionBodyNotSelected(){
    	runMyTest("testOptionBodyNotSelected", "");
	}

    public void testOptionBodySelectedDisabled_true(){
    	runMyTest("testOptionBodySelectedDisabled_true", "");
	}

    public void testOptionBodyNotSelectedDisabled_true(){
    	runMyTest("testOptionBodyNotSelectedDisabled_true", "");
	}

    public void testOptionBodySelectedDisabled_false(){
    	runMyTest("testOptionBodySelectedDisabled_false", "");
	}

    public void testOptionBodyNotSelectedDisabled_false(){
    	runMyTest("testOptionBodyNotSelectedDisabled_false", "");
	}

    public void testOptionBodySelectedDisabled_other(){
    	runMyTest("testOptionBodySelectedDisabled_other", "");
	}

    public void testOptionBodyNotSelectedDisabled_other(){
    	runMyTest("testOptionBodyNotSelectedDisabled_other", "");
	}

    public void testOptionKeySelected(){
    	runMyTest("testOptionKeySelected", "");
	}

    public void testOptionKeyNotSelected(){
    	runMyTest("testOptionKeyNotSelected", "");
	}

    public void testOptionKeySelectedAlternateBundle(){
    	runMyTest("testOptionKeySelectedAlternateBundle", "");
	}

    public void testOptionKeyNotSelectedAlternateBundle(){
    	runMyTest("testOptionKeyNotSelectedAlternateBundle", "");
	}

    public void testOptionKeySelectedLocale_fr(){
    	runMyTest("testOptionKeySelectedLocale_fr", "fr");
	}

    public void testOptionKeyNotSelectedLocale_fr(){
    	runMyTest("testOptionKeyNotSelectedLocale_fr", "fr");
	}

    public void testOptionKeySelectedAlternateBundleLocale_fr(){
    	runMyTest("testOptionKeySelectedAlternateBundleLocale_fr", "fr");
	}

    public void testOptionKeyNotSelectedAlternateBundleLocale_fr(){
    	runMyTest("testOptionKeyNotSelectedAlternateBundleLocale_fr", "fr");
	}

}
