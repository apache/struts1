/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/LinkTag.java,v 1.9 2000/07/18 05:35:12 craigmcc Exp $
 * $Revision: 1.9 $
 * $Date: 2000/07/18 05:35:12 $
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
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForwards;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Generate a URL-encoded hyperlink to the specified URI.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.9 $ $Date: 2000/07/18 05:35:12 $
 */

public class LinkTag extends TagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The logical forward name from which to retrieve the hyperlink URI.
     */
    protected String forward = null;


    /**
     * The hyperlink URI.
     */
    protected String href = null;


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.LocalStrings");


    /**
     * The JSP bean name for query parameters.
     */
    protected String name = null;


    /**
     * The JSP bean property name for query parameters.
     */
    protected String property = null;


    /**
     * The window target.
     */
    protected String target = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the logical forward name.
     */
    public String getForward() {

	return (this.forward);

    }


    /**
     * Set the logical forward name.
     *
     * @param forward The new logical forward name
     */
    public void setForward(String forward) {

	this.forward = forward;

    }


    /**
     * Return the hyperlink URI.
     */
    public String getHref() {

	return (this.href);

    }


    /**
     * Set the hyperlink URI.
     *
     * @param href Set the hyperlink URI
     */
    public void setHref(String href) {

	this.href = href;

    }


    /**
     * Return the bean name.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the bean name.
     *
     * @param name The bean name
     */
    public void setName(String name) {

	this.name = name;

    }


    /**
     * Return the property name.
     */
    public String getProperty() {

	return (this.property);

    }


    /**
     * Set the property name.
     *
     * @param property The property name
     */
    public void setProperty(String property) {

	this.property = property;

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


    // --------------------------------------------------------- Public Methods


    /**
     * Render the beginning of the hyperlink.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Validate our attributes
	if ((forward == null) && (href == null))
	    throw new JspException
		(messages.getMessage("linkTag.destination"));
	else if ((forward != null) && (href != null))
	    throw new JspException
		(messages.getMessage("linkTag.destination"));

	// Generate the hyperlink start element
	HttpServletResponse response =
	  (HttpServletResponse) pageContext.getResponse();
	StringBuffer results = new StringBuffer("<a href=\"");
	results.append(response.encodeURL(BeanUtils.filter(hyperlink())));
	results.append("\"");
	if (target != null) {
	    results.append(" target=\"");
	    results.append(target);
	    results.append("\"");
	}
	results.append(">");

	// Print this element to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("common.io", e.toString()));
	}

	// Evaluate the body of this tag
	return (EVAL_BODY_INCLUDE);

    }



    /**
     * Render the end of the hyperlink.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {


	// Print the ending element to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print("</a>");
	} catch (IOException e) {
	    throw new JspException
	        (messages.getMessage("common.io", e.toString()));
	}

	return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
	forward = null;
	href = null;
	name = null;
	property = null;
	target = null;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the specified hyperlink, modified as necessary with optional
     * request parameters.
     *
     * @exception JspException if an error occurs preparing the hyperlink
     */
    protected String hyperlink() throws JspException {

	String href = this.href;

	// If "forward" was specified, compute the "href" to forward to
	if (forward != null) {
	    ActionForwards forwards = (ActionForwards)
		pageContext.getAttribute(Action.FORWARDS_KEY,
					 PageContext.APPLICATION_SCOPE);
	    if (forwards == null)
		throw new JspException
		    (messages.getMessage("linkTag.forwards"));
	    ActionForward forward = forwards.findForward(this.forward);
	    if (forward == null)
		throw new JspException
		    (messages.getMessage("linkTag.forward"));
	    HttpServletRequest request =
		(HttpServletRequest) pageContext.getRequest();
	    href = request.getContextPath() + forward.getPath();
	}

	// Just return the "href" attribute if there is no bean to look up
	if ((property != null) && (name == null))
	    throw new JspException
		(messages.getMessage("getter.name"));
	if (name == null)
	    return (href);

	// Look up the map we will be using
	Object bean = pageContext.findAttribute(name);
	if (bean == null)
	    throw new JspException
		(messages.getMessage("getter.bean", name));
	Map map = null;
	if (property == null) {
	    try {
		map = (Map) bean;
	    } catch (ClassCastException e) {
		throw new JspException
		    (messages.getMessage("linkTag.type"));
	    }
	} else {
	    try {
		map = (Map) BeanUtils.getPropertyValue(bean, property);
		if (map == null)
		    throw new JspException
			(messages.getMessage("getter.property", property));
	    } catch (ClassCastException e) {
		throw new JspException
		    (messages.getMessage("linkTag.type"));
	    } catch (NoSuchMethodException e) {
		throw new JspException
		    (messages.getMessage("getter.method", property));
	    } catch (Exception e) {
		throw new JspException
		    (messages.getMessage("getter.result",
					 property, e.toString()));
	    }
	}

	// Append the required query parameters
	StringBuffer sb = new StringBuffer(href);
	boolean question = (href.indexOf("?") >= 0);
	Iterator keys = map.keySet().iterator();
	while (keys.hasNext()) {
	    String key = (String) keys.next();
	    Object value = map.get(key);
	    if (value instanceof String[]) {
		String values[] = (String[]) value;
		for (int i = 0; i < values.length; i++) {
		    if (question)
			sb.append('&');
		    else {
			sb.append('?');
			question = true;
		    }
		    sb.append(key);
		    sb.append('=');
		    sb.append(URLEncoder.encode(values[i]));
		}
	    } else {
		if (question)
		    sb.append('&');
		else {
		    sb.append('?');
		    question = true;
		}
		sb.append(key);
		sb.append('=');
		sb.append(URLEncoder.encode(value.toString()));
	    }
	}

	// Return the final result
	return (sb.toString());

    }


}
