/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/Attic/GenericDataSource.java,v 1.9 2002/02/26 05:21:07 dwinterfeldt Exp $
 * $Revision: 1.9 $
 * $Date: 2002/02/26 05:21:07 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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


package org.apache.struts.util;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogSource;


/**
 * <p>Generic data source implementation of the <code>DataSource</code>
 * interface.  <b>WARNING</b> - This implementation does not know how to
 * provide connections with different username/password combinations.
 * Calling this version of the implementation using the
 * getConnection(username,password) signature will throw an exception.</p>
 *
 * <p>The following properties are supported by the standard
 * <code>GenericDataSource</code> implementation:</p>
 * <table>
 * <tr>
 *   <th width="15%">Property</th>
 *   <th width="85%">Description</th>
 * </tr>
 * <tr>
 *   <td align="center">autoCommit</td>
 *   <td>Set to <code>true</code> if you want the connections returned to you
 *       by calling <code>getConnection()</code> to be configured in
 *       "auto-commit after every statement" mode.  The default value is
 *       <code>true</code>, to conform to JDBC standard conventions.</td>
 * </tr>
 * <tr>
 *   <td align="center">description</td>
 *   <td>A short textual description of this data source.  This property is
 *       required by the <code>javax.sql.DataSource</code> interface, but is
 *       not used within this implementation.</td>
 * </tr>
 * <tr>
 *   <td align="center">driverClass</td>
 *   <td>The fully qualified class name of the JDBC driver to be utilized for
 *       the connections created by this data source.  Consult the
 *       documentation for your JDBC driver to identify the value to be
 *       configured for this property.</td>
 * </tr>
 * <tr>
 *   <td align="center">maxCount</td>
 *   <td>The maximum number of JDBC connections that will be created by this
 *       data source.  This value must be greater than or equal to the value
 *       specified for the <code>minCount</count> property.</td>
 * </tr>
 * <tr>
 *   <td align="center">minCount</td>
 *   <td>The minimum number of JDBC connections to establish when this data
 *       source is first opened.  This value must be less than or equal to the
 *       value specified for the <code>maxCount</code> property.</td>
 * </tr>
 * <tr>
 *   <td align="center">password</td>
 *   <td>The database password used to establish the connections created by
 *       this connection pool, in conjunction with the username specified in
 *       the <code>user</code> property.</td>
 * </tr>
 * <tr>
 *   <td align="center">pingCommand</td>
 *   <td>A non-query SQL command that, if specified, will be executed before
 *       a connection is returned by a call to <code>getConnection()</code>.
 *       If any SQLException is thrown by the execution of this statement,
 *       it is assumed that this connection is stale and it will be discarded.
 *       Because this happens on every connection allocation, you should ensure
 *       that the statement executes very quickly.</td>
 * </tr>
 * <tr>
 *   <td align="center">pingQuery</td>
 *   <td>A query SQL command (i.e. a SELECT) that, if specified, will be
 *       executed before a connection is returned by a call to
 *       <code>getConnection()</code>.  If any SQLException is thrown by the
 *       execution of this query (or by the subsequent processing of the
 *       entire returned <code>ResultSet</code>), it is assumed that this
 *       connection is stale and it will be discarded.  Because this happens
 *       on every connection allocation, you should ensure that the
 *       statement executes very quickly.</td>
 * </tr>
 * <tr>
 *   <td align="center">readOnly</td>
 *   <td>Set to <code>true</code> if you want the connections returned to you
 *       by calling <code>getConnection()</code> to be configured for read only
 *       operations.  This can result in more efficient database access,
 *       because the database will know it does not need to retain undo logs
 *       for rolling back the transaction.  The default value is
 *       <code>false</code>.</td>
 * </tr>
 * <tr>
 *   <td align="center">url</td>
 *   <td>The connection URL to be passed to our JDBC driver when establishing
 *       a new connection.  The value specified typically starts with
 *       <code>jdbc:</code>, and includes a reference to the host (and,
 *       optionally, the port number) at which the database server is listening
 *       for connections, plus the name of the database to be opened.  Consult
 *       the documentation for your JDBC driver to identify the value to be
 *       configured for this property.</td>
 * </tr>
 * <tr>
 *   <td align="center">user</td>
 *   <td>The database username used to establish the connections created by
 *       this connection pool, in conjunction with the password specified in
 *       the <code>password</code> property.</td>
 * </tr>
 * </table>
 *
 * <p>In addition, you can add to the set of <code>Properties</code> passed to
 * the JDBC driver by calling <code>addProperty()</code>.</p>
 *
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @version $Revision: 1.9 $ $Date: 2002/02/26 05:21:07 $
 */

public class GenericDataSource implements DataSource {


    // ----------------------------------------------------- Instance Constants

    /**
     * Commons Logging instance.
    */
    private Log log = LogSource.getInstance(this.getClass().getName());


    private static final String SQLEXCEPTION_GETCONNECTION =
     "getConnection(String username, String password)  Method not supported. Use getConnection() instead.";



    // ----------------------------------------------------- Instance Variables


    /**
     * Has this data source been closed?
     */
    protected boolean closed = false;


    /**
     * The list of Connections (wrapped in our associated wrapper class) that
     * have been created but are not currently in use.
     */
    protected LinkedList connections = new LinkedList();


    /**
     * The JDBC driver that we use as a connection factory.
     */
    protected Driver driver = null;


    /**
     * The login timeout for this data source.
     */
    protected int loginTimeout = 0;


    /**
     * The log writer for this data source.
     */
    protected PrintWriter logWriter = null;


    // ------------------------------------------------------------- Properties


    /**
     * Add a generic property to the list of connection properties to be used.
     *
     * @param name Name of the generic property
     * @param value Corresponding generic property value
     */
    public void addProperty(String name, String value) {
        properties.put(name, value);
    }


    /**
     * The number of connections that have been created by this data source.
     */
    protected int activeCount = 0;

    public int getActiveCount() {
        return (this.activeCount);
    }


    /**
     * The default auto-commit state for newly created connections.
     */
    protected boolean autoCommit = true;

    public boolean getAutoCommit() {
        return (this.autoCommit);
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }


    /**
     * The debugging detail level for this data source.
     */
    protected int debug = 0;

    public int getDebug() {
        return (this.debug);
    }

    public void setDebug(int debug) {
        this.debug = debug;
    }


    /**
     * The description of this data source.
     */
    protected String description = null;

    public String getDescription() {
        return (this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * The Java class name of the JDBC driver to use.
     */
    protected String driverClass = null;

    public String getDriverClass() {
        return (this.driverClass);
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }


    /**
     * The maximum number of connections to be created.
     */
    protected int maxCount = 2;

    public int getMaxCount() {
        return (this.maxCount);
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }


    /**
     * The minimum number of connections to be created.
     */
    protected int minCount = 1;

    public int getMinCount() {
        return (this.minCount);
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }


    /**
     * The database password for use in establishing a connection.
     */
    protected String password = null;

    public String getPassword() {
        return (this.password);
    }

    public void setPassword(String password) {
        this.password = password;
        addProperty("password", this.password);
    }



    /**
     * The non-query SQL command used to ping an allocated connection.
     */
    protected String pingCommand = null;

    public String getPingCommand() {
        return (this.pingCommand);
    }

    public void setPingCommand(String pingCommand) {
        this.pingCommand = pingCommand;
    }


    /**
     * The query SQL command used to ping an allocated connection.
     */
    protected String pingQuery = null;

    public String getPingQuery() {
        return (this.pingQuery);
    }

    public void setPingQuery(String pingQuery) {
        this.pingQuery = pingQuery;
    }


    /**
     * The connection properties for use in establishing connections.
     */
    protected Properties properties = new Properties();


    /**
     * The default read-only state for newly created connections.
     */
    protected boolean readOnly = false;

    public boolean getReadOnly() {
        return (this.readOnly);
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }


    /**
     * The JDBC URL for the database connection to be opened.
     */
    protected String url = null;

    public String getUrl() {
        return (this.url);
    }

    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * The number of connections created by this data source that are
     * currently in use.
     */
    protected int useCount = 0;

    public int getUseCount() {
        return (this.useCount);
    }


    /**
     * The database username for use in establishing a connection.
     */
    protected String user = null;

    public String getUser() {
        return (this.user);
    }

    public void setUser(String user) {
        this.user = user;
        addProperty("user", this.user);
    }


    // ----------------------------------------------------- DataSource Methods


    /**
     * Attempt to establish a database connection.
     *
     * @exception SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {

        int seconds = 0;
        if (log.isInfoEnabled()) {
            log.info("  getConnection()");
        }

        // Validate the opened status of this data source
        if (closed) {
            throw new SQLException("getConnection:  Data source is closed");
        }
        if (driver == null) {
            open();
        }

        while (true) {

            // Have we timed out yet?
            if (log.isInfoEnabled()) {
                log.info("   Check for timeout, activeCount=" + activeCount +
                    ", useCount=" + useCount);
            }
            if ((loginTimeout > 0) && (seconds >= loginTimeout)) {
                break;
            }

            // Return an existing connection from the pool if there is one
            synchronized (connections) {
                if (!connections.isEmpty()) {

                    // Allocate the first available connection
                    GenericConnection connection =
                        (GenericConnection) connections.removeFirst();
                    if (log.isInfoEnabled()) {
                        log.info("   Found available connection");
                    }

                    // Make sure this connection is not stale
                    connection.setClosed(false);
                    try {
                        ping(connection);
                    } catch (SQLException e) {
                        log.warn("   Connection stale, releasing");
                        try {
                            connection.getConnection().close();
                        } catch (SQLException f) {
                            ;
                        }
                        activeCount--;
                        continue;
                    }

                    // unclose the connection's wrapper and return it
                    useCount++;
                    if (log.isInfoEnabled()) {
                        log.info("   Return allocated connection, activeCount=" +
                                 activeCount + ", useCount=" + useCount);
                    }
                    
                    return(connection);

                }
            }

            // Create a new connection if we are not yet at the maximum
            if (activeCount < maxCount) {
                Connection connection = createConnection();
                if (connection != null) {
                    try {
                        ping(connection);
                    } catch (SQLException e) {
                        throw e;
                    }
                    useCount++;
                    if (log.isInfoEnabled()) {
                        log.info("   Return new connection, activeCount=" +
                                 activeCount + ", useCount=" + useCount);
                    }
                    
                    return (connection);
                }
            }

            // Wait for an existing connection to be returned
            if (log.isInfoEnabled()) {
                log.info("   Sleep until next test");
            }
            try {
                Thread.sleep(1000);
                seconds++;
            } catch (InterruptedException e) {
                ;
            }

        }

        // We have timed out awaiting an available connection
        if (log.isInfoEnabled()) {
            log.info("   Timeout awaiting connection");
        }
        throw new SQLException
            ("getConnection: Timeout awaiting connection");

    }


    /**
     * Attempt to establish a database connection.  <b>WARNING</b> - The
     * specified username and password are not supported by this
     * implementation.
     *
     * @param username Database username for this connection
     * @param password Database password for this connection
     *
     * @exception SQLException if a database access error occurs
     */
    public Connection getConnection(String username, String password)
        throws SQLException {

        throw new SQLException(SQLEXCEPTION_GETCONNECTION); // Not implemented

    }


    /**
     * Return the login timeout for this data source.
     *
     * @exception SQLException if a database access error occurs
     */
    public int getLoginTimeout() throws SQLException {

        return (this.loginTimeout);

    }



    /**
     * Return the log writer for this data source.
     *
     * @exception SQLException if a database access error occurs
     */
    public PrintWriter getLogWriter() throws SQLException {

        return (this.logWriter);

    }


    /**
     * Set the login timeout for this data source.
     *
     * @param loginTimeout The new login timeout
     *
     * @exception SQLException if a database access error occurs
     */
    public void setLoginTimeout(int loginTimeout) throws SQLException {

        this.loginTimeout = loginTimeout;

    }


    /**
     * Set the log writer for this data source.
     *
     * @param logWriter The new log writer
     *
     * @exception SQLException if a database access error occurs
     */
    public void setLogWriter(PrintWriter logWriter) throws SQLException {

        this.logWriter = logWriter;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Close all connections that have been created by this data source.
     *
     * @exception SQLException if a database access error occurs
     */
    public void close() throws SQLException {

        if (closed)
            throw new SQLException("close:  Data Source already closed");
        if (log.isDebugEnabled()) {
            log.debug(" close()");
        }

        // Shut down all active connections
        while (activeCount > 0) {
            GenericConnection conn = (GenericConnection) getConnection();
            conn.getConnection().close();
            activeCount--;
        }

        // Mark this data source as having been closed and release our driver
        closed = true;
        driver = null;

    }


    /**
     * Open the initial connections that are appropriate for this data source.
     *
     * @exception SQLException if a database access error occurs
     */
    public void open() throws SQLException {

        // Have we already been opened?
        if (driver != null)
            return;
        if (log.isDebugEnabled()) {
            log.debug(" open()");
        }

        // Instantiate our database driver
        try {
            Class clazz = Class.forName(driverClass);
            driver = (Driver) clazz.newInstance();
        } catch (Throwable t) {
            throw new SQLException("open: " + t);
        }

        // Create the initial minimum number of required connections
        synchronized (connections) {
            for (int i = 0; i < minCount; i++) {
                connections.addLast(createConnection());
            }
        }

        closed = false;

    }


    /**
     * Return a string representation of this component.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("GenericDataSource[");
        sb.append("activeCount=");
        sb.append(activeCount);
        sb.append(", autoCommit=");
        sb.append(autoCommit);
        sb.append(", closed=");
        sb.append(closed);
        if (description != null) {
            sb.append(", description=");
            sb.append(description);
        }
        sb.append(", driverClass=");
        sb.append(driverClass);
        sb.append(", loginTimeout=");
        sb.append(loginTimeout);
        sb.append(", maxCount=");
        sb.append(maxCount);
        sb.append(", minCount=");
        sb.append(minCount);
        sb.append(", password=");
        sb.append(password);
        sb.append(", readOnly=");
        sb.append(readOnly);
        sb.append(", url=");
        sb.append(url);
        sb.append(", useCount=");
        sb.append(useCount);
        sb.append(", user=");
        sb.append(user);
        sb.append("]");
        return (sb.toString());

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Create, configure, and return a new JDBC Connection that has been
     * wrapped in our corresponding wrapper.
     *
     * @exception SQLException if a database access error occurs
     */
    protected synchronized Connection createConnection() throws SQLException {

        if (activeCount < maxCount) {
            if (log.isInfoEnabled()) {
                log.info("   createConnection()");
            }
            Connection conn = driver.connect(url, properties);
            activeCount++;
            return (new GenericConnection(this, conn, autoCommit, readOnly));
        }
        
        log.error("   createConnection() returning null");
        
        return (null);

    }


    /**
     * Log the specified message to our log writer, if we have one.
     *
     * @param message The message to be logged
     */
    protected void log(String message) {

        if (logWriter != null) {
            logWriter.print("GenericDataSource[");
            logWriter.print(description);
            logWriter.print("]: ");
            logWriter.println(message);
        }

    }


    /**
     * Log the specified message and exception to our log writer, if we
     * have one.
     *
     * @param message The message to be logged
     * @param throwable The exception to be logged
     */
    protected void log(String message, Throwable throwable) {

        if (logWriter != null) {
            logWriter.print("GenericDataSource[");
            logWriter.print(description);
            logWriter.print("]: ");
            logWriter.println(message);
            throwable.printStackTrace(logWriter);
        }

    }


    /**
     * Perform any configured <code>pingCommand</code> and/or
     * <code>pingQuery</code> on the specified connection, returning any
     * SQLException that is encountered along the way.
     *
     * @param conn The connection to be pinged
     */
    protected void ping(Connection conn) throws SQLException {

        if (pingCommand != null) {

            if (log.isDebugEnabled()) {
                log.debug("    ping(" + pingCommand + ")");
            }

            Statement stmt = conn.createStatement();
            try {
                stmt.execute(pingCommand);
                stmt.close();
            } catch (SQLException e) {
                log.warn("ping failed:  " + e.getMessage(), e);

                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException f) {
                    ;
                }
                throw e;
            }

        }

        if (pingQuery != null) {

            if (log.isDebugEnabled()) {
                log.debug("    ping(" + pingQuery + ")");
            }

            ResultSet rs = null;
            Statement stmt = conn.createStatement();
            try {
                rs = stmt.executeQuery(pingQuery);
                while (rs.next()) {
                    ;
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                log.warn("ping failed: " + e.getMessage(), e);
                
                try {
                    if (rs != null)
                        rs.close();
                } catch (SQLException f) {
                    ;
                }
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException f) {
                    ;
                }
                throw e;
            }

        }

    }


    // -------------------------------------------------------- Package Methods


    /**
     * Return this connection to the available connection pool.
     *
     * @param conn The connection being returned
     */
    void returnConnection(GenericConnection conn) {

        if (log.isInfoEnabled()) {
            log.info("  releaseConnection(), activeCount=" + activeCount +
                ", useCount=" + (useCount - 1));
        }

        synchronized (connections) {
            connections.addLast(conn);
            useCount--;
        }

    }


}
