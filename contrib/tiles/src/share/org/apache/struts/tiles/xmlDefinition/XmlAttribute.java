/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/xmlDefinition/Attic/XmlAttribute.java,v 1.3 2001/10/29 17:32:24 cedric Exp $
 * $Revision: 1.3 $
 * $Date: 2001/10/29 17:32:24 $
 * $Author: cedric $
 *
 */


package org.apache.struts.tiles.xmlDefinition;

import org.apache.struts.tiles.AttributeDefinition;
import org.apache.struts.tiles.DirectStringAttribute;
import org.apache.struts.tiles.PathAttribute;
import org.apache.struts.tiles.DefinitionAttribute;
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
    //System.out.println("body set to'" + body + "'");
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
