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

/**
 * <p><code>ViewController</code> for the <code>index</code> page.</p>
 *
 * $Id$
 */

public class MainMenu extends BaseViewController {
    
    
    // -------------------------------------------------------------- Properties


    // ---------------------------------------------------------- Event Handlers


    /**
     * <p>Set the appropriate mode and forward to the registration page.</p>
     */
    public String edit() {

        getState().setMode("EDIT");
        return "registration";

    }


    /**
     * <p>Remove the currently logged user and return to the welcome page.</p>
     */
    public String logoff() {

        getState().setUser(null);
        return "welcome";

    }


    // -------------------------------------------------- ViewController Methods


}
