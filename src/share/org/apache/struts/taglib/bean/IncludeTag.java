/*
 * $Id: IncludeTag.java,v 1.17 2002/01/13 00:25:36 craigmcc Exp $
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
 * [Additional notices, if required by prior licensing conditions]
 *
 */

package org.apache.struts.taglib.bean;


import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;


/**
 * Define the contents of a specified intra-application request as a
 * page scope attribute of type <code>java.lang.String</code>.  If the
 * current request is part of a session, the session identifier will be
 * included in the generated request, so it will be part of the same
 * session.
 * <p>
 * <strong>FIXME</strong>:  In a servlet 2.3 environment, we can use a
 * wrapped response passed to RequestDispatcher.include().
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.17 $ $Date: 2002/01/13 00:25:36 $
 */

public class IncludeTag extends TagSupport {


    // ------------------------------------------------------------- Properties


    /**
     * Buffer size to use when reading the input stream.
     */
    protected static final int BUFFER_SIZE = 256;


    /**
     * The anchor to be added to the end of the generated hyperlink.
     */
    protected String anchor = null;

    public String getAnchor() {
        return (this.anchor);
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }


    /**
     * The name of the global <code>ActionForward</code> that contains a
     * path to our requested resource.
     */
    protected String forward = null;

    public String getForward() {
        return (this.forward);
    }

    public void setForward(String forward) {
        this.forward = forward;
    }


    /**
     * The absolute URL to the resource to be included.
     */
    protected String href = null;

    public String getHref() {
        return (this.href);
    }

    public void setHref(String href) {
        this.href = href;
    }


    /**
     * The name of the scripting variable that will be exposed as a page
     * scope attribute.
     */
    protected String id = null;

    public String getId() {
        return (this.id);
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
        MessageResources.getMessageResources
        ("org.apache.struts.taglib.bean.LocalStrings");


    /**
     * Deprecated method to set the "name" attribute, which has been
     * replaced by the "page" attribute.
     *
     * @deprecated use setPage(String) instead
     */
    public void setName(String name) {
        this.page = name;
    }


    /**
     * The context-relative URI of the page or servlet to be included.
     */
    protected String page = null;

    public String getPage() {
	return (this.page);
    }

    public void setPage(String page) {
	this.page = page;
    }


    /**
     * Include transaction token (if any) in the hyperlink?
     */
    protected boolean transaction = false;

    public boolean getTransaction() {
        return (this.transaction);
    }

    public void setTransaction(boolean transaction) {
        this.transaction = transaction;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Define the contents returned for the specified resource as a
     * page scope attribute.
     *
     * @exception JspException if a JSP error occurs
     */
    public int doStartTag() throws JspException {

	// Set up a URLConnection to read the requested resource
        Map params = RequestUtils.computeParameters
            (pageContext, null, null, null, null,
             null, null, null, transaction); // FIXME - <html:link> attributes
        String urlString = null;
        URL url = null;
        try {
            urlString = RequestUtils.computeURL(pageContext, forward, href,
                                                page, params, anchor, false);
            if (urlString.indexOf(':') < 0) {
                HttpServletRequest request = (HttpServletRequest)
                    pageContext.getRequest();
                url = new URL(RequestUtils.requestURL(request), urlString);
            } else {
                url = new URL(urlString);
            }
        } catch (MalformedURLException e) {
            RequestUtils.saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("include.url", e.toString()));
        }

	URLConnection conn = null;
	try {
            // Set up the basic connection
	    conn = url.openConnection();
	    conn.setAllowUserInteraction(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(false);
            // Add a session id cookie if appropriate
            HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
            if ((conn instanceof HttpURLConnection) &&
                urlString.startsWith(request.getContextPath()) &&
                (request.getRequestedSessionId() != null) &&
                request.isRequestedSessionIdFromCookie()) {
                StringBuffer sb = new StringBuffer("JSESSIONID=");
                sb.append(request.getRequestedSessionId());
                conn.setRequestProperty("Cookie", sb.toString());
            }
            // Connect to the requested resource
            conn.connect();
	} catch (Exception e) {
            RequestUtils.saveException(pageContext, e);
	    throw new JspException
                (messages.getMessage("include.open",
                                     url.toString(), e.toString()));
	}

	// Copy the contents of this URL
        StringBuffer sb = new StringBuffer();
	try {
	    BufferedInputStream is =
		new BufferedInputStream(conn.getInputStream());
	    InputStreamReader in = new InputStreamReader(is); // FIXME - encoding
            char buffer[] = new char[BUFFER_SIZE];
            int n = 0;
	    while (true) {
                n = in.read(buffer);
                if (n < 1)
                    break;
                sb.append(buffer, 0, n);
	    }
            in.close();
	} catch (Exception e) {
            RequestUtils.saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("include.read",
                                     url.toString(), e.toString()));
	}

        // Define the retrieved content as a page scope attribute
        pageContext.setAttribute(id, sb.toString());

	// Skip any body of this tag
	return (SKIP_BODY);

    }


    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        anchor = null;
        forward = null;
        href = null;
        id = null;
        page = null;
        transaction = false;

    }


}
