/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/actions/DispatchAction.java,v 1.18 2003/08/13 05:29:27 rleland Exp $
 * $Revision: 1.18 $
 * $Date: 2003/08/13 05:29:27 $
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

package org.apache.struts.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <p>An abstract <strong>Action</strong> that dispatches to a public
 * method that is named by the request parameter whose name is specified
 * by the <code>parameter</code> property of the corresponding
 * ActionMapping.  This Action is useful for developers who prefer to
 * combine many similar actions into a single Action class, in order to
 * simplify their application design.</p>
 *
 * <p>To configure the use of this action in your
 * <code>struts-config.xml</code> file, create an entry like this:</p>
 *
 * <code>
 *   &lt;action path="/saveSubscription"
 *           type="org.apache.struts.actions.DispatchAction"
 *           name="subscriptionForm"
 *          scope="request"
 *          input="/subscription.jsp"
 *      parameter="method"/&gt;
 * </code>
 *
 * <p>which will use the value of the request parameter named "method"
 * to pick the appropriate "execute" method, which must have the same
 * signature (other than method name) of the standard Action.execute
 * method.  For example, you might have the following three methods in the
 * same action:</p>
 * <ul>
 * <li>public ActionForward delete(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws Exception</li>
 * <li>public ActionForward insert(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws Exception</li>
 * <li>public ActionForward update(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws Exception</li>
 * </ul>
 * <p>and call one of the methods with a URL like this:</p>
 * <code>
 *   http://localhost:8080/myapp/saveSubscription.do?method=update
 * </code>
 *
 * <p><strong>NOTE</strong> - All of the other mapping characteristics of
 * this action must be shared by the various handlers.  This places some
 * constraints over what types of handlers may reasonably be packaged into
 * the same <code>DispatchAction</code> subclass.</p>
 *
 * <p><strong>NOTE</strong> - If the value of the request parameter is empty,
 * a method named <code>unspecified</code> is called. The default action is
 * to throw an exception. If the request was cancelled (a <code>html:cancel</code>
 * button was pressed), the custom handler <code>cancelled</code> will be used instead.
 * You can also override the <code>getMethodName</code> method to override the action's
 * default handler selection.</p>
 *
 * @author Niall Pemberton <niall.pemberton@btInternet.com>
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @author Leonardo Quijano
 * @author Rob Leland
 * @version $Revision: 1.18 $ $Date: 2003/08/13 05:29:27 $
 */
public abstract class DispatchAction extends Action {


    // ----------------------------------------------------- Instance Variables


    /**
     * The Class instance of this <code>DispatchAction</code> class.
     */
    protected Class clazz = this.getClass();


    /**
     * Commons Logging instance.
     */
    protected static Log log = LogFactory.getLog(DispatchAction.class);


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
            MessageResources.getMessageResources
            ("org.apache.struts.actions.LocalStrings");


    /**
     * The set of Method objects we have introspected for this class,
     * keyed by method name.  This collection is populated as different
     * methods are called, so that introspection needs to occur only
     * once per method name.
     */
    protected HashMap methods = new HashMap();


    /**
     * The set of argument type classes for the reflected method call.  These
     * are the same for all calls, so calculate them only once.
     */
    protected Class[] types =
            {
                ActionMapping.class,
                ActionForm.class,
                HttpServletRequest.class,
                HttpServletResponse.class};



    // --------------------------------------------------------- Public Methods


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
     * @exception Exception if an exception occurs
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        if (isCancelled(request)) {
            ActionForward af = cancelled(mapping, form, request, response);
            if (af != null) {
                return af;
            }
        }
        // Identify the request parameter containing the method name
        String parameter = mapping.getParameter();
        if (parameter == null) {
            String message =
                    messages.getMessage("dispatch.handler", mapping.getPath());

            log.error(message);

            throw new ServletException(message);
        }

        // Get the method's name. This could be overridden in subclasses.
        String name = getMethodName(mapping, form, request, response, parameter);

        // Invoke the named method, and return the result
        return dispatchMethod(mapping, form, request, response, name);

    }


    /**
     * Method which is dispatched to when there is no value for specified
     * request parameter included in the request.  Subclasses of
     * <code>DispatchAction</code> should override this method if they wish
     * to provide default behavior different than throwing a ServletException.
     */
    protected ActionForward unspecified(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        String message =
                messages.getMessage(
                        "dispatch.parameter",
                        mapping.getPath(),
                        mapping.getParameter());

        log.error(message);

        throw new ServletException(message);
    }

    /**
     * Method which is dispatched to when the request is a cancel button submit.
     * Subclasses of <code>DispatchAction</code> should override this method if
     * they wish to provide default behavior different than throwing a
     * ServletException.
     * @since Struts 1.2.1
     */
    protected ActionForward cancelled(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {

        return null;
    }

    // ----------------------------------------------------- Protected Methods


    /**
     * Dispatch to the specified method.
     * @since Struts 1.1
     */
    protected ActionForward dispatchMethod(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response,
                                           String name) throws Exception {

        // Make sure we have a valid method name to call.
        // This may be null if the user hacks the query string.
        if (name == null) {
            return this.unspecified(mapping, form, request, response);
        }

        // Identify the method object to be dispatched to
        Method method = null;
        try {
            method = getMethod(name);

        } catch(NoSuchMethodException e) {
            String message =
                    messages.getMessage("dispatch.method", mapping.getPath(), name);
            log.error(message, e);
            throw e;
        }

        ActionForward forward = null;
        try {
            Object args[] = {mapping, form, request, response};
            forward = (ActionForward) method.invoke(this, args);

        } catch(ClassCastException e) {
            String message =
                    messages.getMessage("dispatch.return", mapping.getPath(), name);
            log.error(message, e);
            throw e;

        } catch(IllegalAccessException e) {
            String message =
                    messages.getMessage("dispatch.error", mapping.getPath(), name);
            log.error(message, e);
            throw e;

        } catch(InvocationTargetException e) {
            // Rethrow the target exception if possible so that the
            // exception handling machinery can deal with it
            Throwable t = e.getTargetException();
            if (t instanceof Exception) {
                throw ((Exception) t);
            } else {
                String message =
                        messages.getMessage("dispatch.error", mapping.getPath(), name);
                log.error(message, e);
                throw new ServletException(t);
            }
        }

        // Return the returned ActionForward instance
        return (forward);
    }


    /**
     * Introspect the current class to identify a method of the specified
     * name that accepts the same parameter types as the <code>execute</code>
     * method does.
     *
     * @param name Name of the method to be introspected
     *
     * @exception NoSuchMethodException if no such method can be found
     */
    protected Method getMethod(String name)
            throws NoSuchMethodException {

        synchronized(methods) {
            Method method = (Method) methods.get(name);
            if (method == null) {
                method = clazz.getMethod(name, types);
                methods.put(name, method);
            }
            return (method);
        }

    }

    /**
     * Returns the method name, given a parameter's value.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @param parameter The <code>ActionMapping</code> parameter's name
     *
     * @return The method's name.
     * @since Struts 1.2.1
     */
    protected String getMethodName(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   String parameter)
            throws Exception {

        // Identify the method name to be dispatched to.
        // dispatchMethod() will call unspecified() if name is null
        return request.getParameter(parameter);
    }

}
