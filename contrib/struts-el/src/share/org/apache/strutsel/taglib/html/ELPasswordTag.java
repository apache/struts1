/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELPasswordTag.java,v 1.4 2002/10/14 03:18:38 dmkarr Exp $
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

import org.apache.struts.taglib.html.PasswordTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

/**
 * Custom tag for input fields of type "password".
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.PasswordTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @author David M. Karr
 * @version $Revision: 1.4 $
 */
public class ELPasswordTag extends PasswordTag {

    /**
     * String value of the "disabled" attribute.
     */
    private String   disabledExpr;
    /**
     * String value of the "indexed" attribute.
     */
    private String   indexedExpr;
    /**
     * String value of the "readonly" attribute.
     */
    private String   readonlyExpr;
    /**
     * String value of the "redisplay" attribute.
     */
    private String   redisplayExpr;

    /**
     * Returns the string value of the "disabled" attribute.
     */
    public  String   getDisabledExpr() { return (disabledExpr); }
    /**
     * Returns the string value of the "indexed" attribute.
     */
    public  String   getIndexedExpr() { return (indexedExpr); }
    /**
     * Returns the string value of the "readonly" attribute.
     */
    public  String   getReadonlyExpr() { return (readonlyExpr); }
    /**
     * Returns the string value of the "redisplay" attribute.
     */
    public  String   getRedisplayExpr() { return (redisplayExpr); }

    /**
     * Sets the string value of the "disabled" attribute.  This attribute is
     * mapped to this method by the <code>ELPasswordTagBeanInfo</code> class.
     */
    public  void     setDisabledExpr(String disabledExpr)
    { this.disabledExpr  = disabledExpr; }

    /**
     * Sets the string value of the "indexed" attribute.  This attribute is
     * mapped to this method by the <code>ELPasswordTagBeanInfo</code> class.
     */
    public  void     setIndexedExpr(String indexedExpr)
    { this.indexedExpr  = indexedExpr; }

    /**
     * Sets the string value of the "readonly" attribute.  This attribute is
     * mapped to this method by the <code>ELPasswordTagBeanInfo</code> class.
     */
    public  void     setReadonlyExpr(String readonlyExpr)
    { this.readonlyExpr  = readonlyExpr; }

    /**
     * Sets the string value of the "redisplay" attribute.  This attribute is
     * mapped to this method by the <code>ELPasswordTagBeanInfo</code> class.
     */
    public  void     setRedisplayExpr(String redisplayExpr)
    { this.redisplayExpr  = redisplayExpr; }
    
    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setDisabledExpr(null);
        setIndexedExpr(null);
        setReadonlyExpr(null);
        setRedisplayExpr(null);
    }

    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
        evaluateExpressions();
        return(super.doStartTag());
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
        return (EvalHelper.eval("password", attrName, attrValue, attrType,
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
            setAccesskey((String) evalAttr("accessKey", getAccesskey(),
                                           String.class));
        } catch (NullAttributeException ex) {
            setAccesskey(null);
        }

        try {
            setAlt((String) evalAttr("alt", getAlt(), String.class));
        } catch (NullAttributeException ex) {
            setAlt(null);
        }

        try {
            setAltKey((String) evalAttr("altKey", getAltKey(), String.class));
        } catch (NullAttributeException ex) {
            setAltKey(null);
        }

        try {
            setDisabled(((Boolean) evalAttr("disabled", getDisabledExpr(),
                                            Boolean.class)).
                        booleanValue());
        } catch (NullAttributeException ex) {
            setDisabled(false);
        }

        try {
            setIndexed(((Boolean) evalAttr("indexed", getIndexedExpr(),
                                           Boolean.class)).
                       booleanValue());
        } catch (NullAttributeException ex) {
            setIndexed(false);
        }

        try {
            setMaxlength((String) evalAttr("maxlength", getMaxlength(),
                                           String.class));
        } catch (NullAttributeException ex) {
            setMaxlength(null);
        }

        try {
            setName((String) evalAttr("name", getName(), String.class));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setOnblur((String) evalAttr("onblur", getOnblur(), String.class));
        } catch (NullAttributeException ex) {
            setOnblur(null);
        }

        try {
            setOnchange((String) evalAttr("onchange", getOnchange(),
                                          String.class));
        } catch (NullAttributeException ex) {
            setOnchange(null);
        }

        try {
            setOnclick((String) evalAttr("onclick", getOnclick(),
                                         String.class));
        } catch (NullAttributeException ex) {
            setOnclick(null);
        }

        try {
            setOndblclick((String) evalAttr("ondblclick", getOndblclick(),
                                            String.class));
        } catch (NullAttributeException ex) {
            setOndblclick(null);
        }

        try {
            setOnfocus((String) evalAttr("onfocus", getOnfocus(),
                                         String.class));
        } catch (NullAttributeException ex) {
            setOnfocus(null);
        }

        try {
            setOnkeydown((String) evalAttr("onkeydown", getOnkeydown(),
                                           String.class));
        } catch (NullAttributeException ex) {
            setOnkeydown(null);
        }

        try {
            setOnkeypress((String) evalAttr("onkeypress", getOnkeypress(),
                                            String.class));
        } catch (NullAttributeException ex) {
            setOnkeypress(null);
        }

        try {
            setOnkeyup((String) evalAttr("onkeyup", getOnkeyup(),
                                         String.class));
        } catch (NullAttributeException ex) {
            setOnkeyup(null);
        }

        try {
            setOnmousedown((String) evalAttr("onmousedown", getOnmousedown(),
                                             String.class));
        } catch (NullAttributeException ex) {
            setOnmousedown(null);
        }

        try {
            setOnmousemove((String) evalAttr("onmousemove", getOnmousemove(), 
                                             String.class));
        } catch (NullAttributeException ex) {
            setOnmousemove(null);
        }

        try {
            setOnmouseout((String) evalAttr("onmouseout", getOnmouseout(),
                                            String.class));
        } catch (NullAttributeException ex) {
            setOnmouseout(null);
        }

        try {
            setOnmouseover((String) evalAttr("onmouseover", getOnmouseover(), 
                                             String.class));
        } catch (NullAttributeException ex) {
            setOnmouseover(null);
        }

        try {
            setOnmouseup((String) evalAttr("onmouseup", getOnmouseup(),
                                           String.class));
        } catch (NullAttributeException ex) {
            setOnmouseup(null);
        }

        try {
            setProperty((String) evalAttr("property", getProperty(),
                                          String.class));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setReadonly(((Boolean) evalAttr("readonly", getReadonlyExpr(),
                                            Boolean.class)).
                        booleanValue());
        } catch (NullAttributeException ex) {
            setReadonly(false);
        }

        try {
            setRedisplay(((Boolean) evalAttr("redisplay", getRedisplayExpr(), 
                                             Boolean.class)).
                         booleanValue());
        } catch (NullAttributeException ex) {
            setRedisplay(false);
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
            setSize((String) evalAttr("size", getSize(), String.class));
        } catch (NullAttributeException ex) {
            setSize(null);
        }

        try {
            setTabindex((String) evalAttr("tabindex", getTabindex(),
                                          String.class));
        } catch (NullAttributeException ex) {
            setTabindex(null);
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
            setValue((String) evalAttr("value", getValue(), String.class));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
