/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/servlet/SelectAction.java,v 1.2 2003/09/29 06:55:08 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2003/09/29 06:55:08 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.struts.chain.servlet;


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.struts.chain.AbstractSelectAction;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Cache the <code>ActionConfig</code> instance for the
 * action to be used for processing this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2003/09/29 06:55:08 $
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
