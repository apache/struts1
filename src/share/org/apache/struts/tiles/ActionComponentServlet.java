/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/Attic/ActionComponentServlet.java,v 1.1 2002/06/25 03:14:49 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2002/06/25 03:14:49 $
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


package org.apache.struts.tiles;

import org.apache.struts.taglib.tiles.ComponentConstants;

import java.util.Locale;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.upload.MultipartRequestWrapper;

/**
 * This servlet extends struts one. It adds channels and screens dispatching
 * capabilities.
 * We overide all methods that do request forward (i.e. 'processActionForward'
 * and 'processValidate') in order to call a new method performing forward.
 * In fact, we copy  methods to overide, and change lines doing forward which
 * now call 'processForward()'. Rest of the method is unchanged.
 * This new method could now be overiden in order to dispatch request before
 * forwarding.
 * Compliant to ActionServlet from struts 2001/06/25 00:02:27
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
      log( "Fail to load Tiles definition factory", ex);
      throw new ServletException( ex.getMessage(), ex );
      }
    log( "Tiles definition factory loaded" );
    }


    /**
     * Overload struts1.0 counterpart in order to catch forward calls.
     * This is an exact copy, except the call to RequestDispatcher.forward()
     * replaced by doForward().
     * This method is only used with Struts1.0.x
     *
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
        doForward( path, request, response );
	    }
	}

    }

    /**
     * Overload struts1.0 counterpart in order to catch forward calls.
     * This is an exact copy, except the call to RequestDispatcher.forward()
     * replaced by doForward().
     * This method is only used with Struts1.0.x
     *
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
        //unwrap the multipart request if there is one
        if (request instanceof MultipartRequestWrapper) {
            request = ((MultipartRequestWrapper) request).getRequest();
        }
  doForward( uri, request, response);
	return (false);

    }

    /**
     * Overload struts1.0 counterpart in order to catch forward calls.
     * This is an exact copy, except the call to RequestDispatcher.forward()
     * replaced by doForward().
     * This method is only used with Struts1.0.x
     *
     * Process a forward requested by this mapping, if any.  Return
     * <code>true</code> if processing of this request should continue (i.e.
     * be processed by an Action class), or <code>false</code> if we have
     * already handled this request.
     *
     * @param mapping The ActionMapping we are processing
     * @param request The request we are processing
     * @param response The response we are processing
     *
     * @exception IOException if the included resource throws an exception
     * @exception ServletException if the included resource throws an
     *  exception
     */
    protected boolean processForward(ActionMapping mapping,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws IOException, ServletException {

        // Are we going to process this request?
        String forward = mapping.getForward();
        if (forward == null)
            return (true);

        //unwrap the multipart request if there is one
        if (request instanceof MultipartRequestWrapper) {
            request = ((MultipartRequestWrapper) request).getRequest();
        }
        // process forward and give Tiles a chance to catch definition names
        doForward( forward, request, response);
        return (false);

    }


    /**
     * Overload struts1.0 counterpart in order to catch include calls.
     * This is an exact copy, except the call to RequestDispatcher.include()
     * replaced by doInclude().
     * This method is only used with Struts1.0.x
     *
     * Process an include requested by this mapping, if any.  Return
     * <code>true</code> if processing of this request should continue (i.e.
     * be processed by an Action class), or <code>false</code> if we have
     * already handled this request.
     *
     * @param mapping The ActionMapping we are processing
     * @param request The request we are processing
     * @param response The response we are processing
     *
     * @exception IOException if the included resource throws an exception
     * @exception ServletException if the included resource throws an
     *  exception
     */
    protected boolean processInclude(ActionMapping mapping,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws IOException, ServletException {

        // Are we going to process this request?
        String include = mapping.getInclude();
        if (include == null)
            return (true);

        //unwrap the multipart request if there is one
        if (request instanceof MultipartRequestWrapper) {
            request = ((MultipartRequestWrapper) request).getRequest();
        }
        // process forward and give Tiles a chance to catch definition names
        doForward( include, request, response);
        return (false);

    }

    /**
     * Do forward, and eventually catch uri containing Tiles definition.
     * Method left for compatibility reasons.
     * @param uri Uri or Definition name to forward
     * @param request Current page request
     * @param response Current page response
     * @deprecated use doForward instead
     */
  protected void processForward(String uri, HttpServletRequest request, HttpServletResponse response)
 	  throws IOException, ServletException
    {
    doForward( uri, request, response );
    }

    /**
     * Do a forward, and eventually catch uri containing Tiles definition.
     * If uri is a valid uri, do a forward to it.
     * If uri is a valid definition name, Tiles context is created from definition,
     * and definition path is used as uri.
      * @param uri Uri or Definition name to forward
     * @param request Current page request
     * @param response Current page response
     */
  protected void doForward(String uri, HttpServletRequest request, HttpServletResponse response)
 	  throws IOException, ServletException
    {
      // Do we do a forward (original behavior) or an include ?
    boolean doInclude = false;
      // Controller associated to a definition, if any
    Controller controller = null;
    ComponentContext tileContext = null;

     try
        {
          // Get current tile context if any.
          // If context exist, we will do an include
        tileContext = ComponentContext.getContext( request );
        doInclude = (tileContext!=null );
        ComponentDefinition definition;

          // Process tiles definition names only if a definition factory exist,
          // and definition found.
        if( definitionsFactory != null )
          { // Get definition of tiles/component corresponding to uri.
          definition = definitionsFactory.getDefinition(uri, request, getServletContext());
          if( definition != null )
            { // We have a definition.
              // We use it to complete missing attribute in context.
              // We also get uri, controller.
            uri = definition.getPath();
            controller = definition.getOrCreateController();
            if( tileContext == null )
              {
              tileContext = new ComponentContext( definition.getAttributes() );
              ComponentContext.setContext( tileContext, request);
              }
             else
              tileContext.addMissing( definition.getAttributes() );
            } // end if
          } // end if

          // Process definition set in Action, if any.
        definition = DefinitionsUtil.getActionDefinition(request);
        if( definition != null )
          { // We have a definition.
            // We use it to complete missing attribute in context.
            // We also overload uri and controller if set in definition.
          if(definition.getPath()!=null)
            uri = definition.getPath();
          if(definition.getOrCreateController()!=null)
            controller = definition.getOrCreateController();
          if( tileContext == null )
            {
            tileContext = new ComponentContext( definition.getAttributes() );
            ComponentContext.setContext( tileContext, request);
            }
          else
            tileContext.addMissing( definition.getAttributes() );
          } // end if

        }
       catch( java.lang.InstantiationException ex )
        {
        throw new ServletException( "Can't create associated controller", ex );
        }
       catch( DefinitionsFactoryException ex )
        {
        throw new ServletException( ex );
        }

      // Execute controller associated to definition, if any.
    if(controller !=null)
      {
      controller.perform( tileContext, request, response, getServletContext());
      } // end if

      // Do dispatching : search dispatcher, then dispatch
	  RequestDispatcher rd = getServletContext().getRequestDispatcher(uri);
    if (rd == null)
      { // error
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               internal.getMessage("requestDispatcher", uri));
      return;
      } // end if

      // If request comes from a previous Tile, do an include.
      // This allows to insert an action in a Tile.
    if( doInclude )
      rd.include(request, response);
     else
      rd.forward(request, response);   // original behavior
   }


}
