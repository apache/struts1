/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/utils/EvalHelper.java,v 1.2 2003/03/09 05:47:27 dmkarr Exp $
 * $Revision: 1.2 $
 * $Date: 2003/03/09 05:47:27 $
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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
 *    any, must include the following acknowledgement:
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

package org.apache.strutsel.taglib.utils;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * This class is used in the <code>evaluateExpressions</code> method of each
 * Tag class.  It is used to process the original attribute value through the
 * JSTL EL engine to produce an evaluated value.  It provides functions to
 * evaluate the expression assuming it is an Object, String, Integer, or
 * Boolean result.
 */
public final class EvalHelper
{
    private EvalHelper() {}

    /**
     * Evaluates the attribute value in the JSTL EL engine, returning the raw
     * Object value of the evaluated expression.  If the original expression is
     * null, or the resulting value is null, it will return null.
     */
    public static Object eval(String      attrName,
                              String      attrValue,
                              Tag         tagObject,
                              PageContext pageContext) 
        throws JspException
    {
        Object result   = null;
        if (attrValue != null) {
            result   =
                ExpressionEvaluatorManager.
                evaluate(attrName, attrValue, Object.class, tagObject,
                         pageContext);
        }

        return (result);
    }

    /**
     * Evaluates the attribute value in the JSTL EL engine, assuming the
     * resulting value is a String object.  If the original expression is null,
     * or the resulting value is null, it will return null.
     */
    public static String evalString(String      attrName,
                                    String      attrValue,
                                    Tag         tagObject,
                                    PageContext pageContext) 
        throws JspException
    {
        Object result   = null;
        if (attrValue != null) {
            result   =
                ExpressionEvaluatorManager.
                evaluate(attrName, attrValue, String.class, tagObject,
                         pageContext);
        }

        return ((String) result);
    }

    /**
     * Evaluates the attribute value in the JSTL EL engine, assuming the
     * resulting value is an Integer object.  If the original expression is
     * null, or the resulting value is null, it will return null.
     */
    public static Integer evalInteger(String       attrName,
                                      String       attrValue,
                                      Tag          tagObject,
                                      PageContext  pageContext) 
        throws JspException
    {
        Object result   = null;
        if (attrValue != null) {
            result   = ExpressionEvaluatorManager.
                evaluate(attrName, attrValue, Integer.class, tagObject,
                         pageContext);
        }

        return ((Integer) result);
    }

    /**
     * Evaluates the attribute value in the JSTL EL engine, assuming the
     * resulting value is an Boolean object.  If the original expression is
     * null, or the resulting value is null, it will return null.
     */
    public static Boolean evalBoolean(String       attrName,
                                      String       attrValue,
                                      Tag          tagObject,
                                      PageContext  pageContext) 
        throws JspException
    {
        Object result   = null;
        if (attrValue != null) {
            result   = ExpressionEvaluatorManager.
                evaluate(attrName, attrValue, Boolean.class, tagObject,
                         pageContext);
        }

        return ((Boolean) result);
    }
}
