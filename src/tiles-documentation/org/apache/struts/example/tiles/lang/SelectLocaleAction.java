/*
 */


package org.apache.struts.example.tiles.lang;


import org.apache.struts.taglib.tiles.ComponentConstants;

import java.io.IOException;
import java.util.Locale;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;

public final class SelectLocaleAction extends Action {


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
    public ActionForward perform(
				 ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws IOException, ServletException {

	// Extract parameters we will need
    String requested = (String)request.getParameter( "language" );

    if( requested == null )
 	    return (mapping.findForward("failed"));
    if( requested.equalsIgnoreCase( "FR" ) )
      setLocale( request, Locale.FRANCE );
    if( requested.equalsIgnoreCase( "UK" ) )
      setLocale( request, Locale.UK );
    if( requested.equalsIgnoreCase( "DE" ) )
      setLocale( request, Locale.GERMAN );

    //System.out.println( "action perform called" );
      // Fill in nested classes
	  return (mapping.findForward("success"));
    }

    protected void setLocale( HttpServletRequest request, Locale locale )
      {
      HttpSession session = request.getSession(false);
      if (session != null)
        session.setAttribute(ComponentConstants.LOCALE_KEY, locale);

      }
}
