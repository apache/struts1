/*
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

import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.RequestUtils;


/**
 * Evalute to <code>true</code> if an <code>ActionMessages</code> class or a
 * class that can be converted to an <code>ActionMessages</code> class is in
 * request scope under the specified key and there is at least one message in the
 * class or for the property specified.
 *
 * @author David Winterfeldt
 * @version $Revision: 1.6 $ $Date: 2003/01/05 00:40:04 $
 * @since Struts 1.1
 */

public class MessagesPresentTag extends ConditionalTagBase {

    /**
     * If this is set to 'true', then the <code>Globals.MESSAGE_KEY</code> will
     * be used to retrieve the messages from scope.
    */
    protected String message = null;


    public MessagesPresentTag() {
        name = Globals.ERROR_KEY;
    }

    public String getMessage() {
        return (this.message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
     * and return <code>true</code> if there is at least one message in the
     * class or for the property specified.
     * This method must be implemented by concrete subclasses.
     *
     * @param desired Desired outcome for a true result
     *
     * @exception JspException if a JSP exception occurs
     */
    protected boolean condition(boolean desired) throws JspException {
        ActionMessages am = null;

        if (message != null && "true".equalsIgnoreCase(message))
           name = Globals.MESSAGE_KEY;

        // Evaluate the presence of the specified value
        boolean bMessages = false;

        try {
            // Definitely know it should be an error so
            // use method to retrieve errors.
            if (Globals.ERROR_KEY.equals(name)) {
                am = RequestUtils.getActionErrors(pageContext, name);
            } else {
                am = RequestUtils.getActionMessages(pageContext, name);
            }
        } catch (JspException e) {
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        Iterator iterator = null;

        if (property == null)
            iterator = am.get();
        else
            iterator = am.get(property);

        if (iterator.hasNext())
           bMessages = true;

        return (bMessages == desired);

    }

    /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        name = Globals.ERROR_KEY;
        message = null;
    }

}
