/*
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
package org.apache.struts.action;

import javax.servlet.*;
import junit.framework.*;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.FormPropertyConfig;


/**
 * Suite of unit tests for the
 * <code>org.apache.struts.action.DynaActionFormClass</code> class.
 */
public class TestDynaActionFormClass extends TestCase
{
    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestDynaActionFormClass(String theName)
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
        junit.awtui.TestRunner.main
            (new String[] {TestDynaActionFormClass.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite()
    {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestDynaActionFormClass.class);
    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The <code>FormBeanConfig</code> structure for the form bean we will
     * be creating.
     */
    protected FormBeanConfig beanConfig = null;


    /**
     * The <code>DynaActionFormClass</code> to use for testing.
     */
    protected DynaActionFormClass dynaClass = null;


    /**
     * The set of <code>FormPropertyConfig</code> objects to use when
     * creating our <code>FormBeanConfig</code>.
     */
    protected static final FormPropertyConfig[] dynaProperties = {
        new FormPropertyConfig("booleanProperty", "boolean", "true"),
        new FormPropertyConfig("booleanSecond", "boolean", "true"),
        new FormPropertyConfig("doubleProperty", "double", "321.0"),
        new FormPropertyConfig("floatProperty", "float", "123.0"),
        new FormPropertyConfig("intArray", "int[]", null),
        new FormPropertyConfig("intIndexed", "int[]", null),
        new FormPropertyConfig("intProperty", "int", "123"),
        new FormPropertyConfig("listProperty", "java.util.List", null),
        new FormPropertyConfig("longProperty", "long", "321"),
        new FormPropertyConfig("mappedProperty", "java.util.Map", null),
        new FormPropertyConfig("mappedIntProperty", "java.util.Map", null),
        new FormPropertyConfig("nullProperty", "java.lang.String", null),
        new FormPropertyConfig("shortProperty", "short", "987"),
        new FormPropertyConfig("stringArray", "java.lang.String[]", null),
        new FormPropertyConfig("stringIndexed", "java.lang.String[]", null),
        new FormPropertyConfig("stringProperty", "java.lang.String",
                               "This is a string"),
   };


    // ----------------------------------------------------- Setup and Teardown


    public void setUp() {

        // Construct a FormBeanConfig to be used
        beanConfig = new FormBeanConfig();
        beanConfig.setDynamic(false); // setType() will change it
        beanConfig.setName("dynaForm");
        beanConfig.setType("org.apache.struts.action.DynaActionForm");

        // Add relevant property definitions
        for (int i = 0; i < dynaProperties.length; i++) {
            beanConfig.addFormPropertyConfig(dynaProperties[i]);
        }

        // FIXME - initial values for arrays/lists/maps?

    }


    public void tearDown() {

        dynaClass = null;
        beanConfig = null;

    }


    // -------------------------------------------------- Verify FormBeanConfig


    public void testConfigCreate() {

        // Check FormBeanConfig properties
        assertTrue("dynamic is correct", beanConfig.getDynamic());
        assertEquals("name is correct", "dynaForm", beanConfig.getName());
        assertEquals("type is correct",
                     "org.apache.struts.action.DynaActionForm",
                     beanConfig.getType());

        // Check the configured FormPropertyConfig element properties
        for (int i = 0; i < dynaProperties.length; i++) {
            FormPropertyConfig dynaProperty =
                beanConfig.findFormPropertyConfig(dynaProperties[i].getName());
            assertNotNull("Found dynaProperty " +
                          dynaProperties[i].getName(), dynaProperty);
            assertEquals("dynaProperty name for " +
                         dynaProperties[i].getName(),
                         dynaProperties[i].getName(),
                         dynaProperty.getName());
            assertEquals("dynaProperty type for " +
                         dynaProperties[i].getName(),
                         dynaProperties[i].getType(),
                         dynaProperty.getType());
            assertEquals("dynaProperty initial for " +
                         dynaProperties[i].getName(),
                         dynaProperties[i].getInitial(),
                         dynaProperty.getInitial());
        }

        // Check the configured FormPropertyConfig element initial values
        assertEquals("booleanProperty value",
                     Boolean.TRUE,
                     beanConfig.findFormPropertyConfig("booleanProperty").initial());
        assertEquals("booleanSecond value",
                     Boolean.TRUE,
                     beanConfig.findFormPropertyConfig("booleanSecond").initial());
        assertEquals("doubleProperty value",
                     new Double(321.0),
                     beanConfig.findFormPropertyConfig("doubleProperty").initial());


    }





    // --------------------------------------------- Create DynaActionFormClass


    public void testClassCreate() {



    }



}
