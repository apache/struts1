/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/DefinitionNameAttribute.java,v 1.2 2001/12/27 17:35:37 cedric Exp $
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

  /**
   * Component attribute.
   * Such attribute value represent an instance name.
   */
public class DefinitionNameAttribute extends UntyppedAttribute {
    /**
     * Constructor.
     */
  public DefinitionNameAttribute( String value )
    {
    super( value);
    }

    /**
     * Constructor.
     */
  public DefinitionNameAttribute( String value ,String role)
    {
    super(value, role);
    }

}
