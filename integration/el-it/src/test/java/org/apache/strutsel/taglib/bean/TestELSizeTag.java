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
package org.apache.strutsel.taglib.bean;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.strutsel.taglib.utils.TestFormBean;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;

public class TestELSizeTag extends JspTestCase {
    protected ELSizeTag elSizeTag = null;

    public TestELSizeTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(new String[] { TestELSizeTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELSizeTag.class);
    }

    public void setUp() {
        elSizeTag = new ELSizeTag();
        elSizeTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elSizeTag = null;
    }

    public void testPlain() throws ServletException, JspException {
        TestFormBean formBean = new TestFormBean();

        formBean.setArrayProperty(new String[] { "abc", "def", "ghi" });
        pageContext.setAttribute("testFormBean", formBean);

        elSizeTag.setIdExpr("sizeVar");
        elSizeTag.setNameExpr("testFormBean");
        elSizeTag.setPropertyExpr("arrayProperty");

        int startTagReturn = elSizeTag.doStartTag();

        Object object = pageContext.getAttribute("sizeVar");

        if (object != null) {
            if (object instanceof Integer) {
                if (((Integer) object).intValue() != 3) {
                    fail("Size variable \"sizeVar\" is not equal to 3.");
                }
            } else {
                fail("Size variable \"sizeVar\" is not an Integer object.");
            }
        } else {
            fail("Size variable \"sizeVar\" not in page context.");
        }
    }

    public void testCollectionProperty()
        throws ServletException, JspException {
        TestFormBean formBean = new TestFormBean();

        formBean.setArrayProperty(new String[] { "abc", "def", "ghi" });
        pageContext.setAttribute("testFormBean", formBean);

        elSizeTag.setIdExpr("sizeVar");
        elSizeTag.setCollectionExpr("${" + "testFormBean.arrayProperty" + "}");

        int startTagReturn = elSizeTag.doStartTag();

        System.out.println("collection[" + elSizeTag.getCollection() + "]");

        Object object = pageContext.getAttribute("sizeVar");

        if (object != null) {
            if (object instanceof Integer) {
                if (((Integer) object).intValue() != 3) {
                    fail("Size variable \"sizeVar\" is not equal to 3.");
                }
            } else {
                fail("Size variable \"sizeVar\" is not an Integer object.");
            }
        } else {
            fail("Size variable \"sizeVar\" not in page context.");
        }
    }
}
