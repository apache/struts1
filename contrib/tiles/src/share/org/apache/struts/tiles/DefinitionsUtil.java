/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/DefinitionsUtil.java,v 1.1 2001/08/01 14:36:42 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:42 $
 * $Author: cedric $
 *
 */


package org.apache.struts.tiles;

import javax.servlet.jsp.PageContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.HashMap;
import java.util.Enumeration;
import java.io.FileNotFoundException;
import java.io.IOException;

//import org.apache.struts.tiles.xmlDefinition.XmlConfigInstancesFactory;
import org.apache.struts.tiles.xmlDefinition.I18nFactorySet;
import org.apache.struts.taglib.tiles.ComponentConstants;

/**
 * Utilities class for definitions factory.
 * Also define userDebugLevel property (to be moved from this class ?).
 * (to do).
 */
public class DefinitionsUtil implements ComponentConstants
{

    /** Global user defined debug level */
  public static int userDebugLevel = 0;
    /** User Debug level */
  static public final int NO_DEBUG = 0;

    /**
     * Name of init property carrying debug level
     * @deprecated use DEFINITIONS_CONFIG_USER_DEBUG_LEVEL instead.
     */
  public static final String INSTANCES_CONFIG_USER_DEBUG_LEVEL = "instances-debug";
    /** Name of init property carrying debug level */
  public static final String DEFINITIONS_CONFIG_USER_DEBUG_LEVEL = "definitions-debug";
    /** Name of init property carrying factory class name */
  public static final String DEFINITIONS_FACTORY_CLASSNAME = "definitions-factory-class";
    /** Constant name used to store factory in context */
  public static final String DEFINITIONS_FACTORY = "org.apache.struts.tiles.DEFINITIONS_FACTORY";


    /**
     * Set user debug level. This property control level of errors output.
     */
  public static void setUserDebugLevel( int level )
    {
    userDebugLevel = level;
    }

   /**
   * Create Definition factory.
   * If
   * Create MapperCollection, and put it in appropriate servlet context.
   * This method is used to initialize Component Instances. It is usually
   * called by the initialization servlet.
   * @param servletContext
   * @return newly created MapperCollection.
   * @throw DefinitionsFactoryException If an error occur while initializing factory
   * @deprecated Use createDefinitionFactory instead.
   * @roseuid 3A599DD00327
   */
  public static void initUserDebugLevel(ServletConfig servletConfig)
  {
    // Set user debug level
  try {
    String str = servletConfig.getInitParameter(DEFINITIONS_CONFIG_USER_DEBUG_LEVEL);
    if( str == null )
      { // Check if we use old keyword
      str = servletConfig.getInitParameter(INSTANCES_CONFIG_USER_DEBUG_LEVEL);
      }
    if( str != null )
      {
      int level = Integer.parseInt( str );
      setUserDebugLevel( level );
      if( userDebugLevel > 1 )
        System.out.println( "Component Definitions debug level = " +  userDebugLevel );
      }
    }
   catch(Exception ex)
    {  // silently fail
    System.out.println( "Set user level fail" );
    ex.printStackTrace();
    }
  }

   /**
   * Create Definition factory.
   * If a factory class name is provided, a factory of this class is created. Otherwise,
   * default factory is created.
   * Factory must have a constructor taking ServletContext and Map as parameter.
   * @param classname Class name of the factory to create.
   * @param servletContext Servlet Context passed to newly created factory.
   * @param properties Map of name/property passed to newly created factory.
   * @return newly created factory.
   * @throw DefinitionsFactoryException If an error occur while initializing factory
   */
  public static ComponentDefinitionsFactory createDefinitionsFactory(ServletContext servletContext, Map properties, String classname)
    throws DefinitionsFactoryException
  {
  if( classname == null )
    return createDefinitionsFactory( servletContext, properties );

    // Try to create from classname
  try
    {
    Class factoryClass = Class.forName(classname);
    ComponentDefinitionsFactory factory = (ComponentDefinitionsFactory)factoryClass.newInstance();
    factory.initFactory( servletContext, properties);
    DefinitionsUtil.setDefinitionsFactory(factory, servletContext  );
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
   * @param servletContext Servlet Context passed to newly created factory.
   * @param properties Map of name/property passed to newly created factory.
   * @return newly created factory.
   * @throw DefinitionsFactoryException If an error occur while initializing factory
   */
  public static ComponentDefinitionsFactory createDefinitionsFactory(ServletContext servletContext, Map properties)
    throws DefinitionsFactoryException
  {
    ComponentDefinitionsFactory factory = new I18nFactorySet(servletContext, properties); ;
    DefinitionsUtil.setDefinitionsFactory(factory, servletContext  );
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
  public static ComponentDefinitionsFactory createDefinitionsFactory(ServletContext servletContext, ServletConfig servletConfig)
    throws DefinitionsFactoryException
  {
    initUserDebugLevel( servletConfig );
    String classname = servletConfig.getInitParameter(DEFINITIONS_FACTORY_CLASSNAME);
    Map properties = new ServletPropertiesMap( servletConfig );

    return createDefinitionsFactory( servletContext, properties, classname);
  }

   /**
   * Create Definition factory.
   * @deprecated Use createDefinitionFactory instead.
   */
  public static ComponentDefinitionsFactory initDefinitionsFactory(ServletContext servletContext, ServletConfig servletConfig)
    throws DefinitionsFactoryException
  {
  return createDefinitionsFactory( servletContext, servletConfig);
  }

   /**
   * Create Definition factory.
   * @param definitionName Name of definition to include.
   * @param pageContext Current page context.
   */
/*  public static void includeDefinition(String definitionName, PageContext pageContext)
    throws DefinitionsFactoryException
  {
    // Search definition
  ComponentDefinition definition = getDefinition( definitionName, pageContext);

  ComponentContext context = new ComponentContext(definition);
  pageContext.getRequest().setAttribute( ComponentConstants.COMPONENT_CONTEXT, context);

  pageContext.include( definition.getPath() );
  }
*/

  /**
   * Set definition factory in appropriate servlet context.
   * @param factory Factory to store.
   * @param servletContext Servlet context that will hold factory.
   */
  static public void setDefinitionsFactory(ComponentDefinitionsFactory factory, ServletContext servletContext)
  {
  servletContext.setAttribute(DEFINITIONS_FACTORY, factory);
  }

  /**
   * Set definition factory in appropriate servlet context.
   * Convenient method.
   * @param factory Factory to store.
   * @param pageContext Page context containing servlet context.
   */
  static public void setDefinitionsFactory(ComponentDefinitionsFactory factory, PageContext pageContext)
  {
  setDefinitionsFactory( factory, pageContext.getServletContext() );
  }

  /**
   * Get a component / template definition by its name.
   * First, retrieve instance factory, and then get requested instance.
   * Throw appropriate exception if definition is not found.
   * @throw FactoryNotFoundException Can't find definition factory.
   * @throw ComponentDefinitionsFactoryException General error in factory while getting definition.
   * @throws NoSuchDefinitionException No definition found for specified name
   */
  static public ComponentDefinition getDefinition(String definitionName, PageContext pageContext)
    throws FactoryNotFoundException, DefinitionsFactoryException
  {
  try
    {
    return getDefinitionsFactory( pageContext).getDefinition(definitionName,
                                                       (HttpServletRequest)pageContext.getRequest(),
                                                        pageContext.getServletContext());
    }
   catch( NullPointerException ex )
    {  // Factory not found in context
    throw new FactoryNotFoundException( "Can't get definitions factory from context." );
    }
  }

  /**
   * Get instances factory from appropriate servlet context.
   * @return Instances factory or null if not found.
   */
 static  public ComponentDefinitionsFactory getDefinitionsFactory(PageContext pageContext)
  {
  return (ComponentDefinitionsFactory)pageContext.getServletContext().getAttribute(DEFINITIONS_FACTORY);
  }
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
