/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/ExceptionConfig.java,v 1.6 2003/02/08 23:31:29 craigmcc Exp $
 * $Revision: 1.6 $
 * $Date: 2003/02/08 23:31:29 $
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


/**
 * <p>A JavaBean representing the configuration information of an
 * <code>&lt;exception&gt;</code> element from a Struts
 * configuration file.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.6 $ $Date: 2003/02/08 23:31:29 $
 * @since Struts 1.1
 */

public class ExceptionConfig implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * Has this component been completely configured?
     */
    protected boolean configured = false;


    // ------------------------------------------------------------- Properties


    /**
     * The servlet context attribute under which the message resources bundle
     * to be used for this exception is located.  If not set, the default
     * message resources for the current module is assumed.
     */
    protected String bundle = null;

    public String getBundle() {
        return (this.bundle);
    }

    public void setBundle(String bundle) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.bundle = bundle;
    }


    /**
     * The fully qualified Java class name of the exception handler class
     * which should be instantiated to handle this exception.
     */
    protected String handler = "org.apache.struts.action.ExceptionHandler";

    public String getHandler() {
        return (this.handler);
    }

    public void setHandler(String handler) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.handler = handler;
    }


    /**
     * The message resources key specifying the error message
     * associated with this exception.
     */
    protected String key = null;

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
     * The module-relative path of the resource to forward to if this
     * exception occurs during an <code>Action</code>.
     */
    protected String path = null;

    public String getPath() {
        return (this.path);
    }

    public void setPath(String path) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.path = path;
    }


    /**
     * The scope in which we should expose the ActionError for this exception
     * handler.
     */
    protected String scope = "request";

    public String getScope() {
        return (this.scope);
    }

    public void setScope(String scope) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.scope = scope;
    }


    /**
     * The fully qualified Java class name of the exception that is to be
     * handled by this handler.
     */
    protected String type = null;

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.type = type;
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

        StringBuffer sb = new StringBuffer("ExceptionConfig[");
        sb.append("type=");
        sb.append(this.type);
        if (this.bundle != null) {
            sb.append(",bundle=");
            sb.append(this.bundle);
        }
        sb.append(",key=");
        sb.append(this.key);
        sb.append(",path=");
        sb.append(this.path);
        sb.append(",scope=");
        sb.append(this.scope);
        sb.append("]");
        return (sb.toString());

    }


}
