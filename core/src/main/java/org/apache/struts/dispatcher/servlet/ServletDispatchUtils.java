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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dispatch utilities for servlet implementations.
 * 
 * @version $Rev$
 * @since Struts 1.4
 */
final public class ServletDispatchUtils {

    /**
     * The set of argument type classes for the classic reflected method call.
     * These are the same for all calls, so calculate them only once.
     * 
     * @see org.apache.struts.action.Action#execute(ActionMapping, ActionForm,
     *      javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    public static final Class[] CLASSIC_EXECUTE_SIGNATURE = { ActionMapping.class, ActionForm.class,
	    HttpServletRequest.class, HttpServletResponse.class };

    /**
     * Constructs the arguments to invoke the classic <code>execute</code>
     * servlet signature from the specified context.
     * 
     * @param context the current context
     * @return the arguments array
     * @see #resolveClassicExecuteMethod(ActionContext, String)
     * @see #CLASSIC_EXECUTE_SIGNATURE
     */
    public static Object[] buildClassicExecuteArguments(ServletActionContext context) {
	ActionConfig mapping = context.getActionConfig();
	ActionForm form = context.getActionForm();
	HttpServletRequest request = context.getRequest();
	HttpServletResponse response = context.getResponse();
	return new Object[] { mapping, form, request, response };
    }

    /**
     * Obtains the method instance with the classic <code>execute</code>
     * servlet signature.
     * 
     * @param context the current context
     * @param name the method name to introspect
     * @return the found method in the action
     * @throws NoSuchMethodException if the method does not exist
     * @see #buildClassicExecuteArguments(ServletActionContext)
     * @see #CLASSIC_EXECUTE_SIGNATURE
     */
    public static Method resolveClassicExecuteMethod(ActionContext context, String name) throws NoSuchMethodException {
	Class actionClass = context.getAction().getClass();
	return actionClass.getMethod(name, CLASSIC_EXECUTE_SIGNATURE);
    }

    /**
     * Prevent instantiation.
     */
    private ServletDispatchUtils() {
	throw new UnsupportedOperationException();
    }

}
