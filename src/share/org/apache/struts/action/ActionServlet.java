/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionServlet.java,v 1.14 2000/06/30 00:46:36 craigmcc Exp $
 * $Revision: 1.14 $
 * $Date: 2000/06/30 00:46:36 $
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


package org.apache.struts.action;


import java.io.InputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.digester.Digester;
import org.apache.struts.taglib.Constants;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;
import org.xml.sax.SAXException;


/**
 * <p><strong>ActionServlet</strong> represents the "controller" in the
 * Model-View-Controller (MVC) design pattern for web applications that is
 * commonly known as "Model 2".  This nomenclature originated with a
 * description in the JavaServerPages Specification, version 0.92, and has
 * persisted ever since (in the absence of a better name).</p>
 *
 * <p>Generally, a "Model 2" application is architected as follows:</p>
 * <ul>
 * <li>The user interface will generally be created with JSP pages, which
 *     will not themselves contain any business logic.  These pages represent
 *     the "view" component of an MVC architecture.
 * <li>Forms and hyperlinks in the user interface that require business logic
 *     to be executed will be submitted to a request URI that is mapped to the
 *     controller servlet.
 * <li>There will be one instance of this servlet class,
 *     which receives and processes all requests that change the state of
 *     a user's interaction with the application.  This component represents
 *     the "controller" component of an MVC architecture.
 * <li>The controller servlet will select and invoke an action class to perform
 *     the requested business logic.
 * <li>The action classes will manipulate the state of the application's
 *     interaction with the user, typically by creating or modifying JavaBeans
 *     that are stored as request or session attributes (depending on how long
 *     they need to be available).  Such JavaBeans represent the "model"
 *     component of an MVC architecture.
 * <li>Instead of producing the next page of the user interface directly,
 *     action classes will generally use the
 *     <code>RequestDispatcher.forward()</code> facility of the servlet API
 *     to pass control to an appropriate JSP page to produce the next page
 *     of the user interface.
 * </ul>
 *
 * <p>The standard version of <code>ActionServlet</code> implements the
 *    following logic for each incoming HTTP request.  You can override
 *    some or all of this functionality by subclassing this servlet and
 *    implementing your own version of the processing.</p>
 * <ul>
 * <li>Identify, from the incoming request URI, the substring that will be
 *     used to select an action procedure.
 * <li>Use this substring to map to the Java class name of the corresponding
 *     action class (an implementation of the <code>Action</code> interface).
 * <li>If this is the first request for a particular action class, instantiate
 *     an instance of that class and cache it for future use.
 * <li>Optionally populate the properties of an <code>ActionForm</code> bean
 *     associated with this mapping.
 * <li>Call the <code>perform()</code> method of this action class, passing
 *     on a reference to the mapping that was used (thereby providing access
 *     to the underlying ActionServlet and ServletContext, as well as any
 *     specialized properties of the mapping itself), and the request and
 *     response that were passed to the controller by the servlet container.
 * </ul>
 *
 * <p>The standard version of <code>ActionServlet</code> is configured based
 * on the following servlet initialization parameters, which you will specify
 * in the web application deployment descriptor (<code>/WEB-INF/web.xml</code>)
 * for your application.  Subclasses that specialize this servlet are free to
 * define additional initialization parameters.</p>
 * <ul>
 * <li><strong>application</strong> - Java class name of the application
 *     resources bundle base class.  [NONE].
 * <li><strong>config</strong> - Context-relative path to the XML resource
 *     containing our configuration information.  [/WEB-INF/action.xml]
 * <li><strong>debug</strong> - The debugging detail level for this
 *     servlet, which controls how much information is logged.  [0]
 * <li><strong>digester</strong> - The debugging detail level for the Digester
 *     we utilize in <code>initMapping()</code>, which logs to System.out
 *     instead of the servlet log.  [0]
 * <li><strong>forward</strong> - The Java class name of the ActionForward
 *     implementation to use [org.apache.struts.action.ActionForward]
 * <li><strong>mapping</strong> - The Java class name of the ActionMapping
 *     implementation to use [org.apache.struts.action.ActionMappingBase]
 * <li><strong>nocache</strong> - If set to <code>true</code>, add HTTP headers
 *     to every response intended to defeat browser caching of any response we
 *     generate or forward to.  [false]
 * <li><strong>null</strong) - If set to <code>true</code>, set our application
 *     resources to return <code>null</code> if an unknown message key is used.
 *     Otherwise, an error message including the offending message key will
 *     be returned.  [true]
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.14 $ $Date: 2000/06/30 00:46:36 $
 */

public class ActionServlet
    extends HttpServlet {


    // ----------------------------------------------------- Instance Variables


    /**
     * The resources object for our application resources (if any).
     */
    protected MessageResources application = null;


    /**
     * The context-relative path to our configuration resource.
     */
    protected String config = "/WEB-INF/action.xml";


    /**
     * The debugging detail level for this servlet.
     */
    protected int debug = 0;


    /**
     * The Java class name of the ActionForward implementation class to use.
     */
    protected String forwardClass =
	"org.apache.struts.action.ActionForward";


    /**
     * The global ActionForward collection for this controller.
     */
    protected ActionForwards forwards = new ActionForwards();


    /**
     * The resources object for our internal resources.
     */
    protected MessageResources internal = null;


    /**
     * The Java base name of our internal resources.
     */
    protected String internalName = "org.apache.struts.action.ActionResources";


    /**
     * The Java class name of our ActionMapping implementation class.
     */
    protected String mappingClass =
	"org.apache.struts.action.ActionMappingBase";


    /**
     * The configured mappings for this web application, keyed by path.
     */
    protected ActionMappings mappings = new ActionMappings();


    /**
     * Include the no-caching headers in our response?
     */
    protected boolean nocache = false;


    // ---------------------------------------------------- HttpServlet Methods


    /**
     * Gracefully shut down this controller servlet, releasing any resources
     * that were allocated at initialization.
     */
    public void destroy() {

	if (debug >= 1)
	    log(internal.getMessage("finalizing"));

	destroyApplication();
	destroyInternal();

    }


    /**
     * Initialize this servlet.  Most of the processing has been factored into
     * support methods so that you can override particular functionality at a
     * fairly granular level.
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void init() throws ServletException {

	initInternal();
	initDebug();
	initApplication();
	try {
	    initMapping();
	} catch (IOException e) {
	    throw new UnavailableException
		(internal.getMessage("configIO", config));
	}
	initOther();

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

	process(request, response);

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

	process(request, response);

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Return the debugging detail level for this servlet.
     */
    public int getDebug() {

	return (this.debug);

    }


    /**
     * Return the Java class name of the class used to instantiate
     * <code>ActionForward</code> objects.
     */
    public String getForwardClass() {

	return (this.forwardClass);

    }


    /**
     * Return the Java class name of the class used to instantiate
     * <code>ActionMapping</code> objects.
     */
    public String getMappingClass() {

	return (this.mappingClass);

    }


    /**
     * Return the application resources for this web application, if any.
     */
    public MessageResources getResources() {

	return (application);

    }


    /**
     * Register a logical forwarding to the set configured for this servlet.
     *
     * @param forward The forwarding to be added
     */
    public void addForward(ActionForward forward) {

	forwards.addForward(forward);

    }


    /**
     * Register a mapping to the set configured for this servlet.
     *
     * @param mapping The mapping to be added
     */
    public void addMapping(ActionMapping mapping) {

	mappings.addMapping(mapping);

    }


    /**
     * Return the forwarding associated with the specified logical name,
     * if any; otherwise return <code>null</code>.
     *
     * @param name Logical name of the requested forwarding
     */
    public ActionForward findForward(String name) {

	return (forwards.findForward(name));

    }


    /**
     * Return the mapping associated with the specified request path, if any;
     * otherwise return <code>null</code>.
     *
     * @param path Request path for which a mapping is requested
     */
    public ActionMapping findMapping(String path) {

	return (mappings.findMapping(path));

    }


    /**
     * Deregister a forwarding from the set configured for this servlet.
     *
     * @param forward The forwarding to be deregistered
     */
    public void removeForward(ActionForward forward) {

	forwards.removeForward(forward);

    }


    /**
     * Deregister a mapping from the set configured for this servlet.
     *
     * @param mapping The mapping to be deregistered
     */
    public void removeMapping(ActionMapping mapping) {

	mappings.removeMapping(mapping);

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Gracefully terminate use of the application MessageResources (if any).
     */
    protected void destroyApplication() {

	if (application != null)
	    getServletContext().removeAttribute(Action.MESSAGES_KEY);
	application = null;

    }


    /**
     * Gracefully terminate use of the internal MessageResources.
     */
    protected void destroyInternal() {

	internal = null;

    }


    /**
     * Initialize the MessageResources bundle for this application, if any.
     *
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initApplication() throws ServletException {

	String value = getServletConfig().getInitParameter("application");
	if (value == null)
	    return;
	if (debug >= 1)
	    log(internal.getMessage("applicationLoading", value));
	try {
	    application = MessageResources.getMessageResources(value);
	    value = getServletConfig().getInitParameter("null");
	    if (value == null)
		value = "true";
	    if (value.equalsIgnoreCase("true") ||
		value.equalsIgnoreCase("yes"))
		application.setReturnNull(true);
	    else
		application.setReturnNull(false);
	} catch (MissingResourceException e) {
	    log(internal.getMessage("applicationResources", value), e);
	    throw new UnavailableException
		(internal.getMessage("applicationResources", value));
	}
	getServletContext().setAttribute(Action.MESSAGES_KEY, application);

    }


    /**
     * Initialize the debugging detail level for this application.
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
     * Initialize our internal MessageResources bundle.
     *
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initInternal() throws ServletException {

	try {
	    internal = MessageResources.getMessageResources(internalName);
	} catch (MissingResourceException e) {
	    log("Cannot load internal resources from '" + internalName + "'",
		e);
	    throw new UnavailableException
		("Cannot load internal resources from '" + internalName + "'");
	}

    }


    /**
     * Initialize the mapping information for this application.
     *
     * @exception IOException if an input/output error is encountered
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initMapping() throws IOException, ServletException {

	String value = null;

	// Initialize the debugging detail level we will use
	int detail;
	try {
	    value = getServletConfig().getInitParameter("digester");
	    detail = Integer.parseInt(value);
	} catch (Throwable t) {
	    detail = 0;
	}

	// Initialize the name of our ActionForward implementation class
	value = getServletConfig().getInitParameter("forward");
	if (value != null)
	    forwardClass = value;

	// Initialize the name of our ActionMapping implementation class
	value = getServletConfig().getInitParameter("mapping");
	if (value != null)
	    mappingClass = value;

	// Initialize the context-relative path to our configuration resources
	value = getServletConfig().getInitParameter("config");
	if (value != null)
	    config = value;
	if (debug >= 1)
	    log(internal.getMessage("configInit", config));

	// Acquire an input stream to our configuration resource
	InputStream input = getServletContext().getResourceAsStream(config);
	if (input == null)
	    throw new UnavailableException
		(internal.getMessage("configMissing", config));

	// Build a digester to process our configuration resource
	Digester digester = new Digester();
	digester.push(this);
	digester.setDebug(detail);
	digester.setValidating(false);
	digester.addObjectCreate("action-mappings/action", mappingClass,
				 "className");
	digester.addSetProperties("action-mappings/action");
	digester.addSetNext("action-mappings/action", "addMapping",
			    "org.apache.struts.action.ActionMapping");
	digester.addObjectCreate("action-mappings/action/forward",
				 forwardClass, "className");
	digester.addSetProperties("action-mappings/action/forward");
	digester.addSetNext("action-mappings/action/forward", "addForward",
			    "org.apache.struts.action.ActionForward");
	digester.addObjectCreate("action-mappings/forward",
				 forwardClass, "className");
	digester.addSetProperties("action-mappings/forward");
	digester.addSetNext("action-mappings/forward", "addForward",
			    "org.apache.struts.action.ActionForward");

	// Parse the input stream to configure our mappings
	try {
	    digester.parse(input);
	    input.close();
	} catch (SAXException e) {
	    throw new ServletException
		(internal.getMessage("configParse", config), e);
	}

    }


    /**
     * Initialize other configuration parameters that have not yet
     * been processed.
     *
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initOther() throws ServletException {

	// Process the "nocache" initialization parameter
	String value = getServletConfig().getInitParameter("nocache");
	if ("true".equalsIgnoreCase(value) ||
	    "yes".equalsIgnoreCase(value))
	    nocache = true;

	// Publish our ActionForwards and ActionMappings collections
	getServletContext().setAttribute(Action.FORWARDS_KEY, forwards);
	getServletContext().setAttribute(Action.MAPPINGS_KEY, mappings);

    }


    /**
     * Process an HTTP request.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected void process(HttpServletRequest request,
			   HttpServletResponse response)
	throws IOException, ServletException {

	// Identify the path component we will use to select a mapping
	String path = processPath(request);
	if (path == null) {
	    if (debug >= 1)
		log(" No path available for request URI " +
		    request.getRequestURI());
	    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
			       internal.getMessage("processPath"));
	    return;
	}
	if (debug >= 1)
	    log("Processing a " + request.getMethod() + " for " + path);

	// Set the no-caching headers if requested
	processNoCache(response);

	// Look up the corresponding mapping
	ActionMapping mapping = processMapping(path);
	if (mapping == null) {
	    if (debug >= 1)
		log(" No mapping available for path " + path);
	    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
			       internal.getMessage("processInvalid", path));
	    return;
	}

	// Process any ActionForm bean related to this request
	ActionForm formInstance = processActionForm(mapping, request);
	if (!processValidate(mapping, formInstance, request, response))
	    return;

	// Call the action instance itself
	processActionInstance(mapping, formInstance, request, response);

    }


    /**
     * Process the <code>ActionForm</code> bean associated with this
     * mapping, if any.  Return the <code>ActionForm</code> instance if
     * we created or utilized one.
     *
     * @param mapping The ActionMapping we are processing
     * @param request The servlet request we are processing
     *
     * @exception ServletException if thrown by BeanUtils.populate()
     */
    protected ActionForm processActionForm(ActionMapping mapping,
    					   HttpServletRequest request)
	throws ServletException {

	HttpSession session = null;
	ActionForm formInstance = null;
	String formAttribute = mapping.getFormAttribute();
	if (formAttribute != null) {
	    if (debug >= 1)
	        log(" Looking for ActionForm bean under attribute '" +
		    formAttribute + "'");
	    session = request.getSession();
	    formInstance = (ActionForm) session.getAttribute(formAttribute);
	    if (formInstance == null) {
		if (debug >= 1)
		    log(" Creating new ActionForm instance");
		formInstance = mapping.createFormInstance();
		if (formInstance != null) {
		    if (debug >= 1)
		        log(" Storing instance under attribute '" +
			    formAttribute + "'");
		    session.setAttribute(formAttribute, formInstance);
	        }
	    }
	}
	if (formInstance != null) {
	    if (debug >= 1)
	        log(" Populating bean properties from this request");
	    BeanUtils.populate(formInstance, mapping.getFormPrefix(),
			       mapping.getFormSuffix(), request);
        }
	return (formInstance);

    }


    /**
     * Identify and call an appropriate <code>Action</code> instance
     * to handle this request.
     *
     * @param mapping The ActionMapping we are processing
     * @param formInstance The ActionForm we are processing
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected void processActionInstance(ActionMapping mapping,
					 ActionForm formInstance,
					 HttpServletRequest request,
					 HttpServletResponse response)
	throws IOException, ServletException {

	// Identify the action class we will be using
	Action actionInstance = mapping.createActionInstance();
	if (actionInstance == null) {
	    if (debug >= 1)
	        log("Could not create an ActionInstance for '" +
		    mapping.getPath() + "'");
	    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			       internal.getMessage("actionCreate",
						   mapping.getPath()));
	    return;
	}

	// Perform the requested action
	ActionForward forward =
	    actionInstance.perform(this, mapping, formInstance,
				   request, response);
	if (forward != null) {
	    String path = forward.getPath();
	    if (forward.getRedirect())
		response.sendRedirect(path);
	    else {
		RequestDispatcher rd =
		    getServletContext().getRequestDispatcher(path);
		rd.forward(request, response);
	    }
	}

    }


    /**
     * Identify and return an appropriate ActionMapping for the specified
     * path.  If no such mapping can be identified, return <code>null</code>.
     *
     * @param path Path component used to select a mapping
     */
    protected ActionMapping processMapping(String path) {

	return (findMapping(path));

    }


    /**
     * Render the HTTP headers to defeat browser caching if requested.
     *
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected void processNoCache(HttpServletResponse response)
	throws IOException, ServletException {

	if (!nocache)
	    return;

	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 1);

    }


    /**
     * Identify and return the path component (from the request URI) that
     * we will use to select an ActionMapping to dispatch with.  If no such
     * path can be identified, return <code>null</code>.
     *
     * @param request The servlet request we are processing
     */
    protected String processPath(HttpServletRequest request) {

	String path = request.getServletPath();
	int slash = path.lastIndexOf("/");
	int period = path.lastIndexOf(".");
	if ((period >= 0) && (period > slash))	// Strip the extenson (if any)
	    path = path.substring(0, period);
	return (path);

    }


    /**
     * If this form is a ValidatingActionForm, perform the required validation
     * and forward back to the input form if there are any errors.  Return
     * <code>true</code> if we should continue processing (and call the Action
     * class perform() method), or <code>false</code> if have already forwarded
     * control back to the input form.
     *
     * @param mapping The ActionMapping we are processing
     * @param formInstance The ActionForm we are processing
     * @param request The servlet request we are processing
     * @param response The servlet response we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected boolean processValidate(ActionMapping mapping,
        ActionForm formInstance, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {

	// Skip validation if it is not appropriate
	if (formInstance == null)
	    return (true);
	if (!(formInstance instanceof ValidatingActionForm))
	    return (true);
	if (mapping.getInputForm() == null)
	    return (true);
	if (request.getParameter(Constants.CANCEL_PROPERTY) != null)
	    return (true);

	// Perform the requested validation
	if (debug >= 1)
	    log(" Validating bean properties");
	String errors[] = ((ValidatingActionForm) formInstance).validate();
	if ((errors == null) || (errors.length == 0))
	    return (true);

	// Save our error messages and return to the input form
	String uri = mapping.getInputForm();
	if (debug >= 1)
	    log(" Form validation error: redirecting to: " + uri);
	request.setAttribute(Action.ERROR_KEY, errors);
	RequestDispatcher rd = getServletContext().getRequestDispatcher(uri);
	rd.forward(request, response);
	return (false);

    }


}
