/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/renderer/ErrorsRenderer.java,v 1.4 2003/12/24 03:21:01 craigmcc Exp $
 * $Revision: 1.4 $
 * $Date: 2003/12/24 03:21:01 $
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

package org.apache.struts.faces.renderer;


import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;


/**
 * <p><code>Renderer</code> implementation for the <code>errors</code> tag
 * from the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.4 $ $Date: 2003/12/24 03:21:01 $
 */

public class ErrorsRenderer extends AbstractRenderer {


    // -------------------------------------------------------- Static Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    private static Log log = LogFactory.getLog(ErrorsRenderer.class);


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Render a combination of error messages from JavaServer Faces
     * <code>Validator</code>s, and Struts messages from form bean
     * <code>validate()</code> methods and corresponding business logic
     * error checks.</p>
     *
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     *
     * @exception IOException if an input/output error occurs while rendering
     * @exception NullPointerException if <code>context</code>
     *  or <code>component</code> is null
     */
    public void encodeEnd(FacesContext context, UIComponent component)
        throws IOException {

        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        // Look up availability of our predefined resource keys
        MessageResources resources = resources(context, component);
        Locale locale = context.getViewRoot().getLocale();
        boolean headerPresent = resources.isPresent(locale, "errors.header");
        boolean footerPresent = resources.isPresent(locale, "errors.footer");
        boolean prefixPresent = resources.isPresent(locale, "errors.prefix");
        boolean suffixPresent = resources.isPresent(locale, "errors.suffix");

        // Set up to render the error messages appropriately
        boolean headerDone = false;
        ResponseWriter writer = context.getResponseWriter();

        // Render any JavaServer Faces messages
        Iterator messages = context.getMessages();
        while (messages.hasNext()) {
            FacesMessage message = (FacesMessage) messages.next();
            if (!headerDone) {
                if (headerPresent) {
                    writer.write
                        (resources.getMessage(locale, "errors.header"));
                }
                headerDone = true;
            }
            if (prefixPresent) {
                writer.write(resources.getMessage(locale, "errors.prefix"));
            }
            writer.write(message.getSummary());
            if (suffixPresent) {
                writer.write(resources.getMessage(locale, "errors.suffix"));
            }
        }

        // Render any Struts messages
        ActionErrors errors = (ActionErrors)
            context.getExternalContext().getRequestMap().get
            (Globals.ERROR_KEY);
        if (errors != null) {
            Iterator reports = errors.get();
            while (reports.hasNext()) {
                ActionError report = (ActionError) reports.next();
                if (!headerDone) {
                    writer = context.getResponseWriter();
                    if (headerPresent) {
                        writer.write
                            (resources.getMessage(locale, "errors.header"));
                    }
                    headerDone = true;
                }
                if (prefixPresent) {
                    writer.write
                        (resources.getMessage(locale, "errors.prefix"));
                }
                writer.write(resources.getMessage(locale, report.getKey(),
                                                  report.getValues()));
                if (suffixPresent) {
                    writer.write
                        (resources.getMessage(locale, "errors.suffix"));
                }
            }
        }

        // Append the list footer if needed
        if (headerDone && footerPresent) {
            writer.write(resources.getMessage(locale, "errors.footer"));
        }

    }



    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Return the <code>MessageResources</code> bundle from which
     * we should return any Struts based error messages.  If no such
     * bundle can be located, return <code>null</code>.</p>
     *
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     */
    protected MessageResources resources(FacesContext context,
                                         UIComponent component) {

        String bundle = (String) component.getAttributes().get("bundle");
        if (bundle == null) {
            bundle = Globals.MESSAGES_KEY;
        }
        return ((MessageResources)
                context.getExternalContext().getApplicationMap().get(bundle));

    }


}
