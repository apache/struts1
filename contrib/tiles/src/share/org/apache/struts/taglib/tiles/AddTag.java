/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/AddTag.java,v 1.1 2001/08/01 14:36:40 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:40 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.tiles.ComponentContext;

import org.apache.struts.taglib.tiles.util.TagUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.IllegalAccessException;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.ServletException;

  /**
   * Add an element to surrounding list tag.
   * Same syntax as put.
   */
public class AddTag extends PutTag {

  /**
   * default constructor
   */
  public AddTag() {
    super();
  }

    /**
     * Find parent tag wich must implements AttributeContainer.
     * @throw JspException If we can't find an appropriate enclosing tag.
     */
  protected void callParent() throws JspException
    {
            // Get enclosing parent,
    AddTagParent enclosingParent = findEnclosingPutListTagParent();
    enclosingParent.processNestedTag( this );
    }

    /**
     * Find parent tag wich must implements AttributeContainer.
     * @throw JspException If we can't find an appropriate enclosing tag.
     */
  protected AddTagParent findEnclosingPutListTagParent() throws JspException {
    try
      {
      AddTagParent parent = (AddTagParent)findAncestorWithClass(this,AddTagParent.class);
      if( parent == null )
        {
        throw new JspException( "Error - tag add : enclosing tag doesn't accept 'add' tag." );
        }
      return parent;
      }
     catch( ClassCastException ex )
      {
      throw new JspException( "Error - tag add : enclosing tag doesn't accept 'add' tag." );
      }
  }
}
