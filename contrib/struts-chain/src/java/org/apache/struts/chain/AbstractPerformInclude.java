/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-chain/src/java/org/apache/struts/chain/AbstractPerformInclude.java,v 1.2 2003/10/25 01:12:38 mrdon Exp $
 * $Revision: 1.2 $
 * $Date: 2003/10/25 01:12:38 $
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
import org.apache.struts.Globals;
import org.apache.commons.chain.web.WebContext;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Perform forwarding or redirection based on the specified
 * <code>String</code> (if any).</p>
 *
 * @author Don Brown
 * @version $Revision: 1.2 $ $Date: 2003/10/25 01:12:38 $
 */

public abstract class AbstractPerformInclude implements Command {


    // ------------------------------------------------------ Instance Variables


    private String includeKey = Constants.INCLUDE_KEY;
    private String moduleConfigKey = Constants.MODULE_CONFIG_KEY;


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context attribute key under which the
     * include uri for the currently selected application
     * action is stored.</p>
     */
    public String getIncludeKey() {

        return (this.includeKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * include uri for the currently selected application
     * action is stored.</p>
     *
     * @param includeKey The new context attribute key
     */
    public void setIncludeKey(String includeKey) {

        this.includeKey = includeKey;

    }
    
    /**
     * <p>Return the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * module is stored.</p>
     */
    public String getModuleConfigKey() {

        return (this.moduleConfigKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>ModuleConfig</code> for the currently selected application
     * module is stored.</p>
     *
     * @param moduleConfigKey The new context attribute key
     */
    public void setModuleConfigKey(String moduleConfigKey) {

        this.moduleConfigKey = moduleConfigKey;

    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Perform an include based on the specified
     * include uri (if any).</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>true</code> so that processing completes
     */
    public boolean execute(Context context) throws Exception {

        // Retrieve module config instance
        WebContext wcontext = (WebContext) context;
        ModuleConfig moduleConfig = (ModuleConfig)
            wcontext.get(getModuleConfigKey());
        
        // Is there an include to be performed?
        String include = (String)
            context.get(getIncludeKey());
        if (include == null) {
            return (false);
        }
        
        // Determine the currect uri
        String uri = moduleConfig.getPrefix() + include;

        // Perform the appropriate processing on this include uri
        perform(context, uri);
        return (true);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Perform the appropriate processing on the specified
     * include uri.</p>
     *
     * @param context The context for this request
     * @param include The forward to be performed
     */
    protected abstract void perform(Context context,
                                    String include)
        throws Exception;


}
