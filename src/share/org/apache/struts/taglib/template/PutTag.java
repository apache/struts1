/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/template/Attic/PutTag.java,v 1.6 2001/01/16 23:48:41 dgeary Exp $
 * $Revision: 1.6 $
 * $Date: 2001/01/16 23:48:41 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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

import java.util.Hashtable;
import java.util.Stack;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.taglib.template.util.Content;

/**
 * Tag handler for &lt;template:put&gt;, which puts content into request scope.
 *
 * @author David Geary
 * @version $Revision: 1.6 $ $Date: 2001/01/16 23:48:41 $
 */
public class PutTag extends BodyTagSupport {

// ----------------------------------------------------- Instance Variables


   /**
     * The content's name.
     */
   private String name;

   /**
     * The role that the user must be in to store content.
     */
   private String role;

   /**
     * The content's URI (or text).
     */
   private String content = null;


   /**
     * Determines whether content is included (false) or printed (true).
     * Content is included (false) by default.
     */
   private String direct = null;

// --------------------------------------------------------- Public Methods


   /**
     * Set the content name.
     */
   public void setName(String name) { 

      this.name = name; 

   }

   /**
     * 
     * @param name The role the user must be in to store content.
     */
   public void setRole(String role) {

      this.role = role;

   }

   /**
     * Set the content's URI (if it's to be included) or text (if it's to
     * be printed).
     */
   public void setContent(String content) {

      this.content = content; 

   }


   /**
     * Set direct to true, and content will be printed directly, instead
     * of included (direct == false).
     */
   public void setDirect(String direct) { 

      this.direct = direct; 

   }


   /**
     * Process the end tag by putting content into the enclosing
     * insert tag.
     *
     * @exception JspException if this tag is not enclosed by 
     * &lt;template:insert&gt;.
     */
   public int doEndTag() throws JspException {

      HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
   	
      if(role != null && !request.isUserInRole(role))
      	return EVAL_PAGE;

      InsertTag insertTag = (InsertTag)getAncestor(
                              "org.apache.struts.taglib.template.InsertTag");

      if(insertTag == null)
         throw new JspException("PutTag.doEndTag(): " +
                                "No InsertTag ancestor");

      insertTag.put(name, new Content(getContent(), getDirect()));

      return EVAL_PAGE;

   }


   /**
     * Reset member variables. 
     */
   public void release() {

      name = content = direct = role = null;

   }


   /**
     * Returns the content associated with this tag.
     */
   private String getContent() throws JspException {

      String bodyAndContentMismatchError = 
                      "Please specify template content in this tag's body " +
                      "or with the content attribute, but not both.",
             bodyAndDirectMismatchError = 
                      "If content is specified in the tag body, the " +
                      "direct attribute must be true.";

      boolean hasBody = hasBody(), contentSpecified = (content != null);

      if((hasBody && contentSpecified) || (!hasBody && !contentSpecified))
         throw new JspException(bodyAndContentMismatchError);

      if(hasBody && direct != null && direct.equalsIgnoreCase("false"))
         throw new JspException(bodyAndDirectMismatchError);

      return hasBody ? bodyContent.getString() : content;

   }


   /**
     * Returns a boolean indicating whether this tag has a body.
     */
   private boolean hasBody() {
      if (bodyContent == null)
         return (false);
      return ! bodyContent.getString().equals("");
   }


   /**
     * Returns the direct attribute associated with this tag.
     */
   private String getDirect() {
      if(hasBody()) return "true";
      else            return direct == null ? "false" : "true";
   }


   /**
     * Convenience method for locating ancestor tags by class name. 
     *
     * @param className The name of the ancestor class.
     */
   private TagSupport getAncestor(String className) 
                                 throws JspException {

      Class klass = null; // can’t name variable "class"
      try {
         klass = Class.forName(className);
      }
      catch(ClassNotFoundException ex) {
         pageContext.setAttribute(Action.EXCEPTION_KEY, ex,
                                  PageContext.REQUEST_SCOPE);
         throw new JspException(ex.getMessage());
      }
      return (TagSupport)findAncestorWithClass(this, klass);

   }
}
