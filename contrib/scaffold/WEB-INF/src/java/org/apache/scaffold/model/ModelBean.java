package org.apache.scaffold.model;


/**
 * Manages transfering data between the application layer
 * and the resource layer using JavaBeans.
 * A caller can pass one bean to populate a subclass,
 * and another bean to use as a factory for a
 * collection of beans to return. The ResultSetUtils
 * can be used to help create the collection.
 * The key fields are a convenience for framing
 * queries.
 * Known implementations: ModelBeanBase.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 11:53:12 $
 */
public interface ModelBean {

    // --------------------------------------------------- Instance Variables
    // ----------------------------------------------------------- Properties

    /**
     * Return the key
     * @return the key
     */
    public String getKey();


    /**
     * Set the key
     * @param key The new key
     */
    public void setKey(String key);


    /**
     * Return the key as an Integer
     * @return the key
     */
    public Integer getKeyInteger();


    /**
     * Set the key as an Integer
     * @param key The new key
     */
    public void setKeyInteger(Integer key);


    /**
     * Return the Key as an Int
     * @return the key
     */
    public int getKeyInt();


    /**
     * Set the key as an Int
     * @param key The new key
     */
    public void setKeyInt(int key);


    // --------------------------------------------------------- Public Methods


    /**
     * Set properties from given object.
     * Base method uses <code>BeanUtils.populate</code> and
     * <code>BeanUtils.describe</code>.
     * @exception Throws ModelException on any error.
     */
    public void set(Object o) throws ModelException;


    /**
     * Populate matching properties on given object.
     * Base method uses <code>BeanUtils.populate</code> and
     * <code>BeanUtils.describe</code>.
     * @exception Throws ModelException on any error.
     */
    public void populate(Object o) throws ModelException;


    /**
     * Perform query for this bean, and return outcome in a ModelResult
     * collection of this QueryBean class, e.g. execute(source, this).
     * @exception ModelException
     * @returns null on success, or the outcome in a ModelResult
     */
    public ModelResult execute(Object source) throws ModelException;


    /**
     * Set properties from object, perform query for this bean,
     * and return outcome in a ModelResult collection of the
     * target bean.
     * @exception ModelException
     * @returns null on success, or the outcome in a ModelResult
     */
    public ModelResult execute(Object source, Object target) throws ModelException;

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
