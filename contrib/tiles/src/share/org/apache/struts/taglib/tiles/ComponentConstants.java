/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/ComponentConstants.java,v 1.1 2001/08/01 14:36:40 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:40 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.taglib.tiles.*;
import javax.servlet.jsp.PageContext;
import org.apache.struts.action.Action;

public interface ComponentConstants {
           
  public static final String COMPONENT_CONTEXT = "CompContext";

  public static final String COMPONENT_INSTANCES = "ComponentInstances";
  public static final String INSTANCES_MAPPING_FACTORY = "instances.mapping.factory";
  public static final int    COMPONENT_INSTANCES_SCOPE = PageContext.REQUEST_SCOPE;

  public static final int    COMPONENT_SCOPE = 8;
  public static final String LOCALE_KEY = Action.LOCALE_KEY;
  public static final String EXCEPTION_KEY = Action.EXCEPTION_KEY;

  public static final String SELECT_MATCHES_KEY = "com.s1.excalibur.core.ui.taglib.SELECT_MATCHES_KEY";
  public static final String TEMPLATE_KEY = "com.s1.excalibur.core.ui.taglib.TEMPLATE_KEY";
  public static final String TEMPLATE_SCREEN_KEY = "com.s1.excalibur.core.ui.taglib.TEMPLATE_SCREEN_KEY";
  public static final String DEFAULT_TEMPLATE_PAGE = "/template.jsp";
  public static final String TEMPLATE_SCREEN_SUFFIX = ".screen";
  public static final String TEMPLATE_PAGE_PARAMETER = "TemplatePage";
  
}

