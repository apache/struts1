/*
 * Copyright 1999-2002,2004 The Apache Software Foundation.
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

package org.apache.shale.examples.mailreader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.examples.mailreader.memory.MemoryUserDatabase;

/**
 * <p><code>ServletContextListener</code> that initializes and finalizes the
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
 * $Id$
 */

public final class ApplicationListener implements ServletContextListener {


    // ----------------------------------------------------- Manifest Constants


    /**
     * <p>Appication scope attribute key under which the in-memory
     * version of our database is stored.</p>
     */
    public static final String DATABASE_KEY = "database";


    /**
     * <p>Application scope attribute key under which the valid
     * selection items for the protocol property is stored.</p>
     */
    public static final String PROTOCOLS_KEY = "protocols";


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The <code>ServletContext</code> for this web application.</p>
     */
    private ServletContext context = null;


    /**
     * The {@link MemoryUserDatabase} object we construct and make available.
     */
    private MemoryUserDatabase database = null;


    /**
     * Logging output for this plug in instance.
     */
    private Log log = LogFactory.getLog(this.getClass());


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


    // ------------------------------------------ ServletContextListener Methods


    /**
     * <p>Gracefully shut down this database, releasing any resources
     * that were allocated at initialization.</p>
     *
     * @param event ServletContextEvent to process
     */
    public void contextDestroyed(ServletContextEvent event) {

        log.info("Finalizing memory database plug in");

        if (database != null) {
            try {
                database.close();
            } catch (Exception e) {
                log.error("Closing memory database", e);
            }
        }

	context.removeAttribute(DATABASE_KEY);
        context.removeAttribute(PROTOCOLS_KEY);
        database = null;
        context = null;

    }


    /**
     * <p>Initialize and load our initial database from persistent storage.</p>
     *
     * @param servlet The ActionServlet for this web application
     * @param config The ApplicationConfig for our owning module
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void contextInitialized(ServletContextEvent event) {

        log.info("Initializing memory database plug in from '" +
                 pathname + "'");

        // Remember our associated ServletContext
        this.context = event.getServletContext();

        // Construct a new database and make it available
        database = new MemoryUserDatabase();
        try {
            String path = calculatePath();
            if (log.isDebugEnabled()) {
                log.debug(" Loading database from '" + path + "'");
            }
            database.setPathname(path);
            database.open();
        } catch (Exception e) {
            log.error("Opening memory database", e);
            throw new IllegalStateException("Cannot load database from '" +
                                            pathname + "': " + e);
        }
        context.setAttribute(DATABASE_KEY, database);

        // Cache the selection items for protocols
        SelectItem protocols[] = new SelectItem[2];
        protocols[0] = new SelectItem("imap", "IMAP Protocol");
        protocols[1] = new SelectItem("pop3", "POP3 Protocol");
        context.setAttribute(PROTOCOLS_KEY, protocols);

    }


    // -------------------------------------------------------- Private Methods


    /**
     * Calculate and return an absolute pathname to the XML file to contain
     * our persistent storage information.
     *
     * @exception Exception if an input/output error occurs
     */
    private String calculatePath() throws Exception {

        // Can we access the database via file I/O?
        String path = context.getRealPath(pathname);
        if (path != null) {
            return (path);
        }

        // Does a copy of this file already exist in our temporary directory
        File dir = (File)
            context.getAttribute("javax.servlet.context.tempdir");
        File file = new File(dir, "struts-example-database.xml");
        if (file.exists()) {
            return (file.getAbsolutePath());
        }

        // Copy the static resource to a temporary file and return its path
        InputStream is =
            context.getResourceAsStream(pathname);
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
