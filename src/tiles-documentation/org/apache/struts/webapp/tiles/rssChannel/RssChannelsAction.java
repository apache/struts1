/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/rssChannel/RssChannelsAction.java,v 1.7 2003/08/23 00:12:39 dgraham Exp $
 * $Revision: 1.7 $
 * $Date: 2003/08/23 00:12:39 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
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

package org.apache.struts.webapp.tiles.rssChannel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.digester.rss.Channel;
import org.apache.commons.digester.rss.RSSDigester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.actions.TilesAction;

/**
 * Read and parse RSS files found at on a given
 * list in Tile attribute, save the Channel
 * beans in Tile attribute,and forward to "continue".
 * Tiles input attributes :
 * <ul>
 *   <li>urls : list of channel urls</li>
 *   <li>url : a channel url (if urls not use)</li>
 * </ul>
 * Tiles output attributes :
 * <ul>
 *   <li>channels : List of Digester Channel beans</li>
 * </ul>
 *
 * @author Ted Husted
 * @author Cedric Dumoulin
 * @version $Revision: 1.7 $ $Date: 2003/08/23 00:12:39 $
 */
public final class RssChannelsAction extends TilesAction {

    /** 
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getLog(RssChannelsAction.class);

    /**
     * Tile attribute key for saving Channel bean
     */
    public static final String CHANNELS_KEY = "CHANNELS";

    /**
     * Tile attribute key for getting Channel urls list
     */
    public static final String CHANNEL_URLS_KEY = "urls";

    /**
     * Tile attribute key for getting Channel url attribute
     */
    public static final String CHANNEL_URL_KEY = "url";

    /**
     * Main process of class. Reads, parses
     * @param context The current Tile context, containing Tile attributes.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request (if any).
     * @param request The HTTP request we are processing.
     * @param response The HTTP response we are creating.
     *
     * @exception Exception if the application business logic throws
     *  an exception
     * @since Struts 1.1
     */
    public ActionForward execute(
        ComponentContext context,
        ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request,
        HttpServletResponse response)
        throws Exception {

        log.debug("Enter Rss Channel Action");

        ActionErrors errors = new ActionErrors();

        // -- Retrieve parameters --
        // Urls can come from a list, or from a single attribute.

        List channels = (List) context.getAttribute(CHANNEL_URLS_KEY);
        if (channels == null) {
            Object url = context.getAttribute(CHANNEL_URL_KEY);
            channels = new ArrayList(1);
            channels.add(url);
        }

        log.debug("urls count" + channels.size());

        // -- Loop through channels --
        List channelBeans = new ArrayList(channels.size());
        try {
            for (int i = 0; i < channels.size(); i++) {
                RSSDigester digester = new RSSDigester();
                String url = (String) channels.get(i);
                // Add application path if needed
                if (url.startsWith("/")) {
                    url = toFullUrl(request, url);
                }

                log.debug("Channel url=" + url);

                Channel obj = (Channel) digester.parse(url);

                log.debug("Channel:" + obj);

                channelBeans.add(obj);
            }
        } catch (Throwable t) {
            errors.add(
                ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("rss.access.error"));

            servlet.log(t.toString());
        }

        // -- Handle Errors ---
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            // If no input page, use error forwarding

            log.debug("Exit Rss Channel Action : error");

            return null;
        }

        // -- Save Bean, and Continue  ---

        log.debug("Exit Rss Channel Action");

        // Use Tile context to pass channels
        context.putAttribute(CHANNELS_KEY, channelBeans);

        return null;
    }

    /**
     * Compute Full local url from an url starting with "/".
     */
    private String toFullUrl(HttpServletRequest request, String url) {
        StringBuffer buff = new StringBuffer();

        buff.append(request.getScheme()).append("://").append(
            request.getServerName());

        if (request.getServerPort() != 80) {
            buff.append(":").append(request.getServerPort());
        }

        buff.append(request.getContextPath()).append(url);

        return buff.toString();
    }

}