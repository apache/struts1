/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionServlet.java,v 1.2 2000/06/02 22:26:25 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2000/06/02 22:26:25 $
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


import java.util.Hashtable;
import java.util.MissingResourceException;
import java.io.InputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.struts.digester.Digester;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * <strong>ActionServlet</strong> represents the "controller" in the
 * Model-View-Controller (MVC) design pattern for web applications that is
 * commonly known as "Model 2".  This nomenclature originated with a
 * description in the JavaServerPages Specification, version 0.92, and has
 * persisted ever since (in the absence of a better name).
 * <p>
 * Generally, a "Model 2" application is architected as follows:
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
 * <p>
 * The specific function of the controller is to perform the following logic
 * for each incoming HTTP request:
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
 * <p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2000/06/02 22:26:25 $
 */

public class ActionServlet
    extends HttpServlet {


    // ----------------------------------------------------- Instance Variables


    /**
     * The resources object for our application resources (if any).
     */
    protected MessageResources application = null;


    /**
     * The Java base name of our application resources (if any).
     */
    protected String applicationName = null;


    /**
     * The resources object for our internal resources.
     */
    protected MessageResources internal = null;


    /**
     * The Java base name of our internal resources.
     */
    protected String internalName = "org.apache.struts.action.ActionResources";


    /**
     * The context-relative path to our configuration resource.
     */
    protected String config = "/WEB-INF/action.xml";


    /**
     * The debugging detail level for this servlet.
     */
    protected int debug = 0;


    /**
     * The Java class name of our ActionMapping implementation class.
     */
    protected String mappingClass =
	"org.apache.struts.action.ActionMappingBase";


    /**
     * The configured mappings for this web application, keyed by path.
     */
    protected Hashtable mappings = new Hashtable();


    // ---------------------------------------------------- HttpServlet Methods


    /**
     * Gracefully shut down this controller servlet, releasing any resources
     * that were allocated at initialization.
     */
    public void destroy() {

	if (debug >= 1)
	    log(internal.getMessage("finalizing"));

	if (application != null)
	    getServletContext().removeAttribute(Action.MESSAGES_KEY);

    }


    /**
     * Initialize this servlet, including loading the configuration information
     * for our mapping tables.  The following servlet initialization parameters
     * are processed, with default values in square brackets:
     * <ul>
     * <li><strong>application</strong> - Java class name of the application
     *     resources bundle base class.  [NONE].
     * <li><strong>config</strong> - Context-relative path to the XML resource
     *     containing our configuration information.  [/WEB-INF/action.xml]
     * <li><strong>debug</strong> - The debugging detail level for this
     *     servlet, which controls how much information is logged.  [0]
     * <li><strong>mapping</strong> - The Java class name of the ActionMapping
     *     implementation to use [org.apache.struts.action.ActionMappingBase]
     * </ul>
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void init() throws ServletException {

	// Load our internationalized resource bundle
	try {
	    internal = new MessageResources(internalName);
	} catch (MissingResourceException e) {
	    throw new UnavailableException("Cannot load internal resources");
	}

	// Process our servlet initialization parameters
	String value;
	value = getServletConfig().getInitParameter("application");
	if (value != null)
	    applicationName = value;
	value = getServletConfig().getInitParameter("config");
	if (value != null)
	    config = value;
	value = getServletConfig().getInitParameter("debug");
	try {
	    debug = Integer.parseInt(value);
	} catch (Throwable t) {
	    debug = 0;
	}
	value = getServletConfig().getInitParameter("mapping");
	if (value != null)
	    mappingClass = value;

	// Load the application resource bundle (if any)
	if (applicationName != null) {
	    try {
		if (debug >= 1)
		    log("Loading application resources from bundle " + applicationName);
		application = new MessageResources(applicationName);
		getServletContext().setAttribute(Action.MESSAGES_KEY,
					       application);
	    } catch (MissingResourceException e) {
		throw new UnavailableException
		    (internal.getMessage("applicationResources",
					 applicationName));
	    }
	}

	// Process our configuration resource
	try {
	    configure();
	} catch (IOException e) {
	    throw new UnavailableException
		(internal.getMessage("configIO", config));
	}

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
     * Return the application resources for this web application, if any.
     */
    public MessageResources getResources() {

	return (application);

    }


    /**
     * Register a mapping to the set configured for this servlet.
     *
     * @param mapping The mapping to be added
     */
    public void addMapping(ActionMapping mapping) {

	mappings.put(mapping.getPath(), mapping);

    }


    /**
     * Deregister a mapping from the set configured for this servlet.
     *
     * @param mapping The mapping to be deregistered
     */
    public void removeMapping(ActionMapping mapping) {

	mappings.remove(mapping.getPath());

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Process our configuration resource to establish the required mappings.
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if we cannot successfully configure
     */
    protected void configure() throws IOException, ServletException {

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
	digester.setDebug(debug);
	digester.setValidating(false);
	digester.addObjectCreate("action-mappings/action", mappingClass);
	digester.addSetProperties("action-mappings/action");
	digester.addSetNext("action-mappings/action", "addMapping",
			    "org.apache.struts.action.ActionMapping");

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
	String path = request.getServletPath();
	int slash = path.lastIndexOf("/");
	int period = path.lastIndexOf(".");
	if ((period >= 0) && (period > slash))	// Strip the extenson (if any)
	    path = path.substring(0, period);
	if (debug >= 1)
	    log("Processing a " + request.getMethod() + " for " + path);

	// Look up the corresponding mapping
	ActionMapping mapping = (ActionMapping) mappings.get(path);
	if (mapping == null) {
	    if (debug >= 1)
		log(" No mapping available for path " + path);
	    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
			       internal.getMessage("processInvalid", path));
	    return;
	}

	// Process any ActionForm bean related to this request
	HttpSession session = null;
	ActionForm formInstance = null;
	String formAttribute = mapping.getFormAttribute();
	if (formAttribute != null) {
	    if (debug >= 1)
	        log(" Looking for ActionForm bean under attribute " + formAttribute);
	    session = request.getSession();
	    if (session == null) {
		response.sendError
		    (HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
		     internal.getMessage("sessionCreate"));
		return;
	    }
	    formInstance = (ActionForm) session.getAttribute(formAttribute);
	    if (formInstance == null) {
		if (debug >= 1)
		    log(" Creating new ActionForm instance");
		formInstance = mapping.createFormInstance();
		if (formInstance != null) {
		    if (debug >= 1)
		        log(" Storing instance under attribute " + formAttribute);
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

	// Call the action instance itself
	Action actionInstance = mapping.createActionInstance();
	if (actionInstance == null) {
	    if (debug >= 1)
	        log("Could not create an ActionInstance");
	    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			       internal.getMessage("actionCreate", path));
	    return;
	}
	actionInstance.perform(this, mapping, formInstance,
			       request, response);

    }


}
