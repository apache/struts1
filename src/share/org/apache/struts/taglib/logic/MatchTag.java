/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/logic/MatchTag.java,v 1.7 2001/02/12 21:49:57 craigmcc Exp $
 * $Revision: 1.7 $
 * $Date: 2001/02/12 21:49:57 $
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


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.apache.struts.util.PropertyUtils;
import org.apache.struts.util.RequestUtils;


/**
 * Evalute the nested body content of this tag if the specified value
 * is a substring of the specified variable.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.7 $ $Date: 2001/02/12 21:49:57 $
 */

public class MatchTag extends ConditionalTagBase {


    // ------------------------------------------------------------- Properties


    /**
     * The location where the match must exist (<code>start</code> or
     * <code>end</code>), or <code>null</code> for anywhere.
     */
    protected String location = null;

    public String getLocation() {
        return (this.location);
    }

    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * The value to which the variable specified by other attributes of this
     * tag will be matched.
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
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        location = null;
        value = null;

    }


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
     * @param desired Desired value for a true result
     *
     * @exception JspException if a JSP exception occurs
     */
    protected boolean condition(boolean desired) throws JspException {

        // Acquire the specified variable
        String variable = null;
        if (cookie != null) {
            Cookie cookies[] =
                ((HttpServletRequest) pageContext.getRequest()).
                getCookies();
            if (cookies == null)
                cookies = new Cookie[0];
            for (int i = 0; i < cookies.length; i++) {
                if (cookie.equals(cookies[i].getName())) {
                    variable = cookies[i].getValue();
                    break;
                }
            }
        } else if (header != null) {
            variable =
                ((HttpServletRequest) pageContext.getRequest()).
                getHeader(header);
        } else if (name != null) {
            Object value =
                RequestUtils.lookup(pageContext, name, property, scope);
            if (value != null)
                variable = value.toString();
        } else if (parameter != null) {
            variable = pageContext.getRequest().getParameter(parameter);
        } else {
            JspException e = new JspException
                (messages.getMessage("logic.selector"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }
        if (variable == null) {
            JspException e = new JspException
                (messages.getMessage("logic.variable", value));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        // Perform the comparison requested by the location attribute
        boolean matched = false;
        if (location == null) {
            matched = (variable.indexOf(value) >= 0);
        } else if (location.equals("start")) {
            matched = variable.startsWith(value);
        } else if (location.equals("end")) {
            matched = variable.endsWith(value);
        } else {
            JspException e = new JspException
                (messages.getMessage("logic.location", location));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        // Return the final result
        return (matched == desired);

    }


}
