/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestLinkTag3.java,v 1.5 2004/01/10 21:03:34 dgraham Exp $
 * $Revision: 1.5 $
 * $Date: 2004/01/10 21:03:34 $
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
 * <code>org.apache.struts.taglib.html.LinkTag</code> class.
 *
 */
public class TestLinkTag3 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLinkTag3(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestLinkTag3.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLinkTag3.class);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/html/TestLinkTag3.jsp");
    }

    /*
     * Testing LinkTag.
     */

//--------Testing attributes using forward------

    public void testLinkAction() throws Exception {
        runMyTest("testLinkAction", "");
    }

    public void testLinkActionAccesskey() throws Exception {
        runMyTest("testLinkActionAccesskey", "");
    }

    public void testLinkActionAnchor() throws Exception {
        runMyTest("testLinkActionAnchor", "");
    }

    public void testLinkActionIndexedArray() throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedArray", "");
        }

    public void testLinkActionIndexedArrayProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedArrayProperty", "");
        }

    public void testLinkActionIndexedMap() throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedMap", "");
        }

    public void testLinkActionIndexedMapProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedMapProperty", "");
        }

    public void testLinkActionIndexedEnumeration() throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedEnumeration", "");
        }

    public void testLinkActionIndexedEnumerationProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedEnumerationProperty", "");
        }


    public void testLinkActionIndexedAlternateIdArray() throws Exception {
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        pageContext.setAttribute("lst", lst, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedAlternateIdArray", "");
        }

    public void testLinkActionIndexedAlternateIdArrayProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        lst.add("Test Message");
        sbft.setList(lst);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedAlternateIdArrayProperty", "");
        }

    public void testLinkActionIndexedAlternateIdMap() throws Exception {
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        pageContext.setAttribute("lst", map, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedAlternateIdMap", "");
        }

    public void testLinkActionIndexedAlternateIdMapProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        HashMap map = new HashMap();
        map.put("tst1", "Test Message");
        sbft.setMap(map);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedAlternateIdMapProperty", "");
        }

    public void testLinkActionIndexedAlternateIdEnumeration() throws Exception {
        StringTokenizer st = new StringTokenizer("Test Message");
        pageContext.setAttribute("lst", st, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedAlternateIdEnumeration", "");
        }

    public void testLinkActionIndexedAlternateIdEnumerationProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        StringTokenizer st = new StringTokenizer("Test Message");
        sbft.setEnumeration(st);
        pageContext.setAttribute("lst", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkActionIndexedAlternateIdEnumerationProperty", "");
        }

    public void testLinkActionLinkName() throws Exception {
       runMyTest("testLinkActionLinkName", "");
    }

    public void testLinkActionNameNoScope() throws Exception {
                HashMap map = new HashMap();
                map.put("param1","value1");
                map.put("param2","value2");
                map.put("param3","value3");
                map.put("param4","value4");
                pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
       runMyTest("testLinkActionNameNoScope", "");
    }

    public void testLinkActionNamePropertyNoScope() throws Exception {
                HashMap map = new HashMap();
                map.put("param1","value1");
                map.put("param2","value2");
                map.put("param3","value3");
                map.put("param4","value4");
                SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
                pageContext.setAttribute("paramPropertyMap", sbft, PageContext.REQUEST_SCOPE);
       runMyTest("testLinkActionNamePropertyNoScope", "");
    }

    public void testLinkActionNameApplicationScope() throws Exception {
                HashMap map = new HashMap();
                map.put("param1","value1");
                map.put("param2","value2");
                map.put("param3","value3");
                map.put("param4","value4");
                pageContext.setAttribute("paramMap", map, PageContext.APPLICATION_SCOPE);
       runMyTest("testLinkActionNameApplicationScope", "");
    }

    public void testLinkActionNamePropertyApplicationScope() throws Exception {
                HashMap map = new HashMap();
                map.put("param1","value1");
                map.put("param2","value2");
                map.put("param3","value3");
                map.put("param4","value4");
                SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
                pageContext.setAttribute("paramPropertyMap", sbft, PageContext.APPLICATION_SCOPE);
       runMyTest("testLinkActionNamePropertyApplicationScope", "");
    }

    public void testLinkActionNameSessionScope() throws Exception {
                HashMap map = new HashMap();
                map.put("param1","value1");
                map.put("param2","value2");
                map.put("param3","value3");
                map.put("param4","value4");
                pageContext.setAttribute("paramMap", map, PageContext.SESSION_SCOPE);
       runMyTest("testLinkActionNameSessionScope", "");
    }

    public void testLinkActionNamePropertySessionScope() throws Exception {
                HashMap map = new HashMap();
                map.put("param1","value1");
                map.put("param2","value2");
                map.put("param3","value3");
                map.put("param4","value4");
                SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
                pageContext.setAttribute("paramPropertyMap", sbft, PageContext.SESSION_SCOPE);
       runMyTest("testLinkActionNamePropertySessionScope", "");
    }

    public void testLinkActionNameRequestScope() throws Exception {
                HashMap map = new HashMap();
                map.put("param1","value1");
                map.put("param2","value2");
                map.put("param3","value3");
                map.put("param4","value4");
                pageContext.setAttribute("paramMap", map, PageContext.REQUEST_SCOPE);
       runMyTest("testLinkActionNameRequestScope", "");
    }

    public void testLinkActionNamePropertyRequestScope() throws Exception {
                HashMap map = new HashMap();
                map.put("param1","value1");
                map.put("param2","value2");
                map.put("param3","value3");
                map.put("param4","value4");
                SimpleBeanForTesting sbft = new SimpleBeanForTesting(map);
                pageContext.setAttribute("paramPropertyMap", sbft, PageContext.REQUEST_SCOPE);
       runMyTest("testLinkActionNamePropertyRequestScope", "");
    }

}
