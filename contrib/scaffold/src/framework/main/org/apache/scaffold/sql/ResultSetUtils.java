package org.apache.scaffold.sql;


import java.beans.PropertyDescriptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
// import org.apache.commons.beanutil.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
// import org.apache.commons.beanutil.PropertyUtils;


/**
 * General purpose utility methods related to ResultSets
 * <p>
 * @author Ted Husted
 * @version $Revision: 1.2 $ $Date: 2001/12/28 13:34:58 $
 */
 public class ResultSetUtils {

    /**
     * Populate the JavaBean properties of the specified bean, based on
     * the specified name/value pairs.  This method uses Java reflection APIs
     * to identify corresponding "property setter" method names. The type of
     * the value in the Map must match the setter type. The setter must
     * expect a single arguement (the one on the Map).
     * <p>
     * The particular setter method to be called for each property is
     * determined using the usual JavaBeans introspection mechanisms. Thus,
     * you may identify custom setter methods using a BeanInfo class that is
     * associated with the class of the bean itself. If no such BeanInfo
     * class is available, the standard method name conversion ("set" plus
     * the capitalized name of the property in question) is used.
     * <p>
     * <strong>NOTE</strong>:  It is contrary to the JavaBeans Specification
     * to have more than one setter method (with different argument
     * signatures) for the same property.
     * <p>
     * This method adopted from the Jakarta Commons BeanUtils.populate.
     * @author Craig R. McClanahan
     * @author Ralph Schaer
     * @author Chris Audley
     * @author Rey François
     * @author Gregor Raýman
     * @author Ted Husted
     *
     * @param bean JavaBean whose properties are being populated
     * @param properties Map keyed by property name, with the
     *  corresponding value to be set
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     */
    public static void setProperties(Object bean, Map properties)
        throws IllegalAccessException, InvocationTargetException {

        if ((bean == null) || (properties == null))
            return;

        /*
        if (debug >= 1)
            System.out.println("BeanUtils.populate(" + bean + ", " +
                               properties + ")");
        */

        // Loop through the property name/value pairs to be set
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {

            // Identify the property name and value(s) to be assigned
            String name = (String) names.next();
            if (name == null)
                continue;

            // Get the property descriptor of the requested property (if any)
            PropertyDescriptor descriptor = null;
            try {
                descriptor = PropertyUtils.getPropertyDescriptor(bean, name);
            } catch (Throwable t) {
                /*
                if (debug >= 1)
                    System.out.println("    getPropertyDescriptor: " + t);
                */
                descriptor = null;
            }
            if (descriptor == null) {
                /*
                if (debug >= 1)
                    System.out.println("    No such property, skipping");
                */
                continue;
            }

            /*
            if (debug >= 1)
                System.out.println("    Property descriptor is '" +
                                   descriptor + "'");
            */

            // Identify the relevant setter method (if there is one)
            Method setter = descriptor.getWriteMethod();
            if (setter == null) {
                /*
                if (debug >= 1)
                    System.out.println("    No setter method, skipping");
                */
                continue;
            }

            // Obtain value to be set
            Object[] args = new Object[1];
            args[0] = properties.get(name); // This MUST match setter type

            /*
            if (debug >= 1)
                System.out.println("  name='" + name + "', value.class='" +
                                   (value == null ? "NONE" :
                                   value.getClass().getName()) + "'");
            */
            /*
            if (debug >= 1)
                System.out.println("    Setting to " +
                                   (parameters[0] == null ? "NULL" :
                                    "'" + parameters[0] + "'"));
            */

            // Invoke the setter method
            setter.invoke(bean,args);
        }

        /*
        if (debug >= 1)
            System.out.println("============================================");
        */

    }


    /**
     * Return a ArrayList of beans populated form a ResultSet.
     * @param resultSet The ResultSet whose parameters are to be used
     * to populate bean properties
     * @param target An instance of the bean to populate
     * @exception SQLException if an exception is thrown while setting
     * property values, populating the bean, or accessing the ResultSet
     */
     public static Collection getCollection(Object target, ResultSet resultSet)
        throws SQLException {

        // Check prerequisites
        if ((target==null) || (resultSet==null))
            throw new SQLException("getCollection: Null parameter");

        // Acquire resultSet MetaData
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();

        // Create hashmap, sized to number of columns
        HashMap properties = new HashMap(cols,1);

        // Use ArrayList to maintain ResultSet sequence
        ArrayList list = new ArrayList();

        // Acquire target class
        Class factory = target.getClass();

        // Scroll to next record and pump into hashmap
        while (resultSet.next()) {
            for (int i=1; i<=cols ; i++) {
                String columnName = metaData.getColumnName(i);
                switch (metaData.getColumnType(i)) {
                    // http://java.sun.com/j2se/1.3.0/docs/api/java/sql/Types.html

                    // Convert known types

                    case Types.BIGINT:
                        properties.put(columnName,
                            new Long(resultSet.getLong(i)));
                        break;

                    case Types.DATE:
                        properties.put(columnName,
                            resultSet.getDate(i));
                        break;

                    case Types.DECIMAL:
                    case Types.DOUBLE:
                        properties.put(columnName,
                            new Double(resultSet.getDouble(i)));
                        break;

                    case Types.FLOAT:
                        properties.put(columnName,
                            new Float(resultSet.getFloat(i)));
                        break;

                    case Types.INTEGER:
                        properties.put(columnName,
                            new Integer(resultSet.getInt(i)));
                        break;

                    case Types.REAL:
                        properties.put(columnName,
                            new Double(resultSet.getString(i)));
                        break;

                    case Types.SMALLINT:
                        properties.put(columnName,
                            new Short(resultSet.getShort(i)));
                        break;

                    case Types.TIME:
                        properties.put(columnName,
                            resultSet.getTime(i));
                        break;

                    case Types.TIMESTAMP:
                        properties.put(columnName,
                            resultSet.getTimestamp(i));
                        break;

// :FIXME: Throws java.lang.ClassCastException: java.lang.Integer
// :FIXME: with Poolman and MySQL unless uses getString.
                    case Types.TINYINT:
                        properties.put(columnName,
                            new Byte(resultSet.getString(i)));
                        break;

                    case Types.CHAR:
                    case Types.CLOB:
                    case Types.VARCHAR:
                    case Types.LONGVARCHAR:
                        // :FIXME: Handle binaries differently?
                    case Types.BLOB:
                    case Types.LONGVARBINARY:
                    case Types.VARBINARY:
                        properties.put(columnName,
                            resultSet.getString(i));
                        break;

/*
:FIXME: Add handlers for
ARRAY
BINARY
BIT
DECIMAL
DISTINCT
JAVA_OBJECT
NULL
NUMERIC
OTHER
REF
STRUCT

*/
                     // Otherwise, pass as *String property to be converted
                    default:
                        properties.put(columnName + "String",
                            resultSet.getString(i));
                        break;
                }
            }

            try {
                Object bean = factory.newInstance();
                setProperties(bean,properties);
                list.add(bean);
            }
            catch (Exception e) {
                throw new SQLException("RequestUtils.getCollection: " +
                    e.getMessage());
            }

            properties.clear();

        } // end while

        return ((Collection) list);
     }

    /**
     * Return a ArrayList of beans populated form a ResultSet,
     * transferring all properties as Strings.
     * If resultSet is empty, then an empty list will be returned.
     * If properties of target and resultSet do not match, a list of
     * blank target beans will be returned.
     * Otherwise, a list of target beans populated from matching
     * properties (columns) in resultSet will be returned.
     * @param resultSet The ResultSet whose parameters are to be used
     * to populate bean properties
     * @param target An instance of the bean to populate
     * @exception SQLException if an exception is thrown while setting
     * property values, populating the bean, or accessing the ResultSet
     */
     public static Collection getCollectionString(Object target, ResultSet resultSet)
        throws SQLException {

        // Acquire resultSet MetaData
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();

        // Create hashmap, sized to number of columns
        HashMap properties = new HashMap(cols,1);

        // Use ArrayList to maintain ResultSet sequence
        ArrayList list = new ArrayList();

        // Acquire target class
        Class factory = target.getClass();

        // Scroll to next record and pump into hashmap
        while (resultSet.next()) {
            for (int i=1; i<=cols ; i++) {
                properties.put(metaData.getColumnName(i),
                    resultSet.getString(i));
            }
            try {
                Object bean = factory.newInstance();
                BeanUtils.populate(bean,properties);
                list.add(bean);
            }
            catch (Exception e) {
                throw new SQLException("RequestUtils.getCollectionString: " +
                    e.getMessage());
            }
            properties.clear();
        } // end while

        return ((Collection) list);
     }
}



/*
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Scaffold", and "Apache Software
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
