package org.apache.struts.tiles;

import org.apache.struts.tiles.DefinitionsUtil;
import org.apache.struts.tiles.DefinitionsFactoryException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;


/**
 * A simple servlet initializing and loading Tiles factory.
 * This servlet can be declared in web.xml, as well as all initialization
 * parameters available with the specified factory.
 * This servlet is intended to be used in application using Tiles without Struts.
 * @author Cedric Dumoulin
 */

public class TilesServlet extends HttpServlet
{

    /**
     * Initialize this servlet
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
  public void init() throws ServletException
    {
      log(  "Start Tiles initialization");
      System.out.println( "Start Tiles initialization" );
    super.init();

       // init component instances
    try
      {
      System.out.println( "Start try" );
      DefinitionsUtil.createDefinitionsFactory(  getServletContext(), getServletConfig() );
      log(  "Tiles Factory loaded");
      }
     catch( DefinitionsFactoryException ex )
      {
      log(  "Tiles Factory load fail !", ex);
      throw new ServletException(ex );
      }

    }


}
