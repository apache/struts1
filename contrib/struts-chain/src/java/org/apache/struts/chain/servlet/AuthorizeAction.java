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


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.struts.chain.AbstractAuthorizeAction;
import org.apache.struts.config.ActionConfig;


/**
 * <p>Determine if the action is authorized for the given roles.</p>
 *
 * @version $Revision: 1.5 $ $Date: 2004/06/24 01:03:00 $
 */

public class AuthorizeAction extends AbstractAuthorizeAction {


    // ------------------------------------------------------- Protected Methods


    protected boolean isAuthorized(Context context, String[] roles,
                                   ActionConfig mapping) throws Exception {
        
        // Identify the HTTP request object
        ServletWebContext swcontext = (ServletWebContext) context;
        HttpServletRequest request = swcontext.getRequest();
        
        // Check the current user against the list of required roles
        for (int i = 0; i < roles.length; i++) {
            if (request.isUserInRole(roles[i])) {
                return (true);
            }
        }
        
        // Default to unauthorized
        return (false);

    }

}
