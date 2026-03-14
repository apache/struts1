/*
 * Copyright 2025 the original author or authors.
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

package org.apache.struts.upload;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpSession;
import org.apache.struts.mock.MockServletContext;

/**
 * Tests for {@link CommonsMultipartRequestHandler}, focusing on the
 * per-field size limit for text parameters.
 *
 * <p>GHSA-74mq-mxf7-qchm / CVE-2023-34396: text form fields in multipart
 * requests are loaded entirely into memory as String objects with no
 * per-field size limit. An attacker can send arbitrarily large text fields
 * to exhaust heap memory, causing a Denial of Service.</p>
 *
 * <p>These tests assert the <b>expected secure behaviour</b>: oversized
 * text fields must be rejected or truncated. They currently <b>fail</b>
 * because the production code has no per-field size limit yet. Once the
 * fix is applied, these tests should pass.</p>
 */
public class TestCommonsMultipartRequestHandler extends TestCase {

    /**
     * A size that is clearly too large for a single text form field
     * (1 MB). A properly hardened handler should reject or truncate
     * text fields well before this size.
     */
    private static final int LARGE_FIELD_SIZE = 1024 * 1024;

    /**
     * A reasonable maximum size for a single text field value.
     * The exact threshold will be determined by the fix, but any
     * sane limit should be well below LARGE_FIELD_SIZE.
     */
    private static final int REASONABLE_MAX_TEXT_SIZE = 256 * 1024;

    /**
     * Subclass that exposes the protected addTextParameter method
     * for direct unit testing.
     */
    static class ExposedHandler extends CommonsMultipartRequestHandler {

        /** Initialize internal Hashtables that addTextParameter writes to. */
        ExposedHandler() {
            // The parent class only initializes these in handleRequest(),
            // so we must do it manually for isolated unit testing.
            try {
                java.lang.reflect.Field f;
                f = CommonsMultipartRequestHandler.class.getDeclaredField("elementsText");
                f.setAccessible(true);
                f.set(this, new Hashtable());

                f = CommonsMultipartRequestHandler.class.getDeclaredField("elementsAll");
                f.setAccessible(true);
                f.set(this, new Hashtable());

                f = CommonsMultipartRequestHandler.class.getDeclaredField("elementsFile");
                f.setAccessible(true);
                f.set(this, new Hashtable());
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize handler for testing", e);
            }
        }

        public void invokeAddTextParameter(HttpServletRequest request, FileItem item) {
            addTextParameter(request, item);
        }
    }

    private MockServletContext context;
    private MockHttpSession session;
    private MockHttpServletRequest request;

    protected void setUp() throws Exception {
        super.setUp();
        context = new MockServletContext();
        session = new MockHttpSession(context);
        request = new MockHttpServletRequest(session) {
            public String getCharacterEncoding() {
                return "UTF-8";
            }
        };
    }

    /**
     * Creates a DiskFileItem representing a text form field with the
     * given name and value bytes.
     */
    private FileItem createTextFieldItem(String fieldName, byte[] value) throws IOException {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        DiskFileItem item = new DiskFileItem(
                fieldName,       // field name
                "text/plain",    // content type
                true,            // isFormField
                null,            // fileName (null for text fields)
                4096,            // sizeThreshold
                tempDir          // repository
        );
        OutputStream os = item.getOutputStream();
        os.write(value);
        os.close();
        return item;
    }

    /**
     * A 1 MB text field must be rejected or truncated by a per-field
     * size limit.
     *
     * <p>Currently fails because addTextParameter loads the entire
     * value into memory without any size check. The fix should enforce
     * a per-field size limit so that oversized values are not stored
     * at their full length.</p>
     */
    public void testAddTextParameter_LargeField_IsRejected() throws Exception {
        byte[] largeValue = new byte[LARGE_FIELD_SIZE];
        Arrays.fill(largeValue, (byte) 'A');

        FileItem item = createTextFieldItem("bigField", largeValue);

        ExposedHandler handler = new ExposedHandler();
        handler.invokeAddTextParameter(request, item);

        Hashtable textElements = handler.getTextElements();
        String[] values = (String[]) textElements.get("bigField");

        // After the fix, the oversized field should either:
        // (a) not be stored at all (values == null), or
        // (b) be truncated to within a reasonable limit.
        if (values == null) {
            // Option (a): field was rejected entirely -- acceptable.
            return;
        }

        assertTrue(
                "Text field value length (" + values[0].length()
                        + ") should be at most " + REASONABLE_MAX_TEXT_SIZE
                        + " but the entire " + LARGE_FIELD_SIZE
                        + " bytes were stored (CVE-2023-34396)",
                values[0].length() <= REASONABLE_MAX_TEXT_SIZE);
    }

    /**
     * Multiple large text fields should not all be stored at full size.
     *
     * <p>Currently fails because every field is stored without
     * restriction, amplifying memory consumption.</p>
     */
    public void testAddTextParameter_MultipleLargeFields_AreLimited() throws Exception {
        int fieldCount = 5;
        int fieldSize = LARGE_FIELD_SIZE; // 1 MB each
        byte[] value = new byte[fieldSize];
        Arrays.fill(value, (byte) 'B');

        ExposedHandler handler = new ExposedHandler();

        for (int i = 0; i < fieldCount; i++) {
            FileItem item = createTextFieldItem("field" + i, value);
            handler.invokeAddTextParameter(request, item);
        }

        Hashtable textElements = handler.getTextElements();

        // After the fix, at least some fields should have been rejected
        // or truncated. Count how many were stored at full size.
        int storedAtFullSize = 0;
        for (int i = 0; i < fieldCount; i++) {
            String[] values = (String[]) textElements.get("field" + i);
            if (values != null && values[0].length() == fieldSize) {
                storedAtFullSize++;
            }
        }

        assertEquals(
                "No field should be stored at full " + fieldSize
                        + " bytes (CVE-2023-34396), but " + storedAtFullSize
                        + " out of " + fieldCount + " were",
                0, storedAtFullSize);
    }

    /**
     * Repeated values for the same field name with oversized data
     * should not all accumulate without bound.
     *
     * <p>Currently fails because the handler keeps growing the
     * String array, storing every oversized value.</p>
     */
    public void testAddTextParameter_RepeatedLargeSameName_AreLimited() throws Exception {
        int repeatCount = 10;
        int fieldSize = LARGE_FIELD_SIZE; // 1 MB each
        byte[] value = new byte[fieldSize];
        Arrays.fill(value, (byte) 'C');

        ExposedHandler handler = new ExposedHandler();

        for (int i = 0; i < repeatCount; i++) {
            FileItem item = createTextFieldItem("repeated", value);
            handler.invokeAddTextParameter(request, item);
        }

        Hashtable textElements = handler.getTextElements();
        String[] values = (String[]) textElements.get("repeated");

        if (values == null) {
            // All rejected -- acceptable.
            return;
        }

        // After the fix, no individual value should be stored at full size.
        for (int i = 0; i < values.length; i++) {
            assertTrue(
                    "Repeated value [" + i + "] length (" + values[i].length()
                            + ") should be at most " + REASONABLE_MAX_TEXT_SIZE
                            + " (CVE-2023-34396)",
                    values[i].length() <= REASONABLE_MAX_TEXT_SIZE);
        }
    }

    /**
     * A normal-sized text field submitted after an oversized field must
     * still be stored correctly.  Verifies that rejecting an oversized
     * field does not discard subsequent fields.
     */
    public void testAddTextParameter_NormalFieldAfterOversized_IsStored() throws Exception {
        byte[] oversized = new byte[LARGE_FIELD_SIZE];
        Arrays.fill(oversized, (byte) 'Z');

        byte[] normal = "hello".getBytes("UTF-8");

        ExposedHandler handler = new ExposedHandler();

        // First: oversized field -- should be skipped
        FileItem bigItem = createTextFieldItem("tooBig", oversized);
        handler.invokeAddTextParameter(request, bigItem);

        // Second: normal field -- must be stored
        FileItem normalItem = createTextFieldItem("normal", normal);
        handler.invokeAddTextParameter(request, normalItem);

        Hashtable textElements = handler.getTextElements();

        assertNull("Oversized field should have been rejected",
                textElements.get("tooBig"));

        String[] vals = (String[]) textElements.get("normal");
        assertNotNull("Normal field after oversized must be stored", vals);
        assertEquals("hello", vals[0]);
    }
}
