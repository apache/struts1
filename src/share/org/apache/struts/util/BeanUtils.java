/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/Attic/BeanUtils.java,v 1.1 2000/05/31 22:28:11 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2000/05/31 22:28:11 $
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


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


/**
 * Utility methods for populating JavaBeans properties via reflection.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2000/05/31 22:28:11 $
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
     *
     * @param value Value to be converted (may be null)
     * @param type Java class to be converted to (must be String or one of
     *  the primitive wrappers)
     */
    public static Object convert(String value, String type) {

	if ("java.lang.String".equals(type)) {
	    if (value == null)
	        return ((String) null);
	    else
	        return (value);
	} else if ("java.lang.Boolean".equals(type) ||
	           "boolean".equals(type)) {
	    if (value == null)
	        return (new Boolean(false));
	    else if (value.equalsIgnoreCase("yes"))
	        return (new Boolean(true));
	    else if (value.equalsIgnoreCase("true"))
	        return (new Boolean(true));
	    else
	        return (new Boolean(false));
	} else if ("java.lang.Integer".equals(type) ||
		   "int".equals(type)) {
	    try {
		return (new Integer(value));
	    } catch (NumberFormatException e) {
		return (new Integer(0));
	    }
	} else if ("java.lang.Long".equals(type) ||
		   "long".equals(type)) {
	    try {
		return (new Long(value));
	    } catch (NumberFormatException e) {
		return (new Long(0));
	    }
	} else if ("java.lang.Float".equals(type) ||
		   "float".equals(type)) {
	    try {
		return (new Float(value));
	    } catch (NumberFormatException e) {
		return (new Float(0.0));
	    }
	} else if ("java.lang.Double".equals(type) ||
		   "double".equals(type)) {
	    try {
		return (new Double(value));
	    } catch (NumberFormatException e) {
		return (new Double(0.0));
	    }
	} else {
	    if (value == null)
	        return ((String) null);
	    else
	        return (value.toString());
	}

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
     * Populate the properties of the specified JavaBean from the specified
     * HTTP request, based on matching each parameter name against the
     * corresponding JavaBeans "property setter" methods in the bean's class.
     * Suitable conversion is done for argument types as described under
     * <code>convert()</code>.
     * <p>
     * <strong>IMPLEMENTATION NOTE</strong>:  If you have more than one setter
     * for the same property name (with different argument types supported by
     * this logic), the setter method that is actually called will be
     * arbitrarily determined.
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
     * <strong>IMPLEMENTATION NOTE</strong>:  If you have more than one setter
     * for the same property name (with different argument types supported by
     * this logic), the setter method that is actually called will be
     * arbitrarily determined.
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
	Enumeration names = request.getParameterNames();
	while (names.hasMoreElements()) {
	    String name = (String) names.nextElement();
	    if (prefix != null) {
		if (!name.startsWith(prefix))
		    continue;
		name = name.substring(0, prefix.length());
	    }
	    if (suffix != null) {
		if (!name.endsWith(suffix))
		    continue;
		name = name.substring(0, name.length() - suffix.length());
	    }
	    properties.put(name, request.getParameter(name));
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
     * <code>double</code>.
     * <p>
     * <strong>IMPLEMENTATION NOTE</strong>:  If you have more than one setter
     * for the same property name (with different argument types supported by
     * this logic), the setter method that is actually called will be
     * arbitrarily determined.
     * <p>
     * FIXME - Deal with array and indexed setters also.
     *
     * @param bean JavaBean whose properties are being populated
     * @param properties Hashtable keyed by property name, with the
     *  corresponding (String or String[]) value(s) to be set
     *
     * @exception Exception if thrown while setting properties
     */
    public static void populate(Object bean, Hashtable properties)
	throws Exception {

	if ((bean == null) || (properties == null))
	    return;

	// Identify the methods supported by our JavaBean
	Method methods[] = null;
	methods = bean.getClass().getMethods();
	Class parameterTypes[] = null;

	// Loop through the property name/value pairs to be set
	Enumeration names = properties.keys();
	while (names.hasMoreElements()) {

	    // Identify the property name, value, and setter method name
	    String name = (String) names.nextElement();
	    String value = null;
	    Object values = properties.get(name);
	    if (values instanceof String)
	        value = (String) values;
	    else if (values instanceof String[])
	        value = (((String[]) values)[0]);
	    else
	        value = values.toString();
	    String setterName = "set" + capitalize(name);

	    // Identify a potential setter method (if there is one)
	    Method setter = null;
	    for (int i = 0; i < methods.length; i++) {
		if (!setterName.equals(methods[i].getName()))
		    continue;
		parameterTypes = methods[i].getParameterTypes();
		if (parameterTypes.length != 1)
		    continue;
		setter = methods[i];
		break;
	    }
	    if (setter == null)
		continue;	// No setter method that takes one argument
	    String parameterType = parameterTypes[0].getName();

	    // Convert the parameter value as required for this setter method
	    Object parameters[] = new Object[1];
	    parameters[0] = convert(value, parameterType);

	    // Invoke the setter method
	    setter.invoke(bean, parameters);

	}

    }


    // -------------------------------------------------------- Private Classes


}
