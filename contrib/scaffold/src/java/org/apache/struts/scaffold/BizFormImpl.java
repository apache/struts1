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
 * @version $Revision: 1.4 $ $Date: 2004/01/18 13:43:09 $
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

