/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/taglib/Attic/LifecycleListener.java,v 1.1 2003/03/07 03:22:44 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/07 03:22:44 $
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


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Properties;
import javax.faces.FactoryFinder;
import javax.faces.lifecycle.ApplicationHandler;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.faces.render.Renderer;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.faces.Constants;
import org.apache.struts.faces.application.ApplicationHandlerImpl;
import org.apache.struts.faces.application.FacesRequestProcessor;


/**
 * <p><code>LifecycleListener</code> to initialize the required lifecycle
 * objects, and perform other one-time setup tasks, for the
 * <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/03/07 03:22:44 $
 */

public class LifecycleListener
    implements ServletContextListener, ServletContextAttributeListener {


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The {@link ApplicationHandler} to be used for request processing.</p>
     */
    protected ApplicationHandler handler = null;


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


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Process an "attribute added" event.</p>
     *
     * @param event The <code>ServletContextAttributeEvent</code>
     *  that has occurred
     */
    public void attributeAdded(ServletContextAttributeEvent event) {

        String name = event.getName();
        log.info("attributeAdded(" + name + "," + event.getValue() + ")");
        if (name.equals(Action.ACTION_SERVLET_KEY)) {
            servlet = (ActionServlet) event.getValue();
            handler = createHandler(servlet);
        } else if (name.startsWith(Action.APPLICATION_KEY)) {
            createProcessor(servlet,
                            (ModuleConfig) event.getValue());
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
        if (name.equals(Action.ACTION_SERVLET_KEY)) {
            handler = null;
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
        if (Action.ACTION_SERVLET_KEY.equals(name)) {
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
        configureComponents();
        configureRenderers();

    }


    // -------------------------------------------------------- Private Methods


    /**
     * <p>Add our <code>UIComponent</code>s to the default
     * <code>RenderKit</code>.</p>
     */
    private void configureComponents() {

        if (log.isDebugEnabled()) {
            log.debug("Configuring UIComponent instances");
        }

        // Acquire a reference to the default render kit
        RenderKitFactory factory = (RenderKitFactory)
            FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
        RenderKit renderKit =
            factory.getRenderKit(RenderKitFactory.DEFAULT_RENDER_KIT);

        // Load the configuration properties
        Properties props = new Properties();
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream
                ("org/apache/struts/faces/component/StrutsFacesComponents.properties");
            if (is == null) {
                return;
            }
            props.load(is);
        } catch (IOException e) {
            log.error("I/O error reading component configuration file", e);
            return;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    ;
                }
                is = null;
            }
        }

        // Register the specified UIComponent instances
        if (log.isTraceEnabled()) {
            log.trace("Processing component types list");
        }
        String list = props.getProperty("componentTypes");
        while (list.length() > 0) {
            String componentType = null;
            int i = list.indexOf(',');
            if (i < 0) {
                componentType = list.trim();
                list = "";
            } else {
                componentType = list.substring(0, i).trim();
                list = list.substring(i + 1);
            }
            if (componentType.length() > 0) {
                if (log.isTraceEnabled()) {
                    log.trace("Registering componentType '" + componentType + "'");
                }
                try {
                    String className = props.getProperty
                        ("componentType." + componentType);
                    Class clazz = Class.forName(className);
                    renderKit.addComponentClass(clazz);
                } catch (Exception e) {
                    log.error("Cannot instantiate componentType '" +
                              componentType + "'", e);
                }
            }
        }

    }


    /**
     * <p>Add our <code>Renderer</code>s to the default
     * <code>RenderKit</code>.</p>
     */
    private void configureRenderers() {

        if (log.isDebugEnabled()) {
            log.debug("Configuring Renderer instances");
        }

        // Acquire a reference to the default render kit
        RenderKitFactory factory = (RenderKitFactory)
            FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
        RenderKit renderKit =
            factory.getRenderKit(RenderKitFactory.DEFAULT_RENDER_KIT);

        // Load the configuration properties
        Properties props = new Properties();
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream
                ("org/apache/struts/faces/renderer/StrutsFacesRenderers.properties");
            if (is == null) {
                return;
            }
            props.load(is);
        } catch (IOException e) {
            log.error("I/O error reading renderer configuration file", e);
            return;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    ;
                }
                is = null;
            }
        }

        // Register the specified Renderer instances
        log.trace("Processing renderer types list");
        String list = props.getProperty("rendererTypes");
        while (list.length() > 0) {
            String rendererType = null;
            int i = list.indexOf(',');
            if (i < 0) {
                rendererType = list.trim();
                list = "";
            } else {
                rendererType = list.substring(0, i).trim();
                list = list.substring(i + 1);
            }
            if (rendererType.length() > 0) {
                if (log.isTraceEnabled()) {
                    log.trace("Registering rendererType '" + rendererType + "'");
                }
                try {
                    String className = props.getProperty
                        ("rendererType." + rendererType);
                    Class clazz = Class.forName(className);
                    renderKit.addRenderer(rendererType,
                                          (Renderer) clazz.newInstance());
                } catch (Exception e) {
                    log.error("Cannot instantiate rendererType '" +
                              rendererType + "'", e);
                }
            }
        }

    }


    /**
     * <p>Create and register an <code>ApplicationHandler</code> instance.</p>
     *
     * @param servlet ActionServlet instance we are associated with
     */
    private ApplicationHandler createHandler(ActionServlet servlet) {

        if (log.isDebugEnabled()) {
            log.debug("Configuring ApplicationHandler instance");
        }
        ApplicationHandler handler =
            new ApplicationHandlerImpl(servlet);
        LifecycleFactory factory = (LifecycleFactory)
            FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        Lifecycle lifecycle =
            factory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
        lifecycle.setApplicationHandler(handler);
        return (handler);

    }



    /**
     * <p>Create and register a <code>RequestProcessor</code> instance
     * for the specified application module.</p>
     *
     * @param servlet ActionServlet instance we are associated with
     * @param modConfig ModuleConfig instance we are associated with
     */
    private RequestProcessor createProcessor(ActionServlet servlet,
                                             ModuleConfig modConfig) {

        if (log.isDebugEnabled()) {
            log.debug("Configuring RequestProcessor instance");
        }
        RequestProcessor processor = new FacesRequestProcessor();
        try {
            processor.init(servlet, modConfig);
            servlet.getServletContext().setAttribute
                (Action.REQUEST_PROCESSOR_KEY + modConfig.getPrefix(),
                 processor);
        } catch (Exception e) {
            log.error("createProcessor()", e);
        }
        return (processor);

    }


}
