/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/logic/ELNotPresentTag.java,v 1.4 2004/01/18 13:43:12 husted Exp $
 * $Revision: 1.4 $
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

import org.apache.struts.taglib.logic.NotPresentTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;

/**
 * Evaluates the nested body content of this tag if the specified value is
 * not present for this request.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.logic.NotPresentTag</code> which
 * provides most of the described functionality.  This subclass allows all
 * attribute values to be specified as expressions utilizing the JavaServer
 * Pages Standard Library expression language.
 *
 * @version $Revision: 1.4 $
 */
public class ELNotPresentTag extends NotPresentTag {

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
     * Instance variable mapped to "role" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String roleExpr;
    /**
     * Instance variable mapped to "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String scopeExpr;
    /**
     * Instance variable mapped to "user" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String userExpr;

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
     * Getter method for "role" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getRoleExpr() { return (roleExpr); }
    /**
     * Getter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getScopeExpr() { return (scopeExpr); }
    /**
     * Getter method for "user" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getUserExpr() { return (userExpr); }

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
     * Setter method for "role" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setRoleExpr(String roleExpr) { this.roleExpr = roleExpr; }
    /**
     * Setter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setScopeExpr(String scopeExpr) { this.scopeExpr = scopeExpr; }
    /**
     * Setter method for "user" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setUserExpr(String userExpr) { this.userExpr = userExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setCookieExpr(null);
        setHeaderExpr(null);
        setNameExpr(null);
        setParameterExpr(null);
        setPropertyExpr(null);
        setRoleExpr(null);
        setScopeExpr(null);
        setUserExpr(null);
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

        if ((string = EvalHelper.evalString("cookie", getCookieExpr(),
                                            this, pageContext)) != null)
            setCookie(string);

        if ((string = EvalHelper.evalString("header", getHeaderExpr(),
                                            this, pageContext)) != null)
            setHeader(string);

        if ((string = EvalHelper.evalString("name", getNameExpr(),
                                            this, pageContext)) != null)
            setName(string);

        if ((string = EvalHelper.evalString("parameter", getParameterExpr(),
                                            this, pageContext)) != null)
            setParameter(string);

        if ((string = EvalHelper.evalString("property", getPropertyExpr(),
                                            this, pageContext)) != null)
            setProperty(string);

        if ((string = EvalHelper.evalString("role", getRoleExpr(),
                                            this, pageContext)) != null)
            setRole(string);

        if ((string = EvalHelper.evalString("scope", getScopeExpr(),
                                            this, pageContext)) != null)
            setScope(string);

        if ((string = EvalHelper.evalString("user", getUserExpr(),
                                            this, pageContext)) != null)
            setUser(string);
    }
}
