/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/AbstractAuthorizeAction.java,v 1.1 2003/10/24 02:35:02 mrdon Exp $
 * $Revision: 1.1 $
 * $Date: 2003/10/24 02:35:02 $
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

package org.apache.struts.chain;


import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.WebContext;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.chain.Constants;
import org.apache.struts.chain.util.ClassUtils;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;


/**
 * <p>Determine whether the requested action is authorized for the current
 * user.  If not, abort chain processing and perferably, return an error
 * message of some kind.</p>
 *
 * @author Don Brown
 * @version $Revision: 1.1 $ $Date: 2003/10/24 02:35:02 $
 */

public abstract class AbstractAuthorizeAction implements Command {


    // ------------------------------------------------------ Instance Variables


    private String actionConfigKey = Constants.ACTION_CONFIG_KEY;


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context attribute key under which the
     * <code>ActionConfig</code> for the currently selected application
     * action is stored.</p>
     */
    public String getActionConfigKey() {

        return (this.actionConfigKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ActionConfig</code> for the currently selected application
     * action is stored.</p>
     *
     * @param actionConfigKey The new context attribute key
     */
    public void setActionConfigKey(String actionConfigKey) {

        this.actionConfigKey = actionConfigKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Determine whether the requested action is authorized for the current
     * user.  If not, abort chain processing and perferably, return an error
     * message of some kind.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> if the user is authorized for the selected
     * action, else <code>true</code> to abort processing.
     */
    public boolean execute(Context context) throws Exception {

        // Retrieve ActionConfig
        ActionConfig actionConfig = (ActionConfig)
            context.get(getActionConfigKey());
            
        // Is this action protected by role requirements?
        String roles[] = actionConfig.getRoleNames();
        if ((roles == null) || (roles.length < 1)) {
            return (false);
        }
        
        return !(isAuthorized(context, roles, actionConfig));
        
    }
    
    
    // ------------------------------------------------------- Protected Methods
    
    
    /**
     * <p>Determine if the action is authorized for the given roles.</p>
     *
     * @param context        The <code>Context</code> for the current request
     * @param roles          An array of valid roles for this request
     * @param actionConfig   The current action mapping
     *
     * @return <code>true</code> if the request is authorized, else 
     * <code>false</code>
     * @exception If the action cannot be tested for authorization
     */
    protected abstract boolean isAuthorized(Context context, String[] roles,    
                                            ActionConfig actionConfig)
              throws Exception;

}
