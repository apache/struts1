/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/MessageTag.java,v 1.7 2000/08/23 01:17:02 craigmcc Exp $
 * $Revision: 1.7 $
 * $Date: 2000/08/23 01:17:02 $
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
import org.apache.struts.util.MessageResources;


/**
 * Custom tag that retrieves an internationalized messages string (with
 * optional parametric replacement) from the <code>ActionResources</code>
 * object stored as a context attribute by our associated
 * <code>ActionServlet</code> implementation.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.7 $ $Date: 2000/08/23 01:17:02 $
 */

public final class MessageTag extends TagSupport {


    // ------------------------------------------------------------ Construtors


    /**
     * Construct a new instance of this tag.
     */
    public MessageTag() {

	super();

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The first optional argument.
     */
    private String arg0 = null;


    /**
     * The second optional argument.
     */
    private String arg1 = null;


    /**
     * The third optional argument.
     */
    private String arg2 = null;


    /**
     * The fourth optional argument.
     */
    private String arg3 = null;


    /**
     * The fifth optional argument.
     */
    private String arg4 = null;


    /**
     * The servlet context attribute key for our resources.
     */
    private String bundle = Action.MESSAGES_KEY;


    /**
     * The default Locale for our server.
     */
    private static final Locale defaultLocale = Locale.getDefault();


    /**
     * The message key of the message to be retrieved.
     */
    private String key = null;


    /**
     * The session scope key under which our Locale is stored.
     */
    private String localeKey = Action.LOCALE_KEY;


    /**
     * The message resources for this package.
     */
    private static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.LocalStrings");


    // ------------------------------------------------------------- Properties


    /**
     * Return the first optional argument.
     */
    public String getArg0() {

	return (this.arg0);

    }


    /**
     * Set the first optional argument.
     *
     * @param arg0 The new optional argument
     */
    public void setArg0(String arg0) {

	this.arg0 = arg0;

    }


    /**
     * Return the second optional argument.
     */
    public String getArg1() {

	return (this.arg1);

    }


    /**
     * Set the second optional argument.
     *
     * @param arg1 The new optional argument
     */
    public void setArg1(String arg1) {

	this.arg1 = arg1;

    }


    /**
     * Return the third optional argument.
     */
    public String getArg2() {

	return (this.arg2);

    }


    /**
     * Set the third optional argument.
     *
     * @param arg2 The new optional argument
     */
    public void setArg2(String arg2) {

	this.arg2 = arg2;

    }


    /**
     * Return the fourth optional argument.
     */
    public String getArg3() {

	return (this.arg3);

    }


    /**
     * Set the fourth optional argument.
     *
     * @param arg3 The new optional argument
     */
    public void setArg3(String arg3) {

	this.arg3 = arg3;

    }


    /**
     * Return the fifth optional argument.
     */
    public String getArg4() {

	return (this.arg4);

    }


    /**
     * Set the fifth optional argument.
     *
     * @param arg4 The new optional argument
     */
    public void setArg4(String arg4) {

	this.arg4 = arg4;

    }


    /**
     * Return the bundle key.
     */
    public String getBundle() {

	return (this.bundle);

    }


    /**
     * Set the bundle key.
     *
     * @param bundle The new bundle key.
     */
    public void setBundle(String bundle) {

	this.bundle = bundle;

    }


    /**
     * Return the message key.
     */
    public String getKey() {

	return (this.key);

    }


    /**
     * Set the message key.
     *
     * @param key The new message key
     */
    public void setKey(String key) {

	this.key = key;

    }


    /**
     * Return the locale key.
     */
    public String getLocale() {

	return (this.localeKey);

    }


    /**
     * Set the locale key.
     *
     * @param locale The new locale key
     */
    public void setLocale(String locale) {

	this.localeKey = locale;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Acquire the resources object containing our messages
	MessageResources resources = (MessageResources)
	    pageContext.getAttribute(bundle, PageContext.APPLICATION_SCOPE);
	if (resources == null)
	    throw new JspException
		(messages.getMessage("messageTag.resources", bundle));

	// Calculate the Locale we will be using
	Locale locale = null;
	try {
	    locale = (Locale)
		pageContext.getAttribute(localeKey, PageContext.SESSION_SCOPE);
	} catch (IllegalStateException e) {	// Invalidated session
	    locale = null;
	}
	if (locale == null)
	    locale = defaultLocale;

	// Construct the optional arguments array we will be using
	Object args[] = new Object[5];
	args[0] = arg0;
	args[1] = arg1;
	args[2] = arg2;
	args[3] = arg3;
	args[4] = arg4;

	// Retrieve the message string we are looking for
	String message = resources.getMessage(locale, key, args);
	if (message == null)
	    throw new JspException
		(messages.getMessage("messageTag.message", key));

	// Print the retrieved message to our output writer
	JspWriter writer = pageContext.getOut();
	try {
	    writer.print(message);
	} catch (IOException e) {
	    throw new JspException
		(messages.getMessage("common.io", e.toString()));
	}

	// Continue processing this page
	return (SKIP_BODY);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
	arg0 = null;
	arg1 = null;
	arg2 = null;
	arg3 = null;
	arg4 = null;
	bundle = Action.MESSAGES_KEY;
	key = null;
	localeKey = Action.LOCALE_KEY;

    }


}
