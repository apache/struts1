/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/upload/MultipartRequestWrapper.java,v 1.3 2002/12/08 07:12:16 rleland Exp $
 * $Revision: 1.3 $
 * $Date: 2002/12/08 07:12:16 $
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

package org.apache.struts.upload;

import java.util.Map;
import java.util.Locale;
import java.util.Vector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.io.IOException;
import java.io.BufferedReader;
import java.security.Principal;
import javax.servlet.ServletInputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

/**
 * This class functions as a wrapper around HttpServletRequest to
 * provide working getParameter methods for multipart requests.  Once
 * Struts requires Servlet 2.3, this class will definately be changed to
 * extend javax.servlet.http.HttpServletRequestWrapper instead of
 * implementing HttpServletRequest.  Servlet 2.3 methods are implemented
 * to return <code>null</code> or do nothing if called on.  Use
 * {@link #getRequest() getRequest} to retrieve the underlying HttpServletRequest
 * object and call on the 2.3 method there, the empty methods are here only
 * so that this will compile with the Servlet 2.3 jar.  This class exists temporarily
 * in the process() method of ActionServlet, just before the ActionForward is processed
 * and just after the Action is performed, the request is set back to the original
 * HttpServletRequest object.
 */
public class MultipartRequestWrapper implements HttpServletRequest {
    
    /**
     * The parameters for this multipart request
     */
    protected Map parameters;
    
    /**
     * The underlying HttpServletRequest
     */
    protected HttpServletRequest request;
    
    public MultipartRequestWrapper(HttpServletRequest request) {
        this.request = request;
        this.parameters = new HashMap();
    }
    
    /**
     * Sets a parameter for this request.  The parameter is actually
     * separate from the request parameters, but calling on the
     * getParameter() methods of this class will work as if they weren't.
     */
    public void setParameter(String name, String value) {
        String[] mValue = (String[]) parameters.get(name);
        if (mValue == null) {
            mValue = new String[0];
        }
        String[] newValue = new String[mValue.length + 1];
        System.arraycopy(mValue, 0, newValue, 0, mValue.length);
        newValue[mValue.length] = value;
        
        parameters.put(name, newValue);
    }
    
    /**
     * Attempts to get a parameter for this request.  It first looks in the
     * underlying HttpServletRequest object for the parameter, and if that
     * doesn't exist it looks for the parameters retrieved from the multipart
     * request
     */
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (value == null) {
            String[] mValue = (String[]) parameters.get(name);
            if ((mValue != null) && (mValue.length > 0)) {
                value = mValue[0];
            }
        }
        return value;
    }
    
    /**
     * Returns the names of the parameters for this request.
     * The enumeration consists of the normal request parameter
     * names plus the parameters read from the multipart request
     */
    public Enumeration getParameterNames() {
        Enumeration baseParams = request.getParameterNames();
        Vector list = new Vector();
        while (baseParams.hasMoreElements()) {
            list.add(baseParams.nextElement());
        }
        Collection multipartParams = parameters.keySet();
        Iterator iterator = multipartParams.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return Collections.enumeration(list);
    }
    
    public String[] getParameterValues(String name) {
        String[] value = request.getParameterValues(name);
        if (value == null) {
            value = (String[]) parameters.get(name);
        }
        return value;
    }
    
    /**
     * Returns the underlying HttpServletRequest for this wrapper
     */
    public HttpServletRequest getRequest() {
        return request;
    }
    
    //WRAPPER IMPLEMENTATIONS OF SERVLET REQUEST METHODS
    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }
    public Enumeration getAttributeNames() {
        return request.getAttributeNames();
    }
    public String getCharacterEncoding() {
        return request.getCharacterEncoding();
    }
    public int getContentLength() {
        return request.getContentLength();
    }
    public String getContentType() {
        return request.getContentType();
    }
    public ServletInputStream getInputStream() throws IOException {
        return request.getInputStream();
    }
    public String getProtocol() {
        return request.getProtocol();
    }
    public String getScheme() {
        return request.getScheme();
    }
    public String getServerName() {
        return request.getServerName();
    }
    public int getServerPort() {
        return request.getServerPort();
    }
    public BufferedReader getReader() throws IOException {
        return request.getReader();
    }
    public String getRemoteAddr() {
        return request.getRemoteAddr();
    }
    public String getRemoteHost() {
        return request.getRemoteHost();
    }
    public void setAttribute(String name, Object o) {
        request.setAttribute(name, o);
    }
    public void removeAttribute(String name) {
        request.removeAttribute(name);
    }
    public Locale getLocale() {
        return request.getLocale();
    }
    public Enumeration getLocales() {
        return request.getLocales();
    }
    public boolean isSecure() {
        return request.isSecure();
    }
    public RequestDispatcher getRequestDispatcher(String path) {
        return request.getRequestDispatcher(path);
    }
    public String getRealPath(String path) {
        return request.getRealPath(path);
    }
    
    //WRAPPER IMPLEMENTATIONS OF HTTPSERVLETREQUEST METHODS
    public String getAuthType() {
        return request.getAuthType();
    }
    public Cookie[] getCookies() {
        return request.getCookies();
    }
    public long getDateHeader(String name) {
        return request.getDateHeader(name);
    }
    public String getHeader(String name) {
        return request.getHeader(name);
    }
    public Enumeration getHeaders(String name) {
        return request.getHeaders(name);
    }
    public Enumeration getHeaderNames() {
        return request.getHeaderNames();
    }
    public int getIntHeader(String name) {
        return request.getIntHeader(name);
    }
    public String getMethod() {
        return request.getMethod();
    }
    public String getPathInfo() {
        return request.getPathInfo();
    }
    public String getPathTranslated() {
        return request.getPathTranslated();
    }
    public String getContextPath() {
        return request.getContextPath();
    }
    public String getQueryString() {
        return request.getQueryString();
    }
    public String getRemoteUser() {
        return request.getRemoteUser();
    }
    public boolean isUserInRole(String user) {
        return request.isUserInRole(user);
    }
    public Principal getUserPrincipal() {
        return request.getUserPrincipal();
    }
    public String getRequestedSessionId() {
        return request.getRequestedSessionId();
    }
    public String getRequestURI() {
        return request.getRequestURI();
    }
    public String getServletPath() {
        return request.getServletPath();
    }
    public HttpSession getSession(boolean create) {
        return request.getSession(create);
    }
    public HttpSession getSession() {
        return request.getSession();
    }
    public boolean isRequestedSessionIdValid() {
        return request.isRequestedSessionIdValid();
    }
    public boolean isRequestedSessionIdFromURL() {
        return request.isRequestedSessionIdFromURL();
    }
    public boolean isRequestedSessionIdFromUrl() {
        return request.isRequestedSessionIdFromUrl();
    }
    
    //SERVLET 2.3 EMPTY METHODS
    /**
     * This method returns null.  To use any Servlet 2.3 methods,
     * call on getRequest() and use that request object.  Once Servlet 2.3
     * is required to build Struts, this will no longer be an issue.
     */
    public Map getParameterMap() {
        return null;
    }
    /**
     * This method does nothing.  To use any Servlet 2.3 methods,
     * call on getRequest() and use that request object.  Once Servlet 2.3
     * is required to build Struts, this will no longer be an issue.
     */
    public void setCharacterEncoding(String encoding) {
        ;
    }
    /**
     * This method returns null.  To use any Servlet 2.3 methods,
     * call on getRequest() and use that request object.  Once Servlet 2.3
     * is required to build Struts, this will no longer be an issue.
     */
    public StringBuffer getRequestURL() {
        return null;
    }
    /**
     * This method returns false.  To use any Servlet 2.3 methods,
     * call on getRequest() and use that request object.  Once Servlet 2.3
     * is required to build Struts, this will no longer be an issue.
     */
    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }
    
    
    
        
        
        
        
    
}
