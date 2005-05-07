/*
 * Copyright 2003,2004 The Apache Software Foundation.
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

package org.apache.struts.chain.commands;


import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Select the <code>Locale</code> to be used for this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Rev$ $Date$
 */

public abstract class AbstractSelectLocale extends ActionCommandBase {

    private static final Log log = LogFactory.getLog(AbstractSelectLocale.class);

    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Select the <code>Locale</code> to be used for this request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(ActionContext actionCtx) throws Exception {

        // Are we configured to select Locale automatically?
        log.trace("retrieve config...");
        ModuleConfig moduleConfig = actionCtx.getModuleConfig();
        if (!moduleConfig.getControllerConfig().getLocale()) {
            log.debug("module is not configured for a specific locale; nothing to do");
            return (false);
        }

        // Retrieve and cache appropriate Locale for this request
        Locale locale = getLocale(actionCtx);
        log.debug("set context locale to " + locale);
        actionCtx.setLocale(locale);

        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Return the <code>Locale</code> to be used for this request.</p>
     *
     * @param context The <code>Context</code> for this request
     */
    protected abstract Locale getLocale(ActionContext context);


}
