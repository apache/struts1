/*
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
 */
 
 
package org.apache.struts.validator.action;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResourcesInitializer;


/**
 * <p><strong>ValidatorServlet</strong> loads validation values into application scope.</p>
 *
 * @author David Winterfeldt
 */

public class ValidatorServlet extends HttpServlet {
    public final static String VALIDATOR_KEY = "org.apache.struts.validator.action.VALIDATOR";

    /**
     * The set of Form instances that have been created and initialized,
     * keyed by the struts form name.
     */
    protected ValidatorResources resources = null;

    /**
     * The context-relative path to our configuration resource.
     */
    protected String config = "WEB-INF/validation.xml";

    /**
     * The debugging detail level for this servlet.
     */
    protected int debug = 0;


    // ---------------------------------------------------- HttpServlet Methods

    /**
     * Gracefully shut down this controller servlet, releasing any resources
     * that were allocated at initialization.
     */
    public void destroy() {

	if (debug >= 1)
	    log("Destroying ValidatorServlet");

        destroyMapping();

    }


    /**
     * Initialize this servlet.  Most of the processing has been factored into
     * support methods so that you can override particular functionality at a
     * fairly granular level.
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void init() throws ServletException {

	try {
	    initDebug();
	    initMapping();
	} catch (IOException e) {
	    throw new UnavailableException
		("Failure to initMapping in ValidatorServlet.");
	}
	
	getServletContext().setAttribute(VALIDATOR_KEY, resources);
    }

    /**
     * Process an HTTP "GET" request.
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

	// if action=reload, reload
	//process(request, response);

    }


    /**
     * Process an HTTP "POST" request.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public void doPost(HttpServletRequest request,
		       HttpServletResponse response)
	throws IOException, ServletException {

	// if action=reload, reload
	//process(request, response);

    }


    // --------------------------------------------------------- Public Methods

    /**
     * Reload the configuration of this controller servlet from our
     * underlying configuration files.
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public void reload() throws IOException, ServletException {

        if (debug >= 1)
            log("Reloading ValidatorServlet");


        // Restart from our confirmation files
        initMapping();

    }



    // ------------------------------------------------------ Protected Methods

    protected void destroyMapping() {
	resources = null;
    }

    /**
     * Initialize the mapping information for this application.
     *
     * @exception IOException if an input/output error is encountered
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initMapping() throws IOException, ServletException {
	// Acquire an input stream to our database file
	
	// Initialize the context-relative path to our configuration resources
	String value = null;
	value = getServletConfig().getInitParameter("config");
	if (value != null)
	    config = value;
	if (debug >= 1)
	    log("Loading validation file from '" + config + "'");

	InputStream input = null;
	input = getServletContext().getResourceAsStream(config);
	if (input == null)
	    throw new UnavailableException("Can't load Validator XML file.");

	BufferedInputStream bis = new BufferedInputStream(input);

        try {
           resources = ValidatorResourcesInitializer.initialize(bis, debug);
           //  new HttpValidatorLog(getServletContext()), bis, debug);
        } catch (Exception e) {
           log("ValidatorServlet::initMapping - " + e.getMessage(), debug);
        }

    }

    /**
     * Initialize the debugging detail level for this application's validator.
     *
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initDebug() throws ServletException {

	String value = getServletConfig().getInitParameter("debug");
	try {
	    debug = Integer.parseInt(value);
	} catch (Throwable t) {
	    debug = 0;
	}
    }
    
    /**
     * Return the debugging detail level for this servlet.
     */
    public int getDebug() {

	return (this.debug);

    }

    /**
     * Log the specified message if the current debugging detail level for
     * this servlet has been set to an equal or higher value.  Otherwise,
     * ignore this message.
     *
     * @param message Message to be logged
     * @param level Debugging detail level of this message
     */
    public void log(String message, int level) {

        if (debug >= level)
            log(message);

    }

}
