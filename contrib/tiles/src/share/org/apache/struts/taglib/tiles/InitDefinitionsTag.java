/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/InitDefinitionsTag.java,v 1.1 2001/08/01 14:36:40 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:40 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.tiles.DefinitionsUtil;
import org.apache.struts.tiles.ComponentDefinitionsFactory;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.xmlDefinition.I18nFactorySet;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import javax.servlet.jsp.PageContext;

public class InitDefinitionsTag extends TagSupport implements ComponentConstants {
  

  private String filename = null;
  private String classname = null;

  /**
   * default constructor
   */
  public InitDefinitionsTag() {
    super();
  }

    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        filename = null;
    }

    /**
     * Set property
     */
  public void setFile(String name){
    this.filename = name;
  }

    /**
     * Set property
     */
  public void setClassname(String classname){
    this.classname = classname;
  }

    /**
     * Do start tag
     */
  public int doStartTag() throws JspException
  {
  ComponentDefinitionsFactory factory = DefinitionsUtil.getDefinitionsFactory(pageContext);
  if(factory != null )
    return SKIP_BODY;

  Map properties = new HashMap();
    // Read properties
    // Not so nice, but works for default factory ;-(
  properties.put( I18nFactorySet.DEFINITIONS_CONFIG_PARAMETER_NAME, filename);
  
  try
    {
    factory = DefinitionsUtil.createDefinitionsFactory(pageContext.getServletContext(), properties, classname);
    }
   catch( DefinitionsFactoryException ex )
      {
      ex.printStackTrace();
      throw new JspException( ex.getMessage() + " See console for details" );
      }
  return SKIP_BODY;
  }

    /**
     * Set property
     */
  public int doEndTag() throws JspException {
    return EVAL_PAGE;
  }

}
