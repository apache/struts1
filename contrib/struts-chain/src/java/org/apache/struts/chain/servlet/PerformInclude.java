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


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.struts.chain.AbstractPerformInclude;
import org.apache.struts.upload.MultipartRequestWrapper;


/**
 * <p>Perform forwarding or redirection based on the specified
 * include uri (if any).</p>
 *
 * @version $Revision: 1.5 $ $Date: 2004/06/24 01:26:41 $
 */

public class PerformInclude extends AbstractPerformInclude {


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Perform the appropriate processing on the specified
     * include uri.</p>
     *
     * @param context The context for this request
     * @param uri The uri to be included
     */
    protected void perform(Context context, String uri)
        throws Exception {

        ServletWebContext swcontext = (ServletWebContext) context;
        
        // Get the underlying request in the case of a multipart wrapper
        HttpServletRequest request = swcontext.getRequest();
        if (request instanceof MultipartRequestWrapper) {
            request = ((MultipartRequestWrapper) request).getRequest();
        }
        
        RequestDispatcher rd =
                swcontext.getContext().getRequestDispatcher(uri);
        rd.forward(request, swcontext.getResponse());
    }


}
