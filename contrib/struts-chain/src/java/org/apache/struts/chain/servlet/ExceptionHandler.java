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

package org.apache.struts.chain.servlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.chain.AbstractExceptionHandler;
import org.apache.struts.chain.Constants;
import org.apache.struts.chain.util.ClassUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Handle the specified exception.</p>
 *
 * @version $Rev$ $Date$
 */

public class ExceptionHandler extends AbstractExceptionHandler {


    // ------------------------------------------------------ Instance Variables


    private String actionFormKey = Constants.ACTION_FORM_KEY;

    private static final Log log = LogFactory.getLog(ExceptionHandler.class);


    // -------------------------------------------------------------- Properties


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


    // ------------------------------------------------------- Protected Methods


    protected ForwardConfig handle(Context context,
                                   Exception exception,
                                   ExceptionConfig exceptionConfig,
                                   ActionConfig actionConfig,
                                   ModuleConfig moduleConfig)
        throws Exception {

        // Look up the remaining properties needed for this handler
        ServletWebContext swcontext = (ServletWebContext) context;
        ActionForm actionForm = (ActionForm)
            swcontext.get(getActionFormKey());
        HttpServletRequest request = swcontext.getRequest();
        HttpServletResponse response = swcontext.getResponse();

        // Handle this exception
        org.apache.struts.action.ExceptionHandler handler =
            (org.apache.struts.action.ExceptionHandler)
            ClassUtils.getApplicationInstance(exceptionConfig.getHandler());
        return (handler.execute(exception,
                                exceptionConfig,
                                (ActionMapping) actionConfig,
                                actionForm,
                                request,
                                response));

    }


}
