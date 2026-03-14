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
 * Unit tests for {@link MatchTag} and its inverse {@link NotMatchTag}.
 *
 * <p>MatchTag evaluates its body when a target value (from a request
 * parameter, header, bean attribute, or cookie) contains/starts-with/ends-with
 * the given {@code value} attribute.</p>
 */
public class TestMatchTag extends TestCase {

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
    // Helpers
    // ------------------------------------------------------------------

    private MatchTag matchTag(String parameter, String value, String location) {
        MatchTag tag = new MatchTag();
        tag.setPageContext(pageContext);
        tag.setParameter(parameter);
        tag.setValue(value);
        if (location != null) {
            tag.setLocation(location);
        }
        return tag;
    }

    // ------------------------------------------------------------------
    // Substring match (no location)
    // ------------------------------------------------------------------

    public void testMatch_Parameter_ContainsValue_EvalBody() throws JspException {
        request.addParameter("q", "foobar");

        MatchTag tag = matchTag("q", "oob", null);
        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }

    public void testMatch_Parameter_DoesNotContainValue_SkipBody() throws JspException {
        request.addParameter("q", "foobar");

        MatchTag tag = matchTag("q", "xyz", null);
        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
    }

    public void testMatch_Parameter_ExactMatch_EvalBody() throws JspException {
        request.addParameter("q", "hello");

        MatchTag tag = matchTag("q", "hello", null);
        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }

    // ------------------------------------------------------------------
    // Location = "start"
    // ------------------------------------------------------------------

    public void testMatch_Parameter_StartsWithValue_EvalBody() throws JspException {
        request.addParameter("q", "foobar");

        MatchTag tag = matchTag("q", "foo", "start");
        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }

    public void testMatch_Parameter_NotStartsWithValue_SkipBody() throws JspException {
        request.addParameter("q", "foobar");

        MatchTag tag = matchTag("q", "bar", "start");
        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
    }

    // ------------------------------------------------------------------
    // Location = "end"
    // ------------------------------------------------------------------

    public void testMatch_Parameter_EndsWithValue_EvalBody() throws JspException {
        request.addParameter("q", "foobar");

        MatchTag tag = matchTag("q", "bar", "end");
        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }

    public void testMatch_Parameter_NotEndsWithValue_SkipBody() throws JspException {
        request.addParameter("q", "foobar");

        MatchTag tag = matchTag("q", "foo", "end");
        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
    }

    // ------------------------------------------------------------------
    // Bean attribute (name)
    // ------------------------------------------------------------------

    public void testMatch_BeanAttribute_Contains_EvalBody() throws JspException {
        pageContext.setAttribute("myBean", "hello world", PageContext.REQUEST_SCOPE);

        MatchTag tag = new MatchTag();
        tag.setPageContext(pageContext);
        tag.setName("myBean");
        tag.setScope("request");
        tag.setValue("world");

        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }

    // ------------------------------------------------------------------
    // Header
    // ------------------------------------------------------------------

    public void testMatch_Header_Contains_EvalBody() throws JspException {
        request.addHeader("User-Agent", "Mozilla/5.0");

        MatchTag tag = new MatchTag();
        tag.setPageContext(pageContext);
        tag.setHeader("User-Agent");
        tag.setValue("Mozilla");

        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }

    public void testMatch_Header_NotContains_SkipBody() throws JspException {
        request.addHeader("User-Agent", "Mozilla/5.0");

        MatchTag tag = new MatchTag();
        tag.setPageContext(pageContext);
        tag.setHeader("User-Agent");
        tag.setValue("MSIE");

        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
    }

    // ------------------------------------------------------------------
    // Invalid location attribute
    // ------------------------------------------------------------------

    public void testMatch_InvalidLocation_ThrowsJspException() {
        request.addParameter("q", "foobar");

        MatchTag tag = matchTag("q", "foo", "middle");
        try {
            tag.doStartTag();
            fail("Should throw JspException for invalid location value");
        } catch (JspException e) {
            // expected
        }
    }

    // ------------------------------------------------------------------
    // NotMatchTag (inverse of MatchTag)
    // ------------------------------------------------------------------

    public void testNotMatch_Parameter_ContainsValue_SkipBody() throws JspException {
        request.addParameter("q", "foobar");

        NotMatchTag tag = new NotMatchTag();
        tag.setPageContext(pageContext);
        tag.setParameter("q");
        tag.setValue("oob");

        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
    }

    public void testNotMatch_Parameter_DoesNotContain_EvalBody() throws JspException {
        request.addParameter("q", "foobar");

        NotMatchTag tag = new NotMatchTag();
        tag.setPageContext(pageContext);
        tag.setParameter("q");
        tag.setValue("xyz");

        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
    }
}
