/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/search/Engine.java,v 1.5 2004/03/14 07:15:07 sraeburn Exp $
 * $Revision: 1.5 $
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
 

package org.apache.artimus.search;


import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.ParseException;

import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;

import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelParameterException;
import org.apache.scaffold.model.ModelResourceException;
import org.apache.scaffold.search.LuceneUtils;


/**
 * Search engine methods for Articles application.
 * <p>
 * @version $Revision: 1.5 $ $Date: 2004/03/14 07:15:07 $
**/
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