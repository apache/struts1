/*
 * $Id$ 
 *
 * Copyright 2005 The Apache Software Foundation.
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

import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.IndexedPropertyDescriptor;
import org.apache.commons.beanutils.DynaProperty;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.action.DynaActionForm</code> class.
 */
public class TestCGLibDynaActionForm extends TestDynaActionForm {


    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestCGLibDynaActionForm(String theName)
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
            (new String[] {TestCGLibDynaActionForm.class.getName()});
    }


    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite()
    {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestCGLibDynaActionForm.class);
    }


    // ----------------------------------------------------- Setup and Teardown


    public void setUp() {
        enhanced = true;

        super.setUp();

    }


    public void tearDown() {

        super.tearDown();
    }


    // --------------------------------------------- Create New DynaActionForms


    /**
     * Check Property Descriptors
     */
    public void testDesciptors() {

        // Introspect the bean and find property descriptors
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(dynaForm.getClass());
            PropertyDescriptor[] descriptors =  beanInfo.getPropertyDescriptors();
            DynaProperty[] dynaProperties = dynaClass.getDynaProperties();
            for (int i = 0; i < dynaProperties.length; i++) {
                DynaProperty dynaProperty = dynaProperties[i];
                PropertyDescriptor descriptor = null;
                for (int j = 0; j < descriptors.length; j++) {
                    if (dynaProperty.getName().equals(descriptors[j].getName())) {
                        descriptor = descriptors[j];
                        break;
                    }
                }
                if (descriptor == null) {
                    fail(i+" Descriptor not found: " + dynaProperty.getName());
                } else {
                    boolean indexedDescriptor = (descriptor instanceof IndexedPropertyDescriptor);
                    if (!(dynaProperty.getType() == descriptor.getPropertyType())) {
                        // ignore java.util.List - not proper indexed properties
                        if (!(List.class.isAssignableFrom(dynaProperty.getType()))) {
                            fail(i+" Descriptor type error: " + dynaProperty.getName() +
                                 " dyna=" + dynaProperty.getType() +
                                 " regular=" + descriptor.getPropertyType());
                        }
                    }
                    if (!(dynaProperty.isIndexed() == indexedDescriptor)) {
                        fail(i+" Descriptor index error: " + dynaProperty.getName() +
                             " dyna=" + dynaProperty.isIndexed() +
                             " regular=" + indexedDescriptor);
                    }
                }
            }
        } catch (Exception e) {
            fail("Exception thrown " + e);
        }

    }


    /**
     * Test simple read property
     */
    public void testSimpleRead() {

        String property   = "floatProperty";
        String methodName = getMethodName(property, true);
        Class[] types = null;
        Object[] args = null;


        Object expected = dynaForm.get(property);
        try {
            Method method = dynaForm.getClass().getMethod(methodName, types);
            Object result = method.invoke(dynaForm, args);
            assertEquals(expected, result);
        } catch(InvocationTargetException e) {
            fail("InvocationTargetException thrown " + e.getCause());
        } catch(Exception e) {
            fail("Exception thrown " + e);
        }
    }

    /**
     * Test simple write property
     */
    public void testSimpleWrite() {

        String property   = "floatProperty";
        String methodName = getMethodName(property, false);
        Object expected = new Float("5.4321");
        Class[] types = new Class[]{Float.TYPE};
        Object[] args = new Object[] {expected};

        try {
            Method method = dynaForm.getClass().getMethod(methodName, types);
            method.invoke(dynaForm, args);
            assertEquals(expected, dynaForm.get(property));
        } catch(InvocationTargetException e) {
            fail("InvocationTargetException thrown " + e.getCause());
        } catch(Exception e) {
            fail("Exception thrown " + e);
        }
    }
    /**
     * Test simple write property
     */
    public void testIndexedRead() {

        String property   = "stringArray";
        String methodName = getMethodName(property, true);
        Integer idx = new Integer(1);
        Class[] types = new Class[] {Integer.TYPE};
        Object[] args = new Object[] {idx};

        Object expected = dynaForm.get(property, idx.intValue());
        
        try {
            Method method = dynaForm.getClass().getMethod(methodName, types);
            Object result = method.invoke(dynaForm, args);
            assertEquals(expected, result);
        } catch(InvocationTargetException e) {
            fail("InvocationTargetException thrown " + e.getCause());
        } catch(Exception e) {
            fail("Exception thrown " + e);
        }
        

    }

    /**
     * Test indexed write property
     */
    public void testIndexedWrite() {

        String property   = "stringArray";
        String methodName = getMethodName(property, false);
        Integer idx = new Integer(1);
        Object expected = "some new value";
        Class[] types = new Class[] {Integer.TYPE, String.class};
        Object[] args = new Object[] {idx, expected};

        try {
            Method method = dynaForm.getClass().getMethod(methodName, types);
            method.invoke(dynaForm, args);
            assertEquals(expected, dynaForm.get(property, idx.intValue()));
        } catch(InvocationTargetException e) {
            fail("InvocationTargetException thrown " + e.getCause());
        } catch(Exception e) {
            fail("Exception thrown " + e);
        }
        

    }

    /**
     * Test simple write property
     */
    public void testIndexedReadInt() {

        String property   = "intIndexed";
        String methodName = getMethodName(property, true);
        Integer idx = new Integer(1);
        Class[] types = new Class[] {Integer.TYPE};
        Object[] args = new Object[] {idx};

        Object expected = dynaForm.get(property, idx.intValue());
        
        try {
            Method method = dynaForm.getClass().getMethod(methodName, types);
            Object result = method.invoke(dynaForm, args);
            assertEquals(expected, result);
        } catch(InvocationTargetException e) {
            fail("InvocationTargetException thrown " + e.getCause());
        } catch(Exception e) {
            fail("Exception thrown " + e);
        }
        

    }

    /**
     * Test indexed write property
     */
    public void testIndexedWriteInt() {

        String property   = "intIndexed";
        String methodName = getMethodName(property, false);
        Integer idx = new Integer(1);
        Object expected = new Integer(54321);
        Class[] types = new Class[] {Integer.TYPE, Integer.TYPE};
        Object[] args = new Object[] {idx, expected};

        try {
            Method method = dynaForm.getClass().getMethod(methodName, types);
            method.invoke(dynaForm, args);
            assertEquals(expected, dynaForm.get(property, idx.intValue()));
        } catch(InvocationTargetException e) {
            fail("InvocationTargetException thrown " + e.getCause());
        } catch(Exception e) {
            fail("Exception thrown " + e);
        }

    }

    private String getMethodName(String property, boolean getter) {
        String methodName = (getter ? "get" : "set") +
                             property.substring(0, 1).toUpperCase() +
                             property.substring(1);
        return methodName;
    }

    


}


