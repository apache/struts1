/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/actions/TilesAction.java,v 1.5 2003/02/27 19:20:36 cedric Exp $
 * $Revision: 1.5 $
 * $Date: 2003/02/27 19:20:36 $
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

import org.apache.struts.tiles.ComponentContext;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Base class for Tiles Actions.
 * This class has the same role as Struts Action. It provides a method execute(...)
 * called when action is invoked. The difference is, that the execute() method takes
 * an additional parameter : tile context.
 * This class extends Struts Action. Subclasses should override
 * execute(ComponentContext ...) method instead of Struts
 * execute(ActionMapping ...) method.
 * Backward compatibility is ensured with the perform(ComponentContext ...) method.
 * @version $Revision: 1.5 $ $Date: 2003/02/27 19:20:36 $
 */

public abstract class TilesAction extends Action
{

  /**
   * Original Struts Action's method.
   * Retrieve current Tile context and call TilesAction execute method.
   * Do not overload this method !
   *
   * @param mapping The ActionMapping used to select this instance.
   * @param form The optional ActionForm bean for this request (if any).
   * @param request The HTTP request we are processing.
   * @param response The HTTP response we are creating.
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
    // Try to retrieve tile context
  ComponentContext context = ComponentContext.getContext( request );
    if( context == null )
      {
      throw new ServletException( "Can't find Tile context for '" + this.getClass().getName()
                                + "'. TilesAction subclasses must be called from a Tile" );
      }
  return execute( context, mapping, form, request, response );
  }

  /**
   * Process the specified HTTP request and create the corresponding HTTP
   * response (or forward to another web component that will create it),
   * with provision for handling exceptions thrown by the business logic.
   * <br>
   * Override this method to provide functionality.
   *
   * @param context The current Tile context, containing Tile attributes.
   * @param mapping The ActionMapping used to select this instance.
   * @param form The optional ActionForm bean for this request (if any).
   * @param request The HTTP request we are processing.
   * @param response The HTTP response we are creating.
   *
   * @exception Exception if the application business logic throws
   *  an exception
   * @since Struts 1.1
   */
  public ActionForward execute(ComponentContext context,
                               ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
      throws Exception
  {
    // Call old method for backward compatibility.
  return perform( context, mapping, form, request, response );
  }

    /**
     * Process the specified HTTP request and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param context The current Tile context, containing Tile attributes.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request (if any).
     * @param request The HTTP request we are processing.
     * @param response The HTTP response we are creating.
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     * @deprecated Use the <code>execute()</code> method instead
     */
    public ActionForward perform( ComponentContext context,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                          throws IOException, ServletException
    {
    return null;
    }

}
