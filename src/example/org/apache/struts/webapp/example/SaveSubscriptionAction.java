/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/SaveSubscriptionAction.java,v 1.15 2003/09/04 16:03:42 husted Exp $
 * $Revision: 1.15 $
 * $Date: 2003/09/04 16:03:42 $
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

package org.apache.struts.webapp.example;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * Implementation of <strong>Action</strong> that validates and creates or
 * updates the mail subscription entered by the user.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.15 $ $Date: 2003/09/04 16:03:42 $
 */

public final class SaveSubscriptionAction extends Action {

    // ----------------------------------------------------- Instance Variables

    /**
     * The <code>Log</code> instance for this application.
     */
    private Log log = LogFactory.getLog("org.apache.struts.webapp.Example");

    // --------------------------------------------------------- Public Methods

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception Exception if the application business logic throws
     *  an exception
     */
    public ActionForward execute(
        ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request,
        HttpServletResponse response)
        throws Exception {

        // Extract attributes and parameters we will need
        MessageResources messages = getResources(request);
        HttpSession session = request.getSession();
        SubscriptionForm subform = (SubscriptionForm) form;
        String action = subform.getAction();
        if (action == null) {
            action = "?";
        }
        if (log.isDebugEnabled()) {
            log.debug("SaveSubscriptionAction:  Processing " + action + " action");
        }

        // Is there a currently logged on user?
        User user = (User) session.getAttribute(Constants.USER_KEY);
        if (user == null) {
            if (log.isTraceEnabled()) {
                log.trace(" User is not logged on in session " + session.getId());
            }
            return (mapping.findForward("logon"));
        }

        // Was this transaction cancelled?
        if (isCancelled(request)) {
            if (log.isTraceEnabled()) {
                log.trace(" Transaction '" + action + "' was cancelled");
            }
            session.removeAttribute(Constants.SUBSCRIPTION_KEY);
            return (mapping.findForward("success"));
        }

        // Is there a related Subscription object?
        Subscription subscription =
            (Subscription) session.getAttribute(Constants.SUBSCRIPTION_KEY);
        if ("Create".equals(action)) {
            subscription = user.createSubscription(request.getParameter("host"));
        }
        if (subscription == null) {
            if (log.isTraceEnabled()) {
                log.trace(
                    " Missing subscription for user '" + user.getUsername() + "'");
            }
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                messages.getMessage("error.noSubscription"));
            return (null);
        }

        // Was this transaction a Delete?
        if (action.equals("Delete")) {
            if (log.isTraceEnabled()) {
                log.trace(
                    " Deleting mail server '"
                        + subscription.getHost()
                        + "' for user '"
                        + user.getUsername()
                        + "'");
            }
            user.removeSubscription(subscription);
            session.removeAttribute(Constants.SUBSCRIPTION_KEY);
            try {
                UserDatabase database =
                    (UserDatabase) servlet.getServletContext().getAttribute(
                        Constants.DATABASE_KEY);
                database.save();
            } catch (Exception e) {
                log.error("Database save", e);
            }
            return (mapping.findForward("success"));
        }

        // All required validations were done by the form itself

        // Update the persistent subscription information
        if (log.isTraceEnabled()) {
            log.trace(" Populating database from form bean");
        }
        try {
            PropertyUtils.copyProperties(subscription, subform);
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t == null)
                t = e;
            log.error("Subscription.populate", t);
            throw new ServletException("Subscription.populate", t);
        } catch (Throwable t) {
            log.error("Subscription.populate", t);
            throw new ServletException("Subscription.populate", t);
        }

        try {
            UserDatabase database =
                (UserDatabase) servlet.getServletContext().getAttribute(
                    Constants.DATABASE_KEY);
            database.save();
        } catch (Exception e) {
            log.error("Database save", e);
        }

        // Remove the obsolete form bean and current subscription
        if (mapping.getAttribute() != null) {
            if ("request".equals(mapping.getScope()))
                request.removeAttribute(mapping.getAttribute());
            else
                session.removeAttribute(mapping.getAttribute());
        }
        session.removeAttribute(Constants.SUBSCRIPTION_KEY);

        // Forward control to the specified success URI
        if (log.isTraceEnabled()) {
            log.trace(" Forwarding to success page");
        }
        return (mapping.findForward("success"));

    }

}
