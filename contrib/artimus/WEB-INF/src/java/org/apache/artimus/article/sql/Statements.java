/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/article/sql/Statements.java,v 1.3 2004/03/14 07:15:04 sraeburn Exp $
 * $Revision: 1.3 $
 * $Date: 2004/03/14 07:15:04 $
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
 

package org.apache.artimus.article.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.scaffold.sql.ConnectionPool;


/**
 * SQL statements for the Article package.
 * @version $Revision: 1.3 $ $Date: 2004/03/14 07:15:04 $
 */
public final class Statements {


    // ---- Article SQL Statements ----

        /**
         * Execute given command on entry in Article table
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
        public static final int execute (
                String command,
                Integer article, Timestamp contributed, String contributor,
                String creator, String title, String content
            ) throws SQLException {

            Connection connection = null;
            PreparedStatement statement = null;
            int result = 0;
            try {
                connection = ConnectionPool.getConnection();
                statement = connection.prepareStatement(command);
                    statement.setObject(1, contributed);
                    statement.setObject(2, contributor);
                    statement.setObject(3, creator);
                    statement.setObject(4, title);
                    statement.setObject(5, content);
                    statement.setObject(6, article);
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