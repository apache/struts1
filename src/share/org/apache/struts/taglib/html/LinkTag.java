/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/LinkTag.java,v 1.4 2001/02/08 01:08:15 craigmcc Exp $
 * $Revision: 1.4 $
 * $Date: 2001/02/08 01:08:15 $
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


package org.apache.struts.taglib.html;


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
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForwards;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyUtils;
import org.apache.struts.util.RequestUtils;


/**
 * Generate a URL-encoded hyperlink to the specified URI.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.4 $ $Date: 2001/02/08 01:08:15 $
 */

public class LinkTag extends BaseHandlerTag {


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
     * The link name for named links.
     */
    protected String linkName = null;

    public String getLinkName() {
        return (this.linkName);
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources(Constants.Package + ".LocalStrings");


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
     * The context-relative page URL (beginning with a slash) to which
     * this hyperlink will be rendered.
     */
    protected String page = null;

    public String getPage() {
        return (this.page);
    }

    public void setPage(String page) {
        this.page = page;
    }


    /**
     * The single-parameter request parameter name to generate.
     */
    protected String paramId = null;

    public String getParamId() {
        return (this.paramId);
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }


    /**
     * The single-parameter JSP bean name.
     */
    protected String paramName = null;

    public String getParamName() {
        return (this.paramName);
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }


    /**
     * The single-parameter JSP bean property.
     */
    protected String paramProperty = null;

    public String getParamProperty() {
        return (this.paramProperty);
    }

    public void setParamProperty(String paramProperty) {
        this.paramProperty = paramProperty;
    }


    /**
     * The single-parameter JSP bean scope.
     */
    protected String paramScope = null;

    public String getParamScope() {
        return (this.paramScope);
    }

    public void setParamScope(String paramScope) {
        this.paramScope = paramScope;
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

	// Generate the name definition or hyperlink element
	HttpServletResponse response =
	  (HttpServletResponse) pageContext.getResponse();
	StringBuffer results = new StringBuffer("<a");
        String hyperlink = hyperlink();
        if (hyperlink != null) {
            results.append(" href=\"");
            results.append(response.encodeURL(BeanUtils.filter(hyperlink)));
            results.append("\"");
        }
        if (linkName != null) {
            results.append(" name=\"");
            results.append(linkName);
            results.append("\"");
        }
	if (target != null) {
	    results.append(" target=\"");
	    results.append(target);
	    results.append("\"");
	}
        results.append(prepareStyles());
        results.append(prepareEventHandlers());
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
	return (EVAL_BODY_TAG);

    }



    /**
     * Render the body content of this hyperlink.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {

        if (bodyContent != null) {
            JspWriter writer = bodyContent.getEnclosingWriter();
            try {
                writer.print(bodyContent.getString().trim());
            } catch (IOException e) {
                throw new JspException
                    (messages.getMessage("common.io", e.toString()));
            }
        }
        return (SKIP_BODY);

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
        linkName = null;
	name = null;
        page = null;
	paramId = null;
	paramName = null;
	paramProperty = null;
	paramScope = null;
	property = null;
        scope = null;
	target = null;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the specified hyperlink, modified as necessary with optional
     * request parameters.  Return <code>null</code> if we are generating
     * a name anchor rather than a hyperlink.
     *
     * @exception JspException if an error occurs preparing the hyperlink
     */
    protected String hyperlink() throws JspException {

        if (linkName != null)
            return (null);      // We are not generating a hyperlink

        // Validate the number of href specifiers that were specified
        int n = 0;
        if (forward != null)
            n++;
        if (href != null)
            n++;
        if (page != null)
            n++;
        if (n != 1) {
            JspException e = new JspException
                (messages.getMessage("linkTag.destination"));
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw e;
        }

        // Start with an unadorned "href"
	String href = null;

        // If "href" was specified, use it as is
        if (this.href != null) {
            href = this.href;
        }

	// If "forward" was specified, compute the "href" to forward to
	else if (forward != null) {
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
            href = RequestUtils.absoluteURL(request, forward.getPath());
	}

        // If "page" was specified, compute the "href" to forward to
        else if (page != null) {
            HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
            href = RequestUtils.absoluteURL(request, page);
        }

        // Append a single-parameter name and value, if requested
        if ((paramId != null) && (paramName != null)) {
            if (href.indexOf('?') < 0)
                href += '?';
            else
                href += '&';
            href += paramId;
            href += '=';
            Object bean = RequestUtils.lookup(pageContext,
                                              paramName, paramScope);
            if (bean != null) {
                if (paramProperty == null)
                    href += bean.toString();
                else {
                    try {
                        Object value =
                            PropertyUtils.getProperty(bean, paramProperty);
                        if (value != null)
                            href += value.toString();
                    } catch (IllegalAccessException e) {
                        pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                                 PageContext.REQUEST_SCOPE);
                        throw new JspException
                            (messages.getMessage("getter.access",
                                                 paramProperty, paramName));
                    } catch (InvocationTargetException e) {
                        Throwable t = e.getTargetException();
                        pageContext.setAttribute(Action.EXCEPTION_KEY, t,
                                                 PageContext.REQUEST_SCOPE);
                        throw new JspException
                            (messages.getMessage("getter.result",
                                                 paramProperty, t.toString()));
                    } catch (NoSuchMethodException e) {
                        pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                                 PageContext.REQUEST_SCOPE);
                        throw new JspException
                            (messages.getMessage("getter.method",
                                                 paramProperty, paramName));
                    }
                }
            }
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
	Object bean = RequestUtils.lookup(pageContext, name, scope);
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
		map = (Map) PropertyUtils.getProperty(bean, property);
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
            if (value == null) {
                if (question)
                    sb.append('&');
                else {
                    sb.append('?');
                    question = true;
                }
                sb.append(key);
                sb.append('=');
                // Interpret null as "no value specified"
	    } else if (value instanceof String[]) {
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
