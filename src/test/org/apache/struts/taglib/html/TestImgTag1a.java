/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestImgTag1a.java,v 1.1 2003/03/12 00:29:44 jmitchell Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/12 00:29:44 $
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
package org.apache.struts.taglib.html;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.ImgTag</code> class.
 *
 * @author James Mitchell
 */
public class TestImgTag1a extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImgTag1a(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestImgTag1a.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImgTag1a.class);
    }

    private void runMyTest(String whichTest, String locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/html/TestImgTag1a.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("There is a problem that is preventing the tests to continue!");
		}
    }

    /*
     * Testing ImgTag.
     */

//--------Testing attributes using page------

    public void testImgPageNameNoScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		pageContext.setAttribute("paramMapNoScope", map, PageContext.REQUEST_SCOPE);
       runMyTest("testImgPageNameNoScope", "");
    }

    public void testImgPageNamePropertyNoScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
		pageContext.setAttribute("paramPropertyMapNoScope", sbft, PageContext.REQUEST_SCOPE);
       runMyTest("testImgPageNamePropertyNoScope", "");
    }

    public void testImgPageNameApplicationScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		pageContext.setAttribute("paramMapApplicationScope", map, PageContext.APPLICATION_SCOPE);
       runMyTest("testImgPageNameApplicationScope", "");
    }

    public void testImgPageNamePropertyApplicationScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
		pageContext.setAttribute("paramPropertyMapApplicationScope", sbft, PageContext.APPLICATION_SCOPE);
       runMyTest("testImgPageNamePropertyApplicationScope", "");
    }

    public void testImgPageNameSessionScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		pageContext.setAttribute("paramMapSessionScope", map, PageContext.SESSION_SCOPE);
       runMyTest("testImgPageNameSessionScope", "");
    }

    public void testImgPageNamePropertySessionScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
		pageContext.setAttribute("paramPropertyMapSessionScope", sbft, PageContext.SESSION_SCOPE);
       runMyTest("testImgPageNamePropertySessionScope", "");
    }

    public void testImgPageNameRequestScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		pageContext.setAttribute("paramMapRequestScope", map, PageContext.REQUEST_SCOPE);
       runMyTest("testImgPageNameRequestScope", "");
    }

    public void testImgPageNamePropertyRequestScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
		pageContext.setAttribute("paramPropertyMapRequestScope", sbft, PageContext.REQUEST_SCOPE);
       runMyTest("testImgPageNamePropertyRequestScope", "");
    }



}
