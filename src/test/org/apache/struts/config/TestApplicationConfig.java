/*
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

package org.apache.struts.config;


import java.io.InputStream;
import java.net.URL;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.digester.Digester;
import org.apache.struts.action.Action;


/**
 * Unit tests for the <code>org.apache.struts.config</code> package.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2001/12/26 21:55:55 $
 */

public class TestApplicationConfig extends TestCase {


    // ----------------------------------------------------- Instance Variables


    /**
     * The ApplicationConfig we are testing.
     */
    protected ApplicationConfig config = null;


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public TestApplicationConfig(String name) {

        super(name);

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Set up instance variables required by this test case.
     */
    public void setUp() {

        config = new ApplicationConfig("", null);

    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {

        return (new TestSuite(TestApplicationConfig.class));

    }


    /**
     * Tear down instance variables required by this test case.
     */
    public void tearDown() {

        config = null;

    }


    // ------------------------------------------------ Individual Test Methods


    /**
     * Test parsing of a struts-config.xml file.
     */
    public void testParse() {


        // Prepare a Digester for parsing a struts-config.xml file
        Digester digester = new Digester();
        digester.push(config);
        digester.setDebug(0);
        digester.setNamespaceAware(true);
        digester.setValidating(true);
        digester.addRuleSet(new ConfigRuleSet());
        digester.register
            ("-//Apache Software Foundation//DTD Struts Configuration 1.1//EN",
             this.getClass().getResource
             ("/org/apache/struts/resources/struts-config_1_1.dtd").toString());

        // Parse the test struts-config.xml file
        try {
            InputStream input = this.getClass().getResourceAsStream
                ("/org/apache/struts/config/struts-config.xml");
            assertNotNull("Got an input stream for struts-config.xml", input);
            digester.parse(input);
            input.close();
        } catch (Throwable t) {
            t.printStackTrace(System.out);
            fail("Parsing threw exception:  " + t);
        }

        // Perform assertion tests on the parsed information

        DataSourceConfig dsc =
            config.findDataSourceConfig(Action.DATA_SOURCE_KEY);
        assertNotNull("Found our data source configuration", dsc);
        assertEquals("Data source description",
                     "Example Data Source Configuration",
                     dsc.getDescription());

        FormBeanConfig fbcs[] = config.findFormBeanConfigs();
        assertNotNull("Found our form bean configurations", fbcs);
        assertEquals("Found three form bean configurations",
                     3, fbcs.length);

        ForwardConfig fcs[] = config.findForwardConfigs();
        assertNotNull("Found our forward configurations", fcs);
        assertEquals("Found three forward configurations",
                     3, fcs.length);

        ActionConfig logon = config.findActionConfig("/logon");
        assertNotNull("Found logon action configuration", logon);
        assertEquals("Found correct logon configuration",
                     "logonForm",
                     logon.getName());


    }



}
