/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/validator/ValidatorPlugIn.java,v 1.2 2002/03/22 23:47:18 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2002/03/22 23:47:18 $
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


package org.apache.struts.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogSource;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResourcesInitializer;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ApplicationConfig;


/**
 * <p>Loads <code>ValidatorResources</code> based on 
 * configuration in the struts-config.xml.</p>
 *
 * @since 1.1
 * @author David Winterfeldt
*/
public class ValidatorPlugIn implements PlugIn {

    /**
     * Commons Logging instance.
    */
    private Log log = LogSource.getInstance(this.getClass().getName());

    /**
     * The application configuration for our owning sub-application.
     */
    private ApplicationConfig config = null;


    /**
     * The {@link ActionServlet} owning this application.
     */
    private ActionServlet servlet = null;

    /**
     * Application scope key that <code>ValidatorResources</code> is stored under.
    */
    public final static String VALIDATOR_KEY = "org.apache.commons.validator.VALIDATOR_RESOURCES";

    /**
     * The set of Form instances that have been created and initialized,
     * keyed by the struts form name.
     */
    protected ValidatorResources resources = null;


    // ------------------------------------------------------------- Properties

    /**
     * The last Validator resource added.
     */
    private String pathname = null; 

    /**
     * The list of  Validator resources added.
    */
    private List lValidatorRules = new ArrayList(); 

    /**
     * Gets the last Validator resource added.
    */
    public String getPathname() {
       return pathname;
    }

    /**
     * Sets the last Validator resource added and 
     * adds the path the list of resources.
    */
    public void setPathname(String pathname) {
       this.pathname = pathname;
       lValidatorRules.add(pathname);
    }



    /**
     * Initialize and load our resources.
     *
     * @param servlet The ActionServlet for our application
     * @param config The ApplicationConfig for our owning sub-application
     *
     * @exception ServletException if we cannot configure ourselves correctly
    */
    public void init(ActionServlet servlet, ApplicationConfig config)
        throws ServletException {

        // Remember our associated configuration and servlet
        this.config = config;
        this.servlet = servlet;

	// Load our database from persistent storage
	try {
	    initResources();
	    servlet.getServletContext().setAttribute(VALIDATOR_KEY, resources);
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    throw new UnavailableException
		("Cannot load validator resources from '" + lValidatorRules + "'");
	}

    }

    /**
     * Gracefully shut down, releasing any resources
     * that were allocated at initialization.
    */
    public void destroy() {
	
	if (log.isDebugEnabled()) {
	    log.debug("Destroying ValidatorPlugin");
	}

        servlet = null;
        config = null;
        
        destroyResources();
    }

    /**
     * Initialize the validator resources for this application.
     *
     * @exception IOException if an input/output error is encountered
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initResources() throws IOException, ServletException {
       resources = new ValidatorResources();
	
       for (Iterator i = lValidatorRules.iterator(); i.hasNext(); ) {
          String validatorRules = (String)i.next();

	  if (log.isInfoEnabled()) {
	     log.info("Loading validation rules file from '" + validatorRules + "'");
	  }          
	  
          InputStream input = null;
          BufferedInputStream bis = null;
          input = servlet.getServletContext().getResourceAsStream(validatorRules);
          
          if (input != null) {
             bis = new BufferedInputStream(input);
          
             try {
                // pass in false so resources aren't processed 
                // until last file is loaded
                ValidatorResourcesInitializer.initialize(resources, bis, false);
             } catch (Exception e) {
                log.error(e.getMessage(), e);
             }
          } else {
             log.error("Skipping validation rules file from '" + validatorRules + "'.  No stream could be opened.");	
          }
       }
       
       // process resources
       resources.process();
    }

    /**
     * Destroy <code>ValidatorResources</code>.
    */
    protected void destroyResources() {
	resources = null;
    }

}
