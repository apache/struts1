/*
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
 */


package com.wintecinc.struts.taglib.html;

import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.ErrorMessages;
import org.apache.struts.util.RequestUtils;


/**
 * Include the body if there are any error messages.
 *
 * @deprecated This tag has an equivalent in Struts (see html:messagesPresent).
 *
 * @author David Winterfeldt
 */

public class ErrorsExistTag extends TagSupport {

    /**
     * The request attribute key for our error messages.
    */
    protected String name = Action.ERROR_KEY;

    /**
     * Gets the request attribute key for error messages.
    */
    public String getName() {
	return (this.name);
    }

    /**
     * Sets the request attribute key for error messages.
    */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * The name of the property for which error messages should be returned,
     * or <code>null</code> to return all errors.
    */
    protected String property = null;

    /**
     * Gets error messages associated with a specific property.
    */
    public String getProperty() {
        return (this.property);
    }

    /**
     * Sets error messages associated with a specific property.
    */
    public void setProperty(String property) {
        this.property = property;
    }


    /**
     * Check if there is an error message.
     *
     * @exception JspException if a JSP exception occurs
     */
    public int doStartTag() throws JspException {
	ActionErrors errors = new ActionErrors();
	try {
	    Object value = pageContext.getAttribute(name, PageContext.REQUEST_SCOPE);
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
            } else {
                JspException e = new JspException("Invalid object type for error message");
                //    (messages.getMessage("errorsTag.errors",
                //                         value.getClass().getName()));
                RequestUtils.saveException(pageContext, e);
                throw e;
	    }
        } catch (Exception e) {
            ;
	}

        Iterator iterator= null;	

        if (property == null)
            iterator = errors.get();
        else
            iterator = errors.get(property);

	if (iterator.hasNext())
           return (EVAL_BODY_INCLUDE);
        else
           return (SKIP_BODY);

    }


    /**
     * Evaluate the remainder of the current page normally.
     *
     * @exception JspException if a JSP exception occurs
     */
    public int doEndTag() throws JspException {
        return (EVAL_PAGE);
    }


    /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        name = Action.ERROR_KEY;
        property = null;
    }

}
