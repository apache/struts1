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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebRequest;
import org.apache.cactus.WebResponse;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.CookieTag</code> class.
 *
 * @author James Mitchell
 */
public class TestCookieTag extends JspTestCase {
	
    protected final static String COOKIE_KEY = "COOKIE_KEY";
    protected final static String COOKIE_VAL = "COOKIE_VAL";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestCookieTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestCookieTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestCookieTag.class);
    }
    

    
    private void runMyTest(String whichTest){
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/bean/TestCookieTag.jsp");
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
     * Testing CookieTag, with Name specified in tags.
     */
    public void beginCookieTagName(WebRequest webRequest) {
		webRequest.addCookie(COOKIE_KEY, COOKIE_VAL);
      	webRequest.addParameter("cacheId", "1");
    }
    public void testCookieTagName(){ 
    	runMyTest("testCookieTagName");
	}
	public void endCookieTagName(WebResponse response){
		formatAndTest(COOKIE_VAL, response.getText());
	}


    /*
     * Testing CookieTag, with Name and Multiple specified in tags.
     */
    public void beginCookieTagNameMultiple(WebRequest webRequest) {
    	for (int i = 0; i < 10; i++) {
	       webRequest.addCookie(COOKIE_KEY, COOKIE_VAL + i);
		}
      	webRequest.addParameter("cacheId", "1");
    }
    public void testCookieTagNameMultiple(){ 
    	runMyTest("testCookieTagNameMultiple");
	}
	public void endCookieTagNameMultiple(WebResponse response){
	    String compareTo = "";
    	for (int i = 0; i < 10; i++) {
			compareTo += COOKIE_VAL + i;
		}
		formatAndTest(compareTo, response.getText());
	}

    /*
     * Testing CookieTag, with Name and Value specified in tags.
     */
    public void beginCookieTagNameValue(WebRequest webRequest) {
      	webRequest.addParameter("cacheId", "1");
    }
    public void testCookieTagNameValue(){ 
    	runMyTest("testCookieTagNameValue");
	}
	public void endCookieTagNameValue(WebResponse response){
		formatAndTest(COOKIE_VAL, response.getText());
	}





}
