/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/TilesRequestProcessor.java,v 1.1 2002/02/18 14:50:04 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2002/02/18 14:50:04 $
 * $Author: cedric $
 *
 */

package org.apache.struts.tiles;

import org.apache.struts.taglib.tiles.ComponentConstants;

import java.util.Locale;

import org.apache.struts.action.RequestProcessor;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;
import org.apache.struts.config.ApplicationConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.upload.MultipartRequestWrapper;

/**
 * <p><strong>RequestProcessor</strong> contains the processing logic that
 * the Struts controller servlet performs as it receives each servlet request
 * from the container.  </p>
 * <p>This processor subclass the Struts one in order to intercept calls to forward
 * or include. When such call is done, Tiles processor check if the specified uri
 * is a definition name. If true, the definition is retrieved and included. If
 * false, the original uri is included or a forward is performed.
 * </p>
 * @author Cedric Dumoulin
 * @since Tiles 1.1.1
 */
public class TilesRequestProcessor extends RequestProcessor
{
    /** Definitions factory */
  private ComponentDefinitionsFactory definitionsFactory;

    /**
     * Initialize this request processor instance.
     *
     * @param servlet The ActionServlet we are associated with
     * @param appConfig The ApplicationConfig we are associated with.
     * @throw ServletException If an error occur during initialization
     */
    public void init(ActionServlet servlet, ApplicationConfig appConfig)
      throws ServletException
    {
    super.init(servlet, appConfig);
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
      definitionsFactory = DefinitionsUtil.createDefinitionsFactory(getServletContext(), servlet.getServletConfig());
      }
     catch( DefinitionsFactoryException ex )
      {
      log( "Fail to load Tiles definition factory from processor", ex);
      throw new ServletException( ex.getMessage(), ex );
      }
    log( "Tiles definition factory loaded for processor " );
    }


    /**
     * Do a forward using request dispatcher.
     * Forward to requested uri.
     * Uri can be a valid uri, or a definition name. If definition name, the
     * definition is retrieved and included. Otherwise, a forward is done to
     * original uri.
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
          // and definition is found.
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
                               getInternal().getMessage
                               ("requestDispatcher", uri));
      return;
      } // end if

      // If request comes from a previous Tile, do an include.
      // This allows to insert an action in a Tile.
    if( doInclude )
      rd.include(request, response);
     else
      rd.forward(request, response);   // original behavior
   }

    /**
     * Do an include of specified uri.
     * Uri can be a valid uri, or a definition name. If definition name, the
     * definition is retrieved and included. Otherwise,
     * original uri is included.
     * @param uri Uri or Definition name to include
     * @param request Current page request
     * @param response Current page response
     */
  protected void doInclude(String uri, HttpServletRequest request, HttpServletResponse response)
 	  throws IOException, ServletException
    {
    doForward( uri, request, response );
    }

}
