/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/upload/DiskMultipartRequestHandler.java,v 1.22 2003/04/21 02:28:20 rleland Exp $
 * $Revision: 1.22 $
 * $Date: 2003/04/21 02:28:20 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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
import java.io.IOException;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.Globals;

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
    protected static Log log =
            LogFactory.getLog(DiskMultipartRequestHandler.class);

    /**
     * The ActionServlet instance used for this class.
     */
    protected ActionServlet servlet;

    /**
     * The ActionMapping instance used for this class.
     */
    protected ActionMapping mapping;

    /**
     * A Hashtable representing the form files uploaded.
     */
    protected Hashtable fileElements;

    /**
     * A Hashtable representing the form text input names and values.
     */
    protected Hashtable textElements;

    /**
     * A Hashtable representing all elemnents.
     */
    protected Hashtable allElements;

    /**
     * The temporary directory.
     */
    protected String tempDir;


    /**
     * This method populates the internal hashtables with multipart request data.
     * If the request argument is an instance of MultipartRequestWrapper,
     * the request wrapper will be populated as well.
     */
    public void handleRequest(HttpServletRequest request) throws ServletException {
        ModuleConfig moduleConfig = (ModuleConfig) request.getAttribute(Globals.MODULE_KEY);
        retrieveTempDir(moduleConfig);
        try {
            MultipartIterator iterator = new MultipartIterator(request, moduleConfig.getControllerConfig().getBufferSize(),
                                                               getMaxSize(moduleConfig.getControllerConfig().getMaxFileSize()),
                                                               tempDir);
            MultipartElement element;

            textElements = new Hashtable();
            fileElements = new Hashtable();
            allElements = new Hashtable();

            while ((element = iterator.getNextElement()) != null) {
                if (!element.isFile()) {
                    createTextElement(request, element);
                } else {
                    createDiskFile(element);
                }
            }
            //take care of maximum length being exceeded
            if (iterator.isMaxLengthExceeded()) {
                request.setAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED, Boolean.TRUE);
            }
        } catch(IOException ioe) {
            throw new ServletException(ioe);
        }
    }

    protected void createTextElement(HttpServletRequest request, MultipartElement element) {
        if (request instanceof MultipartRequestWrapper) {
            ((MultipartRequestWrapper) request).setParameter(element.getName(), element.getValue());
        }
        String[] textValues = (String[]) textElements.get(element.getName());

        if (textValues != null) {
            String[] textValues2 = new String[textValues.length + 1];
            System.arraycopy(textValues, 0, textValues2, 0, textValues.length);
            textValues2[textValues.length] = element.getValue();
            textValues = textValues2;
        } else {
            textValues = new String[1];
            textValues[0] = element.getValue();
        }
        textElements.put(element.getName(), textValues);
        allElements.put(element.getName(), textValues);
    }

    protected void createDiskFile(MultipartElement element) {
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
     * Delete all the files uploaded.
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
     * temporary files.
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
    protected long getMaxSize(String stringSize) throws ServletException {
        long size = -1;
        int multiplier = 1;

        if (stringSize.endsWith("K")) {
            multiplier = 1024;
            stringSize = stringSize.substring(0, stringSize.length() - 1);
        }
        if (stringSize.endsWith("M")) {
            multiplier = 1024 * 1024;
            stringSize = stringSize.substring(0, stringSize.length() - 1);
        } else if (stringSize.endsWith("G")) {
            multiplier = 1024 * 1024 * 1024;
            stringSize = stringSize.substring(0, stringSize.length() - 1);
        }

        try {
            size = Long.parseLong(stringSize);
        } catch(NumberFormatException nfe) {
            throw new ServletException("Invalid format for maximum file size");
        }

        return (size * multiplier);
    }

    /**
     * Retrieves the temporary directory from either ActionServlet, a context
     * property, or a system property, in that order.
     */
    protected void retrieveTempDir(ModuleConfig moduleConfig) {

        //attempt to retrieve the servlet container's temporary directory
        ActionServlet servlet = getServlet();
        if (servlet != null) {
            //attempt to retrieve the servlet container's temporary directory
            ServletContext context = servlet.getServletContext();

            try {
                tempDir =
                        (String) context.getAttribute("javax.servlet.context.tempdir");
            } catch(ClassCastException cce) {
                tempDir = ((File) context.getAttribute("javax.servlet.context.tempdir")).getAbsolutePath();
            }
        }

        if (tempDir == null) {
            //attempt to retrieve the temporary directory from the controller
            tempDir = moduleConfig.getControllerConfig().getTempDir();

            if (tempDir == null) {
                //default to system-wide tempdir
                tempDir = System.getProperty("java.io.tmpdir");

                if (getServlet().getDebug() > 1) {
                    log.debug("DiskMultipartRequestHandler.handleRequest(): " +
                              "defaulting to java.io.tmpdir directory \"" +
                              tempDir);
                }
            }
        }
    }
}
