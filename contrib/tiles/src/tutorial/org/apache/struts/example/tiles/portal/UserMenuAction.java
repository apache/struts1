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
 * This implementation extends Struts Action, and also define Tiles Controller interface.
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
 * @version $Revision: 1.2 $ $Date: 2002/02/18 14:50:04 $
 */

public final class UserMenuAction extends TilesAction implements Controller {

      /** debug flag */
    public static boolean debug = false;

      /** Tile attribute containing name used to store user settings in session context */
    public static String USER_SETTINGS_NAME_ATTRIBUTE = "userSettingsName";
      /** Default name used to store settings in session context */
    public static String DEFAULT_USER_SETTINGS_NAME = "tiles.examples.portal.USER_MENU_SETTINGS";

      /** Default name used to store menu catalog in application scope */
    public static String DEFAULT_MENU_CATALOG_NAME = "tiles.examples.portal.MenuCatalog";
      /** Tile attribute containing name used to store menu catalog in application scope */
    public static String MENU_CATALOG_NAME_ATTRIBUTE = "catalogName";
      /** Tile attribute containing name of the settings definition used to initialize catalog */
    public static final String CATALOG_SETTING_ATTRIBUTE = "catalogSettings";

      /** Tile attribute containing items to render   */
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

      // Load user settings from user context
    MenuSettings settings = getUserSettings( request, context);
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
  public static MenuSettings getUserSettings( HttpServletRequest request, ComponentContext context )
    throws ServletException
  {
      // Get current session.
	  HttpSession session = request.getSession();

      // Retrieve attribute name used to store settings.
    String userSettingsName = (String)context.getAttribute( USER_SETTINGS_NAME_ATTRIBUTE );
    if( userSettingsName == null )
      userSettingsName = DEFAULT_USER_SETTINGS_NAME;

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
       * Get catalog of available menu entries.
       * This implementation create catalog list from the provided menu bar entries.
       *
       * as Tiles attribute.
       * Create it from default values if needed.
       */
    static public List getCatalog( ComponentContext context, HttpServletRequest request, ServletContext servletContext)
      throws ServletException
      {
        // Retrieve name used to store catalog in application context.
        // If not found, use default name
      String catalogName = (String)context.getAttribute( MENU_CATALOG_NAME_ATTRIBUTE );
      if(catalogName == null)
        catalogName = DEFAULT_MENU_CATALOG_NAME;

        // Get catalog from context
      List catalog = (List)servletContext.getAttribute( catalogName );
        // If not found, initialize it from provided default menu
      if( catalog == null )
        {
        Object menuBar = context.getAttribute( CATALOG_SETTING_ATTRIBUTE );
        if( menuBar == null )
          throw new ServletException( "Attribute '" + CATALOG_SETTING_ATTRIBUTE + "' must be set. It define entries used in catalog" );
        catalog = new ArrayList();
        extractItems(catalog, menuBar, request, servletContext);
        if( catalog.size() == 0 )
          throw new ServletException( "Can't initialize menu items catalog" );

          // save it for future use
        servletContext.setAttribute( catalogName, catalog );
        } // end if
        return catalog;
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

