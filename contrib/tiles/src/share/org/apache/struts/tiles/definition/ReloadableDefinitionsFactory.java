/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/definition/Attic/ReloadableDefinitionsFactory.java,v 1.3 2002/11/16 06:04:28 jmitchell Exp $
 * $Revision: 1.3 $
 * $Date: 2002/11/16 06:04:28 $
 * $Author: jmitchell $
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

/**
 * A reloadable factory.
 * This factory is the main entrance to any factory implementation. It takes in
 * charge real implementation instance, and allows reload of it by creating a new
 * instance.
 *
 */
public class ReloadableDefinitionsFactory implements ComponentDefinitionsFactory
{
    /** The real factory instance */
  protected ComponentDefinitionsFactory factory;
    /** Initialization parameters */
  protected Map properties;

    /** Name of init property carrying factory class name */
  public static final String DEFINITIONS_FACTORY_CLASSNAME = "definitions-factory-class";

    /**
     * Constructor.
     * Create a factory according to servlet settings.
     * @throws DefinitionsFactoryException If factory creation fail.
     */
  public ReloadableDefinitionsFactory( ServletContext servletContext, ServletConfig servletConfig )
    throws DefinitionsFactoryException
  {
  properties = new ServletPropertiesMap( servletConfig );
  factory = createFactory( servletContext, properties);
  }

    /**
     * Constructor.
     * Create a factory according to servlet settings.
     * @throws DefinitionsFactoryException If factory creation fail.
     */
  public ReloadableDefinitionsFactory( ServletContext servletContext, Map properties )
    throws DefinitionsFactoryException
  {
  this.properties = properties;
  factory = createFactory( servletContext, properties);
  }


   /**
   * Create Definition factory from provided classname.
   * If a factory class name is provided, a factory of this class is created. Otherwise,
   * default factory is created.
   * Factory must have a constructor taking ServletContext and Map as parameter.
   * @param classname Class name of the factory to create.
   * @param servletContext Servlet Context passed to newly created factory.
   * @param properties Map of name/property passed to newly created factory.
   * @return newly created factory.
   * @throw DefinitionsFactoryException If an error occur while initializing factory
   */
  public ComponentDefinitionsFactory createFactoryFromClassname(ServletContext servletContext, Map properties, String classname)
    throws DefinitionsFactoryException
  {
  if( classname == null )
    return createFactory( servletContext, properties );

    // Try to create from classname
  try
    {
    Class factoryClass = Class.forName(classname);
    ComponentDefinitionsFactory factory = (ComponentDefinitionsFactory)factoryClass.newInstance();
    factory.initFactory( servletContext, properties);
    return factory;
    }
   catch( ClassCastException ex )
    { // Bad classname
    throw new DefinitionsFactoryException( "Error - createDefinitionsFactory : Factory class '"
                                           + classname +" must implements 'ComponentDefinitionsFactory'.", ex );
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
   * Create default Definition factory.
   * Factory must have a constructor taking ServletContext and Map as parameter.
   * In this implementation, default factory is of class I18nFactorySet
   * @param servletContext Servlet Context passed to newly created factory.
   * @param properties Map of name/property passed to newly created factory.
   * @return newly created factory.
   * @throw DefinitionsFactoryException If an error occur while initializing factory
   */
  public ComponentDefinitionsFactory createDefaultFactory(ServletContext servletContext, Map properties)
    throws DefinitionsFactoryException
  {
    ComponentDefinitionsFactory factory = new I18nFactorySet(servletContext, properties); ;
    return factory;
  }

   /**
   * Create Definition factory.
   * Convenience method. ServletConfig is wrapped into a Map allowing retrieval
   * of init parameters. Factory classname is also retrieved, as well as debug level.
   * Finally, approriate createDefinitionsFactory() is called.
   * @param servletContext Servlet Context passed to newly created factory.
   * @param servletConfig Servlet config containing parameters to be passed to newly created factory.
   */
  public ComponentDefinitionsFactory createFactory(ServletContext servletContext, Map properties)
    throws DefinitionsFactoryException
  {
    String classname = (String)properties.get(DEFINITIONS_FACTORY_CLASSNAME);
    if(classname!=null)
      return createFactoryFromClassname( servletContext, properties, classname);

    return new I18nFactorySet( servletContext, properties );
  }


  /**
   * Get a definition by its name.
   * Call appropriate method on underlying factory instance.
   * Throw appropriate exception if definition or definition factory is not found.
   * @param name Name of requested definition.
   * @param request Current servelet request
   * @param servletContext current servlet context
   * @throw FactoryNotFoundException Can't find definition factory.
   * @throw DefinitionsFactoryException General error in factory while getting definition.
   * @throws NoSuchDefinitionException No definition found for specified name
   */
  public ComponentDefinition getDefinition(String definitionName, ServletRequest request, ServletContext servletContext)
    throws FactoryNotFoundException, DefinitionsFactoryException
  {
  return factory.getDefinition(definitionName, (HttpServletRequest)request, servletContext);
  }

  /**
   * Reload underlying factory.
   * Reload is done creating a new factory instance, and replacing old instance
   * by the new one.
   * @param request Current servelet request
   * @param servletContext current servlet context
   * @throws DefinitionsFactoryException If factory creation fail.
   */
  public void reload(ServletContext servletContext)
    throws DefinitionsFactoryException
  {
  ComponentDefinitionsFactory newInstance = createFactory( servletContext, properties);
  factory = newInstance;
  }

  /**
   * Get underlying factory instance.
   */
  public ComponentDefinitionsFactory getFactory()
  {
  return factory;
  }

   /**
     * Init factory.
     * This method is required by interface ComponentDefinitionsFactory. It is
     * not used in this implementation, as it manage itself underlying creation
     * and initialization.
     * @param servletContext Servlet Context passed to newly created factory.
     * @param properties Map of name/property passed to newly created factory.
     * Map can contains more properties than requested.
     * @throws DefinitionsFactoryException An error occur during initialization.
   */
   public void initFactory(ServletContext servletContext, Map properties) throws DefinitionsFactoryException
   {  // do nothing
   }

   /**
    * Get this object as a String
    */
   public String toString()
   {
   return factory.toString();
   }

  /**
   * Inner class.
   * Wrapper for ServletContext init parameters.
   * Object of this class is an hashmap containing parameters and values
   * defined in the servlet config file (web.xml).
   */
 class ServletPropertiesMap extends HashMap {
    /**
     * Constructor.
     */
  ServletPropertiesMap( ServletConfig config )
    {
      // This implementation is very simple.
      // It is possible to avoid creation of a new structure, but this need
      // imply writing all Map interface.
    Enumeration enum = config.getInitParameterNames();
    while( enum.hasMoreElements() )
      {
      String key = (String)enum.nextElement();
      put( key, config.getInitParameter( key ) );
      }
    }
}  // end inner class
}