/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/SubscriptionForm.java,v 1.8 2004/01/13 12:48:44 husted Exp $
 * $Revision: 1.8 $
 * $Date: 2004/01/13 12:48:44 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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
 *    any, must include the following acknowledgement:
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
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
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


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


/**
 * Form bean for the user profile page.  This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>action</b> - The maintenance action we are performing (Create, Delete,
 *     or Edit).
 * <li><b>host</b> - The mail host for this subscription.  [REQUIRED]
 * <li><b>password</b> - The password for this subscription.  [REQUIRED]
 * <li><b>type</b> - The subscription type (imap,pop3)
       for this subscription.  [REQUIRED]
 * <li><b>username</b> - The username of this subscription.  [REQUIRED]
 * </ul>
 *
 * @version $Revision: 1.8 $ $Date: 2004/01/13 12:48:44 $
 */

public final class SubscriptionForm extends ActionForm {


    // --------------------------------------------------- Instance Variables


    /**
     * The maintenance action we are performing (Create or Edit).
     */
    private String action = "Create";


    /**
     * Should we auto-connect at startup time?
     */
    private boolean autoConnect = false;


    /**
     * The host name.
     */
    private String host = null;


    /**
     * The password.
     */
    private String password = null;


    /**
     * The subscription type.
     */
    private String type = null;


    /**
     * The username.
     */
    private String username = null;


    // ----------------------------------------------------------- Properties


    /**
     * Return the maintenance action.
     */
    public String getAction() {

	return (this.action);

    }


    /**
     * Set the maintenance action.
     *
     * @param action The new maintenance action.
     */
    public void setAction(String action) {

        this.action = action;

    }


    /**
     * Return the auto-connect flag.
     */
    public boolean getAutoConnect() {

        return (this.autoConnect);

    }


    /**
     * Set the auto-connect flag.
     *
     * @param autoConnect The new auto-connect flag
     */
    public void setAutoConnect(boolean autoConnect) {

        this.autoConnect = autoConnect;
    }


    /**
     * Return the host name.
     */
    public String getHost() {

	return (this.host);

    }


    /**
     * Set the host name.
     *
     * @param host The host name
     */
    public void setHost(String host) {

        this.host = host;

    }


    /**
     * Return the password.
     */
    public String getPassword() {

	return (this.password);

    }


    /**
     * Set the password.
     *
     * @param password The new password
     */
    public void setPassword(String password) {

        this.password = password;

    }


    /**
     * Return the subscription type.
     */
    public String getType() {

	return (this.type);

    }


    /**
     * Set the subscription type.
     *
     * @param type The subscription type
     */
    public void setType(String type) {

        this.type = type;

    }


    /**
     * Return the username.
     */
    public String getUsername() {

	return (this.username);

    }


    /**
     * Set the username.
     *
     * @param username The new username
     */
    public void setUsername(String username) {

        this.username = username;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        this.action = "Create";
        this.autoConnect = false;
        this.host = null;
        this.password = null;
        this.type = null;
        this.username = null;

    }


    /**
     * Validate the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

	if ((host == null) || (host.length() < 1))
            errors.add("host",
                       new ActionMessage("error.host.required"));
	if ((username == null) || (username.length() < 1))
            errors.add("username",
                       new ActionMessage("error.username.required"));
	if ((password == null) || (password.length() < 1))
            errors.add("password",
                       new ActionMessage("error.password.required"));
	if ((type == null) || (type.length() < 1))
            errors.add("type",
                       new ActionMessage("error.type.required"));
	else if (!"imap".equals(type) && !"pop3".equals(type))
            errors.add("type",
                       new ActionMessage("error.type.invalid", type));

	return (errors);

    }


}

