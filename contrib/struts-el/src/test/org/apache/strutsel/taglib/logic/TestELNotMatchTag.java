/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/test/org/apache/strutsel/taglib/logic/TestELNotMatchTag.java,v 1.3 2002/11/16 05:12:06 jmitchell Exp $
 * $Revision: 1.3 $
 * $Date: 2002/11/16 05:12:06 $
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

package org.apache.strutsel.taglib.logic;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebRequest;
import org.apache.strutsel.taglib.utils.TestFormBean;

public class TestELNotMatchTag
    extends JspTestCase {

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
        junit.awtui.TestRunner.main(
                new String[] { TestELNotMatchTag.class.getName() });
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

        elNotMatchTag.setName("testFormBean");
        elNotMatchTag.setProperty(PROP_KEY);

        elNotMatchTag.setValue(VALUE_KEY);

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
