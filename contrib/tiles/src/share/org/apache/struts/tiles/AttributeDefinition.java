/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/AttributeDefinition.java,v 1.1 2001/08/01 14:36:41 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:41 $
 * $Author: cedric $
 *
 */

 package org.apache.struts.tiles;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;

  /**
   * Attribute definition used in a component definition.
   *
   */
public interface AttributeDefinition extends Serializable
{

    /**
     * Return value hold by this typed attribute.
     */
  public Object getValue();
  
    /**
     * Set role attribute.
     */
  public void setRole(String role);
}
