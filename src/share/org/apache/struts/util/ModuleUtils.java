/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/ModuleUtils.java,v 1.5 2004/01/10 21:03:36 dgraham Exp $
 * $Revision: 1.5 $
 * $Date: 2004/01/10 21:03:36 $
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

package org.apache.struts.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;

/**
 * General purpose utility methods related to module processing.
 * 
 * @version $Revision: 1.5 $
 * @since Struts 1.2
 */
public class ModuleUtils {

    /**
     * The Singleton instance.
     */
    private static final ModuleUtils instance = new ModuleUtils();

    /**
     * Commons logging instance.
     */
    private static final Log log = LogFactory.getLog(ModuleUtils.class);

    /**
     * The message resources for this package.
     */
    private static final MessageResources messages =
        MessageResources.getMessageResources("org.apache.struts.util.LocalStrings");

    /**
     * Returns the Singleton instance of TagUtils.
     */
    public static ModuleUtils getInstance() {
        return instance;
    }

    /**
     * Constructor for ModuleUtils.
     */
    protected ModuleUtils() {
        super();
    }
    
    /**
     * Return the current ModuleConfig object stored in request, if it exists,
     * null otherwise.
     * This method can be used by plugin to retrieve the current module config
     * object. If no moduleConfig is found, this means that the request haven't
     * hit the server throught the struts servlet. The appropriate module config
     * can be set and found with
     * <code>{@link RequestUtils#selectModule(HttpServletRequest, ServletContext)} </code>.
     * @param request The servlet request we are processing
     * @return the ModuleConfig object from request, or null if none is set in
     * the request.
     */
    public ModuleConfig getModuleConfig(HttpServletRequest request) {
        return (ModuleConfig) request.getAttribute(Globals.MODULE_KEY);
    }

    /**
     * Return the ModuleConfig object is it exists, null otherwise.
     * @param request The servlet request we are processing
     * @param context The ServletContext for this web application
     * @return the ModuleConfig object
     */
    public ModuleConfig getModuleConfig(
        HttpServletRequest request,
        ServletContext context) {

        ModuleConfig moduleConfig = this.getModuleConfig(request);

        if (moduleConfig == null) {
            moduleConfig = (ModuleConfig) context.getAttribute(Globals.MODULE_KEY);
            request.setAttribute(Globals.MODULE_KEY, moduleConfig);
        }

        return moduleConfig;
    }

    /**
     * Get the module name to which the specified request belong.
     * @param request The servlet request we are processing
     * @param context The ServletContext for this web application
     * @return The module prefix or ""
     */
    public String getModuleName(
        HttpServletRequest request,
        ServletContext context) {

        // Acquire the path used to compute the module
        String matchPath =
            (String) request.getAttribute(RequestProcessor.INCLUDE_SERVLET_PATH);

        if (matchPath == null) {
            matchPath = request.getServletPath();
        }

        return this.getModuleName(matchPath, context);
    }

    /**
     * Get the module name to which the specified uri belong.
     * @param matchPath The uri from which we want the module name.
     * @param context The ServletContext for this web application
     * @return The module prefix or ""
     */
    public String getModuleName(String matchPath, ServletContext context) {
        if (log.isDebugEnabled()) {
            log.debug("Get module name for path " + matchPath);
        }

        String prefix = ""; // Initialize prefix before we try lookup
        String prefixes[] = getModulePrefixes(context);
        // Get all other possible prefixes
        int lastSlash = 0; // Initialize before loop

        while (prefix.equals("")
            && ((lastSlash = matchPath.lastIndexOf("/")) > 0)) {

            // We may be in a non-default module.  Try to get it's prefix.
            matchPath = matchPath.substring(0, lastSlash);

            // Match against the list of module prefixes
            for (int i = 0; i < prefixes.length; i++) {
                if (matchPath.equals(prefixes[i])) {
                    prefix = prefixes[i];
                    break;
                }
            }
        }

        if (log.isDebugEnabled()) {
            log.debug(
                "Module name found: " + (prefix.equals("") ? "default" : prefix));
        }

        return prefix;
    }

    /**
     * Return the list of module prefixes that are defined for
     * this web application.  <strong>NOTE</strong> -
     * the "" prefix for the default module is not included in this list.
     *
     * @param context The ServletContext for this web application.
     * @return An array of module prefixes.
     */
    public String[] getModulePrefixes(ServletContext context) {
        return (String[]) context.getAttribute(Globals.MODULE_PREFIXES_KEY);
    }

    /**
     * Select the module to which the specified request belongs, and
     * add corresponding request attributes to this request.
     *
     * @param request The servlet request we are processing
     * @param context The ServletContext for this web application
     */
    public void selectModule(HttpServletRequest request, ServletContext context) {
        // Compute module name
        String prefix = getModuleName(request, context);

        // Expose the resources for this module
        this.selectModule(prefix, request, context);

    }

    /**
     * Select the module to which the specified request belongs, and
     * add corresponding request attributes to this request.
     *
     * @param prefix The module prefix of the desired module
     * @param request The servlet request we are processing
     * @param context The ServletContext for this web application
     */
    public void selectModule(
        String prefix,
        HttpServletRequest request,
        ServletContext context) {

        // Expose the resources for this module
        ModuleConfig config =
            (ModuleConfig) context.getAttribute(Globals.MODULE_KEY + prefix);

        if (config != null) {
            request.setAttribute(Globals.MODULE_KEY, config);
        } else {
            request.removeAttribute(Globals.MODULE_KEY);
        }

        MessageResources resources =
            (MessageResources) context.getAttribute(Globals.MESSAGES_KEY + prefix);

        if (resources != null) {
            request.setAttribute(Globals.MESSAGES_KEY, resources);
        } else {
            request.removeAttribute(Globals.MESSAGES_KEY);
        }

    }

}
