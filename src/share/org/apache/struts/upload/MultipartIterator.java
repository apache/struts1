/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/upload/MultipartIterator.java,v 1.23 2003/05/13 16:09:22 turner Exp $
 * $Revision: 1.23 $
 * $Date: 2003/05/13 16:09:22 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.struts.upload;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * The MultipartIterator class is responsible for reading the
 * input data of a multipart request and splitting it up into
 * input elements, wrapped inside of a
 * {@link org.apache.struts.upload.MultipartElement MultipartElement}
 * for easy definition.  To use this class, create a new instance
 * of MultipartIterator passing it a HttpServletRequest in the
 * constructor.  Then use the {@link #getNextElement() getNextElement}
 * method until it returns null, then you're finished.  Example: <br>
 * <pre>
 *      MultipartIterator iterator = new MultipartIterator(request);
 *      MultipartElement element;
 *
 *      while ((element = iterator.getNextElement()) != null) {
 *           //do something with element
 *      }
 * </pre>
 *
 * @see org.apache.struts.upload.MultipartElement
 * @author Mike Schachter
 */
public class MultipartIterator
{

    /**
     * The default encoding of a text element if none is specified.
     */
    private static final String DEFAULT_ENCODING = "iso-8859-1";

    /**
     * The size in bytes to copy of text data at a time.
     */
    private static final int TEXT_BUFFER_SIZE = 1000;

    /**
     * The name of the Content-Type header.
     */
    public static String HEADER_CONTENT_TYPE = "Content-Type";

    /**
     * The name of the Content-Disposition header.
     */
    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";

    /**
     * The exception message for when the boundary of a multipart request can't be determined.
     */
    public static final String MESSAGE_CANNOT_RETRIEVE_BOUNDARY =
                                                    "MultipartIterator: cannot retrieve boundary for multipart request";

    private static final String PARAMETER_BOUNDARY = "boundary=";

    private static final String FILE_PREFIX = "strts";

    /**
     * The request instance for this class
     */
    protected HttpServletRequest request;

    /**
     * The InputStream to use to read the multipart data.
     */
    protected MultipartBoundaryInputStream inputStream;

    /**
     * The boundary for this multipart request
     */
    protected String boundary;

    /**
     * The maximum file size in bytes allowed. Ignored if -1
     */
    protected long maxSize = -1;

    /**
     * The content length of this request
     */
    protected int contentLength;

    /**
     * The size in bytes written to the filesystem at a time [20K]
     */
    protected int diskBufferSize = 2 * 10240;

    /**
     * The amount of data read from a request at a time.
     * This also represents the maximum size in bytes of
     * a line read from the request [4KB]
     */
    protected int bufferSize = 4096;

    /**
     * The temporary directory to store files
     */
    protected String tempDir;

    /**
     * The content-type.
     */
    protected String contentType;

    /**
     * Whether the maximum length has been exceeded.
     */
    protected boolean maxLengthExceeded;

    /**
     * Constructs a MultipartIterator with a default buffer size and no file size
     * limit
     *
     * @param request The multipart request to iterate
     */
    public MultipartIterator(HttpServletRequest request) throws IOException
    {
        this(request, -1);
    }

    /**
     * Constructs a MultipartIterator with the specified buffer size and
     * no file size limit
     *
     * @param request The multipart request to iterate
     * @param bufferSize The size in bytes that should be read from the input
     *                   stream at a times
     */
    public MultipartIterator(HttpServletRequest request, int bufferSize) throws IOException
    {
        this (request, bufferSize, -1);
    }

    /**
     * Constructs a MultipartIterator with the specified buffer size and
     * the specified file size limit in bytes
     *
     * @param request The multipart request to iterate
     * @param bufferSize The size in bytes that should be read from the input
     *                   stream at a times
     * @param maxSize The maximum size in bytes allowed for a multipart element's data
     */
    public MultipartIterator(HttpServletRequest request, int bufferSize, long maxSize) throws IOException
    {
        this(request, bufferSize, maxSize, null);
    }

    public MultipartIterator(HttpServletRequest request, int bufferSize, long maxSize, String tempDir) throws IOException
    {
        this.request = request;
        this.maxSize = maxSize;
        if (bufferSize > -1)
        {
            this.bufferSize = bufferSize;
        }
        if (tempDir != null)
        {
            this.tempDir = tempDir;
        }
        else
        {
            //default to system-wide tempdir
            this.tempDir = System.getProperty("java.io.tmpdir");
        }
        this.maxLengthExceeded = false;
        this.inputStream = new MultipartBoundaryInputStream();
        parseRequest();
    }

    /**
     * Handles retrieving the boundary and setting the input stream
     */
    protected void parseRequest() throws IOException
    {
        //get the content-type header, which contains the boundary used for separating multipart elements
        getContentTypeOfRequest();
        //get the content-length header, used to prevent denial of service attacks and for detecting
        //whether a file size is over the limit before the client sends the file
        this.contentLength = this.request.getContentLength();
        //parse the boundary from the content-type header's value
        getBoundaryFromContentType();
        //don't let the stream read past the content length
        this.inputStream.setMaxLength(this.contentLength+1);
        //just stop now if the content length is bigger than the maximum allowed size
        if ((this.maxSize > -1) && (this.contentLength > this.maxSize))
        {
            this.maxLengthExceeded = true;
        }
        else
        {
            InputStream requestInputStream = this.request.getInputStream();
            //mark the input stream to allow multiple reads
            if (requestInputStream.markSupported())
            {
                requestInputStream.mark(contentLength+1);
            }
            this.inputStream.setBoundary(this.boundary);
            this.inputStream.setInputStream(requestInputStream);
        }
    }

    /**
     * Retrieves the next element in the iterator if one exists.
     *
     * @throws IOException if the post size exceeds the maximum file size
     *         passed in the 3 argument constructor or if the "ISO-8859-1" encoding isn't found
     * @return a {@link org.apache.struts.upload.MultipartElement MultipartElement}
     *         representing the next element in the request data
     *
     */
    public MultipartElement getNextElement() throws IOException
    {
        //the MultipartElement to return
        MultipartElement element = null;
        if (!isMaxLengthExceeded())
        {
            if (!this.inputStream.isFinalBoundaryEncountered())
            {
                if (this.inputStream.isElementFile())
                {
                    //attempt to create the multipart element from the collected data
                    element = createFileMultipartElement();
                }
                //process a text element
                else
                {
                    String encoding = getElementEncoding();
                    element = createTextMultipartElement(encoding);
                }
                this.inputStream.resetForNextBoundary();
            }
        }
        return element;
    }

    /**
     * Get the character encoding used for this current multipart element.
     */
    protected String getElementEncoding()
    {
        String encoding = this.inputStream.getElementCharset();
        if (encoding == null)
        {
            encoding = this.request.getCharacterEncoding();
            if (encoding == null)
            {
                encoding = DEFAULT_ENCODING;
            }
        }
        return encoding;
    }

    /**
     * Create a text element from the data in the body of the element.
     * @param encoding The character encoding of the string.
     */
    protected MultipartElement createTextMultipartElement(String encoding) throws IOException
    {
        MultipartElement element;

        int read = 0;
        byte[] buffer = new byte[TEXT_BUFFER_SIZE];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((read = this.inputStream.read(buffer, 0, TEXT_BUFFER_SIZE)) > 0)
        {
            baos.write(buffer, 0, read);
        }
        //create the element
        String value = baos.toString(encoding);
        element = new MultipartElement(this.inputStream.getElementName(), value);
        return element;
    }

    /**
     * Create a multipart element instance representing the file in the stream.
     */
    protected MultipartElement createFileMultipartElement() throws IOException
    {
        MultipartElement element;
        //create a local file on disk representing the element
        File elementFile = createLocalFile();
        element = new MultipartElement(this.inputStream.getElementName(), this.inputStream.getElementFileName(),
                                       this.inputStream.getElementContentType(), elementFile);
        return element;
    }

    /**
     * Set the maximum amount of bytes read from a line at one time
     *
     * @see javax.servlet.ServletInputStream#readLine(byte[], int, int)
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * Get the maximum amount of bytes read from a line at one time
     *
     * @see javax.servlet.ServletInputStream#readLine(byte[], int, int)
     */
    public int getBufferSize() {
        return bufferSize;
    }

    /**
     * Set the maximum post data size allowed for a multipart request
     * @param maxSize The maximum post data size in bytes, set to <code>-1</code>
     *                for no limit
     */
    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Get the maximum post data size allowed for a multipart request
     * @return The maximum post data size in bytes
     */
    public long getMaxSize()
    {
        return this.maxSize;
    }

    /**
     * Whether or not the maximum length has been exceeded by the client.
     */
    public boolean isMaxLengthExceeded()
    {
        return (this.maxLengthExceeded || this.inputStream.isMaxLengthMet());
    }


    /**
     * Parses a content-type String for the boundary.
     */
    private final void getBoundaryFromContentType() throws IOException
    {
        if (this.contentType.lastIndexOf(PARAMETER_BOUNDARY) != -1)
        {
            String _boundary = this.contentType.substring(this.contentType.lastIndexOf(PARAMETER_BOUNDARY) + 9);
            if (_boundary.endsWith("\n"))
            {
                //strip it off
                this.boundary = _boundary.substring(0, _boundary.length()-1);
            }
            this.boundary = _boundary;
        }
        else
        {
            this.boundary = null;
        }
        //throw an exception if we're unable to obtain a boundary at this point
        if ((this.boundary == null) || (this.boundary.length() < 1))
        {
            throw new IOException(MESSAGE_CANNOT_RETRIEVE_BOUNDARY);
        }
    }
    /**
     * Gets the value of the Content-Type header of the request.
     */
    private final void getContentTypeOfRequest()
    {
        this.contentType = request.getContentType();
        if (this.contentType == null)
        {
            this.contentType = this.request.getHeader(HEADER_CONTENT_TYPE);
        }
    }

    /**
     * Creates a file on disk from the current mulitpart element.
     */
    protected File createLocalFile() throws IOException
    {
        File tempFile = File.createTempFile(FILE_PREFIX, null, new File(this.tempDir));
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(tempFile), this.diskBufferSize);
        int read = 0;
        byte buffer[] = new byte[this.diskBufferSize];
        while ((read = this.inputStream.read(buffer, 0, this.diskBufferSize)) > 0)
        {
            fos.write(buffer, 0, read);
        }
        fos.flush();
        fos.close();
        return tempFile;
    }
}
