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
import org.apache.struts.examples.mailreader.Subscription;
import org.apache.struts.examples.mailreader.User;
import org.apache.struts.examples.mailreader.UserDatabase;

/**
 * <p><code>ViewController</code> for the <code>registration</code> page.</p>
 *
 * $Id$
 */

public class Registration extends BaseViewController {

    
    // -------------------------------------------------------- Static Variables


    /**
     * <p>The log instance for this bean.</p>
     */
    private static final Log log = LogFactory.getLog(Registration.class);


    // -------------------------------------------------------------- Properties


    /**
     * <p>The logon username for this user.</p>
     */
    private String username = null;
    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }


    /**
     * <p>The login password for this user.</p>
     */
    private String password = null;
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }


    /**
     * <p>The confirmation password for this user.</p>
     */
    private String password2 = null;
    public String getPassword2() { return this.password2; }
    public void setPassword2(String password2) { this.password2 = password2; }


    /**
     * <p>The full name of this user.</p>
     */
    private String fullName = null;
    public String getFullName() { return this.fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }


    /**
     * <p>The from address of this user.</p>
     */
    private String fromAddress = null;
    public String getFromAddress() { return this.fromAddress; }
    public void setFromAddress(String fromAddress) { this.fromAddress = fromAddress; }


    /**
     * <p>The reply to address of this user.</p>
     */
    private String replyToAddress = null;
    public String getReplyToAddress() { return this.replyToAddress; }
    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }


    /**
     * <p>The set of subscriptions for the currently logged in user.
     */
    private Subscription[] subscriptions = null;
    public Subscription[] getSubscriptions() { return this.subscriptions; }
    private void setSubscriptions(Subscription subscriptions[]) {
        if (log.isTraceEnabled()) {
            if (subscriptions == null) {
                log.trace("Erasing stored subscriptions");
            } else {
                log.trace("Storing " + subscriptions.length + " subscriptions");
            }
        }
        this.subscriptions = subscriptions;
    }


    // ---------------------------------------------------------- Event Handlers


    /**
     * <p>Return to the appropriate page depending on the current mode.</p>
     */
    public String cancel() {

        if ("CREATE".equals(getState().getMode())) {
            return "welcome";
        } else {
            return "menu";
        }

    }


    /**
     * <p>Create a new subscription.</p>
     */
    public String create() {

        State state = getState();
        state.setHost(null);
        state.setMode("CREATE");
        if (log.isTraceEnabled()) {
            log.trace("subscription(" + state.getMode() + "," + state.getHost() + ")");
        }
        return "subscription";

    }


    /**
     * <p>Delete an existing subscription.</p>
     */
    public String delete() {

        State state = getState();
        state.setHost(((Subscription) getBean("current")).getHost());
        state.setMode("DELETE");
        if (log.isTraceEnabled()) {
            log.trace("subscription(" + state.getMode() + "," + state.getHost() + ")");
        }
        return "subscription";

    }


    /**
     * <p>Edit an existing subscription.</p>
     */
    public String edit() {

        State state = getState();
        state.setHost(((Subscription) getBean("current")).getHost());
        state.setMode("EDIT");
        if (log.isTraceEnabled()) {
            log.trace("subscription(" + state.getMode() + "," + state.getHost() + ")");
        }
        return "subscription";

    }


    /**
     * <p>Create or update the user information.</p>
     */
    public String save() {

        UserDatabase database = getUserDatabase();
        String mode = getState().getMode();
        boolean ok = true;
        User user = null;

        if ("CREATE".equals(mode)) {

            // Verify that the proposed username is not already taken
            if (database.findUser(username) != null) {
                // FIXME - localization
                getFacesContext().addMessage("registration:username",
                  new FacesMessage("That username is already taken"));
                ok = false;
            }

            // Verify that the two password values match
            if (!password.equals(password2)) {
                // FIXME - localization
                getFacesContext().addMessage("registration:password2",
                  new FacesMessage("Password values do not match"));
                ok = false;
            }

            // Create a new user with the specified username and password
            // and log the new user on
            if (ok) {
                user = database.createUser(username);
                getState().setUser(user);
            }
            

        } else /* if ("EDIT".equals(mode)) */ {

            // Verify that the two password values match (if entered)
            if ((password != null) && (password.length() > 0) &&
                (password2 != null) && (password2.length() > 0) &&
                !password.equals(password2)) {
                // FIXME - localization
                getFacesContext().addMessage("registration:password2",
                  new FacesMessage("Password values do not match"));
                ok = false;
            }

            // Edit the currently logged on user
            user = getState().getUser();

        }
        
        // Copy the remaining properties
        if (ok) {
            if ((password != null) && (password.length() > 0)) {
                user.setPassword(password);
            }
            user.setFullName(fullName);
            user.setFromAddress(fromAddress);
            user.setReplyToAddress(replyToAddress);
        }

        // Save the updated information to the database
        try {
            database.save();
        } catch (Exception e) {
            getFacesContext().addMessage(null,
              new FacesMessage(e.getMessage()));
            log.error("Database save exception", e);
            ok = false;
        }

        // Return to the input page if there were any errors
        if (!ok) {
            return null;
        } else {
            return "menu";
        }

    }


    // -------------------------------------------------- ViewController Methods


    /**
     * <p>If this is a postback, and we are in EDIT mode, retrieve the
     * subscriptions for the currently logged on user.  This is required
     * in case the user clicked one of the Delete or Edit buttons in the
     * table, so that the correct row can be positioned to before calling
     * the event handler.</p>
     */
    public void init() {

        if (isPostBack()) {
            State state = getState();
            if ("EDIT".equals(state.getMode())) {
                setSubscriptions(state.getUser().getSubscriptions());
            }
        }

    }


    /**
     * <p>If this is not a postack, and we are in EDIT mode, prepopulate
     * the field values for the user registration update form.  Also, if
     * we are in EDIT mode, and this is not a postback, retrieve the
     * subscriptions for the currently logged in user.  (If this is a
     * postback, we will have done this already in <code>init()</code>.)</p>
     */
    public void prepare() {

        State state = getState();

        // If we are not in EDIT mode, there is nothing to do
        if (!"EDIT".equals(state.getMode())) {
            return;
        }

        // The first time in, prepopulate our input field values
        User user = state.getUser();
        if (!isPostBack()) {
            setUsername(user.getUsername());
            setPassword("");
            setPassword2("");
            setFullName(user.getFullName());
            setFromAddress(user.getFromAddress());
            setReplyToAddress(user.getReplyToAddress());
        }

        // Look up the subscriptions for the currently logged on user.
        // If we were using a real database, this is where executing the
        // query and opening the result set would occur
        if (!isPostBack()) {
            setSubscriptions(user.getSubscriptions());
        }

    }


    /**
     * <p>Release our reference to to the retrieved subscriptions (if any).
     * If we were using a real database, this is where closing the result set
     * would go.</p>
     */
    public void destroy() {

        setSubscriptions(null);

    }


}
