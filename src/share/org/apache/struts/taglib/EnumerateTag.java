/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/EnumerateTag.java,v 1.1 2000/05/31 22:28:12 craigmcc Exp $
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


import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Custom tag that enumerates the elements of a collection, which can be
 * either an attribute or the property of an attribute.  The collection
 * can be an Enumeration, a Vector, or an array of objects.
 * <p>
 * <b>FIXME</b> - Should support Java2 collection classes as well!
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2000/05/31 22:28:12 $
 */

public final class EnumerateTag extends BodyTagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * Enumeration of the elements of this collection.
     */
    private Enumeration enumeration = null;


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
     * The name of the collection or owning bean.
     */
    private String name = null;


    /**
     * The property name containing the collection.
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
     * Return the name of the collection or owning bean.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the name of the collection or owning bean.
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
     * Construct an enumeration for the specified collection, and begin
     * looping through the body once per element.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Acquire the collection we are going to enumerate
	Object collection = null;
	try {
	    Object bean = pageContext.findAttribute(name);
	    if (bean == null)
	        throw new JspException
	            (messages.getMessage("enumerate.noBean", name));
	    if (property == null)
	        collection = bean;
	    else {
		String methodName = "get" + BeanUtils.capitalize(property);
		Class paramTypes[] = new Class[0];
		Method method =
		  bean.getClass().getMethod(methodName, paramTypes);
		collection = method.invoke(bean, new Object[0]);
	    }
	    if (collection == null)
	        throw new JspException
	            (messages.getMessage("enumerate.noProperty",
	                                 name, property));
	} catch (Exception e) {
	    throw new JspException
	        (messages.getMessage("enumerate.noProperty", name, property));
	}

	// Construct an enumeration for this collection
	if (collection.getClass().isArray()) {
	    Object array[] = (Object[]) collection;
	    Vector vector = new Vector(array.length);
	    for (int i = 0; i < array.length; i++)
	        vector.addElement(array[i]);
	    collection = vector;
	}
	if (collection instanceof Enumeration)
	    enumeration = (Enumeration) collection;
	else if (collection instanceof Hashtable)
	    enumeration = ((Hashtable) collection).elements();
	else if (collection instanceof Vector)
	    enumeration = ((Vector) collection).elements();
	else
	    throw new JspException
	        (messages.getMessage("enumerate.noCollection",
	                             collection.toString()));

	// Store the first value and evaluate, or skip the body if none
	if (enumeration.hasMoreElements()) {
	    Object element = enumeration.nextElement();
	    pageContext.setAttribute(id, element);
	    return (EVAL_BODY_TAG);
        } else
            return (SKIP_BODY);

    }


    /**
     * Make the next collection element available and loop, or
     * finish the iterations if there are no more elements.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {

	if (enumeration.hasMoreElements()) {
	    Object element = enumeration.nextElement();
	    pageContext.setAttribute(id, element);
	    return (EVAL_BODY_TAG);
	} else
	    return (SKIP_BODY);

    }


    /**
     * Clean up after processing this enumeration.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

	// Render any previously accumulated body content
	if (bodyContent != null) {
	    try {
		JspWriter out = getPreviousOut();
		out.print(bodyContent.getString());
	    } catch (IOException e) {
		throw new JspException
		    (messages.getMessage("enumerate.io", e.toString()));
	    }
	}

	// Continue processing this page
	return (EVAL_PAGE);

    }


}
