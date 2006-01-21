/*
 * $Id$
 *
 * Copyright 1999-2004 The Apache Software Foundation.
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
package org.apache.struts.config;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.RequestUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>A JavaBean representing the configuration information of an
 * <code>&lt;action&gt;</code> element from a Struts module configuration
 * file.</p>
 *
 * @version $Rev$ $Date: 2005-08-06 04:12:10 -0400 (Sat, 06 Aug 2005)
 *          $
 * @since Struts 1.1
 */
public class ActionConfig extends BaseConfig {
    private static final Log log = LogFactory.getLog(ActionConfig.class);

    // ----------------------------------------------------- Instance Variables

    /**
     * The set of exception handling configurations for this action, if any,
     * keyed by the <code>type</code> property.
     */
    protected HashMap exceptions = new HashMap();

    /**
     * The set of local forward configurations for this action, if any, keyed
     * by the <code>name</code> property.
     */
    protected HashMap forwards = new HashMap();

    // ------------------------------------------------------------- Properties

    /**
     * The module configuration with which we are associated.
     */
    protected ModuleConfig moduleConfig = null;

    /**
     * The request-scope or session-scope attribute name under which our form
     * bean is accessed, if it is different from the form bean's specified
     * <code>name</code>.
     */
    protected String attribute = null;

    /**
     * <p>The path of the ActionConfig that this object should inherit
     * properties from.</p>
     */
    protected String inherit = null;

    /**
     * Have the inheritance values for this class been applied?
     */
    protected boolean extensionProcessed = false;

    /**
     * Context-relative path of the web application resource that will process
     * this request via RequestDispatcher.forward(), instead of instantiating
     * and calling the <code>Action</code> class specified by "type". Exactly
     * one of <code>forward</code>, <code>include</code>, or <code>type</code>
     * must be specified.
     */
    protected String forward = null;

    /**
     * Context-relative path of the web application resource that will process
     * this request via RequestDispatcher.include(), instead of instantiating
     * and calling the <code>Action</code> class specified by "type". Exactly
     * one of <code>forward</code>, <code>include</code>, or <code>type</code>
     * must be specified.
     */
    protected String include = null;

    /**
     * Context-relative path of the input form to which control should be
     * returned if a validation error is encountered.  Required if "name" is
     * specified and the input bean returns validation errors.
     */
    protected String input = null;

    /**
     * Fully qualified Java class name of the <code>MultipartRequestHandler</code>
     * implementation class used to process multi-part request data for this
     * Action.
     */
    protected String multipartClass = null;

    /**
     * Name of the form bean, if any, associated with this Action.
     */
    protected String name = null;

    /**
     * General purpose configuration parameter that can be used to pass extra
     * information to the Action instance selected by this Action. Struts does
     * not itself use this value in any way.
     */
    protected String parameter = null;

    /**
     * Context-relative path of the submitted request, starting with a slash
     * ("/") character, and omitting any filename extension if extension
     * mapping is being used.
     */
    protected String path = null;

    /**
     * Prefix used to match request parameter names to form bean property
     * names, if any.
     */
    protected String prefix = null;

    /**
     * Comma-delimited list of security role names allowed to request this
     * Action.
     */
    protected String roles = null;

    /**
     * The set of security role names used to authorize access to this Action,
     * as an array for faster access.
     */
    protected String[] roleNames = new String[0];

    /**
     * Identifier of the scope ("request" or "session") within which our form
     * bean is accessed, if any.
     */
    protected String scope = "session";

    /**
     * Suffix used to match request parameter names to form bean property
     * names, if any.
     */
    protected String suffix = null;

    /**
     * Fully qualified Java class name of the <code>Action</code> class to be
     * used to process requests for this mapping if the <code>forward</code>
     * and <code>include</code> properties are not set. Exactly one of
     * <code>forward</code>, <code>include</code>, or <code>type</code> must
     * be specified.
     */
    protected String type = null;

    /**
     * Indicates Action be configured as the default one for this module, when
     * true.
     */
    protected boolean unknown = false;

    /**
     * Should the <code>validate()</code> method of the form bean associated
     * with this action be called?
     */
    protected boolean validate = true;

    /**
     * The name of a <code>commons-chain</code> command which should be
     * executed as part of the processing of this action.
     *
     * @since Struts 1.3.0
     */
    protected String command = null;

    /**
     * The name of a <code>commons-chain</code> catalog in which
     * <code>command</code> should be sought.  If a <code>command</code> is
     * defined and this property is undefined, the "default" catalog will be
     * used. This is likely to be infrequently used after a future release of
     * <code>commons-chain</code> supports a one-string expression of a
     * catalog/chain combination.
     *
     * @since Struts 1.3.0
     */
    protected String catalog = null;

    /**
     * The module configuration with which we are associated.
     */
    public ModuleConfig getModuleConfig() {
        return (this.moduleConfig);
    }

    /**
     * The module configuration with which we are associated.
     */
    public void setModuleConfig(ModuleConfig moduleConfig) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.moduleConfig = moduleConfig;
    }

    /**
     * Returns the request-scope or session-scope attribute name under which
     * our form bean is accessed, if it is different from the form bean's
     * specified <code>name</code>.
     *
     * @return attribute name under which our form bean is accessed.
     */
    public String getAttribute() {
        if (this.attribute == null) {
            return (this.name);
        } else {
            return (this.attribute);
        }
    }

    /**
     * Set the request-scope or session-scope attribute name under which our
     * form bean is accessed, if it is different from the form bean's
     * specified <code>name</code>.
     *
     * @param attribute the request-scope or session-scope attribute name
     *                  under which our form bean is access.
     */
    public void setAttribute(String attribute) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.attribute = attribute;
    }

    /**
     * <p>Returns the path of the ActionConfig that this object should inherit
     * properties from.</p>
     *
     * @return the path of the ActionConfig that this object should inherit
     *         properties from.
     */
    public String getExtends() {
        return (this.inherit);
    }

    /**
     * <p>Set the path of the ActionConfig that this object should inherit
     * properties from.</p>
     *
     * @param inherit the path of the ActionConfig that this object should
     *                inherit properties from.
     */
    public void setExtends(String inherit) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.inherit = inherit;
    }

    public boolean isExtensionProcessed() {
        return extensionProcessed;
    }

    /**
     * Returns context-relative path of the web application resource that will
     * process this request.
     *
     * @return context-relative path of the web application resource that will
     *         process this request.
     */
    public String getForward() {
        return (this.forward);
    }

    /**
     * Set the context-relative path of the web application resource that will
     * process this request. Exactly one of <code>forward</code>,
     * <code>include</code>, or <code>type</code> must be specified.
     *
     * @param forward context-relative path of the web application resource
     *                that will process this request.
     */
    public void setForward(String forward) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.forward = forward;
    }

    /**
     * Context-relative path of the web application resource that will process
     * this request.
     *
     * @return Context-relative path of the web application resource that will
     *         process this request.
     */
    public String getInclude() {
        return (this.include);
    }

    /**
     * Set context-relative path of the web application resource that will
     * process this request. Exactly one of <code>forward</code>,
     * <code>include</code>, or <code>type</code> must be specified.
     *
     * @param include context-relative path of the web application resource
     *                that will process this request.
     */
    public void setInclude(String include) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.include = include;
    }

    /**
     * Get the context-relative path of the input form to which control should
     * be returned if a validation error is encountered.
     *
     * @return context-relative path of the input form to which control should
     *         be returned if a validation error is encountered.
     */
    public String getInput() {
        return (this.input);
    }

    /**
     * Set the context-relative path of the input form to which control should
     * be returned if a validation error is encountered.  Required if "name"
     * is specified and the input bean returns validation errors.
     *
     * @param input context-relative path of the input form to which control
     *              should be returned if a validation error is encountered.
     */
    public void setInput(String input) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.input = input;
    }

    /**
     * Return the fully qualified Java class name of the
     * <code>MultipartRequestHandler</code> implementation class used to
     * process multi-part request data for this Action.
     */
    public String getMultipartClass() {
        return (this.multipartClass);
    }

    /**
     * Set the fully qualified Java class name of the <code>MultipartRequestHandler</code>
     * implementation class used to process multi-part request data for this
     * Action.
     *
     * @param multipartClass fully qualified class name of the
     *                       <code>MultipartRequestHandler</code>
     *                       implementation class.
     */
    public void setMultipartClass(String multipartClass) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.multipartClass = multipartClass;
    }

    /**
     * Return name of the form bean, if any, associated with this Action.
     */
    public String getName() {
        return (this.name);
    }

    /**
     * @param name name of the form bean associated with this Action.
     */
    public void setName(String name) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.name = name;
    }

    /**
     * Return general purpose configuration parameter that can be used to pass
     * extra information to the Action instance selected by this Action.
     * Struts does not itself use this value in any way.
     */
    public String getParameter() {
        return (this.parameter);
    }

    /**
     * General purpose configuration parameter that can be used to pass extra
     * information to the Action instance selected by this Action. Struts does
     * not itself use this value in any way.
     *
     * @param parameter General purpose configuration parameter.
     */
    public void setParameter(String parameter) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.parameter = parameter;
    }

    /**
     * Return context-relative path of the submitted request, starting with a
     * slash ("/") character, and omitting any filename extension if extension
     * mapping is being used.
     */
    public String getPath() {
        return (this.path);
    }

    /**
     * Set context-relative path of the submitted request, starting with a
     * slash ("/") character, and omitting any filename extension if extension
     * mapping is being used.
     *
     * @param path context-relative path of the submitted request.
     */
    public void setPath(String path) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.path = path;
    }

    /**
     * Retruns prefix used to match request parameter names to form bean
     * property names, if any.
     */
    public String getPrefix() {
        return (this.prefix);
    }

    /**
     * @param prefix Prefix used to match request parameter names to form bean
     *               property names, if any.
     */
    public void setPrefix(String prefix) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.prefix = prefix;
    }

    public String getRoles() {
        return (this.roles);
    }

    public void setRoles(String roles) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.roles = roles;

        if (roles == null) {
            roleNames = new String[0];

            return;
        }

        ArrayList list = new ArrayList();

        while (true) {
            int comma = roles.indexOf(',');

            if (comma < 0) {
                break;
            }

            list.add(roles.substring(0, comma).trim());
            roles = roles.substring(comma + 1);
        }

        roles = roles.trim();

        if (roles.length() > 0) {
            list.add(roles);
        }

        roleNames = (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * Get array of security role names used to authorize access to this
     * Action.
     */
    public String[] getRoleNames() {
        return (this.roleNames);
    }

    /**
     * Get the scope ("request" or "session") within which our form bean is
     * accessed, if any.
     */
    public String getScope() {
        return (this.scope);
    }

    /**
     * @param scope scope ("request" or "session") within which our form bean
     *              is accessed, if any.
     */
    public void setScope(String scope) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.scope = scope;
    }

    /**
     * Return suffix used to match request parameter names to form bean
     * property names, if any.
     */
    public String getSuffix() {
        return (this.suffix);
    }

    /**
     * @param suffix Suffix used to match request parameter names to form bean
     *               property names, if any.
     */
    public void setSuffix(String suffix) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.suffix = suffix;
    }

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.type = type;
    }

    /**
     * Determine whether Action is configured as the default one for this
     * module.
     */
    public boolean getUnknown() {
        return (this.unknown);
    }

    /**
     * @param unknown Indicates Action is configured as the default one for
     *                this module, when true.
     */
    public void setUnknown(boolean unknown) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.unknown = unknown;
    }

    public boolean getValidate() {
        return (this.validate);
    }

    public void setValidate(boolean validate) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.validate = validate;
    }

    /**
     * Get the name of a <code>commons-chain</code> command which should be
     * executed as part of the processing of this action.
     *
     * @return name of a <code>commons-chain</code> command which should be
     *         executed as part of the processing of this action.
     * @since Struts 1.3.0
     */
    public String getCommand() {
        return (this.command);
    }

    /**
     * Get the name of a <code>commons-chain</code> catalog in which a
     * specified command should be sought.  This is likely to be infrequently
     * used after a future release of <code>commons-chain</code> supports a
     * one-string expression of a catalog/chain combination.
     *
     * @return name of a <code>commons-chain</code> catalog in which a
     *         specified command should be sought.
     * @since Struts 1.3.0
     */
    public String getCatalog() {
        return (this.catalog);
    }

    /**
     * Set the name of a <code>commons-chain</code> command which should be
     * executed as part of the processing of this action.
     *
     * @param command name of a <code>commons-chain</code> command which
     *                should be executed as part of the processing of this
     *                action.
     * @since Struts 1.3.0
     */
    public void setCommand(String command) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.command = command;
    }

    /**
     * Set the name of a <code>commons-chain</code> catalog in which a
     * specified command should be sought. This is likely to be infrequently
     * used after a future release of <code>commons-chain</code> supports a
     * one-string expression of a catalog/chain combination.
     *
     * @param catalog name of a <code>commons-chain</code> catalog in which a
     *                specified command should be sought.
     * @since Struts 1.3.0
     */
    public void setCatalog(String catalog) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        this.catalog = catalog;
    }

    // ------------------------------------------------------ Protected Methods

    /**
     * <p>Traces the hierarchy of this object to check if any of the ancestors
     * is extending this instance.</p>
     *
     * @param moduleConfig The configuration for the module being configured.
     * @return true if circular inheritance was detected.
     */
    protected boolean checkCircularInheritance(ModuleConfig moduleConfig) {
        String ancestorPath = getExtends();

        while (ancestorPath != null) {
            // check if we have the same path as an ancestor
            if (getPath().equals(ancestorPath)) {
                return true;
            }

            // get our ancestor's ancestor
            ActionConfig ancestor =
                    moduleConfig.findActionConfig(ancestorPath);

            if (ancestor != null) {
                ancestorPath = ancestor.getExtends();
            } else {
                ancestorPath = null;
            }
        }

        return false;
    }

    /**
     * <p>Compare the exception handlers of this action with that of the given
     * and copy those that are not present.</p>
     *
     * @param baseConfig The action config to copy handlers from.
     * @see #inheritFrom(ActionConfig)
     */
    protected void inheritExceptionHandlers(ActionConfig baseConfig)
            throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        // Inherit exception handler configs
        ExceptionConfig[] baseHandlers = baseConfig.findExceptionConfigs();

        for (int i = 0; i < baseHandlers.length; i++) {
            ExceptionConfig baseHandler = baseHandlers[i];

            // Do we have this handler?
            ExceptionConfig copy =
                    this.findExceptionConfig(baseHandler.getType());

            if (copy == null) {
                // We don't have this, so let's copy it
                copy = (ExceptionConfig) RequestUtils
                        .applicationInstance(baseHandler.getClass()
                                .getName());

                BeanUtils.copyProperties(copy, baseHandler);
                this.addExceptionConfig(copy);
                copy.setProperties(baseHandler.copyProperties());
            } else {
                // process any extension that this config might have
                copy.processExtends(getModuleConfig(), this);
            }
        }
    }

    /**
     * <p>Compare the forwards of this action with that of the given and copy
     * those that are not present.</p>
     *
     * @param baseConfig The action config to copy forwards from.
     * @see #inheritFrom(ActionConfig)
     */
    protected void inheritForwards(ActionConfig baseConfig)
            throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        // Inherit forward configs
        ForwardConfig[] baseForwards = baseConfig.findForwardConfigs();

        for (int i = 0; i < baseForwards.length; i++) {
            ForwardConfig baseForward = baseForwards[i];

            // Do we have this forward?
            ForwardConfig copy =
                    this.findForwardConfig(baseForward.getName());

            if (copy == null) {
                // We don't have this, so let's copy it
                copy = (ForwardConfig) RequestUtils
                        .applicationInstance(baseForward.getClass()
                                .getName());
                BeanUtils.copyProperties(copy, baseForward);

                this.addForwardConfig(copy);
                copy.setProperties(baseForward.copyProperties());
            } else {
                // process any extension for this forward
                copy.processExtends(getModuleConfig(), this);
            }
        }
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Add a new <code>ExceptionConfig</code> instance to the set associated
     * with this action.
     *
     * @param config The new configuration instance to be added
     * @throws IllegalStateException if this module configuration has been
     *                               frozen
     */
    public void addExceptionConfig(ExceptionConfig config) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        exceptions.put(config.getType(), config);
    }

    /**
     * Add a new <code>ForwardConfig</code> instance to the set of global
     * forwards associated with this action.
     *
     * @param config The new configuration instance to be added
     * @throws IllegalStateException if this module configuration has been
     *                               frozen
     */
    public void addForwardConfig(ForwardConfig config) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        forwards.put(config.getName(), config);
    }

    /**
     * Return the exception configuration for the specified type, if any;
     * otherwise return <code>null</code>.
     *
     * @param type Exception class name to find a configuration for
     */
    public ExceptionConfig findExceptionConfig(String type) {
        return ((ExceptionConfig) exceptions.get(type));
    }

    /**
     * Return the exception configurations for this action.  If there are
     * none, a zero-length array is returned.
     */
    public ExceptionConfig[] findExceptionConfigs() {
        ExceptionConfig[] results = new ExceptionConfig[exceptions.size()];

        return ((ExceptionConfig[]) exceptions.values().toArray(results));
    }

    /**
     * <p>Find and return the <code>ExceptionConfig</code> instance defining
     * how <code>Exceptions</code> of the specified type should be handled.
     * This is performed by checking local and then global configurations for
     * the specified exception's class, and then looking up the superclass
     * chain (again checking local and then global configurations). If no
     * handler configuration can be found, return <code>null</code>.</p>
     *
     * <p>Introduced in <code>ActionMapping</code> in Struts 1.1, but pushed
     * up to <code>ActionConfig</code> in Struts 1.2.0.</p>
     *
     * @param type Exception class for which to find a handler
     * @since Struts 1.2.0
     */
    public ExceptionConfig findException(Class type) {
        // Check through the entire superclass hierarchy as needed
        ExceptionConfig config = null;

        while (true) {
            // Check for a locally defined handler
            String name = type.getName();

            log.debug("findException: look locally for " + name);
            config = findExceptionConfig(name);

            if (config != null) {
                return (config);
            }

            // Check for a globally defined handler
            log.debug("findException: look globally for " + name);
            config = getModuleConfig().findExceptionConfig(name);

            if (config != null) {
                return (config);
            }

            // Loop again for our superclass (if any)
            type = type.getSuperclass();

            if (type == null) {
                break;
            }
        }

        return (null); // No handler has been configured
    }

    /**
     * Return the forward configuration for the specified key, if any;
     * otherwise return <code>null</code>.
     *
     * @param name Name of the forward configuration to return
     */
    public ForwardConfig findForwardConfig(String name) {
        return ((ForwardConfig) forwards.get(name));
    }

    /**
     * Return all forward configurations for this module.  If there are none,
     * a zero-length array is returned.
     */
    public ForwardConfig[] findForwardConfigs() {
        ForwardConfig[] results = new ForwardConfig[forwards.size()];

        return ((ForwardConfig[]) forwards.values().toArray(results));
    }

    /**
     * Freeze the configuration of this action.
     */
    public void freeze() {
        super.freeze();

        ExceptionConfig[] econfigs = findExceptionConfigs();

        for (int i = 0; i < econfigs.length; i++) {
            econfigs[i].freeze();
        }

        ForwardConfig[] fconfigs = findForwardConfigs();

        for (int i = 0; i < fconfigs.length; i++) {
            fconfigs[i].freeze();
        }
    }

    /**
     * <p>Inherit values that have not been overridden from the provided
     * config object.  Subclasses overriding this method should verify that
     * the given parameter is of a class that contains a property it is trying
     * to inherit:</p>
     *
     * <pre>
     * if (config instanceof MyCustomConfig) {
     *     MyCustomConfig myConfig =
     *         (MyCustomConfig) config;
     *
     *     if (getMyCustomProp() == null) {
     *         setMyCustomProp(myConfig.getMyCustomProp());
     *     }
     * }
     * </pre>
     *
     * <p>If the given <code>config</code> is extending another object, those
     * extensions should be resolved before it's used as a parameter to this
     * method.</p>
     *
     * @param config The object that this instance will be inheriting its
     *               values from.
     * @see #processExtends(ModuleConfig)
     */
    public void inheritFrom(ActionConfig config)
            throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        // Inherit values that have not been overridden
        if (getAttribute() == null) {
            setAttribute(config.getAttribute());
        }

        if (getCatalog() == null) {
            setCatalog(config.getCatalog());
        }

        if (getCommand() == null) {
            setCommand(config.getCommand());
        }

        if (getForward() == null) {
            setForward(config.getForward());
        }

        if (getInclude() == null) {
            setInclude(config.getInclude());
        }

        if (getInput() == null) {
            setInput(config.getInput());
        }

        if (getMultipartClass() == null) {
            setMultipartClass(config.getMultipartClass());
        }

        if (getName() == null) {
            setName(config.getName());
        }

        if (getParameter() == null) {
            setParameter(config.getParameter());
        }

        if (getPath() == null) {
            setPath(config.getPath());
        }

        if (getPrefix() == null) {
            setPrefix(config.getPrefix());
        }

        if (getRoles() == null) {
            setRoles(config.getRoles());
        }

        if (getScope().equals("session")) {
            setScope(config.getScope());
        }

        if (getSuffix() == null) {
            setSuffix(config.getSuffix());
        }

        if (getType() == null) {
            setType(config.getType());
        }

        if (!getUnknown()) {
            setUnknown(config.getUnknown());
        }

        if (getValidate()) {
            setValidate(config.getValidate());
        }

        inheritExceptionHandlers(config);
        inheritForwards(config);
        inheritProperties(config);
    }

    /**
     * <p>Inherit configuration information from the ActionConfig that this
     * instance is extending.  This method verifies that any action config
     * object that it inherits from has also had its processExtends() method
     * called.</p>
     *
     * @param moduleConfig The {@link ModuleConfig} that this bean is from.
     * @see #inheritFrom(ActionConfig)
     */
    public void processExtends(ModuleConfig moduleConfig)
            throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        String ancestorPath = getExtends();

        if ((!extensionProcessed) && (ancestorPath != null)) {
            ActionConfig baseConfig =
                    moduleConfig.findActionConfig(ancestorPath);

            if (baseConfig == null) {
                throw new NullPointerException("Unable to find "
                        + "action for '" + ancestorPath + "' to extend.");
            }

            // Check against circular inheritance and make sure the base
            //  config's own extends has been processed already
            if (checkCircularInheritance(moduleConfig)) {
                throw new IllegalArgumentException(
                        "Circular inheritance detected for action "
                                + getPath());
            }

            // Make sure the ancestor's own extension has been processed.
            if (!baseConfig.isExtensionProcessed()) {
                baseConfig.processExtends(moduleConfig);
            }

            // Copy values from the base config
            inheritFrom(baseConfig);
        }

        extensionProcessed = true;
    }

    /**
     * Remove the specified exception configuration instance.
     *
     * @param config ExceptionConfig instance to be removed
     * @throws IllegalStateException if this module configuration has been
     *                               frozen
     */
    public void removeExceptionConfig(ExceptionConfig config) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        exceptions.remove(config.getType());
    }

    /**
     * Remove the specified forward configuration instance.
     *
     * @param config ForwardConfig instance to be removed
     * @throws IllegalStateException if this module configuration has been
     *                               frozen
     */
    public void removeForwardConfig(ForwardConfig config) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }

        forwards.remove(config.getName());
    }

    /**
     * Return a String representation of this object.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("ActionConfig[");

        sb.append("path=");
        sb.append(path);

        if (attribute != null) {
            sb.append(",attribute=");
            sb.append(attribute);
        }

        if (catalog != null) {
            sb.append(",catalog=");
            sb.append(catalog);
        }

        if (command != null) {
            sb.append(",command=");
            sb.append(command);
        }

        if (inherit != null) {
            sb.append(",extends=");
            sb.append(inherit);
        }

        if (forward != null) {
            sb.append(",forward=");
            sb.append(forward);
        }

        if (include != null) {
            sb.append(",include=");
            sb.append(include);
        }

        if (input != null) {
            sb.append(",input=");
            sb.append(input);
        }

        if (multipartClass != null) {
            sb.append(",multipartClass=");
            sb.append(multipartClass);
        }

        if (name != null) {
            sb.append(",name=");
            sb.append(name);
        }

        if (parameter != null) {
            sb.append(",parameter=");
            sb.append(parameter);
        }

        if (prefix != null) {
            sb.append(",prefix=");
            sb.append(prefix);
        }

        if (roles != null) {
            sb.append(",roles=");
            sb.append(roles);
        }

        if (scope != null) {
            sb.append(",scope=");
            sb.append(scope);
        }

        if (suffix != null) {
            sb.append(",suffix=");
            sb.append(suffix);
        }

        if (type != null) {
            sb.append(",type=");
            sb.append(type);
        }

        return (sb.toString());
    }
}
