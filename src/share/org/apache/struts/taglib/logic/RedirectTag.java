/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/logic/RedirectTag.java,v 1.6 2001/02/12 21:49:58 craigmcc Exp $
 * $Revision: 1.6 $
 * $Date: 2001/02/12 21:49:58 $
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
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyUtils;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;


/**
 * Generate a URL-encoded redirect to the specified URI.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.6 $ $Date: 2001/02/12 21:49:58 $
 */

public class RedirectTag extends TagSupport {


    // ------------------------------------------------------------- Properties


    /**
     * The logical forward name from which to retrieve the redirect URI.
     */
    protected String forward = null;

    public String getForward() {
	return (this.forward);
    }

    public void setForward(String forward) {
	this.forward = forward;
    }


    /**
     * The redirect URI.
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
        ("org.apache.struts.taglib.logic.LocalStrings");


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
     * this redirect will be rendered.
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


    // --------------------------------------------------------- Public Methods


    /**
     * Defer generation until the end of this tag is encountered.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        return (SKIP_BODY);

    }


    /**
     * Render the redirect and skip the remainder of this page.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        // Perform the redirection
	HttpServletResponse response =
	  (HttpServletResponse) pageContext.getResponse();
        String hyperlink = ResponseUtils.filter(hyperlink());
        try {
            response.sendRedirect(response.encodeRedirectURL(hyperlink));
        } catch (IOException e) {
            RequestUtils.saveException(pageContext, e);
            throw new JspException(e.getMessage());
        }

        // Skip the remainder of this apge
        return (SKIP_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
	forward = null;
	href = null;
	name = null;
        page = null;
	paramId = null;
	paramName = null;
	paramProperty = null;
	paramScope = null;
	property = null;
        scope = null;

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
                (messages.getMessage("redirect.destination"));
            RequestUtils.saveException(pageContext, e);
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
	    if (forwards == null) {
                JspException e = new JspException
		    (messages.getMessage("redirect.forwards"));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }
	    ActionForward forward = forwards.findForward(this.forward);
	    if (forward == null) {
                JspException e = new JspException
		    (messages.getMessage("redirect.forward", this.forward));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }
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
            Object value =
                RequestUtils.lookup(pageContext, paramName, paramProperty,
                                    paramScope);
            if (value != null)
                href += value.toString();
        }

	// Just return the "href" attribute if there is no bean to look up
	if (name == null)
	    return (href);

	// Look up the map we will be using
        Object value =
            RequestUtils.lookup(pageContext, name, property, scope);
        if (value == null)
            return (href);
        if (!(value instanceof Map)) {
            JspException e = new JspException
                (messages.getMessage("redirect.map", property, name));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }
        Map map = (Map) value;

	// Append the required query parameters
	StringBuffer sb = new StringBuffer(href);
	boolean question = (href.indexOf("?") >= 0);
	Iterator keys = map.keySet().iterator();
	while (keys.hasNext()) {
	    String key = (String) keys.next();
	    value = map.get(key);
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
