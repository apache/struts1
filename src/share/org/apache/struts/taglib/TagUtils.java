/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/TagUtils.java,v 1.24 2003/08/23 00:12:40 dgraham Exp $
 * $Revision: 1.24 $
 * $Date: 2003/08/23 00:12:40 $
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

package org.apache.struts.taglib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.ModuleUtils;
import org.apache.struts.util.RequestUtils;

/**
 * Provides helper methods for JSP tags.
 *
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @author James Turner
 * @author David Graham
 * @author Rob Leland
 * @version $Revision: 1.24 $
 * @since Struts 1.2
 */
public class TagUtils {

    /**
     * The Singleton instance.
     */
    private static final TagUtils instance = new TagUtils();

    /**
     * Commons logging instance.
     */
    private static final Log log = LogFactory.getLog(TagUtils.class);

    /**
     * The message resources for this package.
     * TODO We need to move the relevant messages out of this properties file.
     */
    private static final MessageResources messages =
        MessageResources.getMessageResources("org.apache.struts.taglib.LocalStrings");

    /**
     * Java 1.4 encode method to use instead of deprecated 1.3 version.
     */
    private static Method encode = null;

    /**
     * Maps lowercase JSP scope names to their PageContext integer constant
     * values.
     */
    private static final Map scopes = new HashMap();

    /**
     * Initialize the scope names map and the encode variable with the 
     * Java 1.4 method if available.
     */
    static {
        
        try {
            // get version of encode method with two String args 
            Class[] args = new Class[] { String.class, String.class };
            encode = URLEncoder.class.getMethod("encode", args);
        } catch (NoSuchMethodException e) {
            log.debug("Could not find Java 1.4 encode method.  Using deprecated version.", e);
        }
        
        scopes.put("page", new Integer(PageContext.PAGE_SCOPE));
        scopes.put("request", new Integer(PageContext.REQUEST_SCOPE));
        scopes.put("session", new Integer(PageContext.SESSION_SCOPE));
        scopes.put("application", new Integer(PageContext.APPLICATION_SCOPE));
    }

    /**
     * Constructor for TagUtils.
     */
    protected TagUtils() {
        super();
    }

    /**
     * Returns the Singleton instance of TagUtils.
     */
    public static TagUtils getInstance() {
        return instance;
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
     * @return Map of query parameters
     * @exception JspException if we cannot look up the required beans
     * @exception JspException if a class cast exception occurs on a
     *  looked-up bean or property
     */
    public Map computeParameters(
        PageContext pageContext,
        String paramId,
        String paramName,
        String paramProperty,
        String paramScope,
        String name,
        String property,
        String scope,
        boolean transaction)
        throws JspException {

        // Short circuit if no parameters are specified
        if ((paramId == null) && (name == null) && !transaction) {
            return (null);
        }

        // Locate the Map containing our multi-value parameters map
        Map map = null;
        try {
            if (name != null) {
                map =
                    (Map) TagUtils.getInstance().lookup(
                        pageContext,
                        name,
                        property,
                        scope);
            }
        } catch (ClassCastException e) {
            saveException(pageContext, e);
            throw new JspException(
                messages.getMessage("parameters.multi", name, property, scope));
                
        } catch (JspException e) {
            saveException(pageContext, e);
            throw e;
        }

        // Create a Map to contain our results from the multi-value parameters
        Map results = null;
        if (map != null) {
            results = new HashMap(map);
        } else {
            results = new HashMap();
        }

        // Add the single-value parameter (if any)
        if ((paramId != null) && (paramName != null)) {

            Object paramValue = null;
            try {
                paramValue =
                    TagUtils.getInstance().lookup(
                        pageContext,
                        paramName,
                        paramProperty,
                        paramScope);
                        
            } catch (JspException e) {
                saveException(pageContext, e);
                throw e;
            }

            if (paramValue != null) {

                String paramString = null;
                if (paramValue instanceof String) {
                    paramString = (String) paramValue;
                } else {
                    paramString = paramValue.toString();
                }

                Object mapValue = results.get(paramId);
                if (mapValue == null) {
                    results.put(paramId, paramString);
                    
                } else if (mapValue instanceof String) {
                    String newValues[] = new String[2];
                    newValues[0] = (String) mapValue;
                    newValues[1] = paramString;
                    results.put(paramId, newValues);
                    
                } else {
                    String oldValues[] = (String[]) mapValue;
                    String newValues[] = new String[oldValues.length + 1];
                    System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
                    newValues[oldValues.length] = paramString;
                    results.put(paramId, newValues);
                }

            }

        }

        // Add our transaction control token (if requested)
        if (transaction) {
            HttpSession session = pageContext.getSession();
            String token = null;
            if (session != null) {
                token = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
            }
            
            if (token != null) {
                results.put(Constants.TOKEN_KEY, token);
            }
        }

        // Return the completed Map
        return (results);

    }
    /**
     * Compute a hyperlink URL based on the <code>forward</code>,
     * <code>href</code>, <code>action</code> or <code>page</code> parameter
     * that is not null.
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
     * @param action Logical action name for which to look up
     *  the context-relative URI (if specified)
     *
     * @param params Map of parameters to be dynamically included (if any)
     * @param anchor Anchor to be dynamically included (if any)
     *
     * @param redirect Is this URL for a <code>response.sendRedirect()</code>?
     * @return URL with session identifier
     * @exception java.net.MalformedURLException if a URL cannot be created
     *  for the specified parameters
     */
    public String computeURL(
        PageContext pageContext,
        String forward,
        String href,
        String page,
        String action,
        Map params,
        String anchor,
        boolean redirect)
        throws MalformedURLException {

        return computeURL(
            pageContext,
            forward,
            href,
            page,
            action,
            params,
            anchor,
            redirect,
            true);
    }

    /**
     * Compute a hyperlink URL based on the <code>forward</code>,
     * <code>href</code>, <code>action</code> or <code>page</code> parameter
     * that is not null.
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
     * @param action Logical action name for which to look up
     *  the context-relative URI (if specified)
     *
     * @param params Map of parameters to be dynamically included (if any)
     * @param anchor Anchor to be dynamically included (if any)
     *
     * @param redirect Is this URL for a <code>response.sendRedirect()</code>?
     * @param encodeSeparator This is only checked if redirect is set to false (never
     * encoded for a redirect).  If true, query string parameter separators are encoded
     * as &gt;amp;, else &amp; is used.
     * @return URL with session identifier
     * @exception java.net.MalformedURLException if a URL cannot be created
     *  for the specified parameters
     */
    public String computeURL(
        PageContext pageContext,
        String forward,
        String href,
        String page,
        String action,
        Map params,
        String anchor,
        boolean redirect,
        boolean encodeSeparator)
        throws MalformedURLException {

        // TODO All the computeURL() methods need refactoring!

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
        if (action != null) {
            n++;
        }
        if (n != 1) {
            throw new MalformedURLException(messages.getMessage("computeURL.specifier"));
        }

        // Look up the module configuration for this request
        ModuleConfig config = instance.getModuleConfig(pageContext);

        // Calculate the appropriate URL
        StringBuffer url = new StringBuffer();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        if (forward != null) {
            ForwardConfig fc = config.findForwardConfig(forward);
            if (fc == null) {
                throw new MalformedURLException(messages.getMessage("computeURL.forward", forward));
            }
            if (fc.getRedirect()) {
                redirect = true;
            }
            if (fc.getPath().startsWith("/")) {
                url.append(request.getContextPath());
                url.append(RequestUtils.forwardURL(request, fc));
            } else {
                url.append(fc.getPath());
            }
        } else if (href != null) {
            url.append(href);
        } else if (action != null) {
            url.append(instance.getActionMappingURL(action, pageContext));

        } else /* if (page != null) */ {
            url.append(request.getContextPath());
            url.append(this.pageURL(request, page));
        }

        // Add anchor if requested (replacing any existing anchor)
        if (anchor != null) {
            String temp = url.toString();
            int hash = temp.indexOf('#');
            if (hash >= 0) {
                url.setLength(hash);
            }
            url.append('#');
            url.append(this.encodeURL(anchor));
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
            String separator = null;
            if (redirect) {
                separator = "&";
            } else if (encodeSeparator) {
                separator = "&amp;";
            } else {
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
                    url.append(this.encodeURL(key));
                    url.append('='); // Interpret null as "no value"
                } else if (value instanceof String) {
                    if (!question) {
                        url.append('?');
                        question = true;
                    } else {
                        url.append(separator);
                    }
                    url.append(this.encodeURL(key));
                    url.append('=');
                    url.append(this.encodeURL((String) value));
                } else if (value instanceof String[]) {
                    String values[] = (String[]) value;
                    for (int i = 0; i < values.length; i++) {
                        if (!question) {
                            url.append('?');
                            question = true;
                        } else {
                            url.append(separator);
                        }
                        url.append(this.encodeURL(key));
                        url.append('=');
                        url.append(this.encodeURL(values[i]));
                    }
                } else /* Convert other objects to a string */ {
                    if (!question) {
                        url.append('?');
                        question = true;
                    } else {
                        url.append(separator);
                    }
                    url.append(this.encodeURL(key));
                    url.append('=');
                    url.append(this.encodeURL(value.toString()));
                }
            }

            // Re-add the saved anchor (if any)
            if (anchor != null) {
                url.append('#');
                url.append(this.encodeURL(anchor));
            }

        }

        // Perform URL rewriting to include our session ID (if any)
        if (pageContext.getSession() != null) {
            HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
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
     * Use the new URLEncoder.encode() method from Java 1.4 if available, else
     * use the old deprecated version.  This method uses reflection to find the 
     * appropriate method; if the reflection operations throw exceptions, this 
     * will return the url encoded with the old URLEncoder.encode() method.
     * @return String The encoded url.
     */
    public String encodeURL(String url) {
        try {

            // encode url with new 1.4 method and UTF-8 encoding
            if (encode != null) {
                return (String) encode.invoke(null, new Object[] { url, "UTF-8" });
            }

        } catch (IllegalAccessException e) {
            log.debug("Could not find Java 1.4 encode method.  Using deprecated version.", e);
        } catch (InvocationTargetException e) {
            log.debug("Could not find Java 1.4 encode method. Using deprecated version.", e);
        }

        return URLEncoder.encode(url);
    }
    
    /**
     * Filter the specified string for characters that are senstive to
     * HTML interpreters, returning the string with these characters replaced
     * by the corresponding character entities.
     *
     * @param value The string to be filtered and returned
     */
    public String filter(String value) {

        if (value == null) {
            return (null);
        }

        char content[] = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
            case '<':
                result.append("&lt;");
                break;
            case '>':
                result.append("&gt;");
                break;
            case '&':
                result.append("&amp;");
                break;
            case '"':
                result.append("&quot;");
                break;
            case '\'':
                result.append("&#39;");
                break;
            default:
                result.append(content[i]);
            }
        }
        
        return result.toString();
    }

    /**
     * Retrieves the value from request scope and if it isn't already an
     * <code>ErrorMessages</code> some classes are converted to one.
     *
     * @param pageContext The PageContext for the current page
     * @param paramName Key for parameter value
     * @return ActionErrors from request scope
     * @exception JspException
     */
    public ActionErrors getActionErrors(PageContext pageContext, String paramName)
        throws JspException {

        ActionErrors errors = new ActionErrors();

        Object value = pageContext.findAttribute(paramName);

        try {
            if (value == null) {
                ;

            } else if (value instanceof String) {
                errors.add(
                    ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage((String) value));

            } else if (value instanceof String[]) {
                String keys[] = (String[]) value;
                for (int i = 0; i < keys.length; i++) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(keys[i]));
                }

            } else if (value instanceof ActionErrors) {
                errors = (ActionErrors) value;

            } else {
                throw new JspException(
                    messages.getMessage(
                        "actionErrors.errors",
                        value.getClass().getName()));
            }

        } catch (JspException e) {
            throw e;
            
        } catch (Exception e) {
            log.debug(e, e);
        }

        return errors;
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
        if (question >= 0) {
            value = value.substring(0, question);
        }
        
        int slash = value.lastIndexOf("/");
        int period = value.lastIndexOf(".");
        if ((period >= 0) && (period > slash)) {
            value = value.substring(0, period);
        }
        
        return value.startsWith("/") ? value : ("/" + value);
    }

    /**
     * Return the form action converted into a server-relative URL.
     */
    public String getActionMappingURL(String action, PageContext pageContext) {

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuffer value = new StringBuffer(request.getContextPath());
        ModuleConfig config = ModuleUtils.getInstance().getModuleConfig(request);
        
        if (config != null) {
            value.append(config.getPrefix());
        }

        // Use our servlet mapping, if one is specified
        String servletMapping =
            (String) pageContext.getAttribute(
                Globals.SERVLET_KEY,
                PageContext.APPLICATION_SCOPE);

        if (servletMapping != null) {
            
            String queryString = null;
            int question = action.indexOf("?");
            if (question >= 0) {
                queryString = action.substring(question);
            }

            String actionMapping = getActionMappingName(action);
            if (servletMapping.startsWith("*.")) {
                value.append(actionMapping);
                value.append(servletMapping.substring(1));

            } else if (servletMapping.endsWith("/*")) {
                value.append(
                    servletMapping.substring(0, servletMapping.length() - 2));
                value.append(actionMapping);

            } else if (servletMapping.equals("/")) {
                value.append(actionMapping);
            }
            if (queryString != null) {
                value.append(queryString);
            }
        }

        // Otherwise, assume extension mapping is in use and extension is
        // already included in the action property
        else {
            if (!action.startsWith("/")) {
                value.append("/");
            }
            value.append(action);
        }

        return value.toString();
    }
    
    /**
     * Retrieves the value from request scope and if it isn't already an 
     * <code>ActionMessages</code>, some classes are converted to one.
     *
     * @param pageContext The PageContext for the current page
     * @param paramName Key for parameter value
     * @return ActionErrors in page context.
     * @throws JspException
     */
    public ActionMessages getActionMessages(PageContext pageContext, String paramName)
        throws JspException {

        ActionMessages am = new ActionMessages();

        Object value = pageContext.findAttribute(paramName);

        try {
            if (value == null) {
                ;
            } else if (value instanceof String) {
                am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage((String) value));
                
            } else if (value instanceof String[]) {
                String keys[] = (String[]) value;
                for (int i = 0; i < keys.length; i++){
                    am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(keys[i]));
                }
                    
            } else if (value instanceof ActionMessages) {
                am = (ActionMessages) value;
                
            } else {
                throw new JspException(
                    messages.getMessage("actionMessages.errors", value.getClass().getName()));
            }
            
        } catch (JspException e) {
            throw e;
            
        } catch (Exception e) {
            ;
        }

        return am;
    }

    /**
     * Return the ModuleConfig object if it exists, null if otherwise.
     * @param pageContext The page context.
     * @return the ModuleConfig object
     */
    public ModuleConfig getModuleConfig(PageContext pageContext) {
        return ModuleUtils.getInstance().getModuleConfig(
            (HttpServletRequest) pageContext.getRequest(),
            pageContext.getServletContext());
    }

    /**
     * Converts the scope name into its corresponding PageContext constant value.
     * @param scopeName Can be "page", "request", "session", or "application" in any
     * case.
     * @return The constant representing the scope (ie. PageContext.REQUEST_SCOPE).
     * @throws JspException if the scopeName is not a valid name.
     */
    public int getScope(String scopeName) throws JspException {
        Integer scope = (Integer) scopes.get(scopeName.toLowerCase());

        if (scope == null) {
            throw new JspException(messages.getMessage("lookup.scope", scope));
        }

        return scope.intValue();
    }

    /**
     * Look up and return current user locale, based on the specified parameters.
     *
     * @param pageContext The PageContext associated with this request
     * @param locale Name of the session attribute for our user's Locale.  If this is
     * <code>null</code>, the default locale key is used for the lookup.
     * @return current user locale
     */
    public Locale getUserLocale(PageContext pageContext, String locale) {
        return RequestUtils.getUserLocale(
            (HttpServletRequest) pageContext.getRequest(),
            locale);
    }
    
    /**
     * Returns true if the custom tags are in XHTML mode.
     */
    public boolean isXhtml(PageContext pageContext) {
        String xhtml =
            (String) pageContext.getAttribute(
                Globals.XHTML_KEY,
                PageContext.PAGE_SCOPE);

        return "true".equalsIgnoreCase(xhtml);
    }

    /**
     * Locate and return the specified bean, from an optionally specified
     * scope, in the specified page context.  If no such bean is found,
     * return <code>null</code> instead.  If an exception is thrown, it will
     * have already been saved via a call to <code>saveException()</code>.
     *
     * @param pageContext Page context to be searched
     * @param name Name of the bean to be retrieved
     * @param scopeName Scope to be searched (page, request, session, application)
     *  or <code>null</code> to use <code>findAttribute()</code> instead
     * @return JavaBean in the specified page context
     * @exception JspException if an invalid scope name
     *  is requested
     */
    public Object lookup(PageContext pageContext, String name, String scopeName)
        throws JspException {

        if (scopeName == null) {
            return pageContext.findAttribute(name);
        }

        try {
            return pageContext.getAttribute(name, instance.getScope(scopeName));

        } catch (JspException e) {
            saveException(pageContext, e);
            throw e;
        }

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
     * @return property of specified JavaBean
     *
     * @exception JspException if an invalid scope name
     *  is requested
     * @exception JspException if the specified bean is not found
     * @exception JspException if accessing this property causes an
     *  IllegalAccessException, IllegalArgumentException,
     *  InvocationTargetException, or NoSuchMethodException
     */
    public Object lookup(
        PageContext pageContext,
        String name,
        String property,
        String scope)
        throws JspException {

        // Look up the requested bean, and return if requested
        Object bean = lookup(pageContext, name, scope);
        if (bean == null) {
            JspException e = null;
            if (scope == null) {
                e = new JspException(messages.getMessage("lookup.bean.any", name));
            } else {
                e =
                    new JspException(
                        messages.getMessage("lookup.bean", name, scope));
            }
            saveException(pageContext, e);
            throw e;
        }

        if (property == null) {
            return bean;
        }

        // Locate and return the specified property
        try {
            return PropertyUtils.getProperty(bean, property);

        } catch (IllegalAccessException e) {
            saveException(pageContext, e);
            throw new JspException(
                messages.getMessage("lookup.access", property, name));

        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t == null) {
                t = e;
            }
            saveException(pageContext, t);
            throw new JspException(
                messages.getMessage("lookup.target", property, name));

        } catch (NoSuchMethodException e) {
            saveException(pageContext, e);
            throw new JspException(
                messages.getMessage("lookup.method", property, name));
        }

    }
    
    /**
     * Look up and return a message string, based on the specified parameters.
     *
     * @param pageContext The PageContext associated with this request
     * @param bundle Name of the servlet context attribute for our
     *  message resources bundle
     * @param locale Name of the session attribute for our user's Locale
     * @param key Message key to be looked up and returned
     * @return message string
     *
     * @exception JspException if a lookup error occurs (will have been
     *  saved in the request already)
     */
    public String message(
        PageContext pageContext,
        String bundle,
        String locale,
        String key)
        throws JspException {

        return message(pageContext, bundle, locale, key, null);

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
     * @return message string
     * @exception JspException if a lookup error occurs (will have been
     *  saved in the request already)
     */
    public String message(
        PageContext pageContext,
        String bundle,
        String locale,
        String key,
        Object args[])
        throws JspException {

        MessageResources resources =
            retrieveMessageResources(pageContext, bundle, false);

        Locale userLocale = getUserLocale(pageContext, locale);
        
        if (args == null) {
            return resources.getMessage(userLocale, key);
        } else {
            return resources.getMessage(userLocale, key, args);
        }

    }
    
    /**
     * <p>Return the context-relative URL that corresponds to the specified
     * <code>page</code> attribute value, calculated based on the
     * <code>pagePattern</code> property of the current module's
     * {@link ModuleConfig}.</p>
     *
     * @param request The servlet request we are processing
     * @param page The module-relative URL to be substituted in
     * to the <code>pagePattern</code> pattern for the current module
     * (<strong>MUST</strong> start with a slash)
     * @return context-relative URL
     */
    public String pageURL(HttpServletRequest request, String page) {

        StringBuffer sb = new StringBuffer();
        ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(request);
        String pagePattern = moduleConfig.getControllerConfig().getPagePattern();
        
        if (pagePattern == null) {
            sb.append(moduleConfig.getPrefix());
            sb.append(page);
            
        } else {
            boolean dollar = false;
            for (int i = 0; i < pagePattern.length(); i++) {
                char ch = pagePattern.charAt(i);
                if (dollar) {
                    switch (ch) {
                        case 'M' :
                            sb.append(moduleConfig.getPrefix());
                            break;
                        case 'P' :
                            sb.append(page);
                            break;
                        case '$' :
                            sb.append('$');
                            break;
                        default :
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
        
        return sb.toString();
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
     * @return true if a message string for message key exists
     * @exception JspException if a lookup error occurs (will have been
     *  saved in the request already)
     */
    public boolean present(
        PageContext pageContext,
        String bundle,
        String locale,
        String key)
        throws JspException {

        MessageResources resources =
            retrieveMessageResources(pageContext, bundle, true);

        Locale userLocale = getUserLocale(pageContext, locale);

        return resources.isPresent(userLocale, key);
    }
    
    /**
     * Returns the appropriate MessageResources object for the current module and 
     * the given bundle.
     * 
     * @param pageContext Search the context's scopes for the resources.
     * @param bundle The bundle name to look for.  If this is <code>null</code>, the 
     * default bundle name is used.
     * @return MessageResources The bundle's resources stored in some scope. 
     * @throws JspException if the MessageResources object could not be found.
     */
    private MessageResources retrieveMessageResources(
        PageContext pageContext,
        String bundle,
        boolean checkPageScope)
        throws JspException {
            
        MessageResources resources = null;

        if (bundle == null) {
            bundle = Globals.MESSAGES_KEY;
        }

        if (checkPageScope) {
            resources =
                (MessageResources) pageContext.getAttribute(
                    bundle,
                    PageContext.PAGE_SCOPE);
        }

        if (resources == null) {
            resources =
                (MessageResources) pageContext.getAttribute(
                    bundle,
                    PageContext.REQUEST_SCOPE);
        }

        if (resources == null) {
            ModuleConfig config = getModuleConfig(pageContext);
            resources =
                (MessageResources) pageContext.getAttribute(
                    bundle + config.getPrefix(),
                    PageContext.APPLICATION_SCOPE);
        }

        if (resources == null) {
            JspException e =
                new JspException(messages.getMessage("message.bundle", bundle));
            saveException(pageContext, e);
            throw e;
        }

        return resources;
    }

    /**
     * Save the specified exception as a request attribute for later use.
     *
     * @param pageContext The PageContext for the current page
     * @param exception The exception to be saved
     */
    public void saveException(PageContext pageContext, Throwable exception) {

        pageContext.setAttribute(
            Globals.EXCEPTION_KEY,
            exception,
            PageContext.REQUEST_SCOPE);

    }
    
    /**
     * Write the specified text as the response to the writer associated with
     * this page.  <strong>WARNING</strong> - If you are writing body content
     * from the <code>doAfterBody()</code> method of a custom tag class that
     * implements <code>BodyTag</code>, you should be calling
     * <code>writePrevious()</code> instead.
     *
     * @param pageContext The PageContext object for this page
     * @param text The text to be written
     *
     * @exception JspException if an input/output error occurs (already saved)
     */
    public void write(PageContext pageContext, String text)
        throws JspException {

        JspWriter writer = pageContext.getOut();
        
        try {
            writer.print(text);
            
        } catch (IOException e) {
            TagUtils.getInstance().saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("write.io", e.toString()));
        }

    }


    /**
     * Write the specified text as the response to the writer associated with
     * the body content for the tag within which we are currently nested.
     *
     * @param pageContext The PageContext object for this page
     * @param text The text to be written
     *
     * @exception JspException if an input/output error occurs (already saved)
     */
    public void writePrevious(PageContext pageContext, String text)
        throws JspException {

        JspWriter writer = pageContext.getOut();
        if (writer instanceof BodyContent){
            writer = ((BodyContent) writer).getEnclosingWriter();
        }
        
        try {
            writer.print(text);
            
        } catch (IOException e) {
            TagUtils.getInstance().saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("write.io", e.toString()));
        }

    }

}
