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
package org.apache.strutsel.taglib.logic;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebRequest;
import org.apache.strutsel.taglib.utils.TestFormBean;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

public class TestELNotMatchTag extends JspTestCase {
    protected final static String PROP_KEY = "stringProperty";
    protected final static String VAR_KEY = "stringVar";
    protected final static String VALUE_KEY = "abcde";
    protected final static String PREFIX_VALUE_KEY = "abc";
    protected final static String BAD_VALUE_KEY = "abx";
    protected ELNotMatchTag elNotMatchTag = null;

    public TestELNotMatchTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(new String[] {
                TestELNotMatchTag.class.getName()
            });
    }

    public static Test suite() {
        return new TestSuite(TestELNotMatchTag.class);
    }

    public void setUp() {
        elNotMatchTag = new ELNotMatchTag();
        elNotMatchTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elNotMatchTag = null;
    }

    public void beginMatchStringMatches(WebRequest testRequest) {
    }

    public void testMatchStringMatches()
        throws ServletException, JspException {
        TestFormBean formBean = new TestFormBean();

        formBean.setStringProperty(VALUE_KEY);
        pageContext.setAttribute("testFormBean", formBean);

        pageContext.setAttribute(VAR_KEY, VALUE_KEY);

        elNotMatchTag.setNameExpr("testFormBean");
        elNotMatchTag.setPropertyExpr(PROP_KEY);

        elNotMatchTag.setValueExpr(VALUE_KEY);

        int startTagReturn = elNotMatchTag.doStartTag();

        assertEquals("Match string matches comparison", false,
            startTagReturn == Tag.EVAL_BODY_INCLUDE);
    }

    //     public void testPrefixMatchStringMatches()
    //                                       throws ServletException, JspException {
    //         pageContext.setAttribute(PROP_KEY, PREFIX_VALUE_KEY);
    //         pageContext.setAttribute(VAR_KEY, VALUE_KEY);
    //         elNotMatchTag.setVar(VAR_KEY);
    //         elNotMatchTag.setValue("${" + PROP_KEY + "}");
    //         int startTagReturn = elNotMatchTag.doStartTag();
    //         assertEquals("Match prefix string matches comparison", false, 
    //                      startTagReturn == Tag.EVAL_BODY_INCLUDE);
    //     }
    //     public void testMatchStringNotMatches()
    //                                    throws ServletException, JspException {
    //         pageContext.setAttribute(PROP_KEY, BAD_VALUE_KEY);
    //         pageContext.setAttribute(VAR_KEY, VALUE_KEY);
    //         elNotMatchTag.setVar(VAR_KEY);
    //         elNotMatchTag.setValue("${" + PROP_KEY + "}");
    //         int startTagReturn = elNotMatchTag.doStartTag();
    //         assertEquals("unMatched string not matches comparison", true, 
    //                      startTagReturn == Tag.EVAL_BODY_INCLUDE);
    //     }
}
