/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/Attic/DefaultTilesUtilImpl.java,v 1.1 2002/11/05 14:16:41 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2002/11/05 14:16:41 $
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

import org.apache.struts.tiles.definition.ComponentDefinitionsFactoryWrapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

  /**
   * Default implementation of TilesUtil.
   * This class conatains default implementation of utilities. This implementation
   * is intended to be used without Struts
   */
public class DefaultTilesUtilImpl implements TilesUtilInterface
{
     /** Commons Logging instance.*/
  protected Log log = LogFactory.getLog(DefaultTilesUtilImpl.class);

    /** Constant name used to store factory in servlet context */
  public static final String DEFINITIONS_FACTORY = "org.apache.struts.tiles.DEFINITIONS_FACTORY";

    /**
     * Do a forward using request dispatcher.
     *
     * This method is used by the Tiles package anytime a forward is required.
     * @param uri Uri or Definition name to forward
     * @param request Current page request
     * @param servletContext Current servlet context
     */
  public void doForward(String uri, HttpServletRequest request, HttpServletResponse response,
                        ServletContext servletContext)
    throws IOException, ServletException
  {
  request.getRequestDispatcher( uri ).forward(request, response);
  }

    /**
     * Do an include using request dispatcher.
     *
     * This method is used by the Tiles package anytime an include is required.
     * @param uri Uri or Definition name to forward
     * @param request Current page request
     * @param response Current page response
     * @param servletContext Current servlet context
     */
  public void doInclude(String uri, HttpServletRequest request, HttpServletResponse response,
                        ServletContext servletContext)
    throws IOException, ServletException
  {
  request.getRequestDispatcher( uri ).include(request, response);
  }

    /**
     * Get the default definition factory from appropriate servlet context.
     * @param servletContext Current servlet context
     * @return Definitions factory or null if not found.
     */
  public DefinitionsFactory getDefaultDefinitionsFactory(ServletContext servletContext)
  {
  return (DefinitionsFactory)servletContext.getAttribute(DEFINITIONS_FACTORY);
  }

    /**
     * Get definition factory from appropriate servlet context.
     * @return Definitions factory or null if not found.
     */
  public DefinitionsFactory getDefinitionsFactory(ServletRequest request, ServletContext servletContext)
  {
  return (DefinitionsFactory)servletContext.getAttribute(DEFINITIONS_FACTORY);
  }

    /**
     * Create Definition factory from specified configuration object.
     * Create an instance of the factory , with the class specified in the config
     * object. Then, initialize this factory, and finally store the factory in
     * appropriate context by the way of
     * {@link #makeDefinitionsFactoryAccessible(DefinitionsFactory, ServletContext)}.
     * Factory creation is done by the way of {@link #createDefinitionFactoryInstance(String)}
     * <p>
     *
     * @param servletContext Servlet Context passed to newly created factory.
     * @param factoryConfig Configuration object passed to factory.
     * @return newly created factory of type specified in the config object.
     * @throws DefinitionsFactoryException If an error occur while initializing factory
     */
  public DefinitionsFactory createDefinitionsFactory(ServletContext servletContext, DefinitionsFactoryConfig factoryConfig)
    throws DefinitionsFactoryException
  {
      // Create configurable factory
    DefinitionsFactory factory = createDefinitionFactoryInstance(factoryConfig.getFactoryClassname());
    factory.init( factoryConfig, servletContext );
      // Make factory accessible from jsp tags (push it in appropriate context)
    makeDefinitionsFactoryAccessible(factory, servletContext );
    return factory;
  }

  /**
   * Create Definition factory of specified classname.
   * Factory class must extends the {@link DefinitionsFactory} class.
   * The factory is wrapped appropriately with {@link ComponentDefinitionsFactoryWrapper}
   * if it is instance of the deprecated ComponentDefinitionsFactory class.
   * @param classname Class name of the factory to create.
   * @return newly created factory.
   * @throws DefinitionsFactoryException If an error occur while initializing factory
   */
  protected DefinitionsFactory createDefinitionFactoryInstance(String classname)
    throws DefinitionsFactoryException
  {
  try
    {
    Class factoryClass = Class.forName(classname);
    Object factory = factoryClass.newInstance();

      // Backward compatibility : if factory classes implements old interface,
      // provide appropriate wrapper
    if( factory instanceof ComponentDefinitionsFactory )
      {
      factory = new ComponentDefinitionsFactoryWrapper( (ComponentDefinitionsFactory)factory );
      } // end if
    return (DefinitionsFactory)factory;
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
   * Make definition factory accessible to Tags.
   * Factory is stored in servlet context.
   * @param factory Factory to make accessible
   * @param servletContext Current servlet context
   */
 protected void makeDefinitionsFactoryAccessible(DefinitionsFactory factory, ServletContext servletContext)
  {
  servletContext.setAttribute(DEFINITIONS_FACTORY, factory);
  }

}