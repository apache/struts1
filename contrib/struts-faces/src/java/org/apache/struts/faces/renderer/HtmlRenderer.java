/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/renderer/HtmlRenderer.java,v 1.3 2003/12/24 03:21:01 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2003/12/24 03:21:01 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002-2003 The Apache Software Foundation.  All rights
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
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;


/**
 * <p><code>Renderer</code> implementation for the <code>html</code> tag
 * from the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2003/12/24 03:21:01 $
 */

public class HtmlRenderer extends AbstractRenderer {


    // -------------------------------------------------------- Static Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    private static Log log = LogFactory.getLog(HtmlRenderer.class);


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Render the beginning <code>html</code> tag.</p>
     *
     * @param context FacesContext for the current request
     * @param component UIComponent to be rendered
     *
     * @exception IOException if an input/output error occurs while rendering
     * @exception NullPointerException if <code>context</code>
     *  or <code>component</code> is <code>null</code>
     */
    public void encodeBegin(FacesContext context, UIComponent component)
        throws IOException {

        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        Locale currentLocale = getCurrentLocale(context, component);
        String lang = currentLocale.getLanguage();
        boolean validLanguage = ((lang != null) && (lang.length() > 0));

        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("html", component);
        if (isXhtml(component)) {
            // FIXME -- page scope attribute Globals.XHTML_KEY to "true"?
            writer.writeAttribute("xmlns",
                                  "http://www.w3.org/1999/xhtml", null);
        }
        if ((isLocale(component) || isXhtml(component)) && validLanguage) {
            writer.writeAttribute("lang", lang, null);
        }
        if (isXhtml(component) && validLanguage) {
            writer.writeAttribute("xml:lang", lang, null);
        }
        writer.writeText("\n", null);

    }


    /**
     * <p>Render the end of the <code>html</code> element.</p>
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

        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("html");

    }



    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Return the current <code>Locale</code> for this request, creating a
     * new one if necessary.</p>
     *
     * @param context FacesContext for this request
     * @param component UIComponent we are rendering
     */
    protected Locale getCurrentLocale
        (FacesContext context, UIComponent component) {

        // If locale support not requested, just extract one from the request
        if (!isLocale(component)) {
            return (context.getExternalContext().getRequestLocale());
        }

        // Create a new session if necessary
        HttpSession session = (HttpSession)
            context.getExternalContext().getSession(true);

        // Return current locale or a new one that is created
        Locale current = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        if (current != null) {
            return (current);
        }
        current = context.getExternalContext().getRequestLocale();
        session.setAttribute(Globals.LOCALE_KEY, current);
        return (current);

    }



    /**
     * <p>Return the state of the <code>locale</code> attribute.</p>
     *
     * @param component Component to process
     */
    protected boolean isLocale(UIComponent component) {

        Boolean locale = (Boolean) component.getAttributes().get("locale");
        if (locale != null) {
            return locale.booleanValue();
        } else {
            return (false);
        }

    }


    /**
     * <p>Return the state of the <code>xhtml</code> attribute.</p>
     *
     * @param component Component to process
     */
    protected boolean isXhtml(UIComponent component) {

        Boolean xhtml = (Boolean) component.getAttributes().get("xhtml");
        if (xhtml != null) {
            return xhtml.booleanValue();
        } else {
            return (false);
        }

    }


}
