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
import org.apache.commons.chain.web.WebContext;
import org.apache.struts.Globals;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.chain.Constants;


/**
 * <p>Cache the <code>ActionConfig</code> instance for the
 * action to be used for processing this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Rev$ $Date$
 */

public abstract class AbstractSelectAction implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String moduleConfigKey = Constants.MODULE_CONFIG_KEY;


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
     * <code>ModuleConfig</code> for the currently selected application
     * module is stored.</p>
     */
    public String getModuleConfigKey() {

        return (this.moduleConfigKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * module is stored.</p>
     *
     * @param moduleConfigKey The new context attribute key
     */
    public void setModuleConfigKey(String moduleConfigKey) {

        this.moduleConfigKey = moduleConfigKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Cache the <code>ActionConfig</code> instance for the
     * action to be used for processing this request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @exception IllegalArgumentException if no valid
     *  action can be identified for this request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Identify the matching path for this request
        String path = getPath(context);

        // Cache the corresponding ActonConfig instance
        WebContext wcontext = (WebContext) context;
        ModuleConfig moduleConfig = (ModuleConfig)
            wcontext.get(getModuleConfigKey());
        ActionConfig actionConfig = moduleConfig.findActionConfig(path);

        if (actionConfig == null) {
            // Locate the mapping for unknown paths (if any)
            ActionConfig configs[] = moduleConfig.findActionConfigs();
            for (int i = 0; i < configs.length; i++) {
                if (configs[i].getUnknown()) {
                    actionConfig = configs[i];
                    break;
                }
            }

        }

        if (actionConfig == null) {
            throw new IllegalArgumentException("No action config for '" +
                                               path + "'");
        }
        wcontext.put(getActionConfigKey(), actionConfig);
        wcontext.getRequestScope().put(Globals.MAPPING_KEY, actionConfig);
        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Return the path to be used to select the <code>ActionConfig</code>
     * for this request.</p>
     *
     * @param context The <code>Context</code> for this request
     *
     * @exception IllegalArgumentException if no valid
     *  action can be identified for this request
     */
    protected abstract String getPath(Context context);


}
