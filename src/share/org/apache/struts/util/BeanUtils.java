/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/Attic/BeanUtils.java,v 1.15 2000/11/13 17:31:05 mschachter Exp $
 * $Revision: 1.15 $
 * $Date: 2000/11/13 17:31:05 $
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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;


/**
 * Utility methods for populating JavaBeans properties via reflection.
 *
 * @author Craig R. McClanahan
 * @author Ralph Schaer
 * @author Chris Audley
 * @version $Revision: 1.15 $ $Date: 2000/11/13 17:31:05 $
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
     * Convert the specified value to an object of the specified class (if
     * possible).  Otherwise, return a String representation of the value.
     * If you specify <code>type</code> as the name of a Java primitive
     * type, an instance of the corresponding wrapper class (initialized
     * to the correct value) is returned instead.
     *
     * @param value Value to be converted (may be null)
     * @param clazz Java class to be converted to (must be String, one of
     *  the Java primitive types, or one of the primitive wrappers)
     */
    public static Object convert(String value, Class clazz) {

        String type = clazz.getName();
        if ("java.lang.String".equals(type) ||
        "String".equals(type)) {
            if (value == null)
            return ((String) null);
            else
            return (value);
        } else if ("java.lang.Boolean".equals(type) ||
        Boolean.TYPE.getName().equals(type) ||
        "boolean".equals(type)) {
            return (convertBoolean(value));
        } else if ("java.lang.Byte".equals(type) ||
        Byte.TYPE.getName().equals(type) ||
        "byte".equals(type)) {
            return (convertByte(value));
        } else if ("java.lang.Character".equals(type) ||
        Character.TYPE.getName().equals(type) ||
        "char".equals(type)) {
            return (convertCharacter(value));
        } else if ("java.lang.Integer".equals(type) ||
        Integer.TYPE.getName().equals(type) ||
        "int".equals(type)) {
            return (convertInteger(value));
        } else if ("java.lang.Long".equals(type) ||
        Long.TYPE.getName().equals(type) ||
        "long".equals(type)) {
            return (convertLong(value));
        } else if ("java.lang.Float".equals(type) ||
        Float.TYPE.getName().equals(type) ||
        "float".equals(type)) {
            return (convertFloat(value));
        } else if ("java.lang.Double".equals(type) ||
        Double.TYPE.getName().equals(type) ||
        "double".equals(type)) {
            return (convertDouble(value));
        } else {
            if (value == null)
            return ((String) null);
            else
            return (value.toString());
        }

    }


    /**
     * Convert an array of specified values to an array of objects of the
     * specified class (if possible).  If you specify <code>type</code>
     * as one of the Java primitive types, an array of that type will be
     * returned; otherwise an array of the requested type (must be String
     * or a Java wrapper class for the primitive types) will be returned.
     *
     * @param value Value to be converted (may be null)
     * @param clazz Java array class to be converted to (must be String, one of
     *  the Java primitive types, or one of the primitive wrappers)
     */
    public static Object convert(String values[], Class clazz) {

        String type = clazz.getComponentType().getName();
        if ("java.lang.String".equals(type) ||
        "String".equals(type)) {
            if (values == null)
            return ((String[]) null);
            else
            return (values);
        }

        int len = values.length;
        if ("java.lang.Boolean".equals(type)) {
            Boolean array[] = new Boolean[len];
            for (int i = 0; i < len; i++)
            array[i] = convertBoolean(values[i]);
            return (array);
        } else if ("boolean".equals(type) ||
        Boolean.TYPE.getName().equals(type)) {
            boolean array[] = new boolean[len];
            for (int i = 0; i < len; i++)
            array[i] = convertBoolean(values[i]).booleanValue();
        } else if ("java.lang.Byte".equals(type)) {
            Byte array[] = new Byte[len];
            for (int i = 0; i < len; i++)
            array[i] = convertByte(values[i]);
            return (array);
        } else if ("byte".equals(type) ||
        Byte.TYPE.getName().equals(type)) {
            byte array[] = new byte[len];
            for (int i = 0; i < len; i++)
            array[i] = convertByte(values[i]).byteValue();
        } else if ("java.lang.Character".equals(type)) {
            Character array[] = new Character[len];
            for (int i = 0; i < len; i++)
            array[i] = convertCharacter(values[i]);
            return (array);
        } else if ("char".equals(type) ||
        Character.TYPE.getName().equals(type)) {
            char array[] = new char[len];
            for (int i = 0; i < len; i++)
            array[i] = convertCharacter(values[i]).charValue();
        } else if ("java.lang.Integer".equals(type)) {
            Integer array[] = new Integer[len];
            for (int i = 0; i < len; i++)
            array[i] = convertInteger(values[i]);
            return (array);
        } else if ("int".equals(type) ||
        Integer.TYPE.getName().equals(type)) {
            int array[] = new int[len];
            for (int i = 0; i < len; i++)
            array[i] = convertInteger(values[i]).intValue();
            return (array);
        } else if ("java.lang.Long".equals(type)) {
            Long array[] = new Long[len];
            for (int i = 0; i < len; i++)
            array[i] = convertLong(values[i]);
            return (array);
        } else if ("long".equals(type) ||
        Long.TYPE.getName().equals(type)) {
            long array[] = new long[len];
            for (int i = 0; i < len; i++)
            array[i] = convertLong(values[i]).longValue();
            return (array);
        } else if ("java.lang.Float".equals(type)) {
            Float array[] = new Float[len];
            for (int i = 0; i < len; i++)
            array[i] = convertFloat(values[i]);
            return (array);
        } else if ("float".equals(type) ||
        Float.TYPE.getName().equals(type)) {
            float array[] = new float[len];
            for (int i = 0; i < len; i++)
            array[i] = convertFloat(values[i]).floatValue();
            return (array);
        } else if ("java.lang.Double".equals(type)) {
            Double array[] = new Double[len];
            for (int i = 0; i < len; i++)
            array[i] = convertDouble(values[i]);
            return (array);
        } else if ("double".equals(type) ||
        Double.TYPE.getName().equals(type)) {
            double array[] = new double[len];
            for (int i = 0; i < len; i++)
            array[i] = convertDouble(values[i]).doubleValue();
            return (array);
        } else {
            if (values == null)
            return ((String[]) null);
            else {
                String array[] = new String[len];
                for (int i = 0; i < len; i++)
                array[i] = values[i].toString();
                return (array);
            }
        }

        return ((String[]) null);	// Make the compiler shut up

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
        } else if (value.getClass().isArray()) {
            Vector values = new Vector();
            try {
                int n = Array.getLength(value);
                for (int i = 0; i < n; i++) {
                    values.addElement(Array.get(value, i).toString());
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
     * Return the PropertyDescriptor for the specified property of the
     * specified bean, if there is one;  Otherwise, return <code>null</code>.
     *
     * @param bean The JavaBean that the property belongs to
     * @param name Name of the property whose setter method is requested
     *
     * @deprecated Use <code>PropertyUtils.getPropertyDescriptor()</code>
     *  instead
     */
    public static PropertyDescriptor getPropertyDescriptor(Object bean,
    String name) {

        try {
            return (PropertyUtils.getPropertyDescriptor(bean, name));
        } catch (Throwable t) {
            return (null);
        }

    }


    /**
     * Return an array of PropertyDescriptors for the JavaBeans properties
     * belonging to the specified bean.  If the bean has no properties,
     * a zero-length array is returned.
     *
     * @param bean Bean whose property descriptors are required
     *
     * @deprecated Use <code>PropertyUtils.getPropertyDescriptors()</code>
     *  instead
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Object bean) {

        return (PropertyUtils.getPropertyDescriptors(bean));

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
     * Locate and return the specified bean, from an optionally specified
     * scope, in the specified page context.  If no such bean is found,
     * return <code>null</code> instead.
     *
     * @param pageContext Page context to be searched
     * @param name Name of the bean to be retrieved
     * @param scope Scope to be searched (page, request, session, application)
     *  or <code>null</code> to use <code>findAttribute()</code> instead
     *
     * @exception IllegalArgumentException if an invalid scope name
     *  is requested
     */
    public static Object lookup(PageContext pageContext, String name,
    String scope) {

        Object bean = null;
        if (scope == null)
        bean = pageContext.findAttribute(name);
        else if (scope.equalsIgnoreCase("page"))
        bean = pageContext.getAttribute(name, PageContext.PAGE_SCOPE);
        else if (scope.equalsIgnoreCase("request"))
        bean = pageContext.getAttribute(name, PageContext.REQUEST_SCOPE);
        else if (scope.equalsIgnoreCase("session"))
        bean = pageContext.getAttribute(name, PageContext.SESSION_SCOPE);
        else if (scope.equalsIgnoreCase("application"))
        bean =
        pageContext.getAttribute(name, PageContext.APPLICATION_SCOPE);
        else
        throw new IllegalArgumentException(scope);

        return (bean);

    }


    /**
     * Populate the properties of the specified JavaBean from the specified
     * HTTP request, based on matching each parameter name against the
     * corresponding JavaBeans "property setter" methods in the bean's class.
     * Suitable conversion is done for argument types as described under
     * <code>convert()</code>.
     *
     * @param bean The JavaBean whose properties are to be set
     * @param request The HTTP request whose parameters are to be used
     *                to populate bean properties
     *
     * @exception ServletException if an exception is thrown while setting
     *            property values
     */
    public static void populate(Object bean,
    HttpServletRequest request)
    throws ServletException {

        populate(bean, null, null, request);

    }


    /**
     * Populate the properties of the specified JavaBean from the specified
     * HTTP request, based on matching each parameter name (plus an optional
     * prefix and/or suffix) against the corresponding JavaBeans "property
     * setter" methods in the bean's class.  Suitable conversion is done for
     * argument types as described under <code>setProperties()</code>.
     * <p>
     * If you specify a non-null <code>prefix</code> and a non-null
     * <code>suffix</code>, the parameter name must match <strong>both</strong>
     * conditions for its value(s) to be used in populating bean properties.
     *
     * @param bean The JavaBean whose properties are to be set
     * @param prefix The prefix (if any) to be prepend to bean property
     *               names when looking for matching parameters
     * @param suffix The suffix (if any) to be appended to bean property
     *               names when looking for matching parameters
     * @param request The HTTP request whose parameters are to be used
     *                to populate bean properties
     *
     * @exception ServletException if an exception is thrown while setting
     *            property values
     */
    public static void populate(Object bean, String prefix, String suffix,
    HttpServletRequest request)
    throws ServletException {

        // Build a list of relevant request parameters from this request
        Hashtable properties = new Hashtable();
        //Enumeration of parameter names
        Enumeration names = null;
        //Hashtable for multipart values
        Hashtable multipartElements = null;

        boolean isMultipart = false;
        String contentType = request.getContentType();
        if ((contentType != null) &&
        (contentType.startsWith("multipart/form-data"))) {
            isMultipart = true;
            //initialize a MultipartRequestHandler
            MultipartRequestHandler multipart = null;

            //get an instance of ActionServlet
            ActionServlet servlet;

            if (bean instanceof ActionForm) {

                servlet = ((ActionForm) bean).getServlet();
            }
            else {
                throw new ServletException("bean that's supposed to be " +
                "populated from a multipart request is not of type " +
                "\"org.apache.struts.action.ActionForm\", but type " +
                "\"" + bean.getClass().getName() + "\"");
            }
            String multipartClass = (String)
            request.getAttribute("org.apache.struts.action.mapping.multipartclass");

            request.removeAttribute("org.apache.struts.action.mapping.multipartclass");

            if (multipartClass != null) {
                //try to initialize the mapping specific request handler
                try {
                    multipart = (MultipartRequestHandler) Class.forName(multipartClass).newInstance();
                }
                catch (ClassNotFoundException cnfe) {
                    servlet.log("MultipartRequestHandler class \"" +
                    multipartClass + "\" in mapping class not found, " +
                    "defaulting to global multipart class");
                }
                catch (InstantiationException ie) {
                    servlet.log("InstantiaionException when instantiating " +
                    "MultipartRequestHandler \"" + multipartClass + "\", " +
                    "defaulting to global multipart class, exception: " +
                    ie.getMessage());
                }
                catch (IllegalAccessException iae) {
                    servlet.log("IllegalAccessException when instantiating " +
                    "MultipartRequestHandler \"" + multipartClass + "\", " +
                    "defaulting to global multipart class, exception: " +
                    iae.getMessage());
                }
            }

            if (multipart == null) {
                //try to initialize the global multipart class
                try {
                    multipart = (MultipartRequestHandler) Class.forName(servlet.getMultipartClass()).newInstance();
                }
                catch (ClassNotFoundException cnfe) {
                    throw new ServletException("Cannot find multipart class \"" +
                    servlet.getMultipartClass() + "\"" +
                    ", exception: " + cnfe.getMessage());
                }
                catch (InstantiationException ie) {
                    throw new ServletException("InstantiaionException when instantiating " +
                    "multipart class \"" + servlet.getMultipartClass() +
                    "\", exception: " + ie.getMessage());
                }
                catch (IllegalAccessException iae) {
                    throw new ServletException("IllegalAccessException when instantiating " +
                    "multipart class \"" + servlet.getMultipartClass() +
                    "\", exception: " + iae.getMessage());
                }
            }


            //set the multipart request handler for our ActionForm
            //if the bean isn't an ActionForm, an exception would have been
            //thrown earlier, so it's safe to assume that our bean is
            //in fact an ActionForm
            ((ActionForm) bean).setMultipartRequestHandler(multipart);

            //set servlet and mapping info
            multipart.setServlet(servlet);
            multipart.setMapping((ActionMapping)
            request.getAttribute("org.apache.struts.action.mapping.instance"));
            request.removeAttribute("org.apache.struts.action.mapping.instance");

            //initialize request class handler
            multipart.handleRequest(request);

            //retrive form values and put into properties
            multipartElements = multipart.getAllElements();
            names = multipartElements.keys();
        }

        if (!isMultipart) {
            names = request.getParameterNames();
        }


        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String stripped = name;
            int subscript = stripped.lastIndexOf("[");
            if (subscript >= 0)  // Remove subscript expression
            stripped = stripped.substring(0, subscript);
            if (prefix != null) {
                if (!stripped.startsWith(prefix))
                continue;
                stripped = stripped.substring(prefix.length());
            }
            if (suffix != null) {
                if (!stripped.endsWith(suffix))
                continue;
                stripped =
                stripped.substring(0, stripped.length() - suffix.length());
            }
            if (isMultipart) {
                properties.put(stripped, multipartElements.get(name));
            }
            else {
                properties.put(stripped, request.getParameterValues(name));
            }
        }

        // Set the corresponding properties of our bean
        try {
            populate(bean, properties);
        } catch (Exception e) {
            throw new ServletException("BeanUtils.populate", e);
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
     * @param properties Hashtable keyed by property name, with the
     *  corresponding (String or String[]) value(s) to be set
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     */
    public static void populate(Object bean, Hashtable properties)
    throws IllegalAccessException, InvocationTargetException {

        if ((bean == null) || (properties == null))
        return;

        // Identify the property descriptors supported by our JavaBean
        PropertyDescriptor descriptors[] =
        PropertyUtils.getPropertyDescriptors(bean);
        if (descriptors.length < 1)
        return;

        // Loop through the property name/value pairs to be set
        Enumeration names = properties.keys();
        while (names.hasMoreElements()) {

            // Identify the property name and value(s) to be assigned
            String name = (String) names.nextElement();
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
                    parameters[0] = convert((String[]) values,
                    parameterTypes[0]);
                } else {
                    parameters[0] = convert((String[]) value,
                    parameterTypes[0]);
                }
            } else {
                if (value instanceof String) {
                    parameters[0] = convert((String) value,
                    parameterTypes[0]);
                }
                else if (value instanceof FormFile) {
                    parameters[0] = value;
                } else {
                    parameters[0] = convert( ((String[]) value)[0],
                    parameterTypes[0]);
                }
            }

            // Invoke the setter method
            setter.invoke(bean, parameters);

        }

    }


    // -------------------------------------------------------- Private Methods


    /**
     * Convert a String value to a corresponding Boolean value.
     *
     * @param value The string value to convert
     */
    private static Boolean convertBoolean(String value) {

        if (value == null)
        return (new Boolean(false));
        else if (value.equalsIgnoreCase("yes") ||
        value.equalsIgnoreCase("true") ||
        value.equalsIgnoreCase("on"))
        return (new Boolean(true));
        else
        return (new Boolean(false));

    }


    /**
     * Convert a String value to a corresponding Byte value.
     *
     * @param value The string value to convert
     */
    private static Byte convertByte(String value) {

        try {
            return (new Byte(value));
        } catch (NumberFormatException e) {
            return (new Byte((byte) 0));
        }

    }


    /**
     * Convert a String value to a corresponding Character value.
     *
     * @param value The string value to convert
     */
    private static Character convertCharacter(String value) {

        if (value == null)
        return (new Character(' '));
        else if (value.length() == 0)
        return (new Character(' '));
        else
        return (new Character(value.charAt(0)));

    }


    /**
     * Convert a String value to a corresponding Double value.
     *
     * @param value The string value to convert
     */
    private static Double convertDouble(String value) {

        try {
            return (new Double(value));
        } catch (NumberFormatException e) {
            return (new Double(0.0));
        }

    }


    /**
     * Convert a String value to a corresponding Float value.
     *
     * @param value The string value to convert
     */
    private static Float convertFloat(String value) {

        try {
            return (new Float(value));
        } catch (NumberFormatException e) {
            return (new Float(0.0));
        }

    }


    /**
     * Convert a String value to a corresponding Integer value.
     *
     * @param value The string value to convert
     */
    private static Integer convertInteger(String value) {

        try {
            return (new Integer(value));
        } catch (NumberFormatException e) {
            return (new Integer(0));
        }

    }


    /**
     * Convert a String value to a corresponding Long value.
     *
     * @param value The string value to convert
     */
    private static Long convertLong(String value) {

        try {
            return (new Long(value));
        } catch (NumberFormatException e) {
            return (new Long(0));
        }

    }


}
