/*
 * Copyright 2003,2004 The Apache Software Foundation.
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

package org.apache.struts.chain.commands;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;


/**
 * <p>Create (if necessary) and cache an <code>Action</code> for this request.
 * </p>
 *
 * @author Craig R. McClanahan
 * @version $Rev$ $Date$
 */

public abstract class AbstractCreateAction extends ActionCommandBase {


    // ------------------------------------------------------ Instance Variables
    private static final Log log = LogFactory.getLog(AbstractCreateAction.class);

    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Create (if necessary) and cache an <code>Action</code> for this
     * request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(ActionContext actionCtx) throws Exception {

        // Skip processing if the current request is not valid
        Boolean valid = actionCtx.getFormValid();
        if ((valid == null) || !valid.booleanValue()) {
            log.trace("Invalid form; not going to execute.");
            return (false);
        }

        // Check to see if an action has already been created
        if (actionCtx.getAction() != null) {
            log.trace("already have an action [" + actionCtx.getAction() + "]");
            return (false);
        }

        // Look up the class name for the desired Action
        ActionConfig actionConfig = actionCtx.getActionConfig();
        String type = actionConfig.getType();

        if (type == null) {
            log.trace("no type for " + actionConfig.getPath());
            return (false);
        }

        // Create (if necessary) and cache an Action instance
        Action action = getAction(actionCtx, type, actionConfig);
        log.trace("setting action to " + action);
        actionCtx.setAction(action);

        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * Create and return the appropriate <code>Action</code> class for the given <code>type</code>
     * and <code>actionConfig</code>.
     * @param context
     * @param type
     * @param actionConfig
     * @return
     * @throws Exception if there are any problems instantiating the Action class.
     * @todo The dependence on ActionServlet suggests that this should be broken up
     * along the lines of the other Abstract/concrete pairs in the org.apache.struts.chain.commands package.
     */
    protected abstract Action getAction(ActionContext context, String type, ActionConfig actionConfig) throws Exception;


}
