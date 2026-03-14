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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletInputStream;

import junit.framework.TestCase;

/**
 * Tests for {@link MultipartIterator#createTextMultipartElement(String)},
 * focusing on the per-field size limit for text parameters.
 *
 * <p>GHSA-74mq-mxf7-qchm / CVE-2023-34396: the deprecated
 * {@code MultipartIterator} reads text form field bodies into a
 * {@code ByteArrayOutputStream} without any per-field size limit.
 * An attacker can send arbitrarily large text fields to exhaust heap
 * memory, causing a Denial of Service.</p>
 *
 * <p>These tests assert the <b>expected secure behaviour</b>: oversized
 * text fields must be rejected or truncated. They currently <b>fail</b>
 * because the production code has no per-field size limit yet. Once the
 * fix is applied, these tests should pass.</p>
 */
public class TestMultipartIteratorTextFieldLimit extends TestCase {

    /**
     * A size that is clearly too large for a single text form field
     * (512 KB). A properly hardened handler should reject or truncate
     * text fields well before this size.
     */
    private static final int LARGE_FIELD_SIZE = 512 * 1024;

    /**
     * A reasonable maximum size for a single text field value.
     */
    private static final int REASONABLE_MAX_TEXT_SIZE = 256 * 1024;

    /** Boundary used in the crafted multipart body. */
    private static final String BOUNDARY = "----TestBoundary9876";

    /**
     * Builds a multipart/form-data request body containing a single
     * text field with the given name and value.
     */
    private byte[] buildMultipartBody(String fieldName, byte[] fieldValue) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String header =
                "--" + BOUNDARY + "\r\n"
                + "Content-Disposition: form-data; name=\"" + fieldName + "\"\r\n"
                + "Content-Type: text/plain\r\n"
                + "\r\n";
        baos.write(header.getBytes("ISO-8859-1"));
        baos.write(fieldValue);
        String footer = "\r\n--" + BOUNDARY + "--\r\n";
        baos.write(footer.getBytes("ISO-8859-1"));
        return baos.toByteArray();
    }

    /**
     * Builds a multipart body with multiple text fields.
     */
    private byte[] buildMultipartBodyMultipleFields(String[] names, byte[][] values) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < names.length; i++) {
            String header =
                    "--" + BOUNDARY + "\r\n"
                    + "Content-Disposition: form-data; name=\"" + names[i] + "\"\r\n"
                    + "Content-Type: text/plain\r\n"
                    + "\r\n";
            baos.write(header.getBytes("ISO-8859-1"));
            baos.write(values[i]);
            baos.write("\r\n".getBytes("ISO-8859-1"));
        }
        String footer = "--" + BOUNDARY + "--\r\n";
        baos.write(footer.getBytes("ISO-8859-1"));
        return baos.toByteArray();
    }

    /**
     * Creates a mock HttpServletRequest that provides the multipart data
     * via getInputStream(), getContentType(), and getContentLength().
     */
    private StubMultipartRequest createRequest(byte[] body) {
        return new StubMultipartRequest(body, "multipart/form-data; boundary=" + BOUNDARY);
    }

    /**
     * A 512 KB text field must be rejected or truncated by a per-field
     * size limit.
     *
     * <p>Currently fails because createTextMultipartElement reads the
     * entire value into a ByteArrayOutputStream without any size check.
     * The fix should enforce a per-field size limit so that oversized
     * values are not stored at their full length.</p>
     */
    public void testGetNextElement_LargeTextField_IsRejected() throws Exception {
        byte[] largeValue = new byte[LARGE_FIELD_SIZE];
        Arrays.fill(largeValue, (byte) 'X');

        byte[] body = buildMultipartBody("bigField", largeValue);
        StubMultipartRequest request = createRequest(body);

        // maxSize=-1 means no overall size limit
        MultipartIterator iterator = new MultipartIterator(request, 4096, -1);

        MultipartElement element = iterator.getNextElement();

        // After the fix, the oversized field should either:
        // (a) be null (rejected entirely), or
        // (b) have a truncated value within a reasonable limit.
        if (element == null || element.getValue() == null) {
            // Rejected -- acceptable.
            return;
        }

        assertTrue(
                "Text field value length (" + element.getValue().length()
                        + ") should be at most " + REASONABLE_MAX_TEXT_SIZE
                        + " but the entire " + LARGE_FIELD_SIZE
                        + " bytes were stored (CVE-2023-34396)",
                element.getValue().length() <= REASONABLE_MAX_TEXT_SIZE);
    }

    /**
     * Multiple large text fields should not all be read into memory
     * at full size.
     *
     * <p>Currently fails because every field is read without
     * restriction, amplifying memory consumption.</p>
     */
    public void testGetNextElement_MultipleLargeTextFields_AreLimited() throws Exception {
        int fieldCount = 3;
        int fieldSize = LARGE_FIELD_SIZE; // 512 KB each
        byte[] value = new byte[fieldSize];
        Arrays.fill(value, (byte) 'Y');

        String[] names = new String[fieldCount];
        byte[][] values = new byte[fieldCount][];
        for (int i = 0; i < fieldCount; i++) {
            names[i] = "field" + i;
            values[i] = value;
        }

        byte[] body = buildMultipartBodyMultipleFields(names, values);
        StubMultipartRequest request = createRequest(body);

        MultipartIterator iterator = new MultipartIterator(request, 4096, -1);

        int storedAtFullSize = 0;
        for (int i = 0; i < fieldCount; i++) {
            MultipartElement element = iterator.getNextElement();
            if (element != null
                    && element.getValue() != null
                    && element.getValue().length() == fieldSize) {
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
     * A normal-sized text field submitted after an oversized field must
     * still be readable via getNextElement().  Verifies that skipping an
     * oversized field does not terminate the iteration and discard
     * subsequent fields.
     */
    public void testGetNextElement_NormalFieldAfterOversized_IsReturned() throws Exception {
        byte[] oversized = new byte[LARGE_FIELD_SIZE];
        Arrays.fill(oversized, (byte) 'Z');
        byte[] normal = "hello".getBytes("ISO-8859-1");

        String[] names = new String[] { "tooBig", "normal" };
        byte[][] values = new byte[][] { oversized, normal };

        byte[] body = buildMultipartBodyMultipleFields(names, values);
        StubMultipartRequest request = createRequest(body);

        MultipartIterator iterator = new MultipartIterator(request, 4096, -1);

        // The iterator must skip the oversized field and return the
        // normal field.  It must NOT return null prematurely.
        MultipartElement element = iterator.getNextElement();
        assertNotNull("Normal field after oversized must be returned", element);
        assertEquals("normal", element.getName());
        assertEquals("hello", element.getValue());

        // No more elements
        assertNull(iterator.getNextElement());
    }

    /**
     * A minimal HttpServletRequest stub that provides the multipart body
     * as a ServletInputStream along with Content-Type and Content-Length.
     */
    private static class StubMultipartRequest
            extends org.apache.struts.mock.MockHttpServletRequest {

        private final byte[] body;
        private final String contentTypeValue;

        StubMultipartRequest(byte[] body, String contentType) {
            super(new org.apache.struts.mock.MockHttpSession(
                    new org.apache.struts.mock.MockServletContext()));
            this.body = body;
            this.contentTypeValue = contentType;
            setContentType(contentType);
        }

        public String getCharacterEncoding() {
            return "ISO-8859-1";
        }

        public int getContentLength() {
            return body.length;
        }

        public String getContentType() {
            return contentTypeValue;
        }

        public ServletInputStream getInputStream() {
            return new DelegatingServletInputStream(new ByteArrayInputStream(body));
        }
    }

    /**
     * A ServletInputStream that delegates to a regular InputStream.
     */
    private static class DelegatingServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream delegate;

        DelegatingServletInputStream(ByteArrayInputStream delegate) {
            this.delegate = delegate;
        }

        public int read() throws IOException {
            return delegate.read();
        }

        public int read(byte[] b, int off, int len) throws IOException {
            return delegate.read(b, off, len);
        }

        public int available() throws IOException {
            return delegate.available();
        }

        public boolean markSupported() {
            return delegate.markSupported();
        }

        public void mark(int readLimit) {
            delegate.mark(readLimit);
        }

        public void reset() throws IOException {
            delegate.reset();
        }
    }
}
