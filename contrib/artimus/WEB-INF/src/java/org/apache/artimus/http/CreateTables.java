/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/http/CreateTables.java,v 1.4 2004/03/14 07:15:04 sraeburn Exp $
 * $Revision: 1.4 $
 * $Date: 2004/03/14 07:15:04 $
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Create database tables used by application.
 * <p>
 * Does not create the database. See your product's documentation
 * to do that first. If you are starting-over, you must drop
 * the tables (or entire database) yourself. If table exists,
 * your product should return an error. See your container's log
 * for the details of any error.
 * <p>
 * @see <strong>Commands.java</strong> for table names and
 * definitions.
 * <p>
 * @version $Revision: 1.4 $ $Date: 2004/03/14 07:15:04 $
 */
public final class CreateTables extends Action {

    // --------------------------------------------------------- Instances Variables
    // --------------------------------------------------------- Public Methods


    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException, ServletException {

        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();


        // -- KEYS --
        try {
            writer.print("KEYS TABLE ");
            org.apache.artimus.keys.Access.createTable();
        }
        catch (Exception e) {
            servlet.log("Exception: " + e);
            writer.print("NOT ");
        }
        writer.println("CREATED.");


        // -- ARTICLE --
        try {
            writer.print("ARTICLE TABLE ");
            org.apache.artimus.article.Access.createTable();
        }
        catch (Exception e) {
            servlet.log("Exception: " + e);
            writer.print("NOT ");
        }
        writer.println("CREATED.");


        // -- Process complete ---
        writer.println("DONE");
        return(null);


    } // ---- End perform ----

} // ---- End CreateTables ----