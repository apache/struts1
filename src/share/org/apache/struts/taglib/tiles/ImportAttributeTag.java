/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/tiles/ImportAttributeTag.java,v 1.7 2003/03/08 19:23:49 dgraham Exp $
 * $Revision: 1.7 $
 * $Date: 2003/03/08 19:23:49 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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

import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.tiles.util.TagUtils;
import org.apache.struts.tiles.ComponentContext;


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
     * Are errors ignored. This is the property for attribute <code>ignore</code>.
     * Default value is <code>false</code>, which throws an exception.
     * Only "attribute not found" - errors are ignored.
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
     * Get the name.
     * @return Name.
     */
    public String getName()
     {
     return (this.name);
     }


    /**
     * Set the name.
     * @param name The new name
     */
    public void setName(String name)
     {
     this.name = name;
     }

    /**
     * Set the scope.
     * @param scope Scope.
     */
    public void setScope(String scope)
      {
      this.scopeName = scope;
      }

    /**
     * Get scope.
     * @return Scope.
     */
  public String getScope()
  {
  return scopeName;
  }

    /**
     * Set ignore flag.
     * @param ignore default: <code>false</code>: Exception is thrown when attribute is not found, set to <code>
     * true</code> to ignore missing attributes silently
     */
  public void setIgnore(boolean ignore)
    {
    this.isErrorIgnored = ignore;
    }

    /**
     * Get ignore flag.
     * @return default: <code>false</code>: Exception is thrown when attribute is not found, set to <code>
     * true</code> to ignore missing attributes silently
     */
  public boolean getIgnore()
  {
  return isErrorIgnored;
  }

    // --------------------------------------------------------- Public Methods


    /**
     * Expose the requested property from component context.
     *
     * @exception JspException On errors processing tag.
     */
  public int doStartTag() throws JspException
    {
      // retrieve component context
    ComponentContext compContext = (ComponentContext)pageContext.getAttribute( ComponentConstants.COMPONENT_CONTEXT, PageContext.REQUEST_SCOPE);
    if( compContext == null )
        throw new JspException ( "Error - tag importAttribute : no tiles context found." );

      // set scope
    scope = TagUtils.getScope( scopeName, PageContext.PAGE_SCOPE );

      // push attribute in requested context.
    if( name != null )
      {
      Object value = compContext.getAttribute(name);
        // Check if value exist and if we must send a runtime exception
      if( value == null )
        if(!isErrorIgnored)
          throw new JspException ( "Error - tag importAttribute : property '"+ name + "' not found in context. Check tag syntax" );
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
     * @exception JspException On errors processing tag.
     */
  public int doEndTag() throws JspException
    {
    return (EVAL_PAGE);
    }

}
