/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/taglib/AbstractFacesTag.java,v 1.5 2004/01/18 13:43:13 husted Exp $
 * $Revision: 1.5 $
 * $Date: 2004/01/18 13:43:13 $
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

package org.apache.struts.faces.taglib;


import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;


/**
 * <p>Abstract base class for custom component tags for the
 * <em>Struts-Faces Integration Library</em>.</p>
 *
 *
 * @version $Revision: 1.5 $ $Date: 2004/01/18 13:43:13 $
 */

public abstract class AbstractFacesTag extends UIComponentTag {


    // ---------------------------------------------------------- Tag Attributes


    /**
     * <p>The servlet context attribute under which our
     * <code>MessageResources</code> bundle is stored.</p>
     */
    protected String bundle = null;

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }


    /**
     * <p>The CSS style class used to render this component.</p>
     */
    protected String styleClass = null;

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }


    /**
     * <p>The literal value to be rendered.</p>
     */
    protected String value = null;

    public void setValue(String value) {
        this.value = value;
    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Return the component type of the component to be created for
     * this tag.</p>
     */
    public abstract String getComponentType();


    /**
     * <p>Return the <code>rendererType</code> to be used for rendering
     * our component.</p>
     */
    public abstract String getRendererType();


    /**
     * <p>Release any variables allocated during use of this tag instance.</p>
     */
    public void release() {

        super.release();
        this.bundle = null;
        this.styleClass = null;
        this.value = null;

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Override attributes set on this tag instance.</p>
     *
     * @param component Component whose attributes should be overridden
     */
    protected void setProperties(UIComponent component) {

        super.setProperties(component);
        if (bundle != null) {
            if (isValueReference(bundle)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(bundle);
                component.setValueBinding("bundle", vb);
            } else {
                component.getAttributes().put("bundle", bundle);
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
        if (value != null) {
            if (isValueReference(value)) {
                ValueBinding vb =
                    context.getApplication().createValueBinding(value);
                component.setValueBinding("value", vb);
            } else {
                ((ValueHolder) component).setValue(value);
            }
        }

    }


}
