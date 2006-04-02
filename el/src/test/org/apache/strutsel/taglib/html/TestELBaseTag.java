/*
 * $Id$
 *
 * Copyright 1999-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.strutsel.taglib.html;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.strutsel.taglib.utils.DOMHelper;
import org.apache.strutsel.taglib.utils.JspTagTestCase;
import org.apache.strutsel.taglib.utils.TestHelper;
import org.w3c.dom.Element;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import java.util.HashMap;

public class TestELBaseTag extends JspTagTestCase {
    protected static final String REQUIRED_HREF_VALUE_KEY = "RequiredHRefValue";
    protected static final String REQUIRED_TARGET_VALUE_KEY =
        "RequiredTargetValue";
    protected ELBaseTag elBaseTag = null;

    public TestELBaseTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(new String[] { TestELBaseTag.class.getName() });
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
     * "BaseTag.doStartTag()", which is exactly what we're testing, so they
     * had better match.
     */
    private String getRequiredHrefValue(HttpServletRequest request) {
        // This sequence of assignments is taken from
        // "BaseTag.doStartTag(), so it'd better match.
        StringBuffer sb = new StringBuffer();

        sb.append(request.getScheme());
        sb.append("://");
        sb.append(request.getServerName());

        if ((request.getScheme().equals("http")
            && (request.getServerPort() != 80))
            || (request.getScheme().equals("https")
            && (request.getServerPort() != 443))) {
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
    public void testHref() throws ServletException, JspException {
        HttpServletResponse response =
            (HttpServletResponse) pageContext.getResponse();
        String requiredHrefValue = getRequiredHrefValue(request);

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

            String header =
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
    public void testTarget() throws ServletException, JspException {
        HttpServletResponse response =
            (HttpServletResponse) pageContext.getResponse();
        String requiredTargetValue = "flork";

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
                new String[] { "href", "target" }, false);
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
        HttpServletResponse response =
            (HttpServletResponse) pageContext.getResponse();
        String requiredTargetValue = "";

        response.addHeader(REQUIRED_TARGET_VALUE_KEY, requiredTargetValue);

        String varName = "targetVar";

        pageContext.setAttribute(varName, "flork");
        elBaseTag.setTargetExpr("${" + varName + "x" + "}");

        int startTagReturn = elBaseTag.doStartTag();
    }

    public void endNonexistentVariable(
        com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            Element docElement = testResponse.getDOM().getDocumentElement();

            DOMHelper.printNode(docElement);

            HashMap attrMap = new HashMap();

            DOMHelper.recordFoundAttributes(testResponse.getDOM(),
                "/html/head/base", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap,
                new String[] { "href", "target" }, false);
            checkAttrValue(attrMap, testResponse, REQUIRED_TARGET_VALUE_KEY,
                "base", "target");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
