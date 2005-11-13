/*
 * $Id$
 *
 * Copyright 2000-2004 The Apache Software Foundation.
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


package org.apache.struts.action;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.FormPropertyConfig;
import org.apache.struts.util.RequestUtils;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.asm.Type;
import net.sf.cglib.core.Signature;
import net.sf.cglib.core.Constants;


/**
 * <p>Implementation of <code>DynaClass</code> for
 * <code>DynaActionForm</code> classes that allow developers to define
 * ActionForms without having to individually code all of the classes.
 * <strong>NOTE</strong> - This class is only used in the internal
 * implementation of dynamic action form beans. Application developers
 * never need to consult this documentation.</p>
 *
 * @version $Rev$ $Date$
 * @since Struts 1.1
 */

public class DynaActionFormClass implements DynaClass, Serializable {

    private Log log = LogFactory.getLog(DynaActionFormClass.class);

    // ----------------------------------------------------------- Constructors


    /**
     * <p>Construct a new <code>DynaActionFormClass</code> for the specified
     * form bean configuration.  This constructor is private;
     * <code>DynaActionFormClass</code> instances will be created as needed via
     * calls to the static <code>createDynaActionFormClass()</code> method.</p>
     *
     * @param config The FormBeanConfig instance describing the properties
     *  of the bean to be created
     *
     * @exception IllegalArgumentException if the bean implementation class
     *  specified in the configuration is not DynaActionForm (or a subclass
     *  of DynaActionForm)
     */
    public DynaActionFormClass(FormBeanConfig config) {

        introspect(config);

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The <code>DynaActionForm</code> implementation <code>Class</code>
     * which we will use to create new bean instances.</p>
     */
    protected transient Class beanClass = null;


    /**
     * <p>The form bean configuration information for this class.</p>
     */
    protected FormBeanConfig config = null;


    /**
     * <p>The "dynamic class name" for this <code>DynaClass</code>.</p>
     */
    protected String name = null;


    /**
     * <p>The set of dynamic properties that are part of this DynaClass.</p>
     */
    protected DynaProperty[] properties = null;


    /**
     * <p>The set of dynamic properties that are part of this
     * <code>DynaClass</code>, keyed by the property name.  Individual
     * descriptor instances will be the same instances as those in the
     * <code>properties</code> list.
     */
    protected HashMap propertiesMap = new HashMap();


    // ------------------------------------------------------ DynaClass Methods


    /**
     * <p>Return the name of this <code>DynaClass</code> (analogous to the
     * <code>getName()</code> method of <code>java.lang.Class</code>, which
     * allows the same <code>DynaClass</code> implementation class to support
     * different dynamic classes, with different sets of properties.
     *
     * @return The name of this <code>DynaClass</code>.
     */
    public String getName() {

        return (this.name);

    }


    /**
     * <p>Return a property descriptor for the specified property, if it exists;
     * otherwise, return <code>null</code>.</p>
     *
     * @param name Name of the dynamic property for which a descriptor
     *  is requested
     *
     * @return A property descriptor for the specified property.
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
     * currently defined in this <code>DynaClass</code>.  If no properties are
     * defined, a zero-length array will be returned.</p>
     *
     * @return An array of property instances for this class.
     */
    public DynaProperty[] getDynaProperties() {

        return (properties);
        // :FIXME: Should we really be implementing
        // getBeanInfo instead, which returns property descriptors
        // and a bunch of other stuff?

    }


    /**
     * <p>Instantiate and return a new {@link DynaActionForm} instance,
     * associated with this <code>DynaActionFormClass</code>.  The
     * properties of the returned {@link DynaActionForm} will have been
     * initialized to the default values specified in the form bean
     * configuration information.</p>
     *
     * @return A new {@link DynaActionForm} instance.
     *
     * @exception IllegalAccessException if the Class or the appropriate
     *  constructor is not accessible
     * @exception InstantiationException if this Class represents an abstract
     *  class, an array class, a primitive type, or void; or if instantiation
     *  fails for some other reason
     */
    public DynaBean newInstance()
        throws IllegalAccessException, InstantiationException {

        FormPropertyConfig[] props = config.findFormPropertyConfigs();
        DynaActionForm dynaBean = (DynaActionForm) doCreate(props);
        dynaBean.setDynaActionFormClass(this);
        for (int i = 0; i < props.length; i++) {
            dynaBean.set(props[i].getName(), props[i].initial());
        }
        return (dynaBean);

    }


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Render a <code>String</code> representation of this object.</p>
     *
     * @return The string representation of this instance.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("DynaActionFormBean[name=");
        sb.append(name);
        DynaProperty[] props = getDynaProperties();
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
     * Return the <code>DynaActionFormClass</code> instance for the specified
     * form bean configuration instance.
     *
     * @param config The config for which the class should be created.
     *
     * @return The instance for the specified form bean config.
     */
    public static DynaActionFormClass createDynaActionFormClass(
            FormBeanConfig config) {

        return config.getDynaActionFormClass();

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Return the implementation class we are using to construct new
     * instances, re-introspecting our {@link FormBeanConfig} if necessary
     * (that is, after being deserialized, since <code>beanClass</code> is
     * marked transient).</p>
     *
     * @return The implementation class used to construct new instances.
     */
    protected Class getBeanClass() {

        if (beanClass == null) {
            introspect(config);
        }
        return (beanClass);

    }


    /**
     * <p>Introspect our form bean configuration to identify the supported
     * properties.</p>
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
            throw new IllegalArgumentException(
                    "Cannot instantiate ActionFormBean class '"
                    + config.getType() + "': " + t);
        }
        if (!DynaActionForm.class.isAssignableFrom(beanClass)) {
            throw new IllegalArgumentException(
                    "Class '" + config.getType() + "' is not a subclass of "
                    + "'org.apache.struts.action.DynaActionForm'");
        }

        // Set the name we will know ourselves by from the form bean name
        this.name = config.getName();

        // Look up the property descriptors for this bean class
        FormPropertyConfig[] descriptors = config.findFormPropertyConfigs();
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


    // -------------------------------------------------------- Private Methods

    private Object doCreate(FormPropertyConfig[] props) {
        // Build an interface to implement consisting of getter/setter
        // pairs for each property. Also create a lookup table so we
        // can map method names back to the corresponding dynamic
        // property on invocation. This allows us to correctly handle
        // property names that don't comply with JavaBeans naming
        // conventions.
        Map properties = new HashMap(props.length * 2);
        InterfaceMaker im = new InterfaceMaker();
        for (int i = 0; i < props.length; i++) {
            String name = props[i].getName();
            Class type = props[i].getTypeClass();
            Type ttype = Type.getType(type);

            if (! name.matches("[\\w]+")) {
                // Note: this allows leading digits, which is not legal
                // for an identifier but is valid in a getter/setter
                // method name. Since you can define such getter/setter
                // directly, we support doing so dynamically too.
                if (log.isWarnEnabled()) {
                    log.warn(
                        "Dyna property name '" + name +
                        "' in form bean " + config.getName() +
                        " is not a legal Java identifier. " +
                        "No property access methods generated.");
                }
            } else {
                // Capitalize property name appropriately
                String property;
                if ((name.length() <= 1) ||
                    (  Character.isLowerCase(name.charAt(0)) &&
                    (! Character.isLowerCase(name.charAt(1))))
                ) {
                    property = name;
                } else {
                    property =
                        Character.toUpperCase(name.charAt(0)) +
                        name.substring(1);
                }

                // Create the getter/setter method pair
                Signature getter = new Signature("get"+property, ttype, Constants.TYPES_EMPTY);
                Signature setter = new Signature("set"+property, Type.VOID_TYPE, new Type[] { ttype });
                im.add(getter, Constants.TYPES_EMPTY);
                im.add(setter, Constants.TYPES_EMPTY);
                properties.put("get"+property, name);
                properties.put("set"+property, name);
            }
        }
        Class beanInterface = im.create();

        // Now generate a proxy for the dyna bean that also implements
        // the getter/setter methods defined above. We turn off the
        // Factory interface to preven problems with BeanUtils.copyProperties
        // when both source and target bean are enhanced (otherwise, the
        // target bean's callbacks get overwritten with the source beans,
        // leading to unexpected behaviour).
        Enhancer e = new Enhancer();
        e.setSuperclass(beanClass);
        e.setInterfaces(new Class[] { beanInterface });
        e.setCallback(new BeanInterceptor(properties));
        e.setUseFactory(false);

        // Return the generated/enhanced bean
        return e.create();
    }

    private static class BeanInterceptor implements MethodInterceptor, Serializable {
        private Map propertyLookup;

        public BeanInterceptor(Map propertyLookup) {
            this.propertyLookup = propertyLookup;
        }

        public Object intercept(Object obj, Method method, Object[] args,
                                MethodProxy proxy) throws Throwable {

            String methodNm = method.getName();
            String propertyNm = (String) propertyLookup.get(methodNm);
            String targetNm = methodNm.substring(0, 3); // get/set

            if (propertyNm == null) {
                // Not a dyna property access, just pass call along
                return proxy.invokeSuper(obj, args);
            }

            // Handle the dyna property access:
            //  - map getFoo(...) to get("foo", ...)
            //  - map setFoo(bar, ...) to set("foo", bar, ...)
            Class[] targetArgTypes = new Class[args.length + 1];
            Object[] targetArgs = new Object[args.length + 1];
            targetArgTypes[0] = String.class; // for property name
            targetArgs[0] = propertyNm;

            System.arraycopy(args, 0, targetArgs, 1, args.length);

            for (int i = 0; i < args.length; i++) {
                targetArgTypes[i + 1] = args[i].getClass();
            }

            Method target = obj.getClass().getMethod(targetNm, targetArgTypes);

            // Return the result of mapped get/set
            return target.invoke(obj, targetArgs);
        }
    }
}
