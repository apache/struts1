/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/AbstractPopulateActionForm.java,v 1.3 2004/02/02 13:53:21 germuska Exp $
 * $Revision: 1.3 $
 * $Date: 2004/02/02 13:53:21 $
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
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.WebContext;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ActionConfig;


/**
 * <p>Populate the form bean (if any) for this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2004/02/02 13:53:21 $
 */

public abstract class AbstractPopulateActionForm implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String actionFormKey = Constants.ACTION_FORM_KEY;
    private String cancelKey = Constants.CANCEL_KEY;


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


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Populate the form bean (if any) for this request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Is there a form bean for this request?
        ActionForm actionForm = (ActionForm)
            context.get(getActionFormKey());
        if (actionForm == null) {
            return (false);
        }

        // Reset the form bean property values
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());


        reset(context, actionConfig, actionForm);

        populate(context, actionConfig, actionForm);

        handleCancel(context, actionConfig, actionForm);

        return (false);

    }


    // ------------------------------------------------------- Protected Methods

    /**
     * <p>Call the <code>reset()</code> method on the specified form bean.</p>
     *
     * @param context The context for this reqest
     * @param actionConfing The actionConfig for this request
     * @param actionForm The form bean for this request
     */
    protected abstract void reset(Context context,
                                  ActionConfig actionConfig,
                                  ActionForm actionForm);


    /**
     * <p>Base implementation assumes that the <code>Context</code>
     * can be cast to <code>WebContext</code> and copies the parameter
     * values from the context to the <code>ActionForm</code>.</p>
     *
     * <p>Note that this implementation does not handle "file uploads"
     * because as far as I know there is no API for handling that without
     * committing to servlets -- in a servlet environment, use
     * <code>org.apache.struts.chain.servlet.PopulateActionForm</code>.</p>
     *
     * @param context
     * @param actionConfig
     * @param actionForm
     * @throws Exception
     */
    protected void populate(Context context,
                            ActionConfig actionConfig,
                            ActionForm actionForm) throws Exception
    {
        WebContext wcontext = (WebContext) context;
        Map paramValues = wcontext.getParamValues();
        Map parameters = new HashMap();

        String prefix = actionConfig.getPrefix();
        String suffix = actionConfig.getSuffix();

        Iterator keys = paramValues.keySet().iterator();
        while (keys.hasNext()) {
            String name = (String) keys.next();
            String stripped = name;
            if (prefix != null) {
                if (!stripped.startsWith(prefix)) {
                    continue;
                }
                stripped = stripped.substring(prefix.length());
            }
            if (suffix != null) {
                if (!stripped.endsWith(suffix)) {
                    continue;
                }
                stripped =
                        stripped.substring(0, stripped.length() - suffix.length());
            }
            parameters.put(stripped, paramValues.get(name));
        }
        BeanUtils.populate(actionForm, parameters);
    }


    protected void handleCancel(Context context,
                                ActionConfig actionConfig,
                                ActionForm actionForm) throws Exception
    {
        WebContext wcontext = (WebContext) context;
        Map paramValues = wcontext.getParamValues();

        // Set the cancellation attribute if appropriate
        if ((paramValues.get(org.apache.struts.taglib.html.Constants.CANCEL_PROPERTY) != null) ||
            (paramValues.get(org.apache.struts.taglib.html.Constants.CANCEL_PROPERTY_X) != null)) {
            context.put(getCancelKey(), Boolean.TRUE);
            wcontext.getRequestScope().put(Globals.CANCEL_KEY, Boolean.TRUE);
        } else {
            context.put(getCancelKey(), Boolean.FALSE);
        }
    }
}
