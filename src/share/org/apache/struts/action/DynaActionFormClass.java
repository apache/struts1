/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/DynaActionFormClass.java,v 1.12 2002/12/16 03:48:27 craigmcc Exp $
 * $Revision: 1.12 $
 * $Date: 2002/12/16 03:48:27 $
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


package org.apache.struts.action;


import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.FormPropertyConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;


/**
 * <p>Implementation of <code>DynaClass</code> for
 * <code>DynaActionForm</code> classes that allow developers to define
 * ActionForms without having to individually code all of the classes.
 * <strong>NOTE</strong> - This class is only used in the internal
 * implementation of dynamic action form beans.  Applications never need
 * to consult this documentation.</p>
 *
 * @author Craig McClanahan
 * @version $Revision: 1.12 $ $Date: 2002/12/16 03:48:27 $
 * @since Struts 1.1
 */

public class DynaActionFormClass implements DynaClass, Serializable {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new DynaActionFormClass for the specified form bean
     * configuration.  This constructor is private; DynaActionFormClass
     * instances will be created as needed via calls to the
     * static <code>createDynaActionFormClass()</code> method.
     *
     * @param config The FormBeanConfig instance describing the properties
     *  of the bean to be created
     *
     * @exception IllegalArgumentException if the bean implementation class
     *  specified in the configuration is not DynaActionForm (or a subclass
     *  of DynaActionForm)
     */
    private DynaActionFormClass(FormBeanConfig config) {

        introspect(config);

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The <code>DynaActionForm</code> implementation <code>Class</code> which
     * we will use to create new bean instances.
     */
    protected transient Class beanClass = null;


    /**
     * The form bean configuration information for this class.
     */
    protected FormBeanConfig config = null;


    /**
     * The "dynamic class name" for this <code>DynaClass</code>.
     */
    protected String name = null;


    /**
     * The set of dynamic properties that are part of this DynaClass.
     */
    protected DynaProperty properties[] = null;


    /**
     * The set of dynamic properties that are part of this DynaClass,
     * keyed by the property name.  Individual descriptor instances will
     * be the same instances as those in the <code>properties</code> list.
     */
    protected HashMap propertiesMap = new HashMap();


    // ------------------------------------------------------- Static Variables


    /**
     * The set of <code>DynaActionFormClass</code> instances that have
     * ever been created, keyed by the form bean name.
     */
    protected transient static HashMap dynaClasses = new HashMap();


    /**
     * The lockable object we can synchronize on, even if dynaClasses
     * is null,
     */
    protected static String lock = "";


    // ------------------------------------------------------ DynaClass Methods


    /**
     * Return the name of this DynaClass (analogous to the
     * <code>getName()</code> method of <code>java.lang.Class</code), which
     * allows the same <code>DynaClass</code> implementation class to support
     * different dynamic classes, with different sets of properties.
     */
    public String getName() {

        return (this.name);

    }


    /**
     * Return a property descriptor for the specified property, if it exists;
     * otherwise, return <code>null</code>.
     *
     * @param name Name of the dynamic property for which a descriptor
     *  is requested
     *
     * @exception IllegalArgumentException if no property name is specified
     */
    public DynaProperty getDynaProperty(String name) {

        if (name == null) {
            throw new IllegalArgumentException
                ("No property name specified");
        }
        return ((DynaProperty) propertiesMap.get(name));

    }


    /**
     * <p>Return an array of <code>DynaProperty</code>s for the properties
     * currently defined in this DynaClass.  If no properties are defined, a
     * zero-length array will be returned.</p>
     *
     * <p><strong>FIXME</strong> - Should we really be implementing
     * <code>getBeanInfo()</code> instead, which returns property descriptors
     * and a bunch of other stuff?</p>
     */
    public DynaProperty[] getDynaProperties() {

        return (properties);

    }


    /**
     * <p>Instantiate and return a new {@link DynaActionForm} instance,
     * associated with this <code>DynaActionFormClass</code>.  The
     * properties of the returned {@link DynaActionForm} will have been
     * initialized to the default values specified in the form bean
     * configuration information.</p>
     *
     * @exception IllegalAccessException if the Class or the appropriate
     *  constructor is not accessible
     * @exception InstantiationException if this Class represents an abstract
     *  class, an array class, a primitive type, or void; or if instantiation
     *  fails for some other reason
     */
    public DynaBean newInstance()
        throws IllegalAccessException, InstantiationException {

        DynaActionForm dynaBean =
            (DynaActionForm) getBeanClass().newInstance();
        dynaBean.setDynaActionFormClass(this);
        FormPropertyConfig props[] = config.findFormPropertyConfigs();
        for (int i = 0; i < props.length; i++) {
            dynaBean.set(props[i].getName(), props[i].initial());
        }
        return (dynaBean);

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Render a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("DynaActionFormBean[name=");
        sb.append(name);
        DynaProperty props[] = getDynaProperties();
        if (props == null) {
            props = new DynaProperty[0];
        }
        for (int i = 0; i < props.length; i++) {
            sb.append(',');
            sb.append(props[i].getName());
            sb.append('/');
            sb.append(props[i].getType());
        }
        sb.append("]");
        return (sb.toString());

    }


    // --------------------------------------------------------- Static Methods


    /**
     * Clear our cache of DynaActionFormClass instances.
     */
    public static void clear() {

        synchronized (lock) {
            if (dynaClasses == null) {
                dynaClasses = new HashMap();
            }
            dynaClasses.clear();
        }

    }


    /**
     * Create (if necessary) and return a new <code>DynaActionFormClass</code>
     * instance for the specified form bean configuration instance.
     *
     * @param config The FormBeanConfig instance describing the properties
     *  of the bean to be created
     *
     * @exception IllegalArgumentException if the bean implementation class
     *  specified in the configuration is not DynaActionForm (or a subclass
     *  of DynaActionForm)
     */
    public static DynaActionFormClass
        createDynaActionFormClass(FormBeanConfig config) {

        synchronized (lock) {
            if (dynaClasses == null) {
                dynaClasses = new HashMap();
            }
            ModuleConfig moduleConfig = config.getModuleConfig();
            String key = config.getName();
            if (moduleConfig != null) {
                key += moduleConfig.getPrefix();
            }
            DynaActionFormClass dynaClass =
                (DynaActionFormClass) dynaClasses.get(key);
            if (dynaClass == null) {
                dynaClass = new DynaActionFormClass(config);
                dynaClasses.put(key, dynaClass);
            }
            return (dynaClass);
        }

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the implementation class we are using to construct new
     * instances, re-introspecting our {@link FormBeanConfig} if necessary
     * (that is, after being deserialized, since <code>beanClass</code> is
     * marked transient.
     */
    protected Class getBeanClass() {

        if (beanClass == null) {
            introspect(config);
        }
        return (beanClass);

    }


    /**
     * Introspect our form bean configuration to identify the supported
     * properties.
     *
     * @param config The FormBeanConfig instance describing the properties
     *  of the bean to be created
     *
     * @exception IllegalArgumentException if the bean implementation class
     *  specified in the configuration is not DynaActionForm (or a subclass
     *  of DynaActionForm)
     */
    protected void introspect(FormBeanConfig config) {

        this.config = config;

        // Validate the ActionFormBean implementation class
        try {
            beanClass = RequestUtils.applicationClass(config.getType());
        } catch (Throwable t) {
            throw new IllegalArgumentException
                ("Cannot instantiate ActionFormBean class '" +
                 config.getType() + "': " + t);
        }
        if (!DynaActionForm.class.isAssignableFrom(beanClass)) {
            throw new IllegalArgumentException
                ("Class '" + config.getType() + "' is not a subclass of " +
                 "'org.apache.struts.action.DynaActionForm'");
        }

        // Set the name we will know ourselves by from the form bean name
        this.name = config.getName();

        // Look up the property descriptors for this bean class
        FormPropertyConfig descriptors[] = config.findFormPropertyConfigs();
        if (descriptors == null) {
            descriptors = new FormPropertyConfig[0];
        }

        // Create corresponding dynamic property definitions
        properties = new DynaProperty[descriptors.length];
        for (int i = 0; i < descriptors.length; i++) {
            properties[i] =
                new DynaProperty(descriptors[i].getName(),
                                 descriptors[i].getTypeClass());
            propertiesMap.put(properties[i].getName(),
                              properties[i]);
        }

    }


}
