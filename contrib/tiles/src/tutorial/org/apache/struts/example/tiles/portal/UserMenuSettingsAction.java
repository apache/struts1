package org.apache.struts.example.tiles.portal;

import org.apache.struts.action.Action;
import org.apache.struts.tiles.*;
import org.apache.struts.tiles.actions.TilesAction;
import org.apache.struts.tiles.beans.MenuItem;

import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

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
   * Tiles controller as Struts Action.
   * This controller take a list of lists of MenuItems, and arrange them
   * to be shown by appropriate jsp view.
   * Create and set following attribute in Tile context :
   * <ul>
   *   <li>names : list of names to display</li>
   *   <li>returnedValues : list of corresponding key, or values to return</li>
   *   <li>selecteds : list of boolean indicating whether or not a name is selected</li>
   * </ul>
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
 *   <li>choiceItems : List of menu items proposed as a choice</li>
 *   <li>userItems : List of user actual menu items</li>
 * </ul>
 *
   */
public class UserMenuSettingsAction extends TilesAction
{
      /** debug flag */
    public static boolean debug = false;

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
    if(debug)
      System.out.println("Enter action UserMenuSettingsAction");

    MenuSettingsForm actionForm = (MenuSettingsForm)form;

        // Load user menu settings and available list of choices
    MenuSettings settings = UserMenuAction.getUserSettings( request, context );
    List catalog = UserMenuAction.getCatalog( context, request, getServlet().getServletContext() );

      // Check if form is submitted
      // If true, read, check and store new values submitted by user.
    if( actionForm.isSubmitted() )
      {  // read arrays
      if(debug)
        System.out.println("form submitted");

      settings.reset();
      settings.addItems( getItems(actionForm.getSelected(), catalog) );
      if(debug)
        System.out.println( "settings : " +settings.toString() );
      actionForm.reset();
      //return null; // (mapping.findForward("viewPortal"));
      } // end if

      // Prepare data for view tile
    context.putAttribute( "userItems", settings.getItems() );
    context.putAttribute( "catalog", catalog );

    if(debug)
      System.out.println("Exit action UserMenuSettingsAction");
	  return null;
    }

      /**
       * Check selected items, and return apppropriate list of items.
       * For each item in selected list, check if it exist in catalog.
       * Also check for double.
       * @param selectedKey Key of selected items (generally, link url)
       * @param catalog List of avalaible items to compare against.
       */
  static protected List getItems( String[] selectedKey, List catalog )
    {
    List selectedList = java.util.Arrays.asList( selectedKey );
    List result = new ArrayList(selectedList.size());

      ////////////////
    Iterator iter = selectedList.iterator();
    while( iter.hasNext() )
      {
      MenuItem item = getItem( iter.next(), catalog);
      if( item != null )
        result.add(item );
      } // end loop
    return result;
    }
      /**
       * Get item by its key in list of choices
       * @param key Key of selected items (generally, link url)
       * @param catalog List of avalaible items to compare against.
       * @return corresponding item or null if not found.
       */
  static protected MenuItem getItem( Object key, List catalog )
    {
    Iterator iter = catalog.iterator();
    while( iter.hasNext() )
      {
      MenuItem item = (MenuItem)iter.next();
      if( item.getLink().equals( key) )
        return item;
      } // end loop
    return null;
    }
}