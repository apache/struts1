/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/CreateAction.java,v 1.2 2003/09/29 06:55:07 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2003/09/29 06:55:07 $
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


import java.util.HashMap;
import java.util.Map;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.WebContext;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.Constants;
import org.apache.struts.chain.util.ClassUtils;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Create (if necessary) and cache an <code>Action</code> for this request.
 * </p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2003/09/29 06:55:07 $
 */

public class CreateAction implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionKey = Constants.ACTION_KEY;
    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String actionServletKey = Constants.ACTION_SERVLET_KEY;


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
     * <code>ActionServlet</code> for the current web application
     * is stored.</p>
     */
    public String getActionServletKey() {

        return (this.actionServletKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ActionServlet</code> for the current web application
     * is stored.</p>
     *
     * @param actionServletKey The new context attribute key
     */
    public void setActionServletKey(String actionServletKey) {

        this.actionServletKey = actionServletKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Create (if necessary) and cache an <code>Action</code> for this
     * request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Look up the class name for the desired Action
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
        String type = actionConfig.getType();

        // Create (if necessary) and cache an Action instance
        Action action = null;
        Map actions = getActions(context, actionConfig.getModuleConfig());
        synchronized (actions) {
            action = (Action) actions.get(type);
            if (action == null) {
                action = (Action) ClassUtils.getApplicationInstance(type);
                ActionServlet actionServlet = (ActionServlet)
                    context.get(getActionServletKey());
                action.setServlet(actionServlet);
                actions.put(type, action);
            }
        }
        context.put(getActionKey(), action);

        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Create (if necessary) and return a <code>Map</code> containing the
     * <code>Action</code> instances for the current application module.</p>
     *
     * @param context The context for this request
     * @param moduleConfig The <code>ModuleConfig</code> for the current
     *  application module
     */
    protected synchronized Map getActions(Context context,
                                          ModuleConfig moduleConfig) {

        WebContext wcontext = (WebContext) context;
        String actionsKey = Constants.ACTIONS_KEY + moduleConfig.getPrefix();
        Map actions = (Map) wcontext.getApplicationScope().get(actionsKey);
        if (actions == null) {
            actions = new HashMap();
            wcontext.getApplicationScope().put(actionKey, actions);
        }
        return (actions);

    }



}
