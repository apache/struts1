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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.commons.scaffold.lang.Tokens;


/**
 * Scan request parameters for the name of a local or global
 * forward. If one is found, use it. If not, return null.
 * <p>
 * This class extends BaseAction to provide
 * cross-compatibility with Struts 1.1 and 1.0
 *
 * @version $Rev$ $Date$
 */
public final class FindForwardAction extends BaseAction {

    /**
     * Scan request parameters for the name of a local or global
     * forward. If one is found, use it. If not, return null.
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

        String forwards[] = mapping.findForwards();

        /* -- non-deprecated version for 1.1
        ApplicationConfig config = (ApplicationConfig)
            request.getAttribute(Action.APPLICATION_KEY);
        ForwardConfig forwards[] = config.findForwardConfigs();
        */

        for (int i=0; i<forwards.length; i++) {
            if (request.getParameter(forwards[i])!=null) {
                    // Return the required ActionForward instance
                 return mapping.findForward(forwards[i]);
             }
         }

        return null;

    }

} // end FindForwardAction