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
package org.apache.struts.taglib.html;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.ErrorsTag</code> class.
 *
 * @author James Mitchell
 */
public class TestErrorsTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestErrorsTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestErrorsTag1.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestErrorsTag1.class);
    }

    private void runMyTest(String whichTest, String locale){
		request.setAttribute("runTest", whichTest);
    	pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/html/TestErrorsTag1.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("There is a problem that is preventing the tests to continue!");
		}
    }
    
	private void formatAndTest(String compare, String output) {
		//fix for introduced carriage return / line feeds
		output = output.replaceAll("\r","");
		output = output.replaceAll("\n","");
		output = output.trim();
	    assertEquals(compare, output);
	}

    /*
     * Testing ErrorsTag.
     */
    public void testErrorsDefaultBundle0Errors(){ 
    	runMyTest("testErrorsDefaultBundle0Errors", "");
	}
    public void testErrorsDefaultBundle2Errors(){ 
    	runMyTest("testErrorsDefaultBundle2Errors", "");
	}

    public void testErrorsAlternateBundle0Errors(){ 
    	runMyTest("testErrorsAlternateBundle0Errors", "");
	}
    public void testErrorsAlternateBundle2Errors(){ 
    	runMyTest("testErrorsAlternateBundle2Errors", "");
	}

    public void testErrorsDefaultBundle0Errors_fr(){ 
    	runMyTest("testErrorsDefaultBundle0Errors_fr", "fr");
	}
    public void testErrorsDefaultBundle2Errors_fr(){ 
    	runMyTest("testErrorsDefaultBundle2Errors_fr", "fr");
	}

    public void testErrorsAlternateBundle0Errors_fr(){ 
    	runMyTest("testErrorsAlternateBundle0Errors_fr", "fr");
	}
    public void testErrorsAlternateBundle2Errors_fr(){ 
    	runMyTest("testErrorsAlternateBundle2Errors_fr", "fr");
	}



    public void testErrorsDefaultBundle0ErrorsLocale(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsDefaultBundle0ErrorsLocale", "");
	}
    public void testErrorsDefaultBundle2ErrorsLocale(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsDefaultBundle2ErrorsLocale", "");
	}

    public void testErrorsAlternateBundle0ErrorsLocale(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsAlternateBundle0ErrorsLocale", "");
	}
    public void testErrorsAlternateBundle2ErrorsLocale(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsAlternateBundle2ErrorsLocale", "");
	}

    public void testErrorsDefaultBundle0ErrorsLocale_fr(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsDefaultBundle0ErrorsLocale_fr", "");
	}
    public void testErrorsDefaultBundle2ErrorsLocale_fr(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsDefaultBundle2ErrorsLocale_fr", "");
	}

    public void testErrorsAlternateBundle0ErrorsLocale_fr(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsAlternateBundle0ErrorsLocale_fr", "");
	}
    public void testErrorsAlternateBundle2ErrorsLocale_fr(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsAlternateBundle2ErrorsLocale_fr", "");
	}







    public void testErrorsDefaultBundle0ErrorsName(){ 
    	runMyTest("testErrorsDefaultBundle0ErrorsName", "");
	}
    public void testErrorsDefaultBundle2ErrorsName(){ 
    	runMyTest("testErrorsDefaultBundle2ErrorsName", "");
	}

    public void testErrorsAlternateBundle0ErrorsName(){ 
    	runMyTest("testErrorsAlternateBundle0ErrorsName", "");
	}
    public void testErrorsAlternateBundle2ErrorsName(){ 
    	runMyTest("testErrorsAlternateBundle2ErrorsName", "");
	}

    public void testErrorsDefaultBundle0ErrorsName_fr(){ 
    	runMyTest("testErrorsDefaultBundle0ErrorsName_fr", "fr");
	}
    public void testErrorsDefaultBundle2ErrorsName_fr(){ 
    	runMyTest("testErrorsDefaultBundle2ErrorsName_fr", "fr");
	}

    public void testErrorsAlternateBundle0ErrorsName_fr(){ 
    	runMyTest("testErrorsAlternateBundle0ErrorsName_fr", "fr");
	}
    public void testErrorsAlternateBundle2ErrorsName_fr(){ 
    	runMyTest("testErrorsAlternateBundle2ErrorsName_fr", "fr");
	}



    public void testErrorsDefaultBundle0ErrorsLocaleName(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsDefaultBundle0ErrorsLocaleName", "");
	}
    public void testErrorsDefaultBundle2ErrorsLocaleName(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsDefaultBundle2ErrorsLocaleName", "");
	}

    public void testErrorsAlternateBundle0ErrorsLocaleName(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsAlternateBundle0ErrorsLocaleName", "");
	}
    public void testErrorsAlternateBundle2ErrorsLocaleName(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsAlternateBundle2ErrorsLocaleName", "");
	}

    public void testErrorsDefaultBundle0ErrorsLocaleName_fr(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsDefaultBundle0ErrorsLocaleName_fr", "");
	}
    public void testErrorsDefaultBundle2ErrorsLocaleName_fr(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsDefaultBundle2ErrorsLocaleName_fr", "");
	}

    public void testErrorsAlternateBundle0ErrorsLocaleName_fr(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsAlternateBundle0ErrorsLocaleName_fr", "");
	}
    public void testErrorsAlternateBundle2ErrorsLocaleName_fr(){ 
    	pageContext.setAttribute("MY_LOCALE_KEY", new Locale("fr", "fr"), PageContext.SESSION_SCOPE);
    	runMyTest("testErrorsAlternateBundle2ErrorsLocaleName_fr", "");
	}


}
