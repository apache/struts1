package org.apache.struts.scaffold;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import org.apache.commons.scaffold.lang.Tokens;

/**
 * Remove an object from the user's session.
 * The name of the attribute is passed via the parameter property.
 *
 * @author Ted Husted
 * @version $Revision: 1.2 $ $Date: 2002/08/16 22:29:24 $
 */
public final class ExistsAttributeAction extends BaseAction {

    /**
     * Find "success" if attribute exists, or "failure" if not.
     *
     * The servlet context and attributes are specified as the
     * parameters property, seperated by semi-colons
     * [parameter="application;HOURS].
     * non-error state.
     *
     * To indicatethat all scopes are to be checked,
     * specify an asterisk instead of the scope name
     * [parameter="*;HOURS].
     *
     * If both parameters are not given, an error is set.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The response we are creating
     * @todo Add support for multiple attributes
     */
   protected ActionForward findSuccess(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        String[] parameters = tokenize(mapping.getParameter());

            // If not 2+ parameters, bail
        if (2>parameters.length) {
            ActionErrors errors = new ActionErrors();
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError(Tokens.PROCESS_MISSING_PARAMETER));
            saveErrors(request,errors);
            return mapping.findForward(Tokens.FAILURE);
        }

        String scope = parameters[0];
        Object bean = null;
        String name = parameters[1];

        // :TODO: Add support for multiple attributes

        boolean any = ("*".equals(name));

        if (any) {

            bean = request.getAttribute(name);

            if (null==bean)
                bean = request.getSession().getAttribute(name);

            if (null==bean)
                bean = servlet.getServletContext().getAttribute(name);

        } // end any

        else {

            if (Tokens.REQUEST.equals(scope))
                bean = request.getAttribute(name);

            if (Tokens.SESSION.equals(scope))
                bean = request.getSession().getAttribute(name);

            if (Tokens.APPLICATION.equals(scope))
                bean = servlet.getServletContext().getAttribute(name);

        } // end !any


        if (null==bean) {

            return mapping.findForward(Tokens.FAILURE);

        }

        return mapping.findForward(Tokens.SUCCESS);
    }


} // end ExistsAttribute

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




