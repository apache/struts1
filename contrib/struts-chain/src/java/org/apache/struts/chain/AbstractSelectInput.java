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


import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Select and cache a <code>ForwardConfig</code> that returns us to the
 * input page for the current action, if any.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.4 $ $Date: 2004/03/08 02:50:53 $
 */

public abstract class AbstractSelectInput implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String forwardConfigKey = Constants.FORWARD_CONFIG_KEY;
    private String validKey = Constants.VALID_KEY;

    private static final Log log = LogFactory.getLog(AbstractSelectInput.class);


    // -------------------------------------------------------------- Properties


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
     * <p>Select and cache a <code>ForwardConfig</code> for the input page
     * for the current request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Skip processing if the current request is valid
        Boolean valid = (Boolean) context.get(getValidKey());
        if ((valid != null) && valid.booleanValue()) {
            return (false);
        }

        // Acquire configuration objects that we need
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
        ModuleConfig moduleConfig = actionConfig.getModuleConfig();

        // Cache an ForwardConfig back to our input page
        ForwardConfig forwardConfig = null;
        String input = actionConfig.getInput();
        if (moduleConfig.getControllerConfig().getInputForward()) {
            if (log.isTraceEnabled()) {
                log.trace("Finding ForwardConfig for '" + input + "'");
            }
            forwardConfig = actionConfig.findForwardConfig(input);
            if (forwardConfig == null) {
                forwardConfig = moduleConfig.findForwardConfig(input);
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Delegating to forward() for '" + input + "'");
            }
            forwardConfig = forward(context, moduleConfig, input);
        }
        if (log.isDebugEnabled()) {
            log.debug("Forwarding back to " + forwardConfig);
        }
        context.put(getForwardConfigKey(), forwardConfig);
        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Create and return a <code>ForwardConfig</code> representing the
     * specified module-relative destination.</p>
     *
     * @param context The context for this request
     * @param moduleConfig The <code>ModuleConfig</code> for this request
     * @param uri The module-relative URI to be the destination
     */
    protected abstract ForwardConfig forward(Context context,
                                             ModuleConfig moduleConfig,
                                             String uri);


}



