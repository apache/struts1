/*
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.util.IteratorAdapter;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;


/**
 * Tag for creating multiple &lt;select&gt; options from a collection. The
 * collection may be part of the enclosing form, or may be independent of
 * the form. Each element of the collection must expose a 'label' and a
 * 'value', the property names of which are configurable by attributes of
 * this tag.
 * <p>
 * The collection may be an array of objects, a Collection, an Enumeration,
 * an Iterator, or a Map.
 * <p>
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 *
 * @author Martin Cooper
 * @version $Revision: 1.1 $ $Date: 2002/02/23 07:10:30 $
 */

public class OptionsCollectionTag extends TagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
            MessageResources.getMessageResources(
                Constants.Package + ".LocalStrings");


    // ------------------------------------------------------------- Properties


    /**
     * The name of the bean property containing the label.
     */
    protected String label = "label";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    /**
     * The name of the bean containing the values collection.
     */
    protected String name = Constants.BEAN_KEY;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * The name of the property to use to build the values collection.
     */
    protected String property = null;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }


    /**
     * The style associated with this tag.
     */
    private String style = null;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }


    /**
     * The named style class associated with this tag.
     */
    private String styleClass = null;

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }


    /**
     * The name of the bean property containing the value.
     */
    protected String value = "value";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Acquire the select tag we are associated with
        SelectTag selectTag =
                (SelectTag)pageContext.getAttribute(Constants.SELECT_KEY);

        if (selectTag == null) {
            JspException e = new JspException(
                    messages.getMessage("optionsCollectionTag.select"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        // Acquire the collection containing our options
        Object collection = RequestUtils.lookup(pageContext,
                name, property, null);

        if (collection == null) {
            JspException e = new JspException(
                    messages.getMessage("optionsCollectionTag.collection"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        // Acquire an iterator over the options collection
        Iterator iter = getIterator(collection);

        StringBuffer sb = new StringBuffer();

        // Render the options
        while (iter.hasNext()) {

            Object bean = iter.next();
            Object beanLabel = null;;
            Object beanValue = null;

            // Get the label for this option
            try {
                beanLabel = PropertyUtils.getProperty(bean, label);
                if (beanLabel == null) {
                    beanLabel = "";
                }
            } catch (IllegalAccessException e) {
                JspException jspe = new JspException(
                        messages.getMessage("getter.access", label, bean));
                RequestUtils.saveException(pageContext, jspe);
                throw jspe;
            } catch (InvocationTargetException e) {
                Throwable t = e.getTargetException();
                JspException jspe = new JspException(
                        messages.getMessage("getter.result", label,
                                t.toString()));
                RequestUtils.saveException(pageContext, jspe);
                throw jspe;
            } catch (NoSuchMethodException e) {
                JspException jspe = new JspException(
                        messages.getMessage("getter.method", label, bean));
                RequestUtils.saveException(pageContext, jspe);
                throw jspe;
            }

            // Get the value for this option
            try {
                beanValue = PropertyUtils.getProperty(bean, value);
                if (beanValue == null) {
                    beanValue = "";
                }
            } catch (IllegalAccessException e) {
                JspException jspe = new JspException(
                        messages.getMessage("getter.access", value, bean));
                RequestUtils.saveException(pageContext, jspe);
                throw jspe;
            } catch (InvocationTargetException e) {
                Throwable t = e.getTargetException();
                JspException jspe = new JspException(
                        messages.getMessage("getter.result", value,
                                t.toString()));
                RequestUtils.saveException(pageContext, jspe);
                throw jspe;
            } catch (NoSuchMethodException e) {
                JspException jspe = new JspException(
                        messages.getMessage("getter.method", value, bean));
                RequestUtils.saveException(pageContext, jspe);
                throw jspe;
            }

            String stringLabel = beanLabel.toString();
            String stringValue = beanValue.toString();

            // Render this option
            addOption(sb, stringLabel, stringValue,
                    selectTag.isMatched(stringValue));
        }

        // Render this element to our writer
        ResponseUtils.write(pageContext, sb.toString());

        return SKIP_BODY;

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        label = null;
        name = null;
        property = null;
        style = null;
        styleClass = null;
        value = null;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Add an option element to the specified StringBuffer based on the
     * specified parameters.
     *
     * @param sb StringBuffer accumulating our results
     * @param value Value to be returned to the server for this option
     * @param label Value to be shown to the user for this option
     * @param matched Should this value be marked as selected?
     */
    protected void addOption(StringBuffer sb, String label, String value,
                             boolean matched) {

        sb.append("<option value=\"");
        sb.append(value);
        sb.append("\"");
        if (matched)
            sb.append(" selected=\"selected\"");
        if (style != null) {
            sb.append(" style=\"");
            sb.append(style);
            sb.append("\"");
        }
        if (styleClass != null) {
            sb.append(" class=\"");
            sb.append(styleClass);
            sb.append("\"");
        }
        sb.append(">");
        sb.append(ResponseUtils.filter(label));
        sb.append("</option>\r\n");

    }


    /**
     * Return an iterator for the option labels or values, based on our
     * configured properties.
     *
     * @param name Name of the bean attribute (if any)
     * @param property Name of the bean property (if any)
     *
     * @exception JspException if an error occurs
     */
    protected Iterator getIterator(Object collection)
            throws JspException {

        if (collection.getClass().isArray())
            collection = Arrays.asList((Object[]) collection);

        if (collection instanceof Collection)
            return (((Collection)collection).iterator());
        else if (collection instanceof Iterator)
            return ((Iterator)collection);
        else if (collection instanceof Map)
            return (((Map)collection).entrySet().iterator());
        else if (collection instanceof Enumeration)
            return(new IteratorAdapter((Enumeration)collection));
        else
            throw new JspException(
                    messages.getMessage("optionsCollectionTag.iterator",
                    collection.toString()));

    }

}
