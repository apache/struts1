/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/ComponentDefinition.java,v 1.2 2001/12/27 17:35:37 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/12/27 17:35:37 $
 * $Author: cedric $
 *
 */


package org.apache.struts.tiles;

import org.apache.struts.tiles.Controller;
import org.apache.struts.action.Action;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;
import org.apache.struts.taglib.tiles.DefinitionTagSupport;

/**
 * Definition of a template / component attributes.
 * Attributes of a component can be defined with the help of this class.
 * An instance of this class can be used as a bean, and passed to 'insert' tag.
 */
public class ComponentDefinition implements Serializable
{
  /** Debug flag */
  public static final boolean debug = false;

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
    * @throw InstantiationException if an error occur while instanciating Controller :
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
  public String getPage()
    {
    return path;
    }

  /**
   * Sets the value of the path property.
   *
   * @param aPath the new value of the path property
   * @return void
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
  public void setController(String name)
  {
  this.controller = name;
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
     * @throw InstantiationException if an error occur while instanciating Controller :
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
    * @throw InstantiationException if an error occur while instanciating Controller :
    * (classname can't be instanciated, Illegal access with instanciated class,
    * Error while instanciating class, classname can't be instanciated.
    */
   static public Controller createController(String name, String controllerType)
       throws InstantiationException
   {
   if(debug)
     System.out.println( "Create controller name="+ name+", type=" + controllerType );
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
      * @param controllerType Expected Controller type
      * @return org.apache.struts.tiles.Controller
      * @throw InstantiationException if an error occur while instanciating Controller :
      * (classname can't be instanciated, Illegal access with instanciated class,
      * Error while instanciating class, classname can't be instanciated.
      */
   static public Controller createControllerFromClassname(String classname)
       throws InstantiationException
   {
    try
     {
     Class requestedClass = Class.forName(classname);
     Object instance = requestedClass.newInstance();
     /*
     if( instance instanceof org.apache.struts.action.Action )
       { // wrap strutsinstance
       instance = new ActionController( (Action)instance );
       } // end if
     */
     if(debug)
       System.out.println( "Controller created : " + instance );
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
