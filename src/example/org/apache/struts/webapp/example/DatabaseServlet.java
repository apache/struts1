/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/Attic/DatabaseServlet.java,v 1.1 2001/04/11 02:10:00 rleland Exp $
 * $Revision: 1.1 $
 * $Date: 2001/04/11 02:10:00 $
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.digester.Digester;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * <strong>DatabaseServlet</strong> initializes and finalizes the persistent
 * storage of User and Subscription information for the Struts
 * Demonstration Application.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2001/04/11 02:10:00 $
 */

public final class DatabaseServlet
    extends HttpServlet {


    // ----------------------------------------------------- Instance Variables


    /**
     * The database of Users and their associated Subscriptions, keyed by
     * username.
     */
    private Hashtable database = null;


    /**
     * The debugging detail level for this servlet.
     */
    private int debug = 0;


    /**
     * The resource path of our persistent database storage file.
     */
    private String pathname = "/WEB-INF/database.xml";


    // ---------------------------------------------------- HttpServlet Methods


    /**
     * Gracefully shut down this database servlet, releasing any resources
     * that were allocated at initialization.
     */
    public void destroy() {

	if (debug >= 1)
	    log("Finalizing database servlet");

	// Unload our database to persistent storage if possible
	try {
	    unload();
	} catch (Exception e) {
	    log("Database unload exception", e);
	}

	// Remove the database from our application attributes
	getServletContext().removeAttribute(Constants.DATABASE_KEY);

    }


    /**
     * Initialize this servlet, including loading our initial database from
     * persistent storage.  The following servlet initialization parameters
     * are processed, with default values in square brackets:
     * <ul>
     * <li><strong>debug</strong> - The debugging detail level for this
     *     servlet, which controls how much information is logged.  [0]
     * <li><strong>pathname</strong> - Resource pathname to our persistent
     *     storage ["/WEB-INF/database.xml"]
     * </ul>
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void init() throws ServletException {

	// Process our servlet initialization parameters
	String value;
	value = getServletConfig().getInitParameter("debug");
	try {
	    debug = Integer.parseInt(value);
	} catch (Throwable t) {
	    debug = 0;
	}
	if (debug >= 1)
	    log("Initializing database servlet");
        value = getServletConfig().getInitParameter("pathname");
        if (value != null)
            pathname = value;

	// Load our database from persistent storage
	try {
	    load();
	    getServletContext().setAttribute(Constants.DATABASE_KEY,
					     database);
	} catch (Exception e) {
	    log("Database load exception", e);
	    throw new UnavailableException
		("Cannot load database from '" + pathname + "'");
	}

    }


    /**
     * Process an HTTP "GET" request.  For the purposes of this servlet,
     * processing means flushing the current content of the database to
     * persistent storage.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws IOException, ServletException {

	// Unload our database to persistent storage
	if (debug >= 1)
	    log("Flushing database to persistent storage");
	try {
	    unload();
	} catch (Exception e) {
	    log("Database flush exception", e);
	}

	response.sendError(HttpServletResponse.SC_NO_CONTENT);

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


    /**
     * Return the debugging detail level for this servlet.
     */
    public int getDebug() {

	return (this.debug);

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
	    log("Loading database from '" + pathname + "'");
        InputStream is = getServletContext().getResourceAsStream(pathname);
        if (is == null) {
            log("No such resource available - loading empty database");
            return;
        }
	BufferedInputStream bis = new BufferedInputStream(is);

	// Construct a digester to use for parsing
	Digester digester = new Digester();
	digester.push(this);
	digester.setDebug(debug);
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


    /**
     * Unload our database to its persistent storage version, if possible.
     * If we are running directly out of a WAR file, saving cannot occur.
     *
     * @exception Exception if any problem occurs while unloading
     */
    private synchronized void unload() throws Exception {

        // Calculate the file pathname to our storage file (if any)
        String pathname =
            getServletContext().getRealPath(this.pathname);
        if (pathname == null) {
            log("Cannot unload database to resource path " + this.pathname);
            return;
        }

	// Create a writer for our database
	if (debug >= 1)
	    log("Unloading database to '" + pathname + "'");
	FileWriter fw = new FileWriter(pathname);
	BufferedWriter bw = new BufferedWriter(fw);
	PrintWriter writer = new PrintWriter(bw);
	writer.println("<database>");

	// Render the contents of our database
	Enumeration users = database.elements();
	while (users.hasMoreElements()) {
	    User user = (User) users.nextElement();
	    writer.print("  <user");
            if (user.getUsername() != null)
                writer.print(" username=\"" + user.getUsername() + "\"");
            if (user.getPassword() != null)
                writer.print(" password=\"" + user.getPassword() + "\"");
            if (user.getFullName() != null)
                writer.print(" fullName=\"" + user.getFullName() + "\"");
            if (user.getFromAddress() != null)
                writer.print(" fromAddress=\"" + user.getFromAddress() + "\"");
            if (user.getReplyToAddress() != null)
                writer.print(" replyToAddress=\"" + user.getReplyToAddress() + "\"");
            writer.println(">");
	    Subscription subscriptions[] = user.getSubscriptions();
	    for (int i = 0; i < subscriptions.length; i++) {
		writer.print("    <subscription");
                writer.print(" autoConnect=\"" +
                             subscriptions[i].getAutoConnect() + "\"");
                if (subscriptions[i].getHost() != null)
                    writer.print(" host=\"" + subscriptions[i].getHost() +
                                 "\"");
                if (subscriptions[i].getType() != null)
                    writer.print(" type=\"" + subscriptions[i].getType() +
                                 "\"");
                if (subscriptions[i].getUsername() != null)
                    writer.print(" username=\"" +
                                 subscriptions[i].getUsername() + "\"");
                if (subscriptions[i].getPassword() != null)
                    writer.print(" password=\"" +
                                 subscriptions[i].getPassword() + "\"");
                writer.println("/>");
	    }
	    writer.println("  </user>");
	}

	// Finish up and close our writer
	writer.println("</database>");
	writer.flush();
	writer.close();

    }


}
