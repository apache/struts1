/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/GetPropertyTag.java,v 1.2 2000/07/16 22:29:05 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2000/07/16 22:29:05 $
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
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Expose the value of a bean property as a scripting variable.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2000/07/16 22:29:05 $
 */

public class GetPropertyTag extends TagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The name of the scripting variable to be exposed.
     */
    private String id = null;


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.LocalStrings");


    /**
     * The name of the bean owning the property.
     */
    private String name = null;


    /**
     * The property name to be exposed.
     */
    private String property = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the name of the scripting variable.
     */
    public String getId() {

	return (this.id);

    }


    /**
     * Set the name of the scripting variable.
     *
     * @param id The new name of the scripting variable
     */
    public void setId(String id) {

	this.id = id;

    }


    /**
     * Return the name of the bean.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the name of the bean.
     *
     * @param name The new name
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
     * @param property The property name
     */
    public void setProperty(String property) {

	this.property = property;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Retrieve the required property and expose it as a scripting variable.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Retrieve the required property
	Object value = null;
	try {
	    Object bean = pageContext.findAttribute(name);
	    if (bean == null)
	        throw new JspException
	            (messages.getMessage("getter.bean", name));
	    value = BeanUtils.getPropertyValue(bean, property);
	    if (value == null)
		throw new JspException
		    (messages.getMessage("getter.property", property));
	} catch (NoSuchMethodException e) {
	    throw new JspException
		(messages.getMessage("getter.method", property));
	} catch (Exception e) {
	    throw new JspException
	        (messages.getMessage("getter.result", property));
	}

	// Expose this as a scripting variable and continue
	pageContext.setAttribute(id, value);
	return (EVAL_BODY_INCLUDE);

    }


}
