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
package org.apache.struts.taglib.html;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;
import org.apache.struts.util.LabelValueBean;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.FrameTag</code> class.
 *
 * @author James Mitchell
 */
public class TestFrameTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFrameTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestFrameTag1.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFrameTag1.class);
    }

    private void runMyTest(String whichTest, String locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/html/TestFrameTag1.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("There is a problem that is preventing the tests to continue!");
		}
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------

    public void testFrameForward(){
        runMyTest("testFrameForward", "");
    }

    public void testFrameForwardAnchor(){
        runMyTest("testFrameForwardAnchor", "");
    }

    public void testFrameForwardFrameborder(){
        runMyTest("testFrameForwardFrameborder", "");
    }

    public void testFrameForwardFrameName(){
        runMyTest("testFrameForwardFrameName", "");
    }

    public void testFrameForwardLongdesc(){
        runMyTest("testFrameForwardLongdesc", "");
    }

    public void testFrameForwardMarginheight(){
        runMyTest("testFrameForwardMarginheight", "");
    }

    public void testFrameForwardMarginwidth(){
        runMyTest("testFrameForwardMarginwidth", "");
    }

    public void testFrameForwardNameNoScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
       runMyTest("testFrameForwardNameNoScope", "");
    }

    public void testFrameForwardNamePropertyNoScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
		pageContext.setAttribute("paramPropertyMap", sbft, PageContext.REQUEST_SCOPE);
       runMyTest("testFrameForwardNamePropertyNoScope", "");
    }

    public void testFrameForwardNameApplicationScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		pageContext.setAttribute("paramMap", map, PageContext.APPLICATION_SCOPE);
       runMyTest("testFrameForwardNameApplicationScope", "");
    }

    public void testFrameForwardNamePropertyApplicationScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
		pageContext.setAttribute("paramPropertyMap", sbft, PageContext.APPLICATION_SCOPE);
       runMyTest("testFrameForwardNamePropertyApplicationScope", "");
    }

    public void testFrameForwardNameSessionScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		pageContext.setAttribute("paramMap", map, PageContext.SESSION_SCOPE);
       runMyTest("testFrameForwardNameSessionScope", "");
    }

    public void testFrameForwardNamePropertySessionScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
		pageContext.setAttribute("paramPropertyMap", sbft, PageContext.SESSION_SCOPE);
       runMyTest("testFrameForwardNamePropertySessionScope", "");
    }

    public void testFrameForwardNameRequestScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
       runMyTest("testFrameForwardNameRequestScope", "");
    }

    public void testFrameForwardNamePropertyRequestScope(){
 		HashMap map = new HashMap();
		map.put("param1","value1");
		map.put("param2","value2");
		map.put("param3","value3");
		map.put("param4","value4");
		SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
		pageContext.setAttribute("paramPropertyMap", sbft, PageContext.REQUEST_SCOPE);
       runMyTest("testFrameForwardNamePropertyRequestScope", "");
    }


    public void testFrameForwardNoresize1(){
        runMyTest("testFrameForwardNoresize1", "");
    }

    public void testFrameForwardNoresize2(){
        runMyTest("testFrameForwardNoresize2", "");
    }

    public void testFrameForwardNoresize3(){
        runMyTest("testFrameForwardNoresize3", "");
    }

    public void testFrameForwardNoresize4(){
        runMyTest("testFrameForwardNoresize4", "");
    }

    public void testFrameForwardNoresize5(){
        runMyTest("testFrameForwardNoresize5", "");
    }

    public void testFrameForwardNoresize6(){
        runMyTest("testFrameForwardNoresize6", "");
    }
	
}
