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
import org.apache.struts.dispatcher.AbstractMappingDispatcher;

import java.lang.reflect.Method;

public class ServletMappingDispatcher extends AbstractMappingDispatcher {

    protected Object dispatchMethod(ActionContext context, Method method, String name) throws Exception {
	Action target = context.getAction();
	Object[] args = ServletDispatchUtils.buildClassicExecuteArguments((ServletActionContext) context);
	String path = context.getActionConfig().getPath();
	return invoke(target, method, args, path, name);
    }

    protected Method resolveMethod(String methodName, ActionContext context) throws NoSuchMethodException {
	return ServletDispatchUtils.resolveClassicExecuteMethod(context, methodName);
    }

}
