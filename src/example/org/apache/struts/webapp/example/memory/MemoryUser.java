/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/memory/MemoryUser.java,v 1.1 2002/03/05 04:23:57 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2002/03/05 04:23:57 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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


package org.apache.struts.webapp.example.memory;


import java.util.HashMap;
import org.apache.struts.webapp.example.Subscription;
import org.apache.struts.webapp.example.User;
import org.apache.struts.webapp.example.UserDatabase;


/**
 * <p>Concrete implementation of {@link User} for an in-memory
 * database backed by an XML data file.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2002/03/05 04:23:57 $
 * @since Struts 1.1
 */

public final class MemoryUser implements User {


    // ----------------------------------------------------------- Constructors


    /**
     * <p>Construct a new User associated with the specified
     * {@link UserDatabase}.
     *
     * @param database The user database with which we are associated
     * @param username The username of this user
     */
    public MemoryUser(MemoryUserDatabase database, String username) {

        super();
        this.database = database;
        this.username = username;

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The {@link UserDatabase} with which we are associated.
     */
    private MemoryUserDatabase database = null;


    /**
     * The {@link Subscription}s for this User, keyed by hostname.
     */
    private HashMap subscriptions = new HashMap();


    /**
     * The username for this user.
     */
    private String username = null;


    // ------------------------------------------------------------- Properties


    /**
     * The {@link UserDatabase} with which we are associated.
     */
    public UserDatabase getDatabase() {
        return (this.database);
    }


    /**
     * The email address from which messages are sent.
     */
    private String fromAddress = null;

    public String getFromAddress() {
        return (this.fromAddress);
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }


    /**
     * The full name of this user, included in from addresses.
     */
    private String fullName = null;

    public String getFullName() {
        return (this.fullName);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    /**
     * The password (in clear text).
     */
    private String password = null;

    public String getPassword() {
        return (this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * The EMAIL address to which replies should be sent.
     */
    private String replyToAddress = null;

    public String getReplyToAddress() {
        return (this.replyToAddress);
    }

    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }


    /**
     * Find and return all {@link Subscription}s associated with this user.
     * If there are none, a zero-length array is returned.
     */
    public Subscription[] getSubscriptions() {

        synchronized (subscriptions) {
            Subscription results[] = new Subscription[subscriptions.size()];
            return ((Subscription[]) subscriptions.values().toArray(results));
        }

    }


    /**
     * The username (must be unique).
     */
    public String getUsername() {
        return (this.username);
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Create and return a new {@link Subscription} associated with this
     * User, for the specified host name.
     *
     * @param host Host name for which to create a subscription
     *
     * @exception IllegalArgumentException if the host name is not unique
     *  for this user
     */
    public Subscription createSubscription(String host) {

        synchronized (subscriptions) {
            if (subscriptions.get(host) != null) {
                throw new IllegalArgumentException("Duplicate host '" + host
                                                   + "' for user '" +
                                                   username + "'");
            }
            MemorySubscription subscription =
                new MemorySubscription(this, host);
            synchronized (subscriptions) {
                subscriptions.put(host, subscription);
            }
            return (subscription);
        }

    }


    /**
     * Find and return the {@link Subscription} associated with the specified
     * host.  If none is found, return <code>null</code>.
     *
     * @param host Host name to look up
     */
    public Subscription findSubscription(String host) {

        synchronized (subscriptions) {
            return ((Subscription) subscriptions.get(host));
        }

    }


    /**
     * Remove the specified {@link Subscription} from being associated
     * with this User.
     *
     * @param subscription Subscription to be removed
     *
     * @exception IllegalArgumentException if the specified subscription is not
     *  associated with this User
     */
    public void removeSubscription(Subscription subscription) {

        if (!(this == subscription.getUser())) {
            throw new IllegalArgumentException
                ("Subscription not associated with this user");
        }
        synchronized (subscriptions) {
            subscriptions.remove(subscription.getHost());
        }

    }


    /**
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("<user username=\"");
        sb.append(username);
        sb.append("\"");
        if (fromAddress != null) {
            sb.append(" fromAddress=\"");
            sb.append(fromAddress);
            sb.append("\"");
        }
        if (fullName != null) {
            sb.append(" fullName=\"");
            sb.append(fullName);
            sb.append("\"");
        }
        if (password != null) {
            sb.append(" password=\"");
            sb.append(password);
            sb.append("\"");
        }
        if (replyToAddress != null) {
            sb.append(" replyToAddress=\"");
            sb.append(replyToAddress);
            sb.append("\"");
        }
        sb.append(">");
        return (sb.toString());

    }


}
