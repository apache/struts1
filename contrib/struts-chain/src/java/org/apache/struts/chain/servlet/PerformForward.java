/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/servlet/PerformForward.java,v 1.4 2004/02/29 13:21:34 germuska Exp $
 * $Revision: 1.4 $
 * $Date: 2004/02/29 13:21:34 $
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


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.struts.Globals;
import org.apache.struts.chain.AbstractPerformForward;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Perform forwarding or redirection based on the specified
 * <code>ForwardConfig</code> (if any).</p>
 *
 * @version $Revision: 1.4 $ $Date: 2004/02/29 13:21:34 $
 */

public class PerformForward extends AbstractPerformForward {


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Perform the appropriate processing on the specified
     * <code>ForwardConfig</code>.</p>
     *
     * @param context The context for this request
     * @param forwardConfig The forward to be performed
     */
    protected void perform(Context context,ForwardConfig forwardConfig)
        throws Exception {

        ServletWebContext swcontext = (ServletWebContext) context;
        String forwardPath = forwardConfig.getPath();
        String uri = null;

        ModuleConfig moduleConfig  = (ModuleConfig) context.get(getModuleConfigKey());
        // Resolve module-relative paths
        if (forwardPath.startsWith("/")) {
            uri = RequestUtils.forwardURL(swcontext.getRequest(),
                                          forwardConfig,
                                          moduleConfig);
        } else {
            uri = forwardPath;
        }

        // Get the underlying request in the case of a multipart wrapper
        HttpServletRequest request = swcontext.getRequest();
        if (request instanceof MultipartRequestWrapper) {
            request = ((MultipartRequestWrapper) request).getRequest();
        }

        // Perform redirect or forward
        if (forwardConfig.getRedirect()) {
            if (uri.startsWith("/")) {
                uri = request.getContextPath() + uri;
            }
            swcontext.getResponse().sendRedirect
                (swcontext.getResponse().encodeRedirectURL(uri));
        } else {
            RequestDispatcher rd =
                swcontext.getContext().getRequestDispatcher(uri);
            rd.forward(request, swcontext.getResponse());
        }

    }


}
