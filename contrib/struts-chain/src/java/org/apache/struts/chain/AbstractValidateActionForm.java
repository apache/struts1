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


import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.config.ActionConfig;


/**
 * <p>Validate the properties of the form bean for this request.  If there are
 * any validation errors, execute the specified command; otherwise,
 * proceed normally.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.6 $ $Date: 2004/04/29 03:08:44 $
 */

public abstract class AbstractValidateActionForm implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String actionFormKey = Constants.ACTION_FORM_KEY;
    private String cancelKey = Constants.CANCEL_KEY;
    private String catalogKey = Constants.CATALOG_KEY;
    private String validKey = Constants.VALID_KEY;

    private static final Log log =
        LogFactory.getLog(AbstractValidateActionForm.class);


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
     * cancellation flag for this request is stored.</p>
     */
    public String getCancelKey() {

        return (this.cancelKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * cancellation flag for this request is stored.</p>
     *
     * @param cancelKey The new context attribute key
     */
    public void setCancelKey(String cancelKey) {

        this.cancelKey = cancelKey;

    }


    /**
     * <p>Return the context attribute key under which the
     * <code>Catalog</code> we perform lookups in is stored.</p>
     */
    public String getCatalogKey() {

        return (this.catalogKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>Catalog</code> we perform lookups in is stored.</p>
     *
     * @param catalogKey The new context attribute key
     */
    public void setCatalogKey(String catalogKey) {

        this.catalogKey = catalogKey;

    }


    /**
     * <p>Return the context attribute key under which the
     * validity flag for this request is stored.</p>
     */
    public String getValidKey() {

        return (this.validKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * validity flag for this request is stored.</p>
     *
     * @param validKey The new context attribute key
     */
    public void setValidKey(String validKey) {

        this.validKey = validKey;

    }


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

        // Is there a form bean for this request?
        ActionForm actionForm = (ActionForm)
            context.get(getActionFormKey());
        if (actionForm == null) {
            context.put(getValidKey(), Boolean.TRUE);
            return (false);
        }

        // Was this request cancelled?
        Boolean cancel = (Boolean)
            context.get(getCancelKey());
        if ((cancel != null) && cancel.booleanValue()) {
            context.put(getValidKey(), Boolean.TRUE);
            return (false);
        }

        // Is validation disabled on this request?
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
        if (!actionConfig.getValidate()) {
            context.put(getValidKey(), Boolean.TRUE);
            return (false);
        }

        // Call the validate() method of this form bean
        ActionErrors errors = validate(context, actionConfig, actionForm);

        // If there were no errors, proceed normally
        if ((errors == null) || (errors.isEmpty())) {
            context.put(getValidKey(), Boolean.TRUE);
            return (false);
        }

        // Flag the validation failure and proceed
        context.put(getValidKey(), Boolean.FALSE);
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
    protected abstract ActionErrors validate(Context context,
                                             ActionConfig actionConfig,
                                             ActionForm actionForm);


}
