/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/TilesUtilStrutsImpl.java,v 1.4 2003/04/17 03:51:12 dgraham Exp $
 * $Revision: 1.4 $
 * $Date: 2003/04/17 03:51:12 $
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

import javax.servlet.ServletContext;

import org.apache.struts.util.RequestUtils;
import org.apache.struts.config.ModuleConfig;

  /**
   * TilesUtil implementation for Struts 1.1 with one single factory.
   * This class contains default implementation of utilities. This implementation
   * is intended to be used with Struts 1.1.
   * This class is used as the base class for all Struts 1.1 implementations of TilesUtil.
   */
public class TilesUtilStrutsImpl extends TilesUtilImpl
{

    /**
     * Return the <code>Class</code> object for the specified fully qualified
     * class name from the Struts class loader.
     *
     * @param className Fully qualified class name to be loaded.
     * @return Class object.
     * @exception ClassNotFoundException if the class cannot be found
     * @deprecated Use RequestUtils.applicationClass() instead.
     */
    public Class applicationClass(String className) throws ClassNotFoundException {
        return RequestUtils.applicationClass(className);
    }

    /**
     * Get definition factory for the module attached to the specified moduleConfig.
     * @param servletContext Current servlet context
     * @param moduleConfig Module config of the module for which the factory is requested.
     * @return Definitions factory or null if not found.
     */
  public DefinitionsFactory getDefinitionsFactory(ServletContext servletContext, ModuleConfig moduleConfig)
  {
  return (DefinitionsFactory)servletContext.getAttribute(DEFINITIONS_FACTORY);
  }


}