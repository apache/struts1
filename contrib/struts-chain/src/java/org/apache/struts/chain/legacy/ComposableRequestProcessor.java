/*
 * Copyright 1999-2004 The Apache Software Foundation.
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

package org.apache.struts.chain.legacy;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.CatalogFactory;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.upload.MultipartRequestWrapper;


/**
 * <p><strong>ComposableRequestProcessor</strong> uses the <em>Chain Of
 * Resposibility</em> design pattern (as implemented by the commons-chain
 * package in Jakarta Commons) to support external configuration of command
 * chains to be used.  It is configured via the following context initialization
 * parameters:</p>
 * <ul>
 * <li><strong>org.apache.struts.chain.CATALOG_NAME</strong> - Name of the
 *     <code>Catalog</code> in which we will look up the <code>Command</code>
 *     to be executed for each request.  If not specified, the default value
 *     is <code>struts</code>.</li>
 * <li><strong>org.apache.struts.chain.COMMAND_NAME</strong> - Name of the
 *     <code>Command</code> which we will execute for each request, to be
 *     looked up in the specified <code>Catalog</code>.  If not specified,
 *     the default value is <code>servlet-standard</code>.</li>
 * </ul>
 *
 * @version $Rev$ $Date$
 * @since Struts 1.1
 */

public class ComposableRequestProcessor extends RequestProcessor {


    // ------------------------------------------------------ Manifest Constants


    /**
     * <p>Name of the context initialization parameter containing the
     * name of the <code>Catalog</code> we will use.</p>
     */
    private static final String CATALOG_NAME =
        "org.apache.struts.chain.CATALOG_NAME";


    /**
     * <p>Name of the <code>Command</code> to be executed for each request.</p>
     */
    private static final String COMMAND_NAME =
        "org.apache.struts.chain.COMMAND_NAME";


    // ------------------------------------------------------ Instance Variables


    /**
     * <p>The {@link Catalog} containing all of the available command chains
     * for this module.
     */
    protected Catalog catalog = null;


    /**
     * <p>The {@link Command} to be executed for each request.</p>
     */
    protected Command command = null;


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
        catalog = null;
        command = null;

    }


    /**
     * <p>Initialize this request processor instance.</p>
     *
     * @param servlet The ActionServlet we are associated with
     * @param moduleConfig The ModuleConfig we are associated with.
     *
     * @throws ServletException If an error occurs during initialization
     */
    public void init(ActionServlet servlet,
                     ModuleConfig moduleConfig)
           throws ServletException {

        log.info("Initializing composable request processor for module prefix '"
                 + moduleConfig.getPrefix() + "'");
        super.init(servlet, moduleConfig);

        String catalogName =
            servlet.getServletContext().getInitParameter(CATALOG_NAME);
        if (catalogName == null) {
            catalogName = "struts";
        }
        catalog = CatalogFactory.getInstance().getCatalog(catalogName);
        if (catalog == null) {
            throw new ServletException("Cannot find catalog '" +
                                       catalogName + "'");
        }

        String commandName =
            servlet.getServletContext().getInitParameter(COMMAND_NAME);
        if (commandName == null) {
            commandName = "servlet-standard";
        }
        command = catalog.getCommand(commandName);
        if (command == null) {
            throw new ServletException("Cannot find command '" +
                                       commandName + "'");
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

        // Wrap the request in the case of a multipart request
        request = processMultipart(request);
        
        // Create and populate a Context for this request
        ServletWebContext context = new ServletWebContext();
        context.initialize(getServletContext(), request, response);
        context.put(Constants.ACTION_SERVLET_KEY,
                    this.servlet);
        context.put(Constants.MODULE_CONFIG_KEY,
                    this.moduleConfig);

        // Create and execute the command.
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
    

    /**
     * If this is a multipart request, wrap it with a special wrapper.
     * Otherwise, return the request unchanged.
     *
     * @param request The HttpServletRequest we are processing
     */
    protected HttpServletRequest processMultipart(HttpServletRequest request) {

        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            return (request);
        }
        
        String contentType = request.getContentType();
        if ((contentType != null) &&
            contentType.startsWith("multipart/form-data")) {
            return (new MultipartRequestWrapper(request));
        } else {
            return (request);
        }

    }


}



