/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/AbstractSelectModule.java,v 1.1 2003/08/11 04:55:34 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/08/11 04:55:34 $
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


import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.WebContext;
import org.apache.struts.Globals;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;


/**
 * <p>Cache the <code>ModuleConfig</code> and <code>MessageResources</code>
 * instances for the sub-application module to be used for processing
 * this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/08/11 04:55:34 $
 */

public abstract class AbstractSelectModule implements Command {


    // ------------------------------------------------------ Instance Variables


    private String messageResourcesKey = Constants.MESSAGE_RESOURCES_KEY;
    private String moduleConfigKey = Constants.MODULE_CONFIG_KEY;


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context attribute key under which the default
     * <code>MessageResources</code> for the currently selected application
     * module will be stored.</p>
     */
    public String getMessageResourcesKey() {

        return (this.messageResourcesKey);

    }


    /**
     * <p>Set the context attribute key under which the default
     * <code>MessageResources</code> for the currently selected application
     * module will be stored.</p>
     *
     * @param messageResourcesKey The new context attribute key
     */
    public void setMessageResourcesKey(String messageResourcesKey) {

        this.messageResourcesKey = messageResourcesKey;

    }


    /**
     * <p>Return the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * module will be stored.</p>
     */
    public String getModuleConfigKey() {

        return (this.moduleConfigKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * module will be stored.</p>
     *
     * @param moduleConfigKey The new context attribute key
     */
    public void setModuleConfigKey(String moduleConfigKey) {

        this.moduleConfigKey = moduleConfigKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Cache the <code>ModuleConfig</code> and <code>MessageResources</code>
     * instances for the sub-application module to be used for processing
     * this request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @exception IllegalArgumentException if no valid
     *  ModuleConfig or MessageResources can be identified for this request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Identify the module prefix for the current module
        String prefix = getPrefix(context);

        // Cache the corresponding ModuleConfig and MessageResources instances
        WebContext wcontext = (WebContext) context;
        ModuleConfig moduleConfig = (ModuleConfig)
            wcontext.getApplicationScope().get(Globals.MODULE_KEY + prefix);
        if (moduleConfig == null) {
            throw new IllegalArgumentException("No module config for prefix '" +
                                               prefix + "'");
        }
        wcontext.getAttributes().put(getModuleConfigKey(), moduleConfig);
        wcontext.getRequestScope().put(Globals.MODULE_KEY, moduleConfig);
        MessageResources messageResources = (MessageResources)
            wcontext.getApplicationScope().get(Globals.MESSAGES_KEY + prefix);
        if (messageResources != null) {
            wcontext.getAttributes().put(getMessageResourcesKey(),
                                         messageResources);
            wcontext.getRequestScope().put(Globals.MESSAGES_KEY,
                                           messageResources);
        }

        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Calculate and return the module prefix for the module to be
     * selected for this request.</p>
     *
     * @param context The <code>Context</code> for this request
     *
     * @exception IllegalArgumentException if no valid
     *  ModuleConfig or MessageResources can be identified for this request
     */
    protected abstract String getPrefix(Context context);


}
