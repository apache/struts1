/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/Attic/GenericConnection.java,v 1.2 2000/11/29 23:17:29 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2000/11/29 23:17:29 $
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


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Map;
import javax.sql.DataSource;


/**
 * Generic wrapper implementation of a <strong>Connection</strong> that
 * works with <code>GenericDataSource</code> to wrap connections for any
 * JDBC driver.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2000/11/29 23:17:29 $
 */

public class GenericConnection implements Connection {



    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new GenericConnection wrapping the specified connection.
     *
     * @param source The data source that owns this connection
     * @param conn The connection to wrap
     * @param autoCommit Desired auto-commit state for this connection
     * @param readOnly Desired read-only state for this connection
     *
     * @exception SQLException if an SQL processing error occurs
     */
    public GenericConnection(GenericDataSource source, Connection conn,
                             boolean autoCommit, boolean readOnly)
        throws SQLException {

        super();
        this.source = source;
        this.conn = conn;
        
        this.autoCommit = autoCommit;
        this.catalog = conn.getCatalog();
        this.level = conn.getTransactionIsolation();
        try {
            this.map = conn.getTypeMap();
        } catch (SQLException e) {
            ;   // PostgreSQL throws a "not yet implemented" exception
        } catch (UnsupportedOperationException e) {
            ;   // JDBC-ODBC bridge throws this
        }
        this.readOnly = readOnly;

        this.conn.setAutoCommit(this.autoCommit);
        this.conn.setReadOnly(this.readOnly);

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The initial auto-commit state to which we should return after release.
     */
    protected boolean autoCommit = false;


    /**
     * The initial catalog to which we should return after release.
     */
    protected String catalog = null;


    /**
     * The Connection that is being wrapped.
     */
    protected Connection conn = null;


    /**
     * The initial transaction isolation level to which we should return
     * after release.
     */
    protected int level = 0;


    /**
     * The initial type map to which we should return after release.
     */
    protected Map map = null;


    /**
     * The initial read-only state to which we should return after release.
     */
    protected boolean readOnly = false;


    /**
     * The GenericDataSource that owns this connection.
     */
    protected GenericDataSource source = null;


    // --------------------------------------------------------- Public Methods


    /**
     * Clear all warnings reported for this Connection.
     *
     * @exception SQLException if a database access error occurs
     */
    public void clearWarnings() throws SQLException {

        conn.clearWarnings();

    }


    /**
     * Return this wrapped Connection to our data source connection pool.
     *
     * @exception SQLException if a database access error occurs
     */
    public void close() throws SQLException {

        // Clean up any outstanding transaction as best we can
        try {
            conn.rollback();
        } catch (SQLException e) {
            ;
        }
        try {
            conn.setAutoCommit(this.autoCommit);
        } catch (SQLException e) {
            ;
        }
        try {
            conn.setCatalog(this.catalog);
        } catch (SQLException e) {
            ;
        }
        try {
            conn.setTransactionIsolation(this.level);
        } catch (SQLException e) {
            ;
        }
        try {
            conn.setTypeMap(this.map);
        } catch (SQLException e) {
            ;
        }
        try {
            conn.setReadOnly(this.readOnly);
        } catch (SQLException e) {
            ;
        }
        try {
            conn.clearWarnings();
        } catch (SQLException e) {
            ;
        }

        // Return this connection to the available connection pool
        source.returnConnection(this);

    }


    /**
     * Make all changes made since the previous commit or rollback
     * permanent, and releases any database locks currently held.
     *
     * @exception SQLException if a database access error occurs
     */
    public void commit() throws SQLException {

        conn.commit();

    }


    /**
     * Create a <code>Statement</code> for sending SQL statements to the
     * database.
     *
     * @exception SQLException if a database access error occurs
     */
    public Statement createStatement() throws SQLException {

        return (conn.createStatement());

    }



    /**
     * (JDBC 2.0) Create a Statement that will create a ResultSet of the
     * specified type and concurrency.
     *
     * @param resultSetType A result set type
     * @param resultSetConcurrency A result set concurrency
     *
     * @exception SQLException if a database access error occurs
     */
    public Statement createStatement(int resultSetType,
                                     int resultSetConcurrency)
        throws SQLException {

        return (conn.createStatement(resultSetType, resultSetConcurrency));

    }


    /**
     * Return the current auto-commit state.
     *
     * @exception SQLException if a database access error occurs
     */
    public boolean getAutoCommit() throws SQLException {

        return (conn.getAutoCommit());

    }


    /**
     * Return the current catalog name for this Connection.
     *
     * @exception SQLException if a database access error occurs
     */
    public String getCatalog() throws SQLException {

        return (conn.getCatalog());

    }


    /**
     * Get the metadata regarding this connection's database.
     *
     * @exception SQLException if a database access error occurs
     */
    public DatabaseMetaData getMetaData() throws SQLException {

        return (conn.getMetaData());

    }


    /**
     * Return this Connection's current transaction isolation level.
     *
     * @exception SQLException if a database access error occurs
     */
    public int getTransactionIsolation() throws SQLException {

        return (conn.getTransactionIsolation());

    }


    /**
     * (JDBC 2.0) Return the type map for this connection.
     *
     * @exception SQLException if a database access error occurs
     */
    public Map getTypeMap() throws SQLException {

        return (conn.getTypeMap());

    }


    /**
     * Return the first warning reported by calls to this Connection.
     *
     * @exception SQLException if a database access error occurs
     */
    public SQLWarning getWarnings() throws SQLException {

        return (conn.getWarnings());

    }


    /**
     * Return <code>true</code> if this Connection is closed.
     *
     * @exception SQLException if a database access error occurs
     */
    public boolean isClosed() throws SQLException {

        return (false); // FIXME - isClosed()

    }


    /**
     * Return <code>true</code> if this Connection is in read-only mode.
     *
     * @exception SQLException if a database access error occurs
     */
    public boolean isReadOnly() throws SQLException {

        return (conn.isReadOnly());

    }


    /**
     * Convert the given SQL statement into the system's native SQL grammer.
     *
     * @param sql Statement to be processed
     */
    public String nativeSQL(String sql) throws SQLException {

        return (conn.nativeSQL(sql));

    }


    /**
     * Create a <code>CallableStatement</code> object for calling database
     * stored procedures.
     *
     * @param sql Statement to be processed
     *
     * @exception SQLException if a database access error occurs
     */
    public CallableStatement prepareCall(String sql) throws SQLException {

        return (conn.prepareCall(sql));

    }


    /**
     * (JDBC 2.0) Create a CallableStatement object that will generate
     * ResultSet objects with the given type and concurrency.
     *
     * @param sql Statement to be processed
     * @param resultSetType A result set type
     * @param resultSetConcurrency A result set concurrency
     *
     * @exception SQLException if a database access error occurs
     */
    public CallableStatement prepareCall(String sql, int resultSetType,
                                         int resultSetConcurrency)
        throws SQLException {

        return (conn.prepareCall(sql, resultSetType, resultSetConcurrency));

    }


    /**
     * Create a <code>PreparedStatement</code> object for sending
     * parameterized SQL statements to the database.
     *
     * @param sql Statement to be processed
     *
     * @exception SQLException if a database access error occurs
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {

        return (conn.prepareStatement(sql));

    }


    /**
     * (JDBC 2.0) Create a PreparedStatement object that will generate
     * ResultSet objects with the given type and concurrency.
     *
     * @param sql Statement to be processed
     * @param resultSetType A result set type
     * @param resultSetConcurrency A result set concurrency
     *
     * @exception SQLException if a database access error occurs
     */
    public PreparedStatement prepareStatement(String sql, int resultSetType,
                                              int resultSetConcurrency)
        throws SQLException {

        return (conn.prepareStatement(sql, resultSetType,
                                      resultSetConcurrency));

    }


    /**
     * Drop all changes made since the previous commit or rollback.
     *
     * @exception SQLException if a database access error occurs
     */
    public void rollback() throws SQLException {

        conn.rollback();

    }


    /**
     * Sets this connection's auto-commit mode.
     *
     * @param autoCommit The new auto-commit mode.
     *
     * @exception SQLException if a database access error occurs
     */
    public void setAutoCommit(boolean autoCommit) throws SQLException {

        conn.setAutoCommit(autoCommit);

    }


    /**
     * Set the catalog name for this Connection.
     *
     * @param catalog The new catalog name
     *
     * @exception SQLException if a database access error occurs
     */
    public void setCatalog(String catalog) throws SQLException {

        conn.setCatalog(catalog);

    }


    /**
     * Set the read-only mode of this connection.
     *
     * @param readOnly The new read-only mode
     *
     * @exception SQLException if a database access error occurs
     */
    public void setReadOnly(boolean readOnly) throws SQLException {

        conn.setReadOnly(readOnly);

    }


    /**
     * Set the transaction isolation level for this Connection.
     *
     * @param level The new transaction isolation level
     *
     * @exception SQLException if a database access error occurs
     */
    public void setTransactionIsolation(int level) throws SQLException {

        conn.setTransactionIsolation(level);

    }


    /**
     * (JDBC 2.0) Set the type map for this connection.
     *
     * @param map The new type map
     *
     * @exception SQLException if a database access error occurs
     */
    public void setTypeMap(Map map) throws SQLException {

        conn.setTypeMap(map);

    }


    // -------------------------------------------------------- Package Methods


    /**
     * Return the actual connection that we are wrapping.
     */
    Connection getConnection() {

        return (this.conn);

    }


    /**
     * Return the data source that owns this connection.
     */
    DataSource getDataSource() {

        return (this.source);

    }


}
