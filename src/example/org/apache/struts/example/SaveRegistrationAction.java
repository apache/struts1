/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/example/Attic/SaveRegistrationAction.java,v 1.1 2000/05/31 22:28:14 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2000/05/31 22:28:14 $
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
import java.util.Locale;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionBase;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;


/**
 * Implementation of <strong>Action</strong> that validates and creates or updates
 * the user registration information entered by the user.  If a new registration is
 * created, the user is also implicitly logged on.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2000/05/31 22:28:14 $
 */

public final class SaveRegistrationAction extends ActionBase {


    // --------------------------------------------------------- Public Methods


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
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
    public void perform(ActionServlet servlet,
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws IOException, ServletException {


	// Extract attributes and parameters we will need
	Locale locale = getLocale(request);
	MessageResources messages = getResources(servlet);
	HttpSession session = request.getSession();
	RegistrationForm regform = (RegistrationForm) form;
	String action = request.getParameter("action");
	Hashtable database = (Hashtable)
	  servlet.getServletContext().getAttribute(Constants.DATABASE_KEY);

	// Is there a currently logged on user (unless creating)?
	User user = (User) session.getAttribute(Constants.USER_KEY);
	if (!"Create".equals(action) && (user == null)) {
	    String uri = Constants.LOGON_PAGE;
	    RequestDispatcher rd =
	      servlet.getServletContext().getRequestDispatcher(uri);
	    rd.forward(request, response);
	    return;
	}

	// Was this transaction cancelled?
	String submit = request.getParameter("submit");
	if (submit == null)
	    submit = "Submit";
	if (submit.equals(messages.getMessage(locale, "button.cancel"))) {
	    if (servlet.getDebug() >= 1)
	        servlet.log("SaveRegistrationAction:  Transaction '" + action +
	                    "' was cancelled");
	    if (mapping.getFormAttribute() != null)
	        session.removeAttribute(mapping.getFormAttribute());
	    session.removeAttribute(Constants.SUBSCRIPTION_KEY);
	    String uri = ((ApplicationMapping) mapping).getSuccess();
	    RequestDispatcher rd =
	      servlet.getServletContext().getRequestDispatcher(uri);
	    rd.forward(request, response);
	    return;
	}

	// Validate the request parameters specified by the user
	String value = null;
	Vector errors = new Vector();
	value = regform.getUsername();
	if ((value == null) || (value.length() < 1))
	    errors.addElement(messages.getMessage(locale,
	                      "error.username.required"));
	if (("Create".equals(action)) &&
	    (database.get(value) != null))
	    errors.addElement(messages.getMessage(locale,
	                      "error.username.unique"));
	if (!regform.getPassword().equals(regform.getPassword2()))
	    errors.addElement(messages.getMessage(locale,
			      "error.password.match"));
	value = regform.getFromAddress();
	if ((value == null) || (value.length() < 1))
	    errors.addElement(messages.getMessage(locale,
			      "error.fromAddress.required"));
	else {
	    int atSign = value.indexOf("@");
	    if ((atSign < 1) || (atSign >= (value.length() - 1)))
		errors.addElement(messages.getMessage(locale,
				  "error.fromAddress.format"));
	}
	value = regform.getFullName();
	if ((value == null) || (value.length() < 1))
	    errors.addElement(messages.getMessage(locale,
			      "error.fullName.required"));
	value = regform.getReplyToAddress();
	if ((value != null) && (value.length() > 0)) {
	    int atSign = value.indexOf("@");
	    if ((atSign < 1) || (atSign >= (value.length() - 1)))
		errors.addElement(messages.getMessage(locale,
				  "error.replyToAddress.format"));
	}

	// Report any errors we have discovered back to the original form
	if (errors.size() > 0) {
	    String results[] = new String[errors.size()];
	    for (int i = 0; i < results.length; i++)
	        results[i] = (String) errors.elementAt(i);
	    request.setAttribute(ERROR_KEY, results);
	    String uri = ((ApplicationMapping) mapping).getFailure();
	    RequestDispatcher rd =
	      servlet.getServletContext().getRequestDispatcher(uri);
	    rd.forward(request, response);
	    return;
	}

	// Update the user's persistent profile information
	if ("Create".equals(action)) {
	    user = new User();
	    user.setUsername(regform.getUsername());
	}
	if (regform.getPassword().length() > 0)
	    user.setPassword(regform.getPassword());
	if (regform.getFullName().length() > 0)
	    user.setFullName(regform.getFullName());
	else
	    user.setFullName(null);
	if (regform.getFromAddress().length() > 0)
	    user.setFromAddress(regform.getFromAddress());
	else
	    user.setFromAddress(null);
	if (regform.getReplyToAddress().length() > 0)
	    user.setReplyToAddress(regform.getReplyToAddress());
	else
	    user.setReplyToAddress(null);
	if ("Create".equals(action)) {
	    database.put(user.getUsername(), user);
	    session.setAttribute(Constants.USER_KEY, user);
	    if (servlet.getDebug() >= 1)
		servlet.log("SaveRegisrationAction: User '" +
		            user.getUsername() +
	                    "' logged on in session " + session.getId());
	}

	// Remove any obsolete session objects
	if (mapping.getFormAttribute() != null)
	    session.removeAttribute(mapping.getFormAttribute());

	// Forward control to the specified success URI
	String uri = ((ApplicationMapping) mapping).getSuccess();
	RequestDispatcher rd =
	  servlet.getServletContext().getRequestDispatcher(uri);
	rd.forward(request, response);


    }


}
