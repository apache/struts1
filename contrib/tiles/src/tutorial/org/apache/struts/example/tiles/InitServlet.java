package org.apache.struts.example.tiles;

import org.apache.struts.tiles.DefinitionsUtil;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.taglib.tiles.ComponentConstants;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import java.util.Enumeration;

/**
 * <strong>InitServlet</strong> initializes webshop application.
 *
 * @author Cedric Dumoulin
 */

public class InitServlet extends HttpServlet
{

  public static final String DEFAULT_INSTANCES_FILE_NAME = "/WEB-INF/templateInstances.xml";

  public InitServlet()
    {
    }

    /**
     * Initialize this servlet
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
  public void init() throws ServletException
    {
      log(  "Start initialization");
      System.out.println( "Start initialization" );

       // init component instances
    try
      {
      DefinitionsUtil.initDefinitionsFactory(  getServletContext(), getServletConfig() );
      log(  "initialized");
      }
     catch( DefinitionsFactoryException ex )
      {
      log(  "Initialization failed", ex);
      throw new ServletException(ex );
      }

    }


}
