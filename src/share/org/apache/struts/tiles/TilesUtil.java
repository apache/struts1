/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/TilesUtil.java,v 1.8 2003/04/22 02:28:52 dgraham Exp $
 * $Revision: 1.8 $
 * $Date: 2003/04/22 02:28:52 $
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

package org.apache.struts.tiles;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

  /**
   * Class containing utility methods for Tiles.
   * Methods of this class are static and thereby accessible from anywhere.
   * The underlying implementation can be changed with
   * {@link #setTilesUtil(TilesUtilImpl)}.
   * <br>
   * Real implementation classes should derive from the {@link TilesUtilImpl} class.
   * <br>
   * Some methods are specified to throw the <code>UnsupportedOperationException</code>
   * if the underlying implementation doesn't support the operation.
   */
public class TilesUtil
{
     /** Commons Logging instance.*/
  protected static Log log = LogFactory.getLog(TilesUtil.class);

    /** The implementation of tilesUtilImpl */
  protected static TilesUtilImpl tilesUtilImpl = new TilesUtilImpl();

    /**
     * Get the real implementation.
     * @return The underlying implementation object.
     */
  static public TilesUtilImpl getTilesUtil()
  {
  return tilesUtilImpl;
  }

    /**
     * Set the real implementation.
     * This method should be called only once.
     * Successive calls have no effect.
     * @param tilesUtil The implementaion.
     */
  static public void setTilesUtil(TilesUtilImpl tilesUtil)
  {
  if( implAlreadySet)
    return;
  tilesUtilImpl = tilesUtil;
  implAlreadySet = true;
  }

    /**
     * Getter to know if the underlying implementation is already set to another
     * value than the default value.
     * @return <code>true</code> if {@link #setTilesUtil} has already been called.
     */
  static boolean isTilesUtilImplSet()
  {
  return implAlreadySet;
  }


    /** Flag to know if internal implementation has been set by the setter method */
  private static boolean implAlreadySet=false;

    /**
     * Do a forward using request dispatcher.
     *
     * This method is used by the Tiles package anytime a forward is required.
     * @param uri Uri or Definition name to forward.
     * @param request Current page request.
     * @param response Current page response.
     * @param servletContext Current servlet context.
     */
  public static void doForward(String uri, HttpServletRequest request, HttpServletResponse response,
                        ServletContext servletContext)
                            throws IOException, ServletException
  {
  tilesUtilImpl.doForward(uri, request, response, servletContext);
  }

    /**
     * Do an include using request dispatcher.
     *
     * This method is used by the Tiles package when an include is required.
     * The Tiles package can use indifferently any form of this method.
     * @param uri Uri or Definition name to forward.
     * @param request Current page request.
     * @param response Current page response.
     * @param servletContext Current servlet context.
     */
  public static void doInclude(String uri, HttpServletRequest request, HttpServletResponse response,
                        ServletContext servletContext)
        throws IOException, ServletException
  {
  tilesUtilImpl.doInclude(uri, request, response, servletContext);
  }

    /**
     * Do an include using PageContext.include().
     *
     * This method is used by the Tiles package when an include is required.
     * The Tiles package can use indifferently any form of this method.
     * @param uri Uri or Definition name to forward.
     * @param request Current page request.
     * @param response Current page response.
     * @param servletContext Current servlet context.
     */
    public static void doInclude(String uri, PageContext pageContext)
        throws IOException, ServletException {
        TilesUtilImpl.doInclude(uri, pageContext);
    }

    /**
     * Get definition factory from appropriate servlet context.
     * @return Definitions factory or <code>null</code> if not found.
     */
  static  public DefinitionsFactory getDefinitionsFactory(ServletRequest request, ServletContext servletContext)
  {
  return tilesUtilImpl.getDefinitionsFactory(request, servletContext);
  }

    /**
     * Create Definition factory from specified configuration object.
     * Create a ConfigurableDefinitionsFactory and initialize it with the configuration
     * object. This later can contain the factory classname to use.
     * Factory is made accessible from tags.
     * <p>
     * Fallback of several factory creation methods.
     *
     * @param servletContext Servlet Context passed to newly created factory.
     * @param factoryConfig Configuration object passed to factory.
     * @return newly created factory of type ConfigurableDefinitionsFactory.
     * @throws DefinitionsFactoryException If an error occur while initializing factory
     */
  public static DefinitionsFactory createDefinitionsFactory(ServletContext servletContext, DefinitionsFactoryConfig factoryConfig)
    throws DefinitionsFactoryException
  {
  return tilesUtilImpl.createDefinitionsFactory(servletContext, factoryConfig);
  }

  /**
   * Get a definition by its name.
   * First, retrieve definition factory and then get requested definition.
   * Throw appropriate exception if definition or definition factory is not found.
   * @param definitionName Name of requested definition.
   * @param request Current servelet request.
   * @param servletContext current servlet context.
   * @throws FactoryNotFoundException Can't find definition factory.
   * @throws DefinitionsFactoryException General error in factory while getting definition.
   * @throws NoSuchDefinitionException No definition found for specified name
   */
  static public ComponentDefinition getDefinition(String definitionName, ServletRequest request, ServletContext servletContext)
    throws FactoryNotFoundException, DefinitionsFactoryException
  {
  try
    {
    return getDefinitionsFactory(request, servletContext).getDefinition(definitionName,
                                                       (HttpServletRequest)request,
                                                        servletContext);
    }
   catch( NullPointerException ex )
    {  // Factory not found in context
    throw new FactoryNotFoundException( "Can't get definitions factory from context." );
    }
  }

    /**
     * Return the <code>Class</code> object for the specified fully qualified
     * class name from the underlying class loader.
     *
     * @param className Fully qualified class name to be loaded.
     * @return Class object.
     * @exception ClassNotFoundException if the class cannot be found
     * @deprecated Use RequestUtils.applicationClass() instead.
     */
    public static Class applicationClass(String className)
        throws ClassNotFoundException {
        return tilesUtilImpl.applicationClass(className);
    }


    /**
     * Reset internal state.
     * This method is used by test suites to reset the class to its original state.
     */
  protected static void testReset()
  {
  implAlreadySet = false;
  tilesUtilImpl = new TilesUtilImpl();
  }

}