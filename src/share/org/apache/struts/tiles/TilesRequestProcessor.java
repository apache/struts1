/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/TilesRequestProcessor.java,v 1.19 2003/03/27 03:06:12 dgraham Exp $
 * $Revision: 1.19 $
 * $Date: 2003/03/27 03:06:12 $
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


package org.apache.struts.tiles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;

/**
 * <p><strong>RequestProcessor</strong> contains the processing logic that
 * the Struts controller servlet performs as it receives each servlet request
 * from the container.</p>
 * <p>This processor subclasses the Struts RequestProcessor in order to intercept calls to forward
 * or include. When such calls are done, the Tiles processor checks if the specified URI
 * is a definition name. If true, the definition is retrieved and included. If
 * false, the original URI is included or a forward is performed.
 * <p>
 * Actually, catching is done by overloading the following methods:
 * <ul>
 * <li>{@link #processForwardConfig(HttpServletRequest,HttpServletResponse,ForwardConfig)}</li>
 * <li>{@link #internalModuleRelativeForward(String, HttpServletRequest , HttpServletResponse)}</li>
 * <li>{@link #internalModuleRelativeInclude(String, HttpServletRequest , HttpServletResponse)}</li>
 * </ul>
 * </p>
 * @author Cedric Dumoulin
 * @since Struts 1.1
 */
public class TilesRequestProcessor extends RequestProcessor
{
    /** Definitions factory */
  protected DefinitionsFactory definitionsFactory;

     /**
      * Commons Logging instance.
      */
     protected static Log log = LogFactory.getLog(TilesRequestProcessor.class);

    /**
     * Initialize this request processor instance.
     *
     * @param servlet The ActionServlet we are associated with.
     * @param moduleConfig The ModuleConfig we are associated with.
     * @throws ServletException If an error occurs during initialization.
     */
    public void init(ActionServlet servlet, ModuleConfig moduleConfig)
      throws ServletException
    {
       super.init(servlet, moduleConfig);
       initDefinitionsMapping();
    }

    /**
     * Read component instance mapping configuration file.
     * This is where we read files properties.
     */
  protected void initDefinitionsMapping() throws ServletException
    {
      // Retrieve and set factory for this modules
    definitionsFactory = ((TilesUtilStrutsImpl)TilesUtil.getTilesUtil()).getDefinitionsFactory(getServletContext(), moduleConfig);
    if( definitionsFactory == null )
      {  // problem !
        if(log.isInfoEnabled())
          {
            log.info( "Definition Factory not found for module '"
                + moduleConfig.getPrefix() + "'. "
                + "Have you declared the appropriate plugin in struts-config.xml ?" );
          }
      return;
      }
    if(log.isInfoEnabled())
      {
            log.info("Tiles definition factory found for request processor '" + moduleConfig.getPrefix() + "'.");
      }
    }


    /**
     * Process a Tile definition name.
     * This method tries to process the parameter <code>definitionName</code> as a definition name.
     * It returns <code>true</code> if a definition has been processed, or <code>false</code> otherwise.
     * Parameter <code>contextRelative</code> is not used in this implementation.
     *
     * @param definitionName Definition name to insert.
     * @param contextRelative Is the definition marked contextRelative ?
     * @param request Current page request.
     * @param response Current page response.
     * @return <code>true</code> if the method has processed uri as a definition name, <code>false</code> otherwise.
     */
  protected boolean processTilesDefinition(String definitionName, boolean contextRelative, HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    //System.out.println("doForward(" + uri + ")");
      // Do we do a forward (original behavior) or an include ?
    boolean doInclude = false;
      // Controller associated to a definition, if any
    Controller controller = null;
      // Computed uri to include
    String uri = null;
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
          definition = definitionsFactory.getDefinition(definitionName, request, getServletContext());
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
             else {
              tileContext.addMissing( definition.getAttributes() );
             }
            } // end if
          } // end if

          // Process definition set in Action, if any.
        definition = DefinitionsUtil.getActionDefinition(request);
        if( definition != null )
          { // We have a definition.
            // We use it to complete missing attribute in context.
            // We also overload uri and controller if set in definition.
          if (definition.getPath() != null) {
            uri = definition.getPath();
          }
          if (definition.getOrCreateController() != null) {
            controller = definition.getOrCreateController();
          }
          if( tileContext == null )
            {
            tileContext = new ComponentContext( definition.getAttributes() );
            ComponentContext.setContext( tileContext, request);
            }
          else {
            tileContext.addMissing( definition.getAttributes() );
          }
          } // end if

        }
       catch( java.lang.InstantiationException ex )
        {
        if (log.isErrorEnabled())
          {
          log.error("Can't create associated controller", ex);
          }
        throw new ServletException( "Can't create associated controller", ex );
        }
       catch( DefinitionsFactoryException ex )
        {
        throw new ServletException( ex );
        }

      // Have we found a definition ?
    if (uri == null) {
      return false;
    }

      // Process the definition
      // Execute controller associated to definition, if any.
    if(controller !=null)
      {
      controller.perform( tileContext, request, response, getServletContext());
      } // end if

      // If request comes from a previous Tile, do an include.
      // This allows to insert an action in a Tile.
    if (log.isDebugEnabled()) {
      log.debug( "uri=" + uri + " doInclude=" + doInclude);
    }
    if (doInclude) {
      doInclude(uri, request, response);
    } else {
      doForward(uri, request, response);   // original behavior
    }

    return true;
    }

    /**
     * Do a forward using request dispatcher.
     * Uri is a valid uri. If response has already been commited, do an include
     * instead.
     * @param uri Uri or Definition name to forward.
     * @param request Current page request.
     * @param response Current page response.
     */
  protected void doForward(String uri, HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    if (response.isCommitted()) {
      doInclude(uri, request, response);
    } else {
      super.doForward(uri, request, response);
    }
   }

    /**
     * Overloaded method from Struts' RequestProcessor.
     * Forward or redirect to the specified destination by the specified
     * mechanism.
     * This method catches the Struts' actionForward call. It checks if the
     * actionForward is done on a Tiles definition name. If true, process the
     * definition and insert it. If false, call the original parent's method.
     * @param request The servlet request we are processing.
     * @param response The servlet response we are creating.
     * @param forward The ActionForward controlling where we go next.
     *
     * @exception IOException if an input/output error occurs.
     * @exception ServletException if a servlet exception occurs.
     */
    protected void processForwardConfig(HttpServletRequest request,
                                        HttpServletResponse response,
                                        ForwardConfig forward)
        throws IOException, ServletException
    {
      // Required by struts contract
    if (forward == null)
      {
      return;
      }

    if (log.isDebugEnabled()) {
      log.debug( "processForwardConfig("
                        + forward.getPath() + ", "
                        + forward.getContextRelative() + ")" );
    }
      // Try to process the definition.
    if (processTilesDefinition( forward.getPath(), forward.getContextRelative(), request, response))
      {
      if (log.isDebugEnabled()) {
        log.debug( "  '" +forward.getPath() + "' - processed as definition" );
      }
      return;
      }
    if (log.isDebugEnabled()) {
      log.debug( "  '" +forward.getPath() + "' - processed as uri" );
    }
      // forward doesn't contains a definition, let parent do processing
    super.processForwardConfig(request, response, forward );
    }

    /**
     * Catch the call to a module relative forward.
     * If the specified uri is a tiles definition name, insert it.
     * Otherwise, parent processing is called.
     * Do a module relative forward to specified uri using request dispatcher.
     * Uri is relative to the current module. The real uri is computed by prefixing
     * the module name.
     * <strong>This method is used internally and is not part of the public API. It is
     * advised to not use it in subclasses.</strong>
     * @param uri Module-relative URI to forward to.
     * @param request Current page request.
     * @param response Current page response.
     * @since Struts 1.1
     */
    protected void internalModuleRelativeForward(String uri, HttpServletRequest request,
                             HttpServletResponse response)
        throws IOException, ServletException
    {
    if (processTilesDefinition(uri, false, request, response)) {
      return;
    }

    super.internalModuleRelativeForward(uri, request, response);
    }

    /**
     * Do a module relative include to specified uri using request dispatcher.
     * Uri is relative to the current module. The real uri is computed by prefixing
     * the module name.
     * <strong>This method is used internally and is not part of the public API. It is
     * advice to not use it in subclasses.</strong>
     * @param uri Module-relative URI to forward to.
     * @param request Current page request.
     * @param response Current page response.
     * @since Struts 1.1
     */
    protected void internalModuleRelativeInclude(String uri, HttpServletRequest request,
                             HttpServletResponse response)
        throws IOException, ServletException
    {
    if (processTilesDefinition(uri, false, request, response)) {
      return;
    }

    super.internalModuleRelativeInclude(uri, request, response);
    }

    /**
     * Get associated definition factory.
     */
  public DefinitionsFactory getDefinitionsFactory()
  {
    return definitionsFactory;
  }

}
