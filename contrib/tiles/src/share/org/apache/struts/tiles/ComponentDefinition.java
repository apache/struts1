/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/ComponentDefinition.java,v 1.1 2001/08/01 14:36:42 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:42 $
 * $Author: cedric $
 *
 */


package org.apache.struts.tiles;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;

/**
 * Definition of a template / component attributes.
 * Attributes of a component can be defined with the help of this class.
 * An instance of this class can be used as a bean, and passed to 'insert' tag.
 */
public class ComponentDefinition implements Serializable
{
  /**
   * Instance name
   */
  protected String name;

  /**
   * Component / template path (URL).
   */
  protected String path;

  /**
   * Attributes defined for the component.
   */
  protected Map attributes;
  /** role associated to definition */
  protected String role;

  /**
   * @return void
   * Sets the value of the attributes property.
   *
   * @param aAttributes the new value of the attributes property
   * @deprecated No replacement
   */
  private void setAttributes(Map aAttributes)
    {
    attributes = aAttributes;
    }

  /**
   * Constructor.
   */
  public ComponentDefinition()
  {
  attributes = new HashMap();
  }

  /**
   * Copy Constructor.
   * Create a new definition initialized with parent definition.
   * Do a shallow copy : attributes are shared between copies, but not the Map
   * containing attributes.
   */
  public ComponentDefinition( ComponentDefinition definition )
  {
  attributes = new HashMap( definition.getAttributes() );
  this.name = definition.getName();
  this.path = definition.getPath();
  this.role = definition.getRole();
  }

  /**
   * Constructor.
   */
  public ComponentDefinition( String name, String path, Map attributes )
  {
  this.name = name;
  this.path = path;
  this.attributes = attributes;
  }

  /**
* Access method for the name property.
*
* @return   the current value of the name property
   */
  public String getName() {
    return name;}

  /**
   * @return void
* Sets the value of the name property.
*
* @param aName the new value of the name property
   */
  public void setName(String aName) {
    name = aName;}

  /**
   * Access method for the path property.
   *
   * @return   the current value of the path property
   */
  public String getPath()
    {
    return path;
    }

  /**
   * @return void
   * Sets the value of the path property.
   *
   * @param aPath the new value of the path property
   */
  public void setPath(String aPath)
    {
    path = aPath;
    }

  /**
   * Access method for the template property.
   * Same as getPath()
   * @return   the current value of the template property
   */
  public String getTemplate()
    {
    return path;
    }

  /**
   * @return void
   * Sets the value of the template property.
   * Same as setPath()
   *
   * @param template the new value of the path property
   */
  public void setTemplate(String template)
    {
    path = template;
    }

  /**
   * Access method for the role property.
   * @return   the current value of the template property
   */
  public String getRole()
    {
    return role;
    }

  /**
   * @return void
   * Sets the value of the role property.
   *
   * @param template the new value of the path property
   */
  public void setRole(String role)
    {
    this.role = role;
    }

  /**
   * Access method for the attributes property.
   * If there is no attributes, return an empty map.
   * @return   the current value of the attributes property
   */
  public Map getAttributes()
    {
    return attributes;
    }

  /**
   * Returns the value of the named attribute as an Object, or null if no
   * attribute of the given name exists.
   *
   * @return   requested attribute or null if not found
   */
  public Object getAttribute(String key)
    {
    return attributes.get( key);
    }

  /**
   * Put a new attribute in this component
   *
   * @param key String key for attribute
   * @param value Attibute value.
   */
  public void putAttribute(String key, Object value)
    {
    attributes.put( key, value );
    }

  /**
   * Put an attribute in component / template definition.
   * Attribute can be used as content for tag get.
   * @param name Attribute name
   * @param content Attribute value
   * @roseuid 3AAE7EB602EA
   */
  public void put(String name, Object content)
  {
  put(name, content, false, null );
  }

  /**
   * Put an attribute in template definition.
   * Attribute can be used as content for tag get.
   * @param name Attribute name
   * @param content Attribute value µ
   * @param direct Determines how content is handled by get tag: true means content is printed directly; false, the default, means content is included
   * @roseuid 3AAE7EB80275
   */
  public void put(String name, Object content, boolean direct)
  {
  put(name, content, direct, null );
  }

  /**
   * Put an attribute in template definition.
   * Attribute can be used as content for tag get.
   * @param name Attribute name
   * @param content Attribute value
   * @param direct Determines how content is handled by get tag: true means content is printed directly; false, the default, means content is included
   * @param role Determine if content is used by get tag. If user is in role, content is used.
   * @roseuid 3AAE7EBA02AA
   */
  public void put(String name, Object content, boolean direct, String role)
  {
  if( direct == true )
    { // direct String
    put( name, content, "string", role );
    }
   else
    {
    put( name, content, "template", role );
    } // end if

  }

  /**
   * Put an attribute in template definition.
   * Attribute can be used as content for tag get.
   * @param name Attribute name
   * @param content Attribute value
   * @param type attribute type: template, string, definition
   * @param role Determine if content is used by get tag. If user is in role, content is used.
   * @roseuid 3AAE7EBA02AA
   */
  public void put(String name, Object content, String type, String role)
  {
      // Is there a type set ?
      // First check direct attribute, and translate it to a valueType.
      // Then, evaluate valueType, and create requested typed attribute.
    AttributeDefinition attribute = null;

    if( content != null && type!=null && !(content instanceof AttributeDefinition) )
      {
      String strValue = content.toString();
        if( type.equalsIgnoreCase( "string" ) )
          {
          attribute = new DirectStringAttribute( strValue );
          }
         else if( type.equalsIgnoreCase( "page" ) )
          {
          attribute = new PathAttribute( strValue );
          }
         else if( type.equalsIgnoreCase( "template" ) )
          {
          attribute = new PathAttribute( strValue );
          }
         else if( type.equalsIgnoreCase( "instance" ) )
          {
          attribute = new DefinitionNameAttribute( strValue );
          }
         else if( type.equalsIgnoreCase( "definition" ) )
          {
          attribute = new DefinitionNameAttribute( strValue );
          }  // end if
      } //  end  if
  putAttribute( name, attribute);
  }

    /**
     *
     */
  public String toString()
    {
    return "{name="+ name +
           ", path="+ path +
           ", role="+ role +
           ", attributes=" + attributes + "}\n";
    }

}
