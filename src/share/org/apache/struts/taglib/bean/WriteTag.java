/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/bean/WriteTag.java,v 1.8 2000/12/26 20:29:45 craigmcc Exp $
 * $Revision: 1.8 $
 * $Date: 2000/12/26 20:29:45 $
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


package org.apache.struts.taglib.bean;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyUtils;


/**
 * Tag that retrieves the specified property of the specified bean, converts
 * it to a String representation (if necessary), and writes it to the current
 * output stream, optionally filtering characters that are sensitive in HTML.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.8 $ $Date: 2000/12/26 20:29:45 $
 */

public class WriteTag extends TagSupport {


    // ------------------------------------------------------------- Properties


    /**
     * Filter the rendered output for characters that are sensitive in HTML?
     */
    protected boolean filter = true;

    public boolean getFilter() {
        return (this.filter);
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }


    /**
     * Should we ignore missing beans and simply output nothing?
     */
    protected boolean ignore = false;

    public boolean getIgnore() {
        return (this.ignore);
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }



    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.bean.LocalStrings");


    /**
     * Name of the bean that contains the data we will be rendering.
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Name of the property to be accessed on the specified bean.
     */
    protected String property = null;

    public String getProperty() {
        return (this.property);
    }

    public void setProperty(String property) {
        this.property = property;
    }


    /**
     * The scope to be searched to retrieve the specified bean.
     */
    protected String scope = null;

    public String getScope() {
        return (this.scope);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }



    // --------------------------------------------------------- Public Methods


    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Retrieve the required property value
        Object bean = null;
        Object value = null;
        try {

            // Locate the specified bean
            try {
                bean = BeanUtils.lookup(pageContext, name, scope);
            } catch (IllegalArgumentException e) {
                pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                         PageContext.REQUEST_SCOPE);
                throw new JspException
                    (messages.getMessage("getter.scope", scope));
            }
	    if ((bean == null) && ignore)
                return (SKIP_BODY);  // Nothing to output
            else if (bean == null) {
                JspException e = new JspException
                    (messages.getMessage("getter.bean", name));
                pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                         PageContext.REQUEST_SCOPE);
                throw e;
            }

            // Locate the specified property
            if (property == null)
                value = bean;
            else
                value = PropertyUtils.getProperty(bean, property);
	    if (value == null)
	        return (SKIP_BODY);  // Nothing to output

	    // Convert the property value to a String if necessary
	    // FIXME - deal with array valued properties
	    // FIXME - use a PropertyEditor as necessary
	    if (!(value instanceof String))
	        value = value.toString();

        } catch (IllegalAccessException e) {
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw new JspException
                (messages.getMessage("getter.access", property, name));
	} catch (IllegalArgumentException e) {
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
	    throw new JspException
	      (messages.getMessage("getter.argument", e.toString()));
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t == null)
                t = e;
            pageContext.setAttribute(Action.EXCEPTION_KEY, t,
                                     PageContext.REQUEST_SCOPE);
            throw new JspException
                (messages.getMessage("getter.invocation",
                                     property, name, t.toString()));
        } catch (NoSuchMethodException e) {
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw new JspException
                (messages.getMessage("getter.method", property, name));
        }


	// Print this property value to our output writer, suitably filtered
	JspWriter writer = pageContext.getOut();
	try {
	    if (filter)
	        writer.print(BeanUtils.filter((String) value));
	    else
	        writer.print((String) value);
	} catch (IOException e) {
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
	    throw new JspException
		(messages.getMessage("getter.io", e.toString()));
	}

	// Continue processing this page
	return (SKIP_BODY);

    }


    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        filter = true;
	ignore = false;
	name = null;
	property = null;
	scope = null;

    }


}
