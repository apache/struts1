/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/PutListTag.java,v 1.1 2001/08/01 14:36:41 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:41 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.AttributeDefinition;
import org.apache.struts.tiles.UntyppedAttribute;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


  /**
   * PutList tag implementation.
   */
public class PutListTag extends TagSupport implements ComponentConstants, AddTagParent, PutListTagParent {
  

    /** Name of this attribute */
  private String attributeName = null;
    /** The list itself */
  private List list = null;
    /** Role attribute */
  private String role = null;

  /**
   * default constructor
   */
  public PutListTag() {
    super();
  }

    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        attributeName = null;
        role = null;
    }

    /**
     * Release all internal resources.
     */
    protected void releaseInternal()
      {
      list = null;
      }

    /**
     * Set property
     */
  public void setName(String name)
  {
  this.attributeName = name;
  }

    /**
     * Get property
     */
  public String getName()
  {
  return attributeName;
  }

    /**
     * Set role attribute
     * @param name The role the user must be in to store content.
     */
   public void setRole(String role)
   {
   this.role = role;
   }

    /**
     * Get role attribute
     */
   public String getRole()
   {
   return role;
   }

    /**
     * Get list defined in tag.
     */
   public List getList()
   {
   return list;
   }

    /**
     * Set property
     */
  public void addElement(Object value){
    if(list==null)
      list = new ArrayList();
    list.add(value);
  }

    /**
     * Process nested &lg;putList&gt; tag.
     * Method calls by nested &lg;putList&gt; tags.
     * Nested list is added to current list.
     * If role is defined, nested attribute is wrapped into an untypped definition
     * containing attribute value and role.
     */
  public void processNestedTag(PutListTag nestedTag) throws JspException
   {
      // Get real value and check role
      // If role is set, add it in attribute definition if any.
      // If no attribute definition, create untyped one, and set role.
    Object attributeValue = nestedTag.getList();

    if( nestedTag.getRole() != null )
      {
      AttributeDefinition  def = new UntyppedAttribute( attributeValue );
      def.setRole(nestedTag.getRole());
      attributeValue = def;
      } // end if
      // now add attribute to enclosing parent (i.e. : this object)
    addElement(attributeValue);
    }

    /**
     * Process nested &lg;add&gt; tag.
     * Method calls by nested &lg;add&gt; tags.
     * Nested attribute is added to current list.
     * If role is defined, nested attribute is wrapped into an untypped definition
     * containing attribute value and role.
     */
  public void processNestedTag(AddTag nestedTag) throws JspException
   {
      // Get real value and check role
      // If role is set, add it in attribute definition if any.
      // If no attribute definition, create untyped one, and set role.
    Object attributeValue = nestedTag.getRealValue();
    AttributeDefinition def;

    if( nestedTag.getRole() != null )
      {
      try
        {
        def = ((AttributeDefinition)attributeValue);
        }
       catch( ClassCastException ex )
        {
        def = new UntyppedAttribute( attributeValue );
        }
      def.setRole(nestedTag.getRole());
      attributeValue = def;
      } // end if
      // now add attribute to enclosing parent (i.e. : this object)
    addElement(attributeValue);
    }

    /**
     * Do start tag.
     */
  public int doStartTag() throws JspException
    {
    return EVAL_BODY_INCLUDE;
    }

    /**
     * Do end tag.
     */
  public int doEndTag() throws JspException
   {
   PutListTagParent enclosingParent = findEnclosingParent();
   enclosingParent.processNestedTag( this );
     // Clear list to avoid reuse
   releaseInternal();
   return EVAL_PAGE;
  }

    /**
     * Find enclosing parent tag accepting this tag.
     * @throw JspException If we can't find an appropriate enclosing tag.
     */
  protected PutListTagParent findEnclosingParent() throws JspException {
    try
      {
      PutListTagParent parent = (PutListTagParent)findAncestorWithClass(this,PutListTagParent.class);
      if( parent == null )
        {
        throw new JspException( "Error - tag putList : enclosing tag doesn't accept 'putList' tag." );
        }
      return parent;
      }
     catch( ClassCastException ex )
      {
      throw new JspException( "Error - tag putList : enclosing tag doesn't accept 'putList' tag." );
      }
  }


}
