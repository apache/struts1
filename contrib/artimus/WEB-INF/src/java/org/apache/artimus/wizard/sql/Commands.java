/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/wizard/sql/Commands.java,v 1.3 2004/03/14 07:15:07 sraeburn Exp $
 * $Revision: 1.3 $
 * $Date: 2004/03/14 07:15:07 $
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


/**
 * SQL command constants and custom statements
 * for the Article package.
 * @version $Revision: 1.3 $ $Date: 2004/03/14 07:15:07 $
 */
public final class Commands {


    // ---- Article SQL Statements ----


    /**
     * Name for WIZARD table
     */
    public static final String WIZARD_TABLE = "wizard ";


    /**
     * DDL for WIZARD table
     */
    public static final String WIZARD_CREATE =
    "`wizard` ( " +
        "`wizard` int(11) NOT NULL auto_increment," +
        "`id` int(10) unsigned," +
        "`date` timestamp(14)," +
        "`amount` decimal(9,2)," +
        "`check` tinyint(1)," +
        "`phone` varchar(15)," +
        "`zip` varchar(15)," +
        "`email`  varchar(63)," +
        "`text` varchar(255)," +
        "PRIMARY KEY (`wizard`)" +
        ");";


    // ---- insert ----


    /**
     * Command to insert default properties into WIZARD table
     * Primary key comes last to match update command.
     */
    public static final String WIZARD_INSERT = "INSERT INTO "
        + WIZARD_TABLE +
        "(id,date,amount,check,phone,zip,email,text,wizard) " +
        "VALUES (?,?,?,?,?,?,?,?,?)";


    // ---- update ----


    /**
     * Command to update default properties in WIZARD table by primary key
     */
    public static final String WIZARD_UPDATE = "UPDATE " +
        WIZARD_TABLE + "SET " +
        "id=?,date=?,amount=?,check=?,phone=?,zip=?,email=?,text=? " +
        "WHERE id=?;";


    // ---- select ----


    /**
     * Base command to select default properties from WIZARD table
     */
    public static final String WIZARD_SELECT_BASE = "SELECT " +
        "id,date,amount,check,phone,zip,email,text " +
        "FROM " + WIZARD_TABLE;


    /**
     * Command to select default properties from WIZARD table by primary key
     */
    public static final String WIZARD_SELECT_KEY = WIZARD_SELECT_BASE +
        "WHERE wizard=?;";



    // ---- search ----


    /**
     * Base command to select search list from WIZARD table
     */
    public static final String WIZARD_SEARCH_BASE = "SELECT " +
        "id,date,amount,check,wizard " +
        "FROM " + WIZARD_TABLE;


// ---- End Model ----

}