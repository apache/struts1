/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/ControllerConfig.java,v 1.2 2002/01/13 00:25:36 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2002/01/13 00:25:36 $
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


package org.apache.struts.config;


import java.io.Serializable;


/**
 * <p>A JavaBean representing the configuration information of a
 * <code>&lt;controller&gt;</code> element in a Struts application
 * configuration file.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2002/01/13 00:25:36 $
 * @since Struts 1.1
 */

public class ControllerConfig implements Serializable {


    // ------------------------------------------------------------- Properties


    /**
     * The input buffer size for file uploads.
     */
    protected int bufferSize = 4096;

    public int getBufferSize() {
        return (this.bufferSize);
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }


    /**
     * The content type and character encoding to be set on each response.
     */
    protected String contentType = "text/html";

    public String getContentType() {
        return (this.contentType);
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    /**
     * The debugging detail level that determines logging verbosity.
     */
    protected int debug = 0;

    public int getDebug() {
        return (this.debug);
    }

    public void setDebug(int debug) {
        this.debug = debug;
    }


    /**
     * Should we store a Locale object in the user's session if needed?
     */
    protected boolean locale = false;

    public boolean getLocale() {
        return (this.locale);
    }

    public void setLocale(boolean locale) {
        this.locale = locale;
    }


    /**
     * The maximum file size to process for file uploads.
     */
    protected String maxFileSize = "250M";

    public String getMaxFileSize() {
        return (this.maxFileSize);
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }


    /**
     * The fully qualified Java class name of the MultipartRequestHandler
     * class to be used.
     */
    protected String multipartClass =
        "org.apache.struts.upload.DiskMultipartRequestHandler";

    public String getMultipartClass() {
        return (this.multipartClass);
    }

    public void setMultipartClass(String multipartClass) {
        this.multipartClass = multipartClass;
    }


    /**
     * Should we set no-cache HTTP headers on each response?
     */
    protected boolean nocache = false;

    public boolean getNocache() {
        return (this.nocache);
    }

    public void setNocache(boolean nocache) {
        this.nocache = nocache;
    }


    /**
     * The temporary working directory to use for file uploads.
     */
    protected String tempDir = null;

    public String getTempDir() {
        return (this.tempDir);
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("ControllerConfig[");
        sb.append("bufferSize=");
        sb.append(this.bufferSize);
        if (this.contentType != null) {
            sb.append(",contentType=");
            sb.append(this.contentType);
        }
        sb.append(",locale=");
        sb.append(this.locale);
        if (this.maxFileSize != null) {
            sb.append(",maxFileSzie=");
            sb.append(this.maxFileSize);
        }
        sb.append(",nocache=");
        sb.append(this.nocache);
        if (this.tempDir != null) {
            sb.append(",tempDir=");
            sb.append(this.tempDir);
        }
        sb.append("]");
        return (sb.toString());

    }


}
