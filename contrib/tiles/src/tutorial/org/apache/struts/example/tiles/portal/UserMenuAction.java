/*
 */


package org.apache.struts.example.tiles.portal;

import org.apache.struts.tiles.*;
import org.apache.struts.tiles.actions.TilesAction;
import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.beans.MenuItem;
import org.apache.struts.tiles.DefinitionsUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

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
 * This controller load user menu settings and put them in tile context.
 * If menu settings are not defined for user, defined them based on tiles
 * attributes used as default.
 *
 * This implementation extends Struts Action, and also define Tiles Controller.
 * This allows to use it as well as a controller type or a controller url. If used
 * as controller type, Struts Action functionality are not availables.
 *
 * Tiles input attributes :
 * <ul>
 *   <li>title : menu title</li>
 *   <li>items : Menu entries used as default when user settings is created</li>
 *   <li>defaultChoice : Menus or menu entries porposed as choice to user</li>
 *   <li>storeUnderName : Store user settings under provided name in session context [optional]</li>
 *   <li></li>
 * </ul>
 * Tiles output attributes :
 * <ul>
 *   <li>title : menu title</li>
 *   <li>items : Menu items to display</li>
 *   <li></li>
 * </ul>
 *
 * @author Cedric Dumoulin
 * @version $Revision: 1.1 $ $Date: 2001/12/27 17:37:46 $
 */

public final class UserMenuAction extends TilesAction implements Controller {

      /** debug flag */
    public static boolean debug = false;

      /** Tile attribute name. Value of this tile's attribute is the name used to store settings in user context */
    public static String STORE_UNDER_ATTRIBUTE = "storeUnderName";
      /** Default name used to store settings in session context */
    public static String DEFAULT_STORE_UNDER_NAME = "menu.DEFAULT_STORE_UNDER_NAME";

      /** Name under which item choices are stored in application context */
    public static final String CHOICES_ITEMS_NAME = "menu.AvailableItems";

      /** Tile attribute name. List of menu or items used as choices */
    public static final String CHOICES_ITEMS_ATTRIBUTE = "defaultChoice";

      /** Tile attribute name. User items are stored under this name in tile's context  */
    public static String USER_ITEMS_ATTRIBUTE = "items";

    /**
     * Struts' action perform().
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
    perform( context, request, response, getServlet().getServletContext() );
	  return null; //(mapping.findForward("success"));
    }

   /**
    * Method associated to a tile and called immediately before tile is included.
    * @param tileContext Current tile context.
    * @param request Current request
    * @param response Current response
    * @param servletContext Current servlet context
    */
   public void perform(ComponentContext context,
                       HttpServletRequest request, HttpServletResponse response,
                       ServletContext servletContext)
        throws ServletException, IOException
    {
    if(debug)
      System.out.println("Enter action UserMenuAction");
      // Get current session.
	  HttpSession session = request.getSession();

      // Load user settings from user context
    MenuSettings settings = loadUserSettings( context, session );
      // Set parameters for rendering page
    context.putAttribute( USER_ITEMS_ATTRIBUTE, settings.getItems() );

    if(debug)
      {
      System.out.println( "settings=" + settings );
      System.out.println("Exit action UserMenuAction");
      }
    }

    /**
     * Load user setting.
     * This implementation load setting from user context.
     * If settings are not found, initialized them from default items defined
     * in Tile's context.
     * If settings are not found, initialized them.
     */
  public static MenuSettings loadUserSettings( ComponentContext context, HttpSession session )
    throws ServletException
  {
      // Retrieve attribute name used to store settings.
    String userSettingsName = (String)context.getAttribute( STORE_UNDER_ATTRIBUTE );
    if( userSettingsName == null )
      userSettingsName = DEFAULT_STORE_UNDER_NAME;

      // Get user list from user context
    MenuSettings settings = (MenuSettings)session.getAttribute( userSettingsName );

      // If settings don't exist, create and initialize them
      // Initialization is done from context attribute denoted by ITEMS
    if( settings == null )
      { // List doesn't exist, create it and initialize it from Tiles parameters
      settings = new MenuSettings();
      try {
        settings.addItems( (List)context.getAttribute( USER_ITEMS_ATTRIBUTE ) );
        }
       catch( ClassCastException ex )
        {
        throw new ServletException( "Can't initialize user menu : default items must be a list of items" );
        }
        // Save user settings in session
      session.setAttribute( userSettingsName, settings);
      } // end if

  return settings;
  }

      /**
       * Load list of menu entries available as choices to user.
       * Look in servlet context, or create it from list of object provided
       * as Tiles attribute.
       * Create it from default values if needed.
       */
    static public List loadMenuChoices( ComponentContext context, HttpServletRequest request, ServletContext servletContext)
      throws ServletException
      {
        // Get list of choices from context
      List choices = (List)servletContext.getAttribute( CHOICES_ITEMS_NAME );
        // If not found, initialize it from provided default menu
      if( choices == null )
        {
        Object dflt = context.getAttribute( CHOICES_ITEMS_ATTRIBUTE );
        if( dflt == null )
          throw new ServletException( "Can't find default menu item choices under name '" + CHOICES_ITEMS_ATTRIBUTE + "'" );
        choices = new ArrayList();
        extractItems(choices, dflt, request, servletContext);
        if( choices.size() == 0 )
          throw new ServletException( "Can't initialize menu items choices" );
          // save it for future use
        servletContext.setAttribute( CHOICES_ITEMS_NAME, choices );
        } // end if
        return choices;
      }
      /**
       * Extract menu items from passed object. Items are stored in parameter 'result'.
       * This method allows to create a list of menu entries from existing menus.
       * Check object type class :
       * <li>
       *   <ul>MenuItems : add it</ul>
       *   <ul>ComponentDefinition : get attribute items, or list if not found.
       *       Call ExtractItems with resulting attribute.
       *   </ul>
       *   <ul>List : iterate on list, and call ExtractItems for each element.
       * </li>
       * @param result result list (should be initialized)
       * @param object object to add (MenuItems, Definition, ...)
       * @param request current request
       * @param servletContext current servlet context.
       */
    static private void extractItems( List result, Object object, HttpServletRequest request, ServletContext servletContext)
      {
      if(debug)
        System.out.println( "Extract menu item from '" + object + "'" );
      if( object instanceof String )
        {  // definition name
        try {
          ComponentDefinition def = DefinitionsUtil.getDefinition( (String)object, request, servletContext );
          extractItems( result, def, request, servletContext );
          }
         catch( Exception ex )
          { // silently fail
          }

        }
      else if( object instanceof List )
        {
        List list = (List)object;
        Iterator iter = list.iterator();
        while( iter.hasNext() )
          {
          extractItems( result, iter.next(), request, servletContext );
          } // end loop
        }
       else if( object instanceof ComponentDefinition )
        {
        ComponentDefinition definition = (ComponentDefinition)object;
        Object attribute = definition.getAttribute( "items" );
        if( attribute == null )
          attribute = definition.getAttribute( "list" );
        if( attribute == null )
          {
          return;
          }
        extractItems( result, attribute, request, servletContext );
        }
       else if( object instanceof MenuItem )
        {
        result.add( object );
        }
      }

}

