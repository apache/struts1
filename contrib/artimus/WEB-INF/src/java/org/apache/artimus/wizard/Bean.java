/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/wizard/Bean.java,v 1.3 2004/03/14 07:15:06 sraeburn Exp $
 * $Revision: 1.3 $
 * $Date: 2004/03/14 07:15:06 $
 *
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

package org.apache.artimus.wizard;

import org.apache.scaffold.model.ModelBeanBase;
import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResult;

import org.apache.artimus.lang.Messages;

import java.sql.Timestamp;

/**
 * Base class for other article queries.
 * @version $Revision: 1.3 $ $Date: 2004/03/14 07:15:06 $
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