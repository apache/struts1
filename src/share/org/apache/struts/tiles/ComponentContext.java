/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/ComponentContext.java,v 1.2 2003/02/27 19:20:51 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2003/02/27 19:20:51 $
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

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;
import org.apache.struts.taglib.tiles.ComponentConstants;
import java.io.Serializable;

/**
 * Component context.
 */
public class ComponentContext implements Serializable
{

  /**
   * Component attributes.
   */
  private Map attributes;

  /**
   * EmptyIterator over component attributes.
   */
  private static Iterator EMPTY_ITERATOR = new EmptyIterator();

    /**
     * Constructor.
     */
  public ComponentContext()
    {
    }

    /**
     * Constructor.
     * @deprecated Use {@link #ComponentContext(Map attributes)} instead.
     */
  public ComponentContext( ComponentDefinition instance )
    {
//    try
//      {
        // instance's attributes map is never null.
      attributes = new HashMap(instance.getAttributes());
//      }
//     catch( NullPointerException ex )
//      { // no attributes in instance : silently fail.
//      }
    }

    /**
     * Constructor.
     * Create a context and set specified attributes.
     * @param attributes Attributes to initialize context.
     */
  public ComponentContext( Map attributes )
    {
    if( attributes != null )
      this.attributes = new HashMap(attributes);
    }

  /**
   * Add all attributes to this context.
   * Copies all of the mappings from the specified attribute map to this context.
   * New attribute mappings will replace any mappings that this context had for any of the keys
   * currently in the specified attribute map.
   * @param newAttributes Attributes to add.
   */
  public void addAll(Map newAttributes)
  {
  if( attributes == null )
    {
    attributes = new HashMap(newAttributes);
    return;
    }
  attributes.putAll( newAttributes );
  }

  /**
   * Add all missing attributes to this context.
   * Copies all of the mappings from the specified attributes map to this context.
   * New attribute mappings will be added only if they don't already exist in
   * this context.
   * @param defaultAttributes Attributes to add.
   */
  public void addMissing(Map defaultAttributes)
  {
  if( defaultAttributes == null )
    return;
  if( attributes == null )
    {
    attributes = new HashMap(defaultAttributes);
    return;
    }

  Set entries = defaultAttributes.entrySet();
  Iterator iterator = entries.iterator();
  while( iterator.hasNext() )
    {
    Map.Entry entry = (Map.Entry)iterator.next();
    if( !attributes.containsKey( entry.getKey()) )
      {
      attributes.put(entry.getKey(), entry.getValue());
      } // end if
    } // end loop
  }

  /**
   * Get an attribute from context.
   * @param name Name of the attribute.
   * @return <{Object}>
   */
  public Object getAttribute(String name)
  {
  if( attributes == null )
    return null;
  return attributes.get( name );
  }

  /**
   * Get names of all attributes.
   * @return <{Object}>
   */
  public Iterator getAttributeNames()
  {
  if( attributes == null )
    return EMPTY_ITERATOR;
  return attributes.keySet().iterator();
  }

  /**
   * Put a new attribute to context.
   * @param name Name of the attribute.
   * @param value Value of the attribute.
   */
  public void putAttribute(String name, Object value)
  {
  if( attributes == null )
    attributes = new HashMap();

  attributes.put( name, value );
  }

    /**
     * Find object in one of the contexts.
     * Order : component then pageContext.findAttribute()
     * @param beanName Name of the bean to find.
     * @param pageContext Page context.
     * @return Requested bean or <code>null</code> if not found.
     */

    public Object findAttribute( String beanName, PageContext pageContext)
      {
      Object attribute = getAttribute(beanName);
      if( attribute == null )
        {
        attribute =  pageContext.findAttribute( beanName );
        } // end if
      return attribute;
      }

    /**
     * Get object from requested context.
     * Context can be 'component'.
     * @param beanName Name of the bean to find.
     * @param scope Search scope (see {@link PageContext}).
     * @param pageContext Page context.
     * @return requested bean or <code>null</code> if not found.
     */

    public Object getAttribute( String beanName, int scope, PageContext pageContext)
      {
      if(scope == ComponentConstants.COMPONENT_SCOPE )
        return getAttribute(beanName);
      return  pageContext.getAttribute( beanName, scope );
      }

    /**
     * Get component context from request.
     * @param request ServletRequest.
     * @return ComponentContext
     */
  static public ComponentContext getContext( ServletRequest request )
    {
    return (ComponentContext)request.getAttribute(ComponentConstants.COMPONENT_CONTEXT);
    }

    /**
     * Store component context into request.
     * @param context ComponentContext to store.
     * @param request Request to store ComponentContext.
     */
  static public void setContext( ComponentContext context, ServletRequest request )
    {
    request.setAttribute(ComponentConstants.COMPONENT_CONTEXT, context);
    }
 }

class EmptyIterator  implements Iterator {

  public boolean hasNext()
  {
   return false;
  }

  public Object next()
  {
  throw new java.util.NoSuchElementException();
  }

  public void remove()
  {
  throw new UnsupportedOperationException();
  }
 } // end inner class


