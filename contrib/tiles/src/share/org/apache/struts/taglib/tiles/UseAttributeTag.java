/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/UseAttributeTag.java,v 1.1 2001/08/01 14:36:41 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:41 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.taglib.tiles.util.TagUtils;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import javax.servlet.jsp.tagext.TagSupport;


/**
 * Custom tag that expose a component attribute to page.
 *
 */

public final class UseAttributeTag extends TagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * Class name of object.
     */
    private String  classname = null;


    /**
     * The scope name.
     */
    private String scopeName = null;

    /**
     * The scope value.
     */
    private int scope = PageContext.PAGE_SCOPE;



    /**
     * The attribute name to be exposed.
     */
    private String attributeName = null;
    /**
     * Is errors ignored ? This is the property for attribute 'ignore'.
     * Default value is false, which throw an exception.
     * Only attribute not found errors are ignored.
     */
  protected boolean isErrorIgnored = false;


    // ------------------------------------------------------------- Properties


    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        attributeName = null;
        classname = null;
        scope = PageContext.PAGE_SCOPE;
        scopeName = null;
        isErrorIgnored = false;
    }

    /**
     * Return the length.
     */
    public String getClassname() {

	return (this.classname);

    }


    /**
     * Set the length.
     *
     * @param length The new length
     */
    public void setClassname(String name) {

	this.classname = name;

    }

    /**
     * Set attributeName
     */
  public void setName(String value){
    this.attributeName = value;
  }


    /**
     * Set the offset.
     *
     * @param offset The new offset
     */
    public void setScope(String scope) {

	this.scopeName = scope;

    }

    /**
     * Set ignore attribute
     */
  public void setIgnore(boolean ignore)
    {
    this.isErrorIgnored = ignore;
    }

    // --------------------------------------------------------- Public Methods


    /**
     * Expose the requested attribute from component context.
     *
     * @exception JspException if a JSP exception has occurred
     */
  public int doStartTag() throws JspException
    {
    if( id==null )
      id=attributeName;
      
    ComponentContext compContext = (ComponentContext)pageContext.getAttribute( ComponentConstants.COMPONENT_CONTEXT, pageContext.REQUEST_SCOPE);
    if( compContext == null )
      throw new JspException ( "Error - tag.useAttribute : component context is not defined. Check tag syntax" );

    Object value = compContext.getAttribute(attributeName);
        // Check if value exist and if we must send a runtime exception
    if( value == null )
      if(!isErrorIgnored)
        throw new JspException ( "Error - tag.useAttribute : attribute '"+ attributeName + "' not found in context. Check tag syntax" );
       else
        return SKIP_BODY;

    if( scopeName != null )
      {
      scope = TagUtils.getScope( scopeName, PageContext.PAGE_SCOPE );
      if(scope!=ComponentConstants.COMPONENT_SCOPE)
        pageContext.setAttribute(id, value, scope);
      }
     else
      pageContext.setAttribute(id, value);

	    // Continue processing this page
    return SKIP_BODY;
    }




    /**
     * Clean up after processing this enumeration.
     *
     * @exception JspException if a JSP exception has occurred
     */
  public int doEndTag() throws JspException
    {
	  return (EVAL_PAGE);
    }

}
