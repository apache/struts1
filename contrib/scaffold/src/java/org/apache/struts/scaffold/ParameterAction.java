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

import org.apache.commons.scaffold.lang.Tokens;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Standard Action to append a passed parameter to query string.
 * <p>
 * This class extends BaseAction to provide
 * cross-compatibility with Struts 1.1 and 1.0
 *
 * @version $Rev$ $Date$
 */
public class ParameterAction extends BaseAction {

    /**
     * Append parameter to a query string.
     * The "dispatch" parameter is used to select a forward.
     * The value of the parameter named by the "parameter" property
     * is concaternated to the forward's path.
     * No parsing of the path is performed at this time.
     * The value is simply appended.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The response we are creating
     */
    protected ActionForward findSuccess(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

            // Get "dispatch" parameter
        String parameter = request.getParameter(Tokens.DISPATCH);

            // Get parameter name for this mapping
        String paramName = mapping.getParameter();

            // Get stub URI from mapping (/do/whatever?paramName=)
        StringBuffer path = new StringBuffer(64);
        ActionForward template = mapping.findForward(parameter);
        path.append(template.getPath());
            // Append the value passed (/do/whatever?paramName=paramProperty)
        path.append(request.getParameter(paramName));

            // Return a new forward based on stub+value
        return new ActionForward(path.toString(),template.getRedirect());

    }

} // end ParameterAction