/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/ConfigHelper.java,v 1.5 2003/05/04 22:41:13 dgraham Exp $
 * $Revision: 1.5 $
 * $Date: 2003/05/04 22:41:13 $
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

package org.apache.struts.config;

import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionFormBean;
import org.apache.struts.action.ActionFormBeans;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForwards;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMappings;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.apache.struts.util.MessageResources;

/**
 * NOTE: THIS CLASS IS UNDER ACTIVE DEVELOPMENT.
 * THE CURRENT CODE IS WRITTEN FOR CLARITY NOT EFFICIENCY.
 * NOT EVERY API FUNCTION HAS BEEN IMPLEMENTED YET.
 *
 * A helper object to expose the Struts shared resources,
 * which are be stored in the application, session, or
 * request contexts, as appropriate.
 *
 * An instance should be created for each request
 * processed. The  methods which return resources from
 * the request or session contexts are not thread-safe.
 *
 * Provided for use by other servlets in the application
 * so they can easily access the Struts shared resources.
 *
 * The resources are stored under attributes in the
 * application, session, or request contexts.
 *
 * The ActionConfig methods simply return the resources
 * from under the context and key used by the Struts
 * ActionServlet when the resources are created.
 *
 * @since 1.1
 * @author Ted Husted
 * @author Luis Arias <luis@elysia.com>
 * @version $Revision: 1.5 $ $Date: 2003/05/04 22:41:13 $
 */
public class ConfigHelper implements ConfigHelperInterface {

    // --------------------------------------------------------  Properites

    /**
     * The application associated with this instance.
     */
    private ServletContext application = null;

    /**
     * Set the application associated with this instance.
     * [servlet.getServletContext()]
     */
    public void setApplication(ServletContext application) {
        this.application = application;
    }

    /**
     * The session associated with this instance.
     */
    private HttpSession session = null;

    /**
     * Set the session associated with this instance.
     */
    public void setSession(HttpSession session) {
        this.session = session;
    }

    /**
     * The request associated with this instance.
     */
    private HttpServletRequest request = null;

    /**
     * Set the request associated with this object.
     * Session object is also set or cleared.
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
        if (this.request == null)
            setSession(null);
        else
            setSession(this.request.getSession());
    }

    /**
     * The response associated with this instance.
     */
    private HttpServletResponse response = null;

    /**
     * Set the response associated with this isntance.
     * Session object is also set or cleared.
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * The forward associated with this instance.
     */
    private ActionForward forward = null;

    /**
     * Set the forward associated with this instance.
     */
    public void setForward(ActionForward forward) {
        this.forward = forward;
    }

    /**
     * Set the application and request for this object instance.
     * The ServletContext can be set by any servlet in the application.
     * The request should be the instant request.
     * Most of the other methods retrieve their own objects
     * by reference to the application, request, or session
     * attributes.
     * Do not call other methods without setting these first!
     * This is also called by the convenience constructor.
     *
     * @param application - The associated ServletContext.
     * @param request - The associated HTTP request.
     * @param response - The associated HTTP response.
     */
    public void setResources(
        ServletContext application,
        HttpServletRequest request,
        HttpServletResponse response) {
        setApplication(application);
        setRequest(request);
        setResponse(response);
    }

    // ------------------------------------------------ Application Context

    /**
     * The strong>default</strong>
     * configured data source (which must implement
     * <code>javax.sql.DataSource</code>),
     * if one is configured for this application.
     */
    public DataSource getDataSource() {

        if (this.application == null)
            return null;
        return (DataSource) this.application.getAttribute(Globals.DATA_SOURCE_KEY);

    }

    public ActionMessages getActionMessages() {

        if (this.application == null)
            return null;
        return (ActionMessages) this.application.getAttribute(Globals.MESSAGE_KEY);

    }

    /**
     * The <code>org.apache.struts.action.ActionFormBeans</code> collection
     * for this application.
     */
    public ActionFormBeans getActionFormBeans() {

        if (this.application == null) {
            return null;
        }
        return (ActionFormBeans) this.application.getAttribute(Globals.FORM_BEANS_KEY);

    }

    /**
     * The <code>org.apache.struts.action.ActionForwards</code> collection
     * for this application.
     */
    public ActionForwards getActionForwards() {

        if (this.application == null) {
            return null;
        }
        return (ActionForwards) this.application.getAttribute(Globals.FORWARDS_KEY);

    }

    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionMappings</code> collection
     * is normally stored, unless overridden when initializing our
     * ActionServlet.
     */
    public ActionMappings getActionMappings() {
        if (this.application == null) {
            return null;
        }
        return (ActionMappings) this.application.getAttribute(Globals.MAPPINGS_KEY);
    }

    /**
     * The application resources for this application.
     */
    public MessageResources getMessageResources() {

        if (this.application == null) {
            return null;
        }
        return (MessageResources) this.application.getAttribute(Globals.MESSAGES_KEY);

    }

    /**
     * The path-mapped pattern (<code>/action/*</code>) or
     * extension mapped pattern ((<code>*.do</code>)
     * used to determine our Action URIs in this application.
     */
    public String getServletMapping() {

        if (this.application == null) {
            return null;
        }
        return (String) this.application.getAttribute(Globals.SERVLET_KEY);

    }

    // ---------------------------------------------------- Session Context

    /**
     * The <code>java.util.Locale</code> for the user, if any.
     * If a default locale object is not in the user's session,
     * the system default locale is returned.
     * If used, the user locale is typically set during login
     * processing under the key <code>Globals.LOCALE_KEY</code>.
     */
    public Locale getLocale() {
        Locale locale = null;

        if (session != null) {
            locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        }

        if ((locale == null) && (request != null)) {
            locale = request.getLocale();
        }

        return locale;
    }

    /**
     * The transaction token stored in this session, if it is used.
     */
    public String getToken() {

        if (this.session == null) {
            return null;
        }
        return (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);

    }

    // ---------------------------------------------------- Request Context

    /**
     * The <code>org.apache.struts.action.ActionErrors</code> object,
     * for this request.
     */
    public ActionErrors getActionErrors() {

        if (this.request == null) {
            return null;
        }
        return (ActionErrors) this.request.getAttribute(Globals.ERROR_KEY);

    }

    /**
     * The runtime JspException that may be been thrown by a Struts tag
     * extension, or compatible presentation extension, and placed
     * in the request.
     */
    public Throwable getException() {

        if (this.request == null) {
            return null;
        }
        return (Throwable) this.request.getAttribute(Globals.EXCEPTION_KEY);

    }

    /**
     * The multipart object for this request.
     */
    public MultipartRequestWrapper getMultipartRequestWrapper() {

        if (this.request == null) {
            return null;
        }
        return (MultipartRequestWrapper) this.request.getAttribute(Globals.MULTIPART_KEY);
    }

    /**
      * The <code>org.apache.struts.ActionMapping</code>
      * instance for this request.
      */
    public ActionMapping getMapping() {

        if (this.request == null) {
            return null;
        }
        return (ActionMapping) this.request.getAttribute(Globals.MAPPING_KEY);

    }

    // ---------------------------------------------------- Utility Methods

    /**
     * Return true if a message string for the specified message key
     * is present for the user's Locale.
     *
     * @param key Message key
     */
    public boolean isMessage(String key) {

        // Look up the requested MessageResources
        MessageResources resources = getMessageResources();

        if (resources == null) {
            return false;
        }

        // Return the requested message presence indicator
        return (resources.isPresent(getLocale(), key));

    }

    /*
     * Retrieve and return the <code>ActionForm</code> bean associated with
     * this mapping, creating and stashing one if necessary.  If there is no
     * form bean associated with this mapping, return <code>null</code>.
     *
     */
    public ActionForm getActionForm() {

        // Is there a mapping associated with this request?
        ActionMapping mapping = getMapping();
        if (mapping == null)
            return (null);

        // Is there a form bean associated with this mapping?
        String attribute = mapping.getAttribute();
        if (attribute == null)
            return (null);

        // Look up the existing form bean, if any
        ActionForm instance = null;
        if ("request".equals(mapping.getScope())) {
            instance = (ActionForm) this.request.getAttribute(attribute);
        } else {
            instance = (ActionForm) this.session.getAttribute(attribute);
        }

        return instance;
    }

    /**
     * Return the form bean definition associated with the specified
     * logical name, if any; otherwise return <code>null</code>.
     *
     * @param name Logical name of the requested form bean definition
     */
    public ActionFormBean getFormBean(String name) {

        ActionFormBeans formBeans = getActionFormBeans();

        if (formBeans == null)
            return null;
        return formBeans.findFormBean(name);

    }

    /**
     * Return the forwarding associated with the specified logical name,
     * if any; otherwise return <code>null</code>.
     *
     * @param name Logical name of the requested forwarding
     */
    public ActionForward getActionForward(String name) {

        ActionForwards forwards = getActionForwards();

        if (forwards == null)
            return null;
        return forwards.findForward(name);

    }

    /**
     * Return the mapping associated with the specified request path, if any;
     * otherwise return <code>null</code>.
     *
     * @param path Request path for which a mapping is requested
     */
    public ActionMapping getActionMapping(String path) {

        ActionMappings mappings = getActionMappings();

        if (mappings == null)
            return null;
        return mappings.findMapping(path);

    }

    /**
     * Return the form action converted into an action mapping path.  The
     * value of the <code>action</code> property is manipulated as follows in
     * computing the name of the requested mapping:
     * <ul>
     * <li>Any filename extension is removed (on the theory that extension
     *     mapping is being used to select the controller servlet).</li>
     * <li>If the resulting value does not start with a slash, then a
     *     slash is prepended.</li>
     * </ul>
     */
    public String getActionMappingName(String action) {

        String value = action;
        int question = action.indexOf("?");
        if (question >= 0)
            value = value.substring(0, question);
        int slash = value.lastIndexOf("/");
        int period = value.lastIndexOf(".");
        if ((period >= 0) && (period > slash))
            value = value.substring(0, period);
        if (value.startsWith("/"))
            return (value);
        else
            return ("/" + value);

    }

    /**
     * Return the form action converted into a server-relative URL.
     */
    public String getActionMappingURL(String action) {

        StringBuffer value = new StringBuffer(this.request.getContextPath());

        // Use our servlet mapping, if one is specified
        String servletMapping = getServletMapping();

        if (servletMapping != null) {
            String queryString = null;
            int question = action.indexOf("?");
            if (question >= 0)
                queryString = action.substring(question);
            String actionMapping = getActionMappingName(action);
            if (servletMapping.startsWith("*.")) {
                value.append(actionMapping);
                value.append(servletMapping.substring(1));
            } else if (servletMapping.endsWith("/*")) {
                value.append(servletMapping.substring(0, servletMapping.length() - 2));
                value.append(actionMapping);
            }
            if (queryString != null)
                value.append(queryString);
        }

        // Otherwise, assume extension mapping is in use and extension is
        // already included in the action property
        else {
            if (!action.startsWith("/"))
                value.append("/");
            value.append(action);
        }

        // Return the completed value
        return (value.toString());

    }

    /**
     * Return the url encoded to maintain the user session, if any.
     */
    public String getEncodeURL(String url) {

        if ((session != null) && (response != null)) {

            boolean redirect = false;
            if (forward != null)
                redirect = forward.getRedirect();

            if (redirect)
                return response.encodeRedirectURL(url);
            else
                return response.encodeURL(url);
        } else
            return (url);
    }

    // ------------------------------------------------ Presentation API

    /**
     * Renders the reference for a HTML <base> element
     */
    public String getOrigRef() {

        // HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();

        if (request == null)
            return null;

        StringBuffer result = new StringBuffer();
        result.append(request.getScheme());
        result.append("://");
        result.append(request.getServerName());
        if ("http".equals(request.getScheme()) && (80 == request.getServerPort())) {
            ;
        } else if ("https".equals(request.getScheme()) && (443 == request.getServerPort())) {
            ;
        } else {
            result.append(":");
            result.append(request.getServerPort());
        }
        result.append(request.getRequestURI());

        return result.toString();
    }

    /**
     * Renders the reference for a HTML <base> element.
     */
    public String getBaseRef() {

        if (request == null)
            return null;

        StringBuffer result = new StringBuffer();
        result.append(request.getScheme());
        result.append("://");
        result.append(request.getServerName());
        if ("http".equals(request.getScheme()) && (80 == request.getServerPort())) {
            ;
        } else if ("https".equals(request.getScheme()) && (443 == request.getServerPort())) {
            ;
        } else {
            result.append(":");
            result.append(request.getServerPort());
        }
        String path = null;
        if (forward == null)
            path = request.getRequestURI();
        else
            path = request.getContextPath() + forward.getPath();
        result.append(path);

        return result.toString();
    }

    /**
     * Return the path for the specified forward,
     * otherwise return <code>null</code>.
     *
     * @param name Name given to local or global forward.
     */
    public String getLink(String name) {

        ActionForward forward = getActionForward(name);
        if (forward == null)
            return null;

        StringBuffer path = new StringBuffer(this.request.getContextPath());
        path.append(forward.getPath());

        // :TODO: What about runtime parameters?

        return getEncodeURL(path.toString());

    }

    /**
     * Return the localized message for the specified key,
     * otherwise return <code>null</code>.
     *
     * @param key Message key
     */
    public String getMessage(String key) {

        MessageResources resources = getMessageResources();
        if (resources == null)
            return null;
        return resources.getMessage(getLocale(), key);

    }

    /**
     * Look up and return a message string, based on the specified parameters.
     *
     * @param key Message key to be looked up and returned
     * @param args Replacement parameters for this message
     */
    public String getMessage(String key, Object args[]) {

        MessageResources resources = getMessageResources();

        if (resources == null)
            return null;

        // Return the requested message
        if (args == null)
            return (resources.getMessage(getLocale(), key));
        else
            return (resources.getMessage(getLocale(), key, args));

    }

    /**
     * Return the URL for the specified ActionMapping,
     * otherwise return <code>null</code>.
     *
     * @param path Name given to local or global forward.
     */
    public String getAction(String path) {

        return getEncodeURL(getActionMappingURL(path));

    }

    /**
     * Return the number of error messages.
     */
    public int getErrorSize() {

        ActionErrors actionErrors = getActionErrors();

        if (actionErrors == null)
            return 0;

        return actionErrors.size();
    }

    /**
     * Return true if there are no errors queued
     */
    public boolean getErrorsEmpty() {

        ActionErrors actionErrors = getActionErrors();

        if (actionErrors == null)
            return false;

        return actionErrors.isEmpty();
    }

    /**
     * Return the error messages
     */
    public Iterator getErrors() {

        ActionErrors actionErrors = getActionErrors();

        if (actionErrors == null)
            return null;

        return actionErrors.get();
    }

    /**
     * Return an ActionError for a property
     *
     * @param property Property name
     */
    public Iterator getErrors(String property) {

        ActionErrors actionErrors = getActionErrors();

        if (actionErrors == null)
            return null;

        return actionErrors.get(property);
    }

    /**
     * Return the number of error messages.
     *
     * @param property Property name
     */
    public int getErrorSize(String property) {

        ActionErrors actionErrors = getActionErrors();

        if (actionErrors == null)
            return 0;

        return actionErrors.size(property);
    }

    /**
     * Returns the errors.header, any errors, and the errors.footer.
     *
     * @param property Property name
     */
    public String getErrorOutput(String property) {

        ActionErrors errors = getActionErrors();

        if ((errors == null) || (errors.isEmpty())) {
            return null;
        }

        // Check for presence of header and footer message keys
        boolean headerPresent = isMessage("errors.header");
        boolean footerPresent = isMessage("errors.footer");

        // Render the error messages appropriately
        StringBuffer results = new StringBuffer();
        String message = null;
        if (headerPresent)
            message = getMessage("errors.header");
        Iterator reports = null;

        if (property == null)
            reports = errors.get();
        else
            reports = errors.get(property);

        // Render header if this is a global tag or there is an error for this property
        boolean propertyMsgPresent = reports.hasNext();
        if ((message != null) && (property == null) || propertyMsgPresent) {
            results.append(message);
            results.append("\r\n");
        }

        while (reports.hasNext()) {
            ActionError report = (ActionError) reports.next();
            message = getMessage(report.getKey(), report.getValues());
            if (message != null) {
                results.append(message);
                results.append("\r\n");
            }
        }
        message = null;
        if (footerPresent)
            message = getMessage("errors.footer");

        if ((message != null) && (property == null) || propertyMsgPresent) {
            results.append(message);
            results.append("\r\n");
        }

        // return result
        return results.toString();

    }

    /**
     * Wrapper for getErrorMarkup(null)
     */
    public String getErrorOutput() {
        return getErrorOutput(null);
    }

    // --------------------------------------------- Presentation Wrappers

    /**
     * Wrapper for getLink(String)
     *
     * @param name Name given to local or global forward.
     */
    public String link(String name) {
        return getLink(name);
    }

    /**
     * Wrapper for getMessage(String)
     *
     * @param key Message key
     */
    public String message(String key) {
        return getMessage(key);
    }

    /**
     * Wrapper for getMessage(String,Object[])
     *
     * @param key Message key to be looked up and returned
     * @param args Replacement parameters for this message
     */
    public String message(String key, Object args[]) {
        return getMessage(key, args);
    }

    /**
     * Wrapper for getAction(String)
     *
     * @param path Name given to local or global forward.
     */
    public String action(String path) {
        return getAction(path);
    }

    /**
     * Alias for getErrorSize()
     */
    public int errorSize() {
        return getErrorSize();
    }

    /**
     * Wrapper for getErrorEmpty()
     */
    public boolean errorsEmpty() {
        return getErrorsEmpty();
    }

    /**
     * Wrapper for getErrors()
     */
    public Iterator errors() {
        return errors();
    }

    /**
     * Wrapper for getErrors(String)
     */
    public Iterator errors(String property) {
        return getErrors(property);
    }

    /**
     * Wrapper for getErrorSize(String)
     *
     * @param property Property name
     */
    public int errorSize(String property) {
        return getErrorSize(property);
    }

    /**
     * Wrapper for getErrorMarkup(String)
     */
    public String errorOutput(String property) {
        return getErrorOutput(property);
    }

    /**
     * Wrapper for getErrorMarkup()
     */
    public String errorOutput() {
        return getErrorOutput();
    }

    // ------------------------------------------------------- Constructors

    public ConfigHelper() {
        super();
    }

    public ConfigHelper(
        ServletContext application,
        HttpServletRequest request,
        HttpServletResponse response) {
        super();
        setResources(application, request, response);
    }

}
