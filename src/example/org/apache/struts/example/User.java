/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/example/Attic/User.java,v 1.2 2000/08/01 20:03:25 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2000/08/01 20:03:25 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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


package org.apache.struts.example;


import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Object that represents a registered user of the mail reader application.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2000/08/01 20:03:25 $
 */

public final class User implements Serializable {


    // =================================================== Instance Variables


    /**
     * The EMAIL address from which messages are sent.
     */
    private String fromAddress = null;


    /**
     * The full name of this user, included in from addresses.
     */
    private String fullName = null;


    /**
     * The password (in clear text).
     */
    private String password = null;


    /**
     * The EMAIL address to which replies should be sent.
     */
    private String replyToAddress = null;


    /**
     * The set of Subscriptions associated with this User.
     */
    private Hashtable subscriptions = new Hashtable();


    /**
     * The username (must be unique).
     */
    private String username = null;


    // =========================================================== Properties


    /**
     * Return the from address.
     */
    public String getFromAddress() {

	return (this.fromAddress);

    }


    /**
     * Set the from address.
     *
     * @param fromAddress The new from address
     */
    public void setFromAddress(String fromAddress) {

	this.fromAddress = fromAddress;

    }



    /**
     * Return the full name.
     */
    public String getFullName() {

	return (this.fullName);

    }


    /**
     * Set the full name.
     *
     * @param fullName The new full name
     */
    public void setFullName(String fullName) {

	this.fullName = fullName;

    }


    /**
     * Return the password.
     */
    public String getPassword() {

	return (this.password);

    }


    /**
     * Set the password.
     *
     * @param password The new password
     */
    public void setPassword(String password) {

	this.password = password;

    }


    /**
     * Return the reply-to address.
     */
    public String getReplyToAddress() {

	return (this.replyToAddress);

    }


    /**
     * Set the reply-to address.
     *
     * @param replyToAddress The new reply-to address
     */
    public void setReplyToAddress(String replyToAddress) {

	this.replyToAddress = replyToAddress;

    }


    /**
     * Return the username.
     */
    public String getUsername() {

	return (this.username);

    }


    /**
     * Set the username.
     *
     * @param username The new username
     */
    public void setUsername(String username) {

	this.username = username;

    }


    // ======================================================= Public Methods


    /**
     * Find and return the Subscription associated with the specified host.
     * If none is found, return <code>null</code>.
     *
     * @param host Host name to look up
     */
    public Subscription findSubscription(String host) {

	if (host == null)
	    return (null);
	return ((Subscription) subscriptions.get(host));

    }


    /**
     * Find and return all Subscriptions associated with this user.  If there
     * are none, a zero-length array is returned.
     */
    public Subscription[] getSubscriptions() {

	synchronized (subscriptions) {
	    Subscription results[] = new Subscription[subscriptions.size()];
	    Enumeration subs = subscriptions.elements();
	    int n = 0;
	    while (subs.hasMoreElements()) {
		results[n++] = (Subscription) subs.nextElement();
	    }
	    return (results);
	}

    }


    // ====================================================== Package Methods


    /**
     * Add the specified Subscription to the set associated with this User.
     *
     * @param subscription The subscription to add
     */
    void addSubscription(Subscription subscription) {

	subscriptions.put(subscription.getHost(), subscription);

    }


    /**
     * Remove the specified Subscription from the set associated with
     * this User.
     *
     * @param subscription The subscription to remove
     */
    void removeSubscription(Subscription subscription) {

	subscriptions.remove(subscription.getHost());

    }


}
