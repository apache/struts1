/*
 * $Id$ 
 *
 * Copyright 2001-2004 The Apache Software Foundation.
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
package org.apache.struts.scaffold;


import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import org.apache.commons.scaffold.lang.Log;
import org.apache.commons.scaffold.lang.Tokens;
import org.apache.commons.scaffold.util.ProcessBean;
import org.apache.commons.scaffold.util.ProcessResult;
import org.apache.commons.scaffold.util.ProcessResultBase;
import org.apache.commons.scaffold.util.ResultList;
import org.apache.commons.scaffold.util.ResultListBase;


/**
 * ProcessFormAction that accepts the name of a class and a method
 * with the signature "Object method(Object)" as the parameter.
 * [org.apache.cerebus.Account;store].
 *
 * @version $Rev$ $Date$
 */
public class ProcessDispatchAction extends ProcessFormAction {

    /**
     * The set of argument type classes for the reflected method call.
     * These are the same for all calls, so calculate them only once.
     */
    private static final Class types[] = { Object.class };


    /**
     * Error handler.
     * Posts a message template and two parameters in a ProcessResult.
     */
    public void processError(ProcessResult result, String template,
        ActionMapping mapping) {
        result = new ProcessResultBase(this);
        result.addMessage(template);
        result.addMessage(mapping.getPath());
        result.addMessage(mapping.getParameter());
    }


// --------------------------------------------------------- Public Methods

    /**
     * Instantiate helper object from the type given as the
     * first ActionMapping parameter and execute the method given
     * as the second parameter (delimited with semicolons).
     * <p>
     * Otherwise operates as a ProcessAction.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * <p>
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     * @todo Refactor so both Process*Actions can call core logic
     * @todo Add support for mutliple object/methods
     */
    public void executeLogic(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
        throws Exception {

            // Retrieve user profile, if any
        BaseForm userBean =
            getUserProfile(mapping,form,request,response);

        servlet.log(Log.HELPER_PROCESSING,Log.DEBUG);
        Map properties = null;

            // Munge the parameter property
        servlet.log(Log.TOKENS_PARSING,Log.DEBUG);
        String[] tokens = tokenize(mapping.getParameter());
               
            // Create our ProcessBean helper
        Object helper = createHelperObject(request,tokens[0]);
        servlet.log(Log.HELPER_EXECUTING,Log.DEBUG);
        ProcessBean dataBean = (ProcessBean) helper;
        
            // Pass along the helper's parameter, if any
        if (tokens.length>2) {
            dataBean.setParameter(tokens[2]);
        }

        properties = null;
        if (null!=form) {
            if (form instanceof BaseForm) {

                BaseForm formBean = (BaseForm) form;

                    // Merge user profile (if found)
                    // and our form into a single map
                servlet.log(Log.HELPER_POPULATE,Log.DEBUG);
                properties = formBean.merge(userBean);

                    // Pass up the Locale and RemoteServer (if any)
                dataBean.setLocale(formBean.getSessionLocale());
                dataBean.setRemoteServer(getRemoteServer());
            }
            else {
                properties = PropertyUtils.describe(form);
            }
        } // end null form
        else if (null!=userBean) {
                // if no form, but is profile, still use profile
            properties = PropertyUtils.describe(userBean);
        }

            // Execute business logic, using values from  map
        servlet.log(Log.HELPER_EXECUTING,Log.DEBUG);

        Method method = dataBean.getClass().getMethod(tokens[1],types);
        Object args[] = { properties };

        ProcessResult result = null;

        try {

            result = (ProcessResult) method.invoke(dataBean,args);

        }

        catch (ClassCastException e) {

            processError(result,Tokens.ERROR_DISPATCH_RETURN,mapping);
        }

        catch (IllegalAccessException e) {

            processError(result,Tokens.ERROR_DISPATCH_RETURN,mapping);
        }

        catch (InvocationTargetException e) {

            // Rethrow the target exception if possible so that the
            // exception handling machinery can deal with it
            Throwable t = e.getTargetException();
            if (t instanceof Exception) {
                throw ((Exception) t);
            } else {
                processError(result,Tokens.ERROR_DISPATCH,mapping);
            }
        }

        // Execute business logic, using  map
        checkOutcome(mapping,request,response,result);
     }

} // end ProcessDispatchAction