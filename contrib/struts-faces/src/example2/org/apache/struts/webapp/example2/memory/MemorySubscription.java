/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/example2/org/apache/struts/webapp/example2/memory/MemorySubscription.java,v 1.1 2003/12/31 07:17:48 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/12/31 07:17:48 $
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


package org.apache.struts.webapp.example2.memory;


import org.apache.struts.webapp.example2.Subscription;
import org.apache.struts.webapp.example2.User;


/**
 * <p>Concrete implementation of {@link Subscription} for an in-memory
 * database backed by an XML data file.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/12/31 07:17:48 $
 * @since Struts 1.1
 */

public final class MemorySubscription implements Subscription {


    // ----------------------------------------------------------- Constructors


    /**
     * <p>Construct a new Subscription associated with the specified
     * {@link User}.
     *
     * @param user The user with which we are associated
     * @param host The mail host for this subscription
     */
    public MemorySubscription(MemoryUser user, String host) {

        super();
        this.user = user;
        this.host = host;

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The mail host for this subscription.
     */
    private String host = null;


    /**
     * The {@link User} with which we are associated.
     */
    private MemoryUser user = null;


    // ------------------------------------------------------------- Properties


    /**
     * Should we auto-connect at startup time?
     */
    private boolean autoConnect = false;

    public boolean getAutoConnect() {
        return (this.autoConnect);
    }

    public void setAutoConnect(boolean autoConnect) {
        this.autoConnect = autoConnect;
    }


    /**
     * The mail host for this subscription.
     */
    public String getHost() {
        return (this.host);
    }


    /**
     * The password (in clear text) for this subscription.
     */
    private String password = null;

    public String getPassword() {
        return (this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * The subscription type ("imap" or "pop3").
     */
    private String type = "imap";

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }


    /**
     * The User owning this Subscription.
     */
    public User getUser() {
        return (this.user);
    }


    /**
     * The username for this subscription.
     */
    private String username = null;

    public String getUsername() {
        return (this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("<subscription host=\"");
        sb.append(host);
        sb.append("\" autoConnect=\"");
        sb.append(autoConnect);
        sb.append("\"");
        if (password != null) {
            sb.append(" password=\"");
            sb.append(password);
            sb.append("\"");
        }
        if (type != null) {
            sb.append(" type=\"");
            sb.append(type);
            sb.append("\"");
        }
        if (username != null) {
            sb.append(" username=\"");
            sb.append(username);
            sb.append("\"");
        }
        sb.append(">");
        return (sb.toString());

    }


}
