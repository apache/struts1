/*
 * $Id$ 
 *
 * Copyright 2000-2004 Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts.webapp.example;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.config.ModuleConfig;


/**
 * Check for a valid User logged on in the current session.  If there is no
 * such user, forward control to the logon page.
 *
 * @version $Rev$ $Date$
 */
public final class CheckLogonTag extends TagSupport {


    // --------------------------------------------------- Instance Variables


    /**
     * The key of the session-scope bean we look for.
     */
    private String name = Constants.USER_KEY;


    /**
     * Path to use if a login is needed.
     */
    private static String LOGIN_PATH = "/Logon.do";


    /**
     * The action to which we should forward for the user to log on.
     */
    private String page = LOGIN_PATH;


    // ------------------------------------------------------- Public Methods


    /**
     * Defer our checking until the end of this tag is encountered.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	   return (SKIP_BODY);

    }


    /**
     * Perform our logged-in user check by looking for the existence of
     * a session scope bean under the specified name.  If this bean is not
     * present, control is forwarded to the specified logon page.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {
    
    	// Is there a valid user logged on?
    	boolean valid = false;
    	HttpSession session = pageContext.getSession();
    	if ((session != null) && (session.getAttribute(name) != null)) {
    	    valid = true;
        }
    
        // Forward control based on the results
        if (valid) {
            return (EVAL_PAGE);
        } else {
            ModuleConfig config =
                (ModuleConfig) pageContext.getServletContext().getAttribute(
                    org.apache.struts.Globals.MODULE_KEY);
            
                try {
                    pageContext.forward(config.getPrefix() + page);
                } catch (ServletException e) {
                    throw new JspException(e.toString());
                } catch (IOException e) {
                    throw new JspException(e.toString());
                }
             
            return (SKIP_PAGE);
        }
    
    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        this.name = Constants.USER_KEY;
        this.page = LOGIN_PATH;

    }

}
