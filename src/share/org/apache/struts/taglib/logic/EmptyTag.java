/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/logic/EmptyTag.java,v 1.7 2003/05/10 18:06:40 dgraham Exp $
 * $Revision: 1.7 $
 * $Date: 2003/05/10 18:06:40 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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

import java.util.Collection;
import java.util.Map;
import javax.servlet.jsp.JspException;
import org.apache.struts.util.RequestUtils;

/**
 * Evalute the nested body content of this tag if the specified value
 * is empty for this request.
 *
 * @author Martin Cooper
 * @author David Graham
 * @version $Revision: 1.7 $ $Date: 2003/05/10 18:06:40 $
 * @since Struts 1.1
 */
public class EmptyTag extends ConditionalTagBase {


    // ------------------------------------------------------ Protected Methods


    /**
     * Evaluate the condition that is being tested by this particular tag,
     * and return <code>true</code> if the nested body content of this tag
     * should be evaluated, or <code>false</code> if it should be skipped.
     * This method must be implemented by concrete subclasses.
     *
     * @exception JspException if a JSP exception occurs
     */
    protected boolean condition() throws JspException {

        return (condition(true));

    }


    /**
     * Evaluate the condition that is being tested by this particular tag,
     * and return <code>true</code> if the nested body content of this tag
     * should be evaluated, or <code>false</code> if it should be skipped.
     * This method must be implemented by concrete subclasses.
     *
     * @param desired Desired outcome for a true result
     *
     * @exception JspException if a JSP exception occurs
     */
    protected boolean condition(boolean desired) throws JspException {
		if (this.name == null) {
			JspException e =
				new JspException(messages.getMessage("empty.noNameAttribute"));
			RequestUtils.saveException(pageContext, e);
			throw e;
		}

		boolean empty = true;
        
		Object value = null;
		if (this.property == null) {
			value = RequestUtils.lookup(pageContext, name, scope);
		} else {
			value = RequestUtils.lookup(pageContext, name, property, scope);
		}
        
		if (value != null) {
            
			if (value instanceof String) {
				String strValue = (String) value;
				empty = (strValue.length() < 1);
                
			} else if (value instanceof Collection) {
				Collection collValue = (Collection) value;
				empty = collValue.isEmpty();
                
			} else if (value instanceof Map) {
				Map mapValue = (Map) value;
				empty = mapValue.isEmpty();
                
			} else {
				empty = false;
			}
		}

		return (empty == desired);
    }


}
