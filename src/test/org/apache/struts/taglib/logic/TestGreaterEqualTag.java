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
 * <code>org.apache.struts.taglib.logic.GreaterEqualTag</code> class.
 *
 * @author James Mitchell
 */
public class TestGreaterEqualTag extends JspTestCase {
	
    protected final static String COOKIE_KEY = "org.apache.struts.taglib.logic.COOKIE_KEY";
    protected final static String HEADER_KEY = "org.apache.struts.taglib.logic.HEADER_KEY";
    protected final static String PARAMETER_KEY = "org.apache.struts.taglib.logic.PARAMETER_KEY";
    protected final static String GREATER_VAL = "5";
    protected final static String LESSER_VAL = "5";


    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestGreaterEqualTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestGreaterEqualTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestGreaterEqualTag.class);
    }

    //----- Test initApplication() method --------------------------------------
	
    /**
     * Create cookie for testCookiePresent method test.
    */
    public void beginCookieGreaterEqual(WebRequest testRequest) {
       testRequest.addCookie(COOKIE_KEY, GREATER_VAL);
    }

    /**
     * Create header for testHeaderGreaterEqual method test.
    */
    public void beginHeaderGreaterEqual(WebRequest testRequest) {
       testRequest.addHeader(HEADER_KEY, GREATER_VAL);
    }

    /**
     * Create header for testParameterGreaterEqual method test.
    */
    public void beginParameterGreaterEqual(WebRequest testRequest) {
       testRequest.addParameter(PARAMETER_KEY, GREATER_VAL);
    }

    /**
     * Verify the value stored in a cookie using <code>GreaterEqualTag</code>.
    */
    public void testCookieGreaterEqual() throws ServletException,  JspException {
        GreaterEqualTag ge = new GreaterEqualTag();
        ge.setPageContext(pageContext);
        ge.setCookie(COOKIE_KEY);
        ge.setValue(LESSER_VAL);

        assertTrue(
        	"Cookie Value (" + GREATER_VAL + ") is greater than or equal to value (" + LESSER_VAL + ")", 
        	ge.condition());
    }
    
    /**
     * Verify the value stored in header using <code>GreaterEqualTag</code>.
    */
    public void testHeaderGreaterEqual() throws ServletException,  JspException {
        GreaterEqualTag ge = new GreaterEqualTag();
        ge.setPageContext(pageContext);
        ge.setHeader(HEADER_KEY);
        ge.setValue(LESSER_VAL);

        assertTrue(
        	"Header Value (" + GREATER_VAL + ") is greater than or equal to value (" + LESSER_VAL + ")", 
        	ge.condition());
    }

    /**
     * Verify the value stored in parameter using <code>GreaterEqualTag</code>.
    */
    public void testParameterGreaterEqual() throws ServletException,  JspException {
        GreaterEqualTag ge = new GreaterEqualTag();
        ge.setPageContext(pageContext);
        ge.setParameter(PARAMETER_KEY);
        ge.setValue(LESSER_VAL);

        assertTrue(
        	"Parameter Value (" + GREATER_VAL + ") is greater than or equal to value (" + LESSER_VAL + ")", 
        	ge.condition());
    }
    

    /**
     * Testing <code>GreaterEqualTag</code> using name attribute in
     * the application scope.
    */
    public void testApplicationScopeNameGreaterEqual() 
    	throws ServletException,  JspException {
    
        GreaterEqualTag ge = new GreaterEqualTag();

		String testKey = "testApplicationScopeNameGreaterEqual";
		Integer itgr = new Integer(GREATER_VAL);
		
		pageContext.setAttribute(testKey, itgr, PageContext.APPLICATION_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("application");
		ge.setValue(LESSER_VAL);

        assertTrue(
        	"Application scope value from name (" + GREATER_VAL + ") is greater than or equal to value (" + LESSER_VAL + ")", 
        	ge.condition());
    }

    /**
     * Testing <code>GreaterEqualTag</code> using name attribute in
     * the session scope.
    */
    public void testSessionScopeNameGreaterEqual() 
    	throws ServletException,  JspException {
    
        GreaterEqualTag ge = new GreaterEqualTag();

		String testKey = "testSessionScopeNameGreaterEqual";
		Integer itgr = new Integer(GREATER_VAL);
		
		pageContext.setAttribute(testKey, itgr, PageContext.SESSION_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("session");
		ge.setValue(LESSER_VAL);

        assertTrue(
        	"Session scope value from name (" + GREATER_VAL + ") is greater than or equal to value (" + LESSER_VAL + ")", 
        	ge.condition());
    }

    /**
     * Testing <code>GreaterEqualTag</code> using name attribute in
     * the request scope.
    */
    public void testRequestScopeNameGreaterEqual() 
    	throws ServletException,  JspException {
    
        GreaterEqualTag ge = new GreaterEqualTag();

		String testKey = "testRequestScopeNameGreaterEqual";
		Integer itgr = new Integer(GREATER_VAL);
		
		pageContext.setAttribute(testKey, itgr, PageContext.REQUEST_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("request");
		ge.setValue(LESSER_VAL);

        assertTrue(
        	"Request scope value from name (" + GREATER_VAL + ") is greater than or equal to value (" + LESSER_VAL + ")", 
        	ge.condition());
    }




    /**
     * Testing <code>GreaterEqualTag</code> using name and property attribute in
     * the application scope.
    */
    public void testApplicationScopePropertyGreaterEqual() 
    	throws ServletException,  JspException {
    
        GreaterEqualTag ge = new GreaterEqualTag();

		String testKey = "testApplicationScopePropertyGreaterEqual";
		LabelValueBean lvb = new LabelValueBean("The Key", GREATER_VAL);
		
		pageContext.setAttribute(testKey, lvb, PageContext.APPLICATION_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("application");
		ge.setProperty("value");
		ge.setValue(LESSER_VAL);

        assertTrue(
        	"Value (" + LESSER_VAL + ") is greater than or equal to value (" + GREATER_VAL + ")",
        	ge.condition());
    }

    /**
     * Testing <code>GreaterEqualTag</code> using name and property attribute in
     * the session scope.
    */
    public void testSessionScopePropertyGreaterEqual() 
    	throws ServletException,  JspException {
    
        GreaterEqualTag ge = new GreaterEqualTag();

		String testKey = "testSessionScopePropertyGreaterEqual";
		LabelValueBean lvb = new LabelValueBean("The Key", GREATER_VAL);
		
		pageContext.setAttribute(testKey, lvb, PageContext.SESSION_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("session");
		ge.setProperty("value");
		ge.setValue(LESSER_VAL);

        assertTrue(
        	"Value (" + LESSER_VAL + ") is greater than or equal to value (" + GREATER_VAL + ")",
        	ge.condition());
    }
    
    /**
     * Testing <code>GreaterEqualTag</code> using name and property attribute in
     * the request scope.
    */
    public void testRequestScopePropertyGreaterEqual() 
    	throws ServletException,  JspException {
    
        GreaterEqualTag ge = new GreaterEqualTag();

		String testKey = "testRequestScopePropertyGreaterEqual";
		LabelValueBean lvb = new LabelValueBean("The Key", GREATER_VAL);
		
		pageContext.setAttribute(testKey, lvb, PageContext.REQUEST_SCOPE);
		ge.setPageContext(pageContext);
		ge.setName(testKey);
		ge.setScope("request");
		ge.setProperty("value");
		ge.setValue(LESSER_VAL);

        assertTrue(
        	"Value (" + LESSER_VAL + ") is greater than or equal to value (" + GREATER_VAL + ")",
        	ge.condition());
    }
    
    
    
}
