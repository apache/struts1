/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/java/org/apache/struts/scaffold/RecreateSessionAction.java,v 1.4 2004/03/14 07:15:03 sraeburn Exp $
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
 
  
package org.apache.struts.scaffold;


import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.commons.scaffold.lang.Tokens;


/**
 * Create a new session, perserving any locale attribute
 * from the current session.
 *
 * @version $Revision: 1.4 $ $Date: 2004/03/14 07:15:03 $
 */
public final class RecreateSessionAction extends BaseAction {


    /**
     * Creates a new session, perserving any prior Locale attribute.
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

        HttpSession session = request.getSession();

        Locale locale = (Locale) session.getAttribute(Action.LOCALE_KEY);

        session.invalidate();

        session = request.getSession(true);

        session.setAttribute(Action.LOCALE_KEY,locale);

        return mapping.findForward(Tokens.SUCCESS);
    }

} // end RecreateSessionAction