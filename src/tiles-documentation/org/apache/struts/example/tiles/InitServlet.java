package org.apache.struts.example.tiles;

import org.apache.struts.tiles.DefinitionsUtil;
import org.apache.struts.tiles.DefinitionsFactoryException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;


/**
 * Example of servlet initializing Tiles.
 * This servlet can be declared in web.xml, as well as all initialization
 * parameters available with the specified factory.
 * This servlet is intended to be used in application using Tiles without Struts.
 * @author Cedric Dumoulin
 */

public class InitServlet extends HttpServlet
{

    /**
     * Initialize this servlet
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
  public void init() throws ServletException
    {
      log(  "Start initialization");
      System.out.println( "Start initialization" );
    super.init();

       // init component instances
    try
      {
      DefinitionsUtil.createDefinitionsFactory(  getServletContext(), getServletConfig() );
      log(  "initialized");
      }
     catch( DefinitionsFactoryException ex )
      {
      log(  "Initialization failed", ex);
      throw new ServletException(ex );
      }

    }


}
