/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/test/org/apache/strutsel/taglib/html/TestELHtmlTag.java,v 1.1 2002/10/14 03:11:09 dmkarr Exp $
 * $Revision: 1.1 $
 * $Date: 2002/10/14 03:11:09 $
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
import javax.servlet.jsp.JspException;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.struts.util.LabelValueBean;
import org.apache.strutsel.taglib.utils.JspTagTestCase;
import org.apache.strutsel.taglib.utils.TestHelper;
import org.apache.strutsel.taglib.utils.DOMHelper;

public class TestELHtmlTag
    extends JspTagTestCase {

    protected  ELHtmlTag elHtmlTag = null;

    public TestELHtmlTag(String theName) {
        super(theName);
    }
    
    public static void main(String[] args) {
        junit.awtui.TestRunner.
            main(new String[] { TestELHtmlTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELHtmlTag.class);
    }

    public void setUp() {
        elHtmlTag  = new ELHtmlTag();
        elHtmlTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elHtmlTag = null;
    }

    public void testPlain()
        throws ServletException, JspException {

        Boolean   bool  = new Boolean(true);
        pageContext.setAttribute("localeFlag", bool);
//         elHtmlTag.setLocale("${localeFlag}");
        elHtmlTag.setLocale(true);
        elHtmlTag.setXhtml(true);

        int startTagReturn  = elHtmlTag.doStartTag();
        int afterBodyReturn = elHtmlTag.doAfterBody();
        int endTagReturn    = elHtmlTag.doEndTag();
    }

    public void endPlain(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            HashMap attrMap = new HashMap();
            DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
                                            "/html", attrMap);
            DOMHelper.
                verifyAttributesPresent(attrMap,
                                        new String[] { "lang", "xml:lang" }, 
                                        false);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
