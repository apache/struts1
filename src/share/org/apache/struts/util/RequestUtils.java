/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/RequestUtils.java,v 1.3 2001/01/08 00:17:43 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2001/01/08 00:17:43 $
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


package org.apache.struts.util;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;


/**
 * General purpose utility methods related to processing a servlet request
 * in the Struts controller framework.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2001/01/08 00:17:43 $
 */

public class RequestUtils {


    // --------------------------------------------------------- Public Methods


    /**
     * Create and return an absolute URL for the specified context-relative
     * path, based on the server and context information in the specified
     * request.  If no valid URL can be created, returns <code>null</code>.
     *
     * @param request The servlet request we are processing
     * @param path The context-relative path (must start with '/')
     */
    public static String absoluteURL(HttpServletRequest request, String path) {

        try {
            URL url = new URL(request.getScheme(),
                              request.getServerName(),
                              request.getServerPort(),
                              request.getContextPath() + path);
            return (url.toString());
        } catch (MalformedURLException e) {
            return (null);
        }

    }


    /**
     * Locate and return the specified bean, from an optionally specified
     * scope, in the specified page context.  If no such bean is found,
     * return <code>null</code> instead.
     *
     * @param pageContext Page context to be searched
     * @param name Name of the bean to be retrieved
     * @param scope Scope to be searched (page, request, session, application)
     *  or <code>null</code> to use <code>findAttribute()</code> instead
     *
     * @exception IllegalArgumentException if an invalid scope name
     *  is requested
     */
    public static Object lookup(PageContext pageContext, String name,
    String scope) {

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
        else
            throw new IllegalArgumentException(scope);

        return (bean);

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
        //Hashtable for multipart values
        Hashtable multipartElements = null;

        boolean isMultipart = false;
        String contentType = request.getContentType();
        if ((contentType != null) &&
            (contentType.startsWith("multipart/form-data"))) {
            isMultipart = true;
            //initialize a MultipartRequestHandler
            MultipartRequestHandler multipart = null;

            //get an instance of ActionServlet
            ActionServlet servlet;

            if (bean instanceof ActionForm) {
                servlet = ((ActionForm) bean).getServlet();
            } else {
                throw new ServletException("bean that's supposed to be " +
                                           "populated from a multipart request is not of type " +
                                           "\"org.apache.struts.action.ActionForm\", but type " +
                                           "\"" + bean.getClass().getName() + "\"");
            }
            String multipartClass = (String)
                request.getAttribute(Action.MULTIPART_KEY);
            request.removeAttribute(Action.MULTIPART_KEY);

            if (multipartClass != null) {
                //try to initialize the mapping specific request handler
                try {
                    multipart = (MultipartRequestHandler) Class.forName(multipartClass).newInstance();
                }
                catch (ClassNotFoundException cnfe) {
                    servlet.log("MultipartRequestHandler class \"" +
                    multipartClass + "\" in mapping class not found, " +
                    "defaulting to global multipart class");
                }
                catch (InstantiationException ie) {
                    servlet.log("InstantiaionException when instantiating " +
                    "MultipartRequestHandler \"" + multipartClass + "\", " +
                    "defaulting to global multipart class, exception: " +
                    ie.getMessage());
                }
                catch (IllegalAccessException iae) {
                    servlet.log("IllegalAccessException when instantiating " +
                    "MultipartRequestHandler \"" + multipartClass + "\", " +
                    "defaulting to global multipart class, exception: " +
                    iae.getMessage());
                }
            }

            if (multipart == null) {
                //try to initialize the global multipart class
                try {
                    multipart = (MultipartRequestHandler) Class.forName(servlet.getMultipartClass()).newInstance();
                }
                catch (ClassNotFoundException cnfe) {
                    throw new ServletException("Cannot find multipart class \"" +
                    servlet.getMultipartClass() + "\"" +
                    ", exception: " + cnfe.getMessage());
                }
                catch (InstantiationException ie) {
                    throw new ServletException("InstantiaionException when instantiating " +
                    "multipart class \"" + servlet.getMultipartClass() +
                    "\", exception: " + ie.getMessage());
                }
                catch (IllegalAccessException iae) {
                    throw new ServletException("IllegalAccessException when instantiating " +
                    "multipart class \"" + servlet.getMultipartClass() +
                    "\", exception: " + iae.getMessage());
                }
            }


            //set the multipart request handler for our ActionForm
            //if the bean isn't an ActionForm, an exception would have been
            //thrown earlier, so it's safe to assume that our bean is
            //in fact an ActionForm
            ((ActionForm) bean).setMultipartRequestHandler(multipart);

            //set servlet and mapping info
            multipart.setServlet(servlet);
            multipart.setMapping((ActionMapping)
                                 request.getAttribute(Action.MAPPING_KEY));
            request.removeAttribute(Action.MAPPING_KEY);

            //initialize request class handler
            multipart.handleRequest(request);

            //retrive form values and put into properties
            multipartElements = multipart.getAllElements();
            names = multipartElements.keys();
        }

        if (!isMultipart) {
            names = request.getParameterNames();
        }


        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String stripped = name;
            int subscript = stripped.lastIndexOf("[");
            if (subscript >= 0)  // Remove subscript expression
                stripped = stripped.substring(0, subscript);
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


}
