/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/test/org/apache/strutsel/taglib/html/TestELFrameTag.java,v 1.2 2002/09/28 04:43:06 dmkarr Exp $
 * $Revision: 1.2 $
 * $Date: 2002/09/28 04:43:06 $
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

import java.net.MalformedURLException;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;

import com.meterware.httpunit.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.xml.transform.*;
import junit.framework.*;
import org.apache.cactus.*;
import org.apache.struts.taglib.html.*;
import org.apache.strutsel.taglib.utils.*;
import org.apache.taglibs.standard.tag.common.core.*;
import org.apache.taglibs.standard.tag.el.core.*;
import org.w3c.dom.*;
import org.xml.sax.*;


public class TestELFrameTag
    extends JspTagTestCase {

    private static final String FORWARD_VALUE   = "stuff";
    private static final String PATH_VALUE      = "/stuff";

    protected ELFrameTag   elFrameTag  = null;

    public TestELFrameTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.
            main(new String[] { TestELFrameTag.class.getName() });
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
    public void testPlain()
                   throws ServletException, JspException {
        HttpServletResponse   response =
            (HttpServletResponse)pageContext.getResponse();

        boolean   gotCorrectException  = false;

        try {
            int startTagReturn  = elFrameTag.doStartTag();
        } catch (JspException ex) {
            // This is supposed to happen.
            gotCorrectException  = true;
        } catch (Exception ex) {
            fail();
        }

        if (!gotCorrectException)
            fail();
    }

    /**
     * Tests setting "forward" attribute to a Forward with a null Path.
     */
    public void testForward()
        throws ServletException, JspException {

        ActionServlet   actionServlet  = new ActionServlet();
        actionServlet.init(pageContext.getServletConfig());
        actionServlet.init();

        ApplicationConfig  appConfig   = new ApplicationConfig("");

        pageContext.setAttribute(Action.APPLICATION_KEY, appConfig,
                                 PageContext.APPLICATION_SCOPE);

        ActionForward   actionForward  = new ActionForward();
        actionForward.setName(FORWARD_VALUE);
        actionForward.setPath(PATH_VALUE);
        appConfig.addForwardConfig(actionForward);

        elFrameTag.setForward(FORWARD_VALUE);

        HttpServletResponse   response =
            (HttpServletResponse)pageContext.getResponse();

        int startTagReturn  = elFrameTag.doStartTag();
        int afterBodyReturn = elFrameTag.doAfterBody();
        int endTagReturn    = elFrameTag.doEndTag();
    }

    public void endForward(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
