/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/template/Attic/InsertTag.java,v 1.11 2002/03/12 05:55:08 martinc Exp $
 * $Revision: 1.11 $
 * $Date: 2002/03/12 05:55:08 $
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
import java.util.Hashtable;
import java.util.Stack;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.taglib.template.util.*;

/**
 * This is the tag handler for &lt;template:insert&gt;, which includes 
 * a template. The tag's body content consists of &lt;template:put&gt;
 * tags, which are accessed by &lt;template:get&gt; in the template.
 *
 * @author David Geary
 * @version $Revision: 1.11 $ $Date: 2002/03/12 05:55:08 $
 */
public class InsertTag extends TagSupport {


// ----------------------------------------------------- Instance Variables


   /**
     * Each insert tag has a map of content. 
     */
   private ContentMap map;


   /**
     * The application-relative URI of the template. 
     */
   private String template;


// --------------------------------------------------------- Public Methods


   /**
     * Set the template attribute. 
     */
   public void setTemplate(String template) {

      this.template = template;

   }

   /**
     * Get the template attribute. 
     */
   public String getTemplate() {

      return template;

   }

   /**
     * Get the map attribute. 
     */
   public ContentMap getContentMap() {

      return map;

   }

   /**
     * Process the start tag by pushing this tag's map onto the
     * content map stack. 
     * See org.apache.struts.taglib.template.util.ContentMapStack.
     */
   public int doStartTag() throws JspException {

      map = new ContentMap();
      ContentMapStack.push(pageContext, map);
      return EVAL_BODY_INCLUDE;

   }

   /**
     * Process the end tag by including the template. 
     */
   public int doEndTag() throws JspException {

      String prefix = "";
      ApplicationConfig config = (ApplicationConfig)
          pageContext.getServletContext().getAttribute(Action.APPLICATION_KEY);
      if (config != null) {
          prefix = config.getPrefix();
      }
      try {
         pageContext.include(prefix + template);
      } catch (IOException ex) {
        saveException(ex);
        throw new JspException(ex.getMessage());
      } catch(ServletException ex) {
         saveException(ex);
         throw new JspException(ex.getMessage());
      }
      ContentMapStack.pop(pageContext);
      return EVAL_PAGE;

   }


   /**
     * This method is a convenience for &lt;template:put&gt; tags for
     * putting content into the map.
     */
   public void put(String name, Content content) {

      map.put(name, content);   

   }


   /**
     * Reset member values for reuse. This method calls super.release(),
     * which invokes TagSupport.release(), which typically does nothing.
     */
   public void release() {

      super.release();
      template = null;
      map = null;

   }


    /**
     * Save the specified exception in request scope if there is not already
     * one present.
     *
     * @param exception Exception to be conditionally saved
     */
    private void saveException(Throwable exception) {

        if (pageContext.getAttribute(Action.EXCEPTION_KEY,
                                     PageContext.REQUEST_SCOPE) != null)
            return;
        pageContext.setAttribute(Action.EXCEPTION_KEY, exception,
                                 PageContext.REQUEST_SCOPE);

    }


}
