/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/GetAttributeTag.java,v 1.3 2001/12/27 17:35:37 cedric Exp $
 * $Revision: 1.3 $
 * $Date: 2001/12/27 17:35:37 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.tiles.ComponentContext;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

  /**
   * Retrieve the value of the specified component/template attribute property,
   * and render it to the current JspWriter as a String.
   * The usual toString() conversions is applied on found value.
   */
public class GetAttributeTag extends TagSupport implements ComponentConstants {

  private String attribute = null;
    /** Role attribute */
  private String role = null;
    /**
     * Do we ignore error if attribute is not found ?
     * Default value is false, which throw an exception.
     */
  private boolean isErrorIgnored = false;

  /**
   * default constructor
   */
  public GetAttributeTag() {
    super();
  }

    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        attribute = null;
        role = null;
        isErrorIgnored = false;
    }

    /**
     * Set attribute
     */
  public void setAttribute(String attribute){
    this.attribute = attribute;
  }

    /**
     * Set attribute
     */
  public void setName(String value)
    {
    this.attribute = value;
    }

    /**
     * Set attribute
     */
  public void setIgnore(boolean ignore)
    {
    this.isErrorIgnored = ignore;
    }

    /**
     * Set role attribute
     * @param name The role the user must be in to store content.
     */
   public void setRole(String role) {
      this.role = role;
   }

    /**
     * Set attribute
     */
  public int doEndTag() throws JspException {

      // Check role
    if(role != null && !((HttpServletRequest)pageContext.getRequest()).isUserInRole(role) )
      {
      	return EVAL_PAGE;
      } // end if

      // Get context
    ComponentContext compContext = (ComponentContext)pageContext.getAttribute( ComponentConstants.COMPONENT_CONTEXT, pageContext.REQUEST_SCOPE);

    if( compContext == null )
      throw new JspException ( "Error - tag.getAsString : component context is not defined. Check tag syntax" );

    Object value = compContext.getAttribute(attribute);
    if( value == null)
      { // no value : throw error or fail silently according to ignore
      if(isErrorIgnored == false )
        throw new JspException ( "Error - tag.getAsString : attribute '"+ attribute + "' not found in context. Check tag syntax" );
       else
        return EVAL_PAGE;
      } // end if


    try
      {
      pageContext.getOut().print( value );
      }
     catch( IOException ex )
      {
      ex.printStackTrace();
      throw new JspException ( "Error - tag.getProperty : IOException ");
      }

    return EVAL_PAGE;
  }
}
