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

package org.apache.struts.chain;


import java.util.HashMap;
import java.util.Map;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.WebContext;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.Constants;
import org.apache.struts.chain.util.ClassUtils;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Create (if necessary) and cache an <code>Action</code> for this request.
 * </p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.6 $ $Date: 2004/07/06 03:21:32 $
 */

public class CreateAction implements Command {


    // ------------------------------------------------------ Instance Variables
    private static final Log log = LogFactory.getLog(CreateAction.class);

    private String actionKey = Constants.ACTION_KEY;
    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String actionServletKey = Constants.ACTION_SERVLET_KEY;
    private String validKey = Constants.VALID_KEY;


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context attribute key under which the
     * <code>Action</code> for the currently selected application
     * action is stored.</p>
     */
    public String getActionKey() {

        return (this.actionKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>Action</code> for the currently selected application
     * action is stored.</p>
     *
     * @param actionKey The new context attribute key
     */
    public void setActionKey(String actionKey) {

        this.actionKey = actionKey;

    }


    /**
     * <p>Return the context attribute key under which the
     * <code>ActionConfig</code> for the currently selected application
     * action is stored.</p>
     */
    public String getActionConfigKey() {

        return (this.actionConfigKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ActionConfig</code> for the currently selected application
     * action is stored.</p>
     *
     * @param actionConfigKey The new context attribute key
     */
    public void setActionConfigKey(String actionConfigKey) {

        this.actionConfigKey = actionConfigKey;

    }


    /**
     * <p>Return the context attribute key under which the
     * <code>ActionServlet</code> for the current web application
     * is stored.</p>
     */
    public String getActionServletKey() {

        return (this.actionServletKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ActionServlet</code> for the current web application
     * is stored.</p>
     *
     * @param actionServletKey The new context attribute key
     */
    public void setActionServletKey(String actionServletKey) {

        this.actionServletKey = actionServletKey;

    }


    /**
     * <p>Return the context attribute key under which the
     * validity flag for this request is stored.</p>
     */
    public String getValidKey() {

        return (this.validKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * validity flag for this request is stored.</p>
     *
     * @param validKey The new context attribute key
     */
    public void setValidKey(String validKey) {

        this.validKey = validKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Create (if necessary) and cache an <code>Action</code> for this
     * request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Skip processing if the current request is not valid
        Boolean valid = (Boolean) context.get(getValidKey());
        if ((valid == null) || !valid.booleanValue()) {
            return (false);
        }
        
        // Check to see if an action has already been created
        if (context.get(getActionKey()) != null) {
            return (false);
        }

        // Look up the class name for the desired Action
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
        String type = actionConfig.getType();

        if (type == null) {
            return (false);
        }

        // Create (if necessary) and cache an Action instance
        Action action = null;
        Map actions = getActions(context, actionConfig.getModuleConfig());
        synchronized (actions) {
            action = (Action) actions.get(type);
            if (action == null) {
                log.info("Initialize action of type: " + type + " for actionConfig " + actionConfig);
                action = (Action) ClassUtils.getApplicationInstance(type);
                ActionServlet actionServlet = (ActionServlet)
                    context.get(getActionServletKey());
                action.setServlet(actionServlet);
                actions.put(type, action);
            }
        }
        context.put(getActionKey(), action);

        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Create (if necessary) and return a <code>Map</code> containing the
     * <code>Action</code> instances for the current application module.</p>
     *
     * @param context The context for this request
     * @param moduleConfig The <code>ModuleConfig</code> for the current
     *  application module
     */
    protected synchronized Map getActions(Context context,
                                          ModuleConfig moduleConfig) {

        WebContext wcontext = (WebContext) context;
        String actionsKey = Constants.ACTIONS_KEY + moduleConfig.getPrefix();
        Map actions = (Map) wcontext.getApplicationScope().get(actionsKey);
        if (actions == null) {
            actions = new HashMap();
            wcontext.getApplicationScope().put(actionKey, actions);
        }
        return (actions);

    }



}
