/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/legacy/ComposableRequestProcessor.java,v 1.2 2003/08/30 23:18:56 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2003/08/30 23:18:56 $
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

package org.apache.struts.chain.legacy;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ModuleConfig;

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p><strong>ComposableRequestProcessor</strong> uses the <em>Chain Of
 * Resposibility</em> design pattern (as implemented by the commons-chain
 * package in Jakarta Commons) to support external configuration of command
 * chains to be used.  It expects that an appropriate <code>Catalog</code>
 * will have been configured (typically using one or more invocations of
 * {@link CatalogConfiguratorPlugIn}) and stored in the context attribute
 * key <code>Constants.CATALOG_KEY</code>.  This processor will utilize
 * whatever chain is stored under id <code>servlet-standard</code> (currently
 * hardcoded; needs to be made configurable) to process this request.</p>
 *
 * @author Craig R. McClanahan
 * @author Cedric Dumoulin
 * @author Greg Reddin
 *
 * @version $Revision: 1.2 $ $Date: 2003/08/30 23:18:56 $
 * @since Struts 1.1
 */

public class ComposableRequestProcessor extends RequestProcessor {


    // ------------------------------------------------------ Instance Variables


    /**
     * The catalog containing all of the available command chains for this
     * module.
     */
    protected Catalog catalog;


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    protected static final Log log =
        LogFactory.getLog(ComposableRequestProcessor.class);


    // ---------------------------------------------------------- Public Methods


    /**
     * Clean up in preparation for a shutdown of this application.
     */
    public void destroy() {

        super.destroy();
        this.catalog = null;

    }


    /**
     * <p>Initialize this request processor instance.</p>
     *
     * @param servlet The ActionServlet we are associated with
     * @param moduleConfig The ModuleConfig we are associated with.
     *
     * @throws ServletException If an error occor during initialization
     */
    public void init(ActionServlet servlet,
                     ModuleConfig moduleConfig)
           throws ServletException {

        log.info("Initializing composable request processor for module prefix '"
                 + moduleConfig.getPrefix() + "'");
        super.init(servlet, moduleConfig);
        this.catalog = (Catalog)
            servlet.getServletContext().getAttribute(Constants.CATALOG_KEY);
        if (this.catalog == null) {
            // FIXME - i18n
            throw new ServletException("No Catalog has been configured");
        }

    }


    /**
     * <p>Process an <code>HttpServletRequest</code> and create the
     * corresponding <code>HttpServletResponse</code>.</p>
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a processing exception occurs
     */
    public void process(HttpServletRequest request,
                        HttpServletResponse response)
        throws IOException, ServletException {

        // Create and populate a Context for this request
        ServletWebContext context = new ServletWebContext();
        context.initialize(getServletContext(), request, response);
        context.getAttributes().put("catalog", this.catalog);
        context.getAttributes().put(Constants.ACTION_SERVLET_KEY,
                                    this.servlet);
        context.getAttributes().put(Constants.MODULE_CONFIG_KEY,
                                    this.moduleConfig);

        // Create and execute the command.
        Command command = this.catalog.getCommand("servlet-standard");
        try {
            if (log.isDebugEnabled()) {
                log.debug("Using processing chain for this request");
            }
            command.execute(context);
        } catch (Exception e) {
            // Execute the exception processing chain??
            throw new ServletException(e);
        }

        // Release the context.
        context.release();
    }


}



