/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/renderer/AbstractRenderer.java,v 1.1 2003/03/07 03:22:44 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/07 03:22:44 $
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
import java.util.HashMap;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.component.AttributeDescriptor;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;
import org.apache.struts.faces.util.SupportedComponent;


/**
 * <p>Abstract base class for concrete implementations of
 * <code>javax.faces.render.Renderer</code> for the
 * <em>Struts-Faces Integration Library</em>.</p>
 *
 * <p><strong>IMPLEMENTATION NOTE</strong> - The default implementation
 * assumes that a particular UIComponent class is supported if we have
 * declared support for that class's component type.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/03/07 03:22:44 $
 */

public abstract class AbstractRenderer extends Renderer {


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The supported component descriptions for the component types
     * supported by this Renderer, keyed by component type.</p>
     */
    private HashMap components = new HashMap();


    // ------------------------------------------------------------- Properties


    /**
     * <p>The renderer type implemented by this Renderer.</p>
     */
    private String rendererType = null;


    /**
     * <p>Return the renderer type for this Renderer.</p>
     */
    public String getRendererType() {

        return (this.rendererType);

    }


    /**
     * <p>Set the renderer type for this Renderer.</p>
     *
     * @param rendererType The new renderer type
     */
    public void setRendererType(String rendererType) {

        this.rendererType = rendererType;

    }


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

        // Has a client identifier been generated for this component already?
	String result = null;
	if (null != (result = (String) component.getAttribute("clientId"))) {
	    return result;
	}
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
	    if (null == (result = component.getComponentId())) {
		// Don't call setComponentId() because it checks for
		// uniqueness.  No need.
		component.setAttribute("componentId",
				       result = closestContainer.generateClientId());
	    }

	    // build the client side id
	    containerComponent = (UIComponent) closestContainer;
	    // If this is the root naming container, break
	    if (null != containerComponent.getParent()) {
		result = containerComponent.getClientId(context) +
		    UIComponent.SEPARATOR_CHAR + result;
	    }

	}
	
        // Store the client identifier for future use
	if (null == result) {
	    throw new NullPointerException();
	}
	component.setAttribute("clientId", result);
	return (result);

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

        ; // Default implementation does nothing

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

        ; // Default implementation does nothing

    }


    /**
     * <p>Return an <code>AttributeDescriptor</code> for the specified
     * attribute name, as supported for the specified component type.</p>
     *
     * @param componentType Component type that is supported
     * @param name Attribute naem for which to return a descriptor
     *
     * @exception IllegalArgumentException if the specified component type
     *  is not supported
     * @exception IllegalArgumentException if the specified attribute name
     *  is not supported for the specified component type
     * @exception NullPointerException if componentType or name is null
     */
    public AttributeDescriptor getAttributeDescriptor(String componentType,
                                                      String name) {

        if (componentType == null) {
            // FIXME - i18n
            throw new NullPointerException("No component type specified");
        }
        if (name == null) {
            // FIXME - i18n
            throw new NullPointerException("No attribute name specified");
        }

        SupportedComponent supported = (SupportedComponent)
            components.get(componentType);
        if (supported == null) {
            // FIXME - i18n
            throw new IllegalArgumentException("Component type '" +
                                               componentType +
                                               "' is not supported");
        }
        AttributeDescriptor descriptor =
            supported.getAttributeDescriptor(name);
        if (descriptor == null) {
            // FIXME - i18n
            throw new IllegalArgumentException("Attribute name '" +
                                               name +
                                               "' is not supported");
        }
        return (descriptor);

    }


    /**
     * <p>Return an <code>AttributeDescriptor</code> for the specified
     * attribute name, as supported for the specified <code>UIComponent</code>
     * class.</p>
     *
     * @param component UIComponent whose implementation class is supported
     * @param name Attribute naem for which to return a descriptor
     *
     * @exception IllegalArgumentException if the specified component class
     *  is not supported
     * @exception IllegalArgumentException if the specified attribute name
     *  is not supported for the specified component class
     * @exception NullPointerException if component or name is null
     */
    public AttributeDescriptor getAttributeDescriptor(UIComponent component,
                                                      String name) {

        if (component == null) {
            // FIXME - i18n
            throw new NullPointerException("No component specified");
        }
        return (getAttributeDescriptor(component.getComponentType(), name));

    }

 
   /**
     * <p>Return an Iterator over the names of the supported attributes
     * for the specified component type.  If no attributes are supported,
     * an empty Iterator is returned.</p>
     *
     * @param componentType Component type that is supported
     *
     * @exception IllegalArgumentException if the specified component type
     *  is not supported
     * @exception NullPointerException if componentType is null
     */
    public Iterator getAttributeNames(String componentType) {

        if (componentType == null) {
            // FIXME - i18n
            throw new NullPointerException("No component type specified");
        }

        SupportedComponent supported = (SupportedComponent)
            components.get(componentType);
        if (supported == null) {
            // FIXME - i18n
            throw new IllegalArgumentException("Component type '" +
                                               componentType +
                                               "' is not supported");
        }
        return (supported.getAttributeNames());

    }


    /**
     * <p>Return an Iterator over the names of the supported attributes
     * for the specified UIComponent class.  If no attributes are supported,
     * an empty Iterator is returned.</p>
     *
     * @param component UIComponent whose implementation class is supported
     *
     * @exception IllegalArgumentException if the specified component class
     *  is not supported
     * @exception NullPointerException if component is null
     */
    public Iterator getAttributeNames(UIComponent component) {

        if (component == null) {
            // FIXME - i18n
            throw new NullPointerException("No component specified");
        }
        return (getAttributeNames(component.getComponentType()));

    }


    /**
     * <p>Return <code>true</code> if this Renderer supports the specified
     * component type.</p>
     *
     * @param componentType Component type to be checked
     */
    public boolean supportsComponentType(String componentType) {

        if (componentType == null) {
            // FIXME - i18n
            throw new NullPointerException("No component type specified");
        }
        return (components.get(componentType) != null);

    }


    /**
     * <p>Return <code>true</code> if this Renderer supports the specified
     * component class.</p>
     *
     * @param component Component whose class is to be checked
     */
    public boolean supportsComponentType(UIComponent component) {

        if (component == null) {
            // FIXME - i18n
            throw new NullPointerException("No component specified");
        }
        return (supportsComponentType(component.getComponentType()));

    }


    // -------------------------------------------------- Configuration Methods


    /**
     * <p>Add a new supported component for this Renderer.</p>
     *
     * @param component The supported component instance to be added
     */
    public void addSupportedComponent(SupportedComponent component) {

        components.put(component.getComponentType(), component);

    }


    // -------------------------------------------------------- Package Methods


    // ------------------------------------------------------ Protected Methods


}
