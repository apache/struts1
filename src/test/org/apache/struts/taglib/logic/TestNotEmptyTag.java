/*
 * Copyright 2026 The Struts1-Forever Project.
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
package org.apache.struts.taglib.logic;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import junit.framework.TestCase;

import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;
import org.apache.struts.mock.MockPageContext;
import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;

/**
 * Unit tests for {@link NotEmptyTag}.
 *
 * <p>NotEmptyTag is the logical inverse of EmptyTag: it evaluates its body
 * when the target value is <em>not</em> empty.</p>
 */
public class TestNotEmptyTag extends TestCase {

    protected MockServletContext context;
    protected MockServletConfig config;
    protected MockHttpSession session;
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    protected MockPageContext pageContext;

    public void setUp() {
        context = new MockServletContext();
        config = new MockServletConfig(context);
        session = new MockHttpSession(context);
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
        pageContext = new MockPageContext(config, request, response);
    }

    // ------------------------------------------------------------------
    // EmptyTag (and NotEmptyTag) require the 'name' attribute — they
    // operate on beans/attributes in scope, not on raw request parameters.
    // Setting only 'parameter' (without 'name') throws JspException.
    // ------------------------------------------------------------------

    public void testNotEmpty_NoNameAttribute_ThrowsJspException() {
        // Neither 'name' nor 'parameter' sets the required name field in EmptyTag.
        // EmptyTag.condition() guards against this.
        NotEmptyTag tag = new NotEmptyTag();
        tag.setPageContext(pageContext);
        // intentionally omit setName() — only parameter is set
        tag.setParameter("someParam");

        try {
            tag.doStartTag();
            fail("Should throw JspException when 'name' attribute is absent");
        } catch (JspException e) {
            // expected — "empty.noNameAttribute"
        }
    }

    // ------------------------------------------------------------------
    // Request attribute (name) — present and non-empty
    // ------------------------------------------------------------------

    public void testNotEmpty_Name_NonEmptyString_EvalBody() throws JspException {
        pageContext.setAttribute("myBean", "nonEmpty", PageContext.REQUEST_SCOPE);

        NotEmptyTag tag = new NotEmptyTag();
        tag.setPageContext(pageContext);
        tag.setName("myBean");
        tag.setScope("request");

        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }

    public void testNotEmpty_Name_EmptyString_SkipBody() throws JspException {
        pageContext.setAttribute("myBean", "", PageContext.REQUEST_SCOPE);

        NotEmptyTag tag = new NotEmptyTag();
        tag.setPageContext(pageContext);
        tag.setName("myBean");
        tag.setScope("request");

        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
    }

    public void testNotEmpty_Name_EmptyCollection_SkipBody() throws JspException {
        pageContext.setAttribute("myList", new ArrayList(), PageContext.REQUEST_SCOPE);

        NotEmptyTag tag = new NotEmptyTag();
        tag.setPageContext(pageContext);
        tag.setName("myList");
        tag.setScope("request");

        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
    }

    public void testNotEmpty_Name_NonEmptyCollection_EvalBody() throws JspException {
        ArrayList list = new ArrayList();
        list.add("item");
        pageContext.setAttribute("myList", list, PageContext.REQUEST_SCOPE);

        NotEmptyTag tag = new NotEmptyTag();
        tag.setPageContext(pageContext);
        tag.setName("myList");
        tag.setScope("request");

        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }

    public void testNotEmpty_Name_NonEmptyMap_EvalBody() throws JspException {
        HashMap map = new HashMap();
        map.put("key", "value");
        pageContext.setAttribute("myMap", map, PageContext.REQUEST_SCOPE);

        NotEmptyTag tag = new NotEmptyTag();
        tag.setPageContext(pageContext);
        tag.setName("myMap");
        tag.setScope("request");

        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }

    public void testNotEmpty_Name_Null_SkipBody() throws JspException {
        // attribute is null
        pageContext.setAttribute("myBean", null, PageContext.REQUEST_SCOPE);

        NotEmptyTag tag = new NotEmptyTag();
        tag.setPageContext(pageContext);
        tag.setName("myBean");
        tag.setScope("request");

        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
    }
}
