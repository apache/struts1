/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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
package org.apache.struts.taglib.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebRequest;
import org.apache.cactus.WebResponse;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;
import org.apache.struts.util.LabelValueBean;

/**
  * These tests attempt to cover every single possible configuration of the
  * org.apache.struts.taglib.bean.MessageTag
  * 
  * I've tried to describe what I'm testing as best as possible by the method names.
  * To see how I'm testing, refer to the jsp file that these tests forward to. 
  * 
  * All of these tests depend on a value being correctly written on the repose, then
  * checked here in endXXX method.
  *
  * @author James Mitchell
  */
public class TestMessageTag2 extends JspTestCase {
	
    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL = "Testing Message 1 2";

    public TestMessageTag2(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestMessageTag2.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag2.class);
    }
    
    private void runMyTest(String whichTest, Locale locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, locale, PageContext.SESSION_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/bean/TestMessageTag2.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("There is a problem that is preventing the tests to continue!");
		}
    }
    
	private void formatAndTest(String compare, String output) {
		//fix for introduced carriage return / line feeds
		output = output.replaceAll("\r","");
		output = output.replaceAll("\n","");
		output = output.trim();
		//System.out.println("Testing [" + compare + "] == [" + output + "]");
	    assertEquals(compare, output);
	}

    /*
     * ===========================================================
     * Testing MessageTag (these comments serve as a divider of 
     *                     functionality being tested)
     * 
     * Section: 2 Args
     * Locale:  (default)
     * ===========================================================
     */


    public void testMessageTag2ArgKeyNoScopeDefaultBundle(){ 
     runMyTest("testMessageTag2ArgKeyNoScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgKeyNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgKeyApplicationScopeDefaultBundle(){ 
     runMyTest("testMessageTag2ArgKeyApplicationScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgKeyApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgKeySessionScopeDefaultBundle(){ 
     runMyTest("testMessageTag2ArgKeySessionScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgKeySessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgKeyRequestScopeDefaultBundle(){ 
     runMyTest("testMessageTag2ArgKeyRequestScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgKeyRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}


    public void testMessageTag2ArgKeyNoScopeAlternateBundle(){ 
     runMyTest("testMessageTag2ArgKeyNoScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgKeyNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgKeyApplicationScopeAlternateBundle(){ 
     runMyTest("testMessageTag2ArgKeyApplicationScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgKeyApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgKeySessionScopeAlternateBundle(){ 
     runMyTest("testMessageTag2ArgKeySessionScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgKeySessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgKeyRequestScopeAlternateBundle(){ 
     runMyTest("testMessageTag2ArgKeyRequestScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgKeyRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}



    public void testMessageTag2ArgNameNoScopeDefaultBundle(){ 
     runMyTest("testMessageTag2ArgNameNoScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNameNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNameApplicationScopeDefaultBundle(){ 
     runMyTest("testMessageTag2ArgNameApplicationScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNameApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNameSessionScopeDefaultBundle(){ 
     runMyTest("testMessageTag2ArgNameSessionScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNameSessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNameRequestScopeDefaultBundle(){ 
     runMyTest("testMessageTag2ArgNameRequestScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNameRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}


    public void testMessageTag2ArgNameNoScopeAlternateBundle(){ 
     runMyTest("testMessageTag2ArgNameNoScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNameNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNameApplicationScopeAlternateBundle(){ 
     runMyTest("testMessageTag2ArgNameApplicationScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNameApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNameSessionScopeAlternateBundle(){ 
     runMyTest("testMessageTag2ArgNameSessionScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNameSessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNameRequestScopeAlternateBundle(){ 
     runMyTest("testMessageTag2ArgNameRequestScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNameRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}




    public void testMessageTag2ArgNamePropertyNoScopeDefaultBundle(){
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.2"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag2ArgNamePropertyNoScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNamePropertyNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNamePropertyApplicationScopeDefaultBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.2"), PageContext.APPLICATION_SCOPE);
     runMyTest("testMessageTag2ArgNamePropertyApplicationScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNamePropertyApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNamePropertySessionScopeDefaultBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.2"), PageContext.SESSION_SCOPE);
     runMyTest("testMessageTag2ArgNamePropertySessionScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNamePropertySessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNamePropertyRequestScopeDefaultBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.2"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag2ArgNamePropertyRequestScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNamePropertyRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}


    public void testMessageTag2ArgNamePropertyNoScopeAlternateBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.2"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag2ArgNamePropertyNoScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNamePropertyNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNamePropertyApplicationScopeAlternateBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.2"), PageContext.APPLICATION_SCOPE);
     runMyTest("testMessageTag2ArgNamePropertyApplicationScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNamePropertyApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNamePropertySessionScopeAlternateBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.2"), PageContext.SESSION_SCOPE);
     runMyTest("testMessageTag2ArgNamePropertySessionScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNamePropertySessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag2ArgNamePropertyRequestScopeAlternateBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.2"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag2ArgNamePropertyRequestScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag2ArgNamePropertyRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}


}