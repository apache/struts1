//Source file: H:\\TEMP\\generated\\org\\apache\\struts\\tiles\\ControllerSupport.java

package org.apache.struts.tiles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

/**
 * Basic implementation of Controller.
 */
public class ControllerSupport implements Controller
{

   /**
    * Method associated to a tile and called when immediately before tile is included.
    * This implementation do nothing.
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

   }
}
