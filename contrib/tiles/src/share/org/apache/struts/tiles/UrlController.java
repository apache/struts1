package org.apache.struts.tiles;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;


/**
 * Controller including a local URL
 * @author Cedric Dumoulin
 * @version
 */

public class UrlController implements Controller
{

    /** Url associated to this controller */
  protected String url;

    /**
     * Constructor.
     */
  public UrlController( String url )
  {
  this.url=url;
  }

   /**
    * Method associated to a tile and called when immediately before tile is included.
    * This implementation call a Struts Action. No servlet is set by this method.
    *
    * @param tileContext Current tile context.
    * @param request Current request
    * @param response Current response
    * @param servletContext Current servlet context
    */
   public void perform(ComponentContext tileContext,
                       HttpServletRequest request, HttpServletResponse response,
                       ServletContext servletContext)
     throws ServletException, IOException
   {
   RequestDispatcher rd = servletContext.getRequestDispatcher( url );
   if( rd == null )
     throw new ServletException( "Controller can't find url '" + url + "'." );

   rd.include( request, response );
   }

}