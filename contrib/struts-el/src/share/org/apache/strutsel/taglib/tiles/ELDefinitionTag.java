/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/tiles/ELDefinitionTag.java,v 1.2 2004/01/18 13:43:12 husted Exp $
 * $Revision: 1.2 $
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

package org.apache.strutsel.taglib.tiles;

import org.apache.struts.taglib.tiles.DefinitionTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;

/**
 * This is the tag handler for &lt;tiles:definition&gt;, which defines
 * a tiles (or template / component). Definition is put in requested context and can be
 * used in &lt;tiles:insert&gt.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.tiles.DefinitionTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @version $Revision: 1.2 $
 */
public class ELDefinitionTag extends DefinitionTag {

    /**
     * Instance variable mapped to "id" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String idExpr;
    /**
     * Instance variable mapped to "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String scopeExpr;
    /**
     * Instance variable mapped to "template" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String templateExpr;
    /**
     * Instance variable mapped to "page" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String pageExpr;
    /**
     * Instance variable mapped to "role" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String roleExpr;
    /**
     * Instance variable mapped to "extends" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String extendsExpr;

    /**
     * Getter method for "id" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getIdExpr() { return (idExpr); }
    /**
     * Getter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getScopeExpr() { return (scopeExpr); }
    /**
     * Getter method for "template" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getTemplateExpr() { return (templateExpr); }
    /**
     * Getter method for "page" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getPageExpr() { return (pageExpr); }
    /**
     * Getter method for "role" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getRoleExpr() { return (roleExpr); }
    /**
     * Getter method for "extends" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getExtendsExpr() { return (extendsExpr); }

    /**
     * Setter method for "id" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setIdExpr(String idExpr) { this.idExpr = idExpr; }
    /**
     * Setter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setScopeExpr(String scopeExpr) { this.scopeExpr = scopeExpr; }
    /**
     * Setter method for "template" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setTemplateExpr(String templateExpr) { this.templateExpr = templateExpr; }
    /**
     * Setter method for "page" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setPageExpr(String pageExpr) { this.pageExpr = pageExpr; }
    /**
     * Setter method for "role" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setRoleExpr(String roleExpr) { this.roleExpr = roleExpr; }
    /**
     * Setter method for "extends" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setExtendsExpr(String extendsExpr) { this.extendsExpr = extendsExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setIdExpr(null);
        setScopeExpr(null);
        setTemplateExpr(null);
        setPageExpr(null);
        setRoleExpr(null);
        setExtendsExpr(null);
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

        if ((string = EvalHelper.evalString("id", getIdExpr(),
                                            this, pageContext)) != null)
            setId(string);
        if ((string = EvalHelper.evalString("scope", getScopeExpr(),
                                            this, pageContext)) != null)
            setScope(string);
        if ((string = EvalHelper.evalString("template", getTemplateExpr(),
                                            this, pageContext)) != null)
            setTemplate(string);
        if ((string = EvalHelper.evalString("page", getPageExpr(),
                                            this, pageContext)) != null)
            setPage(string);
        if ((string = EvalHelper.evalString("role", getRoleExpr(),
                                            this, pageContext)) != null)
            setRole(string);
        if ((string = EvalHelper.evalString("extends", getExtendsExpr(),
                                            this, pageContext)) != null)
            setExtends(string);
    }
}
