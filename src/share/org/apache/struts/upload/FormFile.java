/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/upload/FormFile.java,v 1.4 2002/08/09 04:02:43 martinc Exp $
 * $Revision: 1.4 $
 * $Date: 2002/08/09 04:02:43 $
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


import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;


/**
 * This interface represents a file that has been uploaded by a client. It is
 * the only interface or class in upload package which is typically referenced
 * directly by a Struts application.
 */
public interface FormFile
{
    /**
     * Returns the content type for this file.
     *
     * @return A String representing content type.
     */
    public String getContentType();
    
    /**
     * Sets the content type for this file.
     *
     * @param contentType The content type for the file.
     */
    public void setContentType(String contentType);
    
    /**
     * Returns the size of this file.
     *
     * @return The size of the file, in bytes.
     */
    public int getFileSize();
    
    /**
     * Sets the file size.
     *
     * @param fileSize The size of the file, in bytes,
     */
    public void setFileSize(int fileSize);    
    
    /**
     * Returns the file name of this file. This is the base name of the file,
     * as supplied by the user when the file was uploaded.
     *
     * @return The base file name.
     */
    public String getFileName();
    
    /**
     * Sets the file name of this file.
     *
     * @param fileName The base file name.
     */
    public void setFileName(String fileName);
    
    /**
     * Returns the data for the entire file as byte array. Care is needed when
     * using this method, since a large upload could easily exhaust available
     * memory. The preferred method for accessing the file data is
     * {@link #getInputStream() getInputStream}.
     *
     * @return The file data as a byte array.
     *
     * @exception FileNotFoundException if the uploaded file is not found.
     * @exception IOException           if an error occurred while reading the
     *                                  file.
     */
    public byte[] getFileData()
            throws FileNotFoundException, IOException;
    
    /**
     * Returns an input stream for this file. The caller must close the
     * stream when it is no longer needed.
     *
     * @exception FileNotFoundException if the uploaded file is not found.
     * @exception IOException           if an error occurred while reading the
     *                                  file.
     */
    public InputStream getInputStream()
            throws FileNotFoundException, IOException;
    
    /**
     * Destroys all content for the uploaded file, including any underlying
     * data files.
     */
    public void destroy();    
}
