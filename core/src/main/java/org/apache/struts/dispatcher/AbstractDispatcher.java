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
package org.apache.struts.dispatcher;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.util.MessageResources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO
 * 
 * @version $Rev$
 * @since Struts 1.4
 */
public abstract class AbstractDispatcher implements Dispatcher {

    // Package constants
    static final String KEY_DISPATCH_ERROR = "dispatch.error";
    static final String KEY_MISSING_METHOD = "dispatch.method.user";
    static final String KEY_MISSING_HANDLER_PROPERTY = "dispatch.handler";
    static final String KEY_WRONG_RETURN_TYPE = "dispatch.return";
    static final String KEY_MISSING_NAMED_METHOD = "dispatch.method";
    static final String KEY_MISSING_PARAMETER = "dispatch.parameter";
    static final String KEY_RECURSIVE_DISPATCH = "dispatch.recursive";
    static final String LOCAL_STRINGS = "org.apache.struts.actions.LocalStrings";

    /**
     * The name of the <code>cancelled</code> method.
     * 
     * @see #cancelled(ActionContext)
     * @see ActionContext#getCancelled()
     */
    public static final String CANCELLED_METHOD_NAME = "cancelled";

    /**
     * The name of the <code>unspecified</code> method.
     * 
     * @see #unspecified(ActionContext)
     */
    public static final String UNSPECIFIED_METHOD_NAME = "unspecified";

    /**
     * The message resources for this package.
     */
    static MessageResources messages = MessageResources.getMessageResources(LOCAL_STRINGS);

    /**
     * Commons Logging instance.
     */
    protected final Log log;

    /**
     * The dictionary of {@link Method} objects we have introspected for this
     * class, keyed by method name. This collection is populated as different
     * methods are called, so that introspection needs to occur only once per
     * method name.
     */
    private final HashMap methods;

    /**
     * Construct an instance of this class from the supplied parameters.
     * 
     * @param actionInstance the action instance to be invoked
     * @see #ActionDispatcher(Action, int)
     */
    public AbstractDispatcher() {
	log = LogFactory.getLog(getClass());
	methods = new HashMap();
    }

    protected Object cancelled(ActionContext context) throws Exception {
	Method method = getMethod(context, CANCELLED_METHOD_NAME);
	return dispatchMethod(context, method, CANCELLED_METHOD_NAME);
    }

    public Object dispatch(ActionContext context) throws Exception {
	// Process cancelled requests
	if (isCancelled(context)) {
	    try {
		return cancelled(context);
	    } catch (NoSuchMethodException e) {
		// expected and ignore
	    }
	}

	// Ensure there is a valid method name to call.
	// This may be null if the user hacks the query string.
	String methodName = resolveMethodName(context);
	if (methodName == null) {
	    return unspecified(context);
	}

	// Identify the method object to be dispatched to
	Method method;
	try {
	    method = getMethod(context, methodName);
	} catch (NoSuchMethodException e) {
	    String path = context.getActionConfig().getPath();
	    String message = messages.getMessage(KEY_MISSING_NAMED_METHOD, path, methodName);
	    log.error(message, e);

	    String userMsg = messages.getMessage(KEY_MISSING_METHOD, path);
	    NoSuchMethodException e2 = new NoSuchMethodException(userMsg);
	    e2.initCause(e);
	    throw e2;
	}

	// Invoke the named method and return its result
	return dispatchMethod(context, method, methodName);
    }

    /**
     * Dispatch to the specified method.
     * 
     * @param method The method to invoke
     * @param name The name of the method to invoke
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * 
     * @return The forward to which control should be transferred, or
     *         <code>null</code> if the response has been completed.
     * @throws Exception if the application business logic throws an exception.
     * @see #dispatchMethod(ActionMapping, ActionForm, HttpServletRequest,
     *      HttpServletResponse, String)
     */
    protected abstract Object dispatchMethod(ActionContext context, Method method, String name) throws Exception;

    /**
     * Retrieves the name of the method to fallback upon if no value can be
     * obtained from the parameter. The default implementation returns
     * {@value #UNSPECIFIED_METHOD_NAME}.
     * 
     * @return the fallback method name; can be <code>null</code>
     * @see #UNSPECIFIED_METHOD_NAME
     */
    protected String getFallbackMethodName() {
	return UNSPECIFIED_METHOD_NAME;
    }

    /**
     * Introspect the action to identify a method of the specified name that
     * will receive the invocation of this dispatch. This implementation caches
     * the method instance for subsequent invocations.
     * 
     * @param methodName the name of the method to be introspected
     * @return the method of the specified name
     * @throws NoSuchMethodException if no such method can be found
     * @see #resolveMethod(ActionContext, String)
     */
    protected final Method getMethod(ActionContext context, String methodName) throws NoSuchMethodException {
	synchronized (methods) {
	    String key = context.getAction().getClass().getName() + ":" + methodName;
	    Method method = (Method) methods.get(key);

	    if (method == null) {
		method = resolveMethod(methodName, context);
		methods.put(key, method);
	    }

	    return method;
	}
    }

    protected final Object invoke(Object target, Method method, Object[] args, String path, String name)
	    throws Exception {
	try {
	    return method.invoke(target, args);
	} catch (IllegalAccessException e) {
	    String message = messages.getMessage(KEY_DISPATCH_ERROR, path, name);
	    log.error(message, e);
	    throw e;
	} catch (InvocationTargetException e) {
	    // Rethrow the target exception if possible so that the
	    // exception handling machinery can deal with it
	    Throwable t = e.getTargetException();
	    if (t instanceof Exception) {
		throw ((Exception) t);
	    } else {
		String message = messages.getMessage(KEY_DISPATCH_ERROR, path, name);
		log.error(message, e);
		throw new ServletException(t);
	    }
	}
    }

    /**
     * Determines whether the current form's cancel button was pressed. This
     * method will check if the {@link Globals#CANCEL_KEY} request attribute has
     * been set, which normally occurs if the cancel button generated by
     * <strong>CancelTag</strong> was pressed by the user in the current
     * request. If <code>true</code>, validation performed by the
     * <code>validate()</code> method of the form; otherwise it will have been
     * skipped by the controller servlet.
     * 
     * @param context the current action context
     * @return <code>true</code> if the cancel button was pressed;
     *         <code>false</code> otherwise
     * @see org.apache.struts.taglib.html.CancelTag
     */
    protected boolean isCancelled(ActionContext context) {
	Boolean cancelled = context.getCancelled();
	return (cancelled != null) && cancelled.booleanValue();
    }

    /**
     * Decides the appropriate method instance for the specified method name.
     * Implementations may introspect for any desired method signature. The
     * resolution is only needed if {@link #getMethod(String)} does not find a
     * match in its method cache.
     * 
     * @param methodName the method name to use for introspection
     * @param context the current action context
     * @return the method to invoke
     * @throws NoSuchMethodException if an appropriate method cannot be found
     * @see #getMethod(String)
     * @see #invoke(Object, Method, Object[], String, String)
     */
    protected abstract Method resolveMethod(String methodName, ActionContext context) throws NoSuchMethodException;

    /**
     * Decides the method name that can handle the request.
     * 
     * @param context the current action context
     * @return the method name or <code>null</code> if no name can be resolved
     */
    protected abstract String resolveMethodName(ActionContext context);

    /**
     * Invoked when a method cannot be resolved. The default behavior delegates
     * to the fallback method, if specified; otherwise throw an
     * {@link IllegalStateException}. Subclasses should override this to
     * provide custom handling such as sending an HTTP 404 error.
     * 
     * @param context the current action context
     * @throws NoSuchMethodException if the fallback method name is specified
     *         but the corresponding method does not exist
     * @throws IllegalStateException always unless supressed by subclass
     * @see #getFallbackMethodName()
     */
    protected Object unspecified(ActionContext context) throws Exception {
	// Is the fallback method present?
	Method method = null;
	String name = getFallbackMethodName();
	if (name != null) {
	    try {
		method = getMethod(context, name);
	    } catch (NoSuchMethodException e) {
		String msg = messages.getMessage(KEY_MISSING_METHOD, name);
		NoSuchMethodException e2 = new NoSuchMethodException(msg);
		e2.initCause(e);
		throw e2;
	    }
	}

	// Dispatch if fallback is available
	if (method != null) {
	    return dispatchMethod(context, method, name);
	}

	// Otherwise the dispatch has failed
	ActionConfig config = context.getActionConfig();
	String msg = messages.getMessage(KEY_MISSING_PARAMETER, config.getPath(), config.getParameter());
	log.error(msg);
	throw new IllegalStateException(msg);
    }

}
