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
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.util.MessageResources;


/**
 * <p>Determine whether the requested action is authorized for the current
 * user.  If not, abort chain processing and perferably, return an error
 * message of some kind.</p>
 *
 * @author Don Brown
 * @version $Revision: 1.4 $ $Date: 2004/04/29 03:08:44 $
 */

public abstract class AbstractAuthorizeAction implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String actionServletKey = Constants.ACTION_SERVLET_KEY;
    
    private static final Log log =
        LogFactory.getLog(AbstractAuthorizeAction.class);


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
     * <code>ActionServlet</code> for the currently selected application
     * action is stored.</p>
     */
    public String getActionServletKey() {

        return (this.actionServletKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ActionServlet</code> for the currently selected application
     * action is stored.</p>
     *
     * @param actionServletKey The new context attribute key
     */
    public void setActionServletKey(String actionServletKey) {

        this.actionServletKey = actionServletKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Determine whether the requested action is authorized for the current
     * user.  If not, abort chain processing and perferably, return an error
     * message of some kind.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> if the user is authorized for the selected
     * action, else <code>true</code> to abort processing.
     */
    public boolean execute(Context context) throws Exception {

        // Retrieve ActionConfig
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
            
        // Is this action protected by role requirements?
        String roles[] = actionConfig.getRoleNames();
        if ((roles == null) || (roles.length < 1)) {
            return (false);
        }
        
        boolean throwEx = false;
        try {
            throwEx = !(isAuthorized(context, roles, actionConfig));
        }
        catch (Exception ex) {
            throwEx = true;
            log.error("Unable to complete authorization process", ex);
        }
        
        if (throwEx) {
            // Retrieve internal message resources
            ActionServlet servlet = 
                (ActionServlet) context.get(actionServletKey);
            MessageResources resources = servlet.getInternal();
            
            // The current user is not authorized for this action
            throw new UnauthorizedActionException(
                resources.getMessage("notAuthorized",
                actionConfig.getPath()));
        } else {
            return (false);
        }
        
    }
    
    
    // ------------------------------------------------------- Protected Methods
    
    
    /**
     * <p>Determine if the action is authorized for the given roles.</p>
     *
     * @param context        The <code>Context</code> for the current request
     * @param roles          An array of valid roles for this request
     * @param actionConfig   The current action mapping
     *
     * @return <code>true</code> if the request is authorized, else 
     * <code>false</code>
     * @exception Exception If the action cannot be tested for authorization
     */
    protected abstract boolean isAuthorized(Context context, String[] roles,    
                                            ActionConfig actionConfig)
              throws Exception;

}
