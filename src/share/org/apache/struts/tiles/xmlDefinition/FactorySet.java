/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/xmlDefinition/FactorySet.java,v 1.1 2002/06/25 03:15:43 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2002/06/25 03:15:43 $
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


package org.apache.struts.tiles.xmlDefinition;

import org.apache.struts.tiles.ComponentDefinitionsFactory;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.NoSuchDefinitionException;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.FactoryNotFoundException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletContext;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Component Definitions factory.
 * This factory contains several factories identified by a key. The
 * getDefinition() method first look for the factory key, retrieve or create this
 * factory and then call getDefinition() on the found factory.
 */
public abstract class FactorySet implements ComponentDefinitionsFactory
{

    /** Loaded factories */
  protected Map factories=null;

  /**
   * Extract key that will be used to get the sub factory.
   * @param name Name of requested definition
   * @param request Current servlet request.
   * @param servletContext Current servlet context
   */
  abstract protected Object getDefinitionsFactoryKey(String name, ServletRequest request, ServletContext servletContext);

  /**
   * Get default factory.
   * @return Default factory
   */
  abstract protected DefinitionsFactory getDefaultFactory();

  /**
   * Get a factory by its key.
   * If key is null, return defaultFactory.
   * Search in loaded factory. If not found, call create factory, and store return value in
   * loaded factories.
   * @param key Key of requested definition
   * @param request Current servlet request.
   * @param servletContext Current servlet context
   * @throws DefinitionsFactoryException If an error occur while creating factory.
   */
  protected DefinitionsFactory getFactory(Object key, ServletRequest request, ServletContext servletContext)
    throws DefinitionsFactoryException
  {
  if(key == null )
    return getDefaultFactory();

  Object factory = factories.get( key );
  if( factory == null )
    {
      // synchronize creation to avoid double creation by separate threads.
      // Also, check if factory hasn't been created while waiting for synchronized
      // section.
    synchronized(factories)
      {
      factory = factories.get( key );
      if( factory == null )
        {
        factory = createFactory( key, request, servletContext);
        factories.put( key, factory );
        } // end if
      } // end synchronized
    } // end if
  return (DefinitionsFactory)factory;
  }

  /**
   * Get a definition by its name.
   * @throws DefinitionsFactoryException An error occur while getting
   * definition.
   * @param name Name of requested definition
   * @param request Current servlet request.
   * @param servletContext Current servlet context
   * @throws NoSuchDefinitionException No definition found for specified name
   * @throws DefinitionsFactoryException General exception
   */
  public ComponentDefinition getDefinition(String name, ServletRequest request, ServletContext servletContext)
    throws NoSuchDefinitionException, DefinitionsFactoryException
  {
  if( factories == null )
    throw new FactoryNotFoundException( "No definitions factory defined" );

  Object key = getDefinitionsFactoryKey( name, request, servletContext);
  DefinitionsFactory factory = getFactory( key, request, servletContext);
  return factory.getDefinition( name, request, servletContext );
  }

  /**
   * Create a factory for specified key.
   * This method is called by getFactory() when the requested factory doesn't already exist.
   * Must return a factory, even a default one.
   * Real implementation need to provide this method.
   * @param key Key of requested definition
   * @param request Current servlet request.
   * @param servletContext Current servlet context
   * @throws DefinitionsFactoryException If an error occur while creating factory.
   */
  abstract protected DefinitionsFactory createFactory(Object key, ServletRequest request, ServletContext servletContext)
          throws DefinitionsFactoryException;

  /**
   * Init factory set.
   * @param servletContext Current servlet context
   * @param properties properties used to initialized factory set;
   */
  abstract public void initFactory(ServletContext servletContext, Map properties)
    throws DefinitionsFactoryException;

  /**
   *
   */
  public FactorySet()
  {
  factories = new HashMap();
  }

    /**
     *
     */
  public String toString()
    {
    Iterator i = factories.values().iterator();
    StringBuffer buff = new StringBuffer( "all FactorySet's factory : \n" );
    while( i.hasNext() )
      {
      buff.append( i.next().toString() ).append("\n");
      }
    return buff.toString();
    }

}
