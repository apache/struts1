package org.apache.scaffold.sql;


/**
 * Starter class to access a datasource connection pool.
 * :TODO: At some point, this will have to be refactored
 * so that another class using a different implemenation
 * could be loaded here instead.
 * @author Ted Husted
 * @version $Revision: 1.2 $ $Date: 2002/03/05 02:29:56 $
 */

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.codestudio.sql.PoolMan;


public final class ConnectionPool {


    /**
     * An exception message to throw if datasource is null.
     */
    public static final String RESOURCE_ERROR = "Connection pool " +
        "not available. Check your poolman.xml config, and be sure " +
        "you are using a valid dbname parameter (use dbname, not jndiName)";


    /**
     * The application scope attribute under which our datasource
     * is stored.
     */
    public static final String RESOURCE_KEY = "DATASOURCE";


    /**
     * Returns a JDBC connection from a connection pool or other
     * resource, to be used and closed promptly.
     * <p>
     * @returns JDBC connection from resource layer.
     * @exception SQLException on SQL or other errors. May wrap other
     * exceptions depending on implementation. Will not return null.
     */
    public static final Connection getConnection() throws SQLException {
        return getConnection(RESOURCE_KEY);
    }


    /**
     * Returns a JDBC connection from a connection pool or other
     * resource, to be used and closed promptly.
     * <p>
     * @returns JDBC connection from resource layer.
     * @exception SQLException on SQL or other errors. May wrap other
     * exceptions depending on implementation. Will not return null.
     */
    public static final Connection getConnection(String resource) throws SQLException {
        DataSource ds = null;
        if (resource==null)
            ds = PoolMan.findDataSource(RESOURCE_KEY);
        else
            ds = PoolMan.findDataSource(resource);
        if (ds==null)
            throw new SQLException(RESOURCE_ERROR);
        return(ds.getConnection());
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
**/
