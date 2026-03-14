/*
 * Copyright 2004 The Apache Software Foundation.
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

package org.apache.struts.validator;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.mock.TestMockBase;

public class TestValidatorForm extends TestMockBase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestValidatorForm(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(
            new String[] {TestValidatorForm.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        return new TestSuite(TestValidatorForm.class);
    }

    public void setUp() {
        super.setUp();
    }

    public void tearDown() {
    	super.tearDown();
    }

    /**
     * Test value of determine page.
     */
    public void testDeterminePage01() {
        DynaValidatorForm validatorForm = new DynaValidatorForm();
        ActionMapping mapping = new ActionMapping();

        mapping.setAcceptPage(null);
        validatorForm.setPage(-1);
        int page = validatorForm.determinePage(mapping, null);

        assertEquals(Integer.MAX_VALUE, page);
    }

    /**
     * Test value of determine page.
     */
    public void testDeterminePage02() {
        DynaValidatorForm validatorForm = new DynaValidatorForm();
        ActionMapping mapping = new ActionMapping();

        mapping.setAcceptPage(new Integer(-1));
        validatorForm.setPage(-1);
        int page = validatorForm.determinePage(mapping, null);

        assertEquals(-1, page);
    }

    /**
     * Test value of determine page.
     */
    public void testDeterminePage03() {
        DynaValidatorForm validatorForm = new DynaValidatorForm();
        ActionMapping mapping = new ActionMapping();

        mapping.setAcceptPage(new Integer(-1));
        validatorForm.setPage(0);
        int page = validatorForm.determinePage(mapping, null);

        assertEquals(0, page);
    }

    /**
     * Test value of determine page.
     */
    public void testDeterminePage04() {
        DynaValidatorForm validatorForm = new DynaValidatorForm();
        ActionMapping mapping = new ActionMapping();

        mapping.setAcceptPage(new Integer(-1));
        validatorForm.setPage(1);
        int page = validatorForm.determinePage(mapping, null);

        assertEquals(1, page);
    }

}

