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
import org.apache.struts.chain.AbstractSelectAction;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Cache the <code>ActionConfig</code> instance for the
 * action to be used for processing this request.</p>
 *
 * @version $Revision: 1.5 $ $Date: 2004/06/24 01:26:41 $
 */

public class SelectAction extends AbstractSelectAction {


    // ------------------------------------------------------- Protected Methods


    protected String getPath(Context context) {

        ServletWebContext swcontext = (ServletWebContext) context;
        HttpServletRequest request = swcontext.getRequest();
        String path = null;
        boolean extension = false;

        // For prefix matching, match on the path info
        path = (String) request.getAttribute(Constants.INCLUDE_PATH_INFO);
        if (path == null) {
            path = request.getPathInfo();
        }

        // For extension matching, match on the servlet path
        if (path == null) {
            path =
                (String) request.getAttribute(Constants.INCLUDE_SERVLET_PATH);
            if (path == null) {
                path = request.getServletPath();
            }
            if (path == null) {
                throw new IllegalArgumentException
                    ("No path information in request");
            }
            extension = true;
        }

        // Strip the module prefix and extension (if any)
        ModuleConfig moduleConfig = (ModuleConfig)
            swcontext.get(getModuleConfigKey());
        String prefix = moduleConfig.getPrefix();
        if (!path.startsWith(prefix)) {
            throw new IllegalArgumentException("Path does not start with '" +
                                               prefix + "'");
        }
        path = path.substring(prefix.length());
        if (extension) {
            int slash = path.lastIndexOf("/");
            int period = path.lastIndexOf(".");
            if ((period >= 0) && (period > slash)) {
                path = path.substring(0, period);
            }
        }
        return (path);

    }


}
