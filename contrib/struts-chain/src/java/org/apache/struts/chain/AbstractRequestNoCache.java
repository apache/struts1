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
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Check to see if the controller is configured to prevent caching,
 * and if so, request no cache flags to be set.</p>
 *
 * @author Don Brown
 * @version $Revision: 1.3 $ $Date: 2004/04/29 03:08:44 $
 */

public abstract class AbstractRequestNoCache implements Command {


    // ------------------------------------------------------ Instance Variables


    private String moduleConfigKey = Constants.MODULE_CONFIG_KEY;


    // -------------------------------------------------------------- Properties


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
     * <p>Check to see if the controller is configured to prevent caching,
     * and if so, request no cache flags to be set.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Retrieve the ModuleConfig instance
        WebContext wcontext = (WebContext) context;
        ModuleConfig moduleConfig = (ModuleConfig)
            wcontext.get(getModuleConfigKey());
            
        // If the module is configured for no caching, request no caching    
        if (moduleConfig.getControllerConfig().getNocache()) {
            requestNoCache(context);
        }
        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Request no cache flags are set.</p>
     *
     * @param context The <code>Context</code> for this request
     */
    protected abstract void requestNoCache(Context context);


}
