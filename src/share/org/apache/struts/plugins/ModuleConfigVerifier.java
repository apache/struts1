/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/plugins/ModuleConfigVerifier.java,v 1.2 2003/03/23 05:46:23 dgraham Exp $
 * $Revision: 1.2 $
 * $Date: 2003/03/23 05:46:23 $
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


package org.apache.struts.plugins;


import javax.servlet.ServletException;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.MessageResourcesConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;
import org.apache.struts.util.RequestUtils;


/**
 * <p>Convenient implementation of {@link PlugIn} that performs as many
 * verification tests on the information stored in the {@link ModuleConfig}
 * for this application module as is practical.  Based on the setting of the
 * <code>fatal</code> property (which defaults to <code>true</code>), the
 * detection of any such errors will cause a <code>ServletException</code>
 * to be thrown from the <code>init()</code> method, which will ultimately
 * cause the initialization of your Struts controller servlet to fail.</p>
 *
 * <p>Under all circumstances, errors that are detected will be logged via
 * calls to <code>ServletContext.log()</code>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2003/03/23 05:46:23 $
 * @since Struts 1.1
 */

public class ModuleConfigVerifier implements PlugIn {


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The {@link ModuleConfig} instance for our application module.</p>
     */
    protected ModuleConfig config = null;


    /**
     * <p>The {@link ActionServlet} instance we are associated with.</p>
     */
    protected ActionServlet servlet = null;


    // ------------------------------------------------------------- Properties


    /**
     * <p>Should the existence of configuration errors be fatal.</p>
     */
    private boolean fatal = true;


    /**
     * <p>Return the "configuation errors are fatal" flag.</p>
     */
    public boolean isFatal() {

        return (this.fatal);

    }


    /**
     * <p>Set the "configuration errors are fatal" flag.</p>
     *
     * @param fatal The new flag value
     */
    public void setFatal(boolean fatal) {

        this.fatal = fatal;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Receive notification that our owning module is being
     * shut down.</p>
     */
    public void destroy() {

        ; // No action required

    }


    /**
     * <p>Receive notification that the specified module is being
     * started up.</p>
     *
     * @param servlet ActionServlet that is managing all the
     *  modules in this web application
     * @param config ModuleConfig for the module with which
     *  this plug-in is associated
     *
     * @exception ServletException if this <code>PlugIn</code> cannot
     *  be successfully initialized
     */
    public void init(ActionServlet servlet, ModuleConfig config)
        throws ServletException {

        this.servlet = servlet;
        this.config = config;
        boolean ok = true;
        log(servlet.getInternal().getMessage("configVerifying"));

        // Perform detailed validations of each portion of ModuleConfig
        /*
        if (!verifyActionConfigs()) {
            ok = false;
        }
        */
        if (!verifyActionMappingClass()) {
            ok = false;
        }
        /*
        if (!verifyControllerConfig()) {
            ok = false;
        }
        if (!verifyDataSourceConfigs()) {
            ok = false;
        }
        if (!verifyExceptionConfigs()) {
            ok = false;
        }
        if (!verifyFormBeanConfigs()) {
            ok = false;
        }
        */
        if (!verifyForwardConfigs()) {
            ok = false;
        }
        if (!verifyMessageResourcesConfigs()) {
            ok = false;
        }
        if (!verifyPlugInConfigs()) {
            ok = false;
        }

        // Throw an exception on a fatal error
        log(servlet.getInternal().getMessage("configCompleted"));
        if (!ok && isFatal()) {
            throw new ServletException
                (servlet.getInternal().getMessage("configFatal"));
        }


    }



    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Log the specified message to our servlet context log, after a
     * header including the module prefix.</p>
     *
     * @param message The message to be logged
     */
    protected void log(String message) {

        String output = "[" + config.getPrefix() + "]: " + message;
        servlet.log(output);

    }


    /**
     * <p>Return <code>true</code> if information returned by
     * <code>config.getActionMappingClass()</code> is all valid;
     * otherwise, log error messages and return <code>false</code>.</p>
     */
    protected boolean verifyActionMappingClass() {

        String amcName = config.getActionMappingClass();
        if (amcName == null) {
            log(servlet.getInternal().getMessage
                ("verifyActionMappingClass.missing"));
            return (false);
        }
        try {
            Class amcClass = RequestUtils.applicationClass(amcName);
        } catch (ClassNotFoundException e) {
            log(servlet.getInternal().getMessage
                ("verifyActionMappingClass.invalid", amcName));
            return (false);
        }
        return (true);

    }


    /**
     * <p>Return <code>true</code> if information returned by
     * <code>config.findForwardConfigs() is all valid;
     * otherwise, log error messages and return <code>false</code>.</p>
     */
    protected boolean verifyForwardConfigs() {

        boolean ok = true;
        ForwardConfig fcs[] = config.findForwardConfigs();
        for (int i = 0; i < fcs.length; i++) {
            String path = fcs[i].getPath();
            if (path == null) {
                log(servlet.getInternal().getMessage
                    ("verifyForwardConfigs.missing",
                     fcs[i].getName()));
                ok = false;
            } else if (!path.startsWith("/")) {
                log(servlet.getInternal().getMessage
                    ("verifyForwardConfigs.invalid", path,
                     fcs[i].getName()));
            }
        }
        return (ok);

    }


    /**
     * <p>Return <code>true</code> if information returned by
     * <code>config.findMessageResourcesConfigs() is all valid;
     * otherwise, log error messages and return <code>false</code>.</p>
     */
    protected boolean verifyMessageResourcesConfigs() {

        boolean ok = true;
        MessageResourcesConfig mrcs[] = config.findMessageResourcesConfigs();
        for (int i = 0; i < mrcs.length; i++) {
            String factory = mrcs[i].getFactory();
            if (factory == null) {
                log(servlet.getInternal().getMessage
                    ("verifyMessageResourcesConfigs.missing"));
                ok = false;
            } else {
                try {
                    Class clazz = RequestUtils.applicationClass(factory);
                } catch (ClassNotFoundException e) {
                    log(servlet.getInternal().getMessage
                        ("verifyMessageResourcesConfigs.invalid",
                         factory));
                    ok = false;
                }
            }
            String key = mrcs[i].getKey();
            if (key == null) {
                log(servlet.getInternal().getMessage
                    ("verifyMessageResourcesConfigs.key"));
            }
        }
        return (ok);

    }


    /**
     * <p>Return <code>true</code> if information returned by
     * <code>config.findPluginConfigs() is all valid;
     * otherwise, log error messages and return <code>false</code>.</p>
     */
    protected boolean verifyPlugInConfigs() {

        boolean ok = true;
        PlugInConfig pics[] = config.findPlugInConfigs();
        for (int i = 0; i < pics.length; i++) {
            String className = pics[i].getClassName();
            if (className == null) {
                log(servlet.getInternal().getMessage
                    ("verifyPlugInConfigs.missing"));
                ok = false;
            } else {
                try {
                    Class clazz = RequestUtils.applicationClass(className);
                } catch (ClassNotFoundException e) {
                    log(servlet.getInternal().getMessage
                        ("verifyPlugInConfigs.invalid", className));
                    ok = false;
                }
            }
        }
        return (ok);

    }


}
