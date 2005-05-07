/*
 * Copyright 2005 The Apache Software Foundation.
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

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.CatalogFactory;
import org.apache.commons.chain.Command;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;

/**
 * <p>Invoke the appropriate <code>Command</code> for this request.  If the context's
 * <code>ActionConfig</code> has no <code>command</code> property defined, no action
 * will be taken.  If the specified command cannot be found, a warning will be logged,
 * but processing will continue.  Depending on how the chain is configured, this can
 * be used in place of an <code>Action</code> or as a method of performing pre-processing.
 * </p>
 * <p>If used instead of an action, the command which is looked up should put an ActionForward
 * into the context, unless it has already dealt with the response.</p>
 * @version $Id$
 */
public class ExecuteCommand extends ActionCommandBase {


    // ------------------------------------------------------ Instance Variables
    private static final Log log =
        LogFactory.getLog(ExecuteCommand.class);



    // ---------------------------------------------------------- Public Methods


    /**
     * <p>If the <code>context</code> is "valid", lookup a command and execute it.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return the result of the lookup command's <code>execute</code> method, if executed,
     * or <code>false</code> if it was not executed.
     */
    public boolean execute(ActionContext actionCtx) throws Exception {
        if (shouldProcess(actionCtx)) {

            Command command = getCommand(actionCtx);

            if (command != null) {
                return (command.execute(actionCtx));
            }

        }

        return (false) ;

    }

    /**
     * Evaluate the current context to see if a command should even be
     * executed.
     * @param context
     * @return
     */
    protected boolean shouldProcess(ActionContext context) {
        // Skip processing if the current request is not valid
        Boolean valid = context.getFormValid();
        return ((valid != null) && valid.booleanValue());

    }

    /**
     * Find the <code>ActionConfig</code> in the current context and, if it is
     * properly configured, lookup the appropriate <code>commons-chain</code> command.
     * @param context
     * @return a <code>Command</code> to execute, or null if none is specified
     * or if the specified command cannot be found.
     */
    protected Command getCommand(ActionContext context) {

        ActionConfig actionConfig = context.getActionConfig();

        String commandName = actionConfig.getCommand();

        if (commandName == null) {
            return null;
        }

        String catalogName = actionConfig.getCatalog();

        return getCommand(commandName, catalogName);

    }

    /**
     * @param commandName
     * @param catalogName
     * @return
     */
    protected Command getCommand(String commandName, String catalogName) {

        if (commandName == null) return null;

        Command command = null;
        Catalog catalog = null;

        if (catalogName != null) {
            catalog = CatalogFactory.getInstance().getCatalog(catalogName);
            if (catalog == null) {
                log.warn("When looking up " + commandName + ", no catalog found under " + catalogName);
                return null;
            }

        } else {
            catalogName = "the default catalog";
            catalog = CatalogFactory.getInstance().getCatalog();
            if (catalog == null) {
                log.warn("When looking up " + commandName + ", no default catalog found.");
                return null;
            }
        }

        log.debug("looking up command " + commandName + " in " + catalogName);
        return catalog.getCommand(commandName);
    }


}