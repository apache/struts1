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
public class TestMessageTag3 extends JspTestCase {
	
    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL = "Testing Message 1 2 3";

    public TestMessageTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestMessageTag3.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag3.class);
    }
    
    private void runMyTest(String whichTest, Locale locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, locale, PageContext.SESSION_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/bean/TestMessageTag3.jsp");
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
     * Section: 3 Arg
     * Locale:  (default)
     * ===========================================================
     */


    public void testMessageTag3ArgKeyNoScopeDefaultBundle(){ 
     runMyTest("testMessageTag3ArgKeyNoScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgKeyNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgKeyApplicationScopeDefaultBundle(){ 
     runMyTest("testMessageTag3ArgKeyApplicationScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgKeyApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgKeySessionScopeDefaultBundle(){ 
     runMyTest("testMessageTag3ArgKeySessionScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgKeySessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgKeyRequestScopeDefaultBundle(){ 
     runMyTest("testMessageTag3ArgKeyRequestScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgKeyRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}


    public void testMessageTag3ArgKeyNoScopeAlternateBundle(){ 
     runMyTest("testMessageTag3ArgKeyNoScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgKeyNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgKeyApplicationScopeAlternateBundle(){ 
     runMyTest("testMessageTag3ArgKeyApplicationScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgKeyApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgKeySessionScopeAlternateBundle(){ 
     runMyTest("testMessageTag3ArgKeySessionScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgKeySessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgKeyRequestScopeAlternateBundle(){ 
     runMyTest("testMessageTag3ArgKeyRequestScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgKeyRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}



    public void testMessageTag3ArgNameNoScopeDefaultBundle(){ 
     runMyTest("testMessageTag3ArgNameNoScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNameNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNameApplicationScopeDefaultBundle(){ 
     runMyTest("testMessageTag3ArgNameApplicationScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNameApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNameSessionScopeDefaultBundle(){ 
     runMyTest("testMessageTag3ArgNameSessionScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNameSessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNameRequestScopeDefaultBundle(){ 
     runMyTest("testMessageTag3ArgNameRequestScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNameRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}


    public void testMessageTag3ArgNameNoScopeAlternateBundle(){ 
     runMyTest("testMessageTag3ArgNameNoScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNameNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNameApplicationScopeAlternateBundle(){ 
     runMyTest("testMessageTag3ArgNameApplicationScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNameApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNameSessionScopeAlternateBundle(){ 
     runMyTest("testMessageTag3ArgNameSessionScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNameSessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNameRequestScopeAlternateBundle(){ 
     runMyTest("testMessageTag3ArgNameRequestScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNameRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}




    public void testMessageTag3ArgNamePropertyNoScopeDefaultBundle(){
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.3"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag3ArgNamePropertyNoScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNamePropertyNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNamePropertyApplicationScopeDefaultBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.3"), PageContext.APPLICATION_SCOPE);
     runMyTest("testMessageTag3ArgNamePropertyApplicationScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNamePropertyApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNamePropertySessionScopeDefaultBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.3"), PageContext.SESSION_SCOPE);
     runMyTest("testMessageTag3ArgNamePropertySessionScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNamePropertySessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNamePropertyRequestScopeDefaultBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.3"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag3ArgNamePropertyRequestScopeDefaultBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNamePropertyRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}


    public void testMessageTag3ArgNamePropertyNoScopeAlternateBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.3"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag3ArgNamePropertyNoScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNamePropertyNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNamePropertyApplicationScopeAlternateBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.3"), PageContext.APPLICATION_SCOPE);
     runMyTest("testMessageTag3ArgNamePropertyApplicationScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNamePropertyApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNamePropertySessionScopeAlternateBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.3"), PageContext.SESSION_SCOPE);
     runMyTest("testMessageTag3ArgNamePropertySessionScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNamePropertySessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}

    public void testMessageTag3ArgNamePropertyRequestScopeAlternateBundle(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.3"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag3ArgNamePropertyRequestScopeAlternateBundle", new Locale("",""));
	}
	public void endMessageTag3ArgNamePropertyRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL, response.getText());
	}


}