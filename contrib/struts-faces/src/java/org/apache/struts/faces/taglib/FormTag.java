/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/taglib/FormTag.java,v 1.1 2003/03/07 03:22:44 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/07 03:22:44 $
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

package org.apache.struts.faces.taglib;


import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.webapp.FacesTag;
import javax.servlet.jsp.JspException;
import org.apache.struts.faces.component.FormComponent;


/**
 * <p>Render an input form that is submitted to a Struts <code>Action</code>,
 * for the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/03/07 03:22:44 $
 */

public class FormTag extends AbstractFacesTag {


    // --------------------------------------------------------- Tag Attributes


    /**
     * <p>The <code>path</code> of the Struts <code>Action</code> to which
     * this form should be submitted.  This property is analogous to the
     * <code>formName</code> property on the form tag in the standard
     * HTML RenderKit.</p>
     */
    protected String action = null;

    public void setAction(String action) {
        this.action = action;
    }


    /**
     * <p>The content encoding type to use.</p>
     */
    protected String enctype = null;

    public void setEnctype(String enctype) {
        this.enctype = enctype;
    }


    /**
     * <p>The name of the field to which focus should be set when this
     * form is displayed.</p>
     */
    protected String focus = null;

    public void setFocus(String focus) {
        this.focus = focus;
    }


    /**
     * <p>The subscript of the focus field array to receive focus.</p>
     */
    protected String focusIndex = null;

    public void setFocusIndex(String focusIndex) {
        this.focusIndex = focusIndex;
    }


    /**
     * <p>The submit method (GET or POST) to use.</p>
     */
    protected String method = null;

    public void setMethod(String method) {
        this.method = method;
    }


    /**
     * <p>The JavaScript reset event handler.</p>
     */
    protected String onreset = null;

    public void setOnreset(String onreset) {
        this.onreset = onreset;
    }


    /**
     * <p>The JavaScript submit event handler.</p>
     */
    protected String onsubmit = null;

    public void setOnsubmit(String onsubmit) {
        this.onsubmit = onsubmit;
    }


    /**
     * <p>The CSS styles to apply to this element.</p>
     */
    protected String style = null;

    public void setStyle(String style) {
        this.style = style;
    }


    /**
     * <p>The CSS style class to apply to this element.</p>
     */
    protected String styleClass = null;

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }


    /**
     * <p>The HTML element id for this element.</p>
     */
    protected String styleId = null;

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }


    /**
     * <p>The window target for this submit.</p>
     */
    protected String target = null;

    public void setTarget(String target) {
        this.target = target;
    }


    // ------------------------------------------------------------ Tag Methods


    /**
     * <p>Release any allocated resources.</p>
     */
    public void release() {

        super.release();
        action = null;
        enctype = null;
        focus = null;
        focusIndex = null;
        method = null;
        onreset = null;
        onsubmit = null;
        style = null;
        styleClass = null;
        styleId = null;
        target = null;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Create and return a new component to be associated with this tag.</p>
     */
    public UIComponent createComponent() {

        return (new FormComponent());

    }


    /**
     * <p>Return the <code>rendererType</code> to be used for rendering
     * our component.</p>
     */
    public String getRendererType() {

        return ("StrutsForm");

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Override attributes set on this tag instance.</p>
     *
     * @param component Component whose attributes should be overridden
     */
    protected void overrideProperties(UIComponent component) {

        super.overrideProperties(component);
        if ((action != null) &&
            ((FormComponent) component).getAction() == null) {
            ((FormComponent) component).setAction(action);
        }
        if ((enctype != null) &&
            (component.getAttribute("enctype") == null)) {
            component.setAttribute("enctype", action);
        }
        if ((focus != null) &&
            (component.getAttribute("focus") == null)) {
            component.setAttribute("focus", focus);
        }
        if ((focusIndex != null) &&
            (component.getAttribute("focusIndex") == null)) {
            component.setAttribute("focusIndex", focusIndex);
        }
        if ((method != null) &&
            (component.getAttribute("method") == null)) {
            component.setAttribute("method", method);
        }
        if ((onreset != null) &&
            (component.getAttribute("onreset") == null)) {
            component.setAttribute("onreset", onreset);
        }
        if ((onsubmit != null) &&
            (component.getAttribute("onsubmit") == null)) {
            component.setAttribute("onsubmit", onsubmit);
        }
        if ((style != null) &&
            (component.getAttribute("style") == null)) {
            component.setAttribute("style", style);
        }
        if ((styleClass != null) &&
            (component.getAttribute("styleClass") == null)) {
            component.setAttribute("styleClass", styleClass);
        }
        if ((styleId != null) &&
            (component.getAttribute("styleId") == null)) {
            component.setAttribute("styleId", styleId);
        }
        if ((target != null) &&
            (component.getAttribute("target") == null)) {
            component.setAttribute("target", target);
        }

    }


}
