/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/rss/RenderRss.java,v 1.2 2004/03/14 07:15:06 sraeburn Exp $
 * $Revision: 1.2 $
 * $Date: 2004/03/14 07:15:06 $
 *
 * Copyright 1999-2004 The Apache Software Foundation.
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
 
 
package org.apache.struts.scaffold.rss;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.digester.rss.Channel;
import org.apache.commons.digester.rss.Item;

import org.apache.commons.scaffold.lang.Tokens;
import org.apache.commons.scaffold.util.ResultList;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import org.apache.struts.scaffold.BaseAction;


/**
 * Convert result bean to channel and render as response.
 *
 * @author Ted Husted
 * @version $Revision: 1.2 $ $Date: 2004/03/14 07:15:06 $
 */
public final class RenderRss extends BaseAction {

// ------------------------------------------------- Instance Variables
// ----------------------------------------------------- Public Methods


    /**
     (* // :FIXME: Hasn't been tested.
     * Retrieves a Digester Channel bean from request context and
     * renders it.
     * <p>
     * Channel bean must be stored under the attribute
     * ParseRss.CHANNEL_KEY or under an attribute given as the
     * parameter property.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The resonse we are creating
     * @fixme Use ActionForwards for links instead of properties
     */
    public void executeLogic(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
        throws Exception {

        ActionErrors errors = new ActionErrors();
        Channel channel = null;

        try {
            channel = (Channel)
                request.getAttribute(ParseRss.CHANNEL_KEY);
        }
        catch (Throwable t) {
            channel = null;
        }

        if (null==channel) {
            try {
                channel = (Channel)
                    request.getAttribute(mapping.getParameter());
            }
            catch (Throwable t) {
                channel = null;
            }
        }

        if (null==channel) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError(Tokens.PROCESS_MISSING_PARAMETER));
        }

        if (errors.empty()) {
                response.setContentType(Tokens.TEXT_PLAIN);
                channel.render(response.getWriter());
        }

        else {
                // This will trigger findFailure() in the BaseAction
            saveErrors(request,errors);
        }

    } // end executeLogic()


     /**
      * Return null to the controller, indicating that the
      * response has been rendered.
      *
      * @param mapping The ActionMapping used to select this instance
      * @param actionForm The optional ActionForm bean for this request (if any)
      * @param request The HTTP request we are processing
      * @param response The response we are creating
      * @param errors Our ActionErrors collection
      */
     protected ActionForward findSuccess(
         ActionMapping mapping,
         ActionForm form,
         HttpServletRequest request,
         HttpServletResponse response) {

        return null;

    } // end findSucess()

} // end RenderRss