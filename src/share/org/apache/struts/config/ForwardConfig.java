/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/ForwardConfig.java,v 1.13 2004/02/19 18:45:05 husted Exp $
 * $Revision: 1.13 $
 * $Date: 2004/02/19 18:45:05 $
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
 *    any, must include the following acknowledgement:
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
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
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
 * <p>A JavaBean representing the configuration information of a
 * <code>&lt;forward&gt;</code> element from a Struts
 * configuration file.</p>
 *
 * @version $Revision: 1.13 $ $Date: 2004/02/19 18:45:05 $
 * @since Struts 1.1
 */

public class ForwardConfig implements Serializable {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new instance with default values.
     */
    public ForwardConfig() {

        super();

    }


    /**
     * Construct a new instance with the specified values.
     *
     * @param name Name of this forward
     * @param path Path to which control should be forwarded or redirected
     * @param redirect Should we do a redirect?
     */
    public ForwardConfig(String name, String path, boolean redirect) {

        super();
        setName(name);
        setPath(path);
        setRedirect(redirect);

    }


    /**
     * Construct a new instance with the specified values.
     *
     * @param name Name of this forward
     * @param path Path to which control should be forwarded or redirected
     * @param redirect Should we do a redirect?
     * @param contextRelative Is this path context relative?
     * @deprecated Use module rather than contextRelative
     */
    public ForwardConfig(String name, String path, boolean redirect,
                         boolean contextRelative) {

        super();
        setName(name);
        setPath(path);
        setRedirect(redirect);
        setContextRelative(contextRelative);

    }

    /**
     * Construct a new instance with the specified values.
     *
     * @param name Name of this forward
     * @param path Path to which control should be forwarded or redirected
     * @param redirect Should we do a redirect?
     * @param module Module prefix, if any
     */
    public ForwardConfig(String name, String path, boolean redirect,
                         String module) {

        super();
        setName(name);
        setPath(path);
        setRedirect(redirect);
        setModule(module);

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * Has this component been completely configured?
     */
    protected boolean configured = false;


    // ------------------------------------------------------------- Properties


    /**
     * Should the value of the <code>path</code> property be considered
     * context-relative if it starts with a slash (and therefore not
     * prefixed with the module prefix?
     * @deprecated Use module property instead; will be removed in a release following 1.2.0.
     */
    protected boolean contextRelative = false;

    /**
     * @deprecated Use module property instead; will be removed in a release following 1.2.0.
     */
    public boolean getContextRelative() {
        return (this.contextRelative);
    }

    /**
     * @deprecated Use module property instead; will be removed in a release following 1.2.0.
     */
    public void setContextRelative(boolean contextRelative) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.contextRelative = contextRelative;
    }


    /**
     * The unique identifier of this forward, which is used to reference it
     * in <code>Action</code> classes.
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.name = name;
    }


    /**
     * <p>The URL to which this <code>ForwardConfig</code> entry points,
     * which must start with a slash ("/") character.  It is
     * interpreted according to the following rules:</p>
     * <ul>
     * <li>If <code>contextRelative</code> property is <code>true</code>, the
     *     path is considered to be context-relative within the current web
     *     application (even if we are in a named module).  It will be
     *     prefixed by the context path to create a server-relative URL.</li>
     * <li>If the <code>contextRelative</code> property is false, the path is
     *     considered to be the module-relative portion of the URL.
     *     It will be used as the replacement for the <code>$P</code>
     *     marker in the <code>forwardPattern</code> property defined on the
     *     {@link ControllerConfig} element for our current module.
     *     For the default <code>forwardPattern</code> value of
     *     <code>$C$M$P</code>, the resulting server-relative URL will be
     *     the concatenation of the context path, the module prefix,
     *     and the <code>path</code> from this <code>ForwardConfig</code>.</li>
     * </ul>
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
	 * <p>The prefix of the module to which this <code>ForwardConfig</code> entry points,
	 * which must start with a slash ("/") character.  </p>
	 */
	protected String module = null;

	public String getModule() {
		return (this.module);
	}

	public void setModule(String module) {
		if (configured) {
			throw new IllegalStateException("Configuration is frozen");
		}
		this.module = module;
	}


    /**
     * Should a redirect be used to transfer control to the specified path?
     */
    protected boolean redirect = false;

    public boolean getRedirect() {
        return (this.redirect);
    }

    public void setRedirect(boolean redirect) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.redirect = redirect;
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

        StringBuffer sb = new StringBuffer("ForwardConfig[");
        sb.append("name=");
        sb.append(this.name);
        sb.append(",path=");
        sb.append(this.path);
        sb.append(",redirect=");
        sb.append(this.redirect);
        sb.append(",contextRelative=");
        sb.append(this.contextRelative);
        sb.append(",module=");
        sb.append(this.module);
        sb.append("]");
        return (sb.toString());

    }


}
