/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/TagUtils.java,v 1.8 2003/07/26 18:28:03 dgraham Exp $
 * $Revision: 1.8 $
 * $Date: 2003/07/26 18:28:03 $
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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.Globals;
import org.apache.struts.taglib.html.Constants;

/**
 * Provides helper methods for JSP tags.
 *
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @author James Turner
 * @author David Graham
 * @author Rob Leland
 * @version $Revision: 1.8 $
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
        MessageResources.getMessageResources("org.apache.struts.util.LocalStrings");

    /**
     * Maps lowercase JSP scope names to their PageContext integer constant
     * values.
     */
    private static final Map scopes = new HashMap();

    /**
     * Initialize the scope names map.
     */
    static {
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
                map = (Map) TagUtils.getInstance().lookup(pageContext, name, property, scope);
            }
        } catch (ClassCastException e) {
            saveException(pageContext, e);
            throw new JspException(messages.getMessage("parameters.multi", name, property, scope));
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
                paramValue = TagUtils.getInstance().lookup(pageContext, paramName, paramProperty, paramScope);
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
                } else /* if (mapValue instanceof String[]) */ {
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
                    ActionErrors.GLOBAL_ERROR,
                    new ActionError((String) value));

            } else if (value instanceof String[]) {
                String keys[] = (String[]) value;
                for (int i = 0; i < keys.length; i++) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(keys[i]));
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
     * Return the ModuleConfig object if it exists, null if otherwise.
     * @param pageContext The page context.
     * @return the ModuleConfig object
     */
    public ModuleConfig getModuleConfig(PageContext pageContext) {
        return RequestUtils.getModuleConfig(
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
                e = new JspException(messages.getMessage("lookup.bean", name, scope));
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
            throw new JspException(messages.getMessage("lookup.access", property, name));

        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t == null) {
                t = e;
            }
            saveException(pageContext, t);
            throw new JspException(messages.getMessage("lookup.target", property, name));

        } catch (NoSuchMethodException e) {
            saveException(pageContext, e);
            throw new JspException(messages.getMessage("lookup.method", property, name));
        }

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

}
