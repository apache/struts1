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
 * @author Ted Husted
 * @version $Revision: 1.3 $ $Date: 2002/12/08 08:26:37 $
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


/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/http/CreateIndex.java,v 1.3 2002/12/08 08:26:37 rleland Exp $
 * $Revision: 1.3 $
 * $Date: 2002/12/08 08:26:37 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
**/

