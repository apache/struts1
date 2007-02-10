/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts.chain.commands.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.commands.AbstractPopulateActionForm;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Populate the form bean (if any) for this request.  Sets the multipart
 * class from the action config in the request attributes.</p>
 *
 * @version $Rev$ $Date: 2005-11-12 13:01:44 -0500 (Sat, 12 Nov 2005)
 *          $
 */
public class PopulateActionForm extends AbstractPopulateActionForm {
    private static final Log log = LogFactory.getLog(PopulateActionForm.class);

    // ------------------------------------------------------- Protected Methods

    protected void populate(ActionContext context, ActionConfig actionConfig,
        ActionForm actionForm)
        throws Exception {
        ServletActionContext saContext = (ServletActionContext) context;
        HttpServletRequest request = saContext.getRequest();

        // Populate the form bean only if configured so
        if (isPopulate(request, actionConfig)) {
            RequestUtils.populate(actionForm, actionConfig.getPrefix(),
                actionConfig.getSuffix(), saContext.getRequest());
        }
    }

    protected void reset(ActionContext context, ActionConfig actionConfig,
        ActionForm actionForm) {

        ServletActionContext saContext = (ServletActionContext) context;
        HttpServletRequest request = saContext.getRequest();

        // Reset the form bean only if configured so
        if (isReset(request, actionConfig)) {
            actionForm.reset((ActionMapping) actionConfig, saContext.getRequest());
        }

        // Set the multipart class
        if (actionConfig.getMultipartClass() != null) {
            saContext.getRequestScope().put(Globals.MULTIPART_KEY,
                actionConfig.getMultipartClass());
        }
    }

    // ---------------------------------------------------------- Helper Methods

    /**
     * Verifies whether an action form should be populated
     * @param request current HTTP request
     * @param actionConfig action config for current request
     * @return true if action form should be populated
     *
     * @since Struts 1.3.7
     */
    protected boolean isPopulate(HttpServletRequest request, ActionConfig actionConfig) {
        String strPopulate = actionConfig.getPopulate();
        return getResetOrPopulate(request, strPopulate);
    }

    /**
     * Verifies whether an action form should be reset
     * @param request current HTTP request
     * @param actionConfig action config for current request
     * @return true if action form should be reset
     *
     * @since Struts 1.3.7
     */
    protected boolean isReset(HttpServletRequest request, ActionConfig actionConfig) {
        String strReset = actionConfig.getReset();
        return getResetOrPopulate(request, strReset);
    }

    /**
     * Compares current request state (direct or forwarded) with configuration
     * from action mapping.
     * @param request current HTTP request
     * @param strAttr value of either "reset" or "populate" attributes of
     *        an action mapping
     * @return true if action mapping is configured to reset (or populate)
     *         corresponding action form; false if if action mapping is
     *         configured not to reset (or populate) the action form.
     *
     * @since Struts 1.3.7
     */
    protected boolean getResetOrPopulate(HttpServletRequest request, String strAttr) {
        // Reset configuration is not defined (should not happen,
        // because default value are set to "request,forward".
        if (strAttr == null) return true;

        // Reads Globals.FORWARD_KEY attribute from the HTTP request object
        boolean forwarded = RequestUtils.isForwarded(request);

        // Forwarded request is configured for reset/populate
        if (forwarded && strAttr.indexOf(ActionConfig.FORWARD_STR) > -1) return true;

        // Direct request is configured for reset/populate
        if (!forwarded && strAttr.indexOf(ActionConfig.REQUEST_STR) > -1) return true;

        // Do not reset/populate if a user explicity set anything
        // else besides "request" or "populate".
        return false;
    }
}
