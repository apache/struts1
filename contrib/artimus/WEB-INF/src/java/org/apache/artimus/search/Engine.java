package org.apache.artimus.search;


import java.io.IOException;

import java.util.Collection;

import com.lucene.analysis.Analyzer;
import com.lucene.analysis.StopAnalyzer;

import com.lucene.document.Document;
import com.lucene.document.Field;

import com.lucene.index.IndexReader;
import com.lucene.index.IndexWriter;
import com.lucene.index.Term;

import com.lucene.queryParser.QueryParser;
import com.lucene.queryParser.ParseException;

import com.lucene.search.Hits;
import com.lucene.search.IndexSearcher;
import com.lucene.search.Query;
import com.lucene.search.Searcher;

import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelParameterException;
import org.apache.scaffold.model.ModelResourceException;
import org.apache.scaffold.search.LuceneUtils;


/**
 * Search engine methods for Articles application.
 * <p>
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:13 $
 */
public final class Engine {

    /**
     * Default path for index. Override
     * getIndexPath() to change.
    **/
    private static String INDEX_PATH = "/var/lucene/artimus";


    /**
     * Performance optimization to ensure JVM doesn't
     * create a new empty string whenever blankNull
     * needs one.
    **/
    private static String BLANK_STRING = "";


    /**
     * Convenience method to use an empty string in place
     * of a null for indexing.
    **/
    public static final String blankNull(String string) {
        if (string==null) return BLANK_STRING;
        else return string;
    }


    /**
     * Return default analyzer for application
     * A non-English site should use a different analyzer
     * @return default analyzer
    **/
    public static final Analyzer getAnalyzer() {
        return new StopAnalyzer();
    }


    /**
     * Return default path for index
     * @return default analyzer
    **/
    public static final String getIndexPath() {
        return INDEX_PATH;
    }


    /**
     * Return default searcher for application.
     * @return default searcher
    **/
    public static final Searcher getSearcher() throws ModelException {
        try {

            return new IndexSearcher(getIndexPath());

        }
        catch (IOException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Return default writer for application.
     * @return default writer
    **/
    public static final IndexWriter getIndexWriter(boolean create)
        throws ModelException {
        try {

            return (new IndexWriter(getIndexPath(),
                getAnalyzer(),create));

        }
        catch (IOException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Return default reader for application.
     * @return default reader
    **/
    public static final IndexReader getIndexReader()
        throws ModelException {
        try {

            return (IndexReader.open(getIndexPath()));

        }
        catch (IOException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Return hits for model using default searcher.
     * @return hits for model
    **/
    public static final Hits getHits(Query query)
        throws ModelException {
        try {

            return getSearcher().search(query);

        }
        catch (IOException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Return model for key and column
     * @return hits for model
    **/
    public static final Query getQuery(String key, String column)
        throws ModelException {
        try {

            return QueryParser.parse(key,column, getAnalyzer());

        }
        catch (ParseException e) {
            throw new ModelParameterException(e);
        }
    }


} // ---- End Engine -----


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
