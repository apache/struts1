/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/upload/DiskFile.java,v 1.4 2002/12/08 07:12:16 rleland Exp $
 * $Revision: 1.4 $
 * $Date: 2002/12/08 07:12:16 $
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

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class DiskFile implements FormFile {
    
    /**
     * The filepath to the temporary file
     */
    protected String filePath;
    
    /**
     * The content type of the file
     */
    protected String contentType;
    
    /**
     * The size in bytes of the file
     */
    protected int fileSize;
    
    /**
     * The name of the file
     */
    protected String fileName;   
    
    public DiskFile(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Attempt to read the temporary file and get it's data in byte
     * array form.  Tries to read the entire file (using a byte array
     * the size of getFileSize()) at once, in one call to FileInputStream.read(byte[]).
     * For buffered reading, see {@link #getFileData(int) getFileData(int)}.
     * Note that this method can be dangerous, and that the size of a file
     * can cause an OutOfMemoryError quite easily.  You should use 
     * {@link #getInputStream() getInputStream} and do your own thing.
     *
     * @exception FileNotFoundException If the temp file no longer exists
     * @exception IOException if there is some sort of IO problem.
     * @see #getFileData(int)
     */    
    public byte[] getFileData() throws FileNotFoundException, IOException {
        
        byte[] bytes = new byte[getFileSize()];
        
        FileInputStream fis = new FileInputStream(filePath);
        fis.read(bytes);
        fis.close();
        return bytes;
    }
    
    /**
     * Attempts to read a file n bytes at a time, n being equal to "bufferSize".
     * Note that this method can be dangerous, and that the size of a file
     * can cause an OutOfMemoryError quite easily.  You should use 
     * {@link #getInputStream() getInputStream} and do your own thing.
     *
     * @param bufferSize The size in bytes that are read from the file at a time
     * @exception FileNotFoundException If the temp file no longer exists
     */    
    public byte[] getFileData(int bufferSize) throws FileNotFoundException, IOException {
        
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(filePath);
        
        int readLength  = 0;
        int totalLength = 0;
        int offset      = 0;
        
        byte[] bytes = new byte[bufferSize];
        
        while ((readLength = fis.read(bytes, offset, bufferSize)) != -1) {
            
            byteStream.write(bytes, offset, bufferSize);            
            totalLength += readLength;
            offset += readLength;
        }
        
        bytes = byteStream.toByteArray();
        
        fis.close();
        byteStream.close();
        
        return bytes;      
    }
    
    
    /**
     * Delete the temporary file.
     */
    public void destroy() {
        
        File tempFile = new File(filePath);
        
        if (tempFile.exists()) {
            tempFile.delete();
        }    
    }
    
    /**
     * Get the temporary file path for this form file
     * @return A filepath to the temporary file
     */
    public String getFilePath() {
        return filePath;
    }
    
    /**
     * Set the file name
     */
    public void setFileName(String filename) {
        this.fileName = filename;
    }
    
    /**
     * Set the content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    /**
     * Set the file size
     * @param fileSize The size of the file in bytes
     */
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    
    /**
     * Get the file name
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * Get the content type
     */
    public String getContentType() {
        return contentType;
    }
    
    /**
     * Get the file size
     * @return The size of this file in bytes
     */
    public int getFileSize() {
        return fileSize;
    }
    
    /**
     * Returns a FileInputStream to the file
     */
    public InputStream getInputStream() throws FileNotFoundException, IOException {
        return new FileInputStream(filePath);
    }
}
