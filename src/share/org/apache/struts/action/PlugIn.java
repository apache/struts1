/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/PlugIn.java,v 1.1 2002/02/23 22:54:17 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2002/02/23 22:54:17 $
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


package org.apache.struts.action;


import javax.servlet.ServletException;
import org.apache.struts.config.ApplicationConfig;


/**
 * <p>A <strong>PlugIn</strong> is a configuration wrapper for an application
 * specific module or service that needs to be notified about application
 * startup and application shutdown events (i.e. corresponding to when the
 * container calls <code>init()</code> and <code>destroy()</code> on the
 * corresponding {@link ActionServlet} instance).  PlugIn modules can be
 * configured in the <code>struts-config.xml</code> file, without the need
 * to subclass {@link ActionServlet} simply to perform application lifecycle
 * activities.</p>
 *
 * <p>Implementations of this interface must supply a zero-argument constructor
 * for use by {@link ActionServlet}.  Configuration can be accomplished by
 * providing standard JavaBeans property setter methods, which will all have
 * been called before the <code>init()</code> method is invoked.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2002/02/23 22:54:17 $
 */

public interface PlugIn {


    /**
     * <p>Receive notification that our owning sub-application is being
     * shut down.</p>
     */
    public void destroy();


    /**
     * <p>Receive notification that the specified sub-applicaiton is being
     * started up.</p>
     *
     * @param config ApplicationConfig for the sub-application with which
     *  this plug in is associated
     *
     * @exception ServletException if this <code>PlugIn</code> cannot
     *  be successfully initialized
     */
    public void init(ApplicationConfig config) throws ServletException;


}
