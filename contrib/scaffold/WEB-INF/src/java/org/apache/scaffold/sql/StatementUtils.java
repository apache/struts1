package org.apache.scaffold.sql;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.Collection;


/**
 * General purpose SQL Statements.
 * :FIXME: getCollection returns blank string exceptions when used with multiple parameters (single OK).
 * :TODO: Refactor executeUpdate methods like getCollection methods.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 11:53:12 $
 */
public final class StatementUtils {


    /**
     * Prepares statement using SQL statement and executes
     * with DBMS configured with the ConnectionPool.
     * <p>
     * Command may be an INSERT, UPDATE, or DELETE statement
     * or anything that returns nothing, such as a DDL
     * statement.
     * <p>
     * @param command The SQL statement to execute.
     * @exception SQLException if SQL error occurs
     */
    public static final int executeUpdate(String resource, String command)
        throws SQLException {

        Connection connection = null;
        Statement statement = null;
        int result = 0;
        try {
            connection = ConnectionPool.getConnection(resource);
            statement = connection.createStatement();
            result = statement.executeUpdate(command);
        } // end try

        finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection!= null)
                    connection.close();
            }
            catch (SQLException sqle) {
                // non-fatal; only closing if pooled.
                // don't overthrow original exception
            }
        }
        return result;

    } // ---- End executeUpdate ----


    /**
     * Prepares statement using SQL statement and executes
     * with DBMS configured with the ConnectionPool.
     * <p>
     * Command may be an INSERT, UPDATE, or DELETE statement
     * or anything that returns nothing, such as a DDL
     * statement.
     * @param resource The database resource, or null for default
     * @param command The SQL statement to execute.
     * @param key The parameter
     * @exception SQLException if SQL error occurs
     */
    public static final int executeUpdate(String resource,
            String command, String key)
        throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = ConnectionPool.getConnection(resource);
            statement = connection.prepareStatement(command);
            if (key!=null)
                statement.setString(1, key);
            result = statement.executeUpdate();
        } // end try

        finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection!= null)
                    connection.close();
            }
            catch (SQLException sqle) {
                // non-fatal; only closing if pooled.
                // don't overthrow original exception
            }
        }
        return result;

    } // ---- End executeUpdate ----


    /**
     * Prepares statement using SQL statement and executes
     * with DBMS configured with the ConnectionPool.
     * <p>
     * Command may be an INSERT, UPDATE, or DELETE statement
     * or anything that returns nothing, such as a DDL
     * statement.
     * @param resource The database resource, or null for default
     * @param command The SQL statement to execute.
     * @param key The parameter
     * @exception SQLException if SQL error occurs
     */
    public static final int executeUpdate(String resource,
            String command, Integer key)
        throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = ConnectionPool.getConnection(resource);
            statement = connection.prepareStatement(command);
            if (key!=null)
                statement.setInt(1, key.intValue());
            result = statement.executeUpdate();
        } // end try

        finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection!= null)
                    connection.close();
            }
            catch (SQLException sqle) {
                // non-fatal; only closing if pooled.
                // don't overthrow original exception
            }
        }
        return result;

    } // ---- End executeUpdate ----



    /**
     * Prepares statement using SQL statement and executes
     * with DBMS configured with the ConnectionPool.
     * <p>
     * Command may be an INSERT, UPDATE, or DELETE statement
     * or anything that returns nothing, such as a DDL
     * statement.
     * @param resource The database resource, or null for default
     * @param command The SQL statement to execute.
     * @param parameters An array of parameter objects
     * @exception SQLException if SQL error occurs
     */
    public static final int executeUpdate(String resource,
            String command, Object[] parameters)
        throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = ConnectionPool.getConnection(resource);
            statement = connection.prepareStatement(command);
            for (int i=0; i<parameters.length; i++) {
                statement.setObject(i, parameters[i]);
            }
            result = statement.executeUpdate();
        } // end try

        finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection!= null)
                    connection.close();
            }
            catch (SQLException sqle) {
                // non-fatal; only closing if pooled.
                // don't overthrow original exception
            }
        }
        return result;

    } // ---- End executeUpdate ----


    /**
     * Create a new database table in an existing database,
     * by sending "CREATE TABLE " and the parameters to
     * the DBMS configured with the ConnectionPool.
     * <p>
     * For safety, does <b>not</b> drop table first.
     * <p>
     * Returns false if a SQL exception is thrown; exception
     * is written to the servlet log.
     * <p>
     * @param tableName The name of the table to create
     * @param tableCreate The SQL command defining the
     * fields and indices
     * @return Result of statement.execute()
     * @exception SQL Exception if SQL error occurs
     */
    public static final int createTable(
            String resource, String tableName, String tableCreate)
            throws SQLException {

        return executeUpdate(resource,"CREATE TABLE " +
            tableName + " " + tableCreate);

    } // ---- End createTable ----


    /**
     * Prepares the given statement using key, and executes
     * it with DBMS configured with the ConnectionPool to return
     * a ResultSet. The ResultSet is transferred to a Collection
     * using the getCollection() method of the Object object.
     * The ResultSet is released, and the Collection returned.
     * <p>
     * @param key The replaceable parameter, if any.
     * @param command The SQL statement to prepare and execute.
     * @param target The Object class to create collection from ResultSet
     * @exception SQLException if SQL error occurs
     */
     public static final Collection getCollection(String resource,
                Object target, String command, Object[] parameters)
                throws SQLException {
            Object collection = null;
            Connection connection = null;
            Statement statement = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                connection = ConnectionPool.getConnection(resource);

                if ((parameters==null) || (parameters.length==0)) {
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(command);
                }
                else {
                    preparedStatement = connection.prepareStatement(command);
                    for (int i=0; i<parameters.length; i++) {
                        preparedStatement.setObject(i+1, parameters[i]);
                    }
                    resultSet = preparedStatement.executeQuery();
                }

                // ** Transfer ResultSet to Collection of target beans **
                // Worst case, will return an empty list
                collection = ResultSetUtils.getCollection(
                    target,resultSet);
            }
            finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection!= null) connection.close();
                }
                catch (SQLException sqle) {}
            }

            return (Collection) collection;

        } // ---- End getCollection ----


     public static final Collection getCollection(String resource,
                Object target, String command, Object key)
            throws SQLException {
        Object[] parameters = new Object[1];
        parameters[0] = key;
        return getCollection(resource,target,command,parameters);
     }


     public static final Collection getCollection(String resource,
                Object target, String command, int key)
            throws SQLException {
        Object[] parameters = new Object[1];
        parameters[0] = new Integer(key);
        return getCollection(resource,target,command,parameters);
     }


     public static final Collection getCollection(String resource,
                Object target, String command) throws SQLException {
            return getCollection(resource,target,command,null);
     }


    /**
     * Variation of <code>getCollection()</code> for commands
     * that use LIKE operator.
     * @param key The replaceable parameter to use with LIKE.
     * @param command The SQL statement to prepare and execute.
     * @param target The ResultList class to create list from ResultSet
     * @exception SQLException if SQL error occurs
     * @see getResultList
     */
     public static final Collection getCollectionLike(String resource,
                Object target, String command, String key) throws SQLException {
            Object[] parameters = new Object[1];
            parameters[0] = new String("%" + key + "%");
            return getCollection(resource,target,command,parameters);
     }


} // ---- End StatementUtils ---



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

