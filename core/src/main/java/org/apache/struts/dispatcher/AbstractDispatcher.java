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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.util.MessageResources;

import java.io.Serializable;
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
public abstract class AbstractDispatcher implements Dispatcher, Serializable {

    // Package message bundle keys
    static final String LOCAL_STRINGS = "org.apache.struts.dispatcher.LocalStrings";
    static final String MSG_KEY_DISPATCH_ERROR = "dispatcher.error";
    static final String MSG_KEY_MISSING_METHOD = "dispatcher.missingMethod";
    static final String MSG_KEY_MISSING_METHOD_LOG = "dispatcher.missingMethod.log";
    static final String MSG_KEY_MISSING_MAPPING_PARAMETER = "dispatcher.missingMappingParameter";
    static final String MSG_KEY_UNSPECIFIED = "dispatcher.unspecified";

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
     * Shared commons Logging instance among subclasses.
     */
    protected final Log log;

    /**
     * The dictionary of {@link Method} objects we have introspected for this
     * class, keyed by method name. This collection is populated as different
     * methods are called, so that introspection needs to occur only once per
     * method name.
     */
    private transient final HashMap methods;

    /**
     * Construct a new dispatcher.
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

	String methodName = resolveMethodName(context);
	if ((methodName == null) || "".equals(methodName)) {
	    methodName = getDefaultMethodName();
	}

	// Ensure there is a specified method name to invoke.
	// This may be null if the user hacks the query string.
	if (methodName == null) {
	    return unspecified(context);
	}

	// Identify the method object to dispatch
	Method method;
	try {
	    method = getMethod(context, methodName);
	} catch (NoSuchMethodException e) {
	    String path = context.getActionConfig().getPath();
	    String message = messages.getMessage(MSG_KEY_MISSING_METHOD_LOG, path, methodName);
	    log.error(message, e);

	    String userMsg = messages.getMessage(MSG_KEY_MISSING_METHOD, path);
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
     * Empties the method cache.
     * 
     * @see #getMethod(ActionContext, String)
     */
    final void flushMethodCache() {
	synchronized (methods) {
	    methods.clear();
	}
    }

    /**
     * Retrieves the name of the method to fallback upon if no method name can
     * be resolved. The default implementation returns
     * {@value #UNSPECIFIED_METHOD_NAME}.
     * 
     * @return the fallback method name; can be <code>null</code>
     * @see #resolveMethodName(ActionContext)
     * @see #UNSPECIFIED_METHOD_NAME
     */
    protected String getDefaultMethodName() {
	return UNSPECIFIED_METHOD_NAME;
    }

    /**
     * Introspects the action to identify a method of the specified name that
     * will be the target of the dispatch. This implementation caches the method
     * instance for subsequent invocations.
     * 
     * @param methodName the name of the method to be introspected
     * @return the method of the specified name
     * @throws NoSuchMethodException if no such method can be found
     * @see #resolveMethod(ActionContext, String)
     * @see #flushMethodCache()
     */
    protected final Method getMethod(ActionContext context, String methodName) throws NoSuchMethodException {
	synchronized (methods) {
	    // Key the method based on the class-method combination
	    StringBuffer keyBuf = new StringBuffer(100);
	    keyBuf.append(context.getAction().getClass().getName());
	    keyBuf.append(":");
	    keyBuf.append(methodName);
	    String key = keyBuf.toString();

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
	    String message = messages.getMessage(MSG_KEY_DISPATCH_ERROR, path);
	    log.error(message + ":" + name, e);
	    throw e;
	} catch (InvocationTargetException e) {
	    // Rethrow the target exception if possible so that the
	    // exception handling machinery can deal with it
	    Throwable t = e.getTargetException();
	    if (t instanceof Exception) {
		throw ((Exception) t);
	    } else {
		String message = messages.getMessage(MSG_KEY_DISPATCH_ERROR, path);
		log.error(message + ":" + name, e);
		throw new ServletException(t);
	    }
	}
    }

    /**
     * Determines whether the current form's cancel button was pressed. The
     * default behavior method will check if the
     * {@link ActionContext#getCancelled()} context property is set , which
     * normally occurs if the cancel button generated by <strong>CancelTag</strong>
     * was pressed by the user in the current request.
     * 
     * @param context the current action context
     * @return <code>true</code> if the request is cancelled; otherwise
     *         <code>false</code>
     * @see org.apache.struts.taglib.html.CancelTag
     */
    protected boolean isCancelled(ActionContext context) {
	Boolean cancelled = context.getCancelled();
	return (cancelled != null) && cancelled.booleanValue();
    }

    /**
     * Decides the appropriate method instance for the specified method name.
     * Implementations may introspect for any desired method signature. This
     * resolution is only invoked if {@link #getMethod(String)} does not find a
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
     * @see #resolveMethod(String, ActionContext)
     */
    protected abstract String resolveMethodName(ActionContext context);

    /**
     * Services the case when the dispatch fails because the method name cannot
     * be resolved. The default behavior throws an {@link IllegalStateException}.
     * Subclasses should override this to provide custom handling such as
     * sending an HTTP 404 error.
     * 
     * @param context the current action context
     * @throws IllegalStateException always unless supressed by subclass
     * @see #resolveMethodName(ActionContext)
     * @see #getDefaultMethodName()
     */
    protected Object unspecified(ActionContext context) throws Exception {
	ActionConfig config = context.getActionConfig();
	String msg = messages.getMessage(MSG_KEY_UNSPECIFIED, config.getPath());
	log.error(msg);
	throw new IllegalStateException(msg);
    }

}
