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


import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.CatalogFactory;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.chain.Constants;
import org.apache.struts.chain.contexts.ActionContext;


/**
 * <p>Intercept any exception thrown by a subsequent <code>Command</code>
 * in this processing chain, and fire the configured exception handler chain
 * after storing the exception that has occurred into the <code>Context</code>.
 * </p>
 *
 * @author Craig R. McClanahan
 * @version $Rev$ $Date$
 */

public class ExceptionCatcher implements Filter {


    // ------------------------------------------------------ Instance Variables


    private String catalogName = null;
    private String exceptionCommand = null;
    private String exceptionKey = Constants.EXCEPTION_KEY;

    private static final Log log = LogFactory.getLog(ExceptionCatcher.class);


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the name of the <code>Catalog</code> in which to perform
     * lookups, or <code>null</code> for the default <code>Catalog</code>.</p>
     */
    public String getCatalogName() {

        return (this.catalogName);

    }


    /**
     * <p>Set the name of the <code>Catalog</code> in which to perform
     * lookups, or <code>null</code> for the default <code>Catalog</code>.</p>
     *
     * @param catalogName The new catalog name or <code>null</code>
     */
    public void setCatalogName(String catalogName) {

        this.catalogName = catalogName;

    }


    /**
     * <p>Return the name of the command to be executed
     * if an exception occurs.</p>
     */
    public String getExceptionCommand() {

        return (this.exceptionCommand);

    }


    /**
     * <p>Set the name of the command to be executed
     * if an exception occurs.</p>
     *
     * @param exceptionCommand The name of the chain to be executed
     */
    public void setExceptionCommand(String exceptionCommand) {

        this.exceptionCommand = exceptionCommand;

    }



    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Clear any existing stored exception and pass the <code>context</code>
     * on to the remainder of the current chain.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {
        ActionContext actionCtx = (ActionContext) context;
        actionCtx.setException(null);
        return (false);

    }


    /**
     * <p>If an exception was thrown by a subsequent <code>Command</code>,
     * pass it on to the specified exception handling chain.  Otherwise,
     * do nothing.</p>
     *
     * @param context The {@link Context} to be processed by this
     *  {@link Filter}
     * @param exception The <code>Exception</code> (if any) that was thrown
     *  by the last {@link Command} that was executed; otherwise
     *  <code>null</code>
     */ 
    public boolean postprocess(Context context, Exception exception) {
        // Do nothing if there was no exception thrown
        if (exception == null) {
            return (false);
        }

        // Stash the exception in the specified context attribute
        if (log.isDebugEnabled()) {
            log.debug("Attempting to handle a thrown exception");
        }
        ActionContext actionCtx = (ActionContext) context;
        actionCtx.setException(exception);

        // Execute the specified command
        try {
            Command command = lookupExceptionCommand();
            if (command == null) {
                log.error("Cannot find exceptionCommand '" +
                          exceptionCommand + "'");
                throw new IllegalStateException
                    ("Cannot find exceptionCommand '" +
                     exceptionCommand + "'");
            }
            if (log.isTraceEnabled()) {
                log.trace("Calling exceptionCommand '" + exceptionCommand
                          + "'");
            }
            command.execute(context);
        } catch (Exception e) {
            log.warn("Exception from exceptionCommand '" +
                     exceptionCommand + "'", e);
            throw new IllegalStateException("Exception chain threw exception");
        }
        return (true);

    }

    protected Command lookupExceptionCommand() throws IllegalArgumentException, IllegalStateException {
        String catalogName = getCatalogName();
        Catalog catalog = null;
        if (catalogName == null) {
            catalog = CatalogFactory.getInstance().getCatalog();
            if (catalog == null) {
                log.error("Cannot find default catalog");
                throw new IllegalArgumentException
                    ("Cannot find default catalog");
            }
        } else {
            catalog = CatalogFactory.getInstance().getCatalog(catalogName);
            if (catalog == null) {
                log.error("Cannot find catalog '" + catalogName + "'");
                throw new IllegalArgumentException
                    ("Cannot find catalog '" + catalogName + "'");
            }
        }
        String exceptionCommand = getExceptionCommand();
        if (exceptionCommand == null) {
            log.error("No exceptionCommand property specified");
            throw new IllegalStateException
                ("No exceptionCommand property specfied");
        }
        return catalog.getCommand(exceptionCommand);
        
    }

}
