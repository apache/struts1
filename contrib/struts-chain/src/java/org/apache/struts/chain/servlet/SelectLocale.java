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

package org.apache.struts.chain.servlet;


import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.struts.Globals;
import org.apache.struts.chain.AbstractSelectLocale;


/**
 * <p>Select the <code>Locale</code> to be used for this request.</p>
 *
 * @version $Revision: 1.4 $ $Date: 2004/06/24 01:27:31 $
 */

public class SelectLocale extends AbstractSelectLocale {


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Return the <code>Locale</code> to be used for this request.</p>
     *
     * @param context The <code>Context</code> for this request
     */
    protected Locale getLocale(Context context) {

        ServletWebContext swcontext = (ServletWebContext) context;

        // Has a Locale already been selected?
        HttpSession session = swcontext.getRequest().getSession();
        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        if (locale != null) {
            return (locale);
        }

        // Select and cache the Locale to be used
        locale = swcontext.getRequest().getLocale();
        if (locale == null) {
            locale = Locale.getDefault();
        }
        session.setAttribute(Globals.LOCALE_KEY, locale);
        return (locale);

    }


}
