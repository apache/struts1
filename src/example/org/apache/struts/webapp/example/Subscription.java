/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/Subscription.java,v 1.1 2001/04/11 02:10:02 rleland Exp $
 * $Revision: 1.1 $
 * $Date: 2001/04/11 02:10:02 $
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


package org.apache.struts.webapp.example;


import java.io.Serializable;


/**
 * Object that represents a subscription of a registered user on a
 * specific mail server.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2001/04/11 02:10:02 $
 */

public final class Subscription implements Serializable {


    // =================================================== Instance Variables


    /**
     * Should we auto-connect at startup time?
     */
    private boolean autoConnect = false;


    /**
     * The mail host for this subscription.
     */
    private String host = null;


    /**
     * The password (in clear text).
     */
    private String password = null;


    /**
     * The subscription type ("imap" or "pop3").
     */
    private String type = "imap";


    /**
     * The User owning this Subscription.
     */
    private User user = null;


    /**
     * The username (must be unique).
     */
    private String username = null;


    // =========================================================== Properties


    /**
     * Return the auto-connect flag.
     */
    public boolean getAutoConnect() {

        return (this.autoConnect);

    }


    /**
     * Set the auto-connect flag.
     *
     * @param autoConnect The new auto-connect flag
     */
    public void setAutoConnect(boolean autoConnect) {

        this.autoConnect = autoConnect;

    }


    /**
     * Return the host name.
     */
    public String getHost() {

	return (this.host);

    }


    /**
     * Set the host name.
     *
     * @param host The new host name
     */
    public void setHost(String host) {

	if ((this.host != null) && (user != null))
	    user.removeSubscription(this);
	this.host = host;
	if ((this.host != null) && (user != null))
	    user.addSubscription(this);

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
     * Return the subscription type.
     */
    public String getType() {

	return (this.type);

    }


    /**
     * Set the subscription type.
     *
     * @param type The new subscription type
     */
    public void setType(String type) {

	this.type = type;

    }


    /**
     * Return the User owning this Subscription.
     */
    public User getUser() {

	return (this.user);

    }


    /**
     * Set the User owning this Subscription.
     *
     * @param user The new User
     */
    public void setUser(User user) {

	if ((this.host != null) && (this.user != null))
	    this.user.removeSubscription(this);
	this.user = user;
	if ((this.host != null) && (this.user != null))
	    this.user.addSubscription(this);

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
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("Subscription[username=");
        sb.append(username);
        if (host != null) {
            sb.append(", host=");
            sb.append(host);
        }
        if (user != null) {
            sb.append(", user=");
            sb.append(user.getUsername());
        }
        sb.append(", autoConnect=");
        sb.append(autoConnect);
        sb.append("]");
        return (sb.toString());

    }


}
