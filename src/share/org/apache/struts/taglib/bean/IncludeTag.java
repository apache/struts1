/*
 * $Id: IncludeTag.java,v 1.7 2000/12/30 19:52:09 craigmcc Exp $
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
 * [Additional notices, if required by prior licensing conditions]
 *
 */

package org.apache.struts.taglib.bean;


import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.util.MessageResources;


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
 * @version $Revision: 1.7 $ $Date: 2000/12/30 19:52:09 $
 */

public class IncludeTag extends TagSupport {


    // ------------------------------------------------------------- Properties


    /**
     * Buffer size to use when reading the input stream.
     */
    protected static final int BUFFER_SIZE = 256;


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
     * The context-relative URI of the page or servlet to be included.
     */
    protected String name = null;

    public String getName() {
	return (this.name);
    }

    public void setName(String name) {
	this.name = name;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Define the contents returned for the specified resource as a
     * page scope attribute.
     *
     * @exception JspException if a JSP error occurs
     */
    public int doStartTag() throws JspException {

	// Validate the format of the "name" attribute
	// FIXME - deal with relative URIs like <jsp:include> does
	if (!name.startsWith("/")) {
            JspException e = new JspException
              (messages.getMessage("include.format", name));
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw e;
        }

        // Identify our current session identifier (if any)
        String sessionId = null;
        HttpSession session = pageContext.getSession();
        if (session != null) {
            try {
                sessionId = session.getId();
            } catch (IllegalStateException e) {
                sessionId = null;
            }
        }

        // Calculate the URL of the requested page
	HttpServletRequest request =
	    (HttpServletRequest) pageContext.getRequest();
	StringBuffer url = new StringBuffer();
	url.append(request.getScheme());
	url.append("://");
	url.append(request.getServerName());
	if (request.getServerPort() != 80) {
	    url.append(':');
	    url.append(request.getServerPort());
	}
	if (request.getContextPath() != null)
	    url.append(request.getContextPath());
        int question = name.indexOf('?');
        if (sessionId == null) {
            url.append(name);
        } else if (question < 0) {
            url.append(name);
            url.append(";jsessionid=");
            url.append(sessionId);
        } else {
            url.append(name.substring(0, question));
            url.append(";jsessionid=");
            url.append(sessionId);
            url.append(name.substring(question));
        }

	// Set up a URLConnection to read the requested page
	URLConnection conn = null;
	try {
	    conn = (new URL(url.toString())).openConnection();
	    conn.setAllowUserInteraction(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(false);
	    conn.connect();
	} catch (Exception e) {
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
	    throw new JspException
                (messages.getMessage("include.open", name, e.toString()));
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
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw new JspException
                (messages.getMessage("include.read", name, e.toString()));
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
        id = null;
        name = null;

    }


}
