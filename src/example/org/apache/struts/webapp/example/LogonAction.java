/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/LogonAction.java,v 1.9 2002/06/23 04:59:33 jholmes Exp $
 * $Revision: 1.9 $
 * $Date: 2002/06/23 04:59:33 $
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


import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.AppException;
import org.apache.struts.util.MessageResources;
import org.apache.commons.beanutils.PropertyUtils;


/**
 * Implementation of <strong>Action</strong> that validates a user logon.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.9 $ $Date: 2002/06/23 04:59:33 $
 */

public final class LogonAction extends Action {


    // ----------------------------------------------------- Instance Variables


    /**
     * The <code>Log</code> instance for this application.
     */
    private Log log =
        LogFactory.getLog("org.apache.struts.webapp.Example");


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
     * @exception Exception if business logic throws an exception
     */
    public ActionForward execute(ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws Exception {

	// Extract attributes we will need
	Locale locale = getLocale(request);
	MessageResources messages = getResources(request);
	User user = null;

	// Validate the request parameters specified by the user
	ActionErrors errors = new ActionErrors();
	String username = (String)
            PropertyUtils.getSimpleProperty(form, "username");
        String password = (String)
            PropertyUtils.getSimpleProperty(form, "password");
	UserDatabase database = (UserDatabase)
	  servlet.getServletContext().getAttribute(Constants.DATABASE_KEY);
	if (database == null)
            errors.add(ActionErrors.GLOBAL_ERROR,
                       new ActionError("error.database.missing"));
	else {
	    user = getUser(database, username);
	    if ((user != null) && !user.getPassword().equals(password))
		user = null;
	    if (user == null)
                errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError("error.password.mismatch"));
	}

	// Report any errors we have discovered back to the original form
	if (!errors.empty()) {
	    saveErrors(request, errors);
	    return (new ActionForward(mapping.getInput()));
	}

	// Save our logged-in user in the session
	HttpSession session = request.getSession();
	session.setAttribute(Constants.USER_KEY, user);
        if (log.isDebugEnabled()) {
            log.debug("LogonAction: User '" + user.getUsername() +
                      "' logged on in session " + session.getId());
        }

        // Remove the obsolete form bean
	if (mapping.getAttribute() != null) {
            if ("request".equals(mapping.getScope()))
                request.removeAttribute(mapping.getAttribute());
            else
                session.removeAttribute(mapping.getAttribute());
        }

	// Forward control to the specified success URI
	return (mapping.findForward("success"));

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Look up the user, throwing an exception to simulate business logic
     * rule exceptions.
     *
     * @param database Database in which to look up the user
     * @param username Username specified on the logon form
     *
     * @exception AppException if a business logic rule is violated
     */
    public User getUser(UserDatabase database, String username)
        throws AppException {

        // Force an ArithmeticException which can be handled explicitly
        if ("arithmetic".equals(username)) {
            throw new ArithmeticException();
        }

        // Force an application-specific exception which can be handled
        if ("expired".equals(username)) {
            throw new ExpiredPasswordException(username);
        }

        // Look up and return the specified user
        return ((User) database.findUser(username));

    }


}
