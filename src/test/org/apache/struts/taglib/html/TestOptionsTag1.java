/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestOptionsTag1.java,v 1.3 2004/01/13 12:48:55 husted Exp $
 * $Revision: 1.3 $
 * $Date: 2004/01/13 12:48:55 $
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
 *    any, must include the following acknowledgement:
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
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
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
import org.apache.struts.util.LabelValueBean;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.html.OptionsTag</code> class.
 *
 */
public class TestOptionsTag1 extends JspTestCase {


    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestOptionsTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestOptionsTag1.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        return new TestSuite(TestOptionsTag1.class);
    }

    private void runTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                        new Locale(locale, locale),
                        PageContext.SESSION_SCOPE);

        pageContext.setAttribute("runTest", whichTest, PageContext.REQUEST_SCOPE);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestOptionsTag1.jsp");
    }

    private LabelValueBean[] createArrayOfLVB(){
    	LabelValueBean[] labelValueBeans = new LabelValueBean[5];
    	for (int i = 0; i < 5; i++){
    		labelValueBeans[i] = 
    		new LabelValueBean("key" + i, "Test Message " + i);
    	}
    	return labelValueBeans;
    }
    
    private String[] createArrayofStrings(){
    	String[] stringValues = new String[5];
       	for (int i = 0; i < 5; i++){
    		stringValues[i] = "val" + i;
    	}
    	return stringValues;
    }

    
    public void testOptionsCollectionArrayItemValueInCollectionProperty() throws Exception {
    	pageContext.setAttribute("arrayOfLVB", createArrayOfLVB(), PageContext.REQUEST_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			new SimpleBeanForTesting("key1"), PageContext.REQUEST_SCOPE);
    	runTest("testOptionsCollectionArrayItemValueInCollectionProperty", "");
    }
    
    public void testOptionsCollectionArrayItemValueNotInCollectionProperty() throws Exception {
    	pageContext.setAttribute("arrayOfLVB", createArrayOfLVB(), PageContext.REQUEST_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			new SimpleBeanForTesting("key15"), PageContext.REQUEST_SCOPE);
    	runTest("testOptionsCollectionArrayItemValueNotInCollectionProperty", "");
    }

    
    public void testOptionsCollectionArrayItemValueInCollectionPropertyLabelProperty() throws Exception {
    	pageContext.setAttribute("arrayOfLVB", createArrayOfLVB(), PageContext.REQUEST_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			new SimpleBeanForTesting("key1"), PageContext.REQUEST_SCOPE);
    	runTest("testOptionsCollectionArrayItemValueInCollectionPropertyLabelProperty", "");
    }
    
    public void testOptionsCollectionArrayItemValueNotInCollectionPropertyLabelProperty() throws Exception {
    	pageContext.setAttribute("arrayOfLVB", createArrayOfLVB(), PageContext.REQUEST_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			new SimpleBeanForTesting("key15"), PageContext.REQUEST_SCOPE);
    	runTest("testOptionsCollectionArrayItemValueNotInCollectionPropertyLabelProperty", "");
    }
    

    public void testOptionsNameArrayItemValueInCollection() throws Exception {
    	pageContext.setAttribute("stringValues", createArrayofStrings(), PageContext.REQUEST_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			new SimpleBeanForTesting("val1"), PageContext.REQUEST_SCOPE);
    	runTest("testOptionsNameArrayItemValueInCollection", "");
    }
    
    public void testOptionsNameArrayItemValueNotInCollection() throws Exception {
    	pageContext.setAttribute("stringValues", createArrayofStrings(), PageContext.REQUEST_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			new SimpleBeanForTesting("val15"), PageContext.REQUEST_SCOPE);
    	runTest("testOptionsNameArrayItemValueNotInCollection", "");
    }

    
    public void testOptionsPropertyArrayItemValueInCollection() throws Exception {
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting("val1");
    	sbft.setStringArray(createArrayofStrings());
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			sbft, PageContext.REQUEST_SCOPE);
    	runTest("testOptionsPropertyArrayItemValueInCollection", "");
    }
    
    public void testOptionsPropertyArrayItemValueNotInCollection() throws Exception {
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting("val15");
    	sbft.setStringArray(createArrayofStrings());
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			sbft, PageContext.REQUEST_SCOPE);
    	runTest("testOptionsPropertyArrayItemValueNotInCollection", "");
    }
    
    
    public void testOptionsNamePropertyArrayItemValueInCollection() throws Exception {
    	pageContext.setAttribute("stringValues", 
    		new  SimpleBeanForTesting(createArrayofStrings()), PageContext.REQUEST_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			new SimpleBeanForTesting("val1"), PageContext.REQUEST_SCOPE);
    	runTest("testOptionsNamePropertyArrayItemValueInCollection", "");
    }
    
    public void testOptionsNamePropertyArrayItemValueNotInCollection() throws Exception {
    	pageContext.setAttribute("stringValues", 
    			new  SimpleBeanForTesting(createArrayofStrings()), PageContext.REQUEST_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, 
    			new SimpleBeanForTesting("val15"), PageContext.REQUEST_SCOPE);
    	runTest("testOptionsNamePropertyArrayItemValueNotInCollection", "");
    }
}
