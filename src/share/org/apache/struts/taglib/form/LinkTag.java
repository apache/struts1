/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/form/Attic/LinkTag.java,v 1.1 2000/11/04 00:46:30 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2000/11/04 00:46:30 $
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


package org.apache.struts.taglib.form;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
 * @version $Revision: 1.1 $ $Date: 2000/11/04 00:46:30 $
 */

public class LinkTag extends TagSupport {


    // ------------------------------------------------------------- Properties


    /**
     * The logical forward name from which to retrieve the hyperlink URI.
     */
    protected String forward = null;

    public String getForward() {
	return (this.forward);
    }

    public void setForward(String forward) {
	this.forward = forward;
    }


    /**
     * The hyperlink URI.
     */
    protected String href = null;

    public String getHref() {
	return (this.href);
    }

    public void setHref(String href) {
	this.href = href;
    }


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

    public String getName() {
	return (this.name);
    }

    public void setName(String name) {
	this.name = name;
    }


    /**
     * The JSP bean property name for query parameters.
     */
    protected String property = null;

    public String getProperty() {
	return (this.property);
    }

    public void setProperty(String property) {
	this.property = property;
    }


    /**
     * The scope of the bean specified by the name property, if any.
     */
    protected String scope = null;

    public String getScope() {
        return (this.scope);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


    /**
     * The window target.
     */
    protected String target = null;

    public String getTarget() {
	return (this.target);
    }

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
	if ((forward == null) && (href == null)) {
	    JspException e = new JspException
		(messages.getMessage("linkTag.destination"));
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw e;
        } else if ((forward != null) && (href != null)) {
	    JspException e = new JspException
		(messages.getMessage("linkTag.destination"));
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw e;
        }

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
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
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
        scope = null;
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
	if ((property != null) && (name == null)) {
	    JspException e = new JspException
		(messages.getMessage("getter.name"));
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw e;
        }
	if (name == null)
	    return (href);

	// Look up the map we will be using
	Object bean = BeanUtils.lookup(pageContext, name, scope);
        if (bean == null) {
	    JspException e = new JspException
		(messages.getMessage("getter.bean", name));
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw e;
        }
	Map map = null;
	if (property == null) {
	    try {
		map = (Map) bean;
	    } catch (ClassCastException e) {
                pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                         PageContext.REQUEST_SCOPE);
		throw new JspException
		    (messages.getMessage("linkTag.type"));
	    }
	} else {
	    try {
		map = (Map) BeanUtils.getPropertyValue(bean, property);
		if (map == null) {
		    JspException e = new JspException
			(messages.getMessage("getter.property", property));
                    pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                             PageContext.REQUEST_SCOPE);
                    throw e;
                }
	    } catch (IllegalAccessException e) {
                pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                         PageContext.REQUEST_SCOPE);
		throw new JspException
		    (messages.getMessage("getter.access", property, name));
	    } catch (InvocationTargetException e) {
		Throwable t = e.getTargetException();
                pageContext.setAttribute(Action.EXCEPTION_KEY, t,
                                         PageContext.REQUEST_SCOPE);
		throw new JspException
		    (messages.getMessage("getter.result",
					 property, t.toString()));
	    } catch (ClassCastException e) {
                pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                         PageContext.REQUEST_SCOPE);
		throw new JspException
		    (messages.getMessage("linkTag.type"));
	    } catch (NoSuchMethodException e) {
                pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                         PageContext.REQUEST_SCOPE);
		throw new JspException
		    (messages.getMessage("getter.method", property, name));
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
