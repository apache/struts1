/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/AttributeToScopeTag.java,v 1.1 2001/08/01 14:36:40 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:40 $
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
 * Custom tag that put component's attributes in a scope (request, page, ...).
 * @deprecated. Is it still in use ?
 */

public final class AttributeToScopeTag extends TagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The scope name.
     */
    private String scopeName = null;

    /**
     * The scope value.
     */
    private int scope = PageContext.PAGE_SCOPE;



    /**
     * The property name to be exposed.
     */
    private String property = null;


    // ------------------------------------------------------------- Properties



    /**
     * Return the property name.
     */
    public String getProperty() {

	return (this.property);

    }


    /**
     * Set the property name.
     *
     * @param property The property name
     */
    public void setProperty(String property) {

	this.property = property;

    }

    /**
     * Set the offset.
     *
     * @param offset The new offset
     */
    public void setScope(String scope) {

	this.scopeName = scope;

    }

    // --------------------------------------------------------- Public Methods


    /**
     * Expose the requested property from component context.
     *
     * @exception JspException if a JSP exception has occurred
     */
  public int doStartTag() throws JspException
    {
    if( id==null )
      id=property;
      
    ComponentContext compContext = (ComponentContext)pageContext.getAttribute( ComponentConstants.COMPONENT_CONTEXT, pageContext.REQUEST_SCOPE);

    if( compContext == null )
      throw new JspException ( "Error - tag.useProperty : component context is not defined. Check tag syntax" );

    Object value = compContext.getAttribute(property);
    if( value == null )
      throw new JspException ( "Error - tag.useProperty : property '"+ property + "' not found in context. Check tag syntax" );

    if( scopeName != null )
      {
      scope = TagUtils.getScope( scopeName, PageContext.PAGE_SCOPE );
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

    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        property = null;
        scopeName = null;
        scope = PageContext.PAGE_SCOPE;
    }

}
