/*
 * $Id$ 
 *
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package org.apache.artimus.wizard.sql;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.apache.scaffold.sql.ConnectionPool;

import java.sql.Timestamp;


/**
 * SQL statements for the Article package.
 * @version $Rev$ $Date$
 */
public final class Statements {


    // ---- Article SQL Statements ----

        /**
         * Execute given command on entry in Wizard table
         * Called as library function by insert and update.
         * <p>
         * @return 0 if fails without an exception
         * @exception SQLException if SQL error occurs
         * @param article The primary key
         * @param contributor Name of contributor
         * @param creator Creator of article (may also be contributor)
         * @param Title Title for article
         * @param Content
         * @param command The SQL statement to execute
         * @exception SQLException on SQL error.
        **/
        public static final int execute (String command,
                 Integer id, Timestamp date, Float amount, Byte check,
                 String phone, String zip, String email, String text,
                 Integer wizard
            ) throws SQLException {

            Connection connection = null;
            PreparedStatement statement = null;
            int result = 0;
            try {
                connection = ConnectionPool.getConnection("WIZARD");
                statement = connection.prepareStatement(command);
                statement.setObject(1, id);
                statement.setObject(2, date);
                statement.setObject(3,amount);
                statement.setObject(4,check);
                statement.setObject(5,phone);
                statement.setObject(6,zip);
                statement.setObject(7,email);
                statement.setObject(8,text);
                if (wizard!=null)
                    statement.setObject(9,wizard);
                result = statement.executeUpdate();
            } // end try

            finally {
                try {
                    if (statement != null) statement.close();
                    if (connection!= null) connection.close();
                }
                catch (SQLException sqle) {;}
            }
            return result;
        }


// ---- End Statements ----

}
