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


import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;


/**
 * <p>Validate the properties of the form bean for this request.  If there are
 * any validation errors, execute the specified command; otherwise,
 * proceed normally.</p>
 *
 * @author Craig R. McClanahan
 * @version $Rev$ $Date$
 */

public abstract class AbstractValidateActionForm implements Command {


    // ------------------------------------------------------ Instance Variables
    private static final Log log =
        LogFactory.getLog(AbstractValidateActionForm.class);

    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Validate the properties of the form bean for this request.  If
     * there are any validation errors, execute the child commands in our
     * chain; otherwise, proceed normally.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues, if there are
     *  no validation errors; otherwise <code>true</code>
     */
    public boolean execute(Context context) throws Exception {
        ActionContext actionCtx = (ActionContext) context;

        // Is there a form bean for this request?
        ActionForm actionForm = actionCtx.getActionForm();
        if (actionForm == null) {
            actionCtx.setFormValid(Boolean.TRUE);
            return (false);
        }

        // Was this request cancelled?
        Boolean cancel = actionCtx.getCancelled();
        if ((cancel != null) && cancel.booleanValue()) {
            actionCtx.setFormValid(Boolean.TRUE);
            return (false);
        }

        // Is validation disabled on this request?
        ActionConfig actionConfig = actionCtx.getActionConfig();
        if (!actionConfig.getValidate()) {
            actionCtx.setFormValid(Boolean.TRUE);
            return (false);
        }

        // Call the validate() method of this form bean
        ActionErrors errors = validate(actionCtx, actionConfig, actionForm);

        // If there were no errors, proceed normally
        if ((errors == null) || (errors.isEmpty())) {
            actionCtx.setFormValid(Boolean.TRUE);
            return (false);
        }

        // Flag the validation failure and proceed
        /** @todo Is there any concern that there might have already
         * been errors, or that other errors might be coming? */
        actionCtx.saveErrors(errors);
        actionCtx.setFormValid(Boolean.FALSE);
        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Call the <code>validate()</code> method of the specified form bean,
     * and return the resulting <code>ActionErrors</code> object.</p>
     *
     * @param context The context for this request
     * @param actionConfig The <code>ActionConfig</code> for this request
     * @param actionForm The form bean for this request
     */
    protected abstract ActionErrors validate(ActionContext context,
                                             ActionConfig actionConfig,
                                             ActionForm actionForm);


}
