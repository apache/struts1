/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELOptionsTag.java,v 1.8 2004/01/18 13:43:11 husted Exp $
 * $Revision: 1.8 $
 * $Date: 2004/01/18 13:43:11 $
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

import org.apache.struts.taglib.html.OptionsTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;

/**
 * Tag for creating multiple &lt;select&gt; options from a collection.  The
 * associated values displayed to the user may optionally be specified by a
 * second collection, or will be the same as the values themselves.  Each
 * collection may be an array of objects, a Collection, an Enumeration,
 * an Iterator, or a Map.
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.OptionsTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @version $Revision: 1.8 $
 */
public class ELOptionsTag extends OptionsTag {

    /**
     * Instance variable mapped to "collection" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String collectionExpr;
    /**
     * Instance variable mapped to "filter" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String filterExpr;
    /**
     * Instance variable mapped to "labelName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String labelNameExpr;
    /**
     * Instance variable mapped to "labelProperty" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String labelPropertyExpr;
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
     * Getter method for "collection" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getCollectionExpr() { return (collectionExpr); }
    /**
     * Getter method for "filter" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getFilterExpr() { return (filterExpr); }
    /**
     * Getter method for "labelName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getLabelNameExpr() { return (labelNameExpr); }
    /**
     * Getter method for "labelProperty" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getLabelPropertyExpr() { return (labelPropertyExpr); }
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
     * Setter method for "collection" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setCollectionExpr(String collectionExpr) { this.collectionExpr = collectionExpr; }
    /**
     * Setter method for "filter" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setFilterExpr(String filterExpr) { this.filterExpr = filterExpr; }
    /**
     * Setter method for "labelName" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setLabelNameExpr(String labelNameExpr) { this.labelNameExpr = labelNameExpr; }
    /**
     * Setter method for "labelProperty" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setLabelPropertyExpr(String labelPropertyExpr) { this.labelPropertyExpr = labelPropertyExpr; }
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
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setCollectionExpr(null);
        setFilterExpr(null);
        setLabelNameExpr(null);
        setLabelPropertyExpr(null);
        setNameExpr(null);
        setPropertyExpr(null);
        setStyleExpr(null);
        setStyleClassExpr(null);
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
     * Processes all attribute values which use the JSTL expression evaluation
     * engine to determine their values.
     *
     * @exception JspException if a JSP exception has occurred
     */
    private void evaluateExpressions() throws JspException {
        String  string  = null;
        Boolean bool    = null;

        if ((string = EvalHelper.evalString("collection", getCollectionExpr(),
                                            this, pageContext)) != null)
            setCollection(string);

        if ((bool = EvalHelper.evalBoolean("filter", getFilterExpr(),
                                           this, pageContext)) != null)
            setFilter(bool.booleanValue());

        if ((string = EvalHelper.evalString("labelName", getLabelNameExpr(),
                                            this, pageContext)) != null)
            setLabelName(string);

        if ((string = EvalHelper.evalString("labelProperty", getLabelPropertyExpr(),
                                            this, pageContext)) != null)
            setLabelProperty(string);

        if ((string = EvalHelper.evalString("name", getNameExpr(),
                                            this, pageContext)) != null)
            setName(string);

        if ((string = EvalHelper.evalString("property", getPropertyExpr(),
                                            this, pageContext)) != null)
            setProperty(string);

        if ((string = EvalHelper.evalString("style", getStyleExpr(),
                                            this, pageContext)) != null)
            setStyle(string);

        if ((string = EvalHelper.evalString("styleClass", getStyleClassExpr(),
                                            this, pageContext)) != null)
            setStyleClass(string);

        // Note that in contrast to other elements which have "style" and
        // "styleClass" attributes, this tag does not have a "styleId"
        // attribute.  This is because this produces the "id" attribute, which
        // has to be unique document-wide, but this tag can generate more than
        // one "option" element.  Thus, the base tag, "Options" does not
        // support this attribute.
    }
}
