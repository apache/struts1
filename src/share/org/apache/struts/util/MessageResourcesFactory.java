/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/MessageResourcesFactory.java,v 1.4 2001/02/12 00:32:13 craigmcc Exp $
 * $Revision: 1.4 $
 * $Date: 2001/02/12 00:32:13 $
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


package org.apache.struts.util;


import java.io.Serializable;


/**
 * Factory for <code>MessageResources</code> instances.  The general usage
 * pattern for this class is:
 * <ul>
 * <li>Call <code>MessageResourcesFactory().createFactory()</code> to retrieve
 *     a <code>MessageResourcesFactory</code> instance.</li>
 * <li>Set properties as required to configure this factory instance to create
 *     <code>MessageResources</code> instances with desired
 *     characteristics.</li>
 * <li>Call the <code>createResources()</code> method of the factory to
 *     retrieve a newly instantiated <code>MessageResources</code>
 *     instance.</li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.4 $ $Date: 2001/02/12 00:32:13 $
 */

public abstract class MessageResourcesFactory implements Serializable {


    // ---------------------------------------------------- Instance Properties


    /**
     * The "return null" property value to which newly created
     * MessageResourcess should be initialized.
     */
    protected boolean returnNull = true;

    public boolean getReturnNull() {
        return (this.returnNull);
    }

    public void setReturnNull(boolean returnNull) {
        this.returnNull = returnNull;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Create and return a newly instansiated <code>MessageResources</code>.
     * This method must be implemented by concrete subclasses.
     *
     * @param config Configuration parameter(s) for the requested bundle
     */
    public abstract MessageResources createResources(String config);


    // ------------------------------------------------------ Static Properties


    /**
     * The Java class to be used for
     * <code>MessageResourcesFactory</code> instances.
     */
    protected static transient Class clazz = null;


    /**
     * The fully qualified class name to be used for
     * <code>MessageResourcesFactory</code> instances.
     */
    protected static String factoryClass =
        "org.apache.struts.util.PropertyMessageResourcesFactory";

    public static String getFactoryClass() {
        return (MessageResourcesFactory.factoryClass);
    }

    public static void setFactoryClass(String factoryClass) {
        MessageResourcesFactory.factoryClass = factoryClass;
        MessageResourcesFactory.clazz = null;
    }


    // --------------------------------------------------------- Static Methods


    /**
     * Create and return a <code>MessageResourcesFactory</code> instance of the
     * appropriate class, which can be used to create customized
     * <code>MessageResources</code> instances.  If no such factory can be
     * created, return <code>null</code> instead.
     */
    public static MessageResourcesFactory createFactory() {

        // Construct a new instance of the specified factory class
        try {
            if (clazz == null)
                clazz = Class.forName(factoryClass);
            MessageResourcesFactory factory =
                (MessageResourcesFactory) clazz.newInstance();
            return (factory);
        } catch (Throwable t) {
            System.out.println("MessageResourcesFactory.createFactory");
            t.printStackTrace(System.out);
            return (null);
        }

    }


}
