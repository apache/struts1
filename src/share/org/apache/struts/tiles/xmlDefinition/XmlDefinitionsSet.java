/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/xmlDefinition/XmlDefinitionsSet.java,v 1.2 2003/02/27 19:19:42 cedric Exp $
 * $Revision: 1.2 $
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

import org.apache.struts.tiles.NoSuchDefinitionException;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A set of definitions read from XML definitions file.
*/
public class XmlDefinitionsSet
{
    /** Defined definitions. */
  protected Map definitions;

     /**
      * Constructor.
      */
  public XmlDefinitionsSet()
   {
   definitions = new HashMap();
   }

  /**
   * Put definition in set.
   * @param definition Definition to add.
   */
  public void putDefinition(XmlDefinition definition)
  {
  definitions.put( definition.getName(), definition );
  }

  /**
   * Get requested definition.
   * @param name Definition name.
   */
  public XmlDefinition getDefinition(String name)
  {
  return (XmlDefinition)definitions.get( name );
  }

  /**
   * Get definitions map.
   */
  public Map getDefinitions()
  {
  return definitions;
  }

  /**
   * Resolve extended instances.
   */
  public void resolveInheritances() throws NoSuchDefinitionException
    {
      // Walk through all definitions and resolve individual inheritance
    Iterator i = definitions.values().iterator();
    while( i.hasNext() )
      {
      XmlDefinition definition = (XmlDefinition)i.next();
      definition.resolveInheritance( this );
      }  // end loop
    }

  /**
   * Add definitions from specified child definitions set.
   * For each definition in child, look if it already exists in this set.
   * If not, add it, if yes, overload parent's definition with child definition.
   * @param child Definition used to overload this object.
   */
  public void extend( XmlDefinitionsSet child )
    {
    if(child==null)
      return;
    Iterator i = child.getDefinitions().values().iterator();
    while( i.hasNext() )
      {
      XmlDefinition childInstance = (XmlDefinition)i.next();
      XmlDefinition parentInstance = getDefinition(childInstance.getName() );
      if( parentInstance != null )
        {
        parentInstance.overload( childInstance );
        }
       else
        putDefinition( childInstance );
      } // end loop
    }
    /**
     * Get String representation.
     */
  public String toString()
    {
    return "definitions=" + definitions.toString() ;
    }

}
