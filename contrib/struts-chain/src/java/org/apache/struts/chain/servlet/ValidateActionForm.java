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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.AbstractValidateActionForm;
import org.apache.struts.config.ActionConfig;


/**
 * <p>Validate the properties of the form bean for this request.  If there are
 * any validation errors, execute the child commands in our chain; otherwise,
 * proceed normally.  Also, if any errors are found and the request is a
 * multipart request, rollback the <code>MultipartRequestHandler</code>.</p>
 *
 * @author Craig R. McClanahan
 * @author Don Brown
 * @version $Revision: 1.6 $ $Date: 2004/06/24 01:27:31 $
 */

public class ValidateActionForm extends AbstractValidateActionForm {

    // ------------------------------------------------------ Instance Variables


    private static final Log log =
        LogFactory.getLog(ValidateActionForm.class);


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Call the <code>validate()</code> method of the specified form bean,
     * and return the resulting <code>ActionErrors</code> object.</p>
     *
     * @param context The context for this request
     * @param actionForm The form bean for this request
     */
    protected ActionErrors validate(Context context,
                                    ActionConfig actionConfig,
                                    ActionForm actionForm) {

        ServletWebContext swcontext = (ServletWebContext) context;
        ActionErrors errors = (actionForm.validate((ActionMapping) actionConfig,
                                    swcontext.getRequest()));

        // Special handling for multipart request
        if (errors != null && !errors.isEmpty()) {
            if (actionForm.getMultipartRequestHandler() != null) {
                if (log.isTraceEnabled()) {
                    log.trace("  Rolling back multipart request");
                }
                actionForm.getMultipartRequestHandler().rollback();
            }
        }

        return errors;

    }


}
