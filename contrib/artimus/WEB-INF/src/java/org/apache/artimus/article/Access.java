/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/article/Access.java,v 1.5 2004/03/14 07:15:05 sraeburn Exp $
 * $Revision: 1.5 $
 * $Date: 2004/03/14 07:15:05 $
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
 

package org.apache.artimus.article;


import java.io.IOException;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.Collection;
import java.util.Date;

import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResourceException;
import org.apache.scaffold.sql.StatementUtils;

import org.apache.artimus.article.sql.Commands;
import org.apache.artimus.article.sql.Statements;

import org.apache.scaffold.search.LuceneUtils;
import org.apache.artimus.search.Engine;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

// import org.apache.lucene.search.Hits;
// import org.apache.lucene.search.Query;


/**
 * Data access methods for Articles package.
 * This class brings together SQL Commands and SQL Statements
 * to execute update queries or return collections of beans
 * from the database.
 * From the package's viewpoint, this class <b>is</b>
 * the database (or "model").
 * <p>
 * This class could be based on an interface if another
 * (non-SQL) type of access was needed.
 * @version $Revision: 1.5 $ $Date: 2004/03/14 07:15:05 $
**/
public final class Access {


// ---- ARTICLE ----

    /**
     * Creates ARTICLE table. This is a wrapper to pass
     * ARTCLE_TABLE and ARTICLE_CREATE to CreateTable(),
     * thereby encapsulating the implementation.
     * <p>
     * @exception ModelException if SQL error occurs
    **/
    public static final void createTable()
            throws ModelException {
        try {

            StatementUtils.createTable(null,
                Commands.ARTICLE_TABLE, Commands.ARTICLE_CREATE);

            // :FIXME: Should be a toplevel model method for this
            int result = StatementUtils.executeUpdate(null,Commands.ARTICLE_KEY_INIT);

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Add article to the specified index
     * @exception Exception if  error occurs
    **/
    public static final void index (
            String article, String contributor, String creator,
            String title, String content,IndexWriter index
        ) throws ModelException {
        try {
                Document document = new Document();
                    document.add(Field.Keyword("article",
                        Engine.blankNull(article)));
                    document.add(Field.Text("title",
                        Engine.blankNull(title)));
                    document.add(Field.UnStored("contributor",
                        Engine.blankNull(contributor)));
                    document.add(Field.UnStored("creator",
                        Engine.blankNull(creator)));
                    document.add(Field.UnStored("content",
                        Engine.blankNull(content)));
                index.addDocument(document);
        }
        catch (IOException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Return next key for ARTICLE table. This is a wrapper
     * to pass ARTICLE_TABLE to allocateKey() thereby encapsulating
     * the implementation.
     * @return The Integer key value to be inserted
     * @exception ModelException if SQL error occurs
    **/
    public synchronized static final Integer allocateKey()
            throws ModelException {
        try {

             return org.apache.artimus.keys.sql.Statements.allocateKey(
                 Commands.ARTICLE_TABLE);

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }

    }


    /**
     * Select all records.
     * <p>
     * @return Collection with record or empty collection
     * @exception ModelException if SQL error occurs
     * @param key Article number
     * @param target Object object to create Collection from ResultSet
    **/
    public static final Collection select(Object target)
            throws ModelException {
        try {

            return StatementUtils.getCollection(null,
                target,Commands.ARTICLE_SELECT);

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Select record from ARTICLE table by primary key.
     * <p>
     * @return Collection with record or empty collection
     * @exception ModelException if SQL error occurs
     * @param key Article number
     * @param target Object object to create Collection from ResultSet
    **/
    public static final Collection select(Object target, Integer key)
            throws ModelException {
        try {

            return StatementUtils.getCollection(null,
                target,Commands.ARTICLE_SELECT_KEY,key);

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
   }


    /**
     * Select records from ARTICLE table.
     * <p>
     * @return Collection with records or empty Collection
     * @exception ModelException if SQL error occurs
     * @param target Object object to create Collection from ResultSet
    **/
    public static final Collection searchCurrent(Object target)
            throws ModelException {
        try {

            return StatementUtils.getCollection(null,
               target,Commands.ARTICLE_SEARCH_CURRENT);

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Select last X records from ARTICLE table.
     * @return Collection with records or empty Collection
     * @exception ModelException if SQL error occurs
     * @param target Object object to create Collection from ResultSet
    **/
    public static final Collection searchLast(Object target, int key)
            throws ModelException {
        try {

            int next = org.apache.artimus.keys.sql.Statements.peekKey(
                Commands.ARTICLE_TABLE);
            return StatementUtils.getCollection(null,
                target,Commands.ARTICLE_SEARCH_LAST,next-key);

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Select records from ARTICLE table by hours since modified.
     * <p>
     * @return Collection with records or empty Collection
     * @exception ModelException if SQL error occurs
     * @param target Object object to create Collection from ResultSet
    **/
    public static final Collection searchHours(Object target,
            int key) throws ModelException {
        try {

            Date now = new Date();
            long from = key;
            long time = now.getTime() - (from * (60 * 60 * 1000));
            Timestamp since = new Timestamp(time);

            return StatementUtils.getCollection(null,
                target,Commands.ARTICLE_SEARCH_SINCE,since.toString());

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Select record from ARTICLE table by property..
     * <p>
     * @return Collection with record or empty Collection
     * @exception ModelException if SQL error occurs
     * @param key Term to match
     * @param property Field to search
     * @param target Object object to create Collection from ResultSet
    **/
    public static final Collection searchProperty(Object target,
            String key, String property) throws ModelException {

        // return StatementUtils.getCollectionLike(null,
        //    key,Commands.ARTICLE_SEARCH_CREATOR,target);

         return LuceneUtils.getCollection(target,
            Engine.getHits(
                Engine.getQuery(key,property)));
    }


    /**
     * Mark an article record for deletion.
     * @return 0 if fails
     * @exception ModelException if SQL error occurs
    **/
    public static final int delete(Integer key)
            throws ModelException {
        try {

            Engine.getIndexReader().delete(new Term("article",
                key.toString()));

            return StatementUtils.executeUpdate(null,
                Commands.ARTICLE_RECYCLE,key);
        }
        catch (IOException e) {
            throw new ModelResourceException(e);
        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Insert new entry into ARTICLE table.
     * <p>
     * @return 0 if fails
     * @exception ModelException if SQL error occurs
    **/
    public static final int insert (
            Integer article, Timestamp contributed, String contributor, String creator,
            String title, String content
            ) throws ModelException {
        try {

            return Statements.execute(Commands.ARTICLE_INSERT,
                article, contributed, contributor, creator, title, content);
        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Update entry in ARTICLE table.
     * <p>
     * @return 0 if fails
     * @exception ModelException if SQL error occurs
    **/
    public static final int update (
            Integer article, Timestamp contributed, String contributor, String creator,
            String title, String content
            ) throws ModelException {
         try {

            return Statements.execute(Commands.ARTICLE_UPDATE,
                article, contributed, contributor, creator, title, content);
        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }

} // ---- End Model -----