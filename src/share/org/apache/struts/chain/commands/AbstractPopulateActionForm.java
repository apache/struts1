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


import java.util.Map;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;


/**
 * <p>Populate the form bean (if any) for this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Rev$ $Date$
 */

public abstract class AbstractPopulateActionForm implements Command {


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Populate the form bean (if any) for this request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        ActionContext actionCtx = (ActionContext) context;
        // Is there a form bean for this request?
        ActionForm actionForm = actionCtx.getActionForm();

        if (actionForm == null) {
            return (false);
        }

        // Reset the form bean property values
        ActionConfig actionConfig = actionCtx.getActionConfig();

        reset(actionCtx, actionConfig, actionForm);

        populate(actionCtx, actionConfig, actionForm);

        handleCancel(actionCtx, actionConfig, actionForm);

        return (false);

    }


    // ------------------------------------------------------- Protected Methods

    /**
     * <p>Call the <code>reset()</code> method on the specified form bean.</p>
     *
     * @param context The context for this reqest
     * @param actionConfig The actionConfig for this request
     * @param actionForm The form bean for this request
     */
    protected abstract void reset(ActionContext context,
                                  ActionConfig actionConfig,
                                  ActionForm actionForm);


    /**
     * Populate the given <code>ActionForm</code> with request parameter values,
     * taking into account any prefix/suffix values configured on the given
     * <code>ActionConfig</code>.
     * 
     * @param context
     * @param actionConfig
     * @param actionForm
     * @throws Exception
     */
    // original implementation casting context to WebContext is not safe
    // when the input value is an ActionContext.
    protected abstract void populate(ActionContext context,
                                     ActionConfig actionConfig,
                                     ActionForm actionForm) throws Exception;
    

    /**
     * For a given request parameter name, trim off any prefix and/or suffix which
     * are defined in <code>actionConfig</code> and return what remains.  If 
     * either prefix or suffix is defined, then return null for <code>name</code>
     * values which do not begin or end accordingly.
     * @param actionConfig
     * @param name
     * @return
     */
    protected String trimParameterName(ActionConfig actionConfig, String name) {
        String stripped = name;
        String prefix = actionConfig.getPrefix();
        String suffix = actionConfig.getSuffix();

        if (prefix != null) {
            if (!stripped.startsWith(prefix)) {
                return null;
            }
            stripped = stripped.substring(prefix.length());
        }
        if (suffix != null) {
            if (!stripped.endsWith(suffix)) {
                return null;
            }
            stripped = stripped.substring(0, stripped.length() - suffix.length());
        }

        return stripped;

    }

    /**
     * Take into account whether the request includes any defined value for the global "cancel" parameter.
     * @param context
     * @param actionConfig
     * @param actionForm
     * @throws Exception
     * @see Globals.CANCEL_PROPERTY
     * @see Globals.CANCEL_PROPERTY_X
     */
    protected void handleCancel(ActionContext context, ActionConfig actionConfig, ActionForm actionForm) throws Exception {
        Map paramValues = context.getParameterMap();
    
        // Set the cancellation attribute if appropriate
        /** @todo An issue was raised (but I don't think a Bugzilla ticket created) about
         * the security implications of using a well-known cancel property which skips form validation,
         * as you may not write your actions to deal with the cancellation case. */
        if ((paramValues.get(Globals.CANCEL_PROPERTY) != null) ||
            (paramValues.get(Globals.CANCEL_PROPERTY_X) != null)) {
            context.setCancelled(Boolean.TRUE);
        } else {
            context.setCancelled(Boolean.FALSE);
        }
    }
}
