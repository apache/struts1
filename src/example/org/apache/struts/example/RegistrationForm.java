/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/example/Attic/RegistrationForm.java,v 1.5 2000/08/01 20:03:24 craigmcc Exp $
 * $Revision: 1.5 $
 * $Date: 2000/08/01 20:03:24 $
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


import java.util.Vector;
import org.apache.struts.action.ValidatingActionForm;
import org.apache.struts.util.ErrorMessages;


/**
 * Form bean for the user registration page.  This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>action</b> - The maintenance action we are performing (Create, Delete,
 *     or Edit).
 * <li><b>fromAddress</b> - The EMAIL address of the sender, to be included
 *     on sent messages.  [REQUIRED]
 * <li><b>fullName</b> - The full name of the sender, to be included on
 *     sent messages.  [REQUIRED]
 * <li><b>password</b> - The password used by this user to log on.
 * <li><b>password2</b> - The confirmation password, which must match the password
 *     when changing or setting.
 * <li><b>replyToAddress</b> - The "Reply-To" address to be included on
 *     sent messages.  [Same as from address]
 * <li><b>username</b> - The registered username, which must be unique.
 *     [REQUIRED]
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.5 $ $Date: 2000/08/01 20:03:24 $
 */

public final class RegistrationForm implements ValidatingActionForm  {


    // --------------------------------------------------- Instance Variables


    /**
     * The maintenance action we are performing (Create or Edit).
     */
    private String action = "Create";


    /**
     * The from address.
     */
    private String fromAddress = "";


    /**
     * The full name.
     */
    private String fullName = "";


    /**
     * The password.
     */
    private String password = "";


    /**
     * The confirmation password.
     */
    private String password2 = "";


    /**
     * The reply to address.
     */
    private String replyToAddress = "";



    /**
     * The username.
     */
    private String username = "";


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

	if (action == null)
	    this.action = "";
	else
	    this.action = action;

    }


    /**
     * Return the from address.
     */
    public String getFromAddress() {

	return (this.fromAddress);

    }


    /**
     * Set the from address.
     *
     * @param fromAddress The new from address
     */
    public void setFromAddress(String fromAddress) {

	if (fromAddress == null)
	    this.fromAddress = "";
	else
	    this.fromAddress = fromAddress;

    }


    /**
     * Return the full name.
     */
    public String getFullName() {

	return (this.fullName);

    }


    /**
     * Set the full name.
     *
     * @param fullName The new full name
     */
    public void setFullName(String fullName) {

	if (fullName == null)
	    this.fullName = "";
	else
	    this.fullName = fullName;

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

	if (password == null)
	    this.password = "";
	else
	    this.password = password;

    }


    /**
     * Return the confirmation password.
     */
    public String getPassword2() {

	return (this.password2);

    }


    /**
     * Set the confirmation password.
     *
     * @param password The new confirmation password
     */
    public void setPassword2(String password2) {

	if (password2 == null)
	    this.password2 = "";
	else
	    this.password2 = password2;

    }


    /**
     * Return the reply to address.
     */
    public String getReplyToAddress() {

	return (this.replyToAddress);

    }


    /**
     * Set the reply to address.
     *
     * @param replyToAddress The new reply to address
     */
    public void setReplyToAddress(String replyToAddress) {

	if (replyToAddress == null)
	    this.replyToAddress = "";
	else
	    this.replyToAddress = replyToAddress;

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

	if (username == null)
	    this.username = "";
	else
	    this.username = username;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Validate the properties of this form bean, and return an array of
     * message keys for any errors we encounter.
     */
    public String[] validate() {

	ErrorMessages errors = new ErrorMessages();
	if ((username == null) || (username.length() < 1))
	    errors.addError("error.username.required");
	if (!password.equals(password))
	    errors.addError("error.password.match");
	if ((fromAddress == null) || (fromAddress.length() < 1))
	    errors.addError("error.fromAddress.required");
	else {
	    int atSign = fromAddress.indexOf("@");
	    if ((atSign < 1) || (atSign >= (fromAddress.length() - 1)))
		errors.addError("error.fromAddress.format");
	}
	if ((fullName == null) || (fullName.length() < 1))
	    errors.addError("error.fullName.required");
	if ((replyToAddress != null) && (replyToAddress.length() > 0)) {
	    int atSign = replyToAddress.indexOf("@");
	    if ((atSign < 1) || (atSign >= (replyToAddress.length() - 1)))
		errors.addError("error.replyToAddress.format");
	}

	return (errors.getErrors());

    }


}
