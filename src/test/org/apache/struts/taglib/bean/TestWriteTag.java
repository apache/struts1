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
import org.apache.struts.util.ResponseUtils;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.WriteTag</code> class.
 *
 * @author James Mitchell
 */
public class TestWriteTag extends JspTestCase {
	protected final static String TEST_STRING_VAL = "Test Value";
	protected final static Integer TEST_INTEGER_VAL = new Integer("1234");
	protected final static Double  TEST_DOUBLE_VAL  = new Double("1234.5961");
	protected final static String REQUEST_KEY = "REQUEST_KEY";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestWriteTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestWriteTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestWriteTag.class);
    }
    
	private void formatAndTest(String compare, String output) {
		//fix for introduced carriage return / line feeds
		output = output.replaceAll("\r","");
		output = output.replaceAll("\n","");
		output = output.trim();
		//System.out.println("Testing [" + compare + "] == [" + output + "]");
	    assertEquals(compare, output);
	}

    private void runMyTest(String whichTest, String locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/bean/TestWriteTag.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("There is a problem that is preventing the tests to continue!");
		}
    }

	// Name
    public void testWriteTagName(){
		pageContext.setAttribute(REQUEST_KEY,TEST_STRING_VAL,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagName", "");
	}
	public void endWriteTagName(WebResponse response){
		formatAndTest(TEST_STRING_VAL, response.getText());
	}
	
	// Property
    public void testWriteTagNameProperty() {
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting(TEST_STRING_VAL);
		pageContext.setAttribute(REQUEST_KEY, sbft,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNameProperty", "");
	}
	public void endWriteTagNameProperty(WebResponse response){
		formatAndTest(TEST_STRING_VAL, response.getText());
	}
	
	// Name and Format
    public void testWriteTagNameFormat(){
		pageContext.setAttribute(REQUEST_KEY,TEST_INTEGER_VAL,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNameFormat", "");
	}
	public void endWriteTagNameFormat(WebResponse response){
		formatAndTest("1,234" , response.getText());
	}
	
	// Name, Format, and FormatKey (default bundle)
    public void testWriteTagNameFormatKeyDefaultBundle(){
		pageContext.setAttribute(REQUEST_KEY,TEST_INTEGER_VAL,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNameFormatKeyDefaultBundle", "");
	}
	public void endWriteTagNameFormatKeyDefaultBundle(WebResponse response){
		formatAndTest("$1,234" , response.getText());
	}
	
	// Name, Format, and FormatKey (alternate bundle)
    public void testWriteTagNameFormatKeyAlternateBundle(){
		pageContext.setAttribute(REQUEST_KEY,TEST_INTEGER_VAL,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNameFormatKeyAlternateBundle", "");
	}
	public void endWriteTagNameFormatKeyAlternateBundle(WebResponse response){
		formatAndTest("$1,234" , response.getText());
	}
	
	// Name, Format, and FormatKey (default bundle) (Double)
    public void testWriteTagNameFormatKeyDefaultBundleDouble(){
		pageContext.setAttribute(REQUEST_KEY, TEST_DOUBLE_VAL,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNameFormatKeyDefaultBundleDouble", "");
	}
	public void endWriteTagNameFormatKeyDefaultBundleDouble(WebResponse response){
		formatAndTest("$1,235" , response.getText());
	}
	
	// Name, Format, and FormatKey (alternate bundle) (Double)
    public void testWriteTagNameFormatKeyAlternateBundleDouble(){
		pageContext.setAttribute(REQUEST_KEY,TEST_DOUBLE_VAL,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNameFormatKeyAlternateBundleDouble", "");
	}
	public void endWriteTagNameFormatKeyAlternateBundleDouble(WebResponse response){
		formatAndTest("$1,234.6" , response.getText());
	}
	
	// Name, Format, and FormatKey (default bundle)
    public void testWriteTagNameFormatKeyDefaultBundle_fr(){
		pageContext.setAttribute(REQUEST_KEY, TEST_DOUBLE_VAL,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNameFormatKeyDefaultBundle", "fr");
	}
	public void endWriteTagNameFormatKeyDefaultBundle_fr(WebResponse response){
		formatAndTest("$1234,5961.", response.getText());
	}
	
	// Name, Format, and FormatKey (alternate bundle)
    public void testWriteTagNameFormatKeyAlternateBundle_fr(){
		pageContext.setAttribute(REQUEST_KEY,TEST_DOUBLE_VAL,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNameFormatKeyAlternateBundle", "fr");
	}
	public void endWriteTagNameFormatKeyAlternateBundle_fr(WebResponse response){
		formatAndTest("$1234,5961." , response.getText());
	}
	
	// Name, Property, and Format
    public void testWriteTagNamePropertyFormat(){
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting(TEST_INTEGER_VAL);
		pageContext.setAttribute(REQUEST_KEY, sbft,PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNamePropertyFormat", "");
	}
	public void endWriteTagNamePropertyFormat(WebResponse response){
		formatAndTest("1,234", response.getText());
	}

	// Name and ignore
    public void testWriteTagNameIgnore(){
		runMyTest("testWriteTagNameIgnore", "");
	}
	public void endWriteTagNameIgnore(WebResponse response){
		formatAndTest("", response.getText());
	}
	
	// Name and filter
    public void testWriteTagNameFilter(){
		pageContext.setAttribute(REQUEST_KEY,"<testing&'\">",PageContext.REQUEST_SCOPE);
		runMyTest("testWriteTagNameFilter", "");
	}
	public void endWriteTagNameFilter(WebResponse response){
		formatAndTest(ResponseUtils.filter("<testing&'\">"), response.getText());
	}
	

}