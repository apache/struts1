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

public class TestELCheckboxTag extends JspTagTestCase {
    protected static final String PROPERTY_ATTR_VALUE = "stringProperty";
    protected static final String REQUIRED_CHECKED_VALUE_KEY =
        "RequiredCheckedValue";
    protected static final String REQUIRED_DISABLED_VALUE_KEY =
        "RequiredDisabledValue";
    protected static final String REQUIRED_NAME_VALUE_KEY = "RequiredNameValue";
    protected static final String REQUIRED_TYPE_VALUE_KEY = "RequiredTypeValue";
    protected static final String REQUIRED_VALUE_VALUE_KEY =
        "RequiredValueValue";
    protected ELCheckboxTag elCheckboxTag = null;

    public TestELCheckboxTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(new String[] {
                TestELCheckboxTag.class.getName()
            });
    }

    public static Test suite() {
        return new TestSuite(TestELCheckboxTag.class);
    }

    public void setUp() {
        elCheckboxTag = new ELCheckboxTag();
        elCheckboxTag.setPageContext(pageContext);
        elCheckboxTag.setPropertyExpr(PROPERTY_ATTR_VALUE);
    }

    public void tearDown() {
        elCheckboxTag = null;
    }

    /**
     * Tests a plain "checkbox" tag, with the "property" attribute set to a
     * plain value.
     */
    public void testPlain() throws ServletException, JspException {
        HttpServletResponse response =
            (HttpServletResponse) pageContext.getResponse();
        String requiredNameValue = PROPERTY_ATTR_VALUE;

        response.addHeader(REQUIRED_NAME_VALUE_KEY, requiredNameValue);

        String requiredTypeValue = "checkbox";

        response.addHeader(REQUIRED_TYPE_VALUE_KEY, requiredTypeValue);

        String requiredValueValue = "on";

        response.addHeader(REQUIRED_VALUE_VALUE_KEY, requiredValueValue);

        TestFormBean formBean = new TestFormBean();

        pageContext.setAttribute("testFormBean", formBean);

        elCheckboxTag.setNameExpr("testFormBean");

        int startTagReturn = elCheckboxTag.doStartTag();
        int afterBodyReturn = elCheckboxTag.doAfterBody();
        int endTagReturn = elCheckboxTag.doEndTag();
    }

    public void endPlain(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);
            DOMHelper.printNode(testResponse.getDOM().getDocumentElement());

            HashMap attrMap = new HashMap();

            DOMHelper.recordFoundAttributes(testResponse.getDOM(),
                "/html/body/input", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap,
                new String[] { "name", "type", "value" }, false);
            checkAttrValue(attrMap, testResponse, REQUIRED_NAME_VALUE_KEY,
                "checkbox", "name");
            checkAttrValue(attrMap, testResponse, REQUIRED_TYPE_VALUE_KEY,
                "checkbox", "type");
            checkAttrValue(attrMap, testResponse, REQUIRED_VALUE_VALUE_KEY,
                "checkbox", "value");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    //     /**
    //      * Tests the "property" attribute referencing an indexed value, verifying
    //      * it emits the "checked" attribute.
    //      */
    //     public void testCollectionProperty()
    //                                 throws ServletException, JspException {
    //         HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
    //         elCheckboxTag.setName(PROPERTY_ATTR_VALUE + "[1]");
    //         String requiredNameValue = PROPERTY_ATTR_VALUE + "[1]";
    //         response.addHeader(REQUIRED_NAME_VALUE_KEY, requiredNameValue);
    //         String requiredTypeValue = "checkbox";
    //         response.addHeader(REQUIRED_TYPE_VALUE_KEY, requiredTypeValue);
    //         String requiredValueValue = "on";
    //         response.addHeader(REQUIRED_VALUE_VALUE_KEY, requiredValueValue);
    //         String requiredCheckedValue = "checked";
    //         response.addHeader(REQUIRED_CHECKED_VALUE_KEY, requiredCheckedValue);
    //         ArrayList list = new ArrayList();
    //         list.add("off");
    //         list.add("on");
    //         pageContext.setAttribute(PROPERTY_ATTR_VALUE, list, 
    //                                  PageContext.PAGE_SCOPE);
    //         int startTagReturn  = elCheckboxTag.doStartTag();
    //         int afterBodyReturn = elCheckboxTag.doAfterBody();
    //         int endTagReturn    = elCheckboxTag.doEndTag();
    //     }
    //     public void endCollectionProperty(com.meterware.httpunit.WebResponse testResponse) {
    //         try {
    //             TestHelper.printResponse(testResponse);
    //             Element docElement = testResponse.getDOM().getDocumentElement();
    //             DOMHelper.printNode(docElement);
    //             HashMap attrMap = new HashMap();
    //             DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
    //                                             "/html/body/input", attrMap);
    //             DOMHelper.verifyAttributesPresent(attrMap, 
    //                                               new String[] {
    //                 "name", "type", "value", "checked"
    //             }, false);
    //             checkAttrValue(attrMap, testResponse, REQUIRED_NAME_VALUE_KEY, 
    //                            "checkbox", "name");
    //             checkAttrValue(attrMap, testResponse, REQUIRED_TYPE_VALUE_KEY, 
    //                            "checkbox", "type");
    //             checkAttrValue(attrMap, testResponse, REQUIRED_VALUE_VALUE_KEY, 
    //                            "checkbox", "value");
    //             checkAttrValue(attrMap, testResponse, REQUIRED_CHECKED_VALUE_KEY, 
    //                            "checkbox", "checked");
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //             fail();
    //         }
    //     }
    //     /**
    //      * Tests the "property" attribute referencing an indexed value, verifying
    //      * it does not emit the "checked" attribute.
    //      */
    //     public void testCollectionPropertyNotChecked()
    //                                           throws ServletException, 
    //                                                  JspException {
    //         HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
    //         elCheckboxTag.setName(PROPERTY_ATTR_VALUE + "[0]");
    //         String requiredNameValue = PROPERTY_ATTR_VALUE + "[0]";
    //         response.addHeader(REQUIRED_NAME_VALUE_KEY, requiredNameValue);
    //         String requiredTypeValue = "checkbox";
    //         response.addHeader(REQUIRED_TYPE_VALUE_KEY, requiredTypeValue);
    //         String requiredValueValue = "on";
    //         response.addHeader(REQUIRED_VALUE_VALUE_KEY, requiredValueValue);
    //         ArrayList list = new ArrayList();
    //         list.add("off");
    //         list.add("on");
    //         pageContext.setAttribute(PROPERTY_ATTR_VALUE, list, 
    //                                  PageContext.PAGE_SCOPE);
    //         int startTagReturn  = elCheckboxTag.doStartTag();
    //         int afterBodyReturn = elCheckboxTag.doAfterBody();
    //         int endTagReturn    = elCheckboxTag.doEndTag();
    //     }
    //     public void endCollectionPropertyNotChecked(com.meterware.httpunit.WebResponse testResponse) {
    //         try {
    //             TestHelper.printResponse(testResponse);
    //             Element docElement = testResponse.getDOM().getDocumentElement();
    //             DOMHelper.printNode(docElement);
    //             HashMap attrMap = new HashMap();
    //             DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
    //                                             "/html/body/input", attrMap);
    //             DOMHelper.verifyAttributesPresent(attrMap, 
    //                                               new String[] {
    //                 "name", "type", "value"
    //             }, false);
    //             DOMHelper.verifyAttributesNotPresent(attrMap, 
    //                                                  new String[] { "checked" });
    //             checkAttrValue(attrMap, testResponse, REQUIRED_NAME_VALUE_KEY, 
    //                            "checkbox", "name");
    //             checkAttrValue(attrMap, testResponse, REQUIRED_TYPE_VALUE_KEY, 
    //                            "checkbox", "type");
    //             checkAttrValue(attrMap, testResponse, REQUIRED_VALUE_VALUE_KEY, 
    //                            "checkbox", "value");
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //             fail();
    //         }
    //     }
    //     /**
    //      * Tests the "disabled" attribute.
    //      */
    //     public void testDisabled()
    //                       throws ServletException, JspException {
    //         elCheckboxTag.setDisabled(true);
    //         String requiredDisabledValue = "disabled";
    //         response.addHeader(REQUIRED_DISABLED_VALUE_KEY, requiredDisabledValue);
    //         elCheckboxTag.setName("flork");
    //         int startTagReturn  = elCheckboxTag.doStartTag();
    //         int afterBodyReturn = elCheckboxTag.doAfterBody();
    //         int endTagReturn    = elCheckboxTag.doEndTag();
    //     }
    //     public void endDisabled(com.meterware.httpunit.WebResponse testResponse) {
    //         try {
    //             TestHelper.printResponse(testResponse);
    //             Element docElement = testResponse.getDOM().getDocumentElement();
    //             DOMHelper.printNode(docElement);
    //             HashMap attrMap = new HashMap();
    //             DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
    //                                             "/html/body/input", attrMap);
    //             DOMHelper.verifyAttributesPresent(attrMap, 
    //                                               new String[] { "disabled" }, 
    //                                               true);
    //             checkAttrValue(attrMap, testResponse, REQUIRED_DISABLED_VALUE_KEY, 
    //                            "checkbox", "disabled");
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //             fail();
    //         }
    //     }
    //     /**
    //      * Tests the "property" and "checked" attributes, using a simple value in
    //      * page scope.
    //      */
    //     public void testProperty()
    //                       throws ServletException, JspException {
    //         HttpServletResponse response          = (HttpServletResponse)pageContext.getResponse();
    //         String              requiredNameValue = PROPERTY_ATTR_VALUE;
    //         response.addHeader(REQUIRED_NAME_VALUE_KEY, requiredNameValue);
    //         String requiredTypeValue = "checkbox";
    //         response.addHeader(REQUIRED_TYPE_VALUE_KEY, requiredTypeValue);
    //         String requiredValueValue = "on";
    //         response.addHeader(REQUIRED_VALUE_VALUE_KEY, requiredValueValue);
    //         String requiredCheckedValue = "checked";
    //         response.addHeader(REQUIRED_CHECKED_VALUE_KEY, requiredCheckedValue);
    //         pageContext.setAttribute(PROPERTY_ATTR_VALUE, "on", 
    //                                  PageContext.PAGE_SCOPE);
    //         int startTagReturn  = elCheckboxTag.doStartTag();
    //         int afterBodyReturn = elCheckboxTag.doAfterBody();
    //         int endTagReturn    = elCheckboxTag.doEndTag();
    //     }
    //     public void endProperty(com.meterware.httpunit.WebResponse testResponse) {
    //         try {
    //             TestHelper.printResponse(testResponse);
    //             Element docElement = testResponse.getDOM().getDocumentElement();
    //             DOMHelper.printNode(docElement);
    //             HashMap attrMap = new HashMap();
    //             DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
    //                                             "/html/body/input", attrMap);
    //             DOMHelper.verifyAttributesPresent(attrMap, 
    //                                               new String[] {
    //                 "name", "type", "value", "checked"
    //             }, false);
    //             checkAttrValue(attrMap, testResponse, REQUIRED_NAME_VALUE_KEY, 
    //                            "checkbox", "name");
    //             checkAttrValue(attrMap, testResponse, REQUIRED_TYPE_VALUE_KEY, 
    //                            "checkbox", "type");
    //             checkAttrValue(attrMap, testResponse, REQUIRED_VALUE_VALUE_KEY, 
    //                            "checkbox", "value");
    //             checkAttrValue(attrMap, testResponse, REQUIRED_CHECKED_VALUE_KEY, 
    //                            "checkbox", "checked");
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //             fail();
    //         }
    //     }
}
