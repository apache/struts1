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