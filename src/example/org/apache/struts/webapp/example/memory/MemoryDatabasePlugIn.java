/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/memory/MemoryDatabasePlugIn.java,v 1.1 2002/03/05 04:23:57 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2002/03/05 04:23:57 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
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


package org.apache.struts.webapp.example.memory;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.webapp.example.Constants;
import org.apache.struts.webapp.example.Subscription;
import org.apache.struts.webapp.example.User;
import org.apache.struts.webapp.example.UserDatabase;


/**
 * <p><strong>MemoryDatabasePlugIn</strong> initializes and finalizes the
 * persistent storage of User and Subscription information for the Struts
 * Demonstration Application, using an in-memory database backed by an
 * XML file.</p>
 *
 * <p><strong>IMPLEMENTATION WARNING</strong> - If this web application is run
 * from a WAR file, or in another environment where reading and writing of the
 * web application resource is impossible, the initial contents will be copied
 * to a file in the web application temporary directory provided by the
 * container.  This is for demonstration purposes only - you should
 * <strong>NOT</strong> assume that files written here will survive a restart
 * of your servlet container.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2002/03/05 04:23:57 $
 */

public final class MemoryDatabasePlugIn implements PlugIn {


    // ----------------------------------------------------- Instance Variables


    /**
     * The application configuration for our owning sub-application.
     */
    private ApplicationConfig config = null;


    /**
     * The {@link MemoryUserDatabase} object we construct and make available.
     */
    private MemoryUserDatabase database = null;


    /**
     * Logging output for this plug in instance.
     */
    private Log log = LogFactory.getLog(this.getClass());


    /**
     * The {@link ActionServlet} owning this application.
     */
    private ActionServlet servlet = null;


    // ------------------------------------------------------------- Properties


    /**
     * The web application resource path of our persistent database
     * storage file.
     */
    private String pathname = "/WEB-INF/database.xml";

    public String getPathname() {
        return (this.pathname);
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }


    // --------------------------------------------------------- PlugIn Methods


    /**
     * Gracefully shut down this database, releasing any resources
     * that were allocated at initialization.
     */
    public void destroy() {

        log.info("Finalizing memory database plug in");

        if (database != null) {
            try {
                database.close();
            } catch (Exception e) {
                log.error("Closing memory database", e);
            }
        }

	servlet.getServletContext().removeAttribute(Constants.DATABASE_KEY);
        database = null;
        servlet = null;
        database = null;
        config = null;

    }


    /**
     * Initialize and load our initial database from persistent storage.
     *
     * @param config The ApplicationConfig for our owning sub-application
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void init(ApplicationConfig config) throws ServletException {

        log.info("Initializing memory database plug in from '" +
                 pathname + "'");

        // Remember our associated configuration and servlet
        this.config = config;
        this.servlet = config.getServlet();

        // Construct a new database and make it available
        database = new MemoryUserDatabase();
        try {
            String path = calculatePath();
            if (log.isDebugEnabled()) {
                log.debug(" Loading database from '" + path + "'");
            }
            database.setPathname(path);
            database.open();
            database.save();
        } catch (Exception e) {
            log.error("Opening memory database", e);
            throw new ServletException("Cannot load database from '" +
                                       pathname + "'", e);
        }

        // Make the initialized database available
        servlet.getServletContext().setAttribute(Constants.DATABASE_KEY,
                                                 database);

    }


    // --------------------------------------------------------- Public Methods


    // -------------------------------------------------------- Private Methods


    /**
     * Calculate and return an absolute pathname to the XML file to contain
     * our persistent storage information.
     *
     * @exception Exception if an input/output error occurs
     */
    private String calculatePath() throws Exception {

        // Can we access the database via file I/O?
        String path = servlet.getServletContext().getRealPath(pathname);
        if (path != null) {
            return (path);
        }

        // Does a copy of this file already exist in our temporary directory
        File dir = (File)
            servlet.getServletContext().getAttribute
            ("javax.servlet.context.tempdir");
        File file = new File(dir, "struts-example-database.xml");
        if (file.exists()) {
            return (file.getAbsolutePath());
        }

        // Copy the static resource to a temporary file and return its path
        InputStream is =
            servlet.getServletContext().getResourceAsStream(pathname);
        BufferedInputStream bis = new BufferedInputStream(is, 1024);
        FileOutputStream os =
            new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        byte buffer[] = new byte[1024];
        while (true) {
            int n = bis.read(buffer);
            if (n <= 0) {
                break;
            }
            bos.write(buffer, 0, n);
        }
        bos.close();
        bis.close();
        return (file.getAbsolutePath());

    }


}
