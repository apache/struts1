package org.apache.artimus.keys.sql;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.scaffold.sql.ConnectionPool;


/**
 * SQL statements for the Keys package.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:13 $
 */
public final class Statements {


    /**
     * Returns next sequential key for given table, without
     * allocating a key.
     * <p>
     * @return The next int key value to be inserted
     * @exception SQLException if SQL error occurs
     */
    public static final synchronized int peekKey(String keyName)
            throws SQLException {
       int next = 0;
       int result = 0;
       Connection connection = null;
       PreparedStatement statement = null;
       ResultSet resultSet = null;
       try {
           connection = ConnectionPool.getConnection();
           statement =
                connection.prepareStatement(Commands.KEYS_NEXT);
           statement.setString(1,keyName);
           resultSet = statement.executeQuery();
           if (resultSet.next()) {
                next = resultSet.getInt(1);
          }
        }

        finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection!= null) connection.close();
            }
            catch (SQLException sqle) {}
        }
        return next;

    } // ---- End getKeyNext ----


    /**
     * Returns next sequential key for given table.
     * <p>
     * This ensures compatibility for DBMS products that do not
     * support auto-incrementing a key field.
     * <p>
     * Intended to generate primary keys, but could be used to
     * create other serial numbers based on an unsigned int.
     * <p>
     * Allocating the key involves reading the current key, and
     * then incrementing the key for the next user. The method
     * is synchronized so that two threads do not read the
     * same key before it is increment.
     * <p>
     * @return The unsigned int key value to be inserted
     * @exception SQLException if SQL error occurs
     * @param keyName The name of the table for this key
     */
    public static final synchronized Integer allocateKey(String keyName)
            throws SQLException {
       Integer next = null;
       int result = 0;
       Connection connection = null;
       PreparedStatement statement = null;
       ResultSet resultSet = null;
       try {
           connection = ConnectionPool.getConnection();
           statement =
                connection.prepareStatement(Commands.KEYS_NEXT);
           statement.setString(1,keyName);
           resultSet = statement.executeQuery();
           if (resultSet.next()) {
                next = new Integer(resultSet.getInt(1));
                statement = connection.prepareStatement(
                    Commands.KEYS_INC);
                statement.setString(1,keyName);
                result = statement.executeUpdate();
          }
        }

        finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection!= null) connection.close();
            }
            catch (SQLException sqle) {}
        }
        return next;

    } // ---- End getKeyNext ----

}
