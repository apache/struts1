/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/logic/ELNotMatchTag.java,v 1.8 2004/01/18 13:43:12 husted Exp $
 * $Revision: 1.8 $
 * $Date: 2004/01/18 13:43:12 $
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

import org.apache.struts.taglib.logic.NotMatchTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;

/**
 * Evalute the nested body content of this tag if the specified value
 * is a substring of the specified variable.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.logic.NotMatchTag</code> which provides most
 * of the described functionality.  This subclass allows all attribute values
 * to be specified as expressions utilizing the JavaServer Pages Standard
 * Library expression language.
 *
 * @version $Revision: 1.8 $
 */
public class ELNotMatchTag extends NotMatchTag {

    /**
     * Instance variable mapped to "cookie" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String cookieExpr;
    /**
     * Instance variable mapped to "header" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String headerExpr;
    /**
     * Instance variable mapped to "location" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String locationExpr;
    /**
     * Instance variable mapped to "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String nameExpr;
    /**
     * Instance variable mapped to "parameter" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String parameterExpr;
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
     * Instance variable mapped to "value" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String valueExpr;

    /**
     * Getter method for "cookie" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getCookieExpr() { return (cookieExpr); }
    /**
     * Getter method for "header" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getHeaderExpr() { return (headerExpr); }
    /**
     * Getter method for "location" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getLocationExpr() { return (locationExpr); }
    /**
     * Getter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getNameExpr() { return (nameExpr); }
    /**
     * Getter method for "parameter" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getParameterExpr() { return (parameterExpr); }
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
     * Getter method for "value" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getValueExpr() { return (valueExpr); }

    /**
     * Setter method for "cookie" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setCookieExpr(String cookieExpr) { this.cookieExpr = cookieExpr; }
    /**
     * Setter method for "header" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setHeaderExpr(String headerExpr) { this.headerExpr = headerExpr; }
    /**
     * Setter method for "location" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setLocationExpr(String locationExpr) { this.locationExpr = locationExpr; }
    /**
     * Setter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setNameExpr(String nameExpr) { this.nameExpr = nameExpr; }
    /**
     * Setter method for "parameter" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setParameterExpr(String parameterExpr) { this.parameterExpr = parameterExpr; }
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
     * Setter method for "value" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setValueExpr(String valueExpr) { this.valueExpr = valueExpr; }

    /**
     * String value of expression to be evaluated.
     */
    private String   expr;

    /**
     * Returns the string value of the expression.  This value will be
     * evaluated by the JSTL EL engine.
     */
    public  String   getExpr() {
        return (expr);
    }
    
    /**
     * Sets the string value of the expression.  This expression will be
     * evaluated by the JSTL EL engine.
     */
    public  void  setExpr(String expr) {
        this.expr = expr;
    }

    /**
     * Evaluated value of expression.
     */
    private String   exprValue;

    /**
     * Returns the evaluated expression.
     */
    public  String   getExprValue() {
        return (exprValue);
    }

    /**
     * Sets the evaluated expression.
     */
    public  void  setExprValue(String exprValue) {
        this.exprValue  = exprValue;
    }

    /**
     * Releases state of custom tag so this instance can be reused.
     */
    public void release()
    {
        super.release();
        setCookieExpr(null);
        setHeaderExpr(null);
        setLocationExpr(null);
        setNameExpr(null);
        setParameterExpr(null);
        setPropertyExpr(null);
        setScopeExpr(null);
        setValueExpr(null);
        setExpr(null);
        setExprValue(null);
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
     * Evaluates the condition that is being tested by this particular tag,
     * and returns <code>true</code> if the nested body content of this tag
     * should be evaluated, or <code>false</code> if it should be skipped.
     *
     * @param desired Desired value for a true result
     * @exception JspException if a JSP exception occurs
     */
    protected boolean condition(boolean desired) throws JspException {
        boolean   result   = false;
        if (getExprValue() != null) {
            result   =
                ELMatchSupport.condition(desired, getExprValue(), value,
                                         location, messages, pageContext);
        }
        else {
            result   = super.condition(desired);
        }
        return (result);
    }

    /**
     * Processes all attribute values which use the JSTL expression evaluation
     * engine to determine their values.
     *
     * @exception JspException if a JSP exception has occurred
     */
    private void evaluateExpressions() throws JspException {
        String  string  = null;

        if ((string = EvalHelper.evalString("cookie", getCookieExpr(),
                                            this, pageContext)) != null)
            setCookie(string);

        if ((string = EvalHelper.evalString("expr", getExpr(),
                                            this, pageContext)) != null)
            setExprValue(string);

        if ((string = EvalHelper.evalString("header", getHeaderExpr(),
                                            this, pageContext)) != null)
            setHeader(string);

        if ((string = EvalHelper.evalString("location", getLocationExpr(),
                                            this, pageContext)) != null)
            setLocation(string);

        if ((string = EvalHelper.evalString("name", getNameExpr(),
                                            this, pageContext)) != null)
            setName(string);

        if ((string = EvalHelper.evalString("parameter", getParameterExpr(),
                                            this, pageContext)) != null)
            setParameter(string);

        if ((string = EvalHelper.evalString("property", getPropertyExpr(),
                                            this, pageContext)) != null)
            setProperty(string);

        if ((string = EvalHelper.evalString("scope", getScopeExpr(),
                                            this, pageContext)) != null)
            setScope(string);

        if ((string = EvalHelper.evalString("value", getValueExpr(),
                                            this, pageContext)) != null)
            setValue(string);
    }
}
