/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/Attic/DatabasePlugIn.java,v 1.1 2002/02/23 22:54:17 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2002/02/23 22:54:17 $
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


package org.apache.struts.webapp.example;


import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.MissingResourceException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.digester.Digester;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.util.MessageResources;


/**
 * <p><strong>DatabasePlugIn</strong> initializes and finalizes the
 * persistent storage of User and Subscription information for the Struts
 * Demonstration Application.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2002/02/23 22:54:17 $
 */

public final class DatabasePlugIn implements PlugIn {


    // ----------------------------------------------------- Instance Variables


    /**
     * The application configuration for our owning sub-application.
     */
    private ApplicationConfig config = null;


    /**
     * The database of Users and their associated Subscriptions, keyed by
     * username.
     */
    private Hashtable database = null;


    /**
     * The {@link ActionServlet} owning this application.
     */
    private ActionServlet servlet = null;


    // ------------------------------------------------------------- Properties


    /**
     * The debugging detail level for this servlet.
     */
    private int debug = 0;

    public int getDebug() {
        return (this.debug);
    }

    public void setDebug(int debug) {
        this.debug = debug;
    }


    /**
     * The resource path of our persistent database storage file.
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

	if (debug >= 1)
	    servlet.log("Finalizing database plug in");

        // NOTE:  We do not attempt to unload the database because there
        // is no portable way to do so.  Real applications will have used
        // a real database, with no need to unload it

	// Remove the database from our application attributes
	servlet.getServletContext().removeAttribute(Constants.DATABASE_KEY);
        servlet = null;
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

        // Remember our associated configuration and servlet
        this.config = config;
        this.servlet = config.getServlet();

	// Load our database from persistent storage
	try {
	    load();
	    servlet.getServletContext().setAttribute(Constants.DATABASE_KEY,
                                                     database);
	} catch (Exception e) {
	    servlet.log("Database load exception", e);
	    throw new UnavailableException
		("Cannot load database from '" + pathname + "'");
	}

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Add a new User to our database.
     *
     * @param user The user to be added
     */
    public void addUser(User user) {

	database.put(user.getUsername(), user);

    }


    // ------------------------------------------------------ Private Methods



    /**
     * Load our database from its persistent storage version.
     *
     * @exception Exception if any problem occurs while loading
     */
    private synchronized void load() throws Exception {

	// Initialize our database
	database = new Hashtable();

	// Acquire an input stream to our database file
	if (debug >= 1)
	    servlet.log("Loading database from '" + pathname + "'");
        InputStream is =
            servlet.getServletContext().getResourceAsStream(pathname);
        if (is == null) {
            servlet.log("No such resource available - loading empty database");
            return;
        }
	BufferedInputStream bis = new BufferedInputStream(is);

	// Construct a digester to use for parsing
	Digester digester = new Digester();
	digester.push(this);
	digester.setDebug(debug);
        digester.setNamespaceAware(true);
	digester.setValidating(false);
	digester.addObjectCreate("database/user",
				 "org.apache.struts.webapp.example.User");
	digester.addSetProperties("database/user");
	digester.addSetNext("database/user", "addUser");
	digester.addObjectCreate("database/user/subscription",
				 "org.apache.struts.webapp.example.Subscription");
	digester.addSetProperties("database/user/subscription");
	digester.addSetTop("database/user/subscription", "setUser");

	// Parse the input stream to initialize our database
	digester.parse(bis);
	bis.close();

    }


}
