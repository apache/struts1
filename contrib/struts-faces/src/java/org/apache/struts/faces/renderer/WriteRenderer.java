/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/renderer/WriteRenderer.java,v 1.2 2003/06/04 17:38:13 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2003/06/04 17:38:13 $
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
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.ResponseUtils;


/**
 * <p><code>Renderer</code> implementation for the <code>write</code> tag
 * from the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2003/06/04 17:38:13 $
 */

public class WriteRenderer extends AbstractRenderer {


    // ------------------------------------------------------- Static Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    private static Log log = LogFactory.getLog(WriteRenderer.class);


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Encode the specified text to our response.</p>
     *
     * @param context FacesContext for the response we are creating
     * @param component Component to be rendered
     *
     * @exception IOException if an input/output error occurs
     */
    public void encodeEnd(FacesContext context, UIComponent component)
        throws IOException {

        ResponseWriter writer = context.getResponseWriter();
        String styleClass = (String) component.getAttribute("styleClass");
        if (styleClass != null) {
            writer.write("<span class=\"");
            writer.write(styleClass);
            writer.write("\">");
        }
        String text = getText(context, component);
        if (log.isTraceEnabled()) {
            log.trace("encodeEnd(" + component.getComponentId() +
                      "," + text + ")");
        }
        writer.write(text);
        if (styleClass != null) {
            writer.write("</span>");
        }

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Return the text to be rendered for this component, optionally
     * filtered if requested.</p>
     *
     * @param context FacesContext for the response we are creating
     * @param component Component to be rendered
     */
    protected String getText(FacesContext context, UIComponent component) {

        Object value = ((UIOutput) component).currentValue(context);
        if (value == null) {
            value = "";
        }
        String text = value.toString();

        Boolean filter = (Boolean) component.getAttribute("filter");
        if (filter == null) {
            filter = Boolean.FALSE;
        }
        if (filter.booleanValue()) {
            return (ResponseUtils.filter(text));
        } else {
            return (text);
        }

    }


}
