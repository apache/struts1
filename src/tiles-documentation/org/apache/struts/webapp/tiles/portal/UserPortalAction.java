/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/portal/UserPortalAction.java,v 1.2 2002/11/16 04:58:48 jmitchell Exp $
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
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.actions.TilesAction;


/**
 * This controller load user portal settings and put them in tile context.
 * If portal settings are not defined for user, defined them based on tiles
 * attributes used as default.
 *
 * @author Cedric Dumoulin
 * @version $Revision: 1.2 $ $Date: 2002/11/16 04:58:48 $
 */

public final class UserPortalAction extends TilesAction {

      /** Default name used to store settings in session context */
    public static String DEFAULT_USER_SETTINGS_NAME = "tiles.examples.portal.USER_PORTAL_SETTINGS";
      /** Tile attribute containing number of columns in portal */
    public static String NUMCOLS_ATTRIBUTE = "numCols";
      /** Tile attribute containing list prefix name */
    public static String LIST_ATTRIBUTE = "list";
      /** Tile attribute containing list of labels prefix name */
    public static String LIST_LABELS_ATTRIBUTE = "labels";
      /** Tile attribute containing name used to store user settings in session context */
    public static String USER_SETTINGS_NAME_ATTRIBUTE = "userSettingsName";
      /** Name used to store portal catalog in application scope */
    public static String PORTAL_CATALOG_NAME = "tiles.examples.portal.PortalCatalog";

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

      // Get user portal list from user context
    PortalSettings settings = getSettings( request, context);
      // Set parameters for tiles
    context.putAttribute( "numCols", Integer.toString(settings.getNumCols()) );
    for( int i=0; i<settings.getNumCols(); i++ )
      context.putAttribute( "list"+i, settings.getListAt(i) );

    //System.out.println( "settings=" + settings );
    //System.out.println("Exit action UserPortalAction");
	  return null;
    }

    /**
     * Retrieve user setting from session.
     * If settings are not found, initialized them.
     */
  public static PortalSettings getSettings( HttpServletRequest request, ComponentContext context )
  {
      // Get current session.
	  HttpSession session = request.getSession();
      // Retrieve user context id used to store settings
    String userSettingsId = (String)context.getAttribute( USER_SETTINGS_NAME_ATTRIBUTE );
    if( userSettingsId == null )
      userSettingsId = DEFAULT_USER_SETTINGS_NAME;

      // Get user portal list from user context
    PortalSettings settings = (PortalSettings)session.getAttribute( userSettingsId );

    if( settings == null )
      { // List doesn't exist, create it and initialize it from Tiles parameters
      settings = new PortalSettings();
      settings.setNumCols( (String)context.getAttribute( NUMCOLS_ATTRIBUTE ) );
      for( int i=0; i<settings.getNumCols(); i++ )
        {
        List tiles = (List)context.getAttribute( ((String)LIST_ATTRIBUTE+i) );
        settings.setListAt( i, tiles );
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
    static public PortalCatalog getPortalCatalog( ComponentContext context, ServletContext servletContext)
      {
      PortalCatalog catalog = (PortalCatalog)servletContext.getAttribute( PORTAL_CATALOG_NAME );
      if( catalog == null )
        { // Initialize catalog
        catalog = new PortalCatalog();
        int numCols = Integer.parseInt( (String)context.getAttribute( NUMCOLS_ATTRIBUTE ) );
        for( int i=0; i<numCols; i++ )
          {
          List tiles = (List)context.getAttribute( ((String)LIST_ATTRIBUTE+i) );
          List labels = (List)context.getAttribute( ((String)LIST_LABELS_ATTRIBUTE+i) );
          catalog.addTiles( tiles, labels );
          } // end loop
        servletContext.setAttribute( PORTAL_CATALOG_NAME, catalog );
        } // end if

      return catalog;
      }

}

