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
package org.apache.struts.taglib.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebResponse;
import org.apache.struts.taglib.SimpleBeanForTesting;


/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.logic.IterateTag</code> class.
 *
 * @author James Mitchell
 */
public class TestIterateTag extends JspTestCase {
	
	private int iterations = 2;
	
    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestIterateTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestIterateTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestIterateTag.class);
    }


   /**
     * Testing <code>IterateTag</code> using name attribute in
     * the application scope.
     * 
	 * Tests the equivalent of this tag in a jsp:
	 *   <logic:iterate id="theId" name="testApplicationScopeNameIterateList"
	 * 		scope="application">
     * 
     */

	// ========= Application
    public void testApplicationScopeNameIterateList() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testApplicationScopeNameIterateList";

        ArrayList lst = new ArrayList();
        for (int i = 0; i < iterations; i++) {
	       	lst.add("test" + i);
		}
		
		pageContext.setAttribute(testKey, lst, 
									PageContext.APPLICATION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("application");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endApplicationScopeNameIterateList (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	
	// ========= Session
    public void testSessionScopeNameIterateList() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testSessionScopeNameIterateList";

        ArrayList lst = new ArrayList();
        for (int i = 0; i < iterations; i++) {
	       	lst.add("test" + i);
		}
		
		pageContext.setAttribute(testKey, lst, 
									PageContext.SESSION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("session");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endSessionScopeNameIterateList (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}

	// ========= Request
    public void testRequestScopeNameIterateList() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testRequestScopeNameIterateList";

        ArrayList lst = new ArrayList();
        for (int i = 0; i < iterations; i++) {
	       	lst.add("test" + i);
		}
		
		pageContext.setAttribute(testKey, lst, 
									PageContext.REQUEST_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("request");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endRequestScopeNameIterateList (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}


   /**
     * Testing <code>IterateTag</code> using name attribute in
     * the application scope.
     * 
	 * Tests the equivalent of this tag in a jsp:
	 *   <logic:iterate id="theId" name="testApplicationScopeNameIterateList"
	 * 		property="list" scope="application">
     * 
     */
    
	// ========= Application
    public void testApplicationScopePropertyIterateList() 
    	throws ServletException,  JspException, IOException {
		
		
		String testKey = "testApplicationScopePropertyIterate";

        ArrayList lst = new ArrayList();
        for (int i = 0; i < iterations; i++) {
	       	lst.add("test" + i);
		}
		
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setList(lst);
		
		pageContext.setAttribute(testKey, sbft, 
									PageContext.APPLICATION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
		/*
		 * Tests the equivalent of this tag in a jsp:
		 *   <logic:iterate id="theId" name="testApplicationScopePropertyIterate"
		 * 		scope="application">
		 */
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("application");
        tag.setProperty("list");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endApplicationScopePropertyIterateList (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}
		
		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");
		
	    assertEquals(compare, output);
	}

    
	// ========= Session
    public void testSessionScopePropertyIteratesList() 
    	throws ServletException,  JspException, IOException {
		
		
		String testKey = "testSessionScopePropertyIterate";

        ArrayList lst = new ArrayList();
        for (int i = 0; i < iterations; i++) {
	       	lst.add("test" + i);
		}
		
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setList(lst);
		
		pageContext.setAttribute(testKey, sbft, 
									PageContext.SESSION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
		/*
		 * Tests the equivalent of this tag in a jsp:
		 *   <logic:iterate id="theId" name="testSessionScopePropertyIterate"
		 * 		scope="session">
		 */
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("session");
        tag.setProperty("list");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endSessionScopePropertyIterateList (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}
		
		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");
		
	    assertEquals(compare, output);
	}

    
	// ========= Request
    public void testRequestScopePropertyIteratesList() 
    	throws ServletException,  JspException, IOException {
		
		
		String testKey = "testRequestScopePropertyIterate";

        ArrayList lst = new ArrayList();
        for (int i = 0; i < iterations; i++) {
	       	lst.add("test" + i);
		}
		
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setList(lst);
		
		pageContext.setAttribute(testKey, sbft, 
									PageContext.REQUEST_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
		/*
		 * Tests the equivalent of this tag in a jsp:
		 *   <logic:iterate id="theId" name="testRequestScopePropertyIterate"
		 * 		scope="request">
		 */
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("request");
        tag.setProperty("list");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endRequestScopePropertyIterateList (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}
		
		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");
		
	    assertEquals(compare, output);
	}



   /**
     * Testing <code>IterateTag</code> using name attribute in
     * the application scope.
     * 
	 * Tests the equivalent of this tag in a jsp:
	 *   <logic:iterate id="theId" name="testApplicationScopeNameIterateEnumeration"
	 * 		scope="application">
     * 
     */

	// ========= Application
    public void testApplicationScopeNameIterateEnumeration() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testApplicationScopeNameIterateEnumeration";
		
		StringTokenizer st = new StringTokenizer("Application Scope Name Iterate Enumeration");

		pageContext.setAttribute(testKey, st, 
									PageContext.APPLICATION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("application");
		
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();

	}

	public void endApplicationScopeNameIterateEnumeration (WebResponse response){
	    String output = response.getText();
	    StringTokenizer st = new StringTokenizer("Application Scope Name Iterate Enumeration");
	    String compare = "";
	    
	    while (st.hasMoreTokens()) {
        	compare += st.nextToken();
     	}
	    
		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}

	// ========= Session
    public void testSessionScopeNameIterateEnumeration() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testSessionScopeNameIterateEnumeration";
		
		StringTokenizer st = new StringTokenizer("Session Scope Name Iterate Enumeration");

		pageContext.setAttribute(testKey, st, 
									PageContext.SESSION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("session");
		
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();

	}

	public void endSessionScopeNameIterateEnumeration (WebResponse response){
	    String output = response.getText();
	    StringTokenizer st = new StringTokenizer("Session Scope Name Iterate Enumeration");
	    String compare = "";
	    
	    while (st.hasMoreTokens()) {
        	compare += st.nextToken();
     	}
	    
		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}

	// ========= Request
    public void testRequestScopeNameIterateEnumeration() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testRequestScopeNameIterateEnumeration";
		
		StringTokenizer st = new StringTokenizer("Request Scope Name Iterate Enumeration");

		pageContext.setAttribute(testKey, st, 
									PageContext.REQUEST_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("request");
		
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();

	}

	public void endRequestScopeNameIterateEnumeration (WebResponse response){
	    String output = response.getText();
	    StringTokenizer st = new StringTokenizer("Request Scope Name Iterate Enumeration");
	    String compare = "";
	    
	    while (st.hasMoreTokens()) {
        	compare += st.nextToken();
     	}
	    
		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}


   /**
     * Testing <code>IterateTag</code> using property attribute in
     * the application scope.
     * 
	 * Tests the equivalent of this tag in a jsp:
	 *   <logic:iterate id="theId" name="testApplicationScopePropertyIterateEnumeration"
	 * 		scope="application">
     * 
     */

	// ========= Application
    public void testApplicationScopePropertyIterateEnumeration() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testApplicationScopePropertyIterateEnumeration";
		
		StringTokenizer st = new StringTokenizer("Application Scope Property Iterate Enumeration");

		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setEnumeration(st);

		pageContext.setAttribute(testKey, sbft, 
									PageContext.APPLICATION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("application");
        tag.setProperty("enumeration");
		
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();

	}

	public void endApplicationScopePropertyIterateEnumeration (WebResponse response){
	    String output = response.getText();
	    StringTokenizer st = new StringTokenizer("Application Scope Property Iterate Enumeration");
	    String compare = "";
	    
	    while (st.hasMoreTokens()) {
        	compare += st.nextToken();
     	}
	    
		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}

	// ========= Session
    public void testSessionScopePropertyIterateEnumeration() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testSessionScopePropertyIterateEnumeration";
		
		StringTokenizer st = new StringTokenizer("Session Scope Property Iterate Enumeration");

		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setEnumeration(st);

		pageContext.setAttribute(testKey, sbft, 
									PageContext.SESSION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("session");
        tag.setProperty("enumeration");
		
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();

	}

	public void endSessionScopePropertyIterateEnumeration (WebResponse response){
	    String output = response.getText();
	    StringTokenizer st = new StringTokenizer("Session Scope Property Iterate Enumeration");
	    String compare = "";
	    
	    while (st.hasMoreTokens()) {
        	compare += st.nextToken();
     	}
	    
		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}

	// ========= Request
    public void testRequestScopePropertyIterateEnumeration() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testRequestScopePropertyIterateEnumeration";
		
		StringTokenizer st = new StringTokenizer("Request Scope Property Iterate Enumeration");

		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setEnumeration(st);

		pageContext.setAttribute(testKey, sbft, 
									PageContext.REQUEST_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("request");
        tag.setProperty("enumeration");
		
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print((String)pageContext.getAttribute("theId"));
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();

	}

	public void endRequestScopePropertyIterateEnumeration (WebResponse response){
	    String output = response.getText();
	    StringTokenizer st = new StringTokenizer("Request Scope Property Iterate Enumeration");
	    String compare = "";
	    
	    while (st.hasMoreTokens()) {
        	compare += st.nextToken();
     	}
	    
		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}





   /**
     * Testing <code>IterateTag</code> using name attribute in
     * the application scope.
     * 
	 * Tests the equivalent of this tag in a jsp:
	 *   <logic:iterate id="theId" name="testApplicationScopeNameIterateMap"
	 * 		scope="application">
     * 
     */

	// ========= Application
    public void testApplicationScopeNameIterateMap() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testApplicationScopeNameIterateMap";

        HashMap map = new HashMap();
        for (int i = 0; i < iterations; i++) {
	        map.put("test" + i,"test" + i);
		}
		
		pageContext.setAttribute(testKey, map, 
									PageContext.APPLICATION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("application");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endApplicationScopeNameIterateMap (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	
	// ========= Session
    public void testSessionScopeNameIterateMap() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testSessionScopeNameIterateMap";

        HashMap map = new HashMap();
        for (int i = 0; i < iterations; i++) {
	        map.put("test" + i,"test" + i);
		}
		
		pageContext.setAttribute(testKey, map, 
									PageContext.SESSION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("session");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endSessionScopeNameIterateMap (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	
	// ========= Request
    public void testRequestScopeNameIterateMap() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testRequestScopeNameIterateMap";

        HashMap map = new HashMap();
        for (int i = 0; i < iterations; i++) {
	        map.put("test" + i,"test" + i);
		}
		
		pageContext.setAttribute(testKey, map, 
									PageContext.REQUEST_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("request");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endRequestScopeNameIterateMap (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	



   /**
     * Testing <code>IterateTag</code> using property attribute in
     * the application scope.
     * 
	 * Tests the equivalent of this tag in a jsp:
	 *   <logic:iterate id="theId" name="testApplicationScopePropertyIterateMap"
	 * 		scope="application">
     * 
     */

	// ========= Application
    public void testApplicationScopePropertyIterateMap() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testApplicationScopePropertyIterateMap";

        HashMap map = new HashMap();
        for (int i = 0; i < iterations; i++) {
	        map.put("test" + i,"test" + i);
		}
		
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setMap(map);
		
		pageContext.setAttribute(testKey, sbft, 
									PageContext.APPLICATION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("application");
        tag.setProperty("map");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endApplicationScopePropertyIterateMap (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	
	// ========= Session
    public void testSessionScopePropertyIterateMap() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testSessionScopePropertyIterateMap";

        HashMap map = new HashMap();
        for (int i = 0; i < iterations; i++) {
	        map.put("test" + i,"test" + i);
		}

		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setMap(map);
		
		pageContext.setAttribute(testKey, sbft, 
									PageContext.SESSION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("session");
        tag.setProperty("map");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endSessionScopePropertyIterateMap (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	
	// ========= Request
    public void testRequestScopePropertyIterateMap() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testRequestScopePropertyIterateMap";

        HashMap map = new HashMap();
        for (int i = 0; i < iterations; i++) {
	        map.put("test" + i,"test" + i);
		}
		
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setMap(map);
		
		pageContext.setAttribute(testKey, sbft, 
									PageContext.REQUEST_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("request");
        tag.setProperty("map");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endRequestScopePropertyIterateMap (WebResponse response){
	    String output = response.getText();
	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += "test" + i;
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	




   /**
     * Testing <code>IterateTag</code> using name attribute in
     * the application scope.
     * 
	 * Tests the equivalent of this tag in a jsp:
	 *   <logic:iterate id="theId" name="testApplicationScopeNameIterateArray"
	 * 		scope="application">
     * 
     */

	// ========= Application
    public void testApplicationScopeNameIterateArray() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testApplicationScopeNameIterateArray";

        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}
		
		pageContext.setAttribute(testKey, tst, 
									PageContext.APPLICATION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("application");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endApplicationScopeNameIterateArray (WebResponse response){
	    String output = response.getText();
        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}

	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += tst[i];
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	
	// ========= Session
    public void testSessionScopeNameIterateArray() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testSessionScopeNameIterateArray";

        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}
		
		pageContext.setAttribute(testKey, tst, 
									PageContext.SESSION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("session");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endSessionScopeNameIterateArray (WebResponse response){
	    String output = response.getText();
        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}

	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += tst[i];
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	
	// ========= Request
    public void testRequestScopeNameIterateArray() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testRequestScopeNameIterateArray";

        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}
		
		pageContext.setAttribute(testKey, tst, 
									PageContext.REQUEST_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("request");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endRequestScopeNameIterateArray (WebResponse response){
	    String output = response.getText();
        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}

	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += tst[i];
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	

   /**
     * Testing <code>IterateTag</code> using property attribute in
     * the application scope.
     * 
	 * Tests the equivalent of this tag in a jsp:
	 *   <logic:iterate id="theId" name="testApplicationScopePropertyIterateArray"
	 * 		scope="application">
     * 
     */

	// ========= Application
    public void testApplicationScopePropertyIterateArray() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testApplicationScopePropertyIterateArray";

        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setArray(tst);
		
		pageContext.setAttribute(testKey, sbft, 
									PageContext.APPLICATION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("application");
        tag.setProperty("array");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endApplicationScopePropertyIterateArray (WebResponse response){
	    String output = response.getText();
        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}

	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += tst[i];
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	
	// ========= Session
    public void testSessionScopePropertyIterateArray() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testSessionScopePropertyIterateArray";

        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}

		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setArray(tst);

		pageContext.setAttribute(testKey, sbft, 
									PageContext.SESSION_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("session");
        tag.setProperty("array");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endSessionScopePropertyIterateArray (WebResponse response){
	    String output = response.getText();
        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}

	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += tst[i];
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	
	// ========= Request
    public void testRequestScopePropertyIterateArray() 
    	throws ServletException,  JspException, IOException {
		
		String testKey = "testRequestScopePropertyIterateArray";

        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}
		
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
		sbft.setArray(tst);

		pageContext.setAttribute(testKey, sbft, 
									PageContext.REQUEST_SCOPE);

        IterateTag tag = new IterateTag();
		tag.setPageContext(pageContext);
        tag.setId("theId");
        tag.setName(testKey);
        tag.setScope("request");
        tag.setProperty("array");
		
		int iteration = 0;
		tag.doStartTag();
		tag.doInitBody();
		do
		{
			out.print(pageContext.getAttribute("theId"));
		    iteration++;
		
		} while (tag.doAfterBody() == tag.EVAL_BODY_TAG);
		tag.doEndTag();
		assertEquals(iterations, iteration);
	}

	public void endRequestScopePropertyIterateArray (WebResponse response){
	    String output = response.getText();
        String[] tst = new String[iterations];
 		for (int i = 0; i < tst.length; i++) {
			tst[i] = "test" + i;
		}

	    String compare = "";
	    for (int i = 0; i < iterations; i++) {
			compare += tst[i];
		}

		//fix for introduced carriage return / line feeds
		output = compare.replaceAll("\r","");
		output = compare.replaceAll("\n","");

	    assertEquals(compare, output);
	}
	

}
