/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Attic/ActionFormBeans.java,v 1.4 2001/07/16 00:44:52 craigmcc Exp $
 * $Revision: 1.4 $
 * $Date: 2001/07/16 00:44:52 $
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


import java.io.Serializable;
import org.apache.commons.collections.FastHashMap;


/**
 * Encapsulate a collection of ActionFormBean objects that can be
 * administered and searched, while hiding the internal implementation.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.4 $ $Date: 2001/07/16 00:44:52 $
 */

public class ActionFormBeans implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * The collection of ActionFormBean instances, keyed by name.
     */
    protected FastHashMap formBeans = new FastHashMap();


    // ------------------------------------------------------------- Properties


    /**
     * Return the "fast" mode flag.
     */
    public boolean getFast() {

        return (formBeans.getFast());

    }


    /**
     * Set the "fast" mode flag.
     *
     * @param fast The new fast mode flag
     */
    public void setFast(boolean fast) {

        formBeans.setFast(fast);

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Register a form bean to the set configured for this servlet.
     *
     * @param formBean The formBean to be added
     */
    public void addFormBean(ActionFormBean formBean) {

	formBeans.put(formBean.getName(), formBean);

    }


    /**
     * Return the formBean associated with the specified logical name,
     * if any; otherwise return <code>null</code>.
     *
     * @param name Logical name of the desired form bean
     */
    public ActionFormBean findFormBean(String name) {

	return ((ActionFormBean) formBeans.get(name));

    }


    /**
     * Return the set of names for form beans defined in this collection.
     * If there are no such formBeans, a zero-length array is returned.
     */
    public String[] findFormBeans() {

        return
         ((String[]) formBeans.keySet().toArray(new String[formBeans.size()]));

    }


    /**
     * Deregister a formBean from the set configured for this servlet.
     *
     * @param formBean The formBean to be deregistered
     */
    public void removeFormBean(ActionFormBean formBean) {

	formBeans.remove(formBean.getName());

    }


}
