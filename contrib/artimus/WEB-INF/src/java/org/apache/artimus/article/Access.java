package org.apache.artimus.article;


import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.lucene.document.Document;
import com.lucene.document.Field;
import com.lucene.index.IndexWriter;
import com.lucene.index.Term;

// import com.lucene.search.Hits;
// import com.lucene.search.Query;


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
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:12 $
 */
public final class Access {


// ---- ARTICLE ----

    /**
     * Creates ARTICLE table. This is a wrapper to pass
     * ARTCLE_TABLE and ARTICLE_CREATE to CreateTable(),
     * thereby encapsulating the implementation.
     * <p>
     * @exception ModelException if SQL error occurs
     */
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
 *    notice, this Collection of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this Collection of conditions and the following disclaimer in
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
