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


import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.WebContext;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.chain.Constants;
import org.apache.struts.chain.util.ClassUtils;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Create (if necessary) and cache a form bean for this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.5 $ $Date: 2004/04/09 15:17:43 $
 */

public class CreateActionForm implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String actionFormKey = Constants.ACTION_FORM_KEY;
    private String actionServletKey = Constants.ACTION_SERVLET_KEY;
    private String moduleConfigKey = Constants.MODULE_CONFIG_KEY;

    private static final Log log =
        LogFactory.getLog(CreateActionForm.class);

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
     * <code>ActionServlet</code> for the currently selected application
     * action is stored.</p>
     */
    public String getActionServletKey() {

        return (this.actionServletKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ActionServlet</code> for the currently selected application
     * action is stored.</p>
     *
     * @param actionServletKey The new context attribute key
     */
    public void setActionServletKey(String actionServletKey) {

        this.actionServletKey = actionServletKey;

    }
    
    /**
     * <p>Return the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * module is stored.</p>
     */
    public String getModuleConfigKey() {

        return (this.moduleConfigKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * module is stored.</p>
     *
     * @param moduleConfigKey The new context attribute key
     */
    public void setModuleConfigKey(String moduleConfigKey) {

        this.moduleConfigKey = moduleConfigKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Create (if necessary) and cache a form bean for this request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Is there a form bean associated with this ActionConfig?
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
        String name = actionConfig.getName();
        if (name == null) {
            context.remove(getActionFormKey());
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
            context.remove(getActionFormKey());
            return (false);
        }

        // Look up the session scope ActionForm instance (if any)
        WebContext wcontext = (WebContext) context;
        ActionForm instance = null;
        if ("session".equals(actionConfig.getScope())) {
            instance = (ActionForm)
                wcontext.getSessionScope().get(actionConfig.getAttribute());
        }

        // Can we recycle the existing instance (if any)?
        if (instance != null) {
            log.trace("Found an instance in the session; test for reusability");
            if (formBeanConfig.getDynamic()) {
                String className =
                    ((DynaBean) instance).getDynaClass().getName();
                if (className.equals(formBeanConfig.getName())) {
                    wcontext.put
                        (getActionFormKey(), instance);
                    /* It should already be in session scope
                    if ("session".equals(actionConfig.getScope())) {
                        wcontext.getSessionScope().put
                            (actionConfig.getAttribute(), instance);
                    }
                    */
                    log.debug("Using existing instance (dynamic)");
                    return (false);
                }
            } else {
                try {
                    Class configClass =
                        ClassUtils.getApplicationClass
                        (formBeanConfig.getType());
                    if (configClass.isAssignableFrom(instance.getClass())) {
                        wcontext.put
                            (getActionFormKey(), instance);
                        /* It should already be in session scope
                           if ("session".equals(actionConfig.getScope())) {
                           wcontext.getSessionScope().put
                           (actionConfig.getAttribute(), instance);
                           }
                        */
                        log.debug("Using existing instance (non-dynamic)");
                        return (false);
                    }
                } catch (Exception e) {
                    log.debug("Error testing existing instance for reusability; just create a new instance", e);
                }
            }
        }

        log.trace("Make a new instance of: " + formBeanConfig);
        // Create a new form bean instance
        if (formBeanConfig.getDynamic()) {
            ModuleConfig moduleConfig = (ModuleConfig)
                wcontext.get(getModuleConfigKey());
            DynaActionFormClass dynaClass =
                DynaActionFormClass.createDynaActionFormClass(formBeanConfig, moduleConfig);
            instance = (ActionForm) dynaClass.newInstance();
            ((DynaActionForm) instance).initialize
                ((ActionMapping) actionConfig);
        } else {
            instance = (ActionForm)
                ClassUtils.getApplicationInstance(formBeanConfig.getType());
        }

        // Configure and cache the new instance
        ActionServlet servlet = (ActionServlet)
            wcontext.get(getActionServletKey());
        instance.setServlet(servlet);
        wcontext.put(getActionFormKey(), instance);
        if ("session".equals(actionConfig.getScope())) {
            wcontext.getSessionScope().put
                (actionConfig.getAttribute(), instance);
        } else if ("request".equals(actionConfig.getScope())) {
            wcontext.getRequestScope().put
                (actionConfig.getAttribute(), instance);
        }

        return (false);

    }


}
