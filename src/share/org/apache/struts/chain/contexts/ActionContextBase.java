/*
 * $Id$
 *
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
package org.apache.struts.chain.contexts;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.TokenProcessor;


/**
 * <p>Title: struts</p>
 * <p>Description: The core of the Struts framework is a flexible control layer based on standard technologies like Java Servlets, JavaBeans, ResourceBundles, and Extensible Markup Language (XML), as well as various Jakarta Commons packages. Struts encourages application architectures based on the Model 2 approach, a variation of the classic Model-View-Controller (MVC) design paradigm. Struts provides its own Controller component and integrates with other technologies to provide the Model and the View. For the Model, Struts can interact with any standard data access technology, including Enterprise Java Beans, JDBC, and Object Relational Bridge. For the View, Struts works well with JavaServer Pages, including JSTL and JSF, as well as Velocity Templates, XSLT, and other presentation systems. The Struts framework provides the invisible underpinnings every professional web application needs to survive. Struts helps you create an extensible development environment for your application, based on published standards and proven design patterns.</p>
 * <p>Copyright: Copyright (c) 2000-2004 The Apache Software Foundation - All Rights Reserved.</p>
 * <p>Company: The Apache Software Foundation</p>
 * @author germuska
 * @version 1.2.5
 */

public abstract class ActionContextBase extends ContextWrapper implements ActionContext {


    public static final String ACTION_KEY = Constants.ACTION_KEY;

    public static final String ACTION_CONFIG_KEY = Constants.ACTION_CONFIG_KEY;

    public static final String ACTION_FORM_KEY = Constants.ACTION_FORM_KEY;

    public static final String FORWARD_CONFIG_KEY = Constants.FORWARD_CONFIG_KEY;

    public static final String MODULE_CONFIG_KEY = Constants.MODULE_CONFIG_KEY;

    public static final String EXCEPTION_KEY = Constants.EXCEPTION_KEY;

    public static final String ERROR_ACTION_MESSAGES_KEY = "errors";

    public static final String MESSAGE_ACTION_MESSAGES_KEY = "messages";

    public static final String MESSAGE_RESOURCES_KEY = Constants.MESSAGE_RESOURCES_KEY;

    public static final String INCLUDE_KEY = Constants.INCLUDE_KEY;

    public static final String LOCALE_KEY = Constants.LOCALE_KEY;

    public static final String CANCEL_KEY = Constants.CANCEL_KEY;

    public static final String VALID_KEY = Constants.VALID_KEY;

    public static final String TRANSACTION_TOKEN_KEY = "TRANSACTION_TOKEN_KEY";

    public static final String TOKEN_KEY = "TOKEN_KEY";

    protected TokenProcessor token = null;

    private Log logger = null;

    public ActionContextBase(Context context) {
        super(context);
        token = TokenProcessor.getInstance();
        logger = LogFactory.getLog(this.getClass());
    }

    public ActionContextBase() {
        this(new ContextBase());
    }

    // -------------------------------
    // General Application Support
    // -------------------------------

    public void release() {
        this.token = null;
     };

    public abstract Map getApplicationScope();

    public abstract Map getRequestScope();

    public abstract Map getSessionScope();

    public Map getScope(String scopeName) {
        if (REQUEST_SCOPE.equals(scopeName)) {
            return this.getRequestScope();
        }
        if (SESSION_SCOPE.equals(scopeName)) {
            return this.getSessionScope();
        }
        if (APPLICATION_SCOPE.equals(scopeName)) {
            return this.getApplicationScope();
        }
        throw new IllegalArgumentException("Invalid scope: " + scopeName);
    }

    // -------------------------------
    // General Struts properties
    // -------------------------------
    public void setAction(Action action) {
        this.put(ACTION_KEY, action);
    }

    public Action getAction() {
        return (Action) this.get(ACTION_KEY);
    }

    public void setActionForm(ActionForm form) {
        this.put(ACTION_FORM_KEY, form);
    }

    public ActionForm getActionForm() {
        return (ActionForm) this.get(ACTION_FORM_KEY);
    }

    public void setActionConfig(ActionConfig config) {
        this.put(ACTION_CONFIG_KEY, config);
    }

    public ActionConfig getActionConfig() {
        return (ActionConfig) this.get(ACTION_CONFIG_KEY);
    }

    public  void setForwardConfig(ForwardConfig forward) {
        this.put(FORWARD_CONFIG_KEY, forward);
    }

    public ForwardConfig getForwardConfig() {
        return (ForwardConfig) this.get(FORWARD_CONFIG_KEY);
    }

    public void setInclude(String include) {
        this.put(INCLUDE_KEY, include);
    }

    public String getInclude() {
        return (String) this.get(INCLUDE_KEY);
    }

    public Boolean getFormValid() {
        return (Boolean) this.get(VALID_KEY);
    }

    public void setFormValid(Boolean valid) {
        this.put(VALID_KEY, valid);
    }

    public ModuleConfig getModuleConfig() {
        return (ModuleConfig) this.get(MODULE_CONFIG_KEY);
    }

    public void setModuleConfig(ModuleConfig config) {
        this.put(MODULE_CONFIG_KEY, config);
    }

    public Exception getException() {
        return (Exception) this.get(EXCEPTION_KEY);
    }

    public void setException(Exception e) {
        this.put(EXCEPTION_KEY, e);
    }

    // -------------------------------
    // ActionMessage Processing
    // -------------------------------

    public void addMessages(ActionMessages messages) {
        this.addActionMessages(MESSAGE_ACTION_MESSAGES_KEY, messages);
    }

    public void addErrors(ActionMessages errors) {
        this.addActionMessages(ERROR_ACTION_MESSAGES_KEY, errors);
    }

    public ActionMessages getErrors() {
        return (ActionMessages) this.get(ERROR_ACTION_MESSAGES_KEY);
    }

    public ActionMessages getMessages() {
        return (ActionMessages) this.get(MESSAGE_ACTION_MESSAGES_KEY);
    }

    public void saveErrors(ActionMessages errors) {
        this.saveActionMessages(ERROR_ACTION_MESSAGES_KEY, errors);
    }

    public void saveMessages(ActionMessages messages) {
        this.saveActionMessages(MESSAGE_ACTION_MESSAGES_KEY, messages);
    }

    // do we want to add this to the public API?
    public void addActionMessages(String key, ActionMessages msgs) {

        if (msgs == null) {
            // bad programmer! *slap*
            return;
        }

        // get any existing messages from the request, or make a new one
        ActionMessages requestMessages = (ActionMessages) this.get(key);
        if (requestMessages == null) {
            requestMessages = new ActionMessages();
        }
        // add incoming messages
        requestMessages.add(msgs);

        // if still empty, just wipe it out from the request
        this.remove(key);

        // Save the messages
        this.saveActionMessages(key, requestMessages);
    }

    // do we want to add this to the public API?
    public void saveActionMessages(String key, ActionMessages msgs) {
        if ((msgs == null) || msgs.isEmpty()) {
            this.remove(key);
            return;
        }
        this.put(key, msgs);
    }


    /**
     * ActionContextBase only has one scope, so this method delegates
     * to saveMessages(messages).
     * @param scope
     * @param messages
     */
    public void saveMessages(String scope, ActionMessages messages) {
        this.saveMessages(messages);
    }


    // -------------------------------
    // Token Processing
    // -------------------------------

    /** @todo Is there a problem trying to map this method from Action
     * to ActionContext when we aren't necessarily sure how token
     * processing maps into a context with an ill-defined "session"?
     * There's no getToken() method, but maybe there should be. */
    public void saveToken() {
        String token = this.generateToken();
        this.put(TRANSACTION_TOKEN_KEY, token);
    }

    public String generateToken() {
        return token.generateToken(getTokenGeneratorId());

    }

    protected String getTokenGeneratorId() {
        /** @todo The original implementation was based on the HttpSession identifier;
         what would be a way to do that without depending on the Servlet API?
         */
        return "";

    }

    public boolean isTokenValid() {
        return this.isTokenValid(false);
    }

    public boolean isTokenValid(boolean reset) {

        // Retrieve the transaction token from this session, and
        // reset it if requested
        String saved = (String) this.get(TRANSACTION_TOKEN_KEY);
        if (saved == null) {
            return false;
        }

        if (reset) {
            this.resetToken();
        }

        // Retrieve the transaction token included in this request
        String token = (String) this.get(TOKEN_KEY);
        if (token == null) {
            return false;
        }

        return saved.equals(token);
    }

    public void resetToken() {
        this.remove(TRANSACTION_TOKEN_KEY);
    }

    // -------------------------------
    // Cancel Processing
    // -------------------------------
    public Boolean getCancelled() {
        return (Boolean) this.get(CANCEL_KEY);
    }

    public void setCancelled(Boolean cancelled) {
        this.put(CANCEL_KEY, cancelled);
    }

    // -------------------------------
    // MessageResources Processing
    // -------------------------------
    public void setMessageResources(MessageResources messageResources) {
        this.put(MESSAGE_RESOURCES_KEY, messageResources);
    }

    public MessageResources getMessageResources() {
        return (MessageResources) this.get(MESSAGE_RESOURCES_KEY);
    }

    public MessageResources getMessageResources(String key) {
        return (MessageResources) this.get(key);
    }

    // -------------------------------
    // Locale Processing
    // -------------------------------
    public void setLocale(Locale locale) {
        this.put(LOCALE_KEY, locale);
    }


    public Locale getLocale() {
        return (Locale) this.get(LOCALE_KEY);
    }

    // -------------------------------
    // Convenience Methods: these are not part of the formal ActionContext API,
    // but are likely to be commonly useful.
    // -------------------------------
    /**
     * <p>Return the currently configured commons-logging <code>Log</code> instance.</p>
     * @return
     */
    public Log getLogger() {
        return this.logger;
    }

    /**
     * <p>Set the commons-logging <code>Log</code> instance which should be used to log messages.
     * This is initialized at instantiation time but may be overridden.  Be advised not to set the value
     * to null, as <code>ActionContextBase</code> uses the logger for some of its own operations.</p>
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     * Using this <code>ActionContext</code>'s default <code>ModuleConfig</code>, return an existing
     * <code>ActionForm</code> in the specified scope, or create a new one and add it to the specified scope.
     * @param formName
     * @param scopeName
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @see findOrCreateActionForm(String, String, ModuleConfig)
     */
    public ActionForm findOrCreateActionForm(String formName, String scopeName) throws IllegalAccessException, InstantiationException {
        return this.findOrCreateActionForm(formName, scopeName, this.getModuleConfig());
    }

    /**
     * <p>In the context of the given <code>ModuleConfig</code> and this <code>ActionContext</code>,
     * look for an existing <code>ActionForm</code> in the specified scope.  If one is found, return
     * it; otherwise, create a new instance, add it to that scope, and then return it.</p>
     * @param formName
     * @param scopeName
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public ActionForm findOrCreateActionForm(String formName, String scopeName, ModuleConfig moduleConfig) throws IllegalAccessException, InstantiationException {
        Map scope = this.getScope(scopeName);

        ActionForm instance = null;
        FormBeanConfig formBeanConfig = moduleConfig.findFormBeanConfig(formName);

        if (formBeanConfig == null) {
            throw new IllegalArgumentException("No form config found under " + formName + " in module " + moduleConfig.getPrefix() );
        }

        instance = (ActionForm) scope.get(formName);
        // Can we recycle the existing instance (if any)?
        if (instance != null) {
            getLogger().trace("Found an instance in scope " + scopeName + "; test for reusability");
            if (formBeanConfig.canReuse(instance)) {
                return instance;
            }
        }

        ActionForm form = formBeanConfig.createActionForm(this);
        scope.put(formName, form);
        return form;
    }



}
