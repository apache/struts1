/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/xmlDefinition/XmlListAttribute.java,v 1.2 2003/02/27 19:19:42 cedric Exp $
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

import java.util.List;
import java.util.ArrayList;


/**
 * An attribute as a <code>List</code>.
 * This attribute associates a name with a list. The list can be found by the
 * property name.
 * Elements in list are retrieved using List methods.
 * This class is used to read configuration files.
 */
public class XmlListAttribute extends XmlAttribute
{
    /** List.
     * We declare a List to avoid cast.
     * Parent "value" property points to the same list.
     */
  private List list;

    /**
     * Constructor.
     */
  public XmlListAttribute()
    {
    list = new ArrayList();
    setValue(list);
    }

    /**
     * Constructor.
     * @param name Name.
     * @param value List.
     */
  public XmlListAttribute( String name, List value)
    {
    super( name, value );
    list = value;
    }

    /**
     * Add an element in list.
     * We use a property to avoid rewriting a new class.
     * @param element XmlAttribute to add.
     */
  public void add( XmlAttribute element )
    {
    list.add( element.getValue() );
    }

    /**
     * Add an element in list.
     * @param value Object to add.
     */
  public void add( Object value )
    {
    //list.add( value );
      // To correct a bug in digester, we need to check the object type
      // Digester doesn't call correct method according to object type ;-(
    if(value instanceof XmlAttribute)
      {
      add((XmlAttribute)value);
      return;
      }
     else
      list.add( value );
    }

    /**
     * Add an element in list.
     * @param value Object to add.
     */
  public void addObject( Object value )
    {
    list.add( value );
    }



}
