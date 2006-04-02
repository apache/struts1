/*
 * Copyright 2003-2006 The Apache Software Foundation.
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
package org.apache.struts.chain.commands.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.commands.AbstractPerformForward;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Perform forwarding or redirection based on the specified
 * <code>ForwardConfig</code> (if any).</p>
 *
 * @version $Rev$ $Date$
 */
public class PerformForward extends AbstractPerformForward {
    private static final Log LOG = LogFactory.getLog(PerformForward.class);

    // ------------------------------------------------------- Protected Methods

    /**
     * <p>Perform the appropriate processing on the specified
     * <code>ForwardConfig</code>.</p>
     *
     * @param context       The context for this request
     * @param forwardConfig The forward to be performed
     */
    protected void perform(ActionContext context, ForwardConfig forwardConfig)
        throws Exception {
        ServletActionContext sacontext = (ServletActionContext) context;
        String forwardPath = forwardConfig.getPath();
        String uri;

        if (forwardPath == null) {
            // Retrieve internal message resources
            ActionServlet servlet = sacontext.getActionServlet();
            MessageResources resources = servlet.getInternal();

            throw new IllegalArgumentException(resources.getMessage(
                    "forwardPathNull"));
        }

        ModuleConfig moduleConfig = context.getModuleConfig();

        // Resolve module-relative paths
        if (forwardPath.startsWith("/")) {
            uri = RequestUtils.forwardURL(sacontext.getRequest(),
                    forwardConfig, moduleConfig);
        } else {
            uri = forwardPath;
        }

        HttpServletRequest request = sacontext.getRequest();

        // Use of actions within tiles, jsp:include or c:import requires to
        // convert forward (w/o redirect) to an include
        // if response has been committed
        HttpServletResponse response = sacontext.getResponse();

        if (response.isCommitted() && !forwardConfig.getRedirect()) {
            RequestDispatcher rd =
                sacontext.getContext().getRequestDispatcher(uri);

            if (rd == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error getting RequestDispatcher for " + uri);

                return;
            }

            if (LOG.isDebugEnabled()) {
                LOG.debug("Including " + uri);
            }
                
            rd.include(request, response);

            return;
        }

        // Perform redirect or forward
        if (forwardConfig.getRedirect()) {
            if (uri.startsWith("/")) {
                uri = request.getContextPath() + uri;
            }

            if (LOG.isDebugEnabled()) {
                LOG.debug("Redirecting to " + uri);
            }

            sacontext.getResponse().sendRedirect(sacontext.getResponse()
                                                          .encodeRedirectURL(uri));
        } else {
            RequestDispatcher rd =
                sacontext.getContext().getRequestDispatcher(uri);

            if (LOG.isDebugEnabled()) {
                LOG.debug("Forwarding to " + uri);
            }

            rd.forward(request, sacontext.getResponse());
        }
    }
}
