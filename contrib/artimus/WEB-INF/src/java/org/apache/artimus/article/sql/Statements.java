package org.apache.artimus.article.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.scaffold.sql.ConnectionPool;


/**
 * SQL statements for the Article package.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:13 $
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
