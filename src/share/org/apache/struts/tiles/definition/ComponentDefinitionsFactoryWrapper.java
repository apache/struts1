package org.apache.struts.tiles.definition;

import org.apache.struts.tiles.DefinitionsFactory;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.ComponentDefinitionsFactory;
import org.apache.struts.tiles.DefinitionsFactoryConfig;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.NoSuchDefinitionException;

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
     */
  public ComponentDefinitionsFactoryWrapper( ComponentDefinitionsFactory factory )
  {
  this.factory = factory;
  }

    /**
     * Constructor.
     * Create new wrapper.
     * The config object passed to init method should reference a factory implementing
     * ComponentDefinitionsFactory.
     */
  public ComponentDefinitionsFactoryWrapper()
  {
  }

    /**
     * Get requested definition.
     */
  public ComponentDefinition getDefinition(String name, ServletRequest request, ServletContext servletContext)
    throws NoSuchDefinitionException, DefinitionsFactoryException
  {
  return factory.getDefinition(name, request, servletContext);
  }

    /**
     * Call underlying factory init method.
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
     */
  public DefinitionsFactoryConfig getConfig()
  {
  return config;
  }

   /**
    * Get internal factory
    */
  public ComponentDefinitionsFactory getInternalFactory()
  {
  return factory;
  }

  /**
   * Create Definition factory from provided classname which must implements ComponentDefinitionsFactory.
   * Factory class must extends TilesDefinitionsFactory.
   * @param classname Class name of the factory to create.
   * @return newly created factory.
   * @throws DefinitionsFactoryException If an error occur while initializing factory
   */
  protected ComponentDefinitionsFactory createFactoryInstance(String classname)
    throws DefinitionsFactoryException
  {
  try
    {
    Class factoryClass = Class.forName(classname);
    Object factory = factoryClass.newInstance();
    return (ComponentDefinitionsFactory)factory;
    }
   catch( ClassCastException ex )
    { // Bad classname
    throw new DefinitionsFactoryException( "Error - createDefinitionsFactory : Factory class '"
                                           + classname +" must implements 'TilesDefinitionsFactory'.", ex );
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
     * ToString method.
     * Call toString on underlying factory.
     */
  public String toString()
  {
  return getInternalFactory().toString();
  }

    /**
     * Create map of configuration attributes from configuration object.
     * Mapping is done between old names and new names.
     * @return Map Map of name/value pairs.
     */
  public static Map createConfigMap( DefinitionsFactoryConfig config )
  {
   Map map = new HashMap(config.getAttributes());
     // Add property attributes using old names
   map.put(config.DEFINITIONS_CONFIG_PARAMETER_NAME, config.getDefinitionConfigFiles());
   map.put(config.TILES_DETAILS_PARAMETER_NAME, Integer.toString(config.getDebugLevel()) );
   map.put(config.PARSER_DETAILS_PARAMETER_NAME, Integer.toString(config.getParserDebugLevel()) );
   map.put(config.PARSER_VALIDATE_PARAMETER_NAME, new Boolean(config.getParserValidate()).toString() );

   if( ! "org.apache.struts.tiles.xmlDefinition.I18nFactorySet".equals(config.getFactoryClassname()) )
     map.put(config.FACTORY_CLASSNAME_PARAMETER_NAME, config.getFactoryClassname());

  return map;
  }
}
