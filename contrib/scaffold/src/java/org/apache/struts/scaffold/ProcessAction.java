package org.apache.struts.scaffold;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.scaffold.lang.Log;
import org.apache.commons.scaffold.lang.Tokens;
import org.apache.commons.scaffold.util.ProcessBean;
import org.apache.commons.scaffold.util.ProcessResult;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


/**
 * Advanced framework class to instantiate and execute helper beans
 * and process the result.
 *
 * @author Ted Husted
 * @author  Synthis Corporation.
 * @author OK State DEQ
 * @version $Revision: 1.9 $ $Date: 2003/01/02 19:43:27 $
 * @deprecated Use BizAction instead
 */
public class ProcessAction extends BaseHelperAction {


// --------------------------------------------------------- Public Methods


    /**
     * Exposes result in a servlet context.
     *
     * @param request the request being serviced
     * @param name The name to use in scope
     * @param scope The scope to set the attribute in
     * @param bean The attribute to be set
     */
    protected void exposeInScope(
            HttpServletRequest request,
            String name,
            String scope,
            Object bean) {

        if (null==scope) {
            servlet.log(Log.PROCESS_BEAN_NULL_SCOPE,Log.DEBUG);
        }
        else if (Tokens.REQUEST.equals(scope)) {
            request.setAttribute(name,bean);
        }
        else if (Tokens.SESSION.equals(scope)) {
            request.getSession().setAttribute(name,bean);
        }
        else if (Tokens.APPLICATION.equals(scope)) {
            servlet.getServletContext().setAttribute(name,bean);
        }
        else {
            StringBuffer sb = new StringBuffer("exposeInScope: ");
            sb.append(scope);
            sb.append(Tokens.INVALID_SCOPE);
            servlet.log(sb.toString(),Log.DEBUG);
            throw new IllegalArgumentException(sb.toString());
        }

    } // end exposeInScope


    /**
     * Save result object to servlet context.
     * <p>
     * <code>result.getData()</code> must return non-null.
     * If <code>result.getName()</code> is null, the mapping's attribute
     * (<code>mapping.getAttribute()</code>) is used instead.
     * By default, this is the <code>form-bean</code>'s name.
     * <p>
     * If data is a Collection, only the first element is stored.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param result The ProcessResult we are handling
     */
    protected void checkDataSingle(
            ActionMapping mapping,
            HttpServletRequest request,
            ProcessResult result) {

        String name = result.getName();
        if (null==name) {
                // use form-bean or mapping name
            name = mapping.getAttribute();
            result.setName(name);
        }
        String scope = result.getScope();
        Object bean = result.getData();

            // if data is collection, use first element
        if (bean instanceof Collection) {
            Collection collection = (Collection) bean;
            if (collection.isEmpty()) {
                    // for lack of a better idea, get a fresh form-bean
                    // this will return null if there is not a form-bean
                    // associated with this mapping
                bean = createHelperBean(request,mapping.getName());
            }
            else {
                bean = collection.iterator().next();
            }
        }
        if (result.isExposed()) {
            exposeInScope(request,name,scope,bean);

        }

    } // end checkDataSingle


    /**
     * Save result object to servlet context.
     * <p>
     * <code>result.getData()</code> must return non-null.
     * If <code>result.getName()</code> is null, the mapping's attribute
     * (<code>mapping.getAttribute()</code>) is used instead.
     * By default, this is the <code>form-bean</code>'s name.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param result The ProcessResult we are handling
     */
    protected void checkData(
            ActionMapping mapping,
            HttpServletRequest request,
            ProcessResult result) {

        if (result.isSingleForm()) {
            checkDataSingle(mapping,request,result);
        }
        else {

            String name = result.getName();
            if (null==name) {
                name = Tokens.LIST_KEY;
                result.setName(name);
            }
            String scope = result.getScope();
            Object bean = result.getData();

            if (result.isExposed()) {
                exposeInScope(request,name,scope,bean);
            }
        }

    } // end checkData


    /**
     * Stores informational messages for display.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param processResult The ProcessResult we are handling
     */
    protected void checkMessages(
            ActionMapping mapping,
            HttpServletRequest request,
            ProcessResult processResult) {

        saveMessages(request,processResult.getMessages());

    } // end checkMessages


    /**
     * Process new dispatch advice passed by the business tier.
     * <p>
     * This is used to route control to another location besides
     * the default "success" forward registered with the controller.
     *
     * The business tier can pass back either a path or the name of
     * an ActionForward.
     * checkDispatch() will then create an ActionForward to return
     * and save it in the request under the SUCCESS token.
     * The <code>findSuccess()</code> will check for this attribute
     * before returning the controller's default.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param processResult The ProcessResult we are handling
     */
    protected void checkDispatch(
            ActionMapping mapping,
            HttpServletRequest request,
            ProcessResult processResult) {

        String dispatch = processResult.getDispatch();
        ActionForward forward = null;

        if (processResult.isDispatchPath()) {
            forward = new ActionForward(dispatch);
        }
        else {
            forward = mapping.findForward(dispatch);
        }

            // Our findSuccess looks for this
        request.setAttribute(Tokens.SUCCESS,forward);

    } // end checkDispatch


// --------------------------------------------------------- Public Methods


     /**
      * Return the appropriate ActionForward for error or failure
      * conditions.
      * First checks for a FAILURE ActionForward stored in the request.
      * If an override is not found, returns the result of the
      * superclass method.
      *
      * @param mapping The ActionMapping used to select this instance
      * @param form The optional ActionForm bean for this request
      * @param request The HTTP request we are processing
      * @param response The resonse we are creating
      * @return The ActionForward representing FAILURE
      * or null if a FAILURE forward has not been specified.
      */
     protected ActionForward findFailure(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

            // Did someone leave us a forward?
        ActionForward forward = (ActionForward)
            request.getAttribute(Tokens.FAILURE);

        if (null==forward) {
                // No override, use default
            forward = super.findFailure(mapping,form,request,response);
        }
        else {
                // Clear advice from the request
            request.setAttribute(Tokens.FAILURE,null);
        }

        return forward;

    } // end findFailure


   /**
     * Optional extension point for pre-processing.
     * Default method does nothing.
     * To branch to another URI, return an non-null ActionForward.
     * If errors are logged (getErrors() et al),
     * default behaviour will branch to findFailure().
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The resonse we are creating
     */
    protected void preProcess(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

            // Check for cancelled
        ActionForward forward = mapping.findForward(Tokens.CANCEL);
        if ((null!=forward) && (isCancelled(request))) {
            // Our findFailure looks for this
            request.setAttribute(Tokens.FAILURE,forward);
           // Post cancel error message
           ActionErrors errors = getErrors(request,true);
           errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError(Tokens.ERROR_CANCEL));
            return;
         }

            // Check for missing token
        forward = mapping.findForward(Tokens.GET_TOKEN);
        if ((null!=forward) && (!isTokenValid(request))) {
            // Our findFailure looks for this
            request.setAttribute(Tokens.FAILURE,forward);
           // Post token error message
           ActionErrors errors = getErrors(request,true);
           errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError(Tokens.ERROR_TOKEN));
            return;
        }
        if (null!=forward) {
            // reset to guard against duplicate request
            resetToken(request);
        }

          // Check for save token directive (do this last)
        forward = mapping.findForward(Tokens.SET_TOKEN);
        if (null!=forward) saveToken(request);


     } // end preProcess


    /**
     * Check outcome, if any; recurse if container for other outcomes.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param result The ProcessResult we are handling
     */
    protected void checkOutcome(
            ActionMapping mapping,
            HttpServletRequest request,
            ProcessResult result) throws Exception {

        if (result!=null) {

            servlet.log(Log.HELPER_OUTCOME,Log.DEBUG);

            if (result.isAggregate()) {
                    // recurse for each ProcessResult in collection
                Collection collection = (Collection)
                    result.getData();
                Iterator iterator = collection.iterator();
                while (iterator.hasNext()) {
                    ProcessResult nextResult =
                        (ProcessResult) iterator.next();
                    checkOutcome(mapping,request,nextResult);
                }
            }

            else {
                    // call extension points for whatever is returned
                if (result.isData())
                    checkData(mapping,request,result);

                if (result.isMessages())
                    checkMessages(mapping,request,result);

                if (result.isDispatch())
                    checkDispatch(mapping,request,result);
            }
        }

        else {
            throw new Exception(Log.PROCESS_RESULT_NULL);
        }

    } // end checkOutcome


    /**
     * Return the appropriate ActionForward for the nominal,
     * non-error state.
     * First checks for a SUCCESS ActionForward stored in the request.
     * If an override is not found, returns the result of the
     * superclass method.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The response we are creating
     * @return The ActionForward representing SUCCESS
     * or null if a SUCCESS forward has not been specified.
     */
    protected ActionForward findSuccess(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

            // Did someone leave us a forward?
        ActionForward forward = (ActionForward)
            request.getAttribute(Tokens.SUCCESS);

        if (null==forward) {
                // No override, use default
            forward = super.findSuccess(mapping,form,request,response);
        }
        else {
                // Clear advice from the request
            request.setAttribute(Tokens.SUCCESS,null);
        }

        return forward;

    } // end findSuccess


    /**
     * Retrieve from session under known key
     * (<code>ProcessBean.USER_PROFILE_KEY</code>).
     * Override this approach to implement another method (e.g cookies).
     * Also revise UpdateProfile action-mapping to store changes.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected BaseForm getUserProfile(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        return(BaseForm)
            request.getSession().getAttribute(ProcessBean.USER_PROFILE_KEY);

    } // end getUserProfile


    /**
     * Assume that helpers are ProcessBeans, execute each, and
     * process outcome.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @param helpers The object instantiated from type given as parameter.
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected void executeLogic(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response,
            Object[] helpers) throws Exception {

            // Retrieve user profile, if any
        BaseForm userBean =
            getUserProfile(mapping,form,request,response);

        servlet.log(Log.HELPER_PROCESSING,Log.DEBUG);
        Map properties = null;
        for (int i = 0; i < helpers.length; i++) {

                // Get helper instantiated by ancestor
            ProcessBean dataBean = (ProcessBean) helpers[i];

            properties = null;
            if (null!=form) {
                if (form instanceof BaseForm) {

                    BaseForm formBean = (BaseForm) form;

                        // Merge user profile (if found)
                        // and our form into a single map
                    servlet.log(Log.HELPER_POPULATE,Log.DEBUG);
                    properties = formBean.merge(userBean);

                        // Pass up the Locale, RemoteNode, and RemoteServer (if any)
                    dataBean.setLocale(formBean.getSessionLocale());
                    dataBean.setRemoteNode(getRemoteNode(request));
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
            ProcessResult result = (ProcessResult)
                dataBean.execute(properties);

                // Analyze result of business logic
            checkOutcome(mapping,request,result);

        } // end for

    } // end executeLogic

} // end ProcessAction


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


