/*
 */


package org.apache.struts.tiles.actions;

import org.apache.struts.tiles.DefinitionsUtil;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.ComponentDefinitionsFactory;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * <p>A standard <strong>Action</strong> that calls the
 * <code>reload()</code> method of our controller servlet to
 * reload its configuration information from the configuration
 * files (which have presumably been updated) dynamically.</p>
 *
 * @author Craig R. McClanahan
 * @author Cedric Dumoulin
 * @version $Revision: 1.1 $ $Date: 2001/12/27 17:41:35 $
 */

public final class ViewDefinitionsAction extends Action {

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
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
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();

        try {
          ServletContext context = getServlet().getServletContext();
            ComponentDefinitionsFactory factory = DefinitionsUtil.getDefinitionsFactory(context );
            writer.println( factory );
        } catch (Exception e) {
            writer.println("FAIL - " + e.toString());
            getServlet().log("ReloadAction", e);
        }

        writer.flush();
        writer.close();

        return (null);

    }

}

