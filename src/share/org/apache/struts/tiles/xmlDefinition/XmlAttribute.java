/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/xmlDefinition/XmlAttribute.java,v 1.2 2002/10/10 16:32:26 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2002/10/10 16:32:26 $
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

import org.apache.struts.tiles.DirectStringAttribute;
import org.apache.struts.tiles.PathAttribute;
import org.apache.struts.tiles.DefinitionNameAttribute;
import org.apache.struts.tiles.UntyppedAttribute;

/**
 * A property key-value pair.
 * This class is used to read configuration files.
 */
public class XmlAttribute
{

  /**
   * Attribute name or key.
   */
  private String name;

  /**
   * Attribute value.
   * Value read from description file.
   */
  private Object value;

  /**
   * Attribute value.
   */
  private String direct=null;
  /**
   * Attribute value.
   */
  private String valueType=null;
  /**
   * Attribute value.
   */
  private String role=null;

  /**
   * Real attribute value.
   * Real value is the value after processing of valueType.
   * I.e. if a type is defined, realValue contains wrapper for this type.
   */
  private Object realValue;

    /**
     * Constructor.
     */
  public XmlAttribute()
    {
    }

    /**
     * Constructor.
     */
  public XmlAttribute( String name, Object value)
    {
    this.name = name;
    this.value = value;
    }

  /**
   * Access method for the name property.
   *
   * @return   the current value of the name property
   */
  public String getName()
    {
    return name;
    }

  /**
   * @return void
   * Sets the value of the name property.
   *
   * @param aName the new value of the name property
   */
  public void setRole(String role)
    {
    this.role = role;
    }

  /**
   * Access method for the name property.
   *
   * @return   the current value of the name property
   */
  public String getRole()
    {
    return role;
    }

  /**
   * @return void
   * Sets the value of the name property.
   *
   * @param aName the new value of the name property
   */
  public void setName(String aName)
    {
    name = aName;
    }

  /**
   * Another access method for the name property.
   *
   * @return   the current value of the name property
   */
  public String getAttribute()
    {
    return name;
    }

  /**
   * Sets the value of the name property.
   *
   * @param aName the new value of the name property
   */
  public void setAttribute(String aName)
    {
    name = aName;
    }

  /**
    * Access method for the value property.
    * Return the value or a QualifiedAttribute containing the value
    * if 'direct' is set.
    *
   * @return   the current value of the value property
   */
  public Object getValue()
    {
      // Compatibility with JSP Template
    if( realValue ==null )
      realValue = computeRealValue();
    return realValue;
    }

  /**
    * Sets the value of the value property.
   *
   * @param aValue the new value of the value property
   */
  public void setValue(Object aValue)
    {
    realValue=null;
    value = aValue;
    }

  /**
    * Sets the value of the value property.
   *
   * @param aValue the new value of the value property
   */
  public void setContent(Object aValue)
    {
    setValue(aValue);
    }
  /**
    * Sets the value of the value property.
   *
   * @param aValue the new value of the value property
   */
  public void setBody(String body)
    {
    if( body.length() == 0 )
      return;
    setValue(body);
    }
  /**
    * Sets the value of the value property.
   *
   * @param aValue the new value of the value property
   */
  public void setDirect(String value)
    {
    this.direct = value;
    }
  /**
    * Sets the value of the value property.
   *
   * @param aValue the new value of the value property
   */
  public void setType(String value)
    {
    this.valueType = value;
    }

    /**
     * Compute  real value from attributes setting.
     */
  protected Object computeRealValue()
    {
    Object realValue = value;
      // Is there a type set ?
      // First check direct attribute, and translate it to a valueType.
      // Then, evaluate valueType, and create requested typed attribute.
    if( direct != null )
      {
      if( Boolean.valueOf(direct).booleanValue() == true )
        valueType = "string";
       else
        valueType = "path";
      }  // end if
    if( value != null && valueType!=null /* && !(value instanceof AttributeDefinition) */ )
      {
      String strValue = value.toString();
        if( valueType.equalsIgnoreCase( "string" ) )
          {
          realValue = new DirectStringAttribute( strValue );
          }
         else if( valueType.equalsIgnoreCase( "page" ) )
          {
          realValue = new PathAttribute( strValue );
          }
         else if( valueType.equalsIgnoreCase( "template" ) )
          {
          realValue = new PathAttribute( strValue );
          }
         else if( valueType.equalsIgnoreCase( "instance" ) )
          {
          realValue = new DefinitionNameAttribute( strValue );
          }  // end if
        // Set realValue's role value if needed
      if( role !=null )
        ((UntyppedAttribute)realValue).setRole( role );
      } //  end  if

      // Create attribute wrapper to hold role if role is set and no type specified
    if( role!=null && value != null && valueType==null )
      {
      realValue = new UntyppedAttribute( value.toString(), role );
      } // end if

    return realValue;
    }
}
