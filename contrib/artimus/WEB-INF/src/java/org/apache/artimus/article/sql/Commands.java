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
 
package org.apache.artimus.article.sql;


/**
 * SQL command constants and custom statements
 * for the Article package.
 * @version $Rev$ $Date$
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