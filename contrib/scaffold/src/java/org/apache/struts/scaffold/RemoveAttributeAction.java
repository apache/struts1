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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.commons.scaffold.lang.Tokens;


/**
 * Remove an object from the user's session.
 * The name of the attribute is passed via the parameter property.
 *
 * @version $Rev$ $Date$
 */
public final class RemoveAttributeAction extends BaseAction {


    /**
     * // :FIXME: Needs to be tested.<p>
     * Attempt to remove an attribute from a servlet context.
     * Find "success" if attribute exists, or "failure" if not.
     *
     * The servlet context and attributes are specified as the
     * parameters property, seperated by semi-colons
     * [parameter="application;HOURS].
     * non-error state.
     *
     * To indicate that all scopes are to be checked,
     * specify an asterisk instead of the scope name
     * [parameter="*;HOURS]. The attribute will be removed
     * from <b>only</b> the first context found.
     *
     * If both parameters are not given, an error is set.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The response we are creating
     * @todo Add support for multiple attributes
     * @fixme Needs to be tested.
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

        boolean any = ("*".equals(scope));

        if (any) {

            bean = request.getAttribute(name);
            if (null!=bean)
                request.removeAttribute(name);

            if (null==bean) {
                bean = request.getSession().getAttribute(name);
                if (null!=bean)
                    request.getSession().removeAttribute(name);
            }

            if (null==bean) {
                bean = servlet.getServletContext().getAttribute(name);
                if (null!=bean)
                    servlet.getServletContext().removeAttribute(name);
            }

        } // end any

        else {

            if (Tokens.REQUEST.equals(scope)) {
                bean = request.getAttribute(name);
                request.removeAttribute(name);
            }

            if (Tokens.SESSION.equals(scope)) {
                bean = request.getSession().getAttribute(name);
                request.getSession().removeAttribute(name);
            }

            if (Tokens.APPLICATION.equals(scope)) {
                bean = servlet.getServletContext().getAttribute(name);
                servlet.getServletContext().removeAttribute(name);
            }

        } // end !any


        if (null==bean) {

            return mapping.findForward(Tokens.FAILURE);

        }

        return mapping.findForward(Tokens.SUCCESS);
    }

} // end RemoveAttribute