/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/taglib/Attic/LifecycleListener.java,v 1.7 2004/01/18 13:43:13 husted Exp $
 * $Revision: 1.7 $
 * $Date: 2004/01/18 13:43:13 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002-2003 The Apache Software Foundation.  All rights
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

package org.apache.struts.faces.taglib;


import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.ViewHandler;
import javax.faces.el.PropertyResolver;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.faces.application.FacesRequestProcessor;
import org.apache.struts.faces.application.PropertyResolverImpl;
import org.apache.struts.faces.application.ViewHandlerImpl;


/**
 * <p><code>LifecycleListener</code> to initialize the required lifecycle
 * objects, and perform other one-time setup tasks, for the
 * <em>Struts-Faces Integration Library</em>.</p>
 *
 * @version $Revision: 1.7 $ $Date: 2004/01/18 13:43:13 $
 */

public class LifecycleListener
    implements ServletContextListener, ServletContextAttributeListener {


    // ------------------------------------------------------ Instance Variables


    /**
     * <p>The logger for this instance.</p>
     */
    protected static Log log = LogFactory.getLog(LifecycleListener.class);


    /**
     * <p>The <code>ActionServlet</code> we are associated with.</p>
     */
    protected ActionServlet servlet = null;


    /**
     * <p>The <code>ServletContext</code> we are associated with.</p>
     */
    protected ServletContext servletContext = null;


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Process an "attribute added" event.</p>
     *
     * @param event The <code>ServletContextAttributeEvent</code>
     *  that has occurred
     */
    public void attributeAdded(ServletContextAttributeEvent event) {

        String name = event.getName();
        log.info("attributeAdded(" + name + "," + event.getValue() + ")");
        if (name.equals(Globals.ACTION_SERVLET_KEY)) {
            servlet = (ActionServlet) event.getValue();
            createPropertyResolver();
            // createViewHandler();
        }

    }


    /**
     * <p>Process an "attribute removed" event.</p>
     *
     * @param event The <code>ServletContextAttributeEvent</code>
     *  that has occurred
     */
    public void attributeRemoved(ServletContextAttributeEvent event) {

        String name = event.getName();
        log.info("attributeRemoved(" + name + ")");
        if (name.equals(Globals.ACTION_SERVLET_KEY)) {
            servlet = null;
        }

    }


    /**
     * <p>Process an "attribute replaced" event.</p>
     *
     * @param event The <code>ServletContextAttributeEvent</code>
     *  that has occurred
     */
    public void attributeReplaced(ServletContextAttributeEvent event) {

        String name = event.getName();
        log.info("attributeReplaced(" + name + ")");
        if (Globals.ACTION_SERVLET_KEY.equals(name)) {
            servlet = (ActionServlet) event.getValue();
        }

    }


    /**
     * <p>Process a "context destroyed" event.</p>
     *
     * @param event The <code>ServletContextEvent</code> that has occurred
     */
    public void contextDestroyed(ServletContextEvent event) {

        log.info("contextDestroyed()");
        servletContext = null;

    }


    /**
     * <p>Process a "context initialized" event.</p>
     *
     * @param event The <code>ServletContextEvent</code> that has occurred
     */
    public void contextInitialized(ServletContextEvent event) {

        log.info("contextInitialized()");
        servletContext = event.getServletContext();

    }


    // --------------------------------------------------------- Private Methods


    /**
     * <p>Create and register a <code>PropertyResolver</code> instance
     * that supports <code>DynaBean</code>s.</p>
     */
    private PropertyResolver createPropertyResolver() {

        if (log.isDebugEnabled()) {
            log.debug("Configuring custom PropertyResolver");
        }
        ApplicationFactory factory = (ApplicationFactory)
            FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        Application application = factory.getApplication();
        PropertyResolver resolver =
            new PropertyResolverImpl(application.getPropertyResolver());
        application.setPropertyResolver(resolver);
        return (resolver);

    }


    /**
     * <p>Create and register a <code>ViewHandler</code> instance
     * that supports using include instead of forward.</p>
     */
    private ViewHandler createViewHandler() {

        if (log.isDebugEnabled()) {
            log.debug("Configuring custom ViewHandler");
        }
        ApplicationFactory factory = (ApplicationFactory)
            FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        Application application = factory.getApplication();
        ViewHandler handler =
            new ViewHandlerImpl(application.getViewHandler());
        application.setViewHandler(handler);
        return (handler);

    }


}
