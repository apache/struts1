package org.apache.artimus.wizard.http;

import java.io.IOException;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

// import org.apache.struts.utils.ConvertUtils;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

// import com.wintecinc.struts.action.ValidatorForm;  // optional, but recommended


/**
 * @author Ted Husted
 * @version $Revision: 1.1 $ $dateString: 2001/10/25 $
 */
public class Form extends ActionForm {
// public final class Form extends ValidatorForm {

    // --------------------------------------------------- Instance Variables
    // ----------------------------------------------------------- Properties

    /**
     * The integer id
     */
    private String id = null;

    /**
     * Return the id
     * @return the id
     */
    public String getId() {
        return (this.id);
    }

    /**
     * Set the id.
     * @param id The new id
     */
    public void setId(String id) {
        this.id = id;
    }

     /**
     * The dateString
     */
    private String dateString = null;

    /**
     * Return the dateString
     */
    public String getDateString() {
        return (this.dateString);
    }

    /**
     * Set the dateString.
     *
     * @param dateString The new dateString
     */
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    /**
     * The amount
     */
    private String amount = null;

    /**
     * Return the amount
     *
     * @return the amount
     */
    public String getAmount() {
        return (this.amount);
    }

    /**
     * Set the amount.
     *
     * @param amount The new amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * The check
     */
    private Boolean check = null;

    /**
     * Return the check
     *
     * @return the check
     */
    public Boolean getCheck() {
        return (this.check);
    }

    /**
     * Set the check.
     *
     * @param check The new check
     */
    public void setCheck(Boolean check) {
        this.check = check;
    }

    /**
     * The phone
     */
    private String phone = null;

    /**
     * Return the phone
     *
     * @return the phone
     */
    public String getPhone() {
        return (this.phone);
    }

    /**
     * Set the phone.
     *
     * @param phone The new phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * The zip
     */
    private String zip = null;

    /**
     * Return the zip
     *
     * @return the zip
     */
    public String getZip() {
        return (this.zip);
    }

    /**
     * Set the zip.
     *
     * @param zip The new zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * The email
     */
    private String email = null;

    /**
     * Return the email
     *
     * @return the email
     */
    public String getEmail() {
        return (this.email);
    }

    /**
     * Set the email
     *
     * @param email The new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The text
     */
    private String text = null;

    /**
     * Return the text
     *
     * @return the text
     */
    public String getText() {
        return (this.text);
    }

    /**
     * Set the text
     *
     * @param text The new text
     */
    public void setText(String text) {
        this.text = text;
    }


    // --------------------------------------------------------- Public Methods

    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
            // Don't reset a multipage form
        int page = 0; // :FIXME:
        if (page==0) {
            setId(null);
            setDateString(null);
            setAmount(null);
            setCheck(null);
            setPhone(null);
            setZip(null);
            setEmail(null);
            setText(null);
        }
    }


    /**
     * ValidateString the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     * <p>
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
       ActionErrors errors = super.validate(mapping, request);

       /* To add additional checks:

       if (errors==null) errors = new ActionErrors();

       if (errors.empty()) {

           // ValidateString something
           if (!good)
           errors.add(ActionErrors.GLOBAL_ERROR,new
           ActionError("errors.notGood","goodProperty"));
       }

       if (errors.empty()) return null;
       else return errors;

       */

       return (errors);
    }


    /**
     * Convenience mutator to set all properties
     * using native types
     * @param integer The integer
     * @param dateString The dateString
     * @param
     * @param phone The phone
     * @param zip The zip
     * @param email The email
     * @param text The text
     */
    public void set(
        Integer id, Timestamp dateString, Double amount, Boolean check,
        String phone, String zip, String email, String text
        ) {
        setId(id.toString());
        setDateString(dateString.toString());
        setAmount(amount.toString());
        setCheck(check);
        setPhone(phone);
        setZip(zip);
        setEmail(email);
        setText(text);
    }
}

/*
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
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


