/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Action.java,v 1.8 2000/11/18 22:10:55 craigmcc Exp $
 * $Revision: 1.8 $
 * $Date: 2000/11/18 22:10:55 $
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
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.taglib.form.Constants;
import org.apache.struts.util.ErrorMessages;
import org.apache.struts.util.MessageResources;


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
 *     share global resources across requests for the same action.</li>
 * <li>Access to other resources (JavaBeans, session variables, etc.) MUST
 *     be synchronized if those resources require protection.  (Generally,
 *     however, resource classes should be designed to provide their own
 *     protection where necessary.</li>
 * </ul>
 * <p>
 * When an <code>Action</code> instance is first created, the controller
 * servlet will call <code>setServlet()</code> with a non-null argument to
 * identify the controller servlet instance to which this Action is attached.
 * When the controller servlet is to be shut down (or restarted), the
 * <code>setServlet()</code> method will be called with a <code>null</code>
 * argument, which can be used to clean up any allocated resources in use
 * by this Action.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.8 $ $Date: 2000/11/18 22:10:55 $
 */

public class Action {


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
     * The request attribute key under which Struts custom tags might store a
     * <code>Throwable</code> that caused them to report a JspException at
     * runtime.  This value can be used on an error page to provide more
     * detailed information about what really went wrong.
     */
    public static final String EXCEPTION_KEY =
        "org.apache.struts.action.EXCEPTION";


    /**
     * The context attributes key under which our ActionFormBeans collection
     * are normally stored, unless overridden when initializing our
     * ActionServlet.
     */
    public static final String FORM_BEANS_KEY =
        "org.apache.struts.action.FORM_BEANS";


    /**
     * The context attributes key under which our ActionForwards collection
     * are normally stored, unless overridden when initializing our
     * ActionServlet.
     */
    public static final String FORWARDS_KEY =
      "org.apache.struts.action.FORWARDS";


    /**
     * The session attribute key under which the user's selected Locale is
     * stored, if any.  If no such attribute is found, the system default locale
     * will be used when retrieving internationalized messages.  If used, this
     * attribute is typically set during user login processing.
     */
    public static final String LOCALE_KEY =
      "org.apache.struts.action.LOCALE";


    /**
     * The context attributes key under which our ActionMappings collection
     * are normally stored, unless overridden when initializing our
     * ActionServlet.
     */
    public static final String MAPPINGS_KEY =
      "org.apache.struts.action.MAPPINGS";


    /**
     * The context attributes key under which our application resources are
     * normally stored, unless overridden when initializing our ActionServlet.
     */
    public static final String MESSAGES_KEY =
      "org.apache.struts.action.MESSAGE";


    // ----------------------------------------------------- Instance Variables


    /**
     * The system default Locale.
     */
    protected static Locale defaultLocale = Locale.getDefault();


    /**
     * The controller servlet to which we are attached.
     */
    protected ActionServlet servlet = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the controller servlet instance to which we are attached.
     */
    public ActionServlet getServlet() {

        return (this.servlet);

    }


    /**
     * Set the controller servlet instance to which we are attached (if
     * <code>servlet</code> is non-null), or release any allocated resources
     * (if <code>servlet</code> is null).
     *
     * @param servlet The new controller servlet, if any
     */
    public void setServlet(ActionServlet servlet) {

        this.servlet = servlet;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Process the specified non-HTTP request, and create the corresponding
     * non-HTTP response (or forward to another web component that will create
     * it).  Return an <code>ActionForward</code> instance describing where
     * and how control should be forwarded, or <code>null</code> if the
     * response has already been completed.
     * <p>
     * The default implementation attempts to forward to the HTTP version of
     * this method.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 ServletRequest request,
                                 ServletResponse response)
        throws IOException, ServletException {

        try {
            return (perform(mapping, form,
                            (HttpServletRequest) request,
                            (HttpServletResponse) response));
        } catch (ClassCastException e) {
            return (null);
        }

    }


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws IOException, ServletException {

        return (null);  // Override this method to provide functionality

    }


    // ---------------------------------------------------- Protected Methods


    /**
     * Return the user's currently selected Locale.
     *
     * @param request The request we are processing
     */
    protected Locale getLocale(HttpServletRequest request) {

	HttpSession session = request.getSession();
	Locale locale = (Locale) session.getAttribute(LOCALE_KEY);
	if (locale == null)
	    locale = defaultLocale;
	return (locale);

    }


    /**
     * Set the user's currently selected Locale.
     *
     * @param request The request we are processing
     * @param locale The user's selected Locale to be set, or null
     *  to select the server's default Locale
     */
    protected void setLocale(HttpServletRequest request, Locale locale) {

	HttpSession session = request.getSession();
	if (locale == null)
	    locale = defaultLocale;
	session.setAttribute(LOCALE_KEY, locale);

    }


    /**
     * Return the message resources for this application.
     */
    protected MessageResources getResources() {

	return (servlet.getResources());

    }


    /**
     * Returns <code>true</code> if the form cancel button was pressed.
     * This method will check if the cancel button generated by
     * <strong>CancelTag</strong> was pressed by the user in the
     * current request.  If true, validation by any
     * <strong>ValidatingActionForm</strong> validate() method will be
     * skipped.
     *
     * @param request The servlet request we are processing
     * @see org.apache.struts.taglib.CancelTag
     * @see org.apache.struts.action.ValidatingActionForm
     */
    protected boolean isCancelled(HttpServletRequest request) {

	return (request.getParameter(Constants.CANCEL_PROPERTY) != null);

    }


    /**
     * Save the specified error messages keys into the appropriate request
     * attribute for use by the &lt;struts:errors&gt; tag, if any messages
     * are required.  Otherwise, ensure that the request attribute is not
     * created.
     *
     * @param request The servlet request we are processing
     * @param errors Error messages object
     */
    protected void saveErrors(HttpServletRequest request,
			      ActionErrors errors) {

	// Remove any error messages attribute if none are required
	if ((errors == null) || errors.empty()) {
	    request.removeAttribute(ERROR_KEY);
	    return;
	}

	// Save the error messages we need
	request.setAttribute(ERROR_KEY, errors);

    }


}
