/*
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.struts.chain.legacy;


import java.net.URL;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ModuleConfig;

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.CatalogFactory;
import org.apache.commons.chain.config.ConfigParser;
import org.apache.commons.chain.impl.CatalogBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * <p><strong>CatalogConfiguratorPlugIn</strong> parses the configuration
 * resource specified by the <code>path</code> or <code>resource</code>
 * property to configure the default {@link Catalog} registered with the
 * {@link CatalogFactory} for this application.</p>
 * <ul>
 * <li><strong>path</strong> - Context-relative path to a web application
 *     resource that contains chain definitions.</li>
 * <li><strong>resource</strong> - Absolute resource path to a class loader
 *     resource that should be in a JAR file under <code>/WEB-INF/lib</code>,
 *     or a separate file under <code>/WEB-INF/classes</code>.</li>
 * </ul>
 *
 */

public class CatalogConfiguratorPlugIn implements PlugIn {


    // ------------------------------------------------------ Instance Variables


    private static Log log = LogFactory.getLog(CatalogConfiguratorPlugIn.class);
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

        CatalogFactory.clear();

    }


    /**
     * <p>Parse the configuration documents specified by the <code>path</code>
     * or <code>resource</code> property to configure the default
     * {@link Catalog} that is registered in the {@link CatalogFactory}
     * instance for this application.</p>
     */
    public void init(ActionServlet servlet, ModuleConfig config)
        throws ServletException {

        // Parse the configuration file specified by path or resource
        try {
            ConfigParser parser = new ConfigParser();
            URL configResource = null;
            if (path != null) {
                log.info("Loading context relative resources from '" +
                         path + "'");
                configResource =
                    servlet.getServletContext().getResource(path);
            } else if (resource != null) {
                log.info("Loading classloader resources from '" +
                         resource + "'");
                ClassLoader loader =
                    Thread.currentThread().getContextClassLoader();
                if (loader == null) {
                    loader = this.getClass().getClassLoader();
                }
                configResource = loader.getResource(resource);
            }
            parser.parse(configResource);
        } catch (Exception e) {
            log.error("Exception loading resources", e);
            throw new ServletException(e);
        }

    }



}
