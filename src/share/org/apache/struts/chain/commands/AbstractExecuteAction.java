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


import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.struts.chain.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;


/**
 * <p>Invoke the appropriate <code>Action</code> for this request, and cache
 * the returned <code>ActionForward</code>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Rev$ $Date$
 */

public abstract class AbstractExecuteAction implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionKey = Constants.ACTION_KEY;
    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String actionFormKey = Constants.ACTION_FORM_KEY;
    private String forwardConfigKey = Constants.FORWARD_CONFIG_KEY;
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
     * <code>ActionForm</code> for the currently selected application
     * action is stored.</p>
     */
    public String getActionFormKey() {

        return (this.actionFormKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ActionForm</code> for the currently selected application
     * action is stored.</p>
     *
     * @param actionFormKey The new context attribute key
     */
    public void setActionFormKey(String actionFormKey) {

        this.actionFormKey = actionFormKey;

    }


    /**
     * <p>Return the context attribute key under which the
     * <code>ForwardConfig</code> for the currently selected application
     * action is stored.</p>
     */
    public String getForwardConfigKey() {

        return (this.forwardConfigKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ForwardConfig</code> for the currently selected application
     * action is stored.</p>
     *
     * @param forwardConfigKey The new context attribute key
     */
    public void setForwardConfigKey(String forwardConfigKey) {

        this.forwardConfigKey = forwardConfigKey;

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
     * <p>Invoke the appropriate <code>Action</code> for this request, and cache
     * the returned <code>ActionForward</code>.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @exception InvalidPathException if no valid
     *  action can be identified for this request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Skip processing if the current request is not valid
        Boolean valid = (Boolean) context.get(getValidKey());
        if ((valid == null) || !valid.booleanValue()) {
            return (false);
        }

        // Acquire the resources we will need to send to the Action
        Action action = (Action)
            context.get(getActionKey());
        if (action == null) {
            return (false);
        }
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
        ActionForm actionForm = (ActionForm)
            context.get(getActionFormKey());

        // Execute the Action for this request, caching returned ActionForward
        ForwardConfig forwardConfig =
            execute(context, action, actionConfig, actionForm);
        context.put(getForwardConfigKey(), forwardConfig);

        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Execute the specified <code>Action</code>, and return the resulting
     * <code>ForwardConfig</code>.</p>
     *
     * @param context The <code>Context</code> for this request
     * @param action The <code>Action</code> to be executed
     * @param actionConfig The <code>ActionConfig</code> defining this action
     * @param actionForm The <code>ActionForm</code> (if any) for
     *  this action
     *
     * @exception Exception if thrown by the <code>Action</code>
     */
    protected abstract ForwardConfig execute(Context context,
                                             Action action,
                                             ActionConfig actionConfig,
                                             ActionForm actionForm)
        throws Exception;


}
