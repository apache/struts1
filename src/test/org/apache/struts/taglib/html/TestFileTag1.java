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
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.FileTag</code> class.
 *
 * @author James Mitchell
 */
public class TestFileTag1 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestFileTag1(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestFileTag1.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestFileTag1.class);
    }

    private void runMyTest(String whichTest, String locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/html/TestFileTag1.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("There is a problem that is preventing the tests to continue!");
		}
    }

    /*
     * Testing FileTag.
     */
    public void testFileProperty(){ 
    	runMyTest("testFileProperty", "");
	}
    public void testFilePropertyAccept(){ 
    	runMyTest("testFilePropertyAccept", "");
	}
    public void testFilePropertyAccesskey(){ 
    	runMyTest("testFilePropertyAccesskey", "");
	}
    public void testFilePropertyAlt(){ 
    	runMyTest("testFilePropertyAlt", "");
	}
    public void testFilePropertyAltKey1(){ 
    	runMyTest("testFilePropertyAltKey1", "");
	}
    public void testFilePropertyAltKey2(){ 
    	runMyTest("testFilePropertyAltKey2", "");
	}
    public void testFilePropertyAltKey_fr1(){ 
    	runMyTest("testFilePropertyAltKey1_fr", "fr");
	}
    public void testFilePropertyAltKey_fr2(){ 
    	runMyTest("testFilePropertyAltKey2_fr", "fr");
	}
    public void testFilePropertyDisabled_True(){ 
    	runMyTest("testFilePropertyDisabled_True", "");
	}
    public void testFilePropertyDisabled_False1(){ 
    	runMyTest("testFilePropertyDisabled_False1", "");
	}
    public void testFilePropertyDisabled_False2(){ 
    	runMyTest("testFilePropertyDisabled_False2", "");
	}
    public void testFilePropertyOnblur(){ 
    	runMyTest("testFilePropertyOnblur", "");
	}

    public void testFilePropertyOnchange(){ 
    	runMyTest("testFilePropertyOnchange", "");
	}

    public void testFilePropertyOnclick(){ 
    	runMyTest("testFilePropertyOnclick", "");
	}

    public void testFilePropertyOndblclick(){ 
    	runMyTest("testFilePropertyOndblclick", "");
	}

    public void testFilePropertyOnfocus(){ 
    	runMyTest("testFilePropertyOnfocus", "");
	}

    public void testFilePropertyOnkeydown(){ 
    	runMyTest("testFilePropertyOnkeydown", "");
	}

    public void testFilePropertyOnkeypress(){ 
    	runMyTest("testFilePropertyOnkeypress", "");
	}

    public void testFilePropertyOnkeyup(){ 
    	runMyTest("testFilePropertyOnkeyup", "");
	}

    public void testFilePropertyOnmousedown(){ 
    	runMyTest("testFilePropertyOnmousedown", "");
	}

    public void testFilePropertyOnmousemove(){ 
    	runMyTest("testFilePropertyOnmousemove", "");
	}

    public void testFilePropertyOnmouseout(){ 
    	runMyTest("testFilePropertyOnmouseout", "");
	}

    public void testFilePropertyOnmouseover(){ 
    	runMyTest("testFilePropertyOnmouseover", "");
	}

    public void testFilePropertyOnmouseup(){ 
    	runMyTest("testFilePropertyOnmouseup", "");
	}

}
