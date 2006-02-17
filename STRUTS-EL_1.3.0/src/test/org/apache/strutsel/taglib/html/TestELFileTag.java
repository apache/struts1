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
import org.apache.strutsel.taglib.utils.TestFormBean;
import org.apache.strutsel.taglib.utils.TestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import java.util.HashMap;

public class TestELFileTag extends JspTagTestCase {
    protected static final String PROPERTY_ATTR_VALUE = "stringProperty";
    protected static final String REQUIRED_DISABLED_VALUE_KEY =
        "RequiredDisabledValue";
    protected static final String REQUIRED_TYPE_VALUE_KEY = "RequiredTypeValue";
    protected static final String REQUIRED_VALUE_VALUE_KEY =
        "RequiredValueValue";
    protected static final String REQUIRED_NAME_VALUE_KEY = "RequiredNameValue";
    protected ELFileTag elFileTag = null;

    public TestELFileTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(new String[] { TestELFileTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELFileTag.class);
    }

    public void setUp() {
        elFileTag = new ELFileTag();
        elFileTag.setPageContext(pageContext);
        elFileTag.setPropertyExpr(PROPERTY_ATTR_VALUE);
    }

    public void tearDown() {
        elFileTag = null;
    }

    /**
     * Tests a plain "file" tag, with all default attribute values.
     */
    public void testPlain() throws ServletException, JspException {
        HttpServletResponse response =
            (HttpServletResponse) pageContext.getResponse();

        TestFormBean formBean = new TestFormBean();

        pageContext.setAttribute("testFormBean", formBean);
        elFileTag.setNameExpr("testFormBean");

        String requiredNameValue = PROPERTY_ATTR_VALUE;

        response.addHeader(REQUIRED_NAME_VALUE_KEY, requiredNameValue);

        String requiredTypeValue = "file";

        response.addHeader(REQUIRED_TYPE_VALUE_KEY, requiredTypeValue);

        String requiredValueValue = "";

        response.addHeader(REQUIRED_VALUE_VALUE_KEY, requiredValueValue);

        int startTagReturn = elFileTag.doStartTag();
        int afterBodyReturn = elFileTag.doAfterBody();
        int endTagReturn = elFileTag.doEndTag();
    }

    public void endPlain(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();

            DOMHelper.printNode(document.getDocumentElement());

            HashMap attrMap = new HashMap();

            DOMHelper.recordFoundAttributes(testResponse.getDOM(),
                "/html/body/input", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap,
                new String[] { "name", "type", "value" }, false);
            checkAttrValue(attrMap, testResponse, REQUIRED_NAME_VALUE_KEY,
                "file", "name");
            checkAttrValue(attrMap, testResponse, REQUIRED_TYPE_VALUE_KEY,
                "file", "type");
            checkAttrValue(attrMap, testResponse, REQUIRED_VALUE_VALUE_KEY,
                "file", "value");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Tests the "disabled" attribute.
     */
    public void testDisabled()
        throws ServletException, JspException {
        TestFormBean formBean = new TestFormBean();

        pageContext.setAttribute("testFormBean", formBean);
        elFileTag.setNameExpr("testFormBean");

        elFileTag.setDisabledExpr("true");

        String requiredDisabledValue = "disabled";

        response.addHeader(REQUIRED_DISABLED_VALUE_KEY, requiredDisabledValue);

        int startTagReturn = elFileTag.doStartTag();
        int afterBodyReturn = elFileTag.doAfterBody();
        int endTagReturn = elFileTag.doEndTag();
    }

    public void endDisabled(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();

            DOMHelper.printNode(document.getDocumentElement());

            HashMap attrMap = new HashMap();

            DOMHelper.recordFoundAttributes(testResponse.getDOM(),
                "/html/body/input", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap,
                new String[] { "disabled" }, true);
            checkAttrValue(attrMap, testResponse, REQUIRED_DISABLED_VALUE_KEY,
                "file", "disabled");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
