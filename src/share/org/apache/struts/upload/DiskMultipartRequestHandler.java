package org.apache.struts.upload;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ApplicationConfig;

/**
 * This is a MultipartRequestHandler that writes file data directly to
 * to temporary files on disk.
 *
 * @author Mike Schachter
 */
public class DiskMultipartRequestHandler implements MultipartRequestHandler {

    /**
     * Commons Logging instance.
    */
    private Log log = LogSource.getInstance(this.getClass().getName());
    
    /**
     * The ActionServlet instance used for this class
     */
    protected ActionServlet servlet;

    /**
     * The ActionMapping instance used for this class
     */
    protected ActionMapping mapping;

    /**
     * A Hashtable representing the form files uploaded
     */
    protected Hashtable fileElements;

    /**
     * A Hashtable representing the form text input names and values
     */
    protected Hashtable textElements;

    /**
     * A Hashtable representing all elemnents
     */
    protected Hashtable allElements;
    
    /**
     * The temporary directory
     */
    protected String tempDir;
    
    
    /**
     * This method populates the internal hashtables with multipart request data.
     * If the request argument is an instance of MultipartRequestWrapper,
     * the request wrapper will be populated as well.
     */
    public void handleRequest(HttpServletRequest request) throws ServletException {
        
        ApplicationConfig appConfig = (ApplicationConfig)
            request.getAttribute(Action.APPLICATION_KEY);
        retrieveTempDir(appConfig);
        
        MultipartIterator iterator =
            new MultipartIterator(request,
                                  appConfig.getControllerConfig().getBufferSize(),
                                  getMaxSize(appConfig.getControllerConfig().getMaxFileSize()),
                                  tempDir);
        MultipartElement element;

        textElements = new Hashtable();
        fileElements = new Hashtable();
        allElements = new Hashtable();

        try {
            while ((element = iterator.getNextElement()) != null) {
                if (!element.isFile()) {
                    
                    if (request instanceof MultipartRequestWrapper) {
                        ((MultipartRequestWrapper) request).setParameter(element.getName(),
                                                                         element.getValue());
                    }
                    String[] textValues = (String[]) textElements.get(element.getName());
                    
                    if (textValues != null) {
                        String[] textValues2 = new String[textValues.length + 1];
                        System.arraycopy(textValues, 0, textValues2, 0, textValues.length);
                        textValues2[textValues.length] = element.getValue();
                        textValues = textValues2;
                    }
                    else {
                        textValues = new String[1];
                        textValues[0] = element.getValue();
                    }
                    textElements.put(element.getName(), textValues);
                    allElements.put(element.getName(), textValues);
                }
                else {
                    
                     File tempFile = element.getFile();
                     if (tempFile.exists()) {
                         DiskFile theFile = new DiskFile(tempFile.getAbsolutePath());
                         theFile.setContentType(element.getContentType());
                         theFile.setFileName(element.getFileName());
                         theFile.setFileSize((int) tempFile.length());
                         fileElements.put(element.getName(), theFile);
                         allElements.put(element.getName(), theFile);
                     }             
                }
            }
        }
        catch (UnsupportedEncodingException uee) {
            throw new ServletException("Encoding \"ISO-8859-1\" not supported");
        }

    }

    public Hashtable getAllElements() {
        return allElements;
    }

    public Hashtable getTextElements() {
        return textElements;
    }

    public Hashtable getFileElements() {
        return fileElements;
    }

    /**
     * Delete all the files uploaded
     */
    public void rollback() {
        Enumeration names = fileElements.keys();

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            DiskFile theFile = (DiskFile) fileElements.get(name);
            theFile.destroy();
        }
    }

    /**
     * Calls on {@link #rollback() rollback()} to delete
     * temporary files
     */
    public void finish() {
        rollback();
    }

    public void setServlet(ActionServlet servlet) {
        this.servlet = servlet;
    }

    public void setMapping(ActionMapping mapping) {
        this.mapping = mapping;
    }

    public ActionServlet getServlet() {
        return servlet;
    }

    public ActionMapping getMapping() {
        return mapping;
    }

    /**
     * Gets the maximum post data size in bytes from the string
     * representation in the configuration file.
     */
    protected long getMaxSize(String stringSize) throws ServletException{
        long size = -1;
        int multiplier = 1;
        
        if (stringSize.endsWith("K")) {
            multiplier = 1024;
            stringSize = stringSize.substring(0, stringSize.length()-1);
        }
        if (stringSize.endsWith("M")) {
            multiplier = 1024*1024;
            stringSize = stringSize.substring(0, stringSize.length()-1);
        }
        else if (stringSize.endsWith("G")) {
            multiplier = 1024*1024*1024;
            stringSize = stringSize.substring(0, stringSize.length()-1);
        }
        
        try {
            size = Long.parseLong(stringSize);
        }
        catch (NumberFormatException nfe) {
            throw new ServletException("Invalid format for maximum file size");
        }
                
        return (size * multiplier);
    }       
    
    /**
     * Retrieves the temporary directory from either ActionServlet, a context
     * property, or a system property, in that order
     */
    protected void retrieveTempDir(ApplicationConfig appConfig) { 
        
        //attempt to retrieve the servlet container's temporary directory
        ServletContext context =
            appConfig.getServlet().getServletContext();
       
        try {
            tempDir =
                (String) context.getAttribute("javax.servlet.context.tempdir");
        }
        catch (ClassCastException cce) {
            tempDir = ((File) context.getAttribute("javax.servlet.context.tempdir")).getAbsolutePath();
        }           
        
        if (tempDir == null) {            
            //attempt to retrieve the temporary directory from the controller
            tempDir = appConfig.getControllerConfig().getTempDir();

            if (tempDir == null) {
                //default to system-wide tempdir
                tempDir = System.getProperty("java.io.tmpdir");

                if (appConfig.getServlet().getDebug() > 1) {
                    log.debug("DiskMultipartRequestHandler.handleRequest(): " +
                    "defaulting to java.io.tmpdir directory \"" +
                    tempDir);
                }
            }
        }
    }
}
