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

import org.apache.struts.util.LabelValueBean;
import org.apache.strutsel.taglib.utils.DOMHelper;
import org.apache.strutsel.taglib.utils.JspTagTestCase;
import org.apache.strutsel.taglib.utils.TestHelper;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;

import java.util.HashMap;

public class TestELHiddenTag extends JspTagTestCase {
    protected ELHiddenTag elHiddenTag = null;

    public TestELHiddenTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(new String[] { TestELHiddenTag.class
                .getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELHiddenTag.class);
    }

    public void setUp() {
        elHiddenTag = new ELHiddenTag();
        elHiddenTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elHiddenTag = null;
    }

    public void testPlain() throws ServletException, JspException {
        LabelValueBean bean = new LabelValueBean("junk", "stuff");

        pageContext.setAttribute("testFormBean", bean);

        elHiddenTag.setNameExpr("testFormBean");
        elHiddenTag.setPropertyExpr("value");

        int startTagReturn = elHiddenTag.doStartTag();
        int afterBodyReturn = elHiddenTag.doAfterBody();
        int endTagReturn = elHiddenTag.doEndTag();
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
                new String[] { "type", "name", "value" }, false);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
