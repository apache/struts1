/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/ComponentDefinition.java,v 1.7 2003/04/17 03:51:12 dgraham Exp $
 * $Revision: 1.7 $
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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.RequestUtils;

/**
 * Definition of a template / component attributes.
 * Attributes of a component can be defined with the help of this class.
 * An instance of this class can be used as a bean, and passed to 'insert' tag.
 */
public class ComponentDefinition implements Serializable
{
    /** Commons Logging instance. */
  protected static Log log = LogFactory.getLog(ComponentDefinition.class);

  /**
   * Definition name
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

    /** Associated Controller URL or classname, if defined */
  protected String controller;
    /** Associated Controller typename, if controllerName defined.
     *  Can be CONTROLLER, ACTION or URL, or null */
  protected String controllerType;

    /** Controller name type */
  public static final String URL = "url";
    /** Controller name type */
  public static final String CONTROLLER = "controller";
    /** Controller name type */
  public static final String ACTION = "action";

  /**
   * Controller associated to Definition.
   * Lazy creation : only on first request
   */
  private Controller controllerInstance;

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
  this.controllerInstance = definition.getControllerInstance();
  this.controller = definition.getController();
  this.controllerType = definition.getControllerType();
  }

    /**
   * Constructor.
   * Create a new definition initialized from a RawDefinition.
   * Raw definitions are used to read definition from a data source (xml file, db, ...).
   * A RawDefinition mainly contains properties of type String, while Definition
   * contains more complex type (ex : Controller).
   * Do a shallow copy : attributes are shared between objects, but not the Map
   * containing attributes.
   * OO Design issues : Actually RawDefinition (XmlDefinition) extends ComponentDefinition.
   * This must not be the case. I have do it because I am lazy.
    * @throws InstantiationException if an error occur while instanciating Controller :
    * (classname can't be instanciated, Illegal access with instanciated class,
    * Error while instanciating class, classname can't be instanciated.
   */
  public ComponentDefinition( org.apache.struts.tiles.xmlDefinition.XmlDefinition definition )
  {
  this((ComponentDefinition)definition);
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
  public String getPage()
    {
    return path;
    }

  /**
   * Sets the value of the path property.
   *
   * @param aPath the new value of the path property
   */
  public void setPage(String page)
    {
    path = page;
    }

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
   * @return   the current value of the role property
   */
  public String getRole()
    {
    return role;
    }

  /**
   * Sets the value of the role property.
   *
   * @param role the new value of the path property
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
           ", controller="+ controller +
           ", controllerType="+ controllerType +
           ", controllerInstance="+ controllerInstance +
           ", attributes=" + attributes + "}\n";
    }

  /**
   * Get associated controller type.
   * Type denote a fully qualified classname.
   */
  public String getControllerType()
  {
  return controllerType;
  }

  /**
   * Set associated controller type.
   * Type denote a fully qualified classname.
   * @param controllerType Typeof associated controller
   */
  public void setControllerType(String controllerType)
  {
  this.controllerType = controllerType;
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
  setController( controller);
  setControllerType( "url" );
  }

  /**
   * Set associated controller name as a classtype, and controller
   * type as "classname".
   * Name denote a fully qualified classname
   * Convenience method.
   * @param controller Controller classname.
   */
  public void setControllerClass(String controller)
  {
  setController( controller);
  setControllerType( "classname" );
  }

  /**
   * Get associated controller local URL.
   * URL should be local to webcontainer in order to allow request context followup.
   * URL comes as a string.
   */
  public String getController()
  {
  return controller;
  }

  /**
   * Set associated controller URL.
   * URL should be local to webcontainer in order to allow request context followup.
   * URL is specified as a string.
   * @param url Url called locally
   */
  public void setController(String url)
  {
  this.controller = url;
  }

    /**
     * Get controller instance.
     * @return controller instance.
     */
  public Controller getControllerInstance()
    {
    return controllerInstance;
    }

    /**
     * Get or create controller.
     * Get controller, create it if necessary.
     * @return controller if controller or controllerType is set, null otherwise.
     * @throws InstantiationException if an error occur while instanciating Controller :
     * (classname can't be instanciated, Illegal access with instanciated class,
     * Error while instanciating class, classname can't be instanciated.
     */
  public Controller getOrCreateController()
    throws InstantiationException
    {
    if( controllerInstance != null )
      return controllerInstance;

      // Do we define a controller ?
    if( controller == null && controllerType == null )
      return null;

      // check parameters
    if( controllerType != null && controller == null )
      throw new InstantiationException( "Controller name should be defined if controllerType is set" );

    controllerInstance = createController( controller, controllerType );

    return controllerInstance;
    }

    /**
     * Set controller.
     */
  public void setControllerInstance(Controller controller)
    {
    this.controllerInstance = controller;
     }

   /**
    * Create a new instance of controller named in parameter.
    * If controllerType is specified, create controller accordingly.
    * Otherwise, if name denote a classname, create an instance of it. If class is
    *  subclass of org.apache.struts.action.Action, wrap controller
    * appropriately.
    * Otherwise, consider name as an url.
    * @param name Controller name (classname, url, ...)
    * @param controllerType Expected Controller type
    * @return org.apache.struts.tiles.Controller
    * @throws InstantiationException if an error occur while instanciating Controller :
    * (classname can't be instanciated, Illegal access with instanciated class,
    * Error while instanciating class, classname can't be instanciated.
    */
   static public Controller createController(String name, String controllerType)
       throws InstantiationException
   {
   if(log.isDebugEnabled())
     log.debug( "Create controller name="+ name+", type=" + controllerType );
   Controller controller = null;

   if( controllerType == null )
     { // first try as a classname
     try {
       return createControllerFromClassname(name);
       }
      catch( InstantiationException ex )
       { // ok, try something else
       controller = new UrlController( name );
       }
     }
    else if( "url".equalsIgnoreCase( controllerType ) )
     controller = new UrlController( name );
    else if( "classname".equalsIgnoreCase( controllerType ) )
     controller = createControllerFromClassname( name );

   return controller;
   }


     /**
      * Create a controller from specified classname
      * @param classname Controller classname.
      * @return org.apache.struts.tiles.Controller
      * @throws InstantiationException if an error occur while instanciating Controller :
      * (classname can't be instanciated, Illegal access with instanciated class,
      * Error while instanciating class, classname can't be instanciated.
      */
   static public Controller createControllerFromClassname(String classname)
       throws InstantiationException
   {
    try
     {
     Class requestedClass = RequestUtils.applicationClass(classname);
     Object instance = requestedClass.newInstance();
     /*
     if( instance instanceof org.apache.struts.action.Action )
       { // wrap strutsinstance
       instance = new ActionController( (Action)instance );
       } // end if
     */
     if(log.isDebugEnabled())
       log.debug( "Controller created : " + instance );
     return (Controller)instance;
     }
    catch( java.lang.ClassNotFoundException ex)
     {
     throw new InstantiationException( "Error - Class not found :" + ex.getMessage() );
     }
    catch( java.lang.IllegalAccessException ex)
     {
     throw new InstantiationException( "Error - Illegal class access :" + ex.getMessage() );
     }
    catch( java.lang.InstantiationException ex)
     {
     throw ex;
     }
    catch( java.lang.ClassCastException ex)
     {
     throw new InstantiationException( "Controller of class '" + classname
                       + "' should implements 'Controller' or extends 'Action'" );
     }
   }


}
