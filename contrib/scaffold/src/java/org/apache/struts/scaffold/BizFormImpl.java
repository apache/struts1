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


import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
// import org.apache.struts.validator.ValidatorForm; // Struts 1.1
import com.wintecinc.struts.action.ValidatorForm; // Struts 1.0.x

import org.apache.commons.scaffold.lang.ParameterException;
import org.apache.commons.scaffold.lang.Tokens;
import org.apache.commons.scaffold.util.BizRequest;
import org.apache.commons.scaffold.util.Message;
import org.apache.commons.scaffold.util.MessageImpl;
import org.apache.commons.scaffold.util.Messages;
import org.apache.commons.scaffold.util.MessagesImpl;


/**
 * Concrete implementation of a BizForm.
 * To use this object, you must specify the BizFormBean or a superclass
 * as the default ActionFormBean type for the ActionServlet controller.
 *
 * :FIXME: This implementation can only be used with the default module.
 *
 * @version $Rev$ $Date$
 */
public class BizFormImpl extends BaseForm implements BizForm {
    

 // ------------------------------------------------------ Business Request

    /**
     * The business request object.
     */
    private BizRequest bizRequest = null;

    // see interface for Javadoc
    public BizRequest getBizRequest() {
        return (this.bizRequest);
    }

    // see interface for Javadoc
    public void setBizRequest(BizRequest bizRequest) {
        this.bizRequest = bizRequest;
    }

    // see interface for Javadoc
    public BizRequest createBizRequest(String bizType) {
       
       BizRequest bizRequest = (BizRequest) createObject(bizType) ;

       return bizRequest;   
    }
 
 // ------------------------------------------------------ User Profile

    /**
     * The business request type to create, populate, and validate.
     */
    private Object userProfile = null;

    // see interface for Javadoc
    public Object getUserProfile() {
        return (this.userProfile);
    }

    // see interface for Javadoc
    public void setUserProfile(Object userProfile) {
        this.userProfile = userProfile;
    }

    // see interface for Javadoc
    public String getUserProfileName() {    
         return USER_PROFILE_KEY;    
    }

    // see interface for Javadoc
    public void resetUserProfile(HttpServletRequest request) {

        setUserProfile(
            request.getSession().getAttribute(getUserProfileName())
        );

    } 

 // ------------------------------------------------------ Remote Server
  
    /**
     * The business request type to create, populate, and validate.
     */
    private Object remoteServer = null;

    // see interface for Javadoc
    public Object getRemoteServer() {
        return (this.remoteServer);
    }

    // see interface for Javadoc
    public void setRemoteServer(Object remoteServer) {
        this.remoteServer = remoteServer;
    }

    // see interface for Javadoc
    public String getRemoteServerName() {
         return BizRequest.REMOTE_SERVER_KEY;
     }
 
    // see interface for Javadoc
    public void resetRemoteServer(HttpServletRequest request) {
 
        setRemoteServer(servlet.getServletContext().getAttribute(
            getRemoteServerName()));
 
    }
 
 // --------------------------------------------------------------------
 
    // see interface for Javadoc
   public void addMessages(ActionErrors errors, Messages messages) {
       
       //:TODO: add functionality
       
   }
   
    /**
     * Call superclass reset and other reset* methods in this class.
     */
    public void reset(ActionMapping mapping,
        HttpServletRequest request) {
 
        super.reset(mapping,request); 
        
        if (isMutable()) {
            
            resetUserProfile(request);
            resetRemoteServer(request);
        }   
    }

    /**
     * Call superclass validate.
     * If returns null, return an empty ActionErrors for the
     * benefit of our subclasses.
     */
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {

        // First validate ourself
        ActionErrors errors = super.validate(mapping,request);
        if (null==errors) errors = new ActionErrors();
        
        // Then validate our business request
        if ((isMutable()) && (errors.empty())) {
			
            // Find our business request type
        	BizFormBean bizFormBean = (BizFormBean)
            	mapping.getMappings().getServlet().findFormBean(mapping.getName());
            String bizType = bizFormBean.getBizType();

            if ((errors.empty()) && (null!=bizType)) {

                // Generate and populate our business request
                Messages messages = new MessagesImpl();
                try {
    
                    BizRequest bizRequest = createBizRequest(bizType);
                    // Populate the business request with ourselves
                    // merged with any user profile properties
                    Map properties = merge(getUserProfile());
                    BeanUtils.copyProperties(bizRequest,properties);
                
                }
                catch (Throwable t) { 
                    
                    messages.add(new MessageImpl(
                        Tokens.ERROR_GENERAL,
                        t.toString()
                    ));
                    
                }

                // Validate our business request
                if (messages.isEmpty()) { 
                    messages.add(
                        bizRequest.validate(mapping.getAttribute()));
                }
                if (messages.isEmpty()) { 

                    setBizRequest(bizRequest); 
                }
                else {

                    setBizRequest(null);

                    addMessages(errors,messages);
                }
                
            } // end errors empty
        
        } // end isMutable
        
        return errors;
         
     }
 
} // end BizFormImpl