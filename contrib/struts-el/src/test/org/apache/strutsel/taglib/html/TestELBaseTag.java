/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/test/org/apache/strutsel/taglib/html/TestELBaseTag.java,v 1.5 2003/07/26 05:48:03 dmkarr Exp $
 * $Revision: 1.5 $
 * $Date: 2003/07/26 05:48:03 $
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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
 *    any, must include the following acknowledgement:
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

package org.apache.strutsel.taglib.html;

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.strutsel.taglib.utils.DOMHelper;
import org.apache.strutsel.taglib.utils.JspTagTestCase;
import org.apache.strutsel.taglib.utils.TestHelper;
import org.w3c.dom.Element;


public class TestELBaseTag
    extends JspTagTestCase {
    protected static final String REQUIRED_HREF_VALUE_KEY   = 
            "RequiredHRefValue";
    protected static final String REQUIRED_TARGET_VALUE_KEY = 
            "RequiredTargetValue";
    protected ELBaseTag           elBaseTag                 = null;

    public TestELBaseTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(
                new String[] { TestELBaseTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELBaseTag.class);
    }

    public void setUp() {
        elBaseTag = new ELBaseTag();
        elBaseTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elBaseTag = null;
    }

    /**
     * Method to get the required value of the "href" attribute, based on
     * values in the request.  This is taken directly from
     * "BaseTag.doStartTag()", which is exactly what we're testing, so they had
     * better match.
     */
    private String getRequiredHrefValue(HttpServletRequest request) {
        // This sequence of assignments is taken from
        // "BaseTag.doStartTag(), so it'd better match.
        StringBuffer sb = new StringBuffer();
        sb.append(request.getScheme());
        sb.append("://");
        sb.append(request.getServerName());

        if ((request.getScheme().equals("http") && 
                (request.getServerPort() != 80)) || 
            (request.getScheme().equals("https") && 
                (request.getServerPort() != 443))) {
            sb.append(":");
            sb.append(request.getServerPort());
        }

        sb.append(request.getRequestURI());

        String requiredHrefValue = sb.toString();

        return (requiredHrefValue);
    }

    /**
     * Tests the value of the "href" attribute, based on the values in the
     * request.
     */
    public void testHref()
                  throws ServletException, JspException {
        HttpServletResponse response          = (HttpServletResponse)pageContext.getResponse();
        String              requiredHrefValue = getRequiredHrefValue(request);
        System.out.println("requiredHrefValue[" + requiredHrefValue + "]");
        response.addHeader(REQUIRED_HREF_VALUE_KEY, requiredHrefValue);
        response.addHeader("abc", "def");
        response.addHeader("ghi", "jkl");
        response.addHeader("mno", "pqr");
//         response.addHeader("stuvwx", requiredHrefValue);
        response.addHeader("stuvwx", "abc");

        int startTagReturn = elBaseTag.doStartTag();
    }

    public void endHref(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            Element docElement = testResponse.getDOM().getDocumentElement();
            DOMHelper.printNode(docElement);

            HashMap attrMap = new HashMap();
            DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
                                            "/html/head/base", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap, new String[] { "href" }, 
                                              false);
            String   header   =
                testResponse.getHeaderField(REQUIRED_HREF_VALUE_KEY);
            System.out.println("[header[" + header + "]]");
            checkAttrValue(attrMap, testResponse, REQUIRED_HREF_VALUE_KEY, 
                           "base", "href");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Tests the "target" attribute, based on a particular value set into the
     * target property, using the EL engine to evalute the property value.
     */
    public void testTarget()
                    throws ServletException, JspException {
        HttpServletResponse response            = (HttpServletResponse)pageContext.getResponse();
        String              requiredTargetValue = "flork";
        response.addHeader(REQUIRED_TARGET_VALUE_KEY, requiredTargetValue);

        String varName = "targetVar";
        pageContext.setAttribute(varName, requiredTargetValue);
        elBaseTag.setTargetExpr("${" + varName + "}");

        int startTagReturn = elBaseTag.doStartTag();
    }

    public void endTarget(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            Element docElement = testResponse.getDOM().getDocumentElement();
            DOMHelper.printNode(docElement);

            HashMap attrMap = new HashMap();
            DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
                                            "/html/head/base", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap, 
                                              new String[] { "href", "target" }, 
                                              false);
            checkAttrValue(attrMap, testResponse, REQUIRED_TARGET_VALUE_KEY, 
                           "base", "target");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Tests the "target" attribute, like in "testTarget", but specifies a
     * variable name in the EL expression which doesn't exist, which should
     * result in an empty "target" attribute in the output.
     */
    public void testNonexistentVariable()
                                 throws ServletException, JspException {
        HttpServletResponse response            = (HttpServletResponse)pageContext.getResponse();
        String              requiredTargetValue = "";
        response.addHeader(REQUIRED_TARGET_VALUE_KEY, requiredTargetValue);

        String varName = "targetVar";
        pageContext.setAttribute(varName, "flork");
        elBaseTag.setTargetExpr("${" + varName + "x" + "}");

        int startTagReturn = elBaseTag.doStartTag();
    }

    public void endNonexistentVariable(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            Element docElement = testResponse.getDOM().getDocumentElement();
            DOMHelper.printNode(docElement);

            HashMap attrMap = new HashMap();
            DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
                                            "/html/head/base", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap, 
                                              new String[] { "href", "target" }, 
                                              false);
            checkAttrValue(attrMap, testResponse, REQUIRED_TARGET_VALUE_KEY, 
                           "base", "target");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
