package org.apache.struts.upload;

import java.io.IOException;

/**
 * This exception is thrown when multipart post data exceeds the maximum
 * value set
 */
public class MaxLengthExceededException extends IOException {
    
    protected String message;
    
    public MaxLengthExceededException() {
        message = "The maximum length has been exceeded for this request";
    }
    
    public MaxLengthExceededException(long maxLength) {
    
        message = "The maximum length of " + maxLength + " bytes has been " +
            "exceeded";
    }
    
    public String getMessage() {
        return message;
    }
}
