/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/Attic/GenericDataSource.java,v 1.14 2002/07/20 17:41:17 craigmcc Exp $
 * $Revision: 1.14 $
 * $Date: 2002/07/20 17:41:17 $
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


package org.apache.struts.util;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Wrapper around a <code>org.apache.commons.dbcp.BasicDataSource</code>
 * object that isolates Struts from compile-time dependencies on the
 * <code>java.sql.Connection</code> class, which changed between JDK 1.3 and
 * JDK 1.4.</p>
 *
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @version $Revision: 1.14 $ $Date: 2002/07/20 17:41:17 $
 * @deprecated Use a <code>BasicDataSource</code> directly, or indirectly
 *  acquire a data source provided by your container
 */

public class GenericDataSource implements DataSource {


    // ----------------------------------------------------- Instance Variables


    /**
     * Commons DBCP DataSource instance.
     */
    protected BasicDataSource dataSource = null;


    /**
     * Commons Logging instance.
     */
    protected static Log log = LogFactory.getLog(GenericDataSource.class);


    // ------------------------------------------------------------- Properties


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
    protected int maxCount = 8;

    public int getMaxCount() {
        return (this.maxCount);
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }


    /**
     * The maximum number of idle connections allowed before starting
     * to release them, or zero for no limit.
     */
    protected int maxIdle = 8;

    public int getMaxIdle() {
        return (this.maxIdle);
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }


    /**
     * The maximum number of milliseconds that the pool will wait (when there
     * are no available connections) for a connection to be returned before
     * throwing an exception, or -1 to wait indefinitely.
     */
    protected long maxWait = -1;

    public long getMaxWait() {
        return (this.maxWait);
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
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
     * The database username for use in establishing a connection.
     */
    protected String user = null;

    public String getUser() {
        return (this.user);
    }

    public void setUser(String user) {
        this.user = user;
    }


    // ----------------------------------------------------- DataSource Methods


    /**
     * Attempt to establish a database connection.
     *
     * @exception SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {

        return (createDataSource().getConnection());

    }


    /**
     * Attempt to establish a database connection.
     *
     * @param username Database username for this connection
     * @param password Database password for this connection
     *
     * @exception SQLException if a database access error occurs
     */
    public Connection getConnection(String username, String password)
        throws SQLException {

        return (createDataSource().getConnection(username, password));

    }


    /**
     * Return the login timeout for this data source.
     *
     * @exception SQLException if a database access error occurs
     */
    public int getLoginTimeout() throws SQLException {

        return (createDataSource().getLoginTimeout());

    }



    /**
     * Return the log writer for this data source.
     *
     * @exception SQLException if a database access error occurs
     */
    public PrintWriter getLogWriter() throws SQLException {

        return (createDataSource().getLogWriter());

    }


    /**
     * Set the login timeout for this data source.
     *
     * @param loginTimeout The new login timeout
     *
     * @exception SQLException if a database access error occurs
     */
    public void setLoginTimeout(int loginTimeout) throws SQLException {

        createDataSource().setLoginTimeout(loginTimeout);

    }


    /**
     * Set the log writer for this data source.
     *
     * @param logWriter The new log writer
     *
     * @exception SQLException if a database access error occurs
     */
    public void setLogWriter(PrintWriter logWriter) throws SQLException {

        createDataSource().setLogWriter(logWriter);

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Close all opened connections managed by this data source.
     *
     * @exception SQLException if a database access error occurs
     */
    public void close() throws SQLException {

        createDataSource().close();

    }


    /**
     * Open this data source implementation.
     *
     * @exception SQLException if a database access error occurs
     */
    public void open() throws SQLException {

        createDataSource();

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Create (if necessary) and return the actual <code>DataSource</code>
     * implementation that we are wrapping.
     *
     * @exception SQLException if a database access error occurs
     */
    protected synchronized BasicDataSource createDataSource()
        throws SQLException {

        if (dataSource != null) {
            return (dataSource);
        }

        if (log.isDebugEnabled()) {
            log.debug("Creating new BasicDataSource");
        }
        if (log.isTraceEnabled()) {
            log.trace("   autoCommit=" + getAutoCommit());
            log.trace("  driverClass=" + getDriverClass());
            log.trace("     maxCount=" + getMaxCount());
            log.trace("      maxIdle=" + getMaxIdle());
            log.trace("      maxWait=" + getMaxWait());
            log.trace("     password=" + getPassword());
            log.trace("    pingQuery=" + getPingQuery());
            log.trace("     readOnly=" + getReadOnly());
            log.trace("          url=" + getUrl());
            log.trace("         user=" + getUser());
        }
        BasicDataSource bds = new BasicDataSource();

        bds.setDefaultAutoCommit(getAutoCommit());
        bds.setDefaultReadOnly(getReadOnly());
        bds.setDriverClassName(getDriverClass());
        bds.setMaxActive(getMaxCount());
        bds.setMaxIdle(getMaxIdle());
        bds.setMaxWait(getMaxWait());
        bds.setPassword(getPassword());
        bds.setUrl(getUrl());
        bds.setUsername(getUser());
        bds.setValidationQuery(getPingQuery());

        dataSource = bds;
        return (dataSource);

    }


}
