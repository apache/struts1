/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/xmlDefinition/XmlDefinition.java,v 1.3 2003/02/27 19:19:42 cedric Exp $
 * $Revision: 1.3 $
 * $Date: 2003/02/27 19:19:42 $
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

import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.NoSuchDefinitionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;

/**
  *A definition read from an XML definitions file.
  */
public class XmlDefinition extends ComponentDefinition
{
  /**
   * Extends attribute value.
   */
  private String inherit;

    /** Commons Logging instance. */
   protected static Log log = LogFactory.getLog(XmlDefinition.class);

  /**
   * Used for resolving inheritance.
   */
  private boolean isVisited=false;


     /**
      * Constructor.
      */
   public XmlDefinition()
   {
   super();
   //if(debug)
     //System.out.println( "create definition" );
   }

  /**
   * Add an attribute to this component.
   *
   * @param attribute Attribute to add.
   */
  public void addAttribute( XmlAttribute attribute)
    {
    putAttribute( attribute.getName(), attribute.getValue() );
    }

  /**
   * Set extends.
   *
   * @param name Name of the extended definition.
   */
  public void setExtends(String name)
    {
    inherit = name;
    }

  /**
   * Get extends.
   *
   * @return Name of the extended definition.
   */
  public String getExtends()
    {
    return inherit;
    }

  /**
   * Get extends flag.
   *
   */
  public boolean isExtending( )
    {
    return inherit!=null;
    }

  /**
   * Set isVisited.
   *
   */
  public void setIsVisited( boolean isVisited )
    {
    this.isVisited = isVisited;
    }

    /**
     * Resolve inheritance.
     * First, resolve parent's inheritance, then set path to the parent's path.
     * Also copy attributes setted in parent, and not set in child
     * If instance doesn't extend anything, do nothing.
     * @throws NoSuchDefinitionException If an inheritance can not be solved.
     */
  public void resolveInheritance( XmlDefinitionsSet definitionsSet )
    throws NoSuchDefinitionException
    {
      // Already done, or not needed ?
    if( isVisited || !isExtending() )
      return;

    if(log.isDebugEnabled())
      log.debug( "Resolve definition for child name='" + getName()
              + "' extends='" + getExtends() + "'.");

      // Set as visited to avoid endless recurisvity.
    setIsVisited( true );

      // Resolve parent before itself.
    XmlDefinition parent = definitionsSet.getDefinition( getExtends() );
    if( parent == null )
      { // error
      String msg = "Error while resolving definition inheritance: child '"
                           + getName() +    "' can't find its ancestor '"
                           + getExtends() + "'. Please check your description file.";
      log.error( msg );
        // to do : find better exception
      throw new NoSuchDefinitionException( msg );
      }

    parent.resolveInheritance( definitionsSet );

      // Iterate on each parent's attribute and add it if not defined in child.
    Iterator parentAttributes = parent.getAttributes().keySet().iterator();
    while( parentAttributes.hasNext() )
      {
      String name = (String)parentAttributes.next();
      if( !getAttributes().containsKey(name) )
        putAttribute( name, parent.getAttribute(name) );
      }
      // Set path and role if not setted
    if( path == null )
      setPath( parent.getPath() );
    if( role == null )
      setRole( parent.getRole() );
    if( controller==null )
      {
      setController( parent.getController());
      setControllerType( parent.getControllerType());
      }
    }

  /**
   * Overload this definition with passed child.
   * All attributes from child are copied to this definition. Previous attributes with
   * same name are disguarded.
   * Special attribute 'path','role' and 'extends' are overloaded if defined in child.
   * @param child Child used to overload this definition.
   */
  public void overload( XmlDefinition child )
    {
    if( child.getPath() != null )
      {
      path = child.getPath();
      }
    if( child.getExtends() != null )
      {
      inherit = child.getExtends();
      }
    if( child.getRole() != null )
      {
      role = child.getRole();
      }
    if( child.getController()!=null )
      {
      controller = child.getController();
      controllerType =  child.getControllerType();
      }
      // put all child attributes in parent.
    attributes.putAll( child.getAttributes());
    }
}
