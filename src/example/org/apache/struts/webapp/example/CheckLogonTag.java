/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/CheckLogonTag.java,v 1.7 2003/01/18 19:48:56 craigmcc Exp $
 * $Revision: 1.7 $
 * $Date: 2003/01/18 19:48:56 $
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


import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.config.ModuleConfig;


/**
 * Check for a valid User logged on in the current session.  If there is no
 * such user, forward control to the logon page.
 *
 * @author Craig R. McClanahan
 * @author Marius Barduta
 * @version $Revision: 1.7 $ $Date: 2003/01/18 19:48:56 $
 */

public final class CheckLogonTag extends TagSupport {


    // --------------------------------------------------- Instance Variables


    /**
     * The key of the session-scope bean we look for.
     */
    private String name = Constants.USER_KEY;


    /**
     * The page to which we should forward for the user to log on.
     */
    private String page = "/logon.jsp";


    // ----------------------------------------------------------- Properties


    /**
     * Return the bean name.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the bean name.
     *
     * @param name The new bean name
     */
    public void setName(String name) {

	this.name = name;

    }


    /**
     * Return the forward page.
     */
    public String getPage() {

	return (this.page);

    }


    /**
     * Set the forward page.
     *
     * @param page The new forward page
     */
    public void setPage(String page) {

	this.page = page;

    }


    // ------------------------------------------------------- Public Methods


    /**
     * Defer our checking until the end of this tag is encountered.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	return (SKIP_BODY);

    }


    /**
     * Perform our logged-in user check by looking for the existence of
     * a session scope bean under the specified name.  If this bean is not
     * present, control is forwarded to the specified logon page.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

	// Is there a valid user logged on?
	boolean valid = false;
	HttpSession session = pageContext.getSession();
	if ((session != null) && (session.getAttribute(name) != null))
	    valid = true;

	// Forward control based on the results
	if (valid)
	    return (EVAL_PAGE);
	else {
            ModuleConfig config = (ModuleConfig) pageContext.getRequest()
                .getAttribute(org.apache.struts.Globals.MODULE_KEY);
            try {
		pageContext.forward(config.getPrefix() + page);
	    } catch (Exception e) {
		throw new JspException(e.toString());
	    }
	    return (SKIP_PAGE);
	}

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        this.name = Constants.USER_KEY;
        this.page = "/logon.jsp";

    }


}
