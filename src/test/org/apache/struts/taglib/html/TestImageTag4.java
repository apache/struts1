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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.ImageTag</code> class.
 *
 * @author James Mitchell
 */
public class TestImageTag4 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestImageTag4(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestImageTag4.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestImageTag4.class);
    }

    private void runMyTest(String whichTest, String locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/html/TestImageTag4.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("There is a problem that is preventing the tests to continue!");
		}
    }

    /*
     * Testing ImageTag.
     */

//--------Testing attributes using page------

    public void testImagePageKeyOnmousedown(){
        runMyTest("testImagePageKeyOnmousedown", "");
    }

    public void testImagePageKeyOnmousemove(){
        runMyTest("testImagePageKeyOnmousemove", "");
    }

    public void testImagePageKeyOnmouseout(){
        runMyTest("testImagePageKeyOnmouseout", "");
    }

    public void testImagePageKeyOnmouseover(){
        runMyTest("testImagePageKeyOnmouseover", "");
    }

    public void testImagePageKeyOnmouseup(){
        runMyTest("testImagePageKeyOnmouseup", "");
    }

    public void testImagePageKeyProperty(){
        runMyTest("testImagePageKeyProperty", "");
    }

    public void testImagePageKeyStyle(){
        runMyTest("testImagePageKeyStyle", "");
    }

    public void testImagePageKeyStyleClass(){
        runMyTest("testImagePageKeyStyleClass", "");
    }

    public void testImagePageKeyStyleId(){
        runMyTest("testImagePageKeyStyleId", "");
    }

    public void testImagePageKeyTabindex(){
        runMyTest("testImagePageKeyTabindex", "");
    }

    public void testImagePageKeyTitle(){
        runMyTest("testImagePageKeyTitle", "");
    }

    public void testImagePageKeyTitleKeyDefaultBundle(){
        runMyTest("testImagePageKeyTitleKeyDefaultBundle", "");
    }

    public void testImagePageKeyTitleKeyAlternateBundle(){
        runMyTest("testImagePageKeyTitleKeyAlternateBundle", "");
    }

    public void testImagePageKeyTitleKeyDefaultBundle_fr(){
        runMyTest("testImagePageKeyTitleKeyDefaultBundle_fr", "fr");
    }

    public void testImagePageKeyTitleKeyAlterateBundle_fr(){
        runMyTest("testImagePageKeyTitleKeyAlterateBundle_fr", "fr");
    }

    public void testImagePageKeyIndexedArray(){ 
    	ArrayList lst = new ArrayList();
    	lst.add("Test Message");
    	pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
    	runMyTest("testImagePageKeyIndexedArray", "");
	}

    public void testImagePageKeyIndexedArrayProperty(){ 
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting();
    	ArrayList lst = new ArrayList();
    	lst.add("Test Message");
    	sbft.setList(lst);
    	pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
    	runMyTest("testImagePageKeyIndexedArrayProperty", "");
	}

    public void testImagePageKeyIndexedMap(){ 
    	HashMap map = new HashMap();
    	map.put("tst1", "Test Message");
    	pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
    	runMyTest("testImagePageKeyIndexedMap", "");
	}

    public void testImagePageKeyIndexedMapProperty(){ 
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting();
    	HashMap map = new HashMap();
    	map.put("tst1", "Test Message");
    	sbft.setMap(map);
    	pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
    	runMyTest("testImagePageKeyIndexedMapProperty", "");
	}

    public void testImagePageKeyIndexedEnumeration(){ 
    	StringTokenizer st = new StringTokenizer("Test Message");
    	pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
    	runMyTest("testImagePageKeyIndexedEnumeration", "");
	}

    public void testImagePageKeyIndexedEnumerationProperty(){ 
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting();
    	StringTokenizer st = new StringTokenizer("Test Message");
    	sbft.setEnumeration(st);
    	pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
    	runMyTest("testImagePageKeyIndexedEnumerationProperty", "");
	}





}
