/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/Attic/BeanUtils.java,v 1.21 2001/01/07 22:39:08 craigmcc Exp $
 * $Revision: 1.21 $
 * $Date: 2001/01/07 22:39:08 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


/**
 * Utility methods for populating JavaBeans properties via reflection.
 *
 * @author Craig R. McClanahan
 * @author Ralph Schaer
 * @author Chris Audley
 * @version $Revision: 1.21 $ $Date: 2001/01/07 22:39:08 $
 */

public final class BeanUtils {


    // --------------------------------------------------------- Public Classes


    /**
     * Return the input string with the first character capitalized.
     *
     * @param name The string to be modified and returned
     */
    public static String capitalize(String name) {

        if ((name == null) || (name.length() < 1))
            return (name);
        char chars[] = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);

    }


    /**
     * Clone a bean based on the available property getters and setters,
     * even if the bean class itself does not implement Cloneable.
     *
     * @param bean Bean to be cloned
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InstantiationException if a new instance of the bean's
     *  class cannot be instantiated
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  propety cannot be found
     */
    public static Object cloneBean(Object bean)
        throws IllegalAccessException, InstantiationException,
               InvocationTargetException, NoSuchMethodException {

        Class clazz = bean.getClass();
        Object newBean = clazz.newInstance();
        PropertyUtils.copyProperties(newBean, bean);
        return (newBean);

    }


    /**
     * Filter the specified string for characters that are senstive to
     * HTML interpreters, returning the string with these characters replaced
     * by the corresponding character entities.
     *
     * @param value The string to be filtered and returned
     */
    public static String filter(String value) {

        if (value == null)
            return (null);

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (ch == '<')
                result.append("&lt;");
            else if (ch == '>')
                result.append("&gt;");
            else if (ch == '&')
                result.append("&amp;");
            else if (ch == '"')
                result.append("&quot;");
            else
                result.append(ch);
        }
        return (result.toString());

    }


    /**
     * Return the value of the specified array property of the specified
     * bean, as a String array.
     *
     * @param bean Bean whose property is to be extracted
     * @param name Name of the property to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  propety cannot be found
     */
    public static String[] getArrayProperty(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException,
               NoSuchMethodException {

        Object value = PropertyUtils.getProperty(bean, name);
        if (value == null) {
            return (null);
        } else if (value instanceof Collection) {
            ArrayList values = new ArrayList((Collection) value);
            return ((String[]) values.toArray(new String[values.size()]));
        } else if (value.getClass().isArray()) {
            ArrayList values = new ArrayList();
            try {
                int n = Array.getLength(value);
                for (int i = 0; i < n; i++) {
                    values.add(Array.get(value, i).toString());
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ;
            }
            return ((String[]) values.toArray(new String[values.size()]));
        } else {
            String results[] = new String[1];
            results[0] = value.toString();
            return (results);
        }

    }


    /**
     * Return the value of the specified index of the specified array
     * property, as a String.  The index is specified by being appended
     * to the property name in square brackets.  If the specified index
     * is out of range, <code>null</code> is returned.
     *
     * @param bean Bean whose property is to be extracted
     * @param name name of the property to be extracted, plus a literal
     *  integer subscript in square brackets
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  propety cannot be found
     */
    public static String getIndexedProperty(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException,
               NoSuchMethodException {

        // @deprecated Convert the deprecated syntax to the new format
        /*
        int left = name.lastIndexOf("[");
        int right = name.lastIndexOf("]");
        if ((left >= 0) && (right > left))
        name = name.substring(0, left) + PropertyUtils.INDEXED_DELIM +
        name.substring(left + 1, right);
         */

        // Parse the property name and subscript expression
        int delim = PropertyUtils.INDEXED_DELIM;
        if (delim < 0)
            return (getScalarProperty(bean, name));
        int index = -1;
        try {
            index = Integer.parseInt(name.substring(delim + 1));
            name = name.substring(0, delim);
        } catch (NumberFormatException e) {
            return (getScalarProperty(bean, name));
        }

        // Return the value at the specified index
        return (getIndexedProperty(bean, name, index));

    }



    /**
     * Return the value at the specified index of the specified array
     * property, as a String.  If the specified index is out of range,
     * <code>null</code> will be returned.
     *
     * @param bean Bean whose property is to be extracted
     * @param name name of the property to be extracted
     * @param index Index of the property value to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  propety cannot be found
     */
    public static String getIndexedProperty(Object bean, String name,
                                            int index)
        throws IllegalAccessException, InvocationTargetException,
               NoSuchMethodException {

        if (index < 0)
            return (null);
        String values[] = getArrayProperty(bean, name);
        if ((values == null) || (index >= values.length))
            return (null);
        else
            return (values[index]);

    }


    /**
     * Return the value of the specified property of the specified
     * bean, with no type conversinos.
     *
     * @param bean Bean whose property is to be extracted
     * @param name Name of the property to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  propety cannot be found
     *
     * @deprecated Use <code>PropertyUtils.getProperty()</code> instead.
     */
    public static Object getPropertyValue(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException,
               NoSuchMethodException {

        return (PropertyUtils.getProperty(bean, name));

    }


    /**
     * Return the value of the specified scalar property of the specified
     * bean, as a String.
     *
     * @param bean Bean whose property is to be extracted
     * @param name Name of the property to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  propety cannot be found
     */
    public static String getScalarProperty(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException,
               NoSuchMethodException {

        Object value = PropertyUtils.getProperty(bean, name);
        if (value == null) {
            return (null);
        } else if (value.getClass().isArray()) {
            String values[] = getArrayProperty(bean, name);
            if (values.length > 0)
                return (values[0]);
            else
                return (null);
        } else {
            return (value.toString());
        }

    }


    /**
     * Populate the JavaBeans properties of the specified bean, based on
     * the specified name/value pairs.  This method uses Java reflection APIs
     * to identify corresponding "property setter" method names, and deals
     * with setter arguments of type <code>String</code>, <code>boolean</code>,
     * <code>int</code>, <code>long</code>, <code>float</code>, and
     * <code>double</code>.  In addition, array setters for these types (or the
     * corresponding primitive types) can also be identified.
     * <p>
     * The particular setter method to be called for each property is
     * determined using the usual JavaBeans introspection mechanisms.  Thus,
     * you may identify custom setter methods using a BeanInfo class that is
     * associated with the class of the bean itself.  If no such BeanInfo
     * class is available, the standard method name conversion ("set" plus
     * the capitalized name of the property in question) is used.
     * <p>
     * <strong>NOTE</strong>:  It is contrary to the JavaBeans Specification
     * to have more than one setter method (with different argument
     * signatures) for the same property.
     *
     * @param bean JavaBean whose properties are being populated
     * @param properties Map keyed by property name, with the
     *  corresponding (String or String[]) value(s) to be set
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     */
    public static void populate(Object bean, Map properties)
        throws IllegalAccessException, InvocationTargetException {

        if ((bean == null) || (properties == null))
            return;

        // Identify the property descriptors supported by our JavaBean
        PropertyDescriptor descriptors[] =
            PropertyUtils.getPropertyDescriptors(bean);
        if (descriptors.length < 1)
            return;

        // Loop through the property name/value pairs to be set
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {

            // Identify the property name and value(s) to be assigned
            String name = (String) names.next();
            Object value = properties.get(name);	// String or String[]
            if (value == null)
                continue;

            // Identify the relevant setter method (if there is one)
            Method setter = null;
            Class parameterTypes[] = null;
            for (int i = 0; i < descriptors.length; i++) {
                if (!name.equals(descriptors[i].getName()))
                    continue;
                setter = descriptors[i].getWriteMethod();
                if (setter == null)
                    continue;
                parameterTypes = setter.getParameterTypes();
                if (parameterTypes.length != 1) {
                    setter = null;
                    continue;
                }
                break;
            }
            if (setter == null)
                continue;

            // Convert the parameter value as required for this setter method
            Object parameters[] = new Object[1];
            if (parameterTypes[0].isArray()) {
                if (value instanceof String) {
                    String values[] = new String[1];
                    values[0] = (String) value;
                    parameters[0] = ConvertUtils.convert((String[]) values,
                    parameterTypes[0]);
                } else {
                    parameters[0] = ConvertUtils.convert((String[]) value,
                    parameterTypes[0]);
                }
            } else {
                if (value instanceof String) {
                    parameters[0] = ConvertUtils.convert((String) value,
                    parameterTypes[0]);
                }
                /* FIXME - Do we still need this?
                else if (value instanceof FormFile) {
                    parameters[0] = value;
                }
                */
                else {
                    parameters[0] = ConvertUtils.convert(((String[]) value)[0],
                    parameterTypes[0]);
                }
            }

            // Invoke the setter method
            setter.invoke(bean, parameters);

        }

    }


}
