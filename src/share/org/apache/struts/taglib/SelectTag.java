/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/SelectTag.java,v 1.1 2000/05/31 22:28:12 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2000/05/31 22:28:12 $
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
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Custom tag that represents an HTML select element, associated with a
 * bean property specified by our attributes.  This tag must be nested
 * inside a form tag.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2000/05/31 22:28:12 $
 */

public final class SelectTag extends TagSupport {


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
     * The property name we are associated with.
     */
    private String name = null;


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
    public String getName() {

	return (this.name);

    }


    /**
     * Set the property name.
     *
     * @param name The new attribute key name
     */
    public void setName(String name) {

	this.name = name;

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
	results.append(name);
	results.append("\"");
	results.append(">");

	// Print this field to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.println(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("formTag.io", e.toString()));
	}

	// Store this tag itself as a page attribute
	pageContext.setAttribute(Constants.SELECT_KEY, this);

	// Calculate the match value we will actually be using
	if (value != null) {
	    match = value;
        } else {
	    Object bean = pageContext.findAttribute(Constants.BEAN_KEY);
	    if (bean == null)
		throw new JspException
		    (messages.getMessage("baseFieldTag.missing", name));
	    String methodName = "get" + BeanUtils.capitalize(name);
	    Class paramTypes[] = new Class[0];
	    Method method = null;
	    Object value = null;
	    try {
		method = bean.getClass().getMethod(methodName, paramTypes);
		match = method.invoke(bean, new Object[0]).toString();
		if (match == null)
		    match = "";
	    } catch (NoSuchMethodException e) {
		throw new JspException
		    (messages.getMessage("baseFieldTag.method", methodName));
	    } catch (Exception e) {
		throw new JspException
		    (messages.getMessage("baseFieldTag.result",
					 methodName, e.toString()));
	    }
	}

	// Continue processing this page
	return (EVAL_BODY_INCLUDE);

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
	StringBuffer results = new StringBuffer("</select>");

	// Print this value to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.println(results.toString());
	} catch (IOException e) {
	    throw new JspException
	        (messages.getMessage("formTag.io", e.toString()));
	}

	// Continue processing this page
	return (EVAL_PAGE);

    }


}
