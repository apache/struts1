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


import javax.servlet.http.HttpServletResponse;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.struts.chain.AbstractRequestNoCache;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Check to see if the controller is configured to prevent caching,
 * and if so, set the no cache HTTP response headers.</p>
 *
 * @version $Revision: 1.4 $ $Date: 2004/04/25 02:30:16 $
 */

public class RequestNoCache extends AbstractRequestNoCache {


    // ------------------------------------------------------- Protected Methods


    protected void requestNoCache(Context context) {

        ServletWebContext swcontext = (ServletWebContext) context;
        HttpServletResponse response = swcontext.getResponse();
        
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
        response.setDateHeader("Expires", 1);

    }


}
