/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/tiles/PutListTag.java,v 1.4 2003/02/27 19:18:55 cedric Exp $
 * $Revision: 1.4 $
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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.tiles.AttributeDefinition;
import org.apache.struts.tiles.UntyppedAttribute;


  /**
   * PutList tag implementation.
   */
public class PutListTag extends TagSupport implements ComponentConstants, AddTagParent, PutListTagParent {
  

    /** Name of this attribute. */
  private String attributeName = null;
    /** The list itself. */
  private List list = null;
    /** Role attribute. */
  private String role = null;

  /**
   * Default constructor.
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
     * Set property.
     */
  public void setName(String name)
  {
  this.attributeName = name;
  }

    /**
     * Get property.
     */
  public String getName()
  {
  return attributeName;
  }

    /**
     * Set role attribute.
     * @param role The role the user must be in to store content.
     */
   public void setRole(String role)
   {
   this.role = role;
   }

    /**
     * Get role attribute.
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
     * Set property.
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
     * @throws JspException If we can't find an appropriate enclosing tag.
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
