/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/ControllerConfig.java,v 1.15 2003/02/25 04:47:38 dgraham Exp $
 * $Revision: 1.15 $
 * $Date: 2003/02/25 04:47:38 $
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


package org.apache.struts.config;


import java.io.Serializable;


/**
 * <p>A JavaBean representing the configuration information of a
 * <code>&lt;controller&gt;</code> element in a Struts
 * configuration file.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.15 $ $Date: 2003/02/25 04:47:38 $
 * @since Struts 1.1
 */

public class ControllerConfig implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * Has this component been completely configured?
     */
    protected boolean configured = false;


    // ------------------------------------------------------------- Properties


    /**
     * The input buffer size for file uploads.
     */
    protected int bufferSize = 4096;

    public int getBufferSize() {
        return (this.bufferSize);
    }

    public void setBufferSize(int bufferSize) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
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
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.contentType = contentType;
    }


    /**
     * The debugging detail level that determines logging verbosity.
     * @deprecated Configure the logging detail level in your underlying
     *  logging implemenation
     */
    protected int debug = 0;

    public int getDebug() {
        return (this.debug);
    }

    public void setDebug(int debug) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.debug = debug;
    }


    /**
     * <p>The replacement pattern used to determine a context-relative URL
     * from a {@link ForwardConfig} element.  The pattern may consist of any
     * combination of the following markers and characters:</p>
     * <ul>
     * <li><code><strong>$M</strong></code> - Replaced by the module
     *     prefix for the current module.</li>
     * <li><code><strong>$P</strong></code> - Replaced by the <code>path</code>
     *     property of a {@link ForwardConfig} instance.</li>
     * <li><code><strong>$$</strong></code> - Renders a literal dollar sign
     *     ("$") character in the resulting URL.</li>
     * <li>A dollar sign followed by any other character is reserved for
     *     future use, and both characters are silently swallowed.</li>
     * <li>All other characters in the pattern are passed through unchanged.
     *     </li>
     * </ul>
     *
     * <p>If this property is set to <code>null</code>, a default pattern of
     * <code>$M$P</code> is utilized, which is backwards compatible with
     * the hard coded functionality in prior versions.</p>
     */
    protected String forwardPattern = null;

    public String getForwardPattern() {
        return (this.forwardPattern);
    }

    public void setForwardPattern(String forwardPattern) {
        this.forwardPattern = forwardPattern;
    }


    /**
     * <p>Should the <code>input</code> property of {@link ActionConfig}
     * instances associated with this module be treated as the
     * name of a corresponding {@link ForwardConfig}.  A <code>false</code>
     * value treats them as a module-relative path (consistent
     * with the hard coded behavior of earlier versions of Struts.</p>
     *
     * @since Struts 1.1
     */
    protected boolean inputForward = false;

    public boolean getInputForward() {
        return (this.inputForward);
    }

    public void setInputForward(boolean inputForward) {
        this.inputForward = inputForward;
    }


    /**
     * Should we store a Locale object in the user's session if needed?
     */
    protected boolean locale = true;

    public boolean getLocale() {
        return (this.locale);
    }

    public void setLocale(boolean locale) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
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
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.maxFileSize = maxFileSize;
    }


    /**
     * The maximum file size to retain in memory.
     */
    protected String memFileSize = "256K";

    public String getMemFileSize() {
        return (this.memFileSize);
    }

    public void setMemFileSize(String memFileSize) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.memFileSize = memFileSize;
    }


    /**
     * The fully qualified Java class name of the MultipartRequestHandler
     * class to be used.
     */
    protected String multipartClass =
        "org.apache.struts.upload.CommonsMultipartRequestHandler";

    public String getMultipartClass() {
        return (this.multipartClass);
    }

    public void setMultipartClass(String multipartClass) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
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
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.nocache = nocache;
    }


    /**
     * <p>The replacement pattern used to determine a context-relative URL
     * from the <code>page</code> attribute of Struts tags and configuration
     * properties.  The pattern may consist of any combination of the
     * following markers and characters:</p>
     * <ul>
     * <li><code><strong>$M</strong></code> - Replaced by the module
     *     prefix for the current module.</li>
     * <li><code><strong>$P</strong></code> - Replaced by the <code>page</code>
     *     attribute value being evaluated.</li>
     * <li><code><strong>$$</strong></code> - Renders a literal dollar sign
     *     ("$") character in the resulting URL.</li>
     * <li>A dollar sign followed by any other character is reserved for
     *     future use, and both characters are silently swallowed.</li>
     * <li>All other characters in the pattern are passed through unchanged.
     *     </li>
     * </ul>
     *
     * <p>If this property is set to <code>null</code>, a default pattern of
     * <code>$M$P</code> is utilized, which is backwards compatible with
     * the hard coded functionality in prior versions.</p>
     */
    protected String pagePattern = null;

    public String getPagePattern() {
        return (this.pagePattern);
    }

    public void setPagePattern(String pagePattern) {
        this.pagePattern = pagePattern;
    }


    /**
     * The fully qualified class name of the RequestProcessor implementation
     * class to be used for this module.
     */
    protected String processorClass =
        "org.apache.struts.action.RequestProcessor";

    public String getProcessorClass() {
        return (this.processorClass);
    }

    public void setProcessorClass(String processorClass) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.processorClass = processorClass;
    }


    /**
     * The temporary working directory to use for file uploads.
     */
    protected String tempDir = null;

    public String getTempDir() {
        return (this.tempDir);
    }

    public void setTempDir(String tempDir) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.tempDir = tempDir;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Freeze the configuration of this component.
     */
    public void freeze() {

        configured = true;

    }


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
        if (this.forwardPattern != null) {
            sb.append(",forwardPattern=");
            sb.append(this.forwardPattern);
        }
        sb.append(",inputForward=");
        sb.append(this.inputForward);
        sb.append(",locale=");
        sb.append(this.locale);
        if (this.maxFileSize != null) {
            sb.append(",maxFileSize=");
            sb.append(this.maxFileSize);
        }
        if (this.memFileSize != null) {
            sb.append(",memFileSize=");
            sb.append(this.memFileSize);
        }
        sb.append(",multipartClass=");
        sb.append(this.multipartClass);
        sb.append(",nocache=");
        sb.append(this.nocache);
        if (this.pagePattern != null) {
            sb.append(",pagePattern=");
            sb.append(this.pagePattern);
        }
        sb.append(",processorClass=");
        sb.append(this.processorClass);
        if (this.tempDir != null) {
            sb.append(",tempDir=");
            sb.append(this.tempDir);
        }
        sb.append("]");
        return (sb.toString());

    }


}
