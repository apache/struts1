/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/CreateActionForm.java,v 1.3 2004/01/31 14:13:40 germuska Exp $
 * $Revision: 1.3 $
 * $Date: 2004/01/31 14:13:40 $
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Create (if necessary) and cache a form bean for this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2004/01/31 14:13:40 $
 */

public class CreateActionForm implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;
    private String actionFormKey = Constants.ACTION_FORM_KEY;
    private String actionServletKey = Constants.ACTION_SERVLET_KEY;

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
