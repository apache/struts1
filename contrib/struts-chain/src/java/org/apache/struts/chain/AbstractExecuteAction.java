/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/AbstractExecuteAction.java,v 1.1 2003/08/11 04:55:34 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/08/11 04:55:34 $
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


import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.struts.chain.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;


/**
 * <p>Invoke the appropriate <code>Action</code> for this request, and cache
 * the returned <code>ActionForward</code>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/08/11 04:55:34 $
 */

public abstract class AbstractExecuteAction implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionKey = Constants.ACTION_KEY;
    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String actionFormKey = Constants.ACTION_FORM_KEY;
    private String forwardConfigKey = Constants.FORWARD_CONFIG_KEY;


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context attribute key under which the
     * <code>Action</code> for the currently selected application
     * action is stored.</p>
     */
    public String getActionKey() {

        return (this.actionKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>Action</code> for the currently selected application
     * action is stored.</p>
     *
     * @param actionKey The new context attribute key
     */
    public void setActionKey(String actionKey) {

        this.actionKey = actionKey;

    }


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
     * <code>ActionForm</code> for the currently selected application
     * action is stored.</p>
     */
    public String getActionFormKey() {

        return (this.actionFormKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ActionForm</code> for the currently selected application
     * action is stored.</p>
     *
     * @param actionFormKey The new context attribute key
     */
    public void setActionFormKey(String actionFormKey) {

        this.actionFormKey = actionFormKey;

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
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Acquire the resources we will need to send to the Action
        Action action = (Action)
            context.getAttributes().get(getActionKey());
        if (action == null) {
            return (false);
        }
        ActionConfig actionConfig = (ActionConfig)
            context.getAttributes().get(getActionConfigKey());
        ActionForm actionForm = (ActionForm)
            context.getAttributes().get(getActionFormKey());

        // Execute the Action for this request, caching returned ActionForward
        ForwardConfig forwardConfig =
            execute(context, action, actionConfig, actionForm);
        context.getAttributes().put(getForwardConfigKey(), forwardConfig);

        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Execute the specified <code>Action</code>, and return the resulting
     * <code>ForwardConfig</code>.</p>
     *
     * @param context The <code>Context</code> for this request
     * @param action The <code>Action</code> to be executed
     * @param actionConfig The <code>ActionConfig</code> defining this action
     * @param actionForm The <code>ActionForm</code> (if any) for
     *  this action
     *
     * @exception Exception if thrown by the <code>Action</code>
     */
    protected abstract ForwardConfig execute(Context context,
                                             Action action,
                                             ActionConfig actionConfig,
                                             ActionForm actionForm)
        throws Exception;


}
