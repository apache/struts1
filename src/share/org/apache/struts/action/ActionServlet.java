/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionServlet.java,v 1.28 2000/10/07 22:55:13 craigmcc Exp $
 * $Revision: 1.28 $
 * $Date: 2000/10/07 22:55:13 $
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
import java.net.URL;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Vector;
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
 *     the "view" component of an MVC architecture.</li>
 * <li>Forms and hyperlinks in the user interface that require business logic
 *     to be executed will be submitted to a request URI that is mapped to the
 *     controller servlet.</li>
 * <li>There will be one instance of this servlet class,
 *     which receives and processes all requests that change the state of
 *     a user's interaction with the application.  This component represents
 *     the "controller" component of an MVC architecture.</li>
 * <li>The controller servlet will select and invoke an action class to perform
 *     the requested business logic.</li>
 * <li>The action classes will manipulate the state of the application's
 *     interaction with the user, typically by creating or modifying JavaBeans
 *     that are stored as request or session attributes (depending on how long
 *     they need to be available).  Such JavaBeans represent the "model"
 *     component of an MVC architecture.</li>
 * <li>Instead of producing the next page of the user interface directly,
 *     action classes will generally use the
 *     <code>RequestDispatcher.forward()</code> facility of the servlet API
 *     to pass control to an appropriate JSP page to produce the next page
 *     of the user interface.</li>
 * </ul>
 *
 * <p>The standard version of <code>ActionServlet</code> implements the
 *    following logic for each incoming HTTP request.  You can override
 *    some or all of this functionality by subclassing this servlet and
 *    implementing your own version of the processing.</p>
 * <ul>
 * <li>Identify, from the incoming request URI, the substring that will be
 *     used to select an action procedure.</li>
 * <li>Use this substring to map to the Java class name of the corresponding
 *     action class (an implementation of the <code>Action</code> interface).
 *     </li>
 * <li>If this is the first request for a particular action class, instantiate
 *     an instance of that class and cache it for future use.</li>
 * <li>Optionally populate the properties of an <code>ActionForm</code> bean
 *     associated with this mapping.</li>
 * <li>Call the <code>perform()</code> method of this action class, passing
 *     on a reference to the mapping that was used (thereby providing access
 *     to the underlying ActionServlet and ServletContext, as well as any
 *     specialized properties of the mapping itself), and the request and
 *     response that were passed to the controller by the servlet container.
 *     </li>
 * </ul>
 *
 * <p>The standard version of <code>ActionServlet</code> is configured based
 * on the following servlet initialization parameters, which you will specify
 * in the web application deployment descriptor (<code>/WEB-INF/web.xml</code>)
 * for your application.  Subclasses that specialize this servlet are free to
 * define additional initialization parameters.</p>
 * <ul>
 * <li><strong>application</strong> - Java class name of the application
 *     resources bundle base class.  [NONE].</li>
 * <li><strong>config</strong> - Context-relative path to the XML resource
 *     containing our configuration information.  [/WEB-INF/action.xml]</li>
 * <li><strong>debug</strong> - The debugging detail level for this
 *     servlet, which controls how much information is logged.  [0]</li>
 * <li><strong>detail</strong> - The debugging detail level for the Digester
 *     we utilize in <code>initMapping()</code>, which logs to System.out
 *     instead of the servlet log.  [0]</li>
 * <li><strong>forward</strong> - The Java class name of the ActionForward
 *     implementation to use [org.apache.struts.action.ActionForward].
 *     Two convenient classes you may wish to use are:
 *     <ul>
 *     <li><em>org.apache.struts.action.ForwardingActionForward</em> -
 *         Subclass of <code>org.apache.struts.action.ActionForward</code>
 *         that defaults the <code>redirect</code> property to
 *         <code>false</code> (same as the ActionForward default value).
 *     <li><em>org.apache.struts.action.RedirectingActionForward</em> -
 *         Subclass of <code>org.apache.struts.action.ActionForward</code>
 *         that defaults the <code>redirect</code> property to
 *         <code>true</code>.
 *     </ul></li>
 * <li><strong>locale</strong> - If set to <code>true</code>, and there is a
 *     user session, identify and store an appropriate
 *     <code>java.util.Locale</code> object (under the standard key
 *     identified by <code>Action.LOCALE_KEY</code>) in the user's session
 *     if there is not a Locale object there already.</li>
 * <li><strong>mapping</strong> - The Java class name of the ActionMapping
 *     implementation to use [org.apache.struts.action.ActionMapping].
 *     Two convenient classes you may wish to use are:
 *     <ul>
 *     <li><em>org.apache.struts.action.RequestActionMapping</em> - Subclass
 *         of <code>org.apache.struts.action.ActionMapping</code> that
 *         defaults the <code>formScope</code> property to "request".
 *     <li><em>org.apache.struts.action.SessionActionMapping</em> - Subclass
 *         of <code>org.apache.struts.action.ActionMapping</code> that
 *         defaults the <code>formScope</code> property to "session".  (Same
 *         as the ActionMapping default value).
 *     </ul></li>
 * <li><strong>nocache</strong> - If set to <code>true</code>, add HTTP headers
 *     to every response intended to defeat browser caching of any response we
 *     generate or forward to.  [false]</li>
 * <li><strong>null</strong> - If set to <code>true</code>, set our application
 *     resources to return <code>null</code> if an unknown message key is used.
 *     Otherwise, an error message including the offending message key will
 *     be returned.  [true]</li>
 * <li><strong>validate</strong> - Are we using the new configuration file
 *     format?  [false]</li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.28 $ $Date: 2000/10/07 22:55:13 $
 */

public class ActionServlet
    extends HttpServlet {


    // ----------------------------------------------------- Instance Variables


    /**
     * The set of Action instances that have been created and initialized,
     * keyed by the fully qualified Java class name.
     */
    protected Hashtable actions = new Hashtable();


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
     * The default Locale for this server.
     */
    protected final Locale defaultLocale = Locale.getDefault();


    /**
     * The Java class name of the ActionFormBean implementation class to use.
     */
    protected String formBeanClass =
        "org.apache.struts.action.ActionFormBean";


    /**
     * The global ActionFormBean collection for this controller.
     */
    protected ActionFormBeans formBeans = new ActionFormBeans();


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
     * Should we create a <code>java.util.Locale</code> for this user,
     * based on the HTTP headers of the request, if one is not present?
     */
    protected boolean locale = true;


    /**
     * The Java class name of our ActionMapping implementation class.
     */
    protected String mappingClass =
	"org.apache.struts.action.ActionMapping";


    /**
     * The configured mappings for this web application, keyed by path.
     */
    protected ActionMappings mappings = new ActionMappings();


    /**
     * Include the no-caching headers in our response?
     */
    protected boolean nocache = false;


    /**
     * The set of public identifiers, and corresponding resource names, for
     * the versions of the configuration file DTD that we know about.  There
     * <strong>MUST</strong> be an even number of Strings in this list!
     */
    protected String registrations[] = {
        "-//Apache Software Foundation//DTD Struts Configuration 1.0//EN",
        "/org/apache/struts/resources/struts-config_1_0.dtd",
    };


    /**
     * Are we using the new configuration file format?
     */
    protected boolean validate = false;


    // ---------------------------------------------------- HttpServlet Methods


    /**
     * Gracefully shut down this controller servlet, releasing any resources
     * that were allocated at initialization.
     */
    public void destroy() {

	if (debug >= 1)
	    log(internal.getMessage("finalizing"));

        destroyActions();
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
     * Register a form bean definition to the set configured for this servlet.
     *
     * @param formBean The form bean definition to be added
     */
    public void addFormBean(ActionFormBean formBean) {

        formBeans.addFormBean(formBean);

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
     * Return the form bean definition associated with the specified
     * logical name, if any; otherwise return <code>null</code>.
     *
     * @param name Logical name of the requested form bean definition
     */
    public ActionFormBean findFormBean(String name) {

        return (formBeans.findFormBean(name));

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
     * Return the debugging detail level for this servlet.
     */
    public int getDebug() {

	return (this.debug);

    }


    /**
     * Return the Java class name of the class used to instantiate
     * <code>ActionFormBean</code> objects.
     */
    public String getFormBeanClass() {

        return (this.formBeanClass);

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


    /**
     * Reload the configuration of this controller servlet from our
     * underlying configuration files.
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public void reload() throws IOException, ServletException {

        if (debug >= 1)
            log(internal.getMessage("reloading"));

        // Shut down our existing environment
        destroyActions();
        destroyApplication();
        destroyInternal();

        // Restart from our confirmation files
        initInternal();
        initDebug();
        initApplication();
        initMapping();
        initOther();

    }


    /**
     * Deregister a form bean definition from the set configured for
     * this servlet.
     *
     * @param formBean The form bean definition to be deregistered
     */
    public void removeFormBean(ActionFormBean formBean) {

        formBeans.removeFormBean(formBean);

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


    /**
     * Set the Java class name of the class used to instantiate
     * <code>ActionFormBean</code> objects.
     *
     * @param formBeanClass The new class name
     */
    public void setFormBeanClass(String formBeanClass) {

        this.formBeanClass = formBeanClass;

    }


    /**
     * Set the Java class name of the class used to instantiate
     * <code>ActionForward</code> objects.
     *
     * @param forwardClass The new class name
     */
    public void setForwardClass(String forwardClass) {

        this.forwardClass = forwardClass;

    }


    /**
     * Set the Java class name of the class used to instantiate
     * <code>ActionMapping</code> objects.
     *
     * @param mappingClass The new class name
     */
    public void setMappingClass(String mappingClass) {

        this.mappingClass = mappingClass;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Gracefully shut down any action instances we have created.
     */
    protected void destroyActions() {

        synchronized (this.actions) {
            Vector actives = new Vector();
            Enumeration actions = this.actions.elements();
            while (actions.hasMoreElements()) {
                Action action = (Action) actions.nextElement();
                action.setServlet(null);
            }
            this.actions.clear();
        }

    }


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
     * Construct and return a digester that uses the new configuration
     * file format.
     */
    protected Digester initDigester(int detail) {

	// Initialize a new Digester instance
	Digester digester = new Digester();
	digester.push(this);
	digester.setDebug(detail);
	digester.setValidating(true);

	// Register our local copy of the DTDs that we can find
        for (int i = 0; i < registrations.length; i += 2) {
            URL url = this.getClass().getResource(registrations[i+1]);
            if (url != null)
                digester.register(registrations[i], url.toString());
        }

	// Configure the processing rules

        // FIXME "struts-config/action-mappings" type attribute

        digester.addObjectCreate("struts-config/action-mappings/action",
                                 mappingClass, "className");
        digester.addSetProperties("struts-config/action-mappings/action");
        digester.addSetNext("struts-config/action-mappings/action",
                            "addMapping",
                            "org.apache.struts.action.ActionMapping");

        digester.addObjectCreate
            ("struts-config/action-mappings/action/forward",
             forwardClass, "className");
        digester.addSetProperties
            ("struts-config/action-mappings/action/forward");
        digester.addSetNext("struts-config/action-mappings/action/forward",
                            "addForward",
                            "org.apache.struts.action.ActionForward");

        digester.addSetProperty
            ("struts-config/action-mappings/action/forward/set-property",
             "property", "value");

        digester.addSetProperty
            ("struts-config/action-mappings/action/set-property",
             "property", "value");

        // FIXME "struts-config/form-beans" type attribute

        digester.addObjectCreate("struts-config/form-beans/form-bean",
                                 formBeanClass, "className");
        digester.addSetProperties("struts-config/form-beans/form-bean");
        digester.addSetNext("struts-config/form-beans/form-bean",
                            "addFormBean",
                            "org.apache.struts.action.ActionFormBean");

        // FIXME "struts-config/global-forwards" type attribute

        digester.addObjectCreate("struts-config/global-forwards/forward",
                                 forwardClass, "className");
        digester.addSetProperties("struts-config/global-forwards/forward");
        digester.addSetNext("struts-config/global-forwards/forward",
                            "addForward",
                            "org.apache.struts.action.ActionForward");

        digester.addSetProperty
            ("struts-config/global-forwards/forward/set-property",
             "property", "value");

	return (digester);

    }


    /**
     * Construct and return a digester that uses the old configuration
     * file format.
     */
    protected Digester initDigesterOld(int detail) {

	// Initialize a new Digester instance
	Digester digester = new Digester();
	digester.push(this);
	digester.setDebug(detail);
	digester.setValidating(false);

	// Configure the processing rules
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
	digester.addSetProperty("action-mappings/action/forward/property",
				"name", "value");
	digester.addSetProperty("action-mappings/action/property",
				"name", "value");
	digester.addObjectCreate("action-mappings/forward",
				 forwardClass, "className");
	digester.addSetProperties("action-mappings/forward");
	digester.addSetNext("action-mappings/forward", "addForward",
			    "org.apache.struts.action.ActionForward");
	digester.addSetProperty("action-mappings/forward/property",
				"name", "value");

	return (digester);

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

        // Link our mappings collection to this servlet instance
        mappings.setServlet(this);

	// Initialize the debugging detail level we will use
	int detail;
	try {
	    value = getServletConfig().getInitParameter("detail");
	    detail = Integer.parseInt(value);
	} catch (Throwable t) {
	    detail = 0;
	}

	// Initialize the format selector flag
	value = getServletConfig().getInitParameter("validate");
	if (value != null) {
	    if (value.equalsIgnoreCase("true") ||
	        value.equalsIgnoreCase("yes"))
	        validate = true;
	    else
	        validate = false;
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
	Digester digester = null;
	if (validate)
	    digester = initDigester(detail);
	else
	    digester = initDigesterOld(detail);

	// Parse the input stream to configure our mappings
	try {
	    digester.parse(input);
	    input.close();
	} catch (SAXException e) {
	    throw new ServletException
		(internal.getMessage("configParse", config), e);
	}

        // Transitional support for old format
        if (!validate) {
            String paths[] = mappings.findMappings();
            for (int i = 0; i < paths.length; i++) {
                String name =
                    mappings.findMapping(paths[i]).getName();
                if (name == null)
                    continue;
                ActionFormBean formBean = new ActionFormBean();
                formBean.setName(name);
                formBean.setType(name);
                formBeans.addFormBean(formBean);
            }
        }

    }


    /**
     * Initialize other configuration parameters that have not yet
     * been processed.
     *
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initOther() throws ServletException {

	// Process the "locale" and "nocache" initialization parameters
	String value = null;

        value = getServletConfig().getInitParameter("locale");
        if (value != null) {
            if ("true".equalsIgnoreCase(value) ||
                "yes".equalsIgnoreCase(value))
                locale = true;
            else
                locale = false;
        }

        if (value != null) {
            value = getServletConfig().getInitParameter("nocache");
            if ("true".equalsIgnoreCase(value) ||
                "yes".equalsIgnoreCase(value))
                nocache = true;
        }

	// Publish our internal collections as necessary
        getServletContext().setAttribute(Action.FORM_BEANS_KEY, formBeans);
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

        // Automatically select a locale for this user if requested
        processLocale(request);

	// Set the no-caching headers if requested
	processNoCache(response);

	// Look up the corresponding mapping
	ActionMapping mapping = processMapping(path, request);
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

        // Acquire the Action instance to process this request
        Action actionInstance = processActionCreate(mapping, request);
        if (actionInstance == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               internal.getMessage("actionCreate",
                                                   mapping.getPath()));
            return;
        }

	// Call the action instance itself
	processActionPerform(actionInstance, mapping, formInstance,
                             request, response);

    }


    /**
     * Create or retrieve the Action instance that will process this request,
     * or <code>null</code> if no such Action instance can be created.
     *
     * @param mapping The ActionMapping we are processing
     * @param request The servlet request we are processing
     */
    protected Action processActionCreate(ActionMapping mapping,
                                         HttpServletRequest request) {

        // Acquire the Action instance we will be using
        String actionClass = mapping.getActionClass();
        Action actionInstance = (Action) actions.get(actionClass);
        if (actionInstance == null) {
            try {
                Class clazz = Class.forName(actionClass);
                actionInstance = (Action) clazz.newInstance();
                actionInstance.setServlet(this);
                actions.put(actionClass, actionInstance);
            } catch (Throwable t) {
                log("Error creating Action instance for path '" +
                    mapping.getPath() + "', class name '" +
                    actionClass + "'", t);
                return (null);
            }
        }
        return (actionInstance);

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
	    if ("request".equals(mapping.getFormScope())) {
		formInstance =
		    (ActionForm) request.getAttribute(formAttribute);
	    } else {
		session = request.getSession();
		formInstance =
		    (ActionForm) session.getAttribute(formAttribute);
	    }
	    if (formInstance == null) {
		if (debug >= 1)
		    log(" Creating new ActionForm instance");
		formInstance = mapping.createFormInstance();
		if (formInstance != null) {
		    if (debug >= 1)
		        log(" Storing instance under attribute '" +
			    formAttribute + "'");
		    if ("request".equals(mapping.getFormScope()))
			request.setAttribute(formAttribute, formInstance);
		    else
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
     * Ask the specified Action instance to handle this request.
     *
     * @param action The Action to process this request
     * @param mapping The ActionMapping we are processing
     * @param formInstance The ActionForm we are processing
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected void processActionPerform(Action action,
                                        ActionMapping mapping,
                                        ActionForm formInstance,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
	throws IOException, ServletException {

	// Perform the requested action
	ActionForward forward =
	    action.perform(mapping, formInstance, request, response);

        // Obey any returned ActionForward
	if (forward != null) {
	    String path = forward.getPath();
	    if (forward.getRedirect()) {
	        if (path.startsWith("/"))
                    path = request.getContextPath() + path;
		response.sendRedirect(path);
	    } else {
		RequestDispatcher rd =
		    getServletContext().getRequestDispatcher(path);
		rd.forward(request, response);
	    }
	}

    }


    /**
     * Automatically calculate an appropriate <code>java.util.Locale</code>
     * for this user, and store it in their session, if there is no such
     * Locale object present already.
     *
     * @param request The request we are processing
     */
    protected void processLocale(HttpServletRequest request) {

        // Validate that we need to create a Locale
        if (!locale)
            return;             // Service not requested
        HttpSession session = request.getSession(false);
        if (session == null)
            return;             // Not in a session
        if (session.getAttribute(Action.LOCALE_KEY) != null)
            return;             // Locale object is already present

        // Use the Locale returned by the servlet container (if any)
        Locale locale = null;
        try {
            locale = request.getLocale();
        } catch (Throwable t) {
            locale = null;      // Method not present in servlet 2.1
        }
        if (locale != null) {
            if (debug >= 1)
                log("Setting locale '" + locale + "'");
            session.setAttribute(Action.LOCALE_KEY, locale);
            return;
        }

        // Calculate a Locale based on the HTTP headers with this request
        String value = request.getHeader("Accept-Language");
        if (value == null) {
            session.setAttribute(Action.LOCALE_KEY, defaultLocale);
            return;             // No Accept-Language header was present
        }

        int comma = value.indexOf(',');
        if (comma >= 0)
            value = value.substring(0, comma);  // Use first entry only
        int semi = value.indexOf(';');
        if (semi >= 0)
            value = value.substring(0, semi);   // Strip quality ranking

        String language = null;
        String country = null;
        int dash = value.indexOf('-');
        if (dash < 0) {
            language = value.trim();
            country = "";
        } else {
            language = value.substring(0, dash).trim();
            country = value.substring(dash + 1).trim();
        }
        locale = new Locale(language, country);

        // Store the calculated Locale in the user's session
        if (debug >= 1)
            log("Setting locale '" + locale + "' for header '" +
                request.getHeader("Accept-Language") + "'");
        session.setAttribute(Action.LOCALE_KEY, locale);

    }


    /**
     * Identify and return an appropriate ActionMapping for the specified
     * path.  If no such mapping can be identified, return <code>null</code>.
     * The <code>request</code> parameter is available if you need to make
     * decisions on available mappings (such as checking permissions) based
     * on request parameters or other properties, but it is not used in the
     * default implementation.
     *
     * @param path Path component used to select a mapping
     * @param request The request we are processing
     */
    protected ActionMapping processMapping(String path,
                                           HttpServletRequest request) {

        ActionMapping mapping = findMapping(path);
        if (mapping == null)
            mapping = mappings.getUnknown();
        return (mapping);

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

	String path = null;

	// For prefix matching, we want to match on the path info (if any)
	path = request.getPathInfo();
	if ((path != null) && (path.length() > 0))
	    return (path);

	// For extension matching, we want to strip the extension (if any)
	path = request.getServletPath();
	int slash = path.lastIndexOf("/");
	int period = path.lastIndexOf(".");
	if ((period >= 0) && (period > slash))
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
