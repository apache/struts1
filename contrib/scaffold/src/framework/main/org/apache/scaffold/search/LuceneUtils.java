package org.apache.scaffold.search;


import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.search.Hits;

import org.apache.commons.beanutils.BeanUtils;
// import org.apache.commons.beanutil.BeanUtils;

import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelPopulateException;


/**
 * General purpose utility methods related to Hits
 *
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @version $Revision: 1.2 $ $Date: 2001/12/28 13:34:58 $
 */
 public class LuceneUtils {

    /**
     * Populate the properties of the specified JavaBean from the specified
     * Lucene document, based on matching each parameter name against the
     * corresponding JavaBeans "property setter" methods in the bean's class.
     * Suitable conversion is done for argument types as described under
     * <code>setProperties()</code>.
     * <p>
     *
     * @param bean The JavaBean whose properties are to be set
     * @param document The Lucene document whose parameters are to be used
     *                to populate bean properties
     *
     * @exception ModelMapException if an exception is thrown while setting
     *            property values
     */
    public static void populate(Object bean, Document document)
        throws ModelException {

        // Build a list of relevant fields and values
        HashMap properties = new HashMap();

        // Iterator of field names
        Enumeration fields = document.fields();

        while (fields.hasMoreElements()) {
            Field field = (Field) fields.nextElement();
            properties.put(field.name(),field.stringValue());
        }

        // Set the corresponding properties of our bean
        try {
            BeanUtils.populate(bean, properties);
        } catch (Throwable t) {
            throw new ModelPopulateException(t);
        }

    }


   /**
     * Return a ArrayList of hits by looping and calling populate.
     * No assumptions are made about fields in document. Each is
     * processed with a separate call to populate. Whatever fields
     * in each document that match the target bean will be
     * transferred.
     *
     * @param hits The Hits whose documents are to be used
     * to populate bean properties
     * @param target An instance of the bean to populate
     *
     * @exception ModelMapException if an exception is thrown while setting
     * property values, populating the bean, or accessing the Hits
     */
     public static Collection getCollection(Object target, Hits hits)
        throws ModelException {

        // Use ArrayList to maintain sequence
        ArrayList list = new ArrayList();

        // Acquire target class
        Class factory = target.getClass();

        try {
            // Scroll to each document; populate bean, and add to list
            for (int i=0; i<hits.length(); ++i) {
                Object bean = factory.newInstance();
                Document doc = hits.doc(i);
                populate(bean,doc);
                list.add(bean);
            }
        } catch (Throwable t) {
            throw new ModelPopulateException(t);
        }
        return ((Collection) list);
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
 * 4. The names "The Jakarta Project", "Scaffold", and "Apache Software
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
