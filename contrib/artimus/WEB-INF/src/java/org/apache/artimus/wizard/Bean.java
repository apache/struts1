package org.apache.artimus.wizard;

import org.apache.scaffold.model.ModelBeanBase;
import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResult;

import org.apache.artimus.lang.Messages;

import java.sql.Timestamp;

/**
 * Base class for other article queries.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:13 $
 */
public class Bean extends ModelBeanBase {

    // --------------------------------------------------- Instance Variables
    // ----------------------------------------------------------- Properties

    /**
     * The primary key
     */
    private Integer wizard = null;

    /**
     * Return the primary key
     * @return the primary key
     */
    public Integer getWizard() {
        return (this.wizard);
    }

    /**
     * Set the primary key.
     * @param wizard The new primary key
     */
    public void setWizard(Integer wizard) {
        this.wizard = wizard;
            // Advise ancestor property
        if (wizard==null)
            setKey(null);
        else
            setKey(wizard.toString());
    }

    /**
     * The integer id
     */
    private Integer id = null;

    /**
     * Return the id
     * @return the id
     */
    public Integer getId() {
        return (this.id);
    }

    /**
     * Set the id.
     * @param id The new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

     /**
     * The date
     */
    private Timestamp date = null;

    /**
     * Return the date
     */
    public Timestamp getDate() {
        return (this.date);
    }

    /**
     * Set the date.
     *
     * @param date The new date
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * The amount
     */
    private Float amount = null;

    /**
     * Return the amount
     *
     * @return the amount
     */
    public Float getAmount() {
        return (this.amount);
    }

    /**
     * Set the amount.
     *
     * @param amount The new amount
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * The check
     */
    private Byte check = null;

    /**
     * Return the check
     *
     * @return the check
     */
    public Byte getCheck() {
        return (this.check);
    }

    /**
     * Set the check.
     *
     * @param check The new check
     */
    public void setCheck(Byte check) {
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
     * Execute model for this bean, and return outcome in ModelResult.
     * @exception Collects and returns any Exceptions
     * @returns Null on success, or a collection of Exceptions
     */
    public ModelResult execute(Object source, Object target)
            throws ModelException {
        throw new ModelException(Messages.NOT_IMPLEMENTED_EXCEPTION);
    }


} // end Bean


/*
 *
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
