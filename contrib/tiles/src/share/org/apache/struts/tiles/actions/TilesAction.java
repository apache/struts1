package org.apache.struts.tiles.actions;

import org.apache.struts.tiles.ComponentContext;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

/**
 * Base class for Tiles Actions.
 * This class as the same role as Struts Action. It provides a method perform(...)
 * called when action is invoked. The difference is that the perform() method takes
 * an additional parameter : tile context.
 * This class extends Struts Action. Subclasses should implements new perform() method
 * instead of Struts perform() method.
 * @version $Revision: 1.1 $ $Date: 2001/12/27 17:41:35 $
 */

public abstract class TilesAction extends Action
{

    /**
     * Original Struts Action's method.
     * Retrieve current Tile context, and call Tile's perform method.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                          throws IOException, ServletException
  {
    // Try to retrieve tile context
  ComponentContext context = ComponentContext.getContext( request );
    if( context == null )
      {
      throw new ServletException( "Can't find Tile context for '" + this.getClass().getName()
                                + "'. TilesAction subclasses must be called from a Tile" );
      }
  return perform( context, mapping, form, request, response );
  }

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     * This method should be implemented by subclasses.
     *
     * @param context The current Tile context, containing Tile attributes
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    abstract public ActionForward perform( ComponentContext context,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                          throws IOException, ServletException;
}