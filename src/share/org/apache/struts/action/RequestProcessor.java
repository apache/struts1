/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/RequestProcessor.java,v 1.30 2003/04/19 01:16:29 dgraham Exp $
 * $Revision: 1.30 $
 * $Date: 2003/04/19 01:16:29 $
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


package org.apache.struts.action;


import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;


/**
 * <p><strong>RequestProcessor</strong> contains the processing logic that
 * the Struts controller servlet performs as it receives each servlet request
 * from the container.  You can customize the request processing behavior by
 * subclassing this class and overriding the method(s) whose behavior you are
 * interested in changing.</p>
 *
 * @author Craig R. McClanahan
 * @author Cedric Dumoulin
 * @version $Revision: 1.30 $ $Date: 2003/04/19 01:16:29 $
 * @since Struts 1.1
 */

public class RequestProcessor {


    // ----------------------------------------------------- Manifest Constants


    /**
     * The request attribute under which the path information is stored for
     * processing during a RequestDispatcher.include() call.
     */
    public static final String INCLUDE_PATH_INFO =
        "javax.servlet.include.path_info";


    /**
     * The request attribute under which the servlet path information is stored
     * for processing during a RequestDispatcher.include() call.
     */
    public static final String INCLUDE_SERVLET_PATH =
        "javax.servlet.include.servlet_path";


    // ----------------------------------------------------- Instance Variables


    /**
     * The set of Action instances that have been created and initialized,
     * keyed by the fully qualified Java class name of the Action class.
     */
    protected HashMap actions = new HashMap();


    /**
     * The ModuleConfiguration we are associated with.
     * @deprecated use moduleConfig instead.
     */
    protected ModuleConfig appConfig = null;

    /**
     * The ModuleConfiguration we are associated with.
     */
    protected ModuleConfig moduleConfig = null;


    /**
     * Commons Logging instance.
     */
    protected static Log log = LogFactory.getLog(RequestProcessor.class);


    /**
     * The controller servlet we are associated with.
     */
    protected ActionServlet servlet = null;


    // --------------------------------------------------------- Public Methods


    /**
     * Clean up in preparation for a shutdown of this application.
     */
    public void destroy() {

        synchronized (this.actions) {
            Iterator actions = this.actions.values().iterator();
            while (actions.hasNext()) {
                Action action = (Action) actions.next();
                action.setServlet(null);
            }
            this.actions.clear();
        }
        this.servlet = null;

    }


    /**
     * Initialize this request processor instance.
     *
     * @param servlet The ActionServlet we are associated with
     * @param moduleConfig The ModuleConfig we are associated with.
     * @throws ServletException If an error occor during initialization
     */
    public void init(ActionServlet servlet,
                     ModuleConfig moduleConfig)
           throws ServletException {

        synchronized (actions) {
            actions.clear();
        }
        this.servlet = servlet;
        this.appConfig = moduleConfig;
        this.moduleConfig = moduleConfig;

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

        // Wrap multipart requests with a special wrapper
        request = processMultipart(request);

        // Identify the path component we will use to select a mapping
        String path = processPath(request, response);
        if (path == null) {
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug("Processing a '" + request.getMethod() +
                      "' for path '" + path + "'");
        }

        // Select a Locale for the current user if requested
        processLocale(request, response);

        // Set the content type and no-caching headers if requested
        processContent(request, response);
        processNoCache(request, response);

        // General purpose preprocessing hook
        if (!processPreprocess(request, response)) {
            return;
        }

        // Identify the mapping for this request
        ActionMapping mapping = processMapping(request, response, path);
        if (mapping == null) {
            return;
        }

        // Check for any role required to perform this action
        if (!processRoles(request, response, mapping)) {
            return;
        }

        // Process any ActionForm bean related to this request
        ActionForm form = processActionForm(request, response, mapping);
        processPopulate(request, response, form, mapping);
        if (!processValidate(request, response, form, mapping)) {
            return;
        }

        // Process a forward or include specified by this mapping
        if (!processForward(request, response, mapping)) {
            return;
        }
        if (!processInclude(request, response, mapping)) {
            return;
        }

        // Create or acquire the Action instance to process this request
        Action action = processActionCreate(request, response, mapping);
        if (action == null) {
            return;
        }

        // Call the Action instance itself
        ActionForward forward =
            processActionPerform(request, response,
                                 action, form, mapping);

        // Process the returned ActionForward instance
        processForwardConfig(request, response, forward);

    }


    // ----------------------------------------------------- Processing Methods


    /**
     * Return an <code>Action</code> instance that will be used to process
     * the current request, creating a new one if necessary.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param mapping The mapping we are using
     *
     * @exception IOException if an input/output error occurs
     */
    protected Action processActionCreate(HttpServletRequest request,
                                         HttpServletResponse response,
                                         ActionMapping mapping)
        throws IOException {

        // Acquire the Action instance we will be using (if there is one)
        String className = mapping.getType();
        if (log.isDebugEnabled()) {
            log.debug(" Looking for Action instance for class " + className);
        }
        
        Action instance = null;
        synchronized (actions) {

            // Return any existing Action instance of this class
            instance = (Action) actions.get(className);
            if (instance != null) {
                if (log.isTraceEnabled()) {
                    log.trace("  Returning existing Action instance");
                }
                return (instance);
            }

            // Create and return a new Action instance
            if (log.isTraceEnabled()) {
                log.trace("  Creating new Action instance");
            }
            
            try {
                instance = (Action) RequestUtils.applicationInstance(className);
                // TODO Maybe we should propagate this exception instead of returning
                // null.
            } catch (Exception e) {
                log.error(
                    getInternal().getMessage("actionCreate", mapping.getPath()),
                    e);
                    
                response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    getInternal().getMessage("actionCreate", mapping.getPath()));
                    
                return (null);
            }
            
            instance.setServlet(this.servlet);
            actions.put(className, instance);
        }

        return (instance);

    }


    /**
     * Retrieve and return the <code>ActionForm</code> bean associated with
     * this mapping, creating and stashing one if necessary.  If there is no
     * form bean associated with this mapping, return <code>null</code>.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param mapping The mapping we are using
     */
    protected ActionForm processActionForm(HttpServletRequest request,
                                           HttpServletResponse response,
                                           ActionMapping mapping) {

        // Create (if necessary a form bean to use
        ActionForm instance = RequestUtils.createActionForm
            (request, mapping, moduleConfig, servlet);
        if (instance == null) {
            return (null);
        }

        // Store the new instance in the appropriate scope
        if (log.isDebugEnabled()) {
            log.debug(" Storing ActionForm bean instance in scope '" +
                mapping.getScope() + "' under attribute key '" +
                mapping.getAttribute() + "'");
        }
        if ("request".equals(mapping.getScope())) {
            request.setAttribute(mapping.getAttribute(), instance);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(mapping.getAttribute(), instance);
        }
        return (instance);

    }



    /**
     * Forward or redirect to the specified destination, by the specified
     * mechanism.
     *
     * This method takes the old ActionForward object as parameter. User should
     * use {@link #processForwardConfig(HttpServletRequest, HttpServletResponse,ForwardConfig)} when possible.
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param forward The ActionForward controlling where we go next
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     * @deprecated Use processForwardConfig() instead.
     */
    protected void processActionForward(HttpServletRequest request,
                                        HttpServletResponse response,
                                        ActionForward forward)
        throws IOException, ServletException {

        processForwardConfig( request, response, forward );

    }


    /**
     * Forward or redirect to the specified destination, by the specified
     * mechanism.  This method uses a ForwardConfig object instead an ActionForward.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param forward The ForwardConfig controlling where we go next
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected void processForwardConfig(HttpServletRequest request,
                                        HttpServletResponse response,
                                        ForwardConfig forward)
        throws IOException, ServletException {

        if (forward == null) {
            return;
        }
        
        if (log.isDebugEnabled()) {
            log.debug("processForwardConfig(" + forward + ")");
        }
        
        String forwardPath = forward.getPath();
        String uri = null;
        
        // paths not starting with / should be passed through without any processing
        // (ie. they're absolute)
        if (forwardPath.startsWith("/")) {
            uri = RequestUtils.forwardURL(request, forward);    // get module relative uri
        } else {
            uri = forwardPath;
        }
        
        if (forward.getRedirect()) {
            // only prepend context path for relative uri
            if (uri.startsWith("/")) {
                uri = request.getContextPath() + uri;
            }
            response.sendRedirect(response.encodeRedirectURL(uri));
            
        } else {
            doForward(uri, request, response);
        }

    }


    /**
     * Ask the specified <code>Action</code> instance to handle this
     * request.  Return the <code>ActionForward</code> instance (if any)
     * returned by the called <code>Action</code> for further processing.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param action The Action instance to be used
     * @param form The ActionForm instance to pass to this Action
     * @param mapping The ActionMapping instance to pass to this Action
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected ActionForward
        processActionPerform(HttpServletRequest request,
                             HttpServletResponse response,
                             Action action,
                             ActionForm form,
                             ActionMapping mapping)
        throws IOException, ServletException {

        try {
            return (action.execute(mapping, form, request, response));
        } catch (Exception e) {
            return (processException(request, response,
                                     e, form, mapping));
        }

    }


    /**
     * Set the default content type (with optional character encoding) for
     * all responses if requested.  <strong>NOTE</strong> - This header will
     * be overridden automatically if a
     * <code>RequestDispatcher.forward()</code> call is
     * ultimately invoked.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     */
    protected void processContent(HttpServletRequest request,
                                  HttpServletResponse response) {

        String contentType = moduleConfig.getControllerConfig().getContentType();
        if (contentType != null) {
            response.setContentType(contentType);
        }

    }


    /**
     * Ask our exception handler to handle the exception.  Return the
     * <code>ActionForward</code> instance (if any) returned by the
     * called <code>ExceptionHandler</code>.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are processing
     * @param exception The exception being handled
     * @param form The ActionForm we are processing
     * @param mapping The ActionMapping we are using
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected ActionForward processException(HttpServletRequest request,
                                             HttpServletResponse response,
                                             Exception exception,
                                             ActionForm form,
                                             ActionMapping mapping)
        throws IOException, ServletException {

        // Is there a defined handler for this exception?
        ExceptionConfig config = mapping.findException(exception.getClass());
        if (config == null) {
            log.warn(getInternal().getMessage("unhandledException",
                                              exception.getClass()));
            if (exception instanceof IOException) {
                throw (IOException) exception;
            } else if (exception instanceof ServletException) {
                throw (ServletException) exception;
            } else {
                throw new ServletException(exception);
            }
        }

        // Use the configured exception handling
        try {
            ExceptionHandler handler = (ExceptionHandler)
            RequestUtils.applicationInstance(config.getHandler());
            return (handler.execute(exception, config, mapping, form,
                                    request, response));
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }


    /**
     * Process a forward requested by this mapping (if any).  Return
     * <code>true</code> if standard processing should continue, or
     * <code>false</code> if we have already handled this request.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param mapping The ActionMapping we are using
     */
    protected boolean processForward(HttpServletRequest request,
                                     HttpServletResponse response,
                                     ActionMapping mapping)
        throws IOException, ServletException {

        // Are we going to processing this request?
        String forward = mapping.getForward();
        if (forward == null) {
            return (true);
        }

        internalModuleRelativeForward(forward, request, response);
        return (false);

    }


    /**
     * Process an include requested by this mapping (if any).  Return
     * <code>true</code> if standard processing should continue, or
     * <code>false</code> if we have already handled this request.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param mapping The ActionMapping we are using
     */
    protected boolean processInclude(HttpServletRequest request,
                                     HttpServletResponse response,
                                     ActionMapping mapping)
        throws IOException, ServletException {

        // Are we going to processing this request?
        String include = mapping.getInclude();
        if (include == null) {
            return (true);
        }

        internalModuleRelativeInclude(include, request, response);
        return (false);

    }


    /**
     * Automatically select a Locale for the current user, if requested.
     * <strong>NOTE</strong> - configuring Locale selection will trigger
     * the creation of a new <code>HttpSession</code> if necessary.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     */
    protected void processLocale(HttpServletRequest request,
                                 HttpServletResponse response) {

        // Are we configured to select the Locale automatically?
        if (!moduleConfig.getControllerConfig().getLocale()) {
            return;
        }

        // Has a Locale already been selected?
        HttpSession session = request.getSession();
        if (session.getAttribute(Globals.LOCALE_KEY) != null) {
            return;
        }

        // Use the Locale returned by the servlet container (if any)
        Locale locale = request.getLocale();
        if (locale != null) {
            if (log.isDebugEnabled()) {
                log.debug(" Setting user locale '" + locale + "'");
            }
            session.setAttribute(Globals.LOCALE_KEY, locale);
        }

    }


    /**
     * Select the mapping used to process the selection path for this request.
     * If no mapping can be identified, create an error response and return
     * <code>null</code>.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param path The portion of the request URI for selecting a mapping
     *
     * @exception IOException if an input/output error occurs
     */
    protected ActionMapping processMapping(HttpServletRequest request,
                                           HttpServletResponse response,
                                           String path)
        throws IOException {

        // Is there a directly defined mapping for this path?
        ActionMapping mapping = (ActionMapping)
            moduleConfig.findActionConfig(path);
        if (mapping != null) {
            request.setAttribute(Globals.MAPPING_KEY, mapping);
            return (mapping);
        }

        // Locate the mapping for unknown paths (if any)
        ActionConfig configs[] = moduleConfig.findActionConfigs();
        for (int i = 0; i < configs.length; i++) {
            if (configs[i].getUnknown()) {
                mapping = (ActionMapping) configs[i];
                request.setAttribute(Globals.MAPPING_KEY, mapping);
                return (mapping);
            }
        }

        // No mapping can be found to process this request
        log.error(getInternal().getMessage("processInvalid", path));
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                           getInternal().getMessage
                           ("processInvalid", path));
        return (null);

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
     * Set the no-cache headers for all responses, if requested.
     * <strong>NOTE</strong> - This header will be overridden
     * automatically if a <code>RequestDispatcher.forward()</code> call is
     * ultimately invoked.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     */
    protected void processNoCache(HttpServletRequest request,
                                  HttpServletResponse response) {

        if (moduleConfig.getControllerConfig().getNocache()) {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 1);
        }

    }


    /**
     * Identify and return the path component (from the request URI) that
     * we will use to select an ActionMapping to dispatch with.  If no such
     * path can be identified, create an error response and return
     * <code>null</code>.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     */
    protected String processPath(HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException {

        String path = null;

        // For prefix matching, match on the path info (if any)
        path = (String) request.getAttribute(INCLUDE_PATH_INFO);
        if (path == null) {
            path = request.getPathInfo();
        }
        if ((path != null) && (path.length() > 0)) {
            return (path);
        }

        // For extension matching, strip the module prefix and extension
        path = (String) request.getAttribute(INCLUDE_SERVLET_PATH);
        if (path == null) {
            path = request.getServletPath();
        }
        String prefix = moduleConfig.getPrefix();
        if (!path.startsWith(prefix)) {
            log.error(getInternal().getMessage("processPath",
                                         request.getRequestURI()));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                               getInternal().getMessage
                               ("processPath", request.getRequestURI()));
            return (null);
        }
        path = path.substring(prefix.length());
        int slash = path.lastIndexOf("/");
        int period = path.lastIndexOf(".");
        if ((period >= 0) && (period > slash)) {
            path = path.substring(0, period);
        }
        return (path);

    }


    /**
     * Populate the properties of the specified ActionForm instance from
     * the request parameters included with this request.  In addition,
     * request attribute <code>Globals.CANCEL_KEY</code> will be set if
     * the request was submitted with a button created by
     * <code>CancelTag</code>.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param form The ActionForm instance we are populating
     * @param mapping The ActionMapping we are using
     *
     * @exception ServletException if thrown by RequestUtils.populate()
     */
    protected void processPopulate(HttpServletRequest request,
                                   HttpServletResponse response,
                                   ActionForm form,
                                   ActionMapping mapping)
        throws ServletException {

        if (form == null) {
            return;
        }

        // Populate the bean properties of this ActionForm instance
        if (log.isDebugEnabled()) {
            log.debug(" Populating bean properties from this request");
        }
        form.setServlet(this.servlet);
        form.reset(mapping, request);
        if (mapping.getMultipartClass() != null) {
            request.setAttribute(Globals.MULTIPART_KEY,
                                 mapping.getMultipartClass());
        }
        RequestUtils.populate(form, mapping.getPrefix(), mapping.getSuffix(),
                              request);

        // Set the cancellation request attribute if appropriate
        if ((request.getParameter(Constants.CANCEL_PROPERTY) != null) ||
            (request.getParameter(Constants.CANCEL_PROPERTY_X) != null)) {
            request.setAttribute(Globals.CANCEL_KEY, Boolean.TRUE);
        }

    }


    /**
     * General-purpose preprocessing hook that can be overridden as required
     * by subclasses.  Return <code>true</code> if you want standard processing
     * to continue, or <code>false</code> if the response has already been
     * completed.  The default implementation does nothing.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     */
    protected boolean processPreprocess(HttpServletRequest request,
                                        HttpServletResponse response) {

        return (true);

    }


    /**
     * If this action is protected by security roles, make sure that the
     * current user possesses at least one of them.  Return <code>true</code>
     * to continue normal processing, or <code>false</code> if an appropriate
     * response has been created and processing should terminate.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param mapping The mapping we are using
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected boolean processRoles(HttpServletRequest request,
                                   HttpServletResponse response,
                                   ActionMapping mapping)
        throws IOException, ServletException {

        // Is this action protected by role requirements?
        String roles[] = mapping.getRoleNames();
        if ((roles == null) || (roles.length < 1)) {
            return (true);
        }

        // Check the current user against the list of required roles
        for (int i = 0; i < roles.length; i++) {
            if (request.isUserInRole(roles[i])) {
                if (log.isDebugEnabled()) {
                    log.debug(" User '" + request.getRemoteUser() +
                        "' has role '" + roles[i] + "', granting access");
                }
                return (true);
            }
        }

        // The current user is not authorized for this action
        if (log.isDebugEnabled()) {
            log.debug(" User '" + request.getRemoteUser() +
                      "' does not have any required role, denying access");
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                           getInternal().getMessage("notAuthorized",
                                                    mapping.getPath()));
        return (false);

    }


    /**
     * <p>If this request was not cancelled, and the request's
     * {@link ActionMapping} has not disabled validation, call the
     * <code>validate()</code> method of the specified {@link ActionForm},
     * and forward back to the input form if there were any errors.
     * Return <code>true</code> if we should continue processing,
     * or <code>false</code> if we have already forwarded control back
     * to the input form.</p>
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param form The ActionForm instance we are populating
     * @param mapping The ActionMapping we are using
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected boolean processValidate(HttpServletRequest request,
                                      HttpServletResponse response,
                                      ActionForm form,
                                      ActionMapping mapping)
        throws IOException, ServletException {

        if (form == null) {
            return (true);
        }

        // Was this request cancelled?
        if (request.getAttribute(Globals.CANCEL_KEY) != null) {
            if (log.isDebugEnabled()) {
                log.debug(" Cancelled transaction, skipping validation");
            }
            return (true);
        }

        // Has validation been turned off for this mapping?
        if (!mapping.getValidate()) {
            return (true);
        }

        // Call the form bean's validation method
        if (log.isDebugEnabled()) {
            log.debug(" Validating input form properties");
        }
        ActionErrors errors = form.validate(mapping, request);
        if ((errors == null) || errors.isEmpty()) {
            if (log.isTraceEnabled()) {
                log.trace("  No errors detected, accepting input");
            }
            return (true);
        }

        // Special handling for multipart request
        if (form.getMultipartRequestHandler() != null) {
            if (log.isTraceEnabled()) {
                log.trace("  Rolling back multipart request");
            }
            form.getMultipartRequestHandler().rollback();
        }

        // Has an input form been specified for this mapping?
        String input = mapping.getInput();
        if (input == null) {
            if (log.isTraceEnabled()) {
                log.trace("  Validation failed but no input form available");
            }
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               getInternal().getMessage("noInput",
                                                        mapping.getPath()));
            return (false);
        }

        // Save our error messages and return to the input form if possible
        if (log.isDebugEnabled()) {
            log.debug(" Validation failed, returning to '" + input + "'");
        }
        request.setAttribute(Globals.ERROR_KEY, errors);

        if (moduleConfig.getControllerConfig().getInputForward()) {
            ForwardConfig forward = mapping.findForward(input);
            processForwardConfig( request, response, forward);
        } else {
            internalModuleRelativeForward(input, request, response);
        }

        return (false);

    }

    /**
     * Do a module relative forward to specified uri using request dispatcher.
     * Uri is relative to the current module. The real uri is compute by prefixing
     * the module name.
     * This method is used internally and is not part of the public API. It is
     * advised to not use it in subclasses.
     * @param uri Module-relative URI to forward to
     * @param request Current page request
     * @param response Current page response
     * @since Struts 1.1
     */
    protected void internalModuleRelativeForward(
        String uri,
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {
            
        // Construct a request dispatcher for the specified path
        uri = moduleConfig.getPrefix() + uri;

        // Delegate the processing of this request
        // FIXME - exception handling?
        if (log.isDebugEnabled()) {
            log.debug(" Delegating via forward to '" + uri + "'");
        }
        doForward(uri, request, response);
    }

    /**
     * Do a module relative include to specified uri using request dispatcher.
     * Uri is relative to the current module. The real uri is compute by prefixing
     * the module name.
     * This method is used internally and is not part of the public API. It is
     * advised to not use it in subclasses.
     * @param uri Module-relative URI to include
     * @param request Current page request
     * @param response Current page response
     * @since Struts 1.1
     */
    protected void internalModuleRelativeInclude(
        String uri,
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {
            
        // Construct a request dispatcher for the specified path
        uri = moduleConfig.getPrefix() + uri;

        // Delegate the processing of this request
        // FIXME - exception handling?
        if (log.isDebugEnabled()) {
            log.debug(" Delegating via include to '" + uri + "'");
        }
        doInclude(uri, request, response);
    }

    /**
     * Do a forward to specified uri using request dispatcher.
     * This method is used by all internal method needing to do a forward.
     * @param uri Context-relative URI to forward to
     * @param request Current page request
     * @param response Current page response
     * @since Struts 1.1
     */
    protected void doForward(
        String uri,
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {
            
        // Unwrap the multipart request, if there is one.
        if (request instanceof MultipartRequestWrapper) {
            request = ((MultipartRequestWrapper) request).getRequest();
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(uri);
        if (rd == null) {
            response.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                getInternal().getMessage("requestDispatcher", uri));
            return;
        }
        rd.forward(request, response);
    }


    /**
     * Do an include of specified uri using request dispatcher.
     * This method is used by all internal method needing to do an include
     * @param uri Context-relative URI to include
     * @param request Current page request
     * @param response Current page response
     * @since Struts 1.1
     */
    protected void doInclude(
        String uri,
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {
            
        // Unwrap the multipart request, if there is one.
        if (request instanceof MultipartRequestWrapper) {
            request = ((MultipartRequestWrapper) request).getRequest();
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(uri);
        if (rd == null) {
            response.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                getInternal().getMessage("requestDispatcher", uri));
            return;
        }
        rd.include(request, response);
    }


    // -------------------------------------------------------- Support Methods


    /**
     * Return the debugging detail level that has been configured for our
     * controller servlet.
     *
     * @deprecated Configure the logging detail level in your
     *  underlying logging implementation
     */
    public int getDebug() {

        return (servlet.getDebug());

    }


    /**
     * Return the <code>MessageResources</code> instance containing our
     * internal message strings.
     */
    protected MessageResources getInternal() {

        return (servlet.getInternal());

    }


    /**
     * Return the ServletContext for the web application we are running in.
     */
    protected ServletContext getServletContext() {

        return (servlet.getServletContext());

    }

    /**
     * Log the specified message to the servlet context log for this
     * web application.
     *
     * @param message The message to be logged
     */
    protected void log(String message) {

        servlet.log(message);

    }


    /**
     * Log the specified message and exception to the servlet context log
     * for this web application.
     *
     * @param message The message to be logged
     * @param exception The exception to be logged
     */
    protected void log(String message, Throwable exception) {

        servlet.log(message, exception);

    }


}
