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

import java.util.Locale;

import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebResponse;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
  * These tests attempt to cover every single possible configuration of the
  * org.apache.struts.taglib.bean.MessageTag
  * 
  * I've tried to describe what I'm testing as best as possible by the method names.
  * To see how I'm testing, refer to the jsp file that these tests forward to. 
  * 
  * All of these tests depend on a value being correctly written on the response, then
  * checked here in endXXX method.
  *
  * @author James Mitchell
  */
public class TestMessageTag_fr extends JspTestCase {
	
    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL_FR = "Message D'Essai";

    public TestMessageTag_fr(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestMessageTag_fr.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag_fr.class);
    }
    
    private void runMyTest(String whichTest, Locale locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, locale, PageContext.SESSION_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/bean/TestMessageTag.jsp");
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
     * Section: NoArg
     * Locale:  (default)
     * ===========================================================
     */
    public void testMessageTagNoArgKeyNoScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTagNoArgKeyNoScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgKeyNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgKeyApplicationScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTagNoArgKeyApplicationScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgKeyApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgKeySessionScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTagNoArgKeySessionScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgKeySessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgKeyRequestScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTagNoArgKeyRequestScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgKeyRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}


    public void testMessageTagNoArgKeyNoScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTagNoArgKeyNoScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgKeyNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgKeyApplicationScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTagNoArgKeyApplicationScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgKeyApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgKeySessionScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTagNoArgKeySessionScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgKeySessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgKeyRequestScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTagNoArgKeyRequestScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgKeyRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}



    public void testMessageTagNoArgNameNoScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTagNoArgNameNoScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNameNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNameApplicationScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTagNoArgNameApplicationScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNameApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNameSessionScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTagNoArgNameSessionScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNameSessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNameRequestScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTagNoArgNameRequestScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNameRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}


    public void testMessageTagNoArgNameNoScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTagNoArgNameNoScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNameNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNameApplicationScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTagNoArgNameApplicationScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNameApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNameSessionScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTagNoArgNameSessionScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNameSessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNameRequestScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTagNoArgNameRequestScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNameRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}




    public void testMessageTagNoArgNamePropertyNoScopeDefaultBundle_fr(){
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTagNoArgNamePropertyNoScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNamePropertyNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNamePropertyApplicationScopeDefaultBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message"), PageContext.APPLICATION_SCOPE);
     runMyTest("testMessageTagNoArgNamePropertyApplicationScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNamePropertyApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNamePropertySessionScopeDefaultBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message"), PageContext.SESSION_SCOPE);
     runMyTest("testMessageTagNoArgNamePropertySessionScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNamePropertySessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNamePropertyRequestScopeDefaultBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTagNoArgNamePropertyRequestScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNamePropertyRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}


    public void testMessageTagNoArgNamePropertyNoScopeAlternateBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTagNoArgNamePropertyNoScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNamePropertyNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNamePropertyApplicationScopeAlternateBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message"), PageContext.APPLICATION_SCOPE);
     runMyTest("testMessageTagNoArgNamePropertyApplicationScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNamePropertyApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNamePropertySessionScopeAlternateBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message"), PageContext.SESSION_SCOPE);
     runMyTest("testMessageTagNoArgNamePropertySessionScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNamePropertySessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTagNoArgNamePropertyRequestScopeAlternateBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTagNoArgNamePropertyRequestScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTagNoArgNamePropertyRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}








}