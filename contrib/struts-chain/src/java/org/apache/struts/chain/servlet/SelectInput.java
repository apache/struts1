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
import org.apache.struts.action.ActionForward;
import org.apache.struts.chain.AbstractSelectInput;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Validate the properties of the form bean for this request.  If there are
 * any validation errors, execute the child commands in our chain; otherwise,
 * proceed normally.</p>
 *
 * @version $Revision: 1.4 $ $Date: 2004/06/24 01:26:41 $
 */

public class SelectInput extends AbstractSelectInput {


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Create and return a <code>ForwardConfig</code> representing the
     * specified module-relative destination.</p>
     *
     * @param context The context for this request
     * @param moduleConfig The <code>ModuleConfig</code> for this request
     * @param uri The module-relative URI to be the destination
     */
    protected ForwardConfig forward(Context context,
                                    ModuleConfig moduleConfig,
                                    String uri) {

        return (new ActionForward(null, moduleConfig.getPrefix() + uri,
                                  false, true));

    }


}
