/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELFrameTag.java,v 1.4 2002/10/14 03:18:38 dmkarr Exp $
 * $Revision: 1.4 $
 * $Date: 2002/10/14 03:18:38 $
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
 * @version $Revision: 1.4 $
 */
public class ELFrameTag extends FrameTag {

    /**
     * String value of the "noresize" attribute.
     */
    private String   noresizeExpr;
    /**
     * String value of the "transaction" attribute.
     */
    private String   transactionExpr;
    /**
     * String value of the "marginheight" attribute.
     */
    private String   marginheightExpr;
    /**
     * String value of the "marginwidth" attribute.
     */
    private String   marginwidthExpr;

    /**
     * Returns the string value of the "noresize" attribute.
     */
    public  String   getNoresizeExpr() { return (noresizeExpr); }
    /**
     * Returns the string value of the "transaction" attribute.
     */
    public  String   getTransactionExpr() { return (transactionExpr); }
    /**
     * Returns the string value of the "marginheight" attribute.
     */
    public  String   getMarginheightExpr() { return (marginheightExpr); }
    /**
     * Returns the string value of the "marginwidth" attribute.
     */
    public  String   getMarginwidthExpr() { return (marginwidthExpr); }

    /**
     * Sets the string value of the "noresize" attribute.  This attribute is
     * mapped to this method by the <code>ELFrameTagBeanInfo</code> class.
     */
    public  void     setNoresizeExpr(String noresizeExpr)
    { this.noresizeExpr  = noresizeExpr; }

    /**
     * Sets the string value of the "transaction" attribute.  This attribute is
     * mapped to this method by the <code>ELFrameTagBeanInfo</code> class.
     */
    public  void     setTransactionExpr(String transactionExpr)
    { this.transactionExpr  = transactionExpr; }

    /**
     * Sets the string value of the "marginheight" attribute.  This attribute is
     * mapped to this method by the <code>ELFrameTagBeanInfo</code> class.
     */
    public  void     setMarginheightExpr(String marginheightExpr)
    { this.marginheightExpr  = marginheightExpr; }

    /**
     * Sets the string value of the "marginwidth" attribute.  This attribute is
     * mapped to this method by the <code>ELFrameTagBeanInfo</code> class.
     */
    public  void     setMarginwidthExpr(String marginwidthExpr)
    { this.marginwidthExpr  = marginwidthExpr; }
    
    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setNoresizeExpr(null);
        setTransactionExpr(null);
        setMarginheightExpr(null);
        setMarginwidthExpr(null);
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
            setAnchor((String) evalAttr("anchor", getAnchor(), String.class));
        } catch (NullAttributeException ex) {
            setAnchor(null);
        }

        try {
            setForward((String) evalAttr("forward", getForward(),
                                         String.class));
        } catch (NullAttributeException ex) {
            setForward(null);
        }

        try {
            setFrameborder((String) evalAttr("frameborder", getFrameborder(),
                                             String.class));
        } catch (NullAttributeException ex) {
            setFrameborder(null);
        }

        try {
            setFrameName((String) evalAttr("frameName", getFrameName(),
                                           String.class));
        } catch (NullAttributeException ex) {
            setFrameName(null);
        }

        try {
            setHref((String) evalAttr("href", getHref(), String.class));
        } catch (NullAttributeException ex) {
            setHref(null);
        }

        try {
            setLongdesc((String) evalAttr("longdesc", getLongdesc(),
                                          String.class));
        } catch (NullAttributeException ex) {
            setLongdesc(null);
        }

        try {
            setMarginheight(((Integer) evalAttr("marginheight",
                                                getMarginheightExpr(),
                                                Integer.class)).
                            intValue());
        } catch (NullAttributeException ex) {
            setMarginheight(0);
        }

        try {
            setMarginwidth(((Integer) evalAttr("marginwidth",
                                               getMarginwidthExpr(),
                                               Integer.class)).
                           intValue());
        } catch (NullAttributeException ex) {
            setMarginwidth(0);
        }

        try {
            setName((String) evalAttr("name", getName(), String.class));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setNoresize(((Boolean) evalAttr("noresize", getNoresizeExpr(),
                                            Boolean.class)).
                        booleanValue());
        } catch (NullAttributeException ex) {
            setNoresize(false);
        }

        try {
            setPage((String) evalAttr("page", getPage(), String.class));
        } catch (NullAttributeException ex) {
            setPage(null);
        }

        try {
            setParamId((String) evalAttr("paramId", getParamId(),
                                         String.class));
        } catch (NullAttributeException ex) {
            setParamId(null);
        }

        try {
            setParamName((String) evalAttr("paramName", getParamName(),
                                           String.class));
        } catch (NullAttributeException ex) {
            setParamName(null);
        }

        try {
            setParamProperty((String) evalAttr("paramProperty",
                                               getParamProperty(),
                                               String.class));
        } catch (NullAttributeException ex) {
            setParamProperty(null);
        }

        try {
            setParamScope((String) evalAttr("paramScope", getParamScope(),
                                            String.class));
        } catch (NullAttributeException ex) {
            setParamScope(null);
        }

        try {
            setProperty((String) evalAttr("property", getProperty(),
                                          String.class));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String) evalAttr("scope", getScope(), String.class));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setScrolling((String) evalAttr("scrolling", getScrolling(),
                                           String.class));
        } catch (NullAttributeException ex) {
            setScrolling(null);
        }

        try {
            setStyle((String) evalAttr("style", getStyle(), String.class));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String) evalAttr("styleClass", getStyleClass(),
                                            String.class));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

        try {
            setStyleId((String) evalAttr("styleId", getStyleId(),
                                         String.class));
        } catch (NullAttributeException ex) {
            setStyleId(null);
        }

        try {
            setTitle((String) evalAttr("title", getTitle(), String.class));
        } catch (NullAttributeException ex) {
            setTitle(null);
        }

        try {
            setTitleKey((String) evalAttr("titleKey", getTitleKey(),
                                          String.class));
        } catch (NullAttributeException ex) {
            setTitleKey(null);
        }

        try {
            setTransaction(((Boolean) evalAttr("transaction",
                                               getTransactionExpr(),
                                               Boolean.class)).
                           booleanValue());
        } catch (NullAttributeException ex) {
            setTransaction(false);
        }
    }
}
