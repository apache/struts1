package org.apache.artimus.article.sql;


/**
 * SQL command constants and custom statements
 * for the Article package.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:13 $
 */
public final class Commands {


    // ---- Article SQL Statements ----


    /**
     * Name for ARTICLE table
     */
    public static final String ARTICLE_TABLE = "artimus_article ";


    /**
     * DDL for ARTICLE TABLE
     */
    public static final String ARTICLE_CREATE =
      "(article     INTEGER NOT NULL PRIMARY KEY," +
       "marked      BIT NOT NULL," +
       "modified    TIMESTAMP," +
       "user        CHARACTER(15)," +
       "host        CHARACTER(15)," +
       "contributed TIMESTAMP," +
       "contributor CHARACTER(15)," +
       "creator     VARCHAR(79) NOT NULL," +
       "title       VARCHAR(255) NOT NULL," +
       "content     TEXT," +
       "INDEX marked_index (marked)," +
       "INDEX title_index (title,creator)" +
      ");";


    /**
     * Default starting value for primary keys.
     */
    public static final String PRIMARY_KEY_INIT =
        org.apache.artimus.keys.sql.Commands.PRIMARY_KEY_INIT;


    /**
     * Command to intialize primary key for ARTICLE table
     */
    public static final String ARTICLE_KEY_INIT = "INSERT INTO artimus_keys " +
      "(name,next) VALUES ('artimus_article'," + PRIMARY_KEY_INIT + ");";


    // ---- insert ----


    /**
     * Command to insert default properties into ARTICLE table
     * Primary key should come last to match update command.
     */
    public static final String ARTICLE_INSERT = "INSERT INTO "
        + ARTICLE_TABLE +
        "(contributed,contributor,creator,title,content,article) " +
        "VALUES (?,?,?,?,?,?)";


    // ---- update ----


    /**
     * Command to update default properties in ARTICLE table by primary key
     */
    public static final String ARTICLE_UPDATE = "UPDATE " +
        ARTICLE_TABLE + "SET " +
        "contributed=?,contributor=?,creator=?,title=?,content=? " +
        "WHERE article=?;";


    // ---- delete / undelete ----


    /**
     * Command to mark article for deletion
     */
    public static final String ARTICLE_RECYCLE = "UPDATE " +
        ARTICLE_TABLE + "SET marked=1 WHERE article=?;";


    /**
     * Command to unmark an article for deletion
     */
    public static final String ARTICLE_RESTORE = "UPDATE " +
        ARTICLE_TABLE + "SET marked=0 WHERE article=?;";


    /**
     * Command to permanently delete marked articles
     */
    public static final String ARTICLE_DELETE_MARKED = "DELETE FROM " +
        ARTICLE_TABLE + "WHERE marked=1;";


    // ---- select ----


    /**
     * Base command to select default properties from ARTICLE table
     */
    public static final String ARTICLE_SELECT_BASE = "SELECT " +
        "article,contributed,contributor,creator," +
        "title,content " +
        "FROM " + ARTICLE_TABLE;


    /**
     * Command to select default properties from ARTICLE table
     */
    public static final String ARTICLE_SELECT = ARTICLE_SELECT_BASE +
        "WHERE marked=0;";


    /**
     * Command to select default properties from ARTICLE table by primary key
     */
    public static final String ARTICLE_SELECT_KEY = ARTICLE_SELECT_BASE +
        "WHERE article=? AND marked=0;";



    // ---- search ----


    /**
     * Base command to select search list from ARTICLE table
     */
    public static final String ARTICLE_SEARCH_BASE = "SELECT " +
        "article,contributed,contributor,creator,title " +
        "FROM " + ARTICLE_TABLE;


    /**
     * Command to select search list, latest articles first
     */
    public static final String ARTICLE_SEARCH_CURRENT = ARTICLE_SEARCH_BASE +
        " WHERE marked=0 ORDER by article DESC";


    /**
     * Command to select search list, latest articles first, since article key
     */
    public static final String ARTICLE_SEARCH_LAST = ARTICLE_SEARCH_BASE +
        "WHERE article>? AND marked=0 ORDER by article DESC";


    /**
     * Command to select search list, latest articles first, by days old
     */
    public static final String ARTICLE_SEARCH_SINCE = ARTICLE_SEARCH_BASE +
        "WHERE modified>? AND marked=0 ORDER by article DESC";


    /**
     * Command to select search list from ARTICLE table by title
     */
    public static final String ARTICLE_SEARCH_TITLE = ARTICLE_SEARCH_BASE +
        "WHERE marked=0 AND title LIKE (?) ORDER BY title,creator;";


    /**
     * Command to select search list from ARTICLE table by author
     */
    public static final String ARTICLE_SEARCH_CREATOR = ARTICLE_SEARCH_BASE +
        "WHERE marked=0 AND creator LIKE (?) ORDER BY creator,title;";


    /**
     * Command to select search list from ARTICLE table by content
     */
    public static final String ARTICLE_SEARCH_CONTENT = ARTICLE_SEARCH_BASE +
        "WHERE marked=0 AND content LIKE (?) ORDER BY title,creator;";


    /**
     * Command to select search list from ARTICLE table by marked for deletion
     */
    public static final String ARTICLE_SELECT_MARKED = ARTICLE_SELECT_BASE +
        "WHERE marked=1";


// ---- End Data ----

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
