//Source file: H:\\TEMP\\generated\\org\\apache\\struts\\tiles\\DefinitionTagSupport.java

package org.apache.struts.taglib.tiles;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.PageContext;
import java.io.Serializable;

  /**
   * Common base class for tags dealing with Tile Definition.
   * This class define properties used in Definition Tags.
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
   * Type can be 'classname', 'url'
   */
  public String getControllerType()
  {
  return controllerType;
  }

  /**
   * Get controller name.
   * Name denote a fully qualified classname, or an url.
   * Exact type can be specified with setControllerType.
   */
  public String getControllerName()
  {
  return controllerName;
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
   * Set associated controller name.
   * Name denote a fully qualified classname, or an url.
   * Exact type can be specified with setControllerType.
   * @param controller Controller classname or url
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
   * Set associated controller name as a classtype, and controller
   * type as "classname".
   * Name denote a fully qualified classname
   * Convenience method.
   * @param controller Controller classname.
   */
  public void setControllerClass(String controller)
  {
  setControllerName( controller);
  setControllerType( "classname" );
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
   * @param role the new value of the role property
   */
  public void setRole(String role)
    {
    this.role = role;
    }

  /**
   * Sets the value of the page property.
   *
   * @param page the new value of the template property
   */
  public void setPage(String page)
  {
  this.page = page;
  }

  /**
   * Gets the value of the page property.
   *
   * @param page the new value of the template property
   */
  public String getPage(String page)
  {
  return page;
  }

  /**
   * Access method for the template property.
   *
   * @return   the current value of the template property
   */
  public String getTemplate()
  {
  return page;
  }

  /**
   * Sets the value of the template property.
   * Same as setPage()
   *
   * @param template the new value of the template property
   * @roseuid
   */
  public void setTemplate(String template)
  {
  this.page = template;
  }
}
