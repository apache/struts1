/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/ImportAttributeTag.java,v 1.3 2001/12/27 17:35:37 cedric Exp $
 * $Revision: 1.3 $
 * $Date: 2001/12/27 17:35:37 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.taglib.tiles.util.TagUtils;

import java.util.Iterator;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import javax.servlet.jsp.tagext.TagSupport;


/**
	*  Import attribute from component to requested scope.
	*  Attribute name and scope are optional. If not specified, all component
	*  attributes are imported in page scope.
 */

public final class ImportAttributeTag extends TagSupport {

    /**
     * Class name of object.
     */
    private String  name = null;


    /**
     * The scope name.
     */
    private String scopeName = null;

    /**
     * The scope value.
     */
    private int scope = PageContext.PAGE_SCOPE;
    /**
     * Is errors ignored ? This is the property for attribute 'ignore'.
     * Default value is false, which throw an exception.
     * Only attribute not found errors are ignored.
     */
  protected boolean isErrorIgnored = false;


    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        name = null;
        scopeName = null;
        scope = PageContext.PAGE_SCOPE;
        isErrorIgnored = false;
    }

    /**
     * Return the name.
     */
    public String getName()
     {
     return (this.name);
     }


    /**
     * Set the name.
     *
     * @param name The new name
     */
    public void setName(String name)
     {
     this.name = name;
     }


    /**
     * Set the scope by its string name.
     *
     * @param offset The new offset
     */
    public void setScope(String scope)
      {
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
     * Expose the requested property from component context.
     *
     * @exception JspException if a JSP exception has occurred
     */
  public int doStartTag() throws JspException
    {
      // retrieve component context
    ComponentContext compContext = (ComponentContext)pageContext.getAttribute( ComponentConstants.COMPONENT_CONTEXT, pageContext.REQUEST_SCOPE);
    if( compContext == null )
        throw new JspException ( "Error - tag.useProperty : component context is not defined. Check tag syntax" );

      // set scope
    scope = TagUtils.getScope( scopeName, PageContext.PAGE_SCOPE );

      // push attribute in requested context.
    if( name != null )
      {
      Object value = compContext.getAttribute(name);
        // Check if value exist and if we must send a runtime exception
      if( value == null )
        if(!isErrorIgnored)
          throw new JspException ( "Error - tag.useProperty : property '"+ name + "' not found in context. Check tag syntax" );
         else
          return SKIP_BODY;

      pageContext.setAttribute(name, value, scope);
      }
     else
      { // set all attributes
      Iterator names = compContext.getAttributeNames();
      while(names.hasNext())
        {
        String name = (String)names.next();
        pageContext.setAttribute(name, compContext.getAttribute(name), scope);
        } // end loop
      } // end if

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
