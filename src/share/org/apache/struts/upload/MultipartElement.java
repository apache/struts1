package org.apache.struts.upload;

/**
 * This class represents an element in a multipart request.
 * It has a few methods for determining
 * whether or not the element is a String or a file,
 * and methods to retrieve the data of the aforementioned
 * element.  Text input elements have a <code>null</code> content type,
 * files have a non-null content type.
 *
 * @author Mike Schachter
 */
public class MultipartElement {
    
    /**
     * The content type of this element
     */
    protected String contentType;
    
    
    /**
     * The element data
     */
    protected byte[] data;
    
    /**
     * The element name
     */
    protected String name;
    
    /**
     * The element's filename, null for text elements
     */
    protected String fileName;
    
    
    public MultipartElement(String name, String fileName, String contentType, byte[] data) {
        this.name = name;
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }
    
    /**
     * Retrieve the content type
     */
    public String getContentType() {
        return contentType;
    }
    
    
    /**
     * Retrieve the data
     */
    public byte[] getData() {
        return data;
    }
    
    /**
     * Retrieve the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Retrieve the filename, can return <code>null</code>
     * for text elements
     */
    public String getFileName() {
        return fileName;
    }
    
    /** 
     * Set the file name for this element
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * Set the name for this element
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Set the content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    
    /**
     * Set the data
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}

