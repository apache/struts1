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

import java.util.ArrayList;
import java.util.HashMap;

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
 * <code>org.apache.struts.taglib.logic.EmptyTag</code> class.
 *
 * @author James Mitchell
 */
public class TestEmptyTag extends JspTestCase {
	
    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestEmptyTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestEmptyTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestEmptyTag.class);
    }

    private void runNameTest(String testKey, EmptyTag et, 
    		Object o, int scope, String whichScope, boolean condition, boolean useProperty, String property)
	    		throws ServletException,  JspException {

		pageContext.setAttribute(testKey, o, scope);
		et.setPageContext(pageContext);
		et.setName(testKey);
		if (useProperty){
			et.setProperty(property);
		}
		et.setScope(whichScope);

        assertEquals(
        	"Testing " + testKey + " with EmtpyTag in " + whichScope + " scope", 
        	condition,
        	et.condition());
    }

    /**
     * Testing <code>EmptyTag</code> using name attribute in
     * the application scope.
    */
    public void testEmptyTagUsingName() 
    	throws ServletException,  JspException {
    	
    	String tst = "";
    	runNameTest("testStringEmptyString", new EmptyTag(), tst, PageContext.APPLICATION_SCOPE, "application", true, false, null);
    	runNameTest("testStringEmptyString", new EmptyTag(), tst, PageContext.SESSION_SCOPE, "session", true, false, null);
    	runNameTest("testStringEmptyString", new EmptyTag(), tst, PageContext.REQUEST_SCOPE, "request", true, false, null);
		
		tst = "hello";
    	runNameTest("testStringNotEmpty", new EmptyTag(), tst, PageContext.APPLICATION_SCOPE, "application", false, false, null);
    	runNameTest("testStringNotEmpty", new EmptyTag(), tst, PageContext.SESSION_SCOPE, "session", false, false, null);
    	runNameTest("testStringNotEmpty", new EmptyTag(), tst, PageContext.REQUEST_SCOPE, "request", false, false, null);

		// Testing ArrayList
        ArrayList lst = new ArrayList();
    	runNameTest("testArrayListEmpty", new EmptyTag(), lst, PageContext.APPLICATION_SCOPE, "application", true, false, null);
    	runNameTest("testArrayListEmpty", new EmptyTag(), lst, PageContext.SESSION_SCOPE, "session", true, false, null);
    	runNameTest("testArrayListEmpty", new EmptyTag(), lst, PageContext.REQUEST_SCOPE, "request", true, false, null);
		
		lst.add(0, "test");
    	runNameTest("testArrayListNotEmpty", new EmptyTag(), lst, PageContext.APPLICATION_SCOPE, "application", false, false, null);
    	runNameTest("testArrayListNotEmpty", new EmptyTag(), lst, PageContext.SESSION_SCOPE, "session", false, false, null);
    	runNameTest("testArrayListNotEmpty", new EmptyTag(), lst, PageContext.REQUEST_SCOPE, "request", false, false, null);

		// Testing HashMap
		HashMap map = new HashMap();
    	runNameTest("testMapEmpty", new EmptyTag(), map, PageContext.APPLICATION_SCOPE, "application", true, false, null);
    	runNameTest("testMapEmpty", new EmptyTag(), map, PageContext.SESSION_SCOPE, "session", true, false, null);
    	runNameTest("testMapEmpty", new EmptyTag(), map, PageContext.REQUEST_SCOPE, "request", true, false, null);
		
		map.put("testKey", "test");
    	runNameTest("testMapNotEmpty", new EmptyTag(), map, PageContext.APPLICATION_SCOPE, "application", false, false, null);
    	runNameTest("testMapNotEmpty", new EmptyTag(), map, PageContext.SESSION_SCOPE, "session", false, false, null);
    	runNameTest("testMapNotEmpty", new EmptyTag(), map, PageContext.REQUEST_SCOPE, "request", false, false, null);
		
    }
    
    /**
     * Testing <code>EmptyTag</code> using name attribute in
     * the application scope.
    */
    public void testEmptyTagUsingProperty() 
    	throws ServletException,  JspException {
    	
    	LabelValueBean lvb = new LabelValueBean(null, null);
    	runNameTest("testStringEmptyString", new EmptyTag(), lvb, PageContext.APPLICATION_SCOPE, "application", true, true, "value");
    	runNameTest("testStringEmptyString", new EmptyTag(), lvb, PageContext.SESSION_SCOPE, "session", true, true, "value");
    	runNameTest("testStringEmptyString", new EmptyTag(), lvb, PageContext.REQUEST_SCOPE, "request", true, true, "value");
		
		lvb.setValue("");
    	runNameTest("testStringNotEmpty", new EmptyTag(), lvb, PageContext.APPLICATION_SCOPE, "application", true, true, "value");
    	runNameTest("testStringNotEmpty", new EmptyTag(), lvb, PageContext.SESSION_SCOPE, "session", true, true, "value");
    	runNameTest("testStringNotEmpty", new EmptyTag(), lvb, PageContext.REQUEST_SCOPE, "request", true, true, "value");

		lvb.setValue("hello");
    	runNameTest("testStringNotEmpty", new EmptyTag(), lvb, PageContext.APPLICATION_SCOPE, "application", false, true, "value");
    	runNameTest("testStringNotEmpty", new EmptyTag(), lvb, PageContext.SESSION_SCOPE, "session", false, true, "value");
    	runNameTest("testStringNotEmpty", new EmptyTag(), lvb, PageContext.REQUEST_SCOPE, "request", false, true, "value");

		// Testing ArrayList
		SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
		sbft.setList(lst);
    	runNameTest("testArrayListEmpty", new EmptyTag(), sbft, PageContext.APPLICATION_SCOPE, "application", true, true, "list");
    	runNameTest("testArrayListEmpty", new EmptyTag(), sbft, PageContext.SESSION_SCOPE, "session", true, true, "list");
    	runNameTest("testArrayListEmpty", new EmptyTag(), sbft, PageContext.REQUEST_SCOPE, "request", true, true, "list");
		
		lst.add(0, "test");
    	runNameTest("testArrayListNotEmpty", new EmptyTag(), sbft, PageContext.APPLICATION_SCOPE, "application", false, true, "list");
    	runNameTest("testArrayListNotEmpty", new EmptyTag(), sbft, PageContext.SESSION_SCOPE, "session", false, true, "list");
    	runNameTest("testArrayListNotEmpty", new EmptyTag(), sbft, PageContext.REQUEST_SCOPE, "request", false, true, "list");

		// Testing HashMap
		HashMap map = new HashMap();
		sbft.setMap(map);
    	runNameTest("testMapEmpty", new EmptyTag(), sbft, PageContext.APPLICATION_SCOPE, "application", true, true, "map");
    	runNameTest("testMapEmpty", new EmptyTag(), sbft, PageContext.SESSION_SCOPE, "session", true, true, "map");
    	runNameTest("testMapEmpty", new EmptyTag(), sbft, PageContext.REQUEST_SCOPE, "request", true, true, "map");
		
		map.put("testKey", "test");
    	runNameTest("testMapNotEmpty", new EmptyTag(), sbft, PageContext.APPLICATION_SCOPE, "application", false, true, "map");
    	runNameTest("testMapNotEmpty", new EmptyTag(), sbft, PageContext.SESSION_SCOPE, "session", false, true, "map");
    	runNameTest("testMapNotEmpty", new EmptyTag(), sbft, PageContext.REQUEST_SCOPE, "request", false, true, "map");
		
    }
    
    
}
