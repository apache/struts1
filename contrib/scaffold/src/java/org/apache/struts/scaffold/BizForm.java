/*
 * $Id$ 
 *
 * Copyright 2001-2004 The Apache Software Foundation.
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
 
 
package org.apache.struts.scaffold;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
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
 * @version $Rev$ $Date$
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
     * @param request The HTTP request we are processing
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