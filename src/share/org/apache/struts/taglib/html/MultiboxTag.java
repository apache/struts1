/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/MultiboxTag.java,v 1.3 2001/02/10 23:31:50 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2001/02/10 23:31:50 $
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


package org.apache.struts.taglib.html;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspWriter;
import org.apache.struts.action.Action;
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
 * @version $Revision: 1.3 $ $Date: 2001/02/10 23:31:50 $
 */

public class MultiboxTag extends BaseHandlerTag {


    // ----------------------------------------------------- Instance Variables


    /**
     * The constant String value to be returned when this checkbox is
     * selected and the form is submitted.
     */
    protected String constant = null;


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources(Constants.Package + ".LocalStrings");


    /**
     * The name of the bean containing our underlying property.
     */
    protected String name = Constants.BEAN_KEY;

    public String getName() {
	return (this.name);
    }

    public void setName(String name) {
	this.name = name;
    }


    /**
     * The property name for this field.
     */
    protected String property = null;


    /**
     * The value which will mark this checkbox as "checked" if present
     * in the array returned by our property getter.
     */
    protected String value = null;


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
     * Process the beginning of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Defer processing until the end of this tag is encountered
	return (EVAL_BODY_TAG);

    }



    /**
     * Save the body contents of this tag as the constant that we will
     * be returning.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {

        if (bodyContent != null)
            this.constant = bodyContent.getString().trim();
        if ("".equals(this.constant))
            this.constant = null;
        return (SKIP_PAGE);

    }


    /**
     * Render an input element for this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

	// Create an appropriate "input" element based on our parameters
	StringBuffer results = new StringBuffer("<input type=\"checkbox\"");
	results.append(" name=\"");
	results.append(this.property);
	results.append("\"");
	if (accesskey != null) {
	    results.append(" accesskey=\"");
	    results.append(accesskey);
	    results.append("\"");
	}
	if (tabindex != null) {
	    results.append(" tabindex=\"");
	    results.append(tabindex);
	    results.append("\"");
	}
	results.append(" value=\"");
        String value = this.value;
        if (value == null)
            value = this.constant;
        if (value == null) {
            JspException e = new JspException
                (messages.getMessage("multiboxTag.value"));
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw e;
        }
        results.append(BeanUtils.filter(value));
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
	for (int i = 0; i < values.length; i++) {
	    if (value.equals(values[i])) {
		results.append(" checked");
		break;
	    }
	}
	results.append(prepareEventHandlers());
	results.append(prepareStyles());
	results.append(">");

        // Render this element to our response
	JspWriter writer = pageContext.getOut();
	try {
	    writer.println(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("common.io", e.toString()));
	}

	// Continue evaluating this page
	return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
        constant = null;
	name = Constants.BEAN_KEY;
	property = null;
	value = null;

    }


}
