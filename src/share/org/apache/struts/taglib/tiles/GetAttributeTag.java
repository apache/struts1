/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/tiles/GetAttributeTag.java,v 1.6 2002/12/08 06:54:51 rleland Exp $
 * $Revision: 1.6 $
 * $Date: 2002/12/08 06:54:51 $
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

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.tiles.ComponentContext;

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
     * Do we ignore error if attribute is not found.
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
     * Get property
     */
  public String getAttribute()
  {
  return attribute;
  }

    /**
     * Set attribute
     */
  public void setName(String value)
    {
    this.attribute = value;
    }

    /**
     * Get property
     */
  public String getName()
  {
  return attribute;
  }

    /**
     * Set attribute
     */
  public void setIgnore(boolean ignore)
    {
    this.isErrorIgnored = ignore;
    }

    /**
     * Get the property.
     */
  public boolean getIgnore()
  {
  return isErrorIgnored;
  }

    /**
     * Set role attribute
     * @param role The role the user must be in to store content.
     */
   public void setRole(String role) {
      this.role = role;
   }

    /**
     * Get property
     */
  public String getRole()
  {
  return role;
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
