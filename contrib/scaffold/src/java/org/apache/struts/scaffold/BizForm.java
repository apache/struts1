package org.apache.struts.scaffold;


import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
// import org.apache.struts.validator.ValidatorForm; // Struts 1.1
import com.wintecinc.struts.action.ValidatorForm; // Struts 1.0.x

import org.apache.commons.scaffold.lang.Tokens;
import org.apache.commons.scaffold.util.BizRequest;
import org.apache.commons.scaffold.util.Message;
import org.apache.commons.scaffold.util.MessageImpl;
import org.apache.commons.scaffold.util.Messages;
import org.apache.commons.scaffold.util.MessagesImpl;


/**
 * A BizForm creates and validates a business request 
 * [<code>org.apache.commons.scaffold.BizRequest</code>]
 * as part of its own validation routine.
 * A BizForm is typically procesed by a business action 
 * [<code>org.apache.commons.scaffold.BizAction</code>]
 * which passes the business request to a business service 
 * [<code>org.apache.commons.scaffold.BizService</code>].
 * 
 * @author Ted Husted
 * @author Nationwide Insurance Company
 * @version $Revision: 1.1 $ $Date: 2002/11/24 15:53:05 $
 */
public interface BizForm {
	
    /**
     * The session attribute key for our user profile bean ["USER_PROFILE"].
     * (Suggestion only, may be overridden by presentation framework
     */
    public static String USER_PROFILE_KEY = "USER_PROFILE";

    /**
     * Return our bizRequest property.
     */
    public BizRequest getBizRequest();
    
    /**
     * Set our bizRequest property.
     */
    public void setBizRequest(BizRequest bizRequest);

    /**
     * Factory method to create business request object.
     */
    public BizRequest createBizRequest(String bizType);
 
  
 // ------------------------------------------------------ User Profile

   /**
     * Return our userProfile property.
     */
    public Object getUserProfile();
    
    /**
     * Set our userProfile property.
     */
    public void setUserProfile(Object userProfile);

	/**
     * Returns name of result server to be used by this Action,
	 * [BizRequest.USER_PROFILE_KEY]
	 */
	public String getUserProfileName();

    /**
     * Retrieve from session under known key
     * (<code>ProcessBean.USER_PROFILE_KEY</code>).
     * Override this approach to implement another method (e.g cookies).
     * Also revise UpdateProfile action-mapping to store changes.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    public void resetUserProfile(HttpServletRequest request);
    
    /**
     * Return our remoteServer property.
     */
    public Object getRemoteServer();

    /**
     * Set our remoteServer property.
     */
    public void setRemoteServer(Object remoteServer);

    /**
     * Checks application scope for the remote server object
     * specified by <code>getRemoteServerName</code>
     */
    public void resetRemoteServer(HttpServletRequest request);
 
    /**
     * Copy messages from business tier message class to presentation 
     * tier error class.
     * :TODO: Refactor once everything is based on a root Commons 
     * Messages class.
     */
    public void addMessages(ActionErrors errors, Messages messages);
      
 
} // end BizForm


 /*
  * ====================================================================
  *
  * The Apache Software License, Version 1.1
  *
  * Copyright (c) 2002 The Apache Software Foundation.  All rights
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
  * 4. The names "The Jakarta Project", "Scaffold", and "Apache Software
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

