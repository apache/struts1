/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/nested/NestedWriteNestingTag.java,v 1.4 2003/02/28 05:14:01 arron Exp $
 * $Revision: 1.4 $
 * $Date: 2003/02/28 05:14:01 $
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
package org.apache.struts.taglib.nested;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.util.ResponseUtils;

/**
 * NestedWriteNestingTag.
 *
 * Created so developers could have a more elegant way of getting to the
 * underlying nested property their tag properties are referencing.
 *
 * @author Arron Bates
 * @since Struts 1.1
 * @version $Revision: 1.4 $
 */
public class NestedWriteNestingTag extends BodyTagSupport {

  /** Getter method for the <i>property</i> property
   * @return String value of the property property
   */
  public String getProperty() {
    return this.property;
  }

  /** Setter method for the <i>property</i> property
   * @param newProperty new value for the property property
   */
  public void setProperty(String newProperty) {
    this.property = newProperty;
  }


  /** Getter method for the <i>filter</i> property
   * @return String value of the filter property
   */
  public boolean getFilter() {
    return this.filter;
  }

  /** Setter method for the <i>filter</i> property
   * @param newFilter new value for the filter property
   */
  public void setFilter(boolean newFilter) {
    this.filter = newFilter;
  }


  /**
   * Overriding method of the heart of the tag. Gets the relative property
   * and tells the JSP engine to evaluate its body content.
   *
   * @return int JSP continuation directive.
   */
  public int doStartTag() throws JspException {
    // set the original property
    originalProperty = property;

    HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    String nesting = NestedPropertyHelper.getAdjustedProperty(request, property);

    /* write output, filtering if required */
    if (this.filter) {
      ResponseUtils.write(pageContext, ResponseUtils.filter(nesting));
    } else {
      ResponseUtils.write(pageContext, nesting);
    }

    /* continue with page processing */
    return (SKIP_BODY);
  }

  public int doEndTag() throws JspException {
    // do the super thing
    int i = super.doEndTag();
    // reset the property
    property = originalProperty;
    // complete
    return i;
  }


  /**
   * JSP method to release all resources held by the tag.
   */
  public void release() {
    super.release();
    this.filter = false;
    this.property = null;
    this.originalProperty = null;
  }

  /* the usual private member variables */
  private boolean filter = false;
  private String property = null;
  private String originalProperty = null;
}