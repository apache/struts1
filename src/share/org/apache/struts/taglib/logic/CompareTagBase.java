/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/logic/CompareTagBase.java,v 1.6 2001/02/12 21:49:54 craigmcc Exp $
 * $Revision: 1.6 $
 * $Date: 2001/02/12 21:49:54 $
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


import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyUtils;
import org.apache.struts.util.RequestUtils;


/**
 * Abstract base class for comparison tags.  Concrete subclasses need only
 * define values for desired1 and desired2.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.6 $ $Date: 2001/02/12 21:49:54 $
 */

public abstract class CompareTagBase extends ConditionalTagBase {


    // ----------------------------------------------------- Instance Variables


    /**
     * We will do a double/float comparison.
     */
    protected static final int DOUBLE_COMPARE = 0;


    /**
     * We will do a long/int comparison.
     */
    protected static final int LONG_COMPARE = 1;


    /**
     * We will do a String comparison.
     */
    protected static final int STRING_COMPARE = 2;


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources
        ("org.apache.struts.taglib.logic.LocalStrings");


    // ------------------------------------------------------------ Properties


    /**
     * The value to which the variable specified by other attributes of this
     * tag will be compared.
     */
    public String value = null;

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
    protected abstract boolean condition() throws JspException;


    /**
     * Evaluate the condition that is being tested by this particular tag,
     * and return <code>true</code> if the nested body content of this tag
     * should be evaluated, or <code>false</code> if it should be skipped.
     * This method must be implemented by concrete subclasses.
     *
     * @param desired1 First desired value for a true result (-1, 0, +1)
     * @param desired2 Second desired value for a true result (-1, 0, +1)
     *
     * @exception JspException if a JSP exception occurs
     */
    protected boolean condition(int desired1, int desired2)
        throws JspException {

        // Acquire the value and determine the test type
        int type = -1;
        double doubleValue = 0.0;
        long longValue = 0;
        if ((type < 0) && (value.length() > 0)) {
            try {
                doubleValue = Double.parseDouble(value);
                type = DOUBLE_COMPARE;
            } catch (NumberFormatException e) {
                ;
            }
        }
        if ((type < 0) && (value.length() > 0)) {
            try {
                longValue = Long.parseLong(value);
                type = LONG_COMPARE;
            } catch (NumberFormatException e) {
                ;
            }
        }
        if (type < 0) {
            type = STRING_COMPARE;
        }

        // Acquire the unconverted variable value
        Object variable = null;
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
            Object bean = RequestUtils.lookup(pageContext, name, scope);
            if (property != null) {
                if (bean == null) {
                    JspException e = new JspException
                        (messages.getMessage("logic.bean", name));
                    RequestUtils.saveException(pageContext, e);
                    throw e;
                }
                try {
                    variable = PropertyUtils.getProperty(bean, property);
                } catch (InvocationTargetException e) {
                    Throwable t = e.getTargetException();
                    if (t == null)
                        t = e;
                    RequestUtils.saveException(pageContext, t);
                    throw new JspException
                        (messages.getMessage("logic.property", name, property,
                                             t.toString()));
                } catch (Throwable t) {
                    RequestUtils.saveException(pageContext, t);
                    throw new JspException
                        (messages.getMessage("logic.property", name, property,
                                             t.toString()));
                }
            } else {
                variable = bean;
            }
        } else if (parameter != null) {
            variable =
                pageContext.getRequest().getParameter(parameter);
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

        // Perform the appropriate comparison
        int result = 0;
        if (type == DOUBLE_COMPARE) {
            try {
                double doubleVariable =
                    Double.parseDouble(variable.toString());
                if (doubleVariable < doubleValue)
                    result = -1;
                else if (doubleVariable > doubleValue)
                    result = +1;
            } catch (NumberFormatException e) {
                result = variable.toString().compareTo(value);
            }
        } else if (type == LONG_COMPARE) {
            try {
                long longVariable = Long.parseLong(variable.toString());
                if (longVariable < longValue)
                    result = -1;
                else if (longVariable > longValue)
                    result = +1;
            } catch (NumberFormatException e) {
                result = variable.toString().compareTo(value);
            }
        } else {
            result = variable.toString().compareTo(value);
        }

        // Normalize the result
        if (result < 0)
            result = -1;
        else if (result > 0)
            result = +1;

        // Return true if the result matches either desired value
        return ((result == desired1) || (result == desired2));

    }


}
