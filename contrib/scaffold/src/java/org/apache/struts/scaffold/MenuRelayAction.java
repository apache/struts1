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

import org.apache.commons.scaffold.lang.Tokens;
import org.apache.commons.scaffold.text.ConvertUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.scaffold.BaseAction;
import org.apache.struts.scaffold.BaseForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Standard Action to forward control to another mapping
 * given as a runtime parameter (?dispatch=),
 * after running any model classes.
 *
 * @version $Rev$ $Date$
 */
public final class MenuRelayAction extends BaseAction {


    /**
     * Looks for a runtime parameter named "dispatch",
     * and uses its value to search for an ActionForward.
     * If a "dispatch" parameter is not available,
     * and the form is a BaseForm istance, then the
     * dispatch property is checked.
     * If dispatch is blank, the mapping parameter is
     * checked.
     * Finally, if all of these are blank,
     * MenuRelayAction looks for a "menu" forward.
     * <p>
     * This is useful for returning to a submenu when
     * a MenuForm is kept in the session for each user.
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

        String forward = request.getParameter(Tokens.DISPATCH);
        if ((ConvertUtils.blank(forward)) && (form instanceof BaseForm)) {
            BaseForm baseForm = (BaseForm) form;
            forward = baseForm.getDispatch();
        }
        if (ConvertUtils.blank(forward)) forward = mapping.getParameter();
        if (ConvertUtils.blank(forward)) forward = Tokens.MENU;
        return mapping.findForward(forward);
    }

} // end RelayHelperAction
