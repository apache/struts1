/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/upload/MultipartElement.java,v 1.5 2002/07/06 04:44:07 martinc Exp $
 * $Revision: 1.5 $
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

import java.io.File;

/**
 * This class represents an element in a multipart request.
 * It has a few methods for determining * whether or not the element is a
 * String or a file, and methods to retrieve the data of the aforementioned
 * element.  Text input elements have a <code>null</code> content type,
 * files have a non-null content type.
 *
 * @author Mike Schachter
 */
public class MultipartElement
{
    /**
     * The content type of this element.
     */
    protected String contentType;

    /**
     * The element data.
     * @deprecated This should never be used.
     */
    protected byte[] data;

    /**
     * The element's data represented in a (possibly temporary) file.
     */
    protected File file;

    /**
     * The element name.
     */
    protected String name;

    /**
     * The element's filename, null for text elements.
     */
    protected String fileName;


    /**
     * The element's text value, null for file elements
     */
    protected String value;


    /**
     * Whether or not this element is a file.
     */
    protected boolean isFile = false;


    /**
     * @deprecated Use the constructor that takes an File as an argument
     *             as opposed to a byte array argument, which can cause
     *             memory problems.
     */
    public MultipartElement(String name, String fileName,
                            String contentType, byte[] data)
    {

        this.name = name;
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;

        if (fileName != null)
        {
              isFile = true;
        }

    }

    /**
     * Constructor for a file element.
     * @param name The form name of the element
     * @param fileName The file name of the element if this element is a file
     * @param contentType The content type of the element if a file
     * @param file The (possibly temporary) file representing this element if
     *             it's a file
     */
    public MultipartElement(String name, String fileName,
                            String contentType, File file)
    {
        this.name = name;
        this.fileName = fileName;
        this.contentType = contentType;
        this.file = file;
        this.isFile = true;
    }

    /**
     * Constructor for a text element.
     * @param name The name of the element
     * @param value The value of the element
     */
    public MultipartElement(String name, String value)
    {
         this.name = name;
         this.value = value;
         this.isFile = false;
    }

    /**
      * Retrieve the content type.
      */
    public String getContentType()
    {
         return contentType;
    }


    /**
     * Retrieve the data.
     * @deprecated Use the getFile method to get a File representing the
     *             data for this element
     */
    public byte[] getData()
    {

        return data;
    }


    /**
     * Get the File that holds the data for this element.
     */
    public File getFile()
    {
       return file;
    }


    /**
     * Retrieve the name.
     */
    public String getName()
    {
       return name;
    }

    /**
     * Retrieve the filename, can return <code>null</code>
     * for text elements.
     */
    public String getFileName()
    {
      return fileName;
    }


    /**
     * Returns the value of this multipart element.
     * @return A String if the element is a text element, <code>null</code>
     *         otherwise
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Set the file that represents this element.
     */
    public void setFile(File file)
    {
        this.file = file;
    }


    /**
     * Set the file name for this element.
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }


    /**
     * Set the name for this element.
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Set the content type.
     */
    public void setContentType(String contentType)
    {
         this.contentType = contentType;
    }


    /**
     * Is this element a file.
     */
    public boolean isFile()
    {
        if (file == null)
        {
            return false;
        }
        return true;
    }


    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Set the data.
     * @deprecated Use the setFile method to set the file
     *             that represents the data of this element
     */
    public void setData(byte[] data)
    {
        this.data = data;
    }
}
