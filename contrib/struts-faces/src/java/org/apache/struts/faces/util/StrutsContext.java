/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/util/StrutsContext.java,v 1.1 2004/03/08 00:40:49 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2004/03/08 00:40:49 $
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

package org.apache.struts.faces.util;


import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.sql.DataSource;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.faces.Constants;
import org.apache.struts.util.MessageResources;


/**
 * <p>Context bean providing accessors for the Struts related request,
 * session, and application scope objects reated to this request.  Note
 * that this bean's methods will trigger exceptions unless there is a
 * <code>FacesContext</code> instance for this request.</p>
 */

public class StrutsContext {


    // ------------------------------------------------------ Instance Variables


    /**
     * <p>The <code>FacesContext</code> for the current request.</p>
     */
    private FacesContext fcontext =
        FacesContext.getCurrentInstance();


    /**
     * <p>The <code>ExternalContext</code> for the current request.</p>
     */
    private ExternalContext econtext =
        fcontext.getExternalContext();


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Return the <code>ActionEvent</code> for the current request
     * (if any).</p>
     */
    public ActionEvent getActionEvent() {

        return ((ActionEvent) econtext.getRequestMap().
                get(Constants.ACTION_EVENT_KEY));

    }


    /**
     * <p>Return the <code>ActionMapping</code> for the current
     * request (if any).</p>
     */
    public ActionMapping getActionMapping() {

        return ((ActionMapping) econtext.getRequestMap().
                get(Globals.MAPPING_KEY));

    }


    /**
     * <p>Return the <code>ActionMessages</code> instance containing
     * application error messages for this request (if any).</p>
     */
    public ActionMessages getActionMessages() {

        return ((ActionMessages) econtext.getRequestMap().
                get(Globals.MESSAGE_KEY));

    }


    /**
     * <p>Return the <code>ActionServlet</code> instance for this
     * web application.</p>
     */
    public ActionServlet getActionServlet() {

        return ((ActionServlet) econtext.getApplicationMap().
                get(Globals.ACTION_SERVLET_KEY));

    }


    /**
     * <p>Return <code>true</code> if a Boolean true value has been stored
     * in the request attribute indicating that this request has been
     * cancelled.</p>
     */
    public boolean isCancelled() {

        Object value = econtext.getRequestMap().get(Globals.CANCEL_KEY);
        if (value instanceof Boolean) {
            return (((Boolean) value).booleanValue());
        } else {
            return (false);
        }

    }


    /**
     * <p>Return the default <code>DataSource</code> instance for this
     * application (if any).</p>
     */
    public DataSource getDataSource() {

        return ((DataSource) econtext.getApplicationMap().
                get(Globals.DATA_SOURCE_KEY));

    }


    /**
     * <p>Return the exception that caused one of the Struts custom tags
     * to report a JspException (if any).</p>
     */
    public Throwable getException() {

        return ((Throwable) econtext.getRequestMap().
                get(Globals.EXCEPTION_KEY));

    }


    /**
     * <p>Return the <code>ExternalContext</code> for the current request.</p>
     */
    public ExternalContext getExternalContext() {

        return (econtext);

    }


    /**
     * <p>Return the <code>FacesContext</code> for the current request.</p>
     */
    public FacesContext getFacesContext() {

        return (fcontext);

    }


    /**
     * <p>Return the <code>Locale</code> stored in the current user's
     * session (if any) for Struts based localization.</p>
     */
    public Locale getLocale() {

        if (econtext.getSession(false) != null) {
            return ((Locale) econtext.getSessionMap().
                    get(Globals.LOCALE_KEY));
        } else {
            return (null);
        }

    }


    /**
     * <p>Return the <code>MessageResources</code> instance for the
     * application module that is processing this request (if any).</p>
     */
    public MessageResources getMessageResources() {

        return ((MessageResources) econtext.getRequestMap().
                get(Globals.MESSAGES_KEY));

    }


    /**
     * <p>Return the <code>ModuleConfig</code> for the application module
     * to which this request has been assigned (if any).</p>
     */
    public ModuleConfig getModuleConfig() {

        return ((ModuleConfig) econtext.getRequestMap().
                get(Globals.MODULE_KEY));

    }


}

