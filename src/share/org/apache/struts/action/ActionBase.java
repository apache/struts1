/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Attic/ActionBase.java,v 1.7 2000/08/13 03:43:39 craigmcc Exp $
 * $Revision: 1.7 $
 * $Date: 2000/08/13 03:43:39 $
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


package org.apache.struts.action;


import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.taglib.Constants;
import org.apache.struts.util.ErrorMessages;
import org.apache.struts.util.MessageResources;


/**
 * Abstract basic implementation of <strong>Action</strong> that provides
 * useful utility methods for use by Action classes.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.7 $ $Date: 2000/08/13 03:43:39 $
 */

public abstract class ActionBase implements Action {


    // --------------------------------------------------- Instance Variables


    /**
     * The system default Locale.
     */
    protected static Locale defaultLocale = Locale.getDefault();


    // ------------------------------------------------------- Public Methods


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param servlet The ActionServlet making this request
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public abstract ActionForward perform(ActionServlet servlet,
					  ActionMapping mapping,
					  ActionForm form,
					  HttpServletRequest request,
					  HttpServletResponse response)
	throws IOException, ServletException;


    // ---------------------------------------------------- Protected Methods


    /**
     * Return the user's currently selected Locale.
     *
     * @param request The request we are processing
     */
    protected Locale getLocale(HttpServletRequest request) {

	HttpSession session = request.getSession();
	Locale locale = (Locale) session.getAttribute(LOCALE_KEY);
	if (locale == null)
	    locale = defaultLocale;
	return (locale);

    }


    /**
     * Set the user's currently selected Locale.
     *
     * @param request The request we are processing
     * @param locale The user's selected Locale to be set, or null
     *  to select the server's default Locale
     */
    protected void setLocale(HttpServletRequest request, Locale locale) {

	HttpSession session = request.getSession();
	if (locale == null)
	    locale = defaultLocale;
	session.setAttribute(LOCALE_KEY, locale);

    }


    /**
     * Return the message resources for this application.
     *
     * @param servlet The action servlet for this request
     */
    protected MessageResources getResources(ActionServlet servlet) {

	return (servlet.getResources());

    }


    /**
     * Returns <code>true</code> if the form cancel button was pressed.
     * This method will check if the cancel button generated by
     * <strong>CancelTag</strong> was pressed by the user in the
     * current request.  If true, validation by any
     * <strong>ValidatingActionForm</strong> validate() method will be
     * skipped.
     *
     * @param request The servlet request we are processing
     * @see org.apache.struts.taglib.CancelTag
     * @see org.apache.struts.action.ValidatingActionForm
     */
    protected boolean isCancelled(HttpServletRequest request) {

	return (request.getParameter(Constants.CANCEL_PROPERTY) != null);

    }


    /**
     * Save the specified error messages keys into the appropriate request
     * attribute for use by the &lt;struts:errors&gt; tag, if any messages
     * are required.  Otherwise, ensure that the request attribute is not
     * created.
     *
     * @param request The servlet request we are processing
     * @param messages Vector containing message keys for looking
     *  up errors in the application resources
     */
    protected void saveErrors(HttpServletRequest request,
			      ErrorMessages messages) {

	// Remove any error messages attribute if none are required
	if ((messages == null) || (messages.getSize() == 0)) {
	    request.removeAttribute(ERROR_KEY);
	    return;
	}

	// Save the error messages we need
	request.setAttribute(ERROR_KEY, messages.getErrors());

    }


}
