/*
 */


package org.apache.struts.example.tiles.portal;

import org.apache.struts.tiles.*;
import org.apache.struts.tiles.actions.TilesAction;

import java.io.IOException;
import java.util.Locale;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
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
 * This action is used as controller for portal settings editor.
 * It does folowing :
 * <ul>
 * <li>Load or create user portal settings</li>
 * <li>Read web form, and set user portal setting accordingly</li>
 * <li>Prepare portal editor needed attributes</li>
 * <li></li>
 * </ul>
 *
 * @author Cedric Dumoulin
 * @version $Revision: 1.1 $ $Date: 2001/12/27 17:37:46 $
 */

public final class UserPortalSettingsAction extends TilesAction {


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
    public ActionForward perform( ComponentContext context,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                          throws IOException, ServletException
  {
  //System.out.println("Enter action UserPortalSettingsAction");
	HttpSession session = request.getSession();
  PortalSettingsForm prefsForm = (PortalSettingsForm)form;

      // Get user portal settings from user context
  PortalSettings settings = UserPortalAction.getSettings( context, session );
  PortalChoices choices = UserPortalAction.getPortalChoices( context, getServlet().getServletContext() );

  if( prefsForm.isSubmitted() )
    {  // read arrays
    //System.out.println("form submitted");

      // Set settings cols according to user choice
    for( int i=0;i<prefsForm.getNumCol(); i++)
      {
      settings.setListAt( i, choices.checkKeys( prefsForm.getNewCol(i)) );
      } // end loop

    //System.out.println( "settings : " +settings.toString() );
    prefsForm.reset();
	  //return null; // (mapping.findForward("viewPortal"));
    }

      // Set lists values to be shown
    for( int i=0;i<settings.getNumCols(); i++ )
      {
      prefsForm.addCol(settings.getListAt(i) );
      prefsForm.addColLabels(choices.getChoiceLabelsForKeys( settings.getListAt(i)) );
      } // end loop

    prefsForm.setChoices(choices.getChoices() );
    prefsForm.setChoiceLabels(choices.getChoiceLabels() );

    //System.out.println("Exit action UserPortalSettingsAction");
	  return null; //(mapping.findForward("editPortal"));
    }

}
