/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/ImageTag.java,v 1.31 2004/03/14 06:23:46 sraeburn Exp $
 * $Revision: 1.31 $
 * $Date: 2004/03/14 06:23:46 $
 *
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.struts.taglib.html;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;


/**
 * Tag for input fields of type "image".
 *
 * @version $Revision: 1.31 $ $Date: 2004/03/14 06:23:46 $
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

            if (indexed) {
                prepareIndex(results, null);
            }
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

        TagUtils.getInstance().write(pageContext, results.toString());

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
                TagUtils.getInstance().saveException(pageContext, e);
                throw e;
            }
            
            ModuleConfig config = (ModuleConfig)
                pageContext.getRequest().getAttribute(Globals.MODULE_KEY);
            
            HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
            
            if (config == null) {
                return (request.getContextPath() + this.page);
            } else {
                return (request.getContextPath() + config.getPrefix() + this.page);
            }
        }

        // Deal with an indirect context-relative page that has been specified
        if (this.pageKey != null) {
            if ((this.src != null) || (this.srcKey != null)) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.src"));
                TagUtils.getInstance().saveException(pageContext, e);
                throw e;
            }
            
            ModuleConfig config = (ModuleConfig)
                pageContext.getRequest().getAttribute(Globals.MODULE_KEY);
            
            HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
            
            if (config == null) {
                return (
                    request.getContextPath()
                        + TagUtils.getInstance().message(
                            pageContext,
                            getBundle(),
                            getLocale(),
                            this.pageKey));
            } else {
                return (
                    request.getContextPath()
                        + config.getPrefix()
                        + TagUtils.getInstance().message(
                            pageContext,
                            getBundle(),
                            getLocale(),
                            this.pageKey));
            }
        }

        // Deal with an absolute source that has been specified
        if (this.src != null) {
            if (this.srcKey != null) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.src"));
                TagUtils.getInstance().saveException(pageContext, e);
                throw e;
            }
            return (this.src);
        }

        // Deal with an indirect source that has been specified
        if (this.srcKey == null) {
            JspException e = new JspException
                (messages.getMessage("imgTag.src"));
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }
        
        return TagUtils.getInstance().message(
            pageContext,
            getBundle(),
            getLocale(),
            this.srcKey);

    }


}
