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

import javax.faces.application.FacesMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shale.view.AbstractViewController;
import org.apache.struts.examples.mailreader.User;
import org.apache.struts.examples.mailreader.UserDatabase;

/**
 * <p><code>ViewController</code> for the <code>logon</code> page.</p>
 *
 * $Id$
 */

public class Logon extends BaseViewController {

    
    // -------------------------------------------------------- Static Variables


    /**
     * <p><code>Log</code> instance for this class.</p>
     */
    private static final Log log = LogFactory.getLog(Logon.class);


    // -------------------------------------------------------------- Properties


    /**
     * <p>The password input field.</p>
     */
    private String password = null;
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }


    /**
     * <p>The username input field.</p>
     */
    private String username = null;
    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }


    // ---------------------------------------------------------- Event Handlers


    /**
     * <p>Authenticate this user and proceed based on the results.</p>
     */
    public String logon() {

        if (log.isDebugEnabled()) {
            log.debug("logon(" + username + "," + password + ")");
        }

        User user = getUserDatabase().findUser(username);
        if ((user != null) && (user.getPassword().equals(password))) {
            getState().setUser(user);
            return "success";
        }

        // Append an error message and return to the logon page
        // FIXME - use Commons Resources to localize this message
        getFacesContext().addMessage(null,
          new FacesMessage("Invalid username or password, please try again"));
        return null;

    }


    // -------------------------------------------------- ViewController Methods


}
