/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/nested/NestedReference.java,v 1.3 2003/04/19 00:03:53 dgraham Exp $
 * $Revision: 1.3 $
 * $Date: 2003/04/19 00:03:53 $
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

import java.io.Serializable;

/**
 * So that a nested hierarchy can penetrate a dynamic JSP include, this class
 * will hold the details of a bean name and nested property.
 *
 * @author Arron Bates
 * @since Struts 1.1
 * @version $Revision: 1.3 $
 */
public class NestedReference implements Serializable {

  /**
   * Empty constructor.
   */
  public NestedReference() {}
  
  
  /**
   * Constructor takes the all the relevant details to init the object.
   * @param name String name of the bean that the include is to reference
   * @param property String nested property value that the include will be
   *                 continuing on with.
   */
  public NestedReference(String name, String property) {
    this.beanName = name;
    this.property = property;
  }
  
  /** Getter for the bean name
   * @return String value that will be the bean's reference
   */
  public String getBeanName() {
    return beanName;
  }
  
  /** Setter for the bean name
   * @param newName String value to set the bean reference.
   */
  public void setBeanName(String newName) {
    this.beanName = newName;
  }
  
  /** Getter for the nested property
   * @return String value that is the nested property for the current nesting
   */
  public String getNestedProperty() {
    return this.property;
  }
  
  /** Setter for the nested property
   * @param newProperty String value of the new current nesting level
   */
  public void setNestedProperty(String newProperty) {
    this.property = newProperty;
  }
  
  /* Usual member variables */
  private String beanName;
  private String property;
}