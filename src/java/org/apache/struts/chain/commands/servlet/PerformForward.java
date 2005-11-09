/*
 * Copyright 2003-2005 The Apache Software Foundation.
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


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.commands.AbstractPerformForward;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;


/**
 * <p>Perform forwarding or redirection based on the specified
 * <code>ForwardConfig</code> (if any).</p>
 *
 * @version $Rev$ $Date$
 */

public class PerformForward extends AbstractPerformForward {


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Perform the appropriate processing on the specified
     * <code>ForwardConfig</code>.</p>
     *
     * @param context The context for this request
     * @param forwardConfig The forward to be performed
     */
    protected void perform(ActionContext context,ForwardConfig forwardConfig)
        throws Exception {

        ServletActionContext sacontext = (ServletActionContext) context;
        String forwardPath = forwardConfig.getPath();
        String uri = null;

        if (forwardPath == null) {
            // Retrieve internal message resources
            ActionServlet servlet =  (ActionServlet) sacontext.getActionServlet();
            MessageResources resources = servlet.getInternal();
            throw new IllegalArgumentException(resources.getMessage("forwardPathNull"));
        }

        ModuleConfig moduleConfig  = context.getModuleConfig();
        // Resolve module-relative paths
        if (forwardPath.startsWith("/")) {
            uri = RequestUtils.forwardURL(sacontext.getRequest(),
                                          forwardConfig,
                                          moduleConfig);
        } else {
            uri = forwardPath;
        }

        HttpServletRequest request = sacontext.getRequest();

        // Perform redirect or forward
        if (forwardConfig.getRedirect()) {
            if (uri.startsWith("/")) {
                uri = request.getContextPath() + uri;
            }
            sacontext.getResponse().sendRedirect
                (sacontext.getResponse().encodeRedirectURL(uri));
        } else {
            RequestDispatcher rd =
                sacontext.getContext().getRequestDispatcher(uri);
            rd.forward(request, sacontext.getResponse());
        }

    }


}
