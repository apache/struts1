/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/ErrorsTag.java,v 1.3 2000/06/14 19:28:55 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2000/06/14 19:28:55 $
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
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Custom tag that renders error messages if an appropriate request attribute
 * has been created.  Correct operation of this tag depends on the application
 * message resources containing messages for the following keys:
 * <ul>
 * <li><b>errors.header</b> - Text inserted before the list of errors
 * <li><b>errors.detail</b> - Text that will be generated for each error message,
 *     with the text of the error passed as argument zero.
 * <li><b>errors.footer</b> - Text inserted after the list of errors
 * <li><b>errors.ioException</b> - Text thrown as a JspException if an IOException
 *     is encountered while rendering the error messages
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2000/06/14 19:28:55 $
 */

public final class ErrorsTag extends TagSupport {


    // --------------------------------------------------- Instance Variables


    /**
     * The default locale on our server.
     */
    private static Locale defaultLocale = Locale.getDefault();


    /**
     * Name of the request scope attribute containing our error messages,
     * if any.
     */
    private String name = Action.ERROR_KEY;


    // ----------------------------------------------------------- Properties


    /**
     * Return the errors attribute name.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the errors attribute name.
     *
     * @param name The new errors attribute name
     */
    public void setName(String name) {

	this.name = name;

    }


    // ------------------------------------------------------- Public Methods


    /**
     * Render the specified error messages if there are any.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Were any error messages specified?
	String errors[] = null;
	try {
	    Object value = pageContext.getAttribute
	      (name, PageContext.REQUEST_SCOPE);
	    if (value == null) {
		errors = null;
	    } else if (value instanceof String) {
		errors = new String[1];
		errors[0] = (String) value;
	    } else if (value instanceof String[]) {
		errors = (String[]) value;
	    } else {
		errors = new String[1];
		errors[0] = value.toString();
	    }
	} catch (Exception e) {
	    errors = null;
	}
	if (errors == null)
	    return (EVAL_BODY_INCLUDE);

	// Render the error messages appropriately
	Locale locale = (Locale) pageContext.getAttribute
	  (Action.LOCALE_KEY, PageContext.SESSION_SCOPE);
	if (locale == null)
	    locale = defaultLocale;
	MessageResources messages = (MessageResources)
	  pageContext.getAttribute(Action.MESSAGES_KEY,
				   PageContext.APPLICATION_SCOPE);
	StringBuffer results = new StringBuffer();
	results.append(messages.getMessage(locale, "errors.header"));
	for (int i = 0; i < errors.length; i++)
	    results.append
	      (messages.getMessage(locale, "errors.detail", errors[i]));
	results.append(messages.getMessage(locale, "errors.footer"));

	// Print the results to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("errors.ioException", e.toString()));
	}

	// Continue processing this page
	return (EVAL_BODY_INCLUDE);

    }



}
