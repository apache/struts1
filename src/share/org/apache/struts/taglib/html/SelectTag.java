/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/SelectTag.java,v 1.3 2001/02/22 02:53:30 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2001/02/22 02:53:30 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;


/**
 * Custom tag that represents an HTML select element, associated with a
 * bean property specified by our attributes.  This tag must be nested
 * inside a form tag.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2001/02/22 02:53:30 $
 */

public class SelectTag extends BaseHandlerTag {


    // ----------------------------------------------------- Instance Variables


    /**
     * The actual values we will match against, calculated in doStartTag().
     */
    protected String match[] = null;


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources(Constants.Package + ".LocalStrings");


    /**
     * Should multiple selections be allowed?  Any non-null value will
     * trigger rendering this.
     */
    protected String multiple = null;

    public String getMultiple() {
	return (this.multiple);
    }

    public void setMultiple(String multiple) {
	this.multiple = multiple;
    }


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
     * The property name we are associated with.
     */
    protected String property = null;


    /**
     * The saved body content of this tag.
     */
    protected String saveBody = null;


    /**
     * How many available options should be displayed when this element
     * is rendered?
     */
    protected String size = null;

    public String getSize() {
	return (this.size);
    }

    public void setSize(String size) {
	this.size = size;
    }


    /**
     * The value to compare with for marking an option selected.
     */
    protected String value = null;


    // ------------------------------------------------------------- Properties


    /**
     * Does the specified value match one of those we are looking for?
     *
     * @param value Value to be compared
     */
    public boolean isMatched(String value) {

        if ((match == null) || (value == null))
            return (false);
        for (int i = 0; i < match.length; i++) {
            if (value.equals(match[i]))
                return (true);
        }
        return (false);

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
	if (accesskey != null) {
	    results.append(" accesskey=\"");
	    results.append(accesskey);
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
	if (tabindex != null) {
	    results.append(" tabindex=\"");
	    results.append(tabindex);
	    results.append("\"");
	}
	results.append(prepareEventHandlers());
	results.append(prepareStyles());
	results.append(">");

	// Print this field to our output writer
        ResponseUtils.write(pageContext, results.toString());

	// Store this tag itself as a page attribute
	pageContext.setAttribute(Constants.SELECT_KEY, this);

	// Calculate the match values we will actually be using
	if (value != null) {
	    match = new String[1];
            match[0] = value;
        } else {
	    Object bean = pageContext.findAttribute(name);
	    if (bean == null) {
                JspException e = new JspException                    
		    (messages.getMessage("getter.bean", name));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }
	    try {
		match = BeanUtils.getArrayProperty(bean, property);
		if (match == null)
		    match = new String[0];
	    } catch (IllegalAccessException e) {
                RequestUtils.saveException(pageContext, e);
                throw new JspException
		    (messages.getMessage("getter.access", property, name));
	    } catch (InvocationTargetException e) {
		Throwable t = e.getTargetException();
                RequestUtils.saveException(pageContext, t);
		throw new JspException
		    (messages.getMessage("getter.result",
					 property, t.toString()));
	    } catch (NoSuchMethodException e) {
                RequestUtils.saveException(pageContext, e);
                throw new JspException
		    (messages.getMessage("getter.method", property, name));
	    }
	}

	// Continue processing this page
	return (EVAL_BODY_TAG);

    }



    /**
     * Save any body content of this tag, which will generally be the
     * option(s) representing the values displayed to the user.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {

        if (bodyContent != null) {
            String value = bodyContent.getString();
            if (value == null)
                value = "";
            saveBody = value.trim();
        }
        return (SKIP_BODY);

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
	if (saveBody != null)
	    results.append(saveBody);
	results.append("</select>");

	// Print this value to our output writer
        ResponseUtils.write(pageContext, results.toString());

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
        saveBody = null;
	size = null;
	value = null;

    }


}
