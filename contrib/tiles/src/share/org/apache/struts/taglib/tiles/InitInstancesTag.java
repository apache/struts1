/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/InitInstancesTag.java,v 1.1 2001/08/01 14:36:40 cedric Exp $
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

import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.ServletException;

import javax.servlet.jsp.PageContext;

public class InitInstancesTag extends TagSupport implements ComponentConstants {
  

  private String filename = null;
  private String scopeName = null;
  private int scope;

  /**
   * default constructor
   */
  public InitInstancesTag() {
    super();
  }

    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        filename = null;
        scopeName = null;
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
  public void setScope(String scope){
    this.scopeName = scope;
  }

    /**
     * Set property
     */
  public void setScope() throws JspException
    {
    if(scopeName==null)
      scope= PageContext.APPLICATION_SCOPE;
    else if( scopeName.equals("request") )
      {
      scope = PageContext.REQUEST_SCOPE;
      }
    else if( scopeName.equals("page") )
      {
      scope = PageContext.PAGE_SCOPE;
      }
    else if( scopeName.equals("session") )
      {
      scope = PageContext.SESSION_SCOPE;
      }
    else if( scopeName.equals("application") )
      {
      scope = PageContext.APPLICATION_SCOPE;
      }
    else
      {
      throw new JspException( "Error - InitInstance tag : unrecognized scope '"
                             + scopeName
                             + "'" );
      }
  }

    /**
     * Set property
     */
  public int doStartTag() throws JspException
  {
  setScope();
  ComponentDefinitionsFactory factory = DefinitionsUtil.getDefinitionsFactory(pageContext);
  if(factory != null )
    return SKIP_BODY;

  Map properties = new HashMap();
  properties.put( DefinitionsUtil.DEFINITIONS_FACTORY_CLASSNAME, filename);
  
  try
    {
    factory = DefinitionsUtil.createDefinitionsFactory(pageContext.getServletContext(), pageContext.getServletConfig());
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
