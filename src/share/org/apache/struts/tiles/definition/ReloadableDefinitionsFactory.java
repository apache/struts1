/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/definition/ReloadableDefinitionsFactory.java,v 1.9 2003/08/12 15:03:42 martinc Exp $
 * $Revision: 1.9 $
 * $Date: 2003/08/12 15:03:42 $
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

package org.apache.struts.tiles.definition;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.ComponentDefinitionsFactory;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.FactoryNotFoundException;
import org.apache.struts.tiles.xmlDefinition.I18nFactorySet;
import org.apache.struts.tiles.TilesUtil;
import org.apache.struts.util.RequestUtils;

/**
 * A reloadable factory.
 * This factory is the main entrance to any factory implementation. It takes in
 * charge real implementation instance, and allows reloading by creating a new
 * instance.
 *
 * @author Cedric Dumoulin
 * @since Struts 1.1
 * @version $Revision: 1.9 $ $Date: 2003/08/12 15:03:42 $
 */
public class ReloadableDefinitionsFactory implements ComponentDefinitionsFactory {

    /** 
     * The real factory instance. 
     */
    protected ComponentDefinitionsFactory factory = null;

    /** 
     * Initialization parameters. 
     */
    protected Map properties = null;

    /** 
     * Name of init property carrying factory class name. 
     */
    public static final String DEFINITIONS_FACTORY_CLASSNAME =
        "definitions-factory-class";

    /**
     * Constructor.
     * Create a factory according to servlet settings.
     * @param servletContext Our servlet context.
     * @param servletConfig Our servlet config.
     * @throws DefinitionsFactoryException If factory creation fail.
     */
    public ReloadableDefinitionsFactory(
        ServletContext servletContext,
        ServletConfig servletConfig)
        throws DefinitionsFactoryException {

        properties = new ServletPropertiesMap(servletConfig);
        factory = createFactory(servletContext, properties);
    }

    /**
     * Constructor.
     * Create a factory according to servlet settings.
     * @param servletContext Our servlet context.
     * @param properties Map containing all properties.
     * @throws DefinitionsFactoryException If factory creation fail.
     */
    public ReloadableDefinitionsFactory(
        ServletContext servletContext,
        Map properties)
        throws DefinitionsFactoryException {

        this.properties = properties;
        factory = createFactory(servletContext, properties);
    }

    /**
    * Create Definition factory from provided classname.
    * If a factory class name is provided, a factory of this class is created. Otherwise,
    * a default factory is created.
    * Factory must have a constructor taking ServletContext and Map as parameter.
    * @param classname Class name of the factory to create.
    * @param servletContext Servlet Context passed to newly created factory.
    * @param properties Map of name/property passed to newly created factory.
    * @return newly created factory.
    * @throws DefinitionsFactoryException If an error occur while initializing factory
    */
    public ComponentDefinitionsFactory createFactoryFromClassname(
        ServletContext servletContext,
        Map properties,
        String classname)
        throws DefinitionsFactoryException {

        if (classname == null) {
            return createFactory(servletContext, properties);
        }

        // Try to create from classname
        try {
            Class factoryClass = RequestUtils.applicationClass(classname);
            ComponentDefinitionsFactory factory =
                (ComponentDefinitionsFactory) factoryClass.newInstance();
            factory.initFactory(servletContext, properties);
            return factory;

        } catch (ClassCastException ex) { // Bad classname
            throw new DefinitionsFactoryException(
                "Error - createDefinitionsFactory : Factory class '"
                    + classname
                    + " must implements 'ComponentDefinitionsFactory'.",
                ex);

        } catch (ClassNotFoundException ex) { // Bad classname
            throw new DefinitionsFactoryException(
                "Error - createDefinitionsFactory : Bad class name '"
                    + classname
                    + "'.",
                ex);

        } catch (InstantiationException ex) { // Bad constructor or error
            throw new DefinitionsFactoryException(ex);

        } catch (IllegalAccessException ex) {
            throw new DefinitionsFactoryException(ex);
        }

    }

    /**
    * Create default Definition factory.
    * Factory must have a constructor taking ServletContext and Map as parameter.
    * In this implementation, default factory is of class I18nFactorySet
    * @param servletContext Servlet Context passed to newly created factory.
    * @param properties Map of name/property passed to newly created factory.
    * @return newly created factory.
    * @throws DefinitionsFactoryException If an error occur while initializing factory
    */
    public ComponentDefinitionsFactory createDefaultFactory(
        ServletContext servletContext,
        Map properties)
        throws DefinitionsFactoryException {

        ComponentDefinitionsFactory factory =
            new I18nFactorySet(servletContext, properties);

        return factory;
    }

    /**
    * Create Definition factory.
    * Convenience method. ServletConfig is wrapped into a Map allowing retrieval
    * of init parameters. Factory classname is also retrieved, as well as debug level.
    * Finally, approriate createDefinitionsFactory() is called.
    * @param servletContext Servlet Context passed to newly created factory.
    * @param properties Map containing all properties.
    */
    public ComponentDefinitionsFactory createFactory(
        ServletContext servletContext,
        Map properties)
        throws DefinitionsFactoryException {

        String classname = (String) properties.get(DEFINITIONS_FACTORY_CLASSNAME);

        if (classname != null) {
            return createFactoryFromClassname(servletContext, properties, classname);
        }

        return new I18nFactorySet(servletContext, properties);
    }

    /**
     * Get a definition by its name.
     * Call appropriate method on underlying factory instance.
     * Throw appropriate exception if definition or definition factory is not found.
     * @param definitionName Name of requested definition.
     * @param request Current servlet request.
     * @param servletContext Current servlet context.
     * @throws FactoryNotFoundException Can't find definition factory.
     * @throws DefinitionsFactoryException General error in factory while getting definition.
     */
    public ComponentDefinition getDefinition(
        String definitionName,
        ServletRequest request,
        ServletContext servletContext)
        throws FactoryNotFoundException, DefinitionsFactoryException {

        return factory.getDefinition(
            definitionName,
            (HttpServletRequest) request,
            servletContext);
    }

    /**
     * Reload underlying factory.
     * Reload is done by creating a new factory instance, and replacing the old instance
     * with the new one.
     * @param servletContext Current servlet context.
     * @throws DefinitionsFactoryException If factory creation fails.
     */
    public void reload(ServletContext servletContext)
        throws DefinitionsFactoryException {

        ComponentDefinitionsFactory newInstance =
            createFactory(servletContext, properties);

        factory = newInstance;
    }

    /**
     * Get underlying factory instance.
     * @return ComponentDefinitionsFactory
     */
    public ComponentDefinitionsFactory getFactory() {
        return factory;
    }

    /**
      * Init factory.
      * This method is required by interface ComponentDefinitionsFactory. It is
      * not used in this implementation, as it manages itself the underlying creation
      * and initialization.
      * @param servletContext Servlet Context passed to newly created factory.
      * @param properties Map of name/property passed to newly created factory.
      * Map can contain more properties than requested.
      * @throws DefinitionsFactoryException An error occur during initialization.
    */
    public void initFactory(ServletContext servletContext, Map properties)
        throws DefinitionsFactoryException {
        // do nothing
    }

    /**
     * Return String representation.
     * @return String representation.
     */
    public String toString() {
        return factory.toString();
    }

    /**
     * Inner class.
     * Wrapper for ServletContext init parameters.
     * Object of this class is an HashMap containing parameters and values
     * defined in the servlet config file (web.xml).
     */
    class ServletPropertiesMap extends HashMap {
        /**
         * Constructor.
         */
        ServletPropertiesMap(ServletConfig config) {
            // This implementation is very simple.
            // It is possible to avoid creation of a new structure, but this would
            // imply writing all of the Map interface.
            Enumeration e = config.getInitParameterNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                put(key, config.getInitParameter(key));
            }
        }
    } // end inner class
}
