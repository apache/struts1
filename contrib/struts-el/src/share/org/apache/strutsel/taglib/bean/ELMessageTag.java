/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/bean/ELMessageTag.java,v 1.4 2003/02/19 03:49:50 dmkarr Exp $
 * $Revision: 1.4 $
 * $Date: 2003/02/19 03:49:50 $
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

package org.apache.strutsel.taglib.bean;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.bean.MessageTag;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

/**
 * Custom tag that retrieves an internationalized messages string (with
 * optional parametric replacement) from the <code>ActionResources</code>
 * object stored as a context attribute by our associated
 * <code>ActionServlet</code> implementation.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.bean.MessageTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @author David M. Karr
 * @version $Revision: 1.4 $
 */
public class ELMessageTag extends MessageTag {

    /**
     * Instance variable mapped to "arg0" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String arg0Expr;
    /**
     * Instance variable mapped to "arg1" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String arg1Expr;
    /**
     * Instance variable mapped to "arg2" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String arg2Expr;
    /**
     * Instance variable mapped to "arg3" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String arg3Expr;
    /**
     * Instance variable mapped to "arg4" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String arg4Expr;
    /**
     * Instance variable mapped to "bundle" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String bundleExpr;
    /**
     * Instance variable mapped to "key" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String keyExpr;
    /**
     * Instance variable mapped to "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String localeExpr;
    /**
     * Instance variable mapped to "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String nameExpr;
    /**
     * Instance variable mapped to "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String propertyExpr;
    /**
     * Instance variable mapped to "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String scopeExpr;

    /**
     * Getter method for "arg0" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getArg0Expr() { return (arg0Expr); }
    /**
     * Getter method for "arg1" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getArg1Expr() { return (arg1Expr); }
    /**
     * Getter method for "arg2" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getArg2Expr() { return (arg2Expr); }
    /**
     * Getter method for "arg3" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getArg3Expr() { return (arg3Expr); }
    /**
     * Getter method for "arg4" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getArg4Expr() { return (arg4Expr); }
    /**
     * Getter method for "bundle" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getBundleExpr() { return (bundleExpr); }
    /**
     * Getter method for "key" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getKeyExpr() { return (keyExpr); }
    /**
     * Getter method for "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getLocaleExpr() { return (localeExpr); }
    /**
     * Getter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getNameExpr() { return (nameExpr); }
    /**
     * Getter method for "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getPropertyExpr() { return (propertyExpr); }
    /**
     * Getter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getScopeExpr() { return (scopeExpr); }

    /**
     * Setter method for "arg0" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setArg0Expr(String arg0Expr) { this.arg0Expr = arg0Expr; }
    /**
     * Setter method for "arg1" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setArg1Expr(String arg1Expr) { this.arg1Expr = arg1Expr; }
    /**
     * Setter method for "arg2" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setArg2Expr(String arg2Expr) { this.arg2Expr = arg2Expr; }
    /**
     * Setter method for "arg3" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setArg3Expr(String arg3Expr) { this.arg3Expr = arg3Expr; }
    /**
     * Setter method for "arg4" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setArg4Expr(String arg4Expr) { this.arg4Expr = arg4Expr; }
    /**
     * Setter method for "bundle" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setBundleExpr(String bundleExpr) { this.bundleExpr = bundleExpr; }
    /**
     * Setter method for "key" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setKeyExpr(String keyExpr) { this.keyExpr = keyExpr; }
    /**
     * Setter method for "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setLocaleExpr(String localeExpr) { this.localeExpr = localeExpr; }
    /**
     * Setter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setNameExpr(String nameExpr) { this.nameExpr = nameExpr; }
    /**
     * Setter method for "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setPropertyExpr(String propertyExpr) { this.propertyExpr = propertyExpr; }
    /**
     * Setter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setScopeExpr(String scopeExpr) { this.scopeExpr = scopeExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setArg0Expr(null);
        setArg1Expr(null);
        setArg2Expr(null);
        setArg3Expr(null);
        setArg4Expr(null);
        setBundleExpr(null);
        setKeyExpr(null);
        setLocaleExpr(null);
        setNameExpr(null);
        setPropertyExpr(null);
        setScopeExpr(null);
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
        return (ExpressionUtil.evalNotNull("message", attrName, attrValue,
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
            setArg0((String) evalAttr("arg0", getArg0Expr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setArg1((String) evalAttr("arg1", getArg1Expr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setArg2((String) evalAttr("arg2", getArg2Expr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setArg3((String) evalAttr("arg3", getArg3Expr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setArg4((String) evalAttr("arg4", getArg4Expr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setBundle((String) evalAttr("bundle", getBundleExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setKey((String) evalAttr("key", getKeyExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setLocale((String) evalAttr("locale", getLocaleExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setName((String) evalAttr("name", getNameExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setProperty((String) evalAttr("property", getPropertyExpr(),
                                          String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setScope((String) evalAttr("scope", getScopeExpr(), String.class));
        } catch (NullAttributeException ex) {
        }
    }
}
