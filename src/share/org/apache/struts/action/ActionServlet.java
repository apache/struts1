/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionServlet.java,v 1.150 2003/06/28 06:16:34 dgraham Exp $
 * $Revision: 1.150 $
 * $Date: 2003/06/28 06:16:34 $
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
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.MissingResourceException;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.CharacterConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.config.ConfigRuleSet;
import org.apache.struts.config.ControllerConfig;
import org.apache.struts.config.DataSourceConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.MessageResourcesConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;
import org.apache.struts.config.ModuleConfigFactory;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.util.GenericDataSource;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ServletContextWriter;
import org.xml.sax.InputSource;
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
 * <li>There will be <b>one</b> instance of this servlet class,
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
 * <li>Call the <code>execute</code> method of this action class, passing
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
 * define additional initialization parameters. Several of these were
 * deprecated between the 1.0 and 1.1 releases. The deprecated parameters
 * are listed after the nominal parameters.</p>
 * <ul>
 * <li><strong>config</strong> - Comma-separated list of context-relative
 *     path(s) to the XML resource(s) containing the configuration information
 *     for the default module.  (Multiple files support since Struts 1.1)
 *     [/WEB-INF/struts-config.xml].</li>
 * <li><strong>config/${module}</strong> - Comma-separated list of
 *     Context-relative path(s) to the XML resource(s)
 *     containing the configuration information for the module that
 *     will use the specified prefix (/${module}). This can be repeated as many
 *     times as required for multiple modules. (Since Struts 1.1)</li>
 * <li><strong>convertNull</strong> - Force simulation of the Struts 1.0 behavior
 *     when populating forms. If set to true, the numeric Java wrapper class types
 *     (like <code>java.lang.Integer</code>) will default to null (rather than 0).
 *     (Since Struts 1.1) [false] </li>
 * <li><strong>rulesets</strong> - Comma-delimited list of fully qualified
 *     classnames of additional <code>org.apache.commons.digester.RuleSet</code>
 *     instances that should be added to the <code>Digester</code> that will
 *     be processing <code>struts-config.xml</code> files.  By default, only
 *     the <code>RuleSet</code> for the standard configuration elements is
 *     loaded.  (Since Struts 1.1)</li>
 * <li><strong>validating</strong> - Should we use a validating XML parser to
 *     process the configuration file (strongly recommended)? [true]</li>
 * </ul>
 * <p>The following parameters may still be used with the Struts 1.1 release but
 * are <b>deprecated</b>.
 * <ul>
 * <li><strong>application</strong> - Java class name of the application
 *     resources bundle base class.  [NONE]
 *     <em>DEPRECATED - Configure this using the "parameter" attribute
 *     of the &lt;message-resources&gt; element.</em></li>
 * <li><strong>bufferSize</strong> - The size of the input buffer used when
 *     processing file uploads.  [4096]
 *     <em>DEPRECATED - Configure this using the "bufferSize" attribute
 *     of the &lt;controller&gt; element.</em></li>
 * <li><strong>content</strong> - Default content type and character encoding
 *     to be set on each response; may be overridden by a forwarded-to
 *     servlet or JSP page.  [text/html]
 *     <em>DEPRECATED - Configure this using the "contentType" attribute
 *     of the &lt;controller&gt; element.</em></li>
 * <li><strong>debug</strong> - TThe debugging detail level that controls how much
 *     information is logged for this servlet. Accepts values 0 (off) and from
 *     1 (least serious) through 6 (most serious). [0]
 *     <em>DEPRECATED - Configure the logging detail level in your
 *     underlying logging implementation.</em></li>
 * <li><strong>factory</strong> - The Java class name of the
 *     <code>MessageResourcesFactory</code> used to create the application
 *     <code>MessageResources</code> object.
 *     [org.apache.struts.util.PropertyMessageResourcesFactory]
 *     <em>DEPRECATED - Configure this using the "factory" attribute
 *     of the &lt;message-resources&gt; element.</em></li>
 * <li><strong>formBean</strong> - The Java class name of the ActionFormBean
 *     implementation to use [org.apache.struts.action.ActionFormBean].
 *     <em>DEPRECATED - Configure this using the "className" attribute
 *     of each &lt;form-bean&gt; element.</em></li>
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
 *     </ul>
 *     <em>DEPRECATED - Configure this using the "className" attribute of
 *     each &lt;forward&gt; element.</em></li>
 * <li><strong>locale</strong> - If set to <code>true</code>, and there is a
 *     user session, identify and store an appropriate
 *     <code>java.util.Locale</code> object (under the standard key
 *     identified by <code>Globals.LOCALE_KEY</code>) in the user's session
 *     if there is not a Locale object there already. [true]
 *     <em>DEPRECATED - Configure this using the "locale" attribute of
 *     the &lt;controller&gt; element.</em></li>
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
 *     </ul>
 *     <em>DEPRECATED - Configure this using the "className" attribute of
 *     each &lt;action&gt; element, or globally for a module by using the
 *     "type" attribute of the &lt;action-mappings&gt; element.</em></li>
 * <li><strong>maxFileSize</strong> - The maximum size (in bytes) of a file
 *     to be accepted as a file upload.  Can be expressed as a number followed
 *     by a "K" "M", or "G", which are interpreted to mean kilobytes,
 *     megabytes, or gigabytes, respectively.  [250M]
 *     <em>DEPRECATED - Configure this using the "maxFileSize" attribute of
 *     the &lt;controller&gt; element.</em></li>
 * <li><strong>multipartClass</strong> - The fully qualified name of the
 *     MultipartRequestHandler implementation class to be used for processing
 *     file uploads. If set to <code>none</code>, disables Struts multipart
 *     request handling.  [org.apache.struts.upload.CommonsMultipartRequestHandler]
 *     <em>DEPRECATED - Configure this using the "multipartClass" attribute of
 *     the &lt;controller&gt; element.</em></li>
 * <li><strong>nocache</strong> - If set to <code>true</code>, add HTTP headers
 *     to every response intended to defeat browser caching of any response we
 *     generate or forward to.  [false]
 *     <em>DEPRECATED - Configure this using the "nocache" attribute of
 *     the &lt;controller&gt; element.</em></li>
 * <li><strong>null</strong> - If set to <code>true</code>, set our application
 *     resources to return <code>null</code> if an unknown message key is used.
 *     Otherwise, an error message including the offending message key will
 *     be returned.  [true]
 *     <em>DEPRECATED - Configure this using the "null" attribute of
 *     the &lt;message-resources&gt; element.</em></li>
 * <li><strong>tempDir</strong> - The temporary working directory to use when
 *     processing file uploads.  [The working directory provided to this web
 *     application as a servlet context attribute]
 *     <em>DEPRECATED - Configure this using the "tempDir" attribute of
 *     the &lt;controller&gt; element.</em></li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @author Martin Cooper
 * @author David Graham
 * @version $Revision: 1.150 $ $Date: 2003/06/28 06:16:34 $
 */
public class ActionServlet extends HttpServlet {


    // ----------------------------------------------------- Instance Variables


    /**
     * Comma-separated list of context-relative path(s) to our configuration
     * resource(s) for the default module.
     */
    protected String config = "/WEB-INF/struts-config.xml";


    /**
     * The Digester used to produce ModuleConfig objects from a
     * Struts configuration file.
     * @since Struts 1.1
     */
    protected Digester configDigester = null;


    /**
     * The flag to request backwards-compatible conversions for form bean
     * properties of the Java wrapper class types.
     * @since Struts 1.1
     */
    protected boolean convertNull = false;


    /**
     * The JDBC data sources that has been configured for this module,
     * if any, keyed by the servlet context attribute under which they are
     * stored.
     */
    protected FastHashMap dataSources = new FastHashMap();


    /**
     * The debugging detail level for this servlet.
     * @deprecated
     */
    protected int debug = 0;


    /**
     * The resources object for our internal resources.
     */
    protected MessageResources internal = null;


    /**
     * The Java base name of our internal resources.
     * @since Struts 1.1
     */
    protected String internalName = "org.apache.struts.action.ActionResources";


    /**
     * Commons Logging instance.
     * @since Struts 1.1
     */
    protected static Log log = LogFactory.getLog(ActionServlet.class);


    /**
     * The <code>RequestProcessor</code> instance we will use to process
     * all incoming requests.
     * @since Struts 1.1
     */
    protected RequestProcessor processor = null;


    /**
     * The set of public identifiers, and corresponding resource names, for
     * the versions of the configuration file DTDs that we know about.  There
     * <strong>MUST</strong> be an even number of Strings in this list!
     */
    protected String registrations[] = {
        "-//Apache Software Foundation//DTD Struts Configuration 1.0//EN",
        "/org/apache/struts/resources/struts-config_1_0.dtd",
        "-//Apache Software Foundation//DTD Struts Configuration 1.1//EN",
        "/org/apache/struts/resources/struts-config_1_1.dtd",
        "-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN",
        "/org/apache/struts/resources/web-app_2_2.dtd",
        "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN",
        "/org/apache/struts/resources/web-app_2_3.dtd"
    };


    /**
     * The URL pattern to which we are mapped in our web application
     * deployment descriptor.  FIXME - multiples???
     */
    protected String servletMapping = null;


    /**
     * The servlet name under which we are registered in our web application
     * deployment descriptor.
     */
    protected String servletName = null;


    // ---------------------------------------------------- HttpServlet Methods


    /**
     * Gracefully shut down this controller servlet, releasing any resources
     * that were allocated at initialization.
     */
    public void destroy() {

        if (log.isDebugEnabled()) {
            log.debug(internal.getMessage("finalizing"));
        }

        destroyModules();
        destroyDataSources();
        destroyInternal();
        getServletContext().removeAttribute(Globals.ACTION_SERVLET_KEY);

        // FIXME - destroy ModuleConfig and message resource instances

        // Release our LogFactory and Log instances (if any)
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = ActionServlet.class.getClassLoader();
        }
        try {
            LogFactory.release(classLoader);
        } catch (Throwable t) {
            ; // Servlet container doesn't have the latest version
            ; // of commons-logging-api.jar installed
            
            // FIXME Why is this dependent on the container's version of commons-logging?
            // Shouldn't this depend on the version packaged with Struts?
        }

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
        initOther();
        initServlet();

        // Initialize modules as needed
        getServletContext().setAttribute(Globals.ACTION_SERVLET_KEY, this);
        ModuleConfig moduleConfig = initModuleConfig("", config);
        initModuleMessageResources(moduleConfig);
        initModuleDataSources(moduleConfig);
        initModulePlugIns(moduleConfig);
        moduleConfig.freeze();
        Enumeration names = getServletConfig().getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            if (!name.startsWith("config/")) {
                continue;
            }
            String prefix = name.substring(6);
            moduleConfig = initModuleConfig
                (prefix, getServletConfig().getInitParameter(name));
            initModuleMessageResources(moduleConfig);
            initModuleDataSources(moduleConfig);
            initModulePlugIns(moduleConfig);
            moduleConfig.freeze();
        }
        destroyConfigDigester();

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
     * Remember a servlet mapping from our web application deployment
     * descriptor, if it is for this servlet.
     *
     * @param servletName The name of the servlet being mapped
     * @param urlPattern The URL pattern to which this servlet is mapped
     */
    public void addServletMapping(String servletName, String urlPattern) {

        if (log.isDebugEnabled()) {
            log.debug("Process servletName=" + servletName +
                      ", urlPattern=" + urlPattern);
        }
        if (servletName == null) {
            return;
        }
        if (servletName.equals(this.servletName)) {
            this.servletMapping = urlPattern;
        }

    }


    /**
     * Return a JDBC data source associated with this module, if any.
     *
     * @param key The servlet context attribute key under which this data
     *  source is stored, or <code>null</code> for the default.
     *
     * @deprecated Look up data sources directly in servlet context attributes
     */
    public DataSource findDataSource(String key) {
        if (key == null) {
            return ((DataSource) dataSources.get(Globals.DATA_SOURCE_KEY));
        } else {
            return ((DataSource) dataSources.get(key));
        }
    }


    /**
     * Return the form bean definition associated with the specified
     * logical name, if any; otherwise return <code>null</code>.
     *
     * @param name Logical name of the requested form bean definition
     *
     * @deprecated Replaced by ModuleConfig.findFormBeanConfig()
     */
    public ActionFormBean findFormBean(String name) {

        ActionFormBeans afb = (ActionFormBeans)
            getServletContext().getAttribute(Globals.FORM_BEANS_KEY);
        if (afb == null) {
            return (null);
        }
        return (afb.findFormBean(name));

    }


    /**
     * Return the forwarding associated with the specified logical name,
     * if any; otherwise return <code>null</code>.
     *
     * @param name Logical name of the requested forwarding
     *
     * @deprecated Replaced by ModuleConfig.findForwardConfig()
     */
    public ActionForward findForward(String name) {

        ActionForwards af = (ActionForwards)
            getServletContext().getAttribute(Globals.FORWARDS_KEY);
        if (af == null) {
            return (null);
        }
        return (af.findForward(name));

    }


    /**
     * Return the ActionMapping for the specified path, for the default
     * module.
     *
     * @param path Request path for which a mapping is requested
     *
     * @deprecated Replaced by ModuleConfig.findActionConfig()
     */
    public ActionMapping findMapping(String path) {

        ActionMappings am = (ActionMappings)
            getServletContext().getAttribute(Globals.MAPPINGS_KEY);
        if (am == null) {
            return (null);
        }
        return (am.findMapping(path));

    }


    /**
     * Return the debugging detail level for this servlet.
     *
     * @deprecated Configure the logging detail level in your underlying
     *  logging implementation
     */
    public int getDebug() {

        return (this.debug);

    }


    /**
     * Return the <code>MessageResources</code> instance containing our
     * internal message strings.
     * @since Struts 1.1
     */
    public MessageResources getInternal() {

        return (this.internal);

    }


    /**
     * <p>Return the application resources for the default module,
     * if any.
     *
     * @deprecated Actions should call Action.getResources(HttpServletRequest)
     *  instead of this method, in order to retrieve the resources for the
     *  current module.
     */
    public MessageResources getResources() {

        return ((MessageResources) getServletContext().getAttribute
                (Globals.MESSAGES_KEY));

    }


    /**
     * Log the specified message if the current debugging detail level for
     * this servlet has been set to an equal or higher value.  Otherwise,
     * ignore this message.
     *
     * @param message Message to be logged
     * @param level Debugging detail level of this message
     * @deprecated Use commons-logging instead.
     */
    public void log(String message, int level) {

        if (debug >= level) {
            log(message);
        }

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Gracefully terminate use of any modules associated with this
     * application (if any).
     * @since Struts 1.1
     * @deprecated replaced by destroyModules()
     */
    protected void destroyApplications() {
        destroyModules();
    }

    /**
     * Gracefully terminate use of any modules associated with this
     * application (if any).
     * @since Struts 1.1
     */
    protected void destroyModules() {

        ArrayList values = new ArrayList();
        Enumeration names = getServletContext().getAttributeNames();
        while (names.hasMoreElements()) {
            values.add(names.nextElement());
        }

        Iterator keys = values.iterator();
        while (keys.hasNext()) {
            String name = (String) keys.next();
            Object value = getServletContext().getAttribute(name);
            if (value instanceof ModuleConfig) {
                ModuleConfig config = (ModuleConfig) value;
                    try {
                        getRequestProcessor(config).destroy();
                    } catch (ServletException e) {
                        log.error(e);
                    }
                    
                getServletContext().removeAttribute(name);

                PlugIn plugIns[] =
                    (PlugIn[]) getServletContext().getAttribute(
                        Globals.PLUG_INS_KEY + config.getPrefix());
                if (plugIns != null) {
                    for (int i = 0; i < plugIns.length; i++) {
                        int j = plugIns.length - (i + 1);
                        plugIns[j].destroy();
                    }
                    getServletContext().removeAttribute
                        (Globals.PLUG_INS_KEY + config.getPrefix());
                }
            }
        }

    }


    /**
     * Gracefully release any configDigester instance that we have created.
     * @since Struts 1.1
     */
    protected void destroyConfigDigester() {

        configDigester = null;

    }


    /**
     * Gracefully terminate use of the data source associated with this
     * application (if any).
     *
     * @deprecated Will no longer be required with module support
     */
    protected void destroyDataSources() {

        synchronized (dataSources) {
            Iterator keys = dataSources.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                getServletContext().removeAttribute(key);
                DataSource dataSource = findDataSource(key);
                if (dataSource instanceof GenericDataSource) {
                    if (log.isDebugEnabled()) {
                        log.debug(internal.getMessage("dataSource.destroy", key));
                    }
                    try {
                        ((GenericDataSource) dataSource).close();
                    } catch (SQLException e) {
                        log.error(internal.getMessage("destroyDataSource", key), e);
                    }
                }
            }
            dataSources.clear();
        }

    }


    /**
     * Gracefully terminate use of the internal MessageResources.
     */
    protected void destroyInternal() {

        internal = null;

    }


    /**
     * Return the module configuration object for the currently selected
     * module.
     *
     * @param request The servlet request we are processing
     * @since Struts 1.1
     * @deprecated use {@link #getModuleConfig(HttpServletRequest)}
     */
    protected ApplicationConfig getApplicationConfig
        (HttpServletRequest request) {
        /* FIXME for Struts 1.2
           Since Struts 1.1 only has one implementation for
           ModuleConfig casting is safe here. Used only for
           transition purposes !
        */
        return new ApplicationConfig((ModuleConfigImpl)getModuleConfig(request));
    }

    /**
     * Return the module configuration object for the currently selected
     * module.
     *
     * @param request The servlet request we are processing
     * @since Struts 1.1
     */
    protected ModuleConfig getModuleConfig
        (HttpServletRequest request) {

        ModuleConfig config = (ModuleConfig)
            request.getAttribute(Globals.MODULE_KEY);
        if (config == null) {
            config = (ModuleConfig)
                getServletContext().getAttribute(Globals.MODULE_KEY);
        }
        return (config);

    }


    /**
     * Look up and return the {@link RequestProcessor} responsible for the
     * specified module, creating a new one if necessary.
     *
     * @param config The module configuration for which to
     *  acquire and return a RequestProcessor.
     *
     * @exception ServletException if we cannot instantiate a RequestProcessor
     *  instance
     * @since Struts 1.1
     */
    protected synchronized RequestProcessor getRequestProcessor(ModuleConfig config)
        throws ServletException {    
            
        String key = Globals.REQUEST_PROCESSOR_KEY + config.getPrefix();
        RequestProcessor processor = (RequestProcessor)
            getServletContext().getAttribute(key);
            
        if (processor == null) {
            try {
                processor =
                    (RequestProcessor) RequestUtils.applicationInstance(
                        config.getControllerConfig().getProcessorClass());
                        
            } catch (Exception e) {
                throw new UnavailableException(
                    "Cannot initialize RequestProcessor of class "
                        + config.getControllerConfig().getProcessorClass()
                        + ": "
                        + e);
            }

            processor.init(this, config);
            getServletContext().setAttribute(key, processor);

        }
        return (processor);

    }
    
    /**
     * <p>Initialize the application configuration information for the
     * specified module.</p>
     *
     * @param prefix Module prefix for this module
     * @param path Context-relative resource path for this modules's
     *  configuration resource
     *
     * @exception ServletException if initialization cannot be performed
     * @deprecated use {@link #initModuleConfig(String,String)}
     * @since Struts 1.1
     */
    protected ApplicationConfig initApplicationConfig
        (String prefix, String path) throws ServletException {
        /* FIXME for Struts 1.2
           Since Struts 1.1 only has one implementation for
           ModuleConfig casting is safe here. Used only for
           transition purposes !
        */
        return new ApplicationConfig((ModuleConfigImpl)initModuleConfig(prefix,path));
    }
    /**
     * <p>Initialize the application configuration information for the
     * specified module.</p>
     *
     * @param prefix Module prefix for this module
     * @param paths Comma-separated list of context-relative resource path(s)
     *  for this modules's configuration resource(s)
     *
     * @exception ServletException if initialization cannot be performed
     * @since Struts 1.1
     */
    protected ModuleConfig initModuleConfig(String prefix, String paths)
        throws ServletException {

        if (log.isDebugEnabled()) {
            log.debug(
                "Initializing module path '"
                    + prefix
                    + "' configuration from '"
                    + paths
                    + "'");
        }

        // Parse the configuration for this module
        //@todo & FIXME replace with a FactoryMethod
        ModuleConfigFactory factoryObject = ModuleConfigFactory.createFactory();
        ModuleConfig config = factoryObject.createModuleConfig(prefix);

        // Support for module-wide ActionMapping type override
        String mapping = getServletConfig().getInitParameter("mapping");
        if (mapping != null) {
            config.setActionMappingClass(mapping);
        }

        // Configure the Digester instance we will use
        Digester digester = initConfigDigester();

        // Process each specified resource path
        while (paths.length() > 0) {
            digester.push(config);
            String path = null;
            int comma = paths.indexOf(',');
            if (comma >= 0) {
                path = paths.substring(0, comma).trim();
                paths = paths.substring(comma + 1);
            } else {
                path = paths.trim();
                paths = "";
            }

            if (path.length() < 1) {
                break;
            }
            
            this.parseModuleConfigFile(prefix, paths, config, digester, path);
        }

        // Force creation and registration of DynaActionFormClass instances
        // for all dynamic form beans we wil be using
        FormBeanConfig fbs[] = config.findFormBeanConfigs();
        for (int i = 0; i < fbs.length; i++) {
            if (fbs[i].getDynamic()) {
                DynaActionFormClass.createDynaActionFormClass(fbs[i]);
            }
        }

        // Special handling for the default module (for
        // backwards compatibility only, will be removed later)
        if (prefix.length() < 1) {
            defaultControllerConfig(config);
            defaultMessageResourcesConfig(config);
            defaultFormBeansConfig(config);
            defaultForwardsConfig(config);
            defaultMappingsConfig(config);
        }

        // Return the completed configuration object
        //config.freeze();  // Now done after plugins init
        return (config);

    }

    /**
     * Parses one module config file.
     * @param prefix
     * @param paths
     * @param config
     * @param digester Digester instance that does the parsing
     * @param path The path to the config file to parse.
     * @throws UnavailableException
     */
    private void parseModuleConfigFile(
        String prefix,
        String paths,
        ModuleConfig config,
        Digester digester,
        String path)
        throws UnavailableException {

        InputStream input = null;
        try {
            URL url = getServletContext().getResource(path);
            InputSource is = new InputSource(url.toExternalForm());
            input = getServletContext().getResourceAsStream(path);
            is.setByteStream(input);
            digester.parse(is);
            getServletContext().setAttribute(Globals.MODULE_KEY + prefix, config);
            
        } catch (MalformedURLException e) {
            handleConfigException(paths, e);
        } catch (IOException e) {
            handleConfigException(paths, e);
        } catch (SAXException e) {
            handleConfigException(paths, e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new UnavailableException(e.getMessage());
                }
            }
        }
    }

    /**
     * Simplifies exception handling in the parseModuleConfigFile() method.
     * @param paths
     * @param e
     * @throws UnavailableException
     */
    private void handleConfigException(String paths, Exception e)
        throws UnavailableException {
        log.error(internal.getMessage("configParse", paths), e);
        throw new UnavailableException(internal.getMessage("configParse", paths));
    }

    /**
     * <p>Initialize the data sources for the specified module.</p>
     *
     * @param config ModuleConfig information for this module
     *
     * @exception ServletException if initialization cannot be performed
     * @since Struts 1.1
     * @deprecated use initModuleDataSources(ModuleConfig)
     */
    protected void initApplicationDataSources(ModuleConfig config) throws ServletException  {
       initModuleDataSources(config);
    }

    /**
     * <p>Initialize the data sources for the specified module.</p>
     *
     * @param config ModuleConfig information for this module
     *
     * @exception ServletException if initialization cannot be performed
     * @since Struts 1.1
     */
    protected void initModuleDataSources(ModuleConfig config) throws ServletException {

        if (log.isDebugEnabled()) {
            log.debug("Initializing module path '" + config.getPrefix() +
                "' data sources");
        }

        ServletContextWriter scw =
            new ServletContextWriter(getServletContext());
        DataSourceConfig dscs[] = config.findDataSourceConfigs();
        if (dscs == null) {
            dscs = new DataSourceConfig[0];
        }

        dataSources.setFast(false);
        for (int i = 0; i < dscs.length; i++) {
            if (log.isDebugEnabled()) {
                log.debug("Initializing module path '" + config.getPrefix() +
                    "' data source '" + dscs[i].getKey() + "'");
            }
            DataSource ds = null;
            try {
                ds = (DataSource)
                    RequestUtils.applicationInstance(dscs[i].getType());
                BeanUtils.populate(ds, dscs[i].getProperties());
                if (ds instanceof GenericDataSource) {
                    ((GenericDataSource) ds).open();
                }
                ds.setLogWriter(scw);
                
            } catch (Exception e) {
                log.error(internal.getMessage("dataSource.init", dscs[i].getKey()), e);
                throw new UnavailableException
                    (internal.getMessage("dataSource.init", dscs[i].getKey()));
            }
            getServletContext().setAttribute
                (dscs[i].getKey() + config.getPrefix(), ds);
            dataSources.put(dscs[i].getKey(), ds);
        }
        dataSources.setFast(true);

        // Call deprecated method for backwards compatibility
        if ("".equals(config.getPrefix())) {
            initDataSources();
        }

    }


    /**
     * <p>Initialize the plug ins for the specified module.</p>
     *
     * @param config ModuleConfig information for this module
     *
     * @exception ServletException if initialization cannot be performed
     * @deprecated use {@link #initModulePlugIns(ModuleConfig)}
     * @since Struts 1.1
     */
    protected void initApplicationPlugIns
        (ModuleConfig config) throws ServletException {
        initModulePlugIns(config);
    }
    /**
     * <p>Initialize the plug ins for the specified module.</p>
     *
     * @param config ModuleConfig information for this module
     *
     * @exception ServletException if initialization cannot be performed
     * @since Struts 1.1
     */
    protected void initModulePlugIns
        (ModuleConfig config) throws ServletException {

        if (log.isDebugEnabled()) {
            log.debug("Initializing module path '" + config.getPrefix() + "' plug ins");
        }

        PlugInConfig plugInConfigs[] = config.findPlugInConfigs();
        PlugIn plugIns[] = new PlugIn[plugInConfigs.length];

        getServletContext().setAttribute(Globals.PLUG_INS_KEY + config.getPrefix(), plugIns);
        for (int i = 0; i < plugIns.length; i++) {
            try {
                plugIns[i] =
                    (PlugIn)RequestUtils.applicationInstance(plugInConfigs[i].getClassName());
                 BeanUtils.populate(plugIns[i], plugInConfigs[i].getProperties());
                  // Pass the current plugIn config object to the PlugIn.
                  // The property is set only if the plugin declares it.
                  // This plugin config object is needed by Tiles
                try {
                    PropertyUtils.setProperty(
                        plugIns[i],
                        "currentPlugInConfigObject",
                        plugInConfigs[i]);
                } catch (Exception e) {
                  // FIXME Whenever we fail silently, we must document a valid reason
                  // for doing so.  Why should we fail silently if a property can't be set on
                  // the plugin?
                }
                plugIns[i].init(this, (ModuleConfig) config);
                
            } catch (ServletException e) {
                throw e;
            } catch (Exception e) {
                String errMsg =
                    internal.getMessage(
                        "plugIn.init",
                        plugInConfigs[i].getClassName());
                        
                log(errMsg, e);
                throw new UnavailableException(errMsg);
            }
        }

    }


    /**
     * <p>Initialize the application MessageResources for the specified
     * module.</p>
     *
     * @param config ModuleConfig information for this module
     *
     * @exception ServletException if initialization cannot be performed
     * @since Struts 1.1
     * @deprecated use initModuleMessageResources()
     */
    protected void initApplicationMessageResources(ModuleConfig config) throws ServletException {
       initModuleMessageResources(config);
    }
    
    /**
     * <p>Initialize the application MessageResources for the specified
     * module.</p>
     *
     * @param config ModuleConfig information for this module
     *
     * @exception ServletException if initialization cannot be performed
     * @since Struts 1.1
     */
    protected void initModuleMessageResources(ModuleConfig config)
        throws ServletException {

        MessageResourcesConfig mrcs[] = config.findMessageResourcesConfigs();
        for (int i = 0; i < mrcs.length; i++) {
            if ((mrcs[i].getFactory() == null)
                || (mrcs[i].getParameter() == null)) {
                continue;
            }
            if (log.isDebugEnabled()) {
                log.debug(
                    "Initializing module path '"
                        + config.getPrefix()
                        + "' message resources from '"
                        + mrcs[i].getParameter()
                        + "'");
            }

            String factory = mrcs[i].getFactory();
            MessageResourcesFactory.setFactoryClass(factory);
            MessageResourcesFactory factoryObject =
                MessageResourcesFactory.createFactory();
                
            MessageResources resources =
                factoryObject.createResources(mrcs[i].getParameter());
            resources.setReturnNull(mrcs[i].getNull());
            getServletContext().setAttribute(
                mrcs[i].getKey() + config.getPrefix(),
                resources);
        }

    }


    /**
     * <p>Create (if needed) and return a new Digester instance that has been
     * initialized to process Struts module configuraiton files and
     * configure a corresponding ModuleConfig object (which must be
     * pushed on to the evaluation stack before parsing begins).</p>
     *
     * @exception ServletException if a Digester cannot be configured
     * @since Struts 1.1
     */
    protected Digester initConfigDigester() throws ServletException {

        // Do we have an existing instance?
        if (configDigester != null) {
            return (configDigester);
        }

        // Check the status of the "validating" initialization parameter
        boolean validating = true;
        String value = getServletConfig().getInitParameter("validating");
        if ("false".equalsIgnoreCase(value)
            || "no".equalsIgnoreCase(value)
            || "n".equalsIgnoreCase(value)
            || "0".equalsIgnoreCase(value)) {

            validating = false;
        }

        // Create a new Digester instance with standard capabilities
        configDigester = new Digester();
        configDigester.setNamespaceAware(true);
        configDigester.setValidating(validating);
        configDigester.setUseContextClassLoader(true);
        configDigester.addRuleSet(new ConfigRuleSet());
        for (int i = 0; i < registrations.length; i += 2) {
            URL url = this.getClass().getResource(registrations[i+1]);
            if (url != null) {
                configDigester.register(registrations[i], url.toString());
            }
        }

        // Add any custom RuleSet instances that have been specified
        String rulesets = getServletConfig().getInitParameter("rulesets");
        if (rulesets == null) {
            rulesets = "";
        }
        rulesets = rulesets.trim();
        String ruleset = null;
        while (rulesets.length() > 0) {
            int comma = rulesets.indexOf(",");
            if (comma < 0) {
                ruleset = rulesets.trim();
                rulesets = "";
            } else {
                ruleset = rulesets.substring(0, comma).trim();
                rulesets = rulesets.substring(comma + 1).trim();
            }
            if (log.isDebugEnabled()) {
                log.debug("Configuring custom Digester Ruleset of type " + ruleset);
            }
            try {
                RuleSet instance = (RuleSet) RequestUtils.applicationInstance(ruleset);
                configDigester.addRuleSet(instance);
            } catch (Exception e) {
                log.error("Exception configuring custom Digester RuleSet", e);
                throw new ServletException(e);
            }
        }

        // Return the completely configured Digester instance
        return (configDigester);
    }


    /**
     * Initialize data sources for the default module.  This method
     * signature is maintained only for backwards compatibility, and will
     * be removed in a subsequent release.
     *
     * @deprecated Replaced by initApplicationDataSources() that takes
     *  an ModuleConfig argument. This method does nothing.
     */
    protected void initDataSources() throws javax.servlet.ServletException {

        ; // Implementation has been replaced in initApplicationDataSources()

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
            log.error("Cannot load internal resources from '" + internalName + "'",
                e);
            throw new UnavailableException
                ("Cannot load internal resources from '" + internalName + "'");
        }

    }


    /**
     * Initialize other global characteristics of the controller servlet.
     *
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initOther() throws ServletException {

        String value = null;
        value = getServletConfig().getInitParameter("config");
        if (value != null) {
            config = value;
        }
        
        value = getServletConfig().getInitParameter("debug");
        if (value != null) {
            try {
                debug = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // FIXME Why should we catch this?  If the programmer has specified an
                // invalid integer we should probably let this RuntimeException bubble up.
                debug = 0;
            }
        }

        // Backwards compatibility for form beans of Java wrapper classes
        // Set to true for strict Struts 1.0 compatibility
        value = getServletConfig().getInitParameter("convertNull");
        if ("true".equalsIgnoreCase(value)
            || "yes".equalsIgnoreCase(value)
            || "on".equalsIgnoreCase(value)
            || "y".equalsIgnoreCase(value)
            || "1".equalsIgnoreCase(value)) {
                
            convertNull = true;
        }
        
        if (convertNull) {
            ConvertUtils.deregister();
            ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
            ConvertUtils.register(new BigIntegerConverter(null), BigInteger.class);
            ConvertUtils.register(new BooleanConverter(null), Boolean.class);
            ConvertUtils.register(new ByteConverter(null), Byte.class);
            ConvertUtils.register(new CharacterConverter(null), Character.class);
            ConvertUtils.register(new DoubleConverter(null), Double.class);
            ConvertUtils.register(new FloatConverter(null), Float.class);
            ConvertUtils.register(new IntegerConverter(null), Integer.class);
            ConvertUtils.register(new LongConverter(null), Long.class);
            ConvertUtils.register(new ShortConverter(null), Short.class);
        }

    }


    /**
     * Initialize the servlet mapping under which our controller servlet
     * is being accessed.  This will be used in the <code>&html:form&gt;</code>
     * tag to generate correct destination URLs for form submissions.
     * @throws ServletException if error happens while scanning web.xml
     */
    protected void initServlet() throws ServletException {

        // Remember our servlet name
        this.servletName = getServletConfig().getServletName();

        // Prepare a Digester to scan the web application deployment descriptor
        Digester digester = new Digester();
        digester.push(this);
        digester.setNamespaceAware(true);
        digester.setValidating(false);

        // Register our local copy of the DTDs that we can find
        for (int i = 0; i < registrations.length; i += 2) {
            URL url = this.getClass().getResource(registrations[i+1]);
            if (url != null) {
                digester.register(registrations[i], url.toString());
            }
        }

        // Configure the processing rules that we need
        digester.addCallMethod("web-app/servlet-mapping",
                               "addServletMapping", 2);
        digester.addCallParam("web-app/servlet-mapping/servlet-name", 0);
        digester.addCallParam("web-app/servlet-mapping/url-pattern", 1);

        // Process the web application deployment descriptor
        if (log.isDebugEnabled()) {
            log.debug("Scanning web.xml for controller servlet mapping");
        }

        InputStream input =
            getServletContext().getResourceAsStream("/WEB-INF/web.xml");

        try {
            digester.parse(input);

        } catch (IOException e) {
            log.error(internal.getMessage("configWebXml"), e);
            throw new ServletException(e);
            
        } catch (SAXException e) {
            log.error(internal.getMessage("configWebXml"), e);
            throw new ServletException(e);
            
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error(internal.getMessage("configWebXml"), e);
                    throw new ServletException(e);
                }
            }
        }

        // Record a servlet context attribute (if appropriate)
        if (log.isDebugEnabled()) {
            log.debug("Mapping for servlet '" + servletName + "' = '" +
                servletMapping + "'");
        }

        if (servletMapping != null) {
            getServletContext().setAttribute(Globals.SERVLET_KEY, servletMapping);
        }

    }


    /**
     * Perform the standard request processing for this request, and create
     * the corresponding response.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception is thrown
     */
    protected void process(HttpServletRequest request,
                           HttpServletResponse response)
        throws IOException, ServletException {

        RequestUtils.selectModule(request, getServletContext());
        getRequestProcessor(getModuleConfig(request)).process
            (request, response);

    }


    // -------------------------------------------------------- Private Methods


    /**
     * Perform backwards-compatible configuration of the default module's
     * controller configuration from servlet initialization parameters (as
     * were used in Struts 1.0).
     *
     * @param config The ModuleConfig object for the default module
     *
     * @since Struts 1.1
     * @deprecated Will be removed in a release after Struts 1.1.
     */
    private void defaultControllerConfig(ModuleConfig config) {

        String value = null;
        ControllerConfig cc = config.getControllerConfig();
        
        value = getServletConfig().getInitParameter("bufferSize");
        if (value != null) {
            cc.setBufferSize(Integer.parseInt(value));
        }
        
        value = getServletConfig().getInitParameter("content");
        if (value != null) {
            cc.setContentType(value);
        }
        
        value = getServletConfig().getInitParameter("locale");        
        // must check for null here 
        if (value != null) {
            if ("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value)) {
                cc.setLocale(true);
            } else {
                cc.setLocale(false);
            }
        } 
        
        value = getServletConfig().getInitParameter("maxFileSize");
        if (value != null) {
            cc.setMaxFileSize(value);
        }
        
        value = getServletConfig().getInitParameter("nocache");
        if (value != null) {
            if ("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value)) {
                cc.setNocache(true);
            } else {
                cc.setNocache(false);
            }
        }
        
        value = getServletConfig().getInitParameter("multipartClass");
        if (value != null) {
            cc.setMultipartClass(value);
        }
        
        value = getServletConfig().getInitParameter("tempDir");
        if (value != null) {
            cc.setTempDir(value);
        }

    }


    /**
     * Perform backwards-compatible configuration of an ActionFormBeans
     * collection, and expose it as a servlet context attribute (as was
     * used in Struts 1.0).  Note that the current controller code does
     * not (and should not) reference this attribute for any reason.
     *
     * @param config The ModuleConfig object for the default app
     *
     * @since Struts 1.1
     * @deprecated Will be removed in a release after Struts 1.1.
     */
    private void defaultFormBeansConfig(ModuleConfig config) {

        FormBeanConfig fbcs[] = config.findFormBeanConfigs();
        ActionFormBeans afb = new ActionFormBeans();
        afb.setFast(false);
        for (int i = 0; i < fbcs.length; i++) {
            afb.addFormBean((ActionFormBean) fbcs[i]);
        }
        afb.setFast(true);
        getServletContext().setAttribute(Globals.FORM_BEANS_KEY, afb);

    }


    /**
     * Perform backwards-compatible configuration of an ActionForwards
     * collection, and expose it as a servlet context attribute (as was
     * used in Struts 1.0).  Note that the current controller code does
     * not (and should not) reference this attribute for any reason.
     *
     * @param config The ModuleConfig object for the default app
     *
     * @since Struts 1.1
     * @deprecated Will be removed in a release after Struts 1.1.
     */
    private void defaultForwardsConfig(ModuleConfig config) {

        ForwardConfig fcs[] = config.findForwardConfigs();
        ActionForwards af = new ActionForwards();
        af.setFast(false);
        for (int i = 0; i < fcs.length; i++) {
            af.addForward((ActionForward) fcs[i]);
        }
        af.setFast(true);
        getServletContext().setAttribute(Globals.FORWARDS_KEY, af);

    }


    /**
     * Perform backwards-compatible configuration of an ActionMappings
     * collection, and expose it as a servlet context attribute (as was
     * used in Struts 1.0).  Note that the current controller code does
     * not (and should not) reference this attribute for any reason.
     *
     * @param config The ModuleConfig object for the default app
     *
     * @since Struts 1.1
     * @deprecated Will be removed in a release after Struts 1.1.
     */
    private void defaultMappingsConfig(ModuleConfig config) {

        ActionConfig acs[] = config.findActionConfigs();
        ActionMappings am = new ActionMappings();
        am.setServlet(this);
        am.setFast(false);
        for (int i = 0; i < acs.length; i++) {
            am.addMapping((ActionMapping) acs[i]);
        }
        am.setFast(true);
        getServletContext().setAttribute(Globals.MAPPINGS_KEY, am);

    }


    /**
     * Perform backwards-compatible configuration of the default module's
     * message resources configuration from servlet initialization parameters
     * (as were used in Struts 1.0).
     *
     * @param config The ModuleConfig object for the default module
     *
     * @since Struts 1.1
     * @deprecated Will be removed in a release after Struts 1.1.
     */
    private void defaultMessageResourcesConfig(ModuleConfig config) {

        String value = null;

        MessageResourcesConfig mrc =
            config.findMessageResourcesConfig(Globals.MESSAGES_KEY);
        if (mrc == null) {
            mrc = new MessageResourcesConfig();
            mrc.setKey(Globals.MESSAGES_KEY);
            config.addMessageResourcesConfig(mrc);
        }
        value = getServletConfig().getInitParameter("application");
        if (value != null) {
            mrc.setParameter(value);
        }
        value= getServletConfig().getInitParameter("factory");
        if (value != null) {
            mrc.setFactory(value);
        }
        value = getServletConfig().getInitParameter("null");
        if (value != null) {
            if (value.equalsIgnoreCase("true") ||
                value.equalsIgnoreCase("yes")) {
                mrc.setNull(true);
            } else {
                mrc.setNull(false);
            }
        }

    }

}
