/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/FormBeanConfig.java,v 1.13 2004/04/08 22:07:56 mrdon Exp $
 * $Revision: 1.13 $
 * $Date: 2004/04/08 22:07:56 $
 *
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * @version $Revision: 1.13 $ $Date: 2004/04/08 22:07:56 $
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
