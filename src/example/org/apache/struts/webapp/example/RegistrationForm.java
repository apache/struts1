/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/RegistrationForm.java,v 1.7 2003/01/18 19:48:56 craigmcc Exp $
 * $Revision: 1.7 $
 * $Date: 2003/01/18 19:48:56 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/**
 * Form bean for the user registration page.  This form has the following
 * fields, with default values in square brackets:
 * <ul>
 * <li><b>action</b> - The maintenance action we are performing (Create,
 *     Delete, or Edit).
 * <li><b>fromAddress</b> - The EMAIL address of the sender, to be included
 *     on sent messages.  [REQUIRED]
 * <li><b>fullName</b> - The full name of the sender, to be included on
 *     sent messages.  [REQUIRED]
 * <li><b>password</b> - The password used by this user to log on.
 * <li><b>password2</b> - The confirmation password, which must match
 *     the password when changing or setting.
 * <li><b>replyToAddress</b> - The "Reply-To" address to be included on
 *     sent messages.  [Same as from address]
 * <li><b>username</b> - The registered username, which must be unique.
 *     [REQUIRED]
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.7 $ $Date: 2003/01/18 19:48:56 $
 */

public final class RegistrationForm extends ValidatorForm  {


    // ----------------------------------------------------- Instance Variables


    /**
     * The maintenance action we are performing (Create or Edit).
     */
    private String action = "Create";


    /**
     * The from address.
     */
    private String fromAddress = null;


    /**
     * The full name.
     */
    private String fullName = null;


    /**
     * The password.
     */
    private String password = null;


    /**
     * The confirmation password.
     */
    private String password2 = null;


    /**
     * The reply to address.
     */
    private String replyToAddress = null;



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
        this.fromAddress = null;
        this.fullName = null;
        this.password = null;
        this.password2 = null;
        this.replyToAddress = null;
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

        // Perform validator framework validations
        ActionErrors errors = super.validate(mapping, request);

        // Only need crossfield validations here
        if (!password.equals(password2)) {
            errors.add("password2",
                       new ActionError("error.password.match"));
        }
        return errors;

    }


}
