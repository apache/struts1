/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/java/org/apache/struts/scaffold/BizAction.java,v 1.4 2004/03/14 07:15:03 sraeburn Exp $
 * $Revision: 1.4 $
 * $Date: 2004/03/14 07:15:03 $
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
 
package org.apache.struts.scaffold;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.commons.scaffold.lang.Log;
import org.apache.commons.scaffold.lang.ParameterException;
import org.apache.commons.scaffold.lang.Tokens;
import org.apache.commons.scaffold.util.BizRequest;
import org.apache.commons.scaffold.util.BizResponse;
import org.apache.commons.scaffold.util.BizService;
import org.apache.commons.scaffold.util.Message;
import org.apache.commons.scaffold.util.ResultList;
import org.apache.commons.scaffold.util.ResultListBase;


/**
 * Advanced framework class to invoke a business service
 * and process the response.
 *
 * @version $Revision: 1.4 $ $Date: 2004/03/14 07:15:03 $
 */
public class BizAction extends BaseHelperAction {


    /**
     * Exposes result in a servlet context.
     * If there is an existing bean with same attribute name in the target
     * context,
     * then the matching properties on that bean are populated with the
     * result.
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

        if ((null==scope) || (null==bean)) {
            servlet.log(Log.PROCESS_BEAN_NULL_SCOPE,Log.DEBUG);
            return;
        }
        
        if (Tokens.REQUEST.equals(scope)) {
            Object form = request.getAttribute(name);
            if (null==form) {
                request.setAttribute(name,bean);
            }
            else {
                try {
                    BeanUtils.copyProperties(form,bean);
                }
                catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            }
        }
        else if (Tokens.SESSION.equals(scope)) {
            Object form = request.getSession().getAttribute(name);
            if (null==form) {
                request.getSession().setAttribute(name,bean);
            }
            else {
                try {
                    BeanUtils.copyProperties(form,bean);
                }
                catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            }
        }
        else if (Tokens.APPLICATION.equals(scope)) {
            Object form = servlet.getServletContext().getAttribute(name);
            if (null==form) {
                servlet.getServletContext().setAttribute(name,bean);
            }
            else {
                try {
                    BeanUtils.copyProperties(form,bean);
                }
                catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            }
        }
        else {
            StringBuffer sb = new StringBuffer("exposeInScope: ");
            sb.append(scope);
            sb.append(Tokens.INVALID_SCOPE);
            servlet.log(sb.toString(),Log.DEBUG);
            throw new IllegalArgumentException(sb.toString());
        }

    } // end exposeInScope()


    /**
     * Save the result object within the business response to servlet context.
     * <p>
     * <code>bizResponse.getData()</code> must return non-null.
     * <p>
     * If <code>bizResponse.getName()</code> is null, the mapping's attribute
     * (<code>mapping.getAttribute()</code>) is used instead.
     * By default, this is the <code>form-bean</code>'s name.
     * <p>
     * If data is a Collection, only the first element is stored.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param bizResponse The BizResponse we are handling
     */
    protected void checkDataSingle(
            ActionMapping mapping,
            HttpServletRequest request,
            BizResponse bizResponse) {

        String name = bizResponse.getName();
        if (null==name) {
                // use form-bean or mapping name
            name = mapping.getAttribute();
            bizResponse.setName(name);
        }
        String scope = bizResponse.getScope();
        Object bean = bizResponse.getData();

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
        if (bizResponse.isExposed()) {
            exposeInScope(request,name,scope,bean);

        }

    } // end checkDataSingle


    /**
     * Save any result objects within the business response to servlet context.
     * <p>
     * <code>bizResponse.getData()</code> must return non-null.
     * <p>
     * If <code>bizResponse.getName()</code> is null, the mapping's attribute
     * (<code>mapping.getAttribute()</code>) is used instead.
     * By default, this is the <code>form-bean</code>'s name.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param bizResponse The BizResponse we are handling
     */
    protected void checkData(
            ActionMapping mapping,
            HttpServletRequest request,
            BizResponse bizResponse) {

        if (bizResponse.isSingleForm()) {
            checkDataSingle(mapping,request,bizResponse);
        }
        else {

            String name = bizResponse.getName();
            if (null==name) {
                name = Tokens.LIST_KEY;
                bizResponse.setName(name);
            }
            String scope = bizResponse.getScope();
            Object bean = bizResponse.getData();

            if (bizResponse.isExposed()) {
                exposeInScope(request,name,scope,bean);
            }
        }

	} // end checkData


    /**
     * Process new dispatch advice passed by the business tier.
     * <p>
     * This is used to route control to another location besides
     * the default "success" forward registered with the controller.
     * <p>
     * The business tier can pass back either a path or the name of
     * an ActionForward.
     * <code>checkDispatch</code> will then create an ActionForward to return
     * and save it in the request under the SUCCESS token.
     * The <code>findSuccess()</code> will check for this attribute
     * before returning the controller's default.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param BizResponse The BizResponse we are handling
     */
    protected void checkDispatch(
            ActionMapping mapping,
            HttpServletRequest request,
            BizResponse bizResponse) {

        String dispatch = bizResponse.getDispatch();
        ActionForward forward = null;

        if (bizResponse.isDispatchPath()) {
            forward = new ActionForward(dispatch);
        }
        else {
            forward = mapping.findForward(dispatch);
        }

            // Our findSuccess looks for this
        request.setAttribute(Tokens.SUCCESS,forward);

    } // end checkDispatch


    /**
     * Check outcome, if any; recurse if container for other outcomes.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param BizResponse The BizResponse we are handling
     */
    protected void checkOutcome(
            ActionMapping mapping,
            HttpServletRequest request,
            BizResponse bizResponse) throws Exception {

        if (bizResponse!=null) {

            servlet.log(Log.HELPER_OUTCOME,Log.DEBUG);

            if (bizResponse.isAggregate()) {
                    // recurse for each BizResponse in collection
                Collection collection = (Collection)
                    bizResponse.getData();
                Iterator iterator = collection.iterator();
                while (iterator.hasNext()) {
                    BizResponse nextBizResponse =
                        (BizResponse) iterator.next();
                    checkOutcome(mapping,request,nextBizResponse);
                }
            }

            else {
                    // call extension points for whatever is returned
                if (bizResponse.isData())
                    checkData(mapping,request,bizResponse);

                if (bizResponse.isMessages())
                    checkMessages(mapping,request,bizResponse);

                if (bizResponse.isDispatch())
                    checkDispatch(mapping,request,bizResponse);
            }
        }

        else {
            throw new Exception(Log.PROCESS_RESULT_NULL);
        }

	} // end checkOutcome


// --------------------------------------------------------- Public Methods


    /**
     * Stores informational messages for display by the presention device.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param bizResponse The BizResponse we are handling
     */
    protected void checkMessages(
			ActionMapping mapping,
            HttpServletRequest request,
            BizResponse bizResponse) {

		// saveMessages(request,bizResponse.getMessages());

    } // end checkMessages


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
     * The set of argument type classes for the reflected method call.
     * These are the same for all calls, so calculate them only once.
     */
    private static final Class types[] = { Object.class };


    /**
     * Error handler.
     * Posts a message template and two parameters in a BizResponse.
     */
    private BizResponse processError(
        ActionMapping mapping,
		String template) {

			// Any other way to do this?
		BizResponse bizResponse = 
			new org.apache.commons.scaffold.util.BizResponseImpl(); 
		Message message = 
				new org.apache.commons.scaffold.util.MessageImpl(
			template,
			mapping.getPath(),
			mapping.getParameter()
		);
		
		bizResponse.addMessage(message);
                
        return bizResponse;
    }


    /**
     * Invoke an alternate method on BizService.
     */
	protected BizResponse processDispatch(
			ActionMapping mapping, 
            BizService bizService,
            BizRequest bizRequest,
            String methodName) throws Exception {

        Method method = bizService.getClass().getMethod(methodName,types);
        Object args[] = { bizRequest };

		BizResponse bizResponse = null;
        try {

            bizResponse = (BizResponse) method.invoke(bizService,args);

        }

        catch (ClassCastException e) {

             bizResponse = processError(mapping,Tokens.ERROR_DISPATCH_RETURN);
        }

        catch (IllegalAccessException e) {

            bizResponse = processError(mapping,Tokens.ERROR_DISPATCH_RETURN);
        }

        catch (InvocationTargetException e) {

            // Rethrow the target exception if possible so that the
            // exception handling machinery can deal with it
            Throwable t = e.getTargetException();
            if (t instanceof Exception) {
                throw ((Exception) t);
            } else {
                bizResponse = processError(mapping,Tokens.ERROR_DISPATCH);
            }
        }
        
        return bizResponse;
	}



    /**
     * Obtain the business request, invoke the business service,
     * and process the outcome.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @param helpers The object instantiated from type given as parameter.
     * @exception Exception if a error occurs
     */
    protected void executeLogic(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response,
            Object[] helpers) throws Exception {

		BizResponse bizResponse = null;
 		servlet.log(Log.HELPER_PROCESSING,Log.DEBUG);
        try {

            // Cast our form bean; snag our request bean
        	BizForm bizForm = (BizForm) form;
        	BizRequest bizRequest = bizForm.getBizRequest();

            // Munge the parameter property
	        servlet.log(Log.TOKENS_PARSING,Log.DEBUG);
	        String[] tokens = tokenize(mapping.getParameter());

            // Create our business service helper
	        Object helper = createHelperObject(request,tokens[0]);
	        BizService bizService = (BizService) helper;

            // Process business logic
            servlet.log(Log.HELPER_EXECUTING,Log.DEBUG);            
            if (tokens.length>1) {

	            // Pass along the helper's parameter, if any
	            if (tokens.length>2) {
					bizRequest.setParameter(tokens[2]);
				}
               
				bizResponse = processDispatch(
					mapping,
					bizService,
					bizRequest,
					tokens[1]);
			}
			
			else {
            	bizResponse = (BizResponse) bizService.process(bizRequest);
			}
		}

		// Gracefully trap any kinky class-cast or NPE type exceptions 
		catch (Throwable t) {	
			throw new ParameterException(t);
		}

		// Analyze result of business logic
		checkOutcome(mapping,request,bizResponse);       

    } // end executeLogic

} // end BizAction