/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/IterateTag.java,v 1.9 2000/08/14 04:42:51 craigmcc Exp $
 * $Revision: 1.9 $
 * $Date: 2000/08/14 04:42:51 $
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


import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;


/**
 * Custom tag that iterates the elements of a collection, which can be
 * either an attribute or the property of an attribute.  The collection
 * can be any of the following:  an array of objects, an Iterator,
 * a Collection (which includes Lists, Sets and Vectors), or a Map
 * (which includes Hashtables) whose elements will be iterated over.
 * <p>
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.9 $ $Date: 2000/08/14 04:42:51 $
 */

public final class IterateTag extends BodyTagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The collection over which we will be iterating.
     */
    private Object collection = null;


    /**
     * The name of the scripting variable to be exposed.
     */
    private String id = null;


    /**
     * Iterator of the elements of this collection.
     */
    private Iterator iterator = null;


    /**
     * The length value or attribute name (<=0 means no limit).
     */
    private String length = "0";


    /**
     * The number of elements we have already rendered.
     */
    private int lengthCount = 0;


    /**
     * The actual length value (calculated in the start tag).
     */
    private int lengthValue = 0;


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
     * The starting offset (zero relative).
     */
    private String offset = "0";


    /**
     * The actual offset value (calculated in the start tag).
     */
    private int offsetValue = 0;


    /**
     * The property name containing the collection.
     */
    private String property = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the collection over which we will be iterating.
     */
    public Object getCollection() {

	return (this.collection);

    }


    /**
     * Set the collection over which we will be iterating.
     *
     * @param collection The new collection
     */
    public void setCollection(Object collection) {

	this.collection = collection;

    }


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
     * Return the length.
     */
    public String getLength() {

	return (this.length);

    }


    /**
     * Set the length.
     *
     * @param length The new length
     */
    public void setLength(String length) {

	this.length = length;

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
     * Return the offset.
     */
    public String getOffset() {

	return (this.offset);

    }


    /**
     * Set the offset.
     *
     * @param offset The new offset
     */
    public void setOffset(String offset) {

	this.offset = offset;

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
     * Construct an iterator for the specified collection, and begin
     * looping through the body once per element.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Acquire the collection we are going to iterate over (if necessary)
	if (collection == null) {
	    try {
		Object bean = pageContext.findAttribute(name);
		if (bean == null)
		    throw new JspException
			(messages.getMessage("getter.bean", name));
		if (property == null)
		    collection = bean;
		else
		    collection = BeanUtils.getPropertyValue(bean, property);
		if (collection == null)
		    throw new JspException
			(messages.getMessage("getter.property", property));
	    } catch (IllegalAccessException e) {
		throw new JspException
		    (messages.getMessage("getter.access", property, name));
	    } catch (InvocationTargetException e) {
		Throwable t = e.getTargetException();
		throw new JspException
		    (messages.getMessage("getter.result",
					 property, t.toString()));
	    } catch (NoSuchMethodException e) {
		throw new JspException
		    (messages.getMessage("getter.method", property, name));
	    }
	}

	// Construct an iterator for this collection
	if (collection.getClass().isArray())
	    collection = Arrays.asList((Object[]) collection);
	if (collection instanceof Collection)
	    iterator = ((Collection) collection).iterator();
	else if (collection instanceof Iterator)
	    iterator = (Iterator) collection;
	else if (collection instanceof Map)
	    iterator = ((Map) collection).entrySet().iterator();
	else
	    throw new JspException
	        (messages.getMessage("iterateTag.iterator",
	                             collection.toString()));

	// Calculate the starting offset
	if (offset == null)
	    offsetValue = 0;
	else {
	    try {
		offsetValue = Integer.parseInt(offset);
	    } catch (NumberFormatException e) {
		Integer offsetObject =
		  (Integer) pageContext.findAttribute(offset);
		if (offsetObject == null)
		    offsetValue = 0;
		else
		    offsetValue = offsetObject.intValue();
	    }
	}
	if (offsetValue < 0)
	    offsetValue = 0;

	// Calculate the rendering length
	if (length == null)
	    lengthValue = 0;
	else {
	    try {
		lengthValue = Integer.parseInt(length);
	    } catch (NumberFormatException e) {
		Integer lengthObject =
		  (Integer) pageContext.findAttribute(length);
		if (lengthObject == null)
		    lengthValue = 0;
		else
		    lengthValue = lengthObject.intValue();
	    }
	}
	if (lengthValue < 0)
	    lengthValue = 0;
	lengthCount = 0;

	// Skip the leading elements up to the starting offset
	for (int i = 0; i < offsetValue; i++) {
	    if (iterator.hasNext()) {
	        Object element = iterator.next();
	    }
	}

	// Store the first value and evaluate, or skip the body if none
	if (iterator.hasNext()) {
	    Object element = iterator.next();
	    pageContext.setAttribute(id, element);
	    lengthCount++;
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

	if ((lengthValue > 0) && (lengthCount >= lengthValue))
	    return (SKIP_BODY);

	if (iterator.hasNext()) {
	    Object element = iterator.next();
	    pageContext.setAttribute(id, element);
	    lengthCount++;
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
		    (messages.getMessage("common.io", e.toString()));
	    }
	}

	// Continue processing this page
	return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
	collection = null;
	id = null;
	iterator = null;
	length = "0";
	lengthCount = 0;
	lengthValue = 0;
	name = null;
	offset = "0";
	offsetValue = 0;
	property = null;

    }


}
