/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/template/Attic/GetTag.java,v 1.14 2002/11/12 03:56:09 dgraham Exp $
 * $Revision: 1.14 $
 * $Date: 2002/11/12 03:56:09 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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
 * &lt;http://www.apache.org/&gt;.
 *
 */
package org.apache.struts.taglib.template;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;
import org.apache.struts.taglib.template.util.Content;
import org.apache.struts.taglib.template.util.ContentMap;
import org.apache.struts.taglib.template.util.ContentMapStack;

/**
 * This is the tag handler for &lt;template:get&gt;, which gets 
 * content from the request scope and either includes the content or prints 
 * it, depending upon the value of the content's direct attribute.
 *
 * @author David Geary
 * @version $Revision: 1.14 $ $Date: 2002/11/12 03:56:09 $
 * @deprecated Use Tiles instead.
 */
public class GetTag extends TagSupport {

// ----------------------------------------------------- Instance Variables


   /**
    * Should we flush before including this text?
    */
    private boolean flush = false;


   /**
     * The name of the content that this tag includes (or prints).
     */
   private String name;

   /**
     * The role that the user must be in to retrieve content.
     */
   private String role;

   /**
    * Set the flush-before-include property
    * @param flush The new flush property
    * @deprecated Use Tiles instead.
    */
   public void setFlush(boolean flush) {

      this.flush = flush;

   }

   /**
     * Set the name attribute
     * @param name The name of the content to get.
     * @deprecated Use Tiles instead.
     */
   public void setName(String name) {

      this.name = name;

   }

   /**
     * Set the role attribute
     * @param name The role the user must be in to retrieve content.
     * @deprecated Use Tiles instead.
     */
   public void setRole(String role) {

      this.role = role;

   }

   /**
    * Get the flush-before-include attribute.
    * @deprecated Use Tiles instead.
    */
   public boolean getFlush() {

      return flush;

   }


   /**
     * Get the name attribute.
     * @deprecated Use Tiles instead.
     */
   public String getName() { 

      return name; 

   }

   /**
     * Get the role attribute.
     * @deprecated Use Tiles instead.
     */
   public String getRole() { 

      return role; 

   }

// --------------------------------------------------------- Public Methods

   /**
     * Print content named by setName() or include it, depending
     * on the content's direct attribute.
     * @deprecated Use Tiles instead.
     */
   public int doStartTag() throws JspException {

      HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
   	
      if(role != null && !request.isUserInRole(role))
      	return SKIP_BODY;

      ContentMap  map = ContentMapStack.peek(pageContext);
      Content content = map.get(name);

      if(content != null) {
         if(content.isDirect()) {
            try {
               pageContext.getOut().print(content.toString());
            }
            catch(IOException ex) {
               saveException(ex);
               throw new JspException(ex.getMessage());
            }
         }
         else {
            try {
               if (flush)
                  pageContext.getOut().flush();
               pageContext.include(content.toString());
            } catch (IOException ex) {
              saveException(ex);
              throw new JspException(ex.getMessage());
            } catch(ServletException ex) {
               saveException(ex);
               throw new JspException(ex.getMessage());
            }
         }
      }
      return SKIP_BODY;

   }


   /**
     * Reset member values for reuse. This method calls super.release(),
     * which invokes TagSupport.release(), which typically does nothing.
     * @deprecated Use Tiles instead.
     */
   public void release() {
      super.release();
      name = role = null;
      flush = false;
   }


   /**
    * Save the specified exception in request scope if there is not already
    * one present.
    *
    * @param exception Exception to be conditionally saved
    * @deprecated Use Tiles instead.
    */
    private void saveException(Throwable exception) {

        if (pageContext.getAttribute(Globals.EXCEPTION_KEY, PageContext.REQUEST_SCOPE) != null) {
            return;
        }

        pageContext.setAttribute(Globals.EXCEPTION_KEY, exception, PageContext.REQUEST_SCOPE);
    }


}
