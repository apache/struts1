/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/logic/ELMatchTag.java,v 1.3 2002/10/01 04:25:51 dmkarr Exp $
 * $Revision: 1.3 $
 * $Date: 2002/10/01 04:25:51 $
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

/**
 * Evalute the nested body content of this tag if the specified value
 * is a substring of the specified variable.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.logic.MatchTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @author David M. Karr
 * @version $Revision: 1.3 $
 */
public class ELMatchTag extends MatchTag {

    /**
     * String value of expression to be evaluated.
     */
    private String   expr;

    /**
     * Returns the string value of the expression.  This value will be
     * evaluated by the JSTL EL engine.
     */
    public  String   getExpr() {
        return (expr);
    }
    
    /**
     * Sets the string value of the expression.  This expression will be
     * evaluated by the JSTL EL engine.
     */
    public  void  setExpr(String expr) {
        this.expr = expr;
    }
    
    /**
     * Releases state of custom tag so this instance can be reused.
     */
    public void release()
    {
        super.release();
        setExpr(null);
    }
    
    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    /**
     * Evaluates the condition that is being tested by this particular tag,
     * and returns <code>true</code> if the nested body content of this tag
     * should be evaluated, or <code>false</code> if it should be skipped.
     *
     * @param desired Desired value for a true result
     * @exception JspException if a JSP exception occurs
     */
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
    
    /**
     * Evaluates and returns a single attribute value, given the attribute
     * name, attribute value, and attribute type.  It uses
     * <code>ExpressionUtil.evalNotNull</code> to do the actual evaluation, and
     * it passes to this the name of the current tag, the <code>this</code>
     * pointer, and the current pageContext.
     *
     * @param attrName attribute name being evaluated
     * @param attrValue String value of attribute to be evaluated using EL
     * @param attrType Required resulting type of attribute value
     * @return Resulting attribute value
     */
    private Object   evalAttr(String   attrName,
                              String   attrValue,
                              Class    attrType)
        throws JspException, NullAttributeException
    {
        return (ExpressionUtil.evalNotNull("match", attrName, attrValue,
                                           attrType, this, pageContext));
    }
    
    /**
     * Processes all attribute values which use the JSTL expression evaluation
     * engine to determine their values.  If any evaluation fails with a
     * <code>NullAttributeException</code> it will just use <code>null</code>
     * as the value.
     *
     * @exception JspException if a JSP exception has occurred
     */
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
            setLocation((String) evalAttr("location", getLocation(),
                                          String.class));
        } catch (NullAttributeException ex) {
            setLocation(null);
        }

        try {
            setName((String) evalAttr("name", getName(), String.class));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setParameter((String) evalAttr("parameter", getParameter(),
                                           String.class));
        } catch (NullAttributeException ex) {
            setParameter(null);
        }

        try {
            setProperty((String) evalAttr("property", getProperty(),
                                          String.class));
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
