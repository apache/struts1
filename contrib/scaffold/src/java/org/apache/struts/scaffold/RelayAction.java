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
 * Standard Action to forward control to another mapping
 * given as a runtime parameter (?dispatch=).
 * <p>
 * This class extends BaseAction to provide
 * cross-compatibility with Struts 1.1 and 1.0
 *
 * @version $Rev$ $Date$
 */
public final class RelayAction extends BaseAction {

    /**
     * Looks for a runtime parameter named "dispatch",
     * and uses its value to search for an ActionForward.
     * <p>
     * <code>dispatch=findByTile</code> will return
     * the ActionForward named "findByTitle".
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

        return mapping.findForward(request.getParameter(Tokens.DISPATCH));

    } // end findSuccess

} // end RelayAction