/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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
package org.apache.struts.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.util.ModuleException;

/**
 * An ExceptionHandler is configured in the Struts configuration file to handle a specific
 * type of exception thrown by an Action's execute method.
 * 
 * @since Struts 1.1
 */
public class ExceptionHandler {
    /**
     * Handle the exception.
     * Return the <code>ActionForward</code> instance (if any) returned by
     * the called <code>ExceptionHandler</code>.
     *
     * @param ex The exception to handle
     * @param ae The ExceptionConfig corresponding to the exception
     * @param mapping The ActionMapping we are processing
     * @param formInstance The ActionForm we are processing
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception ServletException if a servlet exception occurs
     *
     * @since Struts 1.1
     */
    public ActionForward execute(Exception ex,
                                 ExceptionConfig ae,
                                 ActionMapping mapping,
                                 ActionForm formInstance,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws ServletException {

    ActionForward forward = null;
        ActionError error = null;
        String property = null;

        // Build the forward from the exception mapping if it exists
        // or from the form input
        if (ae.getPath() != null) {
            forward = new ActionForward(ae.getPath());
        } else {
            forward = mapping.getInputForward();
        }

        // Figure out the error
        if (ex instanceof ModuleException) {
            error = ((ModuleException) ex).getError();
            property = ((ModuleException) ex).getProperty();
        } else {
            error = new ActionError(ae.getKey(), ex.getMessage());
            property = error.getKey();
        }

        // Store the exception
        request.setAttribute(Globals.EXCEPTION_KEY, ex);
        storeException(request, property, error, forward, ae.getScope());

        return forward;
    }

    /**
     * Default implementation for handling an <b>ActionError</b> generated
     * from an Exception during <b>Action</b> delegation.  The default
     * implementation is to set an attribute of the request or session, as
     * defined by the scope provided (the scope from the exception mapping).  An
     * <b>ActionErrors</b> instance is created, the error is added to the collection
     * and the collection is set under the Globals.ERROR_KEY.
     *
     * @param request - The request we are handling
     * @param property  - The property name to use for this error
     * @param error - The error generated from the exception mapping
     * @param forward - The forward generated from the input path (from the form or exception mapping)
     * @param scope - The scope of the exception mapping.
     *
     */
    protected void storeException(HttpServletRequest request,
                        String property,
                        ActionError error,
                        ActionForward forward,
                        String scope) {
        ActionErrors errors = new ActionErrors();
        errors.add(property, error);

        if ("request".equals(scope)){
            request.setAttribute(Globals.ERROR_KEY, errors);
        } else {
            request.getSession().setAttribute(Globals.ERROR_KEY, errors);
        }
    }

}

