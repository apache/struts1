/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/SelectTag.java,v 1.12 2001/05/20 01:19:06 craigmcc Exp $
 * $Revision: 1.12 $
 * $Date: 2001/05/20 01:19:06 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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


package org.apache.struts.taglib;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Custom tag that represents an HTML select element, associated with a
 * bean property specified by our attributes.  This tag must be nested
 * inside a form tag.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.12 $ $Date: 2001/05/20 01:19:06 $
 */

public final class SelectTag extends BaseHandlerTag {


    // ----------------------------------------------------- Instance Variables


    /**
     * The actual value we will match against, calculated in doStartTag().
     */
    private String match = null;


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.LocalStrings");


    /**
     * Should multiple selections be allowed?  Any non-null value will
     * trigger rendering this.
     */
    private String multiple = null;

    public String getMultiple() {
	return (this.multiple);
    }

    public void setMultiple(String multiple) {
	this.multiple = multiple;
    }


    /**
     * The name of the bean containing our underlying property.
     */
    private String name = Constants.BEAN_KEY;

    public String getName() {
	return (this.name);
    }

    public void setName(String name) {
	this.name = name;
    }


    /**
     * The property name we are associated with.
     */
    private String property = null;


    /**
     * How many available options should be displayed when this element
     * is rendered?
     */
    private String size = null;

    public String getSize() {
	return (this.size);
    }

    public void setSize(String size) {
	this.size = size;
    }


    /**
     * The value to compare with for marking an option selected.
     */
    private String value = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the actual match value (only valid from nested tags).
     */
    public String getMatch() {

	return (this.match);

    }


    /**
     * Return the property name.
     */
    public String getProperty() {

	return (this.property);

    }


    /**
     * Set the property name.
     *
     * @param property The new property name
     */
    public void setProperty(String property) {

	this.property = property;

    }


    /**
     * Return the comparison value.
     */
    public String getValue() {

	return (this.value);

    }


    /**
     * Set the comparison value.
     *
     * @param value The new comparison value
     */
    public void setValue(String value) {

	this.value = value;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Render the beginning of this form.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Create an appropriate "form" element based on our parameters
	StringBuffer results = new StringBuffer("<select");
	results.append(" name=\"");
	results.append(property);
	results.append("\"");
	if (accessKey != null) {
	    results.append(" accesskey=\"");
	    results.append(accessKey);
	    results.append("\"");
	}
	if (multiple != null) {
	    results.append(" multiple");
	}
	if (size != null) {
	    results.append(" size=\"");
	    results.append(size);
	    results.append("\"");
	}
	if (tabIndex != null) {
	    results.append(" tabindex=\"");
	    results.append(tabIndex);
	    results.append("\"");
	}
	results.append(prepareEventHandlers());
	results.append(prepareStyles());
	results.append(">");

	// Print this field to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.println(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("common.io", e.toString()));
	}

	// Store this tag itself as a page attribute
	pageContext.setAttribute(Constants.SELECT_KEY, this);

	// Calculate the match value we will actually be using
	if (value != null) {
	    match = value;
        } else {
	    Object bean = pageContext.findAttribute(name);
	    if (bean == null)
		throw new JspException
		    (messages.getMessage("getter.bean", name));
	    try {
		match = BeanUtils.getSimpleProperty(bean, property);
		if (match == null)
		    match = "";
	    } catch (IllegalAccessException e) {
		throw new JspException
		    (messages.getMessage("getter.access", property, name));
	    } catch (InvocationTargetException e) {
		Throwable t = e.getTargetException();
		throw new JspException
		    (messages.getMessage("getter.result",
					 property, t.toString()));
	    } catch (NoSuchMethodException e) {
		throw new JspException
		    (messages.getMessage("getter.method", property, name));
	    }
	}

	// Continue processing this page
	return (EVAL_BODY_TAG);

    }



    /**
     * Render the end of this form.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

	// Remove the page scope attributes we created
	pageContext.removeAttribute(Constants.SELECT_KEY);

	// Render a tag representing the end of our current form
	StringBuffer results = new StringBuffer();
	if (bodyContent != null)
	    results.append(bodyContent.getString());
	results.append("</select>");

	// Print this value to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.println(results.toString());
	} catch (IOException e) {
	    throw new JspException
	        (messages.getMessage("common.io", e.toString()));
	}

	// Continue processing this page
	return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
	match = null;
	multiple = null;
	name = Constants.BEAN_KEY;
	property = null;
	size = null;
	value = null;

    }


}
