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


import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.commands.util.ClassUtils;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;


/**
 * <p>Create (if necessary) and cache a form bean for this request.</p>
 *
 * @version $Id$
 * @deprecated This class no longer needs to be abstract.  Use
 * <code>org.apache.struts.chain.commands.CreateActionForm</code> instead.
 * This will be removed BEFORE a full Struts 1.3.0 release.
 */

public abstract class AbstractCreateActionForm extends ActionCommandBase {


    // ------------------------------------------------------ Instance Variables


    private static final Log log =
        LogFactory.getLog(AbstractCreateActionForm.class);

    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Create (if necessary) and cache a form bean for this request.</p>
     *
     * @param actionCtx The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(ActionContext actionCtx) throws Exception {

        // Is there a form bean associated with this ActionConfig?
        ActionConfig actionConfig = actionCtx.getActionConfig();
        String name = actionConfig.getName();
        if (name == null) {
            actionCtx.setActionForm(null);
            return (false);
        }

        log.trace("Look up form-bean " + name);

        // Look up the corresponding FormBeanConfig (if any)
        FormBeanConfig formBeanConfig =
            actionConfig.getModuleConfig().findFormBeanConfig(name);
        if (formBeanConfig == null) {
            log.warn("No FormBeanConfig found in module "
                     + actionConfig.getModuleConfig().getPrefix()
                     + " under name " + name);
            actionCtx.setActionForm(null);
            return (false);
        }

        ActionForm instance = null;
        if ("session".equals(actionConfig.getScope())) {
            instance = (ActionForm)
            actionCtx.getSessionScope().get(actionConfig.getAttribute());
        }

        // Can we recycle the existing instance (if any)?
        if (instance != null) {
            log.trace("Found an instance in the session; test for reusability");
            if (formBeanConfig.getDynamic()) {
                String className =
                    ((DynaBean) instance).getDynaClass().getName();
                if (className.equals(formBeanConfig.getName())) {
                    actionCtx.setActionForm(instance);
                    log.debug("Using existing instance (dynamic)");
                    return (false);
                }
            } else {
                try {
                    Class configClass =
                        ClassUtils.getApplicationClass
                        (formBeanConfig.getType());
                    if (configClass.isAssignableFrom(instance.getClass())) {
                        actionCtx.setActionForm(instance);
                        log.debug("Using existing instance (non-dynamic)");
                        return (false);
                    }
                } catch (Exception e) {
                    log.debug("Error testing existing instance for reusability; just create a new instance", e);
                }
            }
        }

        instance = createNewFormInstance(actionCtx, actionConfig, formBeanConfig);
        actionCtx.setActionForm(instance);

        /** @todo Perhaps this logic could be moved into the ActionContext implementation,
         * or changed to a two-arg form: ActionContext.setActionForm(ActionForm, String)
         * where the String was the scope. */
        if ("session".equals(actionConfig.getScope())) {
            actionCtx.getSessionScope().put
                (actionConfig.getAttribute(), instance);
        } else if ("request".equals(actionConfig.getScope())) {
            actionCtx.getRequestScope().put
                (actionConfig.getAttribute(), instance);
        }

        return (false);

    }

    /**
     * Request the creation of a new ActionForm instance based on the information in
     * the <code>ActionContext</code>, <code>ActionConfig</code>, and <code>FormBeanConfig</code>.
     * :TODO if FormBeanConfig.createActionForm took ActionContext as an argument instead of Servlet,
     * this method wouldn't need to be abstract.
     * @param actionCtx
     * @param actionConfig
     * @param formBeanConfig
     * @return ActionForm
     * @throws Exception
     */
    protected abstract ActionForm createNewFormInstance(ActionContext actionCtx, ActionConfig actionConfig, FormBeanConfig formBeanConfig) throws Exception;

}
