/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/dynPortal/RetrievePortalAction.java,v 1.5 2004/01/10 21:03:40 dgraham Exp $
 * $Revision: 1.5 $
 * $Date: 2004/01/10 21:03:40 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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

package org.apache.struts.webapp.tiles.dynPortal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;


/**
 * Implementation of <strong>Action</strong> that populates an instance of
 * <code>SubscriptionForm</code> from the currently specified subscription.
 *
 */

public final class RetrievePortalAction extends Action {

      /** Name use to store settings in session context */
    public static String USER_PORTAL_SETTINGS = "DynamicPortal.USER_PORTAL_SETTINGS";
      /** Tile parameter name */
    public static String PARAM_NUMCOLS = "numCols";
      /** Tile parameter name */
    public static String PARAM_LIST = "list";
      /** Tile parameter name */
    public static String PARAM_LIST_LABELS = "listLabels";
    // --------------------------------------------------------- Public Methods


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception Exception if the application business logic throws
     *  an exception
     */
    public ActionForward execute(
				 ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws Exception {
    System.out.println("Enter action RetrievePortalAction");
      // Get current session.
	  HttpSession session = request.getSession();

          // Try to retrieve tile context
    ComponentContext context = ComponentContext.getContext( request );
    if( context == null )
      {
      throw new ServletException( "This action must be called by a Tile, not directly" );
      }

      // Get user portal list from user context
    PortalSettings settings = getSettings( context, session );

      // Set parameters for tiles
    context.putAttribute( "numCols", Integer.toString(settings.getNumCols()) );
    for( int i=0; i<settings.getNumCols(); i++ )
      context.putAttribute( "list"+i, settings.getListAt(i) );

    System.out.println("Exit action RetrievePortalAction");
	  return (mapping.findForward("success"));
    }

    /**
     * Retrieve user setting from session.
     * If settings are not found, initialized them.
     */
  public static PortalSettings getSettings( ComponentContext context, HttpSession session )
  {
      // Get user portal list from user context
    PortalSettings settings = (PortalSettings)session.getAttribute( USER_PORTAL_SETTINGS );

    if( settings == null )
      { // List doesn't exist, create it and initialize it from Tiles parameters
      settings = new PortalSettings();
      settings.setNumCols( (String)context.getAttribute( PARAM_NUMCOLS ) );
      for( int i=0; i<settings.getNumCols(); i++ )
        {
        List col = (List)context.getAttribute( ((String)PARAM_LIST+i) );
        List labels = (List)context.getAttribute( ((String)PARAM_LIST_LABELS+i) );
        settings.addChoices( col, labels );
        settings.addList( col );
        } // end loop

        // Save user settings in session
      session.setAttribute( USER_PORTAL_SETTINGS, settings);
      } // end if

  return settings;
  }


}

