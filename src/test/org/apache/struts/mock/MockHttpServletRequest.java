/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/mock/MockHttpServletRequest.java,v 1.4 2004/01/10 21:03:39 dgraham Exp $
 * $Revision: 1.4 $
 * $Date: 2004/01/10 21:03:39 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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


package org.apache.struts.mock;


import java.io.BufferedReader;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



/**
 * <p>Mock <strong>HttpServletRequest</strong> object for low-level unit tests
 * of Struts controller components.  Coarser grained tests should be
 * implemented in terms of the Cactus framework, instead of the mock
 * object classes.</p>
 *
 * <p><strong>WARNING</strong> - Only the minimal set of methods needed to
 * create unit tests is provided, plus additional methods to configure this
 * object as necessary.  Methods for unsupported operations will throw
 * <code>UnsupportedOperationException</code>.</p>
 *
 * <p><strong>WARNING</strong> - Because unit tests operate in a single
 * threaded environment, no synchronization is performed.</p>
 *
 * @version $Revision: 1.4 $ $Date: 2004/01/10 21:03:39 $
 */

public class MockHttpServletRequest implements HttpServletRequest {



    // ----------------------------------------------------------- Constructors


    public MockHttpServletRequest() {
        super();
    }


    public MockHttpServletRequest(HttpSession session) {
        super();
        setHttpSession(session);
    }


    public MockHttpServletRequest(String contextPath, String servletPath,
                                  String pathInfo, String queryString) {
        super();
        setPathElements(contextPath, servletPath, pathInfo, queryString);
    }



    public MockHttpServletRequest(String contextPath, String servletPath,
                                  String pathInfo, String queryString,
                                  HttpSession session) {
        super();
        setPathElements(contextPath, servletPath, pathInfo, queryString);
        setHttpSession(session);
    }



    // ----------------------------------------------------- Instance Variables


    /**
     * The set of request attributes.
     */
    protected HashMap attributes = new HashMap();


    /**
     * The context path for this request.
     */
    protected String contextPath = null;


    /**
     * The preferred locale for this request.
     */
    protected Locale locale = null;


    /**
     * The set of arrays of parameter values, keyed by parameter name.
     */
    protected HashMap parameters = new HashMap();


    /**
     * The extra path information for this request.
     */
    protected String pathInfo = null;


    /**
     * The authenticated user for this request.
     */
    protected Principal principal = null;


    /**
     * The query string for this request.
     */
    protected String queryString = null;


    /**
     * The servlet path for this request.
     */
    protected String servletPath = null;


    /**
     * The HttpSession with which we are associated.
     */
    protected HttpSession session = null;


    // --------------------------------------------------------- Public Methods


    public void addParameter(String name, String value) {
        String values[] = (String[]) parameters.get(name);
        if (values == null) {
            String results[] = new String[] { value };
            parameters.put(name, results);
            return;
        }
        String results[] = new String[values.length + 1];
        System.arraycopy(values, 0, results, 0, values.length);
        results[values.length] = value;
        parameters.put(name, results);
    }


    public void setHttpSession(HttpSession session) {
        this.session = session;
    }


    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public void setPathElements(String contextPath, String servletPath,
                                String pathInfo, String queryString) {

        this.contextPath = contextPath;
        this.servletPath = servletPath;
        this.pathInfo = pathInfo;
        this.queryString = queryString;

    }


    public void setUserPrincipal(Principal principal) {
        this.principal = principal;
    }



    // --------------------------------------------- HttpServletRequest Methods


    public String getAuthType() {
        throw new UnsupportedOperationException();
    }


    public String getContextPath() {
        return (contextPath);
    }


    public Cookie[] getCookies() {
        throw new UnsupportedOperationException();
    }


    public long getDateHeader(String name) {
        throw new UnsupportedOperationException();
    }


    public String getHeader(String name) {
        throw new UnsupportedOperationException();
    }


    public Enumeration getHeaderNames() {
        throw new UnsupportedOperationException();
    }


    public Enumeration getHeaders(String name) {
        throw new UnsupportedOperationException();
    }


    public int getIntHeader(String name) {
        throw new UnsupportedOperationException();
    }


    public String getMethod() {
        throw new UnsupportedOperationException();
    }


    public String getPathInfo() {
        return (pathInfo);
    }


    public String getPathTranslated() {
        throw new UnsupportedOperationException();
    }


    public String getQueryString() {
        return (queryString);
    }


    public String getRemoteUser() {
        if (principal != null) {
            return (principal.getName());
        } else {
            return (null);
        }
    }


    public String getRequestedSessionId() {
        throw new UnsupportedOperationException();
    }


    public String getRequestURI() {
        StringBuffer sb = new StringBuffer();
        if (contextPath != null) {
            sb.append(contextPath);
        }
        if (servletPath != null) {
            sb.append(servletPath);
        }
        if (pathInfo != null) {
            sb.append(pathInfo);
        }
        if (sb.length() > 0) {
            return (sb.toString());
        }
        throw new UnsupportedOperationException();
    }


    public StringBuffer getRequestURL() {
        throw new UnsupportedOperationException();
    }


    public String getServletPath() {
        return (servletPath);
    }


    public HttpSession getSession() {
        return (getSession(true));
    }


    public HttpSession getSession(boolean create) {
        if (create && (session == null)) {
            throw new UnsupportedOperationException();
        }
        return (session);
    }


    public Principal getUserPrincipal() {
        return (principal);
    }


    public boolean isRequestedSessionIdFromCookie() {
        throw new UnsupportedOperationException();
    }


    public boolean isRequestedSessionIdFromUrl() {
        throw new UnsupportedOperationException();
    }


    public boolean isRequestedSessionIdFromURL() {
        throw new UnsupportedOperationException();
    }


    public boolean isRequestedSessionIdValid() {
        throw new UnsupportedOperationException();
    }


    public boolean isUserInRole(String role) {
        if ((principal != null) && (principal instanceof MockPrincipal)) {
            return (((MockPrincipal) principal).isUserInRole(role));
        } else {
            return (false);
        }
    }


    // ------------------------------------------------- ServletRequest Methods


    public Object getAttribute(String name) {
        return (attributes.get(name));
    }


    public Enumeration getAttributeNames() {
        return (new MockEnumeration(attributes.keySet().iterator()));
    }


    public String getCharacterEncoding() {
        throw new UnsupportedOperationException();
    }


    public int getContentLength() {
        throw new UnsupportedOperationException();
    }


    public String getContentType() {
        throw new UnsupportedOperationException();
    }


    public ServletInputStream getInputStream() {
        throw new UnsupportedOperationException();
    }


    public Locale getLocale() {
        return (locale);
    }


    public Enumeration getLocales() {
        throw new UnsupportedOperationException();
    }


    public String getParameter(String name) {
        String values[] = (String[]) parameters.get(name);
        if (values != null) {
            return (values[0]);
        } else {
            return (null);
        }
    }


    public Map getParameterMap() {
        return (parameters);
    }


    public Enumeration getParameterNames() {
        return (new MockEnumeration(parameters.keySet().iterator()));
    }


    public String[] getParameterValues(String name) {
        return ((String[]) parameters.get(name));
    }


    public String getProtocol() {
        throw new UnsupportedOperationException();
    }


    public BufferedReader getReader() {
        throw new UnsupportedOperationException();
    }


    public String getRealPath(String path) {
        throw new UnsupportedOperationException();
    }


    public String getRemoteAddr() {
        throw new UnsupportedOperationException();
    }


    public String getRemoteHost() {
        throw new UnsupportedOperationException();
    }


    public RequestDispatcher getRequestDispatcher(String path) {
        throw new UnsupportedOperationException();
    }


    public String getScheme() {
        return ("http");
    }


    public String getServerName() {
        return ("localhost");
    }


    public int getServerPort() {
        return (8080);
    }


    public boolean isSecure() {
        return (false);
    }


    public void removeAttribute(String name) {
        attributes.remove(name);
    }


    public void setAttribute(String name, Object value) {
        if (value == null) {
            attributes.remove(name);
        } else {
            attributes.put(name, value);
        }
    }


    public void setCharacterEncoding(String name) {
        throw new UnsupportedOperationException();
    }


}
