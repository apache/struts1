/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionForward.java,v 1.4 2001/02/21 00:35:44 craigmcc Exp $
 * $Revision: 1.4 $
 * $Date: 2001/02/21 00:35:44 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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


package org.apache.struts.action;


import java.io.Serializable;


/**
 * An <strong>ActionForward</strong> represents a destination to which the
 * controller servlet, <code>ActionServlet</code>, might be directed to
 * perform a <code>RequestDispatcher.forward()</code> or
 * <code>HttpServletResponse.sendRedirect()</code> to, as a result of
 * processing activities of an <code>Action</code> class.  Instances of this
 * class may be created dynamically as necessary, or configured in association
 * with an <code>ActionMapping</code> instance for named lookup of potentially
 * multiple destinations for a particular mapping instance.
 * <p>
 * An <code>ActionForward</code> has the following minimal set of properties.
 * Additional properties can be provided as needed by subclassses.
 * <ul>
 * <li><strong>name</strong> - Logical name by which this instance may be
 *     looked up in relationship to a particular <code>ActionMapping</code>.
 * <li><strong>path</strong> - Context-relative URI to which control should
 *     be forwarded, or an absolute or relative URI to which control should
 *     be redirected.
 * <li><strong>redirect</strong> - Set to <code>true</code> if the controller
 *     servlet should call <code>HttpServletResponse.sendRedirect()</code>
 *     on the associated path; otherwise <code>false</code>.  [false]
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.4 $ $Date: 2001/02/21 00:35:44 $
 */

public class ActionForward implements Serializable {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new instance with default values.
     */
    public ActionForward() {

	this(null, false);

    }


    /**
     * Construct a new instance with the specified path.
     *
     * @param path Path for this instance
     */
    public ActionForward(String path) {

	this(path, false);

    }


    /**
     * Construct a new instance with the specified path and redirect flag.
     *
     * @param path Path for this instance
     * @param redirect Redirect flag for this instance
     */
    public ActionForward(String path, boolean redirect) {

	super();
	setName(null);
	setPath(path);
	setRedirect(redirect);

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The logical name of this forward.
     */
    protected String name = null;


    /**
     * The context-relative (for a forward) or relative or absolute (for a
     * redirect) URI path to be forwarded to.
     */
    protected String path = null;


    /**
     * Should this be a redirect instead of a forward?
     */
    protected boolean redirect = false;


    // ------------------------------------------------------------- Properties


    /**
     * Return the name.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the name.
     *
     * @param name The new name
     */
    public void setName(String name) {

	this.name = name;

    }


    /**
     * Return the path.
     */
    public String getPath() {

	return (this.path);

    }


    /**
     * Set the path.
     *
     * @param path The new path
     */
    public void setPath(String path) {

	this.path = path;

    }


    /**
     * Return the redirect flag.
     */
    public boolean getRedirect() {

	return (this.redirect);

    }


    /**
     * Set the redirect flag.
     *
     * @param redirect The new redirect flag
     */
    public void setRedirect(boolean redirect) {

	this.redirect = redirect;

    }


    // --------------------------------------------------------- Public Methods

    /**
     * Return a String version of this mapping.
     */
    public String toString() {

	return ("ActionForward[" + name + "]");

    }


}
