//Source file: H:\\TEMP\\generated\\org\\apache\\struts\\tiles\\Controller.java

package org.apache.struts.tiles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

/**
 * A controller is a piece of code called before rendering a jsp page.
 * A controller can be associated to a tile. See <insert> or <definition> for
 * association syntax.
 */
public interface Controller
{

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
        throws ServletException, IOException;
}
