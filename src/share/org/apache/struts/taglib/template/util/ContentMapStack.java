/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/template/util/Attic/ContentMapStack.java,v 1.3 2002/10/25 23:54:53 dgraham Exp $
 * $Revision: 1.3 $
 * $Date: 2002/10/25 23:54:53 $
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
 * <http://www.apache.org/>.
 *
 */
package org.apache.struts.taglib.template.util;

import javax.servlet.jsp.PageContext;
import java.util.Stack;

/**
 * This class provides access to a stack of ContentMaps in request scope
 * through static methods.
 *
 * @author David Geary
 * @version $Revision: 1.3 $ $Date: 2002/10/25 23:54:53 $
 * @deprecated Use Tiles instead.
 */
public class ContentMapStack {

// ------------------------------------------------------------ Construtors

   /**
     * No instantiations of this class are allowed.
     */
   private ContentMapStack() { } // no instantiations

// -------------------------------------------------- Public Static Methods


   /**
     * Return a reference to the stack. If there is no stack, one is
     * created and placed into request scope associated with the
     * page context.
     *
     * @param pc The page context associated with a custom tag.
     * @deprecated Use Tiles instead.
     */
   public static Stack getStack(PageContext pc) {

      Stack s = (Stack)pc.getAttribute("template-stack",
                                       PageContext.REQUEST_SCOPE);
      if(s == null) {
         s = new Stack();
         pc.setAttribute("template-stack", s,
                         PageContext.REQUEST_SCOPE);
      }
      return s;

   }


   /**
     * Peek at the map on top of the stack.
     * 
     * @param pc The page context associated with a custom tag.
     * @deprecated Use Tiles instead.
     */
   public static ContentMap peek(PageContext pc) {

      return (ContentMap)getStack(pc).peek();

   }


   /**
     * Push a content map onto the stack. 
     *
     * @param pc The page context associated with a custom tag.
     * @param map A content map that gets pushed onto the stack.
     * @deprecated Use Tiles instead.
     */
   public static void push(PageContext pc, ContentMap map) {

      getStack(pc).push(map);

   }


   /**
     * 
     * @param pc The page context associated with a custom tag.
     * @deprecated Use Tiles instead.
     */
   public static ContentMap pop(PageContext pc) {

      return (ContentMap)getStack(pc).pop();

   }

}
