/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/mock/MockServletConfig.java,v 1.4 2004/01/13 12:48:53 husted Exp $
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


import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;


/**
 * <p>Mock <strong>ServletConfig</strong> object for low-level unit tests
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

public class MockServletConfig implements ServletConfig {



    // ----------------------------------------------------------- Constructors


    public MockServletConfig() {
        super();
    }


    public MockServletConfig(ServletContext context) {
        super();
        setServletContext(context);
    }


    // ----------------------------------------------------- Instance Variables


    protected ServletContext context = null;
    protected HashMap parameters = new HashMap();


    // --------------------------------------------------------- Public Methods


    public void addInitParameter(String name, String value) {
        parameters.put(name, value);
    }


    public void setServletContext(ServletContext context) {
        this.context = context;
    }


    // ------------------------------------------------- ServletContext Methods


    public String getInitParameter(String name) {
        return ((String) parameters.get(name));
    }


    public Enumeration getInitParameterNames() {
        return (new MockEnumeration(parameters.keySet().iterator()));
    }


    public ServletContext getServletContext() {
        return (this.context);
    }


    public String getServletName() {
        return ("action");
    }


}
