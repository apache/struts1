/*
 * Copyright 2002,2004 The Apache Software Foundation.
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

package org.apache.struts.faces.renderer;


import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
// import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p><code>Renderer</code> implementation for the <code>base</code> tag
 * from the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @version $Revision: 1.9 $ $Date: 2004/03/08 02:49:54 $
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
        writer.startElement("base", component);
        writer.writeURIAttribute("href", uri(context), null);
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
     * @param context <code>FacesContext</code> for the current request
     */
    protected String uri(FacesContext context) {

        return (context.getApplication().getViewHandler().
                getActionURL(context, context.getViewRoot().getViewId()));

        /*
        HttpServletRequest request = (HttpServletRequest)
            context.getExternalContext().getRequest();
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
        */

    }


}
