/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/nested/NestedPropertyTag.java,v 1.8 2003/03/06 16:07:13 martinc Exp $
 * $Revision: 1.8 $
 * $Date: 2003/03/06 16:07:13 $
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
 * NestedPropertyTag.
 *
 * The one of only two additions in this nested suite of tags. This is so that
 * you can specify extra levels of nesting in one elegant tag rather than having
 * to propagate and manage an extra dot notated property in nested child tags.
 *
 * It's simply recognised by the helper class and it's property is added to the
 * nesting list.
 *
 * @author Arron Bates
 * @since Struts 1.1
 * @version $Revision: 1.8 $ $Date: 2003/03/06 16:07:13 $
 */
public class NestedPropertyTag extends BodyTagSupport implements NestedNameSupport {

  public String getName() { return null; }
  public void setName(String newNamed) {}

  /** Getter method for the <i>property</i> property
   * @return String value of the property property
   */
  public String getProperty() {
    return this.property;
  }

  /** Setter method for the <i>property</i> property
   * Also, only setting the original property value to those values not
   * set by the nested logic.
   * @param newProperty new value for the property property
   */
  public void setProperty(String newProperty) {
    property = newProperty;
  }

  /**
   * Overriding method of the heart of the tag. Gets the relative property
   * and tells the JSP engine to evaluate its body content.
   *
   * @return int JSP continuation directive.
   */
  public int doStartTag() throws JspException {
    originalProperty = property;

    HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    originalNest = NestedPropertyHelper.getCurrentProperty(request);
    originalName = NestedPropertyHelper.getCurrentName(request, this);

    String nested = NestedPropertyHelper.getAdjustedProperty(request, originalProperty);
    NestedPropertyHelper.setProperty(request, nested);
    NestedPropertyHelper.setName(request, originalName);

    // run the body part
    return (EVAL_BODY_TAG);
  }


  /**
   * Render the resulting content evaluation.
   *
   * @return int JSP continuation directive.
   */
  public int doAfterBody() throws JspException {

    /* Render the output */
    if (bodyContent != null) {
      ResponseUtils.writePrevious(pageContext, bodyContent.getString());
      bodyContent.clearBody();
    }

    return (SKIP_BODY);
  }


  /**
   * Evaluate the rest of the page
   *
   * @return int JSP continuation directive.
   */
  public int doEndTag() throws JspException {
    /* set the reference back */
    HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    if (originalNest == null) {
      NestedPropertyHelper.deleteReference(request);
    } else {
      NestedPropertyHelper.setName(request, originalName);
      NestedPropertyHelper.setProperty(request, originalNest);
    }
    property = originalProperty;
    return (EVAL_PAGE);
  }


  /**
   * JSP method to release all resources held by the tag.
   */
  public void release() {
    super.release();
    this.property = null;
    this.originalNest = null;
    this.originalName = null;
    this.originalProperty = null;
  }

  /* the usual private member variable */
  private String property = null;
  private String originalNest = null;
  private String originalName = null;
  private String originalProperty = null;
}