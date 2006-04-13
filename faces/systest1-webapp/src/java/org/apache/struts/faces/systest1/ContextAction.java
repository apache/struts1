/*
 * Copyright 2002,2004 The Apache Software Foundation.
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
 *
 * $Id$
 */

package org.apache.struts.faces.systest1;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;


/**
 * <p>Action to render properties from ExternalContext and the corresponding
 * request and application context objects.</p>
 */

public class ContextAction extends Action {


    private static final Log log = LogFactory.getLog(ContextAction.class);


    private String authType = null;
    private String contextPath = null;
    private Locale locale = null;
    private String pathInfo = null;
    private String remoteUser = null;
    private String servletPath = null;


    public String getAuthType() { return (this.authType); }
    public String getContextPath() { return (this.contextPath); }
    public Locale getLocale() { return (this.locale); }
    public String getPathInfo() { return (this.pathInfo); }
    public String getRemoteUser() { return (this.remoteUser); }
    public String getServletPath() { return (this.servletPath); }


    /**
     * <p>Process an attempted logon.</p>
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        this.authType = request.getAuthType();
        this.contextPath = request.getContextPath();
        this.locale = request.getLocale();
        this.pathInfo = request.getPathInfo();
        this.remoteUser = request.getRemoteUser();
        this.servletPath = request.getServletPath();

        request.setAttribute("contextAction", this);
        return (mapping.findForward("context1"));

    }


}
