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
package org.apache.struts.taglib.logic;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebRequest;
import org.apache.struts.util.LabelValueBean;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.logic.LessThanTag</code> class.
 *
 * @author James Mitchell
 */
public class TestLessThanTag extends JspTestCase {
	
    protected final static String COOKIE_KEY = "org.apache.struts.taglib.logic.COOKIE_KEY";
    protected final static String HEADER_KEY = "org.apache.struts.taglib.logic.HEADER_KEY";
    protected final static String PARAMETER_KEY = "org.apache.struts.taglib.logic.PARAMETER_KEY";
    protected final static String COMPARE_VAL = "4";
    protected final static String COMPARE_TO_VAL = "5";


    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLessThanTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestLessThanTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLessThanTag.class);
    }

    //----- Test initApplication() method --------------------------------------
	
    /**
     * Create cookie for testCookiePresent method test.
    */
    public void beginCookieLessThan(WebRequest testRequest) {
       testRequest.addCookie(COOKIE_KEY, COMPARE_VAL);
    }

    /**
     * Create header for testHeaderLessThan method test.
    */
    public void beginHeaderLessThan(WebRequest testRequest) {
       testRequest.addHeader(HEADER_KEY, COMPARE_VAL);
    }

    /**
     * Create header for testParameterLessThan method test.
    */
    public void beginParameterLessThan(WebRequest testRequest) {
       testRequest.addParameter(PARAMETER_KEY, COMPARE_VAL);
    }

    /**
     * Verify the value stored in a cookie using <code>LessThanTag</code>.
    */
    public void testCookieLessThan() throws ServletException,  JspException {
        LessThanTag ge = new LessThanTag();
        ge.setPageContext(pageContext);
        ge.setCookie(COOKIE_KEY);
        ge.setValue(COMPARE_TO_VAL);

        assertTrue(
        	"Cookie Value (" + COMPARE_VAL + ") is less than value (" + COMPARE_TO_VAL + ")", 
        	ge.condition());
    }
    
    /**
     * Verify the value stored in header using <code>LessThanTag</code>.
    */
    public void testHeaderLessThan() throws ServletException,  JspException {
        LessThanTag ge = new LessThanTag();
        ge.setPageContext(pageContext);
        ge.setHeader(HEADER_KEY);
        ge.setValue(COMPARE_TO_VAL);

        assertTrue(
        	"Header Value (" + COMPARE_VAL + ") is less than value (" + COMPARE_TO_VAL + ")", 
        	ge.condition());
    }

    /**
     * Verify the value stored in parameter using <code>LessThanTag</code>.
    */
    public void testParameterLessThan() throws ServletException,  JspException {
        LessThanTag ge = new LessThanTag();
        ge.setPageContext(pageContext);
        ge.setParameter(PARAMETER_KEY);
        ge.setValue(COMPARE_TO_VAL);

        assertTrue(
        	"Parameter Value (" + COMPARE_VAL + ") is less than value (" + COMPARE_TO_VAL + ")", 
        	ge.condition());
    }
    

    /**
     * Testing <code>LessThanTag</code> using name attribute in
     * the application scope.
    */
    public void testApplicationScopeNameLessThan() 
    	throws ServletException,  JspException {
    
        LessThanTag ge = new LessThanTag();

		String testKey = "testApplicationScopeNameLessThan";
		Integer itgr = new Integer(COMPARE_VAL);
		
		pageContext.setAttribute(testKey, itgr, PageContext.APPLICATION_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("application");
		ge.setValue(COMPARE_TO_VAL);

        assertTrue(
        	"Application scope value from name (" + COMPARE_VAL + ") is less than value (" + COMPARE_TO_VAL + ")", 
        	ge.condition());
    }

    /**
     * Testing <code>LessThanTag</code> using name attribute in
     * the session scope.
    */
    public void testSessionScopeNameLessThan() 
    	throws ServletException,  JspException {
    
        LessThanTag ge = new LessThanTag();

		String testKey = "testSessionScopeNameLessThan";
		Integer itgr = new Integer(COMPARE_VAL);
		
		pageContext.setAttribute(testKey, itgr, PageContext.SESSION_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("session");
		ge.setValue(COMPARE_TO_VAL);

        assertTrue(
        	"Session scope value from name (" + COMPARE_VAL + ") is less than value (" + COMPARE_TO_VAL + ")", 
        	ge.condition());
    }

    /**
     * Testing <code>LessThanTag</code> using name attribute in
     * the request scope.
    */
    public void testRequestScopeNameLessThan() 
    	throws ServletException,  JspException {
    
        LessThanTag ge = new LessThanTag();

		String testKey = "testRequestScopeNameLessThan";
		Integer itgr = new Integer(COMPARE_VAL);
		
		pageContext.setAttribute(testKey, itgr, PageContext.REQUEST_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("request");
		ge.setValue(COMPARE_TO_VAL);

        assertTrue(
        	"Request scope value from name (" + COMPARE_VAL + ") is less than value (" + COMPARE_TO_VAL + ")", 
        	ge.condition());
    }




    /**
     * Testing <code>LessThanTag</code> using name and property attribute in
     * the application scope.
    */
    public void testApplicationScopePropertyLessThan() 
    	throws ServletException,  JspException {
    
        LessThanTag ge = new LessThanTag();

		String testKey = "testApplicationScopePropertyLessThan";
		LabelValueBean lvb = new LabelValueBean("The Key", COMPARE_VAL);
		
		pageContext.setAttribute(testKey, lvb, PageContext.APPLICATION_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("application");
		ge.setProperty("value");
		ge.setValue(COMPARE_TO_VAL);

        assertTrue(
        	"Value (" + COMPARE_TO_VAL + ") is less than value (" + COMPARE_VAL + ")",
        	ge.condition());
    }

    /**
     * Testing <code>LessThanTag</code> using name and property attribute in
     * the session scope.
    */
    public void testSessionScopePropertyLessThan() 
    	throws ServletException,  JspException {
    
        LessThanTag ge = new LessThanTag();

		String testKey = "testSessionScopePropertyLessThan";
		LabelValueBean lvb = new LabelValueBean("The Key", COMPARE_VAL);
		
		pageContext.setAttribute(testKey, lvb, PageContext.SESSION_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("session");
		ge.setProperty("value");
		ge.setValue(COMPARE_TO_VAL);

        assertTrue(
        	"Value (" + COMPARE_TO_VAL + ") is less than value (" + COMPARE_VAL + ")",
        	ge.condition());
    }
    
    /**
     * Testing <code>LessThanTag</code> using name and property attribute in
     * the request scope.
    */
    public void testRequestScopePropertyLessThan() 
    	throws ServletException,  JspException {
    
        LessThanTag ge = new LessThanTag();

		String testKey = "testRequestScopePropertyLessThan";
		LabelValueBean lvb = new LabelValueBean("The Key", COMPARE_VAL);
		
		pageContext.setAttribute(testKey, lvb, PageContext.REQUEST_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("request");
		ge.setProperty("value");
		ge.setValue(COMPARE_TO_VAL);

        assertTrue(
        	"Value (" + COMPARE_TO_VAL + ") is less than value (" + COMPARE_VAL + ")",
        	ge.condition());
    }
    
    
    
}
