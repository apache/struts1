/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/PutTag.java,v 1.3 2002/02/18 14:50:03 cedric Exp $
 * $Revision: 1.3 $
 * $Date: 2002/02/18 14:50:03 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.taglib.tiles.util.TagUtils;
import org.apache.struts.tiles.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.IllegalAccessException;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.PageContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


  /**
   * Put an attribute in enclosing attribute container tag.
   * Enclosing attribute container tag can be : &lt;insert&gt; or &lt;definition&gt;.
   * Exception is thrown if no appropriate tag can be found.
   * Put tag can have following atributes :
   * <li>
   * <ul>name : Name of the attribute</ul>
   * <ul>value | content : value to put as attribute</ul>
   * <ul>type : value type. Only valid if value is a String and is set by
   * value="something" or by a bean.
   * Possible type are : string (value is used as direct string),
   * page | template (value is used as a page url to insert),
   * definition (value is used as a definition name to insert)</ul>
   * <ul>direct : Specify if value is to be used as a direct string or as a
   * page url to insert. This is another way to specify the type. It only apply
   * if value is set as a string, and type is not present.</ul>
   * <ul>beanName : Name of a bean used for setting value. Only valid if value is not set.
   * If property is specified, value come from bean's property. Otherwise, bean
   * itself is used for value.</ul>
   * <ul>beanProperty : Name of the property used for retrieving value.</ul>
   * <ul>beanScope : Scope containing bean. </ul>
   * <ul>role : Role to check when 'insert' will be called. If enclosing tag is
   * &lt;insert&gt;, role is checked immediately. If enclosing tag is
   * &lt;definition&gt;, role will be checked when this definition will be
   * inserted.</ul>
   * </li>
   * Value can also come from tag body. Tag body is taken into account only if
   * value is not set by one of the tag attributes. In this case Attribute type is
   * "string", unless tag body define another type.
   */
public class PutTag extends BodyTagSupport implements  ComponentConstants {


    /* JSP Tag attributes */
    /** Name of attribute to put in component context */
  protected String attributeName = null;
    /** associated attribute value */
  private Object value = null;
    /** JSP Template compatibility */
  private String direct = null;
    /** Requested type for the value */
  private String valueType = null;
    /** Bean name attribute */
  private String beanName = null;
    /** Bean property attribute */
  private String beanProperty = null;
    /** Bean scope attribute */
  private String beanScope = null;
    /** Role attribute */
  private String role = null;

    /* Internal properties */
    /** cached real value computed from tag attributes */
  protected Object realValue = null;

  /**
   * default constructor
   */
  public PutTag() {
    super();
  }

    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();

        attributeName = null;
        valueType = null;
        direct = null;
        value = null;
        beanName = null;
        beanProperty = null;
        beanScope = null;
        role = null;
    }

    /**
     * Release internal properties.
     */
    protected void releaseInternal()
      {
      realValue = null;
      }

    /**
     * Set property
     */
  public void setName(String value){
    this.attributeName = value;
  }

    /**
     * Set property
     */
  public String getName(){
    return attributeName;
  }

    /**
     * Set property.
     * Method added to satisfy Tomcat (bug ?)
     */
  public void setValue(String value){
    this.value = value;
  }

    /**
     * Set property.
     * Method added to satisfy Tomcat (bug ?)
     */
  public String getValue(){
    return (String)this.value;
  }

    /**
     * Set property
     */
  public void setValue(Object value){
    this.value = value;
  }

    /**
     * Set property value as an object
     * Added because some web container react badly to value as Object.
     */
  public void setObjectValue(Object value){
    this.value = value;
  }

    /**
     * Set property
     * Method added to satisfy Tomcat (bug ?)
     */
  public void setContent(String value){
    this.value = value;
  }

    /**
     * Get property
     * Method added to satisfy Tomcat (bug ?)
     */
  public String getContent(){
    return (String)value;
  }

    /**
     * Set property
     */
  public void setContent(Object value){
    this.value = value;
  }

    /**
     * Set property
     * Method added for compatibility with JSP1.1
     */
  public void setDirect( String isDirect ){
       this.direct = isDirect;
  }

    /**
     * Set property
     */
  public void setType( String value ){
       this.valueType = value;
  }

    /**
     * Get property
     */
  public String getType( ){
       return this.valueType;
  }

    /**
     * Set property.
     */
  public void setBeanName(String value){
    this.beanName = value;
  }

    /**
     * Set property.
     */
  public void setBeanProperty(String value){
    this.beanProperty = value;
  }

    /**
     * Set property.
     */
  public void setBeanScope(String value){
    this.beanScope = value;
  }

    /**
     * Set role attribute
     * @param name The role the user must be in to store content.
     */
   public void setRole(String role) {
      this.role = role;
   }

    /**
     * Get role attribute
     * @return The role defined in the tag or null.
     */
   public String getRole() {
      return role;
   }

    /**
     * Get real value according to tag attribute.
     * Real value is the value compute after attribute processing :
     * @return real value.
     * @throw JspTagException If something goes wrong while getting value from bean.
     */
   public Object getRealValue() throws JspException
   {
   if( realValue == null )
     computeRealValue();

   return realValue;
   }

    /**
     * Compute real value according to tag attributes.
     * @throw JspTagException If something goes wrong while getting value from bean.
     */
  protected void computeRealValue() throws JspException
    {
        // Compute real value from attributes set.
    realValue = value;

        // Does value comes from a bean ?
      if( value == null && beanName != null )
        {
        getRealValueFromBean();
        } // end if

      // Is there a type set ?
      // First check direct attribute, and translate it to a valueType.
      // Then, evaluate valueType, and create requested typed attribute.
      // If valueType is not set, use the value "as is".
    if( valueType==null && direct != null )
      {
      if( Boolean.valueOf(direct).booleanValue() == true )
        valueType = "string";
       else
        valueType = "path";
      }  // end if

    if( value != null && valueType!=null && !(value instanceof AttributeDefinition) )
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
          }
         else if( valueType.equalsIgnoreCase( "definition" ) )
          {
          realValue = new DefinitionNameAttribute( strValue );
          }
         else
          { // bad type
          throw new JspException( "Warning - Tag put : Bad type '" + valueType + "'." );
          }  // end if
      } //  end  if

  }


    /**
     * Extract real value from specified bean.
     * @throw JspTagException If something goes wrong while getting value from bean.
     */
  protected void getRealValueFromBean() throws JspException
    {
    try
      {
        Object bean = TagUtils.retrieveBean( beanName, beanScope, pageContext );
        if( bean != null && beanProperty != null )
            realValue = TagUtils.getProperty( bean, beanProperty );
           else
            realValue = bean;   // value can be null
      }
     catch( NoSuchMethodException ex )
      {
      throw new JspException( "Error - component.PutAttributeTag : Error while retrieving value from bean '"
                              + beanName + "' with property '"
                              + beanProperty + "' in scope '"
                              + beanScope + "'. (exception : "
                              + ex.getMessage() );
      }
     catch( InvocationTargetException ex )
      {
      throw new JspException( "Error - component.PutAttributeTag : Error while retrieving value from bean '"
                              + beanName + "' with property '"
                              + beanProperty + "' in scope '"
                              + beanScope + "'. (exception : "
                              + ex.getMessage() );
      }
     catch( IllegalAccessException ex )
      {
      throw new JspException( "Error - component.PutAttributeTag : Error while retrieving value from bean '"
                              + beanName + "' with property '"
                              + beanProperty + "' in scope '"
                              + beanScope + "'. (exception : "
                              + ex.getMessage() );
      }
    }

    /**
     * Do start tag
     */
  public int doStartTag() throws JspException
    {
      // Does we need to evaluate body ?
    if( value == null && beanName == null )
      return EVAL_BODY_TAG;
      // Value is set, don't evaluate body.
    return SKIP_BODY;
    }

    /**
     * Do end tag
     */
  public int doEndTag() throws JspException
    {
        // If nothing is set, value must come from body
    if( value == null && beanName == null )
        {  // body
          // Test body content in case of empty body.
        if( bodyContent != null )
          value = bodyContent.getString();
         else
          value = "";
        }
    callParent();

        // clean up tag handler for reuse.
    releaseInternal();

    return EVAL_PAGE;
  }

    /**
     * Find parent tag wich must implements AttributeContainer.
     * @throw JspException If we can't find an appropriate enclosing tag.
     */
  protected void callParent() throws JspException
    {
            // Get enclosing parent,
    PutTagParent enclosingParent = findEnclosingPutTagParent();
    enclosingParent.processNestedTag( this );
    }

    /**
     * Find parent tag wich must implements AttributeContainer.
     * @throw JspException If we can't find an appropriate enclosing tag.
     */
  protected PutTagParent findEnclosingPutTagParent() throws JspException {
    try
      {
      PutTagParent parent = (PutTagParent)findAncestorWithClass(this,PutTagParent.class);
      if( parent == null )
        {
        throw new JspException( "Error - tag put : enclosing tag doesn't accept 'put' tag." );
        }
      return parent;
      }
     catch( ClassCastException ex )
      {
      throw new JspException( "Error - tag put : enclosing tag doesn't accept 'put' tag." );
      }
  }

}









