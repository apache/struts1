/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionServlet.java,v 1.62 2001/02/23 21:13:09 craigmcc Exp $
 * $Revision: 1.62 $
 * $Date: 2001/02/23 21:13:09 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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


import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.digester.Digester;
import org.apache.struts.digester.Rule;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.ConvertUtils;
import org.apache.struts.util.FastHashMap;
import org.apache.struts.util.GenericDataSource;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.RequestUtils;
import org.xml.sax.AttributeList;
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
 *     resources bundle base class.  [NONE]</li>
 * <li><strong>bufferSize</strong> - The size of the input buffer used when
 *     processing file uploads.  [4096]</li>
 * <li><strong>config</strong> - Context-relative path to the XML resource
 *     containing our configuration information.
 *     [/WEB-INF/struts-config.xml]</li>
 * <li><strong>content</strong> - Default content type and character encoding
 *     to be set on each response; may be overridden by a forwarded-to
 *     servlet or JSP page.  [text/html]</li>
 * <li><strong>debug</strong> - The debugging detail level for this
 *     servlet, which controls how much information is logged.  [0]</li>
 * <li><strong>detail</strong> - The debugging detail level for the Digester
 *     we utilize in <code>initMapping()</code>, which logs to System.out
 *     instead of the servlet log.  [0]</li>
 * <li><strong>factory</strong> - The Java class name of the
 *     <code>MessageResourcesFactory</code> used to create the application
 *     <code>MessageResources</code> object.</li>
 * <li><strong>formBean</strong> - The Java class name of the ActionFormBean
 *     implementation to use [org.apache.struts.action.ActionFormBean].
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
 *         defaults the <code>scope</code> property to "request".
 *     <li><em>org.apache.struts.action.SessionActionMapping</em> - Subclass
 *         of <code>org.apache.struts.action.ActionMapping</code> that
 *         defaults the <code>scope</code> property to "session".  (Same
 *         as the ActionMapping default value).
 *     </ul></li>
 * <li><strong>maxFileSize</strong> - The maximum size (in bytes) of a file
 *     to be accepted as a file upload.  Can be expressed as a number followed
 *     by a "K" "M", or "G", which are interpreted to mean kilobytes,
 *     megabytes, or gigabytes, respectively.  [250M]</li>
 * <li><strong>multipartClass</strong> - The fully qualified name of the
 *     MultiplartRequestHandler implementation class to be used for processing
 *     file uploads.  [org.apache.struts.upload.DiskMultipartRequestHandler]
 *     </li>
 * <li><strong>nocache</strong> - If set to <code>true</code>, add HTTP headers
 *     to every response intended to defeat browser caching of any response we
 *     generate or forward to.  [false]</li>
 * <li><strong>null</strong> - If set to <code>true</code>, set our application
 *     resources to return <code>null</code> if an unknown message key is used.
 *     Otherwise, an error message including the offending message key will
 *     be returned.  [true]</li>
 * <li><strong>tempDir</strong> - The temporary working directory to use when
 *     processing file uploads.  [The working directory provided to this web
 *     application as a servlet context attribute]</li>
 * <li><strong>validate</strong> - Are we using the new configuration file
 *     format?  [true]</li>
 * <li><strong>validating</strong> - Should we use a validating XML parse to
 *     process the configuration file (strongly recommended)? [true]</li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.62 $ $Date: 2001/02/23 21:13:09 $
 */

public class ActionServlet
    extends HttpServlet {


    // ----------------------------------------------------- Instance Variables


    /**
     * The set of Action instances that have been created and initialized,
     * keyed by the fully qualified Java class name.
     */
    protected FastHashMap actions = new FastHashMap();


    /**
     * The resources object for our application resources (if any).
     */
    protected MessageResources application = null;


    /**
     * The context-relative path to our configuration resource.
     */
    protected String config = "/WEB-INF/struts-config.xml";


    /**
     * The default content type and character encoding to be set on each
     * response (may be overridden by forwarded-to resources).
     */
    protected String content = "text/html";


    /**
     * The JDBC data sources that has been configured for this application,
     * if any, keyed by the servlet context attribute under which they are
     * stored.
     */
    protected FastHashMap dataSources = new FastHashMap();


    /**
     * The debugging detail level for this servlet.
     */
    protected int debug = 0;


    /**
     * The default Locale for this server.
     */
    protected final Locale defaultLocale = Locale.getDefault();


    /**
     * The Java class name of the <code>MessageResourcesFactory</code>
     * class for the application message resources bundle.
     */
    protected String factoryClass = null;


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
     * the versions of the configuration file DTDs that we know about.  There
     * <strong>MUST</strong> be an even number of Strings in this list!
     */
    protected String registrations[] = {
        "-//Apache Software Foundation//DTD Struts Configuration 1.0//EN",
        "/org/apache/struts/resources/struts-config_1_0.dtd",
        "-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN",
        "/org/apache/struts/resources/web-app_2_2.dtd",
        "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN",
        "/org/apache/struts/resources/web-app_2_3.dtd"
    };


    /**
     * The URL pattern to which we are mapped in our web application
     * deployment descriptor.
     */
    protected String servletMapping = null;


    /**
     * The servlet name under which we are registered in our web application
     * deployment descriptor.
     */
    protected String servletName = null;


    /**
     * Are we using the new configuration file format?
     */
    protected boolean validate = true;
    

    /**
     * Should we use a validating XML parser to read the configuration file?
     */
    protected boolean validating = true;


    /**
     * The size in bytes of the buffer used to read files from a client upload
     */
    protected int bufferSize = 4096;
    
    /**
     * The maximum size allowed for a client upload.  A suffix of "K"
     * represents Kilobytes, a suffix of "M" represents "Megabytes", 
     * a suffix of "G" represents Gigabytes, and no suffix is taken
     * as bytes.
     */
    protected String maxFileSize = "250M";
    
    /**
     * The MultipartRequestHandler class name used for handling
     * multipart form requests.  This is the global default value,
     * the handler can also be set in individual mapping entries
     */
    protected String multipartClass = "org.apache.struts.upload.DiskMultipartRequestHandler";
    
    /**
     * The directory used to store temporary files for the DiskMultipartRequestHandler
     * multipart implementation
     */
    protected String tempDir;





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
        destroyDataSources();
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

        initActions();
	initInternal();
	initDebug();
	initApplication();
	try {
	    initMapping();
	} catch (IOException e) {
	    throw new UnavailableException
		(internal.getMessage("configIO", config));
	}
        initUpload();
        initDataSources();
	initOther();
        initServlet();

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
     * Add a data source object to be used by this application.
     *
     * @param key The servlet context attribute key under which to store
     *  this data source, or <code>null</code> for the default
     * @param dataSource The data source to be used
     */
    public void addDataSource(String key, DataSource dataSource) {

        if (key == null)
            key = Action.DATA_SOURCE_KEY;
        dataSources.put(key, dataSource);

    }


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
     * Remember a servlet mapping from our web application deployment
     * descriptor, if it is for this servlet.
     *
     * @param servletName The name of the servlet being mapped
     * @param urlPattern The URL pattern to which this servlet is mapped
     */
    public void addServletMapping(String servletName, String urlPattern) {

        if (debug >= 1)
            log("Process servletName=" + servletName +
                ", urlPattern=" + urlPattern);
        if (servletName == null)
            return;
        if (servletName.equals(this.servletName))
            this.servletMapping = urlPattern;

    }


    /**
     * Return a JDBC data source associated with this application, if any.
     *
     * @param key The servlet context attribute key under which this data
     *  source is stored, or <code>null</code> for the default.
     */
    public DataSource findDataSource(String key) {

        if (key == null)
            return ((DataSource) dataSources.get(Action.DATA_SOURCE_KEY));
        else
            return ((DataSource) dataSources.get(key));

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
     * Get the buffer size (how large of a chunk of data is
     * recieved by the input stream at once) used for file
     * uploading.
     * 
     * @return The size in bytes of the buffer
     */
    public int getBufferSize() {

        return bufferSize;

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
     * Get the maximum file size.  See {@link #setMaxFileSize(java.lang.String) setMaxFileSize}
     * for information on the number format used.
     */
    public String getMaxFileSize() {
        return maxFileSize;
    }
    
    /**
     * Get the class name of the MultipartRequestHandler implementation
     * 
     * @return A qualified classname of the MultipartRequestHandler implementation
     */
     public String getMultipartClass() {
        return multipartClass;
    }



    /**
     * Return the application resources for this web application, if any.
     */
    public MessageResources getResources() {

	return (application);

    }
    
    /**
     * Get the directory used to temporarily store form files
     *
     * @return A platform-dependant String representing the path to the temporary directory
     */
    public String getTempDir() {
        return tempDir;
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
        destroyDataSources();
        destroyInternal();

        // Restart from our confirmation files
        initActions();
        initInternal();
        initDebug();
        initApplication();
        initMapping();
        initUpload();
        initDataSources();
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
     * Set the buffer size (how large of a chunk of data is
     * recieved by the input stream at once) used for file
     * uploading.
     *
     * @param bufferSize The size in bytes of the buffer
     */
    public void setBufferSize(int bufferSize) {

        this.bufferSize = bufferSize;

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
    
    /**
     * Set the maximum file size that a client can upload,  number String with a trailing
     * letter indicating the size.  "K" indicates "kilobytes", "M" indicates "megabytes",
     * "G" indicates "gigabytes".  If there's no trailing letter the suffix is assumed to
     * indicate the number is in bytes.  For example, to set a maximum file size of
     * 500 megabytes, you'd call <code>setMaxFileSize</code>("<i>500M</i>").
     *
     * @param maxFileSize A String representing the maximum file size.
     */
    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
    
    /**
     * Set the class name of the MultipartRequestHandler implementation
     *
     * @param multipartClass A qualified classname of the MultipartRequestHandler implementation
     */
    public void setMultipartClass(String multipartClass) {
        this.multipartClass = multipartClass;
    }

    
    /**
     * Set the directory used to temporarily store files for MultipartRequestHandler
     * implementations that write to the disk
     *
     * @param tempDir A platform-dependant String representing the path to the temporary directory
     */
    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Gracefully shut down any action instances we have created.
     */
    protected void destroyActions() {

        synchronized (this.actions) {
            Iterator actions = this.actions.values().iterator();
            while (actions.hasNext()) {
                Action action = (Action) actions.next();
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
     * Gracefully terminate use of the data source associated with this
     * application (if any).
     */
    protected void destroyDataSources() {

        synchronized (dataSources) {
            Iterator keys = dataSources.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                getServletContext().removeAttribute(key);
                DataSource dataSource = findDataSource(key);
                if (dataSource instanceof GenericDataSource) {
                    if (debug >= 1)
                        log(internal.getMessage("dataSource.destroy", key));
                    try {
                        ((GenericDataSource) dataSource).close();
                    } catch (SQLException e) {
                        log(internal.getMessage("destroyDataSource", key), e);
                    }
                }
            }
            dataSources.setFast(false);
        }

    }


    /**
     * Gracefully terminate use of the internal MessageResources.
     */
    protected void destroyInternal() {

	internal = null;

    }


    /**
     * Initialize the collection of previously instantiated Action instances.
     */
    protected void initActions() {

        synchronized (actions) {
            actions.setFast(false);
            actions.clear();
            actions.setFast(true);
        }

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
        String factory =
            getServletConfig().getInitParameter("factory");
	if (debug >= 1)
	    log(internal.getMessage("applicationLoading", value));
	try {
            String oldFactory =
                MessageResourcesFactory.getFactoryClass();      
            if (factory != null)
                MessageResourcesFactory.setFactoryClass(factory);
            MessageResourcesFactory factoryObject =
                MessageResourcesFactory.createFactory();
            application = factoryObject.createResources(value);
            MessageResourcesFactory.setFactoryClass(oldFactory);
	    value = getServletConfig().getInitParameter("null");
	    if (value == null)
		value = "true";
	    if (value.equalsIgnoreCase("true") ||
		value.equalsIgnoreCase("yes"))
		application.setReturnNull(true);
	    else
		application.setReturnNull(false);
	} catch (Throwable e) {
	    log(internal.getMessage("applicationResources", value), e);
	    throw new UnavailableException
		(internal.getMessage("applicationResources", value));
	}
	getServletContext().setAttribute(Action.MESSAGES_KEY, application);

    }


    /**
     * Initialize use of the data sources associated with this
     * application (if any).
     */
    protected void initDataSources() {

        synchronized (dataSources) {
            Iterator keys = dataSources.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                DataSource dataSource = findDataSource(key);
                if (dataSource instanceof GenericDataSource) {
                    if (debug >= 1)
                        log(internal.getMessage("dataSource.init", key));
                    try {
                        ((GenericDataSource) dataSource).open();
                    } catch (SQLException e) {
                        log(internal.getMessage("initDataSource", key), e);
                    }
                }
                getServletContext().setAttribute(key, dataSource);
            }
            dataSources.setFast(true);
        }

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
	digester.setValidating(validating);

	// Register our local copy of the DTDs that we can find
        for (int i = 0; i < registrations.length; i += 2) {
            URL url = this.getClass().getResource(registrations[i+1]);
            if (url != null)
                digester.register(registrations[i], url.toString());
        }

	// Configure the processing rules

        digester.addObjectCreate("struts-config/data-sources/data-source",
                                 "org.apache.struts.util.GenericDataSource",
                                 "type");
        digester.addSetProperties("struts-config/data-sources/data-source");
        digester.addRule("struts-config/data-sources/data-source",
                         new AddDataSourceRule(digester));
        digester.addSetProperty
            ("struts-config/data-sources/data-source/set-property",
             "property", "value");

        digester.addObjectCreate("struts-config/action-mappings/action",
                                 mappingClass, "className");
        digester.addSetProperties("struts-config/action-mappings/action");
        digester.addSetNext("struts-config/action-mappings/action",
                            "addMapping",
                            "org.apache.struts.action.ActionMapping");

        digester.addSetProperty
            ("struts-config/action-mappings/action/set-property",
             "property", "value");

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

        digester.addObjectCreate("struts-config/form-beans/form-bean",
                                 formBeanClass, "className");
        digester.addSetProperties("struts-config/form-beans/form-bean");
        digester.addSetNext("struts-config/form-beans/form-bean",
                            "addFormBean",
                            "org.apache.struts.action.ActionFormBean");

        digester.addSetProperty
            ("struts-config/form-beans/form-bean/set-property",
             "property", "value");

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

	// Initialize the validating XML parser flag
	value = getServletConfig().getInitParameter("validating");
	if (value != null) {
	    if (value.equalsIgnoreCase("true") ||
	        value.equalsIgnoreCase("yes"))
	        validating = true;
	    else
	        validating = false;
	}

        // Initialize the name of our ActionFormBean implementation class
        value = getServletConfig().getInitParameter("formBean");
        if (value != null)
            formBeanClass = value;

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
            formBeans.setFast(false);
            forwards.setFast(false);
            mappings.setFast(false);
	    digester.parse(input);
            mappings.setFast(true);
            forwards.setFast(true);
            formBeans.setFast(true);
	} catch (SAXException e) {
	    throw new ServletException
		(internal.getMessage("configParse", config), e);
        } finally {
	    input.close();
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

	// Process the "content", "locale", and "nocache" parameters
	String value = null;

        value = getServletConfig().getInitParameter("content");
        if (value != null)
            content = value;

        value = getServletConfig().getInitParameter("locale");
        if (value != null) {
            if ("true".equalsIgnoreCase(value) ||
                "yes".equalsIgnoreCase(value))
                locale = true;
            else
                locale = false;
        }

        value = getServletConfig().getInitParameter("nocache");
        if (value != null) {
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
     * Initialize the servlet mapping under which our controller servlet
     * is being accessed.  This will be used in the <code>&html:form&gt;</code>
     * tag to generate correct destination URLs for form submissions.
     */
    protected void initServlet() throws ServletException {

        // Remember our servlet name
        this.servletName = getServletConfig().getServletName();

        // Prepare a Digester to scan the web application deployment descriptor
        Digester digester = new Digester();
        digester.push(this);
        digester.setDebug(this.debug);
        digester.setValidating(false);

	// Register our local copy of the DTDs that we can find
        for (int i = 0; i < registrations.length; i += 2) {
            URL url = this.getClass().getResource(registrations[i+1]);
            if (url != null)
                digester.register(registrations[i], url.toString());
        }

        // Configure the processing rules that we need
        digester.addCallMethod("web-app/servlet-mapping",
                               "addServletMapping", 2);
        digester.addCallParam("web-app/servlet-mapping/servlet-name", 0);
        digester.addCallParam("web-app/servlet-mapping/url-pattern", 1);

        // Process the web application deployment descriptor
        InputStream input= null;
        try {
            input =
                getServletContext().getResourceAsStream("/WEB-INF/web.xml");
            digester.parse(input);
        } catch (Throwable e) {
            log(internal.getMessage("configWebXml"), e);
        } finally {
            if (input != null)
                input = null;
        }

        // Record a servlet context attribute (if appropriate)
        if (debug >= 1)
            log("Mapping for servlet '" + servletName + "' = '" +
                servletMapping + "'");
        if (servletMapping != null)
            getServletContext().setAttribute(Action.SERVLET_KEY,
                                             servletMapping);

    }


    /**
     * Initialize upload parameters and "bufferSize", "multipartClass",
     * "maxFileSize", "tempDir"
     *
     * @exception ServletException if there are invalid parameters
     */
    protected void initUpload() throws ServletException {
      
        //buffer size
        String bufferValue = getServletConfig().getInitParameter("bufferSize");
        
        if ((bufferValue != null) && (bufferValue.length() > 0)) {
            int oldBufferSize = bufferSize;
            try {
                bufferSize = Integer.parseInt(bufferValue, 10);
            }
            catch (NumberFormatException nfe) {
                if (debug > 0) {
                    log("initUpload(): invalid value \"" + bufferSize + "\" for " +
                        "init-parameter \"buffer size\"" +
                        ", defaulting to \"" + oldBufferSize + "\"");
                }
                bufferSize = oldBufferSize;
            }
        }
        
        //multipart class
        String classValue = getServletConfig().getInitParameter("multipartClass");
        
        if ((classValue != null) && (classValue.length() > 0)) {
            multipartClass = classValue;
        }
        
        //maximum file size
        String maxsizeValue = getServletConfig().getInitParameter("maxFileSize");
        
        if ((maxsizeValue != null) && (maxsizeValue.length() > 0)) {
            maxFileSize = maxsizeValue;
        }
        
        //temp directory
        String tempDirValue = getServletConfig().getInitParameter("tempDir");
        
        if ((tempDirValue != null) && (tempDirValue.length() > 0)) {
            tempDir = tempDirValue;
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

	// Set the content type and no-caching headers if requested
        processContent(response);
	processNoCache(response);

        // General purpose preprocessing hook
        if (!processPreprocess(request, response))
            return;

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
        processPopulate(formInstance, mapping, request);
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

	// Call the Action instance itself
        ActionForward forward =
            processActionPerform(actionInstance, mapping, formInstance,
                                 request, response);

        // Process the returned ActionForward (if any)
        processActionForward(forward, mapping, formInstance,
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
        String actionClass = mapping.getType();
        if (debug >= 1)
            log(" Looking for Action instance for class " + actionClass);
        Action actionInstance = (Action) actions.get(actionClass);
        if (actionInstance == null) {
            synchronized (actions) {
                if (debug >= 1)
                    log("  Double checking for Action instance already there");
                // Double check to avoid a race condition
                actionInstance = (Action) actions.get(actionClass);
                if (actionInstance != null)
                    return (actionInstance);
                // Go ahead and create the new Action instance
                // ASSERT:  This will never ever happen more than once
                //  for a particular action class name
                try {
                    if (debug >= 1)
                        log("  Creating new Action instance");
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
        }
        return (actionInstance);

    }


    /**
     * Retrieve and return the <code>ActionForm</code> bean associated with
     * this mapping, creating and stashing one if necessary.  If there is no
     * form bean associated with this mapping, return <code>null</code>.
     *
     * @param mapping The ActionMapping we are processing
     * @param request The servlet request we are processing
     */
    protected ActionForm processActionForm(ActionMapping mapping,
    					   HttpServletRequest request) {

        // Is there a form bean associated with this mapping?
	String attribute = mapping.getAttribute();
        if (attribute == null)
            return (null);

        // Look up the existing form bean, if any
        if (debug >= 1)
            log(" Looking for ActionForm bean under attribute '" +
                attribute + "'");
	ActionForm instance = null;
	HttpSession session = null;
        if ("request".equals(mapping.getScope())) {
            instance = (ActionForm) request.getAttribute(attribute);
        } else {
            session = request.getSession();
            instance = (ActionForm) session.getAttribute(attribute);
        }

	// Determine the form bean class that we expect to use
        String name = mapping.getName();
        String className = null;
        ActionFormBean formBean = findFormBean(name);
        if (formBean != null)
            className = formBean.getType();
	else
            return (null);

	// Can we recycle the existing form bean instance?
	if ((instance != null) &&
	    className.equals(instance.getClass().getName())) {
	    if (debug >= 1)
	        log(" Recycling existing ActionForm bean instance of class '"
		    + className + "'");
	    return (instance);
	}

        // Create a new form bean if we need to
        if (debug >= 1)
            log(" Creating new ActionForm instance of class '"
		+ className + "'");
	try {
	    instance = null;
	    Class clazz = Class.forName(className);
	    instance = (ActionForm) clazz.newInstance();
        } catch (Throwable t) {
	    log("Error creating ActionForm instance of class '" +
		className + "'", t);
	}
        if (instance == null)
            return (null);

        // Store the newly created bean in the appropriate scope
        if (debug >= 1)
            log(" Storing instance under attribute '" +
                attribute + "' in scope '" + mapping.getScope() + "'");
        if ("request".equals(mapping.getScope()))
            request.setAttribute(attribute, instance);
        else
            session.setAttribute(attribute, instance);
        return (instance);

    }


    /**
     * Forward to the specified destination, by the specified mechanism,
     * if an <code>ActionForward</code> instance was returned by the
     * <code>Action</code>.
     *
     * @param forward The ActionForward returned by our action
     * @param mapping The ActionMapping we are processing
     * @param formInstance The ActionForm we are processing
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected void processActionForward(ActionForward forward,
                                        ActionMapping mapping,
                                        ActionForm formInstance,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
	throws IOException, ServletException {

	if (forward != null) {
	    String path = forward.getPath();
	    if (forward.getRedirect()) {
	        if (path.startsWith("/"))
                    path = request.getContextPath() + path;
		response.sendRedirect(response.encodeRedirectURL(path));
	    } else {
		RequestDispatcher rd =
		    getServletContext().getRequestDispatcher(path);
		rd.forward(request, response);
	    }
	}

    }


    /**
     * Ask the specified Action instance to handle this request.  Return
     * the <code>ActionForward</code> instance (if any) returned by
     * the called <code>Action</code>.
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
    protected ActionForward processActionPerform(Action action,
                                        ActionMapping mapping,
                                        ActionForm formInstance,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
	throws IOException, ServletException {

	ActionForward forward =
	    action.perform(mapping, formInstance, request, response);
        return (forward);

    }


    /**
     * Set the default content type (with optional character encoding) for
     * all responses.  This value may be overridden by forwarded-to servlets
     * or JSP pages.
     *
     * @param response The response we are processing
     */
    protected void processContent(HttpServletResponse response) {

        if (content != null)
            response.setContentType(content);

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
        HttpSession session = request.getSession();
        if (session.getAttribute(Action.LOCALE_KEY) != null)
            return;             // Locale object is already present

        // Use the Locale returned by the servlet container (if any)
        Locale locale = request.getLocale();
        if (locale != null) {
            if (debug >= 1)
                log("Setting locale '" + locale + "'");
            session.setAttribute(Action.LOCALE_KEY, locale);
        }

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
            mapping = mappings.getUnknown(request);
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
        path =
            (String) request.getAttribute("javax.servlet.include.path_info");
        if (path == null)
            path = request.getPathInfo();
	if ((path != null) && (path.length() > 0))
	    return (path);

	// For extension matching, we want to strip the extension (if any)
        path =
           (String) request.getAttribute("javax.servlet.include.servlet_path");
        if (path == null)
            path = request.getServletPath();
	int slash = path.lastIndexOf("/");
	int period = path.lastIndexOf(".");
	if ((period >= 0) && (period > slash))
	    path = path.substring(0, period);
	return (path);

    }


    /**
     * General purpose preprocessing hook that can be overridden to support
     * application specific preprocessing activity.  This hook can examine
     * and/or modify the properties of the request and response objects, and
     * optionally complete the response if it wishes.
     * <p>
     * The default implementation does nothing.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are generating
     *
     * @return <code>true</code> if the remainder of the standard processing
     *  should be performed, or <code>false</code> if the response has already
     *  been created so the calling method should immediately exit
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected boolean processPreprocess(HttpServletRequest request,
                                        HttpServletResponse response)
        throws IOException, ServletException {

        return (true);  // Default implementation does nothing

    }


    /**
     * Populate the properties of the specified ActionForm from the request
     * parameters included with this request.
     *
     * @param formInstance The ActionForm we are processing
     * @param mapping The ActionMapping we are processing
     * @param request The servlet request we are processing
     *
     * @exception ServletException if thrown by RequestUtils.populate()
     */
    protected void processPopulate(ActionForm formInstance,
                                   ActionMapping mapping,
                                   HttpServletRequest request)
	throws ServletException {

        if (formInstance == null)
            return;
        //set the servlet of the ActionForm
        formInstance.setServlet(this);

        // Populate the bean properties of this ActionForm instance
        if (debug >= 1)
            log(" Populating bean properties from this request");
        formInstance.reset(mapping, request);
        //place the mapping's multipart request handler class
        //into the request to be read by the RequestUtils.populate
        //method in the event of a multipart request
        if (mapping.getMultipartClass() != null)
            request.setAttribute(Action.MULTIPART_KEY,
                                mapping.getMultipartClass());
        //also pass the mapping through the request
        request.setAttribute(Action.MAPPING_KEY,
                             mapping);
        RequestUtils.populate(formInstance, mapping.getPrefix(),
                              mapping.getSuffix(), request);

    }


    /**
     * Call the <code>validate()</code> method of the specified ActionForm,
     * and forward back to the input form if there are any errors.  Return
     * <code>true</code> if we should continue processing (and call the
     * <code>Action</code> class <code>perform()</code> method), or return
     * <code>false</code> if we have already forwarded control back to the
     * input form.
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

        if (formInstance == null)
            return (true);
        if (debug >= 1)
            log(" Validating input form properties");

        // Was this submit cancelled?
	if ((request.getParameter(Constants.CANCEL_PROPERTY) != null) ||
            (request.getParameter(Constants.CANCEL_PROPERTY_X) != null)) {
            if (debug >= 1)
                log("  Cancelled transaction, no validation");
            return (true);
        }

        // Has validation been turned off on this mapping?
        if (!mapping.getValidate())
            return (true);

        // Call the validate() method of our ActionForm bean
        ActionErrors errors = formInstance.validate(mapping, request);
        if ((errors == null) || errors.empty()) {
            if (debug >= 1)
                log("  No errors detected, accepting input");
            return (true);
        }
        
        //does our form have a multipart request?
        if (formInstance.getMultipartRequestHandler() != null) {
            //rollback the request
            if (debug > 1) {
                log("  Rolling back the multipart request");
            }
            
            formInstance.getMultipartRequestHandler().rollback();
        }

        // Has an input form been specified for this mapping?
	String uri = mapping.getInput();
        if (uri == null) {
            if (debug >= 1)
                log("  No input form, but validation returned errors");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               internal.getMessage("noInput",
                                                   mapping.getPath()));
            return (false);
        }

	// Save our error messages and return to the input form if possible
	if (debug >= 1)
	    log("  Validation error(s), redirecting to: " + uri);
	request.setAttribute(Action.ERROR_KEY, errors);
	RequestDispatcher rd = getServletContext().getRequestDispatcher(uri);
	rd.forward(request, response);
	return (false);

    }


}


// ------------------------------------------------------------ Private Classes


/**
 * Private digester <code>Rule</code> that adds a data source to the underlying
 * <code>ActionServlet</code> instance.  The servlet context attributes key
 * is specified by the "key" attribute on the data source element, and defaults
 * to the value of <code>Action.DATA_SOURCE_KEY</code> if not specified.
 */

final class AddDataSourceRule extends Rule {


    public AddDataSourceRule(Digester digester) {

        super(digester);

    }


    public void begin(AttributeList attributes) throws Exception {

        // Acquire the key under which this data source will be stored
        String key = null;
        for (int i = 0; i < attributes.getLength(); i++) {
            if ("key".equals(attributes.getName(i))) {
                key = attributes.getValue(i);
                break;
            }
        }
        if (key == null)
            key = Action.DATA_SOURCE_KEY;

        // Pass the data source at the top of the stack to the action servlet
        // at the next-to-top position
        DataSource child = (DataSource) digester.peek(0);
        ActionServlet parent = (ActionServlet) digester.peek(1);
        if (digester.getDebug() >= 1)
            digester.log("Call " + parent.getClass().getName() +
                         ".addDataSource(" + key + ", " + child + ")");
        parent.addDataSource(key, child);

    }

}
