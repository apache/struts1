/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/actions/DefinitionDispatcherAction.java,v 1.6 2003/02/04 02:23:08 dgraham Exp $
 * $Revision: 1.6 $
 * $Date: 2003/02/04 02:23:08 $
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


package org.apache.struts.tiles.actions;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.DefinitionsUtil;
import org.apache.struts.tiles.FactoryNotFoundException;
import org.apache.struts.tiles.NoSuchDefinitionException;


/**
 * <p>An <strong>Action</strong> that dispatches to a Tiles Definition
 * that is named by the request parameter whose name is specified
 * by the <code>parameter</code> property of the corresponding
 * ActionMapping.
 * This action is usefull in following situations :
 * <li>
 * <ul>To associate an Url to a definition</ul>
 * <ul>To use Struts &lt;html:link&gt; tag on a definition</ul>
 * </li>
 * <p>To configure the use of this action in your
 * <code>struts-config.xml</code> file, create an entry like this:</p>
 *
 * <code>
 *   &lt;action path="/saveSubscription"
 *           type="org.apache.struts.tiles.actions.DefinitionDispatcherAction"
 *           parameter="def"/&gt;
 *     &lt;forward name="success"   path="anything" //&gt;
 *     &lt;forward name="error"     path="path.to.error.page" //&gt;
 * </code>
 *
 * <p>which will use the value of the request parameter named "def"
 * to pick the appropriate definition name.
 * <p>  The value for success doesn't matter. The forward will forward to
 * appropriate definition.
 * <p> The value for error should denote a valid jsp path or definition name.
 *
 * @author Niall Pemberton <niall.pemberton@btInternet.com>
 * @author Craig R. McClanahan
 * @author Cedric Dumoulin
 * @version $Revision: 1.6 $ $Date: 2003/02/04 02:23:08 $
 */

public class DefinitionDispatcherAction extends Action {
    /** Commons Logging instance. */
   protected static Log log = LogFactory.getLog(DefinitionDispatcherAction.class);

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it),
     * with provision for handling exceptions thrown by the business logic.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception Exception if the application business logic throws
     *  an exception
     * @since Struts 1.1
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception
    {
        // Identify the request parameter containing the method name
        // If none defined, use "def"
        String parameter = mapping.getParameter();
        if (parameter == null) {
          parameter = "def";
        }

        // Identify the method name to be dispatched to
        String name = request.getParameter(parameter);
        if (name == null)
          {
          String msg = "Definition dispatcher action : can't get parameter '"
                           + parameter + "'.";
          printError( response, msg );
          return (null);
          }

        // Try to dispatch to requested definition
      try
        {
          // Read definition from factory, but we can create it here.
        ComponentDefinition definition = DefinitionsUtil.getDefinition( name, request, getServlet().getServletContext() );
        if(log.isDebugEnabled())
            log.debug("get Definition " + definition );
        DefinitionsUtil.setActionDefinition( request, definition);
        }
       catch( FactoryNotFoundException ex )
        {
        printError( response, "Error - DefinitionDispatcherAction : Can't get definition factory.");
        return (mapping.findForward("error"));
        }
       catch( NoSuchDefinitionException ex )
        {
        printError( response, "Error - DefinitionDispatcherAction : Can't get definition '" + name +"'.");
        return (mapping.findForward("error"));
        }
       catch( DefinitionsFactoryException ex )
        {
        printError( response, "Error - DefinitionDispatcherAction : General Factory error '" + ex.getMessage() +"'.");
        return (mapping.findForward("error"));
        }
       catch( Exception ex )
        {
        printError( response, "Error - DefinitionDispatcherAction : General error '" + ex.getMessage() +"'.");
        return (mapping.findForward("error"));
        }

   return (mapping.findForward("success"));

   }

  protected void printError( HttpServletResponse response, String msg )
      throws IOException
    {
    response.setContentType("text/plain");
    PrintWriter writer = response.getWriter();
    writer.println(msg);
    writer.flush();
    writer.close();
    }
}

