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
import org.apache.commons.chain.web.WebContext;
import org.apache.struts.Globals;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;


/**
 * <p>Cache the <code>ModuleConfig</code> and <code>MessageResources</code>
 * instances for the sub-application module to be used for processing
 * this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Rev$ $Date$
 */

public abstract class AbstractSelectModule implements Command {


    // ------------------------------------------------------ Instance Variables


    private String messageResourcesKey = Constants.MESSAGE_RESOURCES_KEY;
    private String moduleConfigKey = Constants.MODULE_CONFIG_KEY;


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context attribute key under which the default
     * <code>MessageResources</code> for the currently selected application
     * module will be stored.</p>
     */
    public String getMessageResourcesKey() {

        return (this.messageResourcesKey);

    }


    /**
     * <p>Set the context attribute key under which the default
     * <code>MessageResources</code> for the currently selected application
     * module will be stored.</p>
     *
     * @param messageResourcesKey The new context attribute key
     */
    public void setMessageResourcesKey(String messageResourcesKey) {

        this.messageResourcesKey = messageResourcesKey;

    }


    /**
     * <p>Return the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * module will be stored.</p>
     */
    public String getModuleConfigKey() {

        return (this.moduleConfigKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * module will be stored.</p>
     *
     * @param moduleConfigKey The new context attribute key
     */
    public void setModuleConfigKey(String moduleConfigKey) {

        this.moduleConfigKey = moduleConfigKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Cache the <code>ModuleConfig</code> and <code>MessageResources</code>
     * instances for the sub-application module to be used for processing
     * this request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @exception IllegalArgumentException if no valid
     *  ModuleConfig or MessageResources can be identified for this request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Identify the module prefix for the current module
        String prefix = getPrefix(context);

        // Cache the corresponding ModuleConfig and MessageResources instances
        WebContext wcontext = (WebContext) context;
        ModuleConfig moduleConfig = (ModuleConfig)
            wcontext.getApplicationScope().get(Globals.MODULE_KEY + prefix);
        if (moduleConfig == null) {
            throw new IllegalArgumentException("No module config for prefix '" +
                                               prefix + "'");
        }
        wcontext.put(getModuleConfigKey(), moduleConfig);
        wcontext.getRequestScope().put(Globals.MODULE_KEY, moduleConfig);
        MessageResources messageResources = (MessageResources)
            wcontext.getApplicationScope().get(Globals.MESSAGES_KEY + prefix);
        if (messageResources != null) {
            wcontext.put(getMessageResourcesKey(),
                                         messageResources);
            wcontext.getRequestScope().put(Globals.MESSAGES_KEY,
                                           messageResources);
        }

        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Calculate and return the module prefix for the module to be
     * selected for this request.</p>
     *
     * @param context The <code>Context</code> for this request
     *
     * @exception IllegalArgumentException if no valid
     *  ModuleConfig or MessageResources can be identified for this request
     */
    protected abstract String getPrefix(Context context);


}
