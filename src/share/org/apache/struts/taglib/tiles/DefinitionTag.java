/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/tiles/DefinitionTag.java,v 1.6 2003/02/27 19:18:55 cedric Exp $
 * $Revision: 1.6 $
 * $Date: 2003/02/27 19:18:55 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


package org.apache.struts.taglib.tiles;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.tiles.util.TagUtils;
import org.apache.struts.tiles.AttributeDefinition;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.UntyppedAttribute;

/**
 * This is the tag handler for &lt;tiles:definition&gt;, which defines
 * a tiles (or template / component). Definition is put in requested context and can be
 * used in &lt;tiles:insert&gt.
 *
 * @author Cedric Dumoulin
 * @version $Revision: 1.6 $ $Date: 2003/02/27 19:18:55 $
 */
public class DefinitionTag extends DefinitionTagSupport implements PutTagParent, PutListTagParent
{
    /** Commons Logging instance. */
  //protected static Log log = LogFactory.getLog(DefinitionTag.class);

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
     * Release internal references.
     */
    protected void releaseInternal()
    {
    definition = null;
    }

   /**
     * This method is a convenience for other tags for
     * putting content into the tile definition.
     * Content is already typed by caller.
     */
   public void putAttribute(String name, Object content)
   {
   definition.putAttribute(name, content);
   }

    /**
     * Process nested &lg;put&gt; tag.
     * Method is called from nested &lg;put&gt; tags.
     * Nested list is added to current list.
     * If role is defined, nested attribute is wrapped into an untyped definition
     * containing attribute value and role.
     */
  public void processNestedTag(PutTag nestedTag) throws JspException
   {
      // Get real value and check role
      // If role is set, add it in attribute definition if any.
      // If no attribute definition, create untyped one and set role.
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
     * Method is called from nested &lg;putList&gt; tags.
     * Nested list is added to current list.
     * If role is defined, nested attribute is wrapped into an untyped definition
     * containing attribute value and role.
     */
  public void processNestedTag(PutListTag nestedTag) throws JspException
   {
      // Get real value and check role
      // If role is set, add it in attribute definition if any.
      // If no attribute definition, create untyped one and set role.
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
     * Get the ID.
     * @return ID
     */
    public String getId()
    {
    return id;
    }

    /**
     * Set the ID.
     * @param id New ID.
     */
    public void setId(String id)
    {
    this.id = id;
    }

    /**
     * Get the scope.
     * @return Scope.
     */
    public String getScope()
    {
    return scope;
    }

    /**
     * Set the scope.
     * @param aScope Scope.
     */
    public void setScope(String aScope)
    {
    scope = aScope;
    }

  /**
   * Set <code>extends</code> (parent) definition name.
   * @param definitionName Name of parent definition.
   */
  public void setExtends(String definitionName)
    {
    this.extendsDefinition = definitionName;
    }

  /**
   * Get <code>extends</code> (parent) definition name.
   * @return Name of parent definition.
   */
  public String getExtends()
    {
    return extendsDefinition;
    }

   /**
    * Process the start tag by creating a new definition.
    * @throws JspException On errors processing tag.
    */
   public int doStartTag() throws JspException
   {
     // Do we extend a definition ?
   if( extendsDefinition != null && !extendsDefinition.equals("") )
     {
     ComponentDefinition parentDef = TagUtils.getComponentDefinition( extendsDefinition, pageContext );
     //if(log.isDebugEnabled())
         //log.debug( "parent definition=" + parentDef );
     definition = new ComponentDefinition( parentDef );
     }  // end if
    else
      definition = new ComponentDefinition();

      // Set definitions attributes
   if( page != null )
     definition.setTemplate(page);
   if( role != null )
     definition.setRole(role);

   //if(log.isDebugEnabled())
       //log.debug( "definition=" + definition );
   return EVAL_BODY_INCLUDE;
   }

   /**
     * Process the end tag by putting the definition in appropriate context.
     * @throws JspException On errors processing tag.
     */
   public int doEndTag() throws JspException
   {
   TagUtils.setAttribute( pageContext, id, definition, scope);

   releaseInternal();
   return EVAL_PAGE;
   }


}
