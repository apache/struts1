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
package org.apache.struts.config;

import junit.framework.TestCase;

/**
 * Unit tests for {@link ForwardConfig}.
 */
public class TestForwardConfig extends TestCase {

    // ------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------

    public void testDefaultConstructor() {
        ForwardConfig fc = new ForwardConfig();
        assertNull(fc.getName());
        assertNull(fc.getPath());
        assertFalse(fc.getRedirect());
        assertNull(fc.getModule());
    }

    public void testConstructor_NamePathRedirect() {
        ForwardConfig fc = new ForwardConfig("success", "/home.jsp", false);
        assertEquals("success", fc.getName());
        assertEquals("/home.jsp", fc.getPath());
        assertFalse(fc.getRedirect());
    }

    public void testConstructor_WithRedirect() {
        ForwardConfig fc = new ForwardConfig("logout", "/login.jsp", true);
        assertTrue(fc.getRedirect());
    }

    public void testConstructor_WithModule() {
        ForwardConfig fc = new ForwardConfig("next", "/list.do", false, "/module1");
        assertEquals("/module1", fc.getModule());
    }

    // ------------------------------------------------------------------
    // Property setters / getters
    // ------------------------------------------------------------------

    public void testSetName() {
        ForwardConfig fc = new ForwardConfig();
        fc.setName("myForward");
        assertEquals("myForward", fc.getName());
    }

    public void testSetPath() {
        ForwardConfig fc = new ForwardConfig();
        fc.setPath("/result.jsp");
        assertEquals("/result.jsp", fc.getPath());
    }

    public void testSetRedirect() {
        ForwardConfig fc = new ForwardConfig();
        fc.setRedirect(true);
        assertTrue(fc.getRedirect());
    }

    public void testSetModule() {
        ForwardConfig fc = new ForwardConfig();
        fc.setModule("/admin");
        assertEquals("/admin", fc.getModule());
    }

    // ------------------------------------------------------------------
    // freeze() — mutations are rejected after freeze
    // ------------------------------------------------------------------

    public void testFreeze_PreventsMutation() {
        ForwardConfig fc = new ForwardConfig("ok", "/page.jsp", false);
        fc.freeze();

        try {
            fc.setName("changed");
            fail("Should throw IllegalStateException after freeze");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    public void testFreeze_PreventsMutation_Path() {
        ForwardConfig fc = new ForwardConfig("ok", "/page.jsp", false);
        fc.freeze();

        try {
            fc.setPath("/other.jsp");
            fail("Should throw IllegalStateException after freeze");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    public void testFreeze_PreventsMutation_Redirect() {
        ForwardConfig fc = new ForwardConfig("ok", "/page.jsp", false);
        fc.freeze();

        try {
            fc.setRedirect(true);
            fail("Should throw IllegalStateException after freeze");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    // ------------------------------------------------------------------
    // toString
    // ------------------------------------------------------------------

    public void testToString_ContainsNameAndPath() {
        ForwardConfig fc = new ForwardConfig("myFwd", "/target.jsp", true);
        String s = fc.toString();
        assertTrue(s.contains("myFwd"));
        assertTrue(s.contains("/target.jsp"));
    }
}
