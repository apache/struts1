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

package org.apache.struts.chain;


import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.CatalogFactory;
import org.apache.commons.chain.Command;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ControllerConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.apache.struts.util.RequestUtils;


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

    // ------------------------------------------------------ Instance Variables


    private static final Class[] SERVLET_ACTION_CONTEXT_CTOR_SIGNATURE = new Class[] { ServletContext.class, HttpServletRequest.class, HttpServletResponse.class };


    public static final String ACTION_CONTEXT_CLASS = "ACTION_CONTEXT_CLASS";


    /**
     * <p>The {@link CatalogFactory} from which catalog containing the the
     * base request-processing {@link Command} will be retrieved.</p>
     */
    protected CatalogFactory catalogFactory = null;


    /**
     * <p>The {@link Catalog} containing all of the available command chains
     * for this module.
     */
    protected Catalog catalog = null;


    /**
     * <p>The {@link Command} to be executed for each request.</p>
     */
    protected Command command = null;


    private Class actionContextClass;

    private Constructor servletActionContextConstructor = null;

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
        catalogFactory = null;
        catalog = null;
        command = null;
        actionContextClass = null;
        servletActionContextConstructor = null;

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

        initCatalogFactory(servlet, moduleConfig);

        ControllerConfig controllerConfig = moduleConfig.getControllerConfig();

        String catalogName = controllerConfig.getCatalog();
        catalog = this.catalogFactory.getCatalog(catalogName);
        if (catalog == null) {
            throw new ServletException("Cannot find catalog '" +
                                       catalogName + "'");
        }

        String commandName = controllerConfig.getCommand();
        command = catalog.getCommand(commandName);
        if (command == null) {
            throw new ServletException("Cannot find command '" +
                                       commandName + "'");
        }

        this.setActionContextClassName( controllerConfig.getProperty(ACTION_CONTEXT_CLASS) );
        
    }

    private void setActionContextClass(Class actionContextClass) throws ServletException {
        this.actionContextClass = actionContextClass;
        // if there is a custom class provided and if it uses our "preferred" constructor,
        // cache a reference to that constructor rather than looking it up every time.
        if (actionContextClass != null) {
            this.servletActionContextConstructor = ConstructorUtils.getAccessibleConstructor(actionContextClass, SERVLET_ACTION_CONTEXT_CTOR_SIGNATURE);
        } else {
            this.servletActionContextConstructor = null;
        }
        
    }

    /**
     * Make sure that the specified <code>className</code> identfies a class which can be found
     * and which implements the <code>ActionContext</code> interface.
     * @param className
     * @throws ServletException
     */
    private void setActionContextClassName(String className) throws ServletException {
        if (className != null && className.trim().length() > 0) {
            log.debug("setActionContextClassName: requested context class: " + className);
            try {
                Class actionContextClass = RequestUtils.applicationClass(className);
                if (!ActionContext.class.isAssignableFrom(actionContextClass)) {
                    throw new UnavailableException("ActionContextClass [" + className + "] must implement ActionContext interface.");
                }
                this.setActionContextClass(actionContextClass);
            } catch (ClassNotFoundException e) {
                throw new UnavailableException("ActionContextClass " + className + " not found.");
            }
        } else {
            log.debug("setActionContextClassName: no actionContextClass specified");
            this.setActionContextClass(null);
        }
    }

    /**
     * <p>Establish the <code>CatalogFactory</code> which will be used to look up
     * the catalog which has the request processing command.</p>
     * <p>The base implementation simply calls <code>CatalogFactory.getInstance()</code>,
     * unless the <code>catalogFactory</code> property of this object has already been set,
     * in which case it is not changed.</p>
     * @param servlet
     * @param moduleConfig
     */
    protected void initCatalogFactory(ActionServlet servlet, ModuleConfig moduleConfig) {
        if (this.catalogFactory != null) {
            return;
        }
        this.catalogFactory = CatalogFactory.getInstance();

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
        ActionContext context = contextInstance(request, response);

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
     * Provide the initialized <code>ActionContext</code> instance which will be used by this request.
     * Internally, this simply calls <code>createActionContextInstance</code> followed by 
     * <code>initializeActionContext</code>. 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     */
    protected ActionContext contextInstance(HttpServletRequest request,
                                            HttpServletResponse response) throws ServletException {
        ActionContext context = createActionContextInstance(getServletContext(), request, response);
        initializeActionContext(context);
        return context;
    }

    /**
     * Create a new instance of <code>ActionContext</code> according to configuration.  If no alternative was specified at initialization, 
     * a new instance <code>ServletActionContext</code> is returned.  If an alternative was specified using the 
     * <code>ACTION_CONTEXT_CLASS</code> property, then that value is treated as a classname, and an instance of that class
     * is created.  If that class implements the same constructor that <code>ServletActionContext</code> does, then that constructor
     * will be used: <code>ServletContext, HttpServletRequest, HttpServletResponse</code>; otherwise, it is assumed that the class
     * has a no-arguments constructor.  If these constraints do not suit you, simply override this method in a subclass.
     * @param servletContext
     * @param request
     * @param response
     * @return
     * @throws ServletException
     */
    protected ActionContext createActionContextInstance(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (this.actionContextClass == null) return new ServletActionContext(servletContext, request, response);
        try {
            if (this.servletActionContextConstructor == null) return (ActionContext) this.actionContextClass.newInstance();
            return (ActionContext) this.servletActionContextConstructor.newInstance(new Object[] { servletContext, request, response });
        } catch (Exception e) {
            throw new ServletException("Error creating ActionContext instance of type " + this.actionContextClass, e);
        }
    }
    
    /**
     * Set common properties on the given <code>ActionContext</code> instance so that commands in the chain
     * can count on their presence.  Note that while this method does not require that its argument 
     * be an instance of <code>ServletActionContext</code>, at this time many common Struts
     * commands will be expecting to receive an <code>ActionContext</code> which is also a <code>ServletActionContext</code>.
     * @param context
     */
    protected void initializeActionContext(ActionContext context) {
        if (context instanceof ServletActionContext) {
            ((ServletActionContext) context).setActionServlet(this.servlet);
        }
        context.setModuleConfig(this.moduleConfig);
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

    /**
     * <p>Set the <code>CatalogFactory</code> instance which should be used to find
     * the request-processing command.  In the base implementation, if this value is not
     * already set, then it will be initialized when {@link initCatalogFactory} is called.
     * </p>
     * @param catalogFactory
     */
    public void setCatalogFactory(CatalogFactory catalogFactory) {
        this.catalogFactory = catalogFactory;
    }
}



