/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/RequestUtils.java,v 1.61 2002/10/15 17:37:25 ekbush Exp $
 * $Revision: 1.61 $
 * $Date: 2002/10/15 17:37:25 $
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


package org.apache.struts.util;


import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionServletWrapper;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.upload.MultipartRequestHandler;


/**
 * General purpose utility methods related to processing a servlet request
 * in the Struts controller framework.
 *
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @version $Revision: 1.61 $ $Date: 2002/10/15 17:37:25 $
 */

public class RequestUtils {


    // ------------------------------------------------------- Static Variables


    /**
     * Commons Logging instance.
     */
    protected static Log LOG = LogFactory.getLog(RequestUtils.class);


    /**
     * The default Locale for our server.
     */
    private static final Locale defaultLocale = Locale.getDefault();


    /**
     * The message resources for this package.
     */
    private static MessageResources messages =
    MessageResources.getMessageResources
    ("org.apache.struts.util.LocalStrings");



    /**
     * The context attribute under which we store our prefixes list.
     */
    private static final String PREFIXES_KEY =
        "org.apache.struts.util.PREFIXES";


    // --------------------------------------------------------- Public Methods


    /**
     * Create and return an absolute URL for the specified context-relative
     * path, based on the server and context information in the specified
     * request.
     *
     * @param request The servlet request we are processing
     * @param path The context-relative path (must start with '/')
     *
     * @exception MalformedURLException if we cannot create an absolute URL
     */
    public static URL absoluteURL(HttpServletRequest request, String path)
        throws MalformedURLException {

        return (new URL(serverURL(request), request.getContextPath() + path));

    }


    /**
     * Return the <code>Class</code> object for the specified fully qualified
     * class name, from this web application's class loader.
     *
     * @param className Fully qualified class name to be loaded
     *
     * @exception ClassNotFoundException if the class cannot be found
     */
    public static Class applicationClass(String className)
        throws ClassNotFoundException {

        // Look up the class loader to be used
        ClassLoader classLoader =
            Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = RequestUtils.class.getClassLoader();
        }

        // Attempt to load the specified class
        return (classLoader.loadClass(className));

    }


    /**
     * Return a new instance of the specified fully qualified class name,
     * after loading the class from this web application's class loader.
     * The specified class <strong>MUST</strong> have a public zero-arguments
     * constructor.
     *
     * @param className Fully qualified class name to use
     *
     * @exception ClassNotFoundException if the class cannot be found
     * @exception IllegalAccessException if the class or its constructor
     *  is not accessible
     * @exception InstantiationException if this class represents an
     *  abstract class, an interface, an array class, a primitive type,
     *  or void
     * @exception InstantiationException if this class has no
     *  zero-arguments constructor
     */
    public static Object applicationInstance(String className)
        throws ClassNotFoundException, IllegalAccessException,
               InstantiationException {

        return (applicationClass(className).newInstance());

    }


    /**
     * Compute a set of query parameters that will be dynamically added to
     * a generated URL.  The returned Map is keyed by parameter name, and the
     * values are either null (no value specified), a String (single value
     * specified), or a String[] array (multiple values specified).  Parameter
     * names correspond to the corresponding attributes of the
     * <code>&lt;html:link&gt;</code> tag.  If no query parameters are
     * identified, return <code>null</code>.
     *
     * @param pageContext PageContext we are operating in
     *
     * @param paramId Single-value request parameter name (if any)
     * @param paramName Bean containing single-value parameter value
     * @param paramProperty Property (of bean named by <code>paramName</code>
     *  containing single-value parameter value
     * @param paramScope Scope containing bean named by
     *  <code>paramName</code>
     *
     * @param name Bean containing multi-value parameters Map (if any)
     * @param property Property (of bean named by <code>name</code>
     *  containing multi-value parameters Map
     * @param scope Scope containing bean named by
     *  <code>name</code>
     *
     * @param transaction Should we add our transaction control token?
     *
     * @exception JspException if we cannot look up the required beans
     * @exception JspException if a class cast exception occurs on a
     *  looked-up bean or property
     */
    public static Map computeParameters(PageContext pageContext,
                                        String paramId, String paramName,
                                        String paramProperty,
                                        String paramScope, String name,
                                        String property, String scope,
                                        boolean transaction)
        throws JspException {

        // Short circuit if no parameters are specified
        if ((paramId == null) && (name == null) && !transaction)
            return (null);

        // Locate the Map containing our multi-value parameters map
        Map map = null;
        try {
            if (name != null)
                map = (Map) lookup(pageContext, name,
                                   property, scope);
        } catch (ClassCastException e) {
            saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("parameters.multi", name,
                                     property, scope));
        } catch (JspException e) {
            saveException(pageContext, e);
            throw e;
        }

        // Create a Map to contain our results from the multi-value parameters
        Map results = null;
        if (map != null)
            results = new HashMap(map);
        else
            results = new HashMap();

        // Add the single-value parameter (if any)
        if ((paramId != null) && (paramName != null)) {

            Object paramValue = null;
            try {
                paramValue = lookup(pageContext, paramName,
                                    paramProperty, paramScope);
            } catch (JspException e) {
                saveException(pageContext, e);
                throw e;
            }

            if (paramValue != null) {

                String paramString = null;
                if (paramValue instanceof String)
                    paramString = (String) paramValue;
                else
                    paramString = paramValue.toString();

                Object mapValue = results.get(paramId);
                if (mapValue == null)
                    results.put(paramId, paramString);
                else if (mapValue instanceof String) {
                    String newValues[] = new String[2];
                    newValues[0] = (String) mapValue;
                    newValues[1] = paramString;
                    results.put(paramId, newValues);
                } else /* if (mapValue instanceof String[]) */ {
                    String oldValues[] = (String[]) mapValue;
                    String newValues[] = new String[oldValues.length + 1];
                    System.arraycopy(oldValues, 0, newValues, 0,
                                     oldValues.length);
                    newValues[oldValues.length] = paramString;
                    results.put(paramId, newValues);
                }

            }

        }

        // Add our transaction control token (if requested)
        if (transaction) {
            HttpSession session = pageContext.getSession();
            String token = null;
            if (session != null)
                token = (String)
                    session.getAttribute(Action.TRANSACTION_TOKEN_KEY);
            if (token != null)
                results.put(Constants.TOKEN_KEY, token);
        }

        // Return the completed Map
        return (results);

    }


    /**
     * Compute a hyperlink URL based on the <code>forward</code>,
     * <code>href</code>, or <code>page</code> parameter that is not null.
     * The returned URL will have already been passed to
     * <code>response.encodeURL()</code> for adding a session identifier.
     *
     * @param pageContext PageContext for the tag making this call
     *
     * @param forward Logical forward name for which to look up
     *  the context-relative URI (if specified)
     * @param href URL to be utilized unmodified (if specified)
     * @param page Module-relative page for which a URL should
     *  be created (if specified)
     *
     * @param params Map of parameters to be dynamically included (if any)
     * @param anchor Anchor to be dynamically included (if any)
     *
     * @param redirect Is this URL for a <code>response.sendRedirect()</code>?
     *
     * @exception MalformedURLException if a URL cannot be created
     *  for the specified parameters
     */
    public static String computeURL(PageContext pageContext, String forward,
                                    String href, String page,
                                    Map params, String anchor,
                                    boolean redirect)
        throws MalformedURLException {

        // Validate that exactly one specifier was included
        int n = 0;
        if (forward != null) {
            n++;
        }
        if (href != null) {
            n++;
        }
        if (page != null) {
            n++;
        }
        if (n != 1) {
            throw new MalformedURLException
                (messages.getMessage("computeURL.specifier"));
        }

        // Look up the application module configuration for this request
        ApplicationConfig config = getApplicationConfig(pageContext);


        // Calculate the appropriate URL
        StringBuffer url = new StringBuffer();
        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        if (forward != null) {
            ForwardConfig fc = config.findForwardConfig(forward);
            if (fc == null) {
                throw new MalformedURLException
                    (messages.getMessage("computeURL.forward", forward));
            }
            if (fc.getRedirect()) {
                redirect = true;
            }
            if (fc.getPath().startsWith("/")) {
                url.append(request.getContextPath());
                url.append(forwardURL(request, fc));
            } else {
                url.append(fc.getPath());
            }
        } else if (href != null) {
            url.append(href);
        } else /* if (page != null) */ {
            url.append(request.getContextPath());
            url.append(pageURL(request, page));
        }

        // Add anchor if requested (replacing any existing anchor)
        if (anchor != null) {
            String temp = url.toString();
            int hash = temp.indexOf('#');
            if (hash >= 0) {
                url.setLength(hash);
            }
            url.append('#');
            url.append(URLEncoder.encode(anchor));
        }

        // Add dynamic parameters if requested
        if ((params != null) && (params.size() > 0)) {

            // Save any existing anchor
            String temp = url.toString();
            int hash = temp.indexOf('#');
            if (hash >= 0) {
                anchor = temp.substring(hash + 1);
                url.setLength(hash);
                temp = url.toString();
            } else {
                anchor = null;
            }

            // Define the parameter separator
            String separator = "&amp;";
            if (redirect) {
                separator = "&";
            }

            // Add the required request parameters
            boolean question = temp.indexOf('?') >= 0;
            Iterator keys = params.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                Object value = params.get(key);
                if (value == null) {
                    if (!question) {
                        url.append('?');
                        question = true;
                    } else {
                        url.append(separator);
                    }
                    url.append(URLEncoder.encode(key));
                    url.append('='); // Interpret null as "no value"
                } else if (value instanceof String) {
                    if (!question) {
                        url.append('?');
                        question = true;
                    } else {
                        url.append(separator);
                    }
                    url.append(URLEncoder.encode(key));
                    url.append('=');
                    url.append(URLEncoder.encode((String) value));
                } else if (value instanceof String[]) {
                    String values[] = (String[]) value;
                    for (int i = 0; i < values.length; i++) {
                        if (!question) {
                            url.append('?');
                            question = true;
                        } else {
                            url.append(separator);
                        }
                        url.append(URLEncoder.encode(key));
                        url.append('=');
                        url.append(URLEncoder.encode(values[i]));
                    }
                } else /* Convert other objects to a string */ {
                    if (!question) {
                        url.append('?');
                        question = true;
                    } else {
                        url.append(separator);
                    }
                    url.append(URLEncoder.encode(key));
                    url.append('=');
                    url.append(URLEncoder.encode(value.toString()));
                }
            }

            // Re-add the saved anchor (if any)
            if (anchor != null) {
                url.append('#');
                url.append(URLEncoder.encode(anchor));
            }

        }

        // Perform URL rewriting to include our session ID (if any)
        if (pageContext.getSession() != null) {
            HttpServletResponse response =
                (HttpServletResponse) pageContext.getResponse();
            if (redirect) {
                return (response.encodeRedirectURL(url.toString()));
            } else {
                return (response.encodeURL(url.toString()));
            }
        } else {
            return (url.toString());
        }

    }


    /**
     * Create (if necessary) and return an ActionForm instance appropriate
     * for this request.  If no ActionForm instance is required, return
     * <code>null</code>.
     *
     * @param request The servlet request we are processing
     * @param mapping The action mapping for this request
     * @param appConfig The application configuration for this module
     */
    public static ActionForm createActionForm(HttpServletRequest request,
                                              ActionMapping mapping,
                                              ApplicationConfig appConfig,
                                              ActionServlet servlet) {

        // Is there a form bean associated with this mapping?
        String attribute = mapping.getAttribute();
        if (attribute == null) {
            return (null);
        }

        // Look up the form bean configuration information to use
        String name = mapping.getName();
        FormBeanConfig config = appConfig.findFormBeanConfig(name);
        if (config == null) {
            return (null);
        }

        // Look up any existing form bean instance
        if (LOG.isDebugEnabled()) {
            LOG.debug(" Looking for ActionForm bean instance in scope '" +
                      mapping.getScope() + "' under attribute key '" +
                      attribute + "'");
        }
        ActionForm instance = null;
        HttpSession session = null;
        if ("request".equals(mapping.getScope())) {
            instance = (ActionForm) request.getAttribute(attribute);
        } else {
            session = request.getSession();
            instance = (ActionForm) session.getAttribute(attribute);
        }

        // Can we recycle the existing form bean instance (if there is one)?
        if (instance != null) {
            if (config.getDynamic()) {
                String className =
                    ((DynaBean) instance).getDynaClass().getName();
                if (className.equals(config.getName())) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug
                            (" Recycling existing DynaActionForm instance " +
                            "of type '" + className + "'");
                        LOG.trace(" --> " + instance);
                    }
                    return (instance);
                }
            } else {
                try {
                    Class configClass = applicationClass(config.getType());
                    if (configClass.isAssignableFrom(instance.getClass())) {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug
                                (" Recycling existing ActionForm instance " +
                                 "of class '" + instance.getClass().getName()
                                 + "'");
                            LOG.trace(" --> " + instance);
                        }
                        return (instance);
                    }
                    return (instance);
                } catch (Throwable t) {
                    LOG.error(servlet.getInternal().getMessage
                              ("formBean", config.getType()), t);
                    return (null);
                }
            }
        }

        // Create and return a new form bean instance
        if (config.getDynamic()) {
            try {
                DynaActionFormClass dynaClass =
                    DynaActionFormClass.createDynaActionFormClass(config);
                instance = (ActionForm) dynaClass.newInstance();
                if (LOG.isDebugEnabled()) {
                    LOG.debug
                        (" Creating new DynaActionForm instance " +
                            "of type '" + config.getType() + "'");
                    LOG.trace(" --> " + instance);
                }
            } catch (Throwable t) {
                LOG.error(servlet.getInternal().getMessage
                            ("formBean", config.getType()), t);
                return (null);
            }
        } else {
            try {
                instance = (ActionForm) applicationInstance(config.getType());
                if (LOG.isDebugEnabled()) {
                    LOG.debug
                        (" Creating new ActionForm instance " +
                            "of type '" + config.getType() + "'");
                    LOG.trace(" --> " + instance);
                }
            } catch (Throwable t) {
                LOG.error(servlet.getInternal().getMessage
                          ("formBean", config.getType()), t);
                return (null);
            }
        }
        instance.setServlet(servlet);
        return (instance);

    }


    /**
     * Locate and return the specified bean, from an optionally specified
     * scope, in the specified page context.  If no such bean is found,
     * return <code>null</code> instead.  If an exception is thrown, it will
     * have already been saved via a call to <code>saveException()</code>.
     *
     * @param pageContext Page context to be searched
     * @param name Name of the bean to be retrieved
     * @param scope Scope to be searched (page, request, session, application)
     *  or <code>null</code> to use <code>findAttribute()</code> instead
     *
     * @exception JspException if an invalid scope name
     *  is requested
     */
    public static Object lookup(PageContext pageContext, String name,
                                String scope) throws JspException {

        Object bean = null;
        if (scope == null)
            bean = pageContext.findAttribute(name);
        else if (scope.equalsIgnoreCase("page"))
            bean = pageContext.getAttribute(name, PageContext.PAGE_SCOPE);
        else if (scope.equalsIgnoreCase("request"))
            bean = pageContext.getAttribute(name, PageContext.REQUEST_SCOPE);
        else if (scope.equalsIgnoreCase("session"))
            bean = pageContext.getAttribute(name, PageContext.SESSION_SCOPE);
        else if (scope.equalsIgnoreCase("application"))
            bean =
                pageContext.getAttribute(name, PageContext.APPLICATION_SCOPE);
        else {
            JspException e = new JspException
                (messages.getMessage("lookup.scope", scope));
            saveException(pageContext, e);
            throw e;
        }

        return (bean);

    }


    /**
     * Locate and return the specified property of the specified bean, from
     * an optionally specified scope, in the specified page context.  If an
     * exception is thrown, it will have already been saved via a call to
     * <code>saveException()</code>.
     *
     * @param pageContext Page context to be searched
     * @param name Name of the bean to be retrieved
     * @param property Name of the property to be retrieved, or
     *  <code>null</code> to retrieve the bean itself
     * @param scope Scope to be searched (page, request, session, application)
     *  or <code>null</code> to use <code>findAttribute()</code> instead
     *
     * @exception JspException if an invalid scope name
     *  is requested
     * @exception JspException if the specified bean is not found
     * @exception JspException if accessing this property causes an
     *  IllegalAccessException, IllegalArgumentException,
     *  InvocationTargetException, or NoSuchMethodException
     */
    public static Object lookup(PageContext pageContext, String name,
                                String property, String scope)
        throws JspException {

        // Look up the requested bean, and return if requested
        Object bean = lookup(pageContext, name, scope);
        if (bean == null) {
            JspException e = null;
            if (scope == null) {
                e = new JspException
                    (messages.getMessage("lookup.bean.any", name));
            } else {
                e = new JspException
                    (messages.getMessage("lookup.bean", name, scope));
            }
            saveException(pageContext, e);
            throw e;
        }
        if (property == null)
            return (bean);

        // Locate and return the specified property
        try {
            return (PropertyUtils.getProperty(bean, property));
        } catch (IllegalAccessException e) {
            saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("lookup.access", property, name));
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t == null)
                t = e;
            saveException(pageContext, t);
            throw new JspException
                (messages.getMessage("lookup.target", property, name));
        } catch (NoSuchMethodException e) {
            saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("lookup.method", property, name));
        }

    }


    /**
     * Look up and return current user locale, based on the specified parameters.
     *
     * @param pageContext The PageContext associated with this request
     * @param locale Name of the session attribute for our user's Locale
     */
    public static Locale retrieveUserLocale( PageContext pageContext, String locale ) {
        if (locale == null)
            locale = Action.LOCALE_KEY;
        Locale userLocale = (Locale)
            pageContext.getAttribute(locale, PageContext.SESSION_SCOPE);
        if (userLocale == null)
            userLocale = defaultLocale;
        return userLocale;
    }

    /**
     * Look up and return a message string, based on the specified parameters.
     *
     * @param pageContext The PageContext associated with this request
     * @param bundle Name of the servlet context attribute for our
     *  message resources bundle
     * @param locale Name of the session attribute for our user's Locale
     * @param key Message key to be looked up and returned
     *
     * @exception JspException if a lookup error occurs (will have been
     *  saved in the request already)
     */
    public static String message(PageContext pageContext, String bundle,
                                 String locale, String key)
        throws JspException {

        return (message(pageContext, bundle, locale, key, null));

    }


    /**
     * Look up and return a message string, based on the specified parameters.
     *
     * @param pageContext The PageContext associated with this request
     * @param bundle Name of the servlet context attribute for our
     *  message resources bundle
     * @param locale Name of the session attribute for our user's Locale
     * @param key Message key to be looked up and returned
     * @param args Replacement parameters for this message
     *
     * @exception JspException if a lookup error occurs (will have been
     *  saved in the request already)
     */
    public static String message(PageContext pageContext, String bundle,
                                 String locale, String key, Object args[])
        throws JspException {

        MessageResources resources = null;

        // Look up the requested MessageResources
        if (bundle == null) {
            bundle = Action.MESSAGES_KEY;
            resources = (MessageResources)
                pageContext.getAttribute(bundle, PageContext.REQUEST_SCOPE);


        }
        if (resources == null) {
            resources = (MessageResources)
                pageContext.getAttribute(bundle,
                                         PageContext.APPLICATION_SCOPE);
        }
        if (resources == null) {
            JspException e = new JspException
                (messages.getMessage("message.bundle", bundle));
            saveException(pageContext, e);
            throw e;
        }

        // Look up the requested Locale
        if (locale == null)
            locale = Action.LOCALE_KEY;
        Locale userLocale = retrieveUserLocale( pageContext, locale );

        // Return the requested message
        if (args == null) {
            return (resources.getMessage(userLocale, key));
        } else {
            return (resources.getMessage(userLocale, key, args));
        }

    }


    /**
     * Populate the properties of the specified JavaBean from the specified
     * HTTP request, based on matching each parameter name against the
     * corresponding JavaBeans "property setter" methods in the bean's class.
     * Suitable conversion is done for argument types as described under
     * <code>convert()</code>.
     *
     * @param bean The JavaBean whose properties are to be set
     * @param request The HTTP request whose parameters are to be used
     *                to populate bean properties
     *
     * @exception ServletException if an exception is thrown while setting
     *            property values
     */
    public static void populate(Object bean,
                                HttpServletRequest request)
        throws ServletException {

        populate(bean, null, null, request);

    }


    /**
     * Populate the properties of the specified JavaBean from the specified
     * HTTP request, based on matching each parameter name (plus an optional
     * prefix and/or suffix) against the corresponding JavaBeans "property
     * setter" methods in the bean's class.  Suitable conversion is done for
     * argument types as described under <code>setProperties()</code>.
     * <p>
     * If you specify a non-null <code>prefix</code> and a non-null
     * <code>suffix</code>, the parameter name must match <strong>both</strong>
     * conditions for its value(s) to be used in populating bean properties.
     * If the request's content type is "multipart/form-data" and the
     * method is "POST", the HttpServletRequest object will be wrapped in
     * a MultipartRequestWrapper object.
     *
     * @param bean The JavaBean whose properties are to be set
     * @param prefix The prefix (if any) to be prepend to bean property
     *               names when looking for matching parameters
     * @param suffix The suffix (if any) to be appended to bean property
     *               names when looking for matching parameters
     * @param request The HTTP request whose parameters are to be used
     *                to populate bean properties
     *
     * @exception ServletException if an exception is thrown while setting
     *            property values
     */
    public static void populate(Object bean, String prefix, String suffix,
                                HttpServletRequest request)
        throws ServletException {

        // Build a list of relevant request parameters from this request
        HashMap properties = new HashMap();
        // Iterator of parameter names
        Enumeration names = null;
        // Hashtable for multipart values
        Hashtable multipartElements = null;

        String contentType = request.getContentType();
        String method = request.getMethod();
        boolean isMultipart = false;

        if ((contentType != null) &&
            (contentType.startsWith("multipart/form-data")) &&
            (method.equalsIgnoreCase("POST"))) {

            // Get the ActionServletWrapper from the form bean
            ActionServletWrapper servlet;
            if (bean instanceof ActionForm) {
                servlet = ((ActionForm) bean).getServletWrapper();
            } else {
                throw new ServletException("bean that's supposed to be " +
                   "populated from a multipart request is not of type " +
                   "\"org.apache.struts.action.ActionForm\", but type " +
                   "\"" + bean.getClass().getName() + "\"");
            }

            // Obtain a MultipartRequestHandler
            MultipartRequestHandler multipartHandler = getMultipartHandler(request);

            // Set the multipart request handler for our ActionForm.
            // If the bean isn't an ActionForm, an exception would have been
            // thrown earlier, so it's safe to assume that our bean is
            // in fact an ActionForm.
            ((ActionForm) bean).setMultipartRequestHandler(multipartHandler);

            if (multipartHandler != null)
            {
                isMultipart = true;
                // Set servlet and mapping info
                servlet.setServletFor(multipartHandler);
                multipartHandler.setMapping((ActionMapping) request.getAttribute(Action.MAPPING_KEY));
                // Initialize multipart request class handler
                multipartHandler.handleRequest(request);
                //stop here if the maximum length has been exceeded
                Boolean maxLengthExceeded = (Boolean)
                                            request.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
                if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue()))
                {
                    return;
                }
                //retrive form values and put into properties
                multipartElements = multipartHandler.getAllElements();
                names = multipartElements.keys();
            }
            request.removeAttribute(Action.MAPPING_KEY);
        }

        if (!isMultipart) {
            names = request.getParameterNames();
        }

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String stripped = name;
            if (prefix != null) {
                if (!stripped.startsWith(prefix))
                    continue;
                stripped = stripped.substring(prefix.length());
            }
            if (suffix != null) {
                if (!stripped.endsWith(suffix))
                    continue;
                stripped =
                    stripped.substring(0, stripped.length() - suffix.length());
            }
            if (isMultipart) {
                properties.put(stripped, multipartElements.get(name));
            }
            else {
                properties.put(stripped, request.getParameterValues(name));
            }
        }

        // Set the corresponding properties of our bean
        try {
            BeanUtils.populate(bean, properties);
        } catch (Exception e) {
            throw new ServletException("BeanUtils.populate", e);
        }

    }


    /**
     * Try to locate a multipart request handler for this request. First, look
     * for a mapping-specific handler stored for us under an attribute. If one
     * is not present, use the global multipart handler, if there is one.
     *
     * @param request The HTTP request for which the multipart handler should
     *                be found.
     * @return the multipart handler to use, or <code>null</code> if none is
     *         found.
     *
     * @exception ServletException if any exception is thrown while attempting
     *                             to locate the multipart handler.
     */
    private static MultipartRequestHandler getMultipartHandler(
            HttpServletRequest request)
        throws ServletException {

        MultipartRequestHandler multipartHandler = null;
        String multipartClass = (String)
            request.getAttribute(Action.MULTIPART_KEY);
        request.removeAttribute(Action.MULTIPART_KEY);

        // Try to initialize the mapping specific request handler
        if (multipartClass != null) {
            try {
                multipartHandler = (MultipartRequestHandler)
                    applicationInstance(multipartClass);
            }
            catch (ClassNotFoundException cnfe) {
                LOG.error("MultipartRequestHandler class \"" +
                    multipartClass + "\" in mapping class not found, " +
                    "defaulting to global multipart class");
            }
            catch (InstantiationException ie) {
                LOG.error("InstantiaionException when instantiating " +
                    "MultipartRequestHandler \"" + multipartClass + "\", " +
                    "defaulting to global multipart class, exception: " +
                    ie.getMessage());
            }
            catch (IllegalAccessException iae) {
                LOG.error("IllegalAccessException when instantiating " +
                    "MultipartRequestHandler \"" + multipartClass + "\", " +
                    "defaulting to global multipart class, exception: " +
                    iae.getMessage());
            }

            if (multipartHandler != null)
                return multipartHandler;
        }

        ApplicationConfig appConfig = (ApplicationConfig)
            request.getAttribute(Action.APPLICATION_KEY);
        multipartClass = appConfig.getControllerConfig().getMultipartClass();

        // Try to initialize the global request handler
        if (multipartClass != null) {
            try {
                multipartHandler = (MultipartRequestHandler)
                    applicationInstance(multipartClass);
            }
            catch (ClassNotFoundException cnfe) {
                throw new ServletException("Cannot find multipart class \"" +
                    multipartClass + "\"" +
                    ", exception: " + cnfe.getMessage());
            }
            catch (InstantiationException ie) {
                throw new ServletException(
                    "InstantiaionException when instantiating " +
                    "multipart class \"" + multipartClass +
                    "\", exception: " + ie.getMessage());
            }
            catch (IllegalAccessException iae) {
                throw new ServletException(
                    "IllegalAccessException when instantiating " +
                    "multipart class \"" + multipartClass +
                    "\", exception: " + iae.getMessage());
            }

            if (multipartHandler != null)
                return multipartHandler;
        }

        return multipartHandler;
    }


    /**
     * Return true if a message string for the specified message key
     * is present for the specified Locale.
     *
     * @param pageContext The PageContext associated with this request
     * @param bundle Name of the servlet context attribute for our
     *  message resources bundle
     * @param locale Name of the session attribute for our user's Locale
     * @param key Message key to be looked up and returned
     *
     * @exception JspException if a lookup error occurs (will have been
     *  saved in the request already)
     */
    public static boolean present(PageContext pageContext, String bundle,
                                  String locale, String key)
        throws JspException {

        MessageResources resources = null;

        // Look up the requested MessageResources
        if (bundle == null) {
            bundle = Action.MESSAGES_KEY;
            resources = (MessageResources)
                pageContext.getAttribute(bundle);
        }
        if (resources == null) {
            resources = (MessageResources)
                pageContext.getAttribute(bundle,
                                         PageContext.APPLICATION_SCOPE);
        }
        if (resources == null) {
            JspException e = new JspException
                (messages.getMessage("message.bundle", bundle));
            saveException(pageContext, e);
            throw e;
        }

        // Look up the requested Locale
        if (locale == null)
            locale = Action.LOCALE_KEY;
        Locale userLocale = (Locale)
            pageContext.getAttribute(locale, PageContext.SESSION_SCOPE);
        if (userLocale == null)
            userLocale = defaultLocale;

        // Return the requested message presence indicator
        return (resources.isPresent(userLocale, key));

    }


    /**
     * Compute the printable representation of a URL, leaving off the
     * scheme/host/port part if no host is specified.  This will typically
     * be the case for URLs that were originally created from relative
     * or context-relative URIs.
     *
     * @param url URL to render in a printable representation
     */
    public static String printableURL(URL url) {

        if (url.getHost() != null)
            return (url.toString());

        String file = url.getFile();
        String ref = url.getRef();
        if (ref == null)
            return (file);
        else {
            StringBuffer sb = new StringBuffer(file);
            sb.append('#');
            sb.append(ref);
            return (sb.toString());
        }

    }


    /**
     * Return the context-relative URL that corresponds to the specified
     * {@link ActionConfig}, relative to the application module associated
     * with the current modules's {@link ApplicationConfig}.
     *
     * @param request The servlet request we are processing
     * @param action ActionConfig to be evaluated
     * @param pattern URL pattern used to map the controller servlet
     *
     * @since Struts 1.1b2
     */
    public static String actionURL(HttpServletRequest request,
                                   ActionConfig action,
                                   String pattern) {

        StringBuffer sb = new StringBuffer();
        if (pattern.endsWith("/*")) {
            sb.append(pattern.substring(0, pattern.length() - 2));
            sb.append(action.getPath());
        } else if (pattern.startsWith("*.")) {
            ApplicationConfig appConfig = (ApplicationConfig)
                request.getAttribute(Action.APPLICATION_KEY);
            sb.append(appConfig.getPrefix());
            sb.append(action.getPath());
            sb.append(pattern.substring(1));
        } else {
            throw new IllegalArgumentException(pattern);
        }
        return (sb.toString());

    }

    /**
     * Return the context-relative URL that corresponds to the specified
     * {@link ForwardConfig}, relative to the module associated
     * with the current {@link ApplicationConfig}. The forward path is
     * gracefully prefixed with a '/' according to the boolean
     *
     *
     * @param request The servlet request we are processing
     * @param forward ForwardConfig to be evaluated
     * @since Struts 1.1b2
     */
    public static String forwardURL(HttpServletRequest request,
                                    ForwardConfig forward) {

        String path = forward.getPath();

        // Handle a ForwardConfig marked as context relative
        StringBuffer sb = new StringBuffer();
        if (forward.getContextRelative()) {
            if ( !path.startsWith("/") ) {
                sb.append("/");
            }
            sb.append(path);
            return (sb.toString());
        }

        // Calculate a context relative path for this ForwardConfig
        ApplicationConfig appConfig = (ApplicationConfig)
            request.getAttribute(Action.APPLICATION_KEY);
        String forwardPattern =
            appConfig.getControllerConfig().getForwardPattern();
        if (forwardPattern == null) {
            // Performance optimization for previous default behavior
            sb.append(appConfig.getPrefix());
            // smoothly insert a '/' if needed
            if (!path.startsWith("/")) {
                sb.append("/");
            }
            sb.append(path);
        } else {
            boolean dollar = false;
            for (int i = 0; i < forwardPattern.length(); i++) {
                char ch = forwardPattern.charAt(i);
                if (dollar) {
                    switch (ch) {
                    case 'M':
                        sb.append(appConfig.getPrefix());
                        break;
                    case 'P':
                        // add '/' if needed
                        if (!path.startsWith("/")) {
                            sb.append("/");
                        }
                        sb.append(path);
                        break;
                    case '$':
                        sb.append('$');
                        break;
                    default:
                        ; // Silently swallow
                    }
                    dollar = false;
                    continue;
                } else if (ch == '$') {
                    dollar = true;
                } else {
                    sb.append(ch);
                }
            }
        }
        return (sb.toString());

    }


    /**
     * Return the context-relative URL that corresponds to the specified
     * <code>page</code> attribute value, calculated based on the
     * <code>pagePattern</code> property of the current module's
     * {@link ApplicationConfig}.
     *
     * @param request The servlet request we are processing
     * @param page The module-relative URL to be substituted in
     *  to the <code>pagePattern</code> pattern for the current module.
     *
     * @since Struts 1.1b2
     */
    public static String pageURL(HttpServletRequest request,
                                 String page) {

        StringBuffer sb = new StringBuffer();
        ApplicationConfig appConfig = (ApplicationConfig)
            request.getAttribute(Action.APPLICATION_KEY);
        String pagePattern =
            appConfig.getControllerConfig().getPagePattern();
        if (pagePattern == null) {
            sb.append(appConfig.getPrefix());
            sb.append(page);
        } else {
            boolean dollar = false;
            for (int i = 0; i < pagePattern.length(); i++) {
                char ch = pagePattern.charAt(i);
                if (dollar) {
                    switch (ch) {
                    case 'M':
                        sb.append(appConfig.getPrefix());
                        break;
                    case 'P':
                        sb.append(page);
                        break;
                    case '$':
                        sb.append('$');
                        break;
                    default:
                        ; // Silently swallow
                    }
                    dollar = false;
                    continue;
                } else if (ch == '$') {
                    dollar = true;
                } else {
                    sb.append(ch);
                }
            }
        }
        return (sb.toString());

    }


    /**
     * Return the URL representing the current request.  This is equivalent
     * to <code>HttpServletRequest.getRequestURL()</code> in Servlet 2.3.
     *
     * @param request The servlet request we are processing
     *
     * @exception MalformedURLException if a URL cannot be created
     */
    public static URL requestURL(HttpServletRequest request)
        throws MalformedURLException {

        StringBuffer url = new StringBuffer();
        String scheme = request.getScheme();
        int port = request.getServerPort();
        if (port < 0)
            port = 80; // Work around java.net.URL bug
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) ||
            (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getRequestURI());
        return (new URL(url.toString()));

    }


    /**
     * Return the URL representing the scheme, server, and port number of
     * the current request.  Server-relative URLs can be created by simply
     * appending the server-relative path (starting with '/') to this.
     *
     * @param request The servlet request we are processing
     *
     * @exception MalformedURLException if a URL cannot be created
     */
    public static URL serverURL(HttpServletRequest request)
        throws MalformedURLException {

        StringBuffer url = new StringBuffer();
        String scheme = request.getScheme();
        int port = request.getServerPort();
        if (port < 0)
            port = 80; // Work around java.net.URL bug
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) ||
            (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        return (new URL(url.toString()));

    }


    /**
     * Save the specified exception as a request attribute for later use.
     *
     * @param pageContext The PageContext for the current page
     * @param exception The exception to be saved
     */
    public static void saveException(PageContext pageContext,
                                     Throwable exception) {

        pageContext.setAttribute(Action.EXCEPTION_KEY, exception,
                                 PageContext.REQUEST_SCOPE);

    }


    /**
     * Select the application module to which the specified request belongs, and
     * add corresponding request attributes to this request.
     *
     * @param prefix The module prefix of the desired application module
     * @param request The servlet request we are processing
     * @param context The ServletContext for this web application
     */
    public static void selectApplication(String prefix,
                                         HttpServletRequest request,
                                         ServletContext context) {

        // Expose the resources for this application module
        ApplicationConfig config = (ApplicationConfig)
            context.getAttribute(Action.APPLICATION_KEY + prefix);
        if (config != null) {
            request.setAttribute(Action.APPLICATION_KEY, config);
        } else {
            request.removeAttribute(Action.APPLICATION_KEY);
        }
        MessageResources resources = (MessageResources)
            context.getAttribute(Action.MESSAGES_KEY + prefix);
        if (resources != null) {
            request.setAttribute(Action.MESSAGES_KEY, resources);
        } else {
            request.removeAttribute(Action.MESSAGES_KEY);
        }

    }


    /**
     * Select the module to which the specified request belongs, and
     * add corresponding request attributes to this request.
     *
     * @param request The servlet request we are processing
     * @param context The ServletContext for this web application
     */
    public static void selectApplication(HttpServletRequest request,
                                         ServletContext context) {

        // Acquire the path used to compute the module
        String matchPath = (String)
            request.getAttribute(RequestProcessor.INCLUDE_SERVLET_PATH);
	
        if (matchPath == null) {
            matchPath = request.getServletPath();
        }

        if (LOG.isDebugEnabled())
            {
                LOG.debug("Selecting module for path " + matchPath);
            }

        String prefix = "";  // Initialize prefix before we try lookup
        String prefixes[] =
            getApplicationPrefixes(context); // Get all other possible prefixes
        int lastSlash = 0;  // Initialize before loop
        
        while (prefix.equals("") &&
               ((lastSlash = matchPath.lastIndexOf("/")) != 0)) {
            
            // We may be in a non-default sub-app.  Try to get it's prefix.
            matchPath = matchPath.substring(0, lastSlash);
            
            // Match against the list of module prefixes
            for (int i = 0; i < prefixes.length; i++) {
                if (matchPath.equals(prefixes[i])) {
                    prefix = prefixes[i];
                    break;
                }
            }
        }

        if (LOG.isDebugEnabled())
            {
                LOG.debug("Activating module " +
                          (prefix.equals("") ? "default" : prefix));
            }

        // Expose the resources for this module
        selectApplication(prefix, request, context);

    }

    /**
     * Return the ApplicationConfig object is it exists, null otherwise.
     * @param pageContext The page context.
     * @return the ApplicationConfig object
     * @since 1.1b3
     */
    public static ApplicationConfig getApplicationConfig(PageContext pageContext) {
       ApplicationConfig appConfig = (ApplicationConfig)
           pageContext.getRequest().getAttribute(Action.APPLICATION_KEY);
       if (appConfig == null) { // Backwards compatibility hack
           appConfig = (ApplicationConfig)
               pageContext.getServletContext().getAttribute(Action.APPLICATION_KEY);
       }
       return appConfig;
    }

    /**
     * Return the ApplicationConfig object is it exists, null otherwise.
     * @param request The servlet request we are processing
     * @param context The ServletContext for this web application
     * @return the ApplicationConfig object
     * @since 1.1b3
     */
    public static ApplicationConfig getApplicationConfig(HttpServletRequest request,ServletContext context) {
        ApplicationConfig appConfig = (ApplicationConfig)
            request.getAttribute(Action.APPLICATION_KEY);
        if (appConfig == null) {
            appConfig = (ApplicationConfig)
                context.getAttribute(Action.APPLICATION_KEY);
        }
       return appConfig;
    }

    /**
     * Return the list of module prefixes that are defined for
     * this web application, creating it if necessary.  <strong>NOTE</strong> -
     * the "" prefix for the default module is not included in this list.
     *
     * @param context The ServletContext for this web application
     */
    public static String[] getApplicationPrefixes(ServletContext context) {

        String prefixes[] = (String[]) context.getAttribute(PREFIXES_KEY);
        if (prefixes != null) {
            return (prefixes);
        }

        ArrayList list = new ArrayList();
        Enumeration names = context.getAttributeNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            if (!name.startsWith(Action.APPLICATION_KEY)) {
                continue;
            }
            String prefix = name.substring(Action.APPLICATION_KEY.length());
            if (prefix.length() > 0) {
                list.add(prefix);
            }
        }
        prefixes = (String[]) list.toArray(new String[list.size()]);
        context.setAttribute(PREFIXES_KEY, prefixes);
        return (prefixes);

    }


    /**
     * Retrieves the value from request scope and if it isn't already an <code>ActionMessages</code>
     * some classes are converted to one.
     *
     * @param pageContext   The PageContext for the current page
     * @param paramName     Key for parameter value
     */
    public static ActionMessages getActionMessages(PageContext pageContext, String paramName)
       throws JspException {

        ActionMessages am = new ActionMessages();

        Object value = pageContext.findAttribute(paramName);

        try {
            if (value == null) {
               ;
            } else if (value instanceof String) {
               am.add(ActionMessages.GLOBAL_MESSAGE,
                                 new ActionMessage((String) value));
            } else if (value instanceof String[]) {
               String keys[] = (String[]) value;
               for (int i = 0; i < keys.length; i++)
                  am.add(ActionMessages.GLOBAL_MESSAGE,
                               new ActionMessage(keys[i]));
            } else if (value instanceof ErrorMessages) {
                String keys[] = ((ErrorMessages) value).getErrors();
                if (keys == null)
                    keys = new String[0];
                for (int i = 0; i < keys.length; i++)
                    am.add(ActionErrors.GLOBAL_ERROR,
                                 new ActionError(keys[i]));
            } else if (value instanceof ActionMessages) {
                am = (ActionMessages) value;
            } else {
               throw new JspException
                  (messages.getMessage("actionMessages.errors", value.getClass().getName()));
            }
        } catch (JspException e) {
            throw e;
        } catch (Exception e) {
            ;
        }

        return am;
    }

    /**
     * Retrieves the value from request scope and if it isn't already an <code>ErrorMessages</code>
     * some classes are converted to one.
     *
     * @param pageContext   The PageContext for the current page
     * @param paramName     Key for parameter value
     */
    public static ActionErrors getActionErrors(PageContext pageContext, String paramName)
       throws JspException {

        ActionErrors errors = new ActionErrors();

        Object value = pageContext.findAttribute(paramName);

        try {
        if (value == null) {
        ;
        } else if (value instanceof String) {
        errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError((String) value));
        } else if (value instanceof String[]) {
                String keys[] = (String[]) value;
                for (int i = 0; i < keys.length; i++)
                    errors.add(ActionErrors.GLOBAL_ERROR,
                               new ActionError(keys[i]));
            } else if (value instanceof ErrorMessages) {
        String keys[] = ((ErrorMessages) value).getErrors();
                if (keys == null)
                    keys = new String[0];
                for (int i = 0; i < keys.length; i++)
                    errors.add(ActionErrors.GLOBAL_ERROR,
                               new ActionError(keys[i]));
            } else if (value instanceof ActionErrors) {
                errors = (ActionErrors) value;
            } else {
               throw new JspException
                  (messages.getMessage("actionErrors.errors", value.getClass().getName()));
            }
        } catch (JspException e) {
            throw e;
        } catch (Exception e) {
            ;
        }

        return errors;
    }

}
