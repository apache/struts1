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

package org.apache.struts.faces.component;


import java.io.IOException;
import java.util.Iterator;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;


/**
 * <p>Custom component that replaces the Struts
 * <code>&lt;html:message&gt;</code> tag.</p>
 */

public class MessageComponent extends UIOutput {


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Create a new {@link MessageComponent} with default properties.</p>
     */
    public MessageComponent() {

        super();
        setRendererType("org.apache.struts.faces.Message");

    }


    // ------------------------------------------------------ Instance Variables


    /**
     * <p>MessageResources attribute key to use for message lookup.</p>
     */
    private String bundle = null;


    /**
     * <p>Message key to use for message lookup.</p>
     */
    private String key = null;


    /**
     * <p>CSS style(s) to be rendered for this component.</p>
     */
    private String style = null;


    /**
     * <p>CSS style class(es) to be rendered for this component.</p>
     */
    private String styleClass = null;


    // ---------------------------------------------------- Component Properties


    /**
     * <p>Return the MessageResources key.</p>
     */
    public String getBundle() {

        ValueBinding vb = getValueBinding("bundle");
        if (vb != null) {
            return (String) vb.getValue(getFacesContext());
        } else {
            return bundle;
        }

    }


    /**
     * <p>Set the MessageResources key.</p>
     *
     * @param bundle The new key
     */
    public void setBundle(String bundle) {

        this.bundle = bundle;

    }


    /**
     * <p>Return the component family to which this component belongs.</p>
     */
    public String getFamily() {

        return "org.apache.struts.faces.Message";

    }


    /**
     * <p>Return the message key.</p>
     */
    public String getKey() {

        ValueBinding vb = getValueBinding("key");
        if (vb != null) {
            return (String) vb.getValue(getFacesContext());
        } else {
            return key;
        }

    }


    /**
     * <p>Set the message key.</p>
     *
     * @param key The new key
     */
    public void setKey(String key) {

        this.key = key;

    }


    /**
     * <p>Return the CSS style(s) to be rendered for this component.</p>
     */
    public String getStyle() {

        ValueBinding vb = getValueBinding("style");
        if (vb != null) {
            return (String) vb.getValue(getFacesContext());
        } else {
            return style;
        }

    }


    /**
     * <p>Set the CSS style(s) to be rendered for this component.</p>
     *
     * @param style The new CSS style(s)
     */
    public void setStyle(String style) {

        this.style = style;

    }


    /**
     * <p>Return the CSS style class(es) to be rendered for this component.</p>
     */
    public String getStyleClass() {

        ValueBinding vb = getValueBinding("styleClass");
        if (vb != null) {
            return (String) vb.getValue(getFacesContext());
        } else {
            return styleClass;
        }

    }


    /**
     * <p>Set the CSS style class(es) to be rendered for this component.</p>
     *
     * @param style The new CSS style class(es)
     */
    public void setStyleClass(String styleClass) {

        this.styleClass = styleClass;

    }


    // ---------------------------------------------------- StateManager Methods


    /**
     * <p>Restore the state of this component.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param state State object from which to restore our state
     */
    public void restoreState(FacesContext context, Object state) {

        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        bundle = (String) values[1];
        key = (String) values[2];
        style = (String) values[3];
        styleClass = (String) values[4];

    }


    /**
     * <p>Save the state of this component.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     */
    public Object saveState(FacesContext context) {

        Object values[] = new Object[5];
        values[0] = super.saveState(context);
        values[1] = bundle;
        values[2] = key;
        values[3] = style;
        values[4] = styleClass;
        return values;

    }


}
