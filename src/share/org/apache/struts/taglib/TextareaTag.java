/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/TextareaTag.java,v 1.2 2000/06/11 22:18:56 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2000/06/11 22:18:56 $
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
 * Custom tag for input fields of type "textarea".
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2000/06/11 22:18:56 $
 */

public class TextareaTag extends BaseInputTag {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this tag.
     */
    public TextareaTag() {

	super();

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Create an appropriate "input" element based on our parameters
	StringBuffer results = new StringBuffer("<textarea");
	results.append(" name=\"");
	results.append(name);
	results.append("\"");
	if (cols >= 0) {
	    results.append(" cols=\"");
	    results.append(cols);
	    results.append("\"");
	}
	if (rows >= 0) {
	    results.append(" rows=\"");
	    results.append(rows);
	    results.append("\"");
	}
	results.append(">\r\n");
	if (value != null) {
	    results.append(BeanUtils.filter(value));
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
		value = method.invoke(bean, new Object[0]);
		if (value == null)
		    value = "";
	    } catch (NoSuchMethodException e) {
		throw new JspException
		    (messages.getMessage("baseFieldTag.method", methodName));
	    } catch (Exception e) {
		throw new JspException
		    (messages.getMessage("baseFieldTag.result",
					 methodName, e.toString()));
	    }
	}
	results.append("\r\n</textarea>");

	// Print this field to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("baseFieldTag.io", e.toString()));
	}

	// Continue processing this page
	return (EVAL_BODY_INCLUDE);

    }



}
