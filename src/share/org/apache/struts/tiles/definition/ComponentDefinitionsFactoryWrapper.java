/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/definition/ComponentDefinitionsFactoryWrapper.java,v 1.5 2003/03/08 19:23:50 dgraham Exp $
 * $Revision: 1.5 $
 * $Date: 2003/03/08 19:23:50 $
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

import org.apache.struts.tiles.DefinitionsFactory;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.ComponentDefinitionsFactory;
import org.apache.struts.tiles.DefinitionsFactoryConfig;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.NoSuchDefinitionException;
import org.apache.struts.tiles.TilesUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletContext;

import java.util.Map;
import java.util.HashMap;

/**
 * Wrapper from new definition factory interface to old interface.
 * This class provides mapping from the old interface's life cycle to the new life cycle.
 * @author Cedric Dumoulin
 * @since 20020708
 */

public class ComponentDefinitionsFactoryWrapper implements DefinitionsFactory
{

    /** The underlying factory */
  private ComponentDefinitionsFactory factory;
    /** Factory configuration*/
  private DefinitionsFactoryConfig config;

    /**
     * Constructor.
     * Create new wrapper for specified factory.
     * @param factory The factory to create a wrapper for.
     */
  public ComponentDefinitionsFactoryWrapper( ComponentDefinitionsFactory factory )
  {
  this.factory = factory;
  }

    /**
     * Constructor.
     * Create new wrapper.
     * The config object passed to init method should reference a factory implementing
     * {@link ComponentDefinitionsFactory}.
     */
  public ComponentDefinitionsFactoryWrapper()
  {
  }

    /**
     * Get requested definition.
     * @param name Name of the definition.
     * @param request The request we are processing.
     * @param servletContext Our servlet context.
     * @return ComponentDefition
     */
  public ComponentDefinition getDefinition(String name, ServletRequest request, ServletContext servletContext)
    throws NoSuchDefinitionException, DefinitionsFactoryException
  {
  return factory.getDefinition(name, request, servletContext);
  }

    /**
     * Call underlying factory init method.
     * @param config DefinitionsFactoryConfig.
     * @param servletContext Our servlet context.
     */
  public void init(DefinitionsFactoryConfig config, ServletContext servletContext)
    throws DefinitionsFactoryException
  {
  this.config = config;
    // create factory and initialize it
  if( factory == null)
    factory = createFactoryInstance( config.getFactoryClassname());
  factory.initFactory(servletContext, createConfigMap(config));
  }

    /**
     * Do nothing because old life cycle has no equivalent.
     */
  public void destroy()
  {
  factory = null;
  }

    /**
     * Set underlying factory configuration.
     * @param config DefinitionsFactoryConfig to use.
     * @param servletContext Our servlet context.
     *
     */
  public void setConfig(DefinitionsFactoryConfig config, ServletContext servletContext)
    throws DefinitionsFactoryException
  {
    // create a new factory and initialize it
  ComponentDefinitionsFactory newFactory = createFactoryInstance( config.getFactoryClassname());
  newFactory.initFactory(servletContext, createConfigMap(config));
  factory = newFactory;
  }

    /**
     * Get underlying factory configuration.
     * @return DefinitionsFactoryConfig.
     */
  public DefinitionsFactoryConfig getConfig()
  {
  return config;
  }

   /**
    * Get internal factory.
    * @return The internal ComponentDefitionsFactory.
    */
  public ComponentDefinitionsFactory getInternalFactory()
  {
  return factory;
  }

  /**
   * Create Definition factory from provided classname which must implement {@link ComponentDefinitionsFactory}.
   * Factory class must extend {@link DefinitionsFactory}.
   * @param classname Class name of the factory to create.
   * @return newly created factory.
   * @throws DefinitionsFactoryException If an error occur while initializing factory
   */
  protected ComponentDefinitionsFactory createFactoryInstance(String classname)
    throws DefinitionsFactoryException
  {
  try
    {
    Class factoryClass = TilesUtil.applicationClass(classname);
    Object factory = factoryClass.newInstance();
    return (ComponentDefinitionsFactory)factory;
    }
   catch( ClassCastException ex )
    { // Bad classname
    throw new DefinitionsFactoryException( "Error - createDefinitionsFactory : Factory class '"
                                           + classname +" must implement 'DefinitionsFactory'.", ex );
    }
   catch( ClassNotFoundException ex )
    { // Bad classname
    throw new DefinitionsFactoryException( "Error - createDefinitionsFactory : Bad class name '"
                                           + classname +"'.", ex );
    }
   catch( InstantiationException ex )
    { // Bad constructor or error
    throw new DefinitionsFactoryException( ex );
    }
   catch( IllegalAccessException ex )
    { //
    throw new DefinitionsFactoryException( ex );
    }

  }

    /**
     * Return String representation.
     * Calls toString() on underlying factory.
     * @return String representation.
     */
  public String toString()
  {
  return getInternalFactory().toString();
  }

    /**
     * Create map of configuration attributes from configuration object.
     * Mapping is done between old names and new names.
     * @param config The DefinitionsFactoryConfig to use.
     * @return Map Map of name/value pairs.
     */
  public static Map createConfigMap( DefinitionsFactoryConfig config )
  {
   Map map = new HashMap(config.getAttributes());
     // Add property attributes using old names
   map.put(DefinitionsFactoryConfig.DEFINITIONS_CONFIG_PARAMETER_NAME, config.getDefinitionConfigFiles());
   map.put(DefinitionsFactoryConfig.TILES_DETAILS_PARAMETER_NAME, Integer.toString(config.getDebugLevel()) );
   map.put(DefinitionsFactoryConfig.PARSER_DETAILS_PARAMETER_NAME, Integer.toString(config.getParserDebugLevel()) );
   map.put(DefinitionsFactoryConfig.PARSER_VALIDATE_PARAMETER_NAME, new Boolean(config.getParserValidate()).toString() );

   if( ! "org.apache.struts.tiles.xmlDefinition.I18nFactorySet".equals(config.getFactoryClassname()) )
     map.put(DefinitionsFactoryConfig.FACTORY_CLASSNAME_PARAMETER_NAME, config.getFactoryClassname());

  return map;
  }
}
