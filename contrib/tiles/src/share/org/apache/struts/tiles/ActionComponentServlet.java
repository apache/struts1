/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/ActionComponentServlet.java,v 1.1 2001/08/01 14:36:41 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:41 $
 * $Author: cedric $
 *
 */

package org.apache.struts.tiles;

import org.apache.struts.taglib.tiles.ComponentConstants;

import java.util.Locale;

import org.apache.struts.action.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.apache.struts.taglib.html.Constants;

/**
 * This servlet extends struts one. It adds channels and screens dispatching
 * capabilities.
 * We overide all methods that do request forward (i.e. 'processActionForward'
 * and 'processValidate') in order to call a new method performing forward.
 * In fact, we copy  methods to overide, and change lines doing forward which
 * now call 'processForward()'. Rest of the method is unchanged.
 * This new method could now be overiden in order to dispatch request before
 * forwarding.
 * Compliant to ActionServlet from struts 2001/03/13 22:31:50
 */
public class ActionComponentServlet extends ActionServlet
{
    /** Definitions factory */
  private ComponentDefinitionsFactory definitionsFactory;

    /**
     * Init method.
     * This method is call on any servlet.
     */
  public void init() throws ServletException
    {
    super.init();
    initComponentDefinitionsMapping();
    }

    /**
     * Read component instance mapping configuration file.
     * This is where we read files properties.
     */
  public void initComponentDefinitionsMapping() throws ServletException // IOException,
    {
     try
      {
        // init component instances
      definitionsFactory = DefinitionsUtil.createDefinitionsFactory(getServletContext(), getServletConfig());
      }
     catch( DefinitionsFactoryException ex )
      {
      throw new ServletException( ex.getMessage(), ex );
      }
    }


    /**
     * Forward to the specified destination, by the specified mechanism,
     * if an <code>ActionForward</code> instance was returned by the
     * <code>Action</code>.
     *
     * @param forward The ActionForward returned by our action
     * @param mapping The ActionMapping we are processing
     * @param formInstance The ActionForm we are processing
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected void processActionForward(ActionForward forward,
                                        ActionMapping mapping,
                                        ActionForm formInstance,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
	throws IOException, ServletException {

	if (forward != null) {
	    String path = forward.getPath();
	    if (forward.getRedirect()) {
	        if (path.startsWith("/"))
                    path = request.getContextPath() + path;
		response.sendRedirect(response.encodeRedirectURL(path));
	    } else {
        processForward( path, request, response );
	    }
	}

    }

    /**
     * Call the <code>validate()</code> method of the specified ActionForm,
     * and forward back to the input form if there are any errors.  Return
     * <code>true</code> if we should continue processing (and call the
     * <code>Action</code> class <code>perform()</code> method), or return
     * <code>false</code> if we have already forwarded control back to the
     * input form.
     *
     * @param mapping The ActionMapping we are processing
     * @param formInstance The ActionForm we are processing
     * @param request The servlet request we are processing
     * @param response The servlet response we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected boolean processValidate(ActionMapping mapping,
        ActionForm formInstance, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {

        if (formInstance == null)
            return (true);
        if (debug >= 1)
            log(" Validating input form properties");

        // Was this submit cancelled?
	if ((request.getParameter(Constants.CANCEL_PROPERTY) != null) ||
            (request.getParameter(Constants.CANCEL_PROPERTY_X) != null)) {
            if (debug >= 1)
                log("  Cancelled transaction, no validation");
            return (true);
        }

        // Has validation been turned off on this mapping?
        if (!mapping.getValidate())
            return (true);

        // Call the validate() method of our ActionForm bean
        ActionErrors errors = formInstance.validate(mapping, request);
        if ((errors == null) || errors.empty()) {
            if (debug >= 1)
                log("  No errors detected, accepting input");
            return (true);
        }
        
        //does our form have a multipart request?
        if (formInstance.getMultipartRequestHandler() != null) {
            //rollback the request
            if (debug > 1) {
                log("  Rolling back the multipart request");
            }
            
            formInstance.getMultipartRequestHandler().rollback();
        }

        // Has an input form been specified for this mapping?
	String uri = mapping.getInput();
        if (uri == null) {
            if (debug >= 1)
                log("  No input form, but validation returned errors");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               internal.getMessage("noInput",
                                                   mapping.getPath()));
            return (false);
        }

	// Save our error messages and return to the input form if possible
	if (debug >= 1)
	    log("  Validation error(s), redirecting to: " + uri);
	request.setAttribute(Action.ERROR_KEY, errors);
  processForward( uri, request, response);
	return (false);

    }

    /**
     * Process forward.
     *
     * 'forward' contains screen name. This later is used to find appropriate mapping.
     * Then, request is forwarded to this mapping.
     */
  protected void processForward(String uri, HttpServletRequest request, HttpServletResponse response)
 	  throws IOException, ServletException
    {
      // Do we do a forward (original) or an include.
    boolean hasParentComponent = false;

     try
        {

          // Check if request comes from a parent component
          // This will be used to do an include rather than a forward
        Object parentContext = request.getAttribute( ComponentConstants.COMPONENT_CONTEXT );
        hasParentComponent = (parentContext!=null );

      // Process instance names only if an instances description exist.
    if( definitionsFactory != null )
      {
      // Get definition of component corresponding to uri.
      // If definition is found, replace uri with path defined in definition.
      ComponentDefinition instance = definitionsFactory.getDefinition(uri, request, getServletContext());

      if( instance != null )
        { // use component / template definition

          // Create component context, put it in request scope, and forward to url.
        ComponentContext context = new ComponentContext( instance );
        request.setAttribute( ComponentConstants.COMPONENT_CONTEXT, context);
        uri = instance.getPath();
        } // end if
      } // end if
      
        }
       catch( DefinitionsFactoryException ex )
        {
        throw new ServletException( ex );
        }

      // Do dispatching
	  RequestDispatcher rd = getServletContext().getRequestDispatcher(uri);
    if (rd == null)
      { // error
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               internal.getMessage("requestDispatcher", uri));
      return;
      } // end if

      // If request comes from a previous component, do an include.
      // This allows to insert an action in a components.
    if( hasParentComponent )
      rd.include(request, response);
     else
      rd.forward(request, response);   // original behavior

/*
   try
     { // original behavior
     rd.forward(request, response);
     }
    catch( IllegalStateException ex )
     { // Response already committed : do include
     rd.include( request, response);
     }
*/
   }


}
