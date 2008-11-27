/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts.dispatcher.servlet;

import org.apache.struts.action.Action;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.dispatcher.AbstractParameterDispatcher;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

/**
 * This servlet-based dispatcher uses the value of the request parameter to pick
 * the appropriate method on the action.
 * <p>
 * To configure the use of this dispatcher in your configuration, create an
 * entry like below:
 * <p>
 * <code>
 * <pre>
 * &lt;action path=&quot;/saveSubscription&quot; 
 *         type=&quot;org.example.SubscriptionAction&quot;
 *         dispatcher=&quot;org.apache.struts.dispatcher.servlet.ServletParameterDispatcher&quot;
 *         parameter=&quot;method&quot;/&gt;
 *         name=&quot;subscriptionForm&quot; 
 *         scope=&quot;request&quot; 
 *         input=&quot;/subscription.jsp&quot;
 * </pre>
 * </code>
 * <p>
 * This example will use the value of the request parameter named "method" to
 * pick the appropriate method, which must have the same signature (other than
 * method name) of the standard
 * {@link Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
 * javax.servlet.ServletRequest, javax.servlet.ServletResponse) Action.execute}
 * method. For example, you might have the following three methods in the same
 * action:
 * 
 * <ul>
 * <li><code>public ActionForward delete(ActionMapping mapping, ActionForm form,
 * HttpServletRequest request, HttpServletResponse response) throws Exception</code></li>
 * <li><code>public ActionForward insert(ActionMapping mapping, ActionForm form,
 * HttpServletRequest request, HttpServletResponse response) throws Exception</code></li>
 * <li><code>public ActionForward update(ActionMapping mapping, ActionForm form,
 * HttpServletRequest request, HttpServletResponse response) throws Exception</code></li>
 * </ul>
 * and call one of the methods with a URL like this:
 * <p>
 * <code>http://localhost:8080/myapp/saveSubscription.do?method=update</code>
 * 
 * @version $Rev$
 * @since Struts 1.4
 */
public class ServletParameterDispatcher extends AbstractParameterDispatcher {

    /**
     * Constructs the arguments that will be passed to the dispatched method.
     * 
     * @param context the current action context
     * @param method the target method of this dispatch
     * 
     * @return the arguments array
     * @see #dispatchMethod(ActionContext, Method, String)
     */
    protected Object[] buildMethodArguments(ServletActionContext context, Method method) {
	return ServletDispatchUtils.buildClassicExecuteArguments(context);
    }

    /**
     * @see #buildMethodArguments(ServletActionContext, Method)
     */
    protected final Object dispatchMethod(ActionContext context, Method method, String name) throws Exception {
	Action target = context.getAction();
	String path = context.getActionConfig().getPath();
	Object[] args = buildMethodArguments((ServletActionContext) context, method);
	return invoke(target, method, args, path);
    }

    protected Method resolveMethod(ActionContext context, String methodName) throws NoSuchMethodException {
	return ServletDispatchUtils.resolveClassicExecuteMethod(context, methodName);
    }

    /**
     * Extracts the value from the specified servlet parameter.
     * 
     * @param context {@inheritDoc}
     * @param parameter the servlet parameter name
     * @return the servlet parameter value
     */
    protected String resolveParameterValue(ActionContext context, String parameter) {
	ServletActionContext servletContext = (ServletActionContext) context;
	return (String) servletContext.getParam().get(parameter);
    }

    /**
     * Sends the 404 HTTP error response.
     * 
     * @param context {@inheritDoc}
     * @return always <code>null</code> since the response is handled directly
     * @throws Exception if the error code fails to set
     */
    protected Object unspecified(ActionContext context) throws Exception {
	HttpServletResponse response = ((ServletActionContext) context).getResponse();
	response.sendError(HttpServletResponse.SC_NOT_FOUND);
	return null;
    }

}
