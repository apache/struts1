/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/DefinitionsUtil.java,v 1.3 2001/12/27 17:35:38 cedric Exp $
 * $Revision: 1.3 $
 * $Date: 2001/12/27 17:35:38 $
 * $Author: cedric $
 *
 */


package org.apache.struts.tiles;

import javax.servlet.jsp.PageContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletRequest;

import java.util.Map;
import java.util.HashMap;
import java.util.Enumeration;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.struts.tiles.definition.ReloadableDefinitionsFactory;
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
    /** Constant name used to store definition in jsp context.
     *  Used to pass definition from a Struts action to servlet forward */
  public static final String ACTION_DEFINITION = "org.apache.struts.tiles.ACTION_DEFINITION";


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
   * @deprecated Use createDefinitionsFactory(ServletContext servletContext, Map properties)
   */
  public static ComponentDefinitionsFactory createDefinitionsFactory(ServletContext servletContext, Map properties, String classname)
    throws DefinitionsFactoryException
  {
  properties.put( ReloadableDefinitionsFactory.DEFINITIONS_FACTORY_CLASSNAME, classname );
  return createDefinitionsFactory(servletContext, properties);
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
  ComponentDefinitionsFactory factory = new ReloadableDefinitionsFactory(servletContext, properties); ;
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
  initUserDebugLevel(servletConfig);
  ComponentDefinitionsFactory factory = new ReloadableDefinitionsFactory(servletContext, servletConfig); ;
  DefinitionsUtil.setDefinitionsFactory(factory, servletContext  );
  return factory;
  }

  /**
   * Set definition factory in appropriate servlet context.
   * @param factory Factory to store.
   * @param servletContext Servlet context that will hold factory.
   */
  static protected void setDefinitionsFactory(ComponentDefinitionsFactory factory, ServletContext servletContext)
  {
  servletContext.setAttribute(DEFINITIONS_FACTORY, factory);
  }

  /**
   * Set definition factory in appropriate servlet context.
   * Convenient method.
   * @param factory Factory to store.
   * @param pageContext Page context containing servlet context.
   */
  static protected void setDefinitionsFactory(ComponentDefinitionsFactory factory, PageContext pageContext)
  {
  setDefinitionsFactory( factory, pageContext.getServletContext() );
  }

  /**
   * Get a definition by its name.
   * First, retrieve definition factory, and then get requested definition.
   * Throw appropriate exception if definition or definition factory is not found.
   * @param name Name of requested definition.
   * @param request Current servelet request
   * @param servletContext current servlet context
   * @throw FactoryNotFoundException Can't find definition factory.
   * @throw DefinitionsFactoryException General error in factory while getting definition.
   * @throws NoSuchDefinitionException No definition found for specified name
   */
  static public ComponentDefinition getDefinition(String definitionName, ServletRequest request, ServletContext servletContext)
    throws FactoryNotFoundException, DefinitionsFactoryException
  {
  try
    {
    return getDefinitionsFactory(servletContext).getDefinition(definitionName,
                                                       (HttpServletRequest)request,
                                                        servletContext);
    }
   catch( NullPointerException ex )
    {  // Factory not found in context
    throw new FactoryNotFoundException( "Can't get definitions factory from context." );
    }
  }

  /**
   * Get a component / template definition by its name.
   * First, retrieve instance factory, and then get requested instance.
   * Throw appropriate exception if definition is not found.
   * @param name Name of requested definition.
   * @param request Current servelet request
   * @param servletContext current servlet context
   * @throw FactoryNotFoundException Can't find definition factory.
   * @throw DefinitionsFactoryException General error in factory while getting definition.
   * @throws NoSuchDefinitionException No definition found for specified name
   */
  static public ComponentDefinition getDefinition(String definitionName, PageContext pageContext)
    throws FactoryNotFoundException, DefinitionsFactoryException
  {
  return getDefinition( definitionName,
                        (HttpServletRequest)pageContext.getRequest(),
                        pageContext.getServletContext());
  }

  /**
   * Get instances factory from appropriate servlet context.
   * @return Definitions factory or null if not found.
   */
 static  public ComponentDefinitionsFactory getDefinitionsFactory(ServletContext servletContext)
  {
  return (ComponentDefinitionsFactory)servletContext.getAttribute(DEFINITIONS_FACTORY);
  }

  /**
   * Get instances factory from appropriate servlet context.
   * @return Definitions factory or null if not found.
   */
 static  public ComponentDefinitionsFactory getDefinitionsFactory(PageContext pageContext)
  {
  return getDefinitionsFactory( pageContext.getServletContext());
  }

  /**
   * Get Definition stored in jsp context by an action.
   * @return ComponentDefinition or null if not found.
   */
 static  public ComponentDefinition getActionDefinition(ServletRequest request)
  {
  return (ComponentDefinition)request.getAttribute(ACTION_DEFINITION);
  }

  /**
   * Store definition in jsp context.
   * Mainly used by Struts to pass a definition defined in an Action to the forward.
   */
 static  public void setActionDefinition(ServletRequest request, ComponentDefinition definition)
  {
  request.setAttribute(ACTION_DEFINITION, definition);
  }

  /**
   * Remove Definition stored in jsp context.
   * Mainly used by Struts to pass a definition defined in an Action to the forward.
   */
 static  public void removeActionDefinition(ServletRequest request, ComponentDefinition definition)
  {
  request.removeAttribute(ACTION_DEFINITION);
  }
}

