/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/TilesPlugin.java,v 1.1 2002/07/11 16:22:27 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2002/07/11 16:22:27 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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

package org.apache.struts.tiles;

import org.apache.struts.tiles.definition.ConfigurableDefinitionsFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.PlugInConfig;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.config.ControllerConfig;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import org.apache.struts.config.ApplicationConfig;


/**
 * Tiles Plugin used to initialize Tiles.
 * This plugin is to be used with Struts 1.1 in association with
 * TilesRequestProcessor.
 * <br>
 * Plugin should be declared in each "struts-config.xml" files. This plugin
 * ensure one common definition factory for all modules. The definition factory
 * configuration is read first from 'web.xml' (backward compatibility), then it is
 * overloaded by values found in plugin property values.
 * <br>
 * The plugin change Struts configuration by specifying a TilesRequestProcessor as
 * request processor. If user has also specified another request processor, this
 * later should subclass the TilesRequestProcessor class.
 * <br>
 * In case of several modules, the definition factory
 * configuration is read by the first tiles plugin to be initialized. The order is
 * determined by the order of modules declaration in web.xml. The first module
 * is always the default one if it exists.
 * The plugin should be declared in each struts-config.xml file in order to
 * properly intialize the request processor.
 * @author Cedric Dumoulin
 * @since 1.1
 */
public class TilesPlugin implements PlugIn {

    /** Debug flag */
  public static final boolean debug = true;
    /** Associated definition factory */
  protected DefinitionsFactory definitionFactory;

  /**
   * <p>Receive notification that the specified sub-applicaiton is being
   * started up.</p>
   *
   * @param servlet ActionServlet that is managing all the sub-applications
   *  in this web application
   * @param config ApplicationConfig for the sub-application with which
   *  this plug in is associated
   *
   * @exception ServletException if this <code>PlugIn</code> cannot
   *  be successfully initialized
   */
  public void init(ActionServlet servlet, ApplicationConfig config)
      throws ServletException
  {
    // Check if factory already exist in context.
  definitionFactory = DefinitionsUtil.getDefinitionsFactory( servlet.getServletContext() );
  if( definitionFactory != null )
    {
    if(debug)
      {
      System.out.println( "Warning - TilesPlugin : factory already exists. No new creation." );
      servlet.log( "Warning - TilesPlugin : factory already exists. No new creation." );
      }
    return;
    } // end if

    // Initialize
  DefinitionsFactoryConfig factoryConfig = readFactoryConfig(servlet, config);
    // Set RequestProcessor class
  initRequestProcessorClass( config );
    // Create configurable factory
  try
    {
    definitionFactory = DefinitionsUtil.createDefinitionsFactory(servlet.getServletContext(), factoryConfig );
    }
   catch( DefinitionsFactoryException ex )
    {
    throw new ServletException( ex );
    }

  servlet.log( "Tiles definition factory loaded for processor '" + config.getPrefix() + "'." );
  }

    /**
     * End plugin.
     */
  public void destroy()
  {
  definitionFactory.destroy();
  definitionFactory=null;
  }

  /**
   * Create FactoryConfig and initialize it from web.xml and struts-config.xml.
   *
   * @param servlet ActionServlet that is managing all the sub-applications
   *  in this web application
   * @param config ApplicationConfig for the sub-application with which
   *  this plug in is associated
   * @exception ServletException if this <code>PlugIn</code> cannot
   *  be successfully initialized
   */
  protected DefinitionsFactoryConfig readFactoryConfig(ActionServlet servlet, ApplicationConfig config)
      throws ServletException
  {
    // Create tiles definitions config object
  DefinitionsFactoryConfig factoryConfig = new DefinitionsFactoryConfig();
    // Get init parameters from web.xml files
  try
    {
    DefinitionsUtil.populateDefinitionsFactoryConfig(factoryConfig, servlet.getServletConfig());
    }
   catch(Exception ex)
    {
    if( debug )
      ex.printStackTrace();
    throw new UnavailableException( "Can't populate DefinitionsFactoryConfig class from 'web.xml': " + ex.getMessage() );
    }
    // Get init parameters from struts-config.xml
  try
    {
    Map strutsProperties = findStrutsPlugInConfigProperties(servlet, config);
    factoryConfig.populate(strutsProperties);
    }
   catch(Exception ex)
    {
    if( debug )
      ex.printStackTrace();
    throw new UnavailableException( "Can't populate DefinitionsFactoryConfig class from '"
                                    + config.getPrefix() + "/struts-config.xml':"
                                    + ex.getMessage() );
    }
  return factoryConfig;
  }

  /**
   * Find original properties set in the struts PlugInConfig object.
   * First, need to find the index of this plugin. Then retrieve array of configs
   * and then the object for this plugin.
   * @param servlet ActionServlet that is managing all the sub-applications
   *  in this web application
   * @param config ApplicationConfig for the sub-application with which
   *  this plug in is associated
   *
   * @exception ServletException if this <code>PlugIn</code> cannot
   *  be successfully initialized
   */
  protected Map findStrutsPlugInConfigProperties(ActionServlet servlet, ApplicationConfig config)
      throws ServletException
  {
  PlugIn plugIns[] = (PlugIn[])servlet.getServletContext().getAttribute( Action.PLUG_INS_KEY + config.getPrefix() );
  int index=0;
  while( index<plugIns.length && plugIns[index] != this )
    {
    index++;
    } // end loop
    // Check if ok
  if( plugIns[index] != this )
    {
    String msg = "Can't initialize tiles definition factory : plugin configuration object not found.";
    System.out.println( msg );
    servlet.log( msg );
    throw new ServletException( msg );
    } // end if
    // Get plugin
  PlugInConfig plugInConfig = config.findPlugInConfigs()[index];
  return plugInConfig.getProperties();
  }

  /**
   * Set RequestProcessor to appropriate Tiles RequestProcessor.
   * First check if a requestprocessor is specified. If yes, check if it extends
   * appropriate TilesRequestProcessor class. If no, set processor class to
   * TilesRequestProcessor
   *
   * @param config ApplicationConfig for the sub-application with which
   *  this plug in is associated
   * @throws ServletException If an error occur
   */
  protected void initRequestProcessorClass( ApplicationConfig config )
    throws ServletException
  {
  String tilesProcessorClassname = TilesRequestProcessor.class.getName();
  ControllerConfig ctrlConfig = config.getControllerConfig();
  String configProcessorClassname = ctrlConfig.getProcessorClass();
    // Check if it is default request processor. If true, replace by Tiles' one.
  if( configProcessorClassname.equals(RequestProcessor.class.getName())
      || configProcessorClassname.endsWith(tilesProcessorClassname) )
    {
    ctrlConfig.setProcessorClass(tilesProcessorClassname);
    return;
    }

    // Check if specified request processor is compatible with Tiles.
  try
    {
    Class tilesProcessorClass = TilesRequestProcessor.class;
    Class configProcessorClass = Class.forName( configProcessorClassname );
    if( !tilesProcessorClass.isAssignableFrom( configProcessorClass))
      {  // Not compatible
      String msg = "TilesPlugin : Specified RequestProcessor not compatible with TilesRequestProcessor";
      System.out.println( msg );
      throw new ServletException( msg );
      } // end if
    }
   catch( java.lang.ClassNotFoundException ex )
    {
    throw new ServletException( ex );
    }
  }

}
