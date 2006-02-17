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

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;

import java.util.HashMap;

public class TestELHtmlTag extends JspTagTestCase {
    protected ELHtmlTag elHtmlTag = null;

    public TestELHtmlTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(new String[] { TestELHtmlTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELHtmlTag.class);
    }

    public void setUp() {
        elHtmlTag = new ELHtmlTag();
        elHtmlTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elHtmlTag = null;
    }

    public void testPlain() throws ServletException, JspException {
        Boolean bool = new Boolean(true);

        pageContext.setAttribute("localeFlag", bool);
        elHtmlTag.setXhtmlExpr("true");

        int startTagReturn = elHtmlTag.doStartTag();
        int afterBodyReturn = elHtmlTag.doAfterBody();
        int endTagReturn = elHtmlTag.doEndTag();
    }

    public void endPlain(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();

            DOMHelper.printNode(document.getDocumentElement());

            HashMap attrMap = new HashMap();

            DOMHelper.recordFoundAttributes(testResponse.getDOM(), "/html",
                attrMap);
            DOMHelper.verifyAttributesPresent(attrMap,
                new String[] { "lang", "xml:lang", "xmlns" }, false);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
