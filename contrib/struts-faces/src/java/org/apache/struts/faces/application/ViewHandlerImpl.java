/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/application/ViewHandlerImpl.java,v 1.1 2003/12/31 07:17:48 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/12/31 07:17:48 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
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

package org.apache.struts.faces.application;


import java.io.IOException;
import java.util.Locale;
import javax.faces.FacesException;
import javax.faces.application.StateManager;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Custom implementation of <code>ViewHandler</code> that modifies
 * <code>renderView()</code> to use <code>RequestDispatcher.include()</code>
 * if the response has already been committed.  All other methods are
 * delegated to the original <code>ViewHandler</code> instance that is
 * passed to our constructor.</p>
 */

public class ViewHandlerImpl implements ViewHandler {


    // ------------------------------------------------------------- Constructor


    /**
     * <p>Construct a new {@link ViewHandlerImpl} that delegates as needed to
     * the specified <code>ViewHandler</code> instance.</p>
     *
     * @param original Original <code>ViewHandler</code> for delegation
     *
     * @exception NullPointerException if <code>original</code>
     *  is <code>null</code>
     */
    public ViewHandlerImpl(ViewHandler original) {

        if (original == null) {
            throw new NullPointerException();
        }
        if (log.isDebugEnabled()) {
            log.debug("Creating new instance, wrapping handler " +
                      original);
        }
        this.original = original;

    }


    // ------------------------------------------------------ Instance Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    private static final Log log =
        LogFactory.getLog(ViewHandlerImpl.class);


    /**
     * <p>The original <code>ViewHandler</code> to which we will delegate
     * most processing calls.</p>
     */
    private ViewHandler original;


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>If the current response has already been committed, use
     * <code>RequestDispatcher.include()</code> to incorporate the current
     * view.  Otherwise, delegate to the original <code>ViewHandler</code>.
     *
     * @param context <code>FacesContext</code> for the current request
     * @param view <code>UIViewRoot</code> of the view to render
     *
     * @exception IOException if an input/output error occurs
     */
    public void renderView(FacesContext context, UIViewRoot view)
        throws IOException {

        // If our response has not been committed, delegate
        HttpServletResponse response = (HttpServletResponse)
            context.getExternalContext().getResponse();
        if (!response.isCommitted()) {
            if (log.isTraceEnabled()) {
                log.trace("Delegate for view id " + view.getViewId());
            }
            original.renderView(context, view);
            return;
        }

        // WARNING:  This logic presumes we are using prefix mapping
        // and don't have to do the automatic adjustment (.faces -> .jsp)
        // that a standard ViewHandler implementation supports for
        // extension mapping

        // Use RequestDispatcher.include() to include this view
        if (log.isTraceEnabled()) {
            log.trace("Include for view id " + view.getViewId());
        }
        ServletContext scontext = (ServletContext)
            context.getExternalContext().getContext();
        HttpServletRequest request = (HttpServletRequest)
            context.getExternalContext().getRequest();
        RequestDispatcher rd = scontext.getRequestDispatcher(view.getViewId());
        try {
            rd.include(request, response);
        } catch (ServletException e) {
            throw new FacesException(e);
        }

    }


    /**
     * <p>Delegate to the original <code>ViewHandler</code>.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param viewId View identifier for the current request
     */
    public UIViewRoot restoreView(FacesContext context, String viewId) {

        return (original.restoreView(context, viewId));

    }


    /**
     * <p>Delegate to the original <code>ViewHandler</code>.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param viewId View identifier for the current request
     */
    public UIViewRoot createView(FacesContext context, String viewId) {

        return (original.createView(context, viewId));

    }


    /**
     * <p>Delegate to the original <code>ViewHandler</code>.</p>
     */
    public StateManager getStateManager() {

        return (original.getStateManager());

    }


    /**
     * <p>Delegate to the original <code>ViewHandler</code>.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     */
    public void writeState(FacesContext context) throws IOException {

        original.writeState(context);

    }


    /**
     * <p>Delegate to the original <code>ViewHandler</code>.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     */
    public String getViewIdPath(FacesContext context, String viewId) {

        return (original.getViewIdPath(context, viewId));

    }


    /**
     * <p>Delegate to the original <code>ViewHandler</code>.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     */
    public Locale calculateLocale(FacesContext context) {

        return (original.calculateLocale(context));

    }


}
