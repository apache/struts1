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
 * <p>Perform forwarding or redirection based on the specified
 * <code>String</code> (if any).</p>
 *
 * @author Don Brown
 * @version $Revision: 1.4 $ $Date: 2004/04/29 03:08:44 $
 */

public abstract class AbstractPerformInclude implements Command {


    // ------------------------------------------------------ Instance Variables


    private String includeKey = Constants.INCLUDE_KEY;
    private String moduleConfigKey = Constants.MODULE_CONFIG_KEY;


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context attribute key under which the
     * include uri for the currently selected application
     * action is stored.</p>
     */
    public String getIncludeKey() {

        return (this.includeKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * include uri for the currently selected application
     * action is stored.</p>
     *
     * @param includeKey The new context attribute key
     */
    public void setIncludeKey(String includeKey) {

        this.includeKey = includeKey;

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
     * <p>Perform an include based on the specified
     * include uri (if any).</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>true</code> so that processing completes
     */
    public boolean execute(Context context) throws Exception {

        // Retrieve module config instance
        WebContext wcontext = (WebContext) context;
        ModuleConfig moduleConfig = (ModuleConfig)
            wcontext.get(getModuleConfigKey());
        
        // Is there an include to be performed?
        String include = (String)
            context.get(getIncludeKey());
        if (include == null) {
            return (false);
        }
        
        // Determine the currect uri
        String uri = moduleConfig.getPrefix() + include;

        // Perform the appropriate processing on this include uri
        perform(context, uri);
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
    protected abstract void perform(Context context,
                                    String include)
        throws Exception;


}
