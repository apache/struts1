/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/ErrorsTag.java,v 1.3 2001/01/27 20:09:43 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2001/01/27 20:09:43 $
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
import java.util.Iterator;
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.ErrorMessages;
import org.apache.struts.util.MessageResources;


/**
 * Custom tag that renders error messages if an appropriate request attribute
 * has been created.  The tag looks for a request attribute with a reserved
 * key, and assumes that it is either a String, a String array, containing
 * message keys to be looked up in the application's MessageResources, or
 * an object of type <code>org.apache.struts.action.ActionErrors</code>.
 * <p>
 * The following optional message keys will be utilized if corresponding
 * messages exist for them in the application resources:
 * <ul>
 * <li><b>errors.header</b> - If present, the corresponding message will be
 *     rendered prior to the individual list of error messages.
 * <li><b>errors.footer</b> - If present, the corresponding message will be
 *     rendered following the individual list of error messages.
 * <li><b>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2001/01/27 20:09:43 $
 */

public class ErrorsTag extends TagSupport {


    // ----------------------------------------------------------- Properties


    /**
     * The default locale on our server.
     */
    protected static Locale defaultLocale = Locale.getDefault();


    /**
     * Name of the request scope attribute containing our error messages,
     * if any.
     */
    protected String name = Action.ERROR_KEY;

    public String getName() {
	return (this.name);
    }

    public void setName(String name) {
	this.name = name;
    }


    /**
     * The name of the property for which error messages should be returned,
     * or <code>null</code> to return all errors.
     */
    protected String property = null;

    public String getProperty() {
        return (this.property);
    }

    public void setProperty(String property) {
        this.property = property;
    }



    // ------------------------------------------------------- Public Methods


    /**
     * Render the specified error messages if there are any.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Were any error messages specified?
	ActionErrors errors = new ActionErrors();
	try {
	    Object value = pageContext.getAttribute
                (name, PageContext.REQUEST_SCOPE);
	    if (value == null) {
		;
	    } else if (value instanceof String) {
		errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError((String) value));
	    } else if (value instanceof String[]) {
                String keys[] = (String[]) value;
                for (int i = 0; i < keys.length; i++)
                    errors.add(ActionErrors.GLOBAL_ERROR,
                               new ActionError(keys[i]));
            } else if (value instanceof ErrorMessages) {
		String keys[] = ((ErrorMessages) value).getErrors();
                if (keys == null)
                    keys = new String[0];
                for (int i = 0; i < keys.length; i++)
                    errors.add(ActionErrors.GLOBAL_ERROR,
                               new ActionError(keys[i]));
            } else if (value instanceof ActionErrors) {
                errors = (ActionErrors) value;
	    }
        } catch (Exception e) {
            ;
	}
        if (errors.empty())
	    return (EVAL_BODY_INCLUDE);

	// Render the error messages appropriately
	Locale locale = null;
	try {
	    locale = (Locale) pageContext.getAttribute
		(Action.LOCALE_KEY, PageContext.SESSION_SCOPE);
	} catch (IllegalStateException e) {	// Invalidated session
	    locale = null;
	}
	if (locale == null)
	    locale = defaultLocale;
	MessageResources messages = (MessageResources)
	  pageContext.getAttribute(Action.MESSAGES_KEY,
				   PageContext.APPLICATION_SCOPE);
	String message = null;
	StringBuffer results = new StringBuffer();
        if (messages != null)
            message = messages.getMessage(locale, "errors.header");
        else
            message = "MISSING APPLICATION RESOURCES";
	if (message != null) {
	    results.append(message);
	    results.append("\r\n");
	}
        Iterator reports = null;
        if (property == null)
            reports = errors.get();
        else
            reports = errors.get(property);
        while (reports.hasNext()) {
            ActionError report = (ActionError) reports.next();
            if (messages != null)
                message =
                    messages.getMessage(locale,
                                        report.getKey(), report.getValues());
            else
                message = null;
	    if (message != null) {
		results.append(message);
		results.append("\r\n");
	    }
	}
        if (messages != null)
            message = messages.getMessage(locale, "errors.footer");
        else
            message = null;
	if (message != null) {
	    results.append(message);
	    results.append("\r\n");
	}

	// Print the results to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(results.toString());
	} catch (IOException e) {
	    throw new JspException(e.toString());
	}

	// Continue processing this page
	return (EVAL_BODY_INCLUDE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
	name = Action.ERROR_KEY;
        property = null;

    }


}
