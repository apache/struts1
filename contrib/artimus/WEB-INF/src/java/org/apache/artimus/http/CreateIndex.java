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
 

package org.apache.artimus.http;


import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import org.apache.artimus.article.http.Form;
import org.apache.artimus.search.Engine;
import org.apache.artimus.article.Access;


/**
 * Create search engine indexes used by application.
 * @version $Rev$ $Date$
**/
public final class CreateIndex extends Action {

    // --------------------------------------------------------- Instances Variables
    // --------------------------------------------------------- Public Methods


    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException, ServletException {

        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();
        ArrayList list = null;
        Form article = null;

        // - Retrieve collection of items in databse
        // -- For larger collections, the results would have to
        // -- batched in segments of, say, 100 records each
        // - ResultUtils.getCollection is the underlying
        // - actor here, and returns an ArrayList

        try {
            // - Create an indexWriter and start a fresh index
            IndexWriter index = Engine.getIndexWriter(true);

            // - Run through list and create a document for each article
            // -- Store keyword and title since they are needed for the search list
            // -- Be sure not to pass any blank strings
            list = (ArrayList)
                Access.select(new Form());
            for (int i=0; i<list.size(); i++) {
                article = (Form) list.get(i);
                // - Add document to index
                Access.index(
                    article.getArticle(),article.getContributor(),article.getCreator(),
                    article.getTitle(),article.getContent(),index);
                // - Report progress
                printWriter.print(i); printWriter.print(':');
                printWriter.println(article.getTitle());
            }

            // - Final report
            printWriter.print(index.docCount());
            printWriter.println(" articles indexed.");

            // - Optimize and close index writer
            index.optimize();
            index.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            StringBuffer sb = new StringBuffer("Exception: " + e);
            printWriter.println(sb.toString());
        }

        // -- Process complete ---
        printWriter.println("DONE");
        return(null);


    } // ---- End perform ----

} // ---- End CreateIndex ----