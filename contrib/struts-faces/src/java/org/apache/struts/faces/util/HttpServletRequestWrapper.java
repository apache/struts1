/*
 * Copyright 2002,2004 The Apache Software Foundation.
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
package org.apache.struts.faces.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>Concrete implementation of <code>HttpServletRequest</code> that
 * that wrapps the <code>ServletPath</code> with an URI, that was detected
 * by <code>ActionServlet</code> to forward to a standard <code>FacesServlet</code>.
 *
 */
public class HttpServletRequestWrapper implements HttpServletRequest {

	// ------------------------------------------------------ Instance Variables
	
	protected HttpServletRequest original = null;
	protected String servletPath = null;

	// ------------------------------------------------------------ Constructors


	/**
	 * <p>Construct a new <code>HttpServletRequest</code> instance
	 * and an URI, which is used by <code>FacesServlet</code>.</p>
	 *
	 * @param request Original default <code>HttpServletRequest</code>
	 *
	 * @param servletPat the new ServletPath for a <code>FacesServlet</code>
	 *
	 */
	
	public HttpServletRequestWrapper(HttpServletRequest request, String servletPath){
		this.original = request;
		this.servletPath = servletPath;
	}

	// ---------------------------------------------------------- Public Methods


	/**
	 * <p>Returns the new <code>ServletPath</code> needed by a FacesServlet.</p>
	 *
	 */
     public String getServletPath() {
		// TODO Auto-generated method stub
		return this.servletPath;
	}

	// ---------------------------------------------------------- Delegate Methods
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return original.equals(obj);
	}

	/**
	 * @param arg0
	 * @return
	 */
	public Object getAttribute(String arg0) {
		return original.getAttribute(arg0);
	}

	/**
	 * @return
	 */
	public Enumeration getAttributeNames() {
		return original.getAttributeNames();
	}

	/**
	 * @return
	 */
	public String getAuthType() {
		return original.getAuthType();
	}

	/**
	 * @return
	 */
	public String getCharacterEncoding() {
		return original.getCharacterEncoding();
	}

	/**
	 * @return
	 */
	public int getContentLength() {
		return original.getContentLength();
	}

	/**
	 * @return
	 */
	public String getContentType() {
		return original.getContentType();
	}

	/**
	 * @return
	 */
	public String getContextPath() {
		return original.getContextPath();
	}

	/**
	 * @return
	 */
	public Cookie[] getCookies() {
		return original.getCookies();
	}

	/**
	 * @param arg0
	 * @return
	 */
	public long getDateHeader(String arg0) {
		return original.getDateHeader(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 */
	public String getHeader(String arg0) {
		return original.getHeader(arg0);
	}

	/**
	 * @return
	 */
	public Enumeration getHeaderNames() {
		return original.getHeaderNames();
	}

	/**
	 * @param arg0
	 * @return
	 */
	public Enumeration getHeaders(String arg0) {
		return original.getHeaders(arg0);
	}

	/**
	 * @return
	 * @throws java.io.IOException
	 */
	public ServletInputStream getInputStream() throws IOException {
		return original.getInputStream();
	}

	/**
	 * @param arg0
	 * @return
	 */
	public int getIntHeader(String arg0) {
		return original.getIntHeader(arg0);
	}

	/**
	 * @return
	 */
	public Locale getLocale() {
		return original.getLocale();
	}

	/**
	 * @return
	 */
	public Enumeration getLocales() {
		return original.getLocales();
	}

	/**
	 * @return
	 */
	public String getMethod() {
		return original.getMethod();
	}

	/**
	 * @param arg0
	 * @return
	 */
	public String getParameter(String arg0) {
		return original.getParameter(arg0);
	}

	/**
	 * @return
	 */
	public Map getParameterMap() {
		return original.getParameterMap();
	}

	/**
	 * @return
	 */
	public Enumeration getParameterNames() {
		return original.getParameterNames();
	}

	/**
	 * @param arg0
	 * @return
	 */
	public String[] getParameterValues(String arg0) {
		return original.getParameterValues(arg0);
	}

	/**
	 * @return
	 */
	public String getPathInfo() {
		return original.getPathInfo();
	}

	/**
	 * @return
	 */
	public String getPathTranslated() {
		return original.getPathTranslated();
	}

	/**
	 * @return
	 */
	public String getProtocol() {
		return original.getProtocol();
	}

	/**
	 * @return
	 */
	public String getQueryString() {
		return original.getQueryString();
	}

	/**
	 * @return
	 * @throws java.io.IOException
	 */
	public BufferedReader getReader() throws IOException {
		return original.getReader();
	}

	/**
	 * @param arg0
	 * @return
	 */
	public String getRealPath(String arg0) {
		return original.getRealPath(arg0);
	}

	/**
	 * @return
	 */
	public String getRemoteAddr() {
		return original.getRemoteAddr();
	}

	/**
	 * @return
	 */
	public String getRemoteHost() {
		return original.getRemoteHost();
	}

	/**
	 * @return
	 */
	public String getRemoteUser() {
		return original.getRemoteUser();
	}

	/**
	 * @param arg0
	 * @return
	 */
	public RequestDispatcher getRequestDispatcher(String arg0) {
		return original.getRequestDispatcher(arg0);
	}

	/**
	 * @return
	 */
	public String getRequestedSessionId() {
		return original.getRequestedSessionId();
	}

	/**
	 * @return
	 */
	public String getRequestURI() {
		return original.getRequestURI();
	}

	/**
	 * @return
	 */
	public StringBuffer getRequestURL() {
		return original.getRequestURL();
	}

	/**
	 * @return
	 */
	public String getScheme() {
		return original.getScheme();
	}

	/**
	 * @return
	 */
	public String getServerName() {
		return original.getServerName();
	}

	/**
	 * @return
	 */
	public int getServerPort() {
		return original.getServerPort();
	}

	/**
	 * @return
	 */
	public HttpSession getSession() {
		return original.getSession();
	}

	/**
	 * @param arg0
	 * @return
	 */
	public HttpSession getSession(boolean arg0) {
		return original.getSession(arg0);
	}

	/**
	 * @return
	 */
	public Principal getUserPrincipal() {
		return original.getUserPrincipal();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return original.hashCode();
	}

	/**
	 * @return
	 */
	public boolean isRequestedSessionIdFromCookie() {
		return original.isRequestedSessionIdFromCookie();
	}

	/**
	 * @return
	 */
	public boolean isRequestedSessionIdFromUrl() {
		return original.isRequestedSessionIdFromUrl();
	}

	/**
	 * @return
	 */
	public boolean isRequestedSessionIdFromURL() {
		return original.isRequestedSessionIdFromURL();
	}

	/**
	 * @return
	 */
	public boolean isRequestedSessionIdValid() {
		return original.isRequestedSessionIdValid();
	}

	/**
	 * @return
	 */
	public boolean isSecure() {
		return original.isSecure();
	}

	/**
	 * @param arg0
	 * @return
	 */
	public boolean isUserInRole(String arg0) {
		return original.isUserInRole(arg0);
	}

	/**
	 * @param arg0
	 */
	public void removeAttribute(String arg0) {
		original.removeAttribute(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public void setAttribute(String arg0, Object arg1) {
		original.setAttribute(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @throws java.io.UnsupportedEncodingException
	 */
	public void setCharacterEncoding(String arg0)
		throws UnsupportedEncodingException {
		original.setCharacterEncoding(arg0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return original.toString();
	}
}
