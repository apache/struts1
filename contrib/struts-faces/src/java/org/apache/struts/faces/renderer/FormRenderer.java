/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/renderer/FormRenderer.java,v 1.2 2003/06/04 17:38:13 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2003/06/04 17:38:13 $
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
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.faces.component.FormComponent;


/**
 * <p><code>Renderer</code> implementation for the <code>form</code> tag
 * from the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2003/06/04 17:38:13 $
 */

public class FormRenderer extends AbstractRenderer {


    // ------------------------------------------------------- Static Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    private static Log log = LogFactory.getLog(FormRenderer.class);


    // --------------------------------------------------------- Public Methods



    /**
     * <p>Perform setup processing that will be required for decoding the
     * incoming request.</p>
     *
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be processed
     *
     * @exception IOException if an input/output error occurs while decoding
     * @exception NullPointerException if <code>context</code>
     *  or <code>component</code> is null
     */
    public void decode(FacesContext context, UIComponent component)
        throws IOException {

        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        if (log.isDebugEnabled()) {
            log.debug("decode(" + component.getComponentId() + ")");
        }
        component.setValid(true);

    }


    /**
     * <p>Render the beginning of an HTML <code>&lt;form&gt;</code>
     * control.</p>
     *
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     *
     * @exception IOException if an input/output error occurs while rendering
     * @exception NullPointerException if <code>context</code>
     *  or <code>component</code> is null
     */
    public void encodeBegin(FacesContext context, UIComponent component)
        throws IOException {

        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        if (log.isDebugEnabled()) {
            log.debug("encodeBegin(" + component.getAttribute("formName") + ")");
        }

        // Look up attribute values we need
        String enctype = (String) component.getAttribute("enctype");
        String method = (String) component.getAttribute("method");
        String onreset = (String) component.getAttribute("onreset");
        String onsubmit = (String) component.getAttribute("onsubmit");
        String style = (String) component.getAttribute("style");
        String styleClass = (String) component.getAttribute("styleClass");
        String styleId = (String) component.getAttribute("styleId");
        String target = (String) component.getAttribute("target");

        // Calculate and cache the form name
        FormComponent form = (FormComponent) component;
        String action = form.getAction();
        ModuleConfig moduleConfig = form.lookupModuleConfig(context);
        ActionConfig actionConfig = moduleConfig.findActionConfig(action);
        String beanName = actionConfig.getAttribute();
        form.setAttribute("beanName", beanName);

        // Render the beginning of this form
        ResponseWriter writer = context.getResponseWriter();
        writer.write("<form name=\"");
        writer.write(beanName);
        writer.write("\" method=\"");
        if (method != null) {
            writer.write(method);
        } else {
            writer.write("post");
        }
        writer.write("\" action=\"");
        writer.write(action(context, component));
        writer.write("\"");
        if (styleClass != null) {
            writer.write(" class=\"");
            writer.write(styleClass);
            writer.write("\"");
        }
        if (enctype != null) {
            writer.write(" enctype=\"");
            writer.write(enctype);
            writer.write("\"");
        }
        if (onreset != null) {
            writer.write(" onreset=\"");
            writer.write(onreset);
            writer.write("\"");
        }
        if (onsubmit != null) {
            writer.write(" onsubmit=\"");
            writer.write(onsubmit);
            writer.write("\"");
        }
        if (style != null) {
            writer.write(" style=\"");
            writer.write(style);
            writer.write("\"");
        }
        if (styleId != null) {
            writer.write(" id=\"");
            writer.write(styleId);
            writer.write("\"");
        }
        if (target != null) {
            writer.write(" target=\"");
            writer.write(target);
            writer.write("\"");
        }
        writer.write(">\n");

        // Add a transaction token if necessary
        HttpSession session = (HttpSession)
            context.getExternalContext().getSession(false);
        if (session != null) {
            String token = (String)
                session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
            if (token != null) {
                writer.write("<input type=\"hidden\" name=\"");
                writer.write("org.apache.struts.taglib.html.TOKEN");
                writer.write("\" value=\"");
                writer.write(token);
                writer.write("\">\n");
            }
        }

        // Create an instance of the form bean if necessary
        if (component instanceof FormComponent) {
            ((FormComponent) component).createActionForm(context);
        }

    }


    /**
     * <p>Render the ending of an HTML <code>&lt;form&gt;</code>
     * control.</p>
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
        if (log.isDebugEnabled()) {
            log.debug("encodeEnd(" + component.getComponentId() + ")");
        }

        // Render the ending of this form
        ResponseWriter writer = context.getResponseWriter();
        writer.write("</form>\n");

        // Render focus JavaScript if requested
        if (!(component instanceof FormComponent)) {
            return;
        }
        String focus = (String) component.getAttribute("focus");
        if (focus == null) {
            return;
        }
        String focusIndex = (String) component.getAttribute("focusIndex");
        writer.write("\n");
        FormComponent form = (FormComponent) component;
        writer.write("<script type=\"text/javascript\"");
        if (!isXhtml(component)) {
            writer.write(" language=\"JavaScript\"");
        }
        writer.write(">\n");
        if (!isXhtml(component)) {
            writer.write("<!--\n");
        }

        StringBuffer sb = new StringBuffer("document[\"");
        sb.append((String) component.getAttribute("beanName"));
        sb.append("\"].elements[\"");
        sb.append(focus);
        sb.append("\"]");
        String focusControl = sb.toString();

        writer.write("  var focusControl = ");
        writer.write(focusControl);
        writer.write(";\n");
        writer.write("  if (focusControl.type != \"hidden\") {\n");
        writer.write("    ");
        writer.write(focusControl);
        if (focusIndex != null) {
            writer.write("[");
            writer.write(focusIndex);
            writer.write("]");
        }
        writer.write(".focus();\n");
        writer.write("  }\n");

        if (!isXhtml(component)) {
            writer.write("// -->\n");
        }
        writer.write("</script>\n");

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Calculate and return the value to be specifed for the
     * <code>action</action> attribute on the <code>&lt;form&gt;</code>
     * element to be rendered.</p>
     *
     * @param context FacesContext for the current request
     * @param component Component being processed
     */
    protected String action(FacesContext context, UIComponent component) {

        String treeId = context.getTree().getTreeId();
        StringBuffer sb = new StringBuffer
            (context.getExternalContext().getRequestContextPath());
        sb.append("/faces");
        sb.append(treeId);
        return (context.getExternalContext().encodeURL(sb.toString()));

    }


    /**
     * <p>Return <code>true</code> if we should render as XHTML.</p>
     *
     * @param component The component we are rendering
     */
    protected boolean isXhtml(UIComponent component) {

        return (false); // FIXME -- check up the hierarchy

    }


}
