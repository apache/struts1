/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELErrorsTag.java,v 1.5 2003/02/26 06:12:25 dmkarr Exp $
 * $Revision: 1.5 $
 * $Date: 2003/02/26 06:12:25 $
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

import org.apache.struts.taglib.html.ErrorsTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

/**
 * Custom tag that renders error messages if an appropriate request attribute
 * has been created.  The tag looks for a request attribute with a reserved
 * key, and assumes that it is either a String, a String array, containing
 * message keys to be looked up in the module's MessageResources, or
 * an object of type <code>org.apache.struts.action.ActionErrors</code>.
 * <p>
 * The following optional message keys will be utilized if corresponding
 * messages exist for them in the application resources:
 * <ul>
 * <li><b>errors.header</b> - If present, the corresponding message will be
 *     rendered prior to the individual list of error messages.</li>
 * <li><b>errors.footer</b> - If present, the corresponding message will be
 *     rendered following the individual list of error messages.</li>
 * <li><b>errors.prefix</b> - If present, the corresponding message will be
 *     rendered before each individual error message.</li>
 * <li><b>errors.suffix</b> - If present, the corresponding message will be
 *     rendered after each individual error message.</li>
 * </ul>
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.ErrorsTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @author David M. Karr
 * @version $Revision: 1.5 $
 */
public class ELErrorsTag extends ErrorsTag {

    /**
     * Instance variable mapped to "bundle" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String bundleExpr;
    /**
     * Instance variable mapped to "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String localeExpr;
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
     * Getter method for "bundle" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getBundleExpr() { return (bundleExpr); }
    /**
     * Getter method for "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getLocaleExpr() { return (localeExpr); }
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
     * Setter method for "bundle" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setBundleExpr(String bundleExpr) { this.bundleExpr = bundleExpr; }
    /**
     * Setter method for "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setLocaleExpr(String localeExpr) { this.localeExpr = localeExpr; }
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
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setBundleExpr(null);
        setLocaleExpr(null);
        setNameExpr(null);
        setPropertyExpr(null);
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
     * name, attribute value, and attribute type.  It uses
     * <code>ExpressionUtil.evalNotNull</code> to do the actual evaluation, and
     * it passes to this the name of the current tag, the <code>this</code>
     * pointer, and the current pageContext.
     *
     * @param attrName attribute name being evaluated
     * @param attrValue String value of attribute to be evaluated using EL
     * @param attrType Required resulting type of attribute value
     * @return Resulting attribute value
     */
    private Object   evalAttr(String   attrName,
                              String   attrValue,
                              Class    attrType)
        throws JspException, NullAttributeException
    {
        return (EvalHelper.eval("errors", attrName, attrValue,
                                attrType, this, pageContext));
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
            setBundle((String) evalAttr("bundle", getBundleExpr(), String.class));
        } catch (NullAttributeException ex) {
        }

        try {
            setLocale((String) evalAttr("locale", getLocaleExpr(), String.class));
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
    }
}
