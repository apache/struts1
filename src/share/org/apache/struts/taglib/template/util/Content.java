/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/template/util/Attic/Content.java,v 1.4 2002/06/25 01:30:41 craigmcc Exp $
 * $Revision: 1.4 $
 * $Date: 2002/06/25 01:30:41 $
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

/**
 *  A utility file for templates.
 *  <p>
 *  This represents template content, which is included by templates. 
 *  Templates can also treat content as plain text and print it to the
 *  implicit out variable.
 * 
 *  This simple class maintain two properties:
 *  <ul>
 *  <li><i>content</i>: A string representing either a URI or text.</li>
 *  <li><i>direct</i>: If true, content is printed; otherwise content is 
 *  included (default is false).</li>
 *  </ul>
 *  </p>
 *
 * @author David Geary
 * @version $Revision: 1.4 $
 */
public class Content implements java.io.Serializable {

// ----------------------------------------------------- Instance Variables


    /**
     *  Templates regard this as content to be either included or 
     *  printed directly.  This is a blank final that is
     *  set at construction.
     * 
     */
    private final String content;


    /**
     *  Represents a boolean - if true, content is included, otherwise
     *  content is printed.  This is a blank final that is set at 
     *  construction.  This is a string instead of a boolean as
     *  a convenience for the tags, whose corresponding attribute
     *  is a string.
     *  
     */
    private final String direct;


// ------------------------------------------------------------ Construtors

    /**
     * The only constructor.
     *
     * @param content The content's URI
     * @param direct Is content printed directly (true) or included (false)?
     */
   public Content(String content, String direct) {

      this.content = content;
      this.direct = direct;

   }


    // --------------------------------------------------------- Public Methods

    /**
     * Return content.
     */
    public String getContent() {

      return (content);

    }

    /**
     * Is content to be printed directly (isDirect() == true)
     * instead of included (isDirect() == false).
     */
    public boolean isDirect() {

      return (Boolean.valueOf(direct).booleanValue());

    }


   /**
     * Returns a string representation of the content.
     */
   public String toString() { 

      return content;

   }

}
