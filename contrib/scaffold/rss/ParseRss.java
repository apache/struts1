/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/rss/ParseRss.java,v 1.2 2004/03/14 07:15:06 sraeburn Exp $
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.digester.rss.RSSDigester;
import org.apache.commons.digester.rss.Channel;
import org.apache.commons.scaffold.lang.Tokens;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import org.apache.struts.scaffold.BaseAction;


/**
 * Read and parse RSS file found at a given
 * path, save the Channel bean in request scope,
 * and forward to "success".
 *
 * @author Ted Husted
 * @version $Revision: 1.2 $ $Date: 2004/03/14 07:15:06 $
 */
public final class ParseRss extends BaseAction {

    // --------------------------------------------------------- Instances Variables
    // --------------------------------------------------------- Public Methods

    /**
     * Request attribute key for saving Channel bean ["CHANNEL"].
     */
    public static final String CHANNEL_KEY = "CHANNEL";


    /**
     * Read and parse RSS file found at a given
     * path, save the Channel bean in request scope,
     * and forward to "success".
     *
     * @expects path={uri} on command line or as parameter property to ActionMapping.
     * @expects an input page or error forwarding if exception digesting RSS
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The resonse we are creating
     * @param errors Our ActionErrors collection
     */
    public void executeLogic(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
        throws Exception {

        Channel channel = null;

        String path = request.getParameter(Tokens.PATH);
        if (path==null) path = mapping.getParameter();

        RSSDigester digester = new RSSDigester();
        channel = (Channel) digester.parse(path);

        request.setAttribute(CHANNEL_KEY,channel);

   } // end executeLogic()

} // end ParseRss