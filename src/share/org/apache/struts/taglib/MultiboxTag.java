/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/MultiboxTag.java,v 1.3 2000/07/17 15:16:22 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2000/07/17 15:16:22 $
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
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspWriter;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Tag for input fields of type "checkbox".  This differs from CheckboxTag
 * because it assumes that the underlying property is an array getter (of any
 * supported primitive type, or String), and the checkbox is initialized to
 * "checked" if the value listed for the "value" attribute is present in the
 * values returned by the property getter.
 *
 * @author Ralph Schaer
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2000/07/17 15:16:22 $
 */

public final class MultiboxTag extends BaseHandlerTag {


    // ----------------------------------------------------- Instance Variables


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.LocalStrings");


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
     * The property name for this field.
     */
    private String property = null;


    /**
     * The value which will mark this checkbox as "checked" if present
     * in the array returned by our property getter.
     */
    private String value = null;


    // ------------------------------------------------------------- Properties


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
     * Return the server value.
     */
    public String getValue() {

	return (this.value);

    }


    /**
     * Set the server value.
     *
     * @param value The new server value
     */
    public void setValue(String value) {

	this.value = value;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Create an appropriate "input" element based on our parameters
	StringBuffer results = new StringBuffer("<input type=\"checkbox\"");
	results.append(" name=\"");
	results.append(this.property);
	results.append("\"");
	if (accessKey != null) {
	    results.append(" accesskey=\"");
	    results.append(accessKey);
	    results.append("\"");
	}
	if (tabIndex != null) {
	    results.append(" tabindex=\"");
	    results.append(tabIndex);
	    results.append("\"");
	}
	results.append(" value=\"");
	results.append(this.value);
	results.append("\"");
	Object bean = pageContext.findAttribute(name);
	String values[] = null;
	if (bean == null)
	    throw new JspException
		(messages.getMessage("getter.bean", name));
	try {
	    values = BeanUtils.getArrayProperty(bean, property);
	    if (values == null)
		values = new String[0];
	} catch (NoSuchMethodException e) {
	    throw new JspException
		(messages.getMessage("getter.method", property));
	} catch (Exception e) {
	    throw new JspException
		(messages.getMessage("getter.result",
				     property, e.toString()));
	}
	for (int i = 0; i < values.length; i++) {
	    if (value.equals(values[i])) {
		results.append(" checked");
		break;
	    }
	}
	results.append(prepareEventHandlers());
	results.append(prepareStyles());
	results.append(">");

	// Print this field to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("common.io", e.toString()));
	}

	// Continue processing this page
	return (EVAL_BODY_TAG);

    }



    /**
     * Optionally render the associated label from the body content.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

	if (bodyContent == null)
	    return (EVAL_PAGE);

	JspWriter writer = pageContext.getOut();
	try {
	    writer.println(bodyContent.getString().trim());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("common.io", e.toString()));
	}

	// Continue evaluating this page
	return (EVAL_PAGE);

    }


}
