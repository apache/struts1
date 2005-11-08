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

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.struts.util.LabelValueBean;
import org.apache.strutsel.taglib.utils.DOMHelper;
import org.apache.strutsel.taglib.utils.JspTagTestCase;
import org.apache.strutsel.taglib.utils.TestHelper;


public class TestELButtonTag
    extends JspTagTestCase {
    protected static final String REQUIRED_DISABLED_VALUE_KEY = 
            "RequiredDisabledValue";
    protected static final String REQUIRED_TYPE_VALUE_KEY  = 
            "RequiredTypeValue";
    protected static final String REQUIRED_VALUE_VALUE_KEY = 
            "RequiredValueValue";
    protected ELButtonTag         elButtonTag              = null;

    protected  static   String[] eventHandlers  =
    {
        "onblur", "onchange", "onclick", "ondblclick", "onfocus", "onkeydown",
        "onkeypress", "onkeyup", "onmousedown", "onmousemove", "onmouseout",
        "onmouseover", "onmouseup"
    };

    public TestELButtonTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(
                new String[] { TestELButtonTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELButtonTag.class);
    }

    public void setUp() {
        elButtonTag = new ELButtonTag();
        elButtonTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elButtonTag = null;
    }

    /**
     * Tests a plain "button" tag, with all default attribute values.
     */
    public void testPlain()
                   throws ServletException, JspException {
        HttpServletResponse response          =
            (HttpServletResponse)pageContext.getResponse();
        String              requiredTypeValue = "button";
        response.addHeader(REQUIRED_TYPE_VALUE_KEY, requiredTypeValue);

        String requiredValueValue = "Click";
        response.addHeader(REQUIRED_VALUE_VALUE_KEY, requiredValueValue);

        int startTagReturn  = elButtonTag.doStartTag();
        int afterBodyReturn = elButtonTag.doAfterBody();
        int endTagReturn    = elButtonTag.doEndTag();
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
                                              new String[] { "type", "value" }, 
                                              false);
            checkAttrValue(attrMap, testResponse, REQUIRED_TYPE_VALUE_KEY, 
                           "button", "type");
            checkAttrValue(attrMap, testResponse, REQUIRED_VALUE_VALUE_KEY, 
                           "button", "value");
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

        elButtonTag.setDisabledExpr("true");

        String requiredDisabledValue = "disabled";
        response.addHeader(REQUIRED_DISABLED_VALUE_KEY, requiredDisabledValue);

        int startTagReturn  = elButtonTag.doStartTag();
        int afterBodyReturn = elButtonTag.doAfterBody();
        int endTagReturn    = elButtonTag.doEndTag();
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
                                              new String[] { "disabled" }, 
                                              true);
            checkAttrValue(attrMap, testResponse, REQUIRED_DISABLED_VALUE_KEY, 
                           "button", "disabled");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    public void testEventHandlers()
        throws ServletException, JspException 
    {
        LabelValueBean  bean  = new LabelValueBean("junk", "stuff");
        pageContext.setAttribute("testFormBean", bean);

        String expr  = "${" + "testFormBean.value" + "}";

        elButtonTag.setOnblurExpr(expr);
        elButtonTag.setOnchangeExpr(expr);
        elButtonTag.setOnclickExpr(expr);
        elButtonTag.setOndblclickExpr(expr);
        elButtonTag.setOnfocusExpr(expr);
        elButtonTag.setOnkeydownExpr(expr);
        elButtonTag.setOnkeypressExpr(expr);
        elButtonTag.setOnkeyupExpr(expr);
        elButtonTag.setOnmousedownExpr(expr);
        elButtonTag.setOnmousemoveExpr(expr);
        elButtonTag.setOnmouseoutExpr(expr);
        elButtonTag.setOnmouseoverExpr(expr);
        elButtonTag.setOnmouseupExpr(expr);

        int startTagReturn  = elButtonTag.doStartTag();
        int afterBodyReturn = elButtonTag.doAfterBody();
        int endTagReturn    = elButtonTag.doEndTag();
    }

    public void
        endEventHandlers(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            HashMap attrMap = new HashMap();
            DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
                                            "/html/body/input", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap, eventHandlers, true);
            for (int ctr = 0; ctr < eventHandlers.length; ++ ctr) {
                if (!((String) attrMap.get(eventHandlers[ctr])).
                    equals("stuff")) {
                    fail();
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
