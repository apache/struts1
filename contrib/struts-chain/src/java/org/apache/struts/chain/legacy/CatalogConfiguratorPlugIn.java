/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/legacy/CatalogConfiguratorPlugIn.java,v 1.1 2003/08/30 22:01:24 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/08/30 22:01:24 $
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

package org.apache.struts.chain.legacy;


import java.net.URL;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ModuleConfig;

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.config.ConfigParser;
import org.apache.commons.chain.impl.CatalogBase;


/**
 * <p><strong>CatalogConfiguratorPlugIn</strong> looks up the current
 * <code>Catalog</code> stored under the <code>Constants.CATALOG_KEY</code>
 * context attribute (creating an empty one if necessary), and adds new
 * chain definitions from one of the following two sources (only one of these
 * properties should be configured for a particular instance of this
 * PlugIn) based on the property that was configured:</p>
 * <ul>
 * <li><strong>path</strong> - Context-relative path to a web application
 *     resource that contains chain definitions.</li>
 * <li><strong>resource</strong> - Absolute resource path to a class loader
 *     resource that should be in a JAR file under <code>/WEB-INF/lib</code>,
 *     or a separate file under <code>/WEB-INF/classes</code>.</li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @author Greg Reddin
 */

public class CatalogConfiguratorPlugIn implements PlugIn {


    // ------------------------------------------------------ Instance Variables


    private String path = null;
    private String resource = null;


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context-relative resource path to load from.
     */
    public String getPath() {
        return (this.path);
    }


    /**
     * <p>Set the context-relative resource path to load from.
     *
     * @param path The new context relative resource path
     */
    public void setPath(String path) {
        this.path = path;
    }


    /**
     * <p>Return the classloader resource path to load from.
     */
    public String getResource() {
        return (this.resource);
    }


    /**
     * <p>Set the classloader resource path to load from.
     *
     * @param resource The new classloader resource path
     */
    public void setResource(String resource) {
        this.resource = resource;
    }


    // ---------------------------------------------------------- PlugIn Methods


    /**
     * <p>Clean up at web application shutdown.</p>
     */
    public void destroy() {

        ; // No action required

    }


    /**
     * <p>Look up the <code>Catalog</code> we will be configuring (creating one
     * if necessary), and add definitions from the specified resource specified
     * by the <code>path</code> or <code>resource</code> properties configured
     * on this {@link CatalogConfiguratorPlugIn} instance.</p>
     */
    public void init(ActionServlet servlet, ModuleConfig config)
        throws ServletException {

        // Retrieve or create the Catalog instance we will be updating
        Catalog catalog = (Catalog)
            servlet.getServletContext().getAttribute(Constants.CATALOG_KEY);
        if (catalog == null) {
            catalog = new CatalogBase();
            servlet.getServletContext().setAttribute(Constants.CATALOG_KEY,
                                                     catalog);
        }

        // Add or replace chain definitions from the specified resource
        try {
            ConfigParser parser = new ConfigParser();
            URL configResource = null;
            if (path != null) {
                configResource =
                    servlet.getServletContext().getResource(path);
            } else if (resource != null) {
                ClassLoader loader =
                    Thread.currentThread().getContextClassLoader();
                if (loader == null) {
                    loader = this.getClass().getClassLoader();
                }
                configResource = loader.getResource(resource);
            }
            parser.parse(catalog, configResource);
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }



}
