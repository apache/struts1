package org.apache.artimus.wizard.sql;


/**
 * SQL command constants and custom statements
 * for the Article package.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:14 $
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
