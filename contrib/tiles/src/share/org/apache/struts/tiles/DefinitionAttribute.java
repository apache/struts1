/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/DefinitionAttribute.java,v 1.2 2001/12/27 17:35:37 cedric Exp $
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
   * Attribute representing a Component Definition.
   * This attribute definition contains a Component definition.
   */
public class DefinitionAttribute extends UntyppedAttribute {

  public DefinitionAttribute( String value )
    {
    super( value);
    }

}
