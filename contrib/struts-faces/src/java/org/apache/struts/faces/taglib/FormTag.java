/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/taglib/FormTag.java,v 1.4 2004/01/18 13:43:13 husted Exp $
 * $Revision: 1.4 $
 * $Date: 2004/01/18 13:43:13 $
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
import javax.faces.el.ValueBinding;
import org.apache.struts.faces.component.FormComponent;


/**
 * <p>Render an input form that is submitted to a Struts <code>Action</code>,
 * for the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @version $Revision: 1.4 $ $Date: 2004/01/18 13:43:13 $
 */

public class FormTag extends AbstractFacesTag {


    // ---------------------------------------------------------- Tag Attributes


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


    // ------------------------------------------------------------- Tag Methods


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


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Return the type of component to be created for this tag.</p>
     */
    public String getComponentType() {

        return ("StrutsForm");

    }


    /**
     * <p>Return the <code>rendererType</code> to be used for rendering
     * our component.</p>
     */
    public String getRendererType() {

        return ("StrutsForm");

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Override attributes set on this tag instance.</p>
     *
     * @param component Component whose attributes should be overridden
     */
    protected void setProperties(UIComponent component) {

        super.setProperties(component);
        if (action != null) {
            if (isValueReference(action)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(action);
                component.setValueBinding("action", vb);
            } else {
                ((FormComponent) component).setAction(action);
            }
        }
        if (enctype != null) {
            if (isValueReference(enctype)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(enctype);
                component.setValueBinding("enctype", vb);
            } else {
                component.getAttributes().put("enctype", enctype);
            }
        }
        if (focus != null) {
            if (isValueReference(focus)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(focus);
                component.setValueBinding("focus", vb);
            } else {
                component.getAttributes().put("focus", focus);
            }
        }
        if (focusIndex != null) {
            if (isValueReference(focusIndex)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(focusIndex);
                component.setValueBinding("focusIndex", vb);
            } else {
                component.getAttributes().put("focusIndex", focusIndex);
            }
        }
        if (method != null) {
            if (isValueReference(method)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(method);
                component.setValueBinding("method", vb);
            } else {
                component.getAttributes().put("method", method);
            }
        }
        if (onreset != null) {
            if (isValueReference(onreset)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(onreset);
                component.setValueBinding("onreset", vb);
            } else {
                component.getAttributes().put("onreset", onreset);
            }
        }
        if (onsubmit != null) {
            if (isValueReference(onsubmit)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(onsubmit);
                component.setValueBinding("onsubmit", vb);
            } else {
                component.getAttributes().put("onsubmit", onsubmit);
            }
        }
        if (style != null) {
            if (isValueReference(style)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(style);
                component.setValueBinding("style", vb);
            } else {
                component.getAttributes().put("style", style);
            }
        }
        if (styleClass != null) {
            if (isValueReference(styleClass)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(styleClass);
                component.setValueBinding("styleClass", vb);
            } else {
                component.getAttributes().put("styleClass", styleClass);
            }
        }
        if (styleId != null) {
            if (isValueReference(styleId)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(styleId);
                component.setValueBinding("styleId", vb);
            } else {
                component.getAttributes().put("styleId", styleId);
            }
        }
        if (target != null) {
            if (isValueReference(target)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(target);
                component.setValueBinding("target", vb);
            } else {
                component.getAttributes().put("target", target);
            }
        }


    }


}
