/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Action.java,v 1.61 2003/06/28 06:16:34 dgraham Exp $
 * $Revision: 1.61 $
 * $Date: 2003/06/28 06:16:34 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.TokenProcessor;

/**
 * An <strong>Action</strong> is an adapter between the contents of an incoming
 * HTTP request and the corresponding business logic that should be executed to
 * process this request.  The controller (ActionServlet) will select an
 * appropriate Action for each request, create an instance (if necessary),
 * and call the <code>perform</code> method.</p>
 *
 * <p>Actions must be programmed in a thread-safe manner, because the
 * controller will share the same instance for multiple simultaneous
 * requests.  This means you should design with the following items in mind:
 * </p>
 * <ul>
 * <li>Instance and static variables MUST NOT be used to store information
 *     related to the state of a particular request.  They MAY be used to
 *     share global resources across requests for the same action.</li>
 * <li>Access to other resources (JavaBeans, session variables, etc.) MUST
 *     be synchronized if those resources require protection.  (Generally,
 *     however, resource classes should be designed to provide their own
 *     protection where necessary.</li>
 * </ul>
 *
 * <p>When an <code>Action</code> instance is first created, the controller
 * servlet will call <code>setServlet()</code> with a non-null argument to
 * identify the controller servlet instance to which this Action is attached.
 * When the controller servlet is to be shut down (or restarted), the
 * <code>setServlet()</code> method will be called with a <code>null</code>
 * argument, which can be used to clean up any allocated resources in use
 * by this Action.</p>
 *
 * @author Craig R. McClanahan
 * @author David Graham
 * @version $Revision: 1.61 $ $Date: 2003/06/28 06:16:34 $
 */
public class Action {


    // ----------------------------------------------------- Manifest Constants


    // NOTE - The definitions of manifest constant values have moved from
    // here to the org.apache.struts.Globals class, so that they can be
    // referenced without having to maintain a reference to the Action class
    // itself.  Any future new constant values should be defined there
    // instead of here.


    /**
     * The context attributes key under which our <code>ActionServlet</code>
     * instance will be stored.
     * @deprecated Use Globals.ACTION_SERVLET_KEY instead.
     * @since Struts 1.1
     */
    public static final String ACTION_SERVLET_KEY = Globals.ACTION_SERVLET_KEY;


    /**
     * <p>The base of the context attributes key under which our
     * <code>ModuleConfig</code> data structure will be stored.  This
     * will be suffixed with the actual module prefix (including the
     * leading "/" character) to form the actual attributes key.</p>
     *
     * <p>For each request processed by the controller servlet, the
     * <code>ModuleConfig</code> object for the module selected by
     * the request URI currently being processed will also be exposed under
     * this key as a request attribute.</p>
     *
     * @since Struts 1.1
     * @deprecated  Replaced by {@link org.apache.struts.Globals#MODULE_KEY}
     */
    public static final String APPLICATION_KEY = Globals.MODULE_KEY;


    /**
     * The context attributes key under which our <strong>default</strong>
     * configured data source (which must implement
     * <code>javax.sql.DataSource</code>) is stored,
     * if one is configured for this module.
     * @deprecated  Replaced by {@link org.apache.struts.Globals#DATA_SOURCE_KEY}
     */
    public static final String DATA_SOURCE_KEY = Globals.DATA_SOURCE_KEY;


    /**
     * The request attributes key under which your action should store an
     * <code>org.apache.struts.action.ActionErrors</code> object, if you
     * are using the corresponding custom tag library elements.
     * @deprecated  Replaced by {@link org.apache.struts.Globals#ERROR_KEY}
     */
    public static final String ERROR_KEY = Globals.ERROR_KEY;


    /**
     * The request attributes key under which Struts custom tags might store a
     * <code>Throwable</code> that caused them to report a JspException at
     * runtime.  This value can be used on an error page to provide more
     * detailed information about what really went wrong.
     * @deprecated  Replaced by {@link org.apache.struts.Globals#EXCEPTION_KEY}
     */
    public static final String EXCEPTION_KEY = Globals.EXCEPTION_KEY;


    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionFormBeans</code> collection
     * is normally stored, unless overridden when initializing our
     * ActionServlet.
     *
     * @deprecated Replaced by collection in ModuleConfig
     */
    public static final String FORM_BEANS_KEY = Globals.FORM_BEANS_KEY;


    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionForwards</code> collection
     * is normally stored, unless overridden when initializing our
     * ActionServlet.
     *
     * @deprecated Replaced by collection in ModuleConfig.
     */
    public static final String FORWARDS_KEY = Globals.FORWARDS_KEY;


    /**
     * The session attributes key under which the user's selected
     * <code>java.util.Locale</code> is stored, if any.  If no such
     * attribute is found, the system default locale
     * will be used when retrieving internationalized messages.  If used, this
     * attribute is typically set during user login processing.
     * @deprecated  Replaced by {@link org.apache.struts.Globals#LOCALE_KEY}
     */
    public static final String LOCALE_KEY = Globals.LOCALE_KEY;


    /**
     * The request attributes key under which our
     * <code>org.apache.struts.ActionMapping</code> instance
     * is passed.
     * @deprecated  Replaced by {@link org.apache.struts.Globals#MAPPING_KEY}
     */
    public static final String MAPPING_KEY = Globals.MAPPING_KEY;


    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionMappings</code> collection
     * is normally stored, unless overridden when initializing our
     * ActionServlet.
     *
     * @deprecated Replaced by collection in ModuleConfig
     */
    public static final String MAPPINGS_KEY = Globals.MAPPINGS_KEY;


    /**
     * The request attributes key under which your action should store an
     * <code>org.apache.struts.action.ActionMessages</code> object, if you
     * are using the corresponding custom tag library elements.
     *
     * @since Struts 1.1
     * @deprecated  Replaced by {@link org.apache.struts.Globals#MESSAGE_KEY}
     */
    public static final String MESSAGE_KEY = Globals.MESSAGE_KEY;


    /**
     * <p>The base of the context attributes key under which our
     * module <code>MessageResources</code> will be stored.  This
     * will be suffixed with the actual module prefix (including the
     * leading "/" character) to form the actual resources key.</p>
     *
     * <p>For each request processed by the controller servlet, the
     * <code>MessageResources</code> object for the module selected by
     * the request URI currently being processed will also be exposed under
     * this key as a request attribute.</p>
     * @deprecated Use Globals.MESSAGES_KEY instead.
     */
    public static final String MESSAGES_KEY = Globals.MESSAGES_KEY;


    /**
     * The request attributes key under which our multipart class is stored.
     * @deprecated Use Globals.MULTIPART_KEY instead.
     */
    public static final String MULTIPART_KEY = Globals.MULTIPART_KEY;


    /**
     * <p>The base of the context attributes key under which an array of
     * <code>PlugIn</code> instances will be stored.  This
     * will be suffixed with the actual module prefix (including the
     * leading "/" character) to form the actual attributes key.</p>
     * @since Struts 1.1
     * @deprecated  Replaced by {@link org.apache.struts.Globals#PLUG_INS_KEY}
     */
    public static final String PLUG_INS_KEY = Globals.PLUG_INS_KEY;


    /**
     * <p>The base of the context attributes key under which our
     * <code>RequestProcessor</code> instance will be stored.  This
     * will be suffixed with the actual module prefix (including the
     * leading "/" character) to form the actual attributes key.</p>
     * @since Struts 1.1
     * @deprecated Use Globals.REQUEST_PROCESSOR_KEY instead.
     */
    public static final String REQUEST_PROCESSOR_KEY =
        Globals.REQUEST_PROCESSOR_KEY;


    /**
     * The context attributes key under which we store the mapping defined
     * for our controller serlet, which will be either a path-mapped pattern
     * (<code>/action/*</code>) or an extension mapped pattern
     * (<code>*.do</code>).
     * @deprecated Use Globals.SERVLET_KEY instead.
     */
    public static final String SERVLET_KEY = Globals.SERVLET_KEY;


    /**
     * The session attributes key under which our transaction token is
     * stored, if it is used.
     * @deprecated Use Globals.TRANSACTION_TOKEN_KEY instead.
     */
    public static final String TRANSACTION_TOKEN_KEY =
        Globals.TRANSACTION_TOKEN_KEY;

    /**
     * An instance of TokenProcessor to use for token functionality.
     * @TODO We can make this variable protected and remove Action's token methods
     * or leave it private and allow the token methods to delegate their calls.
     */
    private static TokenProcessor token = TokenProcessor.getInstance();


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
     * Process the specified non-HTTP request, and create the
     * corresponding non-HTTP response (or forward to another web
     * component that will create it), with provision for handling
     * exceptions thrown by the business logic.
     * Return an {@link ActionForward} instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     * <p>
     * The default implementation attempts to forward to the HTTP
     * version of this method.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     *
     * @exception Exception if the application business logic throws
     *  an exception
     * @since Struts 1.1
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 ServletRequest request,
                                 ServletResponse response)
        throws Exception {

        // Call the deprecated method for backwards compatibility
        return (perform(mapping, form, request, response));

    }


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it),
     * with provision for handling exceptions thrown by the business logic.
     * Return an {@link ActionForward} instance describing where and how
     * control should be forwarded, or <code>null</code> if the response
     * has already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception Exception if the application business logic throws
     *  an exception
     * @since Struts 1.1
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        // Call the deprecated method for backwards compatibility
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
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     *
     * @deprecated Use the <code>execute()</code> method instead
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
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     *
     * @deprecated Use the <code>execute()</code> method instead
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
        return token.generateToken(request);
    }


    /**
     * Return the default data source for the current module.
     *
     * @param request The servlet request we are processing
     *
     * @since Struts 1.1
     */
    protected DataSource getDataSource(HttpServletRequest request) {

        return (getDataSource(request, Globals.DATA_SOURCE_KEY));

    }



    /**
     * Return the specified data source for the current module.
     *
     * @param request The servlet request we are processing
     * @param key The key specified in the
     *  <code>&lt;message-resources&gt;</code> element for the
     *  requested bundle
     *
     * @since Struts 1.1
     */
    protected DataSource getDataSource(HttpServletRequest request, String key) {

        // Identify the current module
        ServletContext context = getServlet().getServletContext();
        ModuleConfig moduleConfig = RequestUtils.getModuleConfig(request,context);

        // Return the requested data source instance
        return ((DataSource) context.getAttribute(key + moduleConfig.getPrefix()));

    }


    /**
     * Return the user's currently selected Locale.
     *
     * @param request The request we are processing
     */
    protected Locale getLocale(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        if (locale == null) {
            locale = defaultLocale;
        }
        return (locale);

    }


    /**
     * Return the message resources for the default module.
     *
     * @deprecated This method can only return the resources for the default
     *  module.  Use getResources(HttpServletRequest) to get the
     *  resources for the current module.
     */
    protected MessageResources getResources() {

        return ((MessageResources)
                servlet.getServletContext().getAttribute(Globals.MESSAGES_KEY));

    }


    /**
     * Return the default message resources for the current module.
     *
     * @param request The servlet request we are processing
     * @since Struts 1.1
     */
    protected MessageResources getResources(HttpServletRequest request) {

        return ((MessageResources) request.getAttribute(Globals.MESSAGES_KEY));

    }



    /**
     * Return the specified message resources for the current module.
     *
     * @param request The servlet request we are processing
     * @param key The key specified in the
     *  <code>&lt;message-resources&gt;</code> element for the
     *  requested bundle
     *
     * @since Struts 1.1
     */
    protected MessageResources getResources(HttpServletRequest request,
                                            String key) {

        // Identify the current module
        ServletContext context = getServlet().getServletContext();
        ModuleConfig moduleConfig = RequestUtils.getModuleConfig(request,context);

        // Return the requested message resources instance
        return ((MessageResources) context.getAttribute
                (key + moduleConfig.getPrefix()));

    }


    /**
     * <p>Returns <code>true</code> if the current form's cancel button was
     * pressed.  This method will check if the <code>Globals.CANCEL_KEY</code>
     * request attribute has been set, which normally occurs if the cancel
     * button generated by <strong>CancelTag</strong> was pressed by the user
     * in the current request.  If <code>true</code>, validation performed
     * by an <strong>ActionForm</strong>'s <code>validate()</code> method
     * will have been skipped by the controller servlet.</p>
     *
     * @param request The servlet request we are processing
     * @see org.apache.struts.taglib.html.CancelTag
     */
    protected boolean isCancelled(HttpServletRequest request) {

        return (request.getAttribute(Globals.CANCEL_KEY) != null);

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

        return token.isTokenValid(request, false);

    }


    /**
     * Return <code>true</code> if there is a transaction token stored in
     * the user's current session, and the value submitted as a request
     * parameter with this action matches it.  Returns <code>false</code>
     * <ul>
     * <li>No session associated with this request</li>
     * <li>No transaction token saved in the session</li>
     * <li>No transaction token included as a request parameter</li>
     * <li>The included transaction token value does not match the
     *     transaction token in the user's session</li>
     * </ul>
     *
     * @param request The servlet request we are processing
     * @param reset Should we reset the token after checking it?
     */
    protected boolean isTokenValid(
        HttpServletRequest request,
        boolean reset) {

        return token.isTokenValid(request, reset);
    }


    /**
     * Reset the saved transaction token in the user's session.  This
     * indicates that transactional token checking will not be needed
     * on the next request that is submitted.
     *
     * @param request The servlet request we are processing
     */
    protected void resetToken(HttpServletRequest request) {
        token.resetToken(request);
    }


    /**
     * Save the specified error messages keys into the appropriate request
     * attribute for use by the &lt;html:errors&gt; tag, if any messages
     * are required.  Otherwise, ensure that the request attribute is not
     * created.
     *
     * @param request The servlet request we are processing
     * @param errors Error messages object
     */
    protected void saveErrors(HttpServletRequest request,
                  ActionErrors errors) {

        // Remove any error messages attribute if none are required
        if ((errors == null) || errors.isEmpty()) {
            request.removeAttribute(Globals.ERROR_KEY);
            return;
        }

        // Save the error messages we need
        request.setAttribute(Globals.ERROR_KEY, errors);

    }


    /**
     * Save the specified messages keys into the appropriate request
     * attribute for use by the &lt;html:messages&gt; tag (if
     * messages="true" is set), if any messages are required.  Otherwise,
     * ensure that the request attribute is not created.
     *
     * @param request   The servlet request we are processing
     * @param messages  Messages object
     * @since Struts 1.1
     */
    protected void saveMessages(HttpServletRequest request,
                    ActionMessages messages) {

        // Remove any messages attribute if none are required
        if ((messages == null) || messages.isEmpty()) {
            request.removeAttribute(Globals.MESSAGE_KEY);
            return;
        }

        // Save the messages we need
        request.setAttribute(Globals.MESSAGE_KEY, messages);

    }


    /**
     * Save a new transaction token in the user's current session, creating
     * a new session if necessary.
     *
     * @param request The servlet request we are processing
     */
    protected void saveToken(HttpServletRequest request) {
        token.saveToken(request);
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
        if (locale == null) {
            locale = defaultLocale;
        }
        session.setAttribute(Globals.LOCALE_KEY, locale);

    }


    /**
     * Convert a byte array to a String of hexadecimal digits and return it.
     *
     * @param buffer The byte array to be converted
     * @deprecated This method will be removed in a release after Struts 1.1.
     */
    protected String toHex(byte buffer[]) {
        return token.toHex(buffer);
    }


}
