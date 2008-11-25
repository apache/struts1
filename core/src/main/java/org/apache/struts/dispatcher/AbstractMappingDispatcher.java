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

import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.contexts.ActionContext;

/**
 * <p>
 * This abstract class is a template for choosing the target method based on the
 * <code>parameter</code> attribute of the {@link ActionMapping}.
 * </p>
 * 
 * @version $Rev$
 * @since Struts 1.4
 */
public abstract class AbstractMappingDispatcher extends AbstractDispatcher {

    protected String resolveMethodName(ActionContext context) {
	// Null out an empty string parameter
	ActionMapping mapping = (ActionMapping) context.getActionConfig();
	String parameter = mapping.getParameter();
	if ("".equals(parameter)) {
	    parameter = null;
	}

	if ((parameter == null)) {
	    String message = messages.getMessage(KEY_MISSING_HANDLER_PROPERTY, mapping.getPath());
	    log.error(message);
	    throw new IllegalStateException(message);
	}

	return parameter;
    }

}
