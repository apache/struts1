/*
 * $Id$ 
 *
 * Copyright 2001,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.struts.action;

import java.io.IOException;


import javax.servlet.ServletException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import org.apache.commons.workflow.Activity;
import org.apache.commons.workflow.Step;
import org.apache.commons.workflow.StepException;
import org.apache.commons.workflow.Context;
import org.apache.commons.workflow.ContextEvent;
import org.apache.commons.workflow.ContextListener;
import org.apache.commons.workflow.Registry;

import org.apache.commons.workflow.web.WebContext;



/**
 * @author Craig McClanahan
 * @author Ted Husted
 * @version $Rev$ $Date$
 */
public class ActivityAction extends Action implements ContextListener {


// --------------------------------------------------------- Instance Variables


   // ** FOR THREAD-SAFETY THESE MUST BE GLOBAL TO APPLICATION
   // ** AND NOT SPECIFIC TO A GIVEN REQUEST

   protected Registry registry = null;

   protected Activity activity = null;


// --------------------------------------------------------- Protected Methods


    /**
     * Whether the debug level is set to verbose logging.
     */
    protected boolean doDebugLog() {

        return ((servlet.getDebug() > 1));

    }


    /**
     * Whether the debug level is set to listening.
     */
    protected boolean doDebugListener() {

        return ((servlet.getDebug() > 2));

    }


    /**
     * Return Activity by ID
     */
     protected Activity getActivity(String id) {

       //  return (registry.getActivity(id));
       return this.activity;

    }


    /**
     * Return Activity by looking up ID in mapping.
     */
     protected Activity getActivity(ActionMapping mapping) {

        return (getActivity(mapping.getParameter()));

    }


    /**
     * Return context, instantiating it if necessary.
     */
    protected WebContext getContext(ActionMapping mapping,
            HttpServletRequest request, HttpServletResponse response) {

        String contextId = mapping.getParameter();
        HttpSession session = request.getSession();
        WebContext context = (WebContext)
            session.getAttribute(contextId);
        if (context == null) {
            if (doDebugLog())
                servlet.log("{" + session.getId() + "} Creating new Context");
            context = new WebContext();
            // context.setActivity(getActivity(contextId));
            context.setActivity(activity);
            context.setHttpSession(session);
            context.setServletContext(servlet.getServletContext());
            if (doDebugListener())
                context.addContextListener(this);
            session.setAttribute(contextId, context);
            context.call(context.getActivity());
        }
        return context;

    }


    /**
     * Validate context, returning errors if found.
     */
    protected void validateInit(ActionErrors errors,
            Context context, Activity activity) {

        if ((context==null) ||
            (context.getActivity()==null)) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("action.missing.parameter"));
        }

    }


    /**
     * Execute the next stage of the current Activity
     */
    protected void executeContext(WebContext context, Activity activity,
            ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
       throws ServletException {

        synchronized (context) {

            // If we are not already executing our associated Activity, call it
            if (!activity.equals(context.getActivity())) {
                if (doDebugLog())
                    servlet.log("{" + request.getSession().getId() +
                        "} calling Activity " +
                        activity.getId());
                context.call(activity);
            }

            // Associate our context with the current request and response
            context.setServletRequest(request);
            context.setServletResponse(response);

            // Execute our activity until suspended or ended
            try {
                if (doDebugLog())
                    servlet.log("{" + context.getHttpSession().getId() +
                        "} executing Activity " +
                            context.getActivity().getId());
                context.execute();
            } catch (StepException e) {
                if (e.getCause() == null)
                    throw new ServletException(e.getMessage(), e);
                else
                    throw new ServletException(e.getMessage(),
                        e.getCause());
            }
        }
    }


    /**
     * Analyze initialization properties,
     * and report any errors.
     */
    protected void validateContext(ActionErrors errors,
            Context context) {
    }


    /**
     * Analyze result, and determine next forward.
     * <p>
     * If no errors, forward for id of next step is returned.
     * <p>
     * If there are errors, save errors to request, and
     * forward to input. If there is not input property,
     * then forward to local or global "errors" forwarding.
     */
    protected ActionForward nextForward(ActionMapping mapping,
            ActionErrors errors, HttpServletRequest request,
            WebContext context) {

        if (!errors.empty()) {

            // Save for display
            saveErrors(request,errors);

            // Return to input page, if there is one
            if (mapping.getInput()!=null)
                return (new ActionForward(mapping.getInput()));

            // If no input page, use error forwarding
            return (mapping.findForward("error"));
        }

        // If no errors, goto forwarding for next step
        return (mapping.findForward(
            context.getNextStep().getId()));
    }


// --------------------------------------------------------- Public Properties


    /**
     * Set the Registry  for this Action (or application)
     */
    public void setRegistry(Registry registry) {
        this.registry = registry;
    }



// --------------------------------------------------------- Public Methods

    /**
     * Set the controller servlet instance to which we are attached
     * (if servlet is non-null), or release any allocated resources
     * (if servlet is null).
     * Call setRegistry to obtain registry from servlet context.
     */
    public void setServlet(ActionServlet servlet) {
        super.setServlet(servlet);

        if (servlet==null) {
            setRegistry(null);
            this.activity = null;
        }
        else {
            setRegistry((Registry)
                servlet.getServletContext().getAttribute(
                    RegistryServlet.REGISTRY));
            this.activity = (Activity)
                servlet.getServletContext().getAttribute(
                    RegistryServlet.ACTIVITY);
        }
    }


    /**
     * Retrieve or initialize context from session, using ActionMapping
     * parameter as key. Returns action.missing.parameters if parameter
     * missing or not found. Executes the next step of the actitivity.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward execute(ActionMapping mapping,
                 ActionForm form,
                 HttpServletRequest request,
                 HttpServletResponse response)
    throws Exception {

        ActionErrors errors = new ActionErrors();

        WebContext context = getContext(mapping,request,
            response);

        Activity activity = getActivity(mapping);

        validateInit(errors,context,activity);

        if (errors.isEmpty()) {

                executeContext(context,activity,
                    mapping,form,request,response);

        }

        validateContext(errors,context);

        // return (nextForward(mapping,errors,request,context));
        return null;

    } // end ActionForward


    // ------------------------------------------------ ContextListener Methods


    /**
     * Invoked immediately after execution of the related Activity has
     * been completed normally, been suspended, or been aborted by
     * the throwing of a StepException.  The Step included in this event
     * will be the last one to be executed.
     *
     * @param event The <code>ContextEvent</code> that has occurred
     */
    public void afterActivity(ContextEvent event) {

        WebContext context = (WebContext) event.getContext();
        HttpSession session = context.getHttpSession();
        StringBuffer sb = new StringBuffer("{");
        sb.append(session.getId());
        sb.append("} afterActivity");
        servlet.log(sb.toString());

    }


    /**
     * Invoked immediately after the specified Step was executed.
     *
     * @param event The <code>ContextEvent</code> that has occurred
     */
    public void afterStep(ContextEvent event) {

        WebContext context = (WebContext) event.getContext();
        HttpSession session = context.getHttpSession();
        StringBuffer sb = new StringBuffer("{");
        sb.append(session.getId());
        sb.append("} afterStep ");
        sb.append(event.getStep());
        if (context.getSuspend())
            sb.append(" (Suspended)");
        if (context.getNextStep() == null)
            sb.append(" (Finished)");
        servlet.log(sb.toString());
        if (event.getException() != null)
            servlet.log("-->Step threw exception", event.getException());

    }


    /**
     * Invoked immediately before execution of the related Activity has
     * started.  The Step included in this event will be the first one
     * to be executed.
     *
     * @param event The <code>ContextEvent</code> that has occurred
     */
    public void beforeActivity(ContextEvent event) {

        WebContext context = (WebContext) event.getContext();
        HttpSession session = context.getHttpSession();
        StringBuffer sb = new StringBuffer("{");
        sb.append(session.getId());
        sb.append("} beforeActivity");
        servlet.log(sb.toString());

    }


    /**
     * Invoked immediately before the specified Step is executed.
     *
     * @param event The <code>ContextEvent</code> that has occurred
     */
    public void beforeStep(ContextEvent event) {

        WebContext context = (WebContext) event.getContext();
        HttpSession session = context.getHttpSession();
        StringBuffer sb = new StringBuffer("{");
        sb.append(session.getId());
        sb.append("} beforeStep ");
        sb.append(event.getStep());
        servlet.log(sb.toString());

    }


} // end Activity
