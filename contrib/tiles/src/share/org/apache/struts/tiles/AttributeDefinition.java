/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/AttributeDefinition.java,v 1.2 2001/12/27 17:35:37 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/12/27 17:35:37 $
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
