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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;


/**
 * <p>Select and cache the include for this
 * <code>ActionConfig</code> if specified.</p>
 *
 * @author Don Brown
 * @version $Rev$ $Date$
 */

public class SelectInclude implements Command {


    // ------------------------------------------------------ Instance Variables
    private static final Log log =
        LogFactory.getLog(SelectInclude.class);

    

    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Select and cache the include uri for this
     * <code>ActionConfig</code> if specified.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        ActionContext actionCtx = (ActionContext) context;

        // Acquire configuration objects that we need
        ActionConfig actionConfig = actionCtx.getActionConfig();

        // Cache an include uri if found
        String include = actionConfig.getInclude();
        if (include != null) {
            if (log.isDebugEnabled()) {
                log.debug("Including " + include);
            }
            actionCtx.setInclude(include);
        }
        return (false);

    }

}
