/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/FrameTag.java,v 1.8 2002/11/16 06:05:21 dgraham Exp $
 * $Revision: 1.8 $
 * $Date: 2002/11/16 06:05:21 $
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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


import javax.servlet.jsp.JspException;
import org.apache.struts.util.ResponseUtils;


/**
 * Generate an HTML <code>&lt;frame&gt;</code> tag with similar capabilities
 * as those the <code>&lt;html:link&gt;</code> tag provides for hyperlink
 * elements.  The <code>src</code> element is rendered using the same technique
 * that {@link LinkTag} uses to render the <code>href</code> attribute of a
 * hyperlink.  Additionall, the HTML 4.0
 * frame tag attributes <code>noresize</code>, <code>scrolling</code>,
 * <code>marginheight</code>, <code>marginwidth</code>,
 * <code>frameborder</code>, and <code>longdesc</code> are supported.
 * The frame
 * <code>name</code> attribute is rendered based on the <code>frameName</code>
 * property.
 *
 * Note that the value of <code>longdesc</code> is intended to be a URI, but
 * currently no rewriting is supported.  The attribute is set directly from
 * the property value.
 *
 * @author Joe Germuska
 * @author Craig R. McClanahan
 * @version $Revision: 1.8 $ $Date: 2002/11/16 06:05:21 $
 * @since Struts 1.1
 */

public class FrameTag extends LinkTag {


    // ------------------------------------------------------------- Properties


    /**
     * The frameborder attribute that should be rendered (1, 0).
     */
    protected String frameborder = null;

    public String getFrameborder() {
        return (this.frameborder);
    }

    public void setFrameborder(String frameborder) {
        this.frameborder = frameborder;
    }


    /**
     * The <code>name</code> attribute that should be rendered for this frame.
     */
    protected String frameName = null;

    public String getFrameName() {
        return (this.frameName);
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }


    /**
     * URI of a long description of this frame (complements title).
     */
    protected String longdesc = null;

    public String getLongdesc() {
        return (this.longdesc);
    }

    public void setLongdesc(String longdesc) {
        this.longdesc = longdesc;
    }


    /**
     * The margin height in pixels, or zero for no setting.
     */
    protected int marginheight = 0;

    public int getMarginheight() {
        return (this.marginheight);
    }

    public void setMarginheight(int marginheight) {
        this.marginheight = marginheight;
    }


    /**
     * The margin width in pixels, or zero for no setting.
     */
    protected int marginwidth = 0;

    public int getMarginwidth() {
        return (this.marginwidth);
    }

    public void setMarginwidth(int marginwidth) {
        this.marginwidth = marginwidth;
    }


    /**
     * Should users be disallowed to resize the frame?
     */
    protected boolean noresize = false;

    public boolean getNoresize() {
        return (this.noresize);
    }

    public void setNoresize(boolean noresize) {
        this.noresize = noresize;
    }


    /**
     * What type of scrolling should be supported (yes, no, auto)?
     */
    protected String scrolling = null;

    public String getScrolling() {
        return (this.scrolling);
    }

    public void setScrolling(String scrolling) {
        this.scrolling = scrolling;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Render the appropriately encoded URI.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

    // Print this element to our output writer
        StringBuffer results = new StringBuffer("<frame ");
        results.append("src=\"");
        results.append(calculateURL());
        results.append("\"");
        if (frameName != null) {
            results.append(" name=\"");
            results.append(frameName);
            results.append("\"");
        }
        if (noresize) {
            results.append(" noresize=\"noresize\"");
        }
        if (scrolling != null) {
            results.append(" scrolling=\"");
            results.append(scrolling);
            results.append("\"");
        }
        if (marginheight > 0) {
            results.append(" marginheight=\"");
            results.append(marginheight);
            results.append("\"");
        }
        if (marginwidth > 0) {
            results.append(" marginwidth=\"");
            results.append(marginwidth);
            results.append("\"");
        }
        if (frameborder != null) {
            results.append(" frameborder=\"");
            results.append(frameborder);
            results.append("\"");
        }
        if (longdesc != null) {
            results.append(" longdesc=\"");
            results.append(longdesc);
            results.append("\"");
        }
        results.append(prepareStyles());
        results.append(getElementClose());
        ResponseUtils.write(pageContext,results.toString());

    // Skip the body of this tag
    return (SKIP_BODY);

    }


    /**
     * Ignore the end of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        frameborder = null;
        frameName = null;
        longdesc = null;
        marginheight = 0;
        marginwidth = 0;
        noresize = false;
        scrolling = null;

    }


}
