/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/DataSourceConfig.java,v 1.1 2001/12/26 19:16:25 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2001/12/26 19:16:25 $
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


package org.apache.struts.config;


import org.apache.struts.action.Action;


/**
 * <p>A JavaBean representing the configuration information of a
 * <code>&lt;data-source&gt;</code> element from a Struts application
 * configuration file.</p>
 *
 * <p><strong>WARNING</strong> - The properties of this configuration bean
 * are recognized by the default data source implementation, but some or all
 * of them may be ignored by custom data source implementations.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2001/12/26 19:16:25 $
 * @since Struts 1.1
 */

public class DataSourceConfig {



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
     * The fully qualified Java class name of the JDBC driver to be used.
     */
    protected String driverClass = null;

    public String getDriverClass() {
        return (this.driverClass);
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }


    /**
     * The servlet context attribute key under which this data source
     * is stored and made available.
     */
    protected String key = Action.DATA_SOURCE_KEY;

    public String getKey() {
        return (this.key);
    }

    public void setKey(String key) {
        this.key = key;
    }


    /**
     * The maximum number of seconds to wait for a connection to be created
     * or returned, or zero for no timeout.
     */
    protected int loginTimeout = 0;

    public int getLoginTimeout() {
        return (this.loginTimeout);
    }

    public void setLoginTimeout(int loginTimeout) {
        this.loginTimeout = loginTimeout;
    }


    /**
     * The maximum number of connections to be created, or zero for no limit.
     */
    protected int maxCount = 0;

    public int getMaxCount() {
        return (this.maxCount);
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }


    /**
     * The minimum number of connections to be created, or zero for no limit.
     */
    protected int minCount = 0;

    public int getMinCount() {
        return (this.minCount);
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }


    /**
     * The database password to use when connecting.
     */
    protected String password = null;

    public String getPassword() {
        return (this.password);
    }

    public void setPassword(String password) {
        this.password = password;
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
     * The JDBC URL of the database to connect to.
     */
    protected String url = null;

    public String getUrl() {
        return (this.url);
    }

    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * The database username to use when connecting.
     */
    protected String user = null;

    public String getUser() {
        return (this.user);
    }

    public void setUser(String user) {
        this.user = user;
    }


    // --------------------------------------------------------- Public Methods

    /**
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("DataSourceConfig[");
        sb.append("autoCommit=");
        sb.append(this.autoCommit);
        if (this.description != null) {
            sb.append(",description=");
            sb.append(this.description);
        }
        sb.append(",driverClass=");
        sb.append(this.driverClass);
        if (this.loginTimeout != 0) {
            sb.append(",loginTimeout=");
            sb.append(this.loginTimeout);
        }
        if (this.maxCount != 0) {
            sb.append(",maxCount=");
            sb.append(this.maxCount);
        }
        if (this.minCount != 0) {
            sb.append(",minCount=");
            sb.append(this.minCount);
        }
        sb.append("password=");
        sb.append(this.password);
        sb.append(",readOnly=");
        sb.append(this.readOnly);
        sb.append(",url=");
        sb.append(this.url);
        sb.append(",user=");
        sb.append(this.user);
        sb.append("]");
        return (sb.toString());

    }


}
