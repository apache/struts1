/*
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
  * All of these tests depend on a value being correctly written on the repose, then
  * checked here in endXXX method.
  *
  * @author James Mitchell
  */
public class TestMessageTag4_fr extends JspTestCase {
	
    protected final static String TEST_KEY = "BeanKey";
    protected final static String TEST_VAL_FR = "Message D'Essai 1 2 3 4";

    public TestMessageTag4_fr(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestMessageTag4_fr.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestMessageTag4_fr.class);
    }
    
    private void runMyTest(String whichTest, Locale locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, locale, PageContext.SESSION_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/bean/TestMessageTag4.jsp");
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
     * Section: 4 Args
     * Locale:  (default)
     * ===========================================================
     */


    public void testMessageTag4ArgKeyNoScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTag4ArgKeyNoScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgKeyNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgKeyApplicationScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTag4ArgKeyApplicationScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgKeyApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgKeySessionScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTag4ArgKeySessionScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgKeySessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgKeyRequestScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTag4ArgKeyRequestScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgKeyRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}


    public void testMessageTag4ArgKeyNoScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTag4ArgKeyNoScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgKeyNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgKeyApplicationScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTag4ArgKeyApplicationScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgKeyApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgKeySessionScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTag4ArgKeySessionScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgKeySessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgKeyRequestScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTag4ArgKeyRequestScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgKeyRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}



    public void testMessageTag4ArgNameNoScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTag4ArgNameNoScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNameNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNameApplicationScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTag4ArgNameApplicationScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNameApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNameSessionScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTag4ArgNameSessionScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNameSessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNameRequestScopeDefaultBundle_fr(){ 
     runMyTest("testMessageTag4ArgNameRequestScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNameRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}


    public void testMessageTag4ArgNameNoScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTag4ArgNameNoScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNameNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNameApplicationScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTag4ArgNameApplicationScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNameApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNameSessionScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTag4ArgNameSessionScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNameSessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNameRequestScopeAlternateBundle_fr(){ 
     runMyTest("testMessageTag4ArgNameRequestScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNameRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}




    public void testMessageTag4ArgNamePropertyNoScopeDefaultBundle_fr(){
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.4"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag4ArgNamePropertyNoScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNamePropertyNoScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNamePropertyApplicationScopeDefaultBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.4"), PageContext.APPLICATION_SCOPE);
     runMyTest("testMessageTag4ArgNamePropertyApplicationScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNamePropertyApplicationScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNamePropertySessionScopeDefaultBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.4"), PageContext.SESSION_SCOPE);
     runMyTest("testMessageTag4ArgNamePropertySessionScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNamePropertySessionScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNamePropertyRequestScopeDefaultBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("default.bundle.message.4"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag4ArgNamePropertyRequestScopeDefaultBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNamePropertyRequestScopeDefaultBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}


    public void testMessageTag4ArgNamePropertyNoScopeAlternateBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.4"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag4ArgNamePropertyNoScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNamePropertyNoScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNamePropertyApplicationScopeAlternateBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.4"), PageContext.APPLICATION_SCOPE);
     runMyTest("testMessageTag4ArgNamePropertyApplicationScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNamePropertyApplicationScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNamePropertySessionScopeAlternateBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.4"), PageContext.SESSION_SCOPE);
     runMyTest("testMessageTag4ArgNamePropertySessionScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNamePropertySessionScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}

    public void testMessageTag4ArgNamePropertyRequestScopeAlternateBundle_fr(){ 
    	pageContext.setAttribute("key", new SimpleBeanForTesting("alternate.bundle.message.4"), PageContext.REQUEST_SCOPE);
     runMyTest("testMessageTag4ArgNamePropertyRequestScopeAlternateBundle", new Locale("fr","fr"));
	}
	public void endMessageTag4ArgNamePropertyRequestScopeAlternateBundle(WebResponse response){
		formatAndTest(TEST_VAL_FR, response.getText());
	}


}