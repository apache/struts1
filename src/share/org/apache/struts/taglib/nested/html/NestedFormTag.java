/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/nested/html/NestedFormTag.java,v 1.9 2003/02/28 05:15:06 arron Exp $
 * $Revision: 1.9 $
 * $Date: 2003/02/28 05:15:06 $
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
package org.apache.struts.taglib.nested.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.FormTag;
import org.apache.struts.taglib.nested.NestedNameSupport;
import org.apache.struts.taglib.nested.NestedPropertyHelper;

/**
 * NestedFormTag.
 * @author Arron Bates
 * @since Struts 1.1
 * @version $Revision: 1.9 $ $Date: 2003/02/28 05:15:06 $
 */
public class NestedFormTag extends FormTag implements NestedNameSupport {

  /**
   * Get the string value of the "property" property.
   * @return the property property
   */
  public String getProperty() {
    return "";
  }

  /**
   * Setter for the "property" property
   * @param newProperty new value for the property
   */
  public void setProperty(String newProperty) {}


  /**
   * Overriding to allow the chance to set the details of the system, so that
   * dynamic includes can be possible
   * @return int JSP continuation directive.
   */
  public int doStartTag() throws JspException {
    // store original result
    int temp = super.doStartTag();

    HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    // original nesting details
    originalNesting = NestedPropertyHelper.getCurrentProperty(request);
    originalNestingName = NestedPropertyHelper.getCurrentName(request, this);

    // some new details
    NestedPropertyHelper.setProperty(request, null);
    NestedPropertyHelper.setName(request, super.getBeanName());

    // continue
    return temp;
  }

  /**
   * This is only overriden to clean up the include reference
   * @return int JSP continuation directive.
   */
  public int doEndTag() throws JspException {
    // super result
    int temp = super.doEndTag();

    // all done. clean up
    HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    // reset the original nesting values
    if (originalNesting == null) {
      NestedPropertyHelper.deleteReference(request);
    } else {
      NestedPropertyHelper.setProperty(request, originalNesting);
      NestedPropertyHelper.setName(request, originalNestingName);
    }

    // return the super result
    return temp;
  }

  /**
   * Release the tag's resources and reset the values.
   */
  public void release() {
    // let the super release
    super.release();
    // reset the original value place holders
    originalNesting = null;
    originalNestingName = null;
  }

  // original nesting environment
  private String originalNesting = null;
  private String originalNestingName = null;
}