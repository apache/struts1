/*
 * \$Header\$
 * \$Revision\$
 * \$Date\$
 *
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
package org.apache.struts.upload;

import junit.framework.TestCase;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * This is a unit test for the MultipartBoundaryInputStream class.
 */
public class MultipartBoundaryInputStreamTest extends TestCase
{
    public static final String DISPOSITION_FORM_DATA = "form-data";

    public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

    /**
     * The resource with a single multipart element with a single final boundary.
     */
    public static final String RESOURCE_SINGLE_TEXT_ELEMENT = "/org/apache/struts/upload/resources/multipart-single-text-element.txt";

    /**
     * The resource with two multipart elements with two boundaries.
     */
    public static final String RESOURCE_TWO_TEXT_ELEMENT = "/org/apache/struts/upload/resources/multipart-two-text-element.txt";

    /**
     * The resource with three multipart elements, two text one file.
     */
    public static final String RESOURCE_THREE_MIXED_ELEMENT = "/org/apache/struts/upload/resources/multipart-three-mixed-element.txt";

    /**
     * The resource with three multipart elements, the first with alot of newlines.
     */
    public static final String RESOURCE_THREE_MIXED_NEWLINES = "/org/apache/struts/upload/resources/multipart-three-mixed-newline.txt";

    /**
     * The resource with a problem.
     */
    public static final String RESOURCE_PROBLEM1 = "/org/apache/struts/upload/resources/multipart-problem1.txt";

    public static final String RESOURCE_PROBLEM2 = "/org/apache/struts/upload/resources/multipart-problem2.txt";

    public static final String RESOURCE_PROBLEM3 = "/org/apache/struts/upload/resources/multipart-problem3.txt";



    /**
     * The resource with file data of 5400 bytes.
     */
    public static final String RESOURCE_5400B = "/org/apache/struts/upload/resources/multipart-file-element-5400b.txt";

    /**
     * The resource to compare the newline data with.
     */
    public static final String RESOURCE_NEWLINES = "/org/apache/struts/upload/resources/multipart-file-element-newlines.txt";

    /**
     * The resource that is the value of problem1.
     */
    public static final String RESOURCE_DESIRED_PROBLEM1 = "/org/apache/struts/upload/resources/multipart-file-element-problem1.txt";

    public static final String RESOURCE_DESIRED_PROBLEM2 = "/org/apache/struts/upload/resources/multipart-file-element-problem2.txt";

    public static final String RESOURCE_DESIRED_PROBLEM3 = "/org/apache/struts/upload/resources/multipart-file-element-problem3.txt";

    /**
     * The boundary for the single multipart element resource.
     */
    public static final String BOUNDARY_SINGLE_TEXT_ELEMENT = "x9F38FJJ";

    /**
     * The boundary for the two multipart element resource.
     */
    public static final String BOUNDARY_TWO_TEXT_ELEMENT = "----------x9F38FJJ339502";

    /**
     * The boundary for problem1.
     */
    public static final String BOUNDARY_PROBLEM1 = "---------------------------63341846741";

    public static final String BOUNDARY_PROBLEM2 = "---------------------------7d21fed3c0352";

    public static final String BOUNDARY_PROBLEM3 = "---------------------------7d21cb2b102cc";

    /**
     * The boundary for the three multipart element resource.
     */
    public static final String BOUNDARY_THREE_TEXT_ELEMENT = "----------g320nnjJJF32AA666FF";

    public static final String NAME_SINGLE_TEXT_ELEMENT = "testData";

    public static final String NAME_TWO_TEXT_ELEMENT_1 = "testData1";

    public static final String NAME_TWO_TEXT_ELEMENT_2 = "testData2";

    public static final String NAME_THREE_MIXED_ELEMENT_1 = "testFile1";

    public static final String NAME_THREE_MIXED_ELEMENT_2 = "testData1";

    public static final String NAME_THREE_MIXED_ELEMENT_3 = "testData2";

    public static final String NAME_PROBLEM1_1 = "theText";

    public static final String NAME_PROBLEM1_2 = "theFile";

    public static final String NAME_PROBLEM1_3 = "writeFile";

    public static final String NAME_PROBLEM1_4 = "filePath";

    public static final String NAME_PROBLEM1_5 = "submit";

    /**
     * The expected value of the element.
     */
    public static final String DESIRED_SINGLE_TEXT_ELEMENT = "this is test data";

    /**
     * The expected value of the element.
     */
    public static final String DESIRED_TEST_DATA_1 = "this is test data 1";

    /**
     * The expected value of the element.
     */
    public static final String DESIRED_TEST_DATA_2 = "this is test data 2";

    /**
     * The InputStream to single test data.
     */
    protected InputStream singleTextElementDataStream;

    /**
     * The InputStream to test two element data.
     */
    protected InputStream twoTextElementDataStream;

    /**
     * The InputStream to test three element mixed data.
     */
    protected InputStream threeMixedElementDataStream;

    /**
     * The InputStream to the multipart request with all the newlines.
     */
    protected InputStream newlinesDataStream;

    /**
     * The InputStream to the 5400 byte test file.
     */
    protected InputStream testFile5400bDataStream;

    /**
     * The InputStream to the problem1 test file.
     */
    protected InputStream testFileProblem1;

    protected InputStream testFileProblem2;

    protected InputStream testFileProblem3;

    /**
     * The InputStream to the file with alot of newlines.
     */
    protected InputStream testFileNewlines;

    /**
     * The InputStream to the file with a problem.
     */
    protected InputStream testProblem1;

    protected InputStream testProblem2;

    protected InputStream testProblem3;

    /**
     *
     *                   //  \\
     *                  ((   ''
     *                   \\__,
     *                  /6 (%)\,
     *                 (__/:";,;\--____----_
     *                  ;; :';,:';`;,';,;';`,`_
     *                    ;:,;;';';,;':,';';,-Y\
     *                     ;,;,;';';,;':;';'; Z/
     *                     / ;,';';,;';,;';;'
     *                    / / |';/~~~~~\';;'
     *                   ( K  | |      || |
     *                    \_\ | |      || |
     *                     \Z | |      || |
     *                        L_|      LL_|
     *                        LW/      LLW/
     */
    public MultipartBoundaryInputStreamTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();

        this.singleTextElementDataStream = this.getClass().getResourceAsStream(RESOURCE_SINGLE_TEXT_ELEMENT);
        if (this.singleTextElementDataStream == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_SINGLE_TEXT_ELEMENT);
        }
        this.twoTextElementDataStream    = this.getClass().getResourceAsStream(RESOURCE_TWO_TEXT_ELEMENT);
        if (this.twoTextElementDataStream == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_TWO_TEXT_ELEMENT);
        }
        this.threeMixedElementDataStream = this.getClass().getResourceAsStream(RESOURCE_THREE_MIXED_ELEMENT);
        if (this.threeMixedElementDataStream == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_THREE_MIXED_ELEMENT);
        }
        this.newlinesDataStream = this.getClass().getResourceAsStream(RESOURCE_THREE_MIXED_NEWLINES);
        if (this.newlinesDataStream == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_THREE_MIXED_NEWLINES);
        }
        this.testFile5400bDataStream = this.getClass().getResourceAsStream(RESOURCE_5400B);
        if (this.testFile5400bDataStream == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_5400B);
        }
        this.testFileNewlines = this.getClass().getResourceAsStream(RESOURCE_NEWLINES);
        if (this.testFileNewlines == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_NEWLINES);
        }
        this.testFileProblem1 = this.getClass().getResourceAsStream(RESOURCE_DESIRED_PROBLEM1);
        if (this.testFileProblem1 == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_DESIRED_PROBLEM1);
        }
        this.testFileProblem2 = this.getClass().getResourceAsStream(RESOURCE_DESIRED_PROBLEM2);
        if (this.testFileProblem2 == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_DESIRED_PROBLEM2);
        }
        this.testFileProblem3 = this.getClass().getResourceAsStream(RESOURCE_DESIRED_PROBLEM3);
        if (this.testFileProblem3 == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_DESIRED_PROBLEM3);
        }
        this.testProblem1 = this.getClass().getResourceAsStream(RESOURCE_PROBLEM1);
        if (this.testProblem1 == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_PROBLEM1);
        }
        this.testProblem2 = this.getClass().getResourceAsStream(RESOURCE_PROBLEM2);
        if (this.testProblem2 == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_PROBLEM2);
        }
        this.testProblem3 = this.getClass().getResourceAsStream(RESOURCE_PROBLEM3);
        if (this.testProblem3 == null)
        {
            fail("Couldn't obtain resource " + RESOURCE_PROBLEM3);
        }
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();

        this.singleTextElementDataStream = null;
        this.twoTextElementDataStream    = null;
        this.threeMixedElementDataStream = null;
        this.testFile5400bDataStream     = null;
        this.testFileNewlines            = null;
        this.testFileProblem1            = null;
        this.testProblem1                = null;
        this.testFileProblem2            = null;
        this.testProblem2                = null;
    }

    public void testSingleTextElementData() throws Exception
    {
        MultipartBoundaryInputStream inputStream = new MultipartBoundaryInputStream();
        inputStream.setBoundary(BOUNDARY_SINGLE_TEXT_ELEMENT);
        inputStream.setInputStream(this.singleTextElementDataStream);
        //get only element
        String resultData = streamToString(inputStream);
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_SINGLE_TEXT_ELEMENT, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        assertEquals(DESIRED_SINGLE_TEXT_ELEMENT, resultData);
        assertTrue(inputStream.isBoundaryEncountered());
        assertTrue(inputStream.isFinalBoundaryEncountered());
        assertTrue(inputStream.isEndOfStream());
    }

    public void testTwoTextElementData() throws Exception
    {
        MultipartBoundaryInputStream inputStream = new MultipartBoundaryInputStream();
        inputStream.setBoundary(BOUNDARY_TWO_TEXT_ELEMENT);
        inputStream.setInputStream(this.twoTextElementDataStream);

        //get first element
        String resultData = streamToString(inputStream);
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_TWO_TEXT_ELEMENT_1, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals(DESIRED_TEST_DATA_1, resultData);

        //get second element
        inputStream.resetForNextBoundary();
        resultData = streamToString(inputStream);
        assertEquals(DESIRED_TEST_DATA_2, resultData);
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_TWO_TEXT_ELEMENT_2, inputStream.getElementName());
        assertEquals("utf-8", inputStream.getElementCharset());
        assertTrue(!inputStream.isElementFile());
        assertEquals("text/html", inputStream.getElementContentType());
        assertTrue(inputStream.isBoundaryEncountered());
        assertTrue(inputStream.isFinalBoundaryEncountered());
        assertTrue(inputStream.isEndOfStream());
    }

    public void testThreeMixedElementData() throws Exception
    {
        MultipartBoundaryInputStream inputStream = new MultipartBoundaryInputStream();
        inputStream.setBoundary(BOUNDARY_THREE_TEXT_ELEMENT);
        inputStream.setInputStream(this.threeMixedElementDataStream);

        //get first element
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_THREE_MIXED_ELEMENT_1, inputStream.getElementName());
        assertTrue(inputStream.isElementFile());
        assertEquals("text/html", inputStream.getElementContentType());
        assertTrue(!inputStream.isBoundaryEncountered());
        //read the element
        String resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        String desiredData = streamToString(this.testFile5400bDataStream);
        assertEquals(desiredData, resultData);

        //get second element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_THREE_MIXED_ELEMENT_2, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals(DESIRED_TEST_DATA_1, resultData);

        //get third element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_THREE_MIXED_ELEMENT_3, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals(DESIRED_TEST_DATA_2, resultData);
        //make sure the stream knows it's done
        assertTrue(inputStream.isFinalBoundaryEncountered());
        assertTrue(inputStream.isEndOfStream());
    }

    /**
     * Tests a file filled with many, many newlines. A veritable plethora of newlines.
     */
    public void testManyNewlines() throws Exception
    {
        MultipartBoundaryInputStream inputStream = new MultipartBoundaryInputStream();
        inputStream.setBoundary(BOUNDARY_THREE_TEXT_ELEMENT);
        inputStream.setInputStream(this.newlinesDataStream);

        //get first element
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_THREE_MIXED_ELEMENT_1, inputStream.getElementName());
        assertTrue(inputStream.isElementFile());
        assertEquals("text/html", inputStream.getElementContentType());
        //read the element
        String resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        String desiredData = streamToString(this.testFileNewlines);
        assertEquals(desiredData, resultData);

        //get second element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_THREE_MIXED_ELEMENT_2, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals(DESIRED_TEST_DATA_1, resultData);

        //get third element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_THREE_MIXED_ELEMENT_3, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals(DESIRED_TEST_DATA_2, resultData);
        //make sure the stream knows it's done
        assertTrue(inputStream.isFinalBoundaryEncountered());
        assertTrue(inputStream.isEndOfStream());
    }

    /**
     * This file caused a problem in the past. This test is here to make sure it doesn't happen again.
     */
    public void testProblemFile1() throws Exception
    {
        MultipartBoundaryInputStream inputStream = new MultipartBoundaryInputStream();
        inputStream.setBoundary(BOUNDARY_PROBLEM1);
        inputStream.setInputStream(this.testProblem1);

        //get the first element
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_1, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        String resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("test", resultData);

        //get second element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_2, inputStream.getElementName());
        assertTrue(inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        String desiredData = streamToString(this.testFileProblem1);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals(desiredData, resultData);

        //get third element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_3, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("on", resultData);

        //get fourth element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_4, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("c:\\multipart-test.txt", resultData);

        //get last element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_5, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("Submit", resultData);
        assertTrue(inputStream.isFinalBoundaryEncountered());
        assertTrue(inputStream.isEndOfStream());
    }

    public void testProblemFile2() throws Exception
    {
        MultipartBoundaryInputStream inputStream = new MultipartBoundaryInputStream();
        inputStream.setBoundary(BOUNDARY_PROBLEM2);
        inputStream.setInputStream(this.testProblem2);

        //get the first element
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_1, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        String resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("test", resultData);

        //get second element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_2, inputStream.getElementName());
        assertEquals("hist202.doc", inputStream.getElementFileName());
        assertTrue(inputStream.isElementFile());
        assertEquals("application/msword", inputStream.getElementContentType());
        //read the element
        assertEquals(this.testFileProblem2, inputStream);
        assertTrue(inputStream.isBoundaryEncountered());

        //get third element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_3, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("on", resultData);

        //get fourth element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_4, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("c:\\multipart-test.txt", resultData);

        //get last element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_5, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("Submit", resultData);
        assertTrue(inputStream.isFinalBoundaryEncountered());
        assertTrue(inputStream.isEndOfStream());
    }

    public void testProblemFile3() throws Exception
    {
        MultipartBoundaryInputStream inputStream = new MultipartBoundaryInputStream();
        inputStream.setBoundary(BOUNDARY_PROBLEM3);
        inputStream.setInputStream(this.testProblem3);

        //get the first element
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_1, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        String resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("test", resultData);

        //get second element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_2, inputStream.getElementName());
        assertEquals("testmp3.mp3", inputStream.getElementFileName());
        assertTrue(inputStream.isElementFile());
        assertEquals("audio/mpeg", inputStream.getElementContentType());
        //read the element
        assertEquals(this.testFileProblem3, inputStream);
        assertTrue(inputStream.isBoundaryEncountered());

        //get third element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_3, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("on", resultData);

        //get fourth element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_4, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("c:\\multipart-test7.mp3", resultData);

        //get last element
        inputStream.resetForNextBoundary();
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_5, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("Submit", resultData);
        assertTrue(inputStream.isFinalBoundaryEncountered());
        assertTrue(inputStream.isEndOfStream());
    }

    public void testMaxLengthExceeded() throws Exception
    {
        MultipartBoundaryInputStream inputStream = new MultipartBoundaryInputStream();
        inputStream.setMaxLength(1500);
        inputStream.setBoundary(BOUNDARY_PROBLEM3);
        inputStream.setInputStream(this.testProblem3);

        //get the first element
        assertEquals(DISPOSITION_FORM_DATA, inputStream.getElementContentDisposition());
        assertEquals(NAME_PROBLEM1_1, inputStream.getElementName());
        assertTrue(!inputStream.isElementFile());
        assertEquals(CONTENT_TYPE_TEXT_PLAIN, inputStream.getElementContentType());
        //read the element
        String resultData = streamToString(inputStream);
        assertTrue(inputStream.isBoundaryEncountered());
        assertEquals("test", resultData);

        //get second element
        inputStream.resetForNextBoundary();
        byte[] result = toByteArray(inputStream);
        assertTrue(inputStream.isMaxLengthMet());
        assertTrue(inputStream.getBytesRead() == 1500);
        assertTrue(result.length < 1500);
    }

    public static void assertEquals(InputStream desired, InputStream actual) throws Exception
    {
        byte[] desiredBytes = toByteArray(desired);
        byte[] actualBytes = toByteArray(actual);
        if (!Arrays.equals(desiredBytes,  actualBytes))
        {
            fail("Actual bytes don't match the desired bytes");
        }
    }

    public static byte[] toByteArray(InputStream stream) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read = 0;
        while ((read = stream.read(buffer, 0, buffer.length)) != -1)
        {
            baos.write(buffer, 0, read);
        }
        return baos.toByteArray();
    }

    private final String streamToString(InputStream inputStream) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read = 0;
        int length = 0;
        while ((read = inputStream.read(buffer, 0, buffer.length)) != -1)
        {
            baos.write(buffer, 0, read);
            length += read;
        }
        return baos.toString();
    }

}
