/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/keys/sql/Commands.java,v 1.3 2004/03/14 07:15:04 sraeburn Exp $
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
 

package org.apache.artimus.keys.sql;


/**
 * SQL command constants for the Articles application.
 * <p>
 * @version $Revision: 1.3 $ $Date: 2004/03/14 07:15:04 $
 */
public final class Commands {


    // ---- KEYS ----

    /**
     * Default starting value for primary keys.
     */
    public static final String PRIMARY_KEY_INIT = "101";


    /**
     * Name for KEYS table
     */
    public static final String KEYS_TABLE = "artimus_keys ";


    /**
     * DDL for KEYS TABLE
     */
    public static final String KEYS_CREATE =
      "(name char(31) NOT NULL PRIMARY KEY," +
       "marked bit NOT NULL DEFAULT 0," +
       "next integer NOT NULL DEFAULT 0" +
    ");";


    /**
     * SQL command to select next key.
     */
    public static final String KEYS_NEXT = "SELECT next FROM " +
        KEYS_TABLE + "WHERE name=?";


    /**
     * SQL command to select next key.
     */
    public static final String KEYS_INC = "UPDATE " +
        KEYS_TABLE +"SET next=next+1 WHERE name=?";


// ---- End Commands ----

}
