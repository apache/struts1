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
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @author OK State DEQ
 * @version $Revision: 1.9 $ $Date: 2003/01/15 10:19:26 $
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


/*
 *
 *    Copyright (c) 2002 Synthis Corporation.
 *    430 10th Street NW, Suite S-108, Atlanta GA 30318, U.S.A.
 *    All rights reserved.
 *
 *    This software is licensed to you free of charge under
 *    the Apache Software License, so long as this copyright
 *    statement, list of conditions, and comments,  remains
 *    in the source code.  See bottom of file for more
 *    license information.
 *
 *    This software was written to support code generation
 *    for the Apache Struts J2EE architecture by Synthis'
 *    visual application modeling tool Adalon.
 *
 *    For more information on Adalon and Struts code
 *    generation please visit http://www.synthis.com
 *
 */


 /*
  * ====================================================================
  *
  * The Apache Software License, Version 1.1
  *
  * Copyright (c) 2001 The Apache Software Foundation.  All rights
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
  * 4. The names "The Jakarta Project", "Scaffold", and "Apache Software
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


