package org.apache.scaffold.http;


import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import org.apache.scaffold.lang.Tokens;
import org.apache.scaffold.model.ModelBean;
import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResult;


/**
 * Standard Action to manage helper objects.
 * @author Ted Husted
 * @version $Revision: 1.4 $ $Date: 2002/03/05 02:28:54 $
**/
public class ModelHelper extends BaseHelperAction {


    /**
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param helper The helper object
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     * :FIXME: is there any valid use of the response here?
    **/
    protected ModelResult getResult(ActionMapping mapping,
                 ActionForm form,
                 HttpServletRequest request,
                 HttpServletResponse response,
                 Object[] helpers) throws ModelException {

        ModelBean modelBean = (ModelBean) helpers[0];
        ModelResult modelResult = modelBean.execute(form);
            // Shouldn't return null
        if (modelResult!=null)
            modelResult.populate(form,0);
        return (modelResult);

    }


    /**
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param helper The helper object
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     * :FIXME: is there any valid use of the response here?
    **/
     protected ActionForward getSuccess(
         ActionMapping mapping,
         ActionForm form,
         HttpServletRequest request,
         HttpServletResponse response,
         Object[] helpers
         ) throws IOException, ServletException {

        // return mapping.findForward(request.getParameter(Tokens.FORWARD));
        return mapping.findForward(Tokens.SUCCESS);

    }


    /**
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @param helper The helper object
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
    **/
    protected ActionForward perform(ActionMapping mapping,
                 ActionForm form,
                 HttpServletRequest request,
                 HttpServletResponse response,
                 Object[] helpers)
    throws IOException, ServletException {

        ActionErrors errors = new ActionErrors();
        ModelResult modelResult = null;

        try {
            modelResult = getResult(
                mapping,form,request,response,helpers);
        }
        catch (ModelException e) {
                // Log and print to error console
            servlet.log("Model Exception: ", e );
            e.printStackTrace();
                // General error message
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.general"));
                // Generate error messages from exceptions
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.detail",e.getMessage()));
            if (e.isCause()) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.detail",e.getCauseMessage()));
            }
        }

        // -- Report any errors
        if (!errors.empty()) {
            saveErrors(request, errors);
            if (mapping.getInput()!=null)
                return (new ActionForward(mapping.getInput()));
            // If no input page, use error forwarding
            return (mapping.findForward(Tokens.FAILURE));
        }

        // -- Check for confirmation message
        if (modelResult!=null)
            saveMessage(errors,modelResult.getMessages());

        // -- Save any confirmations
        if (!errors.empty())
            saveErrors(request, errors);

        // -- Save the result
        request.setAttribute(ModelResult.TOKEN,modelResult);

        // -- Return empty (if appropriate) or continue
        ActionForward forward = null;
        if (modelResult.getSize()==0)
            forward = mapping.findForward(Tokens.EMPTY);
        if (forward!=null)
            return forward;

        // -- Return forward for successful outcome
        return getSuccess(mapping,form,request,response,helpers);
    }

} // end ModelResultHelper


/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/framework/main/org/apache/scaffold/http/Attic/ModelHelper.java,v 1.4 2002/03/05 02:28:54 husted Exp $
 * $Revision: 1.4 $
 * $Date: 2002/03/05 02:28:54 $
 *
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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
**/




