/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Action.java,v 1.19 2001/02/23 21:13:09 craigmcc Exp $
 * $Revision: 1.19 $
 * $Date: 2001/02/23 21:13:09 $
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


import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.taglib.html.Constants;
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
 * @version $Revision: 1.19 $ $Date: 2001/02/23 21:13:09 $
 */

public class Action {


    // ----------------------------------------------------- Manifest Constants


    /**
     * The context attributes key under which our <strong>default</strong>
     * configured data source (which must implement
     * <code>javax.sql.DataSource</code>) is stored,
     * if one is configured for this application.
     */
    public static final String DATA_SOURCE_KEY =
      "org.apache.struts.action.DATA_SOURCE";


    /**
     * The request attributes key under which your action should store an
     * <code>org.apache.struts.action.ActionErrors</code> object, if you
     * are using the corresponding custom tag library elements.
     */
    public static final String ERROR_KEY =
      "org.apache.struts.action.ERROR";


    /**
     * The request attributes key under which Struts custom tags might store a
     * <code>Throwable</code> that caused them to report a JspException at
     * runtime.  This value can be used on an error page to provide more
     * detailed information about what really went wrong.
     */
    public static final String EXCEPTION_KEY =
        "org.apache.struts.action.EXCEPTION";


    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionFormBeans</code> collection
     * is normally stored, unless overridden when initializing our
     * ActionServlet.
     */
    public static final String FORM_BEANS_KEY =
        "org.apache.struts.action.FORM_BEANS";


    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionForwards</code> collection
     * is normally stored, unless overridden when initializing our
     * ActionServlet.
     */
    public static final String FORWARDS_KEY =
      "org.apache.struts.action.FORWARDS";


    /**
     * The session attributes key under which the user's selected
     * <code>java.util.Locale</code> is stored, if any.  If no such
     * attribute is found, the system default locale
     * will be used when retrieving internationalized messages.  If used, this
     * attribute is typically set during user login processing.
     */
    public static final String LOCALE_KEY =
      "org.apache.struts.action.LOCALE";


    /**
     * The request attributes key under which our
     * <code>org.apache.struts.ActionMapping</code> instance
     * is passed.
     */
    public static final String MAPPING_KEY =
        "org.apache.struts.action.mapping.instance";


    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionMappings</code> collection
     * is normally stored, unless overridden when initializing our
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


    /**
     * The request attributes key under which our multipart class is stored.
     */
    public static final String MULTIPART_KEY =
        "org.apache.struts.action.mapping.multipartclass";


    /**
     * The context attributes key under which we store the mapping defined
     * for our controller serlet, which will be either a path-mapped pattern
     * (<code>/action/*</code>) or an extension mapped pattern
     * (<code>*.do</code>).
     */
    public static final String SERVLET_KEY =
        "org.apache.struts.action.SERVLET_MAPPING";


    /**
     * The session attributes key under which our transaction token is
     * stored, if it is used.
     */
    public static final String TRANSACTION_TOKEN_KEY =
        "org.apache.struts.action.TOKEN";



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
     *
     * @deprecated Use the new perform() method without a servlet argument
     *
     * @param servlet The ActionServlet instance owning this Action
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The servlet request we are processing
     * @param response The servlet response we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(ActionServlet servlet,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 ServletRequest request,
                                 ServletResponse response)
        throws IOException, ServletException {

        return (perform(mapping, form, request, response));

    }


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
     * Process the specified HTTP request, and create the corresponding
     * HTTP response (or forward to another web component that will create
     * it).  Return an <code>ActionForward</code> instance describing where
     * and how control should be forwarded, or <code>null</code> if the
     * response has already been completed.
     *
     * @deprecated Use the new perform() method without a servlet argument
     *
     * @param servlet The ActionServlet instance owning this Action
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The servlet request we are processing
     * @param response The servlet response we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(ActionServlet servlet,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException, ServletException {

        return (perform(mapping, form, request, response));

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
     * Generate a new transaction token, to be used for enforcing a single
     * request for a particular transaction.
     *
     * @param request The request we are processing
     */
    protected String generateToken(HttpServletRequest request) {

        HttpSession session = request.getSession();
        try {
            byte id[] = session.getId().getBytes();
            byte now[] =
                new Long(System.currentTimeMillis()).toString().getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(id);
            md.update(now);
            return (toHex(md.digest()));
        } catch (IllegalStateException e) {
            return (null);
        } catch (NoSuchAlgorithmException e) {
            return (null);
        }

    }


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
     * Return the message resources for this application.
     */
    protected MessageResources getResources() {

	return (servlet.getResources());

    }


    /**
     * Returns <code>true</code> if the current form's cancel button was
     * pressed.  This method will check if the cancel button generated by
     * <strong>CancelTag</strong> was pressed by the user in the
     * current request.  If true, validation performed by an
     * <strong>ActionForm</strong> validate() method will have been
     * skipped by the controller servlet.
     *
     * @param request The servlet request we are processing
     * @see org.apache.struts.taglib.CancelTag
     * @see org.apache.struts.action.ValidatingActionForm
     */
    protected boolean isCancelled(HttpServletRequest request) {

	return ((request.getParameter(Constants.CANCEL_PROPERTY) != null) ||
                (request.getParameter(Constants.CANCEL_PROPERTY_X) != null));

    }


    /**
     * Return <code>true</code> if there is a transaction token stored in
     * the user's current session, and the value submitted as a request
     * parameter with this action matches it.  Returns <code>false</code>
     * under any of the following circumstances:
     * <ul>
     * <li>No session associated with this request</li>
     * <li>No transaction token saved in the session</li>
     * <li>No transaction token included as a request parameter</li>
     * <li>The included transaction token value does not match the
     *     transaction token in the user's session</li>
     * </ul>
     *
     * @param request The servlet request we are processing
     */
    protected boolean isTokenValid(HttpServletRequest request) {

        // Retrieve the saved transaction token from our session
        HttpSession session = request.getSession(false);
        if (session == null)
            return (false);
        String saved = (String) session.getAttribute(TRANSACTION_TOKEN_KEY);
        if (saved == null)
            return (false);

        // Retrieve the transaction token included in this request
        String token = (String) request.getParameter(Constants.TOKEN_KEY);
        if (token == null)
            return (false);

        // Do the values match?
        return (saved.equals(token));

    }


    /**
     * Reset the saved transaction token in the user's session.  This
     * indicates that transactional token checking will not be needed
     * on the next request that is submitted.
     *
     * @param request The servlet request we are processing
     */
    protected void resetToken(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null)
            return;
        session.removeAttribute(TRANSACTION_TOKEN_KEY);

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


    /**
     * Save a new transaction token in the user's current session, creating
     * a new session if necessary.
     *
     * @param request The servlet request we are processing
     */
    protected void saveToken(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String token = generateToken(request);
        if (token != null)
            session.setAttribute(TRANSACTION_TOKEN_KEY, token);

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
     * Convert a byte array to a String of hexadecimal digits and return it.
     *
     * @param buffer The byte array to be converted
     */
    protected String toHex(byte buffer[]) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buffer.length; i++)
            sb.append(Integer.toHexString((int) buffer[i] & 0xff));
        return (sb.toString());

    }


}
