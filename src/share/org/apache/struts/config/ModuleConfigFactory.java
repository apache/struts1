/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/ModuleConfigFactory.java,v 1.4 2003/02/05 00:51:40 dgraham Exp $
 * $Revision: 1.4 $
 * $Date: 2003/02/05 00:51:40 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All rights
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

package org.apache.struts.config;

import org.apache.struts.util.RequestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A factory interface for creating {@link ModuleConfig}s.
 *
 * @author Robert Leland
 * @version $Revision: 1.4 $ $Date: 2003/02/05 00:51:40 $ 
 *
 * @see ModuleConfig
 */
public abstract class ModuleConfigFactory {
    /**
     * Create and return a newly instansiated {@link ModuleConfig}.
     * This method must be implemented by concrete subclasses.
     *
     * @param prefix Module prefix for Configuration
     */
    public abstract ModuleConfig createModuleConfig(String prefix);


    // ------------------------------------------------------ Static Properties
    /**
     * The fully qualified class name that is used for
     * <code>ModuleConfigFactory</code> instances.
     * @return class name that is used for
     *   <code>ModuleConfigFactory</code> instances
     */
    public static String getFactoryClass() {
        return (ModuleConfigFactory.factoryClass);
    }

    /**
     * Set the fully qualified class name that is used for
     * <code>ModuleConfigFactory</code> instances.
     * @param factoryClass name that is used for
     *   <code>ModuleConfigFactory</code> instances
     */
    public static void setFactoryClass(String factoryClass) {
        ModuleConfigFactory.factoryClass = factoryClass;
        ModuleConfigFactory.clazz = null;
    }

    // --------------------------------------------------------- Static Methods


    /**
     * Create and return a <code>ModuleConfigFactory</code> instance of the
     * appropriate class, which can be used to create customized
     * <code>ModuleConfig</code> instances.  If no such factory can be
     * created, return <code>null</code> instead.
     */
    public static ModuleConfigFactory createFactory() {

        // Construct a new instance of the specified factory class
        try {
            if (clazz == null) {
                clazz = RequestUtils.applicationClass(factoryClass);
            }
            
            ModuleConfigFactory factory =
                (ModuleConfigFactory) clazz.newInstance();
            
            return (factory);
            
        } catch (Throwable t) {
            LOG.error("ModuleConfigFactory.createFactory", t);
            return (null);
        }

    }


    /**
     * The Java class to be used for
     * <code>ModuleConfigFactory</code> instances.
     */
    protected static Class clazz = null;

    /**
     * Commons Logging instance.
     */
    private static Log LOG = LogFactory.getLog(ModuleConfigFactory.class);


    /**
     * The fully qualified class name to be used for
     * <code>ModuleConfigFactory</code> instances.
     */
    protected static String factoryClass =
        "org.apache.struts.config.impl.DefaultModuleConfigFactory";


}
