/*
 */


package org.apache.struts.example.tiles.dynPortal;

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
 * @version $Revision: 1.1 $ $Date: 2001/10/08 13:40:14 $
 */

public final class SetPortalPrefsAction extends Action {


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
                          throws IOException, ServletException
  {
    System.out.println("Enter action SetPortalPrefsAction");
	HttpSession session = request.getSession();
  PortalPrefsForm prefsForm = (PortalPrefsForm)form;

          // Try to retrieve tile context
    ComponentContext context = ComponentContext.getContext( request );
    if( context == null )
      {
      throw new ServletException( "This action must be called by a Tile, not directly" );
      }

      // Get user portal list from user context
    PortalSettings settings = RetrievePortalAction.getSettings( context, session );

  if( prefsForm.isSubmitted() )
    {  // read arrays
    System.out.println("form submitted");

      // Set settings cols according to user choice
    for( int i=0;i<prefsForm.getNumCol(); i++)
      {
      settings.resetListAt( i, prefsForm.getNewCol(i));
      } // end loop


    //settings.resetListAt( 0, prefsForm.getL0());
    //settings.resetListAt( 1, prefsForm.getL1());
    prefsForm.reset();
	  return (mapping.findForward("portal"));
    }

      // Set lists values to be shown
    for( int i=0;i<settings.getNumCols(); i++ )
      {
      prefsForm.addCol(settings.getListAt(i) );
      prefsForm.addColLabels(settings.getListLabelAt(i) );
      } // end loop

    prefsForm.setChoices(settings.getChoices() );
    prefsForm.setChoiceLabels(settings.getChoiceLabels() );

    System.out.println("Exit action SetPortalPrefsAction");
	  return (mapping.findForward("preferences"));
    }


}
