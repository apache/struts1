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
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.AbstractExecuteAction;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;


/**
 * <p>Invoke the appropriate <code>Action</code> for this request, and cache
 * the returned <code>ActionForward</code>.</p>
 *
 * @version $Revision: 1.4 $ $Date: 2004/06/24 01:16:44 $
 */

public class ExecuteAction extends AbstractExecuteAction {


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Execute the specified <code>Action</code>, and return the resulting
     * <code>ActionForward</code>.</p>
     *
     * @param context The <code>Context</code> for this request
     * @param action The <code>Action</code> to be executed
     * @param actionConfig The <code>ActionConfig</code> defining this action
     * @param actionForm The <code>ActionForm</code> (if any) for
     *  this action
     *
     * @exception Exception if thrown by the <code>Action</code>
     */
    protected ForwardConfig execute(Context context,
                                    Action action,
                                    ActionConfig actionConfig,
                                    ActionForm actionForm)
        throws Exception {

        ServletWebContext swcontext = (ServletWebContext) context;
        return (action.execute((ActionMapping) actionConfig,
                               actionForm,
                               swcontext.getRequest(),
                               swcontext.getResponse()));

    }


}
