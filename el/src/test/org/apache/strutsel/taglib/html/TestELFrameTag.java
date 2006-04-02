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

import org.apache.strutsel.taglib.utils.JspTagTestCase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

public class TestELFrameTag extends JspTagTestCase {
    private static final String FORWARD_VALUE = "stuff";
    private static final String PATH_VALUE = "/stuff";
    protected ELFrameTag elFrameTag = null;

    public TestELFrameTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(new String[] { TestELFrameTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELFrameTag.class);
    }

    public void setUp() {
        elFrameTag = new ELFrameTag();
        elFrameTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elFrameTag = null;
    }

    /**
     * Tests all attributes unset, which is illegal.  At least one of
     * "forward", "href", or "page" needs to be set.
     */
    public void testPlain() throws ServletException, JspException {
        HttpServletResponse response =
            (HttpServletResponse) pageContext.getResponse();

        boolean gotCorrectException = false;

        try {
            int startTagReturn = elFrameTag.doStartTag();
        } catch (JspException ex) {
            // This is supposed to happen.
            gotCorrectException = true;
        } catch (Exception ex) {
            fail();
        }

        if (!gotCorrectException) {
            fail();
        }
    }

    //     /**
    //      * Tests setting "forward" attribute to a Forward with a null Path.
    //      */
    //     public void testForward()
    //         throws ServletException, JspException {
    //         ActionServlet   actionServlet  = new ActionServlet();
    //         actionServlet.init(pageContext.getServletConfig());
    //         actionServlet.init();
    //         ApplicationConfig  appConfig   = new ApplicationConfig("");
    //         pageContext.setAttribute(Action.APPLICATION_KEY, appConfig,
    //                                  PageContext.APPLICATION_SCOPE);
    //         ActionForward   actionForward  = new ActionForward();
    //         actionForward.setName(FORWARD_VALUE);
    //         actionForward.setPath(PATH_VALUE);
    //         appConfig.addForwardConfig(actionForward);
    //         elFrameTag.setForwardExpr(FORWARD_VALUE);
    //         HttpServletResponse   response =
    //             (HttpServletResponse)pageContext.getResponse();
    //         int startTagReturn  = elFrameTag.doStartTag();
    //         int afterBodyReturn = elFrameTag.doAfterBody();
    //         int endTagReturn    = elFrameTag.doEndTag();
    //     }
    //     public void endForward(com.meterware.httpunit.WebResponse testResponse) {
    //         try {
    //             TestHelper.printResponse(testResponse);
    //             org.w3c.dom.Document document = testResponse.getDOM();
    //             DOMHelper.printNode(document.getDocumentElement());
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //             fail();
    //         }
    //     }
}
