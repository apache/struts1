/*
 * Copyright 2006 The Apache Software Foundation.
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

import java.util.HashSet;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;
import org.apache.struts.mock.TestMockBase;
import org.apache.struts.util.RequestUtils;

/**
 * Unit tests for ValidatorForm / DynaValidatorForm security properties.
 *
 * <p>CVE-2016-1181 / CVE-2016-1182: the SuppressPropertiesBeanIntrospector
 * configured in ActionServlet.initOther() suppresses "class",
 * "multipartRequestHandler", and "resultValueMap", but does NOT suppress
 * "validatorResults".  Because ValidatorForm.setValidatorResults() is
 * public, an attacker can send a request parameter named
 * "validatorResults" to overwrite validation results via
 * BeanUtils.populate().  This allows manipulation of validation outcomes
 * and, combined with session-scoped forms (CVE-2016-1181), can lead to
 * cross-thread interference.</p>
 */
public class TestValidatorFormPopulate extends TestMockBase {

    public TestValidatorFormPopulate(String theName) {
        super(theName);
    }

    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(
            new String[] { TestValidatorFormPopulate.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestValidatorFormPopulate.class);
    }

    public void setUp() {
        super.setUp();

        // Reproduce the SuppressPropertiesBeanIntrospector configuration
        // that ActionServlet.initOther() performs at startup.
        HashSet suppressProperties = new HashSet();
        suppressProperties.add("class");
        suppressProperties.add("multipartRequestHandler");
        suppressProperties.add("resultValueMap");
        suppressProperties.add("validatorResults");

        PropertyUtils.addBeanIntrospector(
                new SuppressPropertiesBeanIntrospector(suppressProperties));
        PropertyUtils.clearDescriptors();
    }

    public void tearDown() {
        // Reset PropertyUtils to avoid affecting other tests.
        PropertyUtils.resetBeanIntrospectors();
        PropertyUtils.clearDescriptors();
        super.tearDown();
    }

    /**
     * Ensure that the "validatorResults" property of ValidatorForm
     * cannot be populated via request parameters.
     *
     * <p>Currently fails because "validatorResults" is not included in
     * the SuppressPropertiesBeanIntrospector suppressed set.  This test
     * documents the vulnerability: an attacker-supplied
     * "validatorResults" parameter reaches setValidatorResults() through
     * BeanUtils.populate(), allowing manipulation of validation outcomes.</p>
     */
    public void testValidatorResults_IsNotPopulatedFromRequest() throws Exception {
        ValidatorForm form = new ValidatorForm();

        // Precondition: validatorResults is null
        assertNull("validatorResults should be null before populate",
                form.getValidatorResults());

        // Set up a request with a "validatorResults" parameter.
        // If the property is not suppressed, BeanUtils.populate() will
        // attempt to call setValidatorResults() with a converted value.
        request.setMethod("GET");
        request.setContentType("");
        request.addParameter("validatorResults", "injected");

        // Populate the form from the request.
        // If validatorResults is properly suppressed, this should either
        // silently ignore the parameter or throw no exception.
        // If NOT suppressed, BeanUtils will try to convert the string
        // "injected" to a ValidatorResults object, which will fail.
        boolean populateSucceeded = true;
        try {
            RequestUtils.populate(form, request);
        } catch (Exception e) {
            // BeanUtils.populate() threw because it tried to set the
            // validatorResults property (conversion from String failed).
            // This proves the property is NOT suppressed.
            populateSucceeded = false;
        }

        if (populateSucceeded) {
            // If populate succeeded without error, the property should
            // still be null (meaning it was suppressed/ignored).
            assertNull(
                "validatorResults must not be settable via request parameters",
                form.getValidatorResults());
        } else {
            // If populate threw, it means BeanUtils tried to set the
            // property.  The property is reachable and NOT suppressed.
            fail("BeanUtils.populate() attempted to set validatorResults "
                + "from a request parameter -- property is not suppressed");
        }
    }

    /**
     * Ensure that the "validatorResults" property of DynaValidatorForm
     * cannot be populated via request parameters.
     *
     * <p>Same vulnerability as testValidatorResults_IsNotPopulatedFromRequest
     * but for DynaValidatorForm, which also exposes setValidatorResults().</p>
     */
    public void testDynaValidatorResults_IsNotPopulatedFromRequest() throws Exception {
        DynaValidatorForm form = new DynaValidatorForm();

        assertNull("validatorResults should be null before populate",
                form.getValidatorResults());

        request.setMethod("GET");
        request.setContentType("");
        request.addParameter("validatorResults", "injected");

        boolean populateSucceeded = true;
        try {
            RequestUtils.populate(form, request);
        } catch (Exception e) {
            populateSucceeded = false;
        }

        if (populateSucceeded) {
            assertNull(
                "validatorResults must not be settable via request parameters",
                form.getValidatorResults());
        } else {
            fail("BeanUtils.populate() attempted to set validatorResults "
                + "from a request parameter -- property is not suppressed");
        }
    }
}
