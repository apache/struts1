/*
 * $Id$ 
 *
 * Copyright 2002-2004 The Apache Software Foundation.
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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for the <code>org.apache.struts.action.ActionMessage</code> class.
 *
 * @version $LastChangedRevision$ $Date$
 */
public class TestActionMessage extends TestCase {
    
    protected ActionMessage amWithNoValue = null;
    
    protected ActionMessage amWithOneValue = null;
    
    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestActionMessage(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(
            new String[] { TestActionMessage.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestActionMessage.class);
    }

    public void setUp() {
        amWithNoValue = new ActionMessage("amWithNoValue");
        amWithOneValue =
            new ActionMessage("amWithOneValue", new String("stringValue"));
    }

    public void tearDown() {
        amWithNoValue = null;
    }
    
    public void testActionMessageWithNoValue() {
        assertTrue(amWithNoValue.getValues() == null);
        assertTrue(amWithNoValue.getKey() == "amWithNoValue");
    }

    public void testActionMessageWithAStringValue() {
        Object[] values = amWithOneValue.getValues();
        assertTrue(values != null);
        assertTrue(values[0].equals("stringValue"));
        assertTrue(amWithOneValue.getKey() == "amWithOneValue");
    }
}
