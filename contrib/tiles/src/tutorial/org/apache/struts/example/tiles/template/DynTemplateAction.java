/*
 */


package org.apache.struts.example.tiles.template;


import org.apache.struts.tiles.ComponentDefinition;

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


public final class DynTemplateAction extends Action {


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

    System.out.println( "Start dynamic definition" );
      // Create template definition
    ComponentDefinition definition = new ComponentDefinition();
    String path = "/tutorial";
      // set definition 'contents'
    //definition.setTemplate( "/tutorial/basic/myFramesetLayout.jsp" );
    definition.put( "title", "My first dynamic frameset page", true );
      // using type="string" is the same as direct=true
    definition.put( "header", path + "/common/header.jsp", "string", null );
    definition.put( "footer", path + "/common/footer.jsp", true );
    definition.put( "menu", path + "/basic/menu.jsp", true );
    definition.put( "body", path + "/basic/helloBody.jsp", true );

    System.out.println( "definition=" + definition );
      // Save our definition as a bean :
    request.setAttribute( "templateDefinition", definition );

	  return (mapping.findForward("success"));
    }

 }
