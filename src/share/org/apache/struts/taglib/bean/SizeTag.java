/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/bean/SizeTag.java,v 1.1 2001/02/13 01:56:07 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2001/02/13 01:56:07 $
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


package org.apache.struts.taglib.bean;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyUtils;
import org.apache.struts.util.RequestUtils;


/**
 * Define a scripting variable that will contain the number of elements
 * found in a specified array, Collection, or Map.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2001/02/13 01:56:07 $
 */

public class SizeTag extends TagSupport {


    // ------------------------------------------------------------- Properties


    /**
     * The actual collection to be counted.
     */
    protected Object collection = null;

    public Object getCollection() {
        return (this.collection);
    }

    public void setCollection(Object collection) {
        this.collection = collection;
    }


    /**
     * The name of the scripting variable that will be exposed as a page
     * scope attribute.
     */
    protected String id = null;

    public String getId() {
        return (this.id);
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.logic.LocalStrings");



    /**
     * The name of the bean owning the property to be counted.
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * The name of the property to be retrieved.
     */
    protected String property = null;

    public String getProperty() {
        return (this.property);
    }

    public void setProperty(String property) {
        this.property = property;
    }


    /**
     * The scope within which to search for the specified bean.
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
     * Retrieve the required property and expose it as a scripting variable.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Retrieve the required property value
        Object value = this.collection;
        if (value == null)
            value = RequestUtils.lookup(pageContext, name, property, scope);

        // Identify the number of elements, based on the collection type
        int size = 0;
        if (value == null) {
            JspException e = new JspException
                (messages.getMessage("size.collection"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        } else if (value.getClass().isArray()) {
            size = Array.getLength(value);
        } else if (value instanceof Collection) {
            size = ((Collection) value).size();
        } else if (value instanceof Map) {
            size = ((Map) value).size();
        } else {
            JspException e = new JspException
                (messages.getMessage("size.collection"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        // Expose this size as a scripting variable
        pageContext.setAttribute(this.id, new Integer(size),
                                 PageContext.PAGE_SCOPE);
        return (SKIP_BODY);

    }


    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        collection = null;
        id = null;
        name = null;
        property = null;
        scope = null;

    }


}
