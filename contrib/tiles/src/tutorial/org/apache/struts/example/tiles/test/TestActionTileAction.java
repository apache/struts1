/*
 */


package org.apache.struts.example.tiles.test;

import org.apache.struts.tiles.*;

import java.io.IOException;
import java.util.Locale;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;

import org.apache.struts.util.RequestUtils;


/**
 * Implementation of <strong>Action</strong> that populates an instance of
 * <code>SubscriptionForm</code> from the currently specified subscription.
 *
 * @author Craig R. McClanahan
 * @author Cedric Dumoulin
 * @version $Revision: 1.2 $ $Date: 2002/02/18 14:50:05 $
 */

public final class TestActionTileAction extends Action {


    // --------------------------------------------------------- Public Methods


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param servlet The ActionServlet making this request
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
	throws IOException, ServletException {

      // Try to retrieve tile context
    ComponentContext context = ComponentContext.getContext( request );
    if( context == null )
      {
      request.setAttribute( "actionError", "Can't get component context.");
      return (mapping.findForward("failure"));
      }
      // Get requested test from tile parameter
    String param;

      // Set a definition in this action
    param = (String)context.getAttribute( "set-definition-name" );
    if( param != null )
      {
      try
        {
          // Read definition from factory, but we can create it here.
        ComponentDefinition definition = DefinitionsUtil.getDefinition( param, request, getServlet().getServletContext() );
        //definition.putAttribute( "attributeName", "aValue" );
        DefinitionsUtil.setActionDefinition( request, definition);
        }
       catch( FactoryNotFoundException ex )
        {
        request.setAttribute( "actionError", "Can't get definition factory.");
        return (mapping.findForward("failure"));
        }
       catch( NoSuchDefinitionException ex )
        {
        request.setAttribute( "actionError", "Can't get definition '" + param +"'.");
        return (mapping.findForward("failure"));
        }
       catch( DefinitionsFactoryException ex )
        {
        request.setAttribute( "actionError", "General error '" + ex.getMessage() +"'.");
        return (mapping.findForward("failure"));
        }
      }

      // Overload a parameter
    param = (String)context.getAttribute( "set-attribute" );
    if( param != null )
      {
      context.putAttribute( param, context.getAttribute( "set-attribute-value" ));
      } // end if

	  return (mapping.findForward("success"));

    }


}
