/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/ImageTag.java,v 1.25 2002/11/16 06:05:21 dgraham Exp $
 * $Revision: 1.25 $
 * $Date: 2002/11/16 06:05:21 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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


package org.apache.struts.taglib.html;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;
import org.apache.struts.Globals;


/**
 * Tag for input fields of type "image".
 *
 * @author Oleg V Alexeev
 * @version $Revision: 1.25 $ $Date: 2002/11/16 06:05:21 $
 */

public class ImageTag extends SubmitTag {


    // ------------------------------------------------------------- Properties


    /**
     * The alignment for this image.
     */
    protected String align = null;

    /**
     * @deprecated Align attribute is deprecated in HTML 4.x.
     */
    public String getAlign() {
        return (this.align);
    }

    /**
     * @deprecated Align attribute is deprecated in HTML 4.x.
     */
    public void setAlign(String align) {
        this.align = align;
    }


    /**
     * The border size around the image.
     */
    protected String border = null;

    public String getBorder() {
        return (this.border);
    }

    public void setBorder(String border) {
        this.border = border;
    }


    /**
     * The module-relative URI of the image.
     */
    protected String page = null;

    public String getPage() {
        return (this.page);
    }

    public void setPage(String page) {
        this.page = page;
    }


    /**
     * The message resources key of the module-relative URI of the image.
     */
    protected String pageKey = null;

    public String getPageKey() {
        return (this.pageKey);
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }


    /**
     * The name attribute for the image button.
     */
    protected String property = "";

    public String getProperty() {
        return (this.property);
    }

    public void setProperty(String property) {
        this.property = property;
    }


    /**
     * The URL of this image.
     */
    protected String src = null;

    public String getSrc() {
        return (this.src);
    }

    public void setSrc(String src) {
        this.src = src;
    }


    /**
     * The message resources key for the URL of this image.
     */
    protected String srcKey = null;

    public String getSrcKey() {
        return (this.srcKey);
    }

    public void setSrcKey(String srcKey) {
        this.srcKey = srcKey;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        return (EVAL_BODY_TAG);

    }



    /**
     * Process the end of this tag.
     * [Indexed property since Struts 1.1]
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        // Generate an HTML <input type="image"> element
        HttpServletResponse response =
            (HttpServletResponse) pageContext.getResponse();
        String tmp = null;
        StringBuffer results = new StringBuffer();
        results.append("<input type=\"image\"");
        if (property != null) {
            results.append(" name=\"");
            results.append(property);
            // @since Struts 1.1
            if( indexed )
                prepareIndex( results, null );
            results.append("\"");
        }
        
        tmp = src();
        if (tmp != null) {
            results.append(" src=\"");
            results.append(response.encodeURL(tmp));
            results.append("\"");
        }
        if (align != null) {
            results.append(" align=\"");
            results.append(align);
            results.append("\"");
        }
        if (border != null) {
            results.append(" border=\"");
            results.append(border);
            results.append("\"");
        }
        if (value != null) {
            results.append(" value=\"");
            results.append(value);
            results.append("\"");
        }
        if (accesskey != null) {
            results.append(" accesskey=\"");
            results.append(accesskey);
            results.append("\"");
        }
        if (tabindex != null) {
            results.append(" tabindex=\"");
            results.append(tabindex);
            results.append("\"");
        }
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        results.append(getElementClose());

        // Render this element to our writer
        ResponseUtils.write(pageContext, results.toString());

        // Evaluate the remainder of this page
        return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        page = null;
        pageKey = null;
        property = "";
        src = null;
        srcKey = null;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the base source URL that will be rendered in the <code>src</code>
     * property for this generated element, or <code>null</code> if there is
     * no such URL.
     *
     * @exception JspException if an error occurs
     */
    protected String src() throws JspException {

        // Deal with a direct context-relative page that has been specified
        if (this.page != null) {
            if ((this.src != null) || (this.srcKey != null) ||
                (this.pageKey != null)) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.src"));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }
            ModuleConfig config = (ModuleConfig)
                pageContext.getRequest().getAttribute(Globals.MODULE_KEY);
            HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
            if (config == null) {
                return (request.getContextPath() + this.page);
            } else {
                return (request.getContextPath() + config.getPrefix() +
                        this.page);
            }
        }

        // Deal with an indirect context-relative page that has been specified
        if (this.pageKey != null) {
            if ((this.src != null) || (this.srcKey != null)) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.src"));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }
            ModuleConfig config = (ModuleConfig)
                pageContext.getRequest().getAttribute(Globals.MODULE_KEY);
            HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
            if (config == null) {
                return (request.getContextPath() +
                        RequestUtils.message(pageContext, getBundle(),
                                             getLocale(), this.pageKey));
            } else {
                return (request.getContextPath() + config.getPrefix() +
                        RequestUtils.message(pageContext, getBundle(),
                                             getLocale(), this.pageKey));
            }
        }

        // Deal with an absolute source that has been specified
        if (this.src != null) {
            if (this.srcKey != null) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.src"));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }
            return (this.src);
        }

        // Deal with an indirect source that has been specified
        if (this.srcKey == null) {
            JspException e = new JspException
                (messages.getMessage("imgTag.src"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }
        return (RequestUtils.message(pageContext, getBundle(),
                                     getLocale(), this.srcKey));

    }


}
