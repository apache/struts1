/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/example2/org/apache/struts/webapp/example2/RegistrationBacking.java,v 1.1 2003/12/31 07:17:48 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/12/31 07:17:48 $
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


package org.apache.struts.webapp.example2;


import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 * <p>Backing bean for the <code>registration.jsp</code> page.</p>
 */

public class RegistrationBacking {


    // -------------------------------------------------------------- Properties


    // These methods exist to work around a bug in the PFD version of the
    // rendering for <h:data_table> that disallows constant values on
    // per-row command and output components
    public String getDeleteLabel() { return ("Delete"); }
    public String getEditLabel() { return ("Edit"); }


    // ----------------------------------------------------------------- Actions


    /**
     * <p>Create a new subscription.</p>
     */
    public String create() {

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

        try {
            context.getExternalContext().dispatchMessage(url);
        } catch (IOException e) {
            throw new FacesException(e);
        } finally {
            context.responseComplete();
        }

    }


}
