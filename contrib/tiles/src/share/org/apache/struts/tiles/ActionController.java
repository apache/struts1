//Source file: H:\\TEMP\\generated\\org\\apache\\struts\\tiles\\ActionController.java

package org.apache.struts.tiles;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Struts wrapper  implementation of Controller.
 * This implementation allows to wrap a Struts Action in a Controller
 */
public class ActionController implements Controller
{
    /** Struts action wrapped */
  private Action action;

   /**
    * Constructor.
    */
   public ActionController( Action action )
   {
    this.action = action;
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
   action.perform(null, null, request, response);
   }
}
