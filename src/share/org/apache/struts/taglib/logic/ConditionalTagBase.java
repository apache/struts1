/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/logic/ConditionalTagBase.java,v 1.5 2001/02/12 21:49:54 craigmcc Exp $
 * $Revision: 1.5 $
 * $Date: 2001/02/12 21:49:54 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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
 *    any, must include the following acknowlegement:
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


package org.apache.struts.taglib.logic;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.MessageResources;


/**
 * Abstract base class for the various conditional evaluation tags.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.5 $ $Date: 2001/02/12 21:49:54 $
 */

public abstract class ConditionalTagBase extends TagSupport {


    // ------------------------------------------------------------- Properties


    /**
     * The name of the cookie to be used as a variable.
     */
    protected String cookie = null;

    public String getCookie() {
        return (this.cookie);
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }


    /**
     * The name of the HTTP request header to be used as a variable.
     */
    protected String header = null;

    public String getHeader() {
        return (this.header);
    }

    public void setHeader(String header) {
        this.header = header;
    }


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources
        ("org.apache.struts.taglib.logic.LocalStrings");


    /**
     * The name of the JSP bean to be used as a variable (if
     * <code>property</code> is not specified), or whose property is to be
     * accessed (if <code>property</code> is specified).
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * The name of the HTTP request parameter to be used as a variable.
     */
    protected String parameter = null;

    public String getParameter() {
        return (this.parameter);
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }


    /**
     * The name of the bean property to be used as a variable.
     */
    protected String property = null;

    public String getProperty() {
        return (this.property);
    }

    public void setProperty(String property) {
        this.property = property;
    }


    /**
     * The name of the security role to be checked for.
     */
    protected String role = null;

    public String getRole() {
        return (this.role);
    }

    public void setRole(String role) {
        this.role = role;
    }


    /**
     * The scope to search for the bean named by the name property, or
     * "any scope" if null.
     */
    protected String scope = null;

    public String getScope() {
        return (this.scope);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


    /**
     * The user principal name to be checked for.
     */
    protected String user = null;

    public String getUser() {
        return (this.user);
    }

    public void setUser(String user) {
        this.user = user;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Perform the test required for this particular tag, and either evaluate
     * or skip the body of this tag.
     *
     * @exception JspException if a JSP exception occurs
     */
    public int doStartTag() throws JspException {

        if (condition())
            return (EVAL_BODY_INCLUDE);
        else
            return (SKIP_BODY);

    }


    /**
     * Evaluate the remainder of the current page normally.
     *
     * @exception JspException if a JSP exception occurs
     */
    public int doEndTag() throws JspException {

        return (EVAL_PAGE);

    }


    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        cookie = null;
        header = null;
        name = null;
        parameter = null;
        property = null;
        role = null;
        scope = null;
        user = null;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Evaluate the condition that is being tested by this particular tag,
     * and return <code>true</code> if the nested body content of this tag
     * should be evaluated, or <code>false</code> if it should be skipped.
     * This method must be implemented by concrete subclasses.
     *
     * @exception JspException if a JSP exception occurs
     */
    protected abstract boolean condition() throws JspException;


}
