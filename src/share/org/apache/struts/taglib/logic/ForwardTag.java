/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/logic/ForwardTag.java,v 1.5 2001/02/12 21:49:55 craigmcc Exp $
 * $Revision: 1.5 $
 * $Date: 2001/02/12 21:49:55 $
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForwards;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;


/**
 * Perform a forward or redirect to a page that is looked up in the global
 * ActionForwards collection associated with our application.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.5 $ $Date: 2001/02/12 21:49:55 $
 */

public class ForwardTag extends TagSupport {


    // ----------------------------------------------------------- Properties


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.logic.LocalStrings");


    /**
     * The logical name of the <code>ActionForward</code> entry to be
     * looked up.
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }


    // ------------------------------------------------------- Public Methods


    /**
     * Defer processing until the end of this tag is encountered.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	return (SKIP_BODY);

    }


    /**
     * Look up the ActionForward associated with the specified name,
     * and perform a forward or redirect to that path as indicated.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

	// Look up the desired ActionForward entry
	ActionForward forward = null;
	ActionForwards forwards =
	    (ActionForwards) pageContext.getAttribute
	      (Action.FORWARDS_KEY, PageContext.APPLICATION_SCOPE);
	if (forwards != null)
	    forward = forwards.findForward(name);
	if (forward == null) {
            JspException e = new JspException
		(messages.getMessage("forward.lookup", name));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

	// Forward or redirect to the corresponding actual path
	String path = forward.getPath();
	if (forward.getRedirect()) {
	    HttpServletResponse response =
		(HttpServletResponse) pageContext.getResponse();
	    try {
		response.sendRedirect(response.encodeRedirectURL(path));
	    } catch (Exception e) {
                RequestUtils.saveException(pageContext, e);
		throw new JspException
		    (messages.getMessage("forward.redirect",
					 name, e.toString()));
	    }
	} else {
	    try {
		pageContext.forward(path);
	    } catch (Exception e) {
                RequestUtils.saveException(pageContext, e);
		throw new JspException
		    (messages.getMessage("forward.forward",
					 name, e.toString()));
	    }
	}

	// Skip the remainder of this page
	return (SKIP_PAGE);

    }


    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        name = null;

    }


}
