/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/MessageResourcesConfig.java,v 1.8 2003/03/23 03:56:41 jmitchell Exp $
 * $Revision: 1.8 $
 * $Date: 2003/03/23 03:56:41 $
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
import org.apache.struts.Globals;


/**
 * <p>A JavaBean representing the configuration information of a
 * <code>&lt;message-resources&gt;</code> element in a Struts
 * configuration file.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.8 $ $Date: 2003/03/23 03:56:41 $
 * @since Struts 1.1
 */

public class MessageResourcesConfig implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * Has this component been completely configured?
     */
    protected boolean configured = false;


    // ------------------------------------------------------------- Properties


    /**
     * Fully qualified Java class name of the MessageResourcesFactory class
     * we should use.
     */
    protected String factory =
        "org.apache.struts.util.PropertyMessageResourcesFactory";

    public String getFactory() {
        return (this.factory);
    }

    public void setFactory(String factory) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.factory = factory;
    }


    /**
     * The servlet context attributes key under which this MessageResources
     * instance is stored.
     */
    protected String key = Globals.MESSAGES_KEY;

    public String getKey() {
        return (this.key);
    }

    public void setKey(String key) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.key = key;
    }


    /**
     * Should we return <code>null</code> for unknown message keys?
     */
    protected boolean nullValue = true;

    public boolean getNull() {
        return (this.nullValue);
    }

    public void setNull(boolean nullValue) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.nullValue = nullValue;
    }


    /**
     * Parameter that is passed to the <code>createResources()</code> method
     * of our MessageResourcesFactory implementation.
     */
    protected String parameter = null;

    public String getParameter() {
        return (this.parameter);
    }

    public void setParameter(String parameter) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.parameter = parameter;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Freeze the configuration of this component.
     */
    public void freeze() {

        configured = true;

    }


    /**
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("MessageResourcesConfig[");
        sb.append("factory=");
        sb.append(this.factory);
        sb.append(",null=");
        sb.append(this.nullValue);
        sb.append(",parameter=");
        sb.append(this.parameter);
        sb.append("]");
        return (sb.toString());

    }


}
