/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/skin/SimpleSwitchLayoutAction.java,v 1.3 2003/07/21 15:18:47 cedric Exp $
 * $Revision: 1.3 $
 * $Date: 2003/07/21 15:18:47 $
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

package org.apache.struts.webapp.tiles.skin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.actions.TilesAction;

  /**
   * Simple Switch Layout
   */
public class SimpleSwitchLayoutAction extends TilesAction
{
    /** debug flag */
  public static boolean debug = true;
    /** Tile's attribute containing layout key */
  public static final String LAYOUT_ATTRIBUTE = "layout.attribute";
    /** Tile attribute containing name used to store user settings in session context */
  public static String USER_SETTINGS_NAME_ATTRIBUTE = "userSettingsName";
    /** Default name used to store settings in session context */
  public static String DEFAULT_USER_SETTINGS_NAME = "examples.tiles.skin.SELECTED_DEFINITION";

    /** Name of catalog in application context */
  public static final String CATALOG_NAME = "examples.tiles.skin.CATALOG_NAME";

    /** Default name used to store menu catalog in application scope */
  public static String DEFAULT_CATALOG_NAME = "tiles.examples.skin.layoutCatalog";
    /** Tile attribute containing name used to store menu catalog in application scope */
  public static String CATALOG_NAME_ATTRIBUTE = "catalogName";
    /** Tile attribute containing name of the settings definition used to initialize catalog */
  public static final String CATALOG_SETTING_ATTRIBUTE = "catalogSettings";

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     * This method should be implemented by subclasses.
     *
     * @param context The current Tile context, containing Tile attributes.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request (if any).
     * @param request The HTTP request we are processing.
     * @param response The HTTP response we are creating.
     *
     * @exception Exception if the application business logic throws
     *  an exception
     */
    public ActionForward execute(
        ComponentContext context,
        ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request,
        HttpServletResponse response)
        throws Exception
    {
    if(debug)
      System.out.println( "Enter SimpleSwitchLayoutAction"  );

    String layoutDir = "/layouts/";
    String userSelection = getUserSetting( context, request );
    //String layout = "classicLayout.jsp";
    String layout = (String)context.getAttribute( LAYOUT_ATTRIBUTE );
    if(layout==null)
      throw new ServletException( "Attribute '" + LAYOUT_ATTRIBUTE + "' is required." );

    String layoutPath = layoutDir+userSelection+ "/" + layout;

    RequestDispatcher rd = getServlet().getServletContext().getRequestDispatcher( layoutPath );
    if(rd==null)
      {
      layoutPath = layoutDir + layout;
      rd = getServlet().getServletContext().getRequestDispatcher( layoutPath );
      if(rd==null)
        throw new ServletException( "SwitchLayout error : Can't find layout '"
                                  + layoutPath + "'." );
      }
    rd.include(request, response);
    if(debug)
      System.out.println( "Exit  SimpleSwitchLayoutAction"  );
    return null;
    }

    /**
     * Retrieve key associated to user.
     * This key denote a definition in catalog.
     * Return user selected key, or "default" if none is set.
     */
  public static String getUserSetting( ComponentContext context, HttpServletRequest request )
  {
  HttpSession session = request.getSession( false );
  if( session == null )
    return null;

    // Retrieve attribute name used to store settings.
  String userSettingsName = (String)context.getAttribute( USER_SETTINGS_NAME_ATTRIBUTE );
  if( userSettingsName == null )
    userSettingsName = DEFAULT_USER_SETTINGS_NAME;

  return (String)session.getAttribute(userSettingsName);
  }

    /**
     * Set user setting value.
     * This key denote a definition in catalog.
     * Return user selected key, or "default" if none is set.
     */
  public static void setUserSetting( ComponentContext context, HttpServletRequest request, String setting )
  {
  HttpSession session = request.getSession();

    // Retrieve attribute name used to store settings.
  String userSettingsName = (String)context.getAttribute( USER_SETTINGS_NAME_ATTRIBUTE );
  if( userSettingsName == null )
    userSettingsName = DEFAULT_USER_SETTINGS_NAME;

  session.setAttribute(userSettingsName, setting);
  }

    /**
     * Get catalog of available skins.
     */
  public static DefinitionCatalog getCatalog( ComponentContext context, HttpServletRequest request, ServletContext servletContext )
    throws ServletException
  {
    // Retrieve name used to store catalog in application context.
    // If not found, use default name
  String catalogName = (String)context.getAttribute( CATALOG_NAME_ATTRIBUTE );
  if(catalogName == null)
    catalogName = DEFAULT_CATALOG_NAME;

  if(debug)
    System.out.println( "Catalog name=" + catalogName );
  try
    {
    DefinitionCatalog catalog = (DefinitionCatalog)servletContext.getAttribute( catalogName );
    if(catalog == null)
      { // create catalog
      if(debug)
        System.out.println( "Create catalog" );
      String catalogSettings = (String)context.getAttribute( CATALOG_SETTING_ATTRIBUTE );
      if(catalogSettings == null)
        throw new ServletException( "Error - CustomSkinAction : attribute '"
                                  + CATALOG_SETTING_ATTRIBUTE
                                  + "' not found in Tile's attributes. Need it to initialize catalog"  );
      catalog = new DefinitionCatalog( catalogSettings, request, servletContext );
      if(debug)
        System.out.println( "Catalog created" );
      servletContext.setAttribute( catalogName, catalog );
      } // end if
    return catalog;
    }
   catch(DefinitionsFactoryException ex )
    {
    if(debug)
        System.out.println( "Exception : " + ex.getMessage() );
    throw new ServletException( ex.getMessage() );
    }
  }
}
