/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/bean/DefineTag.java,v 1.20 2003/05/10 17:37:43 dgraham Exp $
 * $Revision: 1.20 $
 * $Date: 2003/05/10 17:37:43 $
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


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;


/**
 * Define a scripting variable based on the value(s) of the specified
 * bean property.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.20 $ $Date: 2003/05/10 17:37:43 $
 */

public class DefineTag extends BodyTagSupport {

    // ---------------------------------------------------- Protected variables

    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
        MessageResources.getMessageResources
        ("org.apache.struts.taglib.bean.LocalStrings");


    /**
     * The body content of this tag (if any).
     */
    protected String body = null;


    // ------------------------------------------------------------- Properties


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
     * The name of the bean owning the property to be exposed.
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


    /**
     * The scope within which the newly defined bean will be creatd.
     */
    protected String toScope = null;

    public String getToScope() {
        return (this.toScope);
    }

    public void setToScope(String toScope) {
        this.toScope = toScope;
    }


    /**
     * The fully qualified Java class name of the value to be exposed.
     */
    protected String type = null;

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }


    /**
     * The (String) value to which the defined bean will be set.
     */
    protected String value = null;

    public String getValue() {
        return (this.value);
    }

    public void setValue(String value) {
        this.value = value;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Check if we need to evaluate the body of the tag
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
       
        return (EVAL_BODY_TAG);

    }


    /**
     * Save the body content of this tag (if any), or throw a JspException
     * if the value was already defined.
     *
     * @exception JspException if value was defined by an attribute
     */
    public int doAfterBody() throws JspException {

        if (bodyContent != null) {
            body = bodyContent.getString();
            if (body != null) {
                body = body.trim();
            }
            if (body.length() < 1) {
                body = null;
            }
        }
        return (SKIP_BODY);

    }


    /**
     * Retrieve the required property and expose it as a scripting variable.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        // Enforce restriction on ways to declare the new value
        int n = 0;
        if (this.body != null) {
            n++;
        }
        if (this.name != null) {
            n++;
        }
        if (this.value != null) {
            n++;
        }
        if (n != 1) {
            JspException e =
                new JspException(messages.getMessage("define.value"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        // Retrieve the required property value
        Object value = this.value;
        if ((value == null) && (name != null)) {
            value = RequestUtils.lookup(pageContext, name, property, scope);
        }
        if ((value == null) && (body != null)) {
            value = body;
        }
        if (value == null) {
            JspException e =
                new JspException(messages.getMessage("define.null"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        // Expose this value as a scripting variable
        int inScope = PageContext.PAGE_SCOPE;
        try {
			if (toScope != null) {
				inScope = RequestUtils.getScope(toScope);
			}
		} catch (JspException e) {
			//  toScope was invalid name so we default to PAGE_SCOPE
		}
            
        pageContext.setAttribute(id, value, inScope);

        // Continue processing this page
        return (EVAL_PAGE);

    }

    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        body = null;
        id = null;
        name = null;
        property = null;
        scope = null;
        toScope = "page";
        type = null;
        value = null;

    }


}
