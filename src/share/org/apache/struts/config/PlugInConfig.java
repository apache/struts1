/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/PlugInConfig.java,v 1.2 2002/07/09 23:57:37 husted Exp $
 * $Revision: 1.2 $
 * $Date: 2002/07/09 23:57:37 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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


package org.apache.struts.config;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>A JavaBean representing the configuration information of a
 * <code>&lt;plug-in&gt;</code> element in a Struts
 * configuration file.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2002/07/09 23:57:37 $
 * @since Struts 1.1
 */

public class PlugInConfig implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * Has this component been completely configured?
     */
    protected boolean configured = false;


    /**
     * A <code>Map</code> of the name-value pairs that will be used to
     * configure the property values of a <code>PlugIn</code> instance.
     */
    protected Map properties = new HashMap();


    // ------------------------------------------------------------- Properties


    /**
     * The fully qualified Java class name of the <code>PlugIn</code>
     * implementation class being configured.
     */
    protected String className = null;

    public String getClassName() {
        return (this.className);
    }

    public void setClassName(String className) {
        this.className = className;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Add a new property name and value to the set that will be used to
     * configure the <code>PlugIn</code> instance.
     *
     * @param name Property name
     * @param value Property value
     */
    public void addProperty(String name, String value) {

        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        properties.put(name, value);

    }


    /**
     * Freeze the configuration of this component.
     */
    public void freeze() {

        configured = true;

    }


    /**
     * Return the properties that will be used to configure a
     * <code>PlugIn</code> instance.
     */
    public Map getProperties() {

        return (properties);

    }


}
