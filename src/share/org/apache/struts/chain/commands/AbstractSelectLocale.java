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
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.struts.chain.Constants;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>Select the <code>Locale</code> to be used for this request.</p>
 *
 * @author Craig R. McClanahan
 * @version $Rev$ $Date$
 */

public abstract class AbstractSelectLocale implements Command {


    // ------------------------------------------------------ Instance Variables


    private String localeKey = Constants.LOCALE_KEY;
    private String moduleConfigKey = Constants.MODULE_CONFIG_KEY;


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context attribute key under which the
     * <code>Locale</code> for the current request is stored.</p>
     */
    public String getLocaleKey() {

        return (this.localeKey);

    }


    /**
     * <p>Set the context attribute key under which the
     * <code>Locale</code> for the current request is stored.</p>
     *
     * @param localeKey The new context attribute key
     */
    public void setLocaleKey(String localeKey) {

        this.localeKey = localeKey;

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
     * <p>Select the <code>Locale</code> to be used for this request.</p>
     *
     * @param context The <code>Context</code> for the current request
     *
     * @return <code>false</code> so that processing continues
     */
    public boolean execute(Context context) throws Exception {

        // Are we configured to select Locale automatically?
        ModuleConfig moduleConfig = (ModuleConfig)
            context.get(getModuleConfigKey());
        if (!moduleConfig.getControllerConfig().getLocale()) {
            return (false);
        }

        // Retrieve and cache appropriate Locale for this request
        Locale locale = getLocale(context);
        context.put(getLocaleKey(), locale);

        return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Return the <code>Locale</code> to be used for this request.</p>
     *
     * @param context The <code>Context</code> for this request
     */
    protected abstract Locale getLocale(Context context);


}
