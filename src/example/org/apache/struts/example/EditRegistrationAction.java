/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/example/Attic/EditRegistrationAction.java,v 1.8 2000/10/15 03:34:52 craigmcc Exp $
 * $Revision: 1.8 $
 * $Date: 2000/10/15 03:34:52 $
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


package org.apache.struts.example;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyUtils;


/**
 * Implementation of <strong>Action</strong> that populates an instance of
 * <code>RegistrationForm</code> from the profile of the currently logged on
 * User (if any).
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.8 $ $Date: 2000/10/15 03:34:52 $
 */

public final class EditRegistrationAction extends Action {


    // --------------------------------------------------------- Public Methods


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws IOException, ServletException {

	// Extract attributes we will need
	Locale locale = getLocale(request);
	MessageResources messages = getResources();
	HttpSession session = request.getSession();
	String action = request.getParameter("action");
	if (action == null)
	    action = "Create";
        if (servlet.getDebug() >= 1)
            servlet.log("EditRegistrationAction:  Processing " + action +
                        " action");

	// Is there a currently logged on user?
	User user = null;
	if (!"Create".equals(action)) {
	    user = (User) session.getAttribute(Constants.USER_KEY);
	    if (user == null) {
		if (servlet.getDebug() >= 1)
		    servlet.log(" User is not logged on in session "
	                        + session.getId());
		return (servlet.findForward("logon"));
	    }
	}

	// Populate the user registration form
	if (form == null) {
            if (servlet.getDebug() >= 1)
                servlet.log(" Creating new RegistrationForm bean under key "
                            + mapping.getAttribute());
	    form = new RegistrationForm();
	    session.setAttribute(mapping.getAttribute(), form);
	}
	RegistrationForm regform = (RegistrationForm) form;
	if (user != null) {
            if (servlet.getDebug() >= 1)
                servlet.log(" Populating form from " + user);
            try {
                PropertyUtils.copyProperties(regform, user);
                regform.setAction(action);
                regform.setPassword(null);
                regform.setPassword2(null);
            } catch (InvocationTargetException e) {
                Throwable t = e.getTargetException();
                if (t == null)
                    t = e;
                servlet.log("RegistrationForm.populate", t);
                throw new ServletException("RegistrationForm.populate", t);
            } catch (Throwable t) {
                servlet.log("RegistrationForm.populate", t);
                throw new ServletException("RegistrationForm.populate", t);
            }
	}

	// Forward control to the edit user registration page
        if (servlet.getDebug() >= 1)
            servlet.log(" Forwarding to 'success' page");
	return (mapping.findForward("success"));

    }


}
