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
 * <p>Perform forwarding or redirection based on the specified
 * <code>String</code> (if any).</p>
 *
 * @author Don Brown
 * @version $Rev$ $Date$
 */

public abstract class AbstractPerformInclude implements Command {

    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Perform an include based on the specified
     * include uri (if any).</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>true</code> so that processing completes
     */
    public boolean execute(Context context) throws Exception {

        // Retrieve module config instance
        ActionContext actionCtx = (ActionContext) context;
        ModuleConfig moduleConfig = actionCtx.getModuleConfig();
        
        // Is there an include to be performed?
        String include = actionCtx.getInclude();
        if (include == null) {
            return (false);
        }
        
        // Determine the currect uri
        String uri = moduleConfig.getPrefix() + include;

        // Perform the appropriate processing on this include uri
        perform(actionCtx, uri);
        return (true);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Perform the appropriate processing on the specified
     * include uri.</p>
     *
     * @param context The context for this request
     * @param include The forward to be performed
     */
    protected abstract void perform(ActionContext context,
                                    String include)
        throws Exception;


}
