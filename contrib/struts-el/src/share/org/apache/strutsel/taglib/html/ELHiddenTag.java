/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELHiddenTag.java,v 1.5 2003/02/19 03:52:49 dmkarr Exp $
 * $Revision: 1.5 $
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

import org.apache.struts.taglib.html.HiddenTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

/**
 * Custom tag for input fields of type "hidden".
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.HiddenTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @author David M. Karr
 * @version $Revision: 1.5 $
 */
public class ELHiddenTag extends HiddenTag {

    /**
     * Instance variable mapped to "alt" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String altExpr;
    /**
     * Instance variable mapped to "altKey" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String altKeyExpr;
    /**
     * Instance variable mapped to "indexed" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String indexedExpr;
    /**
     * Instance variable mapped to "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String nameExpr;
    /**
     * Instance variable mapped to "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String propertyExpr;
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
     * Instance variable mapped to "value" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String valueExpr;
    /**
     * Instance variable mapped to "write" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String writeExpr;

    /**
     * Getter method for "alt" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getAltExpr() { return (altExpr); }
    /**
     * Getter method for "altKey" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getAltKeyExpr() { return (altKeyExpr); }
    /**
     * Getter method for "indexed" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getIndexedExpr() { return (indexedExpr); }
    /**
     * Getter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getNameExpr() { return (nameExpr); }
    /**
     * Getter method for "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getPropertyExpr() { return (propertyExpr); }
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
     * Getter method for "value" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getValueExpr() { return (valueExpr); }
    /**
     * Getter method for "write" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getWriteExpr() { return (writeExpr); }

    /**
     * Setter method for "alt" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setAltExpr(String altExpr) { this.altExpr = altExpr; }
    /**
     * Setter method for "altKey" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setAltKeyExpr(String altKeyExpr) { this.altKeyExpr = altKeyExpr; }
    /**
     * Setter method for "indexed" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setIndexedExpr(String indexedExpr) { this.indexedExpr = indexedExpr; }
    /**
     * Setter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setNameExpr(String nameExpr) { this.nameExpr = nameExpr; }
    /**
     * Setter method for "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setPropertyExpr(String propertyExpr) { this.propertyExpr = propertyExpr; }
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
     * Setter method for "value" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setValueExpr(String valueExpr) { this.valueExpr = valueExpr; }
    /**
     * Setter method for "write" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setWriteExpr(String writeExpr) { this.writeExpr = writeExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setAltExpr(null);
        setAltKeyExpr(null);
        setIndexedExpr(null);
        setNameExpr(null);
        setPropertyExpr(null);
        setTitleExpr(null);
        setTitleKeyExpr(null);
        setValueExpr(null);
        setWriteExpr(null);
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
        return (EvalHelper.eval("hidden", attrName, attrValue, attrType,
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
            setAlt((String) evalAttr("alt", getAltExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setAltKey((String) evalAttr("altKey", getAltKeyExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setIndexed(((Boolean) evalAttr("indexed", getIndexedExpr(),
                                           Boolean.class)).
                       booleanValue());
        } catch (NullAttributeException ex) {
        }

        try {
            setName((String) evalAttr("name", getNameExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setProperty((String) evalAttr("property", getPropertyExpr(),
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
            setValue((String) evalAttr("value", getValueExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setWrite(((Boolean) evalAttr("write", getWriteExpr(),
                                         Boolean.class)).
                     booleanValue());
        } catch (NullAttributeException ex) {
        }
    }
}
