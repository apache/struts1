/*
 */


package org.apache.struts.example.tiles.portal;

import org.apache.struts.tiles.*;
import org.apache.struts.tiles.actions.TilesAction;

import java.io.IOException;
import java.util.Locale;
//import java.util.Vector;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;

import org.apache.struts.util.RequestUtils;


/**
 * This controller load user portal settings and put them in tile context.
 * If portal settings are not defined for user, defined them based on tiles
 * attributes used as default.
 *
 * @author Cedric Dumoulin
 * @version $Revision: 1.1 $ $Date: 2001/12/27 17:37:46 $
 */

public final class UserPortalAction extends TilesAction {

      /** Name used to store settings in session context */
    public static String DEFAULT_CONTEXT_ID = "portal.USER_PORTAL_SETTINGS";
      /** Tile parameter name */
    public static String PARAM_NUMCOLS = "numCols";
      /** Tile parameter name */
    public static String PARAM_LIST = "list";
      /** Tile parameter name */
    public static String PARAM_LIST_LABELS = "listLabels";
      /** Tile parameter name */
    public static String PARAM_ID = "userContextId";
      /** Tile parameter name */
    public static String PORTAL_CHOICES_ID = "tiles.portal.PortalChoices";

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
    //System.out.println("Enter action UserPortalAction");
      // Get current session.
	  HttpSession session = request.getSession();

      // Get user portal list from user context
    PortalSettings settings = getSettings( context, session );
      // Set parameters for tiles
    context.putAttribute( "numCols", Integer.toString(settings.getNumCols()) );
    for( int i=0; i<settings.getNumCols(); i++ )
      context.putAttribute( "list"+i, settings.getListAt(i) );

    //System.out.println( "settings=" + settings );
    //System.out.println("Exit action UserPortalAction");
	  return null; //(mapping.findForward("success"));
    }

    /**
     * Retrieve user setting from session.
     * If settings are not found, initialized them.
     */
  public static PortalSettings getSettings( ComponentContext context, HttpSession session )
  {
      // Retrieve user context id used to store settings
    String userSettingsId = (String)context.getAttribute( PARAM_ID );
    if( userSettingsId == null )
      userSettingsId = DEFAULT_CONTEXT_ID;

      // Get user portal list from user context
    PortalSettings settings = (PortalSettings)session.getAttribute( userSettingsId );

    if( settings == null )
      { // List doesn't exist, create it and initialize it from Tiles parameters
      settings = new PortalSettings();
      settings.setNumCols( (String)context.getAttribute( PARAM_NUMCOLS ) );
      for( int i=0; i<settings.getNumCols(); i++ )
        {
        List col = (List)context.getAttribute( ((String)PARAM_LIST+i) );
        //List labels = (List)context.getAttribute( ((String)PARAM_LIST_LABELS+i) );
        settings.setListAt( i, col );
        } // end loop

        // Save user settings in session
      session.setAttribute( userSettingsId, settings);
      } // end if

  return settings;
  }

      /**
       * Retrieve portal choices object.
       * Create it from default values if needed.
       */
    static public PortalChoices getPortalChoices( ComponentContext context, ServletContext servletContext)
      {
      PortalChoices choices = (PortalChoices)servletContext.getAttribute( PORTAL_CHOICES_ID );
      if( choices == null )
        { // Initialize choices
        choices = new PortalChoices();
        int numCols = Integer.parseInt( (String)context.getAttribute( PARAM_NUMCOLS ) );
        for( int i=0; i<numCols; i++ )
          {
          List col = (List)context.getAttribute( ((String)PARAM_LIST+i) );
          List labels = (List)context.getAttribute( ((String)PARAM_LIST_LABELS+i) );
          choices.addChoices( col, labels );
          } // end loop
        servletContext.setAttribute( PORTAL_CHOICES_ID, choices );
        } // end if

      return choices;
      }

}

