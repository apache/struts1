package org.apache.scaffold.model;


import org.apache.struts.util.BeanUtils;


/**
 * Base implementation of ModelBean with
 * default functionality.
 * The only method that must be overridden is
 * ModelResult execute(Object target).
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 11:53:12 $
 */
public abstract class ModelBeanBase implements ModelBean {

// --------------------------------------------------- Instance Variables
// ----------------------------------------------------------- Properties

    /**
     * A convenience property representing
     * a search key for this bean.
     */
    protected String key = null;


    /**
     * Return the Key.
     * @return the key
     */
    public String getKey() {
        return this.key;
    }


    /**
     * Set the Key.
     * @param key The new key.
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * Return the Key as an Integer, or null.
     * @return the key
     */
    public Integer getKeyInteger() {
        Integer keyInteger;
        String keyString = getKey();
        if (keyString==null)
            keyInteger = null;
        else {
            try {
                keyInteger = Integer.valueOf(keyString);
            } catch (java.lang.NumberFormatException e) {
                keyInteger = null;
            }
        }
        return keyInteger;
    }


    /**
     * Set the Key as an Integer.
     * @param key The new key
     */
    public void setKeyInteger(Integer key) {
        if (key==null)
            setKey(null);
        else
            setKey(key.toString());
    }


    /**
     * Value to return if int conversion fails.
     */
    protected int NULL_INT = 0;


    /**
     * Return the Key as an int, or NULL_INT.
     * Generic accessor linked to this object's key field, if any
     * @return the key
     */
    public int getKeyInt() {
        int keyInt;
        String keyString = getKey();
        if (isBlank(keyString))
            keyInt = NULL_INT;
        else {
            try {
                keyInt = Integer.parseInt(key);
            } catch (java.lang.NumberFormatException e) {
                keyInt = NULL_INT;
            }
        }
        return keyInt;
    }


    /**
     * Set the Key as an int.
     * @param key The new key
     */
    public void setKeyInt(int key) {
        setKey(new Integer(key).toString());
    }



// --------------------------------------------------------- Public Methods


    /**
     * A static, empty String used by isBlank.
     */
     private static String EMPTY = "";


    /**
     * Convenience method to check for a null or empty String.
     */
    protected boolean isBlank(String s) {
        return ((s==null) || (EMPTY.equals(s)));
    }


    /**
     * Set properties from given object.
     * Base method uses <code>BeanUtils.populate</code> and
     * <code>BeanUtils.describe</code>.
     * @exception Throws ModelPopulateException on any error.
     */
    public void set(Object o) throws ModelException {
        try {
            BeanUtils.populate(this,BeanUtils.describe(o));
        } catch (Throwable t) {
            throw new ModelPopulateException(t);
      }
    };


    /**
     * Populate matching properties on given object.
     * Base method uses <code>BeanUtils.populate</code> and
     * <code>BeanUtils.describe</code>.
     * @exception Throws ModelPopulateException on any error.
     */
    public void populate(Object o) throws ModelException {
        try {
            BeanUtils.populate(o,BeanUtils.describe(this));
        } catch (Throwable t) {
            throw new ModelPopulateException(t);
      }
    }


    /**
     * Set properties from source, perform query for this bean,
     * and return outcome in a ModelResult collection of this
     * QueryBean class, e.g. execute(source, this).
     * @exception ModelException
     * @returns null on success, or the outcome in a ModelResult
     */
    public ModelResult execute(Object source)
        throws ModelException {
            set(source);
            return execute(source,this);
    }


    /**
     * Perform query for this bean and return outcome in a ModelResult
     * collection of the target bean. Does not autopopulate.
     * Default behavior calls populate and perform.
     * @exception ModelException
     * @returns null on success, or the outcome in a ModelResult
     */
    public abstract ModelResult execute(Object source, Object target)
        throws ModelException;


// ----- end ModelBeanBase -----

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

