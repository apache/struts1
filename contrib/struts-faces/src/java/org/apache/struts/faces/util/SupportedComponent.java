/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/util/Attic/SupportedComponent.java,v 1.1 2003/03/07 03:22:45 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/07 03:22:45 $
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

package org.apache.struts.faces.util;


import java.util.HashMap;
import java.util.Iterator;
import javax.faces.component.AttributeDescriptor;


/**
 * <p>Utility class representing a component type that is supported by
 * a Renderer implementation in the
 * <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/03/07 03:22:45 $
 */

public class SupportedComponent {


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The list of attribute descriptors for attributes supported for the
     * component type represented by this instance.</p>
     */
    private HashMap descriptors = new HashMap();


    // ------------------------------------------------------------- Properties


    /**
     * <p>The component type of the supported component represented by
     * this instance.</p>
     */
    private String componentType = null;

    public String getComponentType() {
        return (this.componentType);
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }


    /**
     * <p>The help text describing this supported component.</p>
     */
    private String description = null;

    public String getDescription() {
        return (this.description);
    }

    public void setDescription(String description) {
        if (description != null) {
            description = description.trim();
            if (description.length() > 0) {
                this.description = description;
            }
        } else {
            this.description = null;
        }
    }


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Add a new attribute descriptor to those supported for this
     * component type.</p>
     *
     * @param descriptor The new attribute descriptor
     */
    public void addAttributeDescriptor(AttributeDescriptor descriptor) {

        descriptors.put(descriptor.getName(), descriptor);

    }


    /**
     * <p>Return the attribute descriptor for the specified attribute name
     * if it is supported; otherwise, return <code>null</code>.</p>
     *
     * @param name Attribute name for which to retrieve a descriptor
     */
    public AttributeDescriptor getAttributeDescriptor(String name) {

        return ((AttributeDescriptor) descriptors.get(name));

    }


    /**
     * <p>Return an Iterator over the attribute names of all
     * supported attributes for this component type.</p>
     */
    public Iterator getAttributeNames() {

        return (descriptors.keySet().iterator());

    }



}
