/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/actions/MappingDispatchAction.java,v 1.8 2004/01/10 21:03:36 dgraham Exp $
 * $Revision: 1.8 $
 * $Date: 2004/01/10 21:03:36 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
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

package org.apache.struts.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>An abstract <strong>Action</strong> that dispatches to a public
 * method that is named by the <code>parameter</code> attribute of 
 * the corresponding ActionMapping.  This is useful for developers who prefer
 * to combine many related actions into a single Action class.</p>
 *
 * <p>To configure the use of this action in your
 * <code>struts-config.xml</code> file, create an entry like this:</p>
 *
 * <pre><code>
 *   &lt;action path="/saveSubscription"
 *           type="org.example.SubscriptionAction"
 *           name="subscriptionForm"
 *          scope="request"
 *          input="/subscription.jsp"
 *      parameter="method"/&gt;
 * </code></pre>
 *
 * <p>where 'method' is the name of a method in your subclass of
 * MappingDispatchAction that has the same signature (other than method
 * name) of the standard Action.execute method.  For example, you might combine
 * the methods for managing a subscription into a  single 
 * MappingDispatchAction class using the following methods:</p> 
 * <ul>
 * <li>public ActionForward create(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws Exception</li>
 * <li>public ActionForward edit(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws Exception</li>
 * <li>public ActionForward save(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws Exception</li>
 * <li>public ActionForward delete(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws Exception</li>
 * <li>public ActionForward list(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws Exception</li>
 * </ul>
 * <p>for which you would create corresponding &lt;action&gt; configurations 
 * that reference this class:</p>
 *
 * <pre><code>
 *  &lt;action path="/createSubscription" 
 *          type="org.example.SubscriptionAction"
 *          parameter="create"&gt;
 *      &lt;forward name="success" path="/editSubscription.jsp"/&gt;
 *  &lt;/action&gt;
 * 
 *  &lt;action path="/editSubscription" 
 *          type="org.example.SubscriptionAction"
 *          parameter="edit"&gt;
 *      &lt;forward name="success" path="/editSubscription.jsp"/&gt;
 *  &lt;/action&gt;
 *
 *  &lt;action path="/saveSubscription" 
 *          type="org.example.SubscriptionAction" 
 *          parameter="save"
 *          name="subscriptionForm" 
 *          validate="true" 
 *          input="/editSubscription.jsp" 
 *          scope="request"&gt;
 *      &lt;forward name="success" path="/savedSubscription.jsp"/&gt;
 *  &lt;/action&gt;
 *
 *  &lt;action path="/deleteSubscription" 
 *          type="org.example.SubscriptionAction"
 *          name="subscriptionForm"
 *          scope="request"
 *          input="/subscription.jsp"
 *          parameter="delete"&gt;
 *      &lt;forward name="success" path="/deletedSubscription.jsp"/&gt;
 *  &lt;/action&gt;
 *
 *  &lt;action path="/listSubscriptions" 
 *          type="org.example.SubscriptionAction"
 *          parameter="list"&gt;
 *      &lt;forward name="success" path="/subscriptionList.jsp"/&gt;
 *  &lt;/action&gt;
 * </code></pre>
 *
 * <p><strong>NOTE</strong> - Unlike DispatchAction, mapping characteristics 
 * may differ between the various handlers, so you can combine actions in the 
 * same class that, for example, differ in their use of forms or validation. 
 * Also, a request parameter, which would be visible to the application user,
 * is not required to enable selection of the handler method. 
 * </p>
 *
 * @version $Revision: 1.8 $ $Date: 2004/01/10 21:03:36 $
 * @since Struts 1.2
 */
public class MappingDispatchAction extends DispatchAction {


	// -------------------------------------------------------- Class Variables


	/**
	 * Commons Logging instance.
	 */
	private static Log log =
		LogFactory.getLog(MappingDispatchAction.class);


	// --------------------------------------------------------- Public Methods


	/**
	 * Process the specified HTTP request, and create the corresponding HTTP
	 * response (or forward to another web component that will create it).
	 * Return an <code>ActionForward</code> instance describing where and how
	 * control should be forwarded, or <code>null</code> if the response has
	 * already been completed.
	 *
	 * This method dispatches the request to other methods of 
	 * <code>MappingDispatchAction</code> using the 'parameter' attribute of
	 * <code>ActionMapping</code> and Java Introspection.
	 *
	 * @param mapping The ActionMapping used to select this instance
	 * @param form The optional ActionForm bean for this request (if any)
	 * @param request The HTTP request we are processing
	 * @param response The HTTP response we are creating
	 *
	 * @return  Return an <code>ActionForward</code> instance describing where
	 *           and how control should be forwarded, or <code>null</code> if
	 *           the response has already been completed.
	 * 
	 * @exception Exception if an exception occurs
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
        
        // Use the overridden getMethodName. 
        return super.execute(mapping, form, request, response);
	}


	/**
	 * Method which is dispatched to when there is no value for the
	 * parameter in the ActionMapping.  Subclasses of
	 * <code>MappingDispatchAction</code> should override this method
	 * if they wish to provide default behavior different than throwing a
	 * ServletException.
	 * 
	 * @param mapping The ActionMapping used to select this instance
	 * @param form The optional ActionForm bean for this request (if any)
	 * @param request The HTTP request we are processing
	 * @param response The HTTP response we are creating
	 *
	 * @return  Return an <code>ActionForward</code> instance describing where
	 *           and how control should be forwarded, or <code>null</code> if
	 *           the response has already been completed.
	 * 
	 * @exception Exception if an exception occurs
	 */
	protected ActionForward unspecified(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		String message =
			messages.getMessage("mapping.parameter", mapping.getPath());

		log.error(message);

		throw new ServletException(message);
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
     * @since Struts 1.2.0
     */
    protected String getMethodName(
        ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request,
        HttpServletResponse response,
        String parameter)
        throws Exception {
        
        // Return the unresolved mapping parameter.
        return parameter;
    }

}
