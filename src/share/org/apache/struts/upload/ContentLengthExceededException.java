package org.apache.struts.upload;

import java.io.IOException;

/**
 * This exception is thrown when multipart post data exceeds the value
 * given by the Content-Length header
 */
public class ContentLengthExceededException extends IOException {
    
    protected String message;
    
    public ContentLengthExceededException() {
        message = "The Content-Length has been exceeded for this request";
    }
    
    public ContentLengthExceededException(long contentLength) {
    
        message = "The Content-Length of " + contentLength + " bytes has been " +
            "exceeded";
    }
    
    public String getMessage() {
        return message;
    }
}