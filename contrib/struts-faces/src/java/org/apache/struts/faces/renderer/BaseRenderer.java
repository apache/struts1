/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/renderer/BaseRenderer.java,v 1.7 2004/01/18 13:43:12 husted Exp $
 * $Revision: 1.7 $
 * $Date: 2004/01/18 13:43:12 $
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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p><code>Renderer</code> implementation for the <code>base</code> tag
 * from the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @version $Revision: 1.7 $ $Date: 2004/01/18 13:43:12 $
 */

public class BaseRenderer extends AbstractRenderer {


    // -------------------------------------------------------- Static Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    private static Log log = LogFactory.getLog(BaseRenderer.class);


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Render an HTML <code>base</code> element.</p>
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
        HttpServletRequest request = (HttpServletRequest)
            context.getExternalContext().getRequest();
        writer.startElement("base", component);
        writer.writeURIAttribute("href", uri(request), null);
        String target = (String) component.getAttributes().get("target");
        if (target != null) {
            writer.writeAttribute("target", target, "target");
        }
        writer.endElement("base");
        writer.writeText("\n", null);

    }



    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Return the absolute URI to be rendered as the value of the
     * <code>href</code> attribute.</p>
     *
     * @param request The servlet request we are processing
     */
    protected String uri(HttpServletRequest request) {

        StringBuffer sb = new StringBuffer(request.getScheme());
        sb.append("://");
        sb.append(request.getServerName());
        if ("http".equals(request.getScheme()) &&
            (80 == request.getServerPort())) {
            ;
        } else if ("https".equals(request.getScheme()) &&
                   (443 == request.getServerPort())) {
            ;
        } else {
            sb.append(":" + request.getServerPort());
        }
        sb.append(request.getContextPath());
        String servletPath = request.getServletPath();
        if (servletPath != null) {
            if (servletPath.startsWith("/faces")) {
                sb.append(servletPath.substring(6));
            } else {
                sb.append(servletPath);
            }
        }
        if (request.getPathInfo() != null) {
            sb.append(request.getPathInfo());
        }
        return (sb.toString());

    }


}
