/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/FormTag.java,v 1.1 2000/05/31 22:28:12 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2000/05/31 22:28:12 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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


package org.apache.struts.taglib;


import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Custom tag that represents an input form, associated with a bean whose
 * properties correspond to the various fields of the form.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2000/05/31 22:28:12 $
 */

public final class FormTag extends TagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The action URL to which this form should be submitted, if any.
     */
    private String action = null;


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.LocalStrings");


    /**
     * The request method used when submitting this form.
     */
    private String method = "POST";


    /**
     * The attribute key under which our associated bean is stored.
     */
    private String name = null;


    /**
     * The scope (request or session) under which our associated bean
     * is stored.
     */
    private String scope = "session";


    /**
     * The Java class name of the bean to be created, if necessary.
     */
    private String type = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the action URL to which this form should be submitted.
     */
    public String getAction() {

	return (this.action);

    }


    /**
     * Set the action URL to which this form should be submitted.
     *
     * @param action The new action URL
     */
    public void setAction(String action) {

	this.action = action;

    }


    /**
     * Return the request method used when submitting this form.
     */
    public String getMethod() {

	return (this.method);

    }


    /**
     * Set the request method used when submitting this form.
     *
     * @param method The new request method
     */
    public void setMethod(String method) {

	this.method = method;

    }


    /**
     * Return the attribute key name of our bean.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the attribute key name of our bean.
     *
     * @param name The new attribute key name
     */
    public void setName(String name) {

	this.name = name;

    }


    /**
     * Return the attribute scope of our bean.
     */
    public String getScope() {

	return (this.scope);

    }


    /**
     * Set the attribute scope of our bean.
     *
     * @param scope The new attribute scope
     */
    public void setScope(String scope) {

	this.scope = scope;

    }


    /**
     * Return the Java class of our bean.
     */
    public String getType() {

	return (this.type);

    }


    /**
     * Set the Java class of our bean.
     *
     * @param type The new Java class
     */
    public void setType(String type) {

	this.type = type;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Render the beginning of this form.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Create an appropriate "form" element based on our parameters
	HttpServletResponse response =
	  (HttpServletResponse) pageContext.getResponse();
	StringBuffer results = new StringBuffer("<form");
	results.append(" method=\"");
	results.append(method);
	results.append("\"");
	if (action != null) {
	    results.append(" action=\"");
	    results.append(response.encodeURL(BeanUtils.filter(action)));
	    results.append("\"");
	}
	results.append(">");

	// Print this field to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("formTag.io", e.toString()));
	}

	// Store this tag itself as a page attribute
	pageContext.setAttribute(Constants.FORM_KEY, this);

	// Locate or create the bean associated with our form
	int scope = PageContext.SESSION_SCOPE;
	if ("request".equals(this.scope))
	    scope = PageContext.REQUEST_SCOPE;
	Object bean = pageContext.getAttribute(name, scope);
	if (bean == null) {
	    if (type == null)
	        throw new JspException
		    (messages.getMessage("formTag.missing", name));
	    try {
		Class clazz = Class.forName(type);
		bean = clazz.newInstance();
	    } catch (Exception e) {
		throw new JspException
		    (messages.getMessage("formTag.create", type,
					 e.toString()));
	    }
	    pageContext.setAttribute(name, bean, scope);
	}
	pageContext.setAttribute(Constants.BEAN_KEY, bean);

	// Continue processing this page
	return (EVAL_BODY_INCLUDE);

    }



    /**
     * Render the end of this form.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

	// Remove the page scope attributes we created
	pageContext.removeAttribute(Constants.BEAN_KEY);
	pageContext.removeAttribute(Constants.FORM_KEY);

	// Render a tag representing the end of our current form
	StringBuffer results = new StringBuffer("</form>");

	// Print this value to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException
	        (messages.getMessage("formTag.io", e.toString()));
	}

	// Continue processing this page
	return (EVAL_PAGE);

    }


}
