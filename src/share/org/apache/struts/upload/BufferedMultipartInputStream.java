/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/upload/BufferedMultipartInputStream.java,v 1.8 2002/11/07 05:58:03 rleland Exp $
 * $Revision: 1.8 $
 * $Date: 2002/11/07 05:58:03 $
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

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;

/**
 * This class implements buffering for an InputStream as well as a
 * readLine method.  The purpose of this is to provide a reliable
 * readLine() method.
 */
public class BufferedMultipartInputStream extends InputStream {
   
    /**
     * The underlying InputStream used by this class
     */
    protected InputStream inputStream;
    
    /**
     * The byte array used to hold buffered data
     */
    protected byte[] buffer;
    
    /**
     * The current offset we're at in the buffer's byte array
     */
    protected int bufferOffset = 0;
    
    /**
     * The size of the byte array buffer
     */
    protected int bufferSize = 8192;
    
    /**
     * The number of bytes read from the underlying InputStream that are
     * in the buffer
     */
    protected int bufferLength = 0;
    
    /**
     * The total number of bytes read so far
     */
    protected int totalLength = 0;
    
    /**
     * The content length of the multipart data
     */
    protected long contentLength;
    
    /**
     * The maximum allowed size for the multipart data, or -1 for an unlimited
     * maximum file length
     */
    protected long maxSize = -1;
    
    /**
     * Whether or not bytes up to the Content-Length have been read
     */
    protected boolean contentLengthMet = false;
    
    /**
     * Whether or not bytes up to the maximum length have been read
     */
    protected boolean maxLengthMet = false;
    
    
    /**
     * Public constructor for this class, just wraps the InputStream
     * given
     * @param inputStream The underlying stream to read from
     * @param bufferSize The size in bytes of the internal buffer
     * @param contentLength The content length for this request
     * @param maxSize The maximum size in bytes that this multipart
     *                request can be, or -1 for an unlimited length
     */
    public BufferedMultipartInputStream(InputStream inputStream,
                                        int bufferSize,
                                        long contentLength,
                                        long maxSize) throws IOException {
        this.inputStream = inputStream;
        this.bufferSize = bufferSize;
        this.contentLength = contentLength;
        this.maxSize = maxSize;
        
        if ((maxSize != -1) && (maxSize < contentLength)) {
            throw new MaxLengthExceededException(maxSize);
        }
        buffer = new byte[bufferSize];
        fill();
    }
    
    /**
     * This method returns the number of available bytes left to read
     * in the buffer before it has to be refilled
     */
    public int available() {
        return bufferLength - bufferOffset;
    }
    
    /**
     * This method attempts to close the underlying InputStream
     */
    public void close() throws IOException {
        inputStream.close();
    }
    
    /**
     * This method calls on the mark() method of the underlying InputStream
     */
    public void mark(int position) {
        inputStream.mark(position);  
    } 
    
    /**
     * This method calls on the markSupported() method of the underlying InputStream
     * @return Whether or not the underlying InputStream supports marking
     */
    public boolean markSupported() {
        return inputStream.markSupported();        
    }
    
    /**
     * @return true if the maximum length has been reached, false otherwise
     */
    public boolean maxLengthMet() {
        return maxLengthMet;
    }
    
    /**
     * @return true if the content length has been reached, false otherwise
     */
    public boolean contentLengthMet() {
        return contentLengthMet;
    }
    
    /**
     * This method returns the next byte in the buffer, and refills it if necessary.
     * @return The next byte read in the buffer, or -1 if the end of the stream has
     *         been reached
     */
    public int read() throws IOException {
        
        if (maxLengthMet) {
            throw new MaxLengthExceededException(maxSize);
        }
        if (contentLengthMet) {
            throw new ContentLengthExceededException(contentLength);
        }
        if (buffer == null) {
            return -1;
        }
        
        if (bufferOffset < bufferLength) {            
            return (int)(char) buffer[bufferOffset++];            
        }
        fill();
        return read();        
    }
    
    /**
     * This method populates the byte array <code>b</code> with data up to
     * <code>b.length</code> bytes
     */
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);        
    }
    
    /**
     * This method populates the byte array <code>b</code> with data up to 
     * <code>length</code> starting at b[offset]
     */
    public int read(byte[] b, int offset, int length) throws IOException {
        
        int count = 0;
        int read = read();
        if (read == -1) {
            return -1;
        }
        
        while ((read != -1) && (count < length)) {
            b[offset] = (byte) read;
            read = read();
            count++;
            offset++;
        }
        return count;
    }
    
    /**
     * This method reads into the byte array <code>b</code> until
     * a newline ('\n') character is encountered or the number of bytes
     * specified by <code>length</code> have been read
     */
    public int readLine(byte[] b, int offset, int length) throws IOException {
        
        int count = 0;
        int read = read();
        if (read == -1) {
            return -1;
        }
        
        while ((read != -1) && (count < length)) {
            if (read == '\n')
                break;
            b[offset] = (byte) read;
            count++;
            offset++;
            read = read();
        }
        return count;
    }

    /**
     * This method reads a line, regardless of length.
     * @return A byte array representing the line.
     */
    public byte[] readLine() throws IOException {

        int read = read();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

         // return null if there are no more bytes to read
        if( -1 == read )
            return null;

        while ((read != -1) && (read != '\n')) {
            baos.write(read);
            read = read();
        }

        return baos.toByteArray();
    }

    /**
     * This method makes a call to the reset() method of the underlying
     * InputStream
     */
    public void reset() throws IOException {
        inputStream.reset();        
    }

    /**
     * Fills the buffer with data from the underlying inputStream.  If it can't
     * fill the entire buffer in one read, it will read as many times as necessary
     * to fill the buffer
     */
    protected void fill() throws IOException {

        if ((bufferOffset > -1) && (bufferLength > -1)) {
            int length = Math.min(bufferSize, (((int) contentLength+1) - totalLength));
            if (length == 0) {
                contentLengthMet = true;
            }
            if ((maxSize > -1) && (length > 0)){
                length = Math.min(length, ((int) maxSize - totalLength));
                if (length == 0) {
                    maxLengthMet = true;
                }
            }

            int bytesRead = -1;
            if (length > 0) {
                bytesRead = inputStream.read(buffer, 0, length);
            }
            if (bytesRead == -1) {
                buffer = null;
                bufferOffset = -1;
                bufferLength = -1;
            }
            else {
                bufferLength = bytesRead;
                totalLength += bytesRead;
                bufferOffset = 0;            
            }
        }
    }
}