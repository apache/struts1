/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/example/org/apache/struts/webapp/example/RegistrationBacking.java,v 1.2 2004/03/08 00:40:48 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2004/03/08 00:40:48 $
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


package org.apache.struts.webapp.example;


import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.component.UIData;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Backing bean for the <code>registration.jsp</code> page.</p>
 */

public class RegistrationBacking {


    // -------------------------------------------------------- Static Variables


    private static final Log log = LogFactory.getLog(RegistrationBacking.class);


    // -------------------------------------------------------------- Properties


    private UIData table = null;


    /**
     * <p>Return the <code>UIData</code> instance we are bound to.</p>
     */
    public UIData getTable() {

        return (this.table);

    }


    /**
     * <p>Set the <code>UIData</code> instance we are bound to.</p>
     *
     * @param table The <code>UIData</code> instance
     */
    public void setTable(UIData table) {

        this.table = table;

    }



    // ----------------------------------------------------------------- Actions


    /**
     * <p>Create a new subscription.</p>
     */
    public String create() {

        if (log.isDebugEnabled()) {
            log.debug("create()");
        }
        FacesContext context = FacesContext.getCurrentInstance();
        StringBuffer url = base(context);
        url.append("?action=Create");
        url.append("&username=");
        User user = (User)
            context.getExternalContext().getSessionMap().get("user");
        url.append(user.getUsername());
        forward(context, url.toString());
        return (null);

    }


    /**
     * <p>Delete an existing subscription.</p>
     */
    public String delete() {

        if (log.isDebugEnabled()) {
            log.debug("delete()");
        }
        FacesContext context = FacesContext.getCurrentInstance();
        StringBuffer url = base(context);
        url.append("?action=Delete");
        url.append("&username=");
        User user = (User)
            context.getExternalContext().getSessionMap().get("user");
        url.append(user.getUsername());
        url.append("&host=");
        Subscription subscription = (Subscription)
            context.getExternalContext().getRequestMap().get("subscription");
        url.append(subscription.getHost());
        forward(context, url.toString());
        return (null);

    }


    /**
     * <p>Edit an existing subscription.</p>
     */
    public String edit() {

        if (log.isDebugEnabled()) {
            log.debug("edit()");
        }
        FacesContext context = FacesContext.getCurrentInstance();
        StringBuffer url = base(context);
        url.append("?action=Edit");
        url.append("&username=");
        User user = (User)
            context.getExternalContext().getSessionMap().get("user");
        url.append(user.getUsername());
        url.append("&host=");
        Subscription subscription = (Subscription)
            context.getExternalContext().getRequestMap().get("subscription");
        url.append(subscription.getHost());
        forward(context, url.toString());
        return (null);

    }


    /**
     * <p>Update the subscriptions to reflect any revisions to the
     * <code>type</code> and <code>autoConnect</code> properties.</p>
     */
    public String update() {

        if (log.isDebugEnabled()) {
            log.debug("update()");
        }

        FacesContext context = FacesContext.getCurrentInstance();

        // Updates went directly to the underlying rows, so all we need to do
        // is save the database
        try {
            UserDatabase database = (UserDatabase)
                context.getExternalContext().getApplicationMap().
                get(Constants.DATABASE_KEY);
            database.save();
        } catch (Exception e) {
            log.error("Database save", e);
        }

        // Forward back to the edit registration page
        forward(context, "/editRegistration.do?action=Edit");
        return (null);

    }


    // --------------------------------------------------------- Private Methods


    /**
     * <p>Return the context relative base URL for the "edit subscriptions"
     * action.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     */
    private StringBuffer base(FacesContext context) {

        // FIXME - assumes extension mapping for Struts
        return (new StringBuffer("/editSubscription.do"));

    }


    /**
     * <p>Forward to the specified URL and mark this response as having
     * been completed.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param url Context-relative URL to forward to
     *
     * @exception FacesException if any error occurs
     */
    private void forward(FacesContext context, String url) {

        if (log.isDebugEnabled()) {
            log.debug("forward(" + url + ")");
        }
        try {
            context.getExternalContext().dispatch(url);
        } catch (IOException e) {
            log.error("IOException from dispatch()", e);
            throw new FacesException(e);
        } finally {
            context.responseComplete();
        }

    }


}
