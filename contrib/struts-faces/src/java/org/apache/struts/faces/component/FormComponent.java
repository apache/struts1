/*
 * Copyright 2002-2004 The Apache Software Foundation.
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

package org.apache.struts.faces.component;


import java.util.Map;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;


/**
 * <p><strong>FormComponent</strong> is a specialized subclass of
 * <code>javax.faces.component.UIForm</code> that supports automatic
 * creation of form beans in request or session scope.</p>
 *
 * @version $Revision: 1.9 $ $Date: 2004/06/09 02:28:28 $
 */
public class FormComponent extends UIForm {


    // -------------------------------------------------------- Static Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    protected static Log log = LogFactory.getLog(FormComponent.class);


    // ---------------------------------------------------- Component Properties


    private String action = null;


    /**
     * <p>Return the Struts action path to which this form should be submitted.
     * </p>
     */
    public String getAction() {

        if (this.action != null) {
            return (this.action);
        }
        ValueBinding vb = getValueBinding("action");
        if (vb != null) {
            return ((String) vb.getValue(getFacesContext()));
        } else {
            return (null);
        }

    }


    /**
     * <p>Set the Struts action to which this form should be submitted.</p>
     *
     * @param action The new action path
     */
    public void setAction(String action) {

        this.action = action;

    }


    // ---------------------------------------------------------- UIForm Methods


    /**
     * <p>Create an instance of the form bean (if necessary) before
     * delegating to the standard decoding process.</p>
     *
     * @param context FacesContext for the request we are processing
     */
    public void processDecodes(FacesContext context) {

        if (context == null) {
            throw new NullPointerException();
        }

        if (log.isDebugEnabled()) {
            log.debug("processDecodes(" + getClientId(context) + ")");
        }

        // Create the form bean (if necessary)
        Map params = context.getExternalContext().getRequestParameterMap();
        if (params.containsKey(getClientId(context))) {
            createActionForm(context);
        }

        // Perform the standard decode processing
        super.processDecodes(context);

    }


    /**
     * <p>Restore our state from the specified object.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param state Object containing our saved state
     */
    public void restoreState(FacesContext context, Object state) {

        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        action = (String) values[1];

    }


    /**
     * <p>Create and return an object representing our state to be saved.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     */
    public Object saveState(FacesContext context) {

        Object values[] = new Object[2];
        values[0] = super.saveState(context);
        values[1] = action;
        return (values);

    }



    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Create an appropriate form bean in the appropriate scope, if one
     * does not already exist.</p>
     *
     * @param context FacesContext for the current request
     *
     * @exception IllegalArgumentException if no ActionConfig for the
     *  specified action attribute can be located
     * @exception IllegalArgumentException if no FormBeanConfig for the
     *  specified form bean can be located
     * @exception IllegalArgumentException if no ModuleConfig can be
     *  located for this application module
     */
    public void createActionForm(FacesContext context) {

        // Look up the application module configuration information we need
        ModuleConfig moduleConfig = lookupModuleConfig(context);

        // Look up the ActionConfig we are processing
        String action = getAction();
        ActionConfig actionConfig = moduleConfig.findActionConfig(action);
        if (actionConfig == null) {
            throw new IllegalArgumentException("Cannot find action '" +
                                               action + "' configuration");
        }

        // Does this ActionConfig specify a form bean?
        String name = actionConfig.getName();
        if (name == null) {
            return;
        }

        // Look up the FormBeanConfig we are processing
        FormBeanConfig fbConfig = moduleConfig.findFormBeanConfig(name);
        if (fbConfig == null) {
            throw new IllegalArgumentException("Cannot find form bean '" +
                                               name + "' configuration");
        }

        // Does a usable form bean attribute already exist?
        String attribute = actionConfig.getAttribute();
        String scope = actionConfig.getScope();
        ActionForm instance = null;
        if ("request".equals(scope)) {
            instance = (ActionForm)
                context.getExternalContext().getRequestMap().get(attribute);
        } else if ("session".equals(scope)) {
            HttpSession session = (HttpSession)
                context.getExternalContext().getSession(true);
            instance = (ActionForm)
                context.getExternalContext().getSessionMap().get(attribute);
        }
        if (instance != null) {
            if (fbConfig.getDynamic()) {
                String className =
                    ((DynaBean) instance).getDynaClass().getName();
                if (className.equals(fbConfig.getName())) {
                    if (log.isDebugEnabled()) {
                        log.debug
                            (" Recycling existing DynaActionForm instance " +
                             "of type '" + className + "'");
                    }
                    return;
                }
            } else {
                try {
                    Class configClass =
                        RequestUtils.applicationClass(fbConfig.getType());
                    if (configClass.isAssignableFrom(instance.getClass())) {
                        if (log.isDebugEnabled()) {
                            log.debug
                                (" Recycling existing ActionForm instance " +
                                 "of class '" + instance.getClass().getName()
                                 + "'");
                        }
                        return;
                    }
                } catch (Throwable t) {
                    throw new IllegalArgumentException
                        ("Cannot load form bean class '" +
                         fbConfig.getType() + "'");
                }
            }
        }

        // Create a new form bean instance
        if (fbConfig.getDynamic()) {
            try {
                DynaActionFormClass dynaClass =
                    DynaActionFormClass.createDynaActionFormClass(fbConfig);
                instance = (ActionForm) dynaClass.newInstance();
                if (log.isDebugEnabled()) {
                    log.debug
                        (" Creating new DynaActionForm instance " +
                         "of type '" + fbConfig.getType() + "'");
                    log.trace(" --> " + instance);
                }
            } catch (Throwable t) {
                throw new IllegalArgumentException
                    ("Cannot create form bean of type '" +
                     fbConfig.getType() + "'");
            }
        } else {
            try {
                instance = (ActionForm)
                    RequestUtils.applicationInstance(fbConfig.getType());
                if (log.isDebugEnabled()) {
                    log.debug
                        (" Creating new ActionForm instance " +
                         "of type '" + fbConfig.getType() + "'");
                    log.trace(" --> " + instance);
                }
            } catch (Throwable t) {
                throw new IllegalArgumentException
                    ("Cannot create form bean of class '" +
                     fbConfig.getType() + "'");
            }
        }

        // Configure and cache the form bean instance in the correct scope
        ActionServlet servlet = (ActionServlet)
            context.getExternalContext().getApplicationMap().get
            (Globals.ACTION_SERVLET_KEY);
        instance.setServlet(servlet);
        if ("request".equals(scope)) {
            context.getExternalContext().getRequestMap().put
                (attribute, instance);
        } else if ("session".equals(scope)) {
            context.getExternalContext().getSessionMap().put
                (attribute, instance);
        }

    }


    /**
     * <p>Return the <code>ModuleConfig</code> for the application module
     * this form is being processed for.</p>
     *
     * @param context The <code>FacesContext</code> for the current request
     *
     * @exception IllegalArgumentException if no <code>ModuleConfig</code>
     *  can be found
     */
    public ModuleConfig lookupModuleConfig(FacesContext context) {

        // Look up the application module configuration information we need
        ModuleConfig modConfig = (ModuleConfig)
            context.getExternalContext().getRequestMap().get
            (Globals.MODULE_KEY);
        if (modConfig == null) {
            modConfig = (ModuleConfig)
                context.getExternalContext().getApplicationMap().get
                (Globals.MODULE_KEY);
        }
        if (modConfig == null) {
            throw new IllegalArgumentException
                ("Cannot find module configuration");
        }
        return (modConfig);

    }


}
