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
 * <code>org.apache.struts.taglib.bean.HeaderTag</code> class.
 *
 * @author James Mitchell
 */
public class TestHeaderTag extends JspTestCase {
	
    protected final static String HEADER_KEY = "HEADER_KEY";
    protected final static String HEADER_VAL = "HEADER_VAL";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestHeaderTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestHeaderTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestHeaderTag.class);
    }
    

    
    private void runMyTest(String whichTest){
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/bean/TestHeaderTag.jsp");
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
     * Testing HeaderTag, with Name specified in tags.
     */
    public void beginHeaderTagName(WebRequest webRequest) {
		webRequest.addHeader(HEADER_KEY, HEADER_VAL);
      	webRequest.addParameter("cacheId", "1");
    }
    public void testHeaderTagName(){ 
    	runMyTest("testHeaderTagName");
	}
	public void endHeaderTagName(WebResponse response){
		formatAndTest(HEADER_VAL, response.getText());
	}


    /*
     * Testing HeaderTag, with Name and Multiple specified in tags.
     */
    public void beginHeaderTagNameMultiple(WebRequest webRequest) {
    	for (int i = 0; i < 10; i++) {
	       webRequest.addHeader(HEADER_KEY, HEADER_VAL + i);
		}
      	webRequest.addParameter("cacheId", "1");
    }
    public void testHeaderTagNameMultiple(){ 
    	runMyTest("testHeaderTagNameMultiple");
	}
	public void endHeaderTagNameMultiple(WebResponse response){
	    String compareTo = "";
	    //Multiple Headers are comma delimited
    	for (int i = 0; i < 10; i++) {
			compareTo += HEADER_VAL + i + ",";
		}
		//remove the trailing comma
		compareTo = compareTo.substring(0, compareTo.length() - 1);
		formatAndTest(compareTo, response.getText());
	}

    /*
     * Testing HeaderTag, with Name and Value specified in tags.
     */
    public void beginHeaderTagNameValue(WebRequest webRequest) {
      	webRequest.addParameter("cacheId", "1");
    }
    public void testHeaderTagNameValue(){ 
    	runMyTest("testHeaderTagNameValue");
	}
	public void endHeaderTagNameValue(WebResponse response){
		formatAndTest(HEADER_VAL, response.getText());
	}





}
