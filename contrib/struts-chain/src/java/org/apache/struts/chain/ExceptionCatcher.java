/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/ExceptionCatcher.java,v 1.1 2003/08/31 21:53:00 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/08/31 21:53:00 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.struts.chain;


import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.chain.Constants;


/**
 * <p>Intercept any exception thrown by a subsequent <code>Command</code>
 * in this processing chain, and fire the configured exception handler chain
 * after storing the exception that has occurred into the <code>Context</code>.
 * </p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/08/31 21:53:00 $
 */

public class ExceptionCatcher implements Filter {


    // ------------------------------------------------------ Instance Variables


    private String catalogKey = Constants.CATALOG_KEY;
    private String exceptionCommand = null;
    private String exceptionKey = Constants.EXCEPTION_KEY;

    private static final Log log = LogFactory.getLog(ExceptionCatcher.class);


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context attribute key under which the
     * <code>Catalog</code> we perform lookups in is stored.</p>
     */
    public String getCatalogKey() {

        return (this.catalogKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>Catalog</code> we perform lookups in is stored.</p>
     *
     * @param catalogKey The new context attribute key
     */
    public void setCatalogKey(String catalogKey) {

        this.catalogKey = catalogKey;

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

        context.getAttributes().remove(getExceptionKey());
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
        context.getAttributes().put(getExceptionKey(), exception);

        // Execute the specified command
        try {
            Catalog catalog = (Catalog)
                context.getAttributes().get(getCatalogKey());
            Command command = catalog.getCommand(getExceptionCommand());
            if (log.isTraceEnabled()) {
                log.trace("Calling handler command '" + getExceptionCommand()
                          + "'");
            }
            command.execute(context);
        } catch (Exception e) {
            log.warn("Exception from handler command '" +
                     getExceptionCommand() + "'", e);
            throw new IllegalStateException("Exception chain threw exception");
        }
        return (true);

    }



}
