/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/LinkSubscriptionTag.java,v 1.2 2002/12/12 17:36:41 ekbush Exp $
 * $Revision: 1.2 $
 * $Date: 2002/12/12 17:36:41 $
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


package org.apache.struts.webapp.example;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.ResponseUtils;
import org.apache.struts.config.ModuleConfig;


/**
 * Generate a URL-encoded hyperlink to the specified URI, with
 * associated query parameters selecting a specified Subscription.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2002/12/12 17:36:41 $
 */

public class LinkSubscriptionTag extends TagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The context-relative URI.
     */
    protected String page = null;


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.webapp.example.ApplicationResources");


    /**
     * The attribute name.
     */
    private String name = "subscription";


    // ------------------------------------------------------------- Properties


    /**
     * Return the context-relative URI.
     */
    public String getPage() {

	return (this.page);

    }


    /**
     * Set the context-relative URI.
     *
     * @param page Set the context-relative URI
     */
    public void setPage(String page) {

	this.page = page;

    }


    /**
     * Return the attribute name.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the attribute name.
     *
     * @param name The new attribute name
     */
    public void setName(String name) {

	this.name = name;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Render the beginning of the hyperlink.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Generate the URL to be encoded
        ModuleConfig config = (ModuleConfig) pageContext.getRequest()
            .getAttribute(org.apache.struts.Globals.MODULE_KEY);
        HttpServletRequest request =
          (HttpServletRequest) pageContext.getRequest();
        StringBuffer url = new StringBuffer(request.getContextPath());
	url.append(config.getPrefix());
        url.append(page);
	Subscription subscription = null;
	try {
	    subscription = (Subscription) pageContext.findAttribute(name);
        } catch (ClassCastException e) {
	    subscription = null;
	}
	if (subscription == null)
	    throw new JspException
	        (messages.getMessage("linkSubscription.noSubscription", name));
	if (page.indexOf("?") < 0)
	    url.append("?");
	else
	    url.append("&");
	url.append("username=");
	url.append(ResponseUtils.filter(subscription.getUser().getUsername()));
	url.append("&host=");
	url.append(ResponseUtils.filter(subscription.getHost()));

	// Generate the hyperlink start element
	HttpServletResponse response =
	  (HttpServletResponse) pageContext.getResponse();
	StringBuffer results = new StringBuffer("<a href=\"");
	results.append(response.encodeURL(url.toString()));
	results.append("\">");

	// Print this element to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("linkSubscription.io", e.toString()));
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
	        (messages.getMessage("link.io", e.toString()));
	}

	return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        this.page = null;
        this.name = "subscription";

    }


}
