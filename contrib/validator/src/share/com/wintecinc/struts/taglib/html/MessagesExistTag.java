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
import org.apache.struts.util.RequestUtils;
import com.wintecinc.struts.action.ActionMessage;
import com.wintecinc.struts.action.ActionMessages;
import com.wintecinc.struts.action.GenericAction;

/**
 * Include the body if there are any messages.
 *
 * @author David Winterfeldt
 */

public class MessagesExistTag extends TagSupport {

    /**
     * The request attribute key for our messages (if any).
     */
    protected String name = GenericAction.MESSAGE_KEY;

    public String getName() {
	return (this.name);
    }

    public void setName(String name) {
	this.name = name;
    }

    /**
     * Check if there is a message.
     *
     * @exception JspException if a JSP exception occurs
     */
    public int doStartTag() throws JspException {
	ActionMessages messages = new ActionMessages();
	
	try {
	    Object value = pageContext.getAttribute(name, PageContext.REQUEST_SCOPE);
	    if (value == null) {
		;
	    } else if (value instanceof String) {
		messages.add(new ActionMessage((String) value));
	    } else if (value instanceof String[]) {
                String keys[] = (String[]) value;
                for (int i = 0; i < keys.length; i++)
                    messages.add(new ActionMessage(keys[i]));
            } else if (value instanceof ActionMessages) {
                messages = (ActionMessages)value;
            } else {
                JspException e = new JspException("Invalid object type for message");
                RequestUtils.saveException(pageContext, e);
                throw e;
	    }
        } catch (Exception e) {
            ;
	}

        Iterator iterator= null;	
        iterator = messages.get();

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
        name = GenericAction.MESSAGE_KEY;
    }

}
