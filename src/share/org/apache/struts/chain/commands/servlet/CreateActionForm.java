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

package org.apache.struts.chain.commands.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.chain.commands.util.ClassUtils;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;

public class CreateActionForm extends
        org.apache.struts.chain.commands.AbstractCreateActionForm {

    // ------------------------------------------------------ Instance Variables
    private static final Log log =
        LogFactory.getLog(CreateActionForm.class);


    /* Implement servlet-specific details of creating a new form instance.
     * @see org.apache.struts.chain.commands.CreateActionForm#createNewFormInstance(org.apache.struts.chain.contexts.ActionContext, org.apache.struts.config.ActionConfig, org.apache.struts.config.FormBeanConfig)
     */
    protected ActionForm createNewFormInstance(ActionContext actionCtx,
            ActionConfig actionConfig, FormBeanConfig formBeanConfig)
            throws Exception {
        ActionForm instance;
        log.trace("Make a new instance of: " + formBeanConfig);
        // Create a new form bean instance
        if (formBeanConfig.getDynamic()) {
            ModuleConfig moduleConfig = actionCtx.getModuleConfig();
            DynaActionFormClass dynaClass =
                DynaActionFormClass.createDynaActionFormClass(formBeanConfig);
            instance = (ActionForm) dynaClass.newInstance();
            ((DynaActionForm) instance).initialize
                ((ActionMapping) actionConfig);
        } else {
            instance = (ActionForm)
                ClassUtils.getApplicationInstance(formBeanConfig.getType());
        }

        // Configure and cache the new instance
        /** @todo Move this dependency on the Servlet API into a subclass and make this one abstract. 
         * Then again, ActionForm itself depends on the Servlet API.
         * */
        ServletActionContext saContext = (ServletActionContext) actionCtx;
        ActionServlet servlet = saContext.getActionServlet();
        instance.setServlet(servlet);
        actionCtx.setActionForm(instance);

        return instance;
    }

}
