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
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Invoke the local or global exception handler configured for the
 * exception class that occurred.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.4 $ $Date: 2004/03/08 02:50:53 $
 */

public abstract class AbstractExceptionHandler implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String exceptionKey = Constants.EXCEPTION_KEY;
    private String forwardConfigKey = Constants.FORWARD_CONFIG_KEY;
    private String moduleConfigKey = Constants.MODULE_CONFIG_KEY;

    private static final Log log =
        LogFactory.getLog(AbstractExceptionHandler.class);


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
     * <p>Return the context attribute key under which any
     * thrown exception will be stored.</p>
     */
    public String getExceptionKey() {

        return (this.exceptionKey);

    }


    /**
     * <p>Set the context attribute key under which any
     * thrown exception will be stored.</p>
     *
     * @param exceptionKey The new context attribute key
     */
    public void setExceptionKey(String exceptionKey) {

        this.exceptionKey = exceptionKey;

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
     * <code>ModuleConfig</code> for the currently selected application
     * action is stored.</p>
     */
    public String getModuleConfigKey() {

        return (this.moduleConfigKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * action is stored.</p>
     *
     * @param moduleConfigKey The new context attribute key
     */
    public void setModuleConfigKey(String moduleConfigKey) {

        this.moduleConfigKey = moduleConfigKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Invoke the appropriate <code>Action</code> for this request, and cache
     * the returned <code>ActionForward</code>.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @exception InvalidPathException if no valid
     *  action can be identified for this request
     *
     * @return <code>false</code> if a <code>ForwardConfig</code> is returned,
     *  else <code>true</code> to complete processing
     */
    public boolean execute(Context context) throws Exception {

        // Look up the exception that was thrown
        Exception exception = (Exception)
            context.get(getExceptionKey());
        if (exception == null) {
            log.warn("No Exception found under key '" +
                     getExceptionKey() + "'");
            return (true);
        }

        // Look up the local or global exception handler configuration
        ExceptionConfig exceptionConfig = null;
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
        ModuleConfig moduleConfig = (ModuleConfig)
            context.get(getModuleConfigKey());


        if (actionConfig != null) {
            log.debug("See if actionConfig " + actionConfig + " has an exceptionConfig for " + exception.getClass().getName());
            exceptionConfig =
                actionConfig.findException(exception.getClass());
        }

        // Handle the exception in the configured manner
        if (exceptionConfig == null) {
            log.warn("Unhandled exception", exception);
            throw exception;
        }
        ForwardConfig forwardConfig =
            handle(context, exception, exceptionConfig,
                   actionConfig, moduleConfig);
        if (forwardConfig != null) {
            context.put(getForwardConfigKey(), forwardConfig);
            return (false);
        } else {
            return (true);
        }

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Perform the required handling of the specified exception.</p>
     *
     * @param context The <code>Context</code> for this request
     * @param exception The exception being handled
     * @param exceptionConfig The corresponding {@link ExceptionConfig}
     * @param actionConfig The {@link ActionConfig} for this request
     * @param moduleConfig The {@link ModuleConfig} for this request
     *
     * @return the <code>ForwardConfig</code> to be processed next (if any),
     *  or <code>null</code> if processing has been completed
     */
    protected abstract ForwardConfig handle(Context context,
                                            Exception exception,
                                            ExceptionConfig exceptionConfig,
                                            ActionConfig actionConfig,
                                            ModuleConfig moduleConfig)
        throws Exception;

}
