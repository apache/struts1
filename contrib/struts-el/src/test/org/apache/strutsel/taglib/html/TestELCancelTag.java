/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/test/org/apache/strutsel/taglib/html/TestELCancelTag.java,v 1.4 2003/02/19 03:54:39 dmkarr Exp $
 * $Revision: 1.4 $
 * $Date: 2003/02/19 03:54:39 $
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.strutsel.taglib.utils.DOMHelper;
import org.apache.strutsel.taglib.utils.JspTagTestCase;
import org.apache.strutsel.taglib.utils.TestHelper;


public class TestELCancelTag
    extends JspTagTestCase {

    protected static final String PROPERTY_ATTR_VALUE         = "stuff";
    protected static final String REQUIRED_DISABLED_VALUE_KEY = 
            "RequiredDisabledValue";
    protected static final String REQUIRED_TYPE_VALUE_KEY  = 
            "RequiredTypeValue";
    protected static final String REQUIRED_VALUE_VALUE_KEY = 
            "RequiredValueValue";
    protected static final String REQUIRED_NAME_VALUE_KEY = 
            "RequiredNameValue";
    protected ELCancelTag         elCancelTag              = null;

    public TestELCancelTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(
                new String[] { TestELCancelTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELCancelTag.class);
    }

    public void setUp() {
        elCancelTag = new ELCancelTag();
        elCancelTag.setPageContext(pageContext);
        elCancelTag.setPropertyExpr(PROPERTY_ATTR_VALUE);
    }

    public void tearDown() {
        elCancelTag = null;
    }

    /**
     * Tests a plain "cancel" tag, with all default attribute values.
     */
    public void testPlain()
                   throws ServletException, JspException {
        HttpServletResponse response          = (HttpServletResponse)pageContext.getResponse();

        String              requiredNameValue = PROPERTY_ATTR_VALUE;
        response.addHeader(REQUIRED_NAME_VALUE_KEY, requiredNameValue);
        String              requiredTypeValue = "submit";
        response.addHeader(REQUIRED_TYPE_VALUE_KEY, requiredTypeValue);

        String requiredValueValue = "Cancel";
        response.addHeader(REQUIRED_VALUE_VALUE_KEY, requiredValueValue);

        int startTagReturn  = elCancelTag.doStartTag();
        int afterBodyReturn = elCancelTag.doAfterBody();
        int endTagReturn    = elCancelTag.doEndTag();
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
                                              new String[] {
                "name", "type", "value", "onclick" }, 
                                              false);

            checkAttrValue(attrMap, testResponse, REQUIRED_NAME_VALUE_KEY, 
                           "cancel", "name");
            checkAttrValue(attrMap, testResponse, REQUIRED_TYPE_VALUE_KEY, 
                           "cancel", "type");
            checkAttrValue(attrMap, testResponse, REQUIRED_VALUE_VALUE_KEY, 
                           "cancel", "value");
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

        elCancelTag.setDisabledExpr("true");

        String requiredDisabledValue = "disabled";
        response.addHeader(REQUIRED_DISABLED_VALUE_KEY, requiredDisabledValue);

        int startTagReturn  = elCancelTag.doStartTag();
        int afterBodyReturn = elCancelTag.doAfterBody();
        int endTagReturn    = elCancelTag.doEndTag();
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
                           "cancel", "disabled");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
