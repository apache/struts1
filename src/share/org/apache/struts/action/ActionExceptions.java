/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Attic/ActionExceptions.java,v 1.1 2001/12/31 01:14:36 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2001/12/31 01:14:36 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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

package org.apache.struts.action;

import java.io.*;
import java.util.*;
import org.apache.commons.collections.FastHashMap;


/**
 * Encapsulate a collection of ActionException objects that can be
 * administered and searched, while hiding the internal implementation.
 *
 * @author ldonlan
 * @version $Revision: 1.1 $ $Date: 2001/12/31 01:14:36 $
 */

public class ActionExceptions implements Serializable{
    private static final String APP_EXCEPTION_KEY =
        "org.apache.struts.util.AppException";

    private static final String APP_EXCEPTION_HANDLER_KEY =
        "org.apache.struts.action.ExceptionHandler";

    /** Creates new ActionExceptions */
    public ActionExceptions() {
        // Register an application exception.
        addAppException();
    }

    // ----------------------------------------------------- Instance Variables


    /**
     * The collection of ActionForward instances, keyed by logical name.
     */
    private FastHashMap exceptions = new FastHashMap();


    // ------------------------------------------------------------- Properties


    /**
     * Return the "fast" mode flag.
     */
    public boolean getFast() {

        return (exceptions.getFast());

    }


    /**
     * Set the "fast" mode flag.
     *
     * @param fast The new fast mode flag
     */
    public void setFast(boolean fast) {

        exceptions.setFast(fast);

    }

    /**
     * Register a logical exception that may occur processing the
     * maping.
     *
     * @param exception The exception to be added
     */
    public void addException(ActionException ex){
        exceptions.put(ex.getType(), ex);

    }

    /**
     * Return the ActionException associated with the specified class name,
     * if any; otherwise return <code>null</code>.<p>
     * The search will consist of first looking for a specific match on
     * the class provided.  If one is not found, all <b>ActionException</b>
     * objects will be examined.  For all objects with the hierarchacal
     * property set to true, a Class.isAssignableFrom() test will be performed.
     * The first match to be made will be returned.
     *
     * @param name class name of the exception
     */
    public ActionException findException(Class type){
        ActionException ex = null;

        ex = (ActionException)exceptions.get(type.getName());

        //If no exact match was made
        if (ex == null){
            //Look through all the A.E's
            Iterator it = exceptions.values().iterator();

            ActionException current = null;
            while (it.hasNext()){
                current = (ActionException)it.next();

                //If there is a hierarchal match and the provided
                //exception is assignable from it, then return it
                if (current.isHierarchical()){
                    //Make sure that the class is not null - i.e the
                    //type could not be used to create a class.
                    if (current.getExClass() != null){
                        if (current.getExClass().isAssignableFrom(type)){
                            ex = current;
                            break;
                        }
                    }
                }
            }
        }

        return ex;
    }


    /**
     *Removes the ActionException with the specified type from the
     *collection.
     *
     */
    public ActionException remove(String type){
        return (ActionException)exceptions.remove(type);
    }


    // -------------------------------------------------------- Private Methods

    /**
     * Register an application exception
     */
    private void addAppException() {
        ActionException ae = new ActionException();
        ae.setType(APP_EXCEPTION_KEY);
        ae.setHandler(APP_EXCEPTION_HANDLER_KEY);
        addException(ae);
    }

}
