package org.apache.scaffold.sql;


/**
 * Starter class to access a datasource connection pool.
 * :TODO: At some point, this will have to be refactored
 * so that another class using a different implemenation
 * could be loaded here instead.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 11:53:12 $
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