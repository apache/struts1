/*
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
package org.apache.struts.util;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionMessage;

/**
 * Used for specialized exception handling.
 */
public class ModuleException extends Exception {
    
    protected String property = null;
    
    /**
     * @deprecated Use message instead.
     */
    protected ActionError error = null;
    
    /**
     * The ActionMessage associated with this exception.
     * @since Struts 1.2
     */
    protected ActionMessage message = null;

	/**
	 * Construct an module exception with no replacement values.
	 *
	 * @param key Message key for this error message
	 */
    public ModuleException(String key) {
        super(key);
		error = new ActionError(key);
        message = new ActionMessage(key);
    }

	/**
	 * Construct an module exception with the specified replacement values.
	 *
	 * @param key Message key for this error message
	 * @param value First replacement value
	 */
	public ModuleException(String key, Object value) {
		super(key);
		error = new ActionError(key, value);
        message = new ActionMessage(key, value);
	}

	/**
	 * Construct an module exception with the specified replacement values.
	 *
	 * @param key Message key for this error message
	 * @param value0 First replacement value
	 * @param value1 Second replacement value
	 */
	public ModuleException(String key, Object value0, Object value1) {
		super(key);
		error = new ActionError(key, value0, value1);
        message = new ActionMessage(key, value0, value1);
	}

	/**
	 * Construct an module exception with the specified replacement values.
	 *
	 * @param key Message key for this error message
	 * @param value0 First replacement value
	 * @param value1 Second replacement value
	 * @param value2 Third replacement value
	 */
	public ModuleException(String key, Object value0, Object value1, Object value2) {
		super(key);
		error = new ActionError(key, value0, value1, value2);
        message = new ActionMessage(key, value0, value1, value2);
	}

	/**
	 * Construct an module exception with the specified replacement values.
	 *
	 * @param key Message key for this error message
	 * @param value0 First replacement value
	 * @param value1 Second replacement value
	 * @param value2 Third replacement value
	 * @param value3 Fourth replacement value
	 */
	public ModuleException(String key, Object value0, Object value1, Object value2, Object value3) {
		super(key);
		error = new ActionError(key, value0, value1, value2, value3);
        message = new ActionMessage(key, value0, value1, value2, value3);
	}

	/**
	 * Construct an action error with the specified replacement values.
	 *
	 * @param key Message key for this message
	 * @param values Array of replacement values
	 */
	public ModuleException(String key, Object[] values) {
		error = new ActionError(key, values);
        message = new ActionMessage(key, values);
	}
    
    /**
	 * Returns the property associated with the exception.
	 * @return Value of property.
	 */
	public String getProperty() {
		return (property != null) ? property : message.getKey();
	}

    /**
	 * Set the property associated with the exception.
	 * It can be a name of the edit field, which 'caused' the exception.
	 */
	public void setProperty(String property) {
		this.property = property;
	}

    /**
	 * Returns the error associated with the exception.
	 * @return Value of property error.
     * @deprecated Use getActionMessage() instead.  This will be removed
     * after Struts 1.2.
	 */
	public ActionError getError() {
		return error;
	}
    
    /**
     * Returns the error associated with the exception.
     * @return Value of property error.
     * @since Struts 1.2
     */
    public ActionMessage getActionMessage() {
        return this.message;
    }
}
