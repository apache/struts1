/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/ComponentConstants.java,v 1.2 2001/09/10 13:02:06 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/09/10 13:02:06 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.taglib.tiles.*;
import javax.servlet.jsp.PageContext;
import org.apache.struts.action.Action;

  /**
   * Constant used by Tiles/Components.
   */
public interface ComponentConstants {

    /** Name used to store Tile/Component context */
  public static final String COMPONENT_CONTEXT = "CompContext";

  public static final int    COMPONENT_SCOPE = 8;
  public static final String LOCALE_KEY = Action.LOCALE_KEY;
  public static final String EXCEPTION_KEY = Action.EXCEPTION_KEY;

}

