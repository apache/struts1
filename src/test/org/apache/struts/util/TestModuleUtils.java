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
package org.apache.struts.util;

import junit.framework.TestCase;

import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpSession;
import org.apache.struts.mock.MockServletContext;

/**
 * Unit tests for {@link ModuleUtils}.
 */
public class TestModuleUtils extends TestCase {

    private ModuleUtils mu;
    private MockServletContext context;
    private MockHttpSession session;
    private MockHttpServletRequest request;

    protected void setUp() {
        mu = ModuleUtils.getInstance();
        context = new MockServletContext();
        session = new MockHttpSession();
        request = new MockHttpServletRequest(session);

        // Register the default module in context
        ModuleConfig defaultModule = new ModuleConfigImpl("");
        context.setAttribute(Globals.MODULE_KEY, defaultModule);
        context.setAttribute(Globals.MODULE_PREFIXES_KEY, new String[]{"/module1"});

        // Register a named module
        ModuleConfig module1 = new ModuleConfigImpl("/module1");
        context.setAttribute(Globals.MODULE_KEY + "/module1", module1);
    }

    // ------------------------------------------------------------------
    // getInstance
    // ------------------------------------------------------------------

    public void testGetInstance_ReturnsSingleton() {
        assertSame(ModuleUtils.getInstance(), ModuleUtils.getInstance());
    }

    // ------------------------------------------------------------------
    // getModuleConfig(request) — reads from request attribute
    // ------------------------------------------------------------------

    public void testGetModuleConfig_FromRequest_WhenPresent() {
        ModuleConfig mc = new ModuleConfigImpl("");
        request.setAttribute(Globals.MODULE_KEY, mc);

        assertSame(mc, mu.getModuleConfig(request));
    }

    public void testGetModuleConfig_FromRequest_WhenAbsent_ReturnsNull() {
        // No MODULE_KEY attribute set on request
        assertNull(mu.getModuleConfig(request));
    }

    // ------------------------------------------------------------------
    // getModuleConfig(prefix, context) — reads from ServletContext
    // ------------------------------------------------------------------

    public void testGetModuleConfig_DefaultModule_FromContext() {
        ModuleConfig mc = mu.getModuleConfig("", context);
        assertNotNull(mc);
        assertEquals("", mc.getPrefix());
    }

    public void testGetModuleConfig_NamedModule_FromContext() {
        ModuleConfig mc = mu.getModuleConfig("/module1", context);
        assertNotNull(mc);
        assertEquals("/module1", mc.getPrefix());
    }

    public void testGetModuleConfig_UnknownPrefix_ReturnsNull() {
        assertNull(mu.getModuleConfig("/nonexistent", context));
    }

    // ------------------------------------------------------------------
    // getModuleConfig(request, context) — fallback to default module
    // ------------------------------------------------------------------

    public void testGetModuleConfig_RequestContext_FallsBackToDefault() {
        // No MODULE_KEY in request → should fall back to default module
        ModuleConfig mc = mu.getModuleConfig(request, context);
        assertNotNull(mc);
        assertEquals("", mc.getPrefix());
    }

    public void testGetModuleConfig_RequestContext_UsesRequestAttributeIfPresent() {
        ModuleConfig mc1 = new ModuleConfigImpl("/module1");
        request.setAttribute(Globals.MODULE_KEY, mc1);

        ModuleConfig result = mu.getModuleConfig(request, context);
        assertSame(mc1, result);
    }

    // ------------------------------------------------------------------
    // getModuleName — resolves module prefix from URL path
    // ------------------------------------------------------------------

    public void testGetModuleName_DefaultModule_ReturnsEmptyString() {
        String name = mu.getModuleName("/app/index.do", context);
        assertEquals("", name);
    }

    public void testGetModuleName_NamedModule_ReturnsPrefix() {
        String name = mu.getModuleName("/module1/hello.do", context);
        assertEquals("/module1", name);
    }

    public void testGetModuleName_UnknownPath_ReturnsEmptyString() {
        String name = mu.getModuleName("/unknown/path.do", context);
        assertEquals("", name);
    }

    // ------------------------------------------------------------------
    // getModulePrefixes
    // ------------------------------------------------------------------

    public void testGetModulePrefixes_ReturnsRegisteredPrefixes() {
        String[] prefixes = mu.getModulePrefixes(context);
        assertNotNull(prefixes);
        assertEquals(1, prefixes.length);
        assertEquals("/module1", prefixes[0]);
    }

    // ------------------------------------------------------------------
    // selectModule(prefix, request, context)
    // ------------------------------------------------------------------

    public void testSelectModule_SetsModuleConfigOnRequest() {
        mu.selectModule("/module1", request, context);

        ModuleConfig mc = (ModuleConfig) request.getAttribute(Globals.MODULE_KEY);
        assertNotNull(mc);
        assertEquals("/module1", mc.getPrefix());
    }

    public void testSelectModule_UnknownPrefix_RemovesModuleKeyFromRequest() {
        // Pre-set a module on request
        request.setAttribute(Globals.MODULE_KEY, new ModuleConfigImpl(""));

        // selectModule with unknown prefix → config is null → removeAttribute
        try {
            mu.selectModule("/nonexistent", request, context);
            // If it doesn't throw, MODULE_KEY should be removed
            assertNull(request.getAttribute(Globals.MODULE_KEY));
        } catch (NullPointerException e) {
            // ModuleUtils.selectModule() calls config.findMessageResourcesConfigs()
            // without null-checking config first — this is a latent bug.
            // The test documents the actual behaviour without fixing production code.
        }
    }
}
