/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/tiles/util/TagUtils.java,v 1.6 2003/05/10 17:37:44 dgraham Exp $
 * $Revision: 1.6 $
 * $Date: 2003/05/10 17:37:44 $
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

package org.apache.struts.taglib.tiles.util;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.apache.struts.taglib.tiles.ComponentConstants;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.FactoryNotFoundException;
import org.apache.struts.tiles.NoSuchDefinitionException;
import org.apache.struts.tiles.TilesUtil;
import org.apache.struts.util.RequestUtils;

/**
 * Collection of utilities.
 * This class also serves as an interface between Components and Struts. If
 * you want to rip away Struts, simply reimplement some methods in this class.
 * You can copy them from Struts.
 * 
 * @author Cedric Dumoulin
 * @author David Graham
 */
public class TagUtils {

    /** Debug flag */
    public static final boolean debug = true;
    
    /**
    * Get scope value from string value
    * @param scopeName Scope as a String.
    * @param defaultValue Returned default value, if not found.
    * @return Scope as an <code>int</code>, or <code>defaultValue</code> if scope is <code>null</code>.
    * @throws JspException Scope name is not recognized as a valid scope.
    */
    public static int getScope(String scopeName, int defaultValue) throws JspException {
        if (scopeName == null) {
            return defaultValue;
        }
        
        if (scopeName.equalsIgnoreCase("component")) {
            return ComponentConstants.COMPONENT_SCOPE;
        } else if (scopeName.equalsIgnoreCase("template")) {
            return ComponentConstants.COMPONENT_SCOPE;
        } else if (scopeName.equalsIgnoreCase("tile")) {
            return ComponentConstants.COMPONENT_SCOPE;
        } else {
            return RequestUtils.getScope(scopeName);
        }
    }

    /**
     * Return the value of the specified property of the specified bean,
     * no matter which property reference format is used, with no
     * type conversions.
     *
     * @param bean Bean whose property is to be extracted.
     * @param name Possibly indexed and/or nested name of the property
     *  to be extracted.
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  propety cannot be found
     */
	public static Object getProperty(Object bean, String name)
		throws
			IllegalAccessException,
			InvocationTargetException,
			NoSuchMethodException {

		return PropertyUtils.getProperty(bean, name);
	}

    /**
     * Retrieve bean from page context, using specified scope.
     * If scope is not set, use <code>findAttribute()</code>.
     *
     * @param beanName Name of bean to retrieve.
     * @param scopeName Scope or <code>null</code>. If <code>null</code>, bean is searched using
     *  findAttribute().
     * @param pageContext Current pageContext.
     * @return Requested bean or <code>null</code> if not found.
     * @throws JspException Scope name is not recognized as a valid scope.
     */
    public static Object retrieveBean(String beanName, String scopeName, PageContext pageContext)
        throws JspException {
        
        if (scopeName == null) {
            return findAttribute(beanName, pageContext);
        }

        // Default value doesn't matter because we have already check it
        int scope = getScope(scopeName, PageContext.PAGE_SCOPE);
        
        //return pageContext.getAttribute( beanName, scope );
        return getAttribute(beanName, scope, pageContext);
    }

    /**
     * Search attribute in different contexts.
     * First, check in component context, then use pageContext.findAttribute().
     * @param beanName Name of bean to retrieve.
     * @param pageContext Current pageContext.
     * @return Requested bean or <code>null</code> if not found.
     */
    public static Object findAttribute(String beanName, PageContext pageContext) {
        ComponentContext compContext = ComponentContext.getContext(pageContext.getRequest());
        
        if (compContext != null) {
            Object attribute = compContext.findAttribute(beanName, pageContext);
            if (attribute != null) {
                return attribute;
            }
        }

        // Search in pageContext scopes
        return pageContext.findAttribute(beanName);
    }

    /**
     * Get object from requested context. Return <code>null</code> if not found.
     * Context can be "component" or normal JSP contexts.
     * @param beanName Name of bean to retrieve.
     * @param scope Scope from which bean must be retrieved.
     * @param pageContext Current pageContext.
     * @return Requested bean or <code>null</code> if not found.
     */
    public static Object getAttribute(String beanName, int scope, PageContext pageContext) {
        if (scope == ComponentConstants.COMPONENT_SCOPE) {
            ComponentContext compContext = ComponentContext.getContext(pageContext.getRequest());
            return compContext.getAttribute(beanName);
        }
        return pageContext.getAttribute(beanName, scope);
    }

    /**
     * Locate and return the specified property of the specified bean, from
     * an optionally specified scope, in the specified page context.
     *
     * @param pageContext Page context to be searched.
     * @param beanName Name of the bean to be retrieved.
     * @param beanProperty Name of the property to be retrieved, or
     *  <code>null</code> to retrieve the bean itself.
     * @param beanScope Scope to be searched (page, request, session, application)
     *  or <code>null</code> to use <code>findAttribute()</code> instead.
     *
     * @exception JspException Scope name is not recognized as a valid scope
     * @exception JspException if the specified bean is not found
     * @exception JspException if accessing this property causes an
     *  IllegalAccessException, IllegalArgumentException,
     *  InvocationTargetException, or NoSuchMethodException
     */
    public static Object getRealValueFromBean(
        String beanName,
        String beanProperty,
        String beanScope,
        PageContext pageContext)
        throws JspException {
            
        try {
            Object realValue;
            Object bean = retrieveBean(beanName, beanScope, pageContext);
            if (bean != null && beanProperty != null) {
                realValue = getProperty(bean, beanProperty);
            } else {
                realValue = bean; // value can be null
            }
            return realValue;
            
        } catch (NoSuchMethodException ex) {
            throw new JspException(
                "Error - component.PutAttributeTag : Error while retrieving value from bean '"
                    + beanName
                    + "' with property '"
                    + beanProperty
                    + "' in scope '"
                    + beanScope
                    + "'. (exception : "
                    + ex.getMessage());
        } catch (InvocationTargetException ex) {
            throw new JspException(
                "Error - component.PutAttributeTag : Error while retrieving value from bean '"
                    + beanName
                    + "' with property '"
                    + beanProperty
                    + "' in scope '"
                    + beanScope
                    + "'. (exception : "
                    + ex.getMessage());
        } catch (IllegalAccessException ex) {
            throw new JspException(
                "Error - component.PutAttributeTag : Error while retrieving value from bean '"
                    + beanName
                    + "' with property '"
                    + beanProperty
                    + "' in scope '"
                    + beanScope
                    + "'. (exception : "
                    + ex.getMessage());
        }
    }

    /**
     * Store bean in requested context.
     * If scope is <code>null</code>, save it in REQUEST_SCOPE context.
     *
     * @param pageContext Current pageContext.
     * @param name Name of the bean.
     * @param scope Scope under which bean is saved (page, request, session, application)
     *  or <code>null</code> to store in <code>request()</code> instead.
     * @param value Bean value to store.
     *
     * @exception JspException Scope name is not recognized as a valid scope
     */
    public static void setAttribute(
        PageContext pageContext,
        String name,
        Object value,
        String scope)
        throws JspException {
            
        if (scope == null)
            pageContext.setAttribute(name, value, PageContext.REQUEST_SCOPE);
        else if (scope.equalsIgnoreCase("page"))
            pageContext.setAttribute(name, value, PageContext.PAGE_SCOPE);
        else if (scope.equalsIgnoreCase("request"))
            pageContext.setAttribute(name, value, PageContext.REQUEST_SCOPE);
        else if (scope.equalsIgnoreCase("session"))
            pageContext.setAttribute(name, value, PageContext.SESSION_SCOPE);
        else if (scope.equalsIgnoreCase("application"))
            pageContext.setAttribute(name, value, PageContext.APPLICATION_SCOPE);
        else {
            throw new JspException("Error - bad scope name '" + scope + "'");
        }
    }

    /**
     * Store bean in REQUEST_SCOPE context.
     *
     * @param pageContext Current pageContext.
     * @param name Name of the bean.
     * @param beanValue Bean value to store.
     *
     * @exception JspException Scope name is not recognized as a valid scope
     */
    public static void setAttribute(PageContext pageContext, String name, Object beanValue)
        throws JspException {
        pageContext.setAttribute(name, beanValue, PageContext.REQUEST_SCOPE);
    }

    /**
     * Save the specified exception as a request attribute for later use.
     *
     * @param pageContext The PageContext for the current page.
     * @param exception The exception to be saved.
     */
    public static void saveException(PageContext pageContext, Throwable exception) {
        pageContext.setAttribute(Globals.EXCEPTION_KEY, exception, PageContext.REQUEST_SCOPE);
    }

    /**
     * Get component definition by its name.
     * @param name Definition name.
     * @param pageContext The PageContext for the current page.
     * @throws JspException -
     */
    public static ComponentDefinition getComponentDefinition(String name, PageContext pageContext)
        throws JspException {
            
        try {
            return TilesUtil.getDefinition(
                name,
                pageContext.getRequest(),
                pageContext.getServletContext());
                
        } catch (NoSuchDefinitionException ex) {
            throw new JspException(
                "Error : Can't get component definition for '"
                    + name
                    + "'. Check if this name exist in component definitions.");
        } catch (FactoryNotFoundException ex) { // factory not found.
            throw new JspException(ex.getMessage());
            
        } catch (DefinitionsFactoryException ex) {
            if (debug)
                ex.printStackTrace();
            // Save exception to be able to show it later
            saveException(pageContext, ex);
            throw new JspException(ex.getMessage());
        }
    }

}
