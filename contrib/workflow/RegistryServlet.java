/*
 * $Id$ 
 *
 * Copyright 1999-2004 The Apache Software Foundation.
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

package org.apache.struts.action;


import java.io.InputStream;


import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.digester.Digester;
import org.apache.commons.workflow.Activity;
import org.apache.commons.workflow.Context;
import org.apache.commons.workflow.ContextEvent;
import org.apache.commons.workflow.ContextListener;
import org.apache.commons.workflow.Step;
import org.apache.commons.workflow.StepException;
import org.apache.commons.workflow.base.BaseRuleSet;
import org.apache.commons.workflow.core.CoreRuleSet;
import org.apache.commons.workflow.io.IoRuleSet;
import org.apache.commons.workflow.web.WebContext;
import org.apache.commons.workflow.web.WebRuleSet;

import simple.workflow.Registry;
import simple.workflow.base.BaseRegistry;


/**
 * <p>Demonstration servlet that illustrates one way that workflow support can
 * be integrated into Struts. This servlet is used to instantiate the Registry
 * of Activities and expose them to the application. Processing of the Activities
 * is expected to take place in an <code>Activity</code> Action.</p>
 *
 * <p>Note: The functionality of this Servlet could be replaced by the new
 * ServiceManager.</p>
 *
 * <p>Initialization parameters (defaults in square brackets):</p>
 * <ul>
 * <li><strong>registry</strong> - Context-relative resource path to the
 *     definition file for the Activities to be initialized by this servlet.</li>
 * <li><strong>registry-key</strong> - Name of the session attribute under
 *     which the <code>Registry</code> implementation is stored.
 *     [org.apache.commons.workflow.REGISTRY]</li>
 * <li><strong>context-key</strong> - Name of the session attribute under
 *     which our current <code>Context</code> implementation is stored.
 *     [org.apache.commons.workflow.web.CONTEXT]</li>
 * <li><strong>debug</strong> - The debugging detail level for this
 *     servlet, which controls how much information is logged.  [0]</li>
 * <li><strong>detail</strong> - The debugging detail level for the Digester
 *     we utilize in <code>initMapping()</code>, which logs to System.out
 *     instead of the servlet log.  [0]</li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @version $Rev$ $Date$
 */


public class RegistryServlet extends HttpServlet {


// ----------------------------------------------------- Instance Variables


    /**
     * The Activity for this servlet
     * //:TODO: TEMPORARY
     */
    protected Activity activity = null;


    /**
     * Default Name of the session attribute under which
     * the current <code>Activity</code> is stored.
     */
    public static String ACTIVITY =
        "org.apache.commons.workflow.ACTIVITY";


    /**
     * Name of the session attribute under
     * which the <code>Activity</code> is stored.
     */
    protected String activityKey = ACTIVITY;


    /**
     * Default Name of the session attribute under which
     *  a current <code>Context</code> is stored.
     */
    public static String CONTEXT =
        "org.apache.commons.workflow.CONTEXT";


    /**
     * Name of the session attribute under
     * which a current <code>Context</code> is stored.
     */
    protected String contextKey = CONTEXT;


    /**
     * The Registry for this servlet
     */
    protected Registry registry = null;


    /**
     * Default Name of the application attribute under which
     * the <code>Registry</code> is stored.
     */
    public static String REGISTRY =
        "org.apache.commons.workflow.REGISTRY";


    /**
     * Name of the session attribute under
     * which the <code>Registry</code> is stored.
     */
    protected String registryKey = REGISTRY;

    /**
     * Default name for Registry XML document
     */
    protected String registryPath = "WEB-INF/workflow.xml";


    /**
     * The debugging detail level for this servlet.
     */
    private int debug = 0;



// --------------------------------------------------------- Public Properties


    /**
     * Set the <code>Activity</code> associated with this instance.
     *
     * @param activity The new associated Activity
     */
    public void setActivity(Activity activity) {

        this.activity = activity;
        getServletContext().setAttribute(ACTIVITY,this.activity);

    }


    /**
     * Return the name of the session attribute under which a current
     * <code>Context</code> is stored.
     */
    public String getContextKey() {

        return contextKey;

    }


    /**
     * Set the name of the session attribute under which a current
     * <code>Context</code> is stored.
     */
    protected void setContextKey(String contextKey) {

        this.contextKey = contextKey;

    }


    /**
     * Return Name of the application attribute under which this
     * servlet is exposed.
     */
    public String getRegistryKey() {

        return registryKey;

    }


    /**
     * Set the name of the session attribute under which a current
     * <code>Registry</code> is stored.
     */
    protected void setRegistryKey(String registryKey) {

        this.registryKey = registryKey;

    }


    /**
     * Return the Registry for this servlet
     */
    protected Registry getRegistry() {

        return (this.registry);

    }


    /**
     * Set the <code>Registry</code> associated with this instance.
     * Called by Digester via parse.
     *
     * @param regisry The new associated Registry
     */
    public void setRegistry(Registry registry) {

        this.registry = registry;
        initRegistry();
        getServletContext().setAttribute(
            getRegistryKey(),getRegistry());

    }



// --------------------------------------------------------- Public Methods


    /**
     * Perform any optimizations or related tasks on Registry once it is
     * populated.
     */
    protected void initRegistry() {

        synchronized (this.registry) {

            registry.initActivities();

        }

    }


    /**
     * Gracefully shut down any activity instances we have created.
     */
    protected void destroyRegistry() {

        synchronized (this.registry) {

            registry.clearActivities();

        }

    }


    /**
     * Perform a graceful shutdown of this servlet instance.
     */
    public void destroy() {

        destroyRegistry();

    }


    /**
     * Perform a graceful startup of this servlet instance.
     *
     * @exception ServletException if we cannot process the activity
     *  definition file for this activity
     */
    public void init() throws ServletException {

        // Record the debugging detail level settings
        String debug = getServletConfig().getInitParameter("debug");
        if (debug != null) {
            try {
                this.debug = Integer.parseInt(debug);
            } catch (NumberFormatException e) {
                throw new UnavailableException
                    ("Debug initialization parameter must be an integer");
            }
        }

        // Record the attribute name for our current Context
        String contextKey = getServletConfig().getInitParameter("context-key");
        if (contextKey != null)
            setContextKey(contextKey);

        // Record the attribute name for our current Context
        String registryKey = getServletConfig().getInitParameter("registry-key");
        if (registryKey != null)
            setRegistryKey(registryKey);

        // Parse the activity definition file for our Activity
        String registryPath = getServletConfig().getInitParameter("registry");
        if (registryPath != null)
            this.registryPath = registryPath;
        parse(this.registryPath);

        // Check result of parse
        if (activity == null)
            throw new UnavailableException("No registry defined in resource "
                                           + registryPath);

    }


    // -------------------------------------------------------- Private Methods


    /**
     * Parse the specified activity definition file for this instance.
     *
     * @param path Context-relative resource path of the activity
     *  definition file
     *
     * @exception ServletException on any processing error in parsing
     */
    private void parse(String path) throws ServletException {

        // Get an input source for the specified path
        InputStream is =
            getServletContext().getResourceAsStream(path);
        if (is == null)
            throw new UnavailableException("Cannot access resource " +
                                           path);

        // Configure a Digester instance to parse our definition file
        Digester digester = new Digester();
        digester.setNamespaceAware(true);
        digester.setValidating(false);
        digester.push(this);

        // Add rules to recognize the built-in steps that we know about
        BaseRuleSet brs = new BaseRuleSet();
        digester.addRuleSet(brs);
        digester.addRuleSet(new CoreRuleSet());
        digester.addRuleSet(new IoRuleSet());
        digester.addRuleSet(new WebRuleSet());

        // Add a rule to register the Activity being created
        digester.setRuleNamespaceURI(brs.getNamespaceURI());
        digester.addSetNext("activity", "setActivity",
                            "org.apache.commons.workflow.Activity");

        // Parse the activity definition file
        try {
            digester.parse(is);
        } catch (Throwable t) {
            log("Cannot parse resource " + path, t);
            throw new UnavailableException("Cannot parse resource " + path);
        } finally {
            try {
                is.close();
            } catch (Throwable u) {
                ;
            }
        }

    }


}
