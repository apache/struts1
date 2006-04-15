/*
 * $Id$
 *
 * Copyright 2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts.chain.commands.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.Constants;
import org.apache.struts.chain.commands.util.ClassUtils;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Concrete implementation of <code>AbstractCreateAction</code> for use in
 * a Servlet API chain.  Expects that the ActionContext passed into it can
 * safely be cast to <code>ServletActionContext</code>.</p>
 */
public class CreateAction
    extends org.apache.struts.chain.commands.AbstractCreateAction {
    // ------------------------------------------------------ Instance Variables
    private static final Log log = LogFactory.getLog(CreateAction.class);

    /* :TODO The Action class' dependency on having its "servlet" property set
     * requires this API-dependent subclass of AbstractCreateAction.
     */
    protected synchronized Action getAction(ActionContext context, String type,
        ActionConfig actionConfig)
        throws Exception {
        ModuleConfig moduleConfig = actionConfig.getModuleConfig();
        String actionsKey = Constants.ACTIONS_KEY + moduleConfig.getPrefix();
        Map actions = (Map) context.getApplicationScope().get(actionsKey);

        if (actions == null) {
            actions = new HashMap();
            context.getApplicationScope().put(actionsKey, actions);
        }

        Action action = null;

        synchronized (actions) {
            action = (Action) actions.get(type);

            if (action == null) {
                log.info("Initialize action of type: " + type);
                action = (Action) ClassUtils.getApplicationInstance(type);
                actions.put(type, action);
            }
        }

        if (action.getServlet() == null) {
            ServletActionContext saContext = (ServletActionContext) context;
            ActionServlet actionServlet = saContext.getActionServlet();

            action.setServlet(actionServlet);
        }

        return (action);
    }
}
