package org.apache.scaffold.model;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * Convenient wrapper for collections of JavaBeans returned
 * from the resource layer.
 * May be inserted in the request context and accessed by
 * tag extensions.
 * The message list can be used to pass confirmations
 * back up from the resource layer.
 * The description property is useful as a caption for the
 * collection.
 * Wrappers for interate and size are provided so that they
 * can be accessed as getIterate and getSize.
 * @author Ted Husted
 * @version $Revision: 1.4 $ $Date: 2002/02/22 10:15:16 $
 */
public interface ModelResult {


// ------------------------------------------------------------- Statics

    /**
     * A standard token to identify this object.
     */
    public static String TOKEN = "RESULT";


    /**
     * The wild card character for a blank select.
     */
    public static String PARAM_ANY = "*";


    /**
     * The equality indicator for description property.
     */
    public static String PARAM_EQUALS = " = ";


// ----------------------------------------------------------- Properties

    /**
     * Add a message to the List.
     */
    public boolean addMessage(Object message);


    /**
     * Return the messages.
     */
    public List getMessages();


    /**
     * Return the description.
     * @return the description
     */
    public String getDescription();


    /**
     * Set the description.
     * @param description The new description
     */
    public void setDescription(String description);


    /**
     * Set the description as "field = value".
     * @param description1 The attribute description
     * @param description2 The value description
     */
    public void setDescription(String value, String field);


    /**
     * Return the number of elements on the List.
     * @return the size of the List
     */
    public int size();


    /**
     * Accessor for <code>size()</code>.
     * @return the size of the List
     */
    public int getSize();


    /**
     * Return an iterator for the List,
     * or null if there is no List.
     * @return an iterator for the List
     */
    public Iterator iterator();


    /**
     * Accessor for <code>iterator()</code>.
     * @return an iterator for the List
     */
    public Iterator getIterator();


    /**
     * Appends the specified element to the end of the List.
     * @return the row count
     */
    public boolean add(Object o);


    /**
     * Appends all of the elements in the specified Collection
     * to the end of this list, in the order that they are
     * returned by the specified Collection's Iterator.
     */
    public boolean addAll(Collection c);


    /**
     * Returns the element at the specified position in this list.
     */
    public Object get(int index);


    /**
     * Populate matching properties on given object,
     * using bean at given index. Returns false if index>size.
     * <code>BeanUtils.describe</code>.
     * @exception Throws ModelException on any error.
     */
    public boolean populate(Object o, int index) throws ModelException;

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
