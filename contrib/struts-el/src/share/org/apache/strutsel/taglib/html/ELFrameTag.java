/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELFrameTag.java,v 1.6 2003/02/19 03:52:49 dmkarr Exp $
 * $Revision: 1.6 $
 * $Date: 2003/02/19 03:52:49 $
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
 *    any, must include the following acknowledgement:
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

package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.FrameTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

/**
 * Generate an HTML <code>&lt;frame&gt;</code> tag with similar capabilities as
 * those the <code>&lt;html:link&gt;</code> tag provides for hyperlink
 * elements.  The <code>src</code> element is rendered using the same technique
 * that {@link org.apache.struts.taglib.html.LinkTag LinkTag} uses to render
 * the <code>href</code> attribute of a hyperlink.  Additionally, the HTML 4.0
 * frame tag attributes <code>noresize</code>, <code>scrolling</code>,
 * <code>marginheight</code>, <code>marginwidth</code>,
 * <code>frameborder</code>, and <code>longdesc</code> are supported.  The
 * frame <code>name</code> attribute is rendered based on the
 * <code>frameName</code> property.
 *<p>
 * Note that the value of <code>longdesc</code> is intended to be a URI, but
 * currently no rewriting is supported.  The attribute is set directly from
 * the property value.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.FrameTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @author David M. Karr
 * @version $Revision: 1.6 $
 */
public class ELFrameTag extends FrameTag {

    /**
     * Instance variable mapped to "anchor" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String anchorExpr;
    /**
     * Instance variable mapped to "forward" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String forwardExpr;
    /**
     * Instance variable mapped to "frameborder" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String frameborderExpr;
    /**
     * Instance variable mapped to "frameName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String frameNameExpr;
    /**
     * Instance variable mapped to "href" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String hrefExpr;
    /**
     * Instance variable mapped to "longdesc" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String longdescExpr;
    /**
     * Instance variable mapped to "marginheight" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String marginheightExpr;
    /**
     * Instance variable mapped to "marginwidth" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String marginwidthExpr;
    /**
     * Instance variable mapped to "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String nameExpr;
    /**
     * Instance variable mapped to "noresize" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String noresizeExpr;
    /**
     * Instance variable mapped to "page" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String pageExpr;
    /**
     * Instance variable mapped to "paramId" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String paramIdExpr;
    /**
     * Instance variable mapped to "paramName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String paramNameExpr;
    /**
     * Instance variable mapped to "paramProperty" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String paramPropertyExpr;
    /**
     * Instance variable mapped to "paramScope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String paramScopeExpr;
    /**
     * Instance variable mapped to "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String propertyExpr;
    /**
     * Instance variable mapped to "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String scopeExpr;
    /**
     * Instance variable mapped to "scrolling" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String scrollingExpr;
    /**
     * Instance variable mapped to "style" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String styleExpr;
    /**
     * Instance variable mapped to "styleClass" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String styleClassExpr;
    /**
     * Instance variable mapped to "styleId" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String styleIdExpr;
    /**
     * Instance variable mapped to "title" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String titleExpr;
    /**
     * Instance variable mapped to "titleKey" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String titleKeyExpr;
    /**
     * Instance variable mapped to "transaction" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String transactionExpr;

    /**
     * Getter method for "anchor" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getAnchorExpr() { return (anchorExpr); }
    /**
     * Getter method for "forward" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getForwardExpr() { return (forwardExpr); }
    /**
     * Getter method for "frameborder" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getFrameborderExpr() { return (frameborderExpr); }
    /**
     * Getter method for "frameName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getFrameNameExpr() { return (frameNameExpr); }
    /**
     * Getter method for "href" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getHrefExpr() { return (hrefExpr); }
    /**
     * Getter method for "longdesc" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getLongdescExpr() { return (longdescExpr); }
    /**
     * Getter method for "marginheight" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getMarginheightExpr() { return (marginheightExpr); }
    /**
     * Getter method for "marginwidth" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getMarginwidthExpr() { return (marginwidthExpr); }
    /**
     * Getter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getNameExpr() { return (nameExpr); }
    /**
     * Getter method for "noresize" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getNoresizeExpr() { return (noresizeExpr); }
    /**
     * Getter method for "page" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getPageExpr() { return (pageExpr); }
    /**
     * Getter method for "paramId" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getParamIdExpr() { return (paramIdExpr); }
    /**
     * Getter method for "paramName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getParamNameExpr() { return (paramNameExpr); }
    /**
     * Getter method for "paramProperty" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getParamPropertyExpr() { return (paramPropertyExpr); }
    /**
     * Getter method for "paramScope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getParamScopeExpr() { return (paramScopeExpr); }
    /**
     * Getter method for "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getPropertyExpr() { return (propertyExpr); }
    /**
     * Getter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getScopeExpr() { return (scopeExpr); }
    /**
     * Getter method for "scrolling" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getScrollingExpr() { return (scrollingExpr); }
    /**
     * Getter method for "style" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getStyleExpr() { return (styleExpr); }
    /**
     * Getter method for "styleClass" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getStyleClassExpr() { return (styleClassExpr); }
    /**
     * Getter method for "styleId" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getStyleIdExpr() { return (styleIdExpr); }
    /**
     * Getter method for "title" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getTitleExpr() { return (titleExpr); }
    /**
     * Getter method for "titleKey" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getTitleKeyExpr() { return (titleKeyExpr); }
    /**
     * Getter method for "transaction" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getTransactionExpr() { return (transactionExpr); }

    /**
     * Setter method for "anchor" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setAnchorExpr(String anchorExpr) { this.anchorExpr = anchorExpr; }
    /**
     * Setter method for "forward" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setForwardExpr(String forwardExpr) { this.forwardExpr = forwardExpr; }
    /**
     * Setter method for "frameborder" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setFrameborderExpr(String frameborderExpr) { this.frameborderExpr = frameborderExpr; }
    /**
     * Setter method for "frameName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setFrameNameExpr(String frameNameExpr) { this.frameNameExpr = frameNameExpr; }
    /**
     * Setter method for "href" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setHrefExpr(String hrefExpr) { this.hrefExpr = hrefExpr; }
    /**
     * Setter method for "longdesc" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setLongdescExpr(String longdescExpr) { this.longdescExpr = longdescExpr; }
    /**
     * Setter method for "marginheight" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setMarginheightExpr(String marginheightExpr) { this.marginheightExpr = marginheightExpr; }
    /**
     * Setter method for "marginwidth" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setMarginwidthExpr(String marginwidthExpr) { this.marginwidthExpr = marginwidthExpr; }
    /**
     * Setter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setNameExpr(String nameExpr) { this.nameExpr = nameExpr; }
    /**
     * Setter method for "noresize" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setNoresizeExpr(String noresizeExpr) { this.noresizeExpr = noresizeExpr; }
    /**
     * Setter method for "page" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setPageExpr(String pageExpr) { this.pageExpr = pageExpr; }
    /**
     * Setter method for "paramId" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setParamIdExpr(String paramIdExpr) { this.paramIdExpr = paramIdExpr; }
    /**
     * Setter method for "paramName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setParamNameExpr(String paramNameExpr) { this.paramNameExpr = paramNameExpr; }
    /**
     * Setter method for "paramProperty" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setParamPropertyExpr(String paramPropertyExpr) { this.paramPropertyExpr = paramPropertyExpr; }
    /**
     * Setter method for "paramScope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setParamScopeExpr(String paramScopeExpr) { this.paramScopeExpr = paramScopeExpr; }
    /**
     * Setter method for "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setPropertyExpr(String propertyExpr) { this.propertyExpr = propertyExpr; }
    /**
     * Setter method for "scope" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setScopeExpr(String scopeExpr) { this.scopeExpr = scopeExpr; }
    /**
     * Setter method for "scrolling" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setScrollingExpr(String scrollingExpr) { this.scrollingExpr = scrollingExpr; }
    /**
     * Setter method for "style" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setStyleExpr(String styleExpr) { this.styleExpr = styleExpr; }
    /**
     * Setter method for "styleClass" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setStyleClassExpr(String styleClassExpr) { this.styleClassExpr = styleClassExpr; }
    /**
     * Setter method for "styleId" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setStyleIdExpr(String styleIdExpr) { this.styleIdExpr = styleIdExpr; }
    /**
     * Setter method for "title" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setTitleExpr(String titleExpr) { this.titleExpr = titleExpr; }
    /**
     * Setter method for "titleKey" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setTitleKeyExpr(String titleKeyExpr) { this.titleKeyExpr = titleKeyExpr; }
    /**
     * Setter method for "transaction" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setTransactionExpr(String transactionExpr) { this.transactionExpr = transactionExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setAnchorExpr(null);
        setForwardExpr(null);
        setFrameborderExpr(null);
        setFrameNameExpr(null);
        setHrefExpr(null);
        setLongdescExpr(null);
        setMarginheightExpr(null);
        setMarginwidthExpr(null);
        setNameExpr(null);
        setNoresizeExpr(null);
        setPageExpr(null);
        setParamIdExpr(null);
        setParamNameExpr(null);
        setParamPropertyExpr(null);
        setParamScopeExpr(null);
        setPropertyExpr(null);
        setScopeExpr(null);
        setScrollingExpr(null);
        setStyleExpr(null);
        setStyleClassExpr(null);
        setStyleIdExpr(null);
        setTitleExpr(null);
        setTitleKeyExpr(null);
        setTransactionExpr(null);
    }

    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    /**
     * Evaluates and returns a single attribute value, given the attribute
     * name, attribute value, and attribute type.  It uses the
     * <code>EvalHelper</code> class to interface to
     * <code>ExpressionUtil.evalNotNull</code> to do the actual evaluation, and
     * it passes to this the name of the current tag, the <code>this</code>
     * pointer, and the current pageContext.
     *
     * @param attrName attribute name being evaluated
     * @param attrValue String value of attribute to be evaluated using EL
     * @param attrType Required resulting type of attribute value
     * @exception NullAttributeException if either the <code>attrValue</code>
     * was null, or the resulting evaluated value was null.
     * @return Resulting attribute value
     */
    private Object   evalAttr(String   attrName,
                              String   attrValue,
                              Class    attrType)
        throws JspException, NullAttributeException
    {
        return (EvalHelper.eval("button", attrName, attrValue, attrType,
                                this, pageContext));
    }
    
    /**
     * Processes all attribute values which use the JSTL expression evaluation
     * engine to determine their values.  If any evaluation fails with a
     * <code>NullAttributeException</code> it will just use <code>null</code>
     * as the value.
     *
     * @exception JspException if a JSP exception has occurred
     */
    private void evaluateExpressions() throws JspException {
        try {
            setAnchor((String) evalAttr("anchor", getAnchorExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setForward((String) evalAttr("forward", getForwardExpr(),
                                         String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setFrameborder((String) evalAttr("frameborder", getFrameborderExpr(),
                                             String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setFrameName((String) evalAttr("frameName", getFrameNameExpr(),
                                           String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setHref((String) evalAttr("href", getHrefExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setLongdesc((String) evalAttr("longdesc", getLongdescExpr(),
                                          String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setMarginheight(((Integer) evalAttr("marginheight",
                                                getMarginheightExpr(),
                                                Integer.class)).
                            intValue());
        } catch (NullAttributeException ex) {
        }

        try {
            setMarginwidth(((Integer) evalAttr("marginwidth",
                                               getMarginwidthExpr(),
                                               Integer.class)).
                           intValue());
        } catch (NullAttributeException ex) {
        }

        try {
            setName((String) evalAttr("name", getNameExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setNoresize(((Boolean) evalAttr("noresize", getNoresizeExpr(),
                                            Boolean.class)).
                        booleanValue());
        } catch (NullAttributeException ex) {
        }

        try {
            setPage((String) evalAttr("page", getPageExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setParamId((String) evalAttr("paramId", getParamIdExpr(),
                                         String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setParamName((String) evalAttr("paramName", getParamNameExpr(),
                                           String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setParamProperty((String) evalAttr("paramProperty",
                                               getParamPropertyExpr(),
                                               String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setParamScope((String) evalAttr("paramScope", getParamScopeExpr(),
                                            String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setProperty((String) evalAttr("property", getPropertyExpr(),
                                          String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setScope((String) evalAttr("scope", getScopeExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setScrolling((String) evalAttr("scrolling", getScrollingExpr(),
                                           String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setStyle((String) evalAttr("style", getStyleExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setStyleClass((String) evalAttr("styleClass", getStyleClassExpr(),
                                            String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setStyleId((String) evalAttr("styleId", getStyleIdExpr(),
                                         String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setTitle((String) evalAttr("title", getTitleExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setTitleKey((String) evalAttr("titleKey", getTitleKeyExpr(),
                                          String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setTransaction(((Boolean) evalAttr("transaction",
                                               getTransactionExpr(),
                                               Boolean.class)).
                           booleanValue());
        } catch (NullAttributeException ex) {
        }
    }
}
