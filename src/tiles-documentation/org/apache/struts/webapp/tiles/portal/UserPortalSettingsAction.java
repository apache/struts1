/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/portal/UserPortalSettingsAction.java,v 1.1 2002/07/11 15:35:20 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2002/07/11 15:35:20 $
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
 * @version $Revision: 1.1 $ $Date: 2002/07/11 15:35:20 $
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
  PortalSettingsForm prefsForm = (PortalSettingsForm)form;

      // Get user portal settings from user context
  PortalSettings settings = UserPortalAction.getSettings( request, context);
  PortalCatalog catalog = UserPortalAction.getPortalCatalog( context, getServlet().getServletContext() );

  if( prefsForm.isSubmitted() )
    {  // read arrays
    //System.out.println("form submitted");

      // Set settings cols according to user choice
    for( int i=0;i<prefsForm.getNumCol(); i++)
      {
      settings.setListAt( i, catalog.getTiles( prefsForm.getNewCol(i)) );
      } // end loop

    //System.out.println( "settings : " +settings.toString() );
    prefsForm.reset();
	  //return null; // (mapping.findForward("viewPortal"));
    }

      // Set lists values to be shown
    for( int i=0;i<settings.getNumCols(); i++ )
      {
      prefsForm.addCol(settings.getListAt(i) );
      prefsForm.addColLabels(catalog.getTileLabels( settings.getListAt(i)) );
      } // end loop

    prefsForm.setChoices(catalog.getTiles() );
    prefsForm.setChoiceLabels(catalog.getTilesLabels() );

    //System.out.println("Exit action UserPortalSettingsAction");
	  return null; //(mapping.findForward("editPortal"));
    }

}
