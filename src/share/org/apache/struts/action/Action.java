/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Action.java,v 1.1 2000/05/31 22:28:11 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2000/05/31 22:28:11 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * An <strong>Action</strong> is an adapter between the contents of an incoming
 * HTTP request and the corresponding business logic that should be executed to
 * process this request.  The controller (ActionServlet) will select an
 * appropriate Action for each request, create an instance (if necessary),
 * and call the <code>perform</code> method.
 * <p>
 * Actions must be programmed in a thread-safe manner, because the controller
 * will share the same instance for multiple simultaneous requests.  In
 * this means you should design with the following items in mind:
 * <ul>
 * <li>Instance and static variables MUST NOT be used to store information
 *     related to the state of a particular request.  They MAY be used to
 *     share global resources across requests for the same action.
 * <li>Access to other resources (JavaBeans, session variables, etc.) MUST
 *     be synchronized if those resources require protection.  (Generally,
 *     however, resource classes should be designed to provide their own
 *     protection where necessary.
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2000/05/31 22:28:11 $
 */

public interface Action {


    // ----------------------------------------------------- Manifest Constants


    /**
     * The request attribute key under which your action should store an error
     * messages, if you are using the corresponding custom tag library elements.
     * The stored data type should be a String array of individual errors.  If
     * this request attribute is missing, or it is a zero-length array, the error
     * tag will assume that no problems need to be reported.
     */
    public static final String ERROR_KEY =
      "org.apache.struts.action.ERROR";


    /**
     * The session attribute key under which the user's selected Locale is
     * stored, if any.  If no such attribute is found, the system default locale
     * will be used when retrieving internationalized messages.  If used, this
     * attribute is typically set during user login processing.
     */
    public static final String LOCALE_KEY =
      "org.apache.struts.action.LOCALE";


    /**
     * The context attributes key under which our application resources are
     * normally stored, unless overridden when initializing our ActionServlet.
     */
    public static final String MESSAGES_KEY =
      "org.apache.struts.action.MESSAGE";


    // --------------------------------------------------------- Public Methods


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     *
     * @param servlet The ActionServlet making this request
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public void perform(ActionServlet servlet,
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws IOException, ServletException;


}
