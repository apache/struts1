/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/FormBeanConfig.java,v 1.9 2002/12/08 02:09:44 craigmcc Exp $
 * $Revision: 1.9 $
 * $Date: 2002/12/08 02:09:44 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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


import java.io.Serializable;
import java.util.HashMap;
import org.apache.struts.action.DynaActionForm;


/**
 * <p>A JavaBean representing the configuration information of a
 * <code>&lt;form-bean&gt;</code> element in a Struts
 * configuration file.<p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.9 $ $Date: 2002/12/08 02:09:44 $
 * @since Struts 1.1
 */

public class FormBeanConfig implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * Has this component been completely configured?
     */
    protected boolean configured = false;


    /**
     * The set of FormProperty elements defining dynamic form properties for
     * this form bean, keyed by property name.
     */
    protected HashMap formProperties = new HashMap();


    // ------------------------------------------------------------- Properties


    /**
     * Is the form bean class an instance of DynaActionForm with dynamic
     * properties?
     */
    protected boolean dynamic = false;

    public boolean getDynamic() {
        return (this.dynamic);
    }

    /**
     * @deprecated The value to be returned by <code>getDynamic()</code>
     * is now computed automatically in <code>setType()</code>
     */
    public void setDynamic(boolean dynamic) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen"); 
        }
        ; // No action required
    }


    /**
     * The {@link ModuleConfig} with which this form bean definition
     * is associated.
     */
    protected ModuleConfig moduleConfig = null;


    /**
     * Return the {@link ModuleConfig} with which this form bean definition
     * is associated.
     */
    public ModuleConfig getModuleConfig() {
        return (this.moduleConfig);
    }


    /**
     * Set the {@link ModuleConfig} with which this form bean definition
     * is associated.
     *
     * @param moduleConfig The new {@link ModuleConfig} or <code>null</code>
     *  to disassociate this form bean configuration from any module
     */
    public void setModuleConfig(ModuleConfig moduleConfig) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.moduleConfig = moduleConfig;
    }

    /**
     * The unique identifier of this form bean, which is used to reference this
     * bean in <code>ActionMapping</code> instances as well as for the name of
     * the request or session attribute under which the corresponding form bean
     * instance is created or accessed.
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.name = name;
    }


    /**
     * The fully qualified Java class name of the implementation class
     * to be used or generated.
     */
    protected String type = null;

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.type = type;
        Class dynaBeanClass = DynaActionForm.class;
        Class formBeanClass = formBeanClass();
        if (formBeanClass != null) {
            if (dynaBeanClass.isAssignableFrom(formBeanClass)) {
                this.dynamic = true;
            } else {
                this.dynamic = false;
            }
        } else {
            this.dynamic = false;
        }
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Add a new <code>FormPropertyConfig</code> instance to the set associated
     * with this module.
     *
     * @param config The new configuration instance to be added
     *
     * @exception IllegalArgumentException if this property name has already
     *  been defined
     */
    public void addFormPropertyConfig(FormPropertyConfig config) {

        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        if (formProperties.containsKey(config.getName())) {
            throw new IllegalArgumentException("Property " +
                                               config.getName() +
                                               " already defined");
        }
        formProperties.put(config.getName(), config);

    }


    /**
     * Return the form property configuration for the specified property
     * name, if any; otherwise return <code>null</code>.
     *
     * @param name Form property name to find a configuration for
     */
    public FormPropertyConfig findFormPropertyConfig(String name) {

        return ((FormPropertyConfig) formProperties.get(name));

    }


    /**
     * Return the form property configurations for this module.  If there
     * are none, a zero-length array is returned.
     */
    public FormPropertyConfig[] findFormPropertyConfigs() {

        FormPropertyConfig results[] =
            new FormPropertyConfig[formProperties.size()];
        return ((FormPropertyConfig[]) formProperties.values().toArray(results));

    }


    /**
     * Freeze the configuration of this component.
     */
    public void freeze() {

        configured = true;

        FormPropertyConfig[] fpconfigs = findFormPropertyConfigs();
        for (int i = 0; i < fpconfigs.length; i++) {
            fpconfigs[i].freeze();
        }

    }


    /**
     * Remove the specified form property configuration instance.
     *
     * @param config FormPropertyConfig instance to be removed
     */
    public void removeFormPropertyConfig(FormPropertyConfig config) {

        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        formProperties.remove(config.getName());

    }


    /**
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("FormBeanConfig[");
        sb.append("name=");
        sb.append(this.name);
        sb.append(",type=");
        sb.append(this.type);
        sb.append("]");
        return (sb.toString());

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the <code>Class</code> instance for the form bean implementation
     * configured by this <code>FormBeanConfig</code> instance.  This method
     * uses the same algorithm as <code>RequestUtils.applicationClass()</code>
     * but is reproduced to avoid a runtime dependence.
     */
    protected Class formBeanClass() {

        ClassLoader classLoader =
            Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = this.getClass().getClassLoader();
        }
        try {
            return (classLoader.loadClass(getType()));
        } catch (Exception e) {
            return (null);
        }

    }


}
