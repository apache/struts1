/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELJavascriptValidatorTag.java,v 1.6 2003/02/19 03:53:49 dmkarr Exp $
 * $Revision: 1.6 $
 * $Date: 2003/02/19 03:53:49 $
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
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

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
 * @author David M. Karr
 * @version $Revision: 1.6 $
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
     * Evaluates and returns a single attribute value, given the attribute
     * name, attribute value, and attribute type.  It uses the
     * <code>EvalHelper</code> class to interface to
     * <code>ExpressionUtil.evalNotNull</code> to do the actual evaluation, and
     * it passes to this the name of the current tag, the <code>this</code>
     * pointer, and the current pageContext.
     *
     * @param attrName attribute name being evaluated
     * @param attrValue String value of attribute to be evaluated using EL
     * @param attrType Required resulting type of attribute value
     * @exception NullAttributeException if either the <code>attrValue</code>
     * was null, or the resulting evaluated value was null.
     * @return Resulting attribute value
     */
    private Object   evalAttr(String   attrName,
                              String   attrValue,
                              Class    attrType)
        throws JspException, NullAttributeException
    {
        return (EvalHelper.eval("javascript", attrName, attrValue, attrType,
                                this, pageContext));
    }
    
    /**
     * Processes all attribute values which use the JSTL expression evaluation
     * engine to determine their values.  If any evaluation fails with a
     * <code>NullAttributeException</code> it will just use <code>null</code>
     * as the value.
     *
     * @exception JspException if a JSP exception has occurred
     */
    private void evaluateExpressions() throws JspException {
        try {
            setCdata((String) evalAttr("cdata", getCdataExpr(),
                                       String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setDynamicJavascript((String) evalAttr("dynamicJavascript",
                                                   getDynamicJavascriptExpr(), 
                                                   String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setFormName((String) evalAttr("formName", getFormNameExpr(),
                                          String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setMethod((String) evalAttr("method", getMethodExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setPage(((Integer) evalAttr("page", getPageExpr(), Integer.class)).
                    intValue());
        } catch (NullAttributeException ex) {
        }

        try {
            setSrc((String) evalAttr("src", getSrcExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setStaticJavascript((String) evalAttr("staticJavascript",
                                                  getStaticJavascriptExpr(), 
                                                  String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setHtmlComment((String) evalAttr("htmlComment", getHtmlCommentExpr(),
                                             String.class));
        } catch (NullAttributeException ex) {
        }
    }
}
