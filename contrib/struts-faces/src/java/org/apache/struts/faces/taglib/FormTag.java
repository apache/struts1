/*
 * Copyright 2002-2004 The Apache Software Foundation.
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

package org.apache.struts.faces.taglib;


import javax.faces.component.UIComponent;


/**
 * <p>Render an input form that is submitted to a Struts <code>Action</code>,
 * for the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @version $Revision: 1.7 $ $Date: 2004/06/09 02:28:28 $
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

        return ("org.apache.struts.faces.Form");

    }


    /**
     * <p>Return the <code>rendererType</code> to be used for rendering
     * our component.</p>
     */
    public String getRendererType() {

        return ("org.apache.struts.faces.Form");

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Override attributes set on this tag instance.</p>
     *
     * @param component Component whose attributes should be overridden
     */
    protected void setProperties(UIComponent component) {

        super.setProperties(component);
        setStringAttribute(component, "action", action);
        setStringAttribute(component, "enctype", enctype);
        setStringAttribute(component, "focus", focus);
        setStringAttribute(component, "focusIndex", focusIndex);
        setStringAttribute(component, "method", method);
        setStringAttribute(component, "onreset", onreset);
        setStringAttribute(component, "onsubmit", onsubmit);
        setStringAttribute(component, "style", style);
        setStringAttribute(component, "styleClass", styleClass);
        setStringAttribute(component, "styleId", styleId);
        setStringAttribute(component, "target", target);

    }


}
