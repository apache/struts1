/*
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
 */

package com.wintecinc.struts.action;


/**
 * An encapsulation of an individual message, consisting
 * of a message key (to be used to look up message text in an appropriate
 * message resources database) plus up to four placeholder objects that can
 * be used for parametric replacement in the message text.
 *
 * @author David Winterfeldt
 * @see org.apache.struts.action.ActionError
 */

public class ActionMessage implements java.io.Serializable {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct an action message with no replacement values.
     *
     * @param key 	Message key for this message
     */
    public ActionMessage(String key) {
        this(key, null, null, null, null);
    }


    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key 	Message key for this message
     * @param value0 	First replacement value
     */
    public ActionMessage(String key, Object value0) {
        this(key, value0, null, null, null);
    }


    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key Message key for this message
     * @param value0 First replacement value
     * @param value1 Second replacement value
     */
    public ActionMessage(String key, Object value0, Object value1) {
        this(key, value0, value1, null, null);
    }


    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key Message key for this message
     * @param value0 First replacement value
     * @param value1 Second replacement value
     * @param value2 Third replacement value
     */
    public ActionMessage(String key, Object value0, Object value1,
                       Object value2) {
        this(key, value0, value1, value2, null);
    }


    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key Message key for this message
     * @param value0 First replacement value
     * @param value1 Second replacement value
     * @param value2 Third replacement value
     * @param value3 Fourth replacement value
     */
    public ActionMessage(String key, Object value0, Object value1,
                         Object value2, Object value3) {
        super();
        this.key = key;
        values[0] = value0;
        values[1] = value1;
        values[2] = value2;
        values[3] = value3;
    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The message key for this message.
     */
    private String key = null;


    /**
     * The replacement values for this mesasge.
     */
    private Object values[] = { null, null, null, null };


    // --------------------------------------------------------- Public Methods


    /**
     * Get the message key for this message.
     */
    public String getKey() {
        return (this.key);
    }


    /**
     * Get the replacement values for this message.
     */
    public Object[] getValues() {
        return (this.values);
    }
    
}
