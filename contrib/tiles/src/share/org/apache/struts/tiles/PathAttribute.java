/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/PathAttribute.java,v 1.2 2001/12/27 17:35:38 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/12/27 17:35:38 $
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
   * Such attribute value represent a path used to include a JSP.
   */
public class PathAttribute extends UntyppedAttribute {

  public PathAttribute( String value )
    {
    super(value);
    }

}
