/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/renderer/AbstractRenderer.java,v 1.4 2003/12/24 03:21:01 craigmcc Exp $
 * $Revision: 1.4 $
 * $Date: 2003/12/24 03:21:01 $
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
import java.util.Map;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.component.ConvertibleValueHolder;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.el.ValueBinding;
import javax.faces.render.Renderer;


/**
 * <p>Abstract base class for concrete implementations of
 * <code>javax.faces.render.Renderer</code> for the
 * <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.4 $ $Date: 2003/12/24 03:21:01 $
 */

public abstract class AbstractRenderer extends Renderer {


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Decode the current state of the specified UIComponent from the
     * request contained in the specified <code>FacesContext</code>, and
     * attempt to convert this state information into an object of the
     * type equired for this component.</p>
     *
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be decoded
     *
     * @exception NullPointerException if context or component is null
     */
    public void decode(FacesContext context, UIComponent component) {

        // Enforce NPE requirements in the Javadocs
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        // Only input components need to be decoded
        if (!(component instanceof UIInput)) {
            return;
        }
        UIInput input = (UIInput) component;

        // Save the old value for use in generating ValueChangedEvents
        Object oldValue = input.getValue();
        if (oldValue instanceof String) {
            try {
                oldValue = getAsObject(context, component, (String) oldValue);
            } catch (ConverterException e) {
                ;
            }
        }
        input.setPrevious(oldValue);

        // Decode and convert (if needed) the new value
        String clientId = component.getClientId(context);
        Map map = context.getExternalContext().getRequestParameterMap();
        String newString = (String) map.get(clientId);
        Object newValue = null;
        try {
            newValue = getAsObject(context, component, newString);
            input.setValue(newValue);
            input.setValid(true);
        } catch (ConverterException e) {
            input.setValue(newValue);
            input.setValid(false);
            addConverterMessage(context, component, e.getMessage());
        }
        
    }


    // --------------------------------------------------------- Package Methods


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Add an error message denoting a conversion failure.</p>
     *
     * @param context The <code>FacesContext</code> for this request
     * @param component The <code>UIComponent</code> that experienced
     *  the conversion failure
     * @param text The text of the error message
     */
    protected void addConverterMessage(FacesContext context,
                                       UIComponent component,
                                       String text) {

        String clientId = component.getClientId(context);
        FacesMessage message = new FacesMessage
            (text,
             "Conversion error on component '" + clientId + "'");
        context.addMessage(clientId, message);

    }


    /**
     * <p>Convert the String representation of this component's value
     * to the corresponding Object representation.  The default
     * implementation utilizes the <code>getAsObject()</code> method of any
     * associated <code>Converter</code>.</p>
     *
     * @param context The <code>FacesContext</code> for this request
     * @param component The <code>UIComponent</code> whose value is
     *  being converted
     * @param value The String representation to be converted
     *
     * @exception ConverterException if conversion fails
     */
    protected Object getAsObject(FacesContext context, UIComponent component,
                                 String value) throws ConverterException {

        // Identify any Converter associated with this component value
        ValueBinding vb = component.getValueBinding("value");
        Converter converter = null;
        if (component instanceof ConvertibleValueHolder) {
            // Acquire explicitly assigned Converter (if any)
            converter = ((ConvertibleValueHolder) component).getConverter();
        }
        if ((converter == null) && (vb != null)) {
            Class type = vb.getType(context);
            if ((type == null) || (type == String.class)) {
                return (value); // No conversion required for Strings
            }
            // Acquire implicit by-type Converter (if any)
            converter = context.getApplication().createConverter(type);
        }

        // Convert the result if we identified a Converter
        if (converter != null) {
            return (converter.getAsObject(context, component, value));
        } else {
            return (value);
        }

    }


    /**
     * <p>Convert the Object representation of this component's value
     * to the corresponding String representation.  The default implementation
     * utilizes the <code>getAsString()</code> method of any associated
     * <code>Converter</code>.</p>
     *
     * @param context The <code>FacesContext</code> for this request
     * @param component The <code>UIComponent</code> whose value is
     *  being converted
     * @param value The Object representation to be converted
     *
     * @exception ConverterException if conversion fails
     */
    protected String getAsString(FacesContext context, UIComponent component,
                                 Object value) throws ConverterException {

        // Identify any Converter associated with this component value
        ValueBinding vb = component.getValueBinding("value");
        Converter converter = null;
        if (component instanceof ConvertibleValueHolder) {
            // Acquire explicitly assigned Converter (if any)
            converter = ((ConvertibleValueHolder) component).getConverter();
        }
        if ((converter == null) && (vb != null)) {
            // Acquire implicit by-type Converter (if any)
            Class type = vb.getType(context);
            if (type != null) {
                converter = context.getApplication().createConverter(type);
            }
        }

        // Convert the result if we identified a Converter
        if (converter != null) {
            return (converter.getAsString(context, component, value));
        } else if (value == null) {
            return ("");
        } else if (value instanceof String) {
            return ((String) value);
        } else {
            return (value.toString());
        }

    }


}
