package org.apache.struts.chain.commands;

import org.apache.struts.chain.Constants;
import org.apache.struts.config.ActionConfig;

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.CatalogFactory;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class ExecuteCommand implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String validKey = Constants.VALID_KEY;

    private static final Log log =
        LogFactory.getLog(ExecuteCommand.class);


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
     * validity flag for this request is stored.</p>
     */
    public String getValidKey() {

        return (this.validKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * validity flag for this request is stored.</p>
     *
     * @param validKey The new context attribute key
     */
    public void setValidKey(String validKey) {

        this.validKey = validKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>If the <code>context</code> is "valid", lookup a command and execute it.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return the result of the lookup command's <code>execute</code> method, if executed,
     * or <code>false</code> if it was not executed.
     */
    public boolean execute(Context context) throws Exception {

        if (shouldProcess(context)) {

            Command command = getCommand(context);

            if (command != null) {
                return (command.execute(context));
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
    protected boolean shouldProcess(Context context) {
        // Skip processing if the current request is not valid
        Boolean valid = (Boolean) context.get(getValidKey());
        return ((valid != null) && valid.booleanValue());
        
    }

    /**
     * Find the <code>ActionConfig</code> in the current context and, if it is 
     * properly configured, lookup the appropriate <code>commons-chain</code> command.
     * @param context
     * @return a <code>Command</code> to execute, or null if none is specified
     * or if the specified command cannot be found.
     */
    protected Command getCommand(Context context) {

        ActionConfig actionConfig = (ActionConfig)
                                    context.get(getActionConfigKey());

        String commandName = actionConfig.getCommand();

        if (commandName == null) {
            return null;
        }

        String catalogName = actionConfig.getCatalog();

        Command command = null;
        Catalog catalog = null;

        if (catalogName != null) {
            catalog = CatalogFactory.getInstance().getCatalog(catalogName);
            if (catalog == null) {
                log.warn("No catalog found under " + catalogName);
                return null;
            }

        } else {
            catalogName = "the default catalog";
            catalog = CatalogFactory.getInstance().getCatalog();
            if (catalog == null) {
                log.warn("No default catalog found.");
                return null;
            }
        }

        log.debug("looking up command " + commandName + " in " + catalogName);
        return catalog.getCommand(commandName);

    }


}