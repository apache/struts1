/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/logic/ELMatchTag.java,v 1.2 2002/09/28 04:43:05 dmkarr Exp $
 * $Revision: 1.2 $
 * $Date: 2002/09/28 04:43:05 $
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

package org.apache.strutsel.taglib.logic;

import org.apache.struts.taglib.logic.MatchTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELMatchTag extends MatchTag {

    private String   expr;

    public  String   getExpr() {
        return (expr);
    }
    
    public  void  setExpr(String expr) {
        this.expr = expr;
    }
    
    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    protected boolean condition(boolean desired) throws JspException {
        boolean   result   = false;
        if (getExpr() != null) {
            result   =
                ELMatchSupport.condition(desired, getExpr(), value, location,
                                         messages, pageContext);
        }
        else {
            result   = super.condition(desired);
        }
        return (result);
    }
    
    private Object   evalAttr(String   attrName,
                              String   attrValue,
                              Class    attrType)
        throws JspException, NullAttributeException
    {
        return (ExpressionUtil.evalNotNull("match", attrName, attrValue,
                                           attrType, this, pageContext));
    }
    
    private void evaluateExpressions() throws JspException {

        try {
            setCookie((String) evalAttr("cookie", getCookie(), String.class));
        } catch (NullAttributeException ex) {
            setCookie(null);
        }

        try {
            setExpr((String) evalAttr("expr", getExpr(), String.class));
        } catch (NullAttributeException ex) {
            setExpr(null);
        }

        try {
            setHeader((String) evalAttr("header", getHeader(), String.class));
        } catch (NullAttributeException ex) {
            setHeader(null);
        }

        try {
            setLocation((String) evalAttr("location", getLocation(), String.class));
        } catch (NullAttributeException ex) {
            setLocation(null);
        }

        try {
            setName((String) evalAttr("name", getName(), String.class));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setParameter((String) evalAttr("parameter", getParameter(), String.class));
        } catch (NullAttributeException ex) {
            setParameter(null);
        }

        try {
            setProperty((String) evalAttr("property", getProperty(), String.class));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String) evalAttr("scope", getScope(), String.class));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setValue((String) evalAttr("value", getValue(), String.class));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
