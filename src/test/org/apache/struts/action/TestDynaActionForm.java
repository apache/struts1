/*
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
package org.apache.struts.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.DynaProperty;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;


/**
 * Suite of unit tests for the
 * <code>org.apache.struts.action.DynaActionForm</code> class.
 */
public class TestDynaActionForm extends TestDynaActionFormClass {


    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestDynaActionForm(String theName)
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
            (new String[] {TestDynaActionForm.class.getName()});
    }


    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite()
    {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestDynaActionForm.class);
    }


    // ----------------------------------------------------- Instance Variables


    /**
     * Dummy ApplicationConfig for calls to reset() and validate().
     */
    protected ApplicationConfig appConfig = null;


    /**
     * The basic <code>DynaActionForm</code> to use for testing.
     */
    protected DynaActionForm dynaForm = null;


    /**
     * Dummy ActionMapping for calls to reset() and validate().
     */
    protected ActionMapping mapping = null;


    /**
     * The set of property names we expect to have returned when calling
     * <code>getDynaProperties()</code>.  You should update this list
     * when new properties are added to TestBean.
     */
    protected final static String[] properties = {
        "booleanProperty",
        "booleanSecond",
        "doubleProperty",
        "floatProperty",
        "intArray",
        "intIndexed",
        "intProperty",
        "listIndexed",
        "longProperty",
        "mappedProperty",
        "mappedIntProperty",
        //        "nullProperty",
        "shortProperty",
        "stringArray",
        "stringIndexed",
        "stringProperty",
    };


    // ----------------------------------------------------- Setup and Teardown


    public void setUp() {

        super.setUp();
        try {
            dynaForm = (DynaActionForm) dynaClass.newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage());
        }
        setupComplexProperties();
        appConfig = new DynaActionFormConfig(beanConfig);
        mapping = new DynaActionFormMapping(appConfig);


    }


    public void tearDown() {

        super.tearDown();
        appConfig = null;
        dynaForm = null;
        mapping = null;

    }


    // --------------------------------------------- Create New DynaActionForms


    // Test basic form bean properties on creation
    public void testBeanCreate() {

        assertEquals("booleanProperty", Boolean.TRUE,
                     (Boolean) dynaForm.get("booleanProperty"));
        assertEquals("booleanSecond", Boolean.TRUE,
                     (Boolean) dynaForm.get("booleanSecond"));
        assertEquals("doubleProperty", new Double(321.0),
                     (Double) dynaForm.get("doubleProperty"));
        assertEquals("floatProperty", new Float((float) 123.0),
                     (Float) dynaForm.get("floatProperty"));
        assertEquals("intProperty", new Integer(123),
                     (Integer) dynaForm.get("intProperty"));
        // FIXME - listIndexed
        assertEquals("longProperty", new Long((long) 321),
                     (Long) dynaForm.get("longProperty"));
        // FIXME - mappedProperty
        // FIXME - mappedIntProperty
        //        assertEquals("nullProperty", (String) null,
        //                     (String) dynaForm.get("nullProperty"));
        assertEquals("shortProperty", new Short((short) 987),
                     (Short) dynaForm.get("shortProperty"));
        assertEquals("stringProperty", "This is a string",
                     (String) dynaForm.get("stringProperty"));

    }


    // Test initialize() method on indexed values to ensure that the
    // result returned by FormPropertyConfig().initial() is never clobbered
    public void testIndexedInitialize() {

        // Update some values in the indexed properties
        dynaForm.set("intArray", 1, new Integer(111));
        assertEquals("intArray[1]", new Integer(111),
                     (Integer) dynaForm.get("intArray", 1));
        dynaForm.set("intIndexed", 2, new Integer(222));
        assertEquals("intIndexed[2]", new Integer(222),
                     (Integer) dynaForm.get("intIndexed", 2));
        dynaForm.set("stringArray", 3, "New String 3");
        assertEquals("stringArray[3]", "New String 3",
                     (String) dynaForm.get("stringArray", 3));
        dynaForm.set("stringIndexed", 4, "New String 4");
        assertEquals("stringIndexed[4]", "New String 4",
                     (String) dynaForm.get("stringIndexed", 4));

        // Perform initialize() and revalidate the original values
        // while ensuring our initial values did not get corrupted
        dynaForm.initialize(mapping);
        setupComplexProperties();
        testGetIndexedValues();

    }


    // Test initialize() method going back to initial values
    public void testScalarInitialize() {

        // Update a bunch of scalar properties to new values
        dynaForm.set("booleanProperty", Boolean.FALSE);
        assertEquals("booleanProperty", Boolean.FALSE,
                     (Boolean) dynaForm.get("booleanProperty"));
        dynaForm.set("booleanSecond", Boolean.FALSE);
        dynaForm.set("doubleProperty", new Double(654.0));
        dynaForm.set("floatProperty", new Float((float) 543.0));
        dynaForm.set("intProperty", new Integer(555));
        dynaForm.set("longProperty", new Long((long) 777));
        dynaForm.set("shortProperty", new Short((short) 222));
        dynaForm.set("stringProperty", "New String Value");
        assertEquals("stringProperty", "New String Value",
                     (String) dynaForm.get("stringProperty"));

        // Perform initialize() and revalidate the original values
        dynaForm.initialize(mapping);
        setupComplexProperties();
        testBeanCreate();

    }


    // --------------------------------------- Tests from BasicDynaBeanTestCase


    /**
     * Corner cases on getDynaProperty invalid arguments.
     */
    public void testGetDescriptorArguments() {

        try {
            DynaProperty descriptor =
                    dynaForm.getDynaClass().getDynaProperty("unknown");
            assertNull("Unknown property descriptor should be null",
                    descriptor);
        } catch (Throwable t) {
            fail("Threw " + t + " instead of returning null");
        }

        try {
            dynaForm.getDynaClass().getDynaProperty(null);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            ; // Expected response
        } catch (Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException");
        }

    }


    /**
     * Positive getDynaProperty on property <code>booleanProperty</code>.
     */
    public void testGetDescriptorBoolean() {

        testGetDescriptorBase("booleanProperty", Boolean.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>doubleProperty</code>.
     */
    public void testGetDescriptorDouble() {

        testGetDescriptorBase("doubleProperty", Double.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>floatProperty</code>.
     */
    public void testGetDescriptorFloat() {

        testGetDescriptorBase("floatProperty", Float.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>intProperty</code>.
     */
    public void testGetDescriptorInt() {

        testGetDescriptorBase("intProperty", Integer.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>longProperty</code>.
     */
    public void testGetDescriptorLong() {

        testGetDescriptorBase("longProperty", Long.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>booleanSecond</code>
     * that uses an "is" method as the getter.
     */
    public void testGetDescriptorSecond() {

        testGetDescriptorBase("booleanSecond", Boolean.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>shortProperty</code>.
     */
    public void testGetDescriptorShort() {

        testGetDescriptorBase("shortProperty", Short.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>stringProperty</code>.
     */
    public void testGetDescriptorString() {

        testGetDescriptorBase("stringProperty", String.class);

    }


    /**
     * Positive test for getDynaPropertys().  Each property name
     * listed in <code>properties</code> should be returned exactly once.
     */
    public void testGetDescriptors() {

        DynaProperty pd[] = dynaForm.getDynaClass().getDynaProperties();
        assertNotNull("Got descriptors", pd);
        int count[] = new int[properties.length];
        for (int i = 0; i < pd.length; i++) {
            String name = pd[i].getName();
            for (int j = 0; j < properties.length; j++) {
                if (name.equals(properties[j]))
                    count[j]++;
            }
        }
        for (int j = 0; j < properties.length; j++) {
            if (count[j] < 0)
                fail("Missing property " + properties[j]);
            else if (count[j] > 1)
                fail("Duplicate property " + properties[j]);
        }

    }


    /**
     * Corner cases on getIndexedProperty invalid arguments.
     */
    public void testGetIndexedArguments() {

        try {
            dynaForm.get("intArray", -1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            ; // Expected response
        } catch (Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
        }


    }


    /**
     * Positive and negative tests on getIndexedProperty valid arguments.
     */
    public void testGetIndexedValues() {

        Object value = null;

        for (int i = 0; i < 5; i++) {

            try {
                value = dynaForm.get("intArray", i);
                assertNotNull("intArray returned value " + i, value);
                assertTrue("intArray returned Integer " + i,
                        value instanceof Integer);
                assertEquals("intArray returned correct " + i, i * 10,
                        ((Integer) value).intValue());
            } catch (Throwable t) {
                fail("intArray " + i + " threw " + t);
            }

            try {
                value = dynaForm.get("intIndexed", i);
                assertNotNull("intIndexed returned value " + i, value);
                assertTrue("intIndexed returned Integer " + i,
                        value instanceof Integer);
                assertEquals("intIndexed returned correct " + i, i * 100,
                        ((Integer) value).intValue());
            } catch (Throwable t) {
                fail("intIndexed " + i + " threw " + t);
            }

            try {
                value = dynaForm.get("listIndexed", i);
                assertNotNull("listIndexed returned value " + i, value);
                assertTrue("list returned String " + i,
                        value instanceof String);
                assertEquals("listIndexed returned correct " + i,
                        "String " + i, (String) value);
            } catch (Throwable t) {
                fail("listIndexed " + i + " threw " + t);
            }

            try {
                value = dynaForm.get("stringArray", i);
                assertNotNull("stringArray returned value " + i, value);
                assertTrue("stringArray returned String " + i,
                        value instanceof String);
                assertEquals("stringArray returned correct " + i,
                        "String " + i, (String) value);
            } catch (Throwable t) {
                fail("stringArray " + i + " threw " + t);
            }

            try {
                value = dynaForm.get("stringIndexed", i);
                assertNotNull("stringIndexed returned value " + i, value);
                assertTrue("stringIndexed returned String " + i,
                        value instanceof String);
                assertEquals("stringIndexed returned correct " + i,
                        "String " + i, (String) value);
            } catch (Throwable t) {
                fail("stringIndexed " + i + " threw " + t);
            }

        }


    }


    /**
     * Corner cases on getMappedProperty invalid arguments.
     */
    public void testGetMappedArguments() {


        try {
            Object value = dynaForm.get("mappedProperty", "unknown");
            assertNull("Should not return a value", value);
        } catch (Throwable t) {
            fail("Threw " + t + " instead of returning null");
        }


    }


    /**
     * Positive and negative tests on getMappedProperty valid arguments.
     */
    public void testGetMappedValues() {

        Object value = null;

        try {
            value = dynaForm.get("mappedProperty", "First Key");
            assertEquals("Can find first value", "First Value", value);
        } catch (Throwable t) {
            fail("Finding first value threw " + t);
        }

        try {
            value = dynaForm.get("mappedProperty", "Second Key");
            assertEquals("Can find second value", "Second Value", value);
        } catch (Throwable t) {
            fail("Finding second value threw " + t);
        }

        try {
            value = dynaForm.get("mappedProperty", "Third Key");
            assertNull("Can not find third value", value);
        } catch (Throwable t) {
            fail("Finding third value threw " + t);
        }

    }


    /**
     * Corner cases on getSimpleProperty invalid arguments.
     */
    public void testGetSimpleArguments() {

        try {
            dynaForm.get(null);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            ; // Expected response
        } catch (Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException");
        }

    }


    /**
     * Test getSimpleProperty on a boolean property.
     */
    public void testGetSimpleBoolean() {

        try {
            Object value = dynaForm.get("booleanProperty");
            assertNotNull("Got a value", value);
            assertTrue("Got correct type", (value instanceof Boolean));
            assertTrue("Got correct value",
                    ((Boolean) value).booleanValue() == true);
        } catch (Throwable e) {
            fail("Exception: " + e);
        }

    }


    /**
     * Test getSimpleProperty on a double property.
     */
    public void testGetSimpleDouble() {

        try {
            Object value = dynaForm.get("doubleProperty");
            assertNotNull("Got a value", value);
            assertTrue("Got correct type", (value instanceof Double));
            assertEquals("Got correct value",
                    ((Double) value).doubleValue(),
                    (double) 321.0,
                    (double) 0.005);
        } catch (Throwable t) {
            fail("Exception: " + t);
        }

    }


    /**
     * Test getSimpleProperty on a float property.
     */
    public void testGetSimpleFloat() {

        try {
            Object value = dynaForm.get("floatProperty");
            assertNotNull("Got a value", value);
            assertTrue("Got correct type", (value instanceof Float));
            assertEquals("Got correct value",
                    ((Float) value).floatValue(),
                    (float) 123.0,
                    (float) 0.005);
        } catch (Throwable t) {
            fail("Exception: " + t);
        }

    }


    /**
     * Test getSimpleProperty on a int property.
     */
    public void testGetSimpleInt() {

        try {
            Object value = dynaForm.get("intProperty");
            assertNotNull("Got a value", value);
            assertTrue("Got correct type", (value instanceof Integer));
            assertEquals("Got correct value",
                    ((Integer) value).intValue(),
                    (int) 123);
        } catch (Throwable t) {
            fail("Exception: " + t);
        }

    }


    /**
     * Test getSimpleProperty on a long property.
     */
    public void testGetSimpleLong() {

        try {
            Object value = dynaForm.get("longProperty");
            assertNotNull("Got a value", value);
            assertTrue("Got correct type", (value instanceof Long));
            assertEquals("Got correct value",
                    ((Long) value).longValue(),
                    (long) 321);
        } catch (Throwable t) {
            fail("Exception: " + t);
        }

    }


    /**
     * Test getSimpleProperty on a short property.
     */
    public void testGetSimpleShort() {

        try {
            Object value = dynaForm.get("shortProperty");
            assertNotNull("Got a value", value);
            assertTrue("Got correct type", (value instanceof Short));
            assertEquals("Got correct value",
                    ((Short) value).shortValue(),
                    (short) 987);
        } catch (Throwable t) {
            fail("Exception: " + t);
        }

    }


    /**
     * Test getSimpleProperty on a String property.
     */
    public void testGetSimpleString() {

        try {
            Object value = dynaForm.get("stringProperty");
            assertNotNull("Got a value", value);
            assertTrue("Got correct type", (value instanceof String));
            assertEquals("Got correct value",
                    (String) value,
                    "This is a string");
        } catch (Throwable t) {
            fail("Exception: " + t);
        }

    }


    /**
     * Test <code>contains()</code> method for mapped properties.
     */
    public void testMappedContains() {

        try {
            assertTrue("Can see first key",
                    dynaForm.contains("mappedProperty", "First Key"));
        } catch (Throwable t) {
            fail("Exception: " + t);
        }


        try {
            assertTrue("Can not see unknown key",
                    !dynaForm.contains("mappedProperty", "Unknown Key"));
        } catch (Throwable t) {
            fail("Exception: " + t);
        }

    }


    /**
     * Test <code>remove()</code> method for mapped properties.
     */
    public void testMappedRemove() {

        try {
            assertTrue("Can see first key",
                    dynaForm.contains("mappedProperty", "First Key"));
            dynaForm.remove("mappedProperty", "First Key");
            assertTrue("Can not see first key",
                    !dynaForm.contains("mappedProperty", "First Key"));
        } catch (Throwable t) {
            fail("Exception: " + t);
        }

        try {
            assertTrue("Can not see unknown key",
                    !dynaForm.contains("mappedProperty", "Unknown Key"));
            dynaForm.remove("mappedProperty", "Unknown Key");
            assertTrue("Can not see unknown key",
                    !dynaForm.contains("mappedProperty", "Unknown Key"));
        } catch (Throwable t) {
            fail("Exception: " + t);
        }

    }


    /**
     * Corner cases on setIndexedProperty invalid arguments.
     */
    public void testSetIndexedArguments() {

        try {
            dynaForm.set("intArray", -1, new Integer(0));
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            ; // Expected response
        } catch (Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
        }

    }


    /**
     * Positive and negative tests on setIndexedProperty valid arguments.
     */
    public void testSetIndexedValues() {

        Object value = null;

        try {
            dynaForm.set("intArray", 0, new Integer(1));
            value = (Integer) dynaForm.get("intArray", 0);
            assertNotNull("Returned new value 0", value);
            assertTrue("Returned Integer new value 0",
                    value instanceof Integer);
            assertEquals("Returned correct new value 0", 1,
                    ((Integer) value).intValue());
        } catch (Throwable t) {
            fail("Threw " + t);
        }

        try {
            dynaForm.set("intIndexed", 1, new Integer(11));
            value = (Integer) dynaForm.get("intIndexed", 1);
            assertNotNull("Returned new value 1", value);
            assertTrue("Returned Integer new value 1",
                    value instanceof Integer);
            assertEquals("Returned correct new value 1", 11,
                    ((Integer) value).intValue());
        } catch (Throwable t) {
            fail("Threw " + t);
        }

        try {
            dynaForm.set("listIndexed", 2, "New Value 2");
            value = (String) dynaForm.get("listIndexed", 2);
            assertNotNull("Returned new value 2", value);
            assertTrue("Returned String new value 2",
                    value instanceof String);
            assertEquals("Returned correct new value 2", "New Value 2",
                    (String) value);
        } catch (Throwable t) {
            fail("Threw " + t);
        }

        try {
            dynaForm.set("stringArray", 3, "New Value 3");
            value = (String) dynaForm.get("stringArray", 3);
            assertNotNull("Returned new value 3", value);
            assertTrue("Returned String new value 3",
                    value instanceof String);
            assertEquals("Returned correct new value 3", "New Value 3",
                    (String) value);
        } catch (Throwable t) {
            fail("Threw " + t);
        }

        try {
            dynaForm.set("stringIndexed", 4, "New Value 4");
            value = (String) dynaForm.get("stringIndexed", 4);
            assertNotNull("Returned new value 4", value);
            assertTrue("Returned String new value 4",
                    value instanceof String);
            assertEquals("Returned correct new value 4", "New Value 4",
                    (String) value);
        } catch (Throwable t) {
            fail("Threw " + t);
        }


    }


    /**
     * Positive and negative tests on setMappedProperty valid arguments.
     */
    public void testSetMappedValues() {

        Object value = null;

        try {
            dynaForm.set("mappedProperty", "First Key", "New First Value");
            assertEquals("Can replace old value",
                    "New First Value",
                    (String) dynaForm.get("mappedProperty", "First Key"));
        } catch (Throwable t) {
            fail("Finding fourth value threw " + t);
        }

        try {
            dynaForm.set("mappedProperty", "Fourth Key", "Fourth Value");
            assertEquals("Can set new value",
                    "Fourth Value",
                    (String) dynaForm.get("mappedProperty", "Fourth Key"));
        } catch (Throwable t) {
            fail("Finding fourth value threw " + t);
        }


    }


    /**
     * Test setSimpleProperty on a boolean property.
     */
    public void testSetSimpleBoolean() {

        try {
            boolean oldValue =
                    ((Boolean) dynaForm.get("booleanProperty")).booleanValue();
            boolean newValue = !oldValue;
            dynaForm.set("booleanProperty", new Boolean(newValue));
            assertTrue("Matched new value",
                    newValue ==
                    ((Boolean) dynaForm.get("booleanProperty")).booleanValue());
        } catch (Throwable e) {
            fail("Exception: " + e);
        }

    }


    /**
     * Test setSimpleProperty on a double property.
     */
    public void testSetSimpleDouble() {

        try {
            double oldValue =
                    ((Double) dynaForm.get("doubleProperty")).doubleValue();
            double newValue = oldValue + 1.0;
            dynaForm.set("doubleProperty", new Double(newValue));
            assertEquals("Matched new value",
                    newValue,
                    ((Double) dynaForm.get("doubleProperty")).doubleValue(),
                    (double) 0.005);
        } catch (Throwable e) {
            fail("Exception: " + e);
        }

    }


    /**
     * Test setSimpleProperty on a float property.
     */
    public void testSetSimpleFloat() {

        try {
            float oldValue =
                    ((Float) dynaForm.get("floatProperty")).floatValue();
            float newValue = oldValue + (float) 1.0;
            dynaForm.set("floatProperty", new Float(newValue));
            assertEquals("Matched new value",
                    newValue,
                    ((Float) dynaForm.get("floatProperty")).floatValue(),
                    (float) 0.005);
        } catch (Throwable e) {
            fail("Exception: " + e);
        }

    }


    /**
     * Test setSimpleProperty on a int property.
     */
    public void testSetSimpleInt() {

        try {
            int oldValue =
                    ((Integer) dynaForm.get("intProperty")).intValue();
            int newValue = oldValue + 1;
            dynaForm.set("intProperty", new Integer(newValue));
            assertEquals("Matched new value",
                    newValue,
                    ((Integer) dynaForm.get("intProperty")).intValue());
        } catch (Throwable e) {
            fail("Exception: " + e);
        }

    }


    /**
     * Test setSimpleProperty on a long property.
     */
    public void testSetSimpleLong() {

        try {
            long oldValue =
                    ((Long) dynaForm.get("longProperty")).longValue();
            long newValue = oldValue + 1;
            dynaForm.set("longProperty", new Long(newValue));
            assertEquals("Matched new value",
                    newValue,
                    ((Long) dynaForm.get("longProperty")).longValue());
        } catch (Throwable e) {
            fail("Exception: " + e);
        }

    }


    /**
     * Test setSimpleProperty on a short property.
     */
    public void testSetSimpleShort() {

        try {
            short oldValue =
                    ((Short) dynaForm.get("shortProperty")).shortValue();
            short newValue = (short) (oldValue + 1);
            dynaForm.set("shortProperty", new Short(newValue));
            assertEquals("Matched new value",
                    newValue,
                    ((Short) dynaForm.get("shortProperty")).shortValue());
        } catch (Throwable e) {
            fail("Exception: " + e);
        }

    }


    /**
     * Test setSimpleProperty on a String property.
     */
    public void testSetSimpleString() {

        try {
            String oldValue = (String) dynaForm.get("stringProperty");
            String newValue = oldValue + " Extra Value";
            dynaForm.set("stringProperty", newValue);
            assertEquals("Matched new value",
                    newValue,
                    (String) dynaForm.get("stringProperty"));
        } catch (Throwable e) {
            fail("Exception: " + e);
        }

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Set up the complex properties that cannot be configured from the
     * initial value expression.
     */
    protected void setupComplexProperties() {

        List listIndexed = new ArrayList();
        listIndexed.add("String 0");
        listIndexed.add("String 1");
        listIndexed.add("String 2");
        listIndexed.add("String 3");
        listIndexed.add("String 4");
        dynaForm.set("listIndexed", listIndexed);

        Map mappedProperty = new HashMap();
        mappedProperty.put("First Key", "First Value");
        mappedProperty.put("Second Key", "Second Value");
        dynaForm.set("mappedProperty", mappedProperty);

        Map mappedIntProperty = new HashMap();
        mappedIntProperty.put("One", new Integer(1));
        mappedIntProperty.put("Two", new Integer(2));
        dynaForm.set("mappedIntProperty", mappedIntProperty);

    }



    /**
     * Base for testGetDescriptorXxxxx() series of tests.
     *
     * @param name Name of the property to be retrieved
     * @param type Expected class type of this property
     */
    protected void testGetDescriptorBase(String name, Class type) {

        try {
            DynaProperty descriptor =
                    dynaForm.getDynaClass().getDynaProperty(name);
            assertNotNull("Got descriptor", descriptor);
            assertEquals("Got correct type", type, descriptor.getType());
        } catch (Throwable t) {
            fail("Threw an exception: " + t);
        }

    }


}


class DynaActionFormMapping extends ActionMapping {

    public DynaActionFormMapping(ModuleConfig appConfig) {
        this.appConfig = appConfig;
    }

    private ModuleConfig appConfig = null;

    public ModuleConfig getModuleConfig() {
        return (this.appConfig);
    }

    public String getName() {
        return ("dynaForm");
    }

}



class DynaActionFormConfig extends ApplicationConfig {

    public DynaActionFormConfig(FormBeanConfig beanConfig) {
        super("");
        this.beanConfig = beanConfig;
    }

    private FormBeanConfig beanConfig = null;

    public FormBeanConfig findFormBeanConfig(String name) {
        return (this.beanConfig);
    }
    
	
}


