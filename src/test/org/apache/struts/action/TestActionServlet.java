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

import javax.servlet.ServletException;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.ServletTestCase;
import org.apache.struts.util.MessageResources;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.action.ActionServlet</code> class.
 */
public class TestActionServlet extends ServletTestCase
{
    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestActionServlet(String theName)
    {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs)
    {
        junit.awtui.TestRunner.main(new String[] {TestActionServlet.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite()
    {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestActionServlet.class);
    }


    // ----------------------------- initInternal() and destroyInternal() tests


    /**
     * Verify that we can initialize and destroy our internal message
     * resources object.
     */
    public void testInitDestroyInternal() {

        ActionServlet servlet = new ActionServlet();
        try {
            servlet.initInternal();
        } catch (ServletException e) {
            fail("initInternal() threw exception: " + e);
        }
        assertTrue("internal was initialized",
                   servlet.getInternal() != null);
        assertTrue("internal of correct type",
                   servlet.getInternal() instanceof MessageResources);
        servlet.destroyInternal();
        assertTrue("internal was destroyed",
                   servlet.getInternal() == null);

    }



    //----- Test initApplication() method --------------------------------------

    /**
     * Verify that nothing happens if no "application" property is defined in
     * the servlet configuration.
     */
    /*
    public void testInitApplicationNull() throws ServletException
    {
        ActionServlet servlet = new ActionServlet();
        servlet.init(config);        

        // Test the initApplication() method
        servlet.initApplication();

        // As no "application" object is found in the servlet config, no
        // attribute should be set in the context
        assertTrue(config.getServletContext().getAttribute(Action.MESSAGES_KEY) == null);
    }
    */

    /**
     * Verify that eveything is fine when only a "application" parameter is
     * defined in the servlet configuration.
     */
    /*
    public void testInitApplicationOk1() throws ServletException
    {
        // initialize config
        config.setInitParameter("application", "org.apache.struts.webapp.example.ApplicationResources");

        ActionServlet servlet = new ActionServlet();
        servlet.init(config);        

        // Test the initApplication() method
        servlet.initApplication();

        assertTrue(servlet.application != null);
        assertTrue(servlet.application.getReturnNull() == true);

        assertTrue(config.getServletContext().getAttribute(Action.MESSAGES_KEY) != null);
        assertEquals(servlet.application, config.getServletContext().getAttribute(Action.MESSAGES_KEY));

    }
    */

    // [...]
}
