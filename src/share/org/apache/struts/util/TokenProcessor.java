/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/TokenProcessor.java,v 1.2 2003/04/25 03:15:49 dgraham Exp $
 * $Revision: 1.2 $
 * $Date: 2003/04/25 03:15:49 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
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

package org.apache.struts.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.taglib.html.Constants;

/**
 * TokenProcessor is responsible for handling all token related functionality.  The 
 * methods in this class are synchronized to protect token processing from multiple
 * threads.  Servlet containers are allowed to return a different HttpSession object
 * for two threads accessing the same session so it is not possible to synchronize 
 * on the session.
 * 
 * @since Struts 1.1
 * @author Craig R. McClanahan
 * @author David Graham
 */
public class TokenProcessor {

    /**
     * The singleton instance of this class.
     */
    private static TokenProcessor instance = new TokenProcessor();

    /**
     * Retrieves the singleton instance of this class.
     */
    public static TokenProcessor getInstance() {
        return instance;
    }

    /**
     * Protected constructor for TokenProcessor.  Use TokenProcessor.getInstance()
     * to obtain a reference to the processor.
     */
    protected TokenProcessor() {
        super();
    }

    /**
     * Return <code>true</code> if there is a transaction token stored in
     * the user's current session, and the value submitted as a request
     * parameter with this action matches it.  Returns <code>false</code>
     * under any of the following circumstances:
     * <ul>
     * <li>No session associated with this request</li>
     * <li>No transaction token saved in the session</li>
     * <li>No transaction token included as a request parameter</li>
     * <li>The included transaction token value does not match the
     *     transaction token in the user's session</li>
     * </ul>
     *
     * @param request The servlet request we are processing
     */
    public synchronized boolean isTokenValid(HttpServletRequest request) {
        return this.isTokenValid(request, false);
    }

    /**
     * Return <code>true</code> if there is a transaction token stored in
     * the user's current session, and the value submitted as a request
     * parameter with this action matches it.  Returns <code>false</code>
     * <ul>
     * <li>No session associated with this request</li>
     * <li>No transaction token saved in the session</li>
     * <li>No transaction token included as a request parameter</li>
     * <li>The included transaction token value does not match the
     *     transaction token in the user's session</li>
     * </ul>
     *
     * @param request The servlet request we are processing
     * @param reset Should we reset the token after checking it?
     */
    public synchronized boolean isTokenValid(
        HttpServletRequest request,
        boolean reset) {

        // Retrieve the current session for this request
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        // Retrieve the transaction token from this session, and
        // reset it if requested
        String saved = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
        if (saved == null) {
            return false;
        }

        if (reset) {
            this.resetToken(request);
        }

        // Retrieve the transaction token included in this request
        String token = request.getParameter(Constants.TOKEN_KEY);
        if (token == null) {
            return false;
        }

        return saved.equals(token);
    }

    /**
     * Reset the saved transaction token in the user's session.  This
     * indicates that transactional token checking will not be needed
     * on the next request that is submitted.
     *
     * @param request The servlet request we are processing
     */
    public synchronized void resetToken(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(Globals.TRANSACTION_TOKEN_KEY);
    }

    /**
     * Save a new transaction token in the user's current session, creating
     * a new session if necessary.
     *
     * @param request The servlet request we are processing
     */
    public synchronized void saveToken(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String token = generateToken(request);
        if (token != null) {
            session.setAttribute(Globals.TRANSACTION_TOKEN_KEY, token);
        }

    }

    /**
     * Generate a new transaction token, to be used for enforcing a single
     * request for a particular transaction.
     * 
     * @param request The request we are processing
     */
    public String generateToken(HttpServletRequest request) {

        HttpSession session = request.getSession();
        try {
            byte id[] = session.getId().getBytes();
            byte now[] = new Long(System.currentTimeMillis()).toString().getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(id);
            md.update(now);
            return this.toHex(md.digest());

        } catch (IllegalStateException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }

    /**
     * Convert a byte array to a String of hexadecimal digits and return it.
     *<p>
     *<strong>WARNING</strong>: This method is not part of TokenProcessor's
     *public API.  It's provided for backward compatibility only.
     *</p>
     * @param buffer The byte array to be converted
     */
    public String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer();
        String s = null;
        for (int i = 0; i < buffer.length; i++) {
            s = Integer.toHexString((int) buffer[i] & 0xff);
            if (s.length() < 2) {
                sb.append('0');
            }
            sb.append(s);
        }
        return sb.toString();
    }

}
