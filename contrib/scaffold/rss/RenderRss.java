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
 * @version $Revision: 1.1 $ $Date: 2002/08/14 18:30:30 $
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


/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/rss/RenderRss.java,v 1.1 2002/08/14 18:30:30 husted Exp $
 * $Revision: 1.1 $
 * $Date: 2002/08/14 18:30:30 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
 */

