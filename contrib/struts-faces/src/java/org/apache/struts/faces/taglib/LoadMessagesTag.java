/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/taglib/LoadMessagesTag.java,v 1.1 2004/03/08 00:42:15 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2004/03/08 00:42:15 $
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

package org.apache.struts.faces.taglib;


import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;
import org.apache.struts.faces.util.MessagesMap;
import org.apache.struts.util.MessageResources;


/**
 * <p>Tag that exposes a specified <code>MessageResources</code> instance
 * as <code>Map</code>, so that the embedded messages may be retrieved via
 * value binding expressions.</p>
 */

public class LoadMessagesTag extends TagSupport {


    // ---------------------------------------------------------- Tag Attributes


    /**
     * <p>The name of the <code>MessageResources</code> to expose, or
     * <code>null</code> for the default <code>MessageResources</code>
     * for this application module.</p>
     */
    private String messages = null;
    public void setMessages(String messages) {
        this.messages = messages;
    }


    /**
     * <p>The request attribute key under which a <code>Map</code>
     * will be exposed.</p>
     */
    private String var = null;
    public void setVar(String var) {
        this.var = var;
    }


    // ------------------------------------------------------------- Tag Methods


    /**
     * <p>Expose a <code>Map</code> wrapping the specified
     * <code>MessageResources</code> instance, for the <code>Locale</code>
     * specified in the view root component of the current view.</p>
     */
    public int doStartTag() {

        // Acquire the Locale to be wrapped
        Locale locale =
            FacesContext.getCurrentInstance().getViewRoot().getLocale();

        // Acquire the MessageResources to be wrapped
        MessageResources messages = null;
        if (this.messages == null) {
            messages = (MessageResources)
                pageContext.getAttribute(Globals.MESSAGES_KEY,
                                         PageContext.REQUEST_SCOPE);
            if (messages == null) {
                messages = (MessageResources)
                    pageContext.getAttribute(Globals.MESSAGES_KEY,
                                             PageContext.APPLICATION_SCOPE);
            }
        } else {
            messages = (MessageResources)
                pageContext.getAttribute(this.messages,
                                         PageContext.APPLICATION_SCOPE);
        }

        // Expose a Map instance under the specified request attribute key
        pageContext.setAttribute(var,
                                 new MessagesMap(messages, locale),
                                 PageContext.REQUEST_SCOPE);

        // Skip the body of this tag (if any)
        return (SKIP_BODY);

    }


    /**
     * <p>Release any resources allocated by this tag instance.</p>
     */
    public void release() {

        this.messages = null;
        this.var = null;

    }


}
