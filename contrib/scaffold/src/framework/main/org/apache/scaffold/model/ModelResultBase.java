package org.apache.scaffold.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;


/**
 * Concrete implementation of ModelResult that can be
 * used "as-is" to manage a collection of beans
 * returned from the resource layer.
 * @author Ted Husted
 * @version $Revision: 1.3 $ $Date: 2002/01/01 13:44:17 $
 */
public class ModelResultBase implements ModelResult {


// ----------------------------------------------------------- Properties


    /**
     * The Message List.
     */
    protected ArrayList messages = null;


    /**
     * Instantiate messages if it does not already exist.
     */
    protected boolean isMessages() {
        if (this.messages==null) this.messages = new ArrayList();
        return (this.messages!=null);
    }


    /**
     * Add a message to the List.
     */
    public boolean addMessage(String message) {
        if (isMessages())
            return messages.add(message);
        return false;
    }


    /**
     * Return the messages.
     */
    public List getMessages() {
        return (List) this.messages;
    }


    /**
     * The search description as a phrase,
     * ie: field = value
     */
    private String description = null;


    /**
     * Return the description.
     * @return the description
     */
    public String getDescription() {
        return (this.description);
    }


    /**
     * Set the description.
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Set the description as "field = value".
     * @param description1 The attribute description
     * @param description2 The value description
     */
    public void setDescription(String value, String field) {
        if ((value==null) || ("".equals(value))) {
            this.description = field + PARAM_EQUALS + PARAM_ANY;
        }
        else {
            this.description = field + PARAM_EQUALS + value;
        }
    }


    /**
     * The result list.
     */
    protected ArrayList result = null;


    /**
     * Instantiate result if it does not already exist.
     */
    protected boolean isResult() {
        if (this.result==null) this.result = new ArrayList();
        return (this.result!=null);
    }


// ----------------------------------------------------------- Public Methods


    /**
     * Return the number of elements on the List.
     * @return the size of the List
     */
    public int size() {
        if (isResult())
            return result.size();
        return 0;
    }


    /**
     * Accessor for <code>size()</code>.
     * @return the size of the List
     */
    public int getSize() {
        return size();
    }


    /**
     * Return an iterator for the List,
     * or null if there is no List.
     * @return an iterator for the List
     */
    public Iterator iterator() {
        if (isResult())
            return result.iterator();
        return null;
    }


    /**
     * Accessor for <code>iterator()</code>.
     * @return an iterator for the List
     */
    public Iterator getIterator() {
        return iterator();
    }


    /**
     * Appends the specified element to the end of the List.
     * @return the row count
     */
    public boolean add(Object o) {
        if (isResult())
            return result.add(o);
        return false;
    }


    /**
     * Appends all of the elements in the specified Collection
     * to the end of this list, in the order that they are
     * returned by the specified Collection's Iterator.
     */
    public boolean addAll(Collection c) {
        if (isResult())
            return result.addAll(c);
        return false;
    }


    /**
     * Returns the element at the specified position in this list.
     */
    public Object get(int index) {
        if (isResult())
            return result.get(index);
        return null;
    }


    /**
     * Populate matching properties on given object,
     * using bean at given index. Returns false if index>size.
     * <code>BeanUtils.describe</code>.
     * @exception Throws ModelException on any error.
     */
    public boolean populate(Object o, int index) throws ModelException {
        Object bean = null;
        if (size()>index)
            bean = get(index);
        if (bean==null) return false;
        try {
            BeanUtils.populate(o,BeanUtils.describe(bean));
        } catch (Throwable t) {
            throw new ModelPopulateException(t);
        }
        return true;
    }


// ----------------------------------------------------------- Constructors


    /**
     * Default constructor.
     */
    public ModelResultBase() {
        super();
    }


    /**
     * Convenience constructor to populate result with a ModelBean.
     */
    public ModelResultBase(Object o) {
        super();
        add(o);
    }


    /**
     * Convenience constructor to populate result with a Collection
     * (e.g. of ModelBeans).
     */
    public ModelResultBase(Collection c) {
        super();
        addAll(c);
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
