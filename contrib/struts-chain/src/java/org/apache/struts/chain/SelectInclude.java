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
import org.apache.struts.config.ActionConfig;


/**
 * <p>Select and cache the include for this
 * <code>ActionConfig</code> if specified.</p>
 *
 * @author Don Brown
 * @version $Revision: 1.3 $ $Date: 2004/04/29 03:08:44 $
 */

public class SelectInclude implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String includeKey = Constants.INCLUDE_KEY;
    
    private static final Log log =
        LogFactory.getLog(SelectInclude.class);

    
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
     * include uri is stored.</p>
     */
    public String getIncludeKey() {

        return (this.includeKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * include uri is stored.</p>
     *
     * @param includeKey The new context attribute key
     */
    public void setIncludeKey(String includeKey) {

        this.includeKey = includeKey;

    }


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

        // Acquire configuration objects that we need
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
            
        // Cache an include uri if found
        String include = actionConfig.getInclude();
        if (include != null) {
            if (log.isDebugEnabled()) {
                log.debug("Including " + include);
            }
            context.put(getIncludeKey(), include);
        }
        return (false);

    }

}
