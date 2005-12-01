/*
 * $Id$
 *
 * Copyright 2005 The Apache Software Foundation.
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
package org.apache.struts.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.asm.Type;
import net.sf.cglib.core.Constants;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Creates dynamic proxies for DynaBeans with getter/setter pairs for each
 * property. The proxies intercept the corresponding methods and map them
 * back to the properties.
 *
 * @since Struts 1.3
 * @version $Revision$ $Date$
 */
public class DynaBeanInterceptor implements MethodInterceptor, Serializable  {

    private static Log log = LogFactory.getLog(DynaBeanInterceptor.class);

    /** A lookup table to map method names to the corresponding properties. */
    private Map propertyLookup = new HashMap();

    /**
     * Default Constructor.
     */
    public DynaBeanInterceptor() {
    }

    /**
     * Creates an Enhancer for a DynaClass/DynaBean.
     *
     * @param dynaClass the dynamic properties to use for enhancement
     * @param beanClass the class to create the proxy for
     * @return an enhancer to generate proxies
     */
    public Enhancer createEnhancer(DynaClass dynaClass, Class beanClass) {
        // Build an interface to implement consisting of getter/setter
        // pairs for each property. Also create a lookup table so we
        // can map method names back to the corresponding dynamic
        // property on invocation. This allows us to correctly handle
        // property names that don't comply with JavaBeans naming
        // conventions.
        DynaProperty[] dynaProperties = dynaClass.getDynaProperties();
        Map properties = new HashMap(dynaProperties.length * 2);
        InterfaceMaker im = new InterfaceMaker();
        for (int i = 0; i < dynaProperties.length; i++) {
            String name = dynaProperties[i].getName();
            Class type = dynaProperties[i].getType();
            Type ttype = Type.getType(type);

            if (! name.matches("[\\w]+")) {
                // Note: this allows leading digits, which is not legal
                // for an identifier but is valid in a getter/setter
                // method name. Since you can define such getter/setter
                // directly, we support doing so dynamically too.
                if (log.isWarnEnabled()) {
                    log.warn(
                        "Dyna property name '" + name +
                        "' in DynaBean " + dynaClass.getName() +
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

                // Method names
                String getterName = "get"+property;
                String setterName = "set"+property;

                // Create the standard getter/setter method pair
                Signature getter = new Signature(getterName, ttype, Constants.TYPES_EMPTY);
                Signature setter = new Signature(setterName, Type.VOID_TYPE, new Type[] { ttype });
                im.add(getter, Constants.TYPES_EMPTY);
                im.add(setter, Constants.TYPES_EMPTY);

                // Create the indexed getter/setter method pair
                if (dynaProperties[i].isIndexed() && dynaProperties[i].getType().isArray()) {
                    Type itype = Type.getType(Integer.TYPE);
                    ttype = Type.getType((type.isArray() ? type.getComponentType() : Object.class));
                    Signature indexGetter = new Signature(getterName, ttype, new Type[] { itype });
                    Signature indexSetter = new Signature(setterName, Type.VOID_TYPE, new Type[] { itype, ttype });
                    im.add(indexGetter, Constants.TYPES_EMPTY);
                    im.add(indexSetter, Constants.TYPES_EMPTY);
                }
                propertyLookup.put(getterName, name);
                propertyLookup.put(setterName, name);

            }
        }
        Class beanInterface = im.create();

        // Now generate a proxy for the dyna bean that also implements
        // the getter/setter methods defined above.  We turn off the
        // Factory interface to prevent problems with BeanUtils.copyProperties
        // when both source and target bean are enhanced (otherwise, the
        // target bean's callbacks get overwritten with the source bean's,
        // leading to unexpected behaviour).
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setInterfaces(new Class[] { beanInterface });
        enhancer.setCallback(this);
        enhancer.setUseFactory(false);
        return enhancer;
    }

    /**
     * Intercepts a method call on the enhanced DynaBean.
     *
     * @param obj the enhanced <code>DynaBean</code>
     * @param method the method to invoke on the object
     * @param args the method parameters
     * @param proxy the method proxy
     * @return the return value of the intercepted method call
     */
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {

        String methodNm = method.getName();
        String property = (String)propertyLookup.get(methodNm);

        if (property == null) {
            // Not a dyna property access, just pass call along
            return proxy.invokeSuper(obj, args);
        }

        boolean getter  = methodNm.startsWith("get");

        DynaBean dynaBean = (DynaBean)obj;
        if (getter) {
            if (args.length == 0) {
                return dynaBean.get(property);
            } else if(args.length == 1 && args[0].getClass() == Integer.class) {
                int index = ((Integer)args[0]).intValue();
                return dynaBean.get(property, index);
            } else {
                return proxy.invokeSuper(obj, args);
            }
        } else {
            if (args.length == 1) {
                dynaBean.set(property, args[0]);
                return null;
            } else if(args.length == 2 && args[0].getClass() == Integer.class) {
                int index = ((Integer)args[0]).intValue();
                dynaBean.set(property, index, args[1]);
                return null;
            } else {
                return proxy.invokeSuper(obj, args);
            }
        }

    }

}
