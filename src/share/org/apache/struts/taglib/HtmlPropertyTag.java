/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/HtmlPropertyTag.java,v 1.1 2000/05/31 22:28:11 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2000/05/31 22:28:11 $
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
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Custom tag implementation that acts like
 * <code>&lt;jsp:getProperty&gt;</code> but encodes the output stream so that
 * HTML-related characters do not cause difficulties.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2000/05/31 22:28:11 $
 */

public final class HtmlPropertyTag extends TagSupport {


    // ------------------------------------------------------------ Construtors


    /**
     * Construct a new instance of this tag.
     */
    public HtmlPropertyTag() {

	super();

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The name of the object instance from which the property is obtained.
     */
    private String name = null;


    /**
     * The message resources for this package.
     */
    private static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.LocalStrings");


    /**
     * The name of the property to get.
     */
    private String property = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the object name.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the object name.
     *
     * @param name The new object name
     */
    public void setName(String name) {

	this.name = name;

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


    // --------------------------------------------------------- Public Methods


    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Acquire the bean for which we are retrieving the property
	Object bean = pageContext.findAttribute(name);
	if (bean == null)
	    throw new JspException
		(messages.getMessage("htmlPropertyTag.bean", name));

	// Acquire the requested property value
	String methodName = "get" + BeanUtils.capitalize(property);
	Class paramTypes[] = new Class[0];
	Method method = null;
	Object value = null;
	try {
	    method = bean.getClass().getMethod(methodName, paramTypes);
	    value = method.invoke(bean, new Object[0]);
	    if (value == null)
		value = "";
	} catch (NoSuchMethodException e) {
	    throw new JspException
		(messages.getMessage("htmlPropertyTag.method", methodName));
	} catch (Exception e) {
	    throw new JspException
		(messages.getMessage("htmlPropertyTag.result",
				     methodName, e.toString()));
	}

	// Print this property value to our output writer, suitably encoded
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(BeanUtils.filter(value.toString()));
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("htmlPropertyTag.io", e.toString()));
	}

	// Continue processing this page
	return (EVAL_BODY_INCLUDE);

    }


}
