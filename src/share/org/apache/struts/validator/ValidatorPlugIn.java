/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/validator/ValidatorPlugIn.java,v 1.17 2003/05/22 00:42:29 dgraham Exp $
 * $Revision: 1.17 $
 * $Date: 2003/05/22 00:42:29 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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

import java.util.StringTokenizer;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResourcesInitializer;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

/**
 * <p>Loads <code>ValidatorResources</code> based on
 * configuration in the struts-config.xml.</p>
 *
 * @author David Winterfeldt
 * @version $Revision: 1.17 $ $Date: 2003/05/22 00:42:29 $
 * @since Struts 1.1
 */
public class ValidatorPlugIn implements PlugIn {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getLog(ValidatorPlugIn.class);

    /**
     * The module configuration for our owning module.
     */
    private ModuleConfig config = null;

    /**
     * The {@link ActionServlet} owning this application.
     */
    private ActionServlet servlet = null;

    /**
     * Delimitter for Validator resources.
     */
    private final static String RESOURCE_DELIM = ",";

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
     * A comma delimitted list of Validator resource.
     */
    private String pathnames = null;

    /**
     * Gets a comma delimitted list of Validator resources.
     * @return comma delimited list of Validator resource path names
     */
    public String getPathnames() {
        return pathnames;
    }

    /**
     * Sets a comma delimitted list of Validator resources.
     * @param pathnames delimited list of Validator resource path names
     */
    public void setPathnames(String pathnames) {
        this.pathnames = pathnames;
    }

    /**
     * Initialize and load our resources.
     *
     * @param servlet The ActionServlet for our application
     * @param config The ModuleConfig for our owning module
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void init(ActionServlet servlet, ModuleConfig config)
        throws ServletException {

        // Remember our associated configuration and servlet
        this.config = config;
        this.servlet = servlet;

        // Load our database from persistent storage
        try {
            initResources();
            servlet.getServletContext().setAttribute(
                VALIDATOR_KEY + config.getPrefix(),
                resources);
                
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UnavailableException(
                "Cannot load a validator resource from '" + pathnames + "'");
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
     * Initialize the validator resources for this module.
     *
     * @exception IOException if an input/output error is encountered, not thrown by this implementation
     * @exception ServletException if we cannot initialize these resources, not thrown by this implementation
     */
    protected void initResources() throws IOException, ServletException {
        this.resources = new ValidatorResources();

        if (pathnames == null || pathnames.length() <= 0) {
            return;
        }

        StringTokenizer st = new StringTokenizer(pathnames, RESOURCE_DELIM);

        while (st.hasMoreTokens()) {
            String validatorRules = st.nextToken().trim();

            if (log.isInfoEnabled()) {
                log.info(
                    "Loading validation rules file from '" + validatorRules + "'");
            }

            InputStream input =
                servlet.getServletContext().getResourceAsStream(validatorRules);

            if (input != null) {
                BufferedInputStream bis = new BufferedInputStream(input);

                try {
                    // pass in false so resources aren't processed
                    // until last file is loaded
                    ValidatorResourcesInitializer.initialize(resources, bis, false);
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } finally {
                    bis.close();
                }

            } else {
                log.error(
                    "Skipping validation rules file from '"
                        + validatorRules
                        + "'.  No stream could be opened.");
            }
        }

        resources.process();

    }

    /**
     * Destroy <code>ValidatorResources</code>.
     */
    protected void destroyResources() {
        resources = null;
    }

}
