/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/logic/IterateTag.java,v 1.7 2001/02/12 21:49:56 craigmcc Exp $
 * $Revision: 1.7 $
 * $Date: 2001/02/12 21:49:56 $
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


package org.apache.struts.taglib.logic;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyUtils;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;


/**
 * Custom tag that iterates the elements of a collection, which can be
 * either an attribute or the property of an attribute.  The collection
 * can be any of the following:  an array of objects, an Iterator,
 * a Collection (which includes Lists, Sets and Vectors), or a Map
 * (which includes Hashtables) whose elements will be iterated over.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.7 $ $Date: 2001/02/12 21:49:56 $
 */

public class IterateTag extends BodyTagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * Iterator of the elements of this collection, while we are actually
     * running.
     */
    protected Iterator iterator = null;


    /**
     * The number of elements we have already rendered.
     */
    protected int lengthCount = 0;


    /**
     * The actual length value (calculated in the start tag).
     */
    protected int lengthValue = 0;


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.logic.LocalStrings");



    /**
     * The actual offset value (calculated in the start tag).
     */
    protected int offsetValue = 0;


    // ------------------------------------------------------------- Properties


    /**
     * The collection over which we will be iterating.
     */
    protected Object collection = null;

    public Object getCollection() {
	return (this.collection);
    }

    public void setCollection(Object collection) {
	this.collection = collection;
    }


    /**
     * The name of the scripting variable to be exposed.
     */
    protected String id = null;

    public String getId() {
	return (this.id);
    }

    public void setId(String id) {
	this.id = id;
    }


    /**
     * The length value or attribute name (<=0 means no limit).
     */
    protected String length = null;

    public String getLength() {
	return (this.length);
    }

    public void setLength(String length) {
	this.length = length;
    }


    /**
     * The name of the collection or owning bean.
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
	this.name = name;
    }


    /**
     * The starting offset (zero relative).
     */
    protected String offset = null;

    public String getOffset() {
	return (this.offset);
    }

    public void setOffset(String offset) {
	this.offset = offset;
    }


    /**
     * The property name containing the collection.
     */
    protected String property = null;

    public String getProperty() {
	return (this.property);
    }

    public void setProperty(String property) {
	this.property = property;
    }


    /**
     * The scope of the bean specified by the name property, if any.
     */
    protected String scope = null;

    public String getScope() {
        return (this.scope);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


    /**
     * The Java class of each exposed element of the collection.
     */
    protected String type = null;

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Construct an iterator for the specified collection, and begin
     * looping through the body once per element.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Acquire the collection we are going to iterate over
        Object collection = this.collection;
	if (collection == null)
            collection =
                RequestUtils.lookup(pageContext, name, property, scope);
        if (collection == null) {
            JspException e = new JspException
                (messages.getMessage("iterate.collection"));
            RequestUtils.saveException(pageContext, e);
            throw e;
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
	else {
	    JspException e = new JspException
	        (messages.getMessage("iterate.iterator"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

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

        // Render the output from this iteration to the output stream
        if (bodyContent != null) {
            ResponseUtils.writePrevious(pageContext, bodyContent.getString());
            bodyContent.clearBody();
        }

        // Decide whether to iterate or quit
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

	// Continue processing this page
	return (EVAL_PAGE);

    }


    /**
     * Release all allocated resources.
     */
    public void release() {

	super.release();

	iterator = null;
	lengthCount = 0;
	lengthValue = 0;
	offsetValue = 0;

        id = null;
        collection = null;
        length = null;
        name = null;
        offset = null;
        property = null;
        scope = null;

    }


}
