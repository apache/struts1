/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/application/ActionListenerImpl.java,v 1.1 2003/06/04 17:38:13 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/06/04 17:38:13 $
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
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.faces.Constants;
import org.apache.struts.util.RequestUtils;


/**
 * <p>Concrete implementation of <code>ActionListener</code> that replaces
 * the default provided implementation.  It converts application-level events
 * into execution of the corresponding Struts request processing lifecycle.
 * </p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/06/04 17:38:13 $
 */

public class ActionListenerImpl implements ActionListener {


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The logger for this instance.</p>
     */
    protected static Log log = LogFactory.getLog(ActionListenerImpl.class);



    // --------------------------------------------------------- Public Methods


    /**
     * <p>Describe the phase identifier for which we are interested in
     * processing events.</p>
     */
    public PhaseId getPhaseId() {

        return (PhaseId.INVOKE_APPLICATION);

    }


    /**
     * <p>Process the specified <code>ActionEvent</code>.</p>
     *
     * @param event The <code>ActionEvent</code> to be processed
     *
     * @exception AbortProcessingException to signal that no further
     *  event processing should be performed
     */
    public void processAction(ActionEvent event)
        throws AbortProcessingException {

        // Acquire Servlet API Object References
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext)
            context.getExternalContext().getContext();
        HttpServletRequest request = (HttpServletRequest)
            context.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse)
            context.getExternalContext().getResponse();

        // Log this event if requested
        if (log.isDebugEnabled()) {
            log.debug("Processing ActionEvent " + event +
                      " from source component " +
                      event.getComponent().getClientId(context));
        }

        // Invoke the appropriate request processor for this request
        try {
            request.setAttribute(Constants.ACTION_EVENT_KEY, event);
            RequestUtils.selectModule(request, servletContext);
            ModuleConfig moduleConfig = (ModuleConfig)
                request.getAttribute(Globals.MODULE_KEY);
            if (log.isTraceEnabled()) {
                log.trace("Assigned to module with prefix '" +
                          moduleConfig.getPrefix() + "'");
            }
            RequestProcessor processor = (RequestProcessor)
                servletContext.getAttribute
                (Globals.REQUEST_PROCESSOR_KEY + moduleConfig.getPrefix());
            if (log.isTraceEnabled()) {
                log.trace("Invoking request processor instance " + processor);
            }
            processor.process(request, response);
        } catch (Exception e) {
            log.error("Exception processing action event " + event, e);
        } finally {
            request.removeAttribute(Constants.ACTION_EVENT_KEY);
        }

    }


    // ------------------------------------------------------ Protected Methods


}
