/*
 * Copyright 1999-2002,2004 The Apache Software Foundation.
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

package org.apache.shale.examples.mailreader;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import org.apache.shale.view.AbstractViewController;
import org.apache.struts.examples.mailreader.UserDatabase;

/**
 * <p>Convenience abstract base <code>ViewController</code>
 * for the Mail Reader example application.</p>
 *
 * $Id$
 */

public abstract class BaseViewController extends AbstractViewController {
    
    
    // -------------------------------------------------------------- Properties


    // ---------------------------------------------------------- Event Handlers


    // -------------------------------------------------- ViewController Methods


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Return the named bean from request, session, or application scope.
     * If this is a managed bean, it might also get created as a side effect.
     * Return <code>null</code> if no such bean can be found or created.</p>
     *
     * @param name Name of the desired bean
     */
    protected Object getBean(String name) {

        FacesContext context = getFacesContext();
        return context.getApplication().getVariableResolver().
          resolveVariable(context, name);

    }


    /**
     * <p>Return the <code>FacesContext</code> instance for the
     * current request.</p>
     */
    protected FacesContext getFacesContext() {

        return FacesContext.getCurrentInstance();

    }


    /**
     * <p>Return the state saving bean for this user's transaction state.</p>
     */
    protected State getState() {

        return (State) getBean("state");

    }


    /**
     * <p>Return the DAO for our user database.</p>
     */
    protected UserDatabase getUserDatabase() {

        return (UserDatabase) getBean("database");

    }


}
