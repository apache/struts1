/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/upload/FormFile.java,v 1.3 2002/07/06 04:44:07 martinc Exp $
 * $Revision: 1.3 $
 * $Date: 2002/07/06 04:44:07 $
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
 * This interface is used to define a file uploaded by a client.
 */
public interface FormFile
{
    /**
     * Get the content type for this file.
     * @return A String representing content type
     */
    public String getContentType();
    
    /**
     * Set the content type for this file
     * @param contentType The content type
     */
    public void setContentType(String contentType);
    
    /**
     * Get the size of this file
     * @return An int representing the size of the file in bytes
     */
    public int getFileSize();
    
    /**
     * Set the file size
     * @param filesize An int reprsenting the size of the file in bytes
     */
    public void setFileSize(int filesize);    
    
    /**
     * Get the file name of this file.
     * @return A String reprsenting a file name
     */
    public String getFileName();
    
    /**
     * Set the filename of this file
     * @param fileName The name of the file
     */
    public void setFileName(String fileName);
    
    /**
     * Get the data in byte array for for this file.  Note that this can be
     * a very hazardous method, files can be large enough to cause 
     * OutOfMemoryErrors.  Short of being deprecated, it's strongly recommended
     * that you use {@link #getInputStream() getInputStream} to get the file
     * data.
     *
     * @exception FileNotFoundException If some sort of file representation
     *                                  cannot be found for the FormFile
     * @exception IOException If there is some sort of IOException
     * @return An array of bytes representing the data contained
     *         in the form file
     */
    public byte[] getFileData() throws FileNotFoundException, IOException;
    
    /**
     * Get an InputStream that represents this file.  This is the preferred
     * method of getting file data.
     * @exception FileNotFoundException If some sort of file representation
     *                                  cannot be found for the FormFile
     * @exception IOException If there is some sort of IOException
     */
    public InputStream getInputStream() throws FileNotFoundException, IOException;
    
    /**
     * Destroy all content for this form file.
     * Implementations should remove any temporary
     * files or any temporary file data stored somewhere
     */
    public void destroy();    
}