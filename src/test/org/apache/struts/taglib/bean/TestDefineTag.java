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

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebRequest;
import org.apache.cactus.WebResponse;
import org.apache.struts.util.LabelValueBean;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.DefineTag</code> class.
 *
 * @author James Mitchell
 */
public class TestDefineTag extends JspTestCase {

	protected final static String TEST_KEY   = "TestDefineTag";
	protected final static String TEST_VALUE = "Test Value";
	
    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestDefineTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestDefineTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestDefineTag.class);
    }
    
    private void runMyTest(String whichTest){
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/bean/TestDefineTag.jsp");
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
	    assertEquals(compare, output);
	}

    /*
     * Testing DefineTag using Application scope, with Application Scope specified in tags.
     */
    public void testDefineNameApplicationScope(){ 
    	pageContext.setAttribute(TEST_KEY, TEST_VALUE, PageContext.APPLICATION_SCOPE);
    	runMyTest("testDefineNameApplicationScope");
	}
	public void endDefineNameApplicationScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineNamePropertyApplicationScope(){ 
    	LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE);
    	pageContext.setAttribute(TEST_KEY, lvb, PageContext.APPLICATION_SCOPE);
    	runMyTest("testDefineNamePropertyApplicationScope");
	}
	public void endDefineNamePropertyApplicationScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineValueApplicationScope(){
    	LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE + " this will be set to the correct value");
    	pageContext.setAttribute(TEST_KEY, lvb, PageContext.APPLICATION_SCOPE);
    	runMyTest("testDefineValueApplicationScope");
	}
	public void endDefineValueApplicationScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineBodyApplicationScope(){ 
    	runMyTest("testDefineBodyApplicationScope");
	}
	public void endDefineBodyApplicationScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}


    /*
     * Testing DefineTag using Session scope, with Session Scope specified in tags.
     */
    public void testDefineNameSessionScope(){ 
    	pageContext.setAttribute(TEST_KEY, TEST_VALUE, PageContext.SESSION_SCOPE);
    	runMyTest("testDefineNameSessionScope");
	}
	public void endDefineNameSessionScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineNamePropertySessionScope(){ 
    	LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE);
    	pageContext.setAttribute(TEST_KEY, lvb, PageContext.SESSION_SCOPE);
    	runMyTest("testDefineNamePropertySessionScope");
	}
	public void endDefineNamePropertySessionScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineValueSessionScope(){
    	LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE + " this will be set to the correct value");
    	pageContext.setAttribute(TEST_KEY, lvb, PageContext.SESSION_SCOPE);
    	runMyTest("testDefineValueSessionScope");
	}
	public void endDefineValueSessionScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineBodySessionScope(){ 
    	runMyTest("testDefineBodySessionScope");
	}
	public void endDefineBodySessionScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    /*
     * Testing DefineTag using Request scope, with Request Scope specified in tags.
     */
    public void testDefineNameRequestScope(){ 
    	pageContext.setAttribute(TEST_KEY, TEST_VALUE, PageContext.REQUEST_SCOPE);
    	runMyTest("testDefineNameRequestScope");
	}
	public void endDefineNameRequestScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineNamePropertyRequestScope(){ 
    	LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE);
    	pageContext.setAttribute(TEST_KEY, lvb, PageContext.REQUEST_SCOPE);
    	runMyTest("testDefineNamePropertyRequestScope");
	}
	public void endDefineNamePropertyRequestScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineValueRequestScope(){
    	LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE + " this will be set to the correct value");
    	pageContext.setAttribute(TEST_KEY, lvb, PageContext.REQUEST_SCOPE);
    	runMyTest("testDefineValueRequestScope");
	}
	public void endDefineValueRequestScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineBodyRequestScope(){ 
    	runMyTest("testDefineBodyRequestScope");
	}
	public void endDefineBodyRequestScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    /*
     * Testing DefineTag using Request scope, with no Scope specified in tags.
     */
    public void testDefineNameNoScope(){ 
    	request.setAttribute(TEST_KEY, TEST_VALUE);
    	runMyTest("testDefineNameNoScope");
	}
	public void endDefineNameNoScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineNamePropertyNoScope(){ 
    	LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE);
    	request.setAttribute(TEST_KEY, lvb);
    	runMyTest("testDefineNamePropertyNoScope");
	}
	public void endDefineNamePropertyNoScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineValueNoScope(){
    	LabelValueBean lvb = new LabelValueBean("key", TEST_VALUE + " this will be set to the correct value");
    	request.setAttribute(TEST_KEY, lvb);
    	runMyTest("testDefineValueNoScope");
	}
	public void endDefineValueNoScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}

    public void testDefineBodyNoScope(){ 
    	runMyTest("testDefineBodyNoScope");
	}
	public void endDefineBodyNoScope(WebResponse response){
		formatAndTest(TEST_VALUE, response.getText());
	}




}
