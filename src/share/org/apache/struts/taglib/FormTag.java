/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/FormTag.java,v 1.11 2001/05/20 01:19:01 craigmcc Exp $
 * $Revision: 1.11 $
 * $Date: 2001/05/20 01:19:01 $
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
import org.apache.struts.util.ResponseUtils;


/**
 * Custom tag that represents an input form, associated with a bean whose
 * properties correspond to the various fields of the form.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.11 $ $Date: 2001/05/20 01:19:01 $
 */

public final class FormTag extends TagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The action URL to which this form should be submitted, if any.
     */
    private String action = null;


    /**
     * The content encoding to be used on a POST submit.
     */
    private String enctype = null;

    public String getEnctype() {
	return (this.enctype);
    }

    public void setEnctype(String enctype) {
	this.enctype = enctype;
    }


    /**
     * The name of the field to receive focus, if any.
     */
    private String focus = null;


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
     * The onReset event script.
     */
    private String onReset = null;


    /**
     * The onSubmit event script.
     */
    private String onSubmit = null;


    /**
     * The scope (request or session) under which our associated bean
     * is stored.
     */
    private String scope = "session";


    /**
     * The style attribute associated with this tag.
     */
    private String style = null;


    /**
     * The style class associated with this tag.
     */
    private String styleClass = null;


    /**
     * The identifier associated with this tag.
     */
    private String styleId = null;


    /**
     * The window target.
     */
    protected String target = null;


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
     * Return the focus field name for this form.
     */
    public String getFocus() {

	return (this.focus);

    }


    /**
     * Set the focus field name for this form.
     *
     * @param focus The new focus field name
     */
    public void setFocus(String focus) {

	this.focus = focus;

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
     * Return the onReset event script.
     */
    public String getOnReset() {

	return (this.onReset);

    }


    /**
     * Set the onReset event script.
     *
     * @param onReset The new event script
     */
    public void setOnReset(String onReset) {

	this.onReset = onReset;

    }


    /**
     * Return the onSubmit event script.
     */
    public String getOnSubmit() {

	return (this.onSubmit);

    }


    /**
     * Set the onSubmit event script.
     *
     * @param onSubmit The new event script
     */
    public void setOnSubmit(String onSubmit) {

	this.onSubmit = onSubmit;

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
     * Return the style attribute for this tag.
     */
    public String getStyle() {

	return (this.style);

    }


    /**
     * Set the style attribute for this tag.
     *
     * @param style The new style attribute
     */
    public void setStyle(String style) {

	this.style = style;

    }


    /**
     * Return the style class for this tag.
     */
    public String getStyleClass() {

	return (this.styleClass);

    }


    /**
     * Set the style class for this tag.
     *
     * @param styleClass The new style class
     */
    public void setStyleClass(String styleClass) {

	this.styleClass = styleClass;

    }


    /**
     * Return the style id for this tag.
     */
    public String getStyleId() {

	return (this.styleId);

    }


    /**
     * Set the style id for this tag.
     *
     * @param styleId The new style id
     */
    public void setStyleId(String styleId) {

	this.styleId = styleId;

    }


    /**
     * Return the window target.
     */
    public String getTarget() {

	return (this.target);

    }


    /**
     * Set the window target.
     *
     * @param target The new window target
     */
    public void setTarget(String target) {

	this.target = target;

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
	results.append(" name=\"");
	results.append(name);
	results.append("\"");
	results.append(" method=\"");
	results.append(method);
	results.append("\"");
	if (action != null) {
	    results.append(" action=\"");
	    results.append(response.encodeURL(ResponseUtils.filter(action)));
	    results.append("\"");
	}
        if (styleClass != null) {
            results.append(" class=\"");
            results.append(styleClass);
            results.append("\"");
        }
        if (styleId != null) {
            results.append(" id=\"");
            results.append(styleId);
            results.append("\"");
        }
	if (enctype != null) {
	    results.append(" enctype=\"");
	    results.append(enctype);
	    results.append("\"");
	}
	if (onReset != null) {
	    results.append(" onreset=\"");
	    results.append(onReset);
	    results.append("\"");
        }
        if (onSubmit != null) {
	    results.append(" onsubmit=\"");
	    results.append(onSubmit);
	    results.append("\"");
	}
        if (style != null) {
            results.append(" style=\"");
            results.append(style);
            results.append("\"");
        }
	if (target != null) {
	    results.append(" target=\"");
	    results.append(target);
	    results.append("\"");
	}
	results.append(">");

	// Print this field to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("common.io", e.toString()));
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
		    (messages.getMessage("getter.bean", name));
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
	if (focus != null) {
	    results.append("\r\n");
	    results.append("<script language=\"JavaScript\">\r\n");
	    results.append("  <!--\r\n");
	    results.append("    document.");
	    results.append(name);
	    results.append(".");
	    results.append(focus);
	    results.append(".focus()\r\n");
	    results.append("  // -->\r\n");
	    results.append("</script>\r\n");
	}

	// Print this value to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException
	        (messages.getMessage("common.io", e.toString()));
	}

	// Continue processing this page
	return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
	action = null;
	enctype = null;
	focus = null;
	method = "POST";
	name = null;
	onReset = null;
	onSubmit = null;
	scope = "session";
	style = null;
	styleClass = null;
        styleId = null;
	target = null;
	type = null;

    }


}
