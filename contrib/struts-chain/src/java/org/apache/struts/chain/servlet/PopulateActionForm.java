/*
 * Copyright 2003,2004 The Apache Software Foundation.
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

package org.apache.struts.chain.servlet;


import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.AbstractPopulateActionForm;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.util.RequestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Populate the form bean (if any) for this request.  Sets the multipart
 * class from the action config in the request attributes.</p>
 *
 * @version $Rev$ $Date$
 */

public class PopulateActionForm extends AbstractPopulateActionForm {


    private static final Log log = LogFactory.getLog(PopulateActionForm.class);

    // ------------------------------------------------------- Protected Methods


    protected void populate(Context context,
                         ActionConfig actionConfig,
                         ActionForm actionForm) throws Exception
    {
        ServletWebContext swcontext = (ServletWebContext) context;
        RequestUtils.populate(actionForm, actionConfig.getPrefix(), actionConfig.getSuffix(), swcontext.getRequest());
    }

    protected void reset(Context context,
                         ActionConfig actionConfig,
                         ActionForm actionForm) {

        ServletWebContext swcontext = (ServletWebContext) context;
        actionForm.reset((ActionMapping) actionConfig, swcontext.getRequest());

        // Set the multipart class
        if (actionConfig.getMultipartClass() != null) {
            swcontext.getRequestScope().put(Globals.MULTIPART_KEY,
                                 actionConfig.getMultipartClass());
        }

    }


}
