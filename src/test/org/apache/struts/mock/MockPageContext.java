/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/mock/MockPageContext.java,v 1.4 2004/01/13 12:48:53 husted Exp $
 * $Revision: 1.4 $
 * $Date: 2004/01/13 12:48:53 $
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
 *    any, must include the following acknowledgement:
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
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
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


import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;


/**
 * <p>Mock <strong>ServletContext</strong> object for low-level unit tests
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
 * @version $Revision: 1.4 $ $Date: 2004/01/13 12:48:53 $
 */

public class MockPageContext extends PageContext {



    // ----------------------------------------------------------- Constructors


    public MockPageContext() {
        super();
    }


    public MockPageContext(ServletConfig config,
                           ServletRequest request,
                           ServletResponse response) {
        super();
        setValues(config, request, response);
    }


    // ----------------------------------------------------- Instance Variables


    protected ServletContext application = null;
    protected HashMap attributes = new HashMap();    // Page scope attributes
    protected ServletConfig config = null;
    protected ServletRequest request = null;
    protected ServletResponse response = null;
    protected HttpSession session = null;


    // --------------------------------------------------------- Public Methods


    public void setValues(ServletConfig config,
                          ServletRequest request,
                          ServletResponse response) {
        this.config = config;
        if (config != null) {
            this.application = config.getServletContext();
        } else {
            this.application = null;
        }
        this.request = request;
        this.response = response;
        if (request != null) {
            session = ((HttpServletRequest) request).getSession(false);
        } else {
            this.session = null;
        }
    }



    // ---------------------------------------------------- PageContext Methods


    public Object findAttribute(String name) {
        Object value = getAttribute(name, PageContext.PAGE_SCOPE);
        if (value == null) {
            value = getAttribute(name, PageContext.REQUEST_SCOPE);
        }
        if (value == null) {
            value = getAttribute(name, PageContext.SESSION_SCOPE);
        }
        if (value == null) {
            value = getAttribute(name, PageContext.APPLICATION_SCOPE);
        }
        return (value);
    }


    public void forward(String path) {
        throw new UnsupportedOperationException();
    }


    public Object getAttribute(String name) {
        return (getAttribute(name, PageContext.PAGE_SCOPE));
    }


    public Object getAttribute(String name, int scope) {
        if (scope == PageContext.PAGE_SCOPE) {
            return (attributes.get(name));
        } else if (scope == PageContext.REQUEST_SCOPE) {
            if (request != null) {
                return (request.getAttribute(name));
            } else {
                return (null);
            }
        } else if (scope == PageContext.SESSION_SCOPE) {
            if (session != null) {
                return (session.getAttribute(name));
            } else {
                return (null);
            }
        } else if (scope == PageContext.APPLICATION_SCOPE) {
            if (application != null) {
                return (application.getAttribute(name));
            } else {
                return (null);
            }
        } else {
            throw new IllegalArgumentException("Invalid scope " + scope);
        }
    }


    public Enumeration getAttributeNamesInScope(int scope) {
        if (scope == PageContext.PAGE_SCOPE) {
            return (new MockEnumeration(attributes.keySet().iterator()));
        } else if (scope == PageContext.REQUEST_SCOPE) {
            if (request != null) {
                return (request.getAttributeNames());
            } else {
                return
                    (new MockEnumeration(Collections.EMPTY_LIST.iterator()));
            }
        } else if (scope == PageContext.SESSION_SCOPE) {
            if (session != null) {
                return (session.getAttributeNames());
            } else {
                return
                    (new MockEnumeration(Collections.EMPTY_LIST.iterator()));
            }
        } else if (scope == PageContext.APPLICATION_SCOPE) {
            if (application != null) {
                return (application.getAttributeNames());
            } else {
                return
                    (new MockEnumeration(Collections.EMPTY_LIST.iterator()));
            }
        } else {
            throw new IllegalArgumentException("Invalid scope " + scope);
        }
    }


    public int getAttributesScope(String name) {
        if (attributes.get(name) != null) {
            return (PageContext.PAGE_SCOPE);
        } else if ((request != null) &&
                   (request.getAttribute(name) != null)) {
            return (PageContext.REQUEST_SCOPE);
        } else if ((session != null) &&
                   (session.getAttribute(name) != null)) {
            return (PageContext.SESSION_SCOPE);
        } else if ((application != null) &&
                   (application.getAttribute(name) != null)) {
            return (PageContext.APPLICATION_SCOPE);
        } else {
            return (0);
        }
    }


    public Exception getException() {
        throw new UnsupportedOperationException();
    }


    public JspWriter getOut() {
        throw new UnsupportedOperationException();
    }


    public Object getPage() {
        throw new UnsupportedOperationException();
    }


    public ServletRequest getRequest() {
        return (this.request);
    }


    public ServletResponse getResponse() {
        return (this.response);
    }


    public ServletConfig getServletConfig() {
        return (this.config);
    }


    public ServletContext getServletContext() {
        return (this.application);
    }


    public HttpSession getSession() {
        return (this.session);
    }


    public void handlePageException(Exception e) {
        throw new UnsupportedOperationException();
    }


    public void handlePageException(Throwable t) {
        throw new UnsupportedOperationException();
    }


    public void include(String path) {
        throw new UnsupportedOperationException();
    }


    public void initialize(Servlet servlet, ServletRequest request,
                           ServletResponse response, String errorPageURL,
                           boolean needsSession, int bufferSize,
                           boolean autoFlush) {
        throw new UnsupportedOperationException();
    }


    public JspWriter popBody() {
        throw new UnsupportedOperationException();
    }


    public BodyContent pushBody() {
        throw new UnsupportedOperationException();
    }


    public void release() {
        throw new UnsupportedOperationException();
    }


    public void removeAttribute(String name) {
        int scope = getAttributesScope(name);
        if (scope != 0) {
            removeAttribute(name, scope);
        }
    }


    public void removeAttribute(String name, int scope) {
        if (scope == PageContext.PAGE_SCOPE) {
            attributes.remove(name);
        } else if (scope == PageContext.REQUEST_SCOPE) {
            if (request != null) {
                request.removeAttribute(name);
            }
        } else if (scope == PageContext.SESSION_SCOPE) {
            if (session != null) {
                session.removeAttribute(name);
            }
        } else if (scope == PageContext.APPLICATION_SCOPE) {
            if (application != null) {
                application.removeAttribute(name);
            }
        } else {
            throw new IllegalArgumentException("Invalid scope " + scope);
        }
    }


    public void setAttribute(String name, Object value) {
        setAttribute(name, value, PageContext.PAGE_SCOPE);
    }


    public void setAttribute(String name, Object value, int scope) {
        if (scope == PageContext.PAGE_SCOPE) {
            attributes.put(name, value);
        } else if (scope == PageContext.REQUEST_SCOPE) {
            if (request != null) {
                request.setAttribute(name, value);
            }
        } else if (scope == PageContext.SESSION_SCOPE) {
            if (session != null) {
                session.setAttribute(name, value);
            }
        } else if (scope == PageContext.APPLICATION_SCOPE) {
            if (application != null) {
                application.setAttribute(name, value);
            }
        } else {
            throw new IllegalArgumentException("Invalid scope " + scope);
        }
    }


}
