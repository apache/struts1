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
//import org.apache.struts.examples.mailreader.Subscription;
import org.apache.struts.examples.mailreader.User;
import org.apache.struts.examples.mailreader.UserDatabase;

/**
 * <p><code>ViewController</code> for the <code>subscription</code> page.</p>
 *
 * $Id$
 */

public class Subscription extends BaseViewController {

    
    // -------------------------------------------------------- Static Variables


    /**
     * <p>The log instance for this bean.</p>
     */
    private static final Log log = LogFactory.getLog(Subscription.class);


    // -------------------------------------------------------------- Properties


    /**
     * <p>The host for this subscription.</p>
     */
    private String host = null;
    public String getHost() { return this.host; }
    public void setHost(String host) { this.host = host; }


    /**
     * <p>The logon username for this subscription.</p>
     */
    private String username = null;
    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }


    /**
     * <p>The login password for this subscription.</p>
     */
    private String password = null;
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }


    /**
     * <p>The type for this subscription.</p>
     */
    private String type = null;
    public String getType() { return this.type; }
    public void setType(String type) { this.type = type; }


    /**
     * <p>The autoConnect state for this subscription.</p>
     */
    private boolean autoConnect = false;
    public boolean isAutoConnect() { return this.autoConnect; }
    public void setAutoConnect(boolean autoConnect) {
        this.autoConnect = autoConnect;
    }


    // ---------------------------------------------------------- Event Handlers


    /**
     * <p>Return to the appropriate page depending on the current mode.</p>
     */
    public String cancel() {

        if ("CREATE".equals(getState().getMode())) {
            return "welcome";
        } else {
            getState().setMode("EDIT");
            return "registration";
        }

    }


    /**
     * <p>Create or update the user information.</p>
     */
    public String save() {

        State state = getState();
        UserDatabase database = getUserDatabase();
        String mode = state.getMode();
        boolean ok = true;
        User user = state.getUser();
        org.apache.struts.examples.mailreader.Subscription subscription =
         user.findSubscription(state.getHost());

        if ("CREATE".equals(mode)) {

            // Verify that the proposed hostname is not already taken
            if (user.findSubscription(host) != null) {
                // FIXME - localization
                getFacesContext().addMessage("subscription:host",
                  new FacesMessage("That hostname is already defined"));
                return null;
            }

            // Create a new subscription
            subscription = user.createSubscription(host);
            
        } else if ("DELETE".equals(mode)) {

            user.removeSubscription(subscription);
            try {
                database.save();
            } catch (Exception e) {
                getFacesContext().addMessage(null,
                  new FacesMessage(e.getMessage()));
                log.error("Database save exception", e);
                return null;
            }
            state.setMode("EDIT");
            return "registration";

        } else /* if ("EDIT".equals(mode)) */ {

            ; // No special action required

        }
        
        // Copy the remaining properties
        subscription.setUsername(username);
        subscription.setPassword(password);
        subscription.setType(type);
        subscription.setAutoConnect(autoConnect);

        // Save the updated information to the database
        try {
            database.save();
        } catch (Exception e) {
            getFacesContext().addMessage(null,
              new FacesMessage(e.getMessage()));
            log.error("Database save exception", e);
            return null;
        }

        // Return to the registration page
        state.setMode("EDIT");
        return "registration";

    }


    // -------------------------------------------------- ViewController Methods


    /**
     * <p>If this is not a postack, and we are in DELETE or EDIT mode,
     * prepopulate the field values for the subscription update form.</p>
     */
    public void prepare() {

        State state = getState();

        // If we are not in DELETE or EDIT mode, there is nothing to do
        if (!"DELETE".equals(state.getMode()) &&
            !"EDIT".equals(state.getMode())) {
            return;
        }

        // The first time in, prepopulate our input field values
        User user = state.getUser();
        org.apache.struts.examples.mailreader.Subscription subscription =
         user.findSubscription(state.getHost());
        if (!isPostBack()) {
            setHost(subscription.getHost());
            setUsername(subscription.getUsername());
            setPassword(subscription.getPassword());
            setType(subscription.getType());
            setAutoConnect(subscription.getAutoConnect());
        }

    }


}
