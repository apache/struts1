package org.apache.struts.example.tiles.test;

import org.apache.struts.tiles.ControllerSupport;
import org.apache.struts.tiles.ComponentContext;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;


  /**
   * Controller example.
   * This controller modify title by adding "ok".
   * @author Cedric Dumoulin
   */
public class TestTileController extends ControllerSupport
{

  public TestTileController()
    {
    }

   /**
    * Method associated to a tile and called when immediately before tile is included.
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
   System.out.println( "Controller called" );


   String title = (String)tileContext.getAttribute("title");
   title += "- controller called";
   tileContext.putAttribute( "title", title);
   }


}