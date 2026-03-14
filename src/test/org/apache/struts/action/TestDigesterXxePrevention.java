/*
 * Copyright 2026 The Struts1-Forever Project.
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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.ServletConfig;

import junit.framework.TestCase;

import org.apache.commons.digester.Digester;
import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;
import org.xml.sax.SAXException;

/**
 * XXE prevention tests for Digester instances (GHSA-h36r-7whm-p5hf).
 *
 * <p>Verifies that Digester instances returned by {@code ActionServlet}
 * reject XML documents that contain DOCTYPE declarations, which are the
 * primary vector for XXE attacks.</p>
 *
 * <p>Tests that assert XXE is blocked are expected to <strong>FAIL</strong>
 * until the production fix is applied.</p>
 */
public class TestDigesterXxePrevention extends TestCase {

    /**
     * ActionServlet subclass that provides a MockServletConfig without
     * triggering the full init() lifecycle (which requires web.xml).
     */
    private static class TestableActionServlet extends ActionServlet {
        private final ServletConfig servletConfig;

        TestableActionServlet(ServletConfig config) {
            this.servletConfig = config;
        }

        public ServletConfig getServletConfig() {
            return servletConfig;
        }
    }

    /**
     * XML with an inline DOCTYPE declaration.
     * A vulnerable parser processes this without error.
     * A hardened parser must throw when it encounters the DOCTYPE.
     */
    private static final String XML_WITH_DOCTYPE =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<!DOCTYPE foo [\n" +
        "  <!ENTITY xxe SYSTEM \"file:///etc/passwd\">\n" +
        "]>\n" +
        "<struts-config></struts-config>";

    /**
     * Minimal valid Struts config with no DOCTYPE — must always parse cleanly.
     */
    private static final String XML_SAFE =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<struts-config></struts-config>";

    // ------------------------------------------------------------------
    // Baseline: safe XML must parse without error
    // ------------------------------------------------------------------

    /**
     * A safe XML document (no DOCTYPE) must be accepted by the Digester
     * returned by {@code initConfigDigester()}.
     */
    public void testConfigDigester_SafeXml_ParsesWithoutError() throws Exception {
        ActionServlet servlet = new TestableActionServlet(
                new MockServletConfig(new MockServletContext()));
        servlet.initInternal();

        Digester digester = servlet.initConfigDigester();

        InputStream in = new ByteArrayInputStream(XML_SAFE.getBytes("UTF-8"));
        try {
            digester.parse(in);
        } catch (SAXException e) {
            fail("Safe XML must not throw: " + e.getMessage());
        } finally {
            servlet.destroyInternal();
        }
    }

    // ------------------------------------------------------------------
    // XXE prevention: DOCTYPE must be rejected (H-1)
    // ------------------------------------------------------------------

    /**
     * The Digester returned by {@code initConfigDigester()} must reject XML
     * with a DOCTYPE declaration to prevent XXE attacks (GHSA-h36r-7whm-p5hf).
     *
     * <p>Currently FAILS — {@code initConfigDigester()} does not set
     * {@code disallow-doctype-decl=true} on the Digester.</p>
     */
    public void testConfigDigester_XmlWithDoctype_IsRejected() throws Exception {
        ActionServlet servlet = new TestableActionServlet(
                new MockServletConfig(new MockServletContext()));
        servlet.initInternal();

        Digester digester = servlet.initConfigDigester();

        InputStream in = new ByteArrayInputStream(XML_WITH_DOCTYPE.getBytes("UTF-8"));
        try {
            digester.parse(in);
            fail("DOCTYPE declaration must be rejected by a hardened Digester (GHSA-h36r-7whm-p5hf)");
        } catch (SAXException e) {
            // Expected after fix: parser rejects DOCTYPE
        } finally {
            servlet.destroyInternal();
        }
    }
}
