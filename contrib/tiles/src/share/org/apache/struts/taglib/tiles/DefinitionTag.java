/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/DefinitionTag.java,v 1.2 2001/12/27 17:35:37 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/12/27 17:35:37 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.taglib.tiles.util.TagUtils;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.AttributeDefinition;
import org.apache.struts.tiles.UntyppedAttribute;
import org.apache.struts.tiles.DefinitionsUtil;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;

/**
 * This is the tag handler for &lt;tiles:definition&gt;, which defines
 * a tiles (or template / component). Definition is put in requested context, and can be
 * used in &lt;tiles:insert&gt.
 *
 * @author Cedric Dumoulin
 * @version $Revision: 1.2 $ $Date: 2001/12/27 17:35:37 $
 */
public class DefinitionTag extends DefinitionTagSupport implements PutTagParent, PutListTagParent
{

    /* JSP Tag attributes */
		/**
		 * Definition identifier.
		 */
		private String id;

		/**
		 * Scope into which definition will be saved.
		 */
		private String scope;

    /**
     * Extends attribute value.
     */
    private String extendsDefinition;

    /* Internal properties */
    /**
     * Template definition
     */
    private ComponentDefinition definition;

		/**
		 * Reset member values for reuse. This method calls super.release(),
		 * which invokes TagSupport.release(), which typically does nothing.
		 */
		public void release()
		{
    super.release();
    id = null;
    page = null;
    scope = null;
    role = null;
    extendsDefinition = null;
		}

		/**
		 * Reset member values for reuse. This method calls super.release(),
		 * which invokes TagSupport.release(), which typically does nothing.
		 */
		protected void releaseInternal()
		{
    definition = null;
		}

   /**
     * This method is a convenience for others tags for
     * putting content into the tile definition.
     * Content is already typed by caller.
     */
   public void putAttribute(String name, Object content)
   {
   definition.putAttribute(name, content);
   }

    /**
     * Process nested &lg;put&gt; tag.
     * Method calls by nested &lg;put&gt; tags.
     * Nested list is added to current list.
     * If role is defined, nested attribute is wrapped into an untypped definition
     * containing attribute value and role.
     */
  public void processNestedTag(PutTag nestedTag) throws JspException
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
    putAttribute( nestedTag.getName(), attributeValue);
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
      // Check if a name is defined
    if( nestedTag.getName() == null)
      throw new JspException( "Error - PutList : attribute name is not defined. It is mandatory as the list is added to a 'definition'." );
      // now add attribute to enclosing parent (i.e. : this object).
    putAttribute(nestedTag.getName(), attributeValue);
    }

		/**
		 * Access method for the id property.
		 *
		 * @return   the current value of the id property
		 * @roseuid
		 */
		public String getId()
		{
    return id;
    }

		/**
		 * Sets the value of the id property.
		 *
		 * @param aId the new value of the id property
		 */
		public void setId(String id)
		{
    this.id = id;
		}

		/**
		 * Access method for the scope property.
		 *
		 * @return   the current value of the scope property
		 */
		public String getScope()
		{
		return scope;
    }

		/**
		 * Sets the value of the scope property.
		 *
		 * @param aScope the new value of the scope property
		 */
		public void setScope(String aScope)
		{
    scope = aScope;
    }

  /**
   * Sets the value of the extends property.
   *
   * @param definitionName Name of parent definition.
   */
  public void setExtends(String definitionName)
    {
    this.extendsDefinition = definitionName;
    }

  /**
   * Access method for the extends property.
   * @return   the current value of the extends property
   */
  public String getExtends()
    {
    return extendsDefinition;
    }

   /**
     * Process the start tag by creating a new definition.
     */
   public int doStartTag() throws JspException
   {
     // Do we extend a definition ?
   if( extendsDefinition != null && !extendsDefinition.equals("") )
     {
     ComponentDefinition parentDef = TagUtils.getComponentDefinition( extendsDefinition, pageContext );
     System.out.println( "parent definition=" + parentDef );
     definition = new ComponentDefinition( parentDef );
     }  // end if
    else
      definition = new ComponentDefinition();

      // Set definitions attributes
   if( page != null )
     definition.setTemplate(page);
   if( role != null )
     definition.setRole(role);

   //System.out.println( "definition=" + definition );
   return EVAL_BODY_INCLUDE;
   }

   /**
     * Process the end tag by puting the definition in appropriate context.
     */
   public int doEndTag() throws JspException
   {
   TagUtils.setAttribute( pageContext, id, definition, scope);

   releaseInternal();
   return EVAL_PAGE;
   }


}
