/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/mock/TestMockBase.java,v 1.9 2002/12/24 18:49:52 craigmcc Exp $
 * $Revision: 1.9 $
 * $Date: 2002/12/24 18:49:52 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionFormBean;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.config.FormPropertyConfig;



/**
 * <p>Convenience base class for unit tests of the
 * <code>org.apache.struts.util</code> package, and others that require
 * a runtime environment similar to what the Struts controller servlet
 * sets up.  The <code>setUp()</code> method establishes
 * a consistent basic environment for the various tests.  The only
 * tests included in this class are simple validations that the basic
 * environment was set up correctly.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.9 $ $Date: 2002/12/24 18:49:52 $
 */

public class TestMockBase extends TestCase {


    // ----------------------------------------------------------------- Basics


    public TestMockBase(String name) {
        super(name);
    }


    public static void main(String args[]) {
        junit.awtui.TestRunner.main
            (new String[] { TestMockBase.class.getName() } );
    }


    public static Test suite() {
        return (new TestSuite(TestMockBase.class));
    }


    // ----------------------------------------------------- Instance Variables


    protected ApplicationConfig appConfig = null;
    protected ApplicationConfig appConfig2 = null;
    protected MockServletConfig config = null;
    protected MockServletContext context = null;
    protected MockPageContext page = null;
    protected MockPrincipal principal = null;
    protected MockHttpServletRequest request = null;
    protected MockHttpServletResponse response = null;
    protected MockHttpSession session = null;


    // ----------------------------------------------------- Setup and Teardown


    public void setUp() {

        // Set up the servlet API objects for a test scenario
        context = new MockServletContext();
        config = new MockServletConfig(context);
        session = new MockHttpSession(context);
        request = new MockHttpServletRequest(session);
        principal = new MockPrincipal("username",
                                      new String[] { "admin", "manager" });
        request.setUserPrincipal(principal);
        response = new MockHttpServletResponse();
        page = new MockPageContext(config, request, response);

        // Set up application configurations for our supported modules
        setUpDefaultApp();
        setUpSecondApp();

        // NOTE - we do not initialize the request attribute
        // for the selected module so that fallbacks to the
        // default module can be tested.  To select a module,
        // tests should set the request attribute Action.APPLICATION_KEY
        // to the ApplicationConfig instance for the selected module

    }


    protected void setUpDefaultApp() {

        ActionFormBean formBean = null;
        ActionForward forward = null;
        ActionMapping mapping = null;

        appConfig = new ApplicationConfig("");
        context.setAttribute(Action.APPLICATION_KEY, appConfig);

        // Forward "external" to "http://jakarta.apache.org/"
        appConfig.addForwardConfig
            (new ActionForward("external", "http://jakarta.apache.org/",
                               false, false));

        // Forward "foo" to "/bar.jsp"
        appConfig.addForwardConfig
            (new ActionForward("foo", "/bar.jsp", false, false));

        // Forward "relative1" to "relative.jsp" non-context-relative
        appConfig.addForwardConfig
            (new ActionForward("relative1", "relative.jsp", false, false));

        // Forward "relative2" to "relative.jsp" context-relative
        appConfig.addForwardConfig
            (new ActionForward("relative2", "relative.jsp", false, true));

        // Form Bean "static" is a standard ActionForm subclass
        formBean = new ActionFormBean
            ("static",
             "org.apache.struts.mock.MockFormBean");
        appConfig.addFormBeanConfig(formBean);

        // Action "/static" uses the "static" form bean in request scope
        mapping = new ActionMapping();
        mapping.setInput("/static.jsp");
        mapping.setName("static");
        mapping.setPath("/static");
        mapping.setScope("request");
        mapping.setType("org.apache.struts.mock.MockAction");
        appConfig.addActionConfig(mapping);

        // Form Bean "dynamic" is a DynaActionForm with the same properties
        formBean = new ActionFormBean
            ("dynamic",
             "org.apache.struts.action.DynaActionForm");
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("booleanProperty", "boolean", "false"));
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("stringProperty", "java.lang.String",
                                    null));
        appConfig.addFormBeanConfig(formBean);

        // Action "/dynamic" uses the "dynamic" form bean in session scope
        mapping = new ActionMapping();
        mapping.setInput("/dynamic.jsp");
        mapping.setName("dynamic");
        mapping.setPath("/dynamic");
        mapping.setScope("session");
        mapping.setType("org.apache.struts.mock.MockAction");
        appConfig.addActionConfig(mapping);

        // Form Bean "/dynamic0" is a DynaActionForm with initializers
        formBean = new ActionFormBean
            ("dynamic0",
             "org.apache.struts.action.DynaActionForm");
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("booleanProperty", "boolean", "true"));
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("stringProperty", "java.lang.String",
                                    "String Property"));
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("intArray1", "int[]",
                                    "{1,2,3}", 4)); // 4 should be ignored
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("intArray2", "int[]",
                                    null, 5)); // 5 should be respected
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("principal",
                                    "org.apache.struts.mock.MockPrincipal",
                                    null));
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("stringArray1", "java.lang.String[]",
                                    "{aaa,bbb,ccc}", 2)); // 2 should be ignored
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("stringArray2", "java.lang.String[]",
                                    null, 3)); // 3 should be respected
        appConfig.addFormBeanConfig(formBean);

        // Action "/dynamic0" uses the "dynamic0" form bean in request scope
        mapping = new ActionMapping();
        mapping.setName("dynamic0");
        mapping.setPath("/dynamic0");
        mapping.setScope("request");
        mapping.setType("org.apache.struts.mock.MockAction");
        appConfig.addActionConfig(mapping);

        // Action "/noform" has no form bean associated with it
        mapping = new ActionMapping();
        mapping.setPath("/noform");
        mapping.setType("org.apache.struts.mock.MockAction");
        appConfig.addActionConfig(mapping);

    }


    protected void setUpSecondApp() {

        ActionFormBean formBean = null;
        ActionMapping mapping = null;

        appConfig2 = new ApplicationConfig("/2");
        context.setAttribute(Action.APPLICATION_KEY + "/2", appConfig2);

        // Forward "external" to "http://jakarta.apache.org/"
        appConfig2.addForwardConfig
            (new ActionForward("external", "http://jakarta.apache.org/",
                               false, false));

        // Forward "foo" to "/baz.jsp" (different from default)
        appConfig2.addForwardConfig
            (new ActionForward("foo", "/baz.jsp", false, false));

        // Forward "relative1" to "relative.jsp" non-context-relative
        appConfig2.addForwardConfig
            (new ActionForward("relative1", "relative.jsp", false, false));

        // Forward "relative2" to "relative.jsp" context-relative
        appConfig2.addForwardConfig
            (new ActionForward("relative2", "relative.jsp", false, true));

        // Form Bean "static" is a standard ActionForm subclass (same as default)
        formBean = new ActionFormBean
            ("static",
             "org.apache.struts.mock.MockFormBean");
        appConfig2.addFormBeanConfig(formBean);

        // Action "/static" uses the "static" form bean in request scope (same as default)
        mapping = new ActionMapping();
        mapping.setInput("/static.jsp");
        mapping.setName("static");
        mapping.setPath("/static");
        mapping.setScope("request");
        mapping.setType("org.apache.struts.mock.MockAction");
        appConfig2.addActionConfig(mapping);

        // Form Bean "dynamic2" is a DynaActionForm with the same properties
        formBean = new ActionFormBean
            ("dynamic2",
             "org.apache.struts.action.DynaActionForm");
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("booleanProperty", "boolean", "false"));
        formBean.addFormPropertyConfig
            (new FormPropertyConfig("stringProperty", "java.lang.String",
                                    null));
        appConfig2.addFormBeanConfig(formBean);

        // Action "/dynamic2" uses the "dynamic2" form bean in session scope
        mapping = new ActionMapping();
        mapping.setInput("/dynamic2.jsp");
        mapping.setName("dynamic2");
        mapping.setPath("/dynamic2");
        mapping.setScope("session");
        mapping.setType("org.apache.struts.mock.MockAction");
        appConfig2.addActionConfig(mapping);

        // Action "/noform" has no form bean associated with it (same as default)
        mapping = new ActionMapping();
        mapping.setPath("/noform");
        mapping.setType("org.apache.struts.mock.MockAction");
        appConfig2.addActionConfig(mapping);

    }


    public void tearDown() {

        appConfig = null;
        config = null;
        context = null;
        page = null;
        principal = null;
        request = null;
        response = null;
        session = null;

    }


    // ------------------------------------------------------- Individual Tests


    // Test the basic setup of the mock object environment
    public void testUtilBaseEnvironment() {

        // Validate the servlet API objects and inter-relationships
        assertNotNull("config is present", config);
        assertNotNull("context is present", context);
        assertNotNull("page is present", page);
        assertNotNull("principal is present", principal);
        assertNotNull("request is present", request);
        assertNotNull("response is present", response);
        assertNotNull("session is present", session);
        assertEquals("page-->config",
                     config, page.getServletConfig());
        assertEquals("config-->context",
                     context, config.getServletContext());
        assertEquals("page-->context",
                     context, page.getServletContext());
        assertEquals("page-->request",
                     request, page.getRequest());
        assertEquals("page-->response",
                     response, page.getResponse());
        assertEquals("page-->session",
                     session, page.getSession());
        assertEquals("request-->principal",
                     principal, request.getUserPrincipal());
        assertEquals("request-->session",
                     session, request.getSession());
        assertEquals("session-->context",
                     context, session.getServletContext());

        // Validate the configuration for the default module
        assertNotNull("appConfig is present", appConfig);
        assertEquals("context-->appConfig",
                     appConfig,
                     context.getAttribute(Action.APPLICATION_KEY));

        // Validate the configuration for the second module
        assertNotNull("appConfig2 is present", appConfig2);
        assertEquals("context-->appConfig2",
                     appConfig2,
                     context.getAttribute(Action.APPLICATION_KEY + "/2"));

    }


}
