/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/application/Attic/ApplicationHandlerImpl.java,v 1.1 2003/03/07 03:22:44 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/07 03:22:44 $
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


import javax.faces.context.FacesContext;
import javax.faces.event.CommandEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FormEvent;
import javax.faces.lifecycle.ApplicationHandler;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.faces.Constants;
import org.apache.struts.util.RequestUtils;


/**
 * <p>Concrete implementation of <code>ApplicationHandler</code> that converts
 * JavaServer Faces <code>CommandEvent</code>s and <code>FormEvent</code>s
 * into execution of the corresponding Struts request processing lifecycle.
 * Events are mapped to Struts actions as follows:</p>
 * <ul>
 * <li><em>CommandEvent</em> - The <code>commandName</code> is assumed to be
 *     the path of an <code>Action</code> to be executed (including the
 *     leading slash).</li>
 * <li><em>FormEvent</em> - The <code>formName</code> is assumed to be
 *     the path of an <code>Action</code> to be executed (including the
 *     leading slash).  The <code>commandName</code> property is used to
 *     determine whether or not a cancel button was pressed.</li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/03/07 03:22:44 $
 */

public class ApplicationHandlerImpl implements ApplicationHandler {


    // ----------------------------------------------------------- Constructors


    /**
     * <p>Construct a new {@link ApplicationHandler} based on the specified
     * parameters.</p>
     *
     * @param servlet ActionServlet instance we are associated with
     */
    public ApplicationHandlerImpl(ActionServlet servlet) {

        this.servlet = servlet;

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The logger for this instance.</p>
     */
    protected static Log log = LogFactory.getLog(ApplicationHandlerImpl.class);


    /**
     * <p>The ActionServlet instance of our controller servlet.</p>
     */
    protected ActionServlet servlet = null;


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Process an event that has been queued for the application.
     * <strong>FIXME</strong> - does the application need to provide any
     * feedback to the lifecycle state machine?</p>
     *
     * @param context FacesContext for the current request
     * @param event FormEvent to be processed
     */
    public boolean processEvent(FacesContext context, FacesEvent event) {

        if (log.isDebugEnabled()) {
            log.debug("Processing event " + event);
        }

        if (event instanceof FormEvent) {
            return (processFormEvent(context, (FormEvent) event));
        } else  if (event instanceof CommandEvent) {
            return (processCommandEvent(context, (CommandEvent) event));
        } else {
            log.error("Skipping unknown event " + event +
                      " of type " + event.getClass().getName());
            return (false);
        }

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Process the specified <code>CommandEvent</code>.</p>
     *
     * @param context FacesContext for the current request
     * @param event CommandEvent to be processed
     */
    public boolean processCommandEvent(FacesContext context,
                                       CommandEvent event) {

        // Treat a command event similarly to a form event
        FormEvent formEvent =
            new FormEvent(event.getComponent(), event.getCommandName(),
                          event.getCommandName());
        return (processFormEvent(context, formEvent));

    }


    /**
     * <p>Process the specified <code>FormEvent</code>.</p>
     *
     * <p><strong>FIXME</strong> - How do <code>/faces/*</code> paths
     * interact with selecting an application module?</p>
     *
     * <p><strong>FIXME</strong> - Make sure that cancel buttons are
     * recognized correctly.</p>
     *
     * @param context FacesContext for the current request
     * @param event FormEvent to be processed
     */
    public boolean processFormEvent(FacesContext context,
                                    FormEvent event) {

        if (log.isDebugEnabled()) {
            log.debug("Processing form event " + event);
        }

        // Post FormEvent as a request attribute (context is there already)
        context.getServletRequest().setAttribute(Constants.FORM_EVENT_KEY,
                                                 event);

        // Invoke the appropriate request processor for this request
        try {
            ServletContext servletContext = context.getServletContext();
            HttpServletRequest request = (HttpServletRequest)
                context.getServletRequest();
            HttpServletResponse response = (HttpServletResponse)
                context.getServletResponse();
            RequestUtils.selectApplication(request, servletContext);
            ModuleConfig modConfig = (ModuleConfig)
                request.getAttribute(Action.APPLICATION_KEY);
            RequestProcessor processor = (RequestProcessor)
                servletContext.getAttribute
                (Action.REQUEST_PROCESSOR_KEY + modConfig.getPrefix());
            if (log.isTraceEnabled()) {
                log.trace("Invoking request processor instance " + processor);
            }
            processor.process(request, response);
        } catch (Exception e) {
            log.error("Exception processing form event " + event, e);
        }

        // Remove the FormEvent request attribute
        context.getServletRequest().removeAttribute(Constants.FORM_EVENT_KEY);
        return (true); // Only one event, because we've done the response

    }





}
