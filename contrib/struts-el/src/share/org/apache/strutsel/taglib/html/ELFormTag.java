/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELFormTag.java,v 1.6 2003/02/26 06:12:25 dmkarr Exp $
 * $Revision: 1.6 $
 * $Date: 2003/02/26 06:12:25 $
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

import org.apache.struts.taglib.html.FormTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

/**
 * Custom tag that represents an input form, associated with a bean whose
 * properties correspond to the various fields of the form.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.FormTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @author David M. Karr
 * @version $Revision: 1.6 $
 */
public class ELFormTag extends FormTag {

    /**
     * Instance variable mapped to "action" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String actionExpr;
    /**
     * Instance variable mapped to "enctype" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String enctypeExpr;
    /**
     * Instance variable mapped to "focus" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String focusExpr;
    /**
     * Instance variable mapped to "focusIndex" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String focusIndexExpr;
    /**
     * Instance variable mapped to "method" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String methodExpr;
    /**
     * Instance variable mapped to "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String nameExpr;
    /**
     * Instance variable mapped to "onreset" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String onresetExpr;
    /**
     * Instance variable mapped to "onsubmit" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String onsubmitExpr;
    /**
     * Instance variable mapped to "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String scopeExpr;
    /**
     * Instance variable mapped to "style" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String styleExpr;
    /**
     * Instance variable mapped to "styleClass" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String styleClassExpr;
    /**
     * Instance variable mapped to "styleId" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String styleIdExpr;
    /**
     * Instance variable mapped to "target" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String targetExpr;
    /**
     * Instance variable mapped to "type" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String typeExpr;

    /**
     * Getter method for "action" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getActionExpr() { return (actionExpr); }
    /**
     * Getter method for "enctype" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getEnctypeExpr() { return (enctypeExpr); }
    /**
     * Getter method for "focus" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getFocusExpr() { return (focusExpr); }
    /**
     * Getter method for "focusIndex" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getFocusIndexExpr() { return (focusIndexExpr); }
    /**
     * Getter method for "method" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getMethodExpr() { return (methodExpr); }
    /**
     * Getter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getNameExpr() { return (nameExpr); }
    /**
     * Getter method for "onreset" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getOnresetExpr() { return (onresetExpr); }
    /**
     * Getter method for "onsubmit" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getOnsubmitExpr() { return (onsubmitExpr); }
    /**
     * Getter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getScopeExpr() { return (scopeExpr); }
    /**
     * Getter method for "style" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getStyleExpr() { return (styleExpr); }
    /**
     * Getter method for "styleClass" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getStyleClassExpr() { return (styleClassExpr); }
    /**
     * Getter method for "styleId" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getStyleIdExpr() { return (styleIdExpr); }
    /**
     * Getter method for "target" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getTargetExpr() { return (targetExpr); }
    /**
     * Getter method for "type" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getTypeExpr() { return (typeExpr); }

    /**
     * Setter method for "action" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setActionExpr(String actionExpr) { this.actionExpr = actionExpr; }
    /**
     * Setter method for "enctype" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setEnctypeExpr(String enctypeExpr) { this.enctypeExpr = enctypeExpr; }
    /**
     * Setter method for "focus" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setFocusExpr(String focusExpr) { this.focusExpr = focusExpr; }
    /**
     * Setter method for "focusIndex" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setFocusIndexExpr(String focusIndexExpr) { this.focusIndexExpr = focusIndexExpr; }
    /**
     * Setter method for "method" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setMethodExpr(String methodExpr) { this.methodExpr = methodExpr; }
    /**
     * Setter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setNameExpr(String nameExpr) { this.nameExpr = nameExpr; }
    /**
     * Setter method for "onreset" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setOnresetExpr(String onresetExpr) { this.onresetExpr = onresetExpr; }
    /**
     * Setter method for "onsubmit" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setOnsubmitExpr(String onsubmitExpr) { this.onsubmitExpr = onsubmitExpr; }
    /**
     * Setter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setScopeExpr(String scopeExpr) { this.scopeExpr = scopeExpr; }
    /**
     * Setter method for "style" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setStyleExpr(String styleExpr) { this.styleExpr = styleExpr; }
    /**
     * Setter method for "styleClass" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setStyleClassExpr(String styleClassExpr) { this.styleClassExpr = styleClassExpr; }
    /**
     * Setter method for "styleId" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setStyleIdExpr(String styleIdExpr) { this.styleIdExpr = styleIdExpr; }
    /**
     * Setter method for "target" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setTargetExpr(String targetExpr) { this.targetExpr = targetExpr; }
    /**
     * Setter method for "type" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setTypeExpr(String typeExpr) { this.typeExpr = typeExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setActionExpr(null);
        setEnctypeExpr(null);
        setFocusExpr(null);
        setFocusIndexExpr(null);
        setMethodExpr(null);
        setNameExpr(null);
        setOnresetExpr(null);
        setOnsubmitExpr(null);
        setScopeExpr(null);
        setStyleExpr(null);
        setStyleClassExpr(null);
        setStyleIdExpr(null);
        setTargetExpr(null);
        setTypeExpr(null);
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
     * name, attribute value, and attribute type.  It uses
     * <code>ExpressionUtil.evalNotNull</code> to do the actual evaluation, and
     * it passes to this the name of the current tag, the <code>this</code>
     * pointer, and the current pageContext.
     *
     * @param attrName attribute name being evaluated
     * @param attrValue String value of attribute to be evaluated using EL
     * @param attrType Required resulting type of attribute value
     * @return Resulting attribute value
     */
    private Object   evalAttr(String   attrName,
                              String   attrValue,
                              Class    attrType)
        throws JspException, NullAttributeException
    {
        return (EvalHelper.eval("form", attrName, attrValue,
                                attrType, this, pageContext));
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
            setAction((String) evalAttr("action", getActionExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setEnctype((String) evalAttr("enctype", getEnctypeExpr(),
                                         String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setFocus((String) evalAttr("focus", getFocusExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setFocusIndex((String) evalAttr("focusIndex", getFocusIndexExpr(),
                                            String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setMethod((String) evalAttr("method", getMethodExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setName((String) evalAttr("name", getNameExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setOnreset((String) evalAttr("onreset", getOnresetExpr(),
                                         String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setOnsubmit((String) evalAttr("onsubmit", getOnsubmitExpr(),
                                          String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setScope((String) evalAttr("scope", getScopeExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setStyle((String) evalAttr("style", getStyleExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setStyleClass((String) evalAttr("styleClass", getStyleClassExpr(),
                                            String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setStyleId((String) evalAttr("styleId", getStyleIdExpr(),
                                         String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setTarget((String) evalAttr("target", getTargetExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setType((String) evalAttr("type", getTypeExpr(), String.class));
        } catch (NullAttributeException ex) {
        }
    }
}
