/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELJavascriptValidatorTag.java,v 1.11 2004/01/18 13:43:11 husted Exp $
 * $Revision: 1.11 $
 * $Date: 2004/01/18 13:43:11 $
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

import org.apache.struts.taglib.html.JavascriptValidatorTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;

/**
 * Custom tag that generates JavaScript for client side validation based
 * on the validation rules loaded by the <code>ValidatorPlugIn</code>
 * defined in the struts-config.xml file.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.JavascriptValidatorTag</code> which
 * provides most of the described functionality.  This subclass allows all
 * attribute values to be specified as expressions utilizing the JavaServer
 * Pages Standard Library expression language.
 *
 * @version $Revision: 1.11 $
 */
public class ELJavascriptValidatorTag extends JavascriptValidatorTag {

    /**
     * Instance variable mapped to "cdata" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String cdataExpr;
    /**
     * Instance variable mapped to "dynamicJavascript" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String dynamicJavascriptExpr;
    /**
     * Instance variable mapped to "formName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String formNameExpr;
    /**
     * Instance variable mapped to "method" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String methodExpr;
    /**
     * Instance variable mapped to "page" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String pageExpr;
    /**
     * Instance variable mapped to "scriptLanguage" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String scriptLanguageExpr;
    /**
     * Instance variable mapped to "src" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String srcExpr;
    /**
     * Instance variable mapped to "staticJavascript" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String staticJavascriptExpr;
    /**
     * Instance variable mapped to "htmlComment" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String htmlCommentExpr;

    /**
     * Getter method for "cdata" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getCdataExpr() { return (cdataExpr); }
    /**
     * Getter method for "dynamicJavascript" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getDynamicJavascriptExpr() { return (dynamicJavascriptExpr); }
    /**
     * Getter method for "formName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getFormNameExpr() { return (formNameExpr); }
    /**
     * Getter method for "method" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getMethodExpr() { return (methodExpr); }
    /**
     * Getter method for "page" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getPageExpr() { return (pageExpr); }
    /**
     * Getter method for "scriptLanguage" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getScriptLanguageExpr() { return (scriptLanguageExpr); }
    /**
     * Getter method for "src" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getSrcExpr() { return (srcExpr); }
    /**
     * Getter method for "staticJavascript" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getStaticJavascriptExpr() { return (staticJavascriptExpr); }
    /**
     * Getter method for "htmlComment" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getHtmlCommentExpr() { return (htmlCommentExpr); }

    /**
     * Setter method for "cdata" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setCdataExpr(String cdataExpr) { this.cdataExpr = cdataExpr; }
    /**
     * Setter method for "dynamicJavascript" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setDynamicJavascriptExpr(String dynamicJavascriptExpr) { this.dynamicJavascriptExpr = dynamicJavascriptExpr; }
    /**
     * Setter method for "formName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setFormNameExpr(String formNameExpr) { this.formNameExpr = formNameExpr; }
    /**
     * Setter method for "method" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setMethodExpr(String methodExpr) { this.methodExpr = methodExpr; }
    /**
     * Setter method for "page" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setPageExpr(String pageExpr) { this.pageExpr = pageExpr; }
    /**
     * Setter method for "scriptLanguage" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setScriptLanguageExpr(String scriptLanguageExpr) { this.scriptLanguageExpr = scriptLanguageExpr; }
    /**
     * Setter method for "src" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setSrcExpr(String srcExpr) { this.srcExpr = srcExpr; }
    /**
     * Setter method for "staticJavascript" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setStaticJavascriptExpr(String staticJavascriptExpr) { this.staticJavascriptExpr = staticJavascriptExpr; }
    /**
     * Setter method for "htmlComment" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setHtmlCommentExpr(String htmlCommentExpr) { this.htmlCommentExpr = htmlCommentExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setCdataExpr(null);
        setDynamicJavascriptExpr(null);
        setFormNameExpr(null);
        setMethodExpr(null);
        setPageExpr(null);
        setScriptLanguageExpr(null);
        setSrcExpr(null);
        setStaticJavascriptExpr(null);
        setHtmlCommentExpr(null);
    }

    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }
    
    /**
     * Processes all attribute values which use the JSTL expression evaluation
     * engine to determine their values.
     *
     * @exception JspException if a JSP exception has occurred
     */
    private void evaluateExpressions() throws JspException {
        String  string  = null;
        Integer integer = null;
        Boolean bool    = null;

        if ((string = EvalHelper.evalString("cdata", getCdataExpr(),
                                            this, pageContext)) != null)
            setCdata(string);

        if ((string = EvalHelper.evalString("dynamicJavascript", getDynamicJavascriptExpr(),
                                            this, pageContext)) != null)
            setDynamicJavascript(string);

        if ((string = EvalHelper.evalString("formName", getFormNameExpr(),
                                            this, pageContext)) != null)
            setFormName(string);

        if ((string = EvalHelper.evalString("method", getMethodExpr(),
                                            this, pageContext)) != null)
            setMethod(string);

        if ((integer = EvalHelper.evalInteger("page", getPageExpr(),
                                              this, pageContext)) != null)
            setPage(integer.intValue());

        if ((bool = EvalHelper.evalBoolean("scriptLanguage", getScriptLanguageExpr(),
                                           this, pageContext)) != null)
            setScriptLanguage(bool.booleanValue());

        if ((string = EvalHelper.evalString("src", getSrcExpr(),
                                            this, pageContext)) != null)
            setSrc(string);

        if ((string = EvalHelper.evalString("staticJavascript", getStaticJavascriptExpr(),
                                            this, pageContext)) != null)
            setStaticJavascript(string);

        if ((string = EvalHelper.evalString("htmlComment", getHtmlCommentExpr(),
                                            this, pageContext)) != null)
            setHtmlComment(string);
    }
}
