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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebResponse;
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.SizeTag</code> class.
 *
 * @author James Mitchell
 */
public class TestSizeTag extends JspTestCase {
	protected final static String TEST_VAL = "Test Value";
	protected final static String REQUEST_KEY = "REQUEST_KEY";
	private String [] strings = { TEST_VAL, TEST_VAL, TEST_VAL };
	private Collection collection = new ArrayList();
	private Map map = new HashMap();

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestSizeTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestSizeTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestSizeTag.class);
    }
    
    public void setUp() {
    	for (int i = 0; i < 3; i++) {
	    	collection.add(TEST_VAL + i);
		}
    	for (int i = 0; i < 3; i++) {
	    	map.put(REQUEST_KEY + i, TEST_VAL + i);
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



	// Array
    public void testSizeTagNameArrayNoScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameArrayNoScope");
		request.setAttribute(REQUEST_KEY, strings);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameArrayNoScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNameArrayApplicationScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameArrayApplicationScope");
		pageContext.setAttribute(REQUEST_KEY, strings, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameArrayApplicationScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNameArraySessionScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameArraySessionScope");
		pageContext.setAttribute(REQUEST_KEY, strings, PageContext.SESSION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameArraySessionScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNameArrayRequestScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameArrayRequestScope");
		pageContext.setAttribute(REQUEST_KEY, strings, PageContext.REQUEST_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameArrayRequestScope(WebResponse response){
		formatAndTest("3", response.getText());
	}


    public void testSizeTagNamePropertyArrayNoScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyArrayNoScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setArray(strings);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyArrayNoScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyArrayApplicationScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyArrayApplicationScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setArray(strings);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyArrayApplicationScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyArraySessionScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyArraySessionScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setArray(strings);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.SESSION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyArraySessionScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyArrayRequestScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyArrayRequestScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setArray(strings);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.REQUEST_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyArrayRequestScope(WebResponse response){
		formatAndTest("3", response.getText());
	}




    public void testSizeTagNamePropertyArrayNoScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyArrayNoScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setArray(strings);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyArrayNoScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyArrayApplicationScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyArrayApplicationScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setArray(strings);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyArrayApplicationScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyArraySessionScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyArraySessionScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setArray(strings);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.SESSION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyArraySessionScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyArrayRequestScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyArrayRequestScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setArray(strings);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.REQUEST_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyArrayRequestScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}



	// Collection
    public void testSizeTagNameCollectionNoScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameCollectionNoScope");
		request.setAttribute(REQUEST_KEY, collection);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameCollectionNoScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNameCollectionApplicationScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameCollectionApplicationScope");
		pageContext.setAttribute(REQUEST_KEY, collection, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameCollectionApplicationScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNameCollectionSessionScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameCollectionSessionScope");
		pageContext.setAttribute(REQUEST_KEY, collection, PageContext.SESSION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameCollectionSessionScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNameCollectionRequestScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameCollectionRequestScope");
		pageContext.setAttribute(REQUEST_KEY, collection, PageContext.REQUEST_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameCollectionRequestScope(WebResponse response){
		formatAndTest("3", response.getText());
	}


    public void testSizeTagNamePropertyCollectionNoScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyCollectionNoScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setCollection(collection);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyCollectionNoScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyCollectionApplicationScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyCollectionApplicationScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setCollection(collection);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyCollectionApplicationScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyCollectionSessionScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyCollectionSessionScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setCollection(collection);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.SESSION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyCollectionSessionScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyCollectionRequestScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyCollectionRequestScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setCollection(collection);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.REQUEST_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyCollectionRequestScope(WebResponse response){
		formatAndTest("3", response.getText());
	}




    public void testSizeTagNamePropertyCollectionNoScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyCollectionNoScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setCollection(collection);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyCollectionNoScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyCollectionApplicationScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyCollectionApplicationScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setCollection(collection);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyCollectionApplicationScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyCollectionSessionScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyCollectionSessionScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setCollection(collection);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.SESSION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyCollectionSessionScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyCollectionRequestScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyCollectionRequestScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setCollection(collection);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.REQUEST_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyCollectionRequestScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}



	// Map
    public void testSizeTagNameMapNoScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameMapNoScope");
		request.setAttribute(REQUEST_KEY, map);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameMapNoScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNameMapApplicationScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameMapApplicationScope");
		pageContext.setAttribute(REQUEST_KEY, map, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameMapApplicationScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNameMapSessionScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameMapSessionScope");
		pageContext.setAttribute(REQUEST_KEY, map, PageContext.SESSION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameMapSessionScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNameMapRequestScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNameMapRequestScope");
		pageContext.setAttribute(REQUEST_KEY, map, PageContext.REQUEST_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNameMapRequestScope(WebResponse response){
		formatAndTest("3", response.getText());
	}


    public void testSizeTagNamePropertyMapNoScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyMapNoScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setMap(map);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyMapNoScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyMapApplicationScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyMapApplicationScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setMap(map);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyMapApplicationScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyMapSessionScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyMapSessionScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setMap(map);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.SESSION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyMapSessionScope(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyMapRequestScope() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyMapRequestScope");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setMap(map);
		pageContext.setAttribute(REQUEST_KEY, sbft, PageContext.REQUEST_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyMapRequestScope(WebResponse response){
		formatAndTest("3", response.getText());
	}




    public void testSizeTagNamePropertyMapNoScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyMapNoScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setMap(map);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyMapNoScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyMapApplicationScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyMapApplicationScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setMap(map);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.APPLICATION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyMapApplicationScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyMapSessionScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyMapSessionScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setMap(map);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.SESSION_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyMapSessionScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}

    public void testSizeTagNamePropertyMapRequestScopeNested() throws IOException, ServletException{
		request.setAttribute("runTest", "testSizeTagNamePropertyMapRequestScopeNested");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		SimpleBeanForTesting sbft2 = new SimpleBeanForTesting();
		sbft.setMap(map);
		sbft2.setNestedObject(sbft);
		pageContext.setAttribute(REQUEST_KEY, sbft2, PageContext.REQUEST_SCOPE);
		pageContext.forward("/test/org/apache/struts/taglib/bean/TestSizeTag.jsp");
	}
	public void endSizeTagNamePropertyMapRequestScopeNested(WebResponse response){
		formatAndTest("3", response.getText());
	}


}
