/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/renderer/AbstractRenderer.java,v 1.3 2003/07/27 06:43:16 jmitchell Exp $
 * $Revision: 1.3 $
 * $Date: 2003/07/27 06:43:16 $
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
import javax.faces.application.MessageImpl;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.render.Renderer;


/**
 * <p>Abstract base class for concrete implementations of
 * <code>javax.faces.render.Renderer</code> for the
 * <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2003/07/27 06:43:16 $
 */

public abstract class AbstractRenderer extends Renderer {


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Return the client-side id for the argument component.</p>
     *
     * <p>The purpose of this method is to give Renderers a chance to
     * define, in a rendering specific way, the client side id for this
     * component.  The client side id should be derived from the
     * component id, if present.  </p>
     *
     * <p>Look up this component's "clientId" attribute.  If non-null,
     * return it.  Get the component id for the argument
     * <code>UIComponent</code>.  If null, generate one using the closest
     * naming container that is an ancestor of this UIComponent, then set
     * the generated id as the componentId of this UIComponent.  Prepend
     * to the component id the component ids of each naming container up
     * to, but not including, the root, separated by the
     * UIComponent.SEPARATOR_CHAR.  In all cases, save the result as the
     * value of the "clientId" attribute.</p>
     *
     * <p>This method must not return null.</p>
     */ 
    public String getClientId(FacesContext context, UIComponent component) {

	String clientId = null;
	NamingContainer closestContainer = null;
	UIComponent containerComponent = component;

        // Search for an ancestor that is a naming container
        while (null != (containerComponent = 
                        containerComponent.getParent())) {
            if (containerComponent instanceof NamingContainer) {
                closestContainer = (NamingContainer) containerComponent;
                break;
            }
        }

        // If none is found, see if this is a naming container
        if (null == closestContainer && component instanceof NamingContainer) {
            closestContainer = (NamingContainer) component;
        }

        if (null != closestContainer) {

            // If there is no componentId, generate one and store it
            if (component.getComponentId() == null) {
                // Don't call setComponentId() because it checks for
                // uniqueness.  No need.
                clientId = closestContainer.generateClientId();
            } else {
                clientId = component.getComponentId();
            }

            // build the client side id
            containerComponent = (UIComponent) closestContainer;

            // If this is the root naming container, break
            if (null != containerComponent.getParent()) {
                clientId = containerComponent.getClientId(context) +
                    UIComponent.SEPARATOR_CHAR + clientId;
            }
        }

        if (null == clientId) {
	    throw new NullPointerException();
	}
	return (clientId);

    }


    /**
     * <p>Decode the current state of the specified UIComponent from the
     * request contained in the specified <code>FacesContext</code>, and
     * attempt to convert this state information into an object of the
     * type equired for this component.</p>
     *
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be decoded
     *
     * @return <code>true</code> if conversion was successful (or no
     *  conversion processing was required), or <code>false</code> if
     *  conversion was not successful
     *
     * @exception IOException if an input/output error occurs while decoding
     * @exception NullPointerException if context or component is null
     */
    public void decode(FacesContext context, UIComponent component)
        throws IOException {

        // Enforce NPE requirements in the Javadocs
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        // Only input components need to be decoded
        if (!(component instanceof UIInput)) {
            component.setValid(true);
            return;
        }
        UIInput input = (UIInput) component;

        // Save the old value for use in generating ValueChangedEvents
        Object oldValue = input.currentValue(context);
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


    /**
     * <p>Render the beginning specified {@link UIComponent} to the
     * output stream or writer associated with the response we are creating.
     * If the conversion attempted in a previous call to <code>decode</code>
     * for this component failed, the state information saved during execution
     * of <code>decode()</code> should be utilized to reproduce the incorrect
     * input.  If the conversion was successful, or if there was no previous
     * call to <code>decode()</code>, the value to be displayed should be
     * acquired by calling <code>component.currentValue()</code>, and
     * rendering the value as appropriate.</p>
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
        ; // Default implementation does nothing

    }


    /**
     * <p>Render the child components of this {@link UIComponent}, following
     * the rules described for <code>encodeBegin()</code> to acquire the
     * appropriate value to be rendered.  This method will only be called
     * if the <code>rendersChildren</code> property of this component
     * is <code>true</code>.</p>
     *
     * @param context FacesContext for the response we are creating
     * @param component UIComponent whose children are to be rendered
     *
     * @exception IOException if an input/output error occurs while rendering
     * @exception NullPointerException if <code>context</code>
     *  is <code>null</code>
     */
    public void encodeChildren(FacesContext context, UIComponent component)
        throws IOException {

        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        ; // Default implementation does nothing

    }


    /**
     * <p>Render the ending of the current state of the specified
     * {@link UIComponent}, following the rules described for
     * <code>encodeBegin()</code> to acquire the appropriate value
     * to be rendered.</p>
     *
     * @param context FacesContext for the response we are creating
     * @param component UIComponent whose children are to be rendered
     *
     * @exception IOException if an input/output error occurs while rendering
     * @exception NullPointerException if <code>context</code>
     *  is <code>null</code>
     */
    public void encodeEnd(FacesContext context, UIComponent component)
        throws IOException {

        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        ; // Default implementation does nothing

    }


    // -------------------------------------------------------- Package Methods


    // ------------------------------------------------------ Protected Methods


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

        MessageImpl message = new MessageImpl();
        message.setSummary("Conversion Error on component '" +
                           component.getClientId(context) +
                           ": " + text);
        context.addMessage(component, message);

    }


    /**
     * <p>Return the <code>Application</code> instance for this webapp.</p>
     */
    protected Application getApplication() {

        ApplicationFactory factory = (ApplicationFactory)
            FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        return (factory.getApplication());

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

        // Is there a Converter associated with this component?
        String converterId = component.getConverter();
        if (converterId == null) {
            return (value);
        }

        // Look up the Converter instance and use it
        Converter converter = getApplication().getConverter(converterId);
        return (converter.getAsObject(context, component, value));

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

        // Is there a Converter associated with this component?
        String converterId = component.getConverter();
        if (converterId == null) {
            if (value == null) {
                return ("");
            } else if (value instanceof String) {
                return ((String) value);
            } else {
                return (value.toString());
            }
        }

        // Look up the Converter instance and use it
        Converter converter = getApplication().getConverter(converterId);
        return (converter.getAsString(context, component, value));

    }


}
