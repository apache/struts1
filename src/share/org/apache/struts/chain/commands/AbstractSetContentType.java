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
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Check to see if the content type is set, and if so, set it for this
 * response.</p>
 *
 * @author Don Brown
 * @version $Rev$ $Date$
 */

public abstract class AbstractSetContentType implements Command {


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Check to see if the content type is set, and if so, set it for this
     * response.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {
        ActionContext actionCtx = (ActionContext) context;
        // Retrieve the ModuleConfig instance
        ModuleConfig moduleConfig = actionCtx.getModuleConfig();
            
        // If the content type is configured, set it for the response
        String contentType = moduleConfig.getControllerConfig().getContentType();
        if (contentType != null) {
            setContentType(actionCtx, contentType);
        }
        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Request no cache flags are set.</p>
     *
     * @param context The <code>Context</code> for this request
     * @param contentType The content type for the response
     */
    protected abstract void setContentType(ActionContext context, String contentType);


}
