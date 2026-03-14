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
 * Unit tests for {@link ExceptionConfig}.
 */
public class TestExceptionConfig extends TestCase {

    // ------------------------------------------------------------------
    // Defaults
    // ------------------------------------------------------------------

    public void testDefaults() {
        ExceptionConfig ec = new ExceptionConfig();
        assertNull(ec.getType());
        assertNull(ec.getKey());
        assertNull(ec.getPath());
        assertNull(ec.getBundle());
        // default scope is "request"
        assertEquals("request", ec.getScope());
        // default handler is the standard ExceptionHandler
        assertEquals("org.apache.struts.action.ExceptionHandler", ec.getHandler());
    }

    // ------------------------------------------------------------------
    // Property setters / getters
    // ------------------------------------------------------------------

    public void testSetType() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.setType("java.lang.RuntimeException");
        assertEquals("java.lang.RuntimeException", ec.getType());
    }

    public void testSetKey() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.setKey("error.runtime");
        assertEquals("error.runtime", ec.getKey());
    }

    public void testSetPath() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.setPath("/error.jsp");
        assertEquals("/error.jsp", ec.getPath());
    }

    public void testSetBundle() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.setBundle("altBundle");
        assertEquals("altBundle", ec.getBundle());
    }

    public void testSetScope() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.setScope("session");
        assertEquals("session", ec.getScope());
    }

    public void testSetHandler() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.setHandler("com.example.MyExceptionHandler");
        assertEquals("com.example.MyExceptionHandler", ec.getHandler());
    }

    // ------------------------------------------------------------------
    // freeze() — mutations rejected after freeze
    // ------------------------------------------------------------------

    public void testFreeze_PreventsMutation_Type() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.setType("java.io.IOException");
        ec.freeze();

        try {
            ec.setType("java.lang.RuntimeException");
            fail("Should throw IllegalStateException after freeze");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    public void testFreeze_PreventsMutation_Key() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.freeze();

        try {
            ec.setKey("some.key");
            fail("Should throw IllegalStateException after freeze");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    public void testFreeze_PreventsMutation_Path() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.freeze();

        try {
            ec.setPath("/error.jsp");
            fail("Should throw IllegalStateException after freeze");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    // ------------------------------------------------------------------
    // toString
    // ------------------------------------------------------------------

    public void testToString_ContainsType() {
        ExceptionConfig ec = new ExceptionConfig();
        ec.setType("java.lang.IllegalStateException");
        ec.setKey("error.state");
        ec.setPath("/error.jsp");
        String s = ec.toString();
        assertTrue(s.contains("java.lang.IllegalStateException"));
    }
}
