/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/portal/UserMenuSettingsAction.java,v 1.2 2002/11/16 04:58:48 jmitchell Exp $
 * $Revision: 1.2 $
 * $Date: 2002/11/16 04:58:48 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.struts.webapp.tiles.portal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.actions.TilesAction;
import org.apache.struts.tiles.beans.MenuItem;

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
