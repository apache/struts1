/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/DefinitionsFactory.java,v 1.2 2002/11/16 06:04:28 jmitchell Exp $
 * $Revision: 1.2 $
 * $Date: 2002/11/16 06:04:28 $
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

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

/**
 * Tiles Definition factory.
 * This interface replace old ComponentDefinitionsFactory.
 * Main method getDefinition() is exactly the same. Initialization method change.
 * This interface allows to retrieve a definition by its name, independently of
 * the factory implementation.
 * Object life cycle is as follow:
 * <ul>
 * <li>Constructor: create object</li>
 * <li>setConfig: set config and initialize factory. After first call to this
 * method, factory is operational.</li>
 * <li>destroy: factory is being shutdown.</li>
 * </ul>
 * Implementation must be Serializable, in order to be compliant with web Container
 * having this constraint (Weblogic 6.x).
 */
public interface DefinitionsFactory extends Serializable
{

   /**
     * Get a definition by its name.
     * @param name Name of requested definition.
     * @param request Current servelet request
     * @param servletContext current servlet context
     * @throws DefinitionsFactoryException An error occur while getting definition.
     * @throws NoSuchDefinitionException No definition found for specified name
     * Implementation can throw more accurate exception as a subclass of this exception
   */
   public ComponentDefinition getDefinition(String name, ServletRequest request, ServletContext servletContext)
     throws NoSuchDefinitionException,DefinitionsFactoryException;

   /**
    * Init definition factory.
    * This method is called immediately after factory creation, and prior any call
    * to setConfig().
    *
    * @param config Configuration object used to set factory configuration.
    * @param servletContext Servlet Context passed to factory.
    * @throws DefinitionsFactoryException An error occur during initialization.
    */
   public void init(DefinitionsFactoryConfig config, ServletContext servletContext)
     throws DefinitionsFactoryException;

    /**
     * <p>Receive notification that the factory is being
     * shut down.</p>
     */
    public void destroy();

   /**
    * Set factory configuration.
    * This method is used to change factory configuration.
    * This method is optional, and can send an exception if implementation
    * doesn't allow change in configuration.
    *
    * @param config Configuration object used to set factory configuration.
    * @param servletContext Servlet Context passed to factory.
    * @throws DefinitionsFactoryException An error occur during initialization.
    */
   public void setConfig(DefinitionsFactoryConfig config, ServletContext servletContext)
     throws DefinitionsFactoryException;

   /**
    * Get factory configuration.
    * @return TilesConfig
    */
   public DefinitionsFactoryConfig getConfig();


}
