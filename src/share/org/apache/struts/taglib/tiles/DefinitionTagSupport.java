/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/tiles/DefinitionTagSupport.java,v 1.3 2003/02/27 19:18:55 cedric Exp $
 * $Revision: 1.3 $
 * $Date: 2003/02/27 19:18:55 $
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


package org.apache.struts.taglib.tiles;

import java.io.Serializable;

import javax.servlet.jsp.tagext.TagSupport;

  /**
   * Common base class for tags dealing with Tiles definitions.
   * This class defines properties used in Definition Tags.
   * It also extends TagSupport.
   */
public class DefinitionTagSupport extends TagSupport implements Serializable
{
    /** Associated Controller type */
  protected String controllerType;
    /** Associated Controller name (classname or url) */
  protected String controllerName;

  /**
   * Role associated to definition.
   */
  protected String role;

  /**
   * Uri of page assoicated to this definition.
   */
  protected String page;

    /**
     * Release class properties.
     */
  public void release()
  {
  super.release();
  controllerType = null;
  controllerName = null;
  role = null;
  page = null;
  }

  /**
   * Get controller type.
   * Type can be 'classname', 'url'.
   * @return Controller type.
   */
  public String getControllerType()
  {
  return controllerType;
  }

  /**
   * Get controller name.
   * Name denotes a fully qualified classname, or an url.
   * Exact type can be specified with {@link #setControllerType}.
   * @return Controller name.
   */
  public String getControllerName()
  {
  return controllerName;
  }

  /**
   * Set associated controller type.
   * Type denotes a fully qualified classname.
   * @param controllerType Type of associated controller.
   */
  public void setControllerType(String controllerType)
  {
  this.controllerType = controllerType;
  }

  /**
   * Set associated controller name.
   * Name denotes a fully qualified classname, or an url.
   * Exact type can be specified with {@link #setControllerType}.
   * @param controller Controller classname or url.
   */
  public void setController(String controller)
  {
  setControllerName(controller); ;
  }

  /**
   * Set associated controller name.
   * Name denote a fully qualified classname, or an url.
   * Exact type can be specified with setControllerType.
   * @param controller Controller classname or url
   */
  public void setControllerName(String controller)
  {
  this.controllerName = controller;
  }

  /**
   * Set associated controller name as an url, and controller
   * type as "url".
   * Name must be an url (not checked).
   * Convenience method.
   * @param controller Controller url
   */
  public void setControllerUrl(String controller)
  {
  setControllerName( controller);
  setControllerType( "url" );
  }

  /**
   * Set associated controller name as a classtype and controller
   * type as "classname".
   * Name denotes a fully qualified classname.
   * Convenience method.
   * @param controller Controller classname.
   */
  public void setControllerClass(String controller)
  {
  setControllerName( controller);
  setControllerType( "classname" );
  }

  /**
   * Get associated role.
   * @return Associated role.
   */
  public String getRole()
    {
    return role;
    }

  /**
   * Set associated role.
   * @param role Associated role.
   */
  public void setRole(String role)
    {
    this.role = role;
    }

  /**
   * Set the page.
   * @param page Page.
   */
  public void setPage(String page)
  {
  this.page = page;
  }

  /**
   * Get the page.
   * @return Page.
   */
  public String getPage()
  {
  return page;
  }

  /**
   * Get the template.
   * Same as getPage().
   * @return Template.
   */
  public String getTemplate()
  {
  return page;
  }

  /**
   * Set the template.
   * Same as setPage().
   * @param template Template.
   */
  public void setTemplate(String template)
  {
  this.page = template;
  }
}
